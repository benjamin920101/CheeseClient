/*
 * Decompiled with CFR 0.152.
 */
public class abi {
    private String[][] a = new String[][]{{"XXX", "X X"}, {"X X", "XXX", "XXX"}, {"XXX", "X X", "X X"}, {"X X", "X X"}};
    private zw[][] b = new zw[][]{{zy.aF, zy.j, zy.i, zy.k}, {zy.Q, zy.Y, zy.ac, zy.ag}, {zy.R, zy.Z, zy.ad, zy.ah}, {zy.S, zy.aa, zy.ae, zy.ai}, {zy.T, zy.ab, zy.af, zy.aj}};

    public void a(abt abt2) {
        for (int i2 = 0; i2 < this.b[0].length; ++i2) {
            zw zw2 = this.b[0][i2];
            for (int i3 = 0; i3 < this.b.length - 1; ++i3) {
                zw zw3 = this.b[i3 + 1][i2];
                abt2.a(new zx(zw3), this.a[i3], Character.valueOf('X'), zw2);
            }
        }
    }
}

