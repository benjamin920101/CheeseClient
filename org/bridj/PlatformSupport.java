/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.io.IOException;
import org.bridj.NativeLibrary;
import org.bridj.Platform;
import org.bridj.util.ClassDefiner;

class PlatformSupport {
    private static final PlatformSupport instance;

    PlatformSupport() {
    }

    public ClassDefiner getClassDefiner(ClassDefiner defaultDefiner, ClassLoader parentClassLoader) {
        return defaultDefiner;
    }

    public static PlatformSupport getInstance() {
        return instance;
    }

    public NativeLibrary loadNativeLibrary(String name) throws IOException {
        return null;
    }

    static {
        PlatformSupport _instance = null;
        if (Platform.isAndroid()) {
            try {
                _instance = (PlatformSupport)Class.forName("org.bridj.AndroidSupport").newInstance();
            }
            catch (Exception ex2) {
                throw new RuntimeException("Failed to instantiate the Android support class... Was the BridJ jar tampered with / trimmed too much ?", ex2);
            }
        }
        if (_instance == null) {
            _instance = new PlatformSupport();
        }
        instance = _instance;
    }
}

