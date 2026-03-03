/*
 * Decompiled with CFR 0.152.
 */
import org.lwjgl.input.Keyboard;

public class axg
extends axu {
    private final axu a;
    private final bde f;
    private avw g;

    public axg(axu axu2, bde bde2) {
        this.a = axu2;
        this.f = bde2;
    }

    @Override
    public void e() {
        this.g.a();
    }

    @Override
    public void b() {
        Keyboard.enableRepeatEvents(true);
        this.n.clear();
        this.n.add(new avs(0, this.l / 2 - 100, this.m / 4 + 96 + 12, bnq.a("selectServer.select", new Object[0])));
        this.n.add(new avs(1, this.l / 2 - 100, this.m / 4 + 120 + 12, bnq.a("gui.cancel", new Object[0])));
        this.g = new avw(2, this.q, this.l / 2 - 100, 116, 200, 20);
        this.g.f(128);
        this.g.b(true);
        this.g.a(this.j.t.aE);
        ((avs)this.n.get((int)0)).l = this.g.b().length() > 0 && this.g.b().split(":").length > 0;
    }

    @Override
    public void m() {
        Keyboard.enableRepeatEvents(false);
        this.j.t.aE = this.g.b();
        this.j.t.b();
    }

    @Override
    protected void a(avs avs2) {
        if (!avs2.l) {
            return;
        }
        if (avs2.k == 1) {
            this.a.a(false, 0);
        } else if (avs2.k == 0) {
            this.f.b = this.g.b();
            this.a.a(true, 0);
        }
    }

    @Override
    protected void a(char c2, int n2) {
        if (this.g.a(c2, n2)) {
            ((avs)this.n.get((int)0)).l = this.g.b().length() > 0 && this.g.b().split(":").length > 0;
        } else if (n2 == 28 || n2 == 156) {
            this.a((avs)this.n.get(0));
        }
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        super.a(n2, n3, n4);
        this.g.a(n2, n3, n4);
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.a(this.q, bnq.a("selectServer.direct", new Object[0]), this.l / 2, 20, 0xFFFFFF);
        this.c(this.q, bnq.a("addServer.enterIp", new Object[0]), this.l / 2 - 100, 100, 0xA0A0A0);
        this.g.g();
        super.a(n2, n3, f2);
    }
}

