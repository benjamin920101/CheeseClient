/*
 * Decompiled with CFR 0.152.
 */
public class ble
implements blb<tw> {
    private final bjz a;

    public ble(bjz bjz2) {
        this.a = bjz2;
    }

    @Override
    public void a(tw tw2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (tw2.ax()) {
            return;
        }
        bfl.E();
        this.a.g().c.c(0.0625f);
        \u2603 = 0.625f;
        bfl.b(0.0f, -0.34375f, 0.0f);
        bfl.b(180.0f, 0.0f, 1.0f, 0.0f);
        bfl.a(\u2603, -\u2603, -\u2603);
        ave.A().ah().a(tw2, new zx(afi.aU, 1), bgr.b.d);
        bfl.F();
    }

    @Override
    public boolean b() {
        return true;
    }
}

