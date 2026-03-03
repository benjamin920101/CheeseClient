/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class bku
implements blb<ug> {
    @Override
    public void a(ug ug2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (ug2.by <= 0) {
            return;
        }
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        avc.a();
        float \u26033 = ((float)ug2.by + f4) / 200.0f;
        float \u26034 = 0.0f;
        if (\u26033 > 0.8f) {
            \u26034 = (\u26033 - 0.8f) / 0.2f;
        }
        Random \u26035 = new Random(432L);
        bfl.x();
        bfl.j(7425);
        bfl.l();
        bfl.b(770, 1);
        bfl.c();
        bfl.o();
        bfl.a(false);
        bfl.E();
        bfl.b(0.0f, -1.0f, -2.0f);
        int \u26036 = 0;
        while ((float)\u26036 < (\u26033 + \u26033 * \u26033) / 2.0f * 60.0f) {
            bfl.b(\u26035.nextFloat() * 360.0f, 1.0f, 0.0f, 0.0f);
            bfl.b(\u26035.nextFloat() * 360.0f, 0.0f, 1.0f, 0.0f);
            bfl.b(\u26035.nextFloat() * 360.0f, 0.0f, 0.0f, 1.0f);
            bfl.b(\u26035.nextFloat() * 360.0f, 1.0f, 0.0f, 0.0f);
            bfl.b(\u26035.nextFloat() * 360.0f, 0.0f, 1.0f, 0.0f);
            bfl.b(\u26035.nextFloat() * 360.0f + \u26033 * 90.0f, 0.0f, 0.0f, 1.0f);
            float f9 = \u26035.nextFloat() * 20.0f + 5.0f + \u26034 * 10.0f;
            \u2603 = \u26035.nextFloat() * 2.0f + 1.0f + \u26034 * 2.0f;
            \u26032.a(6, bms.f);
            \u26032.b(0.0, 0.0, 0.0).b(255, 255, 255, (int)(255.0f * (1.0f - \u26034))).d();
            \u26032.b(-0.866 * (double)\u2603, (double)f9, (double)(-0.5f * \u2603)).b(255, 0, 255, 0).d();
            \u26032.b(0.866 * (double)\u2603, (double)f9, (double)(-0.5f * \u2603)).b(255, 0, 255, 0).d();
            \u26032.b(0.0, (double)f9, (double)(1.0f * \u2603)).b(255, 0, 255, 0).d();
            \u26032.b(-0.866 * (double)\u2603, (double)f9, (double)(-0.5f * \u2603)).b(255, 0, 255, 0).d();
            bfx2.b();
            ++\u26036;
        }
        bfl.F();
        bfl.a(true);
        bfl.p();
        bfl.k();
        bfl.j(7424);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.w();
        bfl.d();
        avc.b();
    }

    @Override
    public boolean b() {
        return false;
    }
}

