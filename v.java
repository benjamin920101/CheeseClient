/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class v
extends i {
    @Override
    public String c() {
        return "testforblocks";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.compare.usage";
    }

    @Override
    public void a(m m22, String[] stringArray) throws bz {
        m m22;
        if (stringArray.length < 9) {
            throw new cf("commands.compare.usage", new Object[0]);
        }
        m22.a(n.a.b, 0);
        cj cj2 = v.a(m22, stringArray, 0, false);
        \u2603 = v.a(m22, stringArray, 3, false);
        \u2603 = v.a(m22, stringArray, 6, false);
        aqe \u26032 = new aqe(cj2, \u2603);
        aqe \u26033 = new aqe(\u2603, \u2603.a(\u26032.b()));
        int \u26034 = \u26032.c() * \u26032.d() * \u26032.e();
        if (\u26034 > 524288) {
            throw new bz("commands.compare.tooManyBlocks", \u26034, 524288);
        }
        if (\u26032.b < 0 || \u26032.e >= 256 || \u26033.b < 0 || \u26033.e >= 256) {
            throw new bz("commands.compare.outOfWorld", new Object[0]);
        }
        adm \u26035 = m22.e();
        if (!\u26035.a(\u26032) || !\u26035.a(\u26033)) {
            throw new bz("commands.compare.outOfWorld", new Object[0]);
        }
        boolean \u26036 = false;
        if (stringArray.length > 9 && stringArray[9].equals("masked")) {
            \u26036 = true;
        }
        \u26034 = 0;
        \u2603 = new cj(\u26033.a - \u26032.a, \u26033.b - \u26032.b, \u26033.c - \u26032.c);
        cj.a \u26037 = new cj.a();
        cj.a \u26038 = new cj.a();
        for (int i2 = \u26032.c; i2 <= \u26032.f; ++i2) {
            for (\u2603 = \u26032.b; \u2603 <= \u26032.e; ++\u2603) {
                for (\u2603 = \u26032.a; \u2603 <= \u26032.d; ++\u2603) {
                    \u26037.c(\u2603, \u2603, i2);
                    \u26038.c(\u2603 + \u2603.n(), \u2603 + \u2603.o(), i2 + \u2603.p());
                    boolean bl2 = false;
                    alz \u26039 = \u26035.p(\u26037);
                    if (\u26036 && \u26039.c() == afi.a) continue;
                    if (\u26039 == \u26035.p(\u26038)) {
                        akw akw2 = \u26035.s(\u26037);
                        \u2603 = \u26035.s(\u26038);
                        if (akw2 != null && \u2603 != null) {
                            dn dn2 = new dn();
                            akw2.b(dn2);
                            dn2.o("x");
                            dn2.o("y");
                            dn2.o("z");
                            \u2603 = new dn();
                            \u2603.b(\u2603);
                            \u2603.o("x");
                            \u2603.o("y");
                            \u2603.o("z");
                            if (!dn2.equals(\u2603)) {
                                bl2 = true;
                            }
                        } else if (akw2 != null) {
                            bl2 = true;
                        }
                    } else {
                        bl2 = true;
                    }
                    ++\u26034;
                    if (!bl2) continue;
                    throw new bz("commands.compare.failed", new Object[0]);
                }
            }
        }
        m22.a(n.a.b, \u26034);
        v.a(m22, (k)this, "commands.compare.success", \u26034);
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length > 0 && stringArray.length <= 3) {
            return v.a(stringArray, 0, cj2);
        }
        if (stringArray.length > 3 && stringArray.length <= 6) {
            return v.a(stringArray, 3, cj2);
        }
        if (stringArray.length > 6 && stringArray.length <= 9) {
            return v.a(stringArray, 6, cj2);
        }
        if (stringArray.length == 10) {
            return v.a(stringArray, "masked", "all");
        }
        return null;
    }
}

