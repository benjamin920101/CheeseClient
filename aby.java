/*
 * Decompiled with CFR 0.152.
 */
public class aby {
    private String[][] a = new String[][]{{"XXX", " # ", " # "}, {"X", "#", "#"}, {"XX", "X#", " #"}, {"XX", " #", " #"}};
    private Object[][] b = new Object[][]{{afi.f, afi.e, zy.j, zy.i, zy.k}, {zy.o, zy.s, zy.b, zy.w, zy.D}, {zy.n, zy.r, zy.a, zy.v, zy.C}, {zy.p, zy.t, zy.c, zy.x, zy.E}, {zy.I, zy.J, zy.K, zy.L, zy.M}};

    public void a(abt abt22) {
        abt abt22;
        for (int i2 = 0; i2 < this.b[0].length; ++i2) {
            Object object = this.b[0][i2];
            for (int i3 = 0; i3 < this.b.length - 1; ++i3) {
                zw zw2 = (zw)this.b[i3 + 1][i2];
                abt22.a(new zx(zw2), this.a[i3], Character.valueOf('#'), zy.y, Character.valueOf('X'), object);
            }
        }
        abt22.a(new zx(zy.be), " #", "# ", Character.valueOf('#'), zy.j);
    }
}

