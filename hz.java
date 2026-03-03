/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hz
implements ff<fj> {
    private int a;
    private int b;
    private int c;
    private int d;
    private byte e;
    private byte f;
    private boolean g;

    public hz() {
    }

    public hz(pk pk2) {
        this.a = pk2.F();
        this.b = ns.c(pk2.s * 32.0);
        this.c = ns.c(pk2.t * 32.0);
        this.d = ns.c(pk2.u * 32.0);
        this.e = (byte)(pk2.y * 256.0f / 360.0f);
        this.f = (byte)(pk2.z * 256.0f / 360.0f);
        this.g = pk2.C;
    }

    public hz(int n2, int n3, int n4, int n5, byte by, byte by2, boolean bl2) {
        this.a = n2;
        this.b = n3;
        this.c = n4;
        this.d = n5;
        this.e = by;
        this.f = by2;
        this.g = bl2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.b = em2.readInt();
        this.c = em2.readInt();
        this.d = em2.readInt();
        this.e = em2.readByte();
        this.f = em2.readByte();
        this.g = em2.readBoolean();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        em2.writeInt(this.b);
        em2.writeInt(this.c);
        em2.writeInt(this.d);
        em2.writeByte(this.e);
        em2.writeByte(this.f);
        em2.writeBoolean(this.g);
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

    public byte e() {
        return this.e;
    }

    public byte f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }
}

