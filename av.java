/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class av
extends i {
    @Override
    public String c() {
        return "playsound";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.playsound.usage";
    }

    @Override
    public void a(m m22, String[] stringArray) throws bz {
        m m22;
        if (stringArray.length < 2) {
            throw new cf(this.c(m22), new Object[0]);
        }
        int n2 = 0;
        String \u26032 = stringArray[n2++];
        lf \u26033 = av.a(m22, stringArray[n2++]);
        aui \u26034 = m22.d();
        double \u26035 = \u26034.a;
        if (stringArray.length > n2) {
            \u26035 = av.b(\u26035, stringArray[n2++], true);
        }
        double \u26036 = \u26034.b;
        if (stringArray.length > n2) {
            \u26036 = av.b(\u26036, stringArray[n2++], 0, 0, false);
        }
        double \u26037 = \u26034.c;
        if (stringArray.length > n2) {
            \u26037 = av.b(\u26037, stringArray[n2++], true);
        }
        double \u26038 = 1.0;
        if (stringArray.length > n2) {
            \u26038 = av.a(stringArray[n2++], 0.0, 3.4028234663852886E38);
        }
        double \u26039 = 1.0;
        if (stringArray.length > n2) {
            \u26039 = av.a(stringArray[n2++], 0.0, 2.0);
        }
        double \u260310 = 0.0;
        if (stringArray.length > n2) {
            \u260310 = av.a(stringArray[n2], 0.0, 1.0);
        }
        double \u260311 = \u26038 > 1.0 ? \u26038 * 16.0 : 16.0;
        double \u260312 = \u26033.f(\u26035, \u26036, \u26037);
        if (\u260312 > \u260311) {
            if (\u260310 <= 0.0) {
                throw new bz("commands.playsound.playerTooFar", \u26033.e_());
            }
            double d2 = \u26035 - \u26033.s;
            \u2603 = \u26036 - \u26033.t;
            \u2603 = \u26037 - \u26033.u;
            \u2603 = Math.sqrt(d2 * d2 + \u2603 * \u2603 + \u2603 * \u2603);
            if (\u2603 > 0.0) {
                \u26035 = \u26033.s + d2 / \u2603 * 2.0;
                \u26036 = \u26033.t + \u2603 / \u2603 * 2.0;
                \u26037 = \u26033.u + \u2603 / \u2603 * 2.0;
            }
            \u26038 = \u260310;
        }
        \u26033.a.a(new gs(\u26032, \u26035, \u26036, \u26037, (float)\u26038, (float)\u26039));
        av.a(m22, (k)this, "commands.playsound.success", \u26032, \u26033.e_());
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 2) {
            return av.a(stringArray, MinecraftServer.N().K());
        }
        if (stringArray.length > 2 && stringArray.length <= 5) {
            return av.a(stringArray, 2, cj2);
        }
        return null;
    }

    @Override
    public boolean b(String[] stringArray, int n2) {
        return n2 == 1;
    }
}

