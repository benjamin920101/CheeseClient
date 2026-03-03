/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class afm
extends afh {
    protected afm() {
        this(arm.k);
    }

    protected afm(arm arm2) {
        this(arm2, arm2.r());
    }

    protected afm(arm arm2, arn arn2) {
        super(arm2, arn2);
        this.a(true);
        float f2 = 0.2f;
        this.a(0.5f - f2, 0.0f, 0.5f - f2, 0.5f + f2, f2 * 3.0f, 0.5f + f2);
        this.a(yz.c);
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        return super.d(adm2, cj2) && this.c(adm2.p(cj2.b()).c());
    }

    protected boolean c(afh afh2) {
        return afh2 == afi.c || afh2 == afi.d || afh2 == afi.ak;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        super.a(adm2, cj2, alz2, afh2);
        this.e(adm2, cj2, alz2);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        this.e(adm2, cj2, alz2);
    }

    protected void e(adm adm2, cj cj2, alz alz2) {
        if (!this.f(adm2, cj2, alz2)) {
            this.b(adm2, cj2, alz2, 0);
            adm2.a(cj2, afi.a.Q(), 3);
        }
    }

    public boolean f(adm adm2, cj cj2, alz alz2) {
        return this.c(adm2.p(cj2.b()).c());
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        return null;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public adf m() {
        return adf.c;
    }
}

