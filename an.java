/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class an
extends i {
    @Override
    public String c() {
        return "banlist";
    }

    @Override
    public int a() {
        return 3;
    }

    @Override
    public boolean a(m m2) {
        return (MinecraftServer.N().ap().i().b() || MinecraftServer.N().ap().h().b()) && super.a(m2);
    }

    @Override
    public String c(m m2) {
        return "commands.banlist.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length >= 1 && stringArray[0].equalsIgnoreCase("ips")) {
            m2.a(new fb("commands.banlist.ips", MinecraftServer.N().ap().i().a().length));
            m2.a(new fa(an.a(MinecraftServer.N().ap().i().a())));
        } else {
            m2.a(new fb("commands.banlist.players", MinecraftServer.N().ap().h().a().length));
            m2.a(new fa(an.a(MinecraftServer.N().ap().h().a())));
        }
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return an.a(stringArray, "players", "ips");
        }
        return null;
    }
}

