/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.properties.PropertyMap;
import java.io.File;
import java.net.Proxy;

public class bao {
    public final e a;
    public final a b;
    public final b c;
    public final c d;
    public final d e;

    public bao(e e2, a a2, b b2, c c2, d d2) {
        this.a = e2;
        this.b = a2;
        this.c = b2;
        this.d = c2;
        this.e = d2;
    }

    public static class d {
        public final String a;
        public final int b;

        public d(String string, int n2) {
            this.a = string;
            this.b = n2;
        }
    }

    public static class b {
        public final File a;
        public final File b;
        public final File c;
        public final String d;

        public b(File file, File file2, File file3, String string) {
            this.a = file;
            this.b = file2;
            this.c = file3;
            this.d = string;
        }
    }

    public static class a {
        public final int a;
        public final int b;
        public final boolean c;
        public final boolean d;

        public a(int n2, int n3, boolean bl2, boolean bl3) {
            this.a = n2;
            this.b = n3;
            this.c = bl2;
            this.d = bl3;
        }
    }

    public static class e {
        public final avm a;
        public final PropertyMap b;
        public final PropertyMap c;
        public final Proxy d;

        public e(avm avm2, PropertyMap propertyMap, PropertyMap propertyMap2, Proxy proxy) {
            this.a = avm2;
            this.b = propertyMap;
            this.c = propertyMap2;
            this.d = proxy;
        }
    }

    public static class c {
        public final boolean a;
        public final String b;

        public c(boolean bl2, String string) {
            this.a = bl2;
            this.b = string;
        }
    }
}

