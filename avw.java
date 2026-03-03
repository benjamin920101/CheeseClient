/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class avw
extends avp {
    private final int g;
    private final avn h;
    public int a;
    public int f;
    private final int i;
    private final int j;
    private String k = "";
    private int l = 32;
    private int m;
    private boolean n = true;
    private boolean o = true;
    private boolean p;
    private boolean q = true;
    private int r;
    private int s;
    private int t;
    private int u = 0xE0E0E0;
    private int v = 0x707070;
    private boolean w = true;
    private awg.b x;
    private Predicate<String> y = Predicates.alwaysTrue();

    public avw(int n2, avn avn2, int n3, int n4, int n5, int n6) {
        this.g = n2;
        this.h = avn2;
        this.a = n3;
        this.f = n4;
        this.i = n5;
        this.j = n6;
    }

    public void a(awg.b b2) {
        this.x = b2;
    }

    public void a() {
        ++this.m;
    }

    public void a(String string) {
        if (!this.y.apply(string)) {
            return;
        }
        this.k = string.length() > this.l ? string.substring(0, this.l) : string;
        this.f();
    }

    public String b() {
        return this.k;
    }

    public String c() {
        int n2 = this.s < this.t ? this.s : this.t;
        \u2603 = this.s < this.t ? this.t : this.s;
        return this.k.substring(n2, \u2603);
    }

    public void a(Predicate<String> predicate) {
        this.y = predicate;
    }

    public void b(String string) {
        int \u26032;
        int n2;
        String string2;
        string2 = "";
        \u2603 = f.a(string);
        int n3 = this.s < this.t ? this.s : this.t;
        \u2603 = this.s < this.t ? this.t : this.s;
        n2 = this.l - this.k.length() - (n3 - \u2603);
        \u26032 = 0;
        if (this.k.length() > 0) {
            string2 = string2 + this.k.substring(0, n3);
        }
        if (n2 < \u2603.length()) {
            string2 = string2 + \u2603.substring(0, n2);
            \u26032 = n2;
        } else {
            string2 = string2 + \u2603;
            \u26032 = \u2603.length();
        }
        if (this.k.length() > 0 && \u2603 < this.k.length()) {
            string2 = string2 + this.k.substring(\u2603);
        }
        if (!this.y.apply(string2)) {
            return;
        }
        this.k = string2;
        this.d(n3 - this.t + \u26032);
        if (this.x != null) {
            this.x.a(this.g, this.k);
        }
    }

    public void a(int n2) {
        if (this.k.length() == 0) {
            return;
        }
        if (this.t != this.s) {
            this.b("");
            return;
        }
        this.b(this.c(n2) - this.s);
    }

    public void b(int n2) {
        if (this.k.length() == 0) {
            return;
        }
        if (this.t != this.s) {
            this.b("");
            return;
        }
        boolean bl2 = n2 < 0;
        int \u26032 = bl2 ? this.s + n2 : this.s;
        int \u26033 = bl2 ? this.s : this.s + n2;
        String \u26034 = "";
        if (\u26032 >= 0) {
            \u26034 = this.k.substring(0, \u26032);
        }
        if (\u26033 < this.k.length()) {
            \u26034 = \u26034 + this.k.substring(\u26033);
        }
        if (!this.y.apply(\u26034)) {
            return;
        }
        this.k = \u26034;
        if (bl2) {
            this.d(n2);
        }
        if (this.x != null) {
            this.x.a(this.g, this.k);
        }
    }

    public int d() {
        return this.g;
    }

    public int c(int n2) {
        return this.a(n2, this.i());
    }

    public int a(int n2, int n3) {
        return this.a(n2, n3, true);
    }

    public int a(int n2, int n3, boolean bl2) {
        int n4 = n3;
        boolean \u26032 = n2 < 0;
        \u2603 = Math.abs(n2);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            if (\u26032) {
                while (bl2 && n4 > 0 && this.k.charAt(n4 - 1) == ' ') {
                    --n4;
                }
                while (n4 > 0 && this.k.charAt(n4 - 1) != ' ') {
                    --n4;
                }
                continue;
            }
            \u2603 = this.k.length();
            if ((n4 = this.k.indexOf(32, n4)) == -1) {
                n4 = \u2603;
                continue;
            }
            while (bl2 && n4 < \u2603 && this.k.charAt(n4) == ' ') {
                ++n4;
            }
        }
        return n4;
    }

    public void d(int n2) {
        this.e(this.t + n2);
    }

    public void e(int n2) {
        this.s = n2;
        \u2603 = this.k.length();
        this.s = ns.a(this.s, 0, \u2603);
        this.i(this.s);
    }

    public void e() {
        this.e(0);
    }

    public void f() {
        this.e(this.k.length());
    }

    public boolean a(char c2, int n2) {
        if (!this.p) {
            return false;
        }
        if (axu.g(n2)) {
            this.f();
            this.i(0);
            return true;
        }
        if (axu.f(n2)) {
            axu.e(this.c());
            return true;
        }
        if (axu.e(n2)) {
            if (this.q) {
                this.b(axu.o());
            }
            return true;
        }
        if (axu.d(n2)) {
            axu.e(this.c());
            if (this.q) {
                this.b("");
            }
            return true;
        }
        switch (n2) {
            case 203: {
                if (axu.r()) {
                    if (axu.q()) {
                        this.i(this.a(-1, this.o()));
                    } else {
                        this.i(this.o() - 1);
                    }
                } else if (axu.q()) {
                    this.e(this.c(-1));
                } else {
                    this.d(-1);
                }
                return true;
            }
            case 205: {
                if (axu.r()) {
                    if (axu.q()) {
                        this.i(this.a(1, this.o()));
                    } else {
                        this.i(this.o() + 1);
                    }
                } else if (axu.q()) {
                    this.e(this.c(1));
                } else {
                    this.d(1);
                }
                return true;
            }
            case 14: {
                if (axu.q()) {
                    if (this.q) {
                        this.a(-1);
                    }
                } else if (this.q) {
                    this.b(-1);
                }
                return true;
            }
            case 211: {
                if (axu.q()) {
                    if (this.q) {
                        this.a(1);
                    }
                } else if (this.q) {
                    this.b(1);
                }
                return true;
            }
            case 199: {
                if (axu.r()) {
                    this.i(0);
                } else {
                    this.e();
                }
                return true;
            }
            case 207: {
                if (axu.r()) {
                    this.i(this.k.length());
                } else {
                    this.f();
                }
                return true;
            }
        }
        if (f.a(c2)) {
            if (this.q) {
                this.b(Character.toString(c2));
            }
            return true;
        }
        return false;
    }

    public void a(int n2, int n3, int n4) {
        boolean bl2 = \u2603 = n2 >= this.a && n2 < this.a + this.i && n3 >= this.f && n3 < this.f + this.j;
        if (this.o) {
            this.b(\u2603);
        }
        if (this.p && \u2603 && n4 == 0) {
            \u2603 = n2 - this.a;
            if (this.n) {
                \u2603 -= 4;
            }
            String string = this.h.a(this.k.substring(this.r), this.p());
            this.e(this.h.a(string, \u2603).length() + this.r);
        }
    }

    public void g() {
        int \u26035;
        if (!this.r()) {
            return;
        }
        if (this.j()) {
            avw.a(this.a - 1, this.f - 1, this.a + this.i + 1, this.f + this.j + 1, -6250336);
            avw.a(this.a, this.f, this.a + this.i, this.f + this.j, -16777216);
        }
        int n2 = this.q ? this.u : this.v;
        \u2603 = this.s - this.r;
        \u2603 = this.t - this.r;
        String \u26032 = this.h.a(this.k.substring(this.r), this.p());
        boolean \u26033 = \u2603 >= 0 && \u2603 <= \u26032.length();
        boolean \u26034 = this.p && this.m / 6 % 2 == 0 && \u26033;
        \u2603 = this.n ? this.a + 4 : this.a;
        \u2603 = this.n ? this.f + (this.j - 8) / 2 : this.f;
        \u26035 = \u2603;
        if (\u2603 > \u26032.length()) {
            \u2603 = \u26032.length();
        }
        if (\u26032.length() > 0) {
            String string = \u26033 ? \u26032.substring(0, \u2603) : \u26032;
            \u26035 = this.h.a(string, (float)\u26035, (float)\u2603, n2);
        }
        boolean bl2 = this.s < this.k.length() || this.k.length() >= this.h();
        int \u26036 = \u26035;
        if (!\u26033) {
            \u26036 = \u2603 > 0 ? \u2603 + this.i : \u2603;
        } else if (bl2) {
            --\u26036;
            --\u26035;
        }
        if (\u26032.length() > 0 && \u26033 && \u2603 < \u26032.length()) {
            \u26035 = this.h.a(\u26032.substring(\u2603), (float)\u26035, (float)\u2603, n2);
        }
        if (\u26034) {
            if (bl2) {
                avp.a(\u26036, \u2603 - 1, \u26036 + 1, \u2603 + 1 + this.h.a, -3092272);
            } else {
                this.h.a("_", (float)\u26036, (float)\u2603, n2);
            }
        }
        if (\u2603 != \u2603) {
            int n3 = \u2603 + this.h.a(\u26032.substring(0, \u2603));
            this.c(\u26036, \u2603 - 1, n3 - 1, \u2603 + 1 + this.h.a);
        }
    }

    private void c(int n2, int n3, int n4, int n5) {
        if (n2 < n4) {
            \u2603 = n2;
            n2 = n4;
            n4 = \u2603;
        }
        if (n3 < n5) {
            \u2603 = n3;
            n3 = n5;
            n5 = \u2603;
        }
        if (n4 > this.a + this.i) {
            n4 = this.a + this.i;
        }
        if (n2 > this.a + this.i) {
            n2 = this.a + this.i;
        }
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        bfl.c(0.0f, 0.0f, 255.0f, 255.0f);
        bfl.x();
        bfl.u();
        bfl.f(5387);
        \u26032.a(7, bms.e);
        \u26032.b((double)n2, (double)n5, 0.0).d();
        \u26032.b((double)n4, (double)n5, 0.0).d();
        \u26032.b((double)n4, (double)n3, 0.0).d();
        \u26032.b((double)n2, (double)n3, 0.0).d();
        bfx2.b();
        bfl.v();
        bfl.w();
    }

    public void f(int n2) {
        this.l = n2;
        if (this.k.length() > n2) {
            this.k = this.k.substring(0, n2);
        }
    }

    public int h() {
        return this.l;
    }

    public int i() {
        return this.s;
    }

    public boolean j() {
        return this.n;
    }

    public void a(boolean bl2) {
        this.n = bl2;
    }

    public void g(int n2) {
        this.u = n2;
    }

    public void h(int n2) {
        this.v = n2;
    }

    public void b(boolean bl2) {
        if (bl2 && !this.p) {
            this.m = 0;
        }
        this.p = bl2;
    }

    public boolean m() {
        return this.p;
    }

    public void c(boolean bl2) {
        this.q = bl2;
    }

    public int o() {
        return this.t;
    }

    public int p() {
        return this.j() ? this.i - 8 : this.i;
    }

    public void i(int n2) {
        \u2603 = this.k.length();
        if (n2 > \u2603) {
            n2 = \u2603;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        this.t = n2;
        if (this.h != null) {
            if (this.r > \u2603) {
                this.r = \u2603;
            }
            \u2603 = this.p();
            String string = this.h.a(this.k.substring(this.r), \u2603);
            int \u26032 = string.length() + this.r;
            if (n2 == this.r) {
                this.r -= this.h.a(this.k, \u2603, true).length();
            }
            if (n2 > \u26032) {
                this.r += n2 - \u26032;
            } else if (n2 <= this.r) {
                this.r -= this.r - n2;
            }
            this.r = ns.a(this.r, 0, \u2603);
        }
    }

    public void d(boolean bl2) {
        this.o = bl2;
    }

    public boolean r() {
        return this.w;
    }

    public void e(boolean bl2) {
        this.w = bl2;
    }
}

