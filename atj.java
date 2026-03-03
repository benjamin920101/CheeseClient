/*
 * Decompiled with CFR 0.152.
 */
import java.io.File;

public class atj
extends atm {
    public atj(File file, String string, boolean bl2) {
        super(file, string, bl2);
    }

    @Override
    public and a(anm anm2) {
        File file = this.b();
        if (anm2 instanceof ann) {
            \u2603 = new File(file, "DIM-1");
            \u2603.mkdirs();
            return new anj(\u2603);
        }
        if (anm2 instanceof anp) {
            \u2603 = new File(file, "DIM1");
            \u2603.mkdirs();
            return new anj(\u2603);
        }
        return new anj(file);
    }

    @Override
    public void a(ato ato2, dn dn2) {
        ato2.e(19133);
        super.a(ato2, dn2);
    }

    @Override
    public void a() {
        try {
            auc.a().b();
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        ani.a();
    }
}

