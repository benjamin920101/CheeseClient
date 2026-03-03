/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class boa
implements bnw {
    private final List<bnz> a;
    private final int b;
    private final int c;
    private final int d;
    private final boolean e;

    public boa(List<bnz> list, int n2, int n3, int n4, boolean bl2) {
        this.a = list;
        this.b = n2;
        this.c = n3;
        this.d = n4;
        this.e = bl2;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.a.size();
    }

    public int d() {
        return this.d;
    }

    public boolean e() {
        return this.e;
    }

    private bnz d(int n2) {
        return this.a.get(n2);
    }

    public int a(int n2) {
        bnz bnz2 = this.d(n2);
        if (bnz2.a()) {
            return this.d;
        }
        return bnz2.b();
    }

    public boolean b(int n2) {
        return !this.a.get(n2).a();
    }

    public int c(int n2) {
        return this.a.get(n2).c();
    }

    public Set<Integer> f() {
        HashSet<Integer> hashSet = Sets.newHashSet();
        for (bnz bnz2 : this.a) {
            hashSet.add(bnz2.c());
        }
        return hashSet;
    }
}

