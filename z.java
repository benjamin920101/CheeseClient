/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class z
extends i {
    @Override
    public String c() {
        return "effect";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.effect.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        int \u26032;
        if (stringArray.length < 2) {
            throw new cf("commands.effect.usage", new Object[0]);
        }
        pr pr2 = z.a(m2, stringArray[0], pr.class);
        if (stringArray[1].equals("clear")) {
            if (pr2.bl().isEmpty()) {
                throw new bz("commands.effect.failure.notActive.all", pr2.e_());
            }
            pr2.bk();
            z.a(m2, (k)this, "commands.effect.success.removed.all", pr2.e_());
            return;
        }
        try {
            \u26032 = z.a(stringArray[1], 1);
        }
        catch (cb cb2) {
            pe pe2 = pe.b(stringArray[1]);
            if (pe2 == null) {
                throw cb2;
            }
            \u26032 = pe2.H;
        }
        int n2 = 600;
        \u2603 = 30;
        \u2603 = 0;
        if (\u26032 < 0 || \u26032 >= pe.a.length || pe.a[\u26032] == null) {
            throw new cb("commands.effect.notFound", \u26032);
        }
        pe \u26033 = pe.a[\u26032];
        if (stringArray.length >= 3) {
            \u2603 = z.a(stringArray[2], 0, 1000000);
            n2 = \u26033.b() ? \u2603 : \u2603 * 20;
        } else if (\u26033.b()) {
            n2 = 1;
        }
        if (stringArray.length >= 4) {
            \u2603 = z.a(stringArray[3], 0, 255);
        }
        boolean \u26034 = true;
        if (stringArray.length >= 5 && "true".equalsIgnoreCase(stringArray[4])) {
            \u26034 = false;
        }
        if (\u2603 > 0) {
            pf pf2 = new pf(\u26032, n2, \u2603, false, \u26034);
            pr2.c(pf2);
            z.a(m2, (k)this, "commands.effect.success", new fb(pf2.g(), new Object[0]), \u26032, \u2603, pr2.e_(), \u2603);
            return;
        }
        if (!pr2.k(\u26032)) {
            throw new bz("commands.effect.failure.notActive", new fb(\u26033.a(), new Object[0]), pr2.e_());
        }
        pr2.m(\u26032);
        z.a(m2, (k)this, "commands.effect.success.removed", new fb(\u26033.a(), new Object[0]), pr2.e_());
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return z.a(stringArray, this.d());
        }
        if (stringArray.length == 2) {
            return z.a(stringArray, pe.c());
        }
        if (stringArray.length == 5) {
            return z.a(stringArray, "true", "false");
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

