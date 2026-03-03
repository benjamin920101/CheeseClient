/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class avo
extends avp {
    private static final jy f = new jy("textures/misc/vignette.png");
    private static final jy g = new jy("textures/gui/widgets.png");
    private static final jy h = new jy("textures/misc/pumpkinblur.png");
    private final Random i = new Random();
    private final ave j;
    private final bjh k;
    private final avt l;
    private final awk m;
    private int n;
    private String o = "";
    private int p;
    private boolean q;
    public float a = 1.0f;
    private int r;
    private zx s;
    private final avv t;
    private final awm u;
    private final awh v;
    private int w;
    private String x = "";
    private String y = "";
    private int z;
    private int A;
    private int B;
    private int C = 0;
    private int D = 0;
    private long E = 0L;
    private long F = 0L;

    public avo(ave ave2) {
        this.j = ave2;
        this.k = ave2.ag();
        this.t = new avv(ave2);
        this.u = new awm(ave2);
        this.l = new avt(ave2);
        this.m = new awk(ave2);
        this.v = new awh(ave2, this);
        this.a();
    }

    public void a() {
        this.z = 10;
        this.A = 70;
        this.B = 20;
    }

    public void a(float f2) {
        float \u26035;
        avr avr2 = new avr(this.j);
        int \u26032 = avr2.a();
        int \u26033 = avr2.b();
        this.j.o.j();
        bfl.l();
        if (ave.w()) {
            this.a(this.j.h.c(f2), avr2);
        } else {
            bfl.a(770, 771, 1, 0);
        }
        zx \u26034 = this.j.h.bi.e(3);
        if (this.j.t.aA == 0 && \u26034 != null && \u26034.b() == zw.a(afi.aU)) {
            this.e(avr2);
        }
        if (!this.j.h.a(pe.k) && (\u2603 = this.j.h.bI + (this.j.h.bH - this.j.h.bI) * f2) > 0.0f) {
            this.b(\u2603, avr2);
        }
        if (this.j.c.a()) {
            this.u.a(avr2, f2);
        } else {
            this.a(avr2, f2);
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(d);
        bfl.l();
        if (this.b()) {
            bfl.a(775, 769, 1, 0);
            bfl.d();
            this.b(\u26032 / 2 - 7, \u26033 / 2 - 7, 0, 0, 16, 16);
        }
        bfl.a(770, 771, 1, 0);
        this.j.A.a("bossHealth");
        this.j();
        this.j.A.b();
        if (this.j.c.b()) {
            this.d(avr2);
        }
        bfl.k();
        if (this.j.h.cg() > 0) {
            this.j.A.a("sleep");
            bfl.i();
            bfl.c();
            int n2 = this.j.h.cg();
            \u26035 = (float)n2 / 100.0f;
            if (\u26035 > 1.0f) {
                \u26035 = 1.0f - (float)(n2 - 100) / 10.0f;
            }
            \u26036 = (int)(220.0f * \u26035) << 24 | 0x101020;
            avo.a(0, 0, \u26032, \u26033, \u26036);
            bfl.d();
            bfl.j();
            this.j.A.b();
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        \u2603 = \u26032 / 2 - 91;
        if (this.j.h.y()) {
            this.a(avr2, \u2603);
        } else if (this.j.c.f()) {
            this.b(avr2, \u2603);
        }
        if (this.j.t.D && !this.j.c.a()) {
            this.a(avr2);
        } else if (this.j.h.v()) {
            this.u.a(avr2);
        }
        if (this.j.t()) {
            this.b(avr2);
        }
        if (this.j.t.aB) {
            this.t.a(avr2);
        }
        if (this.p > 0) {
            this.j.A.a("overlayMessage");
            \u26035 = (float)this.p - f2;
            \u26036 = (int)(\u26035 * 255.0f / 20.0f);
            if (\u26036 > 255) {
                \u26036 = 255;
            }
            if (\u26036 > 8) {
                bfl.E();
                bfl.b(\u26032 / 2, \u26033 - 68, 0.0f);
                bfl.l();
                bfl.a(770, 771, 1, 0);
                \u2603 = 0xFFFFFF;
                if (this.q) {
                    \u2603 = ns.c(\u26035 / 50.0f, 0.7f, 0.6f) & 0xFFFFFF;
                }
                this.f().a(this.o, -this.f().a(this.o) / 2, -4, \u2603 + (\u26036 << 24 & 0xFF000000));
                bfl.k();
                bfl.F();
            }
            this.j.A.b();
        }
        if (this.w > 0) {
            int \u26036;
            this.j.A.a("titleAndSubtitle");
            \u26035 = (float)this.w - f2;
            \u26036 = 255;
            if (this.w > this.B + this.A) {
                float f3 = (float)(this.z + this.A + this.B) - \u26035;
                \u26036 = (int)(f3 * 255.0f / (float)this.z);
            }
            if (this.w <= this.B) {
                \u2603 = \u26035;
                \u26036 = (int)(\u2603 * 255.0f / (float)this.B);
            }
            if ((\u26036 = ns.a(\u26036, 0, 255)) > 8) {
                bfl.E();
                bfl.b(\u26032 / 2, \u26033 / 2, 0.0f);
                bfl.l();
                bfl.a(770, 771, 1, 0);
                bfl.E();
                bfl.a(4.0f, 4.0f, 4.0f);
                int \u26037 = \u26036 << 24 & 0xFF000000;
                this.f().a(this.x, (float)(-this.f().a(this.x) / 2), -10.0f, 0xFFFFFF | \u26037, true);
                bfl.F();
                bfl.E();
                bfl.a(2.0f, 2.0f, 2.0f);
                this.f().a(this.y, (float)(-this.f().a(this.y) / 2), 5.0f, 0xFFFFFF | \u26037, true);
                bfl.F();
                bfl.k();
                bfl.F();
            }
            this.j.A.b();
        }
        auo auo2 = this.j.f.Z();
        auk \u26038 = null;
        aul \u26039 = auo2.h(this.j.h.e_());
        if (\u26039 != null && (\u2603 = \u26039.l().b()) >= 0) {
            \u26038 = auo2.a(3 + \u2603);
        }
        auk auk2 = \u260310 = \u26038 != null ? \u26038 : auo2.a(1);
        if (\u260310 != null) {
            this.a(\u260310, avr2);
        }
        bfl.l();
        bfl.a(770, 771, 1, 0);
        bfl.c();
        bfl.E();
        bfl.b(0.0f, \u26033 - 48, 0.0f);
        this.j.A.a("chat");
        this.l.a(this.n);
        this.j.A.b();
        bfl.F();
        auk \u260310 = auo2.a(0);
        if (this.j.t.ak.d() && (!this.j.E() || this.j.h.a.d().size() > 1 || \u260310 != null)) {
            this.v.a(true);
            this.v.a(\u26032, auo2, \u260310);
        } else {
            this.v.a(false);
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.f();
        bfl.d();
    }

    protected void a(avr avr2, float f2) {
        if (!(this.j.ac() instanceof wn)) {
            return;
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(g);
        wn wn2 = (wn)this.j.ac();
        int \u26032 = avr2.a() / 2;
        float \u26033 = this.e;
        this.e = -90.0f;
        this.b(\u26032 - 91, avr2.b() - 22, 0, 0, 182, 22);
        this.b(\u26032 - 91 - 1 + wn2.bi.c * 20, avr2.b() - 22 - 1, 0, 22, 24, 22);
        this.e = \u26033;
        bfl.B();
        bfl.l();
        bfl.a(770, 771, 1, 0);
        avc.c();
        for (int i2 = 0; i2 < 9; ++i2) {
            \u2603 = avr2.a() / 2 - 90 + i2 * 20 + 2;
            \u2603 = avr2.b() - 16 - 3;
            this.a(i2, \u2603, \u2603, f2, wn2);
        }
        avc.a();
        bfl.C();
        bfl.k();
    }

    public void a(avr avr2, int n2) {
        this.j.A.a("jumpBar");
        this.j.P().a(avp.d);
        float f2 = this.j.h.z();
        int \u26032 = 182;
        int \u26033 = (int)(f2 * (float)(\u26032 + 1));
        int \u26034 = avr2.b() - 32 + 3;
        this.b(n2, \u26034, 0, 84, \u26032, 5);
        if (\u26033 > 0) {
            this.b(n2, \u26034, 0, 89, \u26033, 5);
        }
        this.j.A.b();
    }

    public void b(avr avr2, int n2) {
        this.j.A.a("expBar");
        this.j.P().a(avp.d);
        \u2603 = this.j.h.ck();
        if (\u2603 > 0) {
            \u2603 = 182;
            \u2603 = (int)(this.j.h.bD * (float)(\u2603 + 1));
            \u26032 = avr2.b() - 32 + 3;
            this.b(n2, \u26032, 0, 64, \u2603, 5);
            if (\u2603 > 0) {
                this.b(n2, \u26032, 0, 69, \u2603, 5);
            }
        }
        this.j.A.b();
        if (this.j.h.bB > 0) {
            this.j.A.a("expLevel");
            \u2603 = 8453920;
            String string = "" + this.j.h.bB;
            int \u26032 = (avr2.a() - this.f().a(string)) / 2;
            int \u26033 = avr2.b() - 31 - 4;
            boolean \u26034 = false;
            this.f().a(string, \u26032 + 1, \u26033, 0);
            this.f().a(string, \u26032 - 1, \u26033, 0);
            this.f().a(string, \u26032, \u26033 + 1, 0);
            this.f().a(string, \u26032, \u26033 - 1, 0);
            this.f().a(string, \u26032, \u26033, \u2603);
            this.j.A.b();
        }
    }

    public void a(avr avr2) {
        this.j.A.a("selectedItemName");
        if (this.r > 0 && this.s != null) {
            int n2;
            String string = this.s.q();
            if (this.s.s()) {
                string = (Object)((Object)a.u) + string;
            }
            int \u26032 = (avr2.a() - this.f().a(string)) / 2;
            int \u26033 = avr2.b() - 59;
            if (!this.j.c.b()) {
                \u26033 += 14;
            }
            if ((n2 = (int)((float)this.r * 256.0f / 10.0f)) > 255) {
                n2 = 255;
            }
            if (n2 > 0) {
                bfl.E();
                bfl.l();
                bfl.a(770, 771, 1, 0);
                this.f().a(string, (float)\u26032, (float)\u26033, 0xFFFFFF + (n2 << 24));
                bfl.k();
                bfl.F();
            }
        }
        this.j.A.b();
    }

    public void b(avr avr2) {
        this.j.A.a("demo");
        String string = "";
        string = this.j.f.K() >= 120500L ? bnq.a("demo.demoExpired", new Object[0]) : bnq.a("demo.remainingTime", nx.a((int)(120500L - this.j.f.K())));
        int \u26032 = this.f().a(string);
        this.f().a(string, (float)(avr2.a() - \u26032 - 10), 5.0f, 0xFFFFFF);
        this.j.A.b();
    }

    protected boolean b() {
        if (this.j.t.aB && !this.j.h.cq() && !this.j.t.w) {
            return false;
        }
        if (this.j.c.a()) {
            cj cj2;
            if (this.j.i != null) {
                return true;
            }
            return this.j.s != null && this.j.s.a == auh.a.b && this.j.f.s(cj2 = this.j.s.a()) instanceof og;
        }
        return true;
    }

    public void c(avr avr2) {
        this.m.a(avr2.a() - 10, 10);
    }

    private void a(auk auk2, avr avr2) {
        auo auo2 = auk2.a();
        Collection<aum> \u26032 = auo2.i(auk2);
        ArrayList<aum> \u26033 = Lists.newArrayList(Iterables.filter(\u26032, new Predicate<aum>(){

            public boolean a(aum aum2) {
                return aum2.e() != null && !aum2.e().startsWith("#");
            }

            @Override
            public /* synthetic */ boolean apply(Object object) {
                return this.a((aum)object);
            }
        }));
        \u26032 = \u26033.size() > 15 ? Lists.newArrayList(Iterables.skip(\u26033, \u26032.size() - 15)) : \u26033;
        int \u26034 = this.f().a(auk2.d());
        for (aum aum2 : \u26032) {
            aul aul2 = auo2.h(aum2.e());
            String \u26035 = aul.a(aul2, aum2.e()) + ": " + (Object)((Object)a.m) + aum2.c();
            \u26034 = Math.max(\u26034, this.f().a(\u26035));
        }
        int n2 = \u26032.size() * this.f().a;
        \u2603 = avr2.b() / 2 + n2 / 3;
        \u2603 = 3;
        \u2603 = avr2.a() - \u26034 - \u2603;
        \u2603 = 0;
        for (aum aum3 : \u26032) {
            aul aul3 = auo2.h(aum3.e());
            String \u26036 = aul.a(aul3, aum3.e());
            String \u26037 = (Object)((Object)a.m) + "" + aum3.c();
            int \u26038 = \u2603;
            int \u26039 = \u2603 - ++\u2603 * this.f().a;
            int \u260310 = avr2.a() - \u2603 + 2;
            avo.a(\u26038 - 2, \u26039, \u260310, \u26039 + this.f().a, 0x50000000);
            this.f().a(\u26036, \u26038, \u26039, 0x20FFFFFF);
            this.f().a(\u26037, \u260310 - this.f().a(\u26037), \u26039, 0x20FFFFFF);
            if (\u2603 != \u26032.size()) continue;
            String \u260311 = auk2.d();
            avo.a(\u26038 - 2, \u26039 - this.f().a - 1, \u260310, \u26039 - 1, 0x60000000);
            avo.a(\u26038 - 2, \u26039 - 1, \u260310, \u26039, 0x50000000);
            this.f().a(\u260311, \u26038 + \u26034 / 2 - this.f().a(\u260311) / 2, \u26039 - this.f().a, 0x20FFFFFF);
        }
    }

    private void d(avr avr2) {
        int \u260323;
        int \u260321;
        int n2;
        if (!(this.j.ac() instanceof wn)) {
            return;
        }
        wn wn2 = (wn)this.j.ac();
        int \u26032 = ns.f(wn2.bn());
        boolean bl2 = \u2603 = this.F > (long)this.n && (this.F - (long)this.n) / 3L % 2L == 1L;
        if (\u26032 < this.C && wn2.Z > 0) {
            this.E = ave.J();
            this.F = this.n + 20;
        } else if (\u26032 > this.C && wn2.Z > 0) {
            this.E = ave.J();
            this.F = this.n + 10;
        }
        if (ave.J() - this.E > 1000L) {
            this.C = \u26032;
            this.D = \u26032;
            this.E = ave.J();
        }
        this.C = \u26032;
        int \u26033 = this.D;
        this.i.setSeed(this.n * 312871);
        boolean \u26034 = false;
        xg \u26035 = wn2.cl();
        int \u26036 = \u26035.a();
        int \u26037 = \u26035.b();
        qc \u26038 = wn2.a(vy.a);
        int \u26039 = avr2.a() / 2 - 91;
        int \u260310 = avr2.a() / 2 + 91;
        int \u260311 = avr2.b() - 39;
        float \u260312 = (float)\u26038.e();
        float \u260313 = wn2.bN();
        int \u260314 = ns.f((\u260312 + \u260313) / 2.0f / 10.0f);
        int \u260315 = Math.max(10 - (\u260314 - 2), 3);
        int \u260316 = \u260311 - (\u260314 - 1) * \u260315 - 10;
        float \u260317 = \u260313;
        int \u260318 = wn2.br();
        int \u260319 = -1;
        if (wn2.a(pe.l)) {
            \u260319 = this.n % ns.f(\u260312 + 5.0f);
        }
        this.j.A.a("armor");
        for (n2 = 0; n2 < 10; ++n2) {
            if (\u260318 <= 0) continue;
            i2 = \u26039 + n2 * 8;
            if (n2 * 2 + 1 < \u260318) {
                this.b(i2, \u260316, 34, 9, 9, 9);
            }
            if (n2 * 2 + 1 == \u260318) {
                this.b(i2, \u260316, 25, 9, 9, 9);
            }
            if (n2 * 2 + 1 <= \u260318) continue;
            this.b(i2, \u260316, 16, 9, 9, 9);
        }
        this.j.A.c("health");
        for (n2 = ns.f((\u260312 + \u260313) / 2.0f) - 1; n2 >= 0; --n2) {
            i2 = 16;
            if (wn2.a(pe.u)) {
                i2 += 36;
            } else if (wn2.a(pe.v)) {
                i2 += 72;
            }
            \u260321 = 0;
            if (\u2603) {
                \u260321 = 1;
            }
            \u2603 = ns.f((float)(n2 + 1) / 10.0f) - 1;
            \u260323 = \u26039 + n2 % 10 * 8;
            \u260324 = \u260311 - \u2603 * \u260315;
            if (\u26032 <= 4) {
                \u260324 += this.i.nextInt(2);
            }
            if (n2 == \u260319) {
                \u260324 -= 2;
            }
            \u260325 = 0;
            if (wn2.o.P().t()) {
                \u260325 = 5;
            }
            this.b(\u260323, \u260324, 16 + \u260321 * 9, 9 * \u260325, 9, 9);
            if (\u2603) {
                if (n2 * 2 + 1 < \u26033) {
                    this.b(\u260323, \u260324, i2 + 54, 9 * \u260325, 9, 9);
                }
                if (n2 * 2 + 1 == \u26033) {
                    this.b(\u260323, \u260324, i2 + 63, 9 * \u260325, 9, 9);
                }
            }
            if (\u260317 > 0.0f) {
                if (\u260317 == \u260313 && \u260313 % 2.0f == 1.0f) {
                    this.b(\u260323, \u260324, i2 + 153, 9 * \u260325, 9, 9);
                } else {
                    this.b(\u260323, \u260324, i2 + 144, 9 * \u260325, 9, 9);
                }
                \u260317 -= 2.0f;
                continue;
            }
            if (n2 * 2 + 1 < \u26032) {
                this.b(\u260323, \u260324, i2 + 36, 9 * \u260325, 9, 9);
            }
            if (n2 * 2 + 1 != \u26032) continue;
            this.b(\u260323, \u260324, i2 + 45, 9 * \u260325, 9, 9);
        }
        pk \u260320 = wn2.m;
        if (\u260320 == null) {
            this.j.A.c("food");
            for (int i2 = 0; i2 < 10; ++i2) {
                \u260321 = \u260311;
                \u2603 = 16;
                \u260323 = 0;
                if (wn2.a(pe.s)) {
                    \u2603 += 36;
                    \u260323 = 13;
                }
                if (wn2.cl().e() <= 0.0f && this.n % (\u26036 * 3 + 1) == 0) {
                    \u260321 += this.i.nextInt(3) - 1;
                }
                if (\u26034) {
                    \u260323 = 1;
                }
                \u260324 = \u260310 - i2 * 8 - 9;
                this.b(\u260324, \u260321, 16 + \u260323 * 9, 27, 9, 9);
                if (\u26034) {
                    if (i2 * 2 + 1 < \u26037) {
                        this.b(\u260324, \u260321, \u2603 + 54, 27, 9, 9);
                    }
                    if (i2 * 2 + 1 == \u26037) {
                        this.b(\u260324, \u260321, \u2603 + 63, 27, 9, 9);
                    }
                }
                if (i2 * 2 + 1 < \u26036) {
                    this.b(\u260324, \u260321, \u2603 + 36, 27, 9, 9);
                }
                if (i2 * 2 + 1 != \u26036) continue;
                this.b(\u260324, \u260321, \u2603 + 45, 27, 9, 9);
            }
        } else if (\u260320 instanceof pr) {
            this.j.A.c("mountHealth");
            pr pr2 = (pr)\u260320;
            \u260321 = (int)Math.ceil(pr2.bn());
            float \u260322 = pr2.bu();
            \u260323 = (int)(\u260322 + 0.5f) / 2;
            if (\u260323 > 30) {
                \u260323 = 30;
            }
            int \u260324 = \u260311;
            int \u260325 = 0;
            while (\u260323 > 0) {
                int n3 = Math.min(\u260323, 10);
                \u260323 -= n3;
                for (\u2603 = 0; \u2603 < n3; ++\u2603) {
                    \u2603 = 52;
                    \u2603 = 0;
                    if (\u26034) {
                        \u2603 = 1;
                    }
                    \u2603 = \u260310 - \u2603 * 8 - 9;
                    this.b(\u2603, \u260324, \u2603 + \u2603 * 9, 9, 9, 9);
                    if (\u2603 * 2 + 1 + \u260325 < \u260321) {
                        this.b(\u2603, \u260324, \u2603 + 36, 9, 9, 9);
                    }
                    if (\u2603 * 2 + 1 + \u260325 != \u260321) continue;
                    this.b(\u2603, \u260324, \u2603 + 45, 9, 9, 9);
                }
                \u260324 -= 10;
                \u260325 += 20;
            }
        }
        this.j.A.c("air");
        if (wn2.a(arm.h)) {
            int n4 = this.j.h.az();
            \u260321 = ns.f((double)(n4 - 2) * 10.0 / 300.0);
            \u2603 = ns.f((double)n4 * 10.0 / 300.0) - \u260321;
            for (\u260323 = 0; \u260323 < \u260321 + \u2603; ++\u260323) {
                if (\u260323 < \u260321) {
                    this.b(\u260310 - \u260323 * 8 - 9, \u260316, 16, 18, 9, 9);
                    continue;
                }
                this.b(\u260310 - \u260323 * 8 - 9, \u260316, 25, 18, 9, 9);
            }
        }
        this.j.A.b();
    }

    private void j() {
        if (bfc.c == null || bfc.b <= 0) {
            return;
        }
        --bfc.b;
        avn avn2 = this.j.k;
        avr \u26032 = new avr(this.j);
        int \u26033 = \u26032.a();
        int \u26034 = 182;
        int \u26035 = \u26033 / 2 - \u26034 / 2;
        int \u26036 = (int)(bfc.a * (float)(\u26034 + 1));
        int \u26037 = 12;
        this.b(\u26035, \u26037, 0, 74, \u26034, 5);
        this.b(\u26035, \u26037, 0, 74, \u26034, 5);
        if (\u26036 > 0) {
            this.b(\u26035, \u26037, 0, 79, \u26036, 5);
        }
        String \u26038 = bfc.c;
        this.f().a(\u26038, (float)(\u26033 / 2 - this.f().a(\u26038) / 2), (float)(\u26037 - 10), 0xFFFFFF);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(d);
    }

    private void e(avr avr2) {
        bfl.i();
        bfl.a(false);
        bfl.a(770, 771, 1, 0);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.c();
        this.j.P().a(h);
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        \u26032.a(7, bms.g);
        \u26032.b(0.0, (double)avr2.b(), -90.0).a(0.0, 1.0).d();
        \u26032.b((double)avr2.a(), (double)avr2.b(), -90.0).a(1.0, 1.0).d();
        \u26032.b((double)avr2.a(), 0.0, -90.0).a(1.0, 0.0).d();
        \u26032.b(0.0, 0.0, -90.0).a(0.0, 0.0).d();
        bfx2.b();
        bfl.a(true);
        bfl.j();
        bfl.d();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void a(float f22, avr avr2) {
        float f22 = 1.0f - f22;
        f22 = ns.a(f22, 0.0f, 1.0f);
        ams \u26032 = this.j.f.af();
        \u2603 = (float)\u26032.a(this.j.h);
        double \u26033 = Math.min(\u26032.o() * (double)\u26032.p() * 1000.0, Math.abs(\u26032.j() - \u26032.h()));
        double \u26034 = Math.max((double)\u26032.q(), \u26033);
        \u2603 = (double)\u2603 < \u26034 ? 1.0f - (float)((double)\u2603 / \u26034) : 0.0f;
        this.a = (float)((double)this.a + (double)(f22 - this.a) * 0.01);
        bfl.i();
        bfl.a(false);
        bfl.a(0, 769, 1, 0);
        if (\u2603 > 0.0f) {
            bfl.c(0.0f, \u2603, \u2603, 1.0f);
        } else {
            bfl.c(this.a, this.a, this.a, 1.0f);
        }
        this.j.P().a(f);
        bfx \u26035 = bfx.a();
        bfd \u26036 = \u26035.c();
        \u26036.a(7, bms.g);
        \u26036.b(0.0, (double)avr2.b(), -90.0).a(0.0, 1.0).d();
        \u26036.b((double)avr2.a(), (double)avr2.b(), -90.0).a(1.0, 1.0).d();
        \u26036.b((double)avr2.a(), 0.0, -90.0).a(1.0, 0.0).d();
        \u26036.b(0.0, 0.0, -90.0).a(0.0, 0.0).d();
        \u26035.b();
        bfl.a(true);
        bfl.j();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.a(770, 771, 1, 0);
    }

    private void b(float f22, avr avr2) {
        float f22;
        if (f22 < 1.0f) {
            f22 *= f22;
            f22 *= f22;
            f22 = f22 * 0.8f + 0.2f;
        }
        bfl.c();
        bfl.i();
        bfl.a(false);
        bfl.a(770, 771, 1, 0);
        bfl.c(1.0f, 1.0f, 1.0f, f22);
        this.j.P().a(bmh.g);
        bmi \u26032 = this.j.ae().a().a(afi.aY.Q());
        \u2603 = \u26032.e();
        \u2603 = \u26032.g();
        \u2603 = \u26032.f();
        \u2603 = \u26032.h();
        bfx \u26033 = bfx.a();
        bfd \u26034 = \u26033.c();
        \u26034.a(7, bms.g);
        \u26034.b(0.0, (double)avr2.b(), -90.0).a(\u2603, \u2603).d();
        \u26034.b((double)avr2.a(), (double)avr2.b(), -90.0).a(\u2603, \u2603).d();
        \u26034.b((double)avr2.a(), 0.0, -90.0).a(\u2603, \u2603).d();
        \u26034.b(0.0, 0.0, -90.0).a(\u2603, \u2603).d();
        \u26033.b();
        bfl.a(true);
        bfl.j();
        bfl.d();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void a(int n2, int n3, int n4, float f2, wn wn2) {
        zx zx2 = wn2.bi.a[n2];
        if (zx2 == null) {
            return;
        }
        float \u26032 = (float)zx2.c - f2;
        if (\u26032 > 0.0f) {
            bfl.E();
            float f3 = 1.0f + \u26032 / 5.0f;
            bfl.b(n3 + 8, n4 + 12, 0.0f);
            bfl.a(1.0f / f3, (f3 + 1.0f) / 2.0f, 1.0f);
            bfl.b(-(n3 + 8), -(n4 + 12), 0.0f);
        }
        this.k.b(zx2, n3, n4);
        if (\u26032 > 0.0f) {
            bfl.F();
        }
        this.k.a(this.j.k, zx2, n3, n4);
    }

    public void c() {
        if (this.p > 0) {
            --this.p;
        }
        if (this.w > 0) {
            --this.w;
            if (this.w <= 0) {
                this.x = "";
                this.y = "";
            }
        }
        ++this.n;
        this.m.a();
        if (this.j.h != null) {
            zx zx2 = this.j.h.bi.h();
            if (zx2 == null) {
                this.r = 0;
            } else if (this.s == null || zx2.b() != this.s.b() || !zx.a(zx2, this.s) || !zx2.e() && zx2.i() != this.s.i()) {
                this.r = 40;
            } else if (this.r > 0) {
                --this.r;
            }
            this.s = zx2;
        }
    }

    public void a(String string) {
        this.a(bnq.a("record.nowPlaying", string), true);
    }

    public void a(String string, boolean bl2) {
        this.o = string;
        this.p = 60;
        this.q = bl2;
    }

    public void a(String string, String string2, int n2, int n3, int n4) {
        if (string == null && string2 == null && n2 < 0 && n3 < 0 && n4 < 0) {
            this.x = "";
            this.y = "";
            this.w = 0;
            return;
        }
        if (string != null) {
            this.x = string;
            this.w = this.z + this.A + this.B;
            return;
        }
        if (string2 != null) {
            this.y = string2;
            return;
        }
        if (n2 >= 0) {
            this.z = n2;
        }
        if (n3 >= 0) {
            this.A = n3;
        }
        if (n4 >= 0) {
            this.B = n4;
        }
        if (this.w > 0) {
            this.w = this.z + this.A + this.B;
        }
    }

    public void a(eu eu2, boolean bl2) {
        this.a(eu2.c(), bl2);
    }

    public avt d() {
        return this.l;
    }

    public int e() {
        return this.n;
    }

    public avn f() {
        return this.j.k;
    }

    public awm g() {
        return this.u;
    }

    public awh h() {
        return this.v;
    }

    public void i() {
        this.v.a();
    }
}

