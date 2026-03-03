/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.realms.RealmsBridge;

public class axp
extends axu {
    private int a;
    private int f;

    @Override
    public void b() {
        this.a = 0;
        this.n.clear();
        int n2 = -16;
        \u2603 = 98;
        this.n.add(new avs(1, this.l / 2 - 100, this.m / 4 + 120 + n2, bnq.a("menu.returnToMenu", new Object[0])));
        if (!this.j.E()) {
            ((avs)this.n.get((int)0)).j = bnq.a("menu.disconnect", new Object[0]);
        }
        this.n.add(new avs(4, this.l / 2 - 100, this.m / 4 + 24 + n2, bnq.a("menu.returnToGame", new Object[0])));
        this.n.add(new avs(0, this.l / 2 - 100, this.m / 4 + 96 + n2, 98, 20, bnq.a("menu.options", new Object[0])));
        avs \u26032 = new avs(7, this.l / 2 + 2, this.m / 4 + 96 + n2, 98, 20, bnq.a("menu.shareToLan", new Object[0]));
        this.n.add(\u26032);
        this.n.add(new avs(5, this.l / 2 - 100, this.m / 4 + 48 + n2, 98, 20, bnq.a("gui.achievements", new Object[0])));
        this.n.add(new avs(6, this.l / 2 + 2, this.m / 4 + 48 + n2, 98, 20, bnq.a("gui.stats", new Object[0])));
        \u26032.l = this.j.F() && !this.j.G().b();
    }

    @Override
    protected void a(avs avs2) {
        switch (avs2.k) {
            case 0: {
                this.j.a(new axn(this, this.j.t));
                break;
            }
            case 1: {
                boolean bl2 = this.j.E();
                \u2603 = this.j.al();
                avs2.l = false;
                this.j.f.H();
                this.j.a((bdb)null);
                if (bl2) {
                    this.j.a(new aya());
                    break;
                }
                if (\u2603) {
                    RealmsBridge realmsBridge = new RealmsBridge();
                    realmsBridge.switchToRealms(new aya());
                    break;
                }
                this.j.a(new azh(new aya()));
                break;
            }
            case 4: {
                this.j.a((axu)null);
                this.j.n();
                break;
            }
            case 5: {
                this.j.a(new aye(this, this.j.h.x()));
                break;
            }
            case 6: {
                this.j.a(new ayf(this, this.j.h.x()));
                break;
            }
            case 7: {
                this.j.a(new axw(this));
            }
        }
    }

    @Override
    public void e() {
        super.e();
        ++this.f;
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.a(this.q, bnq.a("menu.game", new Object[0]), this.l / 2, 40, 0xFFFFFF);
        super.a(n2, n3, f2);
    }
}

