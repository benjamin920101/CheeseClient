/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class gj
implements ff<fj> {
    private int a;
    private dn b;

    public gj() {
    }

    public gj(int n2, dn dn2) {
        this.a = n2;
        this.b = dn2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.b = em2.h();
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

    public dn a() {
        return this.b;
    }

    public pk a(adm adm2) {
        return adm2.a(this.a);
    }
}

