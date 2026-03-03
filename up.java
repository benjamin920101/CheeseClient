/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class up
extends un {
    public up(adm adm2) {
        super(adm2);
    }

    public up(adm adm2, cj cj2) {
        super(adm2, cj2);
        this.b((double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5);
        float f2 = 0.125f;
        \u2603 = 0.1875f;
        \u2603 = 0.25f;
        this.a(new aug(this.s - 0.1875, this.t - 0.25 + 0.125, this.u - 0.1875, this.s + 0.1875, this.t + 0.25 + 0.125, this.u + 0.1875));
    }

    @Override
    protected void h() {
        super.h();
    }

    @Override
    public void a(cq cq2) {
    }

    @Override
    public int l() {
        return 9;
    }

    @Override
    public int m() {
        return 9;
    }

    @Override
    public float aS() {
        return -0.0625f;
    }

    @Override
    public boolean a(double d2) {
        return d2 < 1024.0;
    }

    @Override
    public void b(pk pk2) {
    }

    @Override
    public boolean d(dn dn2) {
        return false;
    }

    @Override
    public void b(dn dn2) {
    }

    @Override
    public void a(dn dn2) {
    }

    @Override
    public boolean e(wn wn22) {
        List<ps> \u26033;
        double d2;
        zx zx2 = wn22.bA();
        boolean \u26032 = false;
        if (zx2 != null && zx2.b() == zy.cn && !this.o.D) {
            d2 = 7.0;
            \u26033 = this.o.a(ps.class, new aug(this.s - d2, this.t - d2, this.u - d2, this.s + d2, this.t + d2, this.u + d2));
            for (ps ps2 : \u26033) {
                if (!ps2.cc() || ps2.cd() != wn22) continue;
                ps2.a((pk)this, true);
                \u26032 = true;
            }
        }
        if (!this.o.D && !\u26032) {
            wn wn22;
            this.J();
            if (wn22.bA.d) {
                d2 = 7.0;
                \u26033 = this.o.a(ps.class, new aug(this.s - d2, this.t - d2, this.u - d2, this.s + d2, this.t + d2, this.u + d2));
                for (ps ps2 : \u26033) {
                    if (!ps2.cc() || ps2.cd() != this) continue;
                    ps2.a(true, false);
                }
            }
        }
        return true;
    }

    @Override
    public boolean j() {
        return this.o.p(this.a).c() instanceof agt;
    }

    public static up a(adm adm2, cj cj2) {
        up up2 = new up(adm2, cj2);
        up2.n = true;
        adm2.d(up2);
        return up2;
    }

    public static up b(adm adm2, cj cj2) {
        int n2 = cj2.n();
        \u2603 = cj2.o();
        \u2603 = cj2.p();
        List<up> \u26032 = adm2.a(up.class, new aug((double)n2 - 1.0, (double)\u2603 - 1.0, (double)\u2603 - 1.0, (double)n2 + 1.0, (double)\u2603 + 1.0, (double)\u2603 + 1.0));
        for (up up2 : \u26032) {
            if (!up2.n().equals(cj2)) continue;
            return up2;
        }
        return null;
    }
}

