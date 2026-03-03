/*
 * Decompiled with CFR 0.152.
 */
public class uf
extends pk {
    public int a;
    public int b;

    public uf(adm adm2) {
        super(adm2);
        this.k = true;
        this.a(2.0f, 2.0f);
        this.b = 5;
        this.a = this.V.nextInt(100000);
    }

    public uf(adm adm2, double d2, double d3, double d4) {
        this(adm2);
        this.b(d2, d3, d4);
    }

    @Override
    protected boolean s_() {
        return false;
    }

    @Override
    protected void h() {
        this.ac.a(8, Integer.valueOf(this.b));
    }

    @Override
    public void t_() {
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        ++this.a;
        this.ac.b(8, this.b);
        int n2 = ns.c(this.s);
        \u2603 = ns.c(this.t);
        \u2603 = ns.c(this.u);
        if (this.o.t instanceof anp && this.o.p(new cj(n2, \u2603, \u2603)).c() != afi.ab) {
            this.o.a(new cj(n2, \u2603, \u2603), afi.ab.Q());
        }
    }

    @Override
    protected void b(dn dn2) {
    }

    @Override
    protected void a(dn dn2) {
    }

    @Override
    public boolean ad() {
        return true;
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        if (!this.I && !this.o.D) {
            this.b = 0;
            if (this.b <= 0) {
                this.J();
                if (!this.o.D) {
                    this.o.a(null, this.s, this.t, this.u, 6.0f, true);
                }
            }
        }
        return true;
    }
}

