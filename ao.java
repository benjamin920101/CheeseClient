/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class ao
extends i {
    @Override
    public String c() {
        return "list";
    }

    @Override
    public int a() {
        return 0;
    }

    @Override
    public String c(m m2) {
        return "commands.players.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        int n2 = MinecraftServer.N().I();
        m2.a(new fb("commands.players.list", n2, MinecraftServer.N().J()));
        m2.a(new fa(MinecraftServer.N().ap().b(stringArray.length > 0 && "uuids".equalsIgnoreCase(stringArray[0]))));
        m2.a(n.a.e, n2);
    }
}

