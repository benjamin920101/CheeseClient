/*
 * Decompiled with CFR 0.152.
 */
import org.lwjgl.input.Keyboard;

public class axt
extends axu {
    private axu a;
    private avw f;
    private final String g;

    public axt(axu axu2, String string) {
        this.a = axu2;
        this.g = string;
    }

    @Override
    public void e() {
        this.f.a();
    }

    @Override
    public void b() {
        Keyboard.enableRepeatEvents(true);
        this.n.clear();
        this.n.add(new avs(0, this.l / 2 - 100, this.m / 4 + 96 + 12, bnq.a("selectWorld.renameButton", new Object[0])));
        this.n.add(new avs(1, this.l / 2 - 100, this.m / 4 + 120 + 12, bnq.a("gui.cancel", new Object[0])));
        atr atr2 = this.j.f();
        ato \u26032 = atr2.c(this.g);
        String \u26033 = \u26032.k();
        this.f = new avw(2, this.q, this.l / 2 - 100, 60, 200, 20);
        this.f.b(true);
        this.f.a(\u26033);
    }

    @Override
    public void m() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void a(avs avs2) {
        if (!avs2.l) {
            return;
        }
        if (avs2.k == 1) {
            this.j.a(this.a);
        } else if (avs2.k == 0) {
            atr atr2 = this.j.f();
            atr2.a(this.g, this.f.b().trim());
            this.j.a(this.a);
        }
    }

    @Override
    protected void a(char c2, int n2) {
        this.f.a(c2, n2);
        boolean bl2 = ((avs)this.n.get((int)0)).l = this.f.b().trim().length() > 0;
        if (n2 == 28 || n2 == 156) {
            this.a((avs)this.n.get(0));
        }
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        super.a(n2, n3, n4);
        this.f.a(n2, n3, n4);
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.a(this.q, bnq.a("selectWorld.renameTitle", new Object[0]), this.l / 2, 20, 0xFFFFFF);
        this.c(this.q, bnq.a("selectWorld.enterName", new Object[0]), this.l / 2 - 100, 47, 0xA0A0A0);
        this.f.g();
        super.a(n2, n3, f2);
    }
}

