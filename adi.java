/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class adi {
    private final boolean a;
    private final boolean b;
    private final Random c = new Random();
    private final adm d;
    private final double e;
    private final double f;
    private final double g;
    private final pk h;
    private final float i;
    private final List<cj> j = Lists.newArrayList();
    private final Map<wn, aui> k = Maps.newHashMap();

    public adi(adm adm2, pk pk2, double d2, double d3, double d4, float f2, List<cj> list) {
        this(adm2, pk2, d2, d3, d4, f2, false, true, list);
    }

    public adi(adm adm2, pk pk2, double d2, double d3, double d4, float f2, boolean bl2, boolean bl3, List<cj> list) {
        this(adm2, pk2, d2, d3, d4, f2, bl2, bl3);
        this.j.addAll(list);
    }

    public adi(adm adm2, pk pk2, double d2, double d3, double d4, float f2, boolean bl2, boolean bl3) {
        this.d = adm2;
        this.h = pk2;
        this.i = f2;
        this.e = d2;
        this.f = d3;
        this.g = d4;
        this.a = bl2;
        this.b = bl3;
    }

    public void a() {
        HashSet<cj> hashSet = Sets.newHashSet();
        int \u26032 = 16;
        for (int i2 = 0; i2 < 16; ++i2) {
            for (\u26036 = 0; \u26036 < 16; ++\u26036) {
                for (\u26037 = 0; \u26037 < 16; ++\u26037) {
                    if (i2 != 0 && i2 != 15 && \u26036 != 0 && \u26036 != 15 && \u26037 != 0 && \u26037 != 15) continue;
                    double d2 = (float)i2 / 15.0f * 2.0f - 1.0f;
                    \u2603 = (float)\u26036 / 15.0f * 2.0f - 1.0f;
                    \u2603 = (float)\u26037 / 15.0f * 2.0f - 1.0f;
                    \u2603 = Math.sqrt(d2 * d2 + \u2603 * \u2603 + \u2603 * \u2603);
                    d2 /= \u2603;
                    \u2603 /= \u2603;
                    \u2603 /= \u2603;
                    \u2603 = this.e;
                    \u2603 = this.f;
                    \u2603 = this.g;
                    float \u26033 = 0.3f;
                    for (float f2 = this.i * (0.7f + this.d.s.nextFloat() * 0.6f); f2 > 0.0f; f2 -= 0.22500001f) {
                        cj cj2 = new cj(\u2603, \u2603, \u2603);
                        alz \u26034 = this.d.p(cj2);
                        if (\u26034.c().t() != arm.a) {
                            float f3 = this.h != null ? this.h.a(this, this.d, cj2, \u26034) : \u26034.c().a((pk)null);
                            f2 -= (f3 + 0.3f) * 0.3f;
                        }
                        if (f2 > 0.0f && (this.h == null || this.h.a(this, this.d, cj2, \u26034, f2))) {
                            hashSet.add(cj2);
                        }
                        \u2603 += d2 * (double)0.3f;
                        \u2603 += \u2603 * (double)0.3f;
                        \u2603 += \u2603 * (double)0.3f;
                    }
                }
            }
        }
        this.j.addAll(hashSet);
        float \u26035 = this.i * 2.0f;
        int \u26036 = ns.c(this.e - (double)\u26035 - 1.0);
        int \u26037 = ns.c(this.e + (double)\u26035 + 1.0);
        int \u26038 = ns.c(this.f - (double)\u26035 - 1.0);
        int \u26039 = ns.c(this.f + (double)\u26035 + 1.0);
        int \u260310 = ns.c(this.g - (double)\u26035 - 1.0);
        int \u260311 = ns.c(this.g + (double)\u26035 + 1.0);
        List<pk> \u260312 = this.d.b(this.h, new aug(\u26036, \u26038, \u260310, \u26037, \u26039, \u260311));
        aui \u260313 = new aui(this.e, this.f, this.g);
        for (int i3 = 0; i3 < \u260312.size(); ++i3) {
            pk pk2 = \u260312.get(i3);
            if (pk2.aW() || !((\u2603 = pk2.f(this.e, this.f, this.g) / (double)\u26035) <= 1.0) || (\u2603 = (double)ns.a((\u2603 = pk2.s - this.e) * \u2603 + (\u2603 = pk2.t + (double)pk2.aS() - this.f) * \u2603 + (\u2603 = pk2.u - this.g) * \u2603)) == 0.0) continue;
            \u2603 /= \u2603;
            \u2603 /= \u2603;
            \u2603 /= \u2603;
            double \u260314 = this.d.a(\u260313, pk2.aR());
            double \u260315 = (1.0 - \u2603) * \u260314;
            pk2.a(ow.a(this), (float)((int)((\u260315 * \u260315 + \u260315) / 2.0 * 8.0 * (double)\u26035 + 1.0)));
            double \u260316 = acr.a(pk2, \u260315);
            pk2.v += \u2603 * \u260316;
            pk2.w += \u2603 * \u260316;
            pk2.x += \u2603 * \u260316;
            if (!(pk2 instanceof wn) || ((wn)pk2).bA.a) continue;
            this.k.put((wn)pk2, new aui(\u2603 * \u260315, \u2603 * \u260315, \u2603 * \u260315));
        }
    }

    public void a(boolean bl2) {
        this.d.a(this.e, this.f, this.g, "random.explode", 4.0f, (1.0f + (this.d.s.nextFloat() - this.d.s.nextFloat()) * 0.2f) * 0.7f);
        if (this.i < 2.0f || !this.b) {
            this.d.a(cy.b, this.e, this.f, this.g, 1.0, 0.0, 0.0, new int[0]);
        } else {
            this.d.a(cy.c, this.e, this.f, this.g, 1.0, 0.0, 0.0, new int[0]);
        }
        if (this.b) {
            for (cj cj2 : this.j) {
                afh afh2 = this.d.p(cj2).c();
                if (bl2) {
                    double d2 = (float)cj2.n() + this.d.s.nextFloat();
                    \u2603 = (float)cj2.o() + this.d.s.nextFloat();
                    \u2603 = (float)cj2.p() + this.d.s.nextFloat();
                    \u2603 = d2 - this.e;
                    \u2603 = \u2603 - this.f;
                    \u2603 = \u2603 - this.g;
                    \u2603 = ns.a(\u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603);
                    \u2603 /= \u2603;
                    \u2603 /= \u2603;
                    \u2603 /= \u2603;
                    \u2603 = 0.5 / (\u2603 / (double)this.i + 0.1);
                    this.d.a(cy.a, (d2 + this.e * 1.0) / 2.0, (\u2603 + this.f * 1.0) / 2.0, (\u2603 + this.g * 1.0) / 2.0, \u2603 *= (\u2603 *= (double)(this.d.s.nextFloat() * this.d.s.nextFloat() + 0.3f)), \u2603 *= \u2603, \u2603 *= \u2603, new int[0]);
                    this.d.a(cy.l, d2, \u2603, \u2603, \u2603, \u2603, \u2603, new int[0]);
                }
                if (afh2.t() == arm.a) continue;
                if (afh2.a(this)) {
                    afh2.a(this.d, cj2, this.d.p(cj2), 1.0f / this.i, 0);
                }
                this.d.a(cj2, afi.a.Q(), 3);
                afh2.a(this.d, cj2, this);
            }
        }
        if (this.a) {
            for (cj cj2 : this.j) {
                if (this.d.p(cj2).c().t() != arm.a || !this.d.p(cj2.b()).c().o() || this.c.nextInt(3) != 0) continue;
                this.d.a(cj2, afi.ab.Q());
            }
        }
    }

    public Map<wn, aui> b() {
        return this.k;
    }

    public pr c() {
        if (this.h == null) {
            return null;
        }
        if (this.h instanceof vj) {
            return ((vj)this.h).j();
        }
        if (this.h instanceof pr) {
            return (pr)this.h;
        }
        return null;
    }

    public void d() {
        this.j.clear();
    }

    public List<cj> e() {
        return this.j;
    }
}

