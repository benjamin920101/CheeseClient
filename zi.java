/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class zi
extends zw {
    @Override
    public boolean f(zx zx2) {
        return true;
    }

    @Override
    public boolean f_(zx zx2) {
        return false;
    }

    @Override
    public aaj g(zx zx2) {
        if (this.h(zx2).c() > 0) {
            return aaj.b;
        }
        return super.g(zx2);
    }

    public du h(zx zx2) {
        dn dn2 = zx2.o();
        if (dn2 == null || !dn2.b("StoredEnchantments", 9)) {
            return new du();
        }
        return (du)dn2.a("StoredEnchantments");
    }

    @Override
    public void a(zx zx2, wn wn2, List<String> list, boolean bl2) {
        super.a(zx2, wn2, list, bl2);
        du du2 = this.h(zx2);
        if (du2 != null) {
            for (int i2 = 0; i2 < du2.c(); ++i2) {
                short s2 = du2.b(i2).e("id");
                \u2603 = du2.b(i2).e("lvl");
                if (aci.c(s2) == null) continue;
                list.add(aci.c(s2).d(\u2603));
            }
        }
    }

    public void a(zx zx22, acl acl2) {
        zx zx22;
        du du2 = this.h(zx22);
        boolean \u26032 = true;
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            dn dn2 = du2.b(i2);
            if (dn2.e("id") != acl2.b.B) continue;
            if (dn2.e("lvl") < acl2.c) {
                dn2.a("lvl", (short)acl2.c);
            }
            \u26032 = false;
            break;
        }
        if (\u26032) {
            dn dn3 = new dn();
            dn3.a("id", (short)acl2.b.B);
            dn3.a("lvl", (short)acl2.c);
            du2.a(dn3);
        }
        if (!zx22.n()) {
            zx22.d(new dn());
        }
        zx22.o().a("StoredEnchantments", du2);
    }

    public zx a(acl acl2) {
        zx zx2 = new zx(this);
        this.a(zx2, acl2);
        return zx2;
    }

    public void a(aci aci2, List<zx> list) {
        for (int i2 = aci2.e(); i2 <= aci2.b(); ++i2) {
            list.add(this.a(new acl(aci2, i2)));
        }
    }

    public ob b(Random random) {
        return this.a(random, 1, 1, 1);
    }

    public ob a(Random random, int n2, int n3, int n4) {
        zx zx2 = new zx(zy.aL, 1, 0);
        ack.a(random, zx2, 30);
        return new ob(zx2, n2, n3, n4);
    }
}

