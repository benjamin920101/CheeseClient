/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ai
extends i {
    @Override
    public String c() {
        return "gamerule";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.gamerule.usage";
    }

    @Override
    public void a(m m22, String[] stringArray) throws bz {
        adk adk2 = this.d();
        String \u26032 = stringArray.length > 0 ? stringArray[0] : "";
        String \u26033 = stringArray.length > 1 ? ai.a(stringArray, 1) : "";
        switch (stringArray.length) {
            case 1: {
                if (adk2.e(\u26032)) {
                    String string = adk2.a(\u26032);
                    m22.a(new fa(\u26032).a(" = ").a(string));
                    m22.a(n.a.e, adk2.c(\u26032));
                    break;
                }
                throw new bz("commands.gamerule.norule", \u26032);
            }
            case 0: {
                m m22;
                m22.a(new fa(ai.a(adk2.b())));
                break;
            }
            default: {
                if (adk2.a(\u26032, adk.b.b) && !"true".equals(\u26033) && !"false".equals(\u26033)) {
                    throw new bz("commands.generic.boolean.invalid", \u26033);
                }
                adk2.a(\u26032, \u26033);
                ai.a(adk2, \u26032);
                ai.a(m22, (k)this, "commands.gamerule.success", new Object[0]);
            }
        }
    }

    public static void a(adk adk2, String string) {
        if ("reducedDebugInfo".equals(string)) {
            byte by = adk2.b(string) ? (byte)22 : 23;
            for (lf lf2 : MinecraftServer.N().ap().v()) {
                lf2.a.a(new gi(lf2, by));
            }
        }
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return ai.a(stringArray, this.d().b());
        }
        if (stringArray.length == 2 && (\u2603 = this.d()).a(stringArray[0], adk.b.b)) {
            return ai.a(stringArray, "true", "false");
        }
        return null;
    }

    private adk d() {
        return MinecraftServer.N().a(0).Q();
    }
}

