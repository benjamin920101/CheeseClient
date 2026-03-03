/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;

public class bex
extends bet {
    private boolean a;
    private int b;
    private double c;
    private double d;
    private double e;
    private double f;
    private double g;

    public bex(adm adm2, GameProfile gameProfile) {
        super(adm2, gameProfile);
        this.S = 0.0f;
        this.T = true;
        this.bZ = 0.25f;
        this.j = 10.0;
    }

    @Override
    public boolean a(ow ow2, float f2) {
        return true;
    }

    @Override
    public void a(double d2, double d3, double d4, float f2, float f3, int n2, boolean bl2) {
        this.c = d2;
        this.d = d3;
        this.e = d4;
        this.f = f2;
        this.g = f3;
        this.b = n2;
    }

    @Override
    public void t_() {
        this.bZ = 0.0f;
        super.t_();
        this.aA = this.aB;
        double d2 = this.s - this.p;
        \u2603 = this.u - this.r;
        float \u26032 = ns.a(d2 * d2 + \u2603 * \u2603) * 4.0f;
        if (\u26032 > 1.0f) {
            \u26032 = 1.0f;
        }
        this.aB += (\u26032 - this.aB) * 0.4f;
        this.aC += this.aB;
        if (!this.a && this.ay() && this.bi.a[this.bi.c] != null) {
            zx zx2 = this.bi.a[this.bi.c];
            this.a(this.bi.a[this.bi.c], zx2.b().d(zx2));
            this.a = true;
        } else if (this.a && !this.ay()) {
            this.bV();
            this.a = false;
        }
    }

    @Override
    public void m() {
        if (this.b > 0) {
            double d2 = this.s + (this.c - this.s) / (double)this.b;
            \u2603 = this.t + (this.d - this.t) / (double)this.b;
            \u2603 = this.u + (this.e - this.u) / (double)this.b;
            for (\u2603 = this.f - (double)this.y; \u2603 < -180.0; \u2603 += 360.0) {
            }
            while (\u2603 >= 180.0) {
                \u2603 -= 360.0;
            }
            this.y = (float)((double)this.y + \u2603 / (double)this.b);
            this.z = (float)((double)this.z + (this.g - (double)this.z) / (double)this.b);
            --this.b;
            this.b(d2, \u2603, \u2603);
            this.b(this.y, this.z);
        }
        this.bn = this.bo;
        this.bx();
        float f2 = ns.a(this.v * this.v + this.x * this.x);
        \u2603 = (float)Math.atan(-this.w * (double)0.2f) * 15.0f;
        if (f2 > 0.1f) {
            f2 = 0.1f;
        }
        if (!this.C || this.bn() <= 0.0f) {
            f2 = 0.0f;
        }
        if (this.C || this.bn() <= 0.0f) {
            \u2603 = 0.0f;
        }
        this.bo += (f2 - this.bo) * 0.4f;
        this.aF += (\u2603 - this.aF) * 0.8f;
    }

    @Override
    public void c(int n2, zx zx2) {
        if (n2 == 0) {
            this.bi.a[this.bi.c] = zx2;
        } else {
            this.bi.b[n2 - 1] = zx2;
        }
    }

    @Override
    public void a(eu eu2) {
        ave.A().q.d().a(eu2);
    }

    @Override
    public boolean a(int n2, String string) {
        return false;
    }

    @Override
    public cj c() {
        return new cj(this.s + 0.5, this.t + 0.5, this.u + 0.5);
    }
}

