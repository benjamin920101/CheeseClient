/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class aii
extends afc {
    private static final List<String> a = Lists.newArrayList("harp", "bd", "snare", "hat", "bassattack");

    public aii() {
        super(arm.d);
        this.a(yz.d);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        boolean bl2 = adm2.z(cj2);
        akw \u26032 = adm2.s(cj2);
        if (\u26032 instanceof alm) {
            alm alm2 = (alm)\u26032;
            if (alm2.f != bl2) {
                if (bl2) {
                    alm2.a(adm2, cj2);
                }
                alm2.f = bl2;
            }
        }
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (adm2.D) {
            return true;
        }
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof alm) {
            alm alm2 = (alm)akw2;
            alm2.b();
            alm2.a(adm2, cj2);
            wn2.b(na.S);
        }
        return true;
    }

    @Override
    public void a(adm adm2, cj cj2, wn wn2) {
        if (adm2.D) {
            return;
        }
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof alm) {
            ((alm)akw2).a(adm2, cj2);
            wn2.b(na.R);
        }
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new alm();
    }

    private String b(int n2) {
        if (n2 < 0 || n2 >= a.size()) {
            n2 = 0;
        }
        return a.get(n2);
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, int n2, int n3) {
        float f2 = (float)Math.pow(2.0, (double)(n3 - 12) / 12.0);
        adm2.a((double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, "note." + this.b(n2), 3.0f, f2);
        adm2.a(cy.x, (double)cj2.n() + 0.5, (double)cj2.o() + 1.2, (double)cj2.p() + 0.5, (double)n3 / 24.0, 0.0, 0.0, new int[0]);
        return true;
    }

    @Override
    public int b() {
        return 3;
    }
}

