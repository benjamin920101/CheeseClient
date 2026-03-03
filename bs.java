/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonParseException;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bs
extends i {
    private static final Logger a = LogManager.getLogger();

    @Override
    public String c() {
        return "title";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.title.usage";
    }

    @Override
    public void a(m m22, String[] stringArray2) throws bz {
        m m22;
        if (stringArray2.length < 2) {
            throw new cf("commands.title.usage", new Object[0]);
        }
        if (stringArray2.length < 3) {
            if ("title".equals(stringArray2[1]) || "subtitle".equals(stringArray2[1])) {
                throw new cf("commands.title.usage.title", new Object[0]);
            }
            if ("times".equals(stringArray2[1])) {
                throw new cf("commands.title.usage.times", new Object[0]);
            }
        }
        lf lf2 = bs.a(m22, stringArray2[0]);
        hv.a \u26032 = hv.a.a(stringArray2[1]);
        if (\u26032 == hv.a.d || \u26032 == hv.a.e) {
            if (stringArray2.length != 2) {
                throw new cf("commands.title.usage", new Object[0]);
            }
            hv hv2 = new hv(\u26032, null);
            lf2.a.a(hv2);
            bs.a(m22, (k)this, "commands.title.success", new Object[0]);
            return;
        }
        if (\u26032 == hv.a.c) {
            String[] stringArray2;
            if (stringArray2.length != 5) {
                throw new cf("commands.title.usage", new Object[0]);
            }
            int \u26033 = bs.a(stringArray2[2]);
            int \u26034 = bs.a(stringArray2[3]);
            int \u26035 = bs.a(stringArray2[4]);
            hv \u26036 = new hv(\u26033, \u26034, \u26035);
            lf2.a.a(\u26036);
            bs.a(m22, (k)this, "commands.title.success", new Object[0]);
            return;
        }
        if (stringArray2.length < 3) {
            throw new cf("commands.title.usage", new Object[0]);
        }
        String string = bs.a(stringArray2, 2);
        try {
            eu eu2 = eu.a.a(string);
        }
        catch (JsonParseException jsonParseException) {
            Throwable throwable = ExceptionUtils.getRootCause(jsonParseException);
            throw new cc("commands.tellraw.jsonException", throwable == null ? "" : throwable.getMessage());
        }
        hv \u26037 = new hv(\u26032, ev.a(m22, eu2, lf2));
        lf2.a.a(\u26037);
        bs.a(m22, (k)this, "commands.title.success", new Object[0]);
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return bs.a(stringArray, MinecraftServer.N().K());
        }
        if (stringArray.length == 2) {
            return bs.a(stringArray, hv.a.a());
        }
        return null;
    }

    @Override
    public boolean b(String[] stringArray, int n2) {
        return n2 == 0;
    }
}

