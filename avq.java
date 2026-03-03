/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;

public class avq {
    private static final jy a = new jy("textures/map/map_icons.png");
    private final bmj b;
    private final Map<String, a> c = Maps.newHashMap();

    public avq(bmj bmj2) {
        this.b = bmj2;
    }

    public void a(atg atg2) {
        this.b(atg2).a();
    }

    public void a(atg atg2, boolean bl2) {
        this.b(atg2).a(bl2);
    }

    private a b(atg atg2) {
        a a2 = this.c.get(atg2.a);
        if (a2 == null) {
            a2 = new a(atg2);
            this.c.put(atg2.a, a2);
        }
        return a2;
    }

    public void a() {
        for (a a2 : this.c.values()) {
            this.b.c(a2.d);
        }
        this.c.clear();
    }

    class a {
        private final atg b;
        private final blz c;
        private final jy d;
        private final int[] e;

        private a(atg atg2) {
            this.b = atg2;
            this.c = new blz(128, 128);
            this.e = this.c.e();
            this.d = avq.this.b.a("map/" + atg2.a, this.c);
            for (int i2 = 0; i2 < this.e.length; ++i2) {
                this.e[i2] = 0;
            }
        }

        private void a() {
            for (int i2 = 0; i2 < 16384; ++i2) {
                \u2603 = this.b.f[i2] & 0xFF;
                this.e[i2] = \u2603 / 4 == 0 ? (i2 + i2 / 128 & 1) * 8 + 16 << 24 : arn.a[\u2603 / 4].a(\u2603 & 3);
            }
            this.c.d();
        }

        private void a(boolean bl2) {
            int n2 = 0;
            \u2603 = 0;
            bfx \u26032 = bfx.a();
            bfd \u26033 = \u26032.c();
            float \u26034 = 0.0f;
            avq.this.b.a(this.d);
            bfl.l();
            bfl.a(1, 771, 0, 1);
            bfl.c();
            \u26033.a(7, bms.g);
            \u26033.b((double)((float)(n2 + 0) + \u26034), (double)((float)(\u2603 + 128) - \u26034), (double)-0.01f).a(0.0, 1.0).d();
            \u26033.b((double)((float)(n2 + 128) - \u26034), (double)((float)(\u2603 + 128) - \u26034), (double)-0.01f).a(1.0, 1.0).d();
            \u26033.b((double)((float)(n2 + 128) - \u26034), (double)((float)(\u2603 + 0) + \u26034), (double)-0.01f).a(1.0, 0.0).d();
            \u26033.b((double)((float)(n2 + 0) + \u26034), (double)((float)(\u2603 + 0) + \u26034), (double)-0.01f).a(0.0, 0.0).d();
            \u26032.b();
            bfl.d();
            bfl.k();
            avq.this.b.a(a);
            \u2603 = 0;
            for (atf atf2 : this.b.h.values()) {
                if (bl2 && atf2.a() != 1) continue;
                bfl.E();
                bfl.b((float)n2 + (float)atf2.b() / 2.0f + 64.0f, (float)\u2603 + (float)atf2.c() / 2.0f + 64.0f, -0.02f);
                bfl.b((float)(atf2.d() * 360) / 16.0f, 0.0f, 0.0f, 1.0f);
                bfl.a(4.0f, 4.0f, 3.0f);
                bfl.b(-0.125f, 0.125f, 0.0f);
                byte by = atf2.a();
                float \u26035 = (float)(by % 4 + 0) / 4.0f;
                float \u26036 = (float)(by / 4 + 0) / 4.0f;
                float \u26037 = (float)(by % 4 + 1) / 4.0f;
                float \u26038 = (float)(by / 4 + 1) / 4.0f;
                \u26033.a(7, bms.g);
                float \u26039 = -0.001f;
                \u26033.b(-1.0, 1.0, (double)((float)\u2603 * -0.001f)).a(\u26035, \u26036).d();
                \u26033.b(1.0, 1.0, (double)((float)\u2603 * -0.001f)).a(\u26037, \u26036).d();
                \u26033.b(1.0, -1.0, (double)((float)\u2603 * -0.001f)).a(\u26037, \u26038).d();
                \u26033.b(-1.0, -1.0, (double)((float)\u2603 * -0.001f)).a(\u26035, \u26038).d();
                \u26032.b();
                bfl.F();
                ++\u2603;
            }
            bfl.E();
            bfl.b(0.0f, 0.0f, -0.04f);
            bfl.a(1.0f, 1.0f, 1.0f);
            bfl.F();
        }
    }
}

