/*
 * Decompiled with CFR 0.152.
 */
public class bka<T extends wc>
extends bjo<T> {
    private static final jy a = new jy("textures/entity/spider/spider.png");

    public bka(biu biu2) {
        super(biu2, new bce(), 1.0f);
        this.a(new blf(this));
    }

    @Override
    protected float b(T t2) {
        return 180.0f;
    }

    @Override
    protected jy a(T t2) {
        return a;
    }
}

