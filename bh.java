/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bh
extends i {
    @Override
    public String c() {
        return "spawnpoint";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.spawnpoint.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length > 1 && stringArray.length < 4) {
            throw new cf("commands.spawnpoint.usage", new Object[0]);
        }
        lf lf2 = stringArray.length > 0 ? bh.a(m2, stringArray[0]) : bh.b(m2);
        cj cj2 = \u2603 = stringArray.length > 3 ? bh.a(m2, stringArray, 1, true) : lf2.c();
        if (lf2.o != null) {
            lf2.a(\u2603, true);
            bh.a(m2, (k)this, "commands.spawnpoint.success", lf2.e_(), \u2603.n(), \u2603.o(), \u2603.p());
        }
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return bh.a(stringArray, MinecraftServer.N().K());
        }
        if (stringArray.length > 1 && stringArray.length <= 4) {
            return bh.a(stringArray, 1, cj2);
        }
        return null;
    }

    @Override
    public boolean b(String[] stringArray, int n2) {
        return n2 == 0;
    }
}

