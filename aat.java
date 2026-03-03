/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;
import java.util.List;

public class aat
extends zw {
    private static final String[] a = new String[]{"skeleton", "wither", "zombie", "char", "creeper"};

    public aat() {
        this.a(yz.c);
        this.d(0);
        this.a(true);
    }

    @Override
    public boolean a(zx zx22, wn wn22, adm adm2, cj cj22, cq cq2, float f2, float f3, float f4) {
        wn wn22;
        if (cq2 == cq.a) {
            return false;
        }
        alz alz2 = adm2.p(cj22);
        afh \u26032 = alz2.c();
        boolean \u26033 = \u26032.a(adm2, cj22);
        if (!\u26033) {
            if (!adm2.p(cj22).c().t().a()) {
                return false;
            }
            cj cj22 = cj22.a(cq2);
        }
        if (!wn22.a(cj22, cq2, zx22)) {
            return false;
        }
        if (!afi.ce.d(adm2, cj22)) {
            return false;
        }
        if (!adm2.D) {
            zx zx22;
            adm2.a(cj22, afi.ce.Q().a(ajm.a, cq2), 3);
            int n2 = 0;
            if (cq2 == cq.b) {
                n2 = ns.c((double)(wn22.y * 16.0f / 360.0f) + 0.5) & 0xF;
            }
            if ((\u2603 = adm2.s(cj22)) instanceof alo) {
                alo alo2 = (alo)\u2603;
                if (zx22.i() == 3) {
                    GameProfile gameProfile = null;
                    if (zx22.n()) {
                        dn dn2 = zx22.o();
                        if (dn2.b("SkullOwner", 10)) {
                            gameProfile = dy.a(dn2.m("SkullOwner"));
                        } else if (dn2.b("SkullOwner", 8) && dn2.j("SkullOwner").length() > 0) {
                            gameProfile = new GameProfile(null, dn2.j("SkullOwner"));
                        }
                    }
                    alo2.a(gameProfile);
                } else {
                    alo2.a(zx22.i());
                }
                alo2.b(n2);
                afi.ce.a(adm2, cj22, alo2);
            }
            --zx22.b;
        }
        return true;
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (int i2 = 0; i2 < a.length; ++i2) {
            list.add(new zx(zw2, 1, i2));
        }
    }

    @Override
    public int a(int n2) {
        return n2;
    }

    @Override
    public String e_(zx zx2) {
        int n2 = zx2.i();
        if (n2 < 0 || n2 >= a.length) {
            n2 = 0;
        }
        return super.a() + "." + a[n2];
    }

    @Override
    public String a(zx zx2) {
        if (zx2.i() == 3 && zx2.n()) {
            if (zx2.o().b("SkullOwner", 8)) {
                return di.a("item.skull.player.name", zx2.o().j("SkullOwner"));
            }
            if (zx2.o().b("SkullOwner", 10) && (\u2603 = zx2.o().m("SkullOwner")).b("Name", 8)) {
                return di.a("item.skull.player.name", \u2603.j("Name"));
            }
        }
        return super.a(zx2);
    }

    @Override
    public boolean a(dn dn2) {
        super.a(dn2);
        if (dn2.b("SkullOwner", 8) && dn2.j("SkullOwner").length() > 0) {
            GameProfile gameProfile = new GameProfile(null, dn2.j("SkullOwner"));
            gameProfile = alo.b(gameProfile);
            dn2.a("SkullOwner", dy.a(new dn(), gameProfile));
            return true;
        }
        return false;
    }
}

