/*
 * Decompiled with CFR 0.152.
 */
public class avs
extends avp {
    protected static final jy a = new jy("textures/gui/widgets.png");
    protected int f = 200;
    protected int g = 20;
    public int h;
    public int i;
    public String j;
    public int k;
    public boolean l = true;
    public boolean m = true;
    protected boolean n;

    public avs(int n2, int n3, int n4, String string) {
        this(n2, n3, n4, 200, 20, string);
    }

    public avs(int n2, int n3, int n4, int n5, int n6, String string) {
        this.k = n2;
        this.h = n3;
        this.i = n4;
        this.f = n5;
        this.g = n6;
        this.j = string;
    }

    protected int a(boolean bl2) {
        int n2 = 1;
        if (!this.l) {
            n2 = 0;
        } else if (bl2) {
            n2 = 2;
        }
        return n2;
    }

    public void a(ave ave2, int n2, int n3) {
        if (!this.m) {
            return;
        }
        avn avn2 = ave2.k;
        ave2.P().a(a);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.n = n2 >= this.h && n3 >= this.i && n2 < this.h + this.f && n3 < this.i + this.g;
        int \u26032 = this.a(this.n);
        bfl.l();
        bfl.a(770, 771, 1, 0);
        bfl.b(770, 771);
        this.b(this.h, this.i, 0, 46 + \u26032 * 20, this.f / 2, this.g);
        this.b(this.h + this.f / 2, this.i, 200 - this.f / 2, 46 + \u26032 * 20, this.f / 2, this.g);
        this.b(ave2, n2, n3);
        int \u26033 = 0xE0E0E0;
        if (!this.l) {
            \u26033 = 0xA0A0A0;
        } else if (this.n) {
            \u26033 = 0xFFFFA0;
        }
        this.a(avn2, this.j, this.h + this.f / 2, this.i + (this.g - 8) / 2, \u26033);
    }

    protected void b(ave ave2, int n2, int n3) {
    }

    public void a(int n2, int n3) {
    }

    public boolean c(ave ave2, int n2, int n3) {
        return this.l && this.m && n2 >= this.h && n3 >= this.i && n2 < this.h + this.f && n3 < this.i + this.g;
    }

    public boolean a() {
        return this.n;
    }

    public void b(int n2, int n3) {
    }

    public void a(bpz bpz2) {
        bpz2.a(bpf.a(new jy("gui.button.press"), 1.0f));
    }

    public int b() {
        return this.f;
    }

    public void a(int n2) {
        this.f = n2;
    }
}

