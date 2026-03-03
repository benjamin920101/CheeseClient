/*
 * Decompiled with CFR 0.152.
 */
public class alm
extends akw {
    public byte a;
    public boolean f;

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("note", this.a);
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.a = dn2.d("note");
        this.a = (byte)ns.a(this.a, 0, 24);
    }

    public void b() {
        this.a = (byte)((this.a + 1) % 25);
        this.p_();
    }

    public void a(adm adm2, cj cj2) {
        if (adm2.p(cj2.a()).c().t() != arm.a) {
            return;
        }
        arm arm2 = adm2.p(cj2.b()).c().t();
        int \u26032 = 0;
        if (arm2 == arm.e) {
            \u26032 = 1;
        }
        if (arm2 == arm.p) {
            \u26032 = 2;
        }
        if (arm2 == arm.s) {
            \u26032 = 3;
        }
        if (arm2 == arm.d) {
            \u26032 = 4;
        }
        adm2.c(cj2, afi.B, \u26032, this.a);
    }
}

