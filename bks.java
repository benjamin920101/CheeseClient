/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;

public class bks
implements blb<pr> {
    private final bct a;

    public bks(bct bct2) {
        this.a = bct2;
    }

    @Override
    public void a(pr pr2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f9;
        zx zx2 = pr2.q(3);
        if (zx2 == null || zx2.b() == null) {
            return;
        }
        zw \u26032 = zx2.b();
        ave \u26033 = ave.A();
        bfl.E();
        if (pr2.av()) {
            bfl.b(0.0f, 0.2f, 0.0f);
        }
        boolean bl2 = \u2603 = pr2 instanceof wi || pr2 instanceof we && ((we)pr2).co();
        if (!\u2603 && pr2.j_()) {
            f9 = 2.0f;
            \u2603 = 1.4f;
            bfl.a(\u2603 / f9, \u2603 / f9, \u2603 / f9);
            bfl.b(0.0f, 16.0f * f8, 0.0f);
        }
        this.a.c(0.0625f);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        if (\u26032 instanceof yo) {
            f9 = 0.625f;
            bfl.b(0.0f, -0.25f, 0.0f);
            bfl.b(180.0f, 0.0f, 1.0f, 0.0f);
            bfl.a(f9, -f9, -f9);
            if (\u2603) {
                bfl.b(0.0f, 0.1875f, 0.0f);
            }
            \u26033.ah().a(pr2, zx2, bgr.b.d);
        } else if (\u26032 == zy.bX) {
            f9 = 1.1875f;
            bfl.a(f9, -f9, -f9);
            if (\u2603) {
                bfl.b(0.0f, 0.0625f, 0.0f);
            }
            GameProfile \u26034 = null;
            if (zx2.n()) {
                dn dn2 = zx2.o();
                if (dn2.b("SkullOwner", 10)) {
                    \u26034 = dy.a(dn2.m("SkullOwner"));
                } else if (dn2.b("SkullOwner", 8) && !nx.b(\u2603 = dn2.j("SkullOwner"))) {
                    \u26034 = alo.b(new GameProfile(null, \u2603));
                    dn2.a("SkullOwner", dy.a(new dn(), \u26034));
                }
            }
            bhk.c.a(-0.5f, 0.0f, -0.5f, cq.b, 180.0f, zx2.i(), \u26034, -1);
        }
        bfl.F();
    }

    @Override
    public boolean b() {
        return true;
    }
}

