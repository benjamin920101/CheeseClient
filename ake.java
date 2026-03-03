/*
 * Decompiled with CFR 0.152.
 */
public class ake
extends afh {
    public static final amk a = amk.a("explode");

    public ake() {
        super(arm.u);
        this.j(this.M.b().a(a, false));
        this.a(yz.d);
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        super.c(adm2, cj2, alz2);
        if (adm2.z(cj2)) {
            this.d(adm2, cj2, alz2.a(a, true));
            adm2.g(cj2);
        }
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (adm2.z(cj2)) {
            this.d(adm2, cj2, alz2.a(a, true));
            adm2.g(cj2);
        }
    }

    @Override
    public void a(adm adm2, cj cj2, adi adi2) {
        if (adm2.D) {
            return;
        }
        vj vj2 = new vj(adm2, (float)cj2.n() + 0.5f, cj2.o(), (float)cj2.p() + 0.5f, adi2.c());
        vj2.a = adm2.s.nextInt(vj2.a / 4) + vj2.a / 8;
        adm2.d(vj2);
    }

    @Override
    public void d(adm adm2, cj cj2, alz alz2) {
        this.a(adm2, cj2, alz2, (pr)null);
    }

    public void a(adm adm2, cj cj2, alz alz2, pr pr2) {
        if (adm2.D) {
            return;
        }
        if (alz2.b(a).booleanValue()) {
            vj vj2 = new vj(adm2, (float)cj2.n() + 0.5f, cj2.o(), (float)cj2.p() + 0.5f, pr2);
            adm2.d(vj2);
            adm2.a(vj2, "game.tnt.primed", 1.0f, 1.0f);
        }
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (wn2.bZ() != null && ((\u2603 = wn2.bZ().b()) == zy.d || \u2603 == zy.bL)) {
            this.a(adm2, cj2, alz2.a(a, true), (pr)wn2);
            adm2.g(cj2);
            if (\u2603 == zy.d) {
                wn2.bZ().a(1, (pr)wn2);
            } else if (!wn2.bA.d) {
                --wn2.bZ().b;
            }
            return true;
        }
        return super.a(adm2, cj2, alz2, wn2, cq2, f2, f3, f4);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pk pk2) {
        if (!adm2.D && pk2 instanceof wq && (\u2603 = (wq)pk2).at()) {
            this.a(adm2, cj2, adm2.p(cj2).a(a, true), \u2603.c instanceof pr ? (pr)\u2603.c : null);
            adm2.g(cj2);
        }
    }

    @Override
    public boolean a(adi adi2) {
        return false;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, (n2 & 1) > 0);
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a) != false ? 1 : 0;
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }
}

