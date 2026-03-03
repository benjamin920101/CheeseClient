/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ani {
    private static final Map<File, anh> a = Maps.newHashMap();

    public static synchronized anh a(File file, int n2, int n3) {
        File file2 = new File(file, "region");
        \u2603 = new File(file2, "r." + (n2 >> 5) + "." + (n3 >> 5) + ".mca");
        anh \u26032 = a.get(\u2603);
        if (\u26032 != null) {
            return \u26032;
        }
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (a.size() >= 256) {
            ani.a();
        }
        anh \u26033 = new anh(\u2603);
        a.put(\u2603, \u26033);
        return \u26033;
    }

    public static synchronized void a() {
        for (anh anh2 : a.values()) {
            try {
                if (anh2 == null) continue;
                anh2.c();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        a.clear();
    }

    public static DataInputStream c(File file, int n2, int n3) {
        anh anh2 = ani.a(file, n2, n3);
        return anh2.a(n2 & 0x1F, n3 & 0x1F);
    }

    public static DataOutputStream d(File file, int n2, int n3) {
        anh anh2 = ani.a(file, n2, n3);
        return anh2.b(n2 & 0x1F, n3 & 0x1F);
    }
}

