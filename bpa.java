/*
 * Decompiled with CFR 0.152.
 */
public abstract class bpa
implements bpj {
    protected final jy a;
    protected float b = 1.0f;
    protected float c = 1.0f;
    protected float d;
    protected float e;
    protected float f;
    protected boolean g = false;
    protected int h = 0;
    protected bpj.a i = bpj.a.b;

    protected bpa(jy jy2) {
        this.a = jy2;
    }

    @Override
    public jy a() {
        return this.a;
    }

    @Override
    public boolean b() {
        return this.g;
    }

    @Override
    public int d() {
        return this.h;
    }

    @Override
    public float e() {
        return this.b;
    }

    @Override
    public float f() {
        return this.c;
    }

    @Override
    public float g() {
        return this.d;
    }

    @Override
    public float h() {
        return this.e;
    }

    @Override
    public float i() {
        return this.f;
    }

    @Override
    public bpj.a j() {
        return this.i;
    }
}

