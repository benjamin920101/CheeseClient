/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class agb
extends afm {
    protected agb() {
        super(arm.l);
        float f2 = 0.4f;
        this.a(0.5f - f2, 0.0f, 0.5f - f2, 0.5f + f2, 0.8f, 0.5f + f2);
    }

    @Override
    public arn g(alz alz2) {
        return arn.o;
    }

    @Override
    protected boolean c(afh afh2) {
        return afh2 == afi.m || afh2 == afi.cz || afh2 == afi.cu || afh2 == afi.d;
    }

    @Override
    public boolean a(adm adm2, cj cj2) {
        return true;
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return null;
    }

    @Override
    public void a(adm adm2, wn wn2, cj cj2, alz alz2, akw akw2) {
        if (adm2.D || wn2.bZ() == null || wn2.bZ().b() != zy.be) {
            super.a(adm2, wn2, cj2, alz2, akw2);
        } else {
            wn2.b(na.ab[afh.a(this)]);
            agb.a(adm2, cj2, new zx(afi.I, 1, 0));
        }
    }
}

