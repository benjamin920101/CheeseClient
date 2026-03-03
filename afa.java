/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class afa
extends afc {
    public static final aml a = aml.a("facing", cq.c.a);
    public static final amn b = amn.a("rotation", 0, 15);

    protected afa() {
        super(arm.d);
        float f2 = 0.25f;
        \u2603 = 1.0f;
        this.a(0.5f - f2, 0.0f, 0.5f - f2, 0.5f + f2, \u2603, 0.5f + f2);
    }

    @Override
    public String f() {
        return di.a("item.banner.white.name");
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        return null;
    }

    @Override
    public aug b(adm adm2, cj cj2) {
        this.a((adq)adm2, cj2);
        return super.b(adm2, cj2);
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean b(adq adq2, cj cj2) {
        return true;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean g() {
        return true;
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new aku();
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zy.cE;
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zy.cE;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, float f2, int n2) {
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof aku) {
            zx zx2 = new zx(zy.cE, 1, ((aku)akw2).b());
            dn \u26032 = new dn();
            akw2.b(\u26032);
            \u26032.o("x");
            \u26032.o("y");
            \u26032.o("z");
            \u26032.o("id");
            zx2.a("BlockEntityTag", \u26032);
            afa.a(adm2, cj2, zx2);
        } else {
            super.a(adm2, cj2, alz2, f2, n2);
        }
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        return !this.e(adm2, cj2) && super.d(adm2, cj2);
    }

    @Override
    public void a(adm adm2, wn wn2, cj cj2, alz alz2, akw akw2) {
        if (akw2 instanceof aku) {
            aku aku2 = (aku)akw2;
            zx \u26032 = new zx(zy.cE, 1, ((aku)akw2).b());
            dn \u26033 = new dn();
            aku.a(\u26033, aku2.b(), aku2.d());
            \u26032.a("BlockEntityTag", \u26033);
            afa.a(adm2, cj2, \u26032);
        } else {
            super.a(adm2, wn2, cj2, alz2, null);
        }
    }

    public static class a
    extends afa {
        public a() {
            this.j(this.M.b().a(b, 0));
        }

        @Override
        public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
            if (!adm2.p(cj2.b()).c().t().a()) {
                this.b(adm2, cj2, alz2, 0);
                adm2.g(cj2);
            }
            super.a(adm2, cj2, alz2, afh2);
        }

        @Override
        public alz a(int n2) {
            return this.Q().a(b, n2);
        }

        @Override
        public int c(alz alz2) {
            return alz2.b(b);
        }

        @Override
        protected ama e() {
            return new ama(this, b);
        }
    }

    public static class b
    extends afa {
        public b() {
            this.j(this.M.b().a(a, cq.c));
        }

        @Override
        public void a(adq adq2, cj cj2) {
            cq cq2 = adq2.p(cj2).b(a);
            float \u26032 = 0.0f;
            float \u26033 = 0.78125f;
            float \u26034 = 0.0f;
            float \u26035 = 1.0f;
            float \u26036 = 0.125f;
            this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            switch (cq2) {
                default: {
                    this.a(\u26034, \u26032, 1.0f - \u26036, \u26035, \u26033, 1.0f);
                    break;
                }
                case d: {
                    this.a(\u26034, \u26032, 0.0f, \u26035, \u26033, \u26036);
                    break;
                }
                case e: {
                    this.a(1.0f - \u26036, \u26032, \u26034, 1.0f, \u26033, \u26035);
                    break;
                }
                case f: {
                    this.a(0.0f, \u26032, \u26034, \u26036, \u26033, \u26035);
                }
            }
        }

        @Override
        public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
            cq cq2 = alz2.b(a);
            if (!adm2.p(cj2.a(cq2.d())).c().t().a()) {
                this.b(adm2, cj2, alz2, 0);
                adm2.g(cj2);
            }
            super.a(adm2, cj2, alz2, afh2);
        }

        @Override
        public alz a(int n2) {
            cq cq2 = cq.a(n2);
            if (cq2.k() == cq.a.b) {
                cq2 = cq.c;
            }
            return this.Q().a(a, cq2);
        }

        @Override
        public int c(alz alz2) {
            return alz2.b(a).a();
        }

        @Override
        protected ama e() {
            return new ama(this, a);
        }
    }
}

