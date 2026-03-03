/*
 * Decompiled with CFR 0.152.
 */
public class sl
extends st {
    ty a;
    pr b;

    public sl(ty ty2) {
        super(ty2, false, true);
        this.a = ty2;
        this.a(1);
    }

    @Override
    public boolean a() {
        tf tf2 = this.a.n();
        if (tf2 == null) {
            return false;
        }
        this.b = tf2.b(this.a);
        if (this.b instanceof vn) {
            return false;
        }
        if (!this.a(this.b, false)) {
            if (this.e.bc().nextInt(20) == 0) {
                this.b = tf2.c(this.a);
                return this.a(this.b, false);
            }
            return false;
        }
        return true;
    }

    @Override
    public void c() {
        this.a.d(this.b);
        super.c();
    }
}

