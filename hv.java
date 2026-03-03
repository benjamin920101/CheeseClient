/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hv
implements ff<fj> {
    private a a;
    private eu b;
    private int c;
    private int d;
    private int e;

    public hv() {
    }

    public hv(a a2, eu eu2) {
        this(a2, eu2, -1, -1, -1);
    }

    public hv(int n2, int n3, int n4) {
        this(hv$a.c, null, n2, n3, n4);
    }

    public hv(a a2, eu eu2, int n2, int n3, int n4) {
        this.a = a2;
        this.b = eu2;
        this.c = n2;
        this.d = n3;
        this.e = n4;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.a(a.class);
        if (this.a == hv$a.a || this.a == hv$a.b) {
            this.b = em2.d();
        }
        if (this.a == hv$a.c) {
            this.c = em2.readInt();
            this.d = em2.readInt();
            this.e = em2.readInt();
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        if (this.a == hv$a.a || this.a == hv$a.b) {
            em2.a(this.b);
        }
        if (this.a == hv$a.c) {
            em2.writeInt(this.c);
            em2.writeInt(this.d);
            em2.writeInt(this.e);
        }
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public a a() {
        return this.a;
    }

    public eu b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public static enum a {
        a,
        b,
        c,
        d,
        e;


        public static a a(String string) {
            for (a a2 : hv$a.values()) {
                if (!a2.name().equalsIgnoreCase(string)) continue;
                return a2;
            }
            return a;
        }

        public static String[] a() {
            String[] stringArray = new String[hv$a.values().length];
            int \u26032 = 0;
            for (a a2 : hv$a.values()) {
                stringArray[\u26032++] = a2.name().toLowerCase();
            }
            return stringArray;
        }
    }
}

