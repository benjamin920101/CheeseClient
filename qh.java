/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class qh
implements qc {
    private final qf a;
    private final qb b;
    private final Map<Integer, Set<qd>> c = Maps.newHashMap();
    private final Map<String, Set<qd>> d = Maps.newHashMap();
    private final Map<UUID, qd> e = Maps.newHashMap();
    private double f;
    private boolean g = true;
    private double h;

    public qh(qf qf2, qb qb2) {
        this.a = qf2;
        this.b = qb2;
        this.f = qb2.b();
        for (int i2 = 0; i2 < 3; ++i2) {
            this.c.put(i2, Sets.newHashSet());
        }
    }

    @Override
    public qb a() {
        return this.b;
    }

    @Override
    public double b() {
        return this.f;
    }

    @Override
    public void a(double d2) {
        if (d2 == this.b()) {
            return;
        }
        this.f = d2;
        this.f();
    }

    @Override
    public Collection<qd> a(int n2) {
        return this.c.get(n2);
    }

    @Override
    public Collection<qd> c() {
        HashSet<qd> hashSet = Sets.newHashSet();
        for (int i2 = 0; i2 < 3; ++i2) {
            hashSet.addAll(this.a(i2));
        }
        return hashSet;
    }

    @Override
    public qd a(UUID uUID) {
        return this.e.get(uUID);
    }

    @Override
    public boolean a(qd qd2) {
        return this.e.get(qd2.a()) != null;
    }

    @Override
    public void b(qd qd2) {
        if (this.a(qd2.a()) != null) {
            throw new IllegalArgumentException("Modifier is already applied on this attribute!");
        }
        Set<qd> set = this.d.get(qd2.b());
        if (set == null) {
            set = Sets.newHashSet();
            this.d.put(qd2.b(), set);
        }
        this.c.get(qd2.c()).add(qd2);
        set.add(qd2);
        this.e.put(qd2.a(), qd2);
        this.f();
    }

    protected void f() {
        this.g = true;
        this.a.a(this);
    }

    @Override
    public void c(qd qd22) {
        qd qd22;
        for (int i2 = 0; i2 < 3; ++i2) {
            Set<qd> set = this.c.get(i2);
            set.remove(qd22);
        }
        Set<qd> \u26032 = this.d.get(qd22.b());
        if (\u26032 != null) {
            \u26032.remove(qd22);
            if (\u26032.isEmpty()) {
                this.d.remove(qd22.b());
            }
        }
        this.e.remove(qd22.a());
        this.f();
    }

    @Override
    public void d() {
        Collection<qd> collection = this.c();
        if (collection == null) {
            return;
        }
        collection = Lists.newArrayList(collection);
        for (qd qd2 : collection) {
            this.c(qd2);
        }
    }

    @Override
    public double e() {
        if (this.g) {
            this.h = this.g();
            this.g = false;
        }
        return this.h;
    }

    private double g() {
        double d2;
        double d3 = this.b();
        for (qd qd2 : this.b(0)) {
            d3 += qd2.d();
        }
        d2 = d3;
        for (qd qd3 : this.b(1)) {
            d2 += d3 * qd3.d();
        }
        for (qd qd3 : this.b(2)) {
            d2 *= 1.0 + qd3.d();
        }
        return this.b.a(d2);
    }

    private Collection<qd> b(int n2) {
        HashSet<qd> hashSet = Sets.newHashSet(this.a(n2));
        for (qb qb2 = this.b.d(); qb2 != null; qb2 = qb2.d()) {
            qc qc2 = this.a.a(qb2);
            if (qc2 == null) continue;
            hashSet.addAll(qc2.a(n2));
        }
        return hashSet;
    }
}

