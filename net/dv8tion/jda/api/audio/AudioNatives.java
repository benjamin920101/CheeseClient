/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.audio;

import club.minnced.opus.util.OpusLibrary;
import java.io.IOException;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;

public final class AudioNatives {
    private static final Logger LOG = JDALogger.getLog(AudioNatives.class);
    private static boolean initialized;
    private static boolean audioSupported;

    private AudioNatives() {
    }

    public static boolean isAudioSupported() {
        return audioSupported;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static synchronized boolean ensureOpus() {
        if (initialized) {
            return audioSupported;
        }
        initialized = true;
        try {
            if (OpusLibrary.isInitialized()) {
                audioSupported = true;
                boolean bl2 = true;
                return bl2;
            }
            audioSupported = OpusLibrary.loadFromJar();
        }
        catch (Throwable e2) {
            AudioNatives.handleException(e2);
        }
        finally {
            if (audioSupported) {
                LOG.info("Audio System successfully setup!");
            } else {
                LOG.info("Audio System encountered problems while loading, thus, is disabled.");
            }
        }
        return audioSupported;
    }

    private static void handleException(Throwable e2) {
        if (e2 instanceof UnsupportedOperationException) {
            LOG.error("Sorry, JDA's audio system doesn't support this system.\n{}", (Object)e2.getMessage());
        } else if (e2 instanceof NoClassDefFoundError) {
            LOG.error("Missing opus dependency, unable to initialize audio!");
        } else if (e2 instanceof IOException) {
            LOG.error("There was an IO Exception when setting up the temp files for audio.", e2);
        } else if (e2 instanceof UnsatisfiedLinkError) {
            LOG.error("JDA encountered a problem when attempting to load the Native libraries. Contact a DEV.", e2);
        } else {
            if (e2 instanceof Error) {
                throw (Error)e2;
            }
            LOG.error("An unknown exception occurred while attempting to setup JDA's audio system!", e2);
        }
    }
}

