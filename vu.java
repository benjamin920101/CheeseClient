/*
 * Decompiled with CFR 0.152.
 */
public class vu
extends wb {
    public vu(adm adm2) {
        super(adm2);
        this.ab = true;
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.d).a(0.2f);
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
    public int br() {
        return this.cm() * 3;
    }

    @Override
    public int b(float f2) {
        return 0xF000F0;
    }

    @Override
    public float c(float f2) {
        return 1.0f;
    }

    @Override
    protected cy n() {
        return cy.A;
    }

    @Override
    protected wb cf() {
        return new vu(this.o);
    }

    @Override
    protected zw A() {
        return zy.bE;
    }

    @Override
    protected void b(boolean bl2, int n2) {
        zw zw2 = this.A();
        if (zw2 != null && this.cm() > 1) {
            int n3 = this.V.nextInt(4) - 2;
            if (n2 > 0) {
                n3 += this.V.nextInt(n2 + 1);
            }
            for (\u2603 = 0; \u2603 < n3; ++\u2603) {
                this.a(zw2, 1);
            }
        }
    }

    @Override
    public boolean at() {
        return false;
    }

    @Override
    protected int cg() {
        return super.cg() * 4;
    }

    @Override
    protected void ch() {
        this.a *= 0.9f;
    }

    @Override
    protected void bF() {
        this.w = 0.42f + (float)this.cm() * 0.1f;
        this.ai = true;
    }

    @Override
    protected void bH() {
        this.w = 0.22f + (float)this.cm() * 0.05f;
        this.ai = true;
    }

    @Override
    public void e(float f2, float f3) {
    }

    @Override
    protected boolean ci() {
        return true;
    }

    @Override
    protected int cj() {
        return super.cj() + 2;
    }

    @Override
    protected String ck() {
        if (this.cm() > 1) {
            return "mob.magmacube.big";
        }
        return "mob.magmacube.small";
    }

    @Override
    protected boolean cl() {
        return true;
    }
}

