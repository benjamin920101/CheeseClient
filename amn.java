/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;

public class amn
extends amj<Integer> {
    private final ImmutableSet<Integer> a;

    protected amn(String string, int n2, int n3) {
        super(string, Integer.class);
        if (n2 < 0) {
            throw new IllegalArgumentException("Min value of " + string + " must be 0 or greater");
        }
        if (n3 <= n2) {
            throw new IllegalArgumentException("Max value of " + string + " must be greater than min (" + n2 + ")");
        }
        HashSet<Integer> hashSet = Sets.newHashSet();
        for (int i2 = n2; i2 <= n3; ++i2) {
            hashSet.add(i2);
        }
        this.a = ImmutableSet.copyOf(hashSet);
    }

    @Override
    public Collection<Integer> c() {
        return this.a;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        amn amn2 = (amn)object;
        return this.a.equals(amn2.a);
    }

    @Override
    public int hashCode() {
        int n2 = super.hashCode();
        n2 = 31 * n2 + this.a.hashCode();
        return n2;
    }

    public static amn a(String string, int n2, int n3) {
        return new amn(string, n2, n3);
    }

    @Override
    public String a(Integer n2) {
        return n2.toString();
    }
}

