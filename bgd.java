/*
 * Decompiled with CFR 0.152.
 */
public class bgd
implements bnj {
    private bgc a;
    private final avh b;
    private final bgf c = new bgf();
    private final bgb d = new bgb();
    private final bge e = new bge();

    public bgd(bgc bgc2, avh avh2) {
        this.a = bgc2;
        this.b = avh2;
    }

    public bgc a() {
        return this.a;
    }

    public void a(alz \u260332, cj cj2, bmi bmi2, adq adq2) {
        afh afh2 = \u260332.c();
        int \u26032 = afh2.b();
        if (\u26032 != 3) {
            return;
        }
        alz \u260332 = afh2.a(\u260332, adq2, cj2);
        boq boq2 = this.a.b(\u260332);
        \u2603 = new bow.a(boq2, bmi2).b();
        this.c.a(adq2, \u2603, \u260332, cj2, bfx.a().c());
    }

    public boolean a(alz alz2, cj cj2, adq adq22, bfd bfd2) {
        try {
            int n2 = alz2.c().b();
            if (n2 == -1) {
                return false;
            }
            switch (n2) {
                case 3: {
                    boq boq2 = this.a(alz2, adq22, cj2);
                    return this.c.a(adq22, boq2, alz2, cj2, bfd2);
                }
                case 2: {
                    return false;
                }
                case 1: {
                    adq adq22;
                    return this.e.a(adq22, alz2, cj2, bfd2);
                }
            }
        }
        catch (Throwable throwable) {
            b b2 = b.a(throwable, "Tesselating block in world");
            c \u26032 = b2.a("Block being tesselated");
            c.a(\u26032, cj2, alz2.c(), alz2.c().c(alz2));
            throw new e(b2);
        }
        return false;
    }

    public bgf b() {
        return this.c;
    }

    private boq a(alz alz2, cj cj2) {
        boq boq2 = this.a.b(alz2);
        if (cj2 != null && this.b.v && boq2 instanceof box) {
            boq2 = ((box)boq2).a(ns.a(cj2));
        }
        return boq2;
    }

    public boq a(alz alz22, adq adq2, cj cj2) {
        alz alz22;
        afh afh2 = alz22.c();
        if (adq2.G() != adr.g) {
            try {
                alz22 = afh2.a(alz22, adq2, cj2);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        boq \u26032 = this.a.b(alz22);
        if (cj2 != null && this.b.v && \u26032 instanceof box) {
            \u26032 = ((box)\u26032).a(ns.a(cj2));
        }
        return \u26032;
    }

    public void a(alz alz22, float f2) {
        int n2 = alz22.c().b();
        if (n2 == -1) {
            return;
        }
        switch (n2) {
            case 3: {
                boq boq2 = this.a(alz22, null);
                this.c.a(boq2, alz22, f2, true);
                break;
            }
            case 2: {
                alz alz22;
                this.d.a(alz22.c(), f2);
                break;
            }
        }
    }

    public boolean a(afh afh2, int n2) {
        if (afh2 == null) {
            return false;
        }
        \u2603 = afh2.b();
        if (\u2603 == 3) {
            return false;
        }
        return \u2603 == 2;
    }

    @Override
    public void a(bni bni2) {
        this.e.a();
    }
}

