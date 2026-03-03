/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class aj
extends i {
    @Override
    public String c() {
        return "give";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.give.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length < 2) {
            throw new cf("commands.give.usage", new Object[0]);
        }
        lf lf2 = aj.a(m2, stringArray[0]);
        zw \u26032 = aj.f(m2, stringArray[1]);
        int \u26033 = stringArray.length >= 3 ? aj.a(stringArray[2], 1, 64) : 1;
        int \u26034 = stringArray.length >= 4 ? aj.a(stringArray[3]) : 0;
        zx \u26035 = new zx(\u26032, \u26033, \u26034);
        if (stringArray.length >= 5) {
            String string = aj.a(m2, stringArray, 4).c();
            try {
                \u26035.d(ed.a(string));
            }
            catch (ec \u26036) {
                throw new bz("commands.give.tagError", \u26036.getMessage());
            }
        }
        if (\u2603 = lf2.bi.a(\u26035)) {
            lf2.o.a((pk)lf2, "random.pop", 0.2f, ((lf2.bc().nextFloat() - lf2.bc().nextFloat()) * 0.7f + 1.0f) * 2.0f);
            lf2.bj.b();
        }
        if (!\u2603 || \u26035.b > 0) {
            m2.a(n.a.d, \u26033 - \u26035.b);
            uz \u26037 = lf2.a(\u26035, false);
            if (\u26037 != null) {
                \u26037.q();
                \u26037.b(lf2.e_());
            }
        } else {
            \u26035.b = 1;
            m2.a(n.a.d, \u26033);
            uz \u26038 = lf2.a(\u26035, false);
            if (\u26038 != null) {
                \u26038.v();
            }
        }
        aj.a(m2, (k)this, "commands.give.success", \u26035.C(), \u26033, lf2.e_());
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return aj.a(stringArray, this.d());
        }
        if (stringArray.length == 2) {
            return aj.a(stringArray, zw.e.c());
        }
        return null;
    }

    protected String[] d() {
        return MinecraftServer.N().K();
    }

    @Override
    public boolean b(String[] stringArray, int n2) {
        return n2 == 0;
    }
}

