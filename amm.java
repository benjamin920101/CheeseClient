/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;

public class amm<T extends Enum<T>>
extends amj<T> {
    private final ImmutableSet<T> a;
    private final Map<String, T> b = Maps.newHashMap();

    protected amm(String string, Class<T> clazz, Collection<T> collection) {
        super(string, clazz);
        this.a = ImmutableSet.copyOf(collection);
        for (Enum enum_ : collection) {
            String string2 = ((nw)((Object)enum_)).l();
            if (this.b.containsKey(string2)) {
                throw new IllegalArgumentException("Multiple values have the same name '" + string2 + "'");
            }
            this.b.put(string2, enum_);
        }
    }

    @Override
    public Collection<T> c() {
        return this.a;
    }

    @Override
    public String a(T t2) {
        return ((nw)t2).l();
    }

    public static <T extends Enum<T>> amm<T> a(String string, Class<T> clazz) {
        return amm.a(string, clazz, Predicates.alwaysTrue());
    }

    public static <T extends Enum<T>> amm<T> a(String string, Class<T> clazz, Predicate<T> predicate) {
        return amm.a(string, clazz, Collections2.filter(Lists.newArrayList(clazz.getEnumConstants()), predicate));
    }

    public static <T extends Enum<T>> amm<T> a(String string, Class<T> clazz, T ... TArray) {
        return amm.a(string, clazz, Lists.newArrayList(TArray));
    }

    public static <T extends Enum<T>> amm<T> a(String string, Class<T> clazz, Collection<T> collection) {
        return new amm<T>(string, clazz, collection);
    }
}

