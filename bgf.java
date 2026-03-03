/*
 * Decompiled with CFR 0.152.
 */
import java.util.BitSet;
import java.util.List;
import static b.a;
import static c.a;

public class bgf {
    public boolean a(adq adq2, boq boq2, alz alz2, cj cj2, bfd bfd2) {
        afh afh2 = alz2.c();
        afh2.a(adq2, cj2);
        return this.a(adq2, boq2, alz2, cj2, bfd2, true);
    }

    public boolean a(adq adq2, boq boq2, alz alz2, cj cj2, bfd bfd2, boolean bl2) {
        \u2603 = ave.x() && alz2.c().r() == 0 && boq2.b();
        try {
            afh afh2 = alz2.c();
            if (\u2603) {
                return this.a(adq2, boq2, afh2, cj2, bfd2, bl2);
            }
            return this.b(adq2, boq2, afh2, cj2, bfd2, bl2);
        }
        catch (Throwable throwable) {
            b b2 = a(throwable, "Tesselating block model");
            c \u26032 = b2.a("Block model being tesselated");
            a(\u26032, cj2, alz2);
            \u26032.a("Using AO", \u2603);
            throw new e(b2);
        }
    }

    public boolean a(adq adq2, boq boq2, afh afh2, cj cj2, bfd bfd2, boolean bl2) {
        boolean \u26035;
        \u26035 = false;
        float[] fArray = new float[cq.values().length * 2];
        BitSet \u26032 = new BitSet(3);
        b \u26033 = new b();
        for (cq cq2 : cq.values()) {
            List<bgg> list = boq2.a(cq2);
            if (list.isEmpty()) continue;
            cj \u26034 = cj2.a(cq2);
            if (bl2 && !afh2.a(adq2, \u26034, cq2)) continue;
            this.a(adq2, afh2, cj2, bfd2, list, fArray, \u26032, \u26033);
            \u26035 = true;
        }
        \u2603 = boq2.a();
        if (\u2603.size() > 0) {
            this.a(adq2, afh2, cj2, bfd2, \u2603, fArray, \u26032, \u26033);
            \u26035 = true;
        }
        return \u26035;
    }

    public boolean b(adq adq2, boq boq2, afh afh2, cj cj2, bfd bfd2, boolean bl2) {
        boolean \u26034;
        \u26034 = false;
        BitSet bitSet = new BitSet(3);
        for (cq cq2 : cq.values()) {
            List<bgg> list = boq2.a(cq2);
            if (list.isEmpty()) continue;
            cj \u26032 = cj2.a(cq2);
            if (bl2 && !afh2.a(adq2, \u26032, cq2)) continue;
            int \u26033 = afh2.c(adq2, \u26032);
            this.a(adq2, afh2, cj2, cq2, \u26033, false, bfd2, list, bitSet);
            \u26034 = true;
        }
        \u2603 = boq2.a();
        if (\u2603.size() > 0) {
            this.a(adq2, afh2, cj2, null, -1, true, bfd2, \u2603, bitSet);
            \u26034 = true;
        }
        return \u26034;
    }

    private void a(adq adq2, afh afh2, cj cj2, bfd bfd2, List<bgg> list2, float[] fArray, BitSet bitSet, b b2) {
        List<bgg> list2;
        double d2 = cj2.n();
        \u2603 = cj2.o();
        \u2603 = cj2.p();
        afh.a \u26032 = afh2.R();
        if (\u26032 != afh.a.a) {
            long l2 = ns.a(cj2);
            d2 += ((double)((float)(l2 >> 16 & 0xFL) / 15.0f) - 0.5) * 0.5;
            \u2603 += ((double)((float)(l2 >> 24 & 0xFL) / 15.0f) - 0.5) * 0.5;
            if (\u26032 == afh.a.c) {
                \u2603 += ((double)((float)(l2 >> 20 & 0xFL) / 15.0f) - 1.0) * 0.2;
            }
        }
        for (bgg \u26033 : list2) {
            this.a(afh2, \u26033.a(), \u26033.d(), fArray, bitSet);
            b2.a(adq2, afh2, cj2, \u26033.d(), fArray, bitSet);
            bfd2.a(\u26033.a());
            bfd2.a(b2.c[0], b2.c[1], b2.c[2], b2.c[3]);
            if (\u26033.b()) {
                int n2 = afh2.a(adq2, cj2, \u26033.c());
                if (bfk.a) {
                    n2 = bml.c(n2);
                }
                float \u26034 = (float)(n2 >> 16 & 0xFF) / 255.0f;
                float \u26035 = (float)(n2 >> 8 & 0xFF) / 255.0f;
                float \u26036 = (float)(n2 & 0xFF) / 255.0f;
                bfd2.a(b2.b[0] * \u26034, b2.b[0] * \u26035, b2.b[0] * \u26036, 4);
                bfd2.a(b2.b[1] * \u26034, b2.b[1] * \u26035, b2.b[1] * \u26036, 3);
                bfd2.a(b2.b[2] * \u26034, b2.b[2] * \u26035, b2.b[2] * \u26036, 2);
                bfd2.a(b2.b[3] * \u26034, b2.b[3] * \u26035, b2.b[3] * \u26036, 1);
            } else {
                bfd2.a(b2.b[0], b2.b[0], b2.b[0], 4);
                bfd2.a(b2.b[1], b2.b[1], b2.b[1], 3);
                bfd2.a(b2.b[2], b2.b[2], b2.b[2], 2);
                bfd2.a(b2.b[3], b2.b[3], b2.b[3], 1);
            }
            bfd2.a(d2, \u2603, \u2603);
        }
    }

    private void a(afh afh2, int[] nArray, cq cq2, float[] fArray2, BitSet bitSet) {
        float[] fArray2;
        float \u26033;
        float f2 = 32.0f;
        \u2603 = 32.0f;
        \u2603 = 32.0f;
        \u2603 = -32.0f;
        \u2603 = -32.0f;
        \u2603 = -32.0f;
        for (int i2 = 0; i2 < 4; ++i2) {
            \u26033 = Float.intBitsToFloat(nArray[i2 * 7]);
            \u2603 = Float.intBitsToFloat(nArray[i2 * 7 + 1]);
            \u2603 = Float.intBitsToFloat(nArray[i2 * 7 + 2]);
            f2 = Math.min(f2, \u26033);
            \u2603 = Math.min(\u2603, \u2603);
            \u2603 = Math.min(\u2603, \u2603);
            \u2603 = Math.max(\u2603, \u26033);
            \u2603 = Math.max(\u2603, \u2603);
            \u2603 = Math.max(\u2603, \u2603);
        }
        if (fArray2 != null) {
            fArray2[cq.e.a()] = f2;
            fArray2[cq.f.a()] = \u2603;
            fArray2[cq.a.a()] = \u2603;
            fArray2[cq.b.a()] = \u2603;
            fArray2[cq.c.a()] = \u2603;
            fArray2[cq.d.a()] = \u2603;
            fArray2[cq.e.a() + cq.values().length] = 1.0f - f2;
            fArray2[cq.f.a() + cq.values().length] = 1.0f - \u2603;
            fArray2[cq.a.a() + cq.values().length] = 1.0f - \u2603;
            fArray2[cq.b.a() + cq.values().length] = 1.0f - \u2603;
            fArray2[cq.c.a() + cq.values().length] = 1.0f - \u2603;
            fArray2[cq.d.a() + cq.values().length] = 1.0f - \u2603;
        }
        float \u26032 = 1.0E-4f;
        \u26033 = 0.9999f;
        switch (cq2) {
            case a: {
                bitSet.set(1, f2 >= 1.0E-4f || \u2603 >= 1.0E-4f || \u2603 <= 0.9999f || \u2603 <= 0.9999f);
                bitSet.set(0, (\u2603 < 1.0E-4f || afh2.d()) && \u2603 == \u2603);
                break;
            }
            case b: {
                bitSet.set(1, f2 >= 1.0E-4f || \u2603 >= 1.0E-4f || \u2603 <= 0.9999f || \u2603 <= 0.9999f);
                bitSet.set(0, (\u2603 > 0.9999f || afh2.d()) && \u2603 == \u2603);
                break;
            }
            case c: {
                bitSet.set(1, f2 >= 1.0E-4f || \u2603 >= 1.0E-4f || \u2603 <= 0.9999f || \u2603 <= 0.9999f);
                bitSet.set(0, (\u2603 < 1.0E-4f || afh2.d()) && \u2603 == \u2603);
                break;
            }
            case d: {
                bitSet.set(1, f2 >= 1.0E-4f || \u2603 >= 1.0E-4f || \u2603 <= 0.9999f || \u2603 <= 0.9999f);
                bitSet.set(0, (\u2603 > 0.9999f || afh2.d()) && \u2603 == \u2603);
                break;
            }
            case e: {
                bitSet.set(1, \u2603 >= 1.0E-4f || \u2603 >= 1.0E-4f || \u2603 <= 0.9999f || \u2603 <= 0.9999f);
                bitSet.set(0, (f2 < 1.0E-4f || afh2.d()) && f2 == \u2603);
                break;
            }
            case f: {
                bitSet.set(1, \u2603 >= 1.0E-4f || \u2603 >= 1.0E-4f || \u2603 <= 0.9999f || \u2603 <= 0.9999f);
                bitSet.set(0, (\u2603 > 0.9999f || afh2.d()) && f2 == \u2603);
            }
        }
    }

    private void a(adq adq2, afh afh2, cj cj2, cq cq2, int n2, boolean bl2, bfd bfd22, List<bgg> list, BitSet bitSet) {
        double d2 = cj2.n();
        \u2603 = cj2.o();
        \u2603 = cj2.p();
        afh.a \u26032 = afh2.R();
        if (\u26032 != afh.a.a) {
            int n3 = cj2.n();
            \u2603 = cj2.p();
            long \u26033 = (long)(n3 * 3129871) ^ (long)\u2603 * 116129781L;
            \u26033 = \u26033 * \u26033 * 42317861L + \u26033 * 11L;
            d2 += ((double)((float)(\u26033 >> 16 & 0xFL) / 15.0f) - 0.5) * 0.5;
            \u2603 += ((double)((float)(\u26033 >> 24 & 0xFL) / 15.0f) - 0.5) * 0.5;
            if (\u26032 == afh.a.c) {
                \u2603 += ((double)((float)(\u26033 >> 20 & 0xFL) / 15.0f) - 1.0) * 0.2;
            }
        }
        for (bgg \u26034 : list) {
            bfd bfd22;
            if (bl2) {
                this.a(afh2, \u26034.a(), \u26034.d(), null, bitSet);
                n2 = bitSet.get(0) ? afh2.c(adq2, cj2.a(\u26034.d())) : afh2.c(adq2, cj2);
            }
            bfd22.a(\u26034.a());
            bfd22.a(n2, n2, n2, n2);
            if (\u26034.b()) {
                \u2603 = afh2.a(adq2, cj2, \u26034.c());
                if (bfk.a) {
                    \u2603 = bml.c(\u2603);
                }
                float f2 = (float)(\u2603 >> 16 & 0xFF) / 255.0f;
                \u2603 = (float)(\u2603 >> 8 & 0xFF) / 255.0f;
                \u2603 = (float)(\u2603 & 0xFF) / 255.0f;
                bfd22.a(f2, \u2603, \u2603, 4);
                bfd22.a(f2, \u2603, \u2603, 3);
                bfd22.a(f2, \u2603, \u2603, 2);
                bfd22.a(f2, \u2603, \u2603, 1);
            }
            bfd22.a(d2, \u2603, \u2603);
        }
    }

    public void a(boq boq2, float f22, float f3, float f4, float f5) {
        float f22;
        for (cq cq2 : cq.values()) {
            this.a(f22, f3, f4, f5, boq2.a(cq2));
        }
        this.a(f22, f3, f4, f5, boq2.a());
    }

    public void a(boq boq2, alz alz2, float f2, boolean bl2) {
        afh afh2 = alz2.c();
        afh2.j();
        bfl.b(90.0f, 0.0f, 1.0f, 0.0f);
        int \u26032 = afh2.h(afh2.b(alz2));
        if (bfk.a) {
            \u26032 = bml.c(\u26032);
        }
        float \u26033 = (float)(\u26032 >> 16 & 0xFF) / 255.0f;
        float \u26034 = (float)(\u26032 >> 8 & 0xFF) / 255.0f;
        float \u26035 = (float)(\u26032 & 0xFF) / 255.0f;
        if (!bl2) {
            bfl.c(f2, f2, f2, 1.0f);
        }
        this.a(boq2, f2, \u26033, \u26034, \u26035);
    }

    private void a(float f2, float f3, float f4, float f5, List<bgg> list) {
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        for (bgg bgg2 : list) {
            \u26032.a(7, bms.b);
            \u26032.a(bgg2.a());
            if (bgg2.b()) {
                \u26032.d(f3 * f2, f4 * f2, f5 * f2);
            } else {
                \u26032.d(f2, f2, f2);
            }
            df df2 = bgg2.d().m();
            \u26032.b(df2.n(), df2.o(), df2.p());
            bfx2.b();
        }
    }

    public static enum a {
        a(new cq[]{cq.e, cq.f, cq.c, cq.d}, 0.5f, false, new d[0], new d[0], new d[0], new d[0]),
        b(new cq[]{cq.f, cq.e, cq.c, cq.d}, 1.0f, false, new d[0], new d[0], new d[0], new d[0]),
        c(new cq[]{cq.b, cq.a, cq.f, cq.e}, 0.8f, true, new d[]{bgf$d.b, bgf$d.k, bgf$d.b, bgf$d.e, bgf$d.h, bgf$d.e, bgf$d.h, bgf$d.k}, new d[]{bgf$d.b, bgf$d.l, bgf$d.b, bgf$d.f, bgf$d.h, bgf$d.f, bgf$d.h, bgf$d.l}, new d[]{bgf$d.a, bgf$d.l, bgf$d.a, bgf$d.f, bgf$d.g, bgf$d.f, bgf$d.g, bgf$d.l}, new d[]{bgf$d.a, bgf$d.k, bgf$d.a, bgf$d.e, bgf$d.g, bgf$d.e, bgf$d.g, bgf$d.k}),
        d(new cq[]{cq.e, cq.f, cq.a, cq.b}, 0.8f, true, new d[]{bgf$d.b, bgf$d.k, bgf$d.h, bgf$d.k, bgf$d.h, bgf$d.e, bgf$d.b, bgf$d.e}, new d[]{bgf$d.a, bgf$d.k, bgf$d.g, bgf$d.k, bgf$d.g, bgf$d.e, bgf$d.a, bgf$d.e}, new d[]{bgf$d.a, bgf$d.l, bgf$d.g, bgf$d.l, bgf$d.g, bgf$d.f, bgf$d.a, bgf$d.f}, new d[]{bgf$d.b, bgf$d.l, bgf$d.h, bgf$d.l, bgf$d.h, bgf$d.f, bgf$d.b, bgf$d.f}),
        e(new cq[]{cq.b, cq.a, cq.c, cq.d}, 0.6f, true, new d[]{bgf$d.b, bgf$d.d, bgf$d.b, bgf$d.j, bgf$d.h, bgf$d.j, bgf$d.h, bgf$d.d}, new d[]{bgf$d.b, bgf$d.c, bgf$d.b, bgf$d.i, bgf$d.h, bgf$d.i, bgf$d.h, bgf$d.c}, new d[]{bgf$d.a, bgf$d.c, bgf$d.a, bgf$d.i, bgf$d.g, bgf$d.i, bgf$d.g, bgf$d.c}, new d[]{bgf$d.a, bgf$d.d, bgf$d.a, bgf$d.j, bgf$d.g, bgf$d.j, bgf$d.g, bgf$d.d}),
        f(new cq[]{cq.a, cq.b, cq.c, cq.d}, 0.6f, true, new d[]{bgf$d.g, bgf$d.d, bgf$d.g, bgf$d.j, bgf$d.a, bgf$d.j, bgf$d.a, bgf$d.d}, new d[]{bgf$d.g, bgf$d.c, bgf$d.g, bgf$d.i, bgf$d.a, bgf$d.i, bgf$d.a, bgf$d.c}, new d[]{bgf$d.h, bgf$d.c, bgf$d.h, bgf$d.i, bgf$d.b, bgf$d.i, bgf$d.b, bgf$d.c}, new d[]{bgf$d.h, bgf$d.d, bgf$d.h, bgf$d.j, bgf$d.b, bgf$d.j, bgf$d.b, bgf$d.d});

        protected final cq[] g;
        protected final float h;
        protected final boolean i;
        protected final d[] j;
        protected final d[] k;
        protected final d[] l;
        protected final d[] m;
        private static final a[] n;

        private a(cq[] cqArray, float f2, boolean bl2, d[] dArray, d[] dArray2, d[] dArray3, d[] dArray4) {
            this.g = cqArray;
            this.h = f2;
            this.i = bl2;
            this.j = dArray;
            this.k = dArray2;
            this.l = dArray3;
            this.m = dArray4;
        }

        public static a a(cq cq2) {
            return n[cq2.a()];
        }

        static {
            n = new a[6];
            bgf$a.n[cq.a.a()] = a;
            bgf$a.n[cq.b.a()] = b;
            bgf$a.n[cq.c.a()] = c;
            bgf$a.n[cq.d.a()] = d;
            bgf$a.n[cq.e.a()] = e;
            bgf$a.n[cq.f.a()] = f;
        }
    }

    public static enum d {
        a(cq.a, false),
        b(cq.b, false),
        c(cq.c, false),
        d(cq.d, false),
        e(cq.e, false),
        f(cq.f, false),
        g(cq.a, true),
        h(cq.b, true),
        i(cq.c, true),
        j(cq.d, true),
        k(cq.e, true),
        l(cq.f, true);

        protected final int m;

        private d(cq cq2, boolean bl2) {
            this.m = cq2.a() + (bl2 ? cq.values().length : 0);
        }
    }

    class b {
        private final float[] b = new float[4];
        private final int[] c = new int[4];

        public void a(adq adq2, afh afh2, cj cj2, cq cq2, float[] fArray, BitSet bitSet) {
            int \u260319;
            int \u260318;
            int \u260317;
            cj \u260316;
            int \u260315;
            cj cj3 = bitSet.get(0) ? cj2.a(cq2) : cj2;
            a \u26032 = bgf$a.a(cq2);
            \u2603 = cj3.a(\u26032.g[0]);
            \u2603 = cj3.a(\u26032.g[1]);
            \u2603 = cj3.a(\u26032.g[2]);
            \u2603 = cj3.a(\u26032.g[3]);
            int \u26033 = afh2.c(adq2, \u2603);
            int \u26034 = afh2.c(adq2, \u2603);
            int \u26035 = afh2.c(adq2, \u2603);
            int \u26036 = afh2.c(adq2, \u2603);
            float \u26037 = adq2.p(\u2603).c().h();
            float \u26038 = adq2.p(\u2603).c().h();
            float \u26039 = adq2.p(\u2603).c().h();
            float \u260310 = adq2.p(\u2603).c().h();
            boolean \u260311 = adq2.p(\u2603.a(cq2)).c().q();
            boolean \u260312 = adq2.p(\u2603.a(cq2)).c().q();
            boolean \u260313 = adq2.p(\u2603.a(cq2)).c().q();
            boolean \u260314 = adq2.p(\u2603.a(cq2)).c().q();
            if (\u260313 || \u260311) {
                \u260316 = \u2603.a(\u26032.g[2]);
                float f2 = adq2.p(\u260316).c().h();
                \u260315 = afh2.c(adq2, \u260316);
            } else {
                f2 = \u26037;
                \u260315 = \u26033;
            }
            if (\u260314 || \u260311) {
                \u260316 = \u2603.a(\u26032.g[3]);
                f4 = adq2.p(\u260316).c().h();
                \u260317 = afh2.c(adq2, \u260316);
            } else {
                f4 = \u26037;
                \u260317 = \u26033;
            }
            if (\u260313 || \u260312) {
                \u260316 = \u2603.a(\u26032.g[2]);
                \u2603 = adq2.p(\u260316).c().h();
                \u260318 = afh2.c(adq2, \u260316);
            } else {
                \u2603 = \u26038;
                \u260318 = \u26034;
            }
            if (\u260314 || \u260312) {
                \u260316 = \u2603.a(\u26032.g[3]);
                \u2603 = adq2.p(\u260316).c().h();
                \u260319 = afh2.c(adq2, \u260316);
            } else {
                \u2603 = \u26038;
                \u260319 = \u26034;
            }
            int n2 = afh2.c(adq2, cj2);
            if (bitSet.get(0) || !adq2.p(cj2.a(cq2)).c().c()) {
                n2 = afh2.c(adq2, cj2.a(cq2));
            }
            float \u260320 = bitSet.get(0) ? adq2.p(cj3).c().h() : adq2.p(cj2).c().h();
            c \u260321 = bgf$c.a(cq2);
            if (!bitSet.get(1) || !\u26032.i) {
                float f3 = (\u260310 + \u26037 + f4 + \u260320) * 0.25f;
                \u2603 = (\u26039 + \u26037 + f2 + \u260320) * 0.25f;
                \u2603 = (\u26039 + \u26038 + \u2603 + \u260320) * 0.25f;
                \u2603 = (\u260310 + \u26038 + \u2603 + \u260320) * 0.25f;
                this.c[((c)\u260321).g] = this.a(\u26036, \u26033, \u260317, n2);
                this.c[((c)\u260321).h] = this.a(\u26035, \u26033, \u260315, n2);
                this.c[((c)\u260321).i] = this.a(\u26035, \u26034, \u260318, n2);
                this.c[((c)\u260321).j] = this.a(\u26036, \u26034, \u260319, n2);
                this.b[((c)\u260321).g] = f3;
                this.b[((c)\u260321).h] = \u2603;
                this.b[((c)\u260321).i] = \u2603;
                this.b[((c)\u260321).j] = \u2603;
            } else {
                float f4;
                \u2603 = (\u260310 + \u26037 + f4 + \u260320) * 0.25f;
                \u2603 = (\u26039 + \u26037 + f2 + \u260320) * 0.25f;
                \u2603 = (\u26039 + \u26038 + \u2603 + \u260320) * 0.25f;
                \u2603 = (\u260310 + \u26038 + \u2603 + \u260320) * 0.25f;
                \u2603 = fArray[\u26032.j[0].m] * fArray[\u26032.j[1].m];
                \u2603 = fArray[\u26032.j[2].m] * fArray[\u26032.j[3].m];
                \u2603 = fArray[\u26032.j[4].m] * fArray[\u26032.j[5].m];
                \u2603 = fArray[\u26032.j[6].m] * fArray[\u26032.j[7].m];
                \u2603 = fArray[\u26032.k[0].m] * fArray[\u26032.k[1].m];
                \u2603 = fArray[\u26032.k[2].m] * fArray[\u26032.k[3].m];
                \u2603 = fArray[\u26032.k[4].m] * fArray[\u26032.k[5].m];
                \u2603 = fArray[\u26032.k[6].m] * fArray[\u26032.k[7].m];
                \u2603 = fArray[\u26032.l[0].m] * fArray[\u26032.l[1].m];
                \u2603 = fArray[\u26032.l[2].m] * fArray[\u26032.l[3].m];
                \u2603 = fArray[\u26032.l[4].m] * fArray[\u26032.l[5].m];
                \u2603 = fArray[\u26032.l[6].m] * fArray[\u26032.l[7].m];
                \u2603 = fArray[\u26032.m[0].m] * fArray[\u26032.m[1].m];
                \u2603 = fArray[\u26032.m[2].m] * fArray[\u26032.m[3].m];
                \u2603 = fArray[\u26032.m[4].m] * fArray[\u26032.m[5].m];
                \u2603 = fArray[\u26032.m[6].m] * fArray[\u26032.m[7].m];
                this.b[((c)\u260321).g] = \u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603;
                this.b[((c)\u260321).h] = \u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603;
                this.b[((c)\u260321).i] = \u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603;
                this.b[((c)\u260321).j] = \u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603;
                int \u260322 = this.a(\u26036, \u26033, \u260317, n2);
                int \u260323 = this.a(\u26035, \u26033, \u260315, n2);
                int \u260324 = this.a(\u26035, \u26034, \u260318, n2);
                int \u260325 = this.a(\u26036, \u26034, \u260319, n2);
                this.c[((c)\u260321).g] = this.a(\u260322, \u260323, \u260324, \u260325, \u2603, \u2603, \u2603, \u2603);
                this.c[((c)\u260321).h] = this.a(\u260322, \u260323, \u260324, \u260325, \u2603, \u2603, \u2603, \u2603);
                this.c[((c)\u260321).i] = this.a(\u260322, \u260323, \u260324, \u260325, \u2603, \u2603, \u2603, \u2603);
                this.c[((c)\u260321).j] = this.a(\u260322, \u260323, \u260324, \u260325, \u2603, \u2603, \u2603, \u2603);
            }
        }

        private int a(int n2, int n3, int n4, int n5) {
            if (n2 == 0) {
                n2 = n5;
            }
            if (n3 == 0) {
                n3 = n5;
            }
            if (n4 == 0) {
                n4 = n5;
            }
            return n2 + n3 + n4 + n5 >> 2 & 0xFF00FF;
        }

        private int a(int n2, int n3, int n4, int n5, float f2, float f3, float f4, float f5) {
            int n6 = (int)((float)(n2 >> 16 & 0xFF) * f2 + (float)(n3 >> 16 & 0xFF) * f3 + (float)(n4 >> 16 & 0xFF) * f4 + (float)(n5 >> 16 & 0xFF) * f5) & 0xFF;
            \u2603 = (int)((float)(n2 & 0xFF) * f2 + (float)(n3 & 0xFF) * f3 + (float)(n4 & 0xFF) * f4 + (float)(n5 & 0xFF) * f5) & 0xFF;
            return n6 << 16 | \u2603;
        }
    }

    static enum c {
        a(0, 1, 2, 3),
        b(2, 3, 0, 1),
        c(3, 0, 1, 2),
        d(0, 1, 2, 3),
        e(3, 0, 1, 2),
        f(1, 2, 3, 0);

        private final int g;
        private final int h;
        private final int i;
        private final int j;
        private static final c[] k;

        private c(int n3, int n4, int n5, int n6) {
            this.g = n3;
            this.h = n4;
            this.i = n5;
            this.j = n6;
        }

        public static c a(cq cq2) {
            return k[cq2.a()];
        }

        static {
            k = new c[6];
            bgf$c.k[cq.a.a()] = a;
            bgf$c.k[cq.b.a()] = b;
            bgf$c.k[cq.c.a()] = c;
            bgf$c.k[cq.d.a()] = d;
            bgf$c.k[cq.e.a()] = e;
            bgf$c.k[cq.f.a()] = f;
        }
    }
}

