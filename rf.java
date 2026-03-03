/*
 * Decompiled with CFR 0.152.
 */
public class rf
extends ro {
    private final wi c;
    private boolean d;
    private boolean e;
    private int f;

    public rf(wi wi2, double d2) {
        super(wi2, d2, 16);
        this.c = wi2;
    }

    @Override
    public boolean a() {
        if (this.a <= 0) {
            if (!this.c.o.Q().b("mobGriefing")) {
                return false;
            }
            this.f = -1;
            this.d = this.c.cu();
            this.e = this.c.ct();
        }
        return super.a();
    }

    @Override
    public boolean b() {
        return this.f >= 0 && super.b();
    }

    @Override
    public void c() {
        super.c();
    }

    @Override
    public void d() {
        super.d();
    }

    @Override
    public void e() {
        super.e();
        this.c.p().a((double)this.b.n() + 0.5, this.b.o() + 1, (double)this.b.p() + 0.5, 10.0f, this.c.bQ());
        if (this.f()) {
            adm adm2 = this.c.o;
            cj \u26032 = this.b.a();
            alz \u26033 = adm2.p(\u26032);
            afh \u26034 = \u26033.c();
            if (this.f == 0 && \u26034 instanceof afz && \u26033.b(afz.a) == 7) {
                adm2.b(\u26032, true);
            } else if (this.f == 1 && \u26034 == afi.a) {
                oq oq2 = this.c.cq();
                for (int i2 = 0; i2 < oq2.o_(); ++i2) {
                    zx zx2 = oq2.a(i2);
                    boolean \u26035 = false;
                    if (zx2 != null) {
                        if (zx2.b() == zy.N) {
                            adm2.a(\u26032, afi.aj.Q(), 3);
                            \u26035 = true;
                        } else if (zx2.b() == zy.bS) {
                            adm2.a(\u26032, afi.cc.Q(), 3);
                            \u26035 = true;
                        } else if (zx2.b() == zy.bR) {
                            adm2.a(\u26032, afi.cb.Q(), 3);
                            \u26035 = true;
                        }
                    }
                    if (!\u26035) continue;
                    --zx2.b;
                    if (zx2.b > 0) break;
                    oq2.a(i2, null);
                    break;
                }
            }
            this.f = -1;
            this.a = 10;
        }
    }

    @Override
    protected boolean a(adm adm2, cj cj2) {
        afh \u26032 = adm2.p(cj2).c();
        if (\u26032 == afi.ak) {
            alz alz2 = adm2.p(cj2 = cj2.a());
            \u26032 = alz2.c();
            if (\u26032 instanceof afz && alz2.b(afz.a) == 7 && this.e && (this.f == 0 || this.f < 0)) {
                this.f = 0;
                return true;
            }
            if (\u26032 == afi.a && this.d && (this.f == 1 || this.f < 0)) {
                this.f = 1;
                return true;
            }
        }
        return false;
    }
}

