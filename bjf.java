/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class bjf
extends biv<uz> {
    private final bjh a;
    private Random e = new Random();

    public bjf(biu biu2, bjh bjh2) {
        super(biu2);
        this.a = bjh2;
        this.c = 0.15f;
        this.d = 0.75f;
    }

    private int a(uz uz2, double d2, double d3, double d4, float f2, boq boq2) {
        float f3;
        zx zx2 = uz2.l();
        zw \u26032 = zx2.b();
        if (\u26032 == null) {
            return 0;
        }
        boolean \u26033 = boq2.c();
        int \u26034 = this.a(zx2);
        float \u26035 = 0.25f;
        float \u26036 = ns.a(((float)uz2.o() + f2) / 10.0f + uz2.a) * 0.1f + 0.1f;
        float \u26037 = boq2.f().b((bgr.b)bgr.b.f).d.y;
        bfl.b((float)d2, (float)d3 + \u26036 + 0.25f * \u26037, (float)d4);
        if (\u26033 || this.b.g != null) {
            f3 = (((float)uz2.o() + f2) / 20.0f + uz2.a) * 57.295776f;
            bfl.b(f3, 0.0f, 1.0f, 0.0f);
        }
        if (!\u26033) {
            f3 = -0.0f * (float)(\u26034 - 1) * 0.5f;
            \u2603 = -0.0f * (float)(\u26034 - 1) * 0.5f;
            \u2603 = -0.046875f * (float)(\u26034 - 1) * 0.5f;
            bfl.b(f3, \u2603, \u2603);
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        return \u26034;
    }

    private int a(zx zx2) {
        int n2 = 1;
        if (zx2.b > 48) {
            n2 = 5;
        } else if (zx2.b > 32) {
            n2 = 4;
        } else if (zx2.b > 16) {
            n2 = 3;
        } else if (zx2.b > 1) {
            n2 = 2;
        }
        return n2;
    }

    @Override
    public void a(uz uz2, double d2, double d3, double d4, float f2, float f3) {
        zx zx2 = uz2.l();
        this.e.setSeed(187L);
        boolean \u26032 = false;
        if (this.c(uz2)) {
            this.b.a.b(this.a(uz2)).b(false, false);
            \u26032 = true;
        }
        bfl.B();
        bfl.a(516, 0.1f);
        bfl.l();
        bfl.a(770, 771, 1, 0);
        bfl.E();
        boq \u26033 = this.a.a().a(zx2);
        int \u26034 = this.a(uz2, d2, d3, d4, f3, \u26033);
        for (int i2 = 0; i2 < \u26034; ++i2) {
            float \u26035;
            if (\u26033.c()) {
                bfl.E();
                if (i2 > 0) {
                    \u26035 = (this.e.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    \u26036 = (this.e.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    \u26037 = (this.e.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    bfl.b(\u26035, \u26036, \u26037);
                }
                bfl.a(0.5f, 0.5f, 0.5f);
                \u26033.f().a(bgr.b.f);
                this.a.a(zx2, \u26033);
                bfl.F();
                continue;
            }
            bfl.E();
            \u26033.f().a(bgr.b.f);
            this.a.a(zx2, \u26033);
            bfl.F();
            \u26035 = \u26033.f().o.d.x;
            float \u26036 = \u26033.f().o.d.y;
            float \u26037 = \u26033.f().o.d.z;
            bfl.b(0.0f * \u26035, 0.0f * \u26036, 0.046875f * \u26037);
        }
        bfl.F();
        bfl.C();
        bfl.k();
        this.c(uz2);
        if (\u26032) {
            this.b.a.b(this.a(uz2)).a();
        }
        super.a(uz2, d2, d3, d4, f2, f3);
    }

    @Override
    protected jy a(uz uz2) {
        return bmh.g;
    }
}

