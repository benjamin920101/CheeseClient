/*
 * Decompiled with CFR 0.152.
 */
public class abc
extends zw {
    public abc() {
        this.c(1);
    }

    @Override
    public zx a(zx zx2, adm adm2, wn wn2) {
        wn2.a(zx2);
        wn2.b(na.ad[zw.b(this)]);
        return zx2;
    }

    public static boolean b(dn dn2) {
        if (dn2 == null) {
            return false;
        }
        if (!dn2.b("pages", 9)) {
            return false;
        }
        du du2 = dn2.c("pages", 8);
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            String string = du2.f(i2);
            if (string == null) {
                return false;
            }
            if (string.length() <= Short.MAX_VALUE) continue;
            return false;
        }
        return true;
    }
}

