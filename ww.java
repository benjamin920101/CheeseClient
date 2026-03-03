/*
 * Decompiled with CFR 0.152.
 */
public class ww
extends ws {
    public ww(adm adm2) {
        super(adm2);
        this.a(0.3125f, 0.3125f);
    }

    public ww(adm adm2, pr pr2, double d2, double d3, double d4) {
        super(adm2, pr2, d2, d3, d4);
        this.a(0.3125f, 0.3125f);
    }

    public ww(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        super(adm2, d2, d3, d4, d5, d6, d7);
        this.a(0.3125f, 0.3125f);
    }

    @Override
    protected void a(auh auh2) {
        if (!this.o.D) {
            if (auh2.d != null) {
                boolean bl2 = auh2.d.a(ow.a(this, (pk)this.a), 5.0f);
                if (bl2) {
                    this.a(this.a, auh2.d);
                    if (!auh2.d.T()) {
                        auh2.d.e(5);
                    }
                }
            } else {
                boolean bl3 = true;
                if (this.a != null && this.a instanceof ps) {
                    bl3 = this.o.Q().b("mobGriefing");
                }
                if (bl3 && this.o.d(\u2603 = auh2.a().a(auh2.b))) {
                    this.o.a(\u2603, afi.ab.Q());
                }
            }
            this.J();
        }
    }

    @Override
    public boolean ad() {
        return false;
    }

    @Override
    public boolean a(ow ow2, float f2) {
        return false;
    }
}

