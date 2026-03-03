/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hx
implements ff<fj> {
    private eu a;
    private eu b;

    public hx() {
    }

    public hx(eu eu2) {
        this.a = eu2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.d();
        this.b = em2.d();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        em2.a(this.b);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public eu a() {
        return this.a;
    }

    public eu b() {
        return this.b;
    }
}

