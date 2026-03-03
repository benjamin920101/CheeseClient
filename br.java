/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class br
extends i {
    @Override
    public String c() {
        return "time";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.time.usage";
    }

    @Override
    public void a(m m2, String[] stringArray2) throws bz {
        if (stringArray2.length > 1) {
            String[] stringArray2;
            if (stringArray2[0].equals("set")) {
                int n2 = stringArray2[1].equals("day") ? 1000 : (stringArray2[1].equals("night") ? 13000 : br.a(stringArray2[1], 0));
                this.a(m2, n2);
                br.a(m2, (k)this, "commands.time.set", n2);
                return;
            }
            if (stringArray2[0].equals("add")) {
                int n3 = br.a(stringArray2[1], 0);
                this.b(m2, n3);
                br.a(m2, (k)this, "commands.time.added", n3);
                return;
            }
            if (stringArray2[0].equals("query")) {
                if (stringArray2[1].equals("daytime")) {
                    int n4 = (int)(m2.e().L() % Integer.MAX_VALUE);
                    m2.a(n.a.e, n4);
                    br.a(m2, (k)this, "commands.time.query", n4);
                    return;
                }
                if (stringArray2[1].equals("gametime")) {
                    int n5 = (int)(m2.e().K() % Integer.MAX_VALUE);
                    m2.a(n.a.e, n5);
                    br.a(m2, (k)this, "commands.time.query", n5);
                    return;
                }
            }
        }
        throw new cf("commands.time.usage", new Object[0]);
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return br.a(stringArray, "set", "add", "query");
        }
        if (stringArray.length == 2 && stringArray[0].equals("set")) {
            return br.a(stringArray, "day", "night");
        }
        if (stringArray.length == 2 && stringArray[0].equals("query")) {
            return br.a(stringArray, "daytime", "gametime");
        }
        return null;
    }

    protected void a(m m2, int n2) {
        for (\u2603 = 0; \u2603 < MinecraftServer.N().d.length; ++\u2603) {
            MinecraftServer.N().d[\u2603].b((long)n2);
        }
    }

    protected void b(m m2, int n2) {
        for (\u2603 = 0; \u2603 < MinecraftServer.N().d.length; ++\u2603) {
            le le2 = MinecraftServer.N().d[\u2603];
            le2.b(le2.L() + (long)n2);
        }
    }
}

