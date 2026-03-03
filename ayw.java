/*
 * Decompiled with CFR 0.152.
 */
import java.util.Collection;

public abstract class ayw
extends ayl {
    private boolean u;

    public ayw(xi xi2) {
        super(xi2);
    }

    @Override
    public void b() {
        super.b();
        this.a();
    }

    protected void a() {
        if (!this.j.h.bl().isEmpty()) {
            this.i = 160 + (this.l - this.f - 200) / 2;
            this.u = true;
        } else {
            this.i = (this.l - this.f) / 2;
            this.u = false;
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        super.a(n2, n3, f2);
        if (this.u) {
            this.f();
        }
    }

    private void f() {
        int n2 = this.i - 124;
        \u2603 = this.r;
        \u2603 = 166;
        Collection<pf> \u26032 = this.j.h.bl();
        if (\u26032.isEmpty()) {
            return;
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.f();
        \u2603 = 33;
        if (\u26032.size() > 5) {
            \u2603 = 132 / (\u26032.size() - 1);
        }
        for (pf pf2 : this.j.h.bl()) {
            pe pe2 = pe.a[pf2.a()];
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            this.j.P().a(a);
            this.b(n2, \u2603, 0, 166, 140, 32);
            if (pe2.e()) {
                int n3 = pe2.f();
                this.b(n2 + 6, \u2603 + 7, 0 + n3 % 8 * 18, 198 + n3 / 8 * 18, 18, 18);
            }
            String \u26033 = bnq.a(pe2.a(), new Object[0]);
            if (pf2.c() == 1) {
                \u26033 = \u26033 + " " + bnq.a("enchantment.level.2", new Object[0]);
            } else if (pf2.c() == 2) {
                \u26033 = \u26033 + " " + bnq.a("enchantment.level.3", new Object[0]);
            } else if (pf2.c() == 3) {
                \u26033 = \u26033 + " " + bnq.a("enchantment.level.4", new Object[0]);
            }
            this.q.a(\u26033, (float)(n2 + 10 + 18), (float)(\u2603 + 6), 0xFFFFFF);
            String \u26034 = pe.a(pf2);
            this.q.a(\u26034, (float)(n2 + 10 + 18), (float)(\u2603 + 6 + 10), 0x7F7F7F);
            \u2603 += \u2603;
        }
    }
}

