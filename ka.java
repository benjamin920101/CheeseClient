/*
 * Decompiled with CFR 0.152.
 */
public abstract class ka
extends cn {
    @Override
    public zx b(ck ck2, zx zx2) {
        adm adm2 = ck2.i();
        cz \u26032 = agg.a(ck2);
        cq \u26033 = agg.b(ck2.f());
        wv \u26034 = this.a(adm2, \u26032);
        \u26034.c(\u26033.g(), (float)\u26033.h() + 0.1f, \u26033.i(), this.b(), this.a());
        adm2.d((pk)((Object)\u26034));
        zx2.a(1);
        return zx2;
    }

    @Override
    protected void a(ck ck2) {
        ck2.i().b(1002, ck2.d(), 0);
    }

    protected abstract wv a(adm var1, cz var2);

    protected float a() {
        return 6.0f;
    }

    protected float b() {
        return 1.1f;
    }
}

