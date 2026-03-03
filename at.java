/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class at
extends i {
    @Override
    public String c() {
        return "pardon";
    }

    @Override
    public int a() {
        return 3;
    }

    @Override
    public String c(m m2) {
        return "commands.unban.usage";
    }

    @Override
    public boolean a(m m2) {
        return MinecraftServer.N().ap().h().b() && super.a(m2);
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length != 1 || stringArray[0].length() <= 0) {
            throw new cf("commands.unban.usage", new Object[0]);
        }
        MinecraftServer minecraftServer = MinecraftServer.N();
        GameProfile \u26032 = minecraftServer.ap().h().a(stringArray[0]);
        if (\u26032 == null) {
            throw new bz("commands.unban.failed", stringArray[0]);
        }
        minecraftServer.ap().h().c(\u26032);
        at.a(m2, (k)this, "commands.unban.success", stringArray[0]);
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return at.a(stringArray, MinecraftServer.N().ap().h().a());
        }
        return null;
    }
}

