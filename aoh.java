/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public abstract class aoh
extends aot {
    public aoh(boolean bl2) {
        super(bl2);
    }

    protected boolean a(afh afh2) {
        arm arm2 = afh2.t();
        return arm2 == arm.a || arm2 == arm.j || afh2 == afi.c || afh2 == afi.d || afh2 == afi.r || afh2 == afi.s || afh2 == afi.g || afh2 == afi.bn;
    }

    public void a(adm adm2, Random random, cj cj2) {
    }

    protected void a(adm adm2, cj cj2) {
        if (adm2.p(cj2).c() != afi.d) {
            this.a(adm2, cj2, afi.d.Q());
        }
    }
}

