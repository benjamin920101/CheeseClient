/*
 * Decompiled with CFR 0.152.
 */
public class bdz
extends beb {
    private pr a;

    protected bdz(adm adm2, double d2, double d3, double d4) {
        super(adm2, d2, d3, d4, 0.0, 0.0, 0.0);
        this.at = 1.0f;
        this.as = 1.0f;
        this.ar = 1.0f;
        this.x = 0.0;
        this.w = 0.0;
        this.v = 0.0;
        this.i = 0.0f;
        this.g = 30;
    }

    @Override
    public int a() {
        return 3;
    }

    @Override
    public void t_() {
        super.t_();
        if (this.a == null) {
            vt vt2 = new vt(this.o);
            vt2.co();
            this.a = vt2;
        }
    }

    @Override
    public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        if (this.a == null) {
            return;
        }
        biu biu2 = ave.A().af();
        biu2.a(beb.aw, beb.ax, beb.ay);
        float \u26032 = 0.42553192f;
        float \u26033 = ((float)this.f + f2) / (float)this.g;
        bfl.a(true);
        bfl.l();
        bfl.j();
        bfl.b(770, 771);
        float \u26034 = 240.0f;
        bqs.a(bqs.r, \u26034, \u26034);
        bfl.E();
        float \u26035 = 0.05f + 0.5f * ns.a(\u26033 * (float)Math.PI);
        bfl.c(1.0f, 1.0f, 1.0f, \u26035);
        bfl.b(0.0f, 1.8f, 0.0f);
        bfl.b(180.0f - pk2.y, 0.0f, 1.0f, 0.0f);
        bfl.b(60.0f - 150.0f * \u26033 - pk2.z, 1.0f, 0.0f, 0.0f);
        bfl.b(0.0f, -0.4f, -1.5f);
        bfl.a(\u26032, \u26032, \u26032);
        this.a.A = 0.0f;
        this.a.y = 0.0f;
        this.a.aL = 0.0f;
        this.a.aK = 0.0f;
        biu2.a(this.a, 0.0, 0.0, 0.0, 0.0f, f2);
        bfl.F();
        bfl.j();
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bdz(adm2, d2, d3, d4);
        }
    }
}

