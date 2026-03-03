/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ht
implements ff<fj> {
    private cj a;

    public ht() {
    }

    public ht(cj cj2) {
        this.a = cj2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public cj a() {
        return this.a;
    }
}

