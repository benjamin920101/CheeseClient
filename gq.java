/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class gq
implements ff<fj> {
    private int a;
    private cj b;
    private int c;
    private boolean d;

    public gq() {
    }

    public gq(int n2, cj cj2, int n3, boolean bl2) {
        this.a = n2;
        this.b = cj2;
        this.c = n3;
        this.d = bl2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readInt();
        this.b = em2.c();
        this.c = em2.readInt();
        this.d = em2.readBoolean();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeInt(this.a);
        em2.a(this.b);
        em2.writeInt(this.c);
        em2.writeBoolean(this.d);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public boolean a() {
        return this.d;
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return this.c;
    }

    public cj d() {
        return this.b;
    }
}

