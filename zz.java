/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class zz
extends zw {
    public zz() {
        this.a(yz.i);
    }

    @Override
    public boolean a(zx zx2, wn wn2, adm adm2, cj cj2, cq cq2, float f2, float f3, float f4) {
        afh afh2 = adm2.p(cj2).c();
        if (afh2 instanceof agt) {
            if (adm2.D) {
                return true;
            }
            zz.a(wn2, adm2, cj2);
            return true;
        }
        return false;
    }

    public static boolean a(wn wn2, adm adm2, cj cj2) {
        up up2 = up.b(adm2, cj2);
        boolean \u26032 = false;
        double \u26033 = 7.0;
        int \u26034 = cj2.n();
        int \u26035 = cj2.o();
        int \u26036 = cj2.p();
        List<ps> \u26037 = adm2.a(ps.class, new aug((double)\u26034 - \u26033, (double)\u26035 - \u26033, (double)\u26036 - \u26033, (double)\u26034 + \u26033, (double)\u26035 + \u26033, (double)\u26036 + \u26033));
        for (ps ps22 : \u26037) {
            ps ps22;
            if (!ps22.cc() || ps22.cd() != wn2) continue;
            if (up2 == null) {
                up2 = up.a(adm2, cj2);
            }
            ps22.a((pk)up2, true);
            \u26032 = true;
        }
        return \u26032;
    }
}

