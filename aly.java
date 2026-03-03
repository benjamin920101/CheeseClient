/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public abstract class aly
implements alz {
    private static final Joiner a = Joiner.on(',');
    private static final Function<Map.Entry<amo, Comparable>, String> b = new Function<Map.Entry<amo, Comparable>, String>(){

        public String a(Map.Entry<amo, Comparable> entry) {
            if (entry == null) {
                return "<NULL>";
            }
            amo amo2 = entry.getKey();
            return amo2.a() + "=" + amo2.a(entry.getValue());
        }

        @Override
        public /* synthetic */ Object apply(Object object) {
            return this.a((Map.Entry)object);
        }
    };

    @Override
    public <T extends Comparable<T>> alz a(amo<T> amo2) {
        return this.a(amo2, (Comparable)aly.a(amo2.c(), this.b(amo2)));
    }

    protected static <T> T a(Collection<T> collection, T t2) {
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().equals(t2)) continue;
            if (iterator.hasNext()) {
                return iterator.next();
            }
            return collection.iterator().next();
        }
        return iterator.next();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(afh.c.c(this.c()));
        if (!this.b().isEmpty()) {
            stringBuilder.append("[");
            a.appendTo(stringBuilder, Iterables.transform(this.b().entrySet(), b));
            stringBuilder.append("]");
        }
        return stringBuilder.toString();
    }
}

