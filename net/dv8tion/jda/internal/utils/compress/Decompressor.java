/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.compress;

import java.util.zip.DataFormatException;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;

public interface Decompressor {
    public static final Logger LOG = JDALogger.getLog(Decompressor.class);

    public Compression getType();

    public void reset();

    public void shutdown();

    @Nullable
    public byte[] decompress(byte[] var1) throws DataFormatException;
}

