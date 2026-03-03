/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Multimap;
import java.util.Set;

public class za
extends zw {
    private Set<afh> c;
    protected float a = 4.0f;
    private float d;
    protected zw.a b;

    protected za(float f2, zw.a a2, Set<afh> set) {
        this.b = a2;
        this.c = set;
        this.h = 1;
        this.d(a2.a());
        this.a = a2.b();
        this.d = f2 + a2.c();
        this.a(yz.i);
    }

    @Override
    public float a(zx zx2, afh afh2) {
        return this.c.contains(afh2) ? this.a : 1.0f;
    }

    @Override
    public boolean a(zx zx2, pr pr2, pr pr3) {
        zx2.a(2, pr3);
        return true;
    }

    @Override
    public boolean a(zx zx2, adm adm2, afh afh2, cj cj2, pr pr2) {
        if ((double)afh2.g(adm2, cj2) != 0.0) {
            zx2.a(1, pr2);
        }
        return true;
    }

    @Override
    public boolean w_() {
        return true;
    }

    public zw.a g() {
        return this.b;
    }

    @Override
    public int b() {
        return this.b.e();
    }

    public String h() {
        return this.b.toString();
    }

    @Override
    public boolean a(zx zx2, zx zx3) {
        if (this.b.f() == zx3.b()) {
            return true;
        }
        return super.a(zx2, zx3);
    }

    @Override
    public Multimap<String, qd> i() {
        Multimap<String, qd> multimap = super.i();
        multimap.put(vy.e.a(), new qd(f, "Tool modifier", this.d, 0));
        return multimap;
    }
}

