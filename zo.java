/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public class zo
extends zw {
    @Override
    public boolean a(zx zx2, wn wn2, adm adm2, cj cj2, cq cq2, float f2, float f3, float f4) {
        if (!adm2.D) {
            wt wt2 = new wt(adm2, (float)cj2.n() + f2, (float)cj2.o() + f3, (float)cj2.p() + f4, zx2);
            adm2.d(wt2);
            if (!wn2.bA.d) {
                --zx2.b;
            }
            return true;
        }
        return false;
    }

    @Override
    public void a(zx zx2, wn wn2, List<String> list2, boolean bl2) {
        if (!zx2.n()) {
            return;
        }
        dn dn2 = zx2.o().m("Fireworks");
        if (dn2 == null) {
            return;
        }
        if (dn2.b("Flight", 99)) {
            list2.add(di.a("item.fireworks.flight") + " " + dn2.d("Flight"));
        }
        if ((\u2603 = dn2.c("Explosions", 10)) != null && \u2603.c() > 0) {
            for (int i2 = 0; i2 < \u2603.c(); ++i2) {
                List<String> list2;
                dn dn3 = \u2603.b(i2);
                ArrayList<String> \u26032 = Lists.newArrayList();
                zn.a(dn3, \u26032);
                if (\u26032.size() <= 0) continue;
                for (int i3 = 1; i3 < \u26032.size(); ++i3) {
                    \u26032.set(i3, "  " + (String)\u26032.get(i3));
                }
                list2.addAll(\u26032);
            }
        }
    }
}

