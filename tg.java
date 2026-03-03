/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class tg {
    private adm a;
    private boolean b;
    private int c = -1;
    private int d;
    private int e;
    private tf f;
    private int g;
    private int h;
    private int i;

    public tg(adm adm2) {
        this.a = adm2;
    }

    public void a() {
        if (this.a.w()) {
            this.c = 0;
            return;
        }
        if (this.c == 2) {
            return;
        }
        if (this.c == 0) {
            float f2 = this.a.c(0.0f);
            if ((double)f2 < 0.5 || (double)f2 > 0.501) {
                return;
            }
            this.c = this.a.s.nextInt(10) == 0 ? 1 : 2;
            this.b = false;
            if (this.c == 2) {
                return;
            }
        }
        if (this.c == -1) {
            return;
        }
        if (!this.b) {
            if (this.b()) {
                this.b = true;
            } else {
                return;
            }
        }
        if (this.e > 0) {
            --this.e;
            return;
        }
        this.e = 2;
        if (this.d > 0) {
            this.c();
            --this.d;
        } else {
            this.c = 2;
        }
    }

    private boolean b() {
        List<wn> list = this.a.j;
        for (wn wn2 : list) {
            if (wn2.v()) continue;
            this.f = this.a.ae().a(new cj(wn2), 1);
            if (this.f == null || this.f.c() < 10 || this.f.d() < 20 || this.f.e() < 20) continue;
            cj cj2 = this.f.a();
            float \u26032 = this.f.b();
            boolean \u26033 = false;
            for (int i2 = 0; i2 < 10; ++i2) {
                float f2 = this.a.s.nextFloat() * (float)Math.PI * 2.0f;
                this.g = cj2.n() + (int)((double)(ns.b(f2) * \u26032) * 0.9);
                this.h = cj2.o();
                this.i = cj2.p() + (int)((double)(ns.a(f2) * \u26032) * 0.9);
                \u26033 = false;
                for (tf tf2 : this.a.ae().b()) {
                    if (tf2 == this.f || !tf2.a(new cj(this.g, this.h, this.i))) continue;
                    \u26033 = true;
                    break;
                }
                if (!\u26033) break;
            }
            if (\u26033) {
                return false;
            }
            aui aui2 = this.a(new cj(this.g, this.h, this.i));
            if (aui2 == null) continue;
            this.e = 0;
            this.d = 20;
            return true;
        }
        return false;
    }

    private boolean c() {
        aui aui2 = this.a(new cj(this.g, this.h, this.i));
        if (aui2 == null) {
            return false;
        }
        try {
            we we2 = new we(this.a);
            we2.a(this.a.E(new cj(we2)), null);
            we2.m(false);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        we2.b(aui2.a, aui2.b, aui2.c, this.a.s.nextFloat() * 360.0f, 0.0f);
        this.a.d(we2);
        cj cj2 = this.f.a();
        we2.a(cj2, this.f.b());
        return true;
    }

    private aui a(cj cj2) {
        for (int i2 = 0; i2 < 10; ++i2) {
            cj cj3 = cj2.a(this.a.s.nextInt(16) - 8, this.a.s.nextInt(6) - 3, this.a.s.nextInt(16) - 8);
            if (!this.f.a(cj3) || !adt.a(ps.a.a, this.a, cj3)) continue;
            return new aui(cj3.n(), cj3.o(), cj3.p());
        }
        return null;
    }
}

