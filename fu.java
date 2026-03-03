/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class fu
implements ff<fj> {
    private cj a;
    private int b;
    private int c;
    private afh d;

    public fu() {
    }

    public fu(cj cj2, afh afh2, int n2, int n3) {
        this.a = cj2;
        this.b = n2;
        this.c = n3;
        this.d = afh2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c();
        this.b = em2.readUnsignedByte();
        this.c = em2.readUnsignedByte();
        this.d = afh.c(em2.e() & 0xFFF);
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        em2.writeByte(this.b);
        em2.writeByte(this.c);
        em2.b(afh.a(this.d) & 0xFFF);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public cj a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public afh d() {
        return this.d;
    }
}

