/*
 * Decompiled with CFR 0.152.
 */
public class bld
implements blb<wb> {
    private final bjy a;
    private final bbo b = new bcc(0);

    public bld(bjy bjy2) {
        this.a = bjy2;
    }

    @Override
    public void a(wb wb2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (wb2.ax()) {
            return;
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.z();
        bfl.l();
        bfl.b(770, 771);
        this.b.a(this.a.b());
        this.b.a(wb2, f2, f3, f5, f6, f7, f8);
        bfl.k();
        bfl.A();
    }

    @Override
    public boolean b() {
        return true;
    }
}

