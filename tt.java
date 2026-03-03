/*
 * Decompiled with CFR 0.152.
 */
public class tt
extends tm {
    private final qw bm;

    public tt(adm adm2) {
        super(adm2);
        this.a(0.9f, 0.9f);
        ((sv)this.s()).a(true);
        this.i.a(0, new ra(this));
        this.i.a(1, new rv(this, 1.25));
        this.bm = new qw(this, 0.3f);
        this.i.a(2, this.bm);
        this.i.a(3, new qv(this, 1.0));
        this.i.a(4, new sh(this, 1.2, zy.bY, false));
        this.i.a(4, new sh(this, 1.2, zy.bR, false));
        this.i.a(5, new rc(this, 1.1));
        this.i.a(6, new rz(this, 1.0));
        this.i.a(7, new ri(this, wn.class, 6.0f));
        this.i.a(8, new ry(this));
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(10.0);
        this.a(vy.d).a(0.25);
    }

    @Override
    public boolean bW() {
        zx zx2 = ((wn)this.l).bA();
        return zx2 != null && zx2.b() == zy.bY;
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, Byte.valueOf((byte)0));
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("Saddle", this.cl());
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.l(dn2.n("Saddle"));
    }

    @Override
    protected String z() {
        return "mob.pig.say";
    }

    @Override
    protected String bo() {
        return "mob.pig.say";
    }

    @Override
    protected String bp() {
        return "mob.pig.death";
    }

    @Override
    protected void a(cj cj2, afh afh2) {
        this.a("mob.pig.step", 0.15f, 1.0f);
    }

    @Override
    public boolean a(wn wn2) {
        if (!super.a(wn2)) {
            if (this.cl() && !this.o.D && (this.l == null || this.l == wn2)) {
                wn2.a((pk)this);
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    protected zw A() {
        if (this.at()) {
            return zy.am;
        }
        return zy.al;
    }

    @Override
    protected void b(boolean bl2, int n2) {
        \u2603 = this.V.nextInt(3) + 1 + this.V.nextInt(1 + n2);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            if (this.at()) {
                this.a(zy.am, 1);
                continue;
            }
            this.a(zy.al, 1);
        }
        if (this.cl()) {
            this.a(zy.aA, 1);
        }
    }

    public boolean cl() {
        return (this.ac.a(16) & 1) != 0;
    }

    public void l(boolean bl2) {
        if (bl2) {
            this.ac.b(16, (byte)1);
        } else {
            this.ac.b(16, (byte)0);
        }
    }

    @Override
    public void a(uv uv2) {
        if (this.o.D || this.I) {
            return;
        }
        vw vw2 = new vw(this.o);
        vw2.c(0, new zx(zy.B));
        vw2.b(this.s, this.t, this.u, this.y, this.z);
        vw2.k(this.ce());
        if (this.l_()) {
            vw2.a(this.aM());
            vw2.g(this.aN());
        }
        this.o.d(vw2);
        this.J();
    }

    @Override
    public void e(float f2, float f3) {
        super.e(f2, f3);
        if (f2 > 5.0f && this.l instanceof wn) {
            ((wn)this.l).b(mr.u);
        }
    }

    public tt b(ph ph2) {
        return new tt(this.o);
    }

    @Override
    public boolean d(zx zx2) {
        return zx2 != null && zx2.b() == zy.bR;
    }

    public qw cm() {
        return this.bm;
    }

    @Override
    public /* synthetic */ ph a(ph ph2) {
        return this.b(ph2);
    }
}

