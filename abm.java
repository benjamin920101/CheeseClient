/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;

public class abm
implements abs {
    private zx a;

    @Override
    public boolean a(xp xp2, adm adm2) {
        int n2;
        int n3;
        Object \u26032;
        this.a = null;
        int n4 = 0;
        n2 = 0;
        n3 = 0;
        \u2603 = 0;
        \u2603 = 0;
        \u2603 = 0;
        for (\u2603 = 0; \u2603 < xp2.o_(); ++\u2603) {
            \u26032 = xp2.a(\u2603);
            if (\u26032 == null) continue;
            if (((zx)\u26032).b() == zy.H) {
                ++n2;
                continue;
            }
            if (((zx)\u26032).b() == zy.cc) {
                ++\u2603;
                continue;
            }
            if (((zx)\u26032).b() == zy.aW) {
                ++n3;
                continue;
            }
            if (((zx)\u26032).b() == zy.aK) {
                ++n4;
                continue;
            }
            if (((zx)\u26032).b() == zy.aT) {
                ++\u2603;
                continue;
            }
            if (((zx)\u26032).b() == zy.i) {
                ++\u2603;
                continue;
            }
            if (((zx)\u26032).b() == zy.bL) {
                ++\u2603;
                continue;
            }
            if (((zx)\u26032).b() == zy.G) {
                ++\u2603;
                continue;
            }
            if (((zx)\u26032).b() == zy.bx) {
                ++\u2603;
                continue;
            }
            if (((zx)\u26032).b() == zy.bX) {
                ++\u2603;
                continue;
            }
            return false;
        }
        \u2603 += n3 + \u2603;
        if (n2 > 3 || n4 > 1) {
            return false;
        }
        if (n2 >= 1 && n4 == 1 && \u2603 == 0) {
            this.a = new zx(zy.cb);
            if (\u2603 > 0) {
                dn dn2 = new dn();
                \u26032 = new dn();
                du \u26033 = new du();
                for (int i2 = 0; i2 < xp2.o_(); ++i2) {
                    zx zx2 = xp2.a(i2);
                    if (zx2 == null || zx2.b() != zy.cc || !zx2.n() || !zx2.o().b("Explosion", 10)) continue;
                    \u26033.a(zx2.o().m("Explosion"));
                }
                ((dn)\u26032).a("Explosions", \u26033);
                ((dn)\u26032).a("Flight", (byte)n2);
                dn2.a("Fireworks", (eb)\u26032);
                this.a.d(dn2);
            }
            return true;
        }
        if (n2 == 1 && n4 == 0 && \u2603 == 0 && n3 > 0 && \u2603 <= 1) {
            this.a = new zx(zy.cc);
            dn dn3 = new dn();
            \u26032 = new dn();
            int \u26034 = 0;
            ArrayList<Integer> \u26035 = Lists.newArrayList();
            for (int i3 = 0; i3 < xp2.o_(); ++i3) {
                zx zx3 = xp2.a(i3);
                if (zx3 == null) continue;
                if (zx3.b() == zy.aW) {
                    \u26035.add(ze.a[zx3.i() & 0xF]);
                    continue;
                }
                if (zx3.b() == zy.aT) {
                    ((dn)\u26032).a("Flicker", true);
                    continue;
                }
                if (zx3.b() == zy.i) {
                    ((dn)\u26032).a("Trail", true);
                    continue;
                }
                if (zx3.b() == zy.bL) {
                    \u26034 = 1;
                    continue;
                }
                if (zx3.b() == zy.G) {
                    \u26034 = 4;
                    continue;
                }
                if (zx3.b() == zy.bx) {
                    \u26034 = 2;
                    continue;
                }
                if (zx3.b() != zy.bX) continue;
                \u26034 = 3;
            }
            int[] nArray = new int[\u26035.size()];
            for (int i4 = 0; i4 < nArray.length; ++i4) {
                nArray[i4] = (Integer)\u26035.get(i4);
            }
            ((dn)\u26032).a("Colors", nArray);
            ((dn)\u26032).a("Type", (byte)\u26034);
            dn3.a("Explosion", (eb)\u26032);
            this.a.d(dn3);
            return true;
        }
        if (n2 == 0 && n4 == 0 && \u2603 == 1 && n3 > 0 && n3 == \u2603) {
            dn dn4;
            ArrayList<Integer> arrayList = Lists.newArrayList();
            for (int i5 = 0; i5 < xp2.o_(); ++i5) {
                zx zx4 = xp2.a(i5);
                if (zx4 == null) continue;
                if (zx4.b() == zy.aW) {
                    arrayList.add(ze.a[zx4.i() & 0xF]);
                    continue;
                }
                if (zx4.b() != zy.cc) continue;
                this.a = zx4.k();
                this.a.b = 1;
            }
            int[] \u26036 = new int[arrayList.size()];
            for (int i6 = 0; i6 < \u26036.length; ++i6) {
                \u26036[i6] = (Integer)arrayList.get(i6);
            }
            if (this.a != null && this.a.n()) {
                dn4 = this.a.o().m("Explosion");
                if (dn4 == null) {
                    return false;
                }
            } else {
                return false;
            }
            dn4.a("FadeColors", \u26036);
            return true;
        }
        return false;
    }

    @Override
    public zx a(xp xp2) {
        return this.a.k();
    }

    @Override
    public int a() {
        return 10;
    }

    @Override
    public zx b() {
        return this.a;
    }

    @Override
    public zx[] b(xp xp2) {
        zx[] zxArray = new zx[xp2.o_()];
        for (int i2 = 0; i2 < zxArray.length; ++i2) {
            zx zx2 = xp2.a(i2);
            if (zx2 == null || !zx2.b().r()) continue;
            zxArray[i2] = new zx(zx2.b().q());
        }
        return zxArray;
    }
}

