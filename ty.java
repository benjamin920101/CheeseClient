/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;

public class ty
extends tq {
    private int b;
    tf a;
    private int c;
    private int bm;

    public ty(adm adm2) {
        super(adm2);
        this.a(1.4f, 2.9f);
        ((sv)this.s()).a(true);
        this.i.a(1, new rl(this, 1.0, true));
        this.i.a(2, new rq(this, 0.9, 32.0f));
        this.i.a(3, new rn(this, 0.6, true));
        this.i.a(4, new rp(this, 1.0));
        this.i.a(5, new rt(this));
        this.i.a(6, new rz(this, 0.6));
        this.i.a(7, new ri(this, wn.class, 6.0f));
        this.i.a(8, new ry(this));
        this.bi.a(1, new sl(this));
        this.bi.a(2, new sm((py)this, false, new Class[0]));
        this.bi.a(3, new a<pk>(this, ps.class, 10, false, true, vq.e));
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, Byte.valueOf((byte)0));
    }

    @Override
    protected void E() {
        if (--this.b <= 0) {
            this.b = 70 + this.V.nextInt(50);
            this.a = this.o.ae().a(new cj(this), 32);
            if (this.a == null) {
                this.cj();
            } else {
                cj cj2 = this.a.a();
                this.a(cj2, (int)((float)this.a.b() * 0.6f));
            }
        }
        super.E();
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(100.0);
        this.a(vy.d).a(0.25);
    }

    @Override
    protected int j(int n2) {
        return n2;
    }

    @Override
    protected void s(pk pk2) {
        if (pk2 instanceof vq && !(pk2 instanceof vn) && this.bc().nextInt(20) == 0) {
            this.d((pr)pk2);
        }
        super.s(pk2);
    }

    @Override
    public void m() {
        afh afh2;
        super.m();
        if (this.c > 0) {
            --this.c;
        }
        if (this.bm > 0) {
            --this.bm;
        }
        if (this.v * this.v + this.x * this.x > 2.500000277905201E-7 && this.V.nextInt(5) == 0 && (afh2 = (\u2603 = this.o.p(new cj(\u2603 = ns.c(this.s), \u2603 = ns.c(this.t - (double)0.2f), \u2603 = ns.c(this.u)))).c()).t() != arm.a) {
            this.o.a(cy.L, this.s + ((double)this.V.nextFloat() - 0.5) * (double)this.J, this.aR().b + 0.1, this.u + ((double)this.V.nextFloat() - 0.5) * (double)this.J, 4.0 * ((double)this.V.nextFloat() - 0.5), 0.5, ((double)this.V.nextFloat() - 0.5) * 4.0, afh.f(\u2603));
        }
    }

    @Override
    public boolean a(Class<? extends pr> clazz) {
        if (this.cn() && wn.class.isAssignableFrom(clazz)) {
            return false;
        }
        if (clazz == vn.class) {
            return false;
        }
        return super.a(clazz);
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("PlayerCreated", this.cn());
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.l(dn2.n("PlayerCreated"));
    }

    @Override
    public boolean r(pk pk2) {
        this.c = 10;
        this.o.a((pk)this, (byte)4);
        boolean bl2 = pk2.a(ow.a(this), (float)(7 + this.V.nextInt(15)));
        if (bl2) {
            pk2.w += (double)0.4f;
            this.a(this, pk2);
        }
        this.a("mob.irongolem.throw", 1.0f, 1.0f);
        return bl2;
    }

    @Override
    public void a(byte by) {
        if (by == 4) {
            this.c = 10;
            this.a("mob.irongolem.throw", 1.0f, 1.0f);
        } else if (by == 11) {
            this.bm = 400;
        } else {
            super.a(by);
        }
    }

    public tf n() {
        return this.a;
    }

    public int cl() {
        return this.c;
    }

    public void a(boolean bl2) {
        this.bm = bl2 ? 400 : 0;
        this.o.a((pk)this, (byte)11);
    }

    @Override
    protected String bo() {
        return "mob.irongolem.hit";
    }

    @Override
    protected String bp() {
        return "mob.irongolem.death";
    }

    @Override
    protected void a(cj cj2, afh afh2) {
        this.a("mob.irongolem.walk", 1.0f, 1.0f);
    }

    @Override
    protected void b(boolean bl2, int n2) {
        \u2603 = this.V.nextInt(3);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            this.a(zw.a(afi.O), 1, (float)agw.a.b.b());
        }
        \u2603 = 3 + this.V.nextInt(3);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            this.a(zy.j, 1);
        }
    }

    public int cm() {
        return this.bm;
    }

    public boolean cn() {
        return (this.ac.a(16) & 1) != 0;
    }

    public void l(boolean bl2) {
        byte by = this.ac.a(16);
        if (bl2) {
            this.ac.b(16, (byte)(by | 1));
        } else {
            this.ac.b(16, (byte)(by & 0xFFFFFFFE));
        }
    }

    @Override
    public void a(ow ow2) {
        if (!this.cn() && this.aN != null && this.a != null) {
            this.a.a(this.aN.e_(), -5);
        }
        super.a(ow2);
    }

    static class a<T extends pr>
    extends sp<T> {
        public a(final py py2, Class<T> clazz, int n2, boolean bl2, boolean bl3, final Predicate<? super T> predicate) {
            super(py2, clazz, n2, bl2, bl3, predicate);
            this.c = new Predicate<T>(){

                public boolean a(T t22) {
                    Object t22;
                    if (predicate != null && !predicate.apply(t22)) {
                        return false;
                    }
                    if (t22 instanceof vn) {
                        return false;
                    }
                    if (t22 instanceof wn) {
                        double d2 = a.this.f();
                        if (((pk)t22).av()) {
                            d2 *= (double)0.8f;
                        }
                        if (((pk)t22).ax()) {
                            float f2 = ((wn)t22).bY();
                            if (f2 < 0.1f) {
                                f2 = 0.1f;
                            }
                            d2 *= (double)(0.7f * f2);
                        }
                        if ((double)((pk)t22).g(py2) > d2) {
                            return false;
                        }
                    }
                    return a.this.a(t22, false);
                }

                @Override
                public /* synthetic */ boolean apply(Object object) {
                    return this.a((pr)object);
                }
            };
        }
    }
}

