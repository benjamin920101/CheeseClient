/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class bha
extends bhd<aku> {
    private static final Map<String, a> c = Maps.newHashMap();
    private static final jy d = new jy("textures/entity/banner_base.png");
    private bau e = new bau();

    @Override
    public void a(aku aku2, double d2, double d3, double d4, float f2, int n2) {
        float \u26035;
        boolean bl2 = aku2.z() != null;
        \u2603 = !bl2 || aku2.w() == afi.cK;
        int \u26032 = bl2 ? aku2.u() : 0;
        long \u26033 = bl2 ? aku2.z().K() : 0L;
        bfl.E();
        float \u26034 = 0.6666667f;
        if (\u2603) {
            bfl.b((float)d2 + 0.5f, (float)d3 + 0.75f * \u26034, (float)d4 + 0.5f);
            float f3 = (float)(\u26032 * 360) / 16.0f;
            bfl.b(-f3, 0.0f, 1.0f, 0.0f);
            this.e.b.j = true;
        } else {
            int n3 = \u26032;
            \u26035 = 0.0f;
            if (n3 == 2) {
                \u26035 = 180.0f;
            }
            if (n3 == 4) {
                \u26035 = 90.0f;
            }
            if (n3 == 5) {
                \u26035 = -90.0f;
            }
            bfl.b((float)d2 + 0.5f, (float)d3 - 0.25f * \u26034, (float)d4 + 0.5f);
            bfl.b(-\u26035, 0.0f, 1.0f, 0.0f);
            bfl.b(0.0f, -0.3125f, -0.4375f);
            this.e.b.j = false;
        }
        cj cj2 = aku2.v();
        \u26035 = (float)(cj2.n() * 7 + cj2.o() * 9 + cj2.p() * 13) + (float)\u26033 + f2;
        this.e.a.f = (-0.0125f + 0.01f * ns.b(\u26035 * (float)Math.PI * 0.02f)) * (float)Math.PI;
        bfl.B();
        jy \u26036 = this.a(aku2);
        if (\u26036 != null) {
            this.a(\u26036);
            bfl.E();
            bfl.a(\u26034, -\u26034, -\u26034);
            this.e.a();
            bfl.F();
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.F();
    }

    private jy a(aku aku2) {
        String string = aku2.g();
        if (string.isEmpty()) {
            return null;
        }
        a \u26032 = c.get(string);
        if (\u26032 == null) {
            if (c.size() >= 256) {
                long l2 = System.currentTimeMillis();
                Iterator<String> \u26033 = c.keySet().iterator();
                while (\u26033.hasNext()) {
                    String string2 = \u26033.next();
                    a a2 = c.get(string2);
                    if (l2 - a2.a <= 60000L) continue;
                    ave.A().P().c(a2.b);
                    \u26033.remove();
                }
                if (c.size() >= 256) {
                    return null;
                }
            }
            List<aku.a> list = aku2.c();
            List<zd> \u26035 = aku2.e();
            ArrayList<String> arrayList = Lists.newArrayList();
            for (aku.a a3 : list) {
                arrayList.add("textures/entity/banner/" + a3.a() + ".png");
            }
            \u26032 = new a();
            \u26032.b = new jy(string);
            ave.A().P().a(\u26032.b, new bmc(d, arrayList, \u26035));
            c.put(string, \u26032);
        }
        \u26032.a = System.currentTimeMillis();
        return \u26032.b;
    }

    static class a {
        public long a;
        public jy b;

        private a() {
        }
    }
}

