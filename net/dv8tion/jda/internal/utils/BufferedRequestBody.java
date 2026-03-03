/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils;

import java.io.IOException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

public class BufferedRequestBody
extends RequestBody {
    private final Source source;
    private final MediaType type;
    private byte[] data;

    public BufferedRequestBody(Source source, MediaType type) {
        this.source = source;
        this.type = type;
    }

    @Override
    @Nullable
    public MediaType contentType() {
        return this.type;
    }

    @Override
    public void writeTo(@Nonnull BufferedSink sink) throws IOException {
        if (this.data != null) {
            sink.write(this.data);
            return;
        }
        try (BufferedSource s2 = Okio.buffer(this.source);){
            this.data = s2.readByteArray();
            sink.write(this.data);
        }
    }
}

