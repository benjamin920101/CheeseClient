/*
 * Decompiled with CFR 0.152.
 */
public class bek
extends ber {
    protected bek(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        super(adm2, d2, d3, d4);
        this.i = 0.04f;
        this.k();
        if (d6 == 0.0 && (d5 != 0.0 || d7 != 0.0)) {
            this.v = d5;
            this.w = d6 + 0.1;
            this.x = d7;
        }
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bek(adm2, d2, d3, d4, d5, d6, d7);
        }
    }
}

