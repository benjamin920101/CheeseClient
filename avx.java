/*
 * Decompiled with CFR 0.152.
 */
public class avx
extends avs {
    private float p = 1.0f;
    public boolean o;
    private String q;
    private final float r;
    private final float s;
    private final awg.b t;
    private a u;

    public avx(awg.b b2, int n2, int n3, int n4, String string, float f2, float f3, float f4, a a2) {
        super(n2, n3, n4, 150, 20, "");
        this.q = string;
        this.r = f2;
        this.s = f3;
        this.p = (f4 - f2) / (f3 - f2);
        this.u = a2;
        this.t = b2;
        this.j = this.e();
    }

    public float c() {
        return this.r + (this.s - this.r) * this.p;
    }

    public void a(float f2, boolean bl2) {
        this.p = (f2 - this.r) / (this.s - this.r);
        this.j = this.e();
        if (bl2) {
            this.t.a(this.k, this.c());
        }
    }

    public float d() {
        return this.p;
    }

    private String e() {
        if (this.u == null) {
            return bnq.a(this.q, new Object[0]) + ": " + this.c();
        }
        return this.u.a(this.k, bnq.a(this.q, new Object[0]), this.c());
    }

    @Override
    protected int a(boolean bl2) {
        return 0;
    }

    @Override
    protected void b(ave ave2, int n2, int n3) {
        if (!this.m) {
            return;
        }
        if (this.o) {
            this.p = (float)(n2 - (this.h + 4)) / (float)(this.f - 8);
            if (this.p < 0.0f) {
                this.p = 0.0f;
            }
            if (this.p > 1.0f) {
                this.p = 1.0f;
            }
            this.j = this.e();
            this.t.a(this.k, this.c());
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.b(this.h + (int)(this.p * (float)(this.f - 8)), this.i, 0, 66, 4, 20);
        this.b(this.h + (int)(this.p * (float)(this.f - 8)) + 4, this.i, 196, 66, 4, 20);
    }

    public void a(float f2) {
        this.p = f2;
        this.j = this.e();
        this.t.a(this.k, this.c());
    }

    @Override
    public boolean c(ave ave2, int n2, int n3) {
        if (super.c(ave2, n2, n3)) {
            this.p = (float)(n2 - (this.h + 4)) / (float)(this.f - 8);
            if (this.p < 0.0f) {
                this.p = 0.0f;
            }
            if (this.p > 1.0f) {
                this.p = 1.0f;
            }
            this.j = this.e();
            this.t.a(this.k, this.c());
            this.o = true;
            return true;
        }
        return false;
    }

    @Override
    public void a(int n2, int n3) {
        this.o = false;
    }

    public static interface a {
        public String a(int var1, String var2, float var3);
    }
}

