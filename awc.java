/*
 * Decompiled with CFR 0.152.
 */
public class awc
extends avs {
    private boolean o = false;

    public awc(int n2, int n3, int n4) {
        super(n2, n3, n4, 20, 20, "");
    }

    public boolean c() {
        return this.o;
    }

    public void b(boolean bl2) {
        this.o = bl2;
    }

    @Override
    public void a(ave ave2, int n2, int n3) {
        if (!this.m) {
            return;
        }
        ave2.P().a(avs.a);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        boolean bl2 = \u2603 = n2 >= this.h && n3 >= this.i && n2 < this.h + this.f && n3 < this.i + this.g;
        a a2 = this.o ? (!this.l ? awc$a.c : (\u2603 ? awc$a.b : awc$a.a)) : (!this.l ? awc$a.f : (\u2603 ? awc$a.e : awc$a.d));
        this.b(this.h, this.i, a2.a(), a2.b(), this.f, this.g);
    }

    static enum a {
        a(0, 146),
        b(0, 166),
        c(0, 186),
        d(20, 146),
        e(20, 166),
        f(20, 186);

        private final int g;
        private final int h;

        private a(int n3, int n4) {
            this.g = n3;
            this.h = n4;
        }

        public int a() {
            return this.g;
        }

        public int b() {
            return this.h;
        }
    }
}

