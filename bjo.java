/*
 * Decompiled with CFR 0.152.
 */
public abstract class bjo<T extends ps>
extends bjl<T> {
    public bjo(biu biu2, bbo bbo2, float f2) {
        super(biu2, bbo2, f2);
    }

    @Override
    protected boolean b(T t2) {
        return super.a(t2) && (((pr)t2).aO() || ((pk)t2).l_() && t2 == this.b.d);
    }

    @Override
    public boolean a(T t2, bia bia2, double d2, double d3, double d4) {
        if (super.a(t2, bia2, d2, d3, d4)) {
            return true;
        }
        if (((ps)t2).cc() && ((ps)t2).cd() != null) {
            pk pk2 = ((ps)t2).cd();
            return bia2.a(pk2.aR());
        }
        return false;
    }

    @Override
    public void a(T t2, double d2, double d3, double d4, float f2, float f3) {
        super.a(t2, d2, d3, d4, f2, f3);
        this.b(t2, d2, d3, d4, f2, f3);
    }

    @Override
    public void a(T t2, float f2) {
        int n2 = ((pk)t2).b(f2);
        \u2603 = n2 % 65536;
        \u2603 = n2 / 65536;
        bqs.a(bqs.r, (float)\u2603 / 1.0f, (float)\u2603 / 1.0f);
    }

    private double a(double d2, double d3, double d4) {
        return d2 + (d3 - d2) * d4;
    }

    protected void b(T t2, double d2, double d3, double d4, float f2, float f3) {
        pk pk2 = ((ps)t2).cd();
        if (pk2 != null) {
            float f4;
            int n2;
            d3 -= (1.6 - (double)((ps)t2).K) * 0.5;
            bfx bfx2 = bfx.a();
            bfd \u26032 = bfx2.c();
            double \u26033 = this.a((double)pk2.A, (double)pk2.y, (double)(f3 * 0.5f)) * 0.01745329238474369;
            double \u26034 = this.a((double)pk2.B, (double)pk2.z, (double)(f3 * 0.5f)) * 0.01745329238474369;
            double \u26035 = Math.cos(\u26033);
            double \u26036 = Math.sin(\u26033);
            double \u26037 = Math.sin(\u26034);
            if (pk2 instanceof un) {
                \u26035 = 0.0;
                \u26036 = 0.0;
                \u26037 = -1.0;
            }
            double \u26038 = Math.cos(\u26034);
            double \u26039 = this.a(pk2.p, pk2.s, (double)f3) - \u26035 * 0.7 - \u26036 * 0.5 * \u26038;
            double \u260310 = this.a(pk2.q + (double)pk2.aS() * 0.7, pk2.t + (double)pk2.aS() * 0.7, (double)f3) - \u26037 * 0.5 - 0.25;
            double \u260311 = this.a(pk2.r, pk2.u, (double)f3) - \u26036 * 0.7 + \u26035 * 0.5 * \u26038;
            double \u260312 = this.a((double)((ps)t2).aJ, (double)((ps)t2).aI, (double)f3) * 0.01745329238474369 + 1.5707963267948966;
            \u26035 = Math.cos(\u260312) * (double)((ps)t2).J * 0.4;
            \u26036 = Math.sin(\u260312) * (double)((ps)t2).J * 0.4;
            double \u260313 = this.a(((ps)t2).p, ((ps)t2).s, (double)f3) + \u26035;
            double \u260314 = this.a(((ps)t2).q, ((ps)t2).t, (double)f3);
            double \u260315 = this.a(((ps)t2).r, ((ps)t2).u, (double)f3) + \u26036;
            d2 += \u26035;
            d4 += \u26036;
            double \u260316 = (float)(\u26039 - \u260313);
            double \u260317 = (float)(\u260310 - \u260314);
            double \u260318 = (float)(\u260311 - \u260315);
            bfl.x();
            bfl.f();
            bfl.p();
            int \u260319 = 24;
            double \u260320 = 0.025;
            \u26032.a(5, bms.f);
            for (n2 = 0; n2 <= 24; ++n2) {
                f4 = 0.5f;
                \u2603 = 0.4f;
                \u2603 = 0.3f;
                if (n2 % 2 == 0) {
                    f4 *= 0.7f;
                    \u2603 *= 0.7f;
                    \u2603 *= 0.7f;
                }
                \u2603 = (float)n2 / 24.0f;
                \u26032.b(d2 + \u260316 * (double)\u2603 + 0.0, d3 + \u260317 * (double)(\u2603 * \u2603 + \u2603) * 0.5 + (double)((24.0f - (float)n2) / 18.0f + 0.125f), d4 + \u260318 * (double)\u2603).a(f4, \u2603, \u2603, 1.0f).d();
                \u26032.b(d2 + \u260316 * (double)\u2603 + 0.025, d3 + \u260317 * (double)(\u2603 * \u2603 + \u2603) * 0.5 + (double)((24.0f - (float)n2) / 18.0f + 0.125f) + 0.025, d4 + \u260318 * (double)\u2603).a(f4, \u2603, \u2603, 1.0f).d();
            }
            bfx2.b();
            \u26032.a(5, bms.f);
            for (n2 = 0; n2 <= 24; ++n2) {
                f4 = 0.5f;
                \u2603 = 0.4f;
                \u2603 = 0.3f;
                if (n2 % 2 == 0) {
                    f4 *= 0.7f;
                    \u2603 *= 0.7f;
                    \u2603 *= 0.7f;
                }
                \u2603 = (float)n2 / 24.0f;
                \u26032.b(d2 + \u260316 * (double)\u2603 + 0.0, d3 + \u260317 * (double)(\u2603 * \u2603 + \u2603) * 0.5 + (double)((24.0f - (float)n2) / 18.0f + 0.125f) + 0.025, d4 + \u260318 * (double)\u2603).a(f4, \u2603, \u2603, 1.0f).d();
                \u26032.b(d2 + \u260316 * (double)\u2603 + 0.025, d3 + \u260317 * (double)(\u2603 * \u2603 + \u2603) * 0.5 + (double)((24.0f - (float)n2) / 18.0f + 0.125f), d4 + \u260318 * (double)\u2603 + 0.025).a(f4, \u2603, \u2603, 1.0f).d();
            }
            bfx2.b();
            bfl.e();
            bfl.w();
            bfl.o();
        }
    }

    @Override
    protected /* synthetic */ boolean a(pr pr2) {
        return this.b((T)((ps)pr2));
    }
}

