/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class bq
extends i {
    @Override
    public String c() {
        return "testfor";
    }

    @Override
    public int a() {
        return 2;
    }

    @Override
    public String c(m m2) {
        return "commands.testfor.usage";
    }

    @Override
    public void a(m m22, String[] stringArray) throws bz {
        m m22;
        if (stringArray.length < 1) {
            throw new cf("commands.testfor.usage", new Object[0]);
        }
        pk pk2 = bq.b(m22, stringArray[0]);
        dn \u26032 = null;
        if (stringArray.length >= 2) {
            try {
                \u26032 = ed.a(bq.a(stringArray, 1));
            }
            catch (ec ec2) {
                throw new bz("commands.testfor.tagError", ec2.getMessage());
            }
        }
        if (\u26032 != null) {
            dn dn2 = new dn();
            pk2.e(dn2);
            if (!dy.a(\u26032, dn2, true)) {
                throw new bz("commands.testfor.failure", pk2.e_());
            }
        }
        bq.a(m22, (k)this, "commands.testfor.success", pk2.e_());
    }

    @Override
    public boolean b(String[] stringArray, int n2) {
        return n2 == 0;
    }

    @Override
    public List<String> a(m m2, String[] stringArray, cj cj2) {
        if (stringArray.length == 1) {
            return bq.a(stringArray, MinecraftServer.N().K());
        }
        return null;
    }
}

