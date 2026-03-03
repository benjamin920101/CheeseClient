/*
 * Decompiled with CFR 0.152.
 */
public class bla
implements blb<tt> {
    private static final jy a = new jy("textures/entity/pig/pig_saddle.png");
    private final bjs b;
    private final bbq c = new bbq(0.5f);

    public bla(bjs bjs2) {
        this.b = bjs2;
    }

    @Override
    public void a(tt tt2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (!tt2.cl()) {
            return;
        }
        this.b.a(a);
        this.c.a(this.b.b());
        this.c.a(tt2, f2, f3, f5, f6, f7, f8);
    }

    @Override
    public boolean b() {
        return false;
    }
}

