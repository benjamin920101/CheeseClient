/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class ym
extends yo {
    public ym() {
        super(afi.cK);
        this.h = 16;
        this.a(yz.c);
        this.a(true);
        this.d(0);
    }

    @Override
    public boolean a(zx zx22, wn wn2, adm adm22, cj cj2, cq cq2, float f2, float f3, float f4) {
        zx zx22;
        if (cq2 == cq.a) {
            return false;
        }
        if (!adm22.p(cj2).c().t().a()) {
            return false;
        }
        if (!wn2.a(cj2 = cj2.a(cq2), cq2, zx22)) {
            return false;
        }
        if (!afi.cK.d(adm22, cj2)) {
            return false;
        }
        if (adm22.D) {
            return true;
        }
        if (cq2 == cq.b) {
            int n2 = ns.c((double)((wn2.y + 180.0f) * 16.0f / 360.0f) + 0.5) & 0xF;
            adm22.a(cj2, afi.cK.Q().a(ajv.a, n2), 3);
        } else {
            adm adm22;
            adm22.a(cj2, afi.cL.Q().a(akm.a, cq2), 3);
        }
        --zx22.b;
        akw \u26032 = adm22.s(cj2);
        if (\u26032 instanceof aku) {
            ((aku)\u26032).a(zx22);
        }
        return true;
    }

    @Override
    public String a(zx zx2) {
        String string = "item.banner.";
        zd \u26032 = this.h(zx2);
        string = string + \u26032.d() + ".name";
        return di.a(string);
    }

    @Override
    public void a(zx zx2, wn wn2, List<String> list, boolean bl2) {
        dn dn2 = zx2.a("BlockEntityTag", false);
        if (dn2 == null || !dn2.c("Patterns")) {
            return;
        }
        du \u26032 = dn2.c("Patterns", 10);
        for (int i2 = 0; i2 < \u26032.c() && i2 < 6; ++i2) {
            dn dn3 = \u26032.b(i2);
            zd \u26033 = zd.a(dn3.f("Color"));
            aku.a \u26034 = aku.a.a(dn3.j("Pattern"));
            if (\u26034 == null) continue;
            list.add(di.a("item.banner." + \u26034.a() + "." + \u26033.d()));
        }
    }

    @Override
    public int a(zx zx2, int n2) {
        if (n2 == 0) {
            return 0xFFFFFF;
        }
        zd zd2 = this.h(zx2);
        return zd2.e().L;
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (zd zd2 : zd.values()) {
            dn dn2 = new dn();
            aku.a(dn2, zd2.b(), null);
            \u2603 = new dn();
            \u2603.a("BlockEntityTag", dn2);
            zx \u26032 = new zx(zw2, 1, zd2.b());
            \u26032.d(\u2603);
            list.add(\u26032);
        }
    }

    @Override
    public yz c() {
        return yz.c;
    }

    private zd h(zx zx2) {
        dn dn2 = zx2.a("BlockEntityTag", false);
        zd \u26032 = null;
        \u26032 = dn2 != null && dn2.c("Base") ? zd.a(dn2.f("Base")) : zd.a(zx2.i());
        return \u26032;
    }
}

