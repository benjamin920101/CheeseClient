/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

public abstract class ResponseBody
implements Closeable {
    @Nullable
    private Reader reader;

    @Nullable
    public abstract MediaType contentType();

    public abstract long contentLength();

    public final InputStream byteStream() {
        return this.source().inputStream();
    }

    public abstract BufferedSource source();

    public final byte[] bytes() throws IOException {
        byte[] bytes;
        long contentLength = this.contentLength();
        if (contentLength > Integer.MAX_VALUE) {
            throw new IOException("Cannot buffer entire body for content length: " + contentLength);
        }
        try (BufferedSource source = this.source();){
            bytes = source.readByteArray();
        }
        if (contentLength != -1L && contentLength != (long)bytes.length) {
            throw new IOException("Content-Length (" + contentLength + ") and stream length (" + bytes.length + ") disagree");
        }
        return bytes;
    }

    public final Reader charStream() {
        Reader r2 = this.reader;
        return r2 != null ? r2 : (this.reader = new BomAwareReader(this.source(), this.charset()));
    }

    public final String string() throws IOException {
        try (BufferedSource source = this.source();){
            Charset charset = Util.bomAwareCharset(source, this.charset());
            String string = source.readString(charset);
            return string;
        }
    }

    private Charset charset() {
        MediaType contentType = this.contentType();
        return contentType != null ? contentType.charset(StandardCharsets.UTF_8) : StandardCharsets.UTF_8;
    }

    @Override
    public void close() {
        Util.closeQuietly(this.source());
    }

    public static ResponseBody create(@Nullable MediaType contentType, String content) {
        Charset charset = StandardCharsets.UTF_8;
        if (contentType != null && (charset = contentType.charset()) == null) {
            charset = StandardCharsets.UTF_8;
            contentType = MediaType.parse(contentType + "; charset=utf-8");
        }
        Buffer buffer = new Buffer().writeString(content, charset);
        return ResponseBody.create(contentType, buffer.size(), buffer);
    }

    public static ResponseBody create(@Nullable MediaType contentType, byte[] content) {
        Buffer buffer = new Buffer().write(content);
        return ResponseBody.create(contentType, content.length, buffer);
    }

    public static ResponseBody create(@Nullable MediaType contentType, ByteString content) {
        Buffer buffer = new Buffer().write(content);
        return ResponseBody.create(contentType, content.size(), buffer);
    }

    public static ResponseBody create(final @Nullable MediaType contentType, final long contentLength, final BufferedSource content) {
        if (content == null) {
            throw new NullPointerException("source == null");
        }
        return new ResponseBody(){

            @Override
            @Nullable
            public MediaType contentType() {
                return contentType;
            }

            @Override
            public long contentLength() {
                return contentLength;
            }

            @Override
            public BufferedSource source() {
                return content;
            }
        };
    }

    static final class BomAwareReader
    extends Reader {
        private final BufferedSource source;
        private final Charset charset;
        private boolean closed;
        @Nullable
        private Reader delegate;

        BomAwareReader(BufferedSource source, Charset charset) {
            this.source = source;
            this.charset = charset;
        }

        @Override
        public int read(char[] cbuf, int off, int len) throws IOException {
            if (this.closed) {
                throw new IOException("Stream closed");
            }
            Reader delegate = this.delegate;
            if (delegate == null) {
                Charset charset = Util.bomAwareCharset(this.source, this.charset);
                delegate = this.delegate = new InputStreamReader(this.source.inputStream(), charset);
            }
            return delegate.read(cbuf, off, len);
        }

        @Override
        public void close() throws IOException {
            this.closed = true;
            if (this.delegate != null) {
                this.delegate.close();
            } else {
                this.source.close();
            }
        }
    }
}

