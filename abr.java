/*
 * Decompiled with CFR 0.152.
 */
public class abr {
    private Object[][] a = new Object[][]{{afi.R, new zx(zy.k, 9)}, {afi.S, new zx(zy.j, 9)}, {afi.ah, new zx(zy.i, 9)}, {afi.bT, new zx(zy.bO, 9)}, {afi.y, new zx(zy.aW, 9, zd.l.b())}, {afi.cn, new zx(zy.aC, 9)}, {afi.cA, new zx(zy.h, 9, 0)}, {afi.cx, new zx(zy.O, 9)}, {afi.cE, new zx(zy.aM, 9)}};

    public void a(abt abt2) {
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            afh afh2 = (afh)this.a[i2][0];
            zx \u26032 = (zx)this.a[i2][1];
            abt2.a(new zx(afh2), "###", "###", "###", Character.valueOf('#'), \u26032);
            abt2.a(\u26032, "#", Character.valueOf('#'), afh2);
        }
        abt2.a(new zx(zy.k), "###", "###", "###", Character.valueOf('#'), zy.bx);
        abt2.a(new zx(zy.bx, 9), "#", Character.valueOf('#'), zy.k);
    }
}

