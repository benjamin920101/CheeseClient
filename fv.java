/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class fv
implements ff<fj> {
    private cj a;
    private alz b;

    public fv() {
    }

    public fv(adm adm2, cj cj2) {
        this.a = cj2;
        this.b = adm2.p(cj2);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c();
        this.b = afh.d.a(em2.e());
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        em2.b(afh.d.b(this.b));
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public alz a() {
        return this.b;
    }

    public cj b() {
        return this.a;
    }
}

