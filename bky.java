/*
 * Decompiled with CFR 0.152.
 */
public class bky
implements blb<pr> {
    private final bjl<?> a;

    public bky(bjl<?> bjl2) {
        this.a = bjl2;
    }

    @Override
    public void a(pr pr22, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        pr pr22;
        zx zx2 = pr22.bA();
        if (zx2 == null) {
            return;
        }
        bfl.E();
        if (this.a.b().r) {
            float f9 = 0.5f;
            bfl.b(0.0f, 0.625f, 0.0f);
            bfl.b(-20.0f, -1.0f, 0.0f, 0.0f);
            bfl.a(f9, f9, f9);
        }
        ((bbj)this.a.b()).a(0.0625f);
        bfl.b(-0.0625f, 0.4375f, 0.0625f);
        if (pr22 instanceof wn && ((wn)pr22).bG != null) {
            zx2 = new zx(zy.aR, 0);
        }
        zw \u26032 = zx2.b();
        ave \u26033 = ave.A();
        if (\u26032 instanceof yo && afh.a(\u26032).b() == 2) {
            bfl.b(0.0f, 0.1875f, -0.3125f);
            bfl.b(20.0f, 1.0f, 0.0f, 0.0f);
            bfl.b(45.0f, 0.0f, 1.0f, 0.0f);
            float f10 = 0.375f;
            bfl.a(-f10, -f10, f10);
        }
        if (pr22.av()) {
            bfl.b(0.0f, 0.203125f, 0.0f);
        }
        \u26033.ah().a(pr22, zx2, bgr.b.b);
        bfl.F();
    }

    @Override
    public boolean b() {
        return false;
    }
}

