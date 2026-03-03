/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class aw
extends i {
    @Override
    public String c() {
        return "publish";
    }

    @Override
    public String c(m m2) {
        return "commands.publish.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        String string = MinecraftServer.N().a(adp.a.b, false);
        if (string != null) {
            aw.a(m2, (k)this, "commands.publish.started", string);
        } else {
            aw.a(m2, (k)this, "commands.publish.failed", new Object[0]);
        }
    }
}

