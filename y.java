/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class y
extends ah {
    @Override
    public String c() {
        return "defaultgamemode";
    }

    @Override
    public String c(m m2) {
        return "commands.defaultgamemode.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length <= 0) {
            throw new cf("commands.defaultgamemode.usage", new Object[0]);
        }
        adp.a a2 = this.h(m2, stringArray[0]);
        this.a(a2);
        y.a(m2, (k)this, "commands.defaultgamemode.success", new fb("gameMode." + a2.b(), new Object[0]));
    }

    protected void a(adp.a a2) {
        MinecraftServer minecraftServer = MinecraftServer.N();
        minecraftServer.a(a2);
        if (minecraftServer.ax()) {
            for (lf lf2 : MinecraftServer.N().ap().v()) {
                lf2.a(a2);
                lf2.O = 0.0f;
            }
        }
    }
}

