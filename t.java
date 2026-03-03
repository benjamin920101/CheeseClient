/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class t
extends i {
    @Override
    public String c() {
        return "clear";
    }

    @Override
    public String c(m m2) {
        return "commands.clear.usage";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public void a(m m2, String[] stringArray2) throws bz {
        String[] stringArray2;
        lf lf2 = stringArray2.length == 0 ? t.b(m2) : t.a(m2, stringArray2[0]);
        zw \u26032 = stringArray2.length >= 2 ? t.f(m2, stringArray2[1]) : null;
        int \u26033 = stringArray2.length >= 3 ? t.a(stringArray2[2], -1) : -1;
        int \u26034 = stringArray2.length >= 4 ? t.a(stringArray2[3], -1) : -1;
        dn \u26035 = null;
        if (stringArray2.length >= 5) {
            try {
                \u26035 = ed.a(t.a(stringArray2, 4));
            }
            catch (ec ec2) {
                throw new bz("commands.clear.tagError", ec2.getMessage());
            }
        }
        if (stringArray2.length >= 2 && \u26032 == null) {
            throw new bz("commands.clear.failure", lf2.e_());
        }
        int \u26036 = lf2.bi.a(\u26032, \u26033, \u26034, \u26035);
        lf2.bj.b();
        if (!lf2.bA.d) {
            lf2.o();
        }
        m2.a(n.a.d, \u26036);
        if (\u26036 == 0) {
            throw new bz("commands.clear.failure", lf2.e_());
        }
        if (\u26034 == 0) {
            m2.a(new fb("commands.clear.testing", lf2.e_(), \u26036));
        } else {
            t.a(m2, (k)this, "commands.clear.success", lf2.e_(), \u26036);
        }
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return t.a(stringArray, this.d());
        }
        if (stringArray.length == 2) {
            return t.a(stringArray, zw.e.c());
        }
        return null;
    }

    protected String[] d() {
        return MinecraftServer.N().K();
    }

    @Override
    public boolean b(String[] stringArray, int n2) {
        return n2 == 0;
    }
}

