/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ah
extends i {
    @Override
    public String c() {
        return "gamemode";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.gamemode.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length <= 0) {
            throw new cf("commands.gamemode.usage", new Object[0]);
        }
        adp.a a2 = this.h(m2, stringArray[0]);
        lf \u26032 = stringArray.length >= 2 ? ah.a(m2, stringArray[1]) : ah.b(m2);
        ((wn)\u26032).a(a2);
        \u26032.O = 0.0f;
        if (m2.e().Q().b("sendCommandFeedback")) {
            ((pk)\u26032).a(new fb("gameMode.changed", new Object[0]));
        }
        fb \u26033 = new fb("gameMode." + a2.b(), new Object[0]);
        if (\u26032 != m2) {
            ah.a(m2, (k)this, 1, "commands.gamemode.success.other", \u26032.e_(), \u26033);
        } else {
            ah.a(m2, (k)this, 1, "commands.gamemode.success.self", \u26033);
        }
    }

    protected adp.a h(m m2, String string) throws cb {
        if (string.equalsIgnoreCase(adp.a.b.b()) || string.equalsIgnoreCase("s")) {
            return adp.a.b;
        }
        if (string.equalsIgnoreCase(adp.a.c.b()) || string.equalsIgnoreCase("c")) {
            return adp.a.c;
        }
        if (string.equalsIgnoreCase(adp.a.d.b()) || string.equalsIgnoreCase("a")) {
            return adp.a.d;
        }
        if (string.equalsIgnoreCase(adp.a.e.b()) || string.equalsIgnoreCase("sp")) {
            return adp.a.e;
        }
        return adp.a(ah.a(string, 0, adp.a.values().length - 2));
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return ah.a(stringArray, "survival", "creative", "adventure", "spectator");
        }
        if (stringArray.length == 2) {
            return ah.a(stringArray, this.d());
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

