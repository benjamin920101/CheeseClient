/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import java.util.List;

public class awg
extends awd {
    private final List<d> u = Lists.newArrayList();
    private final nm<avp> v = new nm();
    private final List<avw> w = Lists.newArrayList();
    private final f[][] x;
    private int y;
    private b z;
    private avp A;

    public awg(ave ave2, int n2, int n3, int n4, int n5, int n6, b b2, f[] ... fArray) {
        super(ave2, n2, n3, n4, n5, n6);
        this.z = b2;
        this.x = fArray;
        this.k = false;
        this.s();
        this.t();
    }

    private void s() {
        for (f[] fArray : this.x) {
            for (int i2 = 0; i2 < fArray.length; i2 += 2) {
                f f2 = fArray[i2];
                \u2603 = i2 < fArray.length - 1 ? fArray[i2 + 1] : null;
                avp \u26032 = this.a(f2, 0, \u2603 == null);
                avp \u26033 = this.a(\u2603, 160, f2 == null);
                d \u26034 = new d(\u26032, \u26033);
                this.u.add(\u26034);
                if (f2 != null && \u26032 != null) {
                    this.v.a(f2.b(), \u26032);
                    if (\u26032 instanceof avw) {
                        this.w.add((avw)\u26032);
                    }
                }
                if (\u2603 == null || \u26033 == null) continue;
                this.v.a(\u2603.b(), \u26033);
                if (!(\u26033 instanceof avw)) continue;
                this.w.add((avw)\u26033);
            }
        }
    }

    private void t() {
        this.u.clear();
        for (int i2 = 0; i2 < this.x[this.y].length; i2 += 2) {
            f f2 = this.x[this.y][i2];
            \u2603 = i2 < this.x[this.y].length - 1 ? this.x[this.y][i2 + 1] : null;
            avp \u26032 = this.v.a(f2.b());
            avp \u26033 = \u2603 != null ? this.v.a(\u2603.b()) : null;
            d \u26034 = new d(\u26032, \u26033);
            this.u.add(\u26034);
        }
    }

    public void c(int n2) {
        if (n2 == this.y) {
            return;
        }
        \u2603 = this.y;
        this.y = n2;
        this.t();
        this.e(\u2603, n2);
        this.n = 0.0f;
    }

    public int e() {
        return this.y;
    }

    public int f() {
        return this.x.length;
    }

    public avp g() {
        return this.A;
    }

    public void h() {
        if (this.y > 0) {
            this.c(this.y - 1);
        }
    }

    public void i() {
        if (this.y < this.x.length - 1) {
            this.c(this.y + 1);
        }
    }

    public avp d(int n2) {
        return this.v.a(n2);
    }

    private void e(int n2, int n32) {
        int n32;
        for (f \u26032 : this.x[n2]) {
            if (\u26032 == null) continue;
            this.a(this.v.a(\u26032.b()), false);
        }
        for (f \u26032 : this.x[n32]) {
            if (\u26032 == null) continue;
            this.a(this.v.a(\u26032.b()), true);
        }
    }

    private void a(avp avp2, boolean bl2) {
        if (avp2 instanceof avs) {
            ((avs)avp2).m = bl2;
        } else if (avp2 instanceof avw) {
            ((avw)avp2).e(bl2);
        } else if (avp2 instanceof avy) {
            ((avy)avp2).j = bl2;
        }
    }

    private avp a(f f2, int n2, boolean bl2) {
        if (f2 instanceof g) {
            return this.a(this.b / 2 - 155 + n2, 0, (g)f2);
        }
        if (f2 instanceof a) {
            return this.a(this.b / 2 - 155 + n2, 0, (a)f2);
        }
        if (f2 instanceof c) {
            return this.a(this.b / 2 - 155 + n2, 0, (c)f2);
        }
        if (f2 instanceof e) {
            return this.a(this.b / 2 - 155 + n2, 0, (e)f2, bl2);
        }
        return null;
    }

    public void a(boolean bl2) {
        for (d d2 : this.u) {
            if (d2.b instanceof avs) {
                ((avs)((d)d2).b).l = bl2;
            }
            if (!(d2.c instanceof avs)) continue;
            ((avs)((d)d2).c).l = bl2;
        }
    }

    @Override
    public boolean b(int n2, int n3, int n4) {
        boolean bl2 = super.b(n2, n3, n4);
        int \u26032 = this.c(n2, n3);
        if (\u26032 >= 0) {
            d d2 = this.e(\u26032);
            if (this.A != d2.d && this.A != null && this.A instanceof avw) {
                ((avw)this.A).b(false);
            }
            this.A = d2.d;
        }
        return bl2;
    }

    private avx a(int n2, int n3, g g2) {
        avx avx2 = new avx(this.z, g2.b(), n2, n3, g2.c(), g2.e(), g2.f(), g2.g(), g2.a());
        avx2.m = g2.d();
        return avx2;
    }

    private awb a(int n2, int n3, a a2) {
        awb awb2 = new awb(this.z, a2.b(), n2, n3, a2.c(), a2.a());
        awb2.m = a2.d();
        return awb2;
    }

    private avw a(int n2, int n3, c c2) {
        avw avw2 = new avw(c2.b(), this.a.k, n2, n3, 150, 20);
        avw2.a(c2.c());
        avw2.a(this.z);
        avw2.e(c2.d());
        avw2.a(c2.a());
        return avw2;
    }

    private avy a(int n2, int n3, e e2, boolean bl2) {
        avy avy2 = bl2 ? new avy(this.a.k, e2.b(), n2, n3, this.b - n2 * 2, 20, -1) : new avy(this.a.k, e2.b(), n2, n3, 150, 20, -1);
        avy2.j = e2.d();
        avy2.a(e2.c());
        avy2.a();
        return avy2;
    }

    public void a(char c2, int n2) {
        if (!(this.A instanceof avw)) {
            return;
        }
        avw \u26035 = (avw)this.A;
        if (axu.e(n2)) {
            String string = axu.o();
            String[] \u26032 = string.split(";");
            int \u26033 = \u2603 = this.w.indexOf(this.A);
            for (String string2 : \u26032) {
                this.w.get(\u26033).a(string2);
                \u26033 = \u26033 == this.w.size() - 1 ? 0 : ++\u26033;
                if (\u26033 == \u2603) break;
            }
            return;
        }
        if (n2 == 15) {
            \u26035.b(false);
            int \u26034 = this.w.indexOf(this.A);
            \u26034 = axu.r() ? (\u26034 == 0 ? this.w.size() - 1 : --\u26034) : (\u26034 == this.w.size() - 1 ? 0 : ++\u26034);
            this.A = this.w.get(\u26034);
            \u26035 = (avw)this.A;
            \u26035.b(true);
            int \u26036 = \u26035.f + this.h;
            int \u26037 = \u26035.f;
            if (\u26036 > this.e) {
                this.n += (float)(\u26036 - this.e);
            } else if (\u26037 < this.d) {
                this.n = \u26037;
            }
        } else {
            \u26035.a(c2, n2);
        }
    }

    public d e(int n2) {
        return this.u.get(n2);
    }

    @Override
    public int b() {
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
        return this.e(n2);
    }

    public static interface b {
        public void a(int var1, boolean var2);

        public void a(int var1, float var2);

        public void a(int var1, String var2);
    }

    public static class e
    extends f {
        public e(int n2, String string, boolean bl2) {
            super(n2, string, bl2);
        }
    }

    public static class c
    extends f {
        private final Predicate<String> a;

        public c(int n2, String string, boolean bl2, Predicate<String> predicate) {
            super(n2, string, bl2);
            this.a = Objects.firstNonNull(predicate, Predicates.alwaysTrue());
        }

        public Predicate<String> a() {
            return this.a;
        }
    }

    public static class a
    extends f {
        private final boolean a;

        public a(int n2, String string, boolean bl2, boolean bl3) {
            super(n2, string, bl2);
            this.a = bl3;
        }

        public boolean a() {
            return this.a;
        }
    }

    public static class g
    extends f {
        private final avx.a a;
        private final float b;
        private final float c;
        private final float d;

        public g(int n2, String string, boolean bl2, avx.a a2, float f2, float f3, float f4) {
            super(n2, string, bl2);
            this.a = a2;
            this.b = f2;
            this.c = f3;
            this.d = f4;
        }

        public avx.a a() {
            return this.a;
        }

        public float e() {
            return this.b;
        }

        public float f() {
            return this.c;
        }

        public float g() {
            return this.d;
        }
    }

    public static class f {
        private final int a;
        private final String b;
        private final boolean c;

        public f(int n2, String string, boolean bl2) {
            this.a = n2;
            this.b = string;
            this.c = bl2;
        }

        public int b() {
            return this.a;
        }

        public String c() {
            return this.b;
        }

        public boolean d() {
            return this.c;
        }
    }

    public static class d
    implements awd.a {
        private final ave a = ave.A();
        private final avp b;
        private final avp c;
        private avp d;

        public d(avp avp2, avp avp3) {
            this.b = avp2;
            this.c = avp3;
        }

        public avp a() {
            return this.b;
        }

        public avp b() {
            return this.c;
        }

        @Override
        public void a(int n2, int n3, int n4, int n5, int n6, int n7, int n8, boolean bl2) {
            this.a(this.b, n4, n7, n8, false);
            this.a(this.c, n4, n7, n8, false);
        }

        private void a(avp avp2, int n2, int n3, int n4, boolean bl2) {
            if (avp2 == null) {
                return;
            }
            if (avp2 instanceof avs) {
                this.a((avs)avp2, n2, n3, n4, bl2);
            } else if (avp2 instanceof avw) {
                this.a((avw)avp2, n2, bl2);
            } else if (avp2 instanceof avy) {
                this.a((avy)avp2, n2, n3, n4, bl2);
            }
        }

        private void a(avs avs2, int n2, int n3, int n4, boolean bl2) {
            avs2.i = n2;
            if (!bl2) {
                avs2.a(this.a, n3, n4);
            }
        }

        private void a(avw avw2, int n2, boolean bl2) {
            avw2.f = n2;
            if (!bl2) {
                avw2.g();
            }
        }

        private void a(avy avy2, int n2, int n3, int n4, boolean bl2) {
            avy2.h = n2;
            if (!bl2) {
                avy2.a(this.a, n3, n4);
            }
        }

        @Override
        public void a(int n2, int n3, int n4) {
            this.a(this.b, n4, 0, 0, true);
            this.a(this.c, n4, 0, 0, true);
        }

        @Override
        public boolean a(int n2, int n3, int n4, int n5, int n6, int n7) {
            boolean bl2 = this.a(this.b, n3, n4, n5);
            \u2603 = this.a(this.c, n3, n4, n5);
            return bl2 || \u2603;
        }

        private boolean a(avp avp2, int n2, int n3, int n4) {
            if (avp2 == null) {
                return false;
            }
            if (avp2 instanceof avs) {
                return this.a((avs)avp2, n2, n3, n4);
            }
            if (avp2 instanceof avw) {
                this.a((avw)avp2, n2, n3, n4);
            }
            return false;
        }

        private boolean a(avs avs2, int n2, int n3, int n4) {
            boolean bl2 = avs2.c(this.a, n2, n3);
            if (bl2) {
                this.d = avs2;
            }
            return bl2;
        }

        private void a(avw avw2, int n2, int n3, int n4) {
            avw2.a(n2, n3, n4);
            if (avw2.m()) {
                this.d = avw2;
            }
        }

        @Override
        public void b(int n2, int n3, int n4, int n5, int n6, int n7) {
            this.b(this.b, n3, n4, n5);
            this.b(this.c, n3, n4, n5);
        }

        private void b(avp avp2, int n2, int n3, int n4) {
            if (avp2 == null) {
                return;
            }
            if (avp2 instanceof avs) {
                this.b((avs)avp2, n2, n3, n4);
            }
        }

        private void b(avs avs2, int n2, int n3, int n4) {
            avs2.a(n2, n3);
        }
    }
}

