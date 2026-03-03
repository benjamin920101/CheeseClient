/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class acg
extends aci {
    protected acg(int n2, jy jy2, int n3) {
        super(n2, jy2, n3, acj.j);
        this.c("durability");
    }

    @Override
    public int a(int n2) {
        return 5 + (n2 - 1) * 8;
    }

    @Override
    public int b(int n2) {
        return super.a(n2) + 50;
    }

    @Override
    public int b() {
        return 3;
    }

    @Override
    public boolean a(zx zx2) {
        if (zx2.e()) {
            return true;
        }
        return super.a(zx2);
    }

    public static boolean a(zx zx2, int n2, Random random) {
        if (zx2.b() instanceof yj && random.nextFloat() < 0.6f) {
            return false;
        }
        return random.nextInt(n2 + 1) > 0;
    }
}

