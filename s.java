/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class s
extends i {
    @Override
    public String c() {
        return "blockdata";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.blockdata.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length < 4) {
            throw new cf("commands.blockdata.usage", new Object[0]);
        }
        m2.a(n.a.b, 0);
        cj cj2 = s.a(m2, stringArray, 0, false);
        adm \u26032 = m2.e();
        if (!\u26032.e(cj2)) {
            throw new bz("commands.blockdata.outOfWorld", new Object[0]);
        }
        akw \u26033 = \u26032.s(cj2);
        if (\u26033 == null) {
            throw new bz("commands.blockdata.notValid", new Object[0]);
        }
        dn \u26034 = new dn();
        \u26033.b(\u26034);
        dn \u26035 = (dn)\u26034.b();
        try {
            dn dn2 = ed.a(s.a(m2, stringArray, 3).c());
        }
        catch (ec ec2) {
            throw new bz("commands.blockdata.tagError", ec2.getMessage());
        }
        \u26034.a(dn2);
        \u26034.a("x", cj2.n());
        \u26034.a("y", cj2.o());
        \u26034.a("z", cj2.p());
        if (\u26034.equals(\u26035)) {
            throw new bz("commands.blockdata.failed", \u26034.toString());
        }
        \u26033.a(\u26034);
        \u26033.p_();
        \u26032.h(cj2);
        m2.a(n.a.b, 1);
        s.a(m2, (k)this, "commands.blockdata.success", \u26034.toString());
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length > 0 && stringArray.length <= 3) {
            return s.a(stringArray, 0, cj2);
        }
        return null;
    }
}

