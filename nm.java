/*
 * Decompiled with CFR 0.152.
 */
public class nm<V> {
    private transient a<V>[] a = new a[16];
    private transient int b;
    private int c = 12;
    private final float d;

    public nm() {
        this.d = 0.75f;
    }

    private static int g(int n2) {
        n2 ^= n2 >>> 20 ^ n2 >>> 12;
        return n2 ^ n2 >>> 7 ^ n2 >>> 4;
    }

    private static int a(int n2, int n3) {
        return n2 & n3 - 1;
    }

    public V a(int n2) {
        \u2603 = nm.g(n2);
        a<V> a2 = this.a[nm.a(\u2603, this.a.length)];
        while (a2 != null) {
            if (a2.a == n2) {
                return a2.b;
            }
            a2 = a2.c;
        }
        return null;
    }

    public boolean b(int n2) {
        return this.c(n2) != null;
    }

    final a<V> c(int n2) {
        \u2603 = nm.g(n2);
        a<V> a2 = this.a[nm.a(\u2603, this.a.length)];
        while (a2 != null) {
            if (a2.a == n2) {
                return a2;
            }
            a2 = a2.c;
        }
        return null;
    }

    public void a(int n2, V v2) {
        int n3 = nm.g(n2);
        \u2603 = nm.a(n3, this.a.length);
        a<V> \u26032 = this.a[\u2603];
        while (\u26032 != null) {
            if (\u26032.a == n2) {
                \u26032.b = v2;
                return;
            }
            \u26032 = \u26032.c;
        }
        this.a(n3, n2, v2, \u2603);
    }

    private void h(int n2) {
        a<V>[] aArray = this.a;
        int \u26032 = aArray.length;
        if (\u26032 == 0x40000000) {
            this.c = Integer.MAX_VALUE;
            return;
        }
        a[] \u26033 = new a[n2];
        this.a(\u26033);
        this.a = \u26033;
        this.c = (int)((float)n2 * this.d);
    }

    private void a(a<V>[] aArray) {
        \u2603 = this.a;
        int n2 = aArray.length;
        for (\u2603 = 0; \u2603 < \u2603.length; ++\u2603) {
            a<V> a2 = \u2603[\u2603];
            if (a2 == null) continue;
            \u2603[\u2603] = null;
            do {
                \u2603 = a2.c;
                int n3 = nm.a(a2.d, n2);
                a2.c = aArray[n3];
                aArray[n3] = a2;
            } while ((a2 = \u2603) != null);
        }
    }

    public V d(int n2) {
        a<V> a2 = this.e(n2);
        return a2 == null ? null : (V)a2.b;
    }

    final a<V> e(int n2) {
        \u2603 = nm.g(n2);
        \u2603 = nm.a(\u2603, this.a.length);
        a<V> a2 = \u2603 = this.a[\u2603];
        while (a2 != null) {
            \u2603 = a2.c;
            if (a2.a == n2) {
                --this.b;
                if (\u2603 == a2) {
                    this.a[\u2603] = \u2603;
                } else {
                    \u2603.c = \u2603;
                }
                return a2;
            }
            \u2603 = a2;
            a2 = \u2603;
        }
        return a2;
    }

    public void c() {
        a<V>[] aArray = this.a;
        for (int i2 = 0; i2 < aArray.length; ++i2) {
            aArray[i2] = null;
        }
        this.b = 0;
    }

    private void a(int n2, int n3, V v2, int n4) {
        a<V> a2 = this.a[n4];
        this.a[n4] = new a<V>(n2, n3, v2, a2);
        if (this.b++ >= this.c) {
            this.h(2 * this.a.length);
        }
    }

    static class a<V> {
        final int a;
        V b;
        a<V> c;
        final int d;

        a(int n2, int n3, V v2, a<V> a2) {
            this.b = v2;
            this.c = a2;
            this.a = n3;
            this.d = n2;
        }

        public final int a() {
            return this.a;
        }

        public final V b() {
            return this.b;
        }

        public final boolean equals(Object object) {
            if (!(object instanceof a)) {
                return false;
            }
            a a2 = (a)object;
            Integer \u26032 = this.a();
            return (\u26032 == (\u2603 = Integer.valueOf(a2.a())) || \u26032 != null && ((Object)\u26032).equals(\u2603)) && ((\u2603 = this.b()) == (\u2603 = a2.b()) || \u2603 != null && \u2603.equals(\u2603));
        }

        public final int hashCode() {
            return nm.g(this.a);
        }

        public final String toString() {
            return this.a() + "=" + this.b();
        }
    }
}

