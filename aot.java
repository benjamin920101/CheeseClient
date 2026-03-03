/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public abstract class aot {
    private final boolean a;

    public aot() {
        this(false);
    }

    public aot(boolean bl2) {
        this.a = bl2;
    }

    public abstract boolean b(adm var1, Random var2, cj var3);

    public void e() {
    }

    protected void a(adm adm2, cj cj2, alz alz2) {
        if (this.a) {
            adm2.a(cj2, alz2, 3);
        } else {
            adm2.a(cj2, alz2, 2);
        }
    }
}

