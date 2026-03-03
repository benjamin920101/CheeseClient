/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class us
extends oa.a {
    private final zx b;
    private float c;
    private boolean d;

    public us(zx zx2, int n2) {
        super(n2);
        this.b = zx2;
    }

    public zx a(Random random2) {
        zx zx2 = this.b.k();
        if (this.c > 0.0f) {
            int n2 = (int)(this.c * (float)this.b.j());
            \u2603 = zx2.j() - random2.nextInt(random2.nextInt(n2) + 1);
            if (\u2603 > n2) {
                \u2603 = n2;
            }
            if (\u2603 < 1) {
                \u2603 = 1;
            }
            zx2.b(\u2603);
        }
        if (this.d) {
            Random random2;
            ack.a(random2, zx2, 30);
        }
        return zx2;
    }

    public us a(float f2) {
        this.c = f2;
        return this;
    }

    public us a() {
        this.d = true;
        return this;
    }
}

