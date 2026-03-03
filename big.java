/*
 * Decompiled with CFR 0.152.
 */
public class big
extends bjl<um> {
    public static final jy a = new jy("textures/entity/armorstand/wood.png");

    public big(biu biu2) {
        super(biu2, new bat(), 0.0f);
        bkx bkx2 = new bkx(this){

            @Override
            protected void a() {
                this.c = new bas(0.5f);
                this.d = new bas(1.0f);
            }
        };
        this.a(bkx2);
        this.a(new bky(this));
        this.a(new bks(this.a().e));
    }

    @Override
    protected jy a(um um2) {
        return a;
    }

    public bat a() {
        return (bat)super.b();
    }

    @Override
    protected void a(um um2, float f2, float f3, float f4) {
        bfl.b(180.0f - f3, 0.0f, 1.0f, 0.0f);
    }

    @Override
    protected boolean b(um um2) {
        return um2.aN();
    }

    @Override
    protected /* synthetic */ boolean a(pr pr2) {
        return this.b((um)pr2);
    }

    @Override
    public /* synthetic */ bbo b() {
        return this.a();
    }
}

