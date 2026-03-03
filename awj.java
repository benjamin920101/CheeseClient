/*
 * Decompiled with CFR 0.152.
 */
public class awj
extends avs {
    private float p = 1.0f;
    public boolean o;
    private avh.a q;
    private final float r;
    private final float s;

    public awj(int n2, int n3, int n4, avh.a a2) {
        this(n2, n3, n4, a2, 0.0f, 1.0f);
    }

    public awj(int n2, int n3, int n4, avh.a a2, float f2, float f3) {
        super(n2, n3, n4, 150, 20, "");
        this.q = a2;
        this.r = f2;
        this.s = f3;
        ave ave2 = ave.A();
        this.p = a2.c(ave2.t.a(a2));
        this.j = ave2.t.c(a2);
    }

    @Override
    protected int a(boolean bl2) {
        return 0;
    }

    @Override
    protected void b(ave ave22, int n2, int n3) {
        ave ave22;
        if (!this.m) {
            return;
        }
        if (this.o) {
            this.p = (float)(n2 - (this.h + 4)) / (float)(this.f - 8);
            this.p = ns.a(this.p, 0.0f, 1.0f);
            float f2 = this.q.d(this.p);
            ave22.t.a(this.q, f2);
            this.p = this.q.c(f2);
            this.j = ave22.t.c(this.q);
        }
        ave22.P().a(a);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.b(this.h + (int)(this.p * (float)(this.f - 8)), this.i, 0, 66, 4, 20);
        this.b(this.h + (int)(this.p * (float)(this.f - 8)) + 4, this.i, 196, 66, 4, 20);
    }

    @Override
    public boolean c(ave ave2, int n2, int n3) {
        if (super.c(ave2, n2, n3)) {
            this.p = (float)(n2 - (this.h + 4)) / (float)(this.f - 8);
            this.p = ns.a(this.p, 0.0f, 1.0f);
            ave2.t.a(this.q, this.q.d(this.p));
            this.j = ave2.t.c(this.q);
            this.o = true;
            return true;
        }
        return false;
    }

    @Override
    public void a(int n2, int n3) {
        this.o = false;
    }
}

