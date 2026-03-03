/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.audio.factory;

import java.util.concurrent.ConcurrentMap;
import javax.annotation.CheckForNull;

public interface IAudioSendSystem {
    public void start();

    public void shutdown();

    default public void setContextMap(@CheckForNull ConcurrentMap<String, String> contextMap) {
    }
}

