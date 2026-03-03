/*
 * Decompiled with CFR 0.152.
 */
public class bjx
extends bje<wa> {
    private static final jy j = new jy("textures/entity/skeleton/skeleton.png");
    private static final jy k = new jy("textures/entity/skeleton/wither_skeleton.png");

    public bjx(biu biu2) {
        super(biu2, new bca(), 0.5f);
        this.a(new bky(this));
        this.a(new bkx(this){

            @Override
            protected void a() {
                this.c = new bca(0.5f, true);
                this.d = new bca(1.0f, true);
            }
        });
    }

    @Override
    protected void a(wa wa2, float f2) {
        if (wa2.cm() == 1) {
            bfl.a(1.2f, 1.2f, 1.2f);
        }
    }

    @Override
    public void C_() {
        bfl.b(0.09375f, 0.1875f, 0.0f);
    }

    @Override
    protected jy a(wa wa2) {
        if (wa2.cm() == 1) {
            return k;
        }
        return j;
    }
}

