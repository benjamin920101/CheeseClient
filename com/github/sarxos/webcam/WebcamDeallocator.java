/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamSignalHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

final class WebcamDeallocator {
    private static final WebcamSignalHandler HANDLER = new WebcamSignalHandler();
    private final Webcam[] webcams;

    private WebcamDeallocator(Webcam[] devices) {
        this.webcams = devices;
    }

    protected static void store(Webcam[] webcams) {
        if (HANDLER.get() != null) {
            throw new IllegalStateException("Deallocator is already set!");
        }
        HANDLER.set(new WebcamDeallocator(webcams));
    }

    protected static void unstore() {
        HANDLER.reset();
    }

    protected void deallocate() {
        for (Webcam w2 : this.webcams) {
            try {
                w2.dispose();
            }
            catch (Throwable t2) {
                this.caugh(t2);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void caugh(Throwable t2) {
        File f2 = new File(String.format("webcam-capture-hs-%s", System.currentTimeMillis()));
        PrintStream ps2 = null;
        try {
            ps2 = new PrintStream(f2);
            t2.printStackTrace(ps2);
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        finally {
            if (ps2 != null) {
                ps2.close();
            }
        }
    }
}

