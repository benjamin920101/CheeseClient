/*
 * Decompiled with CFR 0.152.
 */
public class pb
extends pe {
    protected pb(int n2, jy jy2, boolean bl2, int n3) {
        super(n2, jy2, bl2, n3);
    }

    @Override
    public double a(int n2, qd qd2) {
        if (this.H == pe.t.H) {
            return -0.5f * (float)(n2 + 1);
        }
        return 1.3 * (double)(n2 + 1);
    }
}

