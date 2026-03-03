/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class is
implements ff<ic> {
    private int a;
    private a b;
    private int c;

    public is() {
    }

    public is(pk pk2, a a2) {
        this(pk2, a2, 0);
    }

    public is(pk pk2, a a2, int n2) {
        this.a = pk2.F();
        this.b = a2;
        this.c = n2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.b = em2.a(a.class);
        this.c = em2.e();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        em2.a(this.b);
        em2.b(this.c);
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    public a b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public static enum a {
        a,
        b,
        c,
        d,
        e,
        f,
        g;

    }
}

