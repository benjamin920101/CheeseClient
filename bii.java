/*
 * Decompiled with CFR 0.152.
 */
public class bii
extends bjo<tk> {
    private static final jy a = new jy("textures/entity/bat.png");

    public bii(biu biu2) {
        super(biu2, new bav(), 0.25f);
    }

    @Override
    protected jy a(tk tk2) {
        return a;
    }

    @Override
    protected void a(tk tk2, float f2) {
        bfl.a(0.35f, 0.35f, 0.35f);
    }

    @Override
    protected void a(tk tk2, float f2, float f3, float f4) {
        if (!tk2.n()) {
            bfl.b(0.0f, ns.b(f2 * 0.3f) * 0.1f, 0.0f);
        } else {
            bfl.b(0.0f, -0.1f, 0.0f);
        }
        super.a(tk2, f2, f3, f4);
    }
}

