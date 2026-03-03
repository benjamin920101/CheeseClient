/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.nio.FloatBuffer;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public abstract class bjl<T extends pr>
extends biv<T> {
    private static final Logger a = LogManager.getLogger();
    private static final blz e = new blz(16, 16);
    protected bbo f;
    protected FloatBuffer g = avd.h(4);
    protected List<blb<T>> h = Lists.newArrayList();
    protected boolean i = false;

    public bjl(biu biu2, bbo bbo2, float f2) {
        super(biu2);
        this.f = bbo2;
        this.c = f2;
    }

    protected <V extends pr, U extends blb<V>> boolean a(U u2) {
        return this.h.add(u2);
    }

    @Override
    protected <V extends pr, U extends blb<V>> boolean b(U u2) {
        return this.h.remove(u2);
    }

    public bbo b() {
        return this.f;
    }

    protected float a(float f2, float f3, float f4) {
        for (\u2603 = f3 - f2; \u2603 < -180.0f; \u2603 += 360.0f) {
        }
        while (\u2603 >= 180.0f) {
            \u2603 -= 360.0f;
        }
        return f2 + f4 * \u2603;
    }

    public void C_() {
    }

    @Override
    public void a(T t22, double d2, double d3, double d4, float f2, float f3) {
        bfl.E();
        bfl.p();
        this.f.p = this.d(t22, f3);
        this.f.q = ((pk)t22).au();
        this.f.r = ((pr)t22).j_();
        try {
            float \u26034;
            float \u26033;
            float \u26032;
            \u26032 = this.a(((pr)t22).aJ, ((pr)t22).aI, f3);
            \u2603 = this.a(((pr)t22).aL, ((pr)t22).aK, f3);
            \u26033 = \u2603 - \u26032;
            if (((pk)t22).au() && ((pr)t22).m instanceof pr) {
                pr pr2 = (pr)((pr)t22).m;
                \u26032 = this.a(pr2.aJ, pr2.aI, f3);
                \u26033 = \u2603 - \u26032;
                \u26034 = ns.g(\u26033);
                if (\u26034 < -85.0f) {
                    \u26034 = -85.0f;
                }
                if (\u26034 >= 85.0f) {
                    \u26034 = 85.0f;
                }
                \u26032 = \u2603 - \u26034;
                if (\u26034 * \u26034 > 2500.0f) {
                    \u26032 += \u26034 * 0.2f;
                }
            }
            float f4 = ((pr)t22).B + (((pr)t22).z - ((pr)t22).B) * f3;
            this.a(t22, d2, d3, d4);
            \u26034 = this.b(t22, f3);
            this.a(t22, \u26034, \u26032, f3);
            bfl.B();
            bfl.a(-1.0f, -1.0f, 1.0f);
            this.a(t22, f3);
            \u2603 = 0.0625f;
            bfl.b(0.0f, -1.5078125f, 0.0f);
            \u2603 = ((pr)t22).aA + (((pr)t22).aB - ((pr)t22).aA) * f3;
            \u2603 = ((pr)t22).aC - ((pr)t22).aB * (1.0f - f3);
            if (((pr)t22).j_()) {
                \u2603 *= 3.0f;
            }
            if (\u2603 > 1.0f) {
                \u2603 = 1.0f;
            }
            bfl.d();
            this.f.a((pr)t22, \u2603, \u2603, f3);
            this.f.a(\u2603, \u2603, \u26034, \u26033, f4, 0.0625f, (pk)t22);
            if (this.i) {
                boolean bl2 = this.c(t22);
                this.a(t22, \u2603, \u2603, \u26034, \u26033, f4, 0.0625f);
                if (bl2) {
                    this.e();
                }
            } else {
                T t22;
                boolean \u26035 = this.c(t22, f3);
                this.a(t22, \u2603, \u2603, \u26034, \u26033, f4, 0.0625f);
                if (\u26035) {
                    this.f();
                }
                bfl.a(true);
                if (!(t22 instanceof wn) || !((wn)t22).v()) {
                    this.a(t22, \u2603, \u2603, f3, \u26034, \u26033, f4, 0.0625f);
                }
            }
            bfl.C();
        }
        catch (Exception exception) {
            a.error("Couldn't render entity", (Throwable)exception);
        }
        bfl.g(bqs.r);
        bfl.w();
        bfl.g(bqs.q);
        bfl.o();
        bfl.F();
        if (!this.i) {
            super.a(t22, d2, d3, d4, f2, f3);
        }
    }

    @Override
    protected boolean c(T t2) {
        int n2 = 0xFFFFFF;
        if (t2 instanceof wn && (\u2603 = (aul)((pr)t2).bO()) != null && (\u2603 = avn.b(\u2603.e())).length() >= 2) {
            n2 = this.c().b(\u2603.charAt(1));
        }
        float \u26032 = (float)(n2 >> 16 & 0xFF) / 255.0f;
        float \u26033 = (float)(n2 >> 8 & 0xFF) / 255.0f;
        float \u26034 = (float)(n2 & 0xFF) / 255.0f;
        bfl.f();
        bfl.g(bqs.q);
        bfl.c(\u26032, \u26033, \u26034, 1.0f);
        bfl.x();
        bfl.g(bqs.r);
        bfl.x();
        bfl.g(bqs.q);
        return true;
    }

    protected void e() {
        bfl.e();
        bfl.g(bqs.q);
        bfl.w();
        bfl.g(bqs.r);
        bfl.w();
        bfl.g(bqs.q);
    }

    protected void a(T t2, float f2, float f3, float f4, float f5, float f6, float f7) {
        boolean bl2 = !((pk)t2).ax();
        boolean bl3 = \u2603 = !bl2 && !((pk)t2).f(ave.A().h);
        if (bl2 || \u2603) {
            if (!this.c(t2)) {
                return;
            }
            if (\u2603) {
                bfl.E();
                bfl.c(1.0f, 1.0f, 1.0f, 0.15f);
                bfl.a(false);
                bfl.l();
                bfl.b(770, 771);
                bfl.a(516, 0.003921569f);
            }
            this.f.a((pk)t2, f2, f3, f4, f5, f6, f7);
            if (\u2603) {
                bfl.k();
                bfl.a(516, 0.1f);
                bfl.F();
                bfl.a(true);
            }
        }
    }

    protected boolean c(T t2, float f2) {
        return this.a(t2, f2, true);
    }

    protected boolean a(T t2, float f2, boolean bl2) {
        float f3 = ((pk)t2).c(f2);
        int \u26032 = this.a(t2, f3, f2);
        boolean \u26033 = (\u26032 >> 24 & 0xFF) > 0;
        boolean bl3 = \u2603 = ((pr)t2).au > 0 || ((pr)t2).ax > 0;
        if (\u26033 || \u2603) {
            if (!\u26033 && !bl2) {
                return false;
            }
            bfl.g(bqs.q);
            bfl.w();
            GL11.glTexEnvi(8960, 8704, bqs.t);
            GL11.glTexEnvi(8960, bqs.y, 8448);
            GL11.glTexEnvi(8960, bqs.z, bqs.q);
            GL11.glTexEnvi(8960, bqs.A, bqs.v);
            GL11.glTexEnvi(8960, bqs.C, 768);
            GL11.glTexEnvi(8960, bqs.D, 768);
            GL11.glTexEnvi(8960, bqs.F, 7681);
            GL11.glTexEnvi(8960, bqs.G, bqs.q);
            GL11.glTexEnvi(8960, bqs.J, 770);
            bfl.g(bqs.r);
            bfl.w();
            GL11.glTexEnvi(8960, 8704, bqs.t);
            GL11.glTexEnvi(8960, bqs.y, bqs.u);
            GL11.glTexEnvi(8960, bqs.z, bqs.w);
            GL11.glTexEnvi(8960, bqs.A, bqs.x);
            GL11.glTexEnvi(8960, bqs.B, bqs.w);
            GL11.glTexEnvi(8960, bqs.C, 768);
            GL11.glTexEnvi(8960, bqs.D, 768);
            GL11.glTexEnvi(8960, bqs.E, 770);
            GL11.glTexEnvi(8960, bqs.F, 7681);
            GL11.glTexEnvi(8960, bqs.G, bqs.x);
            GL11.glTexEnvi(8960, bqs.J, 770);
            this.g.position(0);
            if (\u2603) {
                this.g.put(1.0f);
                this.g.put(0.0f);
                this.g.put(0.0f);
                this.g.put(0.3f);
            } else {
                \u2603 = (float)(\u26032 >> 24 & 0xFF) / 255.0f;
                \u2603 = (float)(\u26032 >> 16 & 0xFF) / 255.0f;
                \u2603 = (float)(\u26032 >> 8 & 0xFF) / 255.0f;
                \u2603 = (float)(\u26032 & 0xFF) / 255.0f;
                this.g.put(\u2603);
                this.g.put(\u2603);
                this.g.put(\u2603);
                this.g.put(1.0f - \u2603);
            }
            this.g.flip();
            GL11.glTexEnv(8960, 8705, this.g);
            bfl.g(bqs.s);
            bfl.w();
            bfl.i(e.b());
            GL11.glTexEnvi(8960, 8704, bqs.t);
            GL11.glTexEnvi(8960, bqs.y, 8448);
            GL11.glTexEnvi(8960, bqs.z, bqs.x);
            GL11.glTexEnvi(8960, bqs.A, bqs.r);
            GL11.glTexEnvi(8960, bqs.C, 768);
            GL11.glTexEnvi(8960, bqs.D, 768);
            GL11.glTexEnvi(8960, bqs.F, 7681);
            GL11.glTexEnvi(8960, bqs.G, bqs.x);
            GL11.glTexEnvi(8960, bqs.J, 770);
            bfl.g(bqs.q);
            return true;
        }
        return false;
    }

    protected void f() {
        bfl.g(bqs.q);
        bfl.w();
        GL11.glTexEnvi(8960, 8704, bqs.t);
        GL11.glTexEnvi(8960, bqs.y, 8448);
        GL11.glTexEnvi(8960, bqs.z, bqs.q);
        GL11.glTexEnvi(8960, bqs.A, bqs.v);
        GL11.glTexEnvi(8960, bqs.C, 768);
        GL11.glTexEnvi(8960, bqs.D, 768);
        GL11.glTexEnvi(8960, bqs.F, 8448);
        GL11.glTexEnvi(8960, bqs.G, bqs.q);
        GL11.glTexEnvi(8960, bqs.H, bqs.v);
        GL11.glTexEnvi(8960, bqs.J, 770);
        GL11.glTexEnvi(8960, bqs.K, 770);
        bfl.g(bqs.r);
        GL11.glTexEnvi(8960, 8704, bqs.t);
        GL11.glTexEnvi(8960, bqs.y, 8448);
        GL11.glTexEnvi(8960, bqs.C, 768);
        GL11.glTexEnvi(8960, bqs.D, 768);
        GL11.glTexEnvi(8960, bqs.z, 5890);
        GL11.glTexEnvi(8960, bqs.A, bqs.x);
        GL11.glTexEnvi(8960, bqs.F, 8448);
        GL11.glTexEnvi(8960, bqs.J, 770);
        GL11.glTexEnvi(8960, bqs.G, 5890);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.g(bqs.s);
        bfl.x();
        bfl.i(0);
        GL11.glTexEnvi(8960, 8704, bqs.t);
        GL11.glTexEnvi(8960, bqs.y, 8448);
        GL11.glTexEnvi(8960, bqs.C, 768);
        GL11.glTexEnvi(8960, bqs.D, 768);
        GL11.glTexEnvi(8960, bqs.z, 5890);
        GL11.glTexEnvi(8960, bqs.A, bqs.x);
        GL11.glTexEnvi(8960, bqs.F, 8448);
        GL11.glTexEnvi(8960, bqs.J, 770);
        GL11.glTexEnvi(8960, bqs.G, 5890);
        bfl.g(bqs.q);
    }

    @Override
    protected void a(T t2, double d2, double d3, double d4) {
        bfl.b((float)d2, (float)d3, (float)d4);
    }

    protected void a(T t2, float f2, float f3, float f4) {
        bfl.b(180.0f - f3, 0.0f, 1.0f, 0.0f);
        if (((pr)t2).ax > 0) {
            \u2603 = ((float)((pr)t2).ax + f4 - 1.0f) / 20.0f * 1.6f;
            if ((\u2603 = ns.c(\u2603)) > 1.0f) {
                \u2603 = 1.0f;
            }
            bfl.b(\u2603 * this.b(t2), 0.0f, 0.0f, 1.0f);
        } else {
            String string = a.a(((pk)t2).e_());
            if (string != null && (string.equals("Dinnerbone") || string.equals("Grumm")) && (!(t2 instanceof wn) || ((wn)t2).a(wo.a))) {
                bfl.b(0.0f, ((pr)t2).K + 0.1f, 0.0f);
                bfl.b(180.0f, 0.0f, 0.0f, 1.0f);
            }
        }
    }

    protected float d(T t2, float f2) {
        return ((pr)t2).l(f2);
    }

    protected float b(T t2, float f2) {
        return (float)((pr)t2).W + f2;
    }

    protected void a(T t2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        for (blb<T> blb2 : this.h) {
            boolean bl2 = this.a(t2, f4, blb2.b());
            blb2.a(t2, f2, f3, f4, f5, f6, f7, f8);
            if (!bl2) continue;
            this.f();
        }
    }

    protected float b(T t2) {
        return 90.0f;
    }

    protected int a(T t2, float f2, float f3) {
        return 0;
    }

    protected void a(T t2, float f2) {
    }

    public void b(T t2, double d2, double d3, double d4) {
        if (!this.a(t2)) {
            return;
        }
        \u2603 = ((pk)t2).h(this.b.c);
        float f2 = \u2603 = ((pk)t2).av() ? 32.0f : 64.0f;
        if (\u2603 >= (double)(\u2603 * \u2603)) {
            return;
        }
        String string = ((pk)t2).f_().d();
        float \u26032 = 0.02666667f;
        bfl.a(516, 0.1f);
        if (((pk)t2).av()) {
            avn avn2 = this.c();
            bfl.E();
            bfl.b((float)d2, (float)d3 + ((pr)t2).K + 0.5f - (((pr)t2).j_() ? ((pr)t2).K / 2.0f : 0.0f), (float)d4);
            GL11.glNormal3f(0.0f, 1.0f, 0.0f);
            bfl.b(-this.b.e, 0.0f, 1.0f, 0.0f);
            bfl.b(this.b.f, 1.0f, 0.0f, 0.0f);
            bfl.a(-0.02666667f, -0.02666667f, 0.02666667f);
            bfl.b(0.0f, 9.374999f, 0.0f);
            bfl.f();
            bfl.a(false);
            bfl.l();
            bfl.x();
            bfl.a(770, 771, 1, 0);
            int \u26033 = avn2.a(string) / 2;
            bfx \u26034 = bfx.a();
            bfd \u26035 = \u26034.c();
            \u26035.a(7, bms.f);
            \u26035.b((double)(-\u26033 - 1), -1.0, 0.0).a(0.0f, 0.0f, 0.0f, 0.25f).d();
            \u26035.b((double)(-\u26033 - 1), 8.0, 0.0).a(0.0f, 0.0f, 0.0f, 0.25f).d();
            \u26035.b((double)(\u26033 + 1), 8.0, 0.0).a(0.0f, 0.0f, 0.0f, 0.25f).d();
            \u26035.b((double)(\u26033 + 1), -1.0, 0.0).a(0.0f, 0.0f, 0.0f, 0.25f).d();
            \u26034.b();
            bfl.w();
            bfl.a(true);
            avn2.a(string, -avn2.a(string) / 2, 0, 0x20FFFFFF);
            bfl.e();
            bfl.k();
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            bfl.F();
        } else {
            this.a(t2, d2, d3 - (((pr)t2).j_() ? (double)(((pr)t2).K / 2.0f) : 0.0), d4, string, 0.02666667f, \u2603);
        }
    }

    protected boolean a(T t22) {
        T t22;
        bew bew2 = ave.A().h;
        if (t22 instanceof wn && t22 != bew2) {
            auq auq2 = ((pr)t22).bO();
            \u2603 = bew2.bO();
            if (auq2 != null) {
                auq.a a2 = auq2.i();
                switch (a2) {
                    case a: {
                        return true;
                    }
                    case b: {
                        return false;
                    }
                    case c: {
                        return \u2603 == null || auq2.a(\u2603);
                    }
                    case d: {
                        return \u2603 == null || !auq2.a(\u2603);
                    }
                }
                return true;
            }
        }
        return ave.v() && t22 != this.b.c && !((pk)t22).f(bew2) && ((pr)t22).l == null;
    }

    public void a(boolean bl2) {
        this.i = bl2;
    }

    @Override
    protected /* synthetic */ boolean b(pk pk2) {
        return this.a((pr)pk2);
    }

    @Override
    public /* synthetic */ void a(pk pk2, double d2, double d3, double d4) {
        this.b((pr)pk2, d2, d3, d4);
    }

    static {
        int[] nArray = e.e();
        for (int i2 = 0; i2 < 256; ++i2) {
            nArray[i2] = -1;
        }
        e.d();
    }
}

