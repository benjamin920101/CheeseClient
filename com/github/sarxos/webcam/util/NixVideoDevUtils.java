/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.util;

import java.io.File;
import java.io.FilenameFilter;

public class NixVideoDevUtils
implements FilenameFilter {
    private static final File DEV = new File("/dev");

    @Override
    public boolean accept(File dir, String name) {
        return dir.getName().equals("dev") && name.startsWith("video") && name.length() > 5 && Character.isDigit(name.charAt(5));
    }

    public static File[] getVideoFiles() {
        String[] names = DEV.list(new NixVideoDevUtils());
        File[] files = new File[names.length];
        for (int i2 = 0; i2 < names.length; ++i2) {
            files[i2] = new File(DEV, names[i2]);
        }
        return files;
    }
}

