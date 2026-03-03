/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hs
implements ff<fj> {
    private String a = "";
    private String b = "";
    private int c;
    private a d;

    public hs() {
    }

    public hs(aum aum2) {
        this.a = aum2.e();
        this.b = aum2.d().b();
        this.c = aum2.c();
        this.d = hs$a.a;
    }

    public hs(String string) {
        this.a = string;
        this.b = "";
        this.c = 0;
        this.d = hs$a.b;
    }

    public hs(String string, auk auk2) {
        this.a = string;
        this.b = auk2.b();
        this.c = 0;
        this.d = hs$a.b;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c(40);
        this.d = em2.a(a.class);
        this.b = em2.c(16);
        if (this.d != hs$a.b) {
            this.c = em2.e();
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        em2.a(this.d);
        em2.a(this.b);
        if (this.d != hs$a.b) {
            em2.b(this.c);
        }
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

    public int c() {
        return this.c;
    }

    public a d() {
        return this.d;
    }

    public static enum a {
        a,
        b;

    }
}

