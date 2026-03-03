/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.server.MinecraftServer;

public class q
extends i {
    public static final Pattern a = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    @Override
    public String c() {
        return "ban-ip";
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
        return "commands.banip.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length < 1 || stringArray[0].length() <= 1) {
            throw new cf("commands.banip.usage", new Object[0]);
        }
        eu eu2 = stringArray.length >= 2 ? q.a(m2, stringArray, 1) : null;
        Matcher \u26032 = a.matcher(stringArray[0]);
        if (\u26032.matches()) {
            this.a(m2, stringArray[0], eu2 == null ? null : eu2.c());
        } else {
            lf lf2 = MinecraftServer.N().ap().a(stringArray[0]);
            if (lf2 == null) {
                throw new cd("commands.banip.invalid", new Object[0]);
            }
            this.a(m2, lf2.w(), eu2 == null ? null : eu2.c());
        }
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return q.a(stringArray, MinecraftServer.N().K());
        }
        return null;
    }

    protected void a(m m22, String string, String string2) {
        m m22;
        lv lv2 = new lv(string, null, m22.e_(), null, string2);
        MinecraftServer.N().ap().i().a(lv2);
        List<lf> \u26032 = MinecraftServer.N().ap().b(string);
        Object[] \u26033 = new String[\u26032.size()];
        int \u26034 = 0;
        for (lf lf2 : \u26032) {
            lf2.a.c("You have been IP banned.");
            \u26033[\u26034++] = lf2.e_();
        }
        if (\u26032.isEmpty()) {
            q.a(m22, (k)this, "commands.banip.success", string);
        } else {
            q.a(m22, (k)this, "commands.banip.success.players", string, q.a(\u26033));
        }
    }
}

