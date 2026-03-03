/*
 * Decompiled with CFR 0.152.
 */
public class yd
extends oq {
    private alf a;

    public yd() {
        super("container.enderchest", false, 27);
    }

    public void a(alf alf2) {
        this.a = alf2;
    }

    public void a(du du2) {
        int n2;
        for (n2 = 0; n2 < this.o_(); ++n2) {
            this.a(n2, null);
        }
        for (n2 = 0; n2 < du2.c(); ++n2) {
            dn dn2 = du2.b(n2);
            int \u26032 = dn2.d("Slot") & 0xFF;
            if (\u26032 < 0 || \u26032 >= this.o_()) continue;
            this.a(\u26032, zx.a(dn2));
        }
    }

    public du h() {
        du du2 = new du();
        for (int i2 = 0; i2 < this.o_(); ++i2) {
            zx zx2 = this.a(i2);
            if (zx2 == null) continue;
            dn \u26032 = new dn();
            \u26032.a("Slot", (byte)i2);
            zx2.b(\u26032);
            du2.a(\u26032);
        }
        return du2;
    }

    @Override
    public boolean a(wn wn2) {
        if (this.a != null && !this.a.a(wn2)) {
            return false;
        }
        return super.a(wn2);
    }

    @Override
    public void b(wn wn2) {
        if (this.a != null) {
            this.a.b();
        }
        super.b(wn2);
    }

    @Override
    public void c(wn wn2) {
        if (this.a != null) {
            this.a.d();
        }
        super.c(wn2);
        this.a = null;
    }
}

