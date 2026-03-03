/*
 * Decompiled with CFR 0.152.
 */
public abstract class bhd<T extends akw> {
    protected static final jy[] a = new jy[]{new jy("textures/blocks/destroy_stage_0.png"), new jy("textures/blocks/destroy_stage_1.png"), new jy("textures/blocks/destroy_stage_2.png"), new jy("textures/blocks/destroy_stage_3.png"), new jy("textures/blocks/destroy_stage_4.png"), new jy("textures/blocks/destroy_stage_5.png"), new jy("textures/blocks/destroy_stage_6.png"), new jy("textures/blocks/destroy_stage_7.png"), new jy("textures/blocks/destroy_stage_8.png"), new jy("textures/blocks/destroy_stage_9.png")};
    protected bhc b;

    public abstract void a(T var1, double var2, double var4, double var6, float var8, int var9);

    protected void a(jy jy2) {
        bmj bmj2 = this.b.e;
        if (bmj2 != null) {
            bmj2.a(jy2);
        }
    }

    protected adm b() {
        return this.b.f;
    }

    public void a(bhc bhc2) {
        this.b = bhc2;
    }

    public avn c() {
        return this.b.a();
    }

    public boolean a() {
        return false;
    }
}

