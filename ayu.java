/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class ayu
extends ayw {
    private static final jy u = new jy("textures/gui/container/creative_inventory/tabs.png");
    private static oq v = new oq("tmp", true, 45);
    private static int w = yz.b.a();
    private float x;
    private boolean y;
    private boolean z;
    private avw A;
    private List<yg> B;
    private yg C;
    private boolean D;
    private ayt E;

    public ayu(wn wn2) {
        super(new a(wn2));
        wn2.bk = this.h;
        this.p = true;
        this.g = 136;
        this.f = 195;
    }

    @Override
    public void e() {
        if (!this.j.c.h()) {
            this.j.a(new azc(this.j.h));
        }
        this.a();
    }

    @Override
    protected void a(yg yg22, int n2, int n32, int n42) {
        this.D = true;
        boolean bl2 = n42 == 1;
        int n5 = n42 = n2 == -999 && n42 == 0 ? 4 : n42;
        if (yg22 != null || w == yz.m.a() || n42 == 5) {
            int n42;
            yg yg22;
            if (yg22 == this.C && bl2) {
                for (int i2 = 0; i2 < this.j.h.bj.a().size(); ++i2) {
                    this.j.c.a(null, i2);
                }
            } else if (w == yz.m.a()) {
                if (yg22 == this.C) {
                    this.j.h.bi.b((zx)null);
                } else if (n42 == 4 && yg22 != null && yg22.e()) {
                    zx zx2 = yg22.a(n32 == 0 ? 1 : yg22.d().c());
                    this.j.h.a(zx2, true);
                    this.j.c.a(zx2);
                } else if (n42 == 4 && this.j.h.bi.p() != null) {
                    this.j.h.a(this.j.h.bi.p(), true);
                    this.j.c.a(this.j.h.bi.p());
                    this.j.h.bi.b((zx)null);
                } else {
                    this.j.h.bj.a(yg22 == null ? n2 : ((b)((b)yg22)).b.e, n32, n42, (wn)this.j.h);
                    this.j.h.bj.b();
                }
            } else if (n42 != 5 && yg22.d == v) {
                wm wm2 = this.j.h.bi;
                zx \u26032 = wm2.p();
                zx \u26033 = yg22.d();
                if (n42 == 2) {
                    if (\u26033 != null && n32 >= 0 && n32 < 9) {
                        zx zx3 = \u26033.k();
                        zx3.b = zx3.c();
                        this.j.h.bi.a(n32, zx3);
                        this.j.h.bj.b();
                    }
                    return;
                }
                if (n42 == 3) {
                    if (wm2.p() == null && yg22.e()) {
                        zx zx4 = yg22.d().k();
                        zx4.b = zx4.c();
                        wm2.b(zx4);
                    }
                    return;
                }
                if (n42 == 4) {
                    if (\u26033 != null) {
                        zx zx5 = \u26033.k();
                        zx5.b = n32 == 0 ? 1 : zx5.c();
                        this.j.h.a(zx5, true);
                        this.j.c.a(zx5);
                    }
                    return;
                }
                if (\u26032 != null && \u26033 != null && \u26032.a(\u26033)) {
                    int n32;
                    if (n32 == 0) {
                        if (bl2) {
                            \u26032.b = \u26032.c();
                        } else if (\u26032.b < \u26032.c()) {
                            ++\u26032.b;
                        }
                    } else if (\u26032.b <= 1) {
                        wm2.b((zx)null);
                    } else {
                        --\u26032.b;
                    }
                } else if (\u26033 == null || \u26032 != null) {
                    wm2.b((zx)null);
                } else {
                    wm2.b(zx.b(\u26033));
                    \u26032 = wm2.p();
                    if (bl2) {
                        \u26032.b = \u26032.c();
                    }
                }
            } else {
                this.h.a(yg22 == null ? n2 : yg22.e, n32, n42, (wn)this.j.h);
                if (xi.c(n32) == 2) {
                    for (int i3 = 0; i3 < 9; ++i3) {
                        this.j.c.a(this.h.a(45 + i3).d(), 36 + i3);
                    }
                } else if (yg22 != null) {
                    zx zx6 = this.h.a(yg22.e).d();
                    this.j.c.a(zx6, yg22.e - this.h.c.size() + 9 + 36);
                }
            }
        } else {
            wm wm3 = this.j.h.bi;
            if (wm3.p() != null) {
                if (n32 == 0) {
                    this.j.h.a(wm3.p(), true);
                    this.j.c.a(wm3.p());
                    wm3.b((zx)null);
                }
                if (n32 == 1) {
                    zx zx7 = wm3.p().a(1);
                    this.j.h.a(zx7, true);
                    this.j.c.a(zx7);
                    if (wm3.p().b == 0) {
                        wm3.b((zx)null);
                    }
                }
            }
        }
    }

    @Override
    protected void a() {
        int n2 = this.i;
        super.a();
        if (this.A != null && this.i != n2) {
            this.A.a = this.i + 82;
        }
    }

    @Override
    public void b() {
        if (this.j.c.h()) {
            super.b();
            this.n.clear();
            Keyboard.enableRepeatEvents(true);
            this.A = new avw(0, this.q, this.i + 82, this.r + 6, 89, this.q.a);
            this.A.f(15);
            this.A.a(false);
            this.A.e(false);
            this.A.g(0xFFFFFF);
            int n2 = w;
            w = -1;
            this.b(yz.a[n2]);
            this.E = new ayt(this.j);
            this.j.h.bj.a(this.E);
        } else {
            this.j.a(new azc(this.j.h));
        }
    }

    @Override
    public void m() {
        super.m();
        if (this.j.h != null && this.j.h.bi != null) {
            this.j.h.bj.b(this.E);
        }
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void a(char c2, int n2) {
        if (w != yz.g.a()) {
            if (avh.a(this.j.t.aj)) {
                this.b(yz.g);
            } else {
                super.a(c2, n2);
            }
            return;
        }
        if (this.D) {
            this.D = false;
            this.A.a("");
        }
        if (this.b(n2)) {
            return;
        }
        if (this.A.a(c2, n2)) {
            this.h();
        } else {
            super.a(c2, n2);
        }
    }

    private void h() {
        a a2 = (a)this.h;
        a2.a.clear();
        for (zw zw2 : zw.e) {
            if (zw2 == null || zw2.c() == null) continue;
            zw2.a(zw2, null, a2.a);
        }
        for (aci aci2 : aci.b) {
            if (aci2 == null || aci2.C == null) continue;
            zy.cd.a(aci2, a2.a);
        }
        Iterator<zx> \u26032 = a2.a.iterator();
        String \u26033 = this.A.b().toLowerCase();
        while (\u26032.hasNext()) {
            zx zx2 = (zx)\u26032.next();
            boolean \u26034 = false;
            for (String string : zx2.a((wn)this.j.h, this.j.t.y)) {
                if (!a.a(string).toLowerCase().contains(\u26033)) continue;
                \u26034 = true;
                break;
            }
            if (\u26034) continue;
            \u26032.remove();
        }
        this.x = 0.0f;
        a2.a(0.0f);
    }

    @Override
    protected void b(int n2, int n3) {
        yz yz2 = yz.a[w];
        if (yz2.h()) {
            bfl.k();
            this.q.a(bnq.a(yz2.c(), new Object[0]), 8, 6, 0x404040);
        }
    }

    @Override
    protected void a(int n22, int n3, int n4) {
        int n22;
        if (n4 == 0) {
            \u2603 = n22 - this.i;
            \u2603 = n3 - this.r;
            for (yz yz2 : yz.a) {
                if (!this.a(yz2, \u2603, \u2603)) continue;
                return;
            }
        }
        super.a(n22, n3, n4);
    }

    @Override
    protected void b(int n22, int n3, int n4) {
        int n22;
        if (n4 == 0) {
            \u2603 = n22 - this.i;
            \u2603 = n3 - this.r;
            for (yz yz2 : yz.a) {
                if (!this.a(yz2, \u2603, \u2603)) continue;
                this.b(yz2);
                return;
            }
        }
        super.b(n22, n3, n4);
    }

    private boolean i() {
        return w != yz.m.a() && yz.a[w].j() && ((a)this.h).e();
    }

    private void b(yz yz2) {
        int n2 = w;
        w = yz2.a();
        a \u26032 = (a)this.h;
        this.s.clear();
        \u26032.a.clear();
        yz2.a(\u26032.a);
        if (yz2 == yz.m) {
            xi xi2 = this.j.h.bj;
            if (this.B == null) {
                this.B = \u26032.c;
            }
            \u26032.c = Lists.newArrayList();
            for (int i2 = 0; i2 < xi2.c.size(); ++i2) {
                b b2 = new b(xi2.c.get(i2), i2);
                \u26032.c.add(b2);
                if (i2 >= 5 && i2 < 9) {
                    int n3 = i2 - 5;
                    \u2603 = n3 / 2;
                    \u2603 = n3 % 2;
                    b2.f = 9 + \u2603 * 54;
                    b2.g = 6 + \u2603 * 27;
                    continue;
                }
                if (i2 >= 0 && i2 < 5) {
                    b2.g = -2000;
                    b2.f = -2000;
                    continue;
                }
                if (i2 >= xi2.c.size()) continue;
                n3 = i2 - 9;
                \u2603 = n3 % 9;
                \u2603 = n3 / 9;
                b2.f = 9 + \u2603 * 18;
                b2.g = i2 >= 36 ? 112 : 54 + \u2603 * 18;
            }
            this.C = new yg(v, 0, 173, 112);
            \u26032.c.add(this.C);
        } else if (n2 == yz.m.a()) {
            \u26032.c = this.B;
            this.B = null;
        }
        if (this.A != null) {
            if (yz2 == yz.g) {
                this.A.e(true);
                this.A.d(false);
                this.A.b(true);
                this.A.a("");
                this.h();
            } else {
                this.A.e(false);
                this.A.d(true);
                this.A.b(false);
            }
        }
        this.x = 0.0f;
        \u26032.a(0.0f);
    }

    @Override
    public void k() {
        super.k();
        int n2 = Mouse.getEventDWheel();
        if (n2 != 0 && this.i()) {
            \u2603 = ((a)this.h).a.size() / 9 - 5;
            if (n2 > 0) {
                n2 = 1;
            }
            if (n2 < 0) {
                n2 = -1;
            }
            this.x = (float)((double)this.x - (double)n2 / (double)\u2603);
            this.x = ns.a(this.x, 0.0f, 1.0f);
            ((a)this.h).a(this.x);
        }
    }

    @Override
    public void a(int n22, int n3, float f2) {
        int n22;
        boolean bl2 = Mouse.isButtonDown(0);
        int \u26032 = this.i;
        int \u26033 = this.r;
        int \u26034 = \u26032 + 175;
        int \u26035 = \u26033 + 18;
        int \u26036 = \u26034 + 14;
        int \u26037 = \u26035 + 112;
        if (!this.z && bl2 && n22 >= \u26034 && n3 >= \u26035 && n22 < \u26036 && n3 < \u26037) {
            this.y = this.i();
        }
        if (!bl2) {
            this.y = false;
        }
        this.z = bl2;
        if (this.y) {
            this.x = ((float)(n3 - \u26035) - 7.5f) / ((float)(\u26037 - \u26035) - 15.0f);
            this.x = ns.a(this.x, 0.0f, 1.0f);
            ((a)this.h).a(this.x);
        }
        super.a(n22, n3, f2);
        for (yz yz2 : yz.a) {
            if (this.b(yz2, n22, n3)) break;
        }
        if (this.C != null && w == yz.m.a() && this.c(this.C.f, this.C.g, 16, 16, n22, n3)) {
            this.a(bnq.a("inventory.binSlot", new Object[0]), n22, n3);
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.f();
    }

    @Override
    protected void a(zx zx22, int n2, int n3) {
        if (w == yz.g.a()) {
            List<String> list = zx22.a((wn)this.j.h, this.j.t.y);
            yz \u26032 = zx22.b().c();
            if (\u26032 == null && zx22.b() == zy.cd && (\u2603 = ack.a(zx22)).size() == 1) {
                aci aci2 = aci.c(\u2603.keySet().iterator().next());
                for (yz yz2 : yz.a) {
                    if (!yz2.a(aci2.C)) continue;
                    \u26032 = yz2;
                    break;
                }
            }
            if (\u26032 != null) {
                list.add(1, "" + (Object)((Object)a.r) + (Object)((Object)a.j) + bnq.a(\u26032.c(), new Object[0]));
            }
            for (int i2 = 0; i2 < list.size(); ++i2) {
                if (i2 == 0) {
                    list.set(i2, (Object)((Object)zx22.u().e) + list.get(i2));
                    continue;
                }
                list.set(i2, (Object)((Object)a.h) + list.get(i2));
            }
            this.a(list, n2, n3);
        } else {
            zx zx22;
            super.a(zx22, n2, n3);
        }
    }

    @Override
    protected void a(float f2, int n2, int n3) {
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        avc.c();
        yz yz2 = yz.a[w];
        for (yz yz3 : yz.a) {
            this.j.P().a(u);
            if (yz3.a() == w) continue;
            this.a(yz3);
        }
        this.j.P().a(new jy("textures/gui/container/creative_inventory/tab_" + yz2.g()));
        this.b(this.i, this.r, 0, 0, this.f, this.g);
        this.A.g();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        int \u26032 = this.i + 175;
        int \u26033 = this.r + 18;
        int \u26034 = \u26033 + 112;
        this.j.P().a(u);
        if (yz2.j()) {
            this.b(\u26032, \u26033 + (int)((float)(\u26034 - \u26033 - 17) * this.x), 232 + (this.i() ? 0 : 12), 0, 12, 15);
        }
        this.a(yz2);
        if (yz2 == yz.m) {
            azc.a(this.i + 43, this.r + 45, 20, this.i + 43 - n2, this.r + 45 - 30 - n3, this.j.h);
        }
    }

    protected boolean a(yz yz2, int n2, int n3) {
        \u2603 = yz2.l();
        \u2603 = 28 * \u2603;
        \u2603 = 0;
        if (\u2603 == 5) {
            \u2603 = this.f - 28 + 2;
        } else if (\u2603 > 0) {
            \u2603 += \u2603;
        }
        \u2603 = yz2.m() ? (\u2603 -= 32) : (\u2603 += this.g);
        return n2 >= \u2603 && n2 <= \u2603 + 28 && n3 >= \u2603 && n3 <= \u2603 + 32;
    }

    protected boolean b(yz yz2, int n2, int n3) {
        \u2603 = yz2.l();
        \u2603 = 28 * \u2603;
        \u2603 = 0;
        if (\u2603 == 5) {
            \u2603 = this.f - 28 + 2;
        } else if (\u2603 > 0) {
            \u2603 += \u2603;
        }
        \u2603 = yz2.m() ? (\u2603 -= 32) : (\u2603 += this.g);
        if (this.c(\u2603 + 3, \u2603 + 3, 23, 27, n2, n3)) {
            this.a(bnq.a(yz2.c(), new Object[0]), n2, n3);
            return true;
        }
        return false;
    }

    protected void a(yz yz2) {
        boolean bl2 = yz2.a() == w;
        \u2603 = yz2.m();
        int \u26032 = yz2.l();
        int \u26033 = \u26032 * 28;
        int \u26034 = 0;
        int \u26035 = this.i + 28 * \u26032;
        int \u26036 = this.r;
        int \u26037 = 32;
        if (bl2) {
            \u26034 += 32;
        }
        if (\u26032 == 5) {
            \u26035 = this.i + this.f - 28;
        } else if (\u26032 > 0) {
            \u26035 += \u26032;
        }
        if (\u2603) {
            \u26036 -= 28;
        } else {
            \u26034 += 64;
            \u26036 += this.g - 4;
        }
        bfl.f();
        this.b(\u26035, \u26036, \u26033, \u26034, 28, \u26037);
        this.e = 100.0f;
        this.k.a = 100.0f;
        int n2 = \u2603 ? 1 : -1;
        bfl.e();
        bfl.B();
        zx \u26038 = yz2.d();
        this.k.b(\u26038, \u26035 += 6, \u26036 += 8 + n2);
        this.k.a(this.q, \u26038, \u26035, \u26036);
        bfl.f();
        this.k.a = 0.0f;
        this.e = 0.0f;
    }

    @Override
    protected void a(avs avs2) {
        if (avs2.k == 0) {
            this.j.a(new aye(this, this.j.h.x()));
        }
        if (avs2.k == 1) {
            this.j.a(new ayf(this, this.j.h.x()));
        }
    }

    public int f() {
        return w;
    }

    class b
    extends yg {
        private final yg b;

        public b(yg yg2, int n2) {
            super(yg2.d, n2, 0, 0);
            this.b = yg2;
        }

        @Override
        public void a(wn wn2, zx zx2) {
            this.b.a(wn2, zx2);
        }

        @Override
        public boolean a(zx zx2) {
            return this.b.a(zx2);
        }

        @Override
        public zx d() {
            return this.b.d();
        }

        @Override
        public boolean e() {
            return this.b.e();
        }

        @Override
        public void d(zx zx2) {
            this.b.d(zx2);
        }

        @Override
        public void f() {
            this.b.f();
        }

        @Override
        public int a() {
            return this.b.a();
        }

        @Override
        public int b(zx zx2) {
            return this.b.b(zx2);
        }

        @Override
        public String c() {
            return this.b.c();
        }

        @Override
        public zx a(int n2) {
            return this.b.a(n2);
        }

        @Override
        public boolean a(og og2, int n2) {
            return this.b.a(og2, n2);
        }
    }

    static class a
    extends xi {
        public List<zx> a = Lists.newArrayList();

        public a(wn wn2) {
            int n2;
            wm wm2 = wn2.bi;
            for (n2 = 0; n2 < 5; ++n2) {
                for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                    this.a(new yg(v, n2 * 9 + \u2603, 9 + \u2603 * 18, 18 + n2 * 18));
                }
            }
            for (n2 = 0; n2 < 9; ++n2) {
                this.a(new yg(wm2, n2, 9 + n2 * 18, 112));
            }
            this.a(0.0f);
        }

        @Override
        public boolean a(wn wn2) {
            return true;
        }

        public void a(float f2) {
            int n2 = (this.a.size() + 9 - 1) / 9 - 5;
            \u2603 = (int)((double)(f2 * (float)n2) + 0.5);
            if (\u2603 < 0) {
                \u2603 = 0;
            }
            for (\u2603 = 0; \u2603 < 5; ++\u2603) {
                for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                    \u2603 = \u2603 + (\u2603 + \u2603) * 9;
                    if (\u2603 >= 0 && \u2603 < this.a.size()) {
                        v.a(\u2603 + \u2603 * 9, this.a.get(\u2603));
                        continue;
                    }
                    v.a(\u2603 + \u2603 * 9, null);
                }
            }
        }

        public boolean e() {
            return this.a.size() > 45;
        }

        @Override
        protected void a(int n2, int n3, boolean bl2, wn wn2) {
        }

        @Override
        public zx b(wn wn2, int n2) {
            if (n2 >= this.c.size() - 9 && n2 < this.c.size() && (\u2603 = (yg)this.c.get(n2)) != null && \u2603.e()) {
                \u2603.d(null);
            }
            return null;
        }

        @Override
        public boolean a(zx zx2, yg yg2) {
            return yg2.g > 90;
        }

        @Override
        public boolean b(yg yg2) {
            return yg2.d instanceof wm || yg2.g > 90 && yg2.f <= 162;
        }
    }
}

