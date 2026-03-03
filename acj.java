/*
 * Decompiled with CFR 0.152.
 */
public enum acj {
    a,
    b,
    c,
    d,
    e,
    f,
    g,
    h,
    i,
    j,
    k;


    public boolean a(zw zw22) {
        zw zw22;
        if (this == a) {
            return true;
        }
        if (this == j && zw22.m()) {
            return true;
        }
        if (zw22 instanceof yj) {
            if (this == b) {
                return true;
            }
            yj yj2 = (yj)zw22;
            if (yj2.b == 0) {
                return this == f;
            }
            if (yj2.b == 2) {
                return this == d;
            }
            if (yj2.b == 1) {
                return this == e;
            }
            if (yj2.b == 3) {
                return this == c;
            }
            return false;
        }
        if (zw22 instanceof aay) {
            return this == g;
        }
        if (zw22 instanceof za) {
            return this == h;
        }
        if (zw22 instanceof yt) {
            return this == k;
        }
        if (zw22 instanceof zq) {
            return this == i;
        }
        return false;
    }
}

