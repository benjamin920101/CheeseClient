/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class gl
implements ff<fj> {
    private int a;

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

    public int a() {
        return this.a;
    }
}

