/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Multimap;

public class aay
extends zw {
    private float a;
    private final zw.a b;

    public aay(zw.a a2) {
        this.b = a2;
        this.h = 1;
        this.d(a2.a());
        this.a(yz.j);
        this.a = 4.0f + a2.c();
    }

    public float g() {
        return this.b.c();
    }

    @Override
    public float a(zx zx2, afh afh2) {
        if (afh2 == afi.G) {
            return 15.0f;
        }
        arm arm2 = afh2.t();
        if (arm2 == arm.k || arm2 == arm.l || arm2 == arm.v || arm2 == arm.j || arm2 == arm.C) {
            return 1.5f;
        }
        return 1.0f;
    }

    @Override
    public boolean a(zx zx2, pr pr2, pr pr3) {
        zx2.a(1, pr3);
        return true;
    }

    @Override
    public boolean a(zx zx2, adm adm2, afh afh2, cj cj2, pr pr2) {
        if ((double)afh2.g(adm2, cj2) != 0.0) {
            zx2.a(2, pr2);
        }
        return true;
    }

    @Override
    public boolean w_() {
        return true;
    }

    @Override
    public aba e(zx zx2) {
        return aba.d;
    }

    @Override
    public int d(zx zx2) {
        return 72000;
    }

    @Override
    public zx a(zx zx2, adm adm2, wn wn2) {
        wn2.a(zx2, this.d(zx2));
        return zx2;
    }

    @Override
    public boolean b(afh afh2) {
        return afh2 == afi.G;
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
        multimap.put(vy.e.a(), new qd(f, "Weapon modifier", this.a, 0));
        return multimap;
    }
}

