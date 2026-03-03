/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class be
extends i {
    @Override
    public String c() {
        return "setblock";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.setblock.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length < 4) {
            throw new cf("commands.setblock.usage", new Object[0]);
        }
        m2.a(n.a.b, 0);
        cj cj2 = be.a(m2, stringArray, 0, false);
        afh \u26032 = i.g(m2, stringArray[3]);
        int \u26033 = 0;
        if (stringArray.length >= 5) {
            \u26033 = be.a(stringArray[4], 0, 15);
        }
        if (!(\u2603 = m2.e()).e(cj2)) {
            throw new bz("commands.setblock.outOfWorld", new Object[0]);
        }
        dn \u26034 = new dn();
        boolean \u26035 = false;
        if (stringArray.length >= 7 && \u26032.z()) {
            Object object = be.a(m2, stringArray, 6).c();
            try {
                \u26034 = ed.a((String)object);
                \u26035 = true;
            }
            catch (ec \u26036) {
                throw new bz("commands.setblock.tagError", \u26036.getMessage());
            }
        }
        if (stringArray.length >= 6) {
            if (stringArray[5].equals("destroy")) {
                \u2603.b(cj2, true);
                if (\u26032 == afi.a) {
                    be.a(m2, (k)this, "commands.setblock.success", new Object[0]);
                    return;
                }
            } else if (stringArray[5].equals("keep") && !\u2603.d(cj2)) {
                throw new bz("commands.setblock.noChange", new Object[0]);
            }
        }
        if ((object = \u2603.s(cj2)) != null) {
            if (object instanceof og) {
                ((og)object).l();
            }
            \u2603.a(cj2, afi.a.Q(), \u26032 == afi.a ? 2 : 4);
        }
        if (!\u2603.a(cj2, \u2603 = \u26032.a(\u26033), 2)) {
            throw new bz("commands.setblock.noChange", new Object[0]);
        }
        if (\u26035 && (\u2603 = \u2603.s(cj2)) != null) {
            \u26034.a("x", cj2.n());
            \u26034.a("y", cj2.o());
            \u26034.a("z", cj2.p());
            \u2603.a(\u26034);
        }
        \u2603.b(cj2, \u2603.c());
        m2.a(n.a.b, 1);
        be.a(m2, (k)this, "commands.setblock.success", new Object[0]);
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length > 0 && stringArray.length <= 3) {
            return be.a(stringArray, 0, cj2);
        }
        if (stringArray.length == 4) {
            return be.a(stringArray, afh.c.c());
        }
        if (stringArray.length == 6) {
            return be.a(stringArray, "replace", "destroy", "keep");
        }
        return null;
    }
}

