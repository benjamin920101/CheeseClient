/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ib
implements ff<fj> {
    private int a;
    private byte b;
    private byte c;
    private int d;
    private byte e;

    public ib() {
    }

    public ib(int n2, pf pf2) {
        this.a = n2;
        this.b = (byte)(pf2.a() & 0xFF);
        this.c = (byte)(pf2.c() & 0xFF);
        this.d = pf2.b() > Short.MAX_VALUE ? Short.MAX_VALUE : pf2.b();
        this.e = (byte)(pf2.f() ? (char)'\u0001' : '\u0000');
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.b = em2.readByte();
        this.c = em2.readByte();
        this.d = em2.e();
        this.e = em2.readByte();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        em2.writeByte(this.b);
        em2.writeByte(this.c);
        em2.b(this.d);
        em2.writeByte(this.e);
    }

    public boolean a() {
        return this.d == Short.MAX_VALUE;
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public int b() {
        return this.a;
    }

    public byte c() {
        return this.b;
    }

    public byte d() {
        return this.c;
    }

    public int e() {
        return this.d;
    }

    public boolean f() {
        return this.e != 0;
    }
}

