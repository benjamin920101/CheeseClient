/*
 * Decompiled with CFR 0.152.
 */
import java.util.EnumSet;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bn
extends i {
    @Override
    public String c() {
        return "tp";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.tp.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length < 1) {
            throw new cf("commands.tp.usage", new Object[0]);
        }
        int \u26032 = 0;
        if (stringArray.length == 2 || stringArray.length == 4 || stringArray.length == 6) {
            pk pk2 = bn.b(m2, stringArray[0]);
            \u26032 = 1;
        } else {
            pk2 = bn.b(m2);
        }
        if (stringArray.length == 1 || stringArray.length == 2) {
            \u2603 = bn.b(m2, stringArray[stringArray.length - 1]);
            if (\u2603.o != pk2.o) {
                throw new bz("commands.tp.notSameDimension", new Object[0]);
            }
            pk2.a((pk)null);
            if (pk2 instanceof lf) {
                ((lf)pk2).a.a(\u2603.s, \u2603.t, \u2603.u, \u2603.y, \u2603.z);
            } else {
                pk2.b(\u2603.s, \u2603.t, \u2603.u, \u2603.y, \u2603.z);
            }
            bn.a(m2, (k)this, "commands.tp.success", pk2.e_(), \u2603.e_());
            return;
        }
        if (stringArray.length < \u26032 + 3) {
            throw new cf("commands.tp.usage", new Object[0]);
        }
        if (pk2.o == null) {
            return;
        }
        int n2 = \u26032;
        i.a \u26033 = bn.a(pk2.s, stringArray[n2++], true);
        i.a \u26034 = bn.a(pk2.t, stringArray[n2++], 0, 0, false);
        i.a \u26035 = bn.a(pk2.u, stringArray[n2++], true);
        i.a \u26036 = bn.a(pk2.y, stringArray.length > n2 ? stringArray[n2++] : "~", false);
        i.a \u26037 = bn.a(pk2.z, stringArray.length > n2 ? stringArray[n2] : "~", false);
        if (pk2 instanceof lf) {
            EnumSet<fi.a> enumSet = EnumSet.noneOf(fi.a.class);
            if (\u26033.c()) {
                enumSet.add(fi.a.a);
            }
            if (\u26034.c()) {
                enumSet.add(fi.a.b);
            }
            if (\u26035.c()) {
                enumSet.add(fi.a.c);
            }
            if (\u26037.c()) {
                enumSet.add(fi.a.e);
            }
            if (\u26036.c()) {
                enumSet.add(fi.a.d);
            }
            float \u26038 = (float)\u26036.b();
            if (!\u26036.c()) {
                \u26038 = ns.g(\u26038);
            }
            float \u26039 = (float)\u26037.b();
            if (!\u26037.c()) {
                \u26039 = ns.g(\u26039);
            }
            if (\u26039 > 90.0f || \u26039 < -90.0f) {
                \u26039 = ns.g(180.0f - \u26039);
                \u26038 = ns.g(\u26038 + 180.0f);
            }
            pk2.a((pk)null);
            ((lf)pk2).a.a(\u26033.b(), \u26034.b(), \u26035.b(), \u26038, \u26039, enumSet);
            pk2.f(\u26038);
        } else {
            float \u260310 = (float)ns.g(\u26036.a());
            float \u260311 = (float)ns.g(\u26037.a());
            if (\u260311 > 90.0f || \u260311 < -90.0f) {
                \u260311 = ns.g(180.0f - \u260311);
                \u260310 = ns.g(\u260310 + 180.0f);
            }
            pk2.b(\u26033.a(), \u26034.a(), \u26035.a(), \u260310, \u260311);
            pk2.f(\u260310);
        }
        bn.a(m2, (k)this, "commands.tp.success.coordinates", pk2.e_(), \u26033.a(), \u26034.a(), \u26035.a());
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1 || stringArray.length == 2) {
            return bn.a(stringArray, MinecraftServer.N().K());
        }
        return null;
    }

    @Override
    public boolean b(String[] stringArray, int n2) {
        return n2 == 0;
    }
}

