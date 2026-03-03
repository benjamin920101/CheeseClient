/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ar
extends i {
    @Override
    public String c() {
        return "op";
    }

    @Override
    public int a() {
        return 3;
    }

    @Override
    public String c(m m2) {
        return "commands.op.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length != 1 || stringArray[0].length() <= 0) {
            throw new cf("commands.op.usage", new Object[0]);
        }
        MinecraftServer minecraftServer = MinecraftServer.N();
        GameProfile \u26032 = minecraftServer.aF().a(stringArray[0]);
        if (\u26032 == null) {
            throw new bz("commands.op.failed", stringArray[0]);
        }
        minecraftServer.ap().a(\u26032);
        ar.a(m2, (k)this, "commands.op.success", stringArray[0]);
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            String string = stringArray[stringArray.length - 1];
            ArrayList<String> \u26032 = Lists.newArrayList();
            for (GameProfile gameProfile : MinecraftServer.N().L()) {
                if (MinecraftServer.N().ap().h(gameProfile) || !ar.a(string, gameProfile.getName())) continue;
                \u26032.add(gameProfile.getName());
            }
            return \u26032;
        }
        return null;
    }
}

