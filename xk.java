/*
 * Decompiled with CFR 0.152.
 */
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class xk
extends xi {
    private static final Logger f = LogManager.getLogger();
    private og g = new ye();
    private og h = new oq("Repair", true, 2){

        @Override
        public void p_() {
            super.p_();
            xk.this.a(this);
        }
    };
    private adm i;
    private cj j;
    public int a;
    private int k;
    private String l;
    private final wn m;

    public xk(wm wm2, adm adm2, wn wn2) {
        this(wm2, adm2, cj.a, wn2);
    }

    public xk(wm wm2, final adm adm2, final cj cj2, wn wn2) {
        int n2;
        this.j = cj2;
        this.i = adm2;
        this.m = wn2;
        this.a(new yg(this.h, 0, 27, 47));
        this.a(new yg(this.h, 1, 76, 47));
        this.a(new yg(this.g, 2, 134, 47){

            @Override
            public boolean a(zx zx2) {
                return false;
            }

            @Override
            public boolean a(wn wn2) {
                return (wn2.bA.d || wn2.bB >= xk.this.a) && xk.this.a > 0 && this.e();
            }

            @Override
            public void a(wn wn2, zx zx2) {
                Object object;
                if (!wn2.bA.d) {
                    wn2.a(-xk.this.a);
                }
                xk.this.h.a(0, null);
                if (xk.this.k > 0) {
                    object = xk.this.h.a(1);
                    if (object != null && ((zx)object).b > xk.this.k) {
                        ((zx)object).b -= xk.this.k;
                        xk.this.h.a(1, (zx)object);
                    } else {
                        xk.this.h.a(1, null);
                    }
                } else {
                    xk.this.h.a(1, null);
                }
                xk.this.a = 0;
                object = adm2.p(cj2);
                if (!wn2.bA.d && !adm2.D && object.c() == afi.cf && wn2.bc().nextFloat() < 0.12f) {
                    int n2 = object.b(aez.b);
                    if (++n2 > 2) {
                        adm2.g(cj2);
                        adm2.b(1020, cj2, 0);
                    } else {
                        adm2.a(cj2, object.a(aez.b, n2), 2);
                        adm2.b(1021, cj2, 0);
                    }
                } else if (!adm2.D) {
                    adm2.b(1021, cj2, 0);
                }
            }
        });
        for (n2 = 0; n2 < 3; ++n2) {
            for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                this.a(new yg(wm2, \u2603 + n2 * 9 + 9, 8 + \u2603 * 18, 84 + n2 * 18));
            }
        }
        for (n2 = 0; n2 < 9; ++n2) {
            this.a(new yg(wm2, n2, 8 + n2 * 18, 142));
        }
    }

    @Override
    public void a(og og2) {
        super.a(og2);
        if (og2 == this.h) {
            this.e();
        }
    }

    public void e() {
        int n2;
        boolean bl2 = false;
        \u2603 = true;
        \u2603 = true;
        \u2603 = true;
        int \u26032 = 2;
        \u2603 = true;
        \u2603 = true;
        zx \u26033 = this.h.a(0);
        this.a = 1;
        int \u26034 = 0;
        int \u26035 = 0;
        int \u26036 = 0;
        if (\u26033 == null) {
            this.g.a(0, null);
            this.a = 0;
            return;
        }
        zx \u26037 = \u26033.k();
        zx \u26038 = this.h.a(1);
        Map<Integer, Integer> \u26039 = ack.a(\u26037);
        bl4 = false;
        \u26035 += \u26033.A() + (\u26038 == null ? 0 : \u26038.A());
        this.k = 0;
        if (\u26038 != null) {
            boolean bl3 = bl4 = \u26038.b() == zy.cd && zy.cd.h(\u26038).c() > 0;
            if (\u26037.e() && \u26037.b().a(\u26033, \u26038)) {
                n2 = Math.min(\u26037.h(), \u26037.j() / 4);
                if (n2 <= 0) {
                    this.g.a(0, null);
                    this.a = 0;
                    return;
                }
                for (\u2603 = 0; n2 > 0 && \u2603 < \u26038.b; ++\u2603) {
                    \u2603 = \u26037.h() - n2;
                    \u26037.b(\u2603);
                    ++\u26034;
                    n2 = Math.min(\u26037.h(), \u26037.j() / 4);
                }
                this.k = \u2603;
            } else {
                boolean bl4;
                if (!(bl4 || \u26037.b() == \u26038.b() && \u26037.e())) {
                    this.g.a(0, null);
                    this.a = 0;
                    return;
                }
                if (\u26037.e() && !bl4) {
                    int n3 = \u26033.j() - \u26033.h();
                    \u2603 = \u26038.j() - \u26038.h();
                    n4 = \u2603 + \u26037.j() * 12 / 100;
                    \u2603 = n3 + n4;
                    \u260310 = \u26037.j() - \u2603;
                    if (\u260310 < 0) {
                        \u260310 = 0;
                    }
                    if (\u260310 < \u26037.i()) {
                        \u26037.b(\u260310);
                        \u26034 += 2;
                    }
                }
                Map<Integer, Integer> map = ack.a(\u26038);
                for (int n4 : map.keySet()) {
                    aci aci2 = aci.c(n4);
                    if (aci2 == null) continue;
                    int \u260310 = \u26039.containsKey(n4) ? \u26039.get(n4) : 0;
                    int \u260311 = map.get(n4);
                    \u260311 = \u260310 == \u260311 ? ++\u260311 : Math.max(\u260311, \u260310);
                    boolean \u260312 = aci2.a(\u26033);
                    if (this.m.bA.d || \u26033.b() == zy.cd) {
                        \u260312 = true;
                    }
                    for (int n5 : \u26039.keySet()) {
                        if (n5 == n4 || aci2.a(aci.c(n5))) continue;
                        \u260312 = false;
                        ++\u26034;
                    }
                    if (!\u260312) continue;
                    if (\u260311 > aci2.b()) {
                        \u260311 = aci2.b();
                    }
                    \u26039.put(n4, \u260311);
                    int \u260313 = 0;
                    switch (aci2.d()) {
                        case 10: {
                            \u260313 = 1;
                            break;
                        }
                        case 5: {
                            \u260313 = 2;
                            break;
                        }
                        case 2: {
                            \u260313 = 4;
                            break;
                        }
                        case 1: {
                            \u260313 = 8;
                        }
                    }
                    if (bl4) {
                        \u260313 = Math.max(1, \u260313 / 2);
                    }
                    \u26034 += \u260313 * \u260311;
                }
            }
        }
        if (StringUtils.isBlank(this.l)) {
            if (\u26033.s()) {
                \u26036 = 1;
                \u26034 += \u26036;
                \u26037.r();
            }
        } else if (!this.l.equals(\u26033.q())) {
            \u26036 = 1;
            \u26034 += \u26036;
            \u26037.c(this.l);
        }
        this.a = \u26035 + \u26034;
        if (\u26034 <= 0) {
            \u26037 = null;
        }
        if (\u26036 == \u26034 && \u26036 > 0 && this.a >= 40) {
            this.a = 39;
        }
        if (this.a >= 40 && !this.m.bA.d) {
            \u26037 = null;
        }
        if (\u26037 != null) {
            n2 = \u26037.A();
            if (\u26038 != null && n2 < \u26038.A()) {
                n2 = \u26038.A();
            }
            n2 = n2 * 2 + 1;
            \u26037.c(n2);
            ack.a(\u26039, \u26037);
        }
        this.g.a(0, \u26037);
        this.b();
    }

    @Override
    public void a(xn xn2) {
        super.a(xn2);
        xn2.a((xi)this, 0, this.a);
    }

    @Override
    public void b(int n2, int n3) {
        if (n2 == 0) {
            this.a = n3;
        }
    }

    @Override
    public void b(wn wn2) {
        super.b(wn2);
        if (this.i.D) {
            return;
        }
        for (int i2 = 0; i2 < this.h.o_(); ++i2) {
            zx zx2 = this.h.b(i2);
            if (zx2 == null) continue;
            wn2.a(zx2, false);
        }
    }

    @Override
    public boolean a(wn wn2) {
        if (this.i.p(this.j).c() != afi.cf) {
            return false;
        }
        return !(wn2.e((double)this.j.n() + 0.5, (double)this.j.o() + 0.5, (double)this.j.p() + 0.5) > 64.0);
    }

    @Override
    public zx b(wn wn2, int n2) {
        zx zx2 = null;
        yg \u26032 = (yg)this.c.get(n2);
        if (\u26032 != null && \u26032.e()) {
            \u2603 = \u26032.d();
            zx2 = \u2603.k();
            if (n2 == 2) {
                if (!this.a(\u2603, 3, 39, true)) {
                    return null;
                }
                \u26032.a(\u2603, zx2);
            } else if (n2 == 0 || n2 == 1 ? !this.a(\u2603, 3, 39, false) : n2 >= 3 && n2 < 39 && !this.a(\u2603, 0, 2, false)) {
                return null;
            }
            if (\u2603.b == 0) {
                \u26032.d(null);
            } else {
                \u26032.f();
            }
            if (\u2603.b == zx2.b) {
                return null;
            }
            \u26032.a(wn2, \u2603);
        }
        return zx2;
    }

    public void a(String string) {
        this.l = string;
        if (this.a(2).e()) {
            zx zx2 = this.a(2).d();
            if (StringUtils.isBlank(string)) {
                zx2.r();
            } else {
                zx2.c(this.l);
            }
        }
        this.e();
    }
}

