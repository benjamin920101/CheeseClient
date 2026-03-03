/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.audio.factory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.NoRouteToHostException;
import java.net.SocketException;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.CheckForNull;
import net.dv8tion.jda.api.audio.factory.IAudioSendSystem;
import net.dv8tion.jda.api.audio.factory.IPacketProvider;
import net.dv8tion.jda.internal.audio.AudioConnection;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.MDC;

public class DefaultSendSystem
implements IAudioSendSystem {
    private final IPacketProvider packetProvider;
    private Thread sendThread;
    private ConcurrentMap<String, String> contextMap;

    public DefaultSendSystem(IPacketProvider packetProvider) {
        this.packetProvider = packetProvider;
    }

    @Override
    public void setContextMap(@CheckForNull ConcurrentMap<String, String> contextMap) {
        this.contextMap = contextMap;
    }

    @Override
    public void start() {
        DatagramSocket udpSocket = this.packetProvider.getUdpSocket();
        this.sendThread = new Thread(() -> {
            if (this.contextMap != null) {
                MDC.setContextMap(this.contextMap);
            }
            long lastFrameSent = System.currentTimeMillis();
            boolean sentPacket = true;
            while (!udpSocket.isClosed() && !this.sendThread.isInterrupted()) {
                try {
                    boolean changeTalking = !sentPacket || System.currentTimeMillis() - lastFrameSent > 20L;
                    DatagramPacket packet = this.packetProvider.getNextPacket(changeTalking);
                    if (!(sentPacket = packet != null)) continue;
                    udpSocket.send(packet);
                }
                catch (NoRouteToHostException e2) {
                    this.packetProvider.onConnectionLost();
                }
                catch (SocketException sleepTime) {
                }
                catch (Exception e3) {
                    AudioConnection.LOG.error("Error while sending udp audio data", e3);
                }
                finally {
                    long sleepTime = 20L - (System.currentTimeMillis() - lastFrameSent);
                    if (sleepTime > 0L) {
                        try {
                            Thread.sleep(sleepTime);
                        }
                        catch (InterruptedException e4) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (System.currentTimeMillis() < lastFrameSent + 60L) {
                        lastFrameSent += 20L;
                        continue;
                    }
                    lastFrameSent = System.currentTimeMillis();
                }
            }
        });
        this.sendThread.setUncaughtExceptionHandler((thread, throwable) -> {
            JDALogger.getLog(DefaultSendSystem.class).error("Uncaught exception in audio send thread", throwable);
            this.start();
        });
        this.sendThread.setDaemon(true);
        this.sendThread.setName(this.packetProvider.getIdentifier() + " Sending Thread");
        this.sendThread.setPriority(7);
        this.sendThread.start();
    }

    @Override
    public void shutdown() {
        if (this.sendThread != null) {
            this.sendThread.interrupt();
        }
    }
}

