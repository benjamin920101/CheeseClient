/*
 * Decompiled with CFR 0.152.
 */
import java.util.Collection;
import java.util.Random;

public class oa {
    public static int a(Collection<? extends a> collection) {
        int n2 = 0;
        for (a a2 : collection) {
            n2 += a2.a;
        }
        return n2;
    }

    public static <T extends a> T a(Random random, Collection<T> collection, int n2) {
        if (n2 <= 0) {
            throw new IllegalArgumentException();
        }
        \u2603 = random.nextInt(n2);
        return oa.a(collection, \u2603);
    }

    public static <T extends a> T a(Collection<T> collection, int n2) {
        for (a a2 : collection) {
            if ((n2 -= a2.a) >= 0) continue;
            return (T)a2;
        }
        return null;
    }

    public static <T extends a> T a(Random random, Collection<T> collection) {
        return oa.a(random, collection, oa.a(collection));
    }

    public static class a {
        protected int a;

        public a(int n2) {
            this.a = n2;
        }
    }
}

