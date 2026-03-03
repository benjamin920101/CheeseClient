/*
 * Decompiled with CFR 0.152.
 */
public class abz {
    private String[][] a = new String[][]{{"X", "X", "#"}};
    private Object[][] b = new Object[][]{{afi.f, afi.e, zy.j, zy.i, zy.k}, {zy.m, zy.q, zy.l, zy.u, zy.B}};

    public void a(abt abt22) {
        abt abt22;
        for (int i2 = 0; i2 < this.b[0].length; ++i2) {
            Object object = this.b[0][i2];
            for (int i3 = 0; i3 < this.b.length - 1; ++i3) {
                zw zw2 = (zw)this.b[i3 + 1][i2];
                abt22.a(new zx(zw2), this.a[i3], Character.valueOf('#'), zy.y, Character.valueOf('X'), object);
            }
        }
        abt22.a(new zx(zy.f, 1), " #X", "# X", " #X", Character.valueOf('X'), zy.F, Character.valueOf('#'), zy.y);
        abt22.a(new zx(zy.g, 4), "X", "#", "Y", Character.valueOf('Y'), zy.G, Character.valueOf('X'), zy.ak, Character.valueOf('#'), zy.y);
    }
}

