/*
 * Decompiled with CFR 0.152.
 */
public class ayp
extends ayl {
    private static final jy u = new jy("textures/gui/container/brewing_stand.png");
    private final wm v;
    private og w;

    public ayp(wm wm2, og og2) {
        super(new xm(wm2, og2));
        this.v = wm2;
        this.w = og2;
    }

    @Override
    protected void b(int n2, int n3) {
        String string = this.w.f_().c();
        this.q.a(string, this.f / 2 - this.q.a(string) / 2, 6, 0x404040);
        this.q.a(this.v.f_().c(), 8, this.g - 96 + 2, 0x404040);
    }

    @Override
    protected void a(float f2, int n2, int n3) {
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(u);
        \u2603 = (this.l - this.f) / 2;
        \u2603 = (this.m - this.g) / 2;
        this.b(\u2603, \u2603, 0, 0, this.f, this.g);
        \u2603 = this.w.a_(0);
        if (\u2603 > 0) {
            \u2603 = (int)(28.0f * (1.0f - (float)\u2603 / 400.0f));
            if (\u2603 > 0) {
                this.b(\u2603 + 97, \u2603 + 16, 176, 0, 9, \u2603);
            }
            \u2603 = \u2603 / 2 % 7;
            switch (\u2603) {
                case 6: {
                    \u2603 = 0;
                    break;
                }
                case 5: {
                    \u2603 = 6;
                    break;
                }
                case 4: {
                    \u2603 = 11;
                    break;
                }
                case 3: {
                    \u2603 = 16;
                    break;
                }
                case 2: {
                    \u2603 = 20;
                    break;
                }
                case 1: {
                    \u2603 = 24;
                    break;
                }
                case 0: {
                    \u2603 = 29;
                }
            }
            if (\u2603 > 0) {
                this.b(\u2603 + 65, \u2603 + 14 + 29 - \u2603, 185, 29 - \u2603, 12, \u2603);
            }
        }
    }
}

