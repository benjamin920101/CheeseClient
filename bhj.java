/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import org.lwjgl.opengl.GL11;

public class bhj
extends bhd<aln> {
    private static final jy c = new jy("textures/entity/sign.png");
    private final bbx d = new bbx();

    @Override
    public void a(aln aln22, double d2, double d3, double d4, float f2, int n22) {
        int n22;
        float \u26034;
        afh afh2 = aln22.w();
        bfl.E();
        float \u26032 = 0.6666667f;
        if (afh2 == afi.an) {
            bfl.b((float)d2 + 0.5f, (float)d3 + 0.75f * \u26032, (float)d4 + 0.5f);
            float f3 = (float)(aln22.u() * 360) / 16.0f;
            bfl.b(-f3, 0.0f, 1.0f, 0.0f);
            this.d.b.j = true;
        } else {
            aln aln22;
            int \u26033 = aln22.u();
            \u26034 = 0.0f;
            if (\u26033 == 2) {
                \u26034 = 180.0f;
            }
            if (\u26033 == 4) {
                \u26034 = 90.0f;
            }
            if (\u26033 == 5) {
                \u26034 = -90.0f;
            }
            bfl.b((float)d2 + 0.5f, (float)d3 + 0.75f * \u26032, (float)d4 + 0.5f);
            bfl.b(-\u26034, 0.0f, 1.0f, 0.0f);
            bfl.b(0.0f, -0.3125f, -0.4375f);
            this.d.b.j = false;
        }
        if (n22 >= 0) {
            this.a(a[n22]);
            bfl.n(5890);
            bfl.E();
            bfl.a(4.0f, 2.0f, 1.0f);
            bfl.b(0.0625f, 0.0625f, 0.0625f);
            bfl.n(5888);
        } else {
            this.a(c);
        }
        bfl.B();
        bfl.E();
        bfl.a(\u26032, -\u26032, -\u26032);
        this.d.a();
        bfl.F();
        avn avn2 = this.c();
        \u26034 = 0.015625f * \u26032;
        bfl.b(0.0f, 0.5f * \u26032, 0.07f * \u26032);
        bfl.a(\u26034, -\u26034, \u26034);
        GL11.glNormal3f(0.0f, 0.0f, -1.0f * \u26034);
        bfl.a(false);
        int \u26035 = 0;
        if (n22 < 0) {
            for (int i2 = 0; i2 < aln22.a.length; ++i2) {
                if (aln22.a[i2] == null) continue;
                eu eu2 = aln22.a[i2];
                List<eu> \u26036 = avu.a(eu2, 90, avn2, false, true);
                String string = string2 = \u26036 != null && \u26036.size() > 0 ? \u26036.get(0).d() : "";
                if (i2 == aln22.f) {
                    String string2 = "> " + string2 + " <";
                    avn2.a(string2, -avn2.a(string2) / 2, i2 * 10 - aln22.a.length * 5, \u26035);
                    continue;
                }
                avn2.a(string2, -avn2.a(string2) / 2, i2 * 10 - aln22.a.length * 5, \u26035);
            }
        }
        bfl.a(true);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.F();
        if (n22 >= 0) {
            bfl.n(5890);
            bfl.F();
            bfl.n(5888);
        }
    }
}

