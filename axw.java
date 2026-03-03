/*
 * Decompiled with CFR 0.152.
 */
public class axw
extends axu {
    private final axu a;
    private avs f;
    private avs g;
    private String h = "survival";
    private boolean i;

    public axw(axu axu2) {
        this.a = axu2;
    }

    @Override
    public void b() {
        this.n.clear();
        this.n.add(new avs(101, this.l / 2 - 155, this.m - 28, 150, 20, bnq.a("lanServer.start", new Object[0])));
        this.n.add(new avs(102, this.l / 2 + 5, this.m - 28, 150, 20, bnq.a("gui.cancel", new Object[0])));
        this.g = new avs(104, this.l / 2 - 155, 100, 150, 20, bnq.a("selectWorld.gameMode", new Object[0]));
        this.n.add(this.g);
        this.f = new avs(103, this.l / 2 + 5, 100, 150, 20, bnq.a("selectWorld.allowCommands", new Object[0]));
        this.n.add(this.f);
        this.a();
    }

    private void a() {
        this.g.j = bnq.a("selectWorld.gameMode", new Object[0]) + " " + bnq.a("selectWorld.gameMode." + this.h, new Object[0]);
        this.f.j = bnq.a("selectWorld.allowCommands", new Object[0]) + " ";
        this.f.j = this.i ? this.f.j + bnq.a("options.on", new Object[0]) : this.f.j + bnq.a("options.off", new Object[0]);
    }

    @Override
    protected void a(avs avs2) {
        if (avs2.k == 102) {
            this.j.a(this.a);
        } else if (avs2.k == 104) {
            this.h = this.h.equals("spectator") ? "creative" : (this.h.equals("creative") ? "adventure" : (this.h.equals("adventure") ? "survival" : "spectator"));
            this.a();
        } else if (avs2.k == 103) {
            this.i = !this.i;
            this.a();
        } else if (avs2.k == 101) {
            this.j.a((axu)null);
            String string = this.j.G().a(adp.a.a(this.h), this.i);
            es \u26032 = string != null ? new fb("commands.publish.started", string) : new fa("commands.publish.failed");
            this.j.q.d().a(\u26032);
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.a(this.q, bnq.a("lanServer.title", new Object[0]), this.l / 2, 50, 0xFFFFFF);
        this.a(this.q, bnq.a("lanServer.otherPlayers", new Object[0]), this.l / 2, 82, 0xFFFFFF);
        super.a(n2, n3, f2);
    }
}

