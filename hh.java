/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hh
implements ff<fj> {
    public int a;

    public hh() {
    }

    public hh(pk pk2) {
        this.a = pk2.F();
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public pk a(adm adm2) {
        return adm2.a(this.a);
    }
}

