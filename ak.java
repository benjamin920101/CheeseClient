/*
 * Decompiled with CFR 0.152.
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class ak
extends i {
    @Override
    public String c() {
        return "help";
    }

    @Override
    public int a() {
        return 0;
    }

    @Override
    public String c(m m2) {
        return "commands.help.usage";
    }

    @Override
    public List<String> b() {
        return Arrays.asList("?");
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        List<k> list = this.d(m2);
        int \u26032 = 7;
        int \u26033 = (list.size() - 1) / 7;
        int \u26034 = 0;
        try {
            \u26034 = stringArray.length == 0 ? 0 : ak.a(stringArray[0], 1, \u26033 + 1) - 1;
        }
        catch (cb \u26035) {
            Map<String, k> map = this.d();
            k \u26036 = map.get(stringArray[0]);
            if (\u26036 != null) {
                throw new cf(\u26036.c(m2), new Object[0]);
            }
            if (ns.a(stringArray[0], -1) != -1) {
                throw \u26035;
            }
            throw new ce();
        }
        int n2 = Math.min((\u26034 + 1) * 7, list.size());
        fb \u26037 = new fb("commands.help.header", \u26034 + 1, \u26033 + 1);
        \u26037.b().a(a.c);
        m2.a(\u26037);
        for (\u2603 = \u26034 * 7; \u2603 < n2; ++\u2603) {
            k k2 = list.get(\u2603);
            fb \u26038 = new fb(k2.c(m2), new Object[0]);
            \u26038.b().a(new et(et.a.e, "/" + k2.c() + " "));
            m2.a(\u26038);
        }
        if (\u26034 == 0 && m2 instanceof wn) {
            fb fb2 = new fb("commands.help.footer", new Object[0]);
            fb2.b().a(a.k);
            m2.a(fb2);
        }
    }

    protected List<k> d(m m2) {
        List<k> list = MinecraftServer.N().P().a(m2);
        Collections.sort(list);
        return list;
    }

    protected Map<String, k> d() {
        return MinecraftServer.N().P().a();
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            Set<String> set = this.d().keySet();
            return ak.a(stringArray, set.toArray(new String[set.size()]));
        }
        return null;
    }
}

