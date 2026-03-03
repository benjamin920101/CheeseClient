/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class bbo {
    public float p;
    public boolean q;
    public boolean r = true;
    public List<bct> s = Lists.newArrayList();
    private Map<String, bcu> a = Maps.newHashMap();
    public int t = 64;
    public int u = 32;

    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
    }

    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
    }

    public void a(pr pr2, float f2, float f3, float f4) {
    }

    public bct a(Random random) {
        return this.s.get(random.nextInt(this.s.size()));
    }

    protected void a(String string, int n2, int n3) {
        this.a.put(string, new bcu(n2, n3));
    }

    public bcu a(String string) {
        return this.a.get(string);
    }

    public static void a(bct bct2, bct bct3) {
        bct3.f = bct2.f;
        bct3.g = bct2.g;
        bct3.h = bct2.h;
        bct3.c = bct2.c;
        bct3.d = bct2.d;
        bct3.e = bct2.e;
    }

    public void a(bbo bbo2) {
        this.p = bbo2.p;
        this.q = bbo2.q;
        this.r = bbo2.r;
    }
}

