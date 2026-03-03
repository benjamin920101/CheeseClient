/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class anu
implements amv {
    private static final List<alz> a = Lists.newArrayList();
    private static final int b;
    private static final int c;
    private final adm d;

    public anu(adm adm2) {
        this.d = adm2;
    }

    @Override
    public amy d(int n2, int n3) {
        ans ans2 = new ans();
        for (int i2 = 0; i2 < 16; ++i2) {
            for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                \u2603 = n2 * 16 + i2;
                i3 = n3 * 16 + \u2603;
                ans2.a(i2, 60, \u2603, afi.cv.Q());
                alz alz2 = anu.b(\u2603, i3);
                if (alz2 == null) continue;
                ans2.a(i2, 70, \u2603, alz2);
            }
        }
        amy \u26032 = new amy(this.d, ans2, n2, n3);
        \u26032.b();
        ady[] \u26033 = this.d.v().b(null, n2 * 16, n3 * 16, 16, 16);
        byte[] \u26034 = \u26032.k();
        for (int i3 = 0; i3 < \u26034.length; ++i3) {
            \u26034[i3] = (byte)\u26033[i3].az;
        }
        \u26032.b();
        return \u26032;
    }

    public static alz b(int n2, int n3) {
        alz alz2 = null;
        if (n2 > 0 && n3 > 0 && n2 % 2 != 0 && n3 % 2 != 0 && (n2 /= 2) <= b && (n3 /= 2) <= c && (\u2603 = ns.a(n2 * b + n3)) < a.size()) {
            alz2 = a.get(\u2603);
        }
        return alz2;
    }

    @Override
    public boolean a(int n2, int n3) {
        return true;
    }

    @Override
    public void a(amv amv2, int n2, int n3) {
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
        return "DebugLevelSource";
    }

    @Override
    public List<ady.c> a(pt pt2, cj cj2) {
        ady ady2 = this.d.b(cj2);
        return ady2.a(pt2);
    }

    @Override
    public cj a(adm adm2, String string, cj cj2) {
        return null;
    }

    @Override
    public int g() {
        return 0;
    }

    @Override
    public void a(amy amy2, int n2, int n3) {
    }

    @Override
    public amy a(cj cj2) {
        return this.d(cj2.n() >> 4, cj2.p() >> 4);
    }

    static {
        for (afh afh2 : afh.c) {
            a.addAll(afh2.P().a());
        }
        b = ns.f(ns.c((float)a.size()));
        c = ns.f((float)a.size() / (float)b);
    }
}

