/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class aa
extends i {
    @Override
    public String c() {
        return "me";
    }

    @Override
    public int a() {
        return 0;
    }

    @Override
    public String c(m m2) {
        return "commands.me.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length <= 0) {
            throw new cf("commands.me.usage", new Object[0]);
        }
        eu eu2 = aa.b(m2, stringArray, 0, !(m2 instanceof wn));
        MinecraftServer.N().ap().a(new fb("chat.type.emote", m2.f_(), eu2));
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        return aa.a(stringArray, MinecraftServer.N().K());
    }
}

