/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class anv
implements amv {
    private adm a;
    private Random b;
    private final alz[] c = new alz[256];
    private final apz d;
    private final List<aqq> e = Lists.newArrayList();
    private final boolean f;
    private final boolean g;
    private apc h;
    private apc i;

    public anv(adm adm2, long l2, boolean bl2, String string) {
        this.a = adm2;
        this.b = new Random(l2);
        this.d = apz.a(string);
        if (bl2) {
            Map<String, Map<String, String>> map = this.d.b();
            if (map.containsKey("village")) {
                Map<String, String> map2 = map.get("village");
                if (!map2.containsKey("size")) {
                    map2.put("size", "1");
                }
                this.e.add(new aqv(map2));
            }
            if (map.containsKey("biome_1")) {
                this.e.add(new aqm(map.get("biome_1")));
            }
            if (map.containsKey("mineshaft")) {
                this.e.add(new aqf(map.get("mineshaft")));
            }
            if (map.containsKey("stronghold")) {
                this.e.add(new aqo(map.get("stronghold")));
            }
            if (map.containsKey("oceanmonument")) {
                this.e.add(new aqk(map.get("oceanmonument")));
            }
        }
        if (this.d.b().containsKey("lake")) {
            this.h = new apc(afi.j);
        }
        if (this.d.b().containsKey("lava_lake")) {
            this.i = new apc(afi.l);
        }
        this.g = this.d.b().containsKey("dungeon");
        int n2 = 0;
        \u26033 = 0;
        boolean \u26032 = true;
        for (aqa aqa22 : this.d.c()) {
            aqa aqa22;
            for (int i2 = aqa22.d(); i2 < aqa22.d() + aqa22.b(); ++i2) {
                alz alz2 = aqa22.c();
                if (alz2.c() == afi.a) continue;
                \u26032 = false;
                this.c[i2] = alz2;
            }
            if (aqa22.c().c() == afi.a) {
                \u26033 += aqa22.b();
                continue;
            }
            n2 += aqa22.b() + \u26033;
            int \u26033 = 0;
        }
        adm2.b(n2);
        this.f = \u26032 ? false : this.d.b().containsKey("decoration");
    }

    @Override
    public amy d(int n2, int n3) {
        ans ans2 = new ans();
        for (int i2 = 0; i2 < this.c.length; ++i2) {
            alz alz2 = this.c[i2];
            if (alz2 == null) continue;
            for (int i3 = 0; i3 < 16; ++i3) {
                for (int i4 = 0; i4 < 16; ++i4) {
                    ans2.a(i3, i2, i4, alz2);
                }
            }
        }
        for (any any2 : this.e) {
            any2.a(this, this.a, n2, n3, ans2);
        }
        amy amy2 = new amy(this.a, ans2, n2, n3);
        ady[] adyArray = this.a.v().b(null, n2 * 16, n3 * 16, 16, 16);
        byte[] \u26034 = amy2.k();
        for (int i2 = 0; i2 < \u26034.length; ++i2) {
            \u26034[i2] = (byte)adyArray[i2].az;
        }
        amy2.b();
        return amy2;
    }

    @Override
    public boolean a(int n2, int n3) {
        return true;
    }

    @Override
    public void a(amv amv2, int n2, int n3) {
        \u2603 = n2 * 16;
        \u2603 = n3 * 16;
        cj cj2 = new cj(\u2603, 0, \u2603);
        ady \u26032 = this.a.b(new cj(\u2603 + 16, 0, \u2603 + 16));
        boolean \u26033 = false;
        this.b.setSeed(this.a.J());
        long \u26034 = this.b.nextLong() / 2L * 2L + 1L;
        long \u26035 = this.b.nextLong() / 2L * 2L + 1L;
        this.b.setSeed((long)n2 * \u26034 + (long)n3 * \u26035 ^ this.a.J());
        adg \u26036 = new adg(n2, n3);
        Object \u26037 = this.e.iterator();
        while (\u26037.hasNext()) {
            aqq aqq2 = \u26037.next();
            boolean \u26038 = aqq2.a(this.a, this.b, \u26036);
            if (!(aqq2 instanceof aqv)) continue;
            \u26033 |= \u26038;
        }
        if (this.h != null && !\u26033 && this.b.nextInt(4) == 0) {
            this.h.b(this.a, this.b, cj2.a(this.b.nextInt(16) + 8, this.b.nextInt(256), this.b.nextInt(16) + 8));
        }
        if (!(this.i == null || \u26033 || this.b.nextInt(8) != 0 || ((df)(\u26037 = cj2.a(this.b.nextInt(16) + 8, this.b.nextInt(this.b.nextInt(248) + 8), this.b.nextInt(16) + 8))).o() >= this.a.F() && this.b.nextInt(10) != 0)) {
            this.i.b(this.a, this.b, (cj)\u26037);
        }
        if (this.g) {
            for (int i2 = 0; i2 < 8; ++i2) {
                new api().b(this.a, this.b, cj2.a(this.b.nextInt(16) + 8, this.b.nextInt(256), this.b.nextInt(16) + 8));
            }
        }
        if (this.f) {
            \u26032.a(this.a, this.b, cj2);
        }
    }

    @Override
    public boolean a(amv amv2, amy amy2, int n2, int n3) {
        return false;
    }

    @Override
    public boolean a(boolean bl2, nu nu2) {
        return true;
    }

    @Override
    public void c() {
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean e() {
        return true;
    }

    @Override
    public String f() {
        return "FlatLevelSource";
    }

    @Override
    public List<ady.c> a(pt pt2, cj cj2) {
        ady ady2 = this.a.b(cj2);
        return ady2.a(pt2);
    }

    @Override
    public cj a(adm adm2, String string, cj cj2) {
        if ("Stronghold".equals(string)) {
            for (aqq aqq2 : this.e) {
                if (!(aqq2 instanceof aqo)) continue;
                return aqq2.b(adm2, cj2);
            }
        }
        return null;
    }

    @Override
    public int g() {
        return 0;
    }

    @Override
    public void a(amy amy2, int n2, int n3) {
        for (aqq aqq2 : this.e) {
            aqq2.a(this, this.a, n2, n3, null);
        }
    }

    @Override
    public amy a(cj cj2) {
        return this.d(cj2.n() >> 4, cj2.p() >> 4);
    }
}

