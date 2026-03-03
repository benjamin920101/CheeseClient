/*
 * Decompiled with CFR 0.152.
 */
public class bli
implements blb<wd> {
    private final bki a;

    public bli(bki bki2) {
        this.a = bki2;
    }

    @Override
    public void a(wd wd22, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        wd wd22;
        zx zx2 = wd22.bA();
        if (zx2 == null) {
            return;
        }
        bfl.c(1.0f, 1.0f, 1.0f);
        bfl.E();
        if (this.a.b().r) {
            bfl.b(0.0f, 0.625f, 0.0f);
            bfl.b(-20.0f, -1.0f, 0.0f, 0.0f);
            float f9 = 0.5f;
            bfl.a(f9, f9, f9);
        }
        ((bck)this.a.b()).f.c(0.0625f);
        bfl.b(-0.0625f, 0.53125f, 0.21875f);
        zw \u26032 = zx2.b();
        ave \u26033 = ave.A();
        if (\u26032 instanceof yo && \u26033.ae().a(afh.a(\u26032), zx2.i())) {
            bfl.b(0.0f, 0.0625f, -0.25f);
            bfl.b(30.0f, 1.0f, 0.0f, 0.0f);
            bfl.b(-5.0f, 0.0f, 1.0f, 0.0f);
            float f10 = 0.375f;
            bfl.a(f10, -f10, f10);
        } else if (\u26032 == zy.f) {
            bfl.b(0.0f, 0.125f, -0.125f);
            bfl.b(-45.0f, 0.0f, 1.0f, 0.0f);
            float f11 = 0.625f;
            bfl.a(f11, -f11, f11);
            bfl.b(-100.0f, 1.0f, 0.0f, 0.0f);
            bfl.b(-20.0f, 0.0f, 1.0f, 0.0f);
        } else if (\u26032.w_()) {
            if (\u26032.e()) {
                bfl.b(180.0f, 0.0f, 0.0f, 1.0f);
                bfl.b(0.0f, -0.0625f, 0.0f);
            }
            this.a.C_();
            bfl.b(0.0625f, -0.125f, 0.0f);
            float f12 = 0.625f;
            bfl.a(f12, -f12, f12);
            bfl.b(0.0f, 1.0f, 0.0f, 0.0f);
            bfl.b(0.0f, 0.0f, 1.0f, 0.0f);
        } else {
            bfl.b(0.1875f, 0.1875f, 0.0f);
            float f13 = 0.875f;
            bfl.a(f13, f13, f13);
            bfl.b(-20.0f, 0.0f, 0.0f, 1.0f);
            bfl.b(-60.0f, 1.0f, 0.0f, 0.0f);
            bfl.b(-30.0f, 0.0f, 0.0f, 1.0f);
        }
        bfl.b(-15.0f, 1.0f, 0.0f, 0.0f);
        bfl.b(40.0f, 0.0f, 0.0f, 1.0f);
        \u26033.ah().a(wd22, zx2, bgr.b.b);
        bfl.F();
    }

    @Override
    public boolean b() {
        return false;
    }
}

