/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class bt
extends i {
    @Override
    public String c() {
        return "toggledownfall";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.downfall.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        this.d();
        bt.a(m2, (k)this, "commands.downfall.success", new Object[0]);
    }

    protected void d() {
        ato ato2;
        ato2.b(!(ato2 = MinecraftServer.N().d[0].P()).p());
    }
}

