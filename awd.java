/*
 * Decompiled with CFR 0.152.
 */
public abstract class awd
extends awi {
    public awd(ave ave2, int n2, int n3, int n4, int n5, int n6) {
        super(ave2, n2, n3, n4, n5, n6);
    }

    @Override
    protected void a(int n2, boolean bl2, int n3, int n4) {
    }

    @Override
    protected boolean a(int n2) {
        return false;
    }

    @Override
    protected void a() {
    }

    @Override
    protected void a(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.b(n2).a(n2, n3, n4, this.c(), n5, n6, n7, this.c(n6, n7) == n2);
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        this.b(n2).a(n2, n3, n4);
    }

    public boolean b(int n2, int n3, int n4) {
        if (this.g(n3) && (\u2603 = this.c(n2, n3)) >= 0) {
            \u2603 = this.g + (this.b / 2 - this.c() / 2 + 2);
            \u2603 = this.d + 4 - this.n() + (\u2603 * this.h + this.t);
            \u2603 = n2 - \u2603;
            \u2603 = n3 - \u2603;
            if (this.b(\u2603).a(\u2603, n2, n3, n4, \u2603, \u2603)) {
                this.d(false);
                return true;
            }
        }
        return false;
    }

    public boolean c(int n2, int n3, int n4) {
        for (\u2603 = 0; \u2603 < this.b(); ++\u2603) {
            \u2603 = this.g + (this.b / 2 - this.c() / 2 + 2);
            \u2603 = this.d + 4 - this.n() + (\u2603 * this.h + this.t);
            \u2603 = n2 - \u2603;
            \u2603 = n3 - \u2603;
            this.b(\u2603).b(\u2603, n2, n3, n4, \u2603, \u2603);
        }
        this.d(true);
        return false;
    }

    public abstract a b(int var1);

    public static interface a {
        public void a(int var1, int var2, int var3);

        public void a(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8);

        public boolean a(int var1, int var2, int var3, int var4, int var5, int var6);

        public void b(int var1, int var2, int var3, int var4, int var5, int var6);
    }
}

