/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.regex.Matcher;
import net.minecraft.server.MinecraftServer;

public class as
extends i {
    @Override
    public String c() {
        return "pardon-ip";
    }

    @Override
    public int a() {
        return 3;
    }

    @Override
    public boolean a(m m2) {
        return MinecraftServer.N().ap().i().b() && super.a(m2);
    }

    @Override
    public String c(m m2) {
        return "commands.unbanip.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length != 1 || stringArray[0].length() <= 1) {
            throw new cf("commands.unbanip.usage", new Object[0]);
        }
        Matcher matcher = q.a.matcher(stringArray[0]);
        if (!matcher.matches()) {
            throw new cc("commands.unbanip.invalid", new Object[0]);
        }
        MinecraftServer.N().ap().i().c(stringArray[0]);
        as.a(m2, (k)this, "commands.unbanip.success", stringArray[0]);
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return as.a(stringArray, MinecraftServer.N().ap().i().a());
        }
        return null;
    }
}

