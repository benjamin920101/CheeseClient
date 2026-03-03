/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class alu
extends akw
implements km {
    private alz a;
    private cq f;
    private boolean g;
    private boolean h;
    private float i;
    private float j;
    private List<pk> k = Lists.newArrayList();

    public alu() {
    }

    public alu(alz alz2, cq cq2, boolean bl2, boolean bl3) {
        this.a = alz2;
        this.f = cq2;
        this.g = bl2;
        this.h = bl3;
    }

    public alz b() {
        return this.a;
    }

    @Override
    public int u() {
        return 0;
    }

    public boolean d() {
        return this.g;
    }

    public cq e() {
        return this.f;
    }

    public boolean g() {
        return this.h;
    }

    public float a(float f2) {
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        return this.j + (this.i - this.j) * f2;
    }

    public float b(float f2) {
        if (this.g) {
            return (this.a(f2) - 1.0f) * (float)this.f.g();
        }
        return (1.0f - this.a(f2)) * (float)this.f.g();
    }

    public float c(float f2) {
        if (this.g) {
            return (this.a(f2) - 1.0f) * (float)this.f.h();
        }
        return (1.0f - this.a(f2)) * (float)this.f.h();
    }

    public float d(float f2) {
        if (this.g) {
            return (this.a(f2) - 1.0f) * (float)this.f.i();
        }
        return (1.0f - this.a(f2)) * (float)this.f.i();
    }

    private void a(float f2, float f3) {
        f2 = this.g ? 1.0f - f2 : (f2 -= 1.0f);
        aug aug2 = afi.M.a(this.b, this.c, this.a, f2, this.f);
        if (aug2 != null && !(\u2603 = this.b.b(null, aug2)).isEmpty()) {
            this.k.addAll(\u2603);
            for (pk pk2 : this.k) {
                if (this.a.c() == afi.cE && this.g) {
                    switch (this.f.k()) {
                        case a: {
                            pk2.v = this.f.g();
                            break;
                        }
                        case b: {
                            pk2.w = this.f.h();
                            break;
                        }
                        case c: {
                            pk2.x = this.f.i();
                        }
                    }
                    continue;
                }
                pk2.d(f3 * (float)this.f.g(), f3 * (float)this.f.h(), f3 * (float)this.f.i());
            }
            this.k.clear();
        }
    }

    public void h() {
        if (this.j < 1.0f && this.b != null) {
            this.i = 1.0f;
            this.j = 1.0f;
            this.b.t(this.c);
            this.y();
            if (this.b.p(this.c).c() == afi.M) {
                this.b.a(this.c, this.a, 3);
                this.b.d(this.c, this.a.c());
            }
        }
    }

    @Override
    public void c() {
        this.j = this.i;
        if (this.j >= 1.0f) {
            this.a(1.0f, 0.25f);
            this.b.t(this.c);
            this.y();
            if (this.b.p(this.c).c() == afi.M) {
                this.b.a(this.c, this.a, 3);
                this.b.d(this.c, this.a.c());
            }
            return;
        }
        this.i += 0.5f;
        if (this.i >= 1.0f) {
            this.i = 1.0f;
        }
        if (this.g) {
            this.a(this.i, this.i - this.j + 0.0625f);
        }
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.a = afh.c(dn2.f("blockId")).a(dn2.f("blockData"));
        this.f = cq.a(dn2.f("facing"));
        this.j = this.i = dn2.h("progress");
        this.g = dn2.n("extending");
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("blockId", afh.a(this.a.c()));
        dn2.a("blockData", this.a.c().c(this.a));
        dn2.a("facing", this.f.a());
        dn2.a("progress", this.j);
        dn2.a("extending", this.g);
    }
}

