/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.util.Map;

public class amh
implements Predicate<alz> {
    private final ama a;
    private final Map<amo, Predicate> b = Maps.newHashMap();

    private amh(ama ama2) {
        this.a = ama2;
    }

    public static amh a(afh afh2) {
        return new amh(afh2.P());
    }

    public boolean a(alz alz2) {
        if (alz2 == null || !alz2.c().equals(this.a.c())) {
            return false;
        }
        for (Map.Entry<amo, Predicate> entry : this.b.entrySet()) {
            Object t2 = alz2.b(entry.getKey());
            if (entry.getValue().apply(t2)) continue;
            return false;
        }
        return true;
    }

    public <V extends Comparable<V>> amh a(amo<V> amo2, Predicate<? extends V> predicate) {
        if (!this.a.d().contains(amo2)) {
            throw new IllegalArgumentException(this.a + " cannot support property " + amo2);
        }
        this.b.put(amo2, predicate);
        return this;
    }

    @Override
    public /* synthetic */ boolean apply(Object object) {
        return this.a((alz)object);
    }
}

