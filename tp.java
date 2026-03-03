/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import java.util.List;

public class tp
extends tm
implements oh {
    private static final Predicate<pk> bs = new Predicate<pk>(){

        public boolean a(pk pk2) {
            return pk2 instanceof tp && ((tp)pk2).cA();
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((pk)object);
        }
    };
    private static final qb bt = new qj(null, "horse.jumpStrength", 0.7, 0.0, 2.0).a("Jump Strength").a(true);
    private static final String[] bu = new String[]{null, "textures/entity/horse/armor/horse_armor_iron.png", "textures/entity/horse/armor/horse_armor_gold.png", "textures/entity/horse/armor/horse_armor_diamond.png"};
    private static final String[] bv = new String[]{"", "meo", "goo", "dio"};
    private static final int[] bw = new int[]{0, 5, 7, 11};
    private static final String[] bx = new String[]{"textures/entity/horse/horse_white.png", "textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_chestnut.png", "textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_black.png", "textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_darkbrown.png"};
    private static final String[] by = new String[]{"hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb"};
    private static final String[] bz = new String[]{null, "textures/entity/horse/horse_markings_white.png", "textures/entity/horse/horse_markings_whitefield.png", "textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse/horse_markings_blackdots.png"};
    private static final String[] bA = new String[]{"", "wo_", "wmo", "wdo", "bdo"};
    private int bB;
    private int bC;
    private int bD;
    public int bm;
    public int bo;
    protected boolean bp;
    private xj bE;
    private boolean bF;
    protected int bq;
    protected float br;
    private boolean bG;
    private float bH;
    private float bI;
    private float bJ;
    private float bK;
    private float bL;
    private float bM;
    private int bN;
    private String bO;
    private String[] bP = new String[3];
    private boolean bQ = false;

    public tp(adm adm2) {
        super(adm2);
        this.a(1.4f, 1.6f);
        this.ab = false;
        this.o(false);
        ((sv)this.s()).a(true);
        this.i.a(0, new ra(this));
        this.i.a(1, new rv(this, 1.2));
        this.i.a(1, new sd(this, 1.2));
        this.i.a(2, new qv(this, 1.0));
        this.i.a(4, new rc(this, 1.0));
        this.i.a(6, new rz(this, 0.7));
        this.i.a(7, new ri(this, wn.class, 6.0f));
        this.i.a(8, new ry(this));
        this.da();
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, Integer.valueOf(0));
        this.ac.a(19, Byte.valueOf((byte)0));
        this.ac.a(20, Integer.valueOf(0));
        this.ac.a(21, String.valueOf(""));
        this.ac.a(22, Integer.valueOf(0));
    }

    public void r(int n2) {
        this.ac.b(19, (byte)n2);
        this.dc();
    }

    public int cl() {
        return this.ac.a(19);
    }

    public void s(int n2) {
        this.ac.b(20, n2);
        this.dc();
    }

    public int cm() {
        return this.ac.c(20);
    }

    @Override
    public String e_() {
        if (this.l_()) {
            return this.aM();
        }
        int n2 = this.cl();
        switch (n2) {
            default: {
                return di.a("entity.horse.name");
            }
            case 1: {
                return di.a("entity.donkey.name");
            }
            case 2: {
                return di.a("entity.mule.name");
            }
            case 4: {
                return di.a("entity.skeletonhorse.name");
            }
            case 3: 
        }
        return di.a("entity.zombiehorse.name");
    }

    private boolean w(int n2) {
        return (this.ac.c(16) & n2) != 0;
    }

    private void c(int n2, boolean bl2) {
        int n3 = this.ac.c(16);
        if (bl2) {
            this.ac.b(16, n3 | n2);
        } else {
            this.ac.b(16, n3 & ~n2);
        }
    }

    public boolean cn() {
        return !this.j_();
    }

    public boolean co() {
        return this.w(2);
    }

    public boolean cp() {
        return this.cn();
    }

    public String ct() {
        return this.ac.e(21);
    }

    public void b(String string) {
        this.ac.b(21, string);
    }

    public float cu() {
        return 0.5f;
    }

    @Override
    public void a(boolean bl2) {
        if (bl2) {
            this.a(this.cu());
        } else {
            this.a(1.0f);
        }
    }

    public boolean cv() {
        return this.bp;
    }

    public void l(boolean bl2) {
        this.c(2, bl2);
    }

    public void m(boolean bl2) {
        this.bp = bl2;
    }

    @Override
    public boolean cb() {
        return !this.cR() && super.cb();
    }

    @Override
    protected void o(float f2) {
        if (f2 > 6.0f && this.cy()) {
            this.r(false);
        }
    }

    public boolean cw() {
        return this.w(8);
    }

    public int cx() {
        return this.ac.c(22);
    }

    private int f(zx zx2) {
        if (zx2 == null) {
            return 0;
        }
        zw zw2 = zx2.b();
        if (zw2 == zy.ck) {
            return 1;
        }
        if (zw2 == zy.cl) {
            return 2;
        }
        if (zw2 == zy.cm) {
            return 3;
        }
        return 0;
    }

    public boolean cy() {
        return this.w(32);
    }

    public boolean cz() {
        return this.w(64);
    }

    public boolean cA() {
        return this.w(16);
    }

    public boolean cB() {
        return this.bF;
    }

    public void e(zx zx2) {
        this.ac.b(22, this.f(zx2));
        this.dc();
    }

    public void n(boolean bl2) {
        this.c(16, bl2);
    }

    public void o(boolean bl2) {
        this.c(8, bl2);
    }

    public void p(boolean bl2) {
        this.bF = bl2;
    }

    public void q(boolean bl2) {
        this.c(4, bl2);
    }

    public int cC() {
        return this.bq;
    }

    public void t(int n2) {
        this.bq = n2;
    }

    public int u(int n2) {
        \u2603 = ns.a(this.cC() + n2, 0, this.cI());
        this.t(\u2603);
        return \u2603;
    }

    @Override
    public boolean a(ow ow2, float f2) {
        pk pk2 = ow2.j();
        if (this.l != null && this.l.equals(pk2)) {
            return false;
        }
        return super.a(ow2, f2);
    }

    @Override
    public int br() {
        return bw[this.cx()];
    }

    @Override
    public boolean ae() {
        return this.l == null;
    }

    public boolean cD() {
        int n2 = ns.c(this.s);
        \u2603 = ns.c(this.u);
        this.o.b(new cj(n2, 0, \u2603));
        return true;
    }

    public void cE() {
        if (this.o.D || !this.cw()) {
            return;
        }
        this.a(zw.a(afi.ae), 1);
        this.o(false);
    }

    private void cY() {
        this.df();
        if (!this.R()) {
            this.o.a(this, "eating", 1.0f, 1.0f + (this.V.nextFloat() - this.V.nextFloat()) * 0.2f);
        }
    }

    @Override
    public void e(float f2, float f3) {
        if (f2 > 1.0f) {
            this.a("mob.horse.land", 0.4f, 1.0f);
        }
        if ((\u2603 = ns.f((f2 * 0.5f - 3.0f) * f3)) <= 0) {
            return;
        }
        this.a(ow.i, (float)\u2603);
        if (this.l != null) {
            this.l.a(ow.i, (float)\u2603);
        }
        if ((\u2603 = this.o.p(new cj(this.s, this.t - 0.2 - (double)this.A, this.u)).c()).t() != arm.a && !this.R()) {
            afh.b b2 = \u2603.H;
            this.o.a(this, b2.c(), b2.d() * 0.5f, b2.e() * 0.75f);
        }
    }

    private int cZ() {
        int n2 = this.cl();
        if (this.cw() && (n2 == 1 || n2 == 2)) {
            return 17;
        }
        return 2;
    }

    private void da() {
        xj xj2 = this.bE;
        this.bE = new xj("HorseChest", this.cZ());
        this.bE.a(this.e_());
        if (xj2 != null) {
            xj2.b(this);
            int n2 = Math.min(xj2.o_(), this.bE.o_());
            for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                zx zx2 = xj2.a(\u2603);
                if (zx2 == null) continue;
                this.bE.a(\u2603, zx2.k());
            }
        }
        this.bE.a(this);
        this.db();
    }

    private void db() {
        if (!this.o.D) {
            this.q(this.bE.a(0) != null);
            if (this.cO()) {
                this.e(this.bE.a(1));
            }
        }
    }

    @Override
    public void a(oq oq2) {
        int n2 = this.cx();
        boolean \u26032 = this.cG();
        this.db();
        if (this.W > 20) {
            if (n2 == 0 && n2 != this.cx()) {
                this.a("mob.horse.armor", 0.5f, 1.0f);
            } else if (n2 != this.cx()) {
                this.a("mob.horse.armor", 0.5f, 1.0f);
            }
            if (!\u26032 && this.cG()) {
                this.a("mob.horse.leather", 0.5f, 1.0f);
            }
        }
    }

    @Override
    public boolean bR() {
        this.cD();
        return super.bR();
    }

    protected tp a(pk pk2, double d2) {
        \u2603 = Double.MAX_VALUE;
        pk \u26033 = null;
        List<pk> \u26032 = this.o.a(pk2, pk2.aR().a(d2, d2, d2), bs);
        for (pk pk3 : \u26032) {
            double d3 = pk3.e(pk2.s, pk2.t, pk2.u);
            if (!(d3 < \u2603)) continue;
            \u26033 = pk3;
            \u2603 = d3;
        }
        return (tp)\u26033;
    }

    public double cF() {
        return this.a(bt).e();
    }

    @Override
    protected String bp() {
        this.df();
        int n2 = this.cl();
        if (n2 == 3) {
            return "mob.horse.zombie.death";
        }
        if (n2 == 4) {
            return "mob.horse.skeleton.death";
        }
        if (n2 == 1 || n2 == 2) {
            return "mob.horse.donkey.death";
        }
        return "mob.horse.death";
    }

    @Override
    protected zw A() {
        boolean bl2 = this.V.nextInt(4) == 0;
        int \u26032 = this.cl();
        if (\u26032 == 4) {
            return zy.aX;
        }
        if (\u26032 == 3) {
            if (bl2) {
                return null;
            }
            return zy.bt;
        }
        return zy.aF;
    }

    @Override
    protected String bo() {
        int n2;
        this.df();
        if (this.V.nextInt(3) == 0) {
            this.dh();
        }
        if ((n2 = this.cl()) == 3) {
            return "mob.horse.zombie.hit";
        }
        if (n2 == 4) {
            return "mob.horse.skeleton.hit";
        }
        if (n2 == 1 || n2 == 2) {
            return "mob.horse.donkey.hit";
        }
        return "mob.horse.hit";
    }

    public boolean cG() {
        return this.w(4);
    }

    @Override
    protected String z() {
        int n2;
        this.df();
        if (this.V.nextInt(10) == 0 && !this.bD()) {
            this.dh();
        }
        if ((n2 = this.cl()) == 3) {
            return "mob.horse.zombie.idle";
        }
        if (n2 == 4) {
            return "mob.horse.skeleton.idle";
        }
        if (n2 == 1 || n2 == 2) {
            return "mob.horse.donkey.idle";
        }
        return "mob.horse.idle";
    }

    protected String cH() {
        this.df();
        this.dh();
        int n2 = this.cl();
        if (n2 == 3 || n2 == 4) {
            return null;
        }
        if (n2 == 1 || n2 == 2) {
            return "mob.horse.donkey.angry";
        }
        return "mob.horse.angry";
    }

    @Override
    protected void a(cj cj2, afh afh2) {
        afh.b b2 = afh2.H;
        if (this.o.p(cj2.a()).c() == afi.aH) {
            b2 = afi.aH.H;
        }
        if (!afh2.t().d()) {
            int n2 = this.cl();
            if (this.l != null && n2 != 1 && n2 != 2) {
                ++this.bN;
                if (this.bN > 5 && this.bN % 3 == 0) {
                    this.a("mob.horse.gallop", b2.d() * 0.15f, b2.e());
                    if (n2 == 0 && this.V.nextInt(10) == 0) {
                        this.a("mob.horse.breathe", b2.d() * 0.6f, b2.e());
                    }
                } else if (this.bN <= 5) {
                    this.a("mob.horse.wood", b2.d() * 0.15f, b2.e());
                }
            } else if (b2 == afh.f) {
                this.a("mob.horse.wood", b2.d() * 0.15f, b2.e());
            } else {
                this.a("mob.horse.soft", b2.d() * 0.15f, b2.e());
            }
        }
    }

    @Override
    protected void aX() {
        super.aX();
        this.by().b(bt);
        this.a(vy.a).a(53.0);
        this.a(vy.d).a(0.225f);
    }

    @Override
    public int bV() {
        return 6;
    }

    public int cI() {
        return 100;
    }

    @Override
    protected float bB() {
        return 0.8f;
    }

    @Override
    public int w() {
        return 400;
    }

    public boolean cJ() {
        return this.cl() == 0 || this.cx() > 0;
    }

    private void dc() {
        this.bO = null;
    }

    public boolean cK() {
        return this.bQ;
    }

    private void dd() {
        this.bO = "horse/";
        this.bP[0] = null;
        this.bP[1] = null;
        this.bP[2] = null;
        int n2 = this.cl();
        \u2603 = this.cm();
        if (n2 == 0) {
            \u2603 = \u2603 & 0xFF;
            \u2603 = (\u2603 & 0xFF00) >> 8;
            if (\u2603 >= bx.length) {
                this.bQ = false;
                return;
            }
            this.bP[0] = bx[\u2603];
            this.bO = this.bO + by[\u2603];
            if (\u2603 >= bz.length) {
                this.bQ = false;
                return;
            }
            this.bP[1] = bz[\u2603];
            this.bO = this.bO + bA[\u2603];
        } else {
            this.bP[0] = "";
            this.bO = this.bO + "_" + n2 + "_";
        }
        \u2603 = this.cx();
        if (\u2603 >= bu.length) {
            this.bQ = false;
            return;
        }
        this.bP[2] = bu[\u2603];
        this.bO = this.bO + bv[\u2603];
        this.bQ = true;
    }

    public String cL() {
        if (this.bO == null) {
            this.dd();
        }
        return this.bO;
    }

    public String[] cM() {
        if (this.bO == null) {
            this.dd();
        }
        return this.bP;
    }

    public void g(wn wn2) {
        if (!this.o.D && (this.l == null || this.l == wn2) && this.co()) {
            this.bE.a(this.e_());
            wn2.a(this, this.bE);
        }
    }

    @Override
    public boolean a(wn wn22) {
        wn wn22;
        zx zx2 = wn22.bi.h();
        if (zx2 != null && zx2.b() == zy.bJ) {
            return super.a(wn22);
        }
        if (!this.co() && this.cR()) {
            return false;
        }
        if (this.co() && this.cn() && wn22.av()) {
            this.g(wn22);
            return true;
        }
        if (this.cp() && this.l != null) {
            return super.a(wn22);
        }
        if (zx2 != null) {
            boolean bl2 = false;
            if (this.cO()) {
                int n2 = -1;
                if (zx2.b() == zy.ck) {
                    n2 = 1;
                } else if (zx2.b() == zy.cl) {
                    n2 = 2;
                } else if (zx2.b() == zy.cm) {
                    n2 = 3;
                }
                if (n2 >= 0) {
                    if (!this.co()) {
                        this.cW();
                        return true;
                    }
                    this.g(wn22);
                    return true;
                }
            }
            if (!bl2 && !this.cR()) {
                float f2 = 0.0f;
                int \u26032 = 0;
                int \u26033 = 0;
                if (zx2.b() == zy.O) {
                    f2 = 2.0f;
                    \u26032 = 20;
                    \u26033 = 3;
                } else if (zx2.b() == zy.aY) {
                    f2 = 1.0f;
                    \u26032 = 30;
                    \u26033 = 3;
                } else if (afh.a(zx2.b()) == afi.cx) {
                    f2 = 20.0f;
                    \u26032 = 180;
                } else if (zx2.b() == zy.e) {
                    f2 = 3.0f;
                    \u26032 = 60;
                    \u26033 = 3;
                } else if (zx2.b() == zy.bW) {
                    f2 = 4.0f;
                    \u26032 = 60;
                    \u26033 = 5;
                    if (this.co() && this.l() == 0) {
                        bl2 = true;
                        this.c(wn22);
                    }
                } else if (zx2.b() == zy.ao) {
                    f2 = 10.0f;
                    \u26032 = 240;
                    \u26033 = 10;
                    if (this.co() && this.l() == 0) {
                        bl2 = true;
                        this.c(wn22);
                    }
                }
                if (this.bn() < this.bu() && f2 > 0.0f) {
                    this.h(f2);
                    bl2 = true;
                }
                if (!this.cn() && \u26032 > 0) {
                    this.a(\u26032);
                    bl2 = true;
                }
                if (\u26033 > 0 && (bl2 || !this.co()) && \u26033 < this.cI()) {
                    bl2 = true;
                    this.u(\u26033);
                }
                if (bl2) {
                    this.cY();
                }
            }
            if (!this.co() && !bl2) {
                if (zx2 != null && zx2.a(wn22, (pr)this)) {
                    return true;
                }
                this.cW();
                return true;
            }
            if (!bl2 && this.cP() && !this.cw() && zx2.b() == zw.a(afi.ae)) {
                this.o(true);
                this.a("mob.chickenplop", 1.0f, (this.V.nextFloat() - this.V.nextFloat()) * 0.2f + 1.0f);
                bl2 = true;
                this.da();
            }
            if (!bl2 && this.cp() && !this.cG() && zx2.b() == zy.aA) {
                this.g(wn22);
                return true;
            }
            if (bl2) {
                if (!wn22.bA.d && --zx2.b == 0) {
                    wn22.bi.a(wn22.bi.c, null);
                }
                return true;
            }
        }
        if (this.cp() && this.l == null) {
            if (zx2 != null && zx2.a(wn22, (pr)this)) {
                return true;
            }
            this.i(wn22);
            return true;
        }
        return super.a(wn22);
    }

    private void i(wn wn2) {
        wn2.y = this.y;
        wn2.z = this.z;
        this.r(false);
        this.s(false);
        if (!this.o.D) {
            wn2.a((pk)this);
        }
    }

    public boolean cO() {
        return this.cl() == 0;
    }

    public boolean cP() {
        int n2 = this.cl();
        return n2 == 2 || n2 == 1;
    }

    @Override
    protected boolean bD() {
        if (this.l != null && this.cG()) {
            return true;
        }
        return this.cy() || this.cz();
    }

    public boolean cR() {
        int n2 = this.cl();
        return n2 == 3 || n2 == 4;
    }

    public boolean cS() {
        return this.cR() || this.cl() == 2;
    }

    @Override
    public boolean d(zx zx2) {
        return false;
    }

    private void de() {
        this.bm = 1;
    }

    @Override
    public void a(ow ow2) {
        super.a(ow2);
        if (!this.o.D) {
            this.cX();
        }
    }

    @Override
    public void m() {
        if (this.V.nextInt(200) == 0) {
            this.de();
        }
        super.m();
        if (!this.o.D) {
            tp tp2;
            if (this.V.nextInt(900) == 0 && this.ax == 0) {
                this.h(1.0f);
            }
            if (!this.cy() && this.l == null && this.V.nextInt(300) == 0 && this.o.p(new cj(ns.c(this.s), ns.c(this.t) - 1, ns.c(this.u))).c() == afi.c) {
                this.r(true);
            }
            if (this.cy() && ++this.bB > 50) {
                this.bB = 0;
                this.r(false);
            }
            if (this.cA() && !this.cn() && !this.cy() && (tp2 = this.a((pk)this, 16.0)) != null && this.h(tp2) > 4.0) {
                this.h.a(tp2);
            }
        }
    }

    @Override
    public void t_() {
        super.t_();
        if (this.o.D && this.ac.a()) {
            this.ac.e();
            this.dc();
        }
        if (this.bC > 0 && ++this.bC > 30) {
            this.bC = 0;
            this.c(128, false);
        }
        if (!this.o.D && this.bD > 0 && ++this.bD > 20) {
            this.bD = 0;
            this.s(false);
        }
        if (this.bm > 0 && ++this.bm > 8) {
            this.bm = 0;
        }
        if (this.bo > 0) {
            ++this.bo;
            if (this.bo > 300) {
                this.bo = 0;
            }
        }
        this.bI = this.bH;
        if (this.cy()) {
            this.bH += (1.0f - this.bH) * 0.4f + 0.05f;
            if (this.bH > 1.0f) {
                this.bH = 1.0f;
            }
        } else {
            this.bH += (0.0f - this.bH) * 0.4f - 0.05f;
            if (this.bH < 0.0f) {
                this.bH = 0.0f;
            }
        }
        this.bK = this.bJ;
        if (this.cz()) {
            this.bH = 0.0f;
            this.bI = 0.0f;
            this.bJ += (1.0f - this.bJ) * 0.4f + 0.05f;
            if (this.bJ > 1.0f) {
                this.bJ = 1.0f;
            }
        } else {
            this.bG = false;
            this.bJ += (0.8f * this.bJ * this.bJ * this.bJ - this.bJ) * 0.6f - 0.05f;
            if (this.bJ < 0.0f) {
                this.bJ = 0.0f;
            }
        }
        this.bM = this.bL;
        if (this.w(128)) {
            this.bL += (1.0f - this.bL) * 0.7f + 0.05f;
            if (this.bL > 1.0f) {
                this.bL = 1.0f;
            }
        } else {
            this.bL += (0.0f - this.bL) * 0.7f - 0.05f;
            if (this.bL < 0.0f) {
                this.bL = 0.0f;
            }
        }
    }

    private void df() {
        if (!this.o.D) {
            this.bC = 1;
            this.c(128, true);
        }
    }

    private boolean dg() {
        return this.l == null && this.m == null && this.co() && this.cn() && !this.cS() && this.bn() >= this.bu() && this.cr();
    }

    @Override
    public void f(boolean bl2) {
        this.c(32, bl2);
    }

    public void r(boolean bl2) {
        this.f(bl2);
    }

    public void s(boolean bl2) {
        if (bl2) {
            this.r(false);
        }
        this.c(64, bl2);
    }

    private void dh() {
        if (!this.o.D) {
            this.bD = 1;
            this.s(true);
        }
    }

    public void cW() {
        this.dh();
        String string = this.cH();
        if (string != null) {
            this.a(string, this.bB(), this.bC());
        }
    }

    public void cX() {
        this.a((pk)this, this.bE);
        this.cE();
    }

    private void a(pk pk2, xj xj2) {
        if (xj2 == null || this.o.D) {
            return;
        }
        for (int i2 = 0; i2 < xj2.o_(); ++i2) {
            zx zx2 = xj2.a(i2);
            if (zx2 == null) continue;
            this.a(zx2, 0.0f);
        }
    }

    public boolean h(wn wn2) {
        this.b(wn2.aK().toString());
        this.l(true);
        return true;
    }

    @Override
    public void g(float f2, float f3) {
        if (this.l == null || !(this.l instanceof pr) || !this.cG()) {
            this.S = 0.5f;
            this.aM = 0.02f;
            super.g(f2, f3);
            return;
        }
        this.A = this.y = this.l.y;
        this.z = this.l.z * 0.5f;
        this.b(this.y, this.z);
        this.aK = this.aI = this.y;
        f2 = ((pr)this.l).aZ * 0.5f;
        f3 = ((pr)this.l).ba;
        if (f3 <= 0.0f) {
            f3 *= 0.25f;
            this.bN = 0;
        }
        if (this.C && this.br == 0.0f && this.cz() && !this.bG) {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        if (this.br > 0.0f && !this.cv() && this.C) {
            this.w = this.cF() * (double)this.br;
            if (this.a(pe.j)) {
                this.w += (double)((float)(this.b(pe.j).c() + 1) * 0.1f);
            }
            this.m(true);
            this.ai = true;
            if (f3 > 0.0f) {
                \u2603 = ns.a(this.y * (float)Math.PI / 180.0f);
                \u2603 = ns.b(this.y * (float)Math.PI / 180.0f);
                this.v += (double)(-0.4f * \u2603 * this.br);
                this.x += (double)(0.4f * \u2603 * this.br);
                this.a("mob.horse.jump", 0.4f, 1.0f);
            }
            this.br = 0.0f;
        }
        this.S = 1.0f;
        this.aM = this.bI() * 0.1f;
        if (!this.o.D) {
            this.k((float)this.a(vy.d).e());
            super.g(f2, f3);
        }
        if (this.C) {
            this.br = 0.0f;
            this.m(false);
        }
        this.aA = this.aB;
        double d2 = this.s - this.p;
        \u2603 = this.u - this.r;
        float \u26032 = ns.a(d2 * d2 + \u2603 * \u2603) * 4.0f;
        if (\u26032 > 1.0f) {
            \u26032 = 1.0f;
        }
        this.aB += (\u26032 - this.aB) * 0.4f;
        this.aC += this.aB;
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("EatingHaystack", this.cy());
        dn2.a("ChestedHorse", this.cw());
        dn2.a("HasReproduced", this.cB());
        dn2.a("Bred", this.cA());
        dn2.a("Type", this.cl());
        dn2.a("Variant", this.cm());
        dn2.a("Temper", this.cC());
        dn2.a("Tame", this.co());
        dn2.a("OwnerUUID", this.ct());
        if (this.cw()) {
            du du2 = new du();
            for (int i2 = 2; i2 < this.bE.o_(); ++i2) {
                zx zx2 = this.bE.a(i2);
                if (zx2 == null) continue;
                dn \u26032 = new dn();
                \u26032.a("Slot", (byte)i2);
                zx2.b(\u26032);
                du2.a(\u26032);
            }
            dn2.a("Items", du2);
        }
        if (this.bE.a(1) != null) {
            dn2.a("ArmorItem", this.bE.a(1).b(new dn()));
        }
        if (this.bE.a(0) != null) {
            dn2.a("SaddleItem", this.bE.a(0).b(new dn()));
        }
    }

    @Override
    public void a(dn dn22) {
        dn dn22;
        super.a(dn22);
        this.r(dn22.n("EatingHaystack"));
        this.n(dn22.n("Bred"));
        this.o(dn22.n("ChestedHorse"));
        this.p(dn22.n("HasReproduced"));
        this.r(dn22.f("Type"));
        this.s(dn22.f("Variant"));
        this.t(dn22.f("Temper"));
        this.l(dn22.n("Tame"));
        String \u26032 = "";
        if (dn22.b("OwnerUUID", 8)) {
            \u26032 = dn22.j("OwnerUUID");
        } else {
            Object object = dn22.j("Owner");
            \u26032 = lw.a((String)object);
        }
        if (\u26032.length() > 0) {
            this.b(\u26032);
        }
        if ((object = this.by().a("Speed")) != null) {
            this.a(vy.d).a(object.b() * 0.25);
        }
        if (this.cw()) {
            object = dn22.c("Items", 10);
            this.da();
            for (int i2 = 0; i2 < ((du)object).c(); ++i2) {
                dn dn3 = ((du)object).b(i2);
                int \u26033 = dn3.d("Slot") & 0xFF;
                if (\u26033 < 2 || \u26033 >= this.bE.o_()) continue;
                this.bE.a(\u26033, zx.a(dn3));
            }
        }
        if (dn22.b("ArmorItem", 10) && (object = zx.a(dn22.m("ArmorItem"))) != null && tp.a(((zx)object).b())) {
            this.bE.a(1, (zx)object);
        }
        if (dn22.b("SaddleItem", 10)) {
            Object object = zx.a(dn22.m("SaddleItem"));
            if (object != null && ((zx)object).b() == zy.aA) {
                this.bE.a(0, (zx)object);
            }
        } else if (dn22.n("Saddle")) {
            this.bE.a(0, new zx(zy.aA));
        }
        this.db();
    }

    @Override
    public boolean a(tm tm2) {
        if (tm2 == this) {
            return false;
        }
        if (tm2.getClass() != this.getClass()) {
            return false;
        }
        tp tp2 = (tp)tm2;
        if (!this.dg() || !tp2.dg()) {
            return false;
        }
        int \u26032 = this.cl();
        return \u26032 == (\u2603 = tp2.cl()) || \u26032 == 0 && \u2603 == 1 || \u26032 == 1 && \u2603 == 0;
    }

    @Override
    public ph a(ph ph2) {
        tp tp2;
        tp tp3 = (tp)ph2;
        tp2 = new tp(this.o);
        int \u26032 = this.cl();
        int \u26033 = tp3.cl();
        int \u26034 = 0;
        if (\u26032 == \u26033) {
            \u26034 = \u26032;
        } else if (\u26032 == 0 && \u26033 == 1 || \u26032 == 1 && \u26033 == 0) {
            \u26034 = 2;
        }
        if (\u26034 == 0) {
            int n2 = this.V.nextInt(9);
            \u2603 = n2 < 4 ? this.cm() & 0xFF : (n2 < 8 ? tp3.cm() & 0xFF : this.V.nextInt(7));
            \u2603 = this.V.nextInt(5);
            \u2603 = \u2603 < 2 ? (\u2603 |= this.cm() & 0xFF00) : (\u2603 < 4 ? (\u2603 |= tp3.cm() & 0xFF00) : (\u2603 |= this.V.nextInt(5) << 8 & 0xFF00));
            tp2.s(\u2603);
        }
        tp2.r(\u26034);
        double \u26035 = this.a(vy.a).b() + ph2.a(vy.a).b() + (double)this.di();
        tp2.a(vy.a).a(\u26035 / 3.0);
        double \u26036 = this.a(bt).b() + ph2.a(bt).b() + this.dj();
        tp2.a(bt).a(\u26036 / 3.0);
        double \u26037 = this.a(vy.d).b() + ph2.a(vy.d).b() + this.dk();
        tp2.a(vy.d).a(\u26037 / 3.0);
        return tp2;
    }

    @Override
    public pu a(ok ok2, pu pu22) {
        pu22 = super.a(ok2, pu22);
        int n2 = 0;
        \u2603 = 0;
        if (pu22 instanceof a) {
            n2 = ((a)pu22).a;
            \u2603 = ((a)pu22).b & 0xFF | this.V.nextInt(5) << 8;
        } else {
            if (this.V.nextInt(10) == 0) {
                n2 = 1;
            } else {
                \u2603 = this.V.nextInt(7);
                \u2603 = this.V.nextInt(5);
                n2 = 0;
                \u2603 = \u2603 | \u2603 << 8;
            }
            pu pu22 = new a(n2, \u2603);
        }
        this.r(n2);
        this.s(\u2603);
        if (this.V.nextInt(5) == 0) {
            this.b(-24000);
        }
        if (n2 == 4 || n2 == 3) {
            this.a(vy.a).a(15.0);
            this.a(vy.d).a(0.2f);
        } else {
            this.a(vy.a).a(this.di());
            if (n2 == 0) {
                this.a(vy.d).a(this.dk());
            } else {
                this.a(vy.d).a(0.175f);
            }
        }
        if (n2 == 2 || n2 == 1) {
            this.a(bt).a(0.5);
        } else {
            this.a(bt).a(this.dj());
        }
        this.i(this.bu());
        return pu22;
    }

    public float p(float f2) {
        return this.bI + (this.bH - this.bI) * f2;
    }

    public float q(float f2) {
        return this.bK + (this.bJ - this.bK) * f2;
    }

    public float r(float f2) {
        return this.bM + (this.bL - this.bM) * f2;
    }

    public void v(int n2) {
        if (this.cG()) {
            if (n2 < 0) {
                n2 = 0;
            } else {
                this.bG = true;
                this.dh();
            }
            this.br = n2 >= 90 ? 1.0f : 0.4f + 0.4f * (float)n2 / 90.0f;
        }
    }

    protected void t(boolean bl2) {
        cy cy2 = bl2 ? cy.I : cy.l;
        for (int i2 = 0; i2 < 7; ++i2) {
            double d2 = this.V.nextGaussian() * 0.02;
            \u2603 = this.V.nextGaussian() * 0.02;
            \u2603 = this.V.nextGaussian() * 0.02;
            this.o.a(cy2, this.s + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, this.t + 0.5 + (double)(this.V.nextFloat() * this.K), this.u + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, d2, \u2603, \u2603, new int[0]);
        }
    }

    @Override
    public void a(byte by) {
        if (by == 7) {
            this.t(true);
        } else if (by == 6) {
            this.t(false);
        } else {
            super.a(by);
        }
    }

    @Override
    public void al() {
        super.al();
        if (this.bK > 0.0f) {
            float f2 = ns.a(this.aI * (float)Math.PI / 180.0f);
            \u2603 = ns.b(this.aI * (float)Math.PI / 180.0f);
            \u2603 = 0.7f * this.bK;
            \u2603 = 0.15f * this.bK;
            this.l.b(this.s + (double)(\u2603 * f2), this.t + this.an() + this.l.am() + (double)\u2603, this.u - (double)(\u2603 * \u2603));
            if (this.l instanceof pr) {
                ((pr)this.l).aI = this.aI;
            }
        }
    }

    private float di() {
        return 15.0f + (float)this.V.nextInt(8) + (float)this.V.nextInt(9);
    }

    private double dj() {
        return (double)0.4f + this.V.nextDouble() * 0.2 + this.V.nextDouble() * 0.2 + this.V.nextDouble() * 0.2;
    }

    private double dk() {
        return ((double)0.45f + this.V.nextDouble() * 0.3 + this.V.nextDouble() * 0.3 + this.V.nextDouble() * 0.3) * 0.25;
    }

    public static boolean a(zw zw2) {
        return zw2 == zy.ck || zw2 == zy.cl || zw2 == zy.cm;
    }

    @Override
    public boolean k_() {
        return false;
    }

    @Override
    public float aS() {
        return this.K;
    }

    @Override
    public boolean d(int n2, zx zx2) {
        if (n2 == 499 && this.cP()) {
            if (zx2 == null && this.cw()) {
                this.o(false);
                this.da();
                return true;
            }
            if (zx2 != null && zx2.b() == zw.a(afi.ae) && !this.cw()) {
                this.o(true);
                this.da();
                return true;
            }
        }
        if ((\u2603 = n2 - 400) >= 0 && \u2603 < 2 && \u2603 < this.bE.o_()) {
            if (\u2603 == 0 && zx2 != null && zx2.b() != zy.aA) {
                return false;
            }
            if (\u2603 == 1 && (zx2 != null && !tp.a(zx2.b()) || !this.cO())) {
                return false;
            }
            this.bE.a(\u2603, zx2);
            this.db();
            return true;
        }
        int n3 = n2 - 500 + 2;
        if (n3 >= 2 && n3 < this.bE.o_()) {
            this.bE.a(n3, zx2);
            return true;
        }
        return false;
    }

    public static class a
    implements pu {
        public int a;
        public int b;

        public a(int n2, int n3) {
            this.a = n2;
            this.b = n3;
        }
    }
}

