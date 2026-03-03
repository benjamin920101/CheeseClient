/*
 * Decompiled with CFR 0.152.
 */
public class acp
extends aci {
    protected acp(int n2, jy jy2, int n3, acj acj2) {
        super(n2, jy2, n3, acj2);
        if (acj2 == acj.h) {
            this.c("lootBonusDigger");
        } else if (acj2 == acj.i) {
            this.c("lootBonusFishing");
        } else {
            this.c("lootBonus");
        }
    }

    @Override
    public int a(int n2) {
        return 15 + (n2 - 1) * 9;
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
    public boolean a(aci aci2) {
        return super.a(aci2) && aci2.B != acp.s.B;
    }
}

