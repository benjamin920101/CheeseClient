/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class yo
extends zw {
    protected final afh a;

    public yo(afh afh2) {
        this.a = afh2;
    }

    public yo b(String string) {
        super.c(string);
        return this;
    }

    @Override
    public boolean a(zx zx22, wn wn2, adm adm2, cj cj22, cq cq2, float f2, float f3, float f4) {
        zx zx22;
        alz alz2 = adm2.p(cj22);
        afh \u26032 = alz2.c();
        if (!\u26032.a(adm2, cj22)) {
            cj cj22 = cj22.a(cq2);
        }
        if (zx22.b == 0) {
            return false;
        }
        if (!wn2.a(cj22, cq2, zx22)) {
            return false;
        }
        if (adm2.a(this.a, cj22, false, cq2, null, zx22)) {
            int n2 = this.a(zx22.i());
            alz \u26033 = this.a.a(adm2, cj22, cq2, f2, f3, f4, n2, wn2);
            if (adm2.a(cj22, \u26033, 3)) {
                \u26033 = adm2.p(cj22);
                if (\u26033.c() == this.a) {
                    yo.a(adm2, wn2, cj22, zx22);
                    this.a.a(adm2, cj22, \u26033, wn2, zx22);
                }
                adm2.a((float)cj22.n() + 0.5f, (double)((float)cj22.o() + 0.5f), (double)((float)cj22.p() + 0.5f), this.a.H.b(), (this.a.H.d() + 1.0f) / 2.0f, this.a.H.e() * 0.8f);
                --zx22.b;
            }
            return true;
        }
        return false;
    }

    public static boolean a(adm adm2, wn wn2, cj cj2, zx zx2) {
        MinecraftServer minecraftServer = MinecraftServer.N();
        if (minecraftServer == null) {
            return false;
        }
        if (zx2.n() && zx2.o().b("BlockEntityTag", 10) && (\u2603 = adm2.s(cj2)) != null) {
            if (!adm2.D && \u2603.F() && !minecraftServer.ap().h(wn2.cd())) {
                return false;
            }
            dn dn2 = new dn();
            \u2603 = (dn)dn2.b();
            \u2603.b(dn2);
            \u2603 = (dn)zx2.o().a("BlockEntityTag");
            dn2.a(\u2603);
            dn2.a("x", cj2.n());
            dn2.a("y", cj2.o());
            dn2.a("z", cj2.p());
            if (!dn2.equals(\u2603)) {
                \u2603.a(dn2);
                \u2603.p_();
                return true;
            }
        }
        return false;
    }

    public boolean a(adm adm22, cj cj22, cq cq22, wn wn2, zx zx2) {
        adm adm22;
        afh afh2 = adm22.p(cj22).c();
        if (afh2 == afi.aH) {
            cq cq22 = cq.b;
        } else if (!afh2.a(adm22, cj22)) {
            cj cj22 = cj22.a(cq22);
        }
        return adm22.a(this.a, cj22, false, cq22, null, zx2);
    }

    @Override
    public String e_(zx zx2) {
        return this.a.a();
    }

    @Override
    public String a() {
        return this.a.a();
    }

    @Override
    public yz c() {
        return this.a.L();
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        this.a.a(zw2, yz2, list);
    }

    public afh d() {
        return this.a;
    }

    @Override
    public /* synthetic */ zw c(String string) {
        return this.b(string);
    }
}

