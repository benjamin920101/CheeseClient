/*
 * Decompiled with CFR 0.152.
 */
public class bhs
extends bht {
    private final int d = avd.a(adf.values().length);

    public bhs(adm adm2, bfr bfr2, cj cj2, int n2) {
        super(adm2, bfr2, cj2, n2);
    }

    public int a(adf adf2, bhq bhq2) {
        if (!bhq2.b(adf2)) {
            return this.d + adf2.ordinal();
        }
        return -1;
    }

    @Override
    public void a() {
        super.a();
        avd.a(this.d, adf.values().length);
    }
}

