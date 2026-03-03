/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class awf
extends awd {
    private final List<a> u = Lists.newArrayList();

    public awf(ave ave2, int n2, int n3, int n4, int n5, int n6, avh.a ... aArray) {
        super(ave2, n2, n3, n4, n5, n6);
        this.k = false;
        for (int i2 = 0; i2 < aArray.length; i2 += 2) {
            avh.a a2 = aArray[i2];
            \u2603 = i2 < aArray.length - 1 ? aArray[i2 + 1] : null;
            avs \u26032 = this.a(ave2, n2 / 2 - 155, 0, a2);
            avs \u26033 = this.a(ave2, n2 / 2 - 155 + 160, 0, \u2603);
            this.u.add(new a(\u26032, \u26033));
        }
    }

    private avs a(ave ave2, int n2, int n3, avh.a a2) {
        if (a2 == null) {
            return null;
        }
        int n4 = a2.c();
        return a2.a() ? new awj(n4, n2, n3, a2) : new awe(n4, n2, n3, a2, ave2.t.c(a2));
    }

    public a c(int n2) {
        return this.u.get(n2);
    }

    @Override
    protected int b() {
        return this.u.size();
    }

    @Override
    public int c() {
        return 400;
    }

    @Override
    protected int d() {
        return super.d() + 32;
    }

    @Override
    public /* synthetic */ awd.a b(int n2) {
        return this.c(n2);
    }

    public static class a
    implements awd.a {
        private final ave a = ave.A();
        private final avs b;
        private final avs c;

        public a(avs avs2, avs avs3) {
            this.b = avs2;
            this.c = avs3;
        }

        @Override
        public void a(int n2, int n3, int n4, int n5, int n6, int n7, int n8, boolean bl2) {
            if (this.b != null) {
                this.b.i = n4;
                this.b.a(this.a, n7, n8);
            }
            if (this.c != null) {
                this.c.i = n4;
                this.c.a(this.a, n7, n8);
            }
        }

        @Override
        public boolean a(int n2, int n3, int n4, int n5, int n6, int n7) {
            if (this.b.c(this.a, n3, n4)) {
                if (this.b instanceof awe) {
                    this.a.t.a(((awe)this.b).c(), 1);
                    this.b.j = this.a.t.c(avh.a.a(this.b.k));
                }
                return true;
            }
            if (this.c != null && this.c.c(this.a, n3, n4)) {
                if (this.c instanceof awe) {
                    this.a.t.a(((awe)this.c).c(), 1);
                    this.c.j = this.a.t.c(avh.a.a(this.c.k));
                }
                return true;
            }
            return false;
        }

        @Override
        public void b(int n2, int n3, int n4, int n5, int n6, int n7) {
            if (this.b != null) {
                this.b.a(n3, n4);
            }
            if (this.c != null) {
                this.c.a(n3, n4);
            }
        }

        @Override
        public void a(int n2, int n3, int n4) {
        }
    }
}

