/*
 * Decompiled with CFR 0.152.
 */
public class acr
extends aci {
    private static final String[] E = new String[]{"all", "fire", "fall", "explosion", "projectile"};
    private static final int[] F = new int[]{1, 10, 5, 5, 3};
    private static final int[] G = new int[]{11, 8, 6, 8, 6};
    private static final int[] H = new int[]{20, 12, 10, 12, 15};
    public final int a;

    public acr(int n2, jy jy2, int n3, int n4) {
        super(n2, jy2, n3, acj.b);
        this.a = n4;
        if (n4 == 2) {
            this.C = acj.c;
        }
    }

    @Override
    public int a(int n2) {
        return F[this.a] + (n2 - 1) * G[this.a];
    }

    @Override
    public int b(int n2) {
        return this.a(n2) + H[this.a];
    }

    @Override
    public int b() {
        return 4;
    }

    @Override
    public int a(int n2, ow ow2) {
        if (ow2.g()) {
            return 0;
        }
        float f2 = (float)(6 + n2 * n2) / 3.0f;
        if (this.a == 0) {
            return ns.d(f2 * 0.75f);
        }
        if (this.a == 1 && ow2.o()) {
            return ns.d(f2 * 1.25f);
        }
        if (this.a == 2 && ow2 == ow.i) {
            return ns.d(f2 * 2.5f);
        }
        if (this.a == 3 && ow2.c()) {
            return ns.d(f2 * 1.5f);
        }
        if (this.a == 4 && ow2.a()) {
            return ns.d(f2 * 1.5f);
        }
        return 0;
    }

    @Override
    public String a() {
        return "enchantment.protect." + E[this.a];
    }

    @Override
    public boolean a(aci aci22) {
        aci aci22;
        if (aci22 instanceof acr) {
            acr acr2 = (acr)aci22;
            if (acr2.a == this.a) {
                return false;
            }
            return this.a == 2 || acr2.a == 2;
        }
        return super.a(aci22);
    }

    public static int a(pk pk2, int n2) {
        \u2603 = ack.a(aci.d.B, pk2.as());
        if (\u2603 > 0) {
            n2 -= ns.d((float)n2 * ((float)\u2603 * 0.15f));
        }
        return n2;
    }

    public static double a(pk pk2, double d2) {
        int n2 = ack.a(aci.f.B, pk2.as());
        if (n2 > 0) {
            d2 -= (double)ns.c(d2 * (double)((float)n2 * 0.15f));
        }
        return d2;
    }
}

