/*
 * Decompiled with CFR 0.152.
 */
import io.netty.buffer.Unpooled;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class ayq
extends axu {
    private static final Logger a = LogManager.getLogger();
    private avw f;
    private avw g;
    private final adc h;
    private avs i;
    private avs r;
    private avs s;
    private boolean t;

    public ayq(adc adc2) {
        this.h = adc2;
    }

    @Override
    public void e() {
        this.f.a();
    }

    @Override
    public void b() {
        Keyboard.enableRepeatEvents(true);
        this.n.clear();
        this.i = new avs(0, this.l / 2 - 4 - 150, this.m / 4 + 120 + 12, 150, 20, bnq.a("gui.done", new Object[0]));
        this.n.add(this.i);
        this.r = new avs(1, this.l / 2 + 4, this.m / 4 + 120 + 12, 150, 20, bnq.a("gui.cancel", new Object[0]));
        this.n.add(this.r);
        this.s = new avs(4, this.l / 2 + 150 - 20, 150, 20, 20, "O");
        this.n.add(this.s);
        this.f = new avw(2, this.q, this.l / 2 - 150, 50, 300, 20);
        this.f.f(Short.MAX_VALUE);
        this.f.b(true);
        this.f.a(this.h.l());
        this.g = new avw(3, this.q, this.l / 2 - 150, 150, 276, 20);
        this.g.f(Short.MAX_VALUE);
        this.g.c(false);
        this.g.a("-");
        this.t = this.h.m();
        this.a();
        this.i.l = this.f.b().trim().length() > 0;
    }

    @Override
    public void m() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void a(avs avs22) {
        avs avs22;
        if (!avs22.l) {
            return;
        }
        if (avs22.k == 1) {
            this.h.a(this.t);
            this.j.a((axu)null);
        } else if (avs22.k == 0) {
            em em2 = new em(Unpooled.buffer());
            em2.writeByte(this.h.i());
            this.h.a(em2);
            em2.a(this.f.b());
            em2.writeBoolean(this.h.m());
            this.j.u().a(new im("MC|AdvCdm", em2));
            if (!this.h.m()) {
                this.h.b((eu)null);
            }
            this.j.a((axu)null);
        } else if (avs22.k == 4) {
            this.h.a(!this.h.m());
            this.a();
        }
    }

    @Override
    protected void a(char c2, int n2) {
        this.f.a(c2, n2);
        this.g.a(c2, n2);
        boolean bl2 = this.i.l = this.f.b().trim().length() > 0;
        if (n2 == 28 || n2 == 156) {
            this.a(this.i);
        } else if (n2 == 1) {
            this.a(this.r);
        }
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        super.a(n2, n3, n4);
        this.f.a(n2, n3, n4);
        this.g.a(n2, n3, n4);
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.a(this.q, bnq.a("advMode.setCommand", new Object[0]), this.l / 2, 20, 0xFFFFFF);
        this.c(this.q, bnq.a("advMode.command", new Object[0]), this.l / 2 - 150, 37, 0xA0A0A0);
        this.f.g();
        int n4 = 75;
        \u2603 = 0;
        this.c(this.q, bnq.a("advMode.nearestPlayer", new Object[0]), this.l / 2 - 150, n4 + \u2603++ * this.q.a, 0xA0A0A0);
        this.c(this.q, bnq.a("advMode.randomPlayer", new Object[0]), this.l / 2 - 150, n4 + \u2603++ * this.q.a, 0xA0A0A0);
        this.c(this.q, bnq.a("advMode.allPlayers", new Object[0]), this.l / 2 - 150, n4 + \u2603++ * this.q.a, 0xA0A0A0);
        this.c(this.q, bnq.a("advMode.allEntities", new Object[0]), this.l / 2 - 150, n4 + \u2603++ * this.q.a, 0xA0A0A0);
        this.c(this.q, "", this.l / 2 - 150, n4 + \u2603++ * this.q.a, 0xA0A0A0);
        if (this.g.b().length() > 0) {
            this.c(this.q, bnq.a("advMode.previousOutput", new Object[0]), this.l / 2 - 150, n4 += \u2603 * this.q.a + 16, 0xA0A0A0);
            this.g.g();
        }
        super.a(n2, n3, f2);
    }

    private void a() {
        if (this.h.m()) {
            this.s.j = "O";
            if (this.h.k() != null) {
                this.g.a(this.h.k().c());
            }
        } else {
            this.s.j = "X";
            this.g.a("-");
        }
    }
}

