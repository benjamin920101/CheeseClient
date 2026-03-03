/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class bp
extends i {
    @Override
    public String c() {
        return "testforblock";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.testforblock.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length < 4) {
            throw new cf("commands.testforblock.usage", new Object[0]);
        }
        m2.a(n.a.b, 0);
        cj cj2 = bp.a(m2, stringArray, 0, false);
        afh \u26032 = afh.b(stringArray[3]);
        if (\u26032 == null) {
            throw new cb("commands.setblock.notFound", stringArray[3]);
        }
        int \u26033 = -1;
        if (stringArray.length >= 5) {
            \u26033 = bp.a(stringArray[4], -1, 15);
        }
        if (!(\u2603 = m2.e()).e(cj2)) {
            throw new bz("commands.testforblock.outOfWorld", new Object[0]);
        }
        dn \u26034 = new dn();
        boolean \u26035 = false;
        if (stringArray.length >= 6 && \u26032.z()) {
            Object object = bp.a(m2, stringArray, 5).c();
            try {
                \u26034 = ed.a((String)object);
                \u26035 = true;
            }
            catch (ec \u26036) {
                throw new bz("commands.setblock.tagError", \u26036.getMessage());
            }
        }
        if ((\u2603 = (object = \u2603.p(cj2)).c()) != \u26032) {
            throw new bz("commands.testforblock.failed.tile", cj2.n(), cj2.o(), cj2.p(), \u2603.f(), \u26032.f());
        }
        if (\u26033 > -1 && (\u2603 = object.c().c((alz)object)) != \u26033) {
            throw new bz("commands.testforblock.failed.data", cj2.n(), cj2.o(), cj2.p(), \u2603, \u26033);
        }
        if (\u26035) {
            akw \u26037 = \u2603.s(cj2);
            if (\u26037 == null) {
                throw new bz("commands.testforblock.failed.tileEntity", cj2.n(), cj2.o(), cj2.p());
            }
            dn \u26038 = new dn();
            \u26037.b(\u26038);
            if (!dy.a(\u26034, \u26038, true)) {
                throw new bz("commands.testforblock.failed.nbt", cj2.n(), cj2.o(), cj2.p());
            }
        }
        m2.a(n.a.b, 1);
        bp.a(m2, (k)this, "commands.testforblock.success", cj2.n(), cj2.o(), cj2.p());
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length > 0 && stringArray.length <= 3) {
            return bp.a(stringArray, 0, cj2);
        }
        if (stringArray.length == 4) {
            return bp.a(stringArray, afh.c.c());
        }
        return null;
    }
}

