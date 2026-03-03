/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.AbstractIterator;
import java.util.Iterator;

public class cj
extends df {
    public static final cj a = new cj(0, 0, 0);
    private static final int c;
    private static final int d;
    private static final int e;
    private static final int f;
    private static final int g;
    private static final long h;
    private static final long i;
    private static final long j;

    public cj(int n2, int n3, int n4) {
        super(n2, n3, n4);
    }

    public cj(double d2, double d3, double d4) {
        super(d2, d3, d4);
    }

    public cj(pk pk2) {
        this(pk2.s, pk2.t, pk2.u);
    }

    public cj(aui aui2) {
        this(aui2.a, aui2.b, aui2.c);
    }

    public cj(df df2) {
        this(df2.n(), df2.o(), df2.p());
    }

    public cj a(double d2, double d3, double d4) {
        if (d2 == 0.0 && d3 == 0.0 && d4 == 0.0) {
            return this;
        }
        return new cj((double)this.n() + d2, (double)this.o() + d3, (double)this.p() + d4);
    }

    public cj a(int n2, int n3, int n4) {
        if (n2 == 0 && n3 == 0 && n4 == 0) {
            return this;
        }
        return new cj(this.n() + n2, this.o() + n3, this.p() + n4);
    }

    public cj a(df df2) {
        if (df2.n() == 0 && df2.o() == 0 && df2.p() == 0) {
            return this;
        }
        return new cj(this.n() + df2.n(), this.o() + df2.o(), this.p() + df2.p());
    }

    public cj b(df df2) {
        if (df2.n() == 0 && df2.o() == 0 && df2.p() == 0) {
            return this;
        }
        return new cj(this.n() - df2.n(), this.o() - df2.o(), this.p() - df2.p());
    }

    public cj a() {
        return this.b(1);
    }

    public cj b(int n2) {
        return this.a(cq.b, n2);
    }

    public cj b() {
        return this.c(1);
    }

    public cj c(int n2) {
        return this.a(cq.a, n2);
    }

    public cj c() {
        return this.d(1);
    }

    public cj d(int n2) {
        return this.a(cq.c, n2);
    }

    public cj d() {
        return this.e(1);
    }

    public cj e(int n2) {
        return this.a(cq.d, n2);
    }

    public cj e() {
        return this.f(1);
    }

    public cj f(int n2) {
        return this.a(cq.e, n2);
    }

    public cj f() {
        return this.g(1);
    }

    public cj g(int n2) {
        return this.a(cq.f, n2);
    }

    public cj a(cq cq2) {
        return this.a(cq2, 1);
    }

    public cj a(cq cq2, int n2) {
        if (n2 == 0) {
            return this;
        }
        return new cj(this.n() + cq2.g() * n2, this.o() + cq2.h() * n2, this.p() + cq2.i() * n2);
    }

    public cj c(df df2) {
        return new cj(this.o() * df2.p() - this.p() * df2.o(), this.p() * df2.n() - this.n() * df2.p(), this.n() * df2.o() - this.o() * df2.n());
    }

    public long g() {
        return ((long)this.n() & h) << g | ((long)this.o() & i) << f | ((long)this.p() & j) << 0;
    }

    public static cj a(long l2) {
        int n2 = (int)(l2 << 64 - g - c >> 64 - c);
        \u2603 = (int)(l2 << 64 - f - e >> 64 - e);
        \u2603 = (int)(l2 << 64 - d >> 64 - d);
        return new cj(n2, \u2603, \u2603);
    }

    public static Iterable<cj> a(cj cj2, cj cj3) {
        \u2603 = new cj(Math.min(cj2.n(), cj3.n()), Math.min(cj2.o(), cj3.o()), Math.min(cj2.p(), cj3.p()));
        \u2603 = new cj(Math.max(cj2.n(), cj3.n()), Math.max(cj2.o(), cj3.o()), Math.max(cj2.p(), cj3.p()));
        return new Iterable<cj>(){

            @Override
            public Iterator<cj> iterator() {
                return new AbstractIterator<cj>(){
                    private cj b = null;

                    protected cj a() {
                        if (this.b == null) {
                            this.b = \u2603;
                            return this.b;
                        }
                        if (this.b.equals(\u2603)) {
                            return (cj)this.endOfData();
                        }
                        int n2 = this.b.n();
                        \u2603 = this.b.o();
                        \u2603 = this.b.p();
                        if (n2 < \u2603.n()) {
                            ++n2;
                        } else if (\u2603 < \u2603.o()) {
                            n2 = \u2603.n();
                            ++\u2603;
                        } else if (\u2603 < \u2603.p()) {
                            n2 = \u2603.n();
                            \u2603 = \u2603.o();
                            ++\u2603;
                        }
                        this.b = new cj(n2, \u2603, \u2603);
                        return this.b;
                    }

                    @Override
                    protected /* synthetic */ Object computeNext() {
                        return this.a();
                    }
                };
            }
        };
    }

    public static Iterable<a> b(cj cj2, cj cj3) {
        \u2603 = new cj(Math.min(cj2.n(), cj3.n()), Math.min(cj2.o(), cj3.o()), Math.min(cj2.p(), cj3.p()));
        \u2603 = new cj(Math.max(cj2.n(), cj3.n()), Math.max(cj2.o(), cj3.o()), Math.max(cj2.p(), cj3.p()));
        return new Iterable<a>(){

            @Override
            public Iterator<a> iterator() {
                return new AbstractIterator<a>(){
                    private a b = null;

                    protected a a() {
                        if (this.b == null) {
                            this.b = new a(\u2603.n(), \u2603.o(), \u2603.p());
                            return this.b;
                        }
                        if (this.b.equals(\u2603)) {
                            return (a)this.endOfData();
                        }
                        int n2 = this.b.n();
                        \u2603 = this.b.o();
                        \u2603 = this.b.p();
                        if (n2 < \u2603.n()) {
                            ++n2;
                        } else if (\u2603 < \u2603.o()) {
                            n2 = \u2603.n();
                            ++\u2603;
                        } else if (\u2603 < \u2603.p()) {
                            n2 = \u2603.n();
                            \u2603 = \u2603.o();
                            ++\u2603;
                        }
                        this.b.c = n2;
                        this.b.d = \u2603;
                        this.b.e = \u2603;
                        return this.b;
                    }

                    @Override
                    protected /* synthetic */ Object computeNext() {
                        return this.a();
                    }
                };
            }
        };
    }

    @Override
    public /* synthetic */ df d(df df2) {
        return this.c(df2);
    }

    static {
        d = c = 1 + ns.c(ns.b(30000000));
        e = 64 - c - d;
        f = 0 + d;
        g = f + e;
        h = (1L << c) - 1L;
        i = (1L << e) - 1L;
        j = (1L << d) - 1L;
    }

    public static final class a
    extends cj {
        private int c;
        private int d;
        private int e;

        public a() {
            this(0, 0, 0);
        }

        public a(int n2, int n3, int n4) {
            super(0, 0, 0);
            this.c = n2;
            this.d = n3;
            this.e = n4;
        }

        @Override
        public int n() {
            return this.c;
        }

        @Override
        public int o() {
            return this.d;
        }

        @Override
        public int p() {
            return this.e;
        }

        public a c(int n2, int n3, int n4) {
            this.c = n2;
            this.d = n3;
            this.e = n4;
            return this;
        }

        @Override
        public /* synthetic */ df d(df df2) {
            return super.c(df2);
        }
    }
}

