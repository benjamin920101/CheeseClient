/*
 * Decompiled with CFR 0.152.
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bmv {
    private static final Logger a = LogManager.getLogger();
    private final a b;
    private final b c;
    private int d;
    private int e;

    public bmv(int n2, a a2, b b2, int n3) {
        if (!this.a(n2, b2)) {
            a.warn("Multiple vertex elements of the same type other than UVs are not supported. Forcing type to UV.");
            this.c = bmv$b.d;
        } else {
            this.c = b2;
        }
        this.b = a2;
        this.d = n2;
        this.e = n3;
    }

    private final boolean a(int n2, b b2) {
        return n2 == 0 || b2 == bmv$b.d;
    }

    public final a a() {
        return this.b;
    }

    public final b b() {
        return this.c;
    }

    public final int c() {
        return this.e;
    }

    public final int d() {
        return this.d;
    }

    public String toString() {
        return this.e + "," + this.c.a() + "," + this.b.b();
    }

    public final int e() {
        return this.b.a() * this.e;
    }

    public final boolean f() {
        return this.c == bmv$b.a;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        bmv bmv2 = (bmv)object;
        if (this.e != bmv2.e) {
            return false;
        }
        if (this.d != bmv2.d) {
            return false;
        }
        if (this.b != bmv2.b) {
            return false;
        }
        return this.c == bmv2.c;
    }

    public int hashCode() {
        int n2 = this.b.hashCode();
        n2 = 31 * n2 + this.c.hashCode();
        n2 = 31 * n2 + this.d;
        n2 = 31 * n2 + this.e;
        return n2;
    }

    public static enum a {
        a(4, "Float", 5126),
        b(1, "Unsigned Byte", 5121),
        c(1, "Byte", 5120),
        d(2, "Unsigned Short", 5123),
        e(2, "Short", 5122),
        f(4, "Unsigned Int", 5125),
        g(4, "Int", 5124);

        private final int h;
        private final String i;
        private final int j;

        private a(int n3, String string2, int n4) {
            this.h = n3;
            this.i = string2;
            this.j = n4;
        }

        public int a() {
            return this.h;
        }

        public String b() {
            return this.i;
        }

        public int c() {
            return this.j;
        }
    }

    public static enum b {
        a("Position"),
        b("Normal"),
        c("Vertex Color"),
        d("UV"),
        e("Bone Matrix"),
        f("Blend Weight"),
        g("Padding");

        private final String h;

        private b(String string2) {
            this.h = string2;
        }

        public String a() {
            return this.h;
        }
    }
}

