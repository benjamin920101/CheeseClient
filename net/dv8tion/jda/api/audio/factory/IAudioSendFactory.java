/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.audio.factory;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.audio.factory.IAudioSendSystem;
import net.dv8tion.jda.api.audio.factory.IPacketProvider;

public interface IAudioSendFactory {
    @Nonnull
    public IAudioSendSystem createSendSystem(@Nonnull IPacketProvider var1);
}

