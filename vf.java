/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;

public class vf
extends vd
implements ali {
    private boolean a = true;
    private int b = -1;
    private cj c = cj.a;

    public vf(adm adm2) {
        super(adm2);
    }

    public vf(adm adm2, double d2, double d3, double d4) {
        super(adm2, d2, d3, d4);
    }

    @Override
    public va.a s() {
        return va.a.f;
    }

    @Override
    public alz u() {
        return afi.cp.Q();
    }

    @Override
    public int w() {
        return 1;
    }

    @Override
    public int o_() {
        return 5;
    }

    @Override
    public boolean e(wn wn2) {
        if (!this.o.D) {
            wn2.a(this);
        }
        return true;
    }

    @Override
    public void a(int n2, int n3, int n4, boolean bl2) {
        boolean bl3 = \u2603 = !bl2;
        if (\u2603 != this.y()) {
            this.i(\u2603);
        }
    }

    public boolean y() {
        return this.a;
    }

    public void i(boolean bl2) {
        this.a = bl2;
    }

    @Override
    public adm z() {
        return this.o;
    }

    @Override
    public double A() {
        return this.s;
    }

    @Override
    public double B() {
        return this.t + 0.5;
    }

    @Override
    public double C() {
        return this.u;
    }

    @Override
    public void t_() {
        super.t_();
        if (!this.o.D && this.ai() && this.y()) {
            cj cj2 = new cj(this);
            if (cj2.equals(this.c)) {
                --this.b;
            } else {
                this.m(0);
            }
            if (!this.E()) {
                this.m(0);
                if (this.D()) {
                    this.m(4);
                    this.p_();
                }
            }
        }
    }

    public boolean D() {
        if (alj.a(this)) {
            return true;
        }
        Predicate<pk> predicate = this.o.a(uz.class, this.aR().b(0.25, 0.0, 0.25), po.a);
        if (predicate.size() > 0) {
            alj.a(this, (uz)predicate.get(0));
        }
        return false;
    }

    @Override
    public void a(ow ow2) {
        super.a(ow2);
        if (this.o.Q().b("doEntityDrops")) {
            this.a(zw.a(afi.cp), 1, 0.0f);
        }
    }

    @Override
    protected void b(dn dn2) {
        super.b(dn2);
        dn2.a("TransferCooldown", this.b);
    }

    @Override
    protected void a(dn dn2) {
        super.a(dn2);
        this.b = dn2.f("TransferCooldown");
    }

    public void m(int n2) {
        this.b = n2;
    }

    public boolean E() {
        return this.b > 0;
    }

    @Override
    public String k() {
        return "minecraft:hopper";
    }

    @Override
    public xi a(wm wm2, wn wn2) {
        return new xw(wm2, this, wn2);
    }
}

