/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class afu
extends age
implements afj {
    public static final amn a = amn.a("age", 0, 2);

    public afu() {
        super(arm.k);
        this.j(this.M.b().a(O, cq.c).a(a, 0));
        this.a(true);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (!this.e(adm2, cj2, alz2)) {
            this.f(adm2, cj2, alz2);
        } else if (adm2.s.nextInt(5) == 0 && (\u2603 = alz2.b(a).intValue()) < 2) {
            adm2.a(cj2, alz2.a(a, \u2603 + 1), 2);
        }
    }

    public boolean e(adm adm2, cj cj2, alz alz2) {
        \u2603 = adm2.p(cj2 = cj2.a(alz2.b(O)));
        return \u2603.c() == afi.r && \u2603.b(aio.a) == aio.a.d;
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        this.a((adq)adm2, cj2);
        return super.a(adm2, cj2, alz2);
    }

    @Override
    public aug b(adm adm2, cj cj2) {
        this.a((adq)adm2, cj2);
        return super.b(adm2, cj2);
    }

    @Override
    public void a(adq adq2, cj cj2) {
        alz alz2 = adq2.p(cj2);
        cq \u26032 = alz2.b(O);
        int \u26033 = alz2.b(a);
        int \u26034 = 4 + \u26033 * 2;
        int \u26035 = 5 + \u26033 * 2;
        float \u26036 = (float)\u26034 / 2.0f;
        switch (\u26032) {
            case d: {
                this.a((8.0f - \u26036) / 16.0f, (12.0f - (float)\u26035) / 16.0f, (15.0f - (float)\u26034) / 16.0f, (8.0f + \u26036) / 16.0f, 0.75f, 0.9375f);
                break;
            }
            case c: {
                this.a((8.0f - \u26036) / 16.0f, (12.0f - (float)\u26035) / 16.0f, 0.0625f, (8.0f + \u26036) / 16.0f, 0.75f, (1.0f + (float)\u26034) / 16.0f);
                break;
            }
            case e: {
                this.a(0.0625f, (12.0f - (float)\u26035) / 16.0f, (8.0f - \u26036) / 16.0f, (1.0f + (float)\u26034) / 16.0f, 0.75f, (8.0f + \u26036) / 16.0f);
                break;
            }
            case f: {
                this.a((15.0f - (float)\u26034) / 16.0f, (12.0f - (float)\u26035) / 16.0f, (8.0f - \u26036) / 16.0f, 0.9375f, 0.75f, (8.0f + \u26036) / 16.0f);
            }
        }
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pr pr2, zx zx2) {
        cq cq2 = cq.a(pr2.y);
        adm2.a(cj2, alz2.a(O, cq2), 2);
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq22, float f2, float f3, float f4, int n2, pr pr2) {
        cq cq22;
        if (!cq22.k().c()) {
            cq22 = cq.c;
        }
        return this.Q().a(O, cq22.d()).a(a, 0);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (!this.e(adm2, cj2, alz2)) {
            this.f(adm2, cj2, alz2);
        }
    }

    private void f(adm adm2, cj cj2, alz alz2) {
        adm2.a(cj2, afi.a.Q(), 3);
        this.b(adm2, cj2, alz2, 0);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, float f2, int n2) {
        \u2603 = alz2.b(a);
        \u2603 = 1;
        if (\u2603 >= 2) {
            \u2603 = 3;
        }
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            afu.a(adm2, cj2, new zx(zy.aW, 1, zd.m.b()));
        }
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zy.aW;
    }

    @Override
    public int j(adm adm2, cj cj2) {
        return zd.m.b();
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, boolean bl2) {
        return alz2.b(a) < 2;
    }

    @Override
    public boolean a(adm adm2, Random random, cj cj2, alz alz2) {
        return true;
    }

    @Override
    public void b(adm adm2, Random random, cj cj2, alz alz2) {
        adm2.a(cj2, alz2.a(a, alz2.b(a) + 1), 2);
    }

    @Override
    public adf m() {
        return adf.c;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(O, cq.b(n2)).a(a, (n2 & 0xF) >> 2);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(O).b();
        return n2 |= alz2.b(a) << 2;
    }

    @Override
    protected ama e() {
        return new ama(this, O, a);
    }
}

