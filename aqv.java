/*
 * Decompiled with CFR 0.152.
 */
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class aqv
extends aqq {
    public static final List<ady> d = Arrays.asList(ady.q, ady.r, ady.Y);
    private int f;
    private int g = 32;
    private int h = 8;

    public aqv() {
    }

    public aqv(Map<String, String> map) {
        this();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equals("size")) {
                this.f = ns.a(entry.getValue(), this.f, 0);
                continue;
            }
            if (!entry.getKey().equals("distance")) continue;
            this.g = ns.a(entry.getValue(), this.g, this.h + 1);
        }
    }

    @Override
    public String a() {
        return "Village";
    }

    @Override
    protected boolean a(int \u260322, int n2) {
        int n3;
        \u2603 = \u260322;
        n3 = n2;
        if (\u260322 < 0) {
            \u260322 -= this.g - 1;
        }
        if (n2 < 0) {
            n2 -= this.g - 1;
        }
        \u2603 = \u260322 / this.g;
        \u2603 = n2 / this.g;
        Random random = this.c.a(\u2603, \u2603, 10387312);
        \u2603 *= this.g;
        \u2603 *= this.g;
        int \u260322 = \u2603;
        n2 = n3;
        return \u260322 == (\u2603 += random.nextInt(this.g - this.h)) && n2 == (\u2603 += random.nextInt(this.g - this.h)) && (\u2603 = this.c.v().a(\u260322 * 16 + 8, n2 * 16 + 8, 0, d));
    }

    @Override
    protected aqu b(int n2, int n3) {
        return new a(this.c, this.b, n2, n3, this.f);
    }

    public static class a
    extends aqu {
        private boolean c;

        public a() {
        }

        public a(adm adm2, Random random, int n2, int n3, int n4) {
            super(n2, n3);
            int n5;
            List<aqw.e> list = aqw.a(random, n4);
            aqw.k \u26032 = new aqw.k(adm2.v(), 0, random, (n2 << 4) + 2, (n3 << 4) + 2, list, n4);
            this.a.add(\u26032);
            \u26032.a(\u26032, this.a, random);
            List<aqt> \u26033 = \u26032.g;
            List<aqt> \u26034 = \u26032.f;
            while (!\u26033.isEmpty() || !\u26034.isEmpty()) {
                Object \u26035;
                if (\u26033.isEmpty()) {
                    n5 = random.nextInt(\u26034.size());
                    \u26035 = \u26034.remove(n5);
                    ((aqt)\u26035).a(\u26032, this.a, random);
                    continue;
                }
                n5 = random.nextInt(\u26033.size());
                \u26035 = \u26033.remove(n5);
                ((aqt)\u26035).a(\u26032, this.a, random);
            }
            this.c();
            n5 = 0;
            for (aqt aqt2 : this.a) {
                if (aqt2 instanceof aqw.o) continue;
                ++n5;
            }
            this.c = n5 > 2;
        }

        @Override
        public boolean d() {
            return this.c;
        }

        @Override
        public void a(dn dn2) {
            super.a(dn2);
            dn2.a("Valid", this.c);
        }

        @Override
        public void b(dn dn2) {
            super.b(dn2);
            this.c = dn2.n("Valid");
        }
    }
}

