/*
 * Decompiled with CFR 0.152.
 */
import io.netty.buffer.Unpooled;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ayn
extends ayl {
    private static final Logger u = LogManager.getLogger();
    private static final jy v = new jy("textures/gui/container/beacon.png");
    private og w;
    private b x;
    private boolean y;

    public ayn(wm wm2, og og2) {
        super(new xl(wm2, og2));
        this.w = og2;
        this.f = 230;
        this.g = 219;
    }

    @Override
    public void b() {
        super.b();
        this.x = new b(-1, this.i + 164, this.r + 107);
        this.n.add(this.x);
        this.n.add(new a(-2, this.i + 190, this.r + 107));
        this.y = true;
        this.x.l = false;
    }

    @Override
    public void e() {
        int n2;
        super.e();
        int n3 = this.w.a_(0);
        n2 = this.w.a_(1);
        \u2603 = this.w.a_(2);
        if (this.y && n3 >= 0) {
            c c2;
            this.y = false;
            for (n4 = 0; n4 <= 2; ++n4) {
                \u2603 = akv.a[n4].length;
                \u2603 = \u2603 * 22 + (\u2603 - 1) * 2;
                for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
                    \u2603 = akv.a[n4][\u2603].H;
                    c2 = new c(n4 << 8 | \u2603, this.i + 76 + \u2603 * 24 - \u2603 / 2, this.r + 22 + n4 * 25, \u2603, n4);
                    this.n.add(c2);
                    if (n4 >= n3) {
                        c2.l = false;
                        continue;
                    }
                    if (\u2603 != n2) continue;
                    c2.b(true);
                }
            }
            int n4 = 3;
            \u2603 = akv.a[n4].length + 1;
            \u2603 = \u2603 * 22 + (\u2603 - 1) * 2;
            for (\u2603 = 0; \u2603 < \u2603 - 1; ++\u2603) {
                \u2603 = akv.a[n4][\u2603].H;
                c2 = new c(n4 << 8 | \u2603, this.i + 167 + \u2603 * 24 - \u2603 / 2, this.r + 47, \u2603, n4);
                this.n.add(c2);
                if (n4 >= n3) {
                    c2.l = false;
                    continue;
                }
                if (\u2603 != \u2603) continue;
                c2.b(true);
            }
            if (n2 > 0) {
                c c3 = new c(n4 << 8 | n2, this.i + 167 + (\u2603 - 1) * 24 - \u2603 / 2, this.r + 47, n2, n4);
                this.n.add(c3);
                if (n4 >= n3) {
                    c3.l = false;
                } else if (n2 == \u2603) {
                    c3.b(true);
                }
            }
        }
        this.x.l = this.w.a(0) != null && n2 > 0;
    }

    @Override
    protected void a(avs avs2) {
        if (avs2.k == -2) {
            this.j.a((axu)null);
        } else if (avs2.k == -1) {
            String string = "MC|Beacon";
            em \u26032 = new em(Unpooled.buffer());
            \u26032.writeInt(this.w.a_(1));
            \u26032.writeInt(this.w.a_(2));
            this.j.u().a(new im(string, \u26032));
            this.j.a((axu)null);
        } else if (avs2 instanceof c) {
            if (((c)avs2).c()) {
                return;
            }
            int \u26033 = avs2.k;
            int \u26034 = \u26033 & 0xFF;
            int \u26035 = \u26033 >> 8;
            if (\u26035 < 3) {
                this.w.b(1, \u26034);
            } else {
                this.w.b(2, \u26034);
            }
            this.n.clear();
            this.b();
            this.e();
        }
    }

    @Override
    protected void b(int n2, int n3) {
        avc.a();
        this.a(this.q, bnq.a("tile.beacon.primary", new Object[0]), 62, 10, 0xE0E0E0);
        this.a(this.q, bnq.a("tile.beacon.secondary", new Object[0]), 169, 10, 0xE0E0E0);
        for (avs avs2 : this.n) {
            if (!avs2.a()) continue;
            avs2.b(n2 - this.i, n3 - this.r);
            break;
        }
        avc.c();
    }

    @Override
    protected void a(float f2, int n2, int n3) {
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(v);
        \u2603 = (this.l - this.f) / 2;
        \u2603 = (this.m - this.g) / 2;
        this.b(\u2603, \u2603, 0, 0, this.f, this.g);
        this.k.a = 100.0f;
        this.k.b(new zx(zy.bO), \u2603 + 42, \u2603 + 109);
        this.k.b(new zx(zy.i), \u2603 + 42 + 22, \u2603 + 109);
        this.k.b(new zx(zy.k), \u2603 + 42 + 44, \u2603 + 109);
        this.k.b(new zx(zy.j), \u2603 + 42 + 66, \u2603 + 109);
        this.k.a = 0.0f;
    }

    class a
    extends d {
        public a(int n2, int n3, int n4) {
            super(n2, n3, n4, v, 112, 220);
        }

        @Override
        public void b(int n2, int n3) {
            ayn.this.a(bnq.a("gui.cancel", new Object[0]), n2, n3);
        }
    }

    class b
    extends d {
        public b(int n2, int n3, int n4) {
            super(n2, n3, n4, v, 90, 220);
        }

        @Override
        public void b(int n2, int n3) {
            ayn.this.a(bnq.a("gui.done", new Object[0]), n2, n3);
        }
    }

    class c
    extends d {
        private final int p;
        private final int q;

        public c(int n2, int n3, int n4, int n5, int n6) {
            super(n2, n3, n4, ayl.a, 0 + pe.a[n5].f() % 8 * 18, 198 + pe.a[n5].f() / 8 * 18);
            this.p = n5;
            this.q = n6;
        }

        @Override
        public void b(int n2, int n3) {
            String string = bnq.a(pe.a[this.p].a(), new Object[0]);
            if (this.q >= 3 && this.p != pe.l.H) {
                string = string + " II";
            }
            ayn.this.a(string, n2, n3);
        }
    }

    static class d
    extends avs {
        private final jy o;
        private final int p;
        private final int q;
        private boolean r;

        protected d(int n2, int n3, int n4, jy jy2, int n5, int n6) {
            super(n2, n3, n4, 22, 22, "");
            this.o = jy2;
            this.p = n5;
            this.q = n6;
        }

        @Override
        public void a(ave ave2, int n2, int n3) {
            if (!this.m) {
                return;
            }
            ave2.P().a(v);
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            this.n = n2 >= this.h && n3 >= this.i && n2 < this.h + this.f && n3 < this.i + this.g;
            \u2603 = 219;
            \u2603 = 0;
            if (!this.l) {
                \u2603 += this.f * 2;
            } else if (this.r) {
                \u2603 += this.f * 1;
            } else if (this.n) {
                \u2603 += this.f * 3;
            }
            this.b(this.h, this.i, \u2603, \u2603, this.f, this.g);
            if (!v.equals(this.o)) {
                ave2.P().a(this.o);
            }
            this.b(this.h + 2, this.i + 2, this.p, this.q, 18, 18);
        }

        public boolean c() {
            return this.r;
        }

        public void b(boolean bl2) {
            this.r = bl2;
        }
    }
}

