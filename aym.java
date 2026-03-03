/*
 * Decompiled with CFR 0.152.
 */
import io.netty.buffer.Unpooled;
import java.util.List;
import org.lwjgl.input.Keyboard;

public class aym
extends ayl
implements xn {
    private static final jy u = new jy("textures/gui/container/anvil.png");
    private xk v;
    private avw w;
    private wm x;

    public aym(wm wm2, adm adm2) {
        super(new xk(wm2, adm2, ave.A().h));
        this.x = wm2;
        this.v = (xk)this.h;
    }

    @Override
    public void b() {
        super.b();
        Keyboard.enableRepeatEvents(true);
        int n2 = (this.l - this.f) / 2;
        \u2603 = (this.m - this.g) / 2;
        this.w = new avw(0, this.q, n2 + 62, \u2603 + 24, 103, 12);
        this.w.g(-1);
        this.w.h(-1);
        this.w.a(false);
        this.w.f(30);
        this.h.b(this);
        this.h.a(this);
    }

    @Override
    public void m() {
        super.m();
        Keyboard.enableRepeatEvents(false);
        this.h.b(this);
    }

    @Override
    protected void b(int n2, int n3) {
        bfl.f();
        bfl.k();
        this.q.a(bnq.a("container.repair", new Object[0]), 60, 6, 0x404040);
        if (this.v.a > 0) {
            int n4;
            n4 = 8453920;
            boolean bl2 = true;
            String \u26032 = bnq.a("container.repair.cost", this.v.a);
            if (this.v.a >= 40 && !this.j.h.bA.d) {
                \u26032 = bnq.a("container.repair.expensive", new Object[0]);
                n4 = 0xFF6060;
            } else if (!this.v.a(2).e()) {
                bl2 = false;
            } else if (!this.v.a(2).a(this.x.d)) {
                n4 = 0xFF6060;
            }
            if (bl2) {
                int n5 = 0xFF000000 | (n4 & 0xFCFCFC) >> 2 | n4 & 0xFF000000;
                \u2603 = this.f - 8 - this.q.a(\u26032);
                \u2603 = 67;
                if (this.q.a()) {
                    aym.a(\u2603 - 3, \u2603 - 2, this.f - 7, \u2603 + 10, -16777216);
                    aym.a(\u2603 - 2, \u2603 - 1, this.f - 8, \u2603 + 9, -12895429);
                } else {
                    this.q.a(\u26032, \u2603, \u2603 + 1, n5);
                    this.q.a(\u26032, \u2603 + 1, \u2603, n5);
                    this.q.a(\u26032, \u2603 + 1, \u2603 + 1, n5);
                }
                this.q.a(\u26032, \u2603, \u2603, n4);
            }
        }
        bfl.e();
    }

    @Override
    protected void a(char c2, int n2) {
        if (this.w.a(c2, n2)) {
            this.a();
        } else {
            super.a(c2, n2);
        }
    }

    private void a() {
        String string = this.w.b();
        yg \u26032 = this.v.a(0);
        if (\u26032 != null && \u26032.e() && !\u26032.d().s() && string.equals(\u26032.d().q())) {
            string = "";
        }
        this.v.a(string);
        this.j.h.a.a(new im("MC|ItemName", new em(Unpooled.buffer()).a(string)));
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        super.a(n2, n3, n4);
        this.w.a(n2, n3, n4);
    }

    @Override
    public void a(int n2, int n3, float f2) {
        super.a(n2, n3, f2);
        bfl.f();
        bfl.k();
        this.w.g();
    }

    @Override
    protected void a(float f2, int n2, int n3) {
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(u);
        \u2603 = (this.l - this.f) / 2;
        \u2603 = (this.m - this.g) / 2;
        this.b(\u2603, \u2603, 0, 0, this.f, this.g);
        this.b(\u2603 + 59, \u2603 + 20, 0, this.g + (this.v.a(0).e() ? 0 : 16), 110, 16);
        if ((this.v.a(0).e() || this.v.a(1).e()) && !this.v.a(2).e()) {
            this.b(\u2603 + 99, \u2603 + 45, this.f, 0, 28, 21);
        }
    }

    @Override
    public void a(xi xi2, List<zx> list) {
        this.a(xi2, 0, xi2.a(0).d());
    }

    @Override
    public void a(xi xi2, int n2, zx zx2) {
        if (n2 == 0) {
            this.w.a(zx2 == null ? "" : zx2.q());
            this.w.c(zx2 != null);
            if (zx2 != null) {
                this.a();
            }
        }
    }

    @Override
    public void a(xi xi2, int n2, int n3) {
    }

    @Override
    public void a(xi xi2, og og2) {
    }
}

