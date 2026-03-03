/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;

public class avb
implements Comparable<avb> {
    private static final List<avb> a = Lists.newArrayList();
    private static final nm<avb> b = new nm();
    private static final Set<String> c = Sets.newHashSet();
    private final String d;
    private final int e;
    private final String f;
    private int g;
    private boolean h;
    private int i;

    public static void a(int n2) {
        if (n2 == 0) {
            return;
        }
        avb avb2 = b.a(n2);
        if (avb2 != null) {
            ++avb2.i;
        }
    }

    public static void a(int n2, boolean bl2) {
        if (n2 == 0) {
            return;
        }
        avb avb2 = b.a(n2);
        if (avb2 != null) {
            avb2.h = bl2;
        }
    }

    public static void a() {
        for (avb avb2 : a) {
            avb2.j();
        }
    }

    public static void b() {
        b.c();
        for (avb avb2 : a) {
            b.a(avb2.g, avb2);
        }
    }

    public static Set<String> c() {
        return c;
    }

    public avb(String string, int n2, String string2) {
        this.d = string;
        this.g = n2;
        this.e = n2;
        this.f = string2;
        a.add(this);
        b.a(n2, this);
        c.add(string2);
    }

    public boolean d() {
        return this.h;
    }

    public String e() {
        return this.f;
    }

    public boolean f() {
        if (this.i == 0) {
            return false;
        }
        --this.i;
        return true;
    }

    private void j() {
        this.i = 0;
        this.h = false;
    }

    public String g() {
        return this.d;
    }

    public int h() {
        return this.e;
    }

    public int i() {
        return this.g;
    }

    public void b(int n2) {
        this.g = n2;
    }

    public int a(avb avb2) {
        int n2 = bnq.a(this.f, new Object[0]).compareTo(bnq.a(avb2.f, new Object[0]));
        if (n2 == 0) {
            n2 = bnq.a(this.d, new Object[0]).compareTo(bnq.a(avb2.d, new Object[0]));
        }
        return n2;
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.a((avb)object);
    }
}

