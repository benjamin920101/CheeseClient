/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collections;
import java.util.Comparator;

public class sp<T extends pr>
extends st {
    protected final Class<T> a;
    private final int g;
    protected final a b;
    protected Predicate<? super T> c;
    protected pr d;

    public sp(py py2, Class<T> clazz, boolean bl2) {
        this(py2, clazz, bl2, false);
    }

    public sp(py py2, Class<T> clazz, boolean bl2, boolean bl3) {
        this(py2, clazz, 10, bl2, bl3, null);
    }

    public sp(py py2, Class<T> clazz, int n2, boolean bl2, boolean bl3, final Predicate<? super T> predicate) {
        super(py2, bl2, bl3);
        this.a = clazz;
        this.g = n2;
        this.b = new a(py2);
        this.a(1);
        this.c = new Predicate<T>(){

            public boolean a(T t22) {
                Object t22;
                if (predicate != null && !predicate.apply(t22)) {
                    return false;
                }
                if (t22 instanceof wn) {
                    double d2 = sp.this.f();
                    if (((pk)t22).av()) {
                        d2 *= (double)0.8f;
                    }
                    if (((pk)t22).ax()) {
                        float f2 = ((wn)t22).bY();
                        if (f2 < 0.1f) {
                            f2 = 0.1f;
                        }
                        d2 *= (double)(0.7f * f2);
                    }
                    if ((double)((pk)t22).g(sp.this.e) > d2) {
                        return false;
                    }
                }
                return sp.this.a((pr)t22, false);
            }

            @Override
            public /* synthetic */ boolean apply(Object object) {
                return this.a((pr)object);
            }
        };
    }

    @Override
    public boolean a() {
        if (this.g > 0 && this.e.bc().nextInt(this.g) != 0) {
            return false;
        }
        double d2 = this.f();
        Predicate<pk> \u26032 = this.e.o.a(this.a, this.e.aR().b(d2, 4.0, d2), Predicates.and(this.c, po.d));
        Collections.sort(\u26032, this.b);
        if (\u26032.isEmpty()) {
            return false;
        }
        this.d = (pr)\u26032.get(0);
        return true;
    }

    @Override
    public void c() {
        this.e.d(this.d);
        super.c();
    }

    public static class a
    implements Comparator<pk> {
        private final pk a;

        public a(pk pk2) {
            this.a = pk2;
        }

        public int a(pk pk2, pk pk3) {
            double d2 = this.a.h(pk2);
            if (d2 < (\u2603 = this.a.h(pk3))) {
                return -1;
            }
            if (d2 > \u2603) {
                return 1;
            }
            return 0;
        }

        @Override
        public /* synthetic */ int compare(Object object, Object object2) {
            return this.a((pk)object, (pk)object2);
        }
    }
}

