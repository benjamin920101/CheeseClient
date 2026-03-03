/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class qy
extends rd {
    private static final Predicate<alz> b = amh.a(afi.H).a(akc.a, Predicates.equalTo(akc.a.b));
    private ps c;
    private adm d;
    int a;

    public qy(ps ps2) {
        this.c = ps2;
        this.d = ps2.o;
        this.a(7);
    }

    @Override
    public boolean a() {
        if (this.c.bc().nextInt(this.c.j_() ? 50 : 1000) != 0) {
            return false;
        }
        cj cj2 = new cj(this.c.s, this.c.t, this.c.u);
        if (b.apply(this.d.p(cj2))) {
            return true;
        }
        return this.d.p(cj2.b()).c() == afi.c;
    }

    @Override
    public void c() {
        this.a = 40;
        this.d.a((pk)this.c, (byte)10);
        this.c.s().n();
    }

    @Override
    public void d() {
        this.a = 0;
    }

    @Override
    public boolean b() {
        return this.a > 0;
    }

    public int f() {
        return this.a;
    }

    @Override
    public void e() {
        this.a = Math.max(0, this.a - 1);
        if (this.a != 4) {
            return;
        }
        cj cj2 = new cj(this.c.s, this.c.t, this.c.u);
        if (b.apply(this.d.p(cj2))) {
            if (this.d.Q().b("mobGriefing")) {
                this.d.b(cj2, false);
            }
            this.c.v();
        } else {
            \u2603 = cj2.b();
            if (this.d.p(\u2603).c() == afi.c) {
                if (this.d.Q().b("mobGriefing")) {
                    this.d.b(2001, \u2603, afh.a(afi.c));
                    this.d.a(\u2603, afi.d.Q(), 2);
                }
                this.c.v();
            }
        }
    }
}

