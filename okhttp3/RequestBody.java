/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Source;

public abstract class RequestBody {
    @Nullable
    public abstract MediaType contentType();

    public long contentLength() throws IOException {
        return -1L;
    }

    public abstract void writeTo(BufferedSink var1) throws IOException;

    public static RequestBody create(@Nullable MediaType contentType, String content) {
        Charset charset = StandardCharsets.UTF_8;
        if (contentType != null && (charset = contentType.charset()) == null) {
            charset = StandardCharsets.UTF_8;
            contentType = MediaType.parse(contentType + "; charset=utf-8");
        }
        byte[] bytes = content.getBytes(charset);
        return RequestBody.create(contentType, bytes);
    }

    public static RequestBody create(final @Nullable MediaType contentType, final ByteString content) {
        return new RequestBody(){

            @Override
            @Nullable
            public MediaType contentType() {
                return contentType;
            }

            @Override
            public long contentLength() throws IOException {
                return content.size();
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.write(content);
            }
        };
    }

    public static RequestBody create(@Nullable MediaType contentType, byte[] content) {
        return RequestBody.create(contentType, content, 0, content.length);
    }

    public static RequestBody create(final @Nullable MediaType contentType, final byte[] content, final int offset, final int byteCount) {
        if (content == null) {
            throw new NullPointerException("content == null");
        }
        Util.checkOffsetAndCount(content.length, offset, byteCount);
        return new RequestBody(){

            @Override
            @Nullable
            public MediaType contentType() {
                return contentType;
            }

            @Override
            public long contentLength() {
                return byteCount;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.write(content, offset, byteCount);
            }
        };
    }

    public static RequestBody create(final @Nullable MediaType contentType, final File file) {
        if (file == null) {
            throw new NullPointerException("file == null");
        }
        return new RequestBody(){

            @Override
            @Nullable
            public MediaType contentType() {
                return contentType;
            }

            @Override
            public long contentLength() {
                return file.length();
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                try (Source source = Okio.source(file);){
                    sink.writeAll(source);
                }
            }
        };
    }
}

