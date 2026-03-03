/*
 * Decompiled with CFR 0.152.
 */
public class bdx
extends beh {
    protected bdx(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        super(adm2, d2, d3, d4, d5, d6, d7, 2.5f);
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bdx(adm2, d2, d3, d4, d5, d6, d7);
        }
    }
}

