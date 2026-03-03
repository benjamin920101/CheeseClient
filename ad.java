/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class ad
extends i {
    @Override
    public String c() {
        return "execute";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.execute.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length < 5) {
            throw new cf("commands.execute.usage", new Object[0]);
        }
        final pk pk2 = ad.a(m2, stringArray[0], pk.class);
        final double \u26032 = ad.b(pk2.s, stringArray[1], false);
        final double \u26033 = ad.b(pk2.t, stringArray[2], false);
        final double \u26034 = ad.b(pk2.u, stringArray[3], false);
        final cj \u26035 = new cj(\u26032, \u26033, \u26034);
        int \u26036 = 4;
        if ("detect".equals(stringArray[4]) && stringArray.length > 10) {
            Object object = pk2.e();
            double \u26037 = ad.b(\u26032, stringArray[5], false);
            double \u26038 = ad.b(\u26033, stringArray[6], false);
            double \u26039 = ad.b(\u26034, stringArray[7], false);
            afh \u260310 = ad.g(m2, stringArray[8]);
            int \u260311 = ad.a(stringArray[9], -1, 15);
            cj \u260312 = new cj(\u26037, \u26038, \u26039);
            alz \u260313 = ((adm)object).p(\u260312);
            if (\u260313.c() != \u260310 || \u260311 >= 0 && \u260313.c().c(\u260313) != \u260311) {
                throw new bz("commands.execute.failed", "detect", pk2.e_());
            }
            \u26036 = 10;
        }
        object = ad.a(stringArray, \u26036);
        final m m3 = m2;
        \u2603 = new m(){

            @Override
            public String e_() {
                return pk2.e_();
            }

            @Override
            public eu f_() {
                return pk2.f_();
            }

            @Override
            public void a(eu eu2) {
                m3.a(eu2);
            }

            @Override
            public boolean a(int n2, String string) {
                return m3.a(n2, string);
            }

            @Override
            public cj c() {
                return \u26035;
            }

            @Override
            public aui d() {
                return new aui(\u26032, \u26033, \u26034);
            }

            @Override
            public adm e() {
                return pk2.o;
            }

            @Override
            public pk f() {
                return pk2;
            }

            @Override
            public boolean u_() {
                MinecraftServer minecraftServer = MinecraftServer.N();
                return minecraftServer == null || minecraftServer.d[0].Q().b("commandBlockOutput");
            }

            @Override
            public void a(n.a a2, int n2) {
                pk2.a(a2, n2);
            }
        };
        l \u260314 = MinecraftServer.N().P();
        try {
            int n2 = \u260314.a(\u2603, (String)object);
            if (n2 < 1) {
                throw new bz("commands.execute.allInvocationsFailed", object);
            }
        }
        catch (Throwable throwable) {
            throw new bz("commands.execute.failed", object, pk2.e_());
        }
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return ad.a(stringArray, MinecraftServer.N().K());
        }
        if (stringArray.length > 1 && stringArray.length <= 4) {
            return ad.a(stringArray, 1, cj2);
        }
        if (stringArray.length > 5 && stringArray.length <= 8 && "detect".equals(stringArray[4])) {
            return ad.a(stringArray, 5, cj2);
        }
        if (stringArray.length == 9 && "detect".equals(stringArray[4])) {
            return ad.a(stringArray, afh.c.c());
        }
        return null;
    }

    @Override
    public boolean b(String[] stringArray, int n2) {
        return n2 == 0;
    }
}

