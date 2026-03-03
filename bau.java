/*
 * Decompiled with CFR 0.152.
 */
public class bau
extends bbo {
    public bct a;
    public bct b;
    public bct c;

    public bau() {
        this.t = 64;
        this.u = 64;
        this.a = new bct(this, 0, 0);
        this.a.a(-10.0f, 0.0f, -2.0f, 20, 40, 1, 0.0f);
        this.b = new bct(this, 44, 0);
        this.b.a(-1.0f, -30.0f, -1.0f, 2, 42, 2, 0.0f);
        this.c = new bct(this, 0, 42);
        this.c.a(-10.0f, -32.0f, -1.0f, 20, 2, 2, 0.0f);
    }

    public void a() {
        this.a.d = -32.0f;
        this.a.a(0.0625f);
        this.b.a(0.0625f);
        this.c.a(0.0625f);
    }
}

