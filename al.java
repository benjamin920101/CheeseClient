/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class al
extends i {
    @Override
    public String c() {
        return "kick";
    }

    @Override
    public int a() {
        return 3;
    }

    @Override
    public String c(m m2) {
        return "commands.kick.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length <= 0 || stringArray[0].length() <= 1) {
            throw new cf("commands.kick.usage", new Object[0]);
        }
        lf lf2 = MinecraftServer.N().ap().a(stringArray[0]);
        String \u26032 = "Kicked by an operator.";
        boolean \u26033 = false;
        if (lf2 == null) {
            throw new cd();
        }
        if (stringArray.length >= 2) {
            \u26032 = al.a(m2, stringArray, 1).c();
            \u26033 = true;
        }
        lf2.a.c(\u26032);
        if (\u26033) {
            al.a(m2, (k)this, "commands.kick.success.reason", lf2.e_(), \u26032);
        } else {
            al.a(m2, (k)this, "commands.kick.success", lf2.e_());
        }
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length >= 1) {
            return al.a(stringArray, MinecraftServer.N().K());
        }
        return null;
    }
}

