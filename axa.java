/*
 * Decompiled with CFR 0.152.
 */
public class axa
extends axu {
    private final axb a;
    private apz f = apz.e();
    private String g;
    private String h;
    private String i;
    private a r;
    private avs s;
    private avs t;
    private avs u;

    public axa(axb axb2, String string) {
        this.a = axb2;
        this.a(string);
    }

    public String a() {
        return this.f.toString();
    }

    public void a(String string) {
        this.f = apz.a(string);
    }

    @Override
    public void b() {
        this.n.clear();
        this.g = bnq.a("createWorld.customize.flat.title", new Object[0]);
        this.h = bnq.a("createWorld.customize.flat.tile", new Object[0]);
        this.i = bnq.a("createWorld.customize.flat.height", new Object[0]);
        this.r = new a();
        this.s = new avs(2, this.l / 2 - 154, this.m - 52, 100, 20, bnq.a("createWorld.customize.flat.addLayer", new Object[0]) + " (NYI)");
        this.n.add(this.s);
        this.t = new avs(3, this.l / 2 - 50, this.m - 52, 100, 20, bnq.a("createWorld.customize.flat.editLayer", new Object[0]) + " (NYI)");
        this.n.add(this.t);
        this.u = new avs(4, this.l / 2 - 155, this.m - 52, 150, 20, bnq.a("createWorld.customize.flat.removeLayer", new Object[0]));
        this.n.add(this.u);
        this.n.add(new avs(0, this.l / 2 - 155, this.m - 28, 150, 20, bnq.a("gui.done", new Object[0])));
        this.n.add(new avs(5, this.l / 2 + 5, this.m - 52, 150, 20, bnq.a("createWorld.customize.presets", new Object[0])));
        this.n.add(new avs(1, this.l / 2 + 5, this.m - 28, 150, 20, bnq.a("gui.cancel", new Object[0])));
        this.t.m = false;
        this.s.m = false;
        this.f.d();
        this.f();
    }

    @Override
    public void k() {
        super.k();
        this.r.p();
    }

    @Override
    protected void a(avs avs2) {
        int n2 = this.f.c().size() - this.r.u - 1;
        if (avs2.k == 1) {
            this.j.a(this.a);
        } else if (avs2.k == 0) {
            this.a.a = this.a();
            this.j.a(this.a);
        } else if (avs2.k == 5) {
            this.j.a(new axq(this));
        } else if (avs2.k == 4 && this.g()) {
            this.f.c().remove(n2);
            this.r.u = Math.min(this.r.u, this.f.c().size() - 1);
        }
        this.f.d();
        this.f();
    }

    public void f() {
        boolean bl2;
        this.u.l = bl2 = this.g();
        this.t.l = bl2;
        this.t.l = false;
        this.s.l = false;
    }

    private boolean g() {
        return this.r.u > -1 && this.r.u < this.f.c().size();
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.r.a(n2, n3, f2);
        this.a(this.q, this.g, this.l / 2, 8, 0xFFFFFF);
        int n4 = this.l / 2 - 92 - 16;
        this.c(this.q, this.h, n4, 32, 0xFFFFFF);
        this.c(this.q, this.i, n4 + 2 + 213 - this.q.a(this.i), 32, 0xFFFFFF);
        super.a(n2, n3, f2);
    }

    class a
    extends awi {
        public int u;

        public a() {
            super(axa.this.j, axa.this.l, axa.this.m, 43, axa.this.m - 60, 24);
            this.u = -1;
        }

        private void a(int n2, int n3, zx zx2) {
            this.e(n2 + 1, n3 + 1);
            bfl.B();
            if (zx2 != null && zx2.b() != null) {
                avc.c();
                axa.this.k.a(zx2, n2 + 2, n3 + 2);
                avc.a();
            }
            bfl.C();
        }

        private void e(int n2, int n3) {
            this.d(n2, n3, 0, 0);
        }

        private void d(int n2, int n3, int n4, int n5) {
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            this.a.P().a(avp.c);
            float f2 = 0.0078125f;
            \u2603 = 0.0078125f;
            int \u26032 = 18;
            int \u26033 = 18;
            bfx \u26034 = bfx.a();
            bfd \u26035 = \u26034.c();
            \u26035.a(7, bms.g);
            \u26035.b((double)(n2 + 0), (double)(n3 + 18), (double)axa.this.e).a((float)(n4 + 0) * 0.0078125f, (float)(n5 + 18) * 0.0078125f).d();
            \u26035.b((double)(n2 + 18), (double)(n3 + 18), (double)axa.this.e).a((float)(n4 + 18) * 0.0078125f, (float)(n5 + 18) * 0.0078125f).d();
            \u26035.b((double)(n2 + 18), (double)(n3 + 0), (double)axa.this.e).a((float)(n4 + 18) * 0.0078125f, (float)(n5 + 0) * 0.0078125f).d();
            \u26035.b((double)(n2 + 0), (double)(n3 + 0), (double)axa.this.e).a((float)(n4 + 0) * 0.0078125f, (float)(n5 + 0) * 0.0078125f).d();
            \u26034.b();
        }

        @Override
        protected int b() {
            return axa.this.f.c().size();
        }

        @Override
        protected void a(int n2, boolean bl2, int n3, int n4) {
            this.u = n2;
            axa.this.f();
        }

        @Override
        protected boolean a(int n2) {
            return n2 == this.u;
        }

        @Override
        protected void a() {
        }

        @Override
        protected void a(int n2, int n32, int n4, int n5, int n6, int n7) {
            int n32;
            aqa aqa2 = axa.this.f.c().get(axa.this.f.c().size() - n2 - 1);
            alz \u26032 = aqa2.c();
            afh \u26033 = \u26032.c();
            zw \u26034 = zw.a(\u26033);
            zx \u26035 = \u26033 == afi.a || \u26034 == null ? null : new zx(\u26034, 1, \u26033.c(\u26032));
            String string = string2 = \u26035 == null ? "Air" : \u26034.a(\u26035);
            if (\u26034 == null) {
                if (\u26033 == afi.j || \u26033 == afi.i) {
                    \u26034 = zy.ax;
                } else if (\u26033 == afi.l || \u26033 == afi.k) {
                    \u26034 = zy.ay;
                }
                if (\u26034 != null) {
                    \u26035 = new zx(\u26034, 1, \u26033.c(\u26032));
                    String string2 = \u26033.f();
                }
            }
            this.a(n32, n4, \u26035);
            axa.this.q.a(string2, n32 + 18 + 5, n4 + 3, 0xFFFFFF);
            String \u26036 = n2 == 0 ? bnq.a("createWorld.customize.flat.layer.top", aqa2.b()) : (n2 == axa.this.f.c().size() - 1 ? bnq.a("createWorld.customize.flat.layer.bottom", aqa2.b()) : bnq.a("createWorld.customize.flat.layer", aqa2.b()));
            axa.this.q.a(\u26036, n32 + 2 + 213 - axa.this.q.a(\u26036), n4 + 3, 0xFFFFFF);
        }

        @Override
        protected int d() {
            return this.b - 70;
        }
    }
}

