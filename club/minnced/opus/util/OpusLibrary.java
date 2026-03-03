/*
 * Decompiled with CFR 0.152.
 */
package club.minnced.opus.util;

import club.minnced.opus.util.NativeUtil;
import com.sun.jna.Platform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class OpusLibrary {
    private static boolean initialized = false;
    private static final String SUPPORTED_SYSTEMS;
    private static final Map<String, String> platforms;

    private OpusLibrary() {
    }

    public static List<String> getSupportedPlatforms() {
        return Collections.unmodifiableList(new ArrayList<String>(platforms.keySet()));
    }

    public static boolean isSupportedPlatform() {
        return platforms.containsKey(Platform.RESOURCE_PREFIX);
    }

    public static synchronized boolean isInitialized() {
        return initialized;
    }

    public static synchronized boolean loadFrom(String absolutePath) {
        if (initialized) {
            return false;
        }
        System.load(absolutePath);
        System.setProperty("opus.lib", absolutePath);
        initialized = true;
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static synchronized boolean loadFromJar() throws IOException {
        if (initialized) {
            return false;
        }
        String nativesRoot = "";
        try {
            String platform = Platform.RESOURCE_PREFIX;
            String ext = platforms.get(platform);
            if (ext == null) {
                throw new UnsupportedOperationException(SUPPORTED_SYSTEMS);
            }
            String tmpRoot = String.format("/natives/%s/libopus.%s", platform, ext);
            NativeUtil.loadLibraryFromJar(tmpRoot);
            nativesRoot = tmpRoot;
            initialized = true;
        }
        finally {
            System.setProperty("opus.lib", nativesRoot);
        }
        return true;
    }

    static {
        platforms = new HashMap<String, String>(10);
        platforms.put("darwin", "dylib");
        platforms.put("linux-arm", "so");
        platforms.put("linux-aarch64", "so");
        platforms.put("linux-x86", "so");
        platforms.put("linux-x86-64", "so");
        platforms.put("win32-x86", "dll");
        platforms.put("win32-x86-64", "dll");
        SUPPORTED_SYSTEMS = "Supported Systems: " + platforms.keySet() + "\nCurrent Operating system: " + Platform.RESOURCE_PREFIX;
    }
}

