/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class tc {
    private static aui a = new aui(0.0, 0.0, 0.0);

    public static aui a(py py2, int n2, int n3) {
        return tc.c(py2, n2, n3, null);
    }

    public static aui a(py py2, int n2, int n3, aui aui2) {
        a = aui2.a(py2.s, py2.t, py2.u);
        return tc.c(py2, n2, n3, a);
    }

    public static aui b(py py2, int n2, int n3, aui aui2) {
        a = new aui(py2.s, py2.t, py2.u).d(aui2);
        return tc.c(py2, n2, n3, a);
    }

    private static aui c(py py2, int n2, int n3, aui aui2) {
        Random random = py2.bc();
        boolean \u26032 = false;
        int \u26033 = 0;
        int \u26034 = 0;
        int \u26035 = 0;
        float \u26036 = -99999.0f;
        boolean \u26037 = py2.ck() ? (\u2603 = py2.ch().c(ns.c(py2.s), ns.c(py2.t), ns.c(py2.u)) + 4.0) < (\u2603 = (double)(py2.ci() + (float)n2)) * \u2603 : false;
        for (int i2 = 0; i2 < 10; ++i2) {
            int \u26039;
            int \u26038;
            \u26038 = random.nextInt(2 * n2 + 1) - n2;
            \u2603 = random.nextInt(2 * n3 + 1) - n3;
            \u26039 = random.nextInt(2 * n2 + 1) - n2;
            if (aui2 != null && (double)\u26038 * aui2.a + (double)\u26039 * aui2.c < 0.0) continue;
            if (py2.ck() && n2 > 1) {
                cj cj2 = py2.ch();
                \u26038 = py2.s > (double)cj2.n() ? (\u26038 -= random.nextInt(n2 / 2)) : (\u26038 += random.nextInt(n2 / 2));
                \u26039 = py2.u > (double)cj2.p() ? (\u26039 -= random.nextInt(n2 / 2)) : (\u26039 += random.nextInt(n2 / 2));
            }
            cj2 = new cj(\u26038 += ns.c(py2.s), \u2603 += ns.c(py2.t), \u26039 += ns.c(py2.u));
            if (\u26037 && !py2.e(cj2) || !((\u2603 = py2.a(cj2)) > \u26036)) continue;
            \u26036 = \u2603;
            \u26033 = \u26038;
            \u26034 = \u2603;
            \u26035 = \u26039;
            \u26032 = true;
        }
        if (\u26032) {
            return new aui(\u26033, \u26034, \u26035);
        }
        return null;
    }
}

