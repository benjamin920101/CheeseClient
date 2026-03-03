/*
 * Decompiled with CFR 0.152.
 */
public class vn
extends vv {
    private int a;
    private int b;
    private int c = 30;
    private int bm = 3;
    private int bn = 0;

    public vn(adm adm2) {
        super(adm2);
        this.i.a(1, new ra(this));
        this.i.a(2, new sf(this));
        this.i.a(3, new qs<ts>(this, ts.class, 6.0f, 1.0, 1.2));
        this.i.a(4, new rl(this, 1.0, false));
        this.i.a(5, new rz(this, 0.8));
        this.i.a(6, new ri(this, wn.class, 8.0f));
        this.i.a(6, new ry(this));
        this.bi.a(1, new sp<wn>((py)this, wn.class, true));
        this.bi.a(2, new sm((py)this, false, new Class[0]));
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.d).a(0.25);
    }

    @Override
    public int aE() {
        if (this.u() == null) {
            return 3;
        }
        return 3 + (int)(this.bn() - 1.0f);
    }

    @Override
    public void e(float f2, float f3) {
        super.e(f2, f3);
        this.b = (int)((float)this.b + f2 * 1.5f);
        if (this.b > this.c - 5) {
            this.b = this.c - 5;
        }
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, Byte.valueOf((byte)-1));
        this.ac.a(17, Byte.valueOf((byte)0));
        this.ac.a(18, Byte.valueOf((byte)0));
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        if (this.ac.a(17) == 1) {
            dn2.a("powered", true);
        }
        dn2.a("Fuse", (short)this.c);
        dn2.a("ExplosionRadius", (byte)this.bm);
        dn2.a("ignited", this.cn());
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.ac.b(17, (byte)(dn2.n("powered") ? (char)'\u0001' : '\u0000'));
        if (dn2.b("Fuse", 99)) {
            this.c = dn2.e("Fuse");
        }
        if (dn2.b("ExplosionRadius", 99)) {
            this.bm = dn2.d("ExplosionRadius");
        }
        if (dn2.n("ignited")) {
            this.co();
        }
    }

    @Override
    public void t_() {
        if (this.ai()) {
            int n2;
            this.a = this.b;
            if (this.cn()) {
                this.a(1);
            }
            if ((n2 = this.cm()) > 0 && this.b == 0) {
                this.a("creeper.primed", 1.0f, 0.5f);
            }
            this.b += n2;
            if (this.b < 0) {
                this.b = 0;
            }
            if (this.b >= this.c) {
                this.b = this.c;
                this.cr();
            }
        }
        super.t_();
    }

    @Override
    protected String bo() {
        return "mob.creeper.say";
    }

    @Override
    protected String bp() {
        return "mob.creeper.death";
    }

    @Override
    public void a(ow ow22) {
        ow ow22;
        super.a(ow22);
        if (ow22.j() instanceof wa) {
            int n2 = zw.b(zy.cq);
            \u2603 = zw.b(zy.cB);
            \u2603 = n2 + this.V.nextInt(\u2603 - n2 + 1);
            this.a(zw.b(\u2603), 1);
        } else if (ow22.j() instanceof vn && ow22.j() != this && ((vn)ow22.j()).n() && ((vn)ow22.j()).cp()) {
            ((vn)ow22.j()).cq();
            this.a(new zx(zy.bX, 1, 4), 0.0f);
        }
    }

    @Override
    public boolean r(pk pk2) {
        return true;
    }

    public boolean n() {
        return this.ac.a(17) == 1;
    }

    public float a(float f2) {
        return ((float)this.a + (float)(this.b - this.a) * f2) / (float)(this.c - 2);
    }

    @Override
    protected zw A() {
        return zy.H;
    }

    public int cm() {
        return this.ac.a(16);
    }

    public void a(int n2) {
        this.ac.b(16, (byte)n2);
    }

    @Override
    public void a(uv uv2) {
        super.a(uv2);
        this.ac.b(17, (byte)1);
    }

    @Override
    protected boolean a(wn wn2) {
        zx zx2 = wn2.bi.h();
        if (zx2 != null && zx2.b() == zy.d) {
            this.o.a(this.s + 0.5, this.t + 0.5, this.u + 0.5, "fire.ignite", 1.0f, this.V.nextFloat() * 0.4f + 0.8f);
            wn2.bw();
            if (!this.o.D) {
                this.co();
                zx2.a(1, (pr)wn2);
                return true;
            }
        }
        return super.a(wn2);
    }

    private void cr() {
        if (!this.o.D) {
            boolean bl2 = this.o.Q().b("mobGriefing");
            float \u26032 = this.n() ? 2.0f : 1.0f;
            this.o.a(this, this.s, this.t, this.u, (float)this.bm * \u26032, bl2);
            this.J();
        }
    }

    public boolean cn() {
        return this.ac.a(18) != 0;
    }

    public void co() {
        this.ac.b(18, (byte)1);
    }

    public boolean cp() {
        return this.bn < 1 && this.o.Q().b("doMobLoot");
    }

    public void cq() {
        ++this.bn;
    }
}

