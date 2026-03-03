/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.util;

public enum OsUtils {
    WIN,
    NIX,
    OSX;

    private static OsUtils os;

    public static final OsUtils getOS() {
        if (os == null) {
            String osp = System.getProperty("os.name").toLowerCase();
            if (osp.indexOf("win") >= 0) {
                os = WIN;
            } else if (osp.indexOf("mac") >= 0) {
                os = OSX;
            } else if (osp.indexOf("nix") >= 0 || osp.indexOf("nux") >= 0) {
                os = NIX;
            } else {
                throw new RuntimeException(osp + " is not supported");
            }
        }
        return os;
    }

    static {
        os = null;
    }
}

