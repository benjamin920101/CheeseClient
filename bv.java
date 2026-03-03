/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;
import net.minecraft.server.MinecraftServer;

public class bv
extends i {
    @Override
    public String c() {
        return "weather";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.weather.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length < 1 || stringArray.length > 2) {
            throw new cf("commands.weather.usage", new Object[0]);
        }
        int n2 = (300 + new Random().nextInt(600)) * 20;
        if (stringArray.length >= 2) {
            n2 = bv.a(stringArray[1], 1, 1000000) * 20;
        }
        le \u26032 = MinecraftServer.N().d[0];
        ato \u26033 = \u26032.P();
        if ("clear".equalsIgnoreCase(stringArray[0])) {
            \u26033.i(n2);
            \u26033.g(0);
            \u26033.f(0);
            \u26033.b(false);
            \u26033.a(false);
            bv.a(m2, (k)this, "commands.weather.clear", new Object[0]);
        } else if ("rain".equalsIgnoreCase(stringArray[0])) {
            \u26033.i(0);
            \u26033.g(n2);
            \u26033.f(n2);
            \u26033.b(true);
            \u26033.a(false);
            bv.a(m2, (k)this, "commands.weather.rain", new Object[0]);
        } else if ("thunder".equalsIgnoreCase(stringArray[0])) {
            \u26033.i(0);
            \u26033.g(n2);
            \u26033.f(n2);
            \u26033.b(true);
            \u26033.a(true);
            bv.a(m2, (k)this, "commands.weather.thunder", new Object[0]);
        } else {
            throw new cf("commands.weather.usage", new Object[0]);
        }
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return bv.a(stringArray, "clear", "rain", "thunder");
        }
        return null;
    }
}

