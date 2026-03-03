/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.Map;

public abstract class qf {
    protected final Map<qb, qc> a = Maps.newHashMap();
    protected final Map<String, qc> b = new nk<qc>();
    protected final Multimap<qb, qb> c = HashMultimap.create();

    public qc a(qb qb2) {
        return this.a.get(qb2);
    }

    public qc a(String string) {
        return this.b.get(string);
    }

    public qc b(qb qb2) {
        if (this.b.containsKey(qb2.a())) {
            throw new IllegalArgumentException("Attribute is already registered!");
        }
        qc qc2 = this.c(qb2);
        this.b.put(qb2.a(), qc2);
        this.a.put(qb2, qc2);
        for (qb qb3 = qb2.d(); qb3 != null; qb3 = qb3.d()) {
            this.c.put(qb3, qb2);
        }
        return qc2;
    }

    protected abstract qc c(qb var1);

    public Collection<qc> a() {
        return this.b.values();
    }

    public void a(qc qc2) {
    }

    public void a(Multimap<String, qd> multimap) {
        for (Map.Entry<String, qd> entry : multimap.entries()) {
            qc qc2 = this.a(entry.getKey());
            if (qc2 == null) continue;
            qc2.c(entry.getValue());
        }
    }

    public void b(Multimap<String, qd> multimap) {
        for (Map.Entry<String, qd> entry : multimap.entries()) {
            qc qc2 = this.a(entry.getKey());
            if (qc2 == null) continue;
            qc2.c(entry.getValue());
            qc2.b(entry.getValue());
        }
    }
}

