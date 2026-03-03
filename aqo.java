/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class aqo
extends aqq {
    private List<ady> d;
    private boolean f;
    private adg[] g = new adg[3];
    private double h = 32.0;
    private int i = 3;

    public aqo() {
        this.d = Lists.newArrayList();
        for (ady ady2 : ady.n()) {
            if (ady2 == null || !(ady2.an > 0.0f)) continue;
            this.d.add(ady2);
        }
    }

    public aqo(Map<String, String> map) {
        this();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equals("distance")) {
                this.h = ns.a(entry.getValue(), this.h, 1.0);
                continue;
            }
            if (entry.getKey().equals("count")) {
                this.g = new adg[ns.a(entry.getValue(), this.g.length, 1)];
                continue;
            }
            if (!entry.getKey().equals("spread")) continue;
            this.i = ns.a(entry.getValue(), this.i, 1);
        }
    }

    @Override
    public String a() {
        return "Stronghold";
    }

    @Override
    protected boolean a(int n2, int n3) {
        if (!this.f) {
            Random random = new Random();
            random.setSeed(this.c.J());
            double \u26032 = random.nextDouble() * Math.PI * 2.0;
            int \u26033 = 1;
            for (int i2 = 0; i2 < this.g.length; ++i2) {
                double d2 = (1.25 * (double)\u26033 + random.nextDouble()) * (this.h * (double)\u26033);
                int \u26034 = (int)Math.round(Math.cos(\u26032) * d2);
                int \u26035 = (int)Math.round(Math.sin(\u26032) * d2);
                cj \u26036 = this.c.v().a((\u26034 << 4) + 8, (\u26035 << 4) + 8, 112, this.d, random);
                if (\u26036 != null) {
                    \u26034 = \u26036.n() >> 4;
                    \u26035 = \u26036.p() >> 4;
                }
                this.g[i2] = new adg(\u26034, \u26035);
                \u26032 += Math.PI * 2 * (double)\u26033 / (double)this.i;
                if (i2 != this.i) continue;
                \u26033 += 2 + random.nextInt(5);
                this.i += 1 + random.nextInt(2);
            }
            this.f = true;
        }
        for (adg adg2 : this.g) {
            if (n2 != adg2.a || n3 != adg2.b) continue;
            return true;
        }
        return false;
    }

    @Override
    protected List<cj> z_() {
        ArrayList<cj> arrayList = Lists.newArrayList();
        for (adg adg2 : this.g) {
            if (adg2 == null) continue;
            arrayList.add(adg2.a(64));
        }
        return arrayList;
    }

    @Override
    protected aqu b(int n2, int n3) {
        a a2 = new a(this.c, this.b, n2, n3);
        while (a2.b().isEmpty() || ((aqp.m)a2.b().get((int)0)).b == null) {
            a2 = new a(this.c, this.b, n2, n3);
        }
        return a2;
    }

    public static class a
    extends aqu {
        public a() {
        }

        public a(adm adm2, Random random, int n2, int n3) {
            super(n2, n3);
            aqp.b();
            aqp.m m2 = new aqp.m(0, random, (n2 << 4) + 2, (n3 << 4) + 2);
            this.a.add(m2);
            m2.a(m2, this.a, random);
            List<aqt> \u26032 = m2.c;
            while (!\u26032.isEmpty()) {
                int n4 = random.nextInt(\u26032.size());
                aqt \u26033 = \u26032.remove(n4);
                \u26033.a(m2, this.a, random);
            }
            this.c();
            this.a(adm2, random, 10);
        }
    }
}

