/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.audio.factory;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.audio.factory.DefaultSendSystem;
import net.dv8tion.jda.api.audio.factory.IAudioSendFactory;
import net.dv8tion.jda.api.audio.factory.IAudioSendSystem;
import net.dv8tion.jda.api.audio.factory.IPacketProvider;

public class DefaultSendFactory
implements IAudioSendFactory {
    @Override
    @Nonnull
    public IAudioSendSystem createSendSystem(@Nonnull IPacketProvider packetProvider) {
        return new DefaultSendSystem(packetProvider);
    }
}

