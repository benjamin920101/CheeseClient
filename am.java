/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class am
extends i {
    @Override
    public String c() {
        return "kill";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.kill.usage";
    }

    @Override
    public void a(m m22, String[] stringArray) throws bz {
        m m22;
        if (stringArray.length == 0) {
            lf lf2 = am.b(m22);
            lf2.G();
            am.a(m22, (k)this, "commands.kill.successful", lf2.f_());
            return;
        }
        pk \u26032 = am.b(m22, stringArray[0]);
        \u26032.G();
        am.a(m22, (k)this, "commands.kill.successful", \u26032.f_());
    }

    @Override
    public boolean b(String[] stringArray, int n2) {
        return n2 == 0;
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return am.a(stringArray, MinecraftServer.N().K());
        }
        return null;
    }
}

