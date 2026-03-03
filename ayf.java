/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.lwjgl.input.Mouse;

public class ayf
extends axu
implements ayg {
    protected axu a;
    protected String f = "Select world";
    private b g;
    private c h;
    private a i;
    private d r;
    private nb s;
    private awi t;
    private boolean u = true;

    public ayf(axu axu2, nb nb2) {
        this.a = axu2;
        this.s = nb2;
    }

    @Override
    public void b() {
        this.f = bnq.a("gui.stats", new Object[0]);
        this.u = true;
        this.j.u().a(new ig(ig.a.b));
    }

    @Override
    public void k() {
        super.k();
        if (this.t != null) {
            this.t.p();
        }
    }

    public void f() {
        this.g = new b(this.j);
        this.g.d(1, 1);
        this.h = new c(this.j);
        this.h.d(1, 1);
        this.i = new a(this.j);
        this.i.d(1, 1);
        this.r = new d(this.j);
        this.r.d(1, 1);
    }

    public void g() {
        this.n.add(new avs(0, this.l / 2 + 4, this.m - 28, 150, 20, bnq.a("gui.done", new Object[0])));
        this.n.add(new avs(1, this.l / 2 - 160, this.m - 52, 80, 20, bnq.a("stat.generalButton", new Object[0])));
        avs avs2 = new avs(2, this.l / 2 - 80, this.m - 52, 80, 20, bnq.a("stat.blocksButton", new Object[0]));
        this.n.add(avs2);
        \u2603 = new avs(3, this.l / 2, this.m - 52, 80, 20, bnq.a("stat.itemsButton", new Object[0]));
        this.n.add(\u2603);
        \u2603 = new avs(4, this.l / 2 + 80, this.m - 52, 80, 20, bnq.a("stat.mobsButton", new Object[0]));
        this.n.add(\u2603);
        if (this.i.b() == 0) {
            avs2.l = false;
        }
        if (this.h.b() == 0) {
            \u2603.l = false;
        }
        if (this.r.b() == 0) {
            \u2603.l = false;
        }
    }

    @Override
    protected void a(avs avs2) {
        if (!avs2.l) {
            return;
        }
        if (avs2.k == 0) {
            this.j.a(this.a);
        } else if (avs2.k == 1) {
            this.t = this.g;
        } else if (avs2.k == 3) {
            this.t = this.h;
        } else if (avs2.k == 2) {
            this.t = this.i;
        } else if (avs2.k == 4) {
            this.t = this.r;
        } else {
            this.t.a(avs2);
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        if (this.u) {
            this.c();
            this.a(this.q, bnq.a("multiplayer.downloadingStats", new Object[0]), this.l / 2, this.m / 2, 0xFFFFFF);
            this.a(this.q, c_[(int)(ave.J() / 150L % (long)c_.length)], this.l / 2, this.m / 2 + this.q.a * 2, 0xFFFFFF);
        } else {
            this.t.a(n2, n3, f2);
            this.a(this.q, this.f, this.l / 2, 20, 0xFFFFFF);
            super.a(n2, n3, f2);
        }
    }

    @Override
    public void a() {
        if (this.u) {
            this.f();
            this.g();
            this.t = this.g;
            this.u = false;
        }
    }

    @Override
    public boolean d() {
        return !this.u;
    }

    private void a(int n2, int n3, zw zw2) {
        this.b(n2 + 1, n3 + 1);
        bfl.B();
        avc.c();
        this.k.a(new zx(zw2, 1, 0), n2 + 2, n3 + 2);
        avc.a();
        bfl.C();
    }

    private void b(int n2, int n3) {
        this.c(n2, n3, 0, 0);
    }

    private void c(int n2, int n3, int n4, int n5) {
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(c);
        float f2 = 0.0078125f;
        \u2603 = 0.0078125f;
        int \u26032 = 18;
        int \u26033 = 18;
        bfx \u26034 = bfx.a();
        bfd \u26035 = \u26034.c();
        \u26035.a(7, bms.g);
        \u26035.b((double)(n2 + 0), (double)(n3 + 18), (double)this.e).a((float)(n4 + 0) * 0.0078125f, (float)(n5 + 18) * 0.0078125f).d();
        \u26035.b((double)(n2 + 18), (double)(n3 + 18), (double)this.e).a((float)(n4 + 18) * 0.0078125f, (float)(n5 + 18) * 0.0078125f).d();
        \u26035.b((double)(n2 + 18), (double)(n3 + 0), (double)this.e).a((float)(n4 + 18) * 0.0078125f, (float)(n5 + 0) * 0.0078125f).d();
        \u26035.b((double)(n2 + 0), (double)(n3 + 0), (double)this.e).a((float)(n4 + 0) * 0.0078125f, (float)(n5 + 0) * 0.0078125f).d();
        \u26034.b();
    }

    class d
    extends awi {
        private final List<pm.a> v;

        public d(ave ave2) {
            super(ave2, ayf.this.l, ayf.this.m, 32, ayf.this.m - 64, ((ayf)ayf.this).q.a * 4);
            this.v = Lists.newArrayList();
            this.b(false);
            for (pm.a a2 : pm.a.values()) {
                if (ayf.this.s.a(a2.d) <= 0 && ayf.this.s.a(a2.e) <= 0) continue;
                this.v.add(a2);
            }
        }

        @Override
        protected int b() {
            return this.v.size();
        }

        @Override
        protected void a(int n2, boolean bl2, int n3, int n4) {
        }

        @Override
        protected boolean a(int n2) {
            return false;
        }

        @Override
        protected int k() {
            return this.b() * ((ayf)ayf.this).q.a * 4;
        }

        @Override
        protected void a() {
            ayf.this.c();
        }

        @Override
        protected void a(int n2, int n3, int n4, int n5, int n6, int n7) {
            pm.a a2 = this.v.get(n2);
            String \u26032 = bnq.a("entity." + pm.b(a2.a) + ".name", new Object[0]);
            int \u26033 = ayf.this.s.a(a2.d);
            int \u26034 = ayf.this.s.a(a2.e);
            String \u26035 = bnq.a("stat.entityKills", \u26033, \u26032);
            String \u26036 = bnq.a("stat.entityKilledBy", \u26032, \u26034);
            if (\u26033 == 0) {
                \u26035 = bnq.a("stat.entityKills.none", \u26032);
            }
            if (\u26034 == 0) {
                \u26036 = bnq.a("stat.entityKilledBy.none", \u26032);
            }
            ayf.this.c(ayf.this.q, \u26032, n3 + 2 - 10, n4 + 1, 0xFFFFFF);
            ayf.this.c(ayf.this.q, \u26035, n3 + 2, n4 + 1 + ((ayf)ayf.this).q.a, \u26033 == 0 ? 0x606060 : 0x909090);
            ayf.this.c(ayf.this.q, \u26036, n3 + 2, n4 + 1 + ((ayf)ayf.this).q.a * 2, \u26034 == 0 ? 0x606060 : 0x909090);
        }
    }

    class a
    extends e {
        public a(ave ave2) {
            super(ave2);
            this.w = Lists.newArrayList();
            for (mu mu2 : na.e) {
                boolean bl2 = false;
                int \u26032 = zw.b(mu2.a());
                if (ayf.this.s.a(mu2) > 0) {
                    bl2 = true;
                } else if (na.ad[\u26032] != null && ayf.this.s.a(na.ad[\u26032]) > 0) {
                    bl2 = true;
                } else if (na.ac[\u26032] != null && ayf.this.s.a(na.ac[\u26032]) > 0) {
                    bl2 = true;
                }
                if (!bl2) continue;
                this.w.add(mu2);
            }
            this.x = new Comparator<mu>(){

                public int a(mu mu2, mu mu3) {
                    int n2 = zw.b(mu2.a());
                    \u2603 = zw.b(mu3.a());
                    mw \u26032 = null;
                    mw \u26033 = null;
                    if (a.this.y == 2) {
                        \u26032 = na.ab[n2];
                        \u26033 = na.ab[\u2603];
                    } else if (a.this.y == 0) {
                        \u26032 = na.ac[n2];
                        \u26033 = na.ac[\u2603];
                    } else if (a.this.y == 1) {
                        \u26032 = na.ad[n2];
                        \u26033 = na.ad[\u2603];
                    }
                    if (\u26032 != null || \u26033 != null) {
                        if (\u26032 == null) {
                            return 1;
                        }
                        if (\u26033 == null) {
                            return -1;
                        }
                        \u2603 = ayf.this.s.a(\u26032);
                        if (\u2603 != (\u2603 = ayf.this.s.a(\u26033))) {
                            return (\u2603 - \u2603) * a.this.z;
                        }
                    }
                    return n2 - \u2603;
                }

                @Override
                public /* synthetic */ int compare(Object object, Object object2) {
                    return this.a((mu)object, (mu)object2);
                }
            };
        }

        @Override
        protected void a(int n2, int n3, bfx bfx2) {
            super.a(n2, n3, bfx2);
            if (this.v == 0) {
                ayf.this.c(n2 + 115 - 18 + 1, n3 + 1 + 1, 18, 18);
            } else {
                ayf.this.c(n2 + 115 - 18, n3 + 1, 18, 18);
            }
            if (this.v == 1) {
                ayf.this.c(n2 + 165 - 18 + 1, n3 + 1 + 1, 36, 18);
            } else {
                ayf.this.c(n2 + 165 - 18, n3 + 1, 36, 18);
            }
            if (this.v == 2) {
                ayf.this.c(n2 + 215 - 18 + 1, n3 + 1 + 1, 54, 18);
            } else {
                ayf.this.c(n2 + 215 - 18, n3 + 1, 54, 18);
            }
        }

        @Override
        protected void a(int n2, int n3, int n4, int n5, int n6, int n7) {
            mu mu2 = this.c(n2);
            zw \u26032 = mu2.a();
            ayf.this.a(n3 + 40, n4, \u26032);
            int \u26033 = zw.b(\u26032);
            this.a(na.ac[\u26033], n3 + 115, n4, n2 % 2 == 0);
            this.a(na.ad[\u26033], n3 + 165, n4, n2 % 2 == 0);
            this.a(mu2, n3 + 215, n4, n2 % 2 == 0);
        }

        @Override
        protected String b(int n2) {
            if (n2 == 0) {
                return "stat.crafted";
            }
            if (n2 == 1) {
                return "stat.used";
            }
            return "stat.mined";
        }
    }

    class c
    extends e {
        public c(ave ave2) {
            super(ave2);
            this.w = Lists.newArrayList();
            for (mu mu2 : na.d) {
                boolean bl2 = false;
                int \u26032 = zw.b(mu2.a());
                if (ayf.this.s.a(mu2) > 0) {
                    bl2 = true;
                } else if (na.ae[\u26032] != null && ayf.this.s.a(na.ae[\u26032]) > 0) {
                    bl2 = true;
                } else if (na.ac[\u26032] != null && ayf.this.s.a(na.ac[\u26032]) > 0) {
                    bl2 = true;
                }
                if (!bl2) continue;
                this.w.add(mu2);
            }
            this.x = new Comparator<mu>(){

                public int a(mu mu2, mu mu3) {
                    int n2 = zw.b(mu2.a());
                    \u2603 = zw.b(mu3.a());
                    mw \u26032 = null;
                    mw \u26033 = null;
                    if (c.this.y == 0) {
                        \u26032 = na.ae[n2];
                        \u26033 = na.ae[\u2603];
                    } else if (c.this.y == 1) {
                        \u26032 = na.ac[n2];
                        \u26033 = na.ac[\u2603];
                    } else if (c.this.y == 2) {
                        \u26032 = na.ad[n2];
                        \u26033 = na.ad[\u2603];
                    }
                    if (\u26032 != null || \u26033 != null) {
                        if (\u26032 == null) {
                            return 1;
                        }
                        if (\u26033 == null) {
                            return -1;
                        }
                        \u2603 = ayf.this.s.a(\u26032);
                        if (\u2603 != (\u2603 = ayf.this.s.a(\u26033))) {
                            return (\u2603 - \u2603) * c.this.z;
                        }
                    }
                    return n2 - \u2603;
                }

                @Override
                public /* synthetic */ int compare(Object object, Object object2) {
                    return this.a((mu)object, (mu)object2);
                }
            };
        }

        @Override
        protected void a(int n2, int n3, bfx bfx2) {
            super.a(n2, n3, bfx2);
            if (this.v == 0) {
                ayf.this.c(n2 + 115 - 18 + 1, n3 + 1 + 1, 72, 18);
            } else {
                ayf.this.c(n2 + 115 - 18, n3 + 1, 72, 18);
            }
            if (this.v == 1) {
                ayf.this.c(n2 + 165 - 18 + 1, n3 + 1 + 1, 18, 18);
            } else {
                ayf.this.c(n2 + 165 - 18, n3 + 1, 18, 18);
            }
            if (this.v == 2) {
                ayf.this.c(n2 + 215 - 18 + 1, n3 + 1 + 1, 36, 18);
            } else {
                ayf.this.c(n2 + 215 - 18, n3 + 1, 36, 18);
            }
        }

        @Override
        protected void a(int n2, int n3, int n4, int n5, int n6, int n7) {
            mu mu2 = this.c(n2);
            zw \u26032 = mu2.a();
            ayf.this.a(n3 + 40, n4, \u26032);
            int \u26033 = zw.b(\u26032);
            this.a(na.ae[\u26033], n3 + 115, n4, n2 % 2 == 0);
            this.a(na.ac[\u26033], n3 + 165, n4, n2 % 2 == 0);
            this.a(mu2, n3 + 215, n4, n2 % 2 == 0);
        }

        @Override
        protected String b(int n2) {
            if (n2 == 1) {
                return "stat.crafted";
            }
            if (n2 == 2) {
                return "stat.used";
            }
            return "stat.depleted";
        }
    }

    abstract class e
    extends awi {
        protected int v;
        protected List<mu> w;
        protected Comparator<mu> x;
        protected int y;
        protected int z;

        protected e(ave ave2) {
            super(ave2, ayf.this.l, ayf.this.m, 32, ayf.this.m - 64, 20);
            this.v = -1;
            this.y = -1;
            this.b(false);
            this.a(true, 20);
        }

        @Override
        protected void a(int n2, boolean bl2, int n3, int n4) {
        }

        @Override
        protected boolean a(int n2) {
            return false;
        }

        @Override
        protected void a() {
            ayf.this.c();
        }

        @Override
        protected void a(int n2, int n3, bfx bfx2) {
            if (!Mouse.isButtonDown(0)) {
                this.v = -1;
            }
            if (this.v == 0) {
                ayf.this.c(n2 + 115 - 18, n3 + 1, 0, 0);
            } else {
                ayf.this.c(n2 + 115 - 18, n3 + 1, 0, 18);
            }
            if (this.v == 1) {
                ayf.this.c(n2 + 165 - 18, n3 + 1, 0, 0);
            } else {
                ayf.this.c(n2 + 165 - 18, n3 + 1, 0, 18);
            }
            if (this.v == 2) {
                ayf.this.c(n2 + 215 - 18, n3 + 1, 0, 0);
            } else {
                ayf.this.c(n2 + 215 - 18, n3 + 1, 0, 18);
            }
            if (this.y != -1) {
                int n4 = 79;
                \u2603 = 18;
                if (this.y == 1) {
                    n4 = 129;
                } else if (this.y == 2) {
                    n4 = 179;
                }
                if (this.z == 1) {
                    \u2603 = 36;
                }
                ayf.this.c(n2 + n4, n3 + 1, \u2603, 0);
            }
        }

        @Override
        protected void a(int n2, int n3) {
            this.v = -1;
            if (n2 >= 79 && n2 < 115) {
                this.v = 0;
            } else if (n2 >= 129 && n2 < 165) {
                this.v = 1;
            } else if (n2 >= 179 && n2 < 215) {
                this.v = 2;
            }
            if (this.v >= 0) {
                this.d(this.v);
                this.a.W().a(bpf.a(new jy("gui.button.press"), 1.0f));
            }
        }

        @Override
        protected final int b() {
            return this.w.size();
        }

        protected final mu c(int n2) {
            return this.w.get(n2);
        }

        protected abstract String b(int var1);

        protected void a(mw mw2, int n2, int n3, boolean bl2) {
            if (mw2 != null) {
                String string = mw2.a(ayf.this.s.a(mw2));
                ayf.this.c(ayf.this.q, string, n2 - ayf.this.q.a(string), n3 + 5, bl2 ? 0xFFFFFF : 0x909090);
            } else {
                String string = "-";
                ayf.this.c(ayf.this.q, string, n2 - ayf.this.q.a(string), n3 + 5, bl2 ? 0xFFFFFF : 0x909090);
            }
        }

        @Override
        protected void b(int n2, int n3) {
            if (n3 < this.d || n3 > this.e) {
                return;
            }
            \u2603 = this.c(n2, n3);
            \u2603 = this.b / 2 - 92 - 16;
            if (\u2603 >= 0) {
                if (n2 < \u2603 + 40 || n2 > \u2603 + 40 + 20) {
                    return;
                }
                mu mu2 = this.c(\u2603);
                this.a(mu2, n2, n3);
            } else {
                String string = "";
                if (n2 >= \u2603 + 115 - 18 && n2 <= \u2603 + 115) {
                    string = this.b(0);
                } else if (n2 >= \u2603 + 165 - 18 && n2 <= \u2603 + 165) {
                    string = this.b(1);
                } else if (n2 >= \u2603 + 215 - 18 && n2 <= \u2603 + 215) {
                    string = this.b(2);
                } else {
                    return;
                }
                string = ("" + bnq.a(string, new Object[0])).trim();
                if (string.length() > 0) {
                    int n4 = n2 + 12;
                    \u2603 = n3 - 12;
                    \u2603 = ayf.this.q.a(string);
                    ayf.this.a(n4 - 3, \u2603 - 3, n4 + \u2603 + 3, \u2603 + 8 + 3, -1073741824, -1073741824);
                    ayf.this.q.a(string, (float)n4, (float)\u2603, -1);
                }
            }
        }

        protected void a(mu mu2, int n2, int n3) {
            if (mu2 == null) {
                return;
            }
            zw zw2 = mu2.a();
            zx \u26032 = new zx(zw2);
            String \u26033 = \u26032.a();
            String \u26034 = ("" + bnq.a(\u26033 + ".name", new Object[0])).trim();
            if (\u26034.length() > 0) {
                int n4 = n2 + 12;
                \u2603 = n3 - 12;
                \u2603 = ayf.this.q.a(\u26034);
                ayf.this.a(n4 - 3, \u2603 - 3, n4 + \u2603 + 3, \u2603 + 8 + 3, -1073741824, -1073741824);
                ayf.this.q.a(\u26034, (float)n4, (float)\u2603, -1);
            }
        }

        protected void d(int n2) {
            if (n2 != this.y) {
                this.y = n2;
                this.z = -1;
            } else if (this.z == -1) {
                this.z = 1;
            } else {
                this.y = -1;
                this.z = 0;
            }
            Collections.sort(this.w, this.x);
        }
    }

    class b
    extends awi {
        public b(ave ave2) {
            super(ave2, ayf.this.l, ayf.this.m, 32, ayf.this.m - 64, 10);
            this.b(false);
        }

        @Override
        protected int b() {
            return na.c.size();
        }

        @Override
        protected void a(int n2, boolean bl2, int n3, int n4) {
        }

        @Override
        protected boolean a(int n2) {
            return false;
        }

        @Override
        protected int k() {
            return this.b() * 10;
        }

        @Override
        protected void a() {
            ayf.this.c();
        }

        @Override
        protected void a(int n2, int n3, int n4, int n5, int n6, int n7) {
            mw mw2 = na.c.get(n2);
            ayf.this.c(ayf.this.q, mw2.e().c(), n3 + 2, n4 + 1, n2 % 2 == 0 ? 0xFFFFFF : 0x909090);
            String \u26032 = mw2.a(ayf.this.s.a(mw2));
            ayf.this.c(ayf.this.q, \u26032, n3 + 2 + 213 - ayf.this.q.a(\u26032), n4 + 1, n2 % 2 == 0 ? 0xFFFFFF : 0x909090);
        }
    }
}

