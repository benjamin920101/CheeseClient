/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ji
implements ff<jf> {
    private int a;

    public ji() {
    }

    public ji(int n2) {
        this.a = n2;
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
    public void a(jf jf2) {
        jf2.a(this);
    }

    public int a() {
        return this.a;
    }
}

