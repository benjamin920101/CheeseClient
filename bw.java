/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bw
extends i {
    @Override
    public String c() {
        return "whitelist";
    }

    @Override
    public int a() {
        return 3;
    }

    @Override
    public String c(m m2) {
        return "commands.whitelist.usage";
    }

    @Override
    public void a(m m2, String[] stringArray2) throws bz {
        String[] stringArray2;
        if (stringArray2.length < 1) {
            throw new cf("commands.whitelist.usage", new Object[0]);
        }
        MinecraftServer minecraftServer = MinecraftServer.N();
        if (stringArray2[0].equals("on")) {
            minecraftServer.ap().a(true);
            bw.a(m2, (k)this, "commands.whitelist.enabled", new Object[0]);
        } else if (stringArray2[0].equals("off")) {
            minecraftServer.ap().a(false);
            bw.a(m2, (k)this, "commands.whitelist.disabled", new Object[0]);
        } else if (stringArray2[0].equals("list")) {
            m2.a(new fb("commands.whitelist.list", minecraftServer.ap().l().length, minecraftServer.ap().q().length));
            Object[] objectArray = minecraftServer.ap().l();
            m2.a(new fa(bw.a(objectArray)));
        } else if (stringArray2[0].equals("add")) {
            if (stringArray2.length < 2) {
                throw new cf("commands.whitelist.add.usage", new Object[0]);
            }
            GameProfile gameProfile = minecraftServer.aF().a(stringArray2[1]);
            if (gameProfile == null) {
                throw new bz("commands.whitelist.add.failed", stringArray2[1]);
            }
            minecraftServer.ap().d(gameProfile);
            bw.a(m2, (k)this, "commands.whitelist.add.success", stringArray2[1]);
        } else if (stringArray2[0].equals("remove")) {
            if (stringArray2.length < 2) {
                throw new cf("commands.whitelist.remove.usage", new Object[0]);
            }
            GameProfile gameProfile = minecraftServer.ap().k().a(stringArray2[1]);
            if (gameProfile == null) {
                throw new bz("commands.whitelist.remove.failed", stringArray2[1]);
            }
            minecraftServer.ap().c(gameProfile);
            bw.a(m2, (k)this, "commands.whitelist.remove.success", stringArray2[1]);
        } else if (stringArray2[0].equals("reload")) {
            minecraftServer.ap().a();
            bw.a(m2, (k)this, "commands.whitelist.reloaded", new Object[0]);
        }
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return bw.a(stringArray, "on", "off", "list", "add", "remove", "reload");
        }
        if (stringArray.length == 2) {
            if (stringArray[0].equals("remove")) {
                return bw.a(stringArray, MinecraftServer.N().ap().l());
            }
            if (stringArray[0].equals("add")) {
                return bw.a(stringArray, MinecraftServer.N().aF().a());
            }
        }
        return null;
    }
}

