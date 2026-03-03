/*
 * Decompiled with CFR 0.152.
 */
import java.util.Arrays;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ap
extends i {
    @Override
    public List<String> b() {
        return Arrays.asList("w", "msg");
    }

    @Override
    public String c() {
        return "tell";
    }

    @Override
    public int a() {
        return 0;
    }

    @Override
    public String c(m m2) {
        return "commands.message.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length < 2) {
            throw new cf("commands.message.usage", new Object[0]);
        }
        lf lf2 = ap.a(m2, stringArray[0]);
        if (lf2 == m2) {
            throw new cd("commands.message.sameTarget", new Object[0]);
        }
        eu \u26032 = ap.b(m2, stringArray, 1, !(m2 instanceof wn));
        fb \u26033 = new fb("commands.message.display.incoming", m2.f_(), \u26032.f());
        fb \u26034 = new fb("commands.message.display.outgoing", lf2.f_(), \u26032.f());
        \u26033.b().a(a.h).b(true);
        \u26034.b().a(a.h).b(true);
        ((pk)lf2).a(\u26033);
        m2.a(\u26034);
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        return ap.a(stringArray, MinecraftServer.N().K());
    }

    @Override
    public boolean b(String[] stringArray, int n2) {
        return n2 == 0;
    }
}

