/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class sm
extends st {
    private boolean a;
    private int b;
    private final Class[] c;

    public sm(py py2, boolean bl2, Class ... classArray) {
        super(py2, false);
        this.a = bl2;
        this.c = classArray;
        this.a(1);
    }

    @Override
    public boolean a() {
        int n2 = this.e.be();
        return n2 != this.b && this.a(this.e.bd(), false);
    }

    @Override
    public void c() {
        this.e.d(this.e.bd());
        this.b = this.e.be();
        if (this.a) {
            double d2 = this.f();
            List<?> \u26032 = this.e.o.a(this.e.getClass(), new aug(this.e.s, this.e.t, this.e.u, this.e.s + 1.0, this.e.t + 1.0, this.e.u + 1.0).b(d2, 10.0, d2));
            for (py py2 : \u26032) {
                if (this.e == py2 || py2.u() != null || py2.c(this.e.bd())) continue;
                boolean bl2 = false;
                for (Class clazz : this.c) {
                    if (py2.getClass() != clazz) continue;
                    bl2 = true;
                    break;
                }
                if (bl2) continue;
                this.a(py2, this.e.bd());
            }
        }
        super.c();
    }

    protected void a(py py2, pr pr2) {
        py2.d(pr2);
    }
}

