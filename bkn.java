/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;

public abstract class bkn<T extends bbo>
implements blb<pr> {
    protected static final jy b = new jy("textures/misc/enchanted_item_glint.png");
    protected T c;
    protected T d;
    private final bjl<?> a;
    private float e = 1.0f;
    private float f = 1.0f;
    private float g = 1.0f;
    private float h = 1.0f;
    private boolean i;
    private static final Map<String, jy> j = Maps.newHashMap();

    public bkn(bjl<?> bjl2) {
        this.a = bjl2;
        this.a();
    }

    @Override
    public void a(pr pr2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this.a(pr2, f2, f3, f4, f5, f6, f7, f8, 4);
        this.a(pr2, f2, f3, f4, f5, f6, f7, f8, 3);
        this.a(pr2, f2, f3, f4, f5, f6, f7, f8, 2);
        this.a(pr2, f2, f3, f4, f5, f6, f7, f8, 1);
    }

    @Override
    public boolean b() {
        return false;
    }

    private void a(pr pr2, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n2) {
        zx zx2 = this.a(pr2, n2);
        if (zx2 == null || !(zx2.b() instanceof yj)) {
            return;
        }
        yj \u26032 = (yj)zx2.b();
        T \u26033 = this.a(n2);
        ((bbo)\u26033).a(this.a.b());
        ((bbo)\u26033).a(pr2, f2, f3, f4);
        this.a(\u26033, n2);
        boolean \u26034 = this.b(n2);
        this.a.a(this.a(\u26032, \u26034));
        switch (\u26032.x_()) {
            case a: {
                int n3 = \u26032.b(zx2);
                float \u26035 = (float)(n3 >> 16 & 0xFF) / 255.0f;
                float \u26036 = (float)(n3 >> 8 & 0xFF) / 255.0f;
                float \u26037 = (float)(n3 & 0xFF) / 255.0f;
                bfl.c(this.f * \u26035, this.g * \u26036, this.h * \u26037, this.e);
                ((bbo)\u26033).a(pr2, f2, f3, f5, f6, f7, f8);
                this.a.a(this.a(\u26032, \u26034, "overlay"));
            }
            case b: 
            case c: 
            case d: 
            case e: {
                bfl.c(this.f, this.g, this.h, this.e);
                ((bbo)\u26033).a(pr2, f2, f3, f5, f6, f7, f8);
            }
        }
        if (!this.i && zx2.w()) {
            this.a(pr2, \u26033, f2, f3, f4, f5, f6, f7, f8);
        }
    }

    public zx a(pr pr2, int n2) {
        return pr2.q(n2 - 1);
    }

    public T a(int n2) {
        return this.b(n2) ? this.c : this.d;
    }

    private boolean b(int n2) {
        return n2 == 2;
    }

    private void a(pr pr2, T t2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        \u2603 = (float)pr2.W + f4;
        this.a.a(b);
        bfl.l();
        bfl.c(514);
        bfl.a(false);
        \u2603 = 0.5f;
        bfl.c(\u2603, \u2603, \u2603, 1.0f);
        for (int i2 = 0; i2 < 2; ++i2) {
            bfl.f();
            bfl.b(768, 1);
            float f9 = 0.76f;
            bfl.c(0.5f * f9, 0.25f * f9, 0.8f * f9, 1.0f);
            bfl.n(5890);
            bfl.D();
            \u2603 = 0.33333334f;
            bfl.a(\u2603, \u2603, \u2603);
            bfl.b(30.0f - (float)i2 * 60.0f, 0.0f, 0.0f, 1.0f);
            bfl.b(0.0f, \u2603 * (0.001f + (float)i2 * 0.003f) * 20.0f, 0.0f);
            bfl.n(5888);
            ((bbo)t2).a(pr2, f2, f3, f5, f6, f7, f8);
        }
        bfl.n(5890);
        bfl.D();
        bfl.n(5888);
        bfl.e();
        bfl.a(true);
        bfl.c(515);
        bfl.k();
    }

    private jy a(yj yj2, boolean bl2) {
        return this.a(yj2, bl2, null);
    }

    private jy a(yj yj2, boolean bl2, String string) {
        \u2603 = String.format("textures/models/armor/%s_layer_%d%s.png", yj2.x_().c(), bl2 ? 2 : 1, string == null ? "" : String.format("_%s", string));
        jy jy2 = j.get(\u2603);
        if (jy2 == null) {
            jy2 = new jy(\u2603);
            j.put(\u2603, jy2);
        }
        return jy2;
    }

    protected abstract void a();

    protected abstract void a(T var1, int var2);
}

