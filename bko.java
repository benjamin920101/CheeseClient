/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class bko
implements blb<pr> {
    private final bjl a;

    public bko(bjl bjl2) {
        this.a = bjl2;
    }

    @Override
    public void a(pr pr2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        int n2 = pr2.bv();
        if (n2 <= 0) {
            return;
        }
        wq \u26032 = new wq(pr2.o, pr2.s, pr2.t, pr2.u);
        Random \u26033 = new Random(pr2.F());
        avc.a();
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            bfl.E();
            bct bct2 = this.a.b().a(\u26033);
            bcr \u26034 = bct2.l.get(\u26033.nextInt(bct2.l.size()));
            bct2.c(0.0625f);
            float \u26035 = \u26033.nextFloat();
            float \u26036 = \u26033.nextFloat();
            float \u26037 = \u26033.nextFloat();
            float \u26038 = (\u26034.a + (\u26034.d - \u26034.a) * \u26035) / 16.0f;
            float \u26039 = (\u26034.b + (\u26034.e - \u26034.b) * \u26036) / 16.0f;
            float \u260310 = (\u26034.c + (\u26034.f - \u26034.c) * \u26037) / 16.0f;
            bfl.b(\u26038, \u26039, \u260310);
            \u26035 = \u26035 * 2.0f - 1.0f;
            \u26036 = \u26036 * 2.0f - 1.0f;
            \u26037 = \u26037 * 2.0f - 1.0f;
            float \u260311 = ns.c((\u26035 *= -1.0f) * \u26035 + (\u26037 *= -1.0f) * \u26037);
            \u26032.A = \u26032.y = (float)(Math.atan2(\u26035, \u26037) * 180.0 / 3.1415927410125732);
            \u26032.B = \u26032.z = (float)(Math.atan2(\u26036 *= -1.0f, \u260311) * 180.0 / 3.1415927410125732);
            double \u260312 = 0.0;
            double \u260313 = 0.0;
            double \u260314 = 0.0;
            this.a.d().a(\u26032, \u260312, \u260313, \u260314, 0.0f, f4);
            bfl.F();
        }
        avc.b();
    }

    @Override
    public boolean b() {
        return false;
    }
}

