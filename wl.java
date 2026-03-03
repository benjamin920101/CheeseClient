/*
 * Decompiled with CFR 0.152.
 */
public class wl {
    public boolean a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e = true;
    private float f = 0.05f;
    private float g = 0.1f;

    public void a(dn dn2) {
        \u2603 = new dn();
        \u2603.a("invulnerable", this.a);
        \u2603.a("flying", this.b);
        \u2603.a("mayfly", this.c);
        \u2603.a("instabuild", this.d);
        \u2603.a("mayBuild", this.e);
        \u2603.a("flySpeed", this.f);
        \u2603.a("walkSpeed", this.g);
        dn2.a("abilities", \u2603);
    }

    public void b(dn dn2) {
        if (dn2.b("abilities", 10)) {
            \u2603 = dn2.m("abilities");
            this.a = \u2603.n("invulnerable");
            this.b = \u2603.n("flying");
            this.c = \u2603.n("mayfly");
            this.d = \u2603.n("instabuild");
            if (\u2603.b("flySpeed", 99)) {
                this.f = \u2603.h("flySpeed");
                this.g = \u2603.h("walkSpeed");
            }
            if (\u2603.b("mayBuild", 1)) {
                this.e = \u2603.n("mayBuild");
            }
        }
    }

    public float a() {
        return this.f;
    }

    public void a(float f2) {
        this.f = f2;
    }

    public float b() {
        return this.g;
    }

    public void b(float f2) {
        this.g = f2;
    }
}

