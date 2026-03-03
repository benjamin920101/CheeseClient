/*
 * Decompiled with CFR 0.152.
 */
public class anp
extends anm {
    @Override
    public void b() {
        this.c = new aef(ady.y, 0.0f);
        this.g = 1;
        this.e = true;
    }

    @Override
    public amv c() {
        return new aob(this.b, this.b.J());
    }

    @Override
    public float a(long l2, float f2) {
        return 0.0f;
    }

    @Override
    public float[] a(float f2, float f3) {
        return null;
    }

    @Override
    public aui b(float f2, float f3) {
        int n2 = 0xA080A0;
        float \u26032 = ns.b(f2 * (float)Math.PI * 2.0f) * 2.0f + 0.5f;
        \u26032 = ns.a(\u26032, 0.0f, 1.0f);
        float \u26033 = (float)(n2 >> 16 & 0xFF) / 255.0f;
        float \u26034 = (float)(n2 >> 8 & 0xFF) / 255.0f;
        float \u26035 = (float)(n2 & 0xFF) / 255.0f;
        return new aui(\u26033 *= \u26032 * 0.0f + 0.15f, \u26034 *= \u26032 * 0.0f + 0.15f, \u26035 *= \u26032 * 0.0f + 0.15f);
    }

    @Override
    public boolean g() {
        return false;
    }

    @Override
    public boolean e() {
        return false;
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public float f() {
        return 8.0f;
    }

    @Override
    public boolean a(int n2, int n3) {
        return this.b.c(new cj(n2, 0, n3)).t().c();
    }

    @Override
    public cj h() {
        return new cj(100, 50, 0);
    }

    @Override
    public int i() {
        return 50;
    }

    @Override
    public boolean b(int n2, int n3) {
        return true;
    }

    @Override
    public String k() {
        return "The End";
    }

    @Override
    public String l() {
        return "_end";
    }
}

