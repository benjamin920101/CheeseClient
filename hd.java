/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hd
implements ff<fj> {
    private String a;
    private String b;

    public hd() {
    }

    public hd(String string, String string2) {
        this.a = string;
        this.b = string2;
        if (string2.length() > 40) {
            throw new IllegalArgumentException("Hash is too long (max 40, was " + string2.length() + ")");
        }
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c(Short.MAX_VALUE);
        this.b = em2.c(40);
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

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }
}

