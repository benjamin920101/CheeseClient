/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hm
implements ff<fj> {
    private int a;
    private int b;
    private int c;
    private int d;

    public hm() {
    }

    public hm(pk pk2) {
        this(pk2.F(), pk2.v, pk2.w, pk2.x);
    }

    public hm(int n2, double d2, double d3, double d4) {
        this.a = n2;
        \u2603 = 3.9;
        if (d2 < -\u2603) {
            d2 = -\u2603;
        }
        if (d3 < -\u2603) {
            d3 = -\u2603;
        }
        if (d4 < -\u2603) {
            d4 = -\u2603;
        }
        if (d2 > \u2603) {
            d2 = \u2603;
        }
        if (d3 > \u2603) {
            d3 = \u2603;
        }
        if (d4 > \u2603) {
            d4 = \u2603;
        }
        this.b = (int)(d2 * 8000.0);
        this.c = (int)(d3 * 8000.0);
        this.d = (int)(d4 * 8000.0);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.b = em2.readShort();
        this.c = em2.readShort();
        this.d = em2.readShort();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        em2.writeShort(this.b);
        em2.writeShort(this.c);
        em2.writeShort(this.d);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }
}

