/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;

public class bfi {
    public static bfi a = new bfi();
    private aky b = new aky(0);
    private aky c = new aky(1);
    private alf d = new alf();
    private aku e = new aku();
    private alo f = new alo();

    public void a(zx zx22) {
        zx zx22;
        if (zx22.b() == zy.cE) {
            this.e.a(zx22);
            bhc.a.a(this.e, 0.0, 0.0, 0.0, 0.0f);
        } else if (zx22.b() == zy.bX) {
            GameProfile gameProfile = null;
            if (zx22.n()) {
                dn dn2 = zx22.o();
                if (dn2.b("SkullOwner", 10)) {
                    gameProfile = dy.a(dn2.m("SkullOwner"));
                } else if (dn2.b("SkullOwner", 8) && dn2.j("SkullOwner").length() > 0) {
                    gameProfile = new GameProfile(null, dn2.j("SkullOwner"));
                    gameProfile = alo.b(gameProfile);
                    dn2.o("SkullOwner");
                    dn2.a("SkullOwner", dy.a(new dn(), gameProfile));
                }
            }
            if (bhk.c != null) {
                bfl.E();
                bfl.b(-0.5f, 0.0f, -0.5f);
                bfl.a(2.0f, 2.0f, 2.0f);
                bfl.p();
                bhk.c.a(0.0f, 0.0f, 0.0f, cq.b, 0.0f, zx22.i(), gameProfile, -1);
                bfl.o();
                bfl.F();
            }
        } else {
            afh \u26032 = afh.a(zx22.b());
            if (\u26032 == afi.bQ) {
                bhc.a.a(this.d, 0.0, 0.0, 0.0, 0.0f);
            } else if (\u26032 == afi.cg) {
                bhc.a.a(this.c, 0.0, 0.0, 0.0, 0.0f);
            } else {
                bhc.a.a(this.b, 0.0, 0.0, 0.0, 0.0f);
            }
        }
    }
}

