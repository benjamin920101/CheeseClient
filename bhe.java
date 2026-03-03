/*
 * Decompiled with CFR 0.152.
 */
import java.util.Calendar;

public class bhe
extends bhd<aky> {
    private static final jy c = new jy("textures/entity/chest/trapped_double.png");
    private static final jy d = new jy("textures/entity/chest/christmas_double.png");
    private static final jy e = new jy("textures/entity/chest/normal_double.png");
    private static final jy f = new jy("textures/entity/chest/trapped.png");
    private static final jy g = new jy("textures/entity/chest/christmas.png");
    private static final jy h = new jy("textures/entity/chest/normal.png");
    private baz i = new baz();
    private baz j = new bbk();
    private boolean k;

    public bhe() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
            this.k = true;
        }
    }

    @Override
    public void a(aky aky2, double d2, double d3, double d4, float f2, int n2) {
        int \u26032;
        bfl.j();
        bfl.c(515);
        bfl.a(true);
        if (!aky2.t()) {
            \u26032 = 0;
        } else {
            Object object = aky2.w();
            \u26032 = aky2.u();
            if (object instanceof afs && \u26032 == 0) {
                ((afs)object).e(aky2.z(), aky2.v(), aky2.z().p(aky2.v()));
                \u26032 = aky2.u();
            }
            aky2.m();
        }
        if (aky2.f != null || aky2.h != null) {
            return;
        }
        if (aky2.g != null || aky2.i != null) {
            object = this.j;
            if (n2 >= 0) {
                this.a(a[n2]);
                bfl.n(5890);
                bfl.E();
                bfl.a(8.0f, 4.0f, 1.0f);
                bfl.b(0.0625f, 0.0625f, 0.0625f);
                bfl.n(5888);
            } else if (aky2.n() == 1) {
                this.a(c);
            } else if (this.k) {
                this.a(d);
            } else {
                this.a(e);
            }
        } else {
            object = this.i;
            if (n2 >= 0) {
                this.a(a[n2]);
                bfl.n(5890);
                bfl.E();
                bfl.a(4.0f, 4.0f, 1.0f);
                bfl.b(0.0625f, 0.0625f, 0.0625f);
                bfl.n(5888);
            } else if (aky2.n() == 1) {
                this.a(f);
            } else if (this.k) {
                this.a(g);
            } else {
                this.a(h);
            }
        }
        bfl.E();
        bfl.B();
        if (n2 < 0) {
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        }
        bfl.b((float)d2, (float)d3 + 1.0f, (float)d4 + 1.0f);
        bfl.a(1.0f, -1.0f, -1.0f);
        bfl.b(0.5f, 0.5f, 0.5f);
        int n3 = 0;
        if (\u26032 == 2) {
            n3 = 180;
        }
        if (\u26032 == 3) {
            n3 = 0;
        }
        if (\u26032 == 4) {
            n3 = 90;
        }
        if (\u26032 == 5) {
            n3 = -90;
        }
        if (\u26032 == 2 && aky2.g != null) {
            bfl.b(1.0f, 0.0f, 0.0f);
        }
        if (\u26032 == 5 && aky2.i != null) {
            bfl.b(0.0f, 0.0f, -1.0f);
        }
        bfl.b((float)n3, 0.0f, 1.0f, 0.0f);
        bfl.b(-0.5f, -0.5f, -0.5f);
        float \u26033 = aky2.k + (aky2.j - aky2.k) * f2;
        if (aky2.f != null && (\u2603 = aky2.f.k + (aky2.f.j - aky2.f.k) * f2) > \u26033) {
            \u26033 = \u2603;
        }
        if (aky2.h != null && (\u2603 = aky2.h.k + (aky2.h.j - aky2.h.k) * f2) > \u26033) {
            \u26033 = \u2603;
        }
        \u26033 = 1.0f - \u26033;
        \u26033 = 1.0f - \u26033 * \u26033 * \u26033;
        ((baz)object).a.f = -(\u26033 * (float)Math.PI / 2.0f);
        ((baz)object).a();
        bfl.C();
        bfl.F();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        if (n2 >= 0) {
            bfl.n(5890);
            bfl.F();
            bfl.n(5888);
        }
    }
}

