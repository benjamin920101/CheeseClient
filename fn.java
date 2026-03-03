/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;
import java.util.List;

public class fn
implements ff<fj> {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private byte i;
    private byte j;
    private byte k;
    private pz l;
    private List<pz.a> m;

    public fn() {
    }

    public fn(pr pr2) {
        this.a = pr2.F();
        this.b = (byte)pm.a(pr2);
        this.c = ns.c(pr2.s * 32.0);
        this.d = ns.c(pr2.t * 32.0);
        this.e = ns.c(pr2.u * 32.0);
        this.i = (byte)(pr2.y * 256.0f / 360.0f);
        this.j = (byte)(pr2.z * 256.0f / 360.0f);
        this.k = (byte)(pr2.aK * 256.0f / 360.0f);
        double d2 = 3.9;
        \u2603 = pr2.v;
        \u2603 = pr2.w;
        \u2603 = pr2.x;
        if (\u2603 < -d2) {
            \u2603 = -d2;
        }
        if (\u2603 < -d2) {
            \u2603 = -d2;
        }
        if (\u2603 < -d2) {
            \u2603 = -d2;
        }
        if (\u2603 > d2) {
            \u2603 = d2;
        }
        if (\u2603 > d2) {
            \u2603 = d2;
        }
        if (\u2603 > d2) {
            \u2603 = d2;
        }
        this.f = (int)(\u2603 * 8000.0);
        this.g = (int)(\u2603 * 8000.0);
        this.h = (int)(\u2603 * 8000.0);
        this.l = pr2.H();
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.b = em2.readByte() & 0xFF;
        this.c = em2.readInt();
        this.d = em2.readInt();
        this.e = em2.readInt();
        this.i = em2.readByte();
        this.j = em2.readByte();
        this.k = em2.readByte();
        this.f = em2.readShort();
        this.g = em2.readShort();
        this.h = em2.readShort();
        this.m = pz.b(em2);
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        em2.writeByte(this.b & 0xFF);
        em2.writeInt(this.c);
        em2.writeInt(this.d);
        em2.writeInt(this.e);
        em2.writeByte(this.i);
        em2.writeByte(this.j);
        em2.writeByte(this.k);
        em2.writeShort(this.f);
        em2.writeShort(this.g);
        em2.writeShort(this.h);
        this.l.a(em2);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public List<pz.a> a() {
        if (this.m == null) {
            this.m = this.l.c();
        }
        return this.m;
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.d;
    }

    public int f() {
        return this.e;
    }

    public int g() {
        return this.f;
    }

    public int h() {
        return this.g;
    }

    public int i() {
        return this.h;
    }

    public byte j() {
        return this.i;
    }

    public byte k() {
        return this.j;
    }

    public byte l() {
        return this.k;
    }
}

