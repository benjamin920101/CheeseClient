/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bu
extends i {
    @Override
    public String c() {
        return "trigger";
    }

    @Override
    public int a() {
        return 0;
    }

    @Override
    public String c(m m2) {
        return "commands.trigger.usage";
    }

    @Override
    public void a(m m22, String[] stringArray2) throws bz {
        String[] stringArray2;
        Object \u26032;
        lf lf2;
        if (stringArray2.length < 3) {
            throw new cf("commands.trigger.usage", new Object[0]);
        }
        if (m22 instanceof lf) {
            lf2 = (lf)m22;
        } else {
            m m22;
            \u26032 = m22.f();
            if (\u26032 instanceof lf) {
                lf2 = (lf)\u26032;
            } else {
                throw new bz("commands.trigger.invalidPlayer", new Object[0]);
            }
        }
        \u26032 = MinecraftServer.N().a(0).Z();
        auk \u26033 = ((auo)\u26032).b(stringArray2[0]);
        if (\u26033 == null || \u26033.c() != auu.c) {
            throw new bz("commands.trigger.invalidObjective", stringArray2[0]);
        }
        int \u26034 = bu.a(stringArray2[2]);
        if (!((auo)\u26032).b(lf2.e_(), \u26033)) {
            throw new bz("commands.trigger.invalidObjective", stringArray2[0]);
        }
        aum \u26035 = ((auo)\u26032).c(lf2.e_(), \u26033);
        if (\u26035.g()) {
            throw new bz("commands.trigger.disabled", stringArray2[0]);
        }
        if ("set".equals(stringArray2[1])) {
            \u26035.c(\u26034);
        } else if ("add".equals(stringArray2[1])) {
            \u26035.a(\u26034);
        } else {
            throw new bz("commands.trigger.invalidMode", stringArray2[1]);
        }
        \u26035.a(true);
        if (lf2.c.d()) {
            bu.a(m22, (k)this, "commands.trigger.success", stringArray2[0], stringArray2[1], stringArray2[2]);
        }
    }

    @Override
    public List<String> a(m m2, String[] stringArray2, cj cj2) {
        String[] stringArray2;
        if (stringArray2.length == 1) {
            auo auo2 = MinecraftServer.N().a(0).Z();
            ArrayList<String> \u26032 = Lists.newArrayList();
            for (auk auk2 : auo2.c()) {
                if (auk2.c() != auu.c) continue;
                \u26032.add(auk2.b());
            }
            return bu.a(stringArray2, \u26032.toArray(new String[\u26032.size()]));
        }
        if (stringArray2.length == 2) {
            return bu.a(stringArray2, "add", "set");
        }
        return null;
    }
}

