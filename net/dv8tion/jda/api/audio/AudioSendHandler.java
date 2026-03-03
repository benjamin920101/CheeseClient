/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.audio;

import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import javax.sound.sampled.AudioFormat;

public interface AudioSendHandler {
    public static final AudioFormat INPUT_FORMAT = new AudioFormat(48000.0f, 16, 2, true, true);

    public boolean canProvide();

    @Nullable
    public ByteBuffer provide20MsAudio();

    default public boolean isOpus() {
        return false;
    }
}

