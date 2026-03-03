/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class bmf {
    private final int a;
    private final Set<a> b = Sets.newHashSetWithExpectedSize(256);
    private final List<b> c = Lists.newArrayListWithCapacity(256);
    private int d;
    private int e;
    private final int f;
    private final int g;
    private final boolean h;
    private final int i;

    public bmf(int n2, int n3, boolean bl2, int n4, int n5) {
        this.a = n5;
        this.f = n2;
        this.g = n3;
        this.h = bl2;
        this.i = n4;
    }

    public int a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public void a(bmi bmi2) {
        a a2 = new a(bmi2, this.a);
        if (this.i > 0) {
            a2.a(this.i);
        }
        this.b.add(a2);
    }

    public void c() {
        Object[] objectArray = this.b.toArray(new a[this.b.size()]);
        Arrays.sort(objectArray);
        for (Object object : objectArray) {
            if (this.a((a)object)) continue;
            String string = String.format("Unable to fit: %s - size: %dx%d - Maybe try a lowerresolution resourcepack?", ((a)object).a().i(), ((a)object).a().c(), ((a)object).a().d());
            throw new bmg((a)object, string);
        }
        if (this.h) {
            this.d = ns.b(this.d);
            this.e = ns.b(this.e);
        }
    }

    public List<bmi> d() {
        ArrayList<b> arrayList = Lists.newArrayList();
        for (b b2 : this.c) {
            b2.a(arrayList);
        }
        ArrayList<bmi> arrayList2 = Lists.newArrayList();
        for (b b2 : arrayList) {
            a a2 = b2.a();
            bmi \u26032 = a2.a();
            \u26032.a(this.d, this.e, b2.b(), b2.c(), a2.e());
            arrayList2.add(\u26032);
        }
        return arrayList2;
    }

    private static int b(int n2, int n3) {
        return (n2 >> n3) + ((n2 & (1 << n3) - 1) == 0 ? 0 : 1) << n3;
    }

    private boolean a(a a22) {
        a a22;
        for (int i2 = 0; i2 < this.c.size(); ++i2) {
            if (this.c.get(i2).a(a22)) {
                return true;
            }
            a22.d();
            if (this.c.get(i2).a(a22)) {
                return true;
            }
            a22.d();
        }
        return this.b(a22);
    }

    private boolean b(a a2) {
        b \u26033;
        boolean \u26032;
        int n2 = Math.min(a2.b(), a2.c());
        boolean bl2 = \u2603 = this.d == 0 && this.e == 0;
        if (this.h) {
            \u2603 = ns.b(this.d);
            \u2603 = ns.b(this.e);
            \u2603 = ns.b(this.d + n2);
            \u2603 = ns.b(this.e + n2);
            boolean bl3 = \u2603 <= this.f;
            boolean bl4 = \u2603 = \u2603 <= this.g;
            if (!bl3 && !\u2603) {
                return false;
            }
            \u2603 = \u2603 != \u2603;
            boolean bl5 = \u2603 = \u2603 != \u2603;
            \u26032 = \u2603 ^ \u2603 ? !\u2603 : bl3 && \u2603 <= \u2603;
        } else {
            \u2603 = this.d + n2 <= this.f ? 1 : 0;
            int n3 = \u2603 = this.e + n2 <= this.g ? 1 : 0;
            if (\u2603 == 0 && \u2603 == 0) {
                return false;
            }
            \u26032 = \u2603 != 0 && (\u2603 || this.d <= this.e);
        }
        \u2603 = Math.max(a2.b(), a2.c());
        if (ns.b((\u26032 ? this.e : this.d) + \u2603) > (\u26032 ? this.g : this.f)) {
            return false;
        }
        if (\u26032) {
            if (a2.b() > a2.c()) {
                a2.d();
            }
            if (this.e == 0) {
                this.e = a2.c();
            }
            \u26033 = new b(this.d, 0, a2.b(), this.e);
            this.d += a2.b();
        } else {
            \u26033 = new b(0, this.e, this.d, a2.c());
            this.e += a2.c();
        }
        \u26033.a(a2);
        this.c.add(\u26033);
        return true;
    }

    public static class b {
        private final int a;
        private final int b;
        private final int c;
        private final int d;
        private List<b> e;
        private a f;

        public b(int n2, int n3, int n4, int n5) {
            this.a = n2;
            this.b = n3;
            this.c = n4;
            this.d = n5;
        }

        public a a() {
            return this.f;
        }

        public int b() {
            return this.a;
        }

        public int c() {
            return this.b;
        }

        public boolean a(a a2) {
            if (this.f != null) {
                return false;
            }
            int n2 = a2.b();
            \u2603 = a2.c();
            if (n2 > this.c || \u2603 > this.d) {
                return false;
            }
            if (n2 == this.c && \u2603 == this.d) {
                this.f = a2;
                return true;
            }
            if (this.e == null) {
                this.e = Lists.newArrayListWithCapacity(1);
                this.e.add(new b(this.a, this.b, n2, \u2603));
                \u2603 = this.c - n2;
                \u2603 = this.d - \u2603;
                if (\u2603 > 0 && \u2603 > 0) {
                    \u2603 = Math.max(this.d, \u2603);
                    if (\u2603 >= (\u2603 = Math.max(this.c, \u2603))) {
                        this.e.add(new b(this.a, this.b + \u2603, n2, \u2603));
                        this.e.add(new b(this.a + n2, this.b, \u2603, this.d));
                    } else {
                        this.e.add(new b(this.a + n2, this.b, \u2603, \u2603));
                        this.e.add(new b(this.a, this.b + \u2603, this.c, \u2603));
                    }
                } else if (\u2603 == 0) {
                    this.e.add(new b(this.a, this.b + \u2603, n2, \u2603));
                } else if (\u2603 == 0) {
                    this.e.add(new b(this.a + n2, this.b, \u2603, \u2603));
                }
            }
            for (b b2 : this.e) {
                if (!b2.a(a2)) continue;
                return true;
            }
            return false;
        }

        public void a(List<b> list) {
            if (this.f != null) {
                list.add(this);
            } else if (this.e != null) {
                for (b b2 : this.e) {
                    b2.a(list);
                }
            }
        }

        public String toString() {
            return "Slot{originX=" + this.a + ", originY=" + this.b + ", width=" + this.c + ", height=" + this.d + ", texture=" + this.f + ", subSlots=" + this.e + '}';
        }
    }

    public static class a
    implements Comparable<a> {
        private final bmi a;
        private final int b;
        private final int c;
        private final int d;
        private boolean e;
        private float f = 1.0f;

        public a(bmi bmi2, int n2) {
            this.a = bmi2;
            this.b = bmi2.c();
            this.c = bmi2.d();
            this.d = n2;
            this.e = bmf.b(this.c, n2) > bmf.b(this.b, n2);
        }

        public bmi a() {
            return this.a;
        }

        public int b() {
            return this.e ? bmf.b((int)((float)this.c * this.f), this.d) : bmf.b((int)((float)this.b * this.f), this.d);
        }

        public int c() {
            return this.e ? bmf.b((int)((float)this.b * this.f), this.d) : bmf.b((int)((float)this.c * this.f), this.d);
        }

        public void d() {
            this.e = !this.e;
        }

        public boolean e() {
            return this.e;
        }

        public void a(int n2) {
            if (this.b <= n2 || this.c <= n2) {
                return;
            }
            this.f = (float)n2 / (float)Math.min(this.b, this.c);
        }

        public String toString() {
            return "Holder{width=" + this.b + ", height=" + this.c + '}';
        }

        public int a(a a22) {
            int \u26032;
            if (this.c() == a22.c()) {
                if (this.b() == a22.b()) {
                    if (this.a.i() == null) {
                        return a22.a.i() == null ? 0 : -1;
                    }
                    return this.a.i().compareTo(a22.a.i());
                }
                \u26032 = this.b() < a22.b() ? 1 : -1;
            } else {
                a a22;
                \u26032 = this.c() < a22.c() ? 1 : -1;
            }
            return \u26032;
        }

        @Override
        public /* synthetic */ int compareTo(Object object) {
            return this.a((a)object);
        }
    }
}

