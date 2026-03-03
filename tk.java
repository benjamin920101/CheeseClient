/*
 * Decompiled with CFR 0.152.
 */
import java.util.Calendar;

public class tk
extends tj {
    private cj a;

    public tk(adm adm2) {
        super(adm2);
        this.a(0.5f, 0.9f);
        this.a(true);
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, new Byte(0));
    }

    @Override
    protected float bB() {
        return 0.1f;
    }

    @Override
    protected float bC() {
        return super.bC() * 0.95f;
    }

    @Override
    protected String z() {
        if (this.n() && this.V.nextInt(4) != 0) {
            return null;
        }
        return "mob.bat.idle";
    }

    @Override
    protected String bo() {
        return "mob.bat.hurt";
    }

    @Override
    protected String bp() {
        return "mob.bat.death";
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
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(6.0);
    }

    public boolean n() {
        return (this.ac.a(16) & 1) != 0;
    }

    public void a(boolean bl2) {
        byte by = this.ac.a(16);
        if (bl2) {
            this.ac.b(16, (byte)(by | 1));
        } else {
            this.ac.b(16, (byte)(by & 0xFFFFFFFE));
        }
    }

    @Override
    public void t_() {
        super.t_();
        if (this.n()) {
            this.x = 0.0;
            this.w = 0.0;
            this.v = 0.0;
            this.t = (double)ns.c(this.t) + 1.0 - (double)this.K;
        } else {
            this.w *= (double)0.6f;
        }
    }

    @Override
    protected void E() {
        super.E();
        cj cj2 = new cj(this);
        \u2603 = cj2.a();
        if (this.n()) {
            if (!this.o.p(\u2603).c().v()) {
                this.a(false);
                this.o.a(null, 1015, cj2, 0);
            } else {
                if (this.V.nextInt(200) == 0) {
                    this.aK = this.V.nextInt(360);
                }
                if (this.o.a((pk)this, 4.0) != null) {
                    this.a(false);
                    this.o.a(null, 1015, cj2, 0);
                }
            }
        } else {
            if (!(this.a == null || this.o.d(this.a) && this.a.o() >= 1)) {
                this.a = null;
            }
            if (this.a == null || this.V.nextInt(30) == 0 || this.a.c((int)this.s, (int)this.t, (int)this.u) < 4.0) {
                this.a = new cj((int)this.s + this.V.nextInt(7) - this.V.nextInt(7), (int)this.t + this.V.nextInt(6) - 2, (int)this.u + this.V.nextInt(7) - this.V.nextInt(7));
            }
            double d2 = (double)this.a.n() + 0.5 - this.s;
            \u2603 = (double)this.a.o() + 0.1 - this.t;
            \u2603 = (double)this.a.p() + 0.5 - this.u;
            this.v += (Math.signum(d2) * 0.5 - this.v) * (double)0.1f;
            this.w += (Math.signum(\u2603) * (double)0.7f - this.w) * (double)0.1f;
            this.x += (Math.signum(\u2603) * 0.5 - this.x) * (double)0.1f;
            float \u26032 = (float)(ns.b(this.x, this.v) * 180.0 / 3.1415927410125732) - 90.0f;
            float \u26033 = ns.g(\u26032 - this.y);
            this.ba = 0.5f;
            this.y += \u26033;
            if (this.V.nextInt(100) == 0 && this.o.p(\u2603).c().v()) {
                this.a(true);
            }
        }
    }

    @Override
    protected boolean s_() {
        return false;
    }

    @Override
    public void e(float f2, float f3) {
    }

    @Override
    protected void a(double d2, boolean bl2, afh afh2, cj cj2) {
    }

    @Override
    public boolean aI() {
        return true;
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        if (!this.o.D && this.n()) {
            this.a(false);
        }
        return super.a(ow2, f2);
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.ac.b(16, dn2.d("BatFlags"));
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("BatFlags", this.ac.a(16));
    }

    @Override
    public boolean bR() {
        cj cj2 = new cj(this.s, this.aR().b, this.u);
        if (cj2.o() >= this.o.F()) {
            return false;
        }
        int \u26032 = this.o.l(cj2);
        int \u26033 = 4;
        if (this.a(this.o.Y())) {
            \u26033 = 7;
        } else if (this.V.nextBoolean()) {
            return false;
        }
        if (\u26032 > this.V.nextInt(\u26033)) {
            return false;
        }
        return super.bR();
    }

    private boolean a(Calendar calendar) {
        return calendar.get(2) + 1 == 10 && calendar.get(5) >= 20 || calendar.get(2) + 1 == 11 && calendar.get(5) <= 3;
    }

    @Override
    public float aS() {
        return this.K / 2.0f;
    }
}

