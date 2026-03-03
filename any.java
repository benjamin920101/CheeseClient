/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class any {
    protected int a = 8;
    protected Random b = new Random();
    protected adm c;

    public void a(amv amv2, adm adm2, int n2, int n3, ans ans2) {
        int n4 = this.a;
        this.c = adm2;
        this.b.setSeed(adm2.J());
        long \u26032 = this.b.nextLong();
        long \u26033 = this.b.nextLong();
        for (\u2603 = n2 - n4; \u2603 <= n2 + n4; ++\u2603) {
            for (\u2603 = n3 - n4; \u2603 <= n3 + n4; ++\u2603) {
                long l2 = (long)\u2603 * \u26032;
                \u2603 = (long)\u2603 * \u26033;
                this.b.setSeed(l2 ^ \u2603 ^ adm2.J());
                this.a(adm2, \u2603, \u2603, n2, n3, ans2);
            }
        }
    }

    protected void a(adm adm2, int n2, int n3, int n4, int n5, ans ans2) {
    }
}

