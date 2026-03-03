/*
 * Decompiled with CFR 0.152.
 */
public class pa
extends pe {
    protected pa(int n2, jy jy2, boolean bl2, int n3) {
        super(n2, jy2, bl2, n3);
    }

    @Override
    public void a(pr pr2, qf qf2, int n2) {
        pr2.m(pr2.bN() - (float)(4 * (n2 + 1)));
        super.a(pr2, qf2, n2);
    }

    @Override
    public void b(pr pr2, qf qf2, int n2) {
        pr2.m(pr2.bN() + (float)(4 * (n2 + 1)));
        super.b(pr2, qf2, n2);
    }
}

