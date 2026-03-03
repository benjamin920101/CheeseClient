/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class bbf
extends bbo {
    bct a;
    bct[] b = new bct[9];

    public bbf() {
        int n2 = -16;
        this.a = new bct(this, 0, 0);
        this.a.a(-8.0f, -8.0f, -8.0f, 16, 16, 16);
        this.a.d += (float)(24 + n2);
        Random \u26032 = new Random(1660L);
        for (\u2603 = 0; \u2603 < this.b.length; ++\u2603) {
            this.b[\u2603] = new bct(this, 0, 0);
            float f2 = (((float)(\u2603 % 3) - (float)(\u2603 / 3 % 2) * 0.5f + 0.25f) / 2.0f * 2.0f - 1.0f) * 5.0f;
            \u2603 = ((float)(\u2603 / 3) / 2.0f * 2.0f - 1.0f) * 5.0f;
            int \u26033 = \u26032.nextInt(7) + 8;
            this.b[\u2603].a(-1.0f, 0.0f, -1.0f, 2, \u26033, 2);
            this.b[\u2603].c = f2;
            this.b[\u2603].e = \u2603;
            this.b[\u2603].d = 31 + n2;
        }
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        for (int i2 = 0; i2 < this.b.length; ++i2) {
            this.b[i2].f = 0.2f * ns.a(f4 * 0.3f + (float)i2) + 0.4f;
        }
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a(f2, f3, f4, f5, f6, f7, pk2);
        bfl.E();
        bfl.b(0.0f, 0.6f, 0.0f);
        this.a.a(f7);
        for (bct bct2 : this.b) {
            bct2.a(f7);
        }
        bfl.F();
    }
}

