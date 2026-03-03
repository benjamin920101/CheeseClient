/*
 * Decompiled with CFR 0.152.
 */
import java.util.UUID;

public class vw
extends we {
    private static final UUID b = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
    private static final qd c = new qd(b, "Attacking speed boost", 0.05, 0).a(false);
    private int bm;
    private int bn;
    private UUID bo;

    public vw(adm adm2) {
        super(adm2);
        this.ab = true;
    }

    @Override
    public void b(pr pr2) {
        super.b(pr2);
        if (pr2 != null) {
            this.bo = pr2.aK();
        }
    }

    @Override
    protected void n() {
        this.bi.a(1, new b(this));
        this.bi.a(2, new a(this));
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(a).a(0.0);
        this.a(vy.d).a(0.23f);
        this.a(vy.e).a(5.0);
    }

    @Override
    public void t_() {
        super.t_();
    }

    @Override
    protected void E() {
        qc qc2 = this.a(vy.d);
        if (this.cm()) {
            if (!this.j_() && !qc2.a(c)) {
                qc2.b(c);
            }
            --this.bm;
        } else if (qc2.a(c)) {
            qc2.c(c);
        }
        if (this.bn > 0 && --this.bn == 0) {
            this.a("mob.zombiepig.zpigangry", this.bB() * 2.0f, ((this.V.nextFloat() - this.V.nextFloat()) * 0.2f + 1.0f) * 1.8f);
        }
        if (this.bm > 0 && this.bo != null && this.bd() == null) {
            wn wn2 = this.o.b(this.bo);
            this.b((pr)wn2);
            this.aN = wn2;
            this.aO = this.be();
        }
        super.E();
    }

    @Override
    public boolean bR() {
        return this.o.aa() != oj.a;
    }

    @Override
    public boolean bS() {
        return this.o.a(this.aR(), (pk)this) && this.o.a((pk)this, this.aR()).isEmpty() && !this.o.d(this.aR());
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("Anger", (short)this.bm);
        if (this.bo != null) {
            dn2.a("HurtBy", this.bo.toString());
        } else {
            dn2.a("HurtBy", "");
        }
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.bm = dn2.e("Anger");
        String string = dn2.j("HurtBy");
        if (string.length() > 0) {
            this.bo = UUID.fromString(string);
            wn wn2 = this.o.b(this.bo);
            this.b((pr)wn2);
            if (wn2 != null) {
                this.aN = wn2;
                this.aO = this.be();
            }
        }
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        pk pk2 = ow2.j();
        if (pk2 instanceof wn) {
            this.b(pk2);
        }
        return super.a(ow2, f2);
    }

    private void b(pk pk2) {
        this.bm = 400 + this.V.nextInt(400);
        this.bn = this.V.nextInt(40);
        if (pk2 instanceof pr) {
            this.b((pr)pk2);
        }
    }

    public boolean cm() {
        return this.bm > 0;
    }

    @Override
    protected String z() {
        return "mob.zombiepig.zpig";
    }

    @Override
    protected String bo() {
        return "mob.zombiepig.zpighurt";
    }

    @Override
    protected String bp() {
        return "mob.zombiepig.zpigdeath";
    }

    @Override
    protected void b(boolean bl2, int n2) {
        \u2603 = this.V.nextInt(2 + n2);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            this.a(zy.bt, 1);
        }
        \u2603 = this.V.nextInt(2 + n2);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            this.a(zy.bx, 1);
        }
    }

    @Override
    public boolean a(wn wn2) {
        return false;
    }

    @Override
    protected void bq() {
        this.a(zy.k, 1);
    }

    @Override
    protected void a(ok ok2) {
        this.c(0, new zx(zy.B));
    }

    @Override
    public pu a(ok ok2, pu pu2) {
        super.a(ok2, pu2);
        this.m(false);
        return pu2;
    }

    static class a
    extends sp<wn> {
        public a(vw vw2) {
            super((py)vw2, wn.class, true);
        }

        @Override
        public boolean a() {
            return ((vw)this.e).cm() && super.a();
        }
    }

    static class b
    extends sm {
        public b(vw vw2) {
            super((py)vw2, true, new Class[0]);
        }

        @Override
        protected void a(py py2, pr pr2) {
            super.a(py2, pr2);
            if (py2 instanceof vw) {
                ((vw)py2).b(pr2);
            }
        }
    }
}

