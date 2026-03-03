/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class fp
implements ff<fj> {
    private int a;
    private UUID b;
    private int c;
    private int d;
    private int e;
    private byte f;
    private byte g;
    private int h;
    private pz i;
    private List<pz.a> j;

    public fp() {
    }

    public fp(wn wn2) {
        this.a = wn2.F();
        this.b = wn2.cd().getId();
        this.c = ns.c(wn2.s * 32.0);
        this.d = ns.c(wn2.t * 32.0);
        this.e = ns.c(wn2.u * 32.0);
        this.f = (byte)(wn2.y * 256.0f / 360.0f);
        this.g = (byte)(wn2.z * 256.0f / 360.0f);
        zx zx2 = wn2.bi.h();
        this.h = zx2 == null ? 0 : zw.b(zx2.b());
        this.i = wn2.H();
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.b = em2.g();
        this.c = em2.readInt();
        this.d = em2.readInt();
        this.e = em2.readInt();
        this.f = em2.readByte();
        this.g = em2.readByte();
        this.h = em2.readShort();
        this.j = pz.b(em2);
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        em2.a(this.b);
        em2.writeInt(this.c);
        em2.writeInt(this.d);
        em2.writeInt(this.e);
        em2.writeByte(this.f);
        em2.writeByte(this.g);
        em2.writeShort(this.h);
        this.i.a(em2);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public List<pz.a> a() {
        if (this.j == null) {
            this.j = this.i.c();
        }
        return this.j;
    }

    public int b() {
        return this.a;
    }

    public UUID c() {
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

    public byte g() {
        return this.f;
    }

    public byte h() {
        return this.g;
    }

    public int i() {
        return this.h;
    }
}

