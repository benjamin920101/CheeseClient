/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class u
extends i {
    @Override
    public String c() {
        return "clone";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.clone.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length < 9) {
            throw new cf("commands.clone.usage", new Object[0]);
        }
        m2.a(n.a.b, 0);
        cj cj2 = u.a(m2, stringArray, 0, false);
        cj cj3 = u.a(m2, stringArray, 3, false);
        \u2603 = u.a(m2, stringArray, 6, false);
        aqe \u26032 = new aqe(cj2, cj3);
        aqe \u26033 = new aqe(\u2603, \u2603.a(\u26032.b()));
        int \u26034 = \u26032.c() * \u26032.d() * \u26032.e();
        if (\u26034 > 32768) {
            throw new bz("commands.clone.tooManyBlocks", \u26034, 32768);
        }
        boolean \u26035 = false;
        afh \u26036 = null;
        int \u26037 = -1;
        if ((stringArray.length < 11 || !stringArray[10].equals("force") && !stringArray[10].equals("move")) && \u26032.a(\u26033)) {
            throw new bz("commands.clone.noOverlap", new Object[0]);
        }
        if (stringArray.length >= 11 && stringArray[10].equals("move")) {
            \u26035 = true;
        }
        if (\u26032.b < 0 || \u26032.e >= 256 || \u26033.b < 0 || \u26033.e >= 256) {
            throw new bz("commands.clone.outOfWorld", new Object[0]);
        }
        adm \u26038 = m2.e();
        if (!\u26038.a(\u26032) || !\u26038.a(\u26033)) {
            throw new bz("commands.clone.outOfWorld", new Object[0]);
        }
        boolean \u26039 = false;
        if (stringArray.length >= 10) {
            if (stringArray[9].equals("masked")) {
                \u26039 = true;
            } else if (stringArray[9].equals("filtered")) {
                if (stringArray.length < 12) {
                    throw new cf("commands.clone.usage", new Object[0]);
                }
                \u26036 = u.g(m2, stringArray[11]);
                if (stringArray.length >= 13) {
                    \u26037 = u.a(stringArray[12], 0, 15);
                }
            }
        }
        ArrayList<a> \u260310 = Lists.newArrayList();
        ArrayList<a> \u260311 = Lists.newArrayList();
        ArrayList<a> \u260312 = Lists.newArrayList();
        LinkedList<cj> \u260313 = Lists.newLinkedList();
        \u2603 = new cj(\u26033.a - \u26032.a, \u26033.b - \u26032.b, \u26033.c - \u26032.c);
        for (int i2 = \u26032.c; i2 <= \u26032.f; ++i2) {
            for (int i3 = \u26032.b; i3 <= \u26032.e; ++i3) {
                for (int i4 = \u26032.a; i4 <= \u26032.d; ++i4) {
                    cj cj4 = new cj(i4, i3, i2);
                    cj cj5 = cj4.a(\u2603);
                    alz \u260315 = \u26038.p(cj4);
                    if (\u26039 && \u260315.c() == afi.a || \u26036 != null && (\u260315.c() != \u26036 || \u26037 >= 0 && \u260315.c().c(\u260315) != \u26037)) continue;
                    akw \u260316 = \u26038.s(cj4);
                    if (\u260316 != null) {
                        dn dn2 = new dn();
                        \u260316.b(dn2);
                        \u260311.add(new a(cj5, \u260315, dn2));
                        \u260313.addLast(cj4);
                        continue;
                    }
                    if (\u260315.c().o() || \u260315.c().d()) {
                        \u260310.add(new a(cj5, \u260315, null));
                        \u260313.addLast(cj4);
                        continue;
                    }
                    \u260312.add(new a(cj5, \u260315, null));
                    \u260313.addFirst(cj4);
                }
            }
        }
        if (\u26035) {
            for (cj cj6 : \u260313) {
                akw akw2 = \u26038.s(cj6);
                if (akw2 instanceof og) {
                    ((og)((Object)akw2)).l();
                }
                \u26038.a(cj6, afi.cv.Q(), 2);
            }
            for (cj cj7 : \u260313) {
                \u26038.a(cj7, afi.a.Q(), 3);
            }
        }
        ArrayList<a> arrayList = Lists.newArrayList();
        arrayList.addAll(\u260310);
        arrayList.addAll(\u260311);
        arrayList.addAll(\u260312);
        List list = Lists.reverse(arrayList);
        for (a a2 : list) {
            akw akw2 = \u26038.s(a2.a);
            if (akw2 instanceof og) {
                ((og)((Object)akw2)).l();
            }
            \u26038.a(a2.a, afi.cv.Q(), 2);
        }
        \u26034 = 0;
        for (a a3 : arrayList) {
            if (!\u26038.a(a3.a, a3.b, 2)) continue;
            ++\u26034;
        }
        for (a a4 : \u260311) {
            akw akw3 = \u26038.s(a4.a);
            if (a4.c != null && akw3 != null) {
                a4.c.a("x", a4.a.n());
                a4.c.a("y", a4.a.o());
                a4.c.a("z", a4.a.p());
                akw3.a(a4.c);
                akw3.p_();
            }
            \u26038.a(a4.a, a4.b, 2);
        }
        for (a a5 : list) {
            \u26038.b(a5.a, a5.b.c());
        }
        List<adw> list2 = \u26038.a(\u26032, false);
        if (list2 != null) {
            for (adw adw2 : list2) {
                if (!\u26032.b(adw2.a)) continue;
                cj cj8 = adw2.a.a(\u2603);
                \u26038.b(cj8, adw2.a(), (int)(adw2.b - \u26038.P().f()), adw2.c);
            }
        }
        if (\u26034 <= 0) {
            throw new bz("commands.clone.failed", new Object[0]);
        }
        m2.a(n.a.b, \u26034);
        u.a(m2, (k)this, "commands.clone.success", \u26034);
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length > 0 && stringArray.length <= 3) {
            return u.a(stringArray, 0, cj2);
        }
        if (stringArray.length > 3 && stringArray.length <= 6) {
            return u.a(stringArray, 3, cj2);
        }
        if (stringArray.length > 6 && stringArray.length <= 9) {
            return u.a(stringArray, 6, cj2);
        }
        if (stringArray.length == 10) {
            return u.a(stringArray, "replace", "masked", "filtered");
        }
        if (stringArray.length == 11) {
            return u.a(stringArray, "normal", "force", "move");
        }
        if (stringArray.length == 12 && "filtered".equals(stringArray[9])) {
            return u.a(stringArray, afh.c.c());
        }
        return null;
    }

    static class a {
        public final cj a;
        public final alz b;
        public final dn c;

        public a(cj cj2, alz alz2, dn dn2) {
            this.a = cj2;
            this.b = alz2;
            this.c = dn2;
        }
    }
}

