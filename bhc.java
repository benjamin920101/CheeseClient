/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;

public class bhc {
    private Map<Class<? extends akw>, bhd<? extends akw>> m = Maps.newHashMap();
    public static bhc a = new bhc();
    private avn n;
    public static double b;
    public static double c;
    public static double d;
    public bmj e;
    public adm f;
    public pk g;
    public float h;
    public float i;
    public double j;
    public double k;
    public double l;

    private bhc() {
        this.m.put(aln.class, new bhj());
        this.m.put(all.class, new bhh());
        this.m.put(alu.class, new bhi());
        this.m.put(aky.class, new bhe());
        this.m.put(alf.class, new bhg());
        this.m.put(ale.class, new bhf());
        this.m.put(alp.class, new bhl());
        this.m.put(akv.class, new bhb());
        this.m.put(alo.class, new bhk());
        this.m.put(aku.class, new bha());
        for (bhd<? extends akw> bhd2 : this.m.values()) {
            bhd2.a(this);
        }
    }

    public <T extends akw> bhd<T> a(Class<? extends akw> clazz) {
        bhd<akw> bhd2 = this.m.get(clazz);
        if (bhd2 == null && clazz != akw.class) {
            bhd2 = this.a(clazz.getSuperclass());
            this.m.put(clazz, bhd2);
        }
        return bhd2;
    }

    public <T extends akw> bhd<T> b(akw akw2) {
        if (akw2 == null) {
            return null;
        }
        return this.a(akw2.getClass());
    }

    public void a(adm adm2, bmj bmj2, avn avn2, pk pk2, float f2) {
        if (this.f != adm2) {
            this.a(adm2);
        }
        this.e = bmj2;
        this.g = pk2;
        this.n = avn2;
        this.h = pk2.A + (pk2.y - pk2.A) * f2;
        this.i = pk2.B + (pk2.z - pk2.B) * f2;
        this.j = pk2.P + (pk2.s - pk2.P) * (double)f2;
        this.k = pk2.Q + (pk2.t - pk2.Q) * (double)f2;
        this.l = pk2.R + (pk2.u - pk2.R) * (double)f2;
    }

    public void a(akw akw2, float f2, int n2) {
        if (akw2.a(this.j, this.k, this.l) < akw2.s()) {
            \u2603 = this.f.b(akw2.v(), 0);
            \u2603 = \u2603 % 65536;
            \u2603 = \u2603 / 65536;
            bqs.a(bqs.r, (float)\u2603 / 1.0f, (float)\u2603 / 1.0f);
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            cj cj2 = akw2.v();
            this.a(akw2, (double)cj2.n() - b, (double)cj2.o() - c, (double)cj2.p() - d, f2, n2);
        }
    }

    public void a(akw akw2, double d2, double d3, double d4, float f2) {
        this.a(akw2, d2, d3, d4, f2, -1);
    }

    public void a(akw akw2, double d2, double d3, double d4, float f2, int n2) {
        bhd<akw> bhd2 = this.b(akw2);
        if (bhd2 != null) {
            try {
                bhd2.a(akw2, d2, d3, d4, f2, n2);
            }
            catch (Throwable throwable) {
                b b2 = b.a(throwable, "Rendering Block Entity");
                c \u26032 = b2.a("Block Entity Details");
                akw2.a(\u26032);
                throw new e(b2);
            }
        }
    }

    public void a(adm adm2) {
        this.f = adm2;
    }

    public avn a() {
        return this.n;
    }
}

