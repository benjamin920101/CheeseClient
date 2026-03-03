/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;
import java.util.ArrayList;

public class ada
extends ArrayList<acz> {
    public ada() {
    }

    public ada(dn dn2) {
        this.a(dn2);
    }

    public acz a(zx zx2, zx zx3, int n2) {
        if (n2 > 0 && n2 < this.size()) {
            acz acz2 = (acz)this.get(n2);
            if (this.a(zx2, acz2.a()) && (zx3 == null && !acz2.c() || acz2.c() && this.a(zx3, acz2.b())) && zx2.b >= acz2.a().b && (!acz2.c() || zx3.b >= acz2.b().b)) {
                return acz2;
            }
            return null;
        }
        for (int i2 = 0; i2 < this.size(); ++i2) {
            acz acz3 = (acz)this.get(i2);
            if (!this.a(zx2, acz3.a()) || zx2.b < acz3.a().b || (acz3.c() || zx3 != null) && (!acz3.c() || !this.a(zx3, acz3.b()) || zx3.b < acz3.b().b)) continue;
            return acz3;
        }
        return null;
    }

    private boolean a(zx zx2, zx zx3) {
        return zx.c(zx2, zx3) && (!zx3.n() || zx2.n() && dy.a(zx3.o(), zx2.o(), false));
    }

    public void a(em em2) {
        em2.writeByte((byte)(this.size() & 0xFF));
        for (int i2 = 0; i2 < this.size(); ++i2) {
            acz acz2 = (acz)this.get(i2);
            em2.a(acz2.a());
            em2.a(acz2.d());
            zx \u26032 = acz2.b();
            em2.writeBoolean(\u26032 != null);
            if (\u26032 != null) {
                em2.a(\u26032);
            }
            em2.writeBoolean(acz2.h());
            em2.writeInt(acz2.e());
            em2.writeInt(acz2.f());
        }
    }

    public static ada b(em em2) throws IOException {
        ada ada2 = new ada();
        int \u26032 = em2.readByte() & 0xFF;
        for (int i2 = 0; i2 < \u26032; ++i2) {
            zx zx2 = em2.i();
            \u2603 = em2.i();
            \u2603 = null;
            if (em2.readBoolean()) {
                \u2603 = em2.i();
            }
            boolean \u26033 = em2.readBoolean();
            int \u26034 = em2.readInt();
            int \u26035 = em2.readInt();
            acz \u26036 = new acz(zx2, \u2603, \u2603, \u26034, \u26035);
            if (\u26033) {
                \u26036.i();
            }
            ada2.add(\u26036);
        }
        return ada2;
    }

    public void a(dn dn2) {
        du du2 = dn2.c("Recipes", 10);
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            dn dn3 = du2.b(i2);
            this.add(new acz(dn3));
        }
    }

    public dn a() {
        dn dn2 = new dn();
        du \u26032 = new du();
        for (int i2 = 0; i2 < this.size(); ++i2) {
            acz acz2 = (acz)this.get(i2);
            \u26032.a(acz2.k());
        }
        dn2.a("Recipes", \u26032);
        return dn2;
    }
}

