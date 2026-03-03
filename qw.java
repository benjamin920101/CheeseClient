/*
 * Decompiled with CFR 0.152.
 */
public class qw
extends rd {
    private final ps a;
    private final float b;
    private float c;
    private boolean d;
    private int e;
    private int f;

    public qw(ps ps2, float f2) {
        this.a = ps2;
        this.b = f2;
        this.a(7);
    }

    @Override
    public void c() {
        this.c = 0.0f;
    }

    @Override
    public void d() {
        this.d = false;
        this.c = 0.0f;
    }

    @Override
    public boolean a() {
        return this.a.ai() && this.a.l != null && this.a.l instanceof wn && (this.d || this.a.bW());
    }

    @Override
    public void e() {
        wn wn2 = (wn)this.a.l;
        py \u26032 = (py)this.a;
        float \u26033 = ns.g(wn2.y - this.a.y) * 0.5f;
        if (\u26033 > 5.0f) {
            \u26033 = 5.0f;
        }
        if (\u26033 < -5.0f) {
            \u26033 = -5.0f;
        }
        this.a.y = ns.g(this.a.y + \u26033);
        if (this.c < this.b) {
            this.c += (this.b - this.c) * 0.01f;
        }
        if (this.c > this.b) {
            this.c = this.b;
        }
        int \u26034 = ns.c(this.a.s);
        int \u26035 = ns.c(this.a.t);
        int \u26036 = ns.c(this.a.u);
        float \u26037 = this.c;
        if (this.d) {
            if (this.e++ > this.f) {
                this.d = false;
            }
            \u26037 += \u26037 * 1.15f * ns.a((float)this.e / (float)this.f * (float)Math.PI);
        }
        float \u26038 = 0.91f;
        if (this.a.C) {
            \u26038 = this.a.o.p((cj)new cj((int)ns.d((float)((float)\u26034)), (int)(ns.d((float)((float)\u26035)) - 1), (int)ns.d((float)((float)\u26036)))).c().L * 0.91f;
        }
        float \u26039 = 0.16277136f / (\u26038 * \u26038 * \u26038);
        float \u260310 = ns.a(\u26032.y * (float)Math.PI / 180.0f);
        float \u260311 = ns.b(\u26032.y * (float)Math.PI / 180.0f);
        float \u260312 = \u26032.bI() * \u26039;
        float \u260313 = Math.max(\u26037, 1.0f);
        \u260313 = \u260312 / \u260313;
        float \u260314 = \u26037 * \u260313;
        float \u260315 = -(\u260314 * \u260310);
        float \u260316 = \u260314 * \u260311;
        if (ns.e(\u260315) > ns.e(\u260316)) {
            if (\u260315 < 0.0f) {
                \u260315 -= this.a.J / 2.0f;
            }
            if (\u260315 > 0.0f) {
                \u260315 += this.a.J / 2.0f;
            }
            \u260316 = 0.0f;
        } else {
            \u260315 = 0.0f;
            if (\u260316 < 0.0f) {
                \u260316 -= this.a.J / 2.0f;
            }
            if (\u260316 > 0.0f) {
                \u260316 += this.a.J / 2.0f;
            }
        }
        int \u260317 = ns.c(this.a.s + (double)\u260315);
        int \u260318 = ns.c(this.a.u + (double)\u260316);
        int \u260319 = ns.d(this.a.J + 1.0f);
        int \u260320 = ns.d(this.a.K + wn2.K + 1.0f);
        int \u260321 = ns.d(this.a.J + 1.0f);
        if (\u26034 != \u260317 || \u26036 != \u260318) {
            Object object = this.a.o.p(new cj(\u26034, \u26035, \u26036)).c();
            boolean bl2 = \u2603 = !this.a((afh)object) && (((afh)object).t() != arm.a || !this.a(this.a.o.p(new cj(\u26034, \u26035 - 1, \u26036)).c()));
            if (\u2603 && 0 == ata.a(this.a.o, this.a, \u260317, \u26035, \u260318, \u260319, \u260320, \u260321, false, false, true) && 1 == ata.a(this.a.o, this.a, \u26034, \u26035 + 1, \u26036, \u260319, \u260320, \u260321, false, false, true) && 1 == ata.a(this.a.o, this.a, \u260317, \u26035 + 1, \u260318, \u260319, \u260320, \u260321, false, false, true)) {
                \u26032.r().a();
            }
        }
        if (!wn2.bA.d && this.c >= this.b * 0.5f && this.a.bc().nextFloat() < 0.006f && !this.d && (object = wn2.bA()) != null && ((zx)object).b() == zy.bY) {
            ((zx)object).a(1, (pr)wn2);
            if (((zx)object).b == 0) {
                zx zx2 = new zx(zy.aR);
                zx2.d(((zx)object).o());
                wn2.bi.a[wn2.bi.c] = zx2;
            }
        }
        this.a.g(0.0f, \u26037);
    }

    private boolean a(afh afh2) {
        return afh2 instanceof aju || afh2 instanceof ahh;
    }

    public boolean f() {
        return this.d;
    }

    public void g() {
        this.d = true;
        this.e = 0;
        this.f = this.a.bc().nextInt(841) + 140;
    }

    public boolean h() {
        return !this.f() && this.c > this.b * 0.3f;
    }
}

