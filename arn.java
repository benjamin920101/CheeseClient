/*
 * Decompiled with CFR 0.152.
 */
public class arn {
    public static final arn[] a = new arn[64];
    public static final arn b = new arn(0, 0);
    public static final arn c = new arn(1, 8368696);
    public static final arn d = new arn(2, 16247203);
    public static final arn e = new arn(3, 0xC7C7C7);
    public static final arn f = new arn(4, 0xFF0000);
    public static final arn g = new arn(5, 0xA0A0FF);
    public static final arn h = new arn(6, 0xA7A7A7);
    public static final arn i = new arn(7, 31744);
    public static final arn j = new arn(8, 0xFFFFFF);
    public static final arn k = new arn(9, 10791096);
    public static final arn l = new arn(10, 9923917);
    public static final arn m = new arn(11, 0x707070);
    public static final arn n = new arn(12, 0x4040FF);
    public static final arn o = new arn(13, 9402184);
    public static final arn p = new arn(14, 0xFFFCF5);
    public static final arn q = new arn(15, 14188339);
    public static final arn r = new arn(16, 11685080);
    public static final arn s = new arn(17, 6724056);
    public static final arn t = new arn(18, 0xE5E533);
    public static final arn u = new arn(19, 8375321);
    public static final arn v = new arn(20, 15892389);
    public static final arn w = new arn(21, 0x4C4C4C);
    public static final arn x = new arn(22, 0x999999);
    public static final arn y = new arn(23, 5013401);
    public static final arn z = new arn(24, 8339378);
    public static final arn A = new arn(25, 3361970);
    public static final arn B = new arn(26, 6704179);
    public static final arn C = new arn(27, 6717235);
    public static final arn D = new arn(28, 0x993333);
    public static final arn E = new arn(29, 0x191919);
    public static final arn F = new arn(30, 16445005);
    public static final arn G = new arn(31, 6085589);
    public static final arn H = new arn(32, 4882687);
    public static final arn I = new arn(33, 55610);
    public static final arn J = new arn(34, 8476209);
    public static final arn K = new arn(35, 0x700200);
    public final int L;
    public final int M;

    private arn(int n2, int n3) {
        if (n2 < 0 || n2 > 63) {
            throw new IndexOutOfBoundsException("Map colour ID must be between 0 and 63 (inclusive)");
        }
        this.M = n2;
        this.L = n3;
        arn.a[n2] = this;
    }

    public int a(int n2) {
        \u2603 = 220;
        if (n2 == 3) {
            \u2603 = 135;
        }
        if (n2 == 2) {
            \u2603 = 255;
        }
        if (n2 == 1) {
            \u2603 = 220;
        }
        if (n2 == 0) {
            \u2603 = 180;
        }
        \u2603 = (this.L >> 16 & 0xFF) * \u2603 / 255;
        \u2603 = (this.L >> 8 & 0xFF) * \u2603 / 255;
        \u2603 = (this.L & 0xFF) * \u2603 / 255;
        return 0xFF000000 | \u2603 << 16 | \u2603 << 8 | \u2603;
    }
}

