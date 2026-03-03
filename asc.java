/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class asc {
    private static int a = 256;
    private static List<int[]> b = Lists.newArrayList();
    private static List<int[]> c = Lists.newArrayList();
    private static List<int[]> d = Lists.newArrayList();
    private static List<int[]> e = Lists.newArrayList();

    public static synchronized int[] a(int n22) {
        int n22;
        if (n22 <= 256) {
            if (b.isEmpty()) {
                int[] nArray = new int[256];
                c.add(nArray);
                return nArray;
            }
            int[] nArray = b.remove(b.size() - 1);
            c.add(nArray);
            return nArray;
        }
        if (n22 > a) {
            a = n22;
            d.clear();
            e.clear();
            int[] nArray = new int[a];
            e.add(nArray);
            return nArray;
        }
        if (d.isEmpty()) {
            int[] nArray = new int[a];
            e.add(nArray);
            return nArray;
        }
        int[] nArray = d.remove(d.size() - 1);
        e.add(nArray);
        return nArray;
    }

    public static synchronized void a() {
        if (!d.isEmpty()) {
            d.remove(d.size() - 1);
        }
        if (!b.isEmpty()) {
            b.remove(b.size() - 1);
        }
        d.addAll(e);
        b.addAll(c);
        e.clear();
        c.clear();
    }

    public static synchronized String b() {
        return "cache: " + d.size() + ", tcache: " + b.size() + ", allocated: " + e.size() + ", tallocated: " + c.size();
    }
}

