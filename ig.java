/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ig
implements ff<ic> {
    private a a;

    public ig() {
    }

    public ig(a a2) {
        this.a = a2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.a(a.class);
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    public a a() {
        return this.a;
    }

    public static enum a {
        a,
        b,
        c;

    }
}

