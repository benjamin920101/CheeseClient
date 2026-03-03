/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ja
implements ff<ic> {
    private static final cj a = new cj(-1, -1, -1);
    private cj b;
    private int c;
    private zx d;
    private float e;
    private float f;
    private float g;

    public ja() {
    }

    public ja(zx zx2) {
        this(a, 255, zx2, 0.0f, 0.0f, 0.0f);
    }

    public ja(cj cj2, int n2, zx zx2, float f2, float f3, float f4) {
        this.b = cj2;
        this.c = n2;
        this.d = zx2 != null ? zx2.k() : null;
        this.e = f2;
        this.f = f3;
        this.g = f4;
    }

    @Override
    public void a(em em2) throws IOException {
        this.b = em2.c();
        this.c = em2.readUnsignedByte();
        this.d = em2.i();
        this.e = (float)em2.readUnsignedByte() / 16.0f;
        this.f = (float)em2.readUnsignedByte() / 16.0f;
        this.g = (float)em2.readUnsignedByte() / 16.0f;
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.b);
        em2.writeByte(this.c);
        em2.a(this.d);
        em2.writeByte((int)(this.e * 16.0f));
        em2.writeByte((int)(this.f * 16.0f));
        em2.writeByte((int)(this.g * 16.0f));
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    public cj a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public zx c() {
        return this.d;
    }

    public float d() {
        return this.e;
    }

    public float e() {
        return this.f;
    }

    public float f() {
        return this.g;
    }
}

