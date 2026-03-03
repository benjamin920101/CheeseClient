/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public abstract class es
implements eu {
    protected List<eu> a = Lists.newArrayList();
    private ez b;

    @Override
    public eu a(eu eu2) {
        eu2.b().a(this.b());
        this.a.add(eu2);
        return this;
    }

    @Override
    public List<eu> a() {
        return this.a;
    }

    @Override
    public eu a(String string) {
        return this.a(new fa(string));
    }

    @Override
    public eu a(ez ez2) {
        this.b = ez2;
        for (eu eu2 : this.a) {
            eu2.b().a(this.b());
        }
        return this;
    }

    @Override
    public ez b() {
        if (this.b == null) {
            this.b = new ez();
            for (eu eu2 : this.a) {
                eu2.b().a(this.b);
            }
        }
        return this.b;
    }

    @Override
    public Iterator<eu> iterator() {
        return Iterators.concat(Iterators.forArray(this), es.a(this.a));
    }

    @Override
    public final String c() {
        StringBuilder stringBuilder = new StringBuilder();
        for (eu eu2 : this) {
            stringBuilder.append(eu2.e());
        }
        return stringBuilder.toString();
    }

    @Override
    public final String d() {
        StringBuilder stringBuilder = new StringBuilder();
        for (eu eu2 : this) {
            stringBuilder.append(eu2.b().k());
            stringBuilder.append(eu2.e());
            stringBuilder.append((Object)a.v);
        }
        return stringBuilder.toString();
    }

    public static Iterator<eu> a(Iterable<eu> iterable) {
        Iterator<eu> iterator = Iterators.concat(Iterators.transform(iterable.iterator(), new Function<eu, Iterator<eu>>(){

            public Iterator<eu> a(eu eu2) {
                return eu2.iterator();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((eu)object);
            }
        }));
        iterator = Iterators.transform(iterator, new Function<eu, eu>(){

            public eu a(eu eu2) {
                \u2603 = eu2.f();
                \u2603.a(\u2603.b().n());
                return \u2603;
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((eu)object);
            }
        });
        return iterator;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof es) {
            es es2 = (es)object;
            return this.a.equals(es2.a) && this.b().equals(es2.b());
        }
        return false;
    }

    public int hashCode() {
        return 31 * this.b.hashCode() + this.a.hashCode();
    }

    public String toString() {
        return "BaseComponent{style=" + this.b + ", siblings=" + this.a + '}';
    }
}

