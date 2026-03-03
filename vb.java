/*
 * Decompiled with CFR 0.152.
 */
public class vb
extends vd {
    public vb(adm adm2) {
        super(adm2);
    }

    public vb(adm adm2, double d2, double d3, double d4) {
        super(adm2, d2, d3, d4);
    }

    @Override
    public void a(ow ow2) {
        super.a(ow2);
        if (this.o.Q().b("doEntityDrops")) {
            this.a(zw.a(afi.ae), 1, 0.0f);
        }
    }

    @Override
    public int o_() {
        return 27;
    }

    @Override
    public va.a s() {
        return va.a.b;
    }

    @Override
    public alz u() {
        return afi.ae.Q().a(afs.a, cq.c);
    }

    @Override
    public int w() {
        return 8;
    }

    @Override
    public String k() {
        return "minecraft:chest";
    }

    @Override
    public xi a(wm wm2, wn wn2) {
        return new xo(wm2, this, wn2);
    }
}

