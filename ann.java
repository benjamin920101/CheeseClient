/*
 * Decompiled with CFR 0.152.
 */
public class ann
extends anm {
    @Override
    public void b() {
        this.c = new aef(ady.x, 0.0f);
        this.d = true;
        this.e = true;
        this.g = -1;
    }

    @Override
    public aui b(float f2, float f3) {
        return new aui(0.2f, 0.03f, 0.03f);
    }

    @Override
    protected void a() {
        float f2 = 0.1f;
        for (int i2 = 0; i2 <= 15; ++i2) {
            float f3 = 1.0f - (float)i2 / 15.0f;
            this.f[i2] = (1.0f - f3) / (f3 * 3.0f + 1.0f) * (1.0f - f2) + f2;
        }
    }

    @Override
    public amv c() {
        return new anw(this.b, this.b.P().s(), this.b.J());
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean a(int n2, int n3) {
        return false;
    }

    @Override
    public float a(long l2, float f2) {
        return 0.5f;
    }

    @Override
    public boolean e() {
        return false;
    }

    @Override
    public boolean b(int n2, int n3) {
        return true;
    }

    @Override
    public String k() {
        return "Nether";
    }

    @Override
    public String l() {
        return "_nether";
    }

    @Override
    public ams r() {
        return new ams(){

            @Override
            public double f() {
                return super.f() / 8.0;
            }

            @Override
            public double g() {
                return super.g() / 8.0;
            }
        };
    }
}

