/*
 * Decompiled with CFR 0.152.
 */
import java.util.UUID;

public abstract class py
extends ps {
    public static final UUID bk = UUID.fromString("E199AD21-BA8A-4C53-8D13-6182D5C69D3A");
    public static final qd bl = new qd(bk, "Fleeing speed bonus", 2.0, 2).a(false);
    private cj a = cj.a;
    private float b = -1.0f;
    private rd c = new rp(this, 1.0);
    private boolean bm;

    public py(adm adm2) {
        super(adm2);
    }

    public float a(cj cj2) {
        return 0.0f;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean bR() {
        if (!super.bR()) return false;
        cj cj2 = new cj(this.s, this.aR().b, this.u);
        if (!(this.a(cj2) >= 0.0f)) return false;
        return true;
    }

    public boolean cf() {
        return !this.h.m();
    }

    public boolean cg() {
        return this.e(new cj(this));
    }

    public boolean e(cj cj2) {
        if (this.b == -1.0f) {
            return true;
        }
        return this.a.i(cj2) < (double)(this.b * this.b);
    }

    public void a(cj cj2, int n2) {
        this.a = cj2;
        this.b = n2;
    }

    public cj ch() {
        return this.a;
    }

    public float ci() {
        return this.b;
    }

    public void cj() {
        this.b = -1.0f;
    }

    public boolean ck() {
        return this.b != -1.0f;
    }

    @Override
    protected void ca() {
        super.ca();
        if (this.cc() && this.cd() != null && this.cd().o == this.o) {
            pk pk2 = this.cd();
            this.a(new cj((int)pk2.s, (int)pk2.t, (int)pk2.u), 5);
            float \u26032 = this.g(pk2);
            if (this instanceof qa && ((qa)this).cn()) {
                if (\u26032 > 10.0f) {
                    this.a(true, true);
                }
                return;
            }
            if (!this.bm) {
                this.i.a(2, this.c);
                if (this.s() instanceof sv) {
                    ((sv)this.s()).a(false);
                }
                this.bm = true;
            }
            this.o(\u26032);
            if (\u26032 > 4.0f) {
                this.s().a(pk2, 1.0);
            }
            if (\u26032 > 6.0f) {
                double d2 = (pk2.s - this.s) / (double)\u26032;
                \u2603 = (pk2.t - this.t) / (double)\u26032;
                \u2603 = (pk2.u - this.u) / (double)\u26032;
                this.v += d2 * Math.abs(d2) * 0.4;
                this.w += \u2603 * Math.abs(\u2603) * 0.4;
                this.x += \u2603 * Math.abs(\u2603) * 0.4;
            }
            if (\u26032 > 10.0f) {
                this.a(true, true);
            }
        } else if (!this.cc() && this.bm) {
            this.bm = false;
            this.i.a(this.c);
            if (this.s() instanceof sv) {
                ((sv)this.s()).a(true);
            }
            this.cj();
        }
    }

    protected void o(float f2) {
    }
}

