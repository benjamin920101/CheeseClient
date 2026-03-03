/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.audio.hooks;

import java.util.EnumSet;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.audio.SpeakingMode;
import net.dv8tion.jda.api.audio.hooks.ConnectionListener;
import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListenerProxy
implements ConnectionListener {
    private static final Logger log = LoggerFactory.getLogger(ListenerProxy.class);
    private volatile ConnectionListener listener = null;

    @Override
    public void onPing(long ping) {
        block4: {
            if (this.listener == null) {
                return;
            }
            ConnectionListener listener = this.listener;
            try {
                if (listener != null) {
                    listener.onPing(ping);
                }
            }
            catch (Throwable t2) {
                log.error("The ConnectionListener encountered and uncaught exception", t2);
                if (!(t2 instanceof Error)) break block4;
                throw (Error)t2;
            }
        }
    }

    @Override
    public void onStatusChange(@Nonnull ConnectionStatus status) {
        block4: {
            if (this.listener == null) {
                return;
            }
            ConnectionListener listener = this.listener;
            try {
                if (listener != null) {
                    listener.onStatusChange(status);
                }
            }
            catch (Throwable t2) {
                log.error("The ConnectionListener encountered and uncaught exception", t2);
                if (!(t2 instanceof Error)) break block4;
                throw (Error)t2;
            }
        }
    }

    @Override
    public void onUserSpeaking(@Nonnull User user, @Nonnull EnumSet<SpeakingMode> modes) {
        block4: {
            if (this.listener == null) {
                return;
            }
            ConnectionListener listener = this.listener;
            try {
                if (listener != null) {
                    listener.onUserSpeaking(user, modes);
                    listener.onUserSpeaking(user, modes.contains((Object)SpeakingMode.VOICE));
                    listener.onUserSpeaking(user, modes.contains((Object)SpeakingMode.VOICE), modes.contains((Object)SpeakingMode.SOUNDSHARE));
                }
            }
            catch (Throwable t2) {
                log.error("The ConnectionListener encountered and uncaught exception", t2);
                if (!(t2 instanceof Error)) break block4;
                throw (Error)t2;
            }
        }
    }

    @Override
    public void onUserSpeaking(@Nonnull User user, boolean speaking) {
    }

    public void setListener(ConnectionListener listener) {
        this.listener = listener;
    }

    public ConnectionListener getListener() {
        return this.listener;
    }
}

