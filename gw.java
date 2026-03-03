/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class gw
implements ff<fj> {
    private cj a;

    public gw() {
    }

    public gw(cj cj2) {
        this.a = cj2;
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
    }

    public cj a() {
        return this.a;
    }
}

