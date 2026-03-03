/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public abstract class aci {
    private static final aci[] a = new aci[256];
    public static final aci[] b;
    private static final Map<jy, aci> E;
    public static final aci c;
    public static final aci d;
    public static final aci e;
    public static final aci f;
    public static final aci g;
    public static final aci h;
    public static final aci i;
    public static final aci j;
    public static final aci k;
    public static final aci l;
    public static final aci m;
    public static final aci n;
    public static final aci o;
    public static final aci p;
    public static final aci q;
    public static final aci r;
    public static final aci s;
    public static final aci t;
    public static final aci u;
    public static final aci v;
    public static final aci w;
    public static final aci x;
    public static final aci y;
    public static final aci z;
    public static final aci A;
    public final int B;
    private final int F;
    public acj C;
    protected String D;

    public static aci c(int n2) {
        if (n2 < 0 || n2 >= a.length) {
            return null;
        }
        return a[n2];
    }

    protected aci(int n2, jy jy2, int n3, acj acj2) {
        this.B = n2;
        this.F = n3;
        this.C = acj2;
        if (a[n2] != null) {
            throw new IllegalArgumentException("Duplicate enchantment id!");
        }
        aci.a[n2] = this;
        E.put(jy2, this);
    }

    public static aci b(String string) {
        return E.get(new jy(string));
    }

    public static Set<jy> c() {
        return E.keySet();
    }

    public int d() {
        return this.F;
    }

    public int e() {
        return 1;
    }

    public int b() {
        return 1;
    }

    public int a(int n2) {
        return 1 + n2 * 10;
    }

    public int b(int n2) {
        return this.a(n2) + 5;
    }

    public int a(int n2, ow ow2) {
        return 0;
    }

    public float a(int n2, pw pw2) {
        return 0.0f;
    }

    public boolean a(aci aci2) {
        return this != aci2;
    }

    public aci c(String string) {
        this.D = string;
        return this;
    }

    public String a() {
        return "enchantment." + this.D;
    }

    public String d(int n2) {
        String string = di.a(this.a());
        return string + " " + di.a("enchantment.level." + n2);
    }

    public boolean a(zx zx2) {
        return this.C.a(zx2.b());
    }

    public void a(pr pr2, pk pk2, int n2) {
    }

    public void b(pr pr2, pk pk2, int n2) {
    }

    static {
        E = Maps.newHashMap();
        c = new acr(0, new jy("protection"), 10, 0);
        d = new acr(1, new jy("fire_protection"), 5, 1);
        e = new acr(2, new jy("feather_falling"), 5, 2);
        f = new acr(3, new jy("blast_protection"), 2, 3);
        g = new acr(4, new jy("projectile_protection"), 5, 4);
        h = new acq(5, new jy("respiration"), 2);
        i = new acv(6, new jy("aqua_affinity"), 2);
        j = new acs(7, new jy("thorns"), 1);
        k = new acu(8, new jy("depth_strider"), 2);
        l = new acf(16, new jy("sharpness"), 10, 0);
        m = new acf(17, new jy("smite"), 5, 1);
        n = new acf(18, new jy("bane_of_arthropods"), 5, 2);
        o = new aco(19, new jy("knockback"), 5);
        p = new acm(20, new jy("fire_aspect"), 2);
        q = new acp(21, new jy("looting"), 2, acj.g);
        r = new ach(32, new jy("efficiency"), 10);
        s = new act(33, new jy("silk_touch"), 1);
        t = new acg(34, new jy("unbreaking"), 5);
        u = new acp(35, new jy("fortune"), 2, acj.h);
        v = new acb(48, new jy("power"), 10);
        w = new ace(49, new jy("punch"), 2);
        x = new acc(50, new jy("flame"), 2);
        y = new acd(51, new jy("infinity"), 1);
        z = new acp(61, new jy("luck_of_the_sea"), 2, acj.i);
        A = new acn(62, new jy("lure"), 2, acj.i);
        ArrayList<aci> arrayList = Lists.newArrayList();
        for (aci aci2 : a) {
            if (aci2 == null) continue;
            arrayList.add(aci2);
        }
        b = arrayList.toArray(new aci[arrayList.size()]);
    }
}

