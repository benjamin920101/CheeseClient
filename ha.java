/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ha
implements ff<fj> {
    private int a;
    private cj b;

    public ha() {
    }

    public ha(wn wn2, cj cj2) {
        this.a = wn2.F();
        this.b = cj2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.b = em2.c();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        em2.a(this.b);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public wn a(adm adm2) {
        return (wn)adm2.a(this.a);
    }

    public cj a() {
        return this.b;
    }
}

