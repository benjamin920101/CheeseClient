/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class acs
extends aci {
    public acs(int n2, jy jy2, int n3) {
        super(n2, jy2, n3, acj.e);
        this.c("thorns");
    }

    @Override
    public int a(int n2) {
        return 10 + 20 * (n2 - 1);
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
        if (zx2.b() instanceof yj) {
            return true;
        }
        return super.a(zx2);
    }

    @Override
    public void b(pr pr2, pk pk2, int n2) {
        Random random = pr2.bc();
        zx \u26032 = ack.a(aci.j, pr2);
        if (acs.a(n2, random)) {
            if (pk2 != null) {
                pk2.a(ow.a((pk)pr2), (float)acs.b(n2, random));
                pk2.a("damage.thorns", 0.5f, 1.0f);
            }
            if (\u26032 != null) {
                \u26032.a(3, pr2);
            }
        } else if (\u26032 != null) {
            \u26032.a(1, pr2);
        }
    }

    public static boolean a(int n2, Random random) {
        if (n2 <= 0) {
            return false;
        }
        return random.nextFloat() < 0.15f * (float)n2;
    }

    public static int b(int n2, Random random) {
        if (n2 > 10) {
            return n2 - 10;
        }
        return 1 + random.nextInt(4);
    }
}

