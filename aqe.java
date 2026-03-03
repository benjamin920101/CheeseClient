/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Objects;

public class aqe {
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public aqe() {
    }

    public aqe(int[] nArray) {
        if (nArray.length == 6) {
            this.a = nArray[0];
            this.b = nArray[1];
            this.c = nArray[2];
            this.d = nArray[3];
            this.e = nArray[4];
            this.f = nArray[5];
        }
    }

    public static aqe a() {
        return new aqe(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public static aqe a(int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, cq cq2) {
        switch (cq2) {
            default: {
                return new aqe(n2 + n5, n3 + n6, n4 + n7, n2 + n8 - 1 + n5, n3 + n9 - 1 + n6, n4 + n10 - 1 + n7);
            }
            case c: {
                return new aqe(n2 + n5, n3 + n6, n4 - n10 + 1 + n7, n2 + n8 - 1 + n5, n3 + n9 - 1 + n6, n4 + n7);
            }
            case d: {
                return new aqe(n2 + n5, n3 + n6, n4 + n7, n2 + n8 - 1 + n5, n3 + n9 - 1 + n6, n4 + n10 - 1 + n7);
            }
            case e: {
                return new aqe(n2 - n10 + 1 + n7, n3 + n6, n4 + n5, n2 + n7, n3 + n9 - 1 + n6, n4 + n8 - 1 + n5);
            }
            case f: 
        }
        return new aqe(n2 + n7, n3 + n6, n4 + n5, n2 + n10 - 1 + n7, n3 + n9 - 1 + n6, n4 + n8 - 1 + n5);
    }

    public static aqe a(int n2, int n3, int n4, int n5, int n6, int n7) {
        return new aqe(Math.min(n2, n5), Math.min(n3, n6), Math.min(n4, n7), Math.max(n2, n5), Math.max(n3, n6), Math.max(n4, n7));
    }

    public aqe(aqe aqe2) {
        this.a = aqe2.a;
        this.b = aqe2.b;
        this.c = aqe2.c;
        this.d = aqe2.d;
        this.e = aqe2.e;
        this.f = aqe2.f;
    }

    public aqe(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.a = n2;
        this.b = n3;
        this.c = n4;
        this.d = n5;
        this.e = n6;
        this.f = n7;
    }

    public aqe(df df2, df df3) {
        this.a = Math.min(df2.n(), df3.n());
        this.b = Math.min(df2.o(), df3.o());
        this.c = Math.min(df2.p(), df3.p());
        this.d = Math.max(df2.n(), df3.n());
        this.e = Math.max(df2.o(), df3.o());
        this.f = Math.max(df2.p(), df3.p());
    }

    public aqe(int n2, int n3, int n4, int n5) {
        this.a = n2;
        this.c = n3;
        this.d = n4;
        this.f = n5;
        this.b = 1;
        this.e = 512;
    }

    public boolean a(aqe aqe2) {
        return this.d >= aqe2.a && this.a <= aqe2.d && this.f >= aqe2.c && this.c <= aqe2.f && this.e >= aqe2.b && this.b <= aqe2.e;
    }

    public boolean a(int n2, int n3, int n4, int n5) {
        return this.d >= n2 && this.a <= n4 && this.f >= n3 && this.c <= n5;
    }

    public void b(aqe aqe2) {
        this.a = Math.min(this.a, aqe2.a);
        this.b = Math.min(this.b, aqe2.b);
        this.c = Math.min(this.c, aqe2.c);
        this.d = Math.max(this.d, aqe2.d);
        this.e = Math.max(this.e, aqe2.e);
        this.f = Math.max(this.f, aqe2.f);
    }

    public void a(int n2, int n3, int n4) {
        this.a += n2;
        this.b += n3;
        this.c += n4;
        this.d += n2;
        this.e += n3;
        this.f += n4;
    }

    public boolean b(df df2) {
        return df2.n() >= this.a && df2.n() <= this.d && df2.p() >= this.c && df2.p() <= this.f && df2.o() >= this.b && df2.o() <= this.e;
    }

    public df b() {
        return new df(this.d - this.a, this.e - this.b, this.f - this.c);
    }

    public int c() {
        return this.d - this.a + 1;
    }

    public int d() {
        return this.e - this.b + 1;
    }

    public int e() {
        return this.f - this.c + 1;
    }

    public df f() {
        return new cj(this.a + (this.d - this.a + 1) / 2, this.b + (this.e - this.b + 1) / 2, this.c + (this.f - this.c + 1) / 2);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("x0", this.a).add("y0", this.b).add("z0", this.c).add("x1", this.d).add("y1", this.e).add("z1", this.f).toString();
    }

    public ds g() {
        return new ds(new int[]{this.a, this.b, this.c, this.d, this.e, this.f});
    }
}

