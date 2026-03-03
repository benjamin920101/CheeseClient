/*
 * Decompiled with CFR 0.152.
 */
public class rs
extends ro {
    private final ts c;

    public rs(ts ts2, double d2) {
        super(ts2, d2, 8);
        this.c = ts2;
    }

    @Override
    public boolean a() {
        return this.c.cl() && !this.c.cn() && super.a();
    }

    @Override
    public boolean b() {
        return super.b();
    }

    @Override
    public void c() {
        super.c();
        this.c.cp().a(false);
    }

    @Override
    public void d() {
        super.d();
        this.c.n(false);
    }

    @Override
    public void e() {
        super.e();
        this.c.cp().a(false);
        if (!this.f()) {
            this.c.n(false);
        } else if (!this.c.cn()) {
            this.c.n(true);
        }
    }

    @Override
    protected boolean a(adm adm2, cj cj2) {
        if (!adm2.d(cj2.a())) {
            return false;
        }
        alz alz2 = adm2.p(cj2);
        afh \u26032 = alz2.c();
        if (\u26032 == afi.ae) {
            akw akw2 = adm2.s(cj2);
            if (akw2 instanceof aky && ((aky)akw2).l < 1) {
                return true;
            }
        } else {
            if (\u26032 == afi.am) {
                return true;
            }
            if (\u26032 == afi.C && alz2.b(afg.a) != afg.a.a) {
                return true;
            }
        }
        return false;
    }
}

