/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class bl
extends i {
    @Override
    public String c() {
        return "stop";
    }

    @Override
    public String c(m m2) {
        return "commands.stop.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (MinecraftServer.N().d != null) {
            bl.a(m2, (k)this, "commands.stop.start", new Object[0]);
        }
        MinecraftServer.N().w();
    }
}

