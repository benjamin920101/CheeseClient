/*
 * Decompiled with CFR 0.152.
 */
public class bjt
extends bje<vw> {
    private static final jy j = new jy("textures/entity/zombie_pigman.png");

    public bjt(biu biu2) {
        super(biu2, new bcn(), 0.5f, 1.0f);
        this.a(new bky(this));
        this.a(new bkx(this){

            @Override
            protected void a() {
                this.c = new bcn(0.5f, true);
                this.d = new bcn(1.0f, true);
            }
        });
    }

    @Override
    protected jy a(vw vw2) {
        return j;
    }
}

