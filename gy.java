/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class gy
implements ff<fj> {
    public a a;
    public int b;
    public int c;
    public int d;
    public String e;

    public gy() {
    }

    public gy(ov ov2, a a2) {
        this.a = a2;
        pr pr2 = ov2.c();
        switch (a2) {
            case b: {
                this.d = ov2.f();
                this.c = pr2 == null ? -1 : pr2.F();
                break;
            }
            case c: {
                this.b = ov2.h().F();
                this.c = pr2 == null ? -1 : pr2.F();
                this.e = ov2.b().c();
            }
        }
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.a(a.class);
        if (this.a == gy$a.b) {
            this.d = em2.e();
            this.c = em2.readInt();
        } else if (this.a == gy$a.c) {
            this.b = em2.e();
            this.c = em2.readInt();
            this.e = em2.c(Short.MAX_VALUE);
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        if (this.a == gy$a.b) {
            em2.b(this.d);
            em2.writeInt(this.c);
        } else if (this.a == gy$a.c) {
            em2.b(this.b);
            em2.writeInt(this.c);
            em2.a(this.e);
        }
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public static enum a {
        a,
        b,
        c;

    }
}

