/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class ay
extends i {
    @Override
    public String c() {
        return "save-all";
    }

    @Override
    public String c(m m2) {
        return "commands.save.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        MinecraftServer minecraftServer = MinecraftServer.N();
        m2.a(new fb("commands.save.start", new Object[0]));
        if (minecraftServer.ap() != null) {
            minecraftServer.ap().j();
        }
        try {
            boolean \u26032;
            int \u26033;
            for (\u26033 = 0; \u26033 < minecraftServer.d.length; ++\u26033) {
                if (minecraftServer.d[\u26033] == null) continue;
                le le2 = minecraftServer.d[\u26033];
                \u26032 = le2.c;
                le2.c = false;
                le2.a(true, null);
                le2.c = \u26032;
            }
            if (stringArray.length > 0 && "flush".equals(stringArray[0])) {
                m2.a(new fb("commands.save.flushStart", new Object[0]));
                for (\u26033 = 0; \u26033 < minecraftServer.d.length; ++\u26033) {
                    if (minecraftServer.d[\u26033] == null) continue;
                    le2 = minecraftServer.d[\u26033];
                    \u26032 = le2.c;
                    le2.c = false;
                    le2.n();
                    le2.c = \u26032;
                }
                m2.a(new fb("commands.save.flushEnd", new Object[0]));
            }
        }
        catch (adn adn2) {
            ay.a(m2, (k)this, "commands.save.failed", adn2.getMessage());
            return;
        }
        ay.a(m2, (k)this, "commands.save.success", new Object[0]);
    }
}

