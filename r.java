/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class r
extends i {
    @Override
    public String c() {
        return "ban";
    }

    @Override
    public int a() {
        return 3;
    }

    @Override
    public String c(m m2) {
        return "commands.ban.usage";
    }

    @Override
    public boolean a(m m2) {
        return MinecraftServer.N().ap().h().b() && super.a(m2);
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length < 1 || stringArray[0].length() <= 0) {
            throw new cf("commands.ban.usage", new Object[0]);
        }
        MinecraftServer minecraftServer = MinecraftServer.N();
        GameProfile \u26032 = minecraftServer.aF().a(stringArray[0]);
        if (\u26032 == null) {
            throw new bz("commands.ban.failed", stringArray[0]);
        }
        String \u26033 = null;
        if (stringArray.length >= 2) {
            \u26033 = r.a(m2, stringArray, 1).c();
        }
        md \u26034 = new md(\u26032, null, m2.e_(), null, \u26033);
        minecraftServer.ap().h().a(\u26034);
        lf \u26035 = minecraftServer.ap().a(stringArray[0]);
        if (\u26035 != null) {
            \u26035.a.c("You are banned from this server.");
        }
        r.a(m2, (k)this, "commands.ban.success", stringArray[0]);
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length >= 1) {
            return r.a(stringArray, MinecraftServer.N().K());
        }
        return null;
    }
}

