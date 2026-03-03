/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class bg
extends i {
    @Override
    public String c() {
        return "setidletimeout";
    }

    @Override
    public int a() {
        return 3;
    }

    @Override
    public String c(m m2) {
        return "commands.setidletimeout.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length != 1) {
            throw new cf("commands.setidletimeout.usage", new Object[0]);
        }
        int n2 = bg.a(stringArray[0], 0);
        MinecraftServer.N().d(n2);
        bg.a(m2, (k)this, "commands.setidletimeout.success", n2);
    }
}

