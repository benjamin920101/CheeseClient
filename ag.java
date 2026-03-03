/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ag
extends i {
    @Override
    public String c() {
        return "difficulty";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.difficulty.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length <= 0) {
            throw new cf("commands.difficulty.usage", new Object[0]);
        }
        oj oj2 = this.e(stringArray[0]);
        MinecraftServer.N().a(oj2);
        ag.a(m2, (k)this, "commands.difficulty.success", new fb(oj2.b(), new Object[0]));
    }

    protected oj e(String string) throws cb {
        if (string.equalsIgnoreCase("peaceful") || string.equalsIgnoreCase("p")) {
            return oj.a;
        }
        if (string.equalsIgnoreCase("easy") || string.equalsIgnoreCase("e")) {
            return oj.b;
        }
        if (string.equalsIgnoreCase("normal") || string.equalsIgnoreCase("n")) {
            return oj.c;
        }
        if (string.equalsIgnoreCase("hard") || string.equalsIgnoreCase("h")) {
            return oj.d;
        }
        return oj.a(ag.a(string, 0, 3));
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return ag.a(stringArray, "peaceful", "easy", "normal", "hard");
        }
        return null;
    }
}

