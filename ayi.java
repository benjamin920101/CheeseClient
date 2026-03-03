/*
 * Decompiled with CFR 0.152.
 */
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

public class ayi
extends awd {
    private final ayj u;
    private final ave v;
    private final awd.a[] w;
    private int x = 0;

    public ayi(ayj ayj2, ave ave2) {
        super(ave2, ayj2.l, ayj2.m, 63, ayj2.m - 32, 20);
        this.u = ayj2;
        this.v = ave2;
        Object[] objectArray = ArrayUtils.clone(ave2.t.aw);
        this.w = new awd.a[objectArray.length + avb.c().size()];
        Arrays.sort(objectArray);
        int \u26032 = 0;
        String \u26033 = null;
        for (Object object : objectArray) {
            String string = ((avb)object).e();
            if (!string.equals(\u26033)) {
                \u26033 = string;
                this.w[\u26032++] = new a(string);
            }
            if ((\u2603 = ave2.k.a(bnq.a(((avb)object).g(), new Object[0]))) > this.x) {
                this.x = \u2603;
            }
            this.w[\u26032++] = new b((avb)object);
        }
    }

    @Override
    protected int b() {
        return this.w.length;
    }

    @Override
    public awd.a b(int n2) {
        return this.w[n2];
    }

    @Override
    protected int d() {
        return super.d() + 15;
    }

    @Override
    public int c() {
        return super.c() + 32;
    }

    public class b
    implements awd.a {
        private final avb b;
        private final String c;
        private final avs d;
        private final avs e;

        private b(avb avb2) {
            this.b = avb2;
            this.c = bnq.a(avb2.g(), new Object[0]);
            this.d = new avs(0, 0, 0, 75, 20, bnq.a(avb2.g(), new Object[0]));
            this.e = new avs(0, 0, 0, 50, 20, bnq.a("controls.reset", new Object[0]));
        }

        @Override
        public void a(int n2, int n3, int n4, int n5, int n6, int n7, int n8, boolean bl2) {
            boolean bl3;
            bl3 = ((ayi)ayi.this).u.f == this.b;
            ((ayi)ayi.this).v.k.a(this.c, n3 + 90 - ayi.this.x, n4 + n6 / 2 - ((ayi)ayi.this).v.k.a / 2, 0xFFFFFF);
            this.e.h = n3 + 190;
            this.e.i = n4;
            this.e.l = this.b.i() != this.b.h();
            this.e.a(ayi.this.v, n7, n8);
            this.d.h = n3 + 105;
            this.d.i = n4;
            this.d.j = avh.c(this.b.i());
            bl4 = false;
            if (this.b.i() != 0) {
                for (avb avb2 : ((ayi)ayi.this).v.t.aw) {
                    if (avb2 == this.b || avb2.i() != this.b.i()) continue;
                    boolean bl4 = true;
                    break;
                }
            }
            if (bl3) {
                this.d.j = (Object)((Object)a.p) + "> " + (Object)((Object)a.o) + this.d.j + (Object)((Object)a.p) + " <";
            } else if (bl4) {
                this.d.j = (Object)((Object)a.m) + this.d.j;
            }
            this.d.a(ayi.this.v, n7, n8);
        }

        @Override
        public boolean a(int n2, int n3, int n4, int n5, int n6, int n7) {
            if (this.d.c(ayi.this.v, n3, n4)) {
                ((ayi)ayi.this).u.f = this.b;
                return true;
            }
            if (this.e.c(ayi.this.v, n3, n4)) {
                ((ayi)ayi.this).v.t.a(this.b, this.b.h());
                avb.b();
                return true;
            }
            return false;
        }

        @Override
        public void b(int n2, int n3, int n4, int n5, int n6, int n7) {
            this.d.a(n3, n4);
            this.e.a(n3, n4);
        }

        @Override
        public void a(int n2, int n3, int n4) {
        }
    }

    public class a
    implements awd.a {
        private final String b;
        private final int c;

        public a(String string) {
            this.b = bnq.a(string, new Object[0]);
            this.c = ((ayi)ayi.this).v.k.a(this.b);
        }

        @Override
        public void a(int n2, int n3, int n4, int n5, int n6, int n7, int n8, boolean bl2) {
            ((ayi)ayi.this).v.k.a(this.b, ((ayi)ayi.this).v.m.l / 2 - this.c / 2, n4 + n6 - ((ayi)ayi.this).v.k.a - 1, 0xFFFFFF);
        }

        @Override
        public boolean a(int n2, int n3, int n4, int n5, int n6, int n7) {
            return false;
        }

        @Override
        public void b(int n2, int n3, int n4, int n5, int n6, int n7) {
        }

        @Override
        public void a(int n2, int n3, int n4) {
        }
    }
}

