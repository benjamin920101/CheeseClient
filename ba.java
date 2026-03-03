/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class ba
extends i {
    @Override
    public String c() {
        return "save-on";
    }

    @Override
    public String c(m m2) {
        return "commands.save-on.usage";
    }

    @Override
    public void a(m m22, String[] stringArray) throws bz {
        m m22;
        MinecraftServer minecraftServer = MinecraftServer.N();
        boolean \u26032 = false;
        for (int i2 = 0; i2 < minecraftServer.d.length; ++i2) {
            if (minecraftServer.d[i2] == null) continue;
            le le2 = minecraftServer.d[i2];
            if (!le2.c) continue;
            le2.c = false;
            \u26032 = true;
        }
        if (!\u26032) {
            throw new bz("commands.save-on.alreadyOn", new Object[0]);
        }
        ba.a(m22, (k)this, "commands.save.enabled", new Object[0]);
    }
}

