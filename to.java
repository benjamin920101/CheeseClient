/*
 * Decompiled with CFR 0.152.
 */
public class to
extends tm {
    public to(adm adm2) {
        super(adm2);
        this.a(0.9f, 1.3f);
        ((sv)this.s()).a(true);
        this.i.a(0, new ra(this));
        this.i.a(1, new rv(this, 2.0));
        this.i.a(2, new qv(this, 1.0));
        this.i.a(3, new sh(this, 1.25, zy.O, false));
        this.i.a(4, new rc(this, 1.25));
        this.i.a(5, new rz(this, 1.0));
        this.i.a(6, new ri(this, wn.class, 6.0f));
        this.i.a(7, new ry(this));
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(10.0);
        this.a(vy.d).a(0.2f);
    }

    @Override
    protected String z() {
        return "mob.cow.say";
    }

    @Override
    protected String bo() {
        return "mob.cow.hurt";
    }

    @Override
    protected String bp() {
        return "mob.cow.hurt";
    }

    @Override
    protected void a(cj cj2, afh afh2) {
        this.a("mob.cow.step", 0.15f, 1.0f);
    }

    @Override
    protected float bB() {
        return 0.4f;
    }

    @Override
    protected zw A() {
        return zy.aF;
    }

    @Override
    protected void b(boolean bl2, int n2) {
        \u2603 = this.V.nextInt(3) + this.V.nextInt(1 + n2);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            this.a(zy.aF, 1);
        }
        \u2603 = this.V.nextInt(3) + 1 + this.V.nextInt(1 + n2);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            if (this.at()) {
                this.a(zy.bj, 1);
                continue;
            }
            this.a(zy.bi, 1);
        }
    }

    @Override
    public boolean a(wn wn2) {
        zx zx2 = wn2.bi.h();
        if (zx2 != null && zx2.b() == zy.aw && !wn2.bA.d && !this.j_()) {
            if (zx2.b-- == 1) {
                wn2.bi.a(wn2.bi.c, new zx(zy.aG));
            } else if (!wn2.bi.a(new zx(zy.aG))) {
                wn2.a(new zx(zy.aG, 1, 0), false);
            }
            return true;
        }
        return super.a(wn2);
    }

    public to b(ph ph2) {
        return new to(this.o);
    }

    @Override
    public float aS() {
        return this.K;
    }

    @Override
    public /* synthetic */ ph a(ph ph2) {
        return this.b(ph2);
    }
}

