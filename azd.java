/*
 * Decompiled with CFR 0.152.
 */
import io.netty.buffer.Unpooled;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class azd
extends ayl {
    private static final Logger u = LogManager.getLogger();
    private static final jy v = new jy("textures/gui/container/villager.png");
    private acy w;
    private a x;
    private a y;
    private int z;
    private eu A;

    public azd(wm wm2, acy acy2, adm adm2) {
        super(new yb(wm2, acy2, adm2));
        this.w = acy2;
        this.A = acy2.f_();
    }

    @Override
    public void b() {
        super.b();
        int n2 = (this.l - this.f) / 2;
        \u2603 = (this.m - this.g) / 2;
        this.x = new a(1, n2 + 120 + 27, \u2603 + 24 - 1, true);
        this.n.add(this.x);
        this.y = new a(2, n2 + 36 - 19, \u2603 + 24 - 1, false);
        this.n.add(this.y);
        this.x.l = false;
        this.y.l = false;
    }

    @Override
    protected void b(int n2, int n3) {
        String string = this.A.c();
        this.q.a(string, this.f / 2 - this.q.a(string) / 2, 6, 0x404040);
        this.q.a(bnq.a("container.inventory", new Object[0]), 8, this.g - 96 + 2, 0x404040);
    }

    @Override
    public void e() {
        super.e();
        ada ada2 = this.w.b_(this.j.h);
        if (ada2 != null) {
            this.x.l = this.z < ada2.size() - 1;
            this.y.l = this.z > 0;
        }
    }

    @Override
    protected void a(avs avs2) {
        boolean \u26032 = false;
        if (avs2 == this.x) {
            ++this.z;
            Object object = this.w.b_(this.j.h);
            if (object != null && this.z >= ((ArrayList)object).size()) {
                this.z = ((ArrayList)object).size() - 1;
            }
            \u26032 = true;
        } else if (avs2 == this.y) {
            --this.z;
            if (this.z < 0) {
                this.z = 0;
            }
            \u26032 = true;
        }
        if (\u26032) {
            ((yb)this.h).d(this.z);
            object = new em(Unpooled.buffer());
            ((em)object).writeInt(this.z);
            this.j.u().a(new im("MC|TrSel", (em)object));
        }
    }

    @Override
    protected void a(float f2, int n2, int n3) {
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(v);
        \u2603 = (this.l - this.f) / 2;
        \u2603 = (this.m - this.g) / 2;
        this.b(\u2603, \u2603, 0, 0, this.f, this.g);
        ada ada2 = this.w.b_(this.j.h);
        if (ada2 != null && !ada2.isEmpty()) {
            int n4 = this.z;
            if (n4 < 0 || n4 >= ada2.size()) {
                return;
            }
            acz \u26032 = (acz)ada2.get(n4);
            if (\u26032.h()) {
                this.j.P().a(v);
                bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
                bfl.f();
                this.b(this.i + 83, this.r + 21, 212, 0, 28, 21);
                this.b(this.i + 83, this.r + 51, 212, 0, 28, 21);
            }
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        super.a(n2, n3, f2);
        ada ada2 = this.w.b_(this.j.h);
        if (ada2 != null && !ada2.isEmpty()) {
            int n4 = (this.l - this.f) / 2;
            \u2603 = (this.m - this.g) / 2;
            \u2603 = this.z;
            acz \u26032 = (acz)ada2.get(\u2603);
            zx \u26033 = \u26032.a();
            zx \u26034 = \u26032.b();
            zx \u26035 = \u26032.d();
            bfl.E();
            avc.c();
            bfl.f();
            bfl.B();
            bfl.g();
            bfl.e();
            this.k.a = 100.0f;
            this.k.b(\u26033, n4 + 36, \u2603 + 24);
            this.k.a(this.q, \u26033, n4 + 36, \u2603 + 24);
            if (\u26034 != null) {
                this.k.b(\u26034, n4 + 62, \u2603 + 24);
                this.k.a(this.q, \u26034, n4 + 62, \u2603 + 24);
            }
            this.k.b(\u26035, n4 + 120, \u2603 + 24);
            this.k.a(this.q, \u26035, n4 + 120, \u2603 + 24);
            this.k.a = 0.0f;
            bfl.f();
            if (this.c(36, 24, 16, 16, n2, n3) && \u26033 != null) {
                this.a(\u26033, n2, n3);
            } else if (\u26034 != null && this.c(62, 24, 16, 16, n2, n3) && \u26034 != null) {
                this.a(\u26034, n2, n3);
            } else if (\u26035 != null && this.c(120, 24, 16, 16, n2, n3) && \u26035 != null) {
                this.a(\u26035, n2, n3);
            } else if (\u26032.h() && (this.c(83, 21, 28, 21, n2, n3) || this.c(83, 51, 28, 21, n2, n3))) {
                this.a(bnq.a("merchant.deprecated", new Object[0]), n2, n3);
            }
            bfl.F();
            bfl.e();
            bfl.j();
            avc.b();
        }
    }

    public acy a() {
        return this.w;
    }

    static class a
    extends avs {
        private final boolean o;

        public a(int n2, int n3, int n4, boolean bl2) {
            super(n2, n3, n4, 12, 19, "");
            this.o = bl2;
        }

        @Override
        public void a(ave ave2, int n2, int n3) {
            if (!this.m) {
                return;
            }
            ave2.P().a(v);
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            boolean bl2 = n2 >= this.h && n3 >= this.i && n2 < this.h + this.f && n3 < this.i + this.g;
            int \u26032 = 0;
            int \u26033 = 176;
            if (!this.l) {
                \u26033 += this.f * 2;
            } else if (bl2) {
                \u26033 += this.f;
            }
            if (!this.o) {
                \u26032 += this.g;
            }
            this.b(this.h, this.i, \u26033, \u26032, this.f, this.g);
        }
    }
}

