/*
 * Decompiled with CFR 0.152.
 */
public class ru
extends qx {
    boolean g;
    int h;

    public ru(ps ps2, boolean bl2) {
        super(ps2);
        this.a = ps2;
        this.g = bl2;
    }

    @Override
    public boolean b() {
        return this.g && this.h > 0 && super.b();
    }

    @Override
    public void c() {
        this.h = 20;
        this.c.a(this.a.o, this.b, true);
    }

    @Override
    public void d() {
        if (this.g) {
            this.c.a(this.a.o, this.b, false);
        }
    }

    @Override
    public void e() {
        --this.h;
        super.e();
    }
}

