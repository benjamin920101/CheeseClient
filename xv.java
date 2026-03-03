/*
 * Decompiled with CFR 0.152.
 */
public class xv
extends yg {
    private wn a;
    private int b;

    public xv(wn wn2, og og2, int n2, int n3, int n4) {
        super(og2, n2, n3, n4);
        this.a = wn2;
    }

    @Override
    public boolean a(zx zx2) {
        return false;
    }

    @Override
    public zx a(int n2) {
        if (this.e()) {
            this.b += Math.min(n2, this.d().b);
        }
        return super.a(n2);
    }

    @Override
    public void a(wn wn2, zx zx2) {
        this.c(zx2);
        super.a(wn2, zx2);
    }

    @Override
    protected void a(zx zx2, int n2) {
        this.b += n2;
        this.c(zx2);
    }

    @Override
    protected void c(zx zx2) {
        zx2.a(this.a.o, this.a, this.b);
        if (!this.a.o.D) {
            int n2 = this.b;
            float \u26032 = abo.a().b(zx2);
            if (\u26032 == 0.0f) {
                n2 = 0;
            } else if (\u26032 < 1.0f) {
                \u2603 = ns.d((float)n2 * \u26032);
                if (\u2603 < ns.f((float)n2 * \u26032) && Math.random() < (double)((float)n2 * \u26032 - (float)\u2603)) {
                    ++\u2603;
                }
                n2 = \u2603;
            }
            while (n2 > 0) {
                \u2603 = pp.a(n2);
                n2 -= \u2603;
                this.a.o.d(new pp(this.a.o, this.a.s, this.a.t + 0.5, this.a.u + 0.5, \u2603));
            }
        }
        this.b = 0;
        if (zx2.b() == zy.j) {
            this.a.b(mr.k);
        }
        if (zx2.b() == zy.aV) {
            this.a.b(mr.p);
        }
    }
}

