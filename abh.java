/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;

public class abh
implements abs {
    @Override
    public boolean a(xp xp2, adm adm2) {
        zx zx2 = null;
        ArrayList<zx> \u26032 = Lists.newArrayList();
        for (int i2 = 0; i2 < xp2.o_(); ++i2) {
            zx zx3 = xp2.a(i2);
            if (zx3 == null) continue;
            if (zx3.b() instanceof yj) {
                yj yj2 = (yj)zx3.b();
                if (yj2.x_() == yj.a.a && zx2 == null) {
                    zx2 = zx3;
                    continue;
                }
                return false;
            }
            if (zx3.b() == zy.aW) {
                \u26032.add(zx3);
                continue;
            }
            return false;
        }
        return zx2 != null && !\u26032.isEmpty();
    }

    @Override
    public zx a(xp xp2) {
        float \u26037;
        float \u26036;
        int n2;
        zx zx2 = null;
        int[] \u26032 = new int[3];
        int \u26033 = 0;
        int \u26034 = 0;
        yj \u26035 = null;
        for (n2 = 0; n2 < xp2.o_(); ++n2) {
            zx zx3 = xp2.a(n2);
            if (zx3 == null) continue;
            if (zx3.b() instanceof yj) {
                \u26035 = (yj)zx3.b();
                if (\u26035.x_() == yj.a.a && zx2 == null) {
                    zx2 = zx3.k();
                    zx2.b = 1;
                    if (!\u26035.d_(zx3)) continue;
                    int n3 = \u26035.b(zx2);
                    \u26036 = (float)(n3 >> 16 & 0xFF) / 255.0f;
                    \u26037 = (float)(n3 >> 8 & 0xFF) / 255.0f;
                    float \u26038 = (float)(n3 & 0xFF) / 255.0f;
                    \u26033 = (int)((float)\u26033 + Math.max(\u26036, Math.max(\u26037, \u26038)) * 255.0f);
                    \u26032[0] = (int)((float)\u26032[0] + \u26036 * 255.0f);
                    \u26032[1] = (int)((float)\u26032[1] + \u26037 * 255.0f);
                    \u26032[2] = (int)((float)\u26032[2] + \u26038 * 255.0f);
                    ++\u26034;
                    continue;
                }
                return null;
            }
            if (zx3.b() == zy.aW) {
                float[] fArray = tv.a(zd.a(zx3.i()));
                int \u26039 = (int)(fArray[0] * 255.0f);
                int \u260310 = (int)(fArray[1] * 255.0f);
                int \u260311 = (int)(fArray[2] * 255.0f);
                \u26033 += Math.max(\u26039, Math.max(\u260310, \u260311));
                \u26032[0] = \u26032[0] + \u26039;
                \u26032[1] = \u26032[1] + \u260310;
                \u26032[2] = \u26032[2] + \u260311;
                ++\u26034;
                continue;
            }
            return null;
        }
        if (\u26035 == null) {
            return null;
        }
        n2 = \u26032[0] / \u26034;
        \u2603 = \u26032[1] / \u26034;
        n3 = \u26032[2] / \u26034;
        \u26036 = (float)\u26033 / (float)\u26034;
        \u26037 = Math.max(n2, Math.max(\u2603, n3));
        n2 = (int)((float)n2 * \u26036 / \u26037);
        \u2603 = (int)((float)\u2603 * \u26036 / \u26037);
        n3 = (int)((float)n3 * \u26036 / \u26037);
        \u2603 = n2;
        \u2603 = (\u2603 << 8) + \u2603;
        \u2603 = (\u2603 << 8) + n3;
        \u26035.b(zx2, \u2603);
        return zx2;
    }

    @Override
    public int a() {
        return 10;
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

