/*
 * Decompiled with CFR 0.152.
 */
public class afy
extends afh {
    protected afy() {
        super(arm.d);
        this.a(yz.c);
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (adm2.D) {
            return true;
        }
        wn2.a(new a(adm2, cj2));
        wn2.b(na.Z);
        return true;
    }

    public static class a
    implements ol {
        private final adm a;
        private final cj b;

        public a(adm adm2, cj cj2) {
            this.a = adm2;
            this.b = cj2;
        }

        @Override
        public String e_() {
            return null;
        }

        @Override
        public boolean l_() {
            return false;
        }

        @Override
        public eu f_() {
            return new fb(afi.ai.a() + ".name", new Object[0]);
        }

        @Override
        public xi a(wm wm2, wn wn2) {
            return new xq(wm2, this.a, this.b);
        }

        @Override
        public String k() {
            return "minecraft:crafting_table";
        }
    }
}

