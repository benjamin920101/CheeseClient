/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.audio.factory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.entities.VoiceChannel;

@NotThreadSafe
public interface IPacketProvider {
    @Nonnull
    public String getIdentifier();

    @Nonnull
    public VoiceChannel getConnectedChannel();

    @Nonnull
    public DatagramSocket getUdpSocket();

    @Nonnull
    public InetSocketAddress getSocketAddress();

    @Nullable
    public ByteBuffer getNextPacketRaw(boolean var1);

    @Nullable
    public DatagramPacket getNextPacket(boolean var1);

    public void onConnectionError(@Nonnull ConnectionStatus var1);

    public void onConnectionLost();
}

