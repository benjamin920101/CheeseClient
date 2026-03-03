/*
 * Decompiled with CFR 0.152.
 */
public class acf
extends aci {
    private static final String[] E = new String[]{"all", "undead", "arthropods"};
    private static final int[] F = new int[]{1, 5, 5};
    private static final int[] G = new int[]{11, 8, 8};
    private static final int[] H = new int[]{20, 20, 20};
    public final int a;

    public acf(int n2, jy jy2, int n3, int n4) {
        super(n2, jy2, n3, acj.g);
        this.a = n4;
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
        return 5;
    }

    @Override
    public float a(int n2, pw pw2) {
        if (this.a == 0) {
            return (float)n2 * 1.25f;
        }
        if (this.a == 1 && pw2 == pw.b) {
            return (float)n2 * 2.5f;
        }
        if (this.a == 2 && pw2 == pw.c) {
            return (float)n2 * 2.5f;
        }
        return 0.0f;
    }

    @Override
    public String a() {
        return "enchantment.damage." + E[this.a];
    }

    @Override
    public boolean a(aci aci2) {
        return !(aci2 instanceof acf);
    }

    @Override
    public boolean a(zx zx2) {
        if (zx2.b() instanceof yl) {
            return true;
        }
        return super.a(zx2);
    }

    @Override
    public void a(pr pr2, pk pk2, int n2) {
        if (pk2 instanceof pr) {
            pr pr3 = (pr)pk2;
            if (this.a == 2 && pr3.bz() == pw.c) {
                int n3 = 20 + pr2.bc().nextInt(10 * n2);
                pr3.c(new pf(pe.d.H, n3, 3));
            }
        }
    }
}

