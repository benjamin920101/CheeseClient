/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import java.util.Calendar;
import java.util.UUID;

public class we
extends vv {
    protected static final qb a = new qj(null, "zombie.spawnReinforcements", 0.0, 0.0, 1.0).a("Spawn Reinforcements Chance");
    private static final UUID b = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final qd c = new qd(b, "Baby speed boost", 0.5, 1);
    private final qu bm = new qu(this);
    private int bn;
    private boolean bo = false;
    private float bp = -1.0f;
    private float bq;

    public we(adm adm2) {
        super(adm2);
        ((sv)this.s()).b(true);
        this.i.a(0, new ra(this));
        this.i.a(2, new rl(this, wn.class, 1.0, false));
        this.i.a(5, new rp(this, 1.0));
        this.i.a(7, new rz(this, 1.0));
        this.i.a(8, new ri(this, wn.class, 8.0f));
        this.i.a(8, new ry(this));
        this.n();
        this.a(0.6f, 1.95f);
    }

    protected void n() {
        this.i.a(4, new rl(this, wi.class, 1.0, true));
        this.i.a(4, new rl(this, ty.class, 1.0, true));
        this.i.a(6, new rn(this, 1.0, false));
        this.bi.a(1, new sm((py)this, true, vw.class));
        this.bi.a(2, new sp<wn>((py)this, wn.class, true));
        this.bi.a(2, new sp<wi>((py)this, wi.class, false));
        this.bi.a(2, new sp<ty>((py)this, ty.class, true));
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.b).a(35.0);
        this.a(vy.d).a(0.23f);
        this.a(vy.e).a(3.0);
        this.by().b(a).a(this.V.nextDouble() * (double)0.1f);
    }

    @Override
    protected void h() {
        super.h();
        this.H().a(12, Byte.valueOf((byte)0));
        this.H().a(13, Byte.valueOf((byte)0));
        this.H().a(14, Byte.valueOf((byte)0));
    }

    @Override
    public int br() {
        int n2 = super.br() + 2;
        if (n2 > 20) {
            n2 = 20;
        }
        return n2;
    }

    public boolean cn() {
        return this.bo;
    }

    public void a(boolean bl2) {
        if (this.bo != bl2) {
            this.bo = bl2;
            if (bl2) {
                this.i.a(1, this.bm);
            } else {
                this.i.a(this.bm);
            }
        }
    }

    @Override
    public boolean j_() {
        return this.H().a(12) == 1;
    }

    @Override
    protected int b(wn wn2) {
        if (this.j_()) {
            this.b_ = (int)((float)this.b_ * 2.5f);
        }
        return super.b(wn2);
    }

    public void l(boolean bl22) {
        boolean bl22;
        this.H().b(12, (byte)(bl22 ? (char)'\u0001' : '\u0000'));
        if (this.o != null && !this.o.D) {
            qc qc2 = this.a(vy.d);
            qc2.c(c);
            if (bl22) {
                qc2.b(c);
            }
        }
        this.n(bl22);
    }

    public boolean co() {
        return this.H().a(13) == 1;
    }

    public void m(boolean bl2) {
        this.H().b(13, (byte)(bl2 ? (char)'\u0001' : '\u0000'));
    }

    @Override
    public void m() {
        if (this.o.w() && !this.o.D && !this.j_()) {
            float f2 = this.c(1.0f);
            cj \u26032 = new cj(this.s, Math.round(this.t), this.u);
            if (f2 > 0.5f && this.V.nextFloat() * 30.0f < (f2 - 0.4f) * 2.0f && this.o.i(\u26032)) {
                boolean bl2 = true;
                zx \u26033 = this.p(4);
                if (\u26033 != null) {
                    if (\u26033.e()) {
                        \u26033.b(\u26033.h() + this.V.nextInt(2));
                        if (\u26033.h() >= \u26033.j()) {
                            this.b(\u26033);
                            this.c(4, null);
                        }
                    }
                    bl2 = false;
                }
                if (bl2) {
                    this.e(8);
                }
            }
        }
        if (this.au() && this.u() != null && this.m instanceof tn) {
            ((ps)this.m).s().a(this.s().j(), 1.5);
        }
        super.m();
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (super.a(ow2, f2)) {
            pr pr2 = this.u();
            if (pr2 == null && ow2.j() instanceof pr) {
                pr2 = (pr)ow2.j();
            }
            if (pr2 != null && this.o.aa() == oj.d && (double)this.V.nextFloat() < this.a(a).e()) {
                int n2 = ns.c(this.s);
                \u2603 = ns.c(this.t);
                \u2603 = ns.c(this.u);
                we \u26032 = new we(this.o);
                for (\u2603 = 0; \u2603 < 50; ++\u2603) {
                    \u2603 = n2 + ns.a(this.V, 7, 40) * ns.a(this.V, -1, 1);
                    if (!adm.a(this.o, new cj(\u2603, (\u2603 = \u2603 + ns.a(this.V, 7, 40) * ns.a(this.V, -1, 1)) - 1, \u2603 = \u2603 + ns.a(this.V, 7, 40) * ns.a(this.V, -1, 1))) || this.o.l(new cj(\u2603, \u2603, \u2603)) >= 10) continue;
                    \u26032.b(\u2603, \u2603, \u2603);
                    if (this.o.b(\u2603, \u2603, (double)\u2603, 7.0) || !this.o.a(\u26032.aR(), (pk)\u26032) || !this.o.a((pk)\u26032, \u26032.aR()).isEmpty() || this.o.d(\u26032.aR())) continue;
                    this.o.d(\u26032);
                    \u26032.d(pr2);
                    \u26032.a(this.o.E(new cj(\u26032)), null);
                    this.a(a).b(new qd("Zombie reinforcement caller charge", -0.05f, 0));
                    \u26032.a(a).b(new qd("Zombie reinforcement callee charge", -0.05f, 0));
                    break;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void t_() {
        if (!this.o.D && this.cp()) {
            int n2 = this.cr();
            this.bn -= n2;
            if (this.bn <= 0) {
                this.cq();
            }
        }
        super.t_();
    }

    @Override
    public boolean r(pk pk2) {
        boolean bl2 = super.r(pk2);
        if (bl2) {
            int n2 = this.o.aa().a();
            if (this.bA() == null && this.at() && this.V.nextFloat() < (float)n2 * 0.3f) {
                pk2.e(2 * n2);
            }
        }
        return bl2;
    }

    @Override
    protected String z() {
        return "mob.zombie.say";
    }

    @Override
    protected String bo() {
        return "mob.zombie.hurt";
    }

    @Override
    protected String bp() {
        return "mob.zombie.death";
    }

    @Override
    protected void a(cj cj2, afh afh2) {
        this.a("mob.zombie.step", 0.15f, 1.0f);
    }

    @Override
    protected zw A() {
        return zy.bt;
    }

    @Override
    public pw bz() {
        return pw.b;
    }

    @Override
    protected void bq() {
        switch (this.V.nextInt(3)) {
            case 0: {
                this.a(zy.j, 1);
                break;
            }
            case 1: {
                this.a(zy.bR, 1);
                break;
            }
            case 2: {
                this.a(zy.bS, 1);
            }
        }
    }

    @Override
    protected void a(ok ok2) {
        super.a(ok2);
        float f2 = this.V.nextFloat();
        float f3 = this.o.aa() == oj.d ? 0.05f : 0.01f;
        if (f2 < f3) {
            int n2 = this.V.nextInt(3);
            if (n2 == 0) {
                this.c(0, new zx(zy.l));
            } else {
                this.c(0, new zx(zy.a));
            }
        }
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        if (this.j_()) {
            dn2.a("IsBaby", true);
        }
        if (this.co()) {
            dn2.a("IsVillager", true);
        }
        dn2.a("ConversionTime", this.cp() ? this.bn : -1);
        dn2.a("CanBreakDoors", this.cn());
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        if (dn2.n("IsBaby")) {
            this.l(true);
        }
        if (dn2.n("IsVillager")) {
            this.m(true);
        }
        if (dn2.b("ConversionTime", 99) && dn2.f("ConversionTime") > -1) {
            this.a(dn2.f("ConversionTime"));
        }
        this.a(dn2.n("CanBreakDoors"));
    }

    @Override
    public void a(pr pr2) {
        super.a(pr2);
        if ((this.o.aa() == oj.c || this.o.aa() == oj.d) && pr2 instanceof wi) {
            if (this.o.aa() != oj.d && this.V.nextBoolean()) {
                return;
            }
            ps ps2 = (ps)pr2;
            we \u26032 = new we(this.o);
            \u26032.m(pr2);
            this.o.e(pr2);
            \u26032.a(this.o.E(new cj(\u26032)), null);
            \u26032.m(true);
            if (pr2.j_()) {
                \u26032.l(true);
            }
            \u26032.k(ps2.ce());
            if (ps2.l_()) {
                \u26032.a(ps2.aM());
                \u26032.g(ps2.aN());
            }
            this.o.d(\u26032);
            this.o.a(null, 1016, new cj((int)this.s, (int)this.t, (int)this.u), 0);
        }
    }

    @Override
    public float aS() {
        float f2 = 1.74f;
        if (this.j_()) {
            f2 = (float)((double)f2 - 0.81);
        }
        return f2;
    }

    @Override
    protected boolean a(zx zx2) {
        if (zx2.b() == zy.aP && this.j_() && this.au()) {
            return false;
        }
        return super.a(zx2);
    }

    @Override
    public pu a(ok ok2, pu pu22) {
        pu pu22;
        pu22 = super.a(ok2, pu22);
        float f2 = ok2.c();
        this.j(this.V.nextFloat() < 0.55f * f2);
        if (pu22 == null) {
            pu22 = new a(this.o.s.nextFloat() < 0.05f, this.o.s.nextFloat() < 0.05f);
        }
        if (pu22 instanceof a) {
            Object object = (a)pu22;
            if (((a)object).b) {
                this.m(true);
            }
            if (((a)object).a) {
                this.l(true);
                if ((double)this.o.s.nextFloat() < 0.05) {
                    Predicate<pk> predicate = this.o.a(tn.class, this.aR().b(5.0, 3.0, 5.0), po.b);
                    if (!predicate.isEmpty()) {
                        tn tn2 = (tn)predicate.get(0);
                        tn2.l(true);
                        this.a((pk)tn2);
                    }
                } else if ((double)this.o.s.nextFloat() < 0.05) {
                    tn tn3 = new tn(this.o);
                    tn3.b(this.s, this.t, this.u, this.y, 0.0f);
                    tn3.a(ok2, null);
                    tn3.l(true);
                    this.o.d(tn3);
                    this.a((pk)tn3);
                }
            }
        }
        this.a(this.V.nextFloat() < f2 * 0.1f);
        this.a(ok2);
        this.b(ok2);
        if (this.p(4) == null && ((Calendar)(object = this.o.Y())).get(2) + 1 == 10 && ((Calendar)object).get(5) == 31 && this.V.nextFloat() < 0.25f) {
            this.c(4, new zx(this.V.nextFloat() < 0.1f ? afi.aZ : afi.aU));
            this.bj[4] = 0.0f;
        }
        this.a(vy.c).b(new qd("Random spawn bonus", this.V.nextDouble() * (double)0.05f, 0));
        double \u26032 = this.V.nextDouble() * 1.5 * (double)f2;
        if (\u26032 > 1.0) {
            this.a(vy.b).b(new qd("Random zombie-spawn bonus", \u26032, 2));
        }
        if (this.V.nextFloat() < f2 * 0.05f) {
            this.a(a).b(new qd("Leader zombie bonus", this.V.nextDouble() * 0.25 + 0.5, 0));
            this.a(vy.a).b(new qd("Leader zombie bonus", this.V.nextDouble() * 3.0 + 1.0, 2));
            this.a(true);
        }
        return pu22;
    }

    @Override
    public boolean a(wn wn2) {
        zx zx2 = wn2.bZ();
        if (zx2 != null && zx2.b() == zy.ao && zx2.i() == 0 && this.co() && this.a(pe.t)) {
            if (!wn2.bA.d) {
                --zx2.b;
            }
            if (zx2.b <= 0) {
                wn2.bi.a(wn2.bi.c, null);
            }
            if (!this.o.D) {
                this.a(this.V.nextInt(2401) + 3600);
            }
            return true;
        }
        return false;
    }

    protected void a(int n2) {
        this.bn = n2;
        this.H().b(14, (byte)1);
        this.m(pe.t.H);
        this.c(new pf(pe.g.H, n2, Math.min(this.o.aa().a() - 1, 0)));
        this.o.a((pk)this, (byte)16);
    }

    @Override
    public void a(byte by) {
        if (by == 16) {
            if (!this.R()) {
                this.o.a(this.s + 0.5, this.t + 0.5, this.u + 0.5, "mob.zombie.remedy", 1.0f + this.V.nextFloat(), this.V.nextFloat() * 0.7f + 0.3f, false);
            }
        } else {
            super.a(by);
        }
    }

    @Override
    protected boolean C() {
        return !this.cp();
    }

    public boolean cp() {
        return this.H().a(14) == 1;
    }

    protected void cq() {
        wi wi2 = new wi(this.o);
        wi2.m(this);
        wi2.a(this.o.E(new cj(wi2)), null);
        wi2.cp();
        if (this.j_()) {
            wi2.b(-24000);
        }
        this.o.e(this);
        wi2.k(this.ce());
        if (this.l_()) {
            wi2.a(this.aM());
            wi2.g(this.aN());
        }
        this.o.d(wi2);
        wi2.c(new pf(pe.k.H, 200, 0));
        this.o.a(null, 1017, new cj((int)this.s, (int)this.t, (int)this.u), 0);
    }

    protected int cr() {
        int n2 = 1;
        if (this.V.nextFloat() < 0.01f) {
            \u2603 = 0;
            cj.a a2 = new cj.a();
            for (int i2 = (int)this.s - 4; i2 < (int)this.s + 4 && \u2603 < 14; ++i2) {
                for (\u2603 = (int)this.t - 4; \u2603 < (int)this.t + 4 && \u2603 < 14; ++\u2603) {
                    for (\u2603 = (int)this.u - 4; \u2603 < (int)this.u + 4 && \u2603 < 14; ++\u2603) {
                        afh afh2 = this.o.p(a2.c(i2, \u2603, \u2603)).c();
                        if (afh2 != afi.bi && afh2 != afi.C) continue;
                        if (this.V.nextFloat() < 0.3f) {
                            ++n2;
                        }
                        ++\u2603;
                    }
                }
            }
        }
        return n2;
    }

    public void n(boolean bl2) {
        this.a(bl2 ? 0.5f : 1.0f);
    }

    @Override
    protected final void a(float f2, float f3) {
        boolean bl2 = this.bp > 0.0f && this.bq > 0.0f;
        this.bp = f2;
        this.bq = f3;
        if (!bl2) {
            this.a(1.0f);
        }
    }

    protected final void a(float f2) {
        super.a(this.bp * f2, this.bq * f2);
    }

    @Override
    public double am() {
        return this.j_() ? 0.0 : -0.35;
    }

    @Override
    public void a(ow ow2) {
        super.a(ow2);
        if (ow2.j() instanceof vn && !(this instanceof vw) && ((vn)ow2.j()).n() && ((vn)ow2.j()).cp()) {
            ((vn)ow2.j()).cq();
            this.a(new zx(zy.bX, 1, 2), 0.0f);
        }
    }

    class a
    implements pu {
        public boolean a = false;
        public boolean b = false;

        private a(boolean bl2, boolean bl3) {
            this.a = bl2;
            this.b = bl3;
        }
    }
}

