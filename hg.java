/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hg
implements ff<fj> {
    private a a;
    private int b;
    private double c;
    private double d;
    private double e;
    private double f;
    private long g;
    private int h;
    private int i;

    public hg() {
    }

    public hg(ams ams2, a a2) {
        this.a = a2;
        this.c = ams2.f();
        this.d = ams2.g();
        this.f = ams2.h();
        this.e = ams2.j();
        this.g = ams2.i();
        this.b = ams2.l();
        this.i = ams2.q();
        this.h = ams2.p();
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.a(a.class);
        switch (this.a) {
            case a: {
                this.e = em2.readDouble();
                break;
            }
            case b: {
                this.f = em2.readDouble();
                this.e = em2.readDouble();
                this.g = em2.f();
                break;
            }
            case c: {
                this.c = em2.readDouble();
                this.d = em2.readDouble();
                break;
            }
            case f: {
                this.i = em2.e();
                break;
            }
            case e: {
                this.h = em2.e();
                break;
            }
            case d: {
                this.c = em2.readDouble();
                this.d = em2.readDouble();
                this.f = em2.readDouble();
                this.e = em2.readDouble();
                this.g = em2.f();
                this.b = em2.e();
                this.i = em2.e();
                this.h = em2.e();
            }
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        switch (this.a) {
            case a: {
                em2.writeDouble(this.e);
                break;
            }
            case b: {
                em2.writeDouble(this.f);
                em2.writeDouble(this.e);
                em2.b(this.g);
                break;
            }
            case c: {
                em2.writeDouble(this.c);
                em2.writeDouble(this.d);
                break;
            }
            case e: {
                em2.b(this.h);
                break;
            }
            case f: {
                em2.b(this.i);
                break;
            }
            case d: {
                em2.writeDouble(this.c);
                em2.writeDouble(this.d);
                em2.writeDouble(this.f);
                em2.writeDouble(this.e);
                em2.b(this.g);
                em2.b(this.b);
                em2.b(this.i);
                em2.b(this.h);
            }
        }
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    @Override
    public void a(ams ams2) {
        switch (this.a) {
            case a: {
                ams2.a(this.e);
                break;
            }
            case b: {
                ams2.a(this.f, this.e, this.g);
                break;
            }
            case c: {
                ams2.c(this.c, this.d);
                break;
            }
            case d: {
                ams2.c(this.c, this.d);
                if (this.g > 0L) {
                    ams2.a(this.f, this.e, this.g);
                } else {
                    ams2.a(this.e);
                }
                ams2.a(this.b);
                ams2.c(this.i);
                ams2.b(this.h);
                break;
            }
            case e: {
                ams2.b(this.h);
                break;
            }
            case f: {
                ams2.c(this.i);
            }
        }
    }

    public static enum a {
        a,
        b,
        c,
        d,
        e,
        f;

    }
}

