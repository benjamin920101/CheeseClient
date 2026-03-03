/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ug
extends ps
implements uc,
ud,
vq {
    public double a;
    public double b;
    public double c;
    public double[][] bk = new double[64][3];
    public int bl = -1;
    public ue[] bm;
    public ue bn = new ue(this, "head", 6.0f, 6.0f);
    public ue bo = new ue(this, "body", 8.0f, 8.0f);
    public ue bp = new ue(this, "tail", 4.0f, 4.0f);
    public ue bq = new ue(this, "tail", 4.0f, 4.0f);
    public ue br = new ue(this, "tail", 4.0f, 4.0f);
    public ue bs = new ue(this, "wing", 4.0f, 4.0f);
    public ue bt = new ue(this, "wing", 4.0f, 4.0f);
    public float bu;
    public float bv;
    public boolean bw;
    public boolean bx;
    private pk bA;
    public int by;
    public uf bz;

    public ug(adm adm2) {
        super(adm2);
        this.bm = new ue[]{this.bn, this.bo, this.bp, this.bq, this.br, this.bs, this.bt};
        this.i(this.bu());
        this.a(16.0f, 8.0f);
        this.T = true;
        this.ab = true;
        this.b = 100.0;
        this.ah = true;
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(200.0);
    }

    @Override
    protected void h() {
        super.h();
    }

    public double[] b(int n2, float f2) {
        if (this.bn() <= 0.0f) {
            f2 = 0.0f;
        }
        f2 = 1.0f - f2;
        int n3 = this.bl - n2 * 1 & 0x3F;
        \u2603 = this.bl - n2 * 1 - 1 & 0x3F;
        double[] \u26032 = new double[3];
        double \u26033 = this.bk[n3][0];
        double \u26034 = ns.g(this.bk[\u2603][0] - \u26033);
        \u26032[0] = \u26033 + \u26034 * (double)f2;
        \u26033 = this.bk[n3][1];
        \u26034 = this.bk[\u2603][1] - \u26033;
        \u26032[1] = \u26033 + \u26034 * (double)f2;
        \u26032[2] = this.bk[n3][2] + (this.bk[\u2603][2] - this.bk[n3][2]) * (double)f2;
        return \u26032;
    }

    @Override
    public void m() {
        float \u26032;
        float f2;
        if (this.o.D) {
            f2 = ns.b(this.bv * (float)Math.PI * 2.0f);
            f3 = ns.b(this.bu * (float)Math.PI * 2.0f);
            if (f3 <= -0.3f && f2 >= -0.3f && !this.R()) {
                this.o.a(this.s, this.t, this.u, "mob.enderdragon.wings", 5.0f, 0.8f + this.V.nextFloat() * 0.3f, false);
            }
        }
        this.bu = this.bv;
        if (this.bn() <= 0.0f) {
            f2 = (this.V.nextFloat() - 0.5f) * 8.0f;
            f3 = (this.V.nextFloat() - 0.5f) * 4.0f;
            \u2603 = (this.V.nextFloat() - 0.5f) * 8.0f;
            this.o.a(cy.b, this.s + (double)f2, this.t + 2.0 + (double)f3, this.u + (double)\u2603, 0.0, 0.0, 0.0, new int[0]);
            return;
        }
        this.n();
        f2 = 0.2f / (ns.a(this.v * this.v + this.x * this.x) * 10.0f + 1.0f);
        this.bv = this.bx ? (this.bv += f2 * 0.5f) : (this.bv += (f2 *= (float)Math.pow(2.0, this.w)));
        this.y = ns.g(this.y);
        if (this.ce()) {
            this.bv = 0.5f;
            return;
        }
        if (this.bl < 0) {
            for (int i2 = 0; i2 < this.bk.length; ++i2) {
                this.bk[i2][0] = this.y;
                this.bk[i2][1] = this.t;
            }
        }
        if (++this.bl == this.bk.length) {
            this.bl = 0;
        }
        this.bk[this.bl][0] = this.y;
        this.bk[this.bl][1] = this.t;
        if (this.o.D) {
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
            }
        } else {
            double d3 = this.a - this.s;
            \u2603 = this.b - this.t;
            \u2603 = this.c - this.u;
            \u2603 = d3 * d3 + \u2603 * \u2603 + \u2603 * \u2603;
            if (this.bA != null) {
                this.a = this.bA.s;
                this.c = this.bA.u;
                \u2603 = this.a - this.s;
                \u2603 = this.c - this.u;
                \u2603 = Math.sqrt(\u2603 * \u2603 + \u2603 * \u2603);
                \u2603 = (double)0.4f + \u2603 / 80.0 - 1.0;
                if (\u2603 > 10.0) {
                    \u2603 = 10.0;
                }
                this.b = this.bA.aR().b + \u2603;
            } else {
                this.a += this.V.nextGaussian() * 2.0;
                this.c += this.V.nextGaussian() * 2.0;
            }
            if (this.bw || \u2603 < 100.0 || \u2603 > 22500.0 || this.D || this.E) {
                this.cf();
            }
            \u2603 /= (double)ns.a(d3 * d3 + \u2603 * \u2603);
            \u26032 = 0.6f;
            \u2603 = ns.a(\u2603, (double)(-\u26032), (double)\u26032);
            this.w += \u2603 * (double)0.1f;
            this.y = ns.g(this.y);
            \u2603 = 180.0 - ns.b(d3, \u2603) * 180.0 / 3.1415927410125732;
            \u2603 = ns.g(\u2603 - (double)this.y);
            if (\u2603 > 50.0) {
                \u2603 = 50.0;
            }
            if (\u2603 < -50.0) {
                \u2603 = -50.0;
            }
            aui \u26033 = new aui(this.a - this.s, this.b - this.t, this.c - this.u).a();
            \u2603 = -ns.b(this.y * (float)Math.PI / 180.0f);
            aui \u26034 = new aui(ns.a(this.y * (float)Math.PI / 180.0f), this.w, \u2603).a();
            float \u26035 = ((float)\u26034.b(\u26033) + 0.5f) / 1.5f;
            if (\u26035 < 0.0f) {
                \u26035 = 0.0f;
            }
            this.bb *= 0.8f;
            float \u26036 = ns.a(this.v * this.v + this.x * this.x) * 1.0f + 1.0f;
            \u2603 = Math.sqrt(this.v * this.v + this.x * this.x) * 1.0 + 1.0;
            if (\u2603 > 40.0) {
                \u2603 = 40.0;
            }
            this.bb = (float)((double)this.bb + \u2603 * ((double)0.7f / \u2603 / (double)\u26036));
            this.y += this.bb * 0.1f;
            float \u26037 = (float)(2.0 / (\u2603 + 1.0));
            float \u26038 = 0.06f;
            this.a(0.0f, -1.0f, \u26038 * (\u26035 * \u26037 + (1.0f - \u26037)));
            if (this.bx) {
                this.d(this.v * (double)0.8f, this.w * (double)0.8f, this.x * (double)0.8f);
            } else {
                this.d(this.v, this.w, this.x);
            }
            aui \u26039 = new aui(this.v, this.w, this.x).a();
            float \u260310 = ((float)\u26039.b(\u26034) + 1.0f) / 2.0f;
            \u260310 = 0.8f + 0.15f * \u260310;
            this.v *= (double)\u260310;
            this.x *= (double)\u260310;
            this.w *= (double)0.91f;
        }
        this.aI = this.y;
        this.bn.K = 3.0f;
        this.bn.J = 3.0f;
        this.bp.K = 2.0f;
        this.bp.J = 2.0f;
        this.bq.K = 2.0f;
        this.bq.J = 2.0f;
        this.br.K = 2.0f;
        this.br.J = 2.0f;
        this.bo.K = 3.0f;
        this.bo.J = 5.0f;
        this.bs.K = 2.0f;
        this.bs.J = 4.0f;
        this.bt.K = 3.0f;
        this.bt.J = 4.0f;
        float f3 = (float)(this.b(5, 1.0f)[1] - this.b(10, 1.0f)[1]) * 10.0f / 180.0f * (float)Math.PI;
        \u2603 = ns.b(f3);
        \u2603 = -ns.a(f3);
        \u2603 = this.y * (float)Math.PI / 180.0f;
        \u2603 = ns.a(\u2603);
        \u2603 = ns.b(\u2603);
        this.bo.t_();
        this.bo.b(this.s + (double)(\u2603 * 0.5f), this.t, this.u - (double)(\u2603 * 0.5f), 0.0f, 0.0f);
        this.bs.t_();
        this.bs.b(this.s + (double)(\u2603 * 4.5f), this.t + 2.0, this.u + (double)(\u2603 * 4.5f), 0.0f, 0.0f);
        this.bt.t_();
        this.bt.b(this.s - (double)(\u2603 * 4.5f), this.t + 2.0, this.u - (double)(\u2603 * 4.5f), 0.0f, 0.0f);
        if (!this.o.D && this.au == 0) {
            this.a(this.o.b(this, this.bs.aR().b(4.0, 2.0, 4.0).c(0.0, -2.0, 0.0)));
            this.a(this.o.b(this, this.bt.aR().b(4.0, 2.0, 4.0).c(0.0, -2.0, 0.0)));
            this.b(this.o.b(this, this.bn.aR().b(1.0, 1.0, 1.0)));
        }
        double[] \u260311 = this.b(5, 1.0f);
        double[] \u260312 = this.b(0, 1.0f);
        \u26032 = ns.a(this.y * (float)Math.PI / 180.0f - this.bb * 0.01f);
        \u2603 = ns.b(this.y * (float)Math.PI / 180.0f - this.bb * 0.01f);
        this.bn.t_();
        this.bn.b(this.s + (double)(\u26032 * 5.5f * \u2603), this.t + (\u260312[1] - \u260311[1]) * 1.0 + (double)(\u2603 * 5.5f), this.u - (double)(\u2603 * 5.5f * \u2603), 0.0f, 0.0f);
        for (int i3 = 0; i3 < 3; ++i3) {
            ue ue2 = null;
            if (i3 == 0) {
                ue2 = this.bp;
            }
            if (i3 == 1) {
                ue2 = this.bq;
            }
            if (i3 == 2) {
                ue2 = this.br;
            }
            double[] \u260313 = this.b(12 + i3 * 2, 1.0f);
            float \u260314 = this.y * (float)Math.PI / 180.0f + this.b(\u260313[0] - \u260311[0]) * (float)Math.PI / 180.0f * 1.0f;
            float \u260315 = ns.a(\u260314);
            float \u260316 = ns.b(\u260314);
            float \u260317 = 1.5f;
            float \u260318 = (float)(i3 + 1) * 2.0f;
            ue2.t_();
            ue2.b(this.s - (double)((\u2603 * \u260317 + \u260315 * \u260318) * \u2603), this.t + (\u260313[1] - \u260311[1]) * 1.0 - (double)((\u260318 + \u260317) * \u2603) + 1.5, this.u + (double)((\u2603 * \u260317 + \u260316 * \u260318) * \u2603), 0.0f, 0.0f);
        }
        if (!this.o.D) {
            this.bx = this.b(this.bn.aR()) | this.b(this.bo.aR());
        }
    }

    private void n() {
        if (this.bz != null) {
            if (this.bz.I) {
                if (!this.o.D) {
                    this.a(this.bn, ow.a(null), 10.0f);
                }
                this.bz = null;
            } else if (this.W % 10 == 0 && this.bn() < this.bu()) {
                this.i(this.bn() + 1.0f);
            }
        }
        if (this.V.nextInt(10) == 0) {
            float f2 = 32.0f;
            List<uf> \u26032 = this.o.a(uf.class, this.aR().b(f2, f2, f2));
            uf \u26033 = null;
            double \u26034 = Double.MAX_VALUE;
            for (uf uf2 : \u26032) {
                double d2 = uf2.h(this);
                if (!(d2 < \u26034)) continue;
                \u26034 = d2;
                \u26033 = uf2;
            }
            this.bz = \u26033;
        }
    }

    private void a(List<pk> list) {
        double d2 = (this.bo.aR().a + this.bo.aR().d) / 2.0;
        \u2603 = (this.bo.aR().c + this.bo.aR().f) / 2.0;
        for (pk pk2 : list) {
            if (!(pk2 instanceof pr)) continue;
            double d3 = pk2.s - d2;
            \u2603 = pk2.u - \u2603;
            \u2603 = d3 * d3 + \u2603 * \u2603;
            pk2.g(d3 / \u2603 * 4.0, 0.2f, \u2603 / \u2603 * 4.0);
        }
    }

    private void b(List<pk> list) {
        for (int i2 = 0; i2 < list.size(); ++i2) {
            pk pk2 = list.get(i2);
            if (!(pk2 instanceof pr)) continue;
            pk2.a(ow.a(this), 10.0f);
            this.a(this, pk2);
        }
    }

    private void cf() {
        this.bw = false;
        ArrayList<wn> arrayList = Lists.newArrayList(this.o.j);
        Iterator \u26032 = arrayList.iterator();
        while (\u26032.hasNext()) {
            if (!((wn)\u26032.next()).v()) continue;
            \u26032.remove();
        }
        if (this.V.nextInt(2) == 0 && !arrayList.isEmpty()) {
            this.bA = (pk)arrayList.get(this.V.nextInt(arrayList.size()));
        } else {
            do {
                this.a = 0.0;
                this.b = 70.0f + this.V.nextFloat() * 50.0f;
                this.c = 0.0;
                this.a += (double)(this.V.nextFloat() * 120.0f - 60.0f);
                this.c += (double)(this.V.nextFloat() * 120.0f - 60.0f);
            } while (!(\u2603 = (\u2603 = this.s - this.a) * \u2603 + (\u2603 = this.t - this.b) * \u2603 + (\u2603 = this.u - this.c) * \u2603 > 100.0));
            this.bA = null;
        }
    }

    private float b(double d2) {
        return (float)ns.g(d2);
    }

    private boolean b(aug aug2) {
        int n2 = ns.c(aug2.a);
        \u2603 = ns.c(aug2.b);
        \u2603 = ns.c(aug2.c);
        \u2603 = ns.c(aug2.d);
        \u2603 = ns.c(aug2.e);
        \u2603 = ns.c(aug2.f);
        boolean \u26032 = false;
        boolean \u26033 = false;
        for (\u2603 = n2; \u2603 <= \u2603; ++\u2603) {
            for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                    cj cj2 = new cj(\u2603, \u2603, \u2603);
                    afh \u26034 = this.o.p(cj2).c();
                    if (\u26034.t() == arm.a) continue;
                    if (\u26034 == afi.cv || \u26034 == afi.Z || \u26034 == afi.bH || \u26034 == afi.h || \u26034 == afi.bX || !this.o.Q().b("mobGriefing")) {
                        \u26032 = true;
                        continue;
                    }
                    \u26033 = this.o.g(cj2) || \u26033;
                }
            }
        }
        if (\u26033) {
            double d2 = aug2.a + (aug2.d - aug2.a) * (double)this.V.nextFloat();
            \u2603 = aug2.b + (aug2.e - aug2.b) * (double)this.V.nextFloat();
            \u2603 = aug2.c + (aug2.f - aug2.c) * (double)this.V.nextFloat();
            this.o.a(cy.b, d2, \u2603, \u2603, 0.0, 0.0, 0.0, new int[0]);
        }
        return \u26032;
    }

    @Override
    public boolean a(ue ue2, ow ow2, float f2) {
        if (ue2 != this.bn) {
            f2 = f2 / 4.0f + 1.0f;
        }
        \u2603 = this.y * (float)Math.PI / 180.0f;
        \u2603 = ns.a(\u2603);
        \u2603 = ns.b(\u2603);
        this.a = this.s + (double)(\u2603 * 5.0f) + (double)((this.V.nextFloat() - 0.5f) * 2.0f);
        this.b = this.t + (double)(this.V.nextFloat() * 3.0f) + 1.0;
        this.c = this.u - (double)(\u2603 * 5.0f) + (double)((this.V.nextFloat() - 0.5f) * 2.0f);
        this.bA = null;
        if (ow2.j() instanceof wn || ow2.c()) {
            this.e(ow2, f2);
        }
        return true;
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (ow2 instanceof ox && ((ox)ow2).w()) {
            this.e(ow2, f2);
        }
        return false;
    }

    protected boolean e(ow ow2, float f2) {
        return super.a(ow2, f2);
    }

    @Override
    public void G() {
        this.J();
    }

    @Override
    protected void aZ() {
        ++this.by;
        if (this.by >= 180 && this.by <= 200) {
            float f2 = (this.V.nextFloat() - 0.5f) * 8.0f;
            \u2603 = (this.V.nextFloat() - 0.5f) * 4.0f;
            \u2603 = (this.V.nextFloat() - 0.5f) * 8.0f;
            this.o.a(cy.c, this.s + (double)f2, this.t + 2.0 + (double)\u2603, this.u + (double)\u2603, 0.0, 0.0, 0.0, new int[0]);
        }
        boolean bl2 = this.o.Q().b("doMobLoot");
        if (!this.o.D) {
            if (this.by > 150 && this.by % 5 == 0 && bl2) {
                for (int i2 = 1000; i2 > 0; i2 -= \u2603) {
                    \u2603 = pp.a(i2);
                    this.o.d(new pp(this.o, this.s, this.t, this.u, \u2603));
                }
            }
            if (this.by == 1) {
                this.o.a(1018, new cj(this), 0);
            }
        }
        this.d(0.0, 0.1f, 0.0);
        this.aI = this.y += 20.0f;
        if (this.by == 200 && !this.o.D) {
            if (bl2) {
                for (int i3 = 2000; i3 > 0; i3 -= \u2603) {
                    \u2603 = pp.a(i3);
                    this.o.d(new pp(this.o, this.s, this.t, this.u, \u2603));
                }
            }
            this.a(new cj(this.s, 64.0, this.u));
            this.J();
        }
    }

    private void a(cj cj2) {
        int n2 = 4;
        double \u26032 = 12.25;
        double \u26033 = 6.25;
        for (\u2603 = -1; \u2603 <= 32; ++\u2603) {
            for (\u2603 = -4; \u2603 <= 4; ++\u2603) {
                for (\u2603 = -4; \u2603 <= 4; ++\u2603) {
                    double d2 = \u2603 * \u2603 + \u2603 * \u2603;
                    if (d2 > 12.25) continue;
                    cj \u26034 = cj2.a(\u2603, \u2603, \u2603);
                    if (\u2603 < 0) {
                        if (!(d2 <= 6.25)) continue;
                        this.o.a(\u26034, afi.h.Q());
                        continue;
                    }
                    if (\u2603 > 0) {
                        this.o.a(\u26034, afi.a.Q());
                        continue;
                    }
                    if (d2 > 6.25) {
                        this.o.a(\u26034, afi.h.Q());
                        continue;
                    }
                    this.o.a(\u26034, afi.bF.Q());
                }
            }
        }
        this.o.a(cj2, afi.h.Q());
        this.o.a(cj2.a(), afi.h.Q());
        cj cj3 = cj2.b(2);
        this.o.a(cj3, afi.h.Q());
        this.o.a(cj3.e(), afi.aa.Q().a(akf.a, cq.f));
        this.o.a(cj3.f(), afi.aa.Q().a(akf.a, cq.e));
        this.o.a(cj3.c(), afi.aa.Q().a(akf.a, cq.d));
        this.o.a(cj3.d(), afi.aa.Q().a(akf.a, cq.c));
        this.o.a(cj2.b(3), afi.h.Q());
        this.o.a(cj2.b(4), afi.bI.Q());
    }

    @Override
    protected void D() {
    }

    @Override
    public pk[] aB() {
        return this.bm;
    }

    @Override
    public boolean ad() {
        return false;
    }

    @Override
    public adm a() {
        return this.o;
    }

    @Override
    protected String z() {
        return "mob.enderdragon.growl";
    }

    @Override
    protected String bo() {
        return "mob.enderdragon.hit";
    }

    @Override
    protected float bB() {
        return 5.0f;
    }
}

