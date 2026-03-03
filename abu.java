/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;

public class abu
implements abs {
    @Override
    public boolean a(xp xp2, adm adm2) {
        ArrayList<zx> arrayList = Lists.newArrayList();
        for (int i2 = 0; i2 < xp2.o_(); ++i2) {
            zx zx2 = xp2.a(i2);
            if (zx2 == null) continue;
            arrayList.add(zx2);
            if (arrayList.size() <= 1) continue;
            \u2603 = (zx)arrayList.get(0);
            if (zx2.b() == \u2603.b() && \u2603.b == 1 && zx2.b == 1 && \u2603.b().m()) continue;
            return false;
        }
        return arrayList.size() == 2;
    }

    @Override
    public zx a(xp xp2) {
        Object \u26032;
        ArrayList<zx> arrayList = Lists.newArrayList();
        for (int i2 = 0; i2 < xp2.o_(); ++i2) {
            zx zx2 = xp2.a(i2);
            if (zx2 == null) continue;
            arrayList.add(zx2);
            if (arrayList.size() <= 1) continue;
            \u26032 = (zx)arrayList.get(0);
            if (zx2.b() == ((zx)\u26032).b() && ((zx)\u26032).b == 1 && zx2.b == 1 && ((zx)\u26032).b().m()) continue;
            return null;
        }
        if (arrayList.size() == 2) {
            \u2603 = (zx)arrayList.get(0);
            zx2 = (zx)arrayList.get(1);
            if (\u2603.b() == zx2.b() && \u2603.b == 1 && zx2.b == 1 && \u2603.b().m()) {
                \u26032 = \u2603.b();
                int \u26033 = ((zw)\u26032).l() - \u2603.h();
                int \u26034 = ((zw)\u26032).l() - zx2.h();
                int \u26035 = \u26033 + \u26034 + ((zw)\u26032).l() * 5 / 100;
                int \u26036 = ((zw)\u26032).l() - \u26035;
                if (\u26036 < 0) {
                    \u26036 = 0;
                }
                return new zx(\u2603.b(), 1, \u26036);
            }
        }
        return null;
    }

    @Override
    public int a() {
        return 4;
    }

    @Override
    public zx b() {
        return null;
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

