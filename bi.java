/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class bi
extends i {
    @Override
    public boolean a(m m2) {
        return MinecraftServer.N().T() || super.a(m2);
    }

    @Override
    public String c() {
        return "seed";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.seed.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        adm adm2 = m2 instanceof wn ? ((wn)m2).o : MinecraftServer.N().a(0);
        m2.a(new fb("commands.seed.success", adm2.J()));
    }
}

