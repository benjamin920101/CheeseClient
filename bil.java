/*
 * Decompiled with CFR 0.152.
 */
public class bil
extends bka<vm> {
    private static final jy a = new jy("textures/entity/spider/cave_spider.png");

    public bil(biu biu2) {
        super(biu2);
        this.c *= 0.7f;
    }

    @Override
    protected void a(vm vm2, float f2) {
        bfl.a(0.7f, 0.7f, 0.7f);
    }

    @Override
    protected jy a(vm vm2) {
        return a;
    }
}

