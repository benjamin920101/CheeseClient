/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bb
extends i {
    @Override
    public String c() {
        return "say";
    }

    @Override
    public int a() {
        return 1;
    }

    @Override
    public String c(m m2) {
        return "commands.say.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length <= 0 || stringArray[0].length() <= 0) {
            throw new cf("commands.say.usage", new Object[0]);
        }
        eu eu2 = bb.b(m2, stringArray, 0, true);
        MinecraftServer.N().ap().a(new fb("chat.type.announcement", m2.f_(), eu2));
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length >= 1) {
            return bb.a(stringArray, MinecraftServer.N().K());
        }
        return null;
    }
}

