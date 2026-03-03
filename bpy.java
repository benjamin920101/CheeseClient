/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class bpy
implements bqb<bpw> {
    private final List<bqb<bpw>> a = Lists.newArrayList();
    private final Random b = new Random();
    private final jy c;
    private final bpg d;
    private double e;
    private double f;

    public bpy(jy jy2, double d2, double d3, bpg bpg2) {
        this.c = jy2;
        this.f = d3;
        this.e = d2;
        this.d = bpg2;
    }

    @Override
    public int a() {
        int n2 = 0;
        for (bqb<bpw> bqb2 : this.a) {
            n2 += bqb2.a();
        }
        return n2;
    }

    public bpw b() {
        int n2 = this.a();
        if (this.a.isEmpty() || n2 == 0) {
            return bpz.a;
        }
        \u2603 = this.b.nextInt(n2);
        for (bqb<bpw> bqb2 : this.a) {
            if ((\u2603 -= bqb2.a()) >= 0) continue;
            bpw bpw2 = bqb2.g();
            bpw2.a(bpw2.b() * this.e);
            bpw2.b(bpw2.c() * this.f);
            return bpw2;
        }
        return bpz.a;
    }

    public void a(bqb<bpw> bqb2) {
        this.a.add(bqb2);
    }

    public jy c() {
        return this.c;
    }

    public bpg d() {
        return this.d;
    }

    @Override
    public /* synthetic */ Object g() {
        return this.b();
    }
}

