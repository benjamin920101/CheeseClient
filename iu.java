/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class iu
implements ff<ic> {
    private String a;
    private a b;

    public iu() {
    }

    public iu(String string2, a a2) {
        String string2;
        if (string2.length() > 40) {
            string2 = string2.substring(0, 40);
        }
        this.a = string2;
        this.b = a2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c(40);
        this.b = em2.a(a.class);
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        em2.a(this.b);
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    public static enum a {
        a,
        b,
        c,
        d;

    }
}

