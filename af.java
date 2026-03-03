/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class af
extends i {
    @Override
    public String c() {
        return "fill";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.fill.usage";
    }

    @Override
    public void a(m m2, String[] stringArray2) throws bz {
        Object object;
        if (stringArray2.length < 7) {
            throw new cf("commands.fill.usage", new Object[0]);
        }
        m2.a(n.a.b, 0);
        cj cj2 = af.a(m2, stringArray2, 0, false);
        \u2603 = af.a(m2, stringArray2, 3, false);
        afh \u26032 = i.g(m2, stringArray2[6]);
        int \u26033 = 0;
        if (stringArray2.length >= 8) {
            \u26033 = af.a(stringArray2[7], 0, 15);
        }
        \u2603 = new cj(Math.min(cj2.n(), \u2603.n()), Math.min(cj2.o(), \u2603.o()), Math.min(cj2.p(), \u2603.p()));
        \u2603 = new cj(Math.max(cj2.n(), \u2603.n()), Math.max(cj2.o(), \u2603.o()), Math.max(cj2.p(), \u2603.p()));
        int \u26034 = (\u2603.n() - \u2603.n() + 1) * (\u2603.o() - \u2603.o() + 1) * (\u2603.p() - \u2603.p() + 1);
        if (\u26034 > 32768) {
            throw new bz("commands.fill.tooManyBlocks", \u26034, 32768);
        }
        if (\u2603.o() < 0 || \u2603.o() >= 256) {
            throw new bz("commands.fill.outOfWorld", new Object[0]);
        }
        adm \u26035 = m2.e();
        for (int i2 = \u2603.p(); i2 < \u2603.p() + 16; i2 += 16) {
            for (\u26036 = \u2603.n(); \u26036 < \u2603.n() + 16; \u26036 += 16) {
                if (\u26035.e(new cj(\u26036, \u2603.o() - \u2603.o(), i2))) continue;
                throw new bz("commands.fill.outOfWorld", new Object[0]);
            }
        }
        dn dn2 = new dn();
        int \u26036 = 0;
        if (stringArray2.length >= 10 && \u26032.z()) {
            object = af.a(m2, stringArray2, 9).c();
            try {
                dn2 = ed.a((String)object);
                \u26036 = 1;
            }
            catch (ec ec2) {
                throw new bz("commands.fill.tagError", ec2.getMessage());
            }
        }
        object = Lists.newArrayList();
        \u26034 = 0;
        for (int i3 = \u2603.p(); i3 <= \u2603.p(); ++i3) {
            for (\u2603 = \u2603.o(); \u2603 <= \u2603.o(); ++\u2603) {
                for (\u2603 = \u2603.n(); \u2603 <= \u2603.n(); ++\u2603) {
                    alz \u26037;
                    cj cj3 = new cj(\u2603, \u2603, i3);
                    if (stringArray2.length >= 9) {
                        if (stringArray2[8].equals("outline") || stringArray2[8].equals("hollow")) {
                            if (\u2603 != \u2603.n() && \u2603 != \u2603.n() && \u2603 != \u2603.o() && \u2603 != \u2603.o() && i3 != \u2603.p() && i3 != \u2603.p()) {
                                if (!stringArray2[8].equals("hollow")) continue;
                                \u26035.a(cj3, afi.a.Q(), 2);
                                object.add(cj3);
                                continue;
                            }
                        } else if (stringArray2[8].equals("destroy")) {
                            \u26035.b(cj3, true);
                        } else if (stringArray2[8].equals("keep")) {
                            if (!\u26035.d(cj3)) {
                                continue;
                            }
                        } else if (stringArray2[8].equals("replace") && !\u26032.z()) {
                            String[] stringArray2;
                            if (stringArray2.length > 9) {
                                Object object2 = i.g(m2, stringArray2[9]);
                                if (\u26035.p(cj3).c() != object2) continue;
                            }
                            if (stringArray2.length > 10) {
                                int n2 = i.a(stringArray2[10]);
                                \u26037 = \u26035.p(cj3);
                                if (\u26037.c().c(\u26037) != n2) continue;
                            }
                        }
                    }
                    if ((object2 = \u26035.s(cj3)) != null) {
                        if (object2 instanceof og) {
                            ((og)object2).l();
                        }
                        \u26035.a(cj3, afi.cv.Q(), \u26032 == afi.cv ? 2 : 4);
                    }
                    if (!\u26035.a(cj3, \u26037 = \u26032.a(\u26033), 2)) continue;
                    object.add(cj3);
                    ++\u26034;
                    if (\u26036 == 0 || (\u2603 = \u26035.s(cj3)) == null) continue;
                    dn2.a("x", cj3.n());
                    dn2.a("y", cj3.o());
                    dn2.a("z", cj3.p());
                    \u2603.a(dn2);
                }
            }
        }
        Iterator iterator = object.iterator();
        while (iterator.hasNext()) {
            cj cj4 = (cj)iterator.next();
            afh \u26038 = \u26035.p(cj4).c();
            \u26035.b(cj4, \u26038);
        }
        if (\u26034 <= 0) {
            throw new bz("commands.fill.failed", new Object[0]);
        }
        m2.a(n.a.b, \u26034);
        af.a(m2, (k)this, "commands.fill.success", \u26034);
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length > 0 && stringArray.length <= 3) {
            return af.a(stringArray, 0, cj2);
        }
        if (stringArray.length > 3 && stringArray.length <= 6) {
            return af.a(stringArray, 3, cj2);
        }
        if (stringArray.length == 7) {
            return af.a(stringArray, afh.c.c());
        }
        if (stringArray.length == 9) {
            return af.a(stringArray, "replace", "destroy", "keep", "hollow", "outline");
        }
        if (stringArray.length == 10 && "replace".equals(stringArray[8])) {
            return af.a(stringArray, afh.c.c());
        }
        return null;
    }
}

