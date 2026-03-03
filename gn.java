/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class gn
implements ff<fj> {
    private int a;

    public gn() {
    }

    public gn(int n2) {
        this.a = n2;
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
    }

    public int a() {
        return this.a;
    }
}

