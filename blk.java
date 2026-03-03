/*
 * Decompiled with CFR 0.152.
 */
public class blk
implements blb<ua> {
    private static final jy a = new jy("textures/entity/wolf/wolf_collar.png");
    private final bkl b;

    public blk(bkl bkl2) {
        this.b = bkl2;
    }

    @Override
    public void a(ua ua2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (!ua2.cl() || ua2.ax()) {
            return;
        }
        this.b.a(a);
        zd zd2 = zd.b(ua2.cw().a());
        float[] \u26032 = tv.a(zd2);
        bfl.c(\u26032[0], \u26032[1], \u26032[2]);
        this.b.b().a(ua2, f2, f3, f5, f6, f7, f8);
    }

    @Override
    public boolean b() {
        return true;
    }
}

