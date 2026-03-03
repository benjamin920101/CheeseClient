/*
 * Decompiled with CFR 0.152.
 */
public class sa
extends rd {
    private final ps a;
    private final vx b;
    private pr c;
    private int d = -1;
    private double e;
    private int f;
    private int g;
    private int h;
    private float i;
    private float j;

    public sa(vx vx2, double d2, int n2, float f2) {
        this(vx2, d2, n2, n2, f2);
    }

    public sa(vx vx2, double d2, int n2, int n3, float f2) {
        if (!(vx2 instanceof pr)) {
            throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
        }
        this.b = vx2;
        this.a = (ps)((Object)vx2);
        this.e = d2;
        this.g = n2;
        this.h = n3;
        this.i = f2;
        this.j = f2 * f2;
        this.a(3);
    }

    @Override
    public boolean a() {
        pr pr2 = this.a.u();
        if (pr2 == null) {
            return false;
        }
        this.c = pr2;
        return true;
    }

    @Override
    public boolean b() {
        return this.a() || !this.a.s().m();
    }

    @Override
    public void d() {
        this.c = null;
        this.f = 0;
        this.d = -1;
    }

    @Override
    public void e() {
        double d2 = this.a.e(this.c.s, this.c.aR().b, this.c.u);
        boolean \u26032 = this.a.t().a(this.c);
        this.f = \u26032 ? ++this.f : 0;
        if (d2 > (double)this.j || this.f < 20) {
            this.a.s().a(this.c, this.e);
        } else {
            this.a.s().n();
        }
        this.a.p().a(this.c, 30.0f, 30.0f);
        if (--this.d == 0) {
            if (d2 > (double)this.j || !\u26032) {
                return;
            }
            float f2 = \u2603 = ns.a(d2) / this.i;
            f2 = ns.a(f2, 0.1f, 1.0f);
            this.b.a(this.c, f2);
            this.d = ns.d(\u2603 * (float)(this.h - this.g) + (float)this.g);
        } else if (this.d < 0) {
            float \u26033 = ns.a(d2) / this.i;
            this.d = ns.d(\u26033 * (float)(this.h - this.g) + (float)this.g);
        }
    }
}

