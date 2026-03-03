/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ae
extends i {
    @Override
    public String c() {
        return "xp";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.xp.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length <= 0) {
            throw new cf("commands.xp.usage", new Object[0]);
        }
        String string = stringArray[0];
        boolean bl2 = \u2603 = string.endsWith("l") || string.endsWith("L");
        if (\u2603 && string.length() > 1) {
            string = string.substring(0, string.length() - 1);
        }
        boolean bl3 = \u2603 = (\u2603 = ae.a(string)) < 0;
        if (\u2603) {
            \u2603 *= -1;
        }
        lf lf2 = \u2603 = stringArray.length > 1 ? ae.a(m2, stringArray[1]) : ae.b(m2);
        if (\u2603) {
            m2.a(n.a.e, \u2603.bB);
            if (\u2603) {
                ((wn)\u2603).a(-\u2603);
                ae.a(m2, (k)this, "commands.xp.success.negative.levels", \u2603, \u2603.e_());
            } else {
                ((wn)\u2603).a(\u2603);
                ae.a(m2, (k)this, "commands.xp.success.levels", \u2603, \u2603.e_());
            }
        } else {
            m2.a(n.a.e, \u2603.bC);
            if (\u2603) {
                throw new bz("commands.xp.failure.widthdrawXp", new Object[0]);
            }
            \u2603.u(\u2603);
            ae.a(m2, (k)this, "commands.xp.success", \u2603, \u2603.e_());
        }
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 2) {
            return ae.a(stringArray, this.d());
        }
        return null;
    }

    protected String[] d() {
        return MinecraftServer.N().K();
    }

    @Override
    public boolean b(String[] stringArray, int n2) {
        return n2 == 1;
    }
}

