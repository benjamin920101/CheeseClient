/*
 * Decompiled with CFR 0.152.
 */
public class sd
extends rd {
    private tp a;
    private double b;
    private double c;
    private double d;
    private double e;

    public sd(tp tp2, double d2) {
        this.a = tp2;
        this.b = d2;
        this.a(1);
    }

    @Override
    public boolean a() {
        if (this.a.co() || this.a.l == null) {
            return false;
        }
        aui aui2 = tc.a(this.a, 5, 4);
        if (aui2 == null) {
            return false;
        }
        this.c = aui2.a;
        this.d = aui2.b;
        this.e = aui2.c;
        return true;
    }

    @Override
    public void c() {
        this.a.s().a(this.c, this.d, this.e, this.b);
    }

    @Override
    public boolean b() {
        return !this.a.s().m() && this.a.l != null;
    }

    @Override
    public void e() {
        if (this.a.bc().nextInt(50) == 0) {
            if (this.a.l instanceof wn) {
                int n2 = this.a.cC();
                \u2603 = this.a.cI();
                if (\u2603 > 0 && this.a.bc().nextInt(\u2603) < n2) {
                    this.a.h((wn)this.a.l);
                    this.a.o.a((pk)this.a, (byte)7);
                    return;
                }
                this.a.u(5);
            }
            this.a.l.a((pk)null);
            this.a.l = null;
            this.a.cW();
            this.a.o.a((pk)this.a, (byte)6);
        }
    }
}

