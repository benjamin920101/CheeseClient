/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class qs<T extends pk>
extends rd {
    private final Predicate<pk> c = new Predicate<pk>(){

        public boolean a(pk pk2) {
            return pk2.ai() && qs.this.a.t().a(pk2);
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((pk)object);
        }
    };
    protected py a;
    private double d;
    private double e;
    protected T b;
    private float f;
    private asx g;
    private sw h;
    private Class<T> i;
    private Predicate<? super T> j;

    public qs(py py2, Class<T> clazz, float f2, double d2, double d3) {
        this(py2, clazz, Predicates.alwaysTrue(), f2, d2, d3);
    }

    public qs(py py2, Class<T> clazz, Predicate<? super T> predicate, float f2, double d2, double d3) {
        this.a = py2;
        this.i = clazz;
        this.j = predicate;
        this.f = f2;
        this.d = d2;
        this.e = d3;
        this.h = py2.s();
        this.a(1);
    }

    @Override
    public boolean a() {
        Predicate predicate = this.a.o.a(this.i, this.a.aR().b(this.f, 3.0, this.f), Predicates.and(po.d, this.c, this.j));
        if (predicate.isEmpty()) {
            return false;
        }
        this.b = (pk)predicate.get(0);
        aui \u26032 = tc.b(this.a, 16, 7, new aui(((pk)this.b).s, ((pk)this.b).t, ((pk)this.b).u));
        if (\u26032 == null) {
            return false;
        }
        if (((pk)this.b).e(\u26032.a, \u26032.b, \u26032.c) < ((pk)this.b).h(this.a)) {
            return false;
        }
        this.g = this.h.a(\u26032.a, \u26032.b, \u26032.c);
        if (this.g == null) {
            return false;
        }
        return this.g.b(\u26032);
    }

    @Override
    public boolean b() {
        return !this.h.m();
    }

    @Override
    public void c() {
        this.h.a(this.g, this.d);
    }

    @Override
    public void d() {
        this.b = null;
    }

    @Override
    public void e() {
        if (this.a.h((pk)this.b) < 49.0) {
            this.a.s().a(this.e);
        } else {
            this.a.s().a(this.d);
        }
    }
}

