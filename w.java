/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class w
extends i {
    @Override
    public String c() {
        return "deop";
    }

    @Override
    public int a() {
        return 3;
    }

    @Override
    public String c(m m2) {
        return "commands.deop.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length != 1 || stringArray[0].length() <= 0) {
            throw new cf("commands.deop.usage", new Object[0]);
        }
        MinecraftServer minecraftServer = MinecraftServer.N();
        GameProfile \u26032 = minecraftServer.ap().m().a(stringArray[0]);
        if (\u26032 == null) {
            throw new bz("commands.deop.failed", stringArray[0]);
        }
        minecraftServer.ap().b(\u26032);
        w.a(m2, (k)this, "commands.deop.success", stringArray[0]);
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return w.a(stringArray, MinecraftServer.N().ap().n());
        }
        return null;
    }
}

