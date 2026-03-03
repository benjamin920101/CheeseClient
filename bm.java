/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class bm
extends i {
    @Override
    public String c() {
        return "summon";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.summon.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length < 1) {
            throw new cf("commands.summon.usage", new Object[0]);
        }
        String string = stringArray[0];
        cj \u26032 = m2.c();
        aui \u26033 = m2.d();
        double \u26034 = \u26033.a;
        double \u26035 = \u26033.b;
        double \u26036 = \u26033.c;
        if (stringArray.length >= 4) {
            \u26034 = bm.b(\u26034, stringArray[1], true);
            \u26035 = bm.b(\u26035, stringArray[2], false);
            \u26036 = bm.b(\u26036, stringArray[3], true);
            \u26032 = new cj(\u26034, \u26035, \u26036);
        }
        if (!(\u2603 = m2.e()).e(\u26032)) {
            throw new bz("commands.summon.outOfWorld", new Object[0]);
        }
        if ("LightningBolt".equals(string)) {
            \u2603.c(new uv(\u2603, \u26034, \u26035, \u26036));
            bm.a(m2, (k)this, "commands.summon.success", new Object[0]);
            return;
        }
        dn \u26037 = new dn();
        boolean \u26038 = false;
        if (stringArray.length >= 5) {
            Object object = bm.a(m2, stringArray, 4);
            try {
                \u26037 = ed.a(object.c());
                \u26038 = true;
            }
            catch (ec \u26039) {
                throw new bz("commands.summon.tagError", \u26039.getMessage());
            }
        }
        \u26037.a("id", string);
        try {
            object = pm.a(\u26037, \u2603);
        }
        catch (RuntimeException runtimeException) {
            throw new bz("commands.summon.failed", new Object[0]);
        }
        if (object == null) {
            throw new bz("commands.summon.failed", new Object[0]);
        }
        ((pk)object).b(\u26034, \u26035, \u26036, ((pk)object).y, ((pk)object).z);
        if (!\u26038 && object instanceof ps) {
            ((ps)object).a(\u2603.E(new cj((pk)object)), null);
        }
        \u2603.d((pk)object);
        Object \u260311 = object;
        dn \u260310 = \u26037;
        while (\u260311 != null && \u260310.b("Riding", 10)) {
            pk pk2 = pm.a(\u260310.m("Riding"), \u2603);
            if (pk2 != null) {
                pk2.b(\u26034, \u26035, \u26036, pk2.y, pk2.z);
                \u2603.d(pk2);
                ((pk)\u260311).a(pk2);
            }
            \u260311 = pk2;
            \u260310 = \u260310.m("Riding");
        }
        bm.a(m2, (k)this, "commands.summon.success", new Object[0]);
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return bm.a(stringArray, pm.b());
        }
        if (stringArray.length > 1 && stringArray.length <= 4) {
            return bm.a(stringArray, 1, cj2);
        }
        return null;
    }
}

