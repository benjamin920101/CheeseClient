/*
 * Decompiled with CFR 0.152.
 */
import io.netty.buffer.ByteBuf;

public class vc
extends va {
    private final adc a = new adc(){

        @Override
        public void h() {
            vc.this.H().b(23, this.l());
            vc.this.H().b(24, eu.a.a(this.k()));
        }

        @Override
        public int i() {
            return 1;
        }

        @Override
        public void a(ByteBuf byteBuf) {
            byteBuf.writeInt(vc.this.F());
        }

        @Override
        public cj c() {
            return new cj(vc.this.s, vc.this.t + 0.5, vc.this.u);
        }

        @Override
        public aui d() {
            return new aui(vc.this.s, vc.this.t, vc.this.u);
        }

        @Override
        public adm e() {
            return vc.this.o;
        }

        @Override
        public pk f() {
            return vc.this;
        }
    };
    private int b = 0;

    public vc(adm adm2) {
        super(adm2);
    }

    public vc(adm adm2, double d2, double d3, double d4) {
        super(adm2, d2, d3, d4);
    }

    @Override
    protected void h() {
        super.h();
        this.H().a(23, "");
        this.H().a(24, "");
    }

    @Override
    protected void a(dn dn2) {
        super.a(dn2);
        this.a.b(dn2);
        this.H().b(23, this.j().l());
        this.H().b(24, eu.a.a(this.j().k()));
    }

    @Override
    protected void b(dn dn2) {
        super.b(dn2);
        this.a.a(dn2);
    }

    @Override
    public va.a s() {
        return va.a.g;
    }

    @Override
    public alz u() {
        return afi.bX.Q();
    }

    public adc j() {
        return this.a;
    }

    @Override
    public void a(int n2, int n3, int n4, boolean bl2) {
        if (bl2 && this.W - this.b >= 4) {
            this.j().a(this.o);
            this.b = this.W;
        }
    }

    @Override
    public boolean e(wn wn2) {
        this.a.a(wn2);
        return false;
    }

    @Override
    public void i(int n2) {
        super.i(n2);
        if (n2 == 24) {
            try {
                this.a.b(eu.a.a(this.H().e(24)));
            }
            catch (Throwable throwable) {}
        } else if (n2 == 23) {
            this.a.a(this.H().e(23));
        }
    }
}

