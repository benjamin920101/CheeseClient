/*
 * Decompiled with CFR 0.152.
 */
public class bje<T extends ps>
extends bjo<T> {
    private static final jy j = new jy("textures/entity/steve.png");
    protected bbj a;
    protected float e;

    public bje(biu biu2, bbj bbj2, float f2) {
        this(biu2, bbj2, f2, 1.0f);
        this.a(new bky(this));
    }

    public bje(biu biu2, bbj bbj2, float f2, float f3) {
        super(biu2, bbj2, f2);
        this.a = bbj2;
        this.e = f3;
        this.a(new bks(bbj2.e));
    }

    @Override
    protected jy a(T t2) {
        return j;
    }

    @Override
    public void C_() {
        bfl.b(0.0f, 0.1875f, 0.0f);
    }
}

