/*
 * Decompiled with CFR 0.152.
 */
public class ahq
extends afc {
    public static final amk a = amk.a("has_record");

    protected ahq() {
        super(arm.d, arn.l);
        this.j(this.M.b().a(a, false));
        this.a(yz.c);
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz22, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (alz22.b(a).booleanValue()) {
            this.e(adm2, cj2, alz22);
            alz alz22 = alz22.a(a, false);
            adm2.a(cj2, alz22, 2);
            return true;
        }
        return false;
    }

    public void a(adm adm2, cj cj2, alz alz2, zx zx2) {
        if (adm2.D) {
            return;
        }
        akw akw2 = adm2.s(cj2);
        if (!(akw2 instanceof a)) {
            return;
        }
        ((a)akw2).a(new zx(zx2.b(), 1, zx2.i()));
        adm2.a(cj2, alz2.a(a, true), 2);
    }

    private void e(adm adm2, cj cj2, alz alz2) {
        if (adm2.D) {
            return;
        }
        akw akw2 = adm2.s(cj2);
        if (!(akw2 instanceof a)) {
            return;
        }
        a \u26032 = (a)akw2;
        zx \u26033 = \u26032.a();
        if (\u26033 == null) {
            return;
        }
        adm2.b(1005, cj2, 0);
        adm2.a(cj2, (String)null);
        \u26032.a((zx)null);
        float \u26034 = 0.7f;
        double \u26035 = (double)(adm2.s.nextFloat() * \u26034) + (double)(1.0f - \u26034) * 0.5;
        double \u26036 = (double)(adm2.s.nextFloat() * \u26034) + (double)(1.0f - \u26034) * 0.2 + 0.6;
        double \u26037 = (double)(adm2.s.nextFloat() * \u26034) + (double)(1.0f - \u26034) * 0.5;
        zx \u26038 = \u26033.k();
        uz \u26039 = new uz(adm2, (double)cj2.n() + \u26035, (double)cj2.o() + \u26036, (double)cj2.p() + \u26037, \u26038);
        \u26039.p();
        adm2.d(\u26039);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        this.e(adm2, cj2, alz2);
        super.b(adm2, cj2, alz2);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, float f2, int n2) {
        if (adm2.D) {
            return;
        }
        super.a(adm2, cj2, alz2, f2, 0);
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new a();
    }

    @Override
    public boolean O() {
        return true;
    }

    @Override
    public int l(adm adm2, cj cj2) {
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof a && (\u2603 = ((a)akw2).a()) != null) {
            return zw.b(\u2603.b()) + 1 - zw.b(zy.cq);
        }
        return 0;
    }

    @Override
    public int b() {
        return 3;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, n2 > 0);
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a) != false ? 1 : 0;
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }

    public static class a
    extends akw {
        private zx a;

        @Override
        public void a(dn dn2) {
            super.a(dn2);
            if (dn2.b("RecordItem", 10)) {
                this.a(zx.a(dn2.m("RecordItem")));
            } else if (dn2.f("Record") > 0) {
                this.a(new zx(zw.b(dn2.f("Record")), 1, 0));
            }
        }

        @Override
        public void b(dn dn2) {
            super.b(dn2);
            if (this.a() != null) {
                dn2.a("RecordItem", this.a().b(new dn()));
            }
        }

        public zx a() {
            return this.a;
        }

        public void a(zx zx2) {
            this.a = zx2;
            this.p_();
        }
    }
}

