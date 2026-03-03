/*
 * Decompiled with CFR 0.152.
 */
public abstract class ph
extends py {
    protected int a;
    protected int b;
    protected int c;
    private float bm = -1.0f;
    private float bn;

    public ph(adm adm2) {
        super(adm2);
    }

    public abstract ph a(ph var1);

    @Override
    public boolean a(wn wn2) {
        zx zx2 = wn2.bi.h();
        if (zx2 != null && zx2.b() == zy.bJ) {
            if (!this.o.D && (\u2603 = pm.a(zx2.i())) != null && this.getClass() == \u2603 && (\u2603 = this.a(this)) != null) {
                \u2603.b(-24000);
                \u2603.b(this.s, this.t, this.u, 0.0f, 0.0f);
                this.o.d(\u2603);
                if (zx2.s()) {
                    \u2603.a(zx2.q());
                }
                if (!wn2.bA.d) {
                    --zx2.b;
                    if (zx2.b <= 0) {
                        wn2.bi.a(wn2.bi.c, null);
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(12, Byte.valueOf((byte)0));
    }

    public int l() {
        if (this.o.D) {
            return this.ac.a(12);
        }
        return this.a;
    }

    public void a(int n2, boolean bl2) {
        int n3 = \u2603 = this.l();
        if ((\u2603 += n2 * 20) > 0) {
            \u2603 = 0;
            if (n3 < 0) {
                this.n();
            }
        }
        \u2603 = \u2603 - n3;
        this.b(\u2603);
        if (bl2) {
            this.b += \u2603;
            if (this.c == 0) {
                this.c = 40;
            }
        }
        if (this.l() == 0) {
            this.b(this.b);
        }
    }

    public void a(int n2) {
        this.a(n2, false);
    }

    public void b(int n2) {
        this.ac.b(12, (byte)ns.a(n2, -1, 1));
        this.a = n2;
        this.a(this.j_());
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("Age", this.l());
        dn2.a("ForcedAge", this.b);
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.b(dn2.f("Age"));
        this.b = dn2.f("ForcedAge");
    }

    @Override
    public void m() {
        super.m();
        if (this.o.D) {
            if (this.c > 0) {
                if (this.c % 4 == 0) {
                    this.o.a(cy.v, this.s + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, this.t + 0.5 + (double)(this.V.nextFloat() * this.K), this.u + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, 0.0, 0.0, 0.0, new int[0]);
                }
                --this.c;
            }
            this.a(this.j_());
        } else {
            int n2 = this.l();
            if (n2 < 0) {
                this.b(++n2);
                if (n2 == 0) {
                    this.n();
                }
            } else if (n2 > 0) {
                this.b(--n2);
            }
        }
    }

    protected void n() {
    }

    @Override
    public boolean j_() {
        return this.l() < 0;
    }

    public void a(boolean bl2) {
        this.a(bl2 ? 0.5f : 1.0f);
    }

    @Override
    protected final void a(float f2, float f3) {
        boolean bl2 = this.bm > 0.0f;
        this.bm = f2;
        this.bn = f3;
        if (!bl2) {
            this.a(1.0f);
        }
    }

    protected final void a(float f2) {
        super.a(this.bm * f2, this.bn * f2);
    }
}

