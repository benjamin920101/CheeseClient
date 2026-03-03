/*
 * Decompiled with CFR 0.152.
 */
public abstract class vv
extends py
implements vq {
    public vv(adm adm2) {
        super(adm2);
        this.b_ = 5;
    }

    @Override
    public void m() {
        this.bx();
        float f2 = this.c(1.0f);
        if (f2 > 0.5f) {
            this.aQ += 2;
        }
        super.m();
    }

    @Override
    public void t_() {
        super.t_();
        if (!this.o.D && this.o.aa() == oj.a) {
            this.J();
        }
    }

    @Override
    protected String P() {
        return "game.hostile.swim";
    }

    @Override
    protected String aa() {
        return "game.hostile.swim.splash";
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        if (super.a(ow2, f2)) {
            pk pk2 = ow2.j();
            if (this.l == pk2 || this.m == pk2) {
                return true;
            }
            return true;
        }
        return false;
    }

    @Override
    protected String bo() {
        return "game.hostile.hurt";
    }

    @Override
    protected String bp() {
        return "game.hostile.die";
    }

    @Override
    protected String n(int n2) {
        if (n2 > 4) {
            return "game.hostile.hurt.fall.big";
        }
        return "game.hostile.hurt.fall.small";
    }

    @Override
    public boolean r(pk pk2) {
        float f2 = (float)this.a(vy.e).e();
        int \u26032 = 0;
        if (pk2 instanceof pr) {
            f2 += ack.a(this.bA(), ((pr)pk2).bz());
            \u26032 += ack.a(this);
        }
        if (\u2603 = pk2.a(ow.a(this), f2)) {
            if (\u26032 > 0) {
                pk2.g(-ns.a(this.y * (float)Math.PI / 180.0f) * (float)\u26032 * 0.5f, 0.1, ns.b(this.y * (float)Math.PI / 180.0f) * (float)\u26032 * 0.5f);
                this.v *= 0.6;
                this.x *= 0.6;
            }
            if ((\u2603 = ack.b(this)) > 0) {
                pk2.e(\u2603 * 4);
            }
            this.a(this, pk2);
        }
        return \u2603;
    }

    @Override
    public float a(cj cj2) {
        return 0.5f - this.o.o(cj2);
    }

    protected boolean n_() {
        cj cj2 = new cj(this.s, this.aR().b, this.u);
        if (this.o.b(ads.a, cj2) > this.V.nextInt(32)) {
            return false;
        }
        int \u26032 = this.o.l(cj2);
        if (this.o.R()) {
            int n2 = this.o.ab();
            this.o.c(10);
            \u26032 = this.o.l(cj2);
            this.o.c(n2);
        }
        return \u26032 <= this.V.nextInt(8);
    }

    @Override
    public boolean bR() {
        return this.o.aa() != oj.a && this.n_() && super.bR();
    }

    @Override
    protected void aX() {
        super.aX();
        this.by().b(vy.e);
    }

    @Override
    protected boolean ba() {
        return true;
    }
}

