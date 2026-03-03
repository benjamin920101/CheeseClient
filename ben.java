/*
 * Decompiled with CFR 0.152.
 */
public class ben
extends beo {
    protected ben(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, alz alz2) {
        super(adm2, d2, d3, d4, d5, d6, d7, alz2);
        this.v = d5;
        this.w = d6;
        this.x = d7;
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            alz alz2 = afh.d(nArray[0]);
            if (alz2.c().b() == -1) {
                return null;
            }
            return new ben(adm2, d2, d3, d4, d5, d6, d7, alz2).l();
        }
    }
}

