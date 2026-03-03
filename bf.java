/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bf
extends i {
    @Override
    public String c() {
        return "setworldspawn";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.setworldspawn.usage";
    }

    @Override
    public void a(m m22, String[] stringArray2) throws bz {
        m m22;
        String[] stringArray2;
        cj cj2;
        if (stringArray2.length == 0) {
            cj2 = bf.b(m22).c();
        } else if (stringArray2.length == 3 && m22.e() != null) {
            cj2 = bf.a(m22, stringArray2, 0, true);
        } else {
            throw new cf("commands.setworldspawn.usage", new Object[0]);
        }
        m22.e().B(cj2);
        MinecraftServer.N().ap().a(new ht(cj2));
        bf.a(m22, (k)this, "commands.setworldspawn.success", cj2.n(), cj2.o(), cj2.p());
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length > 0 && stringArray.length <= 3) {
            return bf.a(stringArray, 0, cj2);
        }
        return null;
    }
}

