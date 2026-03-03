/*
 * Decompiled with CFR 0.152.
 */
import java.util.UUID;

public abstract class qa
extends tm
implements px {
    protected se bm = new se(this);

    public qa(adm adm2) {
        super(adm2);
        this.cm();
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, Byte.valueOf((byte)0));
        this.ac.a(17, "");
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        if (this.b() == null) {
            dn2.a("OwnerUUID", "");
        } else {
            dn2.a("OwnerUUID", this.b());
        }
        dn2.a("Sitting", this.cn());
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        String string = "";
        if (dn2.b("OwnerUUID", 8)) {
            string = dn2.j("OwnerUUID");
        } else {
            \u2603 = dn2.j("Owner");
            string = lw.a(\u2603);
        }
        if (string.length() > 0) {
            this.b(string);
            this.m(true);
        }
        this.bm.a(dn2.n("Sitting"));
        this.n(dn2.n("Sitting"));
    }

    protected void l(boolean bl2) {
        cy cy2 = cy.I;
        if (!bl2) {
            cy2 = cy.l;
        }
        for (int i2 = 0; i2 < 7; ++i2) {
            double d2 = this.V.nextGaussian() * 0.02;
            \u2603 = this.V.nextGaussian() * 0.02;
            \u2603 = this.V.nextGaussian() * 0.02;
            this.o.a(cy2, this.s + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, this.t + 0.5 + (double)(this.V.nextFloat() * this.K), this.u + (double)(this.V.nextFloat() * this.J * 2.0f) - (double)this.J, d2, \u2603, \u2603, new int[0]);
        }
    }

    @Override
    public void a(byte by) {
        if (by == 7) {
            this.l(true);
        } else if (by == 6) {
            this.l(false);
        } else {
            super.a(by);
        }
    }

    public boolean cl() {
        return (this.ac.a(16) & 4) != 0;
    }

    public void m(boolean bl2) {
        byte by = this.ac.a(16);
        if (bl2) {
            this.ac.b(16, (byte)(by | 4));
        } else {
            this.ac.b(16, (byte)(by & 0xFFFFFFFB));
        }
        this.cm();
    }

    protected void cm() {
    }

    public boolean cn() {
        return (this.ac.a(16) & 1) != 0;
    }

    public void n(boolean bl2) {
        byte by = this.ac.a(16);
        if (bl2) {
            this.ac.b(16, (byte)(by | 1));
        } else {
            this.ac.b(16, (byte)(by & 0xFFFFFFFE));
        }
    }

    @Override
    public String b() {
        return this.ac.e(17);
    }

    public void b(String string) {
        this.ac.b(17, string);
    }

    public pr co() {
        try {
            UUID uUID = UUID.fromString(this.b());
            if (uUID == null) {
                return null;
            }
            return this.o.b(uUID);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }

    public boolean e(pr pr2) {
        return pr2 == this.co();
    }

    public se cp() {
        return this.bm;
    }

    public boolean a(pr pr2, pr pr3) {
        return true;
    }

    @Override
    public auq bO() {
        pr pr2;
        if (this.cl() && (pr2 = this.co()) != null) {
            return pr2.bO();
        }
        return super.bO();
    }

    @Override
    public boolean c(pr pr2) {
        if (this.cl()) {
            \u2603 = this.co();
            if (pr2 == \u2603) {
                return true;
            }
            if (\u2603 != null) {
                return \u2603.c(pr2);
            }
        }
        return super.c(pr2);
    }

    @Override
    public void a(ow ow2) {
        if (!this.o.D && this.o.Q().b("showDeathMessages") && this.l_() && this.co() instanceof lf) {
            ((lf)this.co()).a(this.bs().b());
        }
        super.a(ow2);
    }

    @Override
    public /* synthetic */ pk m_() {
        return this.co();
    }
}

