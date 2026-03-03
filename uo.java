/*
 * Decompiled with CFR 0.152.
 */
public class uo
extends un {
    private float c = 1.0f;

    public uo(adm adm2) {
        super(adm2);
    }

    public uo(adm adm2, cj cj2, cq cq2) {
        super(adm2, cj2);
        this.a(cq2);
    }

    @Override
    protected void h() {
        this.H().a(8, 5);
        this.H().a(9, Byte.valueOf((byte)0));
    }

    @Override
    public float ao() {
        return 0.0f;
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        if (!ow2.c() && this.o() != null) {
            if (!this.o.D) {
                this.a(ow2.j(), false);
                this.a((zx)null);
            }
            return true;
        }
        return super.a(ow2, f2);
    }

    @Override
    public int l() {
        return 12;
    }

    @Override
    public int m() {
        return 12;
    }

    @Override
    public boolean a(double d2) {
        \u2603 = 16.0;
        return d2 < (\u2603 *= 64.0 * this.j) * \u2603;
    }

    @Override
    public void b(pk pk2) {
        this.a(pk2, true);
    }

    public void a(pk pk2, boolean bl22) {
        boolean bl22;
        if (!this.o.Q().b("doEntityDrops")) {
            return;
        }
        zx zx2 = this.o();
        if (pk2 instanceof wn) {
            wn wn2 = (wn)pk2;
            if (wn2.bA.d) {
                this.b(zx2);
                return;
            }
        }
        if (bl22) {
            this.a(new zx(zy.bP), 0.0f);
        }
        if (zx2 != null && this.V.nextFloat() < this.c) {
            zx2 = zx2.k();
            this.b(zx2);
            this.a(zx2, 0.0f);
        }
    }

    private void b(zx zx22) {
        zx zx22;
        if (zx22 == null) {
            return;
        }
        if (zx22.b() == zy.bd) {
            atg atg2 = ((aab)zx22.b()).a(zx22, this.o);
            atg2.h.remove("frame-" + this.F());
        }
        zx22.a((uo)null);
    }

    public zx o() {
        return this.H().f(8);
    }

    public void a(zx zx2) {
        this.a(zx2, true);
    }

    private void a(zx zx22, boolean bl2) {
        zx zx22;
        if (zx22 != null) {
            zx22 = zx22.k();
            zx22.b = 1;
            zx22.a(this);
        }
        this.H().b(8, zx22);
        this.H().i(8);
        if (bl2 && this.a != null) {
            this.o.e(this.a, afi.a);
        }
    }

    public int p() {
        return this.H().a(9);
    }

    public void a(int n2) {
        this.a(n2, true);
    }

    private void a(int n2, boolean bl2) {
        this.H().b(9, (byte)(n2 % 8));
        if (bl2 && this.a != null) {
            this.o.e(this.a, afi.a);
        }
    }

    @Override
    public void b(dn dn2) {
        if (this.o() != null) {
            dn2.a("Item", this.o().b(new dn()));
            dn2.a("ItemRotation", (byte)this.p());
            dn2.a("ItemDropChance", this.c);
        }
        super.b(dn2);
    }

    @Override
    public void a(dn dn2) {
        \u2603 = dn2.m("Item");
        if (\u2603 != null && !\u2603.c_()) {
            this.a(zx.a(\u2603), false);
            this.a((int)dn2.d("ItemRotation"), false);
            if (dn2.b("ItemDropChance", 99)) {
                this.c = dn2.h("ItemDropChance");
            }
            if (dn2.c("Direction")) {
                this.a(this.p() * 2, false);
            }
        }
        super.a(dn2);
    }

    @Override
    public boolean e(wn wn2) {
        if (this.o() == null) {
            zx zx2 = wn2.bA();
            if (zx2 != null && !this.o.D) {
                this.a(zx2);
                if (!wn2.bA.d && --zx2.b <= 0) {
                    wn2.bi.a(wn2.bi.c, null);
                }
            }
        } else if (!this.o.D) {
            this.a(this.p() + 1);
        }
        return true;
    }

    public int q() {
        if (this.o() == null) {
            return 0;
        }
        return this.p() % 8 + 1;
    }
}

