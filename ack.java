/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ack {
    private static final Random a = new Random();
    private static final e b = new e();
    private static final d c = new d();
    private static final b d = new b();
    private static final a e = new a();

    public static int a(int n2, zx zx2) {
        if (zx2 == null) {
            return 0;
        }
        du du2 = zx2.p();
        if (du2 == null) {
            return 0;
        }
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            short s2 = du2.b(i2).e("id");
            \u2603 = du2.b(i2).e("lvl");
            if (s2 != n2) continue;
            return \u2603;
        }
        return 0;
    }

    public static Map<Integer, Integer> a(zx zx2) {
        LinkedHashMap<Integer, Integer> linkedHashMap = Maps.newLinkedHashMap();
        du du2 = \u2603 = zx2.b() == zy.cd ? zy.cd.h(zx2) : zx2.p();
        if (\u2603 != null) {
            for (int i2 = 0; i2 < \u2603.c(); ++i2) {
                short s2 = \u2603.b(i2).e("id");
                \u2603 = \u2603.b(i2).e("lvl");
                linkedHashMap.put(Integer.valueOf(s2), Integer.valueOf(\u2603));
            }
        }
        return linkedHashMap;
    }

    public static void a(Map<Integer, Integer> map, zx zx2) {
        du du2 = new du();
        for (int n2 : map.keySet()) {
            aci aci2 = aci.c(n2);
            if (aci2 == null) continue;
            dn \u26032 = new dn();
            \u26032.a("id", (short)n2);
            \u26032.a("lvl", (short)map.get(n2).intValue());
            du2.a(\u26032);
            if (zx2.b() != zy.cd) continue;
            zy.cd.a(zx2, new acl(aci2, map.get(n2)));
        }
        if (du2.c() > 0) {
            if (zx2.b() != zy.cd) {
                zx2.a("ench", du2);
            }
        } else if (zx2.n()) {
            zx2.o().o("ench");
        }
    }

    public static int a(int n2, zx[] zxArray) {
        if (zxArray == null) {
            return 0;
        }
        int n3 = 0;
        for (zx zx2 : zxArray) {
            int n4 = ack.a(n2, zx2);
            if (n4 <= n3) continue;
            n3 = n4;
        }
        return n3;
    }

    private static void a(c c2, zx zx2) {
        if (zx2 == null) {
            return;
        }
        du du2 = zx2.p();
        if (du2 == null) {
            return;
        }
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            short s2 = du2.b(i2).e("id");
            \u2603 = du2.b(i2).e("lvl");
            if (aci.c(s2) == null) continue;
            c2.a(aci.c(s2), \u2603);
        }
    }

    private static void a(c c2, zx[] zxArray) {
        for (zx zx2 : zxArray) {
            ack.a(c2, zx2);
        }
    }

    public static int a(zx[] zxArray, ow ow2) {
        ack.b.a = 0;
        ack.b.b = ow2;
        ack.a((c)b, zxArray);
        if (ack.b.a > 25) {
            ack.b.a = 25;
        } else if (ack.b.a < 0) {
            ack.b.a = 0;
        }
        return (ack.b.a + 1 >> 1) + a.nextInt((ack.b.a >> 1) + 1);
    }

    public static float a(zx zx2, pw pw2) {
        ack.c.a = 0.0f;
        ack.c.b = pw2;
        ack.a((c)c, zx2);
        return ack.c.a;
    }

    public static void a(pr pr2, pk pk2) {
        ack.d.b = pk2;
        ack.d.a = pr2;
        if (pr2 != null) {
            ack.a((c)d, pr2.as());
        }
        if (pk2 instanceof wn) {
            ack.a((c)d, pr2.bA());
        }
    }

    public static void b(pr pr2, pk pk2) {
        ack.e.a = pr2;
        ack.e.b = pk2;
        if (pr2 != null) {
            ack.a((c)e, pr2.as());
        }
        if (pr2 instanceof wn) {
            ack.a((c)e, pr2.bA());
        }
    }

    public static int a(pr pr2) {
        return ack.a(aci.o.B, pr2.bA());
    }

    public static int b(pr pr2) {
        return ack.a(aci.p.B, pr2.bA());
    }

    public static int a(pk pk2) {
        return ack.a(aci.h.B, pk2.as());
    }

    public static int b(pk pk2) {
        return ack.a(aci.k.B, pk2.as());
    }

    public static int c(pr pr2) {
        return ack.a(aci.r.B, pr2.bA());
    }

    public static boolean e(pr pr2) {
        return ack.a(aci.s.B, pr2.bA()) > 0;
    }

    public static int f(pr pr2) {
        return ack.a(aci.u.B, pr2.bA());
    }

    public static int g(pr pr2) {
        return ack.a(aci.z.B, pr2.bA());
    }

    public static int h(pr pr2) {
        return ack.a(aci.A.B, pr2.bA());
    }

    public static int i(pr pr2) {
        return ack.a(aci.q.B, pr2.bA());
    }

    public static boolean j(pr pr2) {
        return ack.a(aci.i.B, pr2.as()) > 0;
    }

    public static zx a(aci aci2, pr pr2) {
        for (zx zx2 : pr2.as()) {
            if (zx2 == null || ack.a(aci2.B, zx2) <= 0) continue;
            return zx2;
        }
        return null;
    }

    public static int a(Random random2, int n2, int n32, zx zx2) {
        Random random2;
        zw zw2 = zx2.b();
        int \u26032 = zw2.b();
        if (\u26032 <= 0) {
            return 0;
        }
        if (n32 > 15) {
            int n32 = 15;
        }
        int \u26033 = random2.nextInt(8) + 1 + (n32 >> 1) + random2.nextInt(n32 + 1);
        if (n2 == 0) {
            return Math.max(\u26033 / 3, 1);
        }
        if (n2 == 1) {
            return \u26033 * 2 / 3 + 1;
        }
        return Math.max(\u26033, n32 * 2);
    }

    public static zx a(Random random, zx zx22, int n2) {
        zx zx22;
        List<acl> list = ack.b(random, zx22, n2);
        boolean bl2 = \u2603 = zx22.b() == zy.aL;
        if (\u2603) {
            zx22.a(zy.cd);
        }
        if (list != null) {
            for (acl acl2 : list) {
                if (\u2603) {
                    zy.cd.a(zx22, acl2);
                    continue;
                }
                zx22.a(acl2.b, acl2.c);
            }
        }
        return zx22;
    }

    public static List<acl> b(Random random2, zx zx2, int n2) {
        float f2;
        zw zw2 = zx2.b();
        int \u26032 = zw2.b();
        if (\u26032 <= 0) {
            return null;
        }
        \u26032 /= 2;
        int \u26033 = (\u26032 = 1 + random2.nextInt((\u26032 >> 1) + 1) + random2.nextInt((\u26032 >> 1) + 1)) + n2;
        int \u26034 = (int)((float)\u26033 * (1.0f + (f2 = (random2.nextFloat() + random2.nextFloat() - 1.0f) * 0.15f)) + 0.5f);
        if (\u26034 < 1) {
            \u26034 = 1;
        }
        ArrayList<acl> \u26035 = null;
        Map<Integer, acl> \u26036 = ack.b(\u26034, zx2);
        if (\u26036 != null && !\u26036.isEmpty() && (\u2603 = oa.a(random2, \u26036.values())) != null) {
            \u26035 = Lists.newArrayList();
            \u26035.add(\u2603);
            for (int i2 = \u26034; random2.nextInt(50) <= i2; i2 >>= 1) {
                Iterator<Integer> iterator = \u26036.keySet().iterator();
                while (iterator.hasNext()) {
                    Integer \u26038 = iterator.next();
                    boolean \u26037 = true;
                    for (acl acl2 : \u26035) {
                        if (acl2.b.a(aci.c(\u26038))) continue;
                        \u26037 = false;
                        break;
                    }
                    if (\u26037) continue;
                    iterator.remove();
                }
                if (\u26036.isEmpty()) continue;
                acl acl3 = oa.a(random2, \u26036.values());
                \u26035.add(acl3);
            }
        }
        return \u26035;
    }

    public static Map<Integer, acl> b(int n2, zx zx2) {
        zw zw2 = zx2.b();
        HashMap<Integer, acl> \u26032 = null;
        boolean \u26033 = zx2.b() == zy.aL;
        for (aci aci2 : aci.b) {
            if (aci2 == null || !aci2.C.a(zw2) && !\u26033) continue;
            for (int i2 = aci2.e(); i2 <= aci2.b(); ++i2) {
                if (n2 < aci2.a(i2) || n2 > aci2.b(i2)) continue;
                if (\u26032 == null) {
                    \u26032 = Maps.newHashMap();
                }
                \u26032.put(aci2.B, new acl(aci2, i2));
            }
        }
        return \u26032;
    }

    static final class a
    implements c {
        public pr a;
        public pk b;

        private a() {
        }

        @Override
        public void a(aci aci2, int n2) {
            aci2.a(this.a, this.b, n2);
        }
    }

    static final class b
    implements c {
        public pr a;
        public pk b;

        private b() {
        }

        @Override
        public void a(aci aci2, int n2) {
            aci2.b(this.a, this.b, n2);
        }
    }

    static final class d
    implements c {
        public float a;
        public pw b;

        private d() {
        }

        @Override
        public void a(aci aci2, int n2) {
            this.a += aci2.a(n2, this.b);
        }
    }

    static final class e
    implements c {
        public int a;
        public ow b;

        private e() {
        }

        @Override
        public void a(aci aci2, int n2) {
            this.a += aci2.a(n2, this.b);
        }
    }

    static interface c {
        public void a(aci var1, int var2);
    }
}

