/*
 * Decompiled with CFR 0.152.
 */
public abstract class alk
extends akw
implements ol,
oo {
    private on a = on.a;

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.a = on.b(dn2);
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        if (this.a != null) {
            this.a.a(dn2);
        }
    }

    @Override
    public boolean r_() {
        return this.a != null && !this.a.a();
    }

    @Override
    public on i() {
        return this.a;
    }

    @Override
    public void a(on on2) {
        this.a = on2;
    }

    @Override
    public eu f_() {
        if (this.l_()) {
            return new fa(this.e_());
        }
        return new fb(this.e_(), new Object[0]);
    }
}

