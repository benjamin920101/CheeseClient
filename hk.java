/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;
import java.util.List;

public class hk
implements ff<fj> {
    private int a;
    private List<pz.a> b;

    public hk() {
    }

    public hk(int n2, pz pz2, boolean bl2) {
        this.a = n2;
        this.b = bl2 ? pz2.c() : pz2.b();
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.b = pz.b(em2);
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        pz.a(this.b, em2);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public List<pz.a> a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }
}

