/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class aqi
extends aqq {
    private List<ady.c> d = Lists.newArrayList();

    public aqi() {
        this.d.add(new ady.c(vl.class, 10, 2, 3));
        this.d.add(new ady.c(vw.class, 5, 4, 4));
        this.d.add(new ady.c(wa.class, 10, 4, 4));
        this.d.add(new ady.c(vu.class, 3, 4, 4));
    }

    @Override
    public String a() {
        return "Fortress";
    }

    public List<ady.c> b() {
        return this.d;
    }

    @Override
    protected boolean a(int n2, int n3) {
        \u2603 = n2 >> 4;
        \u2603 = n3 >> 4;
        this.b.setSeed((long)(\u2603 ^ \u2603 << 4) ^ this.c.J());
        this.b.nextInt();
        if (this.b.nextInt(3) != 0) {
            return false;
        }
        if (n2 != (\u2603 << 4) + 4 + this.b.nextInt(8)) {
            return false;
        }
        return n3 == (\u2603 << 4) + 4 + this.b.nextInt(8);
    }

    @Override
    protected aqu b(int n2, int n3) {
        return new a(this.c, this.b, n2, n3);
    }

    public static class a
    extends aqu {
        public a() {
        }

        public a(adm adm2, Random random, int n2, int n3) {
            super(n2, n3);
            aqj.q q2 = new aqj.q(random, (n2 << 4) + 2, (n3 << 4) + 2);
            this.a.add(q2);
            q2.a(q2, this.a, random);
            List<aqt> \u26032 = q2.e;
            while (!\u26032.isEmpty()) {
                int n4 = random.nextInt(\u26032.size());
                aqt \u26033 = \u26032.remove(n4);
                \u26033.a(q2, this.a, random);
            }
            this.c();
            this.a(adm2, random, 48, 70);
        }
    }
}

