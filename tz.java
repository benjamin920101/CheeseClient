/*
 * Decompiled with CFR 0.152.
 */
public abstract class tz
extends ps
implements pi {
    public tz(adm adm2) {
        super(adm2);
    }

    @Override
    public boolean aY() {
        return true;
    }

    @Override
    public boolean bR() {
        return true;
    }

    @Override
    public boolean bS() {
        return this.o.a(this.aR(), (pk)this);
    }

    @Override
    public int w() {
        return 120;
    }

    @Override
    protected boolean C() {
        return true;
    }

    @Override
    protected int b(wn wn2) {
        return 1 + this.o.s.nextInt(3);
    }

    @Override
    public void K() {
        int n2 = this.az();
        super.K();
        if (this.ai() && !this.V()) {
            this.h(--n2);
            if (this.az() == -20) {
                this.h(0);
                this.a(ow.f, 2.0f);
            }
        } else {
            this.h(300);
        }
    }

    @Override
    public boolean aL() {
        return false;
    }
}

