/*
 * Decompiled with CFR 0.152.
 */
public class axe
extends axu
implements awx {
    private int a;
    private boolean f = false;

    @Override
    public void b() {
        this.n.clear();
        if (this.j.f.P().t()) {
            if (this.j.E()) {
                this.n.add(new avs(1, this.l / 2 - 100, this.m / 4 + 96, bnq.a("deathScreen.deleteWorld", new Object[0])));
            } else {
                this.n.add(new avs(1, this.l / 2 - 100, this.m / 4 + 96, bnq.a("deathScreen.leaveServer", new Object[0])));
            }
        } else {
            this.n.add(new avs(0, this.l / 2 - 100, this.m / 4 + 72, bnq.a("deathScreen.respawn", new Object[0])));
            this.n.add(new avs(1, this.l / 2 - 100, this.m / 4 + 96, bnq.a("deathScreen.titleScreen", new Object[0])));
            if (this.j.L() == null) {
                ((avs)this.n.get((int)1)).l = false;
            }
        }
        for (avs avs2 : this.n) {
            avs2.l = false;
        }
    }

    @Override
    protected void a(char c2, int n2) {
    }

    @Override
    protected void a(avs avs2) {
        switch (avs2.k) {
            case 0: {
                this.j.h.cb();
                this.j.a((axu)null);
                break;
            }
            case 1: {
                if (this.j.f.P().t()) {
                    this.j.a(new aya());
                    break;
                }
                awy awy2 = new awy(this, bnq.a("deathScreen.quit.confirm", new Object[0]), "", bnq.a("deathScreen.titleScreen", new Object[0]), bnq.a("deathScreen.respawn", new Object[0]), 0);
                this.j.a(awy2);
                awy2.b(20);
            }
        }
    }

    @Override
    public void a(boolean bl2, int n2) {
        if (bl2) {
            this.j.f.H();
            this.j.a((bdb)null);
            this.j.a(new aya());
        } else {
            this.j.h.cb();
            this.j.a((axu)null);
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.a(0, 0, this.l, this.m, 0x60500000, -1602211792);
        bfl.E();
        bfl.a(2.0f, 2.0f, 2.0f);
        boolean bl2 = this.j.f.P().t();
        String \u26032 = bl2 ? bnq.a("deathScreen.title.hardcore", new Object[0]) : bnq.a("deathScreen.title", new Object[0]);
        this.a(this.q, \u26032, this.l / 2 / 2, 30, 0xFFFFFF);
        bfl.F();
        if (bl2) {
            this.a(this.q, bnq.a("deathScreen.hardcoreInfo", new Object[0]), this.l / 2, 144, 0xFFFFFF);
        }
        this.a(this.q, bnq.a("deathScreen.score", new Object[0]) + ": " + (Object)((Object)a.o) + this.j.h.bX(), this.l / 2, 100, 0xFFFFFF);
        super.a(n2, n3, f2);
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public void e() {
        super.e();
        ++this.a;
        if (this.a == 20) {
            for (avs avs2 : this.n) {
                avs2.l = true;
            }
        }
    }
}

