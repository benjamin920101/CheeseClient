/*
 * Decompiled with CFR 0.152.
 */
public class bcp
extends bbo {
    private bct a;
    private bct b = new bct(this, "glass");
    private bct c;

    public bcp(float f2, boolean bl2) {
        this.b.a(0, 0).a(-4.0f, -4.0f, -4.0f, 8, 8, 8);
        this.a = new bct(this, "cube");
        this.a.a(32, 0).a(-4.0f, -4.0f, -4.0f, 8, 8, 8);
        if (bl2) {
            this.c = new bct(this, "base");
            this.c.a(0, 16).a(-6.0f, 0.0f, -6.0f, 12, 4, 12);
        }
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        bfl.E();
        bfl.a(2.0f, 2.0f, 2.0f);
        bfl.b(0.0f, -0.5f, 0.0f);
        if (this.c != null) {
            this.c.a(f7);
        }
        bfl.b(f3, 0.0f, 1.0f, 0.0f);
        bfl.b(0.0f, 0.8f + f4, 0.0f);
        bfl.b(60.0f, 0.7071f, 0.0f, 0.7071f);
        this.b.a(f7);
        \u2603 = 0.875f;
        bfl.a(\u2603, \u2603, \u2603);
        bfl.b(60.0f, 0.7071f, 0.0f, 0.7071f);
        bfl.b(f3, 0.0f, 1.0f, 0.0f);
        this.b.a(f7);
        bfl.a(\u2603, \u2603, \u2603);
        bfl.b(60.0f, 0.7071f, 0.0f, 0.7071f);
        bfl.b(f3, 0.0f, 1.0f, 0.0f);
        this.a.a(f7);
        bfl.F();
    }
}

