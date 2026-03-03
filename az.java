/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class az
extends i {
    @Override
    public String c() {
        return "save-off";
    }

    @Override
    public String c(m m2) {
        return "commands.save-off.usage";
    }

    @Override
    public void a(m m22, String[] stringArray) throws bz {
        m m22;
        MinecraftServer minecraftServer = MinecraftServer.N();
        boolean \u26032 = false;
        for (int i2 = 0; i2 < minecraftServer.d.length; ++i2) {
            if (minecraftServer.d[i2] == null) continue;
            le le2 = minecraftServer.d[i2];
            if (le2.c) continue;
            le2.c = true;
            \u26032 = true;
        }
        if (!\u26032) {
            throw new bz("commands.save-off.alreadyOff", new Object[0]);
        }
        az.a(m22, (k)this, "commands.save.disabled", new Object[0]);
    }
}

