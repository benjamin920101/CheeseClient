/*
 * Decompiled with CFR 0.152.
 */
public class wg
implements acy {
    private ya a;
    private wn b;
    private ada c;
    private eu d;

    public wg(wn wn2, eu eu2) {
        this.b = wn2;
        this.d = eu2;
        this.a = new ya(wn2, this);
    }

    @Override
    public wn v_() {
        return this.b;
    }

    @Override
    public void a_(wn wn2) {
    }

    @Override
    public ada b_(wn wn2) {
        return this.c;
    }

    @Override
    public void a(ada ada2) {
        this.c = ada2;
    }

    @Override
    public void a(acz acz2) {
        acz2.g();
    }

    @Override
    public void a_(zx zx2) {
    }

    @Override
    public eu f_() {
        if (this.d != null) {
            return this.d;
        }
        return new fb("entity.Villager.name", new Object[0]);
    }
}

