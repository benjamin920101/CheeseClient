/*
 * Decompiled with CFR 0.152.
 */
public class bjb
extends bjo<vs> {
    private static final jy a = new jy("textures/entity/zombie/zombie.png");
    private float e;

    public bjb(biu biu2, bbo bbo2, float f2, float f3) {
        super(biu2, bbo2, f2 * f3);
        this.e = f3;
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
    public void C_() {
        bfl.b(0.0f, 0.1875f, 0.0f);
    }

    @Override
    protected void a(vs vs2, float f2) {
        bfl.a(this.e, this.e, this.e);
    }

    @Override
    protected jy a(vs vs2) {
        return a;
    }
}

