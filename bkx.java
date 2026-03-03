/*
 * Decompiled with CFR 0.152.
 */
public class bkx
extends bkn<bbj> {
    public bkx(bjl<?> bjl2) {
        super(bjl2);
    }

    @Override
    protected void a() {
        this.c = new bbj(0.5f);
        this.d = new bbj(1.0f);
    }

    @Override
    protected void a(bbj bbj2, int n2) {
        this.a(bbj2);
        switch (n2) {
            case 4: {
                bbj2.e.j = true;
                bbj2.f.j = true;
                break;
            }
            case 3: {
                bbj2.g.j = true;
                bbj2.h.j = true;
                bbj2.i.j = true;
                break;
            }
            case 2: {
                bbj2.g.j = true;
                bbj2.j.j = true;
                bbj2.k.j = true;
                break;
            }
            case 1: {
                bbj2.j.j = true;
                bbj2.k.j = true;
            }
        }
    }

    protected void a(bbj bbj2) {
        bbj2.a(false);
    }
}

