/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ab
extends i {
    @Override
    public String c() {
        return "enchant";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.enchant.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        Object \u26033;
        int \u26032;
        if (stringArray.length < 2) {
            throw new cf("commands.enchant.usage", new Object[0]);
        }
        lf lf2 = ab.a(m2, stringArray[0]);
        m2.a(n.a.d, 0);
        try {
            \u26032 = ab.a(stringArray[1], 0);
        }
        catch (cb cb2) {
            \u26033 = aci.b(stringArray[1]);
            if (\u26033 == null) {
                throw cb2;
            }
            \u26032 = ((aci)\u26033).B;
        }
        int n2 = 1;
        \u26033 = lf2.bZ();
        if (\u26033 == null) {
            throw new bz("commands.enchant.noItem", new Object[0]);
        }
        aci \u26034 = aci.c(\u26032);
        if (\u26034 == null) {
            throw new cb("commands.enchant.notFound", \u26032);
        }
        if (!\u26034.a((zx)\u26033)) {
            throw new bz("commands.enchant.cantEnchant", new Object[0]);
        }
        if (stringArray.length >= 3) {
            n2 = ab.a(stringArray[2], \u26034.e(), \u26034.b());
        }
        if (((zx)\u26033).n() && (\u2603 = ((zx)\u26033).p()) != null) {
            for (\u2603 = 0; \u2603 < \u2603.c(); ++\u2603) {
                short s2 = \u2603.b(\u2603).e("id");
                if (aci.c(s2) == null || (\u2603 = aci.c(s2)).a(\u26034)) continue;
                throw new bz("commands.enchant.cantCombine", \u26034.d(n2), \u2603.d(\u2603.b(\u2603).e("lvl")));
            }
        }
        ((zx)\u26033).a(\u26034, n2);
        ab.a(m2, (k)this, "commands.enchant.success", new Object[0]);
        m2.a(n.a.d, 1);
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return ab.a(stringArray, this.d());
        }
        if (stringArray.length == 2) {
            return ab.a(stringArray, aci.c());
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

