/*
 * Decompiled with CFR 0.152.
 */
public class ac
extends i {
    @Override
    public String c() {
        return "entitydata";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.entitydata.usage";
    }

    @Override
    public void a(m m2, String[] stringArray) throws bz {
        if (stringArray.length < 2) {
            throw new cf("commands.entitydata.usage", new Object[0]);
        }
        pk pk2 = ac.b(m2, stringArray[0]);
        if (pk2 instanceof wn) {
            throw new bz("commands.entitydata.noPlayers", pk2.f_());
        }
        dn \u26032 = new dn();
        pk2.e(\u26032);
        dn \u26033 = (dn)\u26032.b();
        try {
            dn dn2 = ed.a(ac.a(m2, stringArray, 1).c());
        }
        catch (ec ec2) {
            throw new bz("commands.entitydata.tagError", ec2.getMessage());
        }
        dn2.o("UUIDMost");
        dn2.o("UUIDLeast");
        \u26032.a(dn2);
        if (\u26032.equals(\u26033)) {
            throw new bz("commands.entitydata.failed", \u26032.toString());
        }
        pk2.f(\u26032);
        ac.a(m2, (k)this, "commands.entitydata.success", \u26032.toString());
    }

    @Override
    public boolean b(String[] stringArray, int n2) {
        return n2 == 0;
    }
}

