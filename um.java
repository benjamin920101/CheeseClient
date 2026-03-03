/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class um
extends pr {
    private static final dc a = new dc(0.0f, 0.0f, 0.0f);
    private static final dc b = new dc(0.0f, 0.0f, 0.0f);
    private static final dc c = new dc(-10.0f, 0.0f, -10.0f);
    private static final dc d = new dc(-15.0f, 0.0f, 10.0f);
    private static final dc e = new dc(-1.0f, 0.0f, -1.0f);
    private static final dc f = new dc(1.0f, 0.0f, 1.0f);
    private final zx[] g = new zx[5];
    private boolean h;
    private long i;
    private int bi;
    private boolean bj;
    private dc bk = a;
    private dc bl = b;
    private dc bm = c;
    private dc bn = d;
    private dc bo = e;
    private dc bp = f;

    public um(adm adm2) {
        super(adm2);
        this.b(true);
        this.T = this.p();
        this.a(0.5f, 1.975f);
    }

    public um(adm adm2, double d2, double d3, double d4) {
        this(adm2);
        this.b(d2, d3, d4);
    }

    @Override
    public boolean bM() {
        return super.bM() && !this.p();
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(10, Byte.valueOf((byte)0));
        this.ac.a(11, a);
        this.ac.a(12, b);
        this.ac.a(13, c);
        this.ac.a(14, d);
        this.ac.a(15, e);
        this.ac.a(16, f);
    }

    @Override
    public zx bA() {
        return this.g[0];
    }

    @Override
    public zx p(int n2) {
        return this.g[n2];
    }

    @Override
    public zx q(int n2) {
        return this.g[n2 + 1];
    }

    @Override
    public void c(int n2, zx zx2) {
        this.g[n2] = zx2;
    }

    @Override
    public zx[] as() {
        return this.g;
    }

    @Override
    public boolean d(int n22, zx zx22) {
        zx zx22;
        if (n22 == 99) {
            int n3 = 0;
        } else {
            int n22;
            n3 = n22 - 100 + 1;
            if (n3 < 0 || n3 >= this.g.length) {
                return false;
            }
        }
        if (zx22 == null || ps.c(zx22) == n3 || n3 == 4 && zx22.b() instanceof yo) {
            this.c(n3, zx22);
            return true;
        }
        return false;
    }

    @Override
    public void b(dn dn22) {
        dn dn22;
        super.b(dn22);
        du du2 = new du();
        for (int i2 = 0; i2 < this.g.length; ++i2) {
            dn dn3 = new dn();
            if (this.g[i2] != null) {
                this.g[i2].b(dn3);
            }
            du2.a(dn3);
        }
        dn22.a("Equipment", du2);
        if (this.aN() && (this.aM() == null || this.aM().length() == 0)) {
            dn22.a("CustomNameVisible", this.aN());
        }
        dn22.a("Invisible", this.ax());
        dn22.a("Small", this.n());
        dn22.a("ShowArms", this.q());
        dn22.a("DisabledSlots", this.bi);
        dn22.a("NoGravity", this.p());
        dn22.a("NoBasePlate", this.r());
        if (this.s()) {
            dn22.a("Marker", this.s());
        }
        dn22.a("Pose", this.z());
    }

    @Override
    public void a(dn dn22) {
        dn dn22;
        eb \u26032;
        super.a(dn22);
        if (dn22.b("Equipment", 9)) {
            \u26032 = dn22.c("Equipment", 10);
            for (int i2 = 0; i2 < this.g.length; ++i2) {
                this.g[i2] = zx.a(\u26032.b(i2));
            }
        }
        this.e(dn22.n("Invisible"));
        this.j(dn22.n("Small"));
        this.l(dn22.n("ShowArms"));
        this.bi = dn22.f("DisabledSlots");
        this.k(dn22.n("NoGravity"));
        this.m(dn22.n("NoBasePlate"));
        this.n(dn22.n("Marker"));
        this.bj = !this.s();
        this.T = this.p();
        \u26032 = dn22.m("Pose");
        this.h((dn)\u26032);
    }

    private void h(dn dn2) {
        du du2 = dn2.c("Head", 5);
        if (du2.c() > 0) {
            this.a(new dc(du2));
        } else {
            this.a(a);
        }
        \u2603 = dn2.c("Body", 5);
        if (\u2603.c() > 0) {
            this.b(new dc(\u2603));
        } else {
            this.b(b);
        }
        \u2603 = dn2.c("LeftArm", 5);
        if (\u2603.c() > 0) {
            this.c(new dc(\u2603));
        } else {
            this.c(c);
        }
        \u2603 = dn2.c("RightArm", 5);
        if (\u2603.c() > 0) {
            this.d(new dc(\u2603));
        } else {
            this.d(d);
        }
        \u2603 = dn2.c("LeftLeg", 5);
        if (\u2603.c() > 0) {
            this.e(new dc(\u2603));
        } else {
            this.e(e);
        }
        \u2603 = dn2.c("RightLeg", 5);
        if (\u2603.c() > 0) {
            this.f(new dc(\u2603));
        } else {
            this.f(f);
        }
    }

    private dn z() {
        dn dn2 = new dn();
        if (!a.equals(this.bk)) {
            dn2.a("Head", this.bk.a());
        }
        if (!b.equals(this.bl)) {
            dn2.a("Body", this.bl.a());
        }
        if (!c.equals(this.bm)) {
            dn2.a("LeftArm", this.bm.a());
        }
        if (!d.equals(this.bn)) {
            dn2.a("RightArm", this.bn.a());
        }
        if (!e.equals(this.bo)) {
            dn2.a("LeftLeg", this.bo.a());
        }
        if (!f.equals(this.bp)) {
            dn2.a("RightLeg", this.bp.a());
        }
        return dn2;
    }

    @Override
    public boolean ae() {
        return false;
    }

    @Override
    protected void s(pk pk2) {
    }

    @Override
    protected void bL() {
        List<pk> list = this.o.b(this, this.aR());
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); ++i2) {
                pk pk2 = list.get(i2);
                if (!(pk2 instanceof va) || ((va)pk2).s() != va.a.a || !(this.h(pk2) <= 0.2)) continue;
                pk2.i(this);
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public boolean a(wn \u2603, aui \u2603) {
        block21: {
            block22: {
                block20: {
                    if (this.s()) {
                        return false;
                    }
                    if (this.o.D || \u2603.v()) {
                        return true;
                    }
                    \u2603 = 0;
                    \u2603 = \u2603.bZ();
                    v0 = \u2603 = \u2603 != null;
                    if (\u2603 && \u2603.b() instanceof yj) {
                        \u2603 = (yj)\u2603.b();
                        if (\u2603.b == 3) {
                            \u2603 = 1;
                        } else if (\u2603.b == 2) {
                            \u2603 = 2;
                        } else if (\u2603.b == 1) {
                            \u2603 = 3;
                        } else if (\u2603.b == 0) {
                            \u2603 = 4;
                        }
                    }
                    if (\u2603 && (\u2603.b() == zy.bX || \u2603.b() == zw.a(afi.aU))) {
                        \u2603 = 4;
                    }
                    \u2603 = 0.1;
                    \u2603 = 0.9;
                    \u2603 = 0.4;
                    \u2603 = 1.6;
                    \u2603 = 0;
                    \u2603 = this.n();
                    v1 = \u2603 = \u2603 != false ? \u2603.b * 2.0 : \u2603.b;
                    if (!(\u2603 >= 0.1)) break block20;
                    v2 = \u2603 != false ? 0.8 : 0.45;
                    if (!(\u2603 < 0.1 + v2) || this.g[1] == null) break block20;
                    \u2603 = 1;
                    break block21;
                }
                v3 = \u2603 != false ? 0.3 : 0.0;
                if (!(\u2603 >= 0.9 + v3)) break block22;
                v4 = \u2603 != false ? 1.0 : 0.7;
                if (!(\u2603 < 0.9 + v4) || this.g[3] == null) break block22;
                \u2603 = 3;
                break block21;
            }
            if (!(\u2603 >= 0.4)) ** GOTO lbl-1000
            v5 = \u2603 != false ? 1.0 : 0.8;
            if (\u2603 < 0.4 + v5 && this.g[2] != null) {
                \u2603 = 2;
            } else if (\u2603 >= 1.6 && this.g[4] != null) {
                \u2603 = 4;
            }
        }
        v6 = \u2603 = this.g[\u2603] != null;
        if (((this.bi & 1 << \u2603) != 0 || (this.bi & 1 << \u2603) != 0) && (this.bi & 1 << (\u2603 = \u2603)) != 0) {
            if ((this.bi & 1) != 0) {
                return true;
            }
            \u2603 = 0;
        }
        if (\u2603 && \u2603 == 0 && !this.q()) {
            return true;
        }
        if (\u2603) {
            this.a(\u2603, \u2603);
        } else if (\u2603) {
            this.a(\u2603, \u2603);
        }
        return true;
    }

    private void a(wn wn2, int n2) {
        zx zx2 = this.g[n2];
        if (zx2 != null && (this.bi & 1 << n2 + 8) != 0) {
            return;
        }
        if (zx2 == null && (this.bi & 1 << n2 + 16) != 0) {
            return;
        }
        int \u26032 = wn2.bi.c;
        \u2603 = wn2.bi.a(\u26032);
        if (wn2.bA.d && (zx2 == null || zx2.b() == zw.a(afi.a)) && \u2603 != null) {
            \u2603 = \u2603.k();
            \u2603.b = 1;
            this.c(n2, \u2603);
            return;
        }
        if (\u2603 != null && \u2603.b > 1) {
            if (zx2 != null) {
                return;
            }
            \u2603 = \u2603.k();
            \u2603.b = 1;
            this.c(n2, \u2603);
            --\u2603.b;
            return;
        }
        this.c(n2, \u2603);
        wn2.bi.a(\u26032, zx2);
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.o.D) {
            return false;
        }
        if (ow.j.equals(ow2)) {
            this.J();
            return false;
        }
        if (this.b(ow2) || this.h || this.s()) {
            return false;
        }
        if (ow2.c()) {
            this.D();
            this.J();
            return false;
        }
        if (ow.a.equals(ow2)) {
            if (!this.at()) {
                this.e(5);
            } else {
                this.a(0.15f);
            }
            return false;
        }
        if (ow.c.equals(ow2) && this.bn() > 0.5f) {
            this.a(4.0f);
            return false;
        }
        boolean bl2 = "arrow".equals(ow2.p());
        \u2603 = "player".equals(ow2.p());
        if (!\u2603 && !bl2) {
            return false;
        }
        if (ow2.i() instanceof wq) {
            ow2.i().J();
        }
        if (ow2.j() instanceof wn && !((wn)ow2.j()).bA.e) {
            return false;
        }
        if (ow2.u()) {
            this.A();
            this.J();
            return false;
        }
        long \u26032 = this.o.K();
        if (\u26032 - this.i <= 5L || bl2) {
            this.C();
            this.A();
            this.J();
        } else {
            this.i = \u26032;
        }
        return false;
    }

    @Override
    public boolean a(double d2) {
        \u2603 = this.aR().a() * 4.0;
        if (Double.isNaN(\u2603) || \u2603 == 0.0) {
            \u2603 = 4.0;
        }
        return d2 < (\u2603 *= 64.0) * \u2603;
    }

    private void A() {
        if (this.o instanceof le) {
            ((le)this.o).a(cy.M, this.s, this.t + (double)this.K / 1.5, this.u, 10, (double)(this.J / 4.0f), (double)(this.K / 4.0f), (double)(this.J / 4.0f), 0.05, afh.f(afi.f.Q()));
        }
    }

    private void a(float f2) {
        \u2603 = this.bn();
        if ((\u2603 -= f2) <= 0.5f) {
            this.D();
            this.J();
        } else {
            this.i(\u2603);
        }
    }

    private void C() {
        afh.a(this.o, new cj(this), new zx(zy.cj));
        this.D();
    }

    private void D() {
        for (int i2 = 0; i2 < this.g.length; ++i2) {
            if (this.g[i2] == null || this.g[i2].b <= 0) continue;
            if (this.g[i2] != null) {
                afh.a(this.o, new cj(this).a(), this.g[i2]);
            }
            this.g[i2] = null;
        }
    }

    @Override
    protected float h(float f2, float f3) {
        this.aJ = this.A;
        this.aI = this.y;
        return 0.0f;
    }

    @Override
    public float aS() {
        return this.j_() ? this.K * 0.5f : this.K * 0.9f;
    }

    @Override
    public void g(float f2, float f3) {
        if (this.p()) {
            return;
        }
        super.g(f2, f3);
    }

    @Override
    public void t_() {
        super.t_();
        dc dc2 = this.ac.h(11);
        if (!this.bk.equals(dc2)) {
            this.a(dc2);
        }
        if (!this.bl.equals(\u2603 = this.ac.h(12))) {
            this.b(\u2603);
        }
        if (!this.bm.equals(\u2603 = this.ac.h(13))) {
            this.c(\u2603);
        }
        if (!this.bn.equals(\u2603 = this.ac.h(14))) {
            this.d(\u2603);
        }
        if (!this.bo.equals(\u2603 = this.ac.h(15))) {
            this.e(\u2603);
        }
        if (!this.bp.equals(\u2603 = this.ac.h(16))) {
            this.f(\u2603);
        }
        boolean \u26032 = this.s();
        if (!this.bj && \u26032) {
            this.a(false);
        } else if (this.bj && !\u26032) {
            this.a(true);
        } else {
            return;
        }
        this.bj = \u26032;
    }

    private void a(boolean bl2) {
        double d2 = this.s;
        \u2603 = this.t;
        \u2603 = this.u;
        if (bl2) {
            this.a(0.5f, 1.975f);
        } else {
            this.a(0.0f, 0.0f);
        }
        this.b(d2, \u2603, \u2603);
    }

    @Override
    protected void B() {
        this.e(this.h);
    }

    @Override
    public void e(boolean bl2) {
        this.h = bl2;
        super.e(bl2);
    }

    @Override
    public boolean j_() {
        return this.n();
    }

    @Override
    public void G() {
        this.J();
    }

    @Override
    public boolean aW() {
        return this.ax();
    }

    private void j(boolean bl2) {
        byte by = this.ac.a(10);
        by = bl2 ? (byte)(by | 1) : (byte)(by & 0xFFFFFFFE);
        this.ac.b(10, by);
    }

    public boolean n() {
        return (this.ac.a(10) & 1) != 0;
    }

    private void k(boolean bl2) {
        byte by = this.ac.a(10);
        by = bl2 ? (byte)(by | 2) : (byte)(by & 0xFFFFFFFD);
        this.ac.b(10, by);
    }

    public boolean p() {
        return (this.ac.a(10) & 2) != 0;
    }

    private void l(boolean bl2) {
        byte by = this.ac.a(10);
        by = bl2 ? (byte)(by | 4) : (byte)(by & 0xFFFFFFFB);
        this.ac.b(10, by);
    }

    public boolean q() {
        return (this.ac.a(10) & 4) != 0;
    }

    private void m(boolean bl2) {
        byte by = this.ac.a(10);
        by = bl2 ? (byte)(by | 8) : (byte)(by & 0xFFFFFFF7);
        this.ac.b(10, by);
    }

    public boolean r() {
        return (this.ac.a(10) & 8) != 0;
    }

    private void n(boolean bl2) {
        byte by = this.ac.a(10);
        by = bl2 ? (byte)(by | 0x10) : (byte)(by & 0xFFFFFFEF);
        this.ac.b(10, by);
    }

    public boolean s() {
        return (this.ac.a(10) & 0x10) != 0;
    }

    public void a(dc dc2) {
        this.bk = dc2;
        this.ac.b(11, dc2);
    }

    public void b(dc dc2) {
        this.bl = dc2;
        this.ac.b(12, dc2);
    }

    public void c(dc dc2) {
        this.bm = dc2;
        this.ac.b(13, dc2);
    }

    public void d(dc dc2) {
        this.bn = dc2;
        this.ac.b(14, dc2);
    }

    public void e(dc dc2) {
        this.bo = dc2;
        this.ac.b(15, dc2);
    }

    public void f(dc dc2) {
        this.bp = dc2;
        this.ac.b(16, dc2);
    }

    public dc t() {
        return this.bk;
    }

    public dc u() {
        return this.bl;
    }

    public dc v() {
        return this.bm;
    }

    public dc w() {
        return this.bn;
    }

    public dc x() {
        return this.bo;
    }

    public dc y() {
        return this.bp;
    }

    @Override
    public boolean ad() {
        return super.ad() && !this.s();
    }
}

