/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class fk
implements ff<fj> {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;

    public fk() {
    }

    public fk(pk pk2, int n2) {
        this(pk2, n2, 0);
    }

    public fk(pk pk2, int n2, int n3) {
        this.a = pk2.F();
        this.b = ns.c(pk2.s * 32.0);
        this.c = ns.c(pk2.t * 32.0);
        this.d = ns.c(pk2.u * 32.0);
        this.h = ns.d(pk2.z * 256.0f / 360.0f);
        this.i = ns.d(pk2.y * 256.0f / 360.0f);
        this.j = n2;
        this.k = n3;
        if (n3 > 0) {
            double d2 = pk2.v;
            \u2603 = pk2.w;
            \u2603 = pk2.x;
            \u2603 = 3.9;
            if (d2 < -\u2603) {
                d2 = -\u2603;
            }
            if (\u2603 < -\u2603) {
                \u2603 = -\u2603;
            }
            if (\u2603 < -\u2603) {
                \u2603 = -\u2603;
            }
            if (d2 > \u2603) {
                d2 = \u2603;
            }
            if (\u2603 > \u2603) {
                \u2603 = \u2603;
            }
            if (\u2603 > \u2603) {
                \u2603 = \u2603;
            }
            this.e = (int)(d2 * 8000.0);
            this.f = (int)(\u2603 * 8000.0);
            this.g = (int)(\u2603 * 8000.0);
        }
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.j = em2.readByte();
        this.b = em2.readInt();
        this.c = em2.readInt();
        this.d = em2.readInt();
        this.h = em2.readByte();
        this.i = em2.readByte();
        this.k = em2.readInt();
        if (this.k > 0) {
            this.e = em2.readShort();
            this.f = em2.readShort();
            this.g = em2.readShort();
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        em2.writeByte(this.j);
        em2.writeInt(this.b);
        em2.writeInt(this.c);
        em2.writeInt(this.d);
        em2.writeByte(this.h);
        em2.writeByte(this.i);
        em2.writeInt(this.k);
        if (this.k > 0) {
            em2.writeShort(this.e);
            em2.writeShort(this.f);
            em2.writeShort(this.g);
        }
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

    public int e() {
        return this.e;
    }

    public int f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }

    public int h() {
        return this.h;
    }

    public int i() {
        return this.i;
    }

    public int j() {
        return this.j;
    }

    public int k() {
        return this.k;
    }

    @Override
    public void a(int n2) {
        this.b = n2;
    }

    public void b(int n2) {
        this.c = n2;
    }

    public void c(int n2) {
        this.d = n2;
    }

    public void d(int n2) {
        this.e = n2;
    }

    public void e(int n2) {
        this.f = n2;
    }

    public void f(int n2) {
        this.g = n2;
    }

    public void g(int n2) {
        this.k = n2;
    }
}

