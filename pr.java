/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public abstract class pr
extends pk {
    private static final UUID a = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
    private static final qd b = new qd(a, "Sprinting speed boost", 0.3f, 2).a(false);
    private qf c;
    private final ov f = new ov(this);
    private final Map<Integer, pf> g = Maps.newHashMap();
    private final zx[] h = new zx[5];
    public boolean ar;
    public int as;
    public int at;
    public int au;
    public int av;
    public float aw;
    public int ax;
    public float ay;
    public float az;
    public float aA;
    public float aB;
    public float aC;
    public int aD = 20;
    public float aE;
    public float aF;
    public float aG;
    public float aH;
    public float aI;
    public float aJ;
    public float aK;
    public float aL;
    public float aM = 0.02f;
    protected wn aN;
    protected int aO;
    protected boolean aP;
    protected int aQ;
    protected float aR;
    protected float aS;
    protected float aT;
    protected float aU;
    protected float aV;
    protected int aW;
    protected float aX;
    protected boolean aY;
    public float aZ;
    public float ba;
    protected float bb;
    protected int bc;
    protected double bd;
    protected double be;
    protected double bf;
    protected double bg;
    protected double bh;
    private boolean i = true;
    private pr bi;
    private int bj;
    private pr bk;
    private int bl;
    private float bm;
    private int bn;
    private float bo;

    @Override
    public void G() {
        this.a(ow.j, Float.MAX_VALUE);
    }

    public pr(adm adm2) {
        super(adm2);
        this.aX();
        this.i(this.bu());
        this.k = true;
        this.aH = (float)((Math.random() + 1.0) * (double)0.01f);
        this.b(this.s, this.t, this.u);
        this.aG = (float)Math.random() * 12398.0f;
        this.aK = this.y = (float)(Math.random() * 3.1415927410125732 * 2.0);
        this.S = 0.6f;
    }

    @Override
    protected void h() {
        this.ac.a(7, Integer.valueOf(0));
        this.ac.a(8, Byte.valueOf((byte)0));
        this.ac.a(9, Byte.valueOf((byte)0));
        this.ac.a(6, Float.valueOf(1.0f));
    }

    protected void aX() {
        this.by().b(vy.a);
        this.by().b(vy.c);
        this.by().b(vy.d);
    }

    @Override
    protected void a(double d2, boolean bl2, afh afh2, cj cj2) {
        if (!this.V()) {
            this.W();
        }
        if (!this.o.D && this.O > 3.0f && bl2) {
            alz alz2 = this.o.p(cj2);
            afh \u26032 = alz2.c();
            float \u26033 = ns.f(this.O - 3.0f);
            if (\u26032.t() != arm.a) {
                double d3 = Math.min(0.2f + \u26033 / 15.0f, 10.0f);
                if (d3 > 2.5) {
                    d3 = 2.5;
                }
                int \u26034 = (int)(150.0 * d3);
                ((le)this.o).a(cy.M, this.s, this.t, this.u, \u26034, 0.0, 0.0, 0.0, (double)0.15f, afh.f(alz2));
            }
        }
        super.a(d2, bl2, afh2, cj2);
    }

    public boolean aY() {
        return false;
    }

    @Override
    public void K() {
        this.ay = this.az;
        super.K();
        this.o.B.a("livingEntityBaseTick");
        boolean bl2 = this instanceof wn;
        if (this.ai()) {
            if (this.aj()) {
                this.a(ow.e, 1.0f);
            } else if (bl2 && !this.o.af().a(this.aR()) && (\u2603 = this.o.af().a(this) + this.o.af().m()) < 0.0) {
                this.a(ow.e, (float)Math.max(1, ns.c(-\u2603 * this.o.af().n())));
            }
        }
        if (this.T() || this.o.D) {
            this.N();
        }
        boolean bl3 = \u2603 = bl2 && ((wn)this).bA.a;
        if (this.ai()) {
            if (this.a(arm.h)) {
                if (!(this.aY() || this.k(pe.o.H) || \u2603)) {
                    this.h(this.j(this.az()));
                    if (this.az() == -20) {
                        this.h(0);
                        for (int i2 = 0; i2 < 8; ++i2) {
                            float f2 = this.V.nextFloat() - this.V.nextFloat();
                            \u2603 = this.V.nextFloat() - this.V.nextFloat();
                            \u2603 = this.V.nextFloat() - this.V.nextFloat();
                            this.o.a(cy.e, this.s + (double)f2, this.t + (double)\u2603, this.u + (double)\u2603, this.v, this.w, this.x, new int[0]);
                        }
                        this.a(ow.f, 2.0f);
                    }
                }
                if (!this.o.D && this.au() && this.m instanceof pr) {
                    this.a((pk)null);
                }
            } else {
                this.h(300);
            }
        }
        if (this.ai() && this.U()) {
            this.N();
        }
        this.aE = this.aF;
        if (this.au > 0) {
            --this.au;
        }
        if (this.Z > 0 && !(this instanceof lf)) {
            --this.Z;
        }
        if (this.bn() <= 0.0f) {
            this.aZ();
        }
        if (this.aO > 0) {
            --this.aO;
        } else {
            this.aN = null;
        }
        if (this.bk != null && !this.bk.ai()) {
            this.bk = null;
        }
        if (this.bi != null) {
            if (!this.bi.ai()) {
                this.b((pr)null);
            } else if (this.W - this.bj > 100) {
                this.b((pr)null);
            }
        }
        this.bi();
        this.aU = this.aT;
        this.aJ = this.aI;
        this.aL = this.aK;
        this.A = this.y;
        this.B = this.z;
        this.o.B.b();
    }

    public boolean j_() {
        return false;
    }

    protected void aZ() {
        ++this.ax;
        if (this.ax == 20) {
            if (!this.o.D && (this.aO > 0 || this.bb()) && this.ba() && this.o.Q().b("doMobLoot")) {
                int n2;
                for (i2 = this.b(this.aN); i2 > 0; i2 -= n2) {
                    n2 = pp.a(i2);
                    this.o.d(new pp(this.o, this.s, this.t, this.u, n2));
                }
            }
            this.J();
            for (int i2 = 0; i2 < 20; ++i2) {
                double d2 = this.V.nextGaussian() * 0.02;
                \u2603 = this.V.nextGaussian() * 0.02;
                \u2603 = this.V.nextGaussian() * 0.02;
                this.o.a(cy.a, this.s + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, this.t + (double)(this.V.nextFloat() * this.K), this.u + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, d2, \u2603, \u2603, new int[0]);
            }
        }
    }

    protected boolean ba() {
        return !this.j_();
    }

    protected int j(int n2) {
        \u2603 = ack.a((pk)this);
        if (\u2603 > 0 && this.V.nextInt(\u2603 + 1) > 0) {
            return n2;
        }
        return n2 - 1;
    }

    protected int b(wn wn2) {
        return 0;
    }

    protected boolean bb() {
        return false;
    }

    public Random bc() {
        return this.V;
    }

    public pr bd() {
        return this.bi;
    }

    public int be() {
        return this.bj;
    }

    public void b(pr pr2) {
        this.bi = pr2;
        this.bj = this.W;
    }

    public pr bf() {
        return this.bk;
    }

    public int bg() {
        return this.bl;
    }

    public void p(pk pk2) {
        this.bk = pk2 instanceof pr ? (pr)pk2 : null;
        this.bl = this.W;
    }

    public int bh() {
        return this.aQ;
    }

    @Override
    public void b(dn dn22) {
        dn dn22;
        dn22.a("HealF", this.bn());
        dn22.a("Health", (short)Math.ceil(this.bn()));
        dn22.a("HurtTime", (short)this.au);
        dn22.a("HurtByTimestamp", this.bj);
        dn22.a("DeathTime", (short)this.ax);
        dn22.a("AbsorptionAmount", this.bN());
        for (zx zx2 : this.as()) {
            if (zx2 == null) continue;
            this.c.a(zx2.B());
        }
        dn22.a("Attributes", vy.a(this.by()));
        for (zx zx2 : this.as()) {
            if (zx2 == null) continue;
            this.c.b(zx2.B());
        }
        if (!this.g.isEmpty()) {
            du du2 = new du();
            for (pf pf2 : this.g.values()) {
                du2.a(pf2.a(new dn()));
            }
            dn22.a("ActiveEffects", du2);
        }
    }

    @Override
    public void a(dn dn22) {
        dn dn22;
        eb eb2;
        this.m(dn22.h("AbsorptionAmount"));
        if (dn22.b("Attributes", 9) && this.o != null && !this.o.D) {
            vy.a(this.by(), dn22.c("Attributes", 10));
        }
        if (dn22.b("ActiveEffects", 9)) {
            eb2 = dn22.c("ActiveEffects", 10);
            for (int i2 = 0; i2 < ((du)eb2).c(); ++i2) {
                dn dn3 = ((du)eb2).b(i2);
                pf \u26032 = pf.b(dn3);
                if (\u26032 == null) continue;
                this.g.put(\u26032.a(), \u26032);
            }
        }
        if (dn22.b("HealF", 99)) {
            this.i(dn22.h("HealF"));
        } else {
            eb2 = dn22.a("Health");
            if (eb2 == null) {
                this.i(this.bu());
            } else if (eb2.a() == 5) {
                this.i(((dr)eb2).h());
            } else if (eb2.a() == 2) {
                this.i((float)((dz)eb2).e());
            }
        }
        this.au = dn22.e("HurtTime");
        this.ax = dn22.e("DeathTime");
        this.bj = dn22.f("HurtByTimestamp");
    }

    protected void bi() {
        Iterator<Integer> iterator = this.g.keySet().iterator();
        while (iterator.hasNext()) {
            Integer n2 = iterator.next();
            pf \u26032 = this.g.get(n2);
            if (!\u26032.a(this)) {
                if (this.o.D) continue;
                iterator.remove();
                this.b(\u26032);
                continue;
            }
            if (\u26032.b() % 600 != 0) continue;
            this.a(\u26032, false);
        }
        if (this.i) {
            if (!this.o.D) {
                this.B();
            }
            this.i = false;
        }
        int n3 = this.ac.c(7);
        boolean bl2 = \u2603 = this.ac.a(8) > 0;
        if (n3 > 0) {
            boolean bl3 = false;
            if (!this.ax()) {
                bl3 = this.V.nextBoolean();
            } else {
                boolean bl4 = bl3 = this.V.nextInt(15) == 0;
            }
            if (\u2603) {
                bl3 &= this.V.nextInt(5) == 0;
            }
            if (bl3 && n3 > 0) {
                double d2 = (double)(n3 >> 16 & 0xFF) / 255.0;
                \u2603 = (double)(n3 >> 8 & 0xFF) / 255.0;
                \u2603 = (double)(n3 >> 0 & 0xFF) / 255.0;
                this.o.a(\u2603 ? cy.q : cy.p, this.s + (this.V.nextDouble() - 0.5) * (double)this.J, this.t + this.V.nextDouble() * (double)this.K, this.u + (this.V.nextDouble() - 0.5) * (double)this.J, d2, \u2603, \u2603, new int[0]);
            }
        }
    }

    protected void B() {
        if (this.g.isEmpty()) {
            this.bj();
            this.e(false);
        } else {
            int n2 = abe.a(this.g.values());
            this.ac.b(8, abe.b(this.g.values()) ? (byte)1 : 0);
            this.ac.b(7, n2);
            this.e(this.k(pe.p.H));
        }
    }

    protected void bj() {
        this.ac.b(8, (byte)0);
        this.ac.b(7, 0);
    }

    public void bk() {
        Iterator<Integer> iterator = this.g.keySet().iterator();
        while (iterator.hasNext()) {
            Integer n2 = iterator.next();
            pf \u26032 = this.g.get(n2);
            if (this.o.D) continue;
            iterator.remove();
            this.b(\u26032);
        }
    }

    public Collection<pf> bl() {
        return this.g.values();
    }

    public boolean k(int n2) {
        return this.g.containsKey(n2);
    }

    public boolean a(pe pe2) {
        return this.g.containsKey(pe2.H);
    }

    public pf b(pe pe2) {
        return this.g.get(pe2.H);
    }

    public void c(pf pf2) {
        if (!this.d(pf2)) {
            return;
        }
        if (this.g.containsKey(pf2.a())) {
            this.g.get(pf2.a()).a(pf2);
            this.a(this.g.get(pf2.a()), true);
        } else {
            this.g.put(pf2.a(), pf2);
            this.a(pf2);
        }
    }

    public boolean d(pf pf2) {
        return this.bz() != pw.b || (\u2603 = pf2.a()) != pe.l.H && \u2603 != pe.u.H;
    }

    public boolean bm() {
        return this.bz() == pw.b;
    }

    public void l(int n2) {
        this.g.remove(n2);
    }

    public void m(int n2) {
        pf pf2 = this.g.remove(n2);
        if (pf2 != null) {
            this.b(pf2);
        }
    }

    protected void a(pf pf2) {
        this.i = true;
        if (!this.o.D) {
            pe.a[pf2.a()].b(this, this.by(), pf2.c());
        }
    }

    protected void a(pf pf2, boolean bl2) {
        this.i = true;
        if (bl2 && !this.o.D) {
            pe.a[pf2.a()].a(this, this.by(), pf2.c());
            pe.a[pf2.a()].b(this, this.by(), pf2.c());
        }
    }

    protected void b(pf pf2) {
        this.i = true;
        if (!this.o.D) {
            pe.a[pf2.a()].a(this, this.by(), pf2.c());
        }
    }

    public void h(float f2) {
        \u2603 = this.bn();
        if (\u2603 > 0.0f) {
            this.i(\u2603 + f2);
        }
    }

    public final float bn() {
        return this.ac.d(6);
    }

    public void i(float f2) {
        this.ac.b(6, Float.valueOf(ns.a(f2, 0.0f, this.bu())));
    }

    @Override
    public boolean a(ow ow2, float f2) {
        Object object;
        if (this.b(ow2)) {
            return false;
        }
        if (this.o.D) {
            return false;
        }
        this.aQ = 0;
        if (this.bn() <= 0.0f) {
            return false;
        }
        if (ow2.o() && this.a(pe.n)) {
            return false;
        }
        if ((ow2 == ow.n || ow2 == ow.o) && this.p(4) != null) {
            this.p(4).a((int)(f2 * 4.0f + this.V.nextFloat() * f2 * 2.0f), this);
            f2 *= 0.75f;
        }
        this.aB = 1.5f;
        boolean bl2 = true;
        if ((float)this.Z > (float)this.aD / 2.0f) {
            if (f2 <= this.aX) {
                return false;
            }
            this.d(ow2, f2 - this.aX);
            this.aX = f2;
            bl2 = false;
        } else {
            this.aX = f2;
            this.Z = this.aD;
            this.d(ow2, f2);
            this.av = 10;
            this.au = 10;
        }
        this.aw = 0.0f;
        pk \u26032 = ow2.j();
        if (\u26032 != null) {
            if (\u26032 instanceof pr) {
                this.b((pr)\u26032);
            }
            if (\u26032 instanceof wn) {
                this.aO = 100;
                this.aN = (wn)\u26032;
            } else if (\u26032 instanceof ua && ((qa)(object = (ua)\u26032)).cl()) {
                this.aO = 100;
                this.aN = null;
            }
        }
        if (bl2) {
            this.o.a((pk)this, (byte)2);
            if (ow2 != ow.f) {
                this.ac();
            }
            if (\u26032 != null) {
                double d2 = \u26032.s - this.s;
                \u2603 = \u26032.u - this.u;
                while (d2 * d2 + \u2603 * \u2603 < 1.0E-4) {
                    d2 = (Math.random() - Math.random()) * 0.01;
                    \u2603 = (Math.random() - Math.random()) * 0.01;
                }
                this.aw = (float)(ns.b(\u2603, d2) * 180.0 / 3.1415927410125732 - (double)this.y);
                this.a(\u26032, f2, d2, \u2603);
            } else {
                this.aw = (int)(Math.random() * 2.0) * 180;
            }
        }
        if (this.bn() <= 0.0f) {
            object = this.bp();
            if (bl2 && object != null) {
                this.a((String)object, this.bB(), this.bC());
            }
            this.a(ow2);
        } else {
            object = this.bo();
            if (bl2 && object != null) {
                this.a((String)object, this.bB(), this.bC());
            }
        }
        return true;
    }

    public void b(zx zx2) {
        this.a("random.break", 0.8f, 0.8f + this.o.s.nextFloat() * 0.4f);
        for (int i2 = 0; i2 < 5; ++i2) {
            aui aui2 = new aui(((double)this.V.nextFloat() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, 0.0);
            aui2 = aui2.a(-this.z * (float)Math.PI / 180.0f);
            aui2 = aui2.b(-this.y * (float)Math.PI / 180.0f);
            double \u26032 = (double)(-this.V.nextFloat()) * 0.6 - 0.3;
            \u2603 = new aui(((double)this.V.nextFloat() - 0.5) * 0.3, \u26032, 0.6);
            \u2603 = \u2603.a(-this.z * (float)Math.PI / 180.0f);
            \u2603 = \u2603.b(-this.y * (float)Math.PI / 180.0f);
            \u2603 = \u2603.b(this.s, this.t + (double)this.aS(), this.u);
            this.o.a(cy.K, \u2603.a, \u2603.b, \u2603.c, aui2.a, aui2.b + 0.05, aui2.c, zw.b(zx2.b()));
        }
    }

    public void a(ow ow2) {
        pk pk2 = ow2.j();
        pr \u26032 = this.bt();
        if (this.aW >= 0 && \u26032 != null) {
            \u26032.b(this, this.aW);
        }
        if (pk2 != null) {
            pk2.a(this);
        }
        this.aP = true;
        this.bs().g();
        if (!this.o.D) {
            int n2 = 0;
            if (pk2 instanceof wn) {
                n2 = ack.i((pr)pk2);
            }
            if (this.ba() && this.o.Q().b("doMobLoot")) {
                this.b(this.aO > 0, n2);
                this.a(this.aO > 0, n2);
                if (this.aO > 0 && this.V.nextFloat() < 0.025f + (float)n2 * 0.01f) {
                    this.bq();
                }
            }
        }
        this.o.a((pk)this, (byte)3);
    }

    protected void a(boolean bl2, int n2) {
    }

    public void a(pk pk2, float f2, double d2, double d3) {
        if (this.V.nextDouble() < this.a(vy.c).e()) {
            return;
        }
        this.ai = true;
        float f3 = ns.a(d2 * d2 + d3 * d3);
        \u2603 = 0.4f;
        this.v /= 2.0;
        this.w /= 2.0;
        this.x /= 2.0;
        this.v -= d2 / (double)f3 * (double)\u2603;
        this.w += (double)\u2603;
        this.x -= d3 / (double)f3 * (double)\u2603;
        if (this.w > (double)0.4f) {
            this.w = 0.4f;
        }
    }

    protected String bo() {
        return "game.neutral.hurt";
    }

    protected String bp() {
        return "game.neutral.die";
    }

    protected void bq() {
    }

    protected void b(boolean bl2, int n2) {
    }

    public boolean k_() {
        int n2 = ns.c(this.s);
        afh \u26032 = this.o.p(new cj(n2, \u2603 = ns.c(this.aR().b), \u2603 = ns.c(this.u))).c();
        return !(\u26032 != afi.au && \u26032 != afi.bn || this instanceof wn && ((wn)this).v());
    }

    @Override
    public boolean ai() {
        return !this.I && this.bn() > 0.0f;
    }

    @Override
    public void e(float f2, float f3) {
        super.e(f2, f3);
        pf pf2 = this.b(pe.j);
        float \u26032 = pf2 != null ? (float)(pf2.c() + 1) : 0.0f;
        int \u26033 = ns.f((f2 - 3.0f - \u26032) * f3);
        if (\u26033 > 0) {
            this.a(this.n(\u26033), 1.0f, 1.0f);
            this.a(ow.i, (float)\u26033);
            int n2 = ns.c(this.s);
            \u2603 = ns.c(this.t - (double)0.2f);
            \u2603 = ns.c(this.u);
            afh \u26034 = this.o.p(new cj(n2, \u2603, \u2603)).c();
            if (\u26034.t() != arm.a) {
                afh.b b2 = \u26034.H;
                this.a(b2.c(), b2.d() * 0.5f, b2.e() * 0.75f);
            }
        }
    }

    protected String n(int n2) {
        if (n2 > 4) {
            return "game.neutral.hurt.fall.big";
        }
        return "game.neutral.hurt.fall.small";
    }

    @Override
    public void ar() {
        this.av = 10;
        this.au = 10;
        this.aw = 0.0f;
    }

    public int br() {
        int n2 = 0;
        for (zx zx2 : this.as()) {
            if (zx2 == null || !(zx2.b() instanceof yj)) continue;
            int n3 = ((yj)zx2.b()).c;
            n2 += n3;
        }
        return n2;
    }

    protected void j(float f2) {
    }

    protected float b(ow ow2, float \u260332) {
        float \u260332;
        if (!ow2.e()) {
            int n2 = 25 - this.br();
            float \u26032 = \u260332 * (float)n2;
            this.j(\u260332);
            \u260332 = \u26032 / 25.0f;
        }
        return \u260332;
    }

    protected float c(ow ow2, float \u260332) {
        float \u260332;
        float \u26032;
        if (ow2.h()) {
            return \u260332;
        }
        if (this.a(pe.m) && ow2 != ow.j) {
            int n2 = (this.b(pe.m).c() + 1) * 5;
            \u2603 = 25 - n2;
            \u26032 = \u260332 * (float)\u2603;
            \u260332 = \u26032 / 25.0f;
        }
        if (\u260332 <= 0.0f) {
            return 0.0f;
        }
        n2 = ack.a(this.as(), ow2);
        if (n2 > 20) {
            n2 = 20;
        }
        if (n2 > 0 && n2 <= 20) {
            \u2603 = 25 - n2;
            \u26032 = \u260332 * (float)\u2603;
            \u260332 = \u26032 / 25.0f;
        }
        return \u260332;
    }

    protected void d(ow ow2, float f2) {
        if (this.b(ow2)) {
            return;
        }
        f2 = this.b(ow2, f2);
        \u2603 = f2 = this.c(ow2, f2);
        f2 = Math.max(f2 - this.bN(), 0.0f);
        this.m(this.bN() - (\u2603 - f2));
        if (f2 == 0.0f) {
            return;
        }
        \u2603 = this.bn();
        this.i(\u2603 - f2);
        this.bs().a(ow2, \u2603, f2);
        this.m(this.bN() - f2);
    }

    public ov bs() {
        return this.f;
    }

    public pr bt() {
        if (this.f.c() != null) {
            return this.f.c();
        }
        if (this.aN != null) {
            return this.aN;
        }
        if (this.bi != null) {
            return this.bi;
        }
        return null;
    }

    public final float bu() {
        return (float)this.a(vy.a).e();
    }

    public final int bv() {
        return this.ac.a(9);
    }

    public final void o(int n2) {
        this.ac.b(9, (byte)n2);
    }

    private int n() {
        if (this.a(pe.e)) {
            return 6 - (1 + this.b(pe.e).c()) * 1;
        }
        if (this.a(pe.f)) {
            return 6 + (1 + this.b(pe.f).c()) * 2;
        }
        return 6;
    }

    public void bw() {
        if (!this.ar || this.as >= this.n() / 2 || this.as < 0) {
            this.as = -1;
            this.ar = true;
            if (this.o instanceof le) {
                ((le)this.o).s().a(this, new fq(this, 0));
            }
        }
    }

    @Override
    public void a(byte by2) {
        byte by2;
        if (by2 == 2) {
            this.aB = 1.5f;
            this.Z = this.aD;
            this.av = 10;
            this.au = 10;
            this.aw = 0.0f;
            String string = this.bo();
            if (string != null) {
                this.a(this.bo(), this.bB(), (this.V.nextFloat() - this.V.nextFloat()) * 0.2f + 1.0f);
            }
            this.a(ow.k, 0.0f);
        } else if (by2 == 3) {
            String string = this.bp();
            if (string != null) {
                this.a(this.bp(), this.bB(), (this.V.nextFloat() - this.V.nextFloat()) * 0.2f + 1.0f);
            }
            this.i(0.0f);
            this.a(ow.k);
        } else {
            super.a(by2);
        }
    }

    @Override
    protected void O() {
        this.a(ow.j, 4.0f);
    }

    protected void bx() {
        int n2 = this.n();
        if (this.ar) {
            ++this.as;
            if (this.as >= n2) {
                this.as = 0;
                this.ar = false;
            }
        } else {
            this.as = 0;
        }
        this.az = (float)this.as / (float)n2;
    }

    public qc a(qb qb2) {
        return this.by().a(qb2);
    }

    public qf by() {
        if (this.c == null) {
            this.c = new qi();
        }
        return this.c;
    }

    public pw bz() {
        return pw.a;
    }

    public abstract zx bA();

    public abstract zx p(int var1);

    public abstract zx q(int var1);

    @Override
    public abstract void c(int var1, zx var2);

    @Override
    public void d(boolean bl2) {
        super.d(bl2);
        qc qc2 = this.a(vy.d);
        if (qc2.a(a) != null) {
            qc2.c(b);
        }
        if (bl2) {
            qc2.b(b);
        }
    }

    @Override
    public abstract zx[] as();

    protected float bB() {
        return 1.0f;
    }

    protected float bC() {
        if (this.j_()) {
            return (this.V.nextFloat() - this.V.nextFloat()) * 0.2f + 1.5f;
        }
        return (this.V.nextFloat() - this.V.nextFloat()) * 0.2f + 1.0f;
    }

    protected boolean bD() {
        return this.bn() <= 0.0f;
    }

    public void q(pk pk2) {
        double \u26035;
        double \u26034;
        double \u26033 = pk2.s;
        \u26034 = pk2.aR().b + (double)pk2.K;
        \u26035 = pk2.u;
        int \u26032 = 1;
        for (int i2 = -\u26032; i2 <= \u26032; ++i2) {
            for (\u2603 = -\u26032; \u2603 < \u26032; ++\u2603) {
                if (i2 == 0 && \u2603 == 0) continue;
                \u2603 = (int)(this.s + (double)i2);
                \u2603 = (int)(this.u + (double)\u2603);
                aug aug2 = this.aR().c(i2, 1.0, \u2603);
                if (!this.o.a(aug2).isEmpty()) continue;
                if (adm.a(this.o, new cj(\u2603, (int)this.t, \u2603))) {
                    this.a(this.s + (double)i2, this.t + 1.0, this.u + (double)\u2603);
                    return;
                }
                if (!adm.a(this.o, new cj(\u2603, (int)this.t - 1, \u2603)) && this.o.p(new cj(\u2603, (int)this.t - 1, \u2603)).c().t() != arm.h) continue;
                \u26033 = this.s + (double)i2;
                \u26034 = this.t + 1.0;
                \u26035 = this.u + (double)\u2603;
            }
        }
        this.a(\u26033, \u26034, \u26035);
    }

    @Override
    public boolean aO() {
        return false;
    }

    protected float bE() {
        return 0.42f;
    }

    protected void bF() {
        this.w = this.bE();
        if (this.a(pe.j)) {
            this.w += (double)((float)(this.b(pe.j).c() + 1) * 0.1f);
        }
        if (this.aw()) {
            float f2 = this.y * ((float)Math.PI / 180);
            this.v -= (double)(ns.a(f2) * 0.2f);
            this.x += (double)(ns.b(f2) * 0.2f);
        }
        this.ai = true;
    }

    protected void bG() {
        this.w += (double)0.04f;
    }

    protected void bH() {
        this.w += (double)0.04f;
    }

    public void g(float f2, float f3) {
        if (this.bM()) {
            float \u26032;
            if (!(!this.V() || this instanceof wn && ((wn)this).bA.b)) {
                double d2 = this.t;
                \u26032 = 0.8f;
                float \u26033 = 0.02f;
                float \u26034 = ack.b((pk)this);
                if (\u26034 > 3.0f) {
                    \u26034 = 3.0f;
                }
                if (!this.C) {
                    \u26034 *= 0.5f;
                }
                if (\u26034 > 0.0f) {
                    \u26032 += (0.54600006f - \u26032) * \u26034 / 3.0f;
                    \u26033 += (this.bI() * 1.0f - \u26033) * \u26034 / 3.0f;
                }
                this.a(f2, f3, \u26033);
                this.d(this.v, this.w, this.x);
                this.v *= (double)\u26032;
                this.w *= (double)0.8f;
                this.x *= (double)\u26032;
                this.w -= 0.02;
                if (this.D && this.c(this.v, this.w + (double)0.6f - this.t + d2, this.x)) {
                    this.w = 0.3f;
                }
            } else if (!(!this.ab() || this instanceof wn && ((wn)this).bA.b)) {
                \u2603 = this.t;
                this.a(f2, f3, 0.02f);
                this.d(this.v, this.w, this.x);
                this.v *= 0.5;
                this.w *= 0.5;
                this.x *= 0.5;
                this.w -= 0.02;
                if (this.D && this.c(this.v, this.w + (double)0.6f - this.t + \u2603, this.x)) {
                    this.w = 0.3f;
                }
            } else {
                float \u26035 = 0.91f;
                if (this.C) {
                    \u26035 = this.o.p((cj)new cj((int)ns.c((double)this.s), (int)(ns.c((double)this.aR().b) - 1), (int)ns.c((double)this.u))).c().L * 0.91f;
                }
                float \u26036 = 0.16277136f / (\u26035 * \u26035 * \u26035);
                \u26032 = this.C ? this.bI() * \u26036 : this.aM;
                this.a(f2, f3, \u26032);
                \u26035 = 0.91f;
                if (this.C) {
                    \u26035 = this.o.p((cj)new cj((int)ns.c((double)this.s), (int)(ns.c((double)this.aR().b) - 1), (int)ns.c((double)this.u))).c().L * 0.91f;
                }
                if (this.k_()) {
                    float f4 = 0.15f;
                    this.v = ns.a(this.v, (double)(-f4), (double)f4);
                    this.x = ns.a(this.x, (double)(-f4), (double)f4);
                    this.O = 0.0f;
                    if (this.w < -0.15) {
                        this.w = -0.15;
                    }
                    boolean bl2 = \u2603 = this.av() && this instanceof wn;
                    if (\u2603 && this.w < 0.0) {
                        this.w = 0.0;
                    }
                }
                this.d(this.v, this.w, this.x);
                if (this.D && this.k_()) {
                    this.w = 0.2;
                }
                this.w = !this.o.D || this.o.e(new cj((int)this.s, 0, (int)this.u)) && this.o.f(new cj((int)this.s, 0, (int)this.u)).o() ? (this.w -= 0.08) : (this.t > 0.0 ? -0.1 : 0.0);
                this.w *= (double)0.98f;
                this.v *= (double)\u26035;
                this.x *= (double)\u26035;
            }
        }
        this.aA = this.aB;
        double d3 = this.s - this.p;
        \u2603 = this.u - this.r;
        float \u26037 = ns.a(d3 * d3 + \u2603 * \u2603) * 4.0f;
        if (\u26037 > 1.0f) {
            \u26037 = 1.0f;
        }
        this.aB += (\u26037 - this.aB) * 0.4f;
        this.aC += this.aB;
    }

    public float bI() {
        return this.bm;
    }

    public void k(float f2) {
        this.bm = f2;
    }

    public boolean r(pk pk2) {
        this.p(pk2);
        return false;
    }

    public boolean bJ() {
        return false;
    }

    @Override
    public void t_() {
        super.t_();
        if (!this.o.D) {
            int n2 = this.bv();
            if (n2 > 0) {
                if (this.at <= 0) {
                    this.at = 20 * (30 - n2);
                }
                --this.at;
                if (this.at <= 0) {
                    this.o(n2 - 1);
                }
            }
            for (\u2603 = 0; \u2603 < 5; ++\u2603) {
                zx zx2 = this.h[\u2603];
                \u2603 = this.p(\u2603);
                if (zx.b(\u2603, zx2)) continue;
                ((le)this.o).s().a(this, new hn(this.F(), \u2603, \u2603));
                if (zx2 != null) {
                    this.c.a(zx2.B());
                }
                if (\u2603 != null) {
                    this.c.b(\u2603.B());
                }
                this.h[\u2603] = \u2603 == null ? null : \u2603.k();
            }
            if (this.W % 20 == 0) {
                this.bs().g();
            }
        }
        this.m();
        double d2 = this.s - this.p;
        \u2603 = this.u - this.r;
        float \u26032 = (float)(d2 * d2 + \u2603 * \u2603);
        float \u26033 = this.aI;
        float \u26034 = 0.0f;
        this.aR = this.aS;
        float \u26035 = 0.0f;
        if (\u26032 > 0.0025000002f) {
            \u26035 = 1.0f;
            \u26034 = (float)Math.sqrt(\u26032) * 3.0f;
            \u26033 = (float)ns.b(\u2603, d2) * 180.0f / (float)Math.PI - 90.0f;
        }
        if (this.az > 0.0f) {
            \u26033 = this.y;
        }
        if (!this.C) {
            \u26035 = 0.0f;
        }
        this.aS += (\u26035 - this.aS) * 0.3f;
        this.o.B.a("headTurn");
        \u26034 = this.h(\u26033, \u26034);
        this.o.B.b();
        this.o.B.a("rangeChecks");
        while (this.y - this.A < -180.0f) {
            this.A -= 360.0f;
        }
        while (this.y - this.A >= 180.0f) {
            this.A += 360.0f;
        }
        while (this.aI - this.aJ < -180.0f) {
            this.aJ -= 360.0f;
        }
        while (this.aI - this.aJ >= 180.0f) {
            this.aJ += 360.0f;
        }
        while (this.z - this.B < -180.0f) {
            this.B -= 360.0f;
        }
        while (this.z - this.B >= 180.0f) {
            this.B += 360.0f;
        }
        while (this.aK - this.aL < -180.0f) {
            this.aL -= 360.0f;
        }
        while (this.aK - this.aL >= 180.0f) {
            this.aL += 360.0f;
        }
        this.o.B.b();
        this.aT += \u26034;
    }

    protected float h(float f2, float f3) {
        \u2603 = ns.g(f2 - this.aI);
        this.aI += \u2603 * 0.3f;
        \u2603 = ns.g(this.y - this.aI);
        boolean bl2 = \u2603 = \u2603 < -90.0f || \u2603 >= 90.0f;
        if (\u2603 < -75.0f) {
            \u2603 = -75.0f;
        }
        if (\u2603 >= 75.0f) {
            \u2603 = 75.0f;
        }
        this.aI = this.y - \u2603;
        if (\u2603 * \u2603 > 2500.0f) {
            this.aI += \u2603 * 0.2f;
        }
        if (\u2603) {
            f3 *= -1.0f;
        }
        return f3;
    }

    public void m() {
        if (this.bn > 0) {
            --this.bn;
        }
        if (this.bc > 0) {
            double d2 = this.s + (this.bd - this.s) / (double)this.bc;
            \u2603 = this.t + (this.be - this.t) / (double)this.bc;
            \u2603 = this.u + (this.bf - this.u) / (double)this.bc;
            \u2603 = ns.g(this.bg - (double)this.y);
            this.y = (float)((double)this.y + \u2603 / (double)this.bc);
            this.z = (float)((double)this.z + (this.bh - (double)this.z) / (double)this.bc);
            --this.bc;
            this.b(d2, \u2603, \u2603);
            this.b(this.y, this.z);
        } else if (!this.bM()) {
            this.v *= 0.98;
            this.w *= 0.98;
            this.x *= 0.98;
        }
        if (Math.abs(this.v) < 0.005) {
            this.v = 0.0;
        }
        if (Math.abs(this.w) < 0.005) {
            this.w = 0.0;
        }
        if (Math.abs(this.x) < 0.005) {
            this.x = 0.0;
        }
        this.o.B.a("ai");
        if (this.bD()) {
            this.aY = false;
            this.aZ = 0.0f;
            this.ba = 0.0f;
            this.bb = 0.0f;
        } else if (this.bM()) {
            this.o.B.a("newAi");
            this.bK();
            this.o.B.b();
        }
        this.o.B.b();
        this.o.B.a("jump");
        if (this.aY) {
            if (this.V()) {
                this.bG();
            } else if (this.ab()) {
                this.bH();
            } else if (this.C && this.bn == 0) {
                this.bF();
                this.bn = 10;
            }
        } else {
            this.bn = 0;
        }
        this.o.B.b();
        this.o.B.a("travel");
        this.aZ *= 0.98f;
        this.ba *= 0.98f;
        this.bb *= 0.9f;
        this.g(this.aZ, this.ba);
        this.o.B.b();
        this.o.B.a("push");
        if (!this.o.D) {
            this.bL();
        }
        this.o.B.b();
    }

    protected void bK() {
    }

    protected void bL() {
        List<pk> list = this.o.a(this, this.aR().b(0.2f, 0.0, 0.2f), Predicates.and(po.d, new Predicate<pk>(){

            public boolean a(pk pk2) {
                return pk2.ae();
            }

            @Override
            public /* synthetic */ boolean apply(Object object) {
                return this.a((pk)object);
            }
        }));
        if (!list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); ++i2) {
                pk pk2 = list.get(i2);
                this.s(pk2);
            }
        }
    }

    protected void s(pk pk2) {
        pk2.i(this);
    }

    @Override
    public void a(pk pk2) {
        if (this.m != null && pk2 == null) {
            if (!this.o.D) {
                this.q(this.m);
            }
            if (this.m != null) {
                this.m.l = null;
            }
            this.m = null;
            return;
        }
        super.a(pk2);
    }

    @Override
    public void ak() {
        super.ak();
        this.aR = this.aS;
        this.aS = 0.0f;
        this.O = 0.0f;
    }

    @Override
    public void a(double d2, double d3, double d4, float f2, float f3, int n2, boolean bl2) {
        this.bd = d2;
        this.be = d3;
        this.bf = d4;
        this.bg = f2;
        this.bh = f3;
        this.bc = n2;
    }

    public void i(boolean bl2) {
        this.aY = bl2;
    }

    public void a(pk pk2, int n2) {
        if (!pk2.I && !this.o.D) {
            la la2 = ((le)this.o).s();
            if (pk2 instanceof uz) {
                la2.a(pk2, new hy(pk2.F(), this.F()));
            }
            if (pk2 instanceof wq) {
                la2.a(pk2, new hy(pk2.F(), this.F()));
            }
            if (pk2 instanceof pp) {
                la2.a(pk2, new hy(pk2.F(), this.F()));
            }
        }
    }

    public boolean t(pk pk2) {
        return this.o.a(new aui(this.s, this.t + (double)this.aS(), this.u), new aui(pk2.s, pk2.t + (double)pk2.aS(), pk2.u)) == null;
    }

    @Override
    public aui ap() {
        return this.d(1.0f);
    }

    @Override
    public aui d(float f2) {
        if (f2 == 1.0f) {
            return this.f(this.z, this.aK);
        }
        \u2603 = this.B + (this.z - this.B) * f2;
        \u2603 = this.aL + (this.aK - this.aL) * f2;
        return this.f(\u2603, \u2603);
    }

    public float l(float f2) {
        \u2603 = this.az - this.ay;
        if (\u2603 < 0.0f) {
            \u2603 += 1.0f;
        }
        return this.ay + \u2603 * f2;
    }

    public boolean bM() {
        return !this.o.D;
    }

    @Override
    public boolean ad() {
        return !this.I;
    }

    @Override
    public boolean ae() {
        return !this.I;
    }

    @Override
    protected void ac() {
        this.G = this.V.nextDouble() >= this.a(vy.c).e();
    }

    @Override
    public float aC() {
        return this.aK;
    }

    @Override
    public void f(float f2) {
        this.aK = f2;
    }

    @Override
    public void g(float f2) {
        this.aI = f2;
    }

    public float bN() {
        return this.bo;
    }

    public void m(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.bo = f2;
    }

    public auq bO() {
        return this.o.Z().h(this.aK().toString());
    }

    public boolean c(pr pr2) {
        return this.a(pr2.bO());
    }

    public boolean a(auq auq2) {
        if (this.bO() != null) {
            return this.bO().a(auq2);
        }
        return false;
    }

    public void h_() {
    }

    public void j() {
    }

    protected void bP() {
        this.i = true;
    }
}

