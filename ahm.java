/*
 * Decompiled with CFR 0.152.
 */
public class ahm
extends ajg {
    public ahm() {
        super(arm.b, arn.t);
        this.j(this.M.b().a(N, cq.a.b));
        this.a(yz.b);
    }

    @Override
    public alz a(int n2) {
        cq.a a2 = cq.a.b;
        int \u26032 = n2 & 0xC;
        if (\u26032 == 4) {
            a2 = cq.a.a;
        } else if (\u26032 == 8) {
            a2 = cq.a.c;
        }
        return this.Q().a(N, a2);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        cq.a \u26032 = (cq.a)alz2.b(N);
        if (\u26032 == cq.a.a) {
            n2 |= 4;
        } else if (\u26032 == cq.a.c) {
            n2 |= 8;
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, N);
    }

    @Override
    protected zx i(alz alz2) {
        return new zx(zw.a(this), 1, 0);
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return super.a(adm2, cj2, cq2, f2, f3, f4, n2, pr2).a(N, cq2.k());
    }
}

