/*
 * Decompiled with CFR 0.152.
 */
import org.lwjgl.input.Keyboard;

public class aze
extends axu {
    private aln a;
    private int f;
    private int g;
    private avs h;

    public aze(aln aln2) {
        this.a = aln2;
    }

    @Override
    public void b() {
        this.n.clear();
        Keyboard.enableRepeatEvents(true);
        this.h = new avs(0, this.l / 2 - 100, this.m / 4 + 120, bnq.a("gui.done", new Object[0]));
        this.n.add(this.h);
        this.a.a(false);
    }

    @Override
    public void m() {
        Keyboard.enableRepeatEvents(false);
        bcy bcy2 = this.j.u();
        if (bcy2 != null) {
            bcy2.a(new ix(this.a.v(), this.a.a));
        }
        this.a.a(true);
    }

    @Override
    public void e() {
        ++this.f;
    }

    @Override
    protected void a(avs avs2) {
        if (!avs2.l) {
            return;
        }
        if (avs2.k == 0) {
            this.a.p_();
            this.j.a((axu)null);
        }
    }

    @Override
    protected void a(char c2, int n2) {
        if (n2 == 200) {
            this.g = this.g - 1 & 3;
        }
        if (n2 == 208 || n2 == 28 || n2 == 156) {
            this.g = this.g + 1 & 3;
        }
        String string = this.a.a[this.g].c();
        if (n2 == 14 && string.length() > 0) {
            string = string.substring(0, string.length() - 1);
        }
        if (f.a(c2) && this.q.a(string + c2) <= 90) {
            string = string + c2;
        }
        this.a.a[this.g] = new fa(string);
        if (n2 == 1) {
            this.a(this.h);
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.a(this.q, bnq.a("sign.edit", new Object[0]), this.l / 2, 40, 0xFFFFFF);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.E();
        bfl.b(this.l / 2, 0.0f, 50.0f);
        \u2603 = 93.75f;
        bfl.a(-\u2603, -\u2603, -\u2603);
        bfl.b(180.0f, 0.0f, 1.0f, 0.0f);
        afh afh2 = this.a.w();
        if (afh2 == afi.an) {
            float f3 = (float)(this.a.u() * 360) / 16.0f;
            bfl.b(f3, 0.0f, 1.0f, 0.0f);
            bfl.b(0.0f, -1.0625f, 0.0f);
        } else {
            int n4 = this.a.u();
            float \u26032 = 0.0f;
            if (n4 == 2) {
                \u26032 = 180.0f;
            }
            if (n4 == 4) {
                \u26032 = 90.0f;
            }
            if (n4 == 5) {
                \u26032 = -90.0f;
            }
            bfl.b(\u26032, 0.0f, 1.0f, 0.0f);
            bfl.b(0.0f, -1.0625f, 0.0f);
        }
        if (this.f / 6 % 2 == 0) {
            this.a.f = this.g;
        }
        bhc.a.a(this.a, -0.5, -0.75, -0.5, 0.0f);
        this.a.f = -1;
        bfl.F();
        super.a(n2, n3, f2);
    }
}

