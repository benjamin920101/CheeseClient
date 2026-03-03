/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonParseException;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class aq
extends i {
    @Override
    public String c() {
        return "tellraw";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.tellraw.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length < 2) {
            throw new cf("commands.tellraw.usage", new Object[0]);
        }
        lf lf2 = aq.a(m2, stringArray[0]);
        String \u26032 = aq.a(stringArray, 1);
        try {
            eu eu2 = eu.a.a(\u26032);
            ((pk)lf2).a(ev.a(m2, eu2, lf2));
        }
        catch (JsonParseException jsonParseException) {
            Throwable throwable = ExceptionUtils.getRootCause(jsonParseException);
            throw new cc("commands.tellraw.jsonException", throwable == null ? "" : throwable.getMessage());
        }
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return aq.a(stringArray, MinecraftServer.N().K());
        }
        return null;
    }

    @Override
    public boolean b(String[] stringArray, int n2) {
        return n2 == 0;
    }
}

