/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;

public final class MultipartBody
extends RequestBody {
    public static final MediaType MIXED = MediaType.get("multipart/mixed");
    public static final MediaType ALTERNATIVE = MediaType.get("multipart/alternative");
    public static final MediaType DIGEST = MediaType.get("multipart/digest");
    public static final MediaType PARALLEL = MediaType.get("multipart/parallel");
    public static final MediaType FORM = MediaType.get("multipart/form-data");
    private static final byte[] COLONSPACE = new byte[]{58, 32};
    private static final byte[] CRLF = new byte[]{13, 10};
    private static final byte[] DASHDASH = new byte[]{45, 45};
    private final ByteString boundary;
    private final MediaType originalType;
    private final MediaType contentType;
    private final List<Part> parts;
    private long contentLength = -1L;

    MultipartBody(ByteString boundary, MediaType type, List<Part> parts) {
        this.boundary = boundary;
        this.originalType = type;
        this.contentType = MediaType.get(type + "; boundary=" + boundary.utf8());
        this.parts = Util.immutableList(parts);
    }

    public MediaType type() {
        return this.originalType;
    }

    public String boundary() {
        return this.boundary.utf8();
    }

    public int size() {
        return this.parts.size();
    }

    public List<Part> parts() {
        return this.parts;
    }

    public Part part(int index) {
        return this.parts.get(index);
    }

    @Override
    public MediaType contentType() {
        return this.contentType;
    }

    @Override
    public long contentLength() throws IOException {
        long result = this.contentLength;
        if (result != -1L) {
            return result;
        }
        this.contentLength = this.writeOrCountBytes(null, true);
        return this.contentLength;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        this.writeOrCountBytes(sink, false);
    }

    private long writeOrCountBytes(@Nullable BufferedSink sink, boolean countBytes) throws IOException {
        long byteCount = 0L;
        Buffer byteCountBuffer = null;
        if (countBytes) {
            byteCountBuffer = new Buffer();
            sink = byteCountBuffer;
        }
        int partCount = this.parts.size();
        for (int p2 = 0; p2 < partCount; ++p2) {
            long contentLength;
            MediaType contentType;
            Part part = this.parts.get(p2);
            Headers headers = part.headers;
            RequestBody body = part.body;
            sink.write(DASHDASH);
            sink.write(this.boundary);
            sink.write(CRLF);
            if (headers != null) {
                int headerCount = headers.size();
                for (int h2 = 0; h2 < headerCount; ++h2) {
                    sink.writeUtf8(headers.name(h2)).write(COLONSPACE).writeUtf8(headers.value(h2)).write(CRLF);
                }
            }
            if ((contentType = body.contentType()) != null) {
                sink.writeUtf8("Content-Type: ").writeUtf8(contentType.toString()).write(CRLF);
            }
            if ((contentLength = body.contentLength()) != -1L) {
                sink.writeUtf8("Content-Length: ").writeDecimalLong(contentLength).write(CRLF);
            } else if (countBytes) {
                byteCountBuffer.clear();
                return -1L;
            }
            sink.write(CRLF);
            if (countBytes) {
                byteCount += contentLength;
            } else {
                body.writeTo(sink);
            }
            sink.write(CRLF);
        }
        sink.write(DASHDASH);
        sink.write(this.boundary);
        sink.write(DASHDASH);
        sink.write(CRLF);
        if (countBytes) {
            byteCount += byteCountBuffer.size();
            byteCountBuffer.clear();
        }
        return byteCount;
    }

    static void appendQuotedString(StringBuilder target, String key) {
        target.append('\"');
        int len = key.length();
        block5: for (int i2 = 0; i2 < len; ++i2) {
            char ch = key.charAt(i2);
            switch (ch) {
                case '\n': {
                    target.append("%0A");
                    continue block5;
                }
                case '\r': {
                    target.append("%0D");
                    continue block5;
                }
                case '\"': {
                    target.append("%22");
                    continue block5;
                }
                default: {
                    target.append(ch);
                }
            }
        }
        target.append('\"');
    }

    public static final class Builder {
        private final ByteString boundary;
        private MediaType type = MIXED;
        private final List<Part> parts = new ArrayList<Part>();

        public Builder() {
            this(UUID.randomUUID().toString());
        }

        public Builder(String boundary) {
            this.boundary = ByteString.encodeUtf8(boundary);
        }

        public Builder setType(MediaType type) {
            if (type == null) {
                throw new NullPointerException("type == null");
            }
            if (!type.type().equals("multipart")) {
                throw new IllegalArgumentException("multipart != " + type);
            }
            this.type = type;
            return this;
        }

        public Builder addPart(RequestBody body) {
            return this.addPart(Part.create(body));
        }

        public Builder addPart(@Nullable Headers headers, RequestBody body) {
            return this.addPart(Part.create(headers, body));
        }

        public Builder addFormDataPart(String name, String value) {
            return this.addPart(Part.createFormData(name, value));
        }

        public Builder addFormDataPart(String name, @Nullable String filename, RequestBody body) {
            return this.addPart(Part.createFormData(name, filename, body));
        }

        public Builder addPart(Part part) {
            if (part == null) {
                throw new NullPointerException("part == null");
            }
            this.parts.add(part);
            return this;
        }

        public MultipartBody build() {
            if (this.parts.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new MultipartBody(this.boundary, this.type, this.parts);
        }
    }

    public static final class Part {
        @Nullable
        final Headers headers;
        final RequestBody body;

        public static Part create(RequestBody body) {
            return Part.create(null, body);
        }

        public static Part create(@Nullable Headers headers, RequestBody body) {
            if (body == null) {
                throw new NullPointerException("body == null");
            }
            if (headers != null && headers.get("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            }
            if (headers != null && headers.get("Content-Length") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Length");
            }
            return new Part(headers, body);
        }

        public static Part createFormData(String name, String value) {
            return Part.createFormData(name, null, RequestBody.create(null, value));
        }

        public static Part createFormData(String name, @Nullable String filename, RequestBody body) {
            if (name == null) {
                throw new NullPointerException("name == null");
            }
            StringBuilder disposition = new StringBuilder("form-data; name=");
            MultipartBody.appendQuotedString(disposition, name);
            if (filename != null) {
                disposition.append("; filename=");
                MultipartBody.appendQuotedString(disposition, filename);
            }
            Headers headers = new Headers.Builder().addUnsafeNonAscii("Content-Disposition", disposition.toString()).build();
            return Part.create(headers, body);
        }

        private Part(@Nullable Headers headers, RequestBody body) {
            this.headers = headers;
            this.body = body;
        }

        @Nullable
        public Headers headers() {
            return this.headers;
        }

        public RequestBody body() {
            return this.body;
        }
    }
}

