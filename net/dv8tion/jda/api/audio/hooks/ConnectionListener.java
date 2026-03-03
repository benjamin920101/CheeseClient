/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.audio.hooks;

import java.util.EnumSet;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.audio.SpeakingMode;
import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.entities.User;

public interface ConnectionListener {
    public void onPing(long var1);

    public void onStatusChange(@Nonnull ConnectionStatus var1);

    public void onUserSpeaking(@Nonnull User var1, boolean var2);

    default public void onUserSpeaking(@Nonnull User user, @Nonnull EnumSet<SpeakingMode> modes) {
    }

    default public void onUserSpeaking(@Nonnull User user, boolean speaking, boolean soundshare) {
    }
}

