/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class ov {
    private final List<ou> a = Lists.newArrayList();
    private final pr b;
    private int c;
    private int d;
    private int e;
    private boolean f;
    private boolean g;
    private String h;

    public ov(pr pr2) {
        this.b = pr2;
    }

    public void a() {
        this.j();
        if (this.b.k_()) {
            afh afh2 = this.b.o.p(new cj(this.b.s, this.b.aR().b, this.b.u)).c();
            if (afh2 == afi.au) {
                this.h = "ladder";
            } else if (afh2 == afi.bn) {
                this.h = "vines";
            }
        } else if (this.b.V()) {
            this.h = "water";
        }
    }

    public void a(ow ow2, float f2, float f3) {
        this.g();
        this.a();
        ou ou2 = new ou(ow2, this.b.W, f2, f3, this.h, this.b.O);
        this.a.add(ou2);
        this.c = this.b.W;
        this.g = true;
        if (ou2.f() && !this.f && this.b.ai()) {
            this.f = true;
            this.e = this.d = this.b.W;
            this.b.h_();
        }
    }

    public eu b() {
        eu \u26034;
        if (this.a.size() == 0) {
            return new fb("death.attack.generic", this.b.f_());
        }
        ou ou2 = this.i();
        \u2603 = this.a.get(this.a.size() - 1);
        eu \u26032 = \u2603.h();
        pk \u26033 = \u2603.a().j();
        if (ou2 != null && \u2603.a() == ow.i) {
            eu eu2 = ou2.h();
            if (ou2.a() == ow.i || ou2.a() == ow.j) {
                \u26034 = new fb("death.fell.accident." + this.a(ou2), this.b.f_());
            } else if (!(eu2 == null || \u26032 != null && eu2.equals(\u26032))) {
                pk pk2 = ou2.a().j();
                zx zx2 = \u2603 = pk2 instanceof pr ? ((pr)pk2).bA() : null;
                \u26034 = \u2603 != null && \u2603.s() ? new fb("death.fell.assist.item", this.b.f_(), eu2, \u2603.C()) : new fb("death.fell.assist", this.b.f_(), eu2);
            } else if (\u26032 != null) {
                zx zx3 = \u2603 = \u26033 instanceof pr ? ((pr)\u26033).bA() : null;
                \u26034 = \u2603 != null && \u2603.s() ? new fb("death.fell.finish.item", this.b.f_(), \u26032, \u2603.C()) : new fb("death.fell.finish", this.b.f_(), \u26032);
            } else {
                \u26034 = new fb("death.fell.killer", this.b.f_());
            }
        } else {
            \u26034 = \u2603.a().b(this.b);
        }
        return \u26034;
    }

    public pr c() {
        pr pr2 = null;
        wn \u26032 = null;
        float \u26033 = 0.0f;
        float \u26034 = 0.0f;
        for (ou ou2 : this.a) {
            if (ou2.a().j() instanceof wn && (\u26032 == null || ou2.c() > \u26034)) {
                \u26034 = ou2.c();
                \u26032 = (wn)ou2.a().j();
            }
            if (!(ou2.a().j() instanceof pr) || pr2 != null && !(ou2.c() > \u26033)) continue;
            \u26033 = ou2.c();
            pr2 = (pr)ou2.a().j();
        }
        if (\u26032 != null && \u26034 >= \u26033 / 3.0f) {
            return \u26032;
        }
        return pr2;
    }

    private ou i() {
        ou ou2 = null;
        \u2603 = null;
        int \u26032 = 0;
        float \u26033 = 0.0f;
        for (int i2 = 0; i2 < this.a.size(); ++i2) {
            ou ou3 = this.a.get(i2);
            ou ou4 = \u2603 = i2 > 0 ? this.a.get(i2 - 1) : null;
            if ((ou3.a() == ow.i || ou3.a() == ow.j) && ou3.i() > 0.0f && (ou2 == null || ou3.i() > \u26033)) {
                ou2 = i2 > 0 ? \u2603 : ou3;
                \u26033 = ou3.i();
            }
            if (ou3.g() == null || \u2603 != null && !(ou3.c() > (float)\u26032)) continue;
            \u2603 = ou3;
        }
        if (\u26033 > 5.0f && ou2 != null) {
            return ou2;
        }
        if (\u26032 > 5 && \u2603 != null) {
            return \u2603;
        }
        return null;
    }

    private String a(ou ou2) {
        return ou2.g() == null ? "generic" : ou2.g();
    }

    public int f() {
        if (this.f) {
            return this.b.W - this.d;
        }
        return this.e - this.d;
    }

    private void j() {
        this.h = null;
    }

    public void g() {
        int n2;
        int n3 = n2 = this.f ? 300 : 100;
        if (this.g && (!this.b.ai() || this.b.W - this.c > n2)) {
            boolean bl2 = this.f;
            this.g = false;
            this.f = false;
            this.e = this.b.W;
            if (bl2) {
                this.b.j();
            }
            this.a.clear();
        }
    }

    public pr h() {
        return this.b;
    }
}

