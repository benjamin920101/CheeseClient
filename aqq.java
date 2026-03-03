/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

public abstract class aqq
extends any {
    private aqs d;
    protected Map<Long, aqu> e = Maps.newHashMap();

    public abstract String a();

    @Override
    protected final void a(adm adm2, final int n2, final int n3, int n4, int n5, ans ans2) {
        this.a(adm2);
        if (this.e.containsKey(adg.a(n2, n3))) {
            return;
        }
        this.b.nextInt();
        try {
            if (this.a(n2, n3)) {
                aqu aqu2 = this.b(n2, n3);
                this.e.put(adg.a(n2, n3), aqu2);
                this.a(n2, n3, aqu2);
            }
        }
        catch (Throwable throwable) {
            b b2 = b.a(throwable, "Exception preparing structure feature");
            c \u26032 = b2.a("Feature being prepared");
            \u26032.a("Is feature chunk", new Callable<String>(){

                public String a() throws Exception {
                    return aqq.this.a(n2, n3) ? "True" : "False";
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            \u26032.a("Chunk location", String.format("%d,%d", n2, n3));
            \u26032.a("Chunk pos hash", new Callable<String>(){

                public String a() throws Exception {
                    return String.valueOf(adg.a(n2, n3));
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            \u26032.a("Structure type", new Callable<String>(){

                public String a() throws Exception {
                    return aqq.this.getClass().getCanonicalName();
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            throw new e(b2);
        }
    }

    public boolean a(adm adm2, Random random, adg adg2) {
        this.a(adm2);
        int n2 = (adg2.a << 4) + 8;
        \u2603 = (adg2.b << 4) + 8;
        boolean \u26032 = false;
        for (aqu aqu2 : this.e.values()) {
            if (!aqu2.d() || !aqu2.a(adg2) || !aqu2.a().a(n2, \u2603, n2 + 15, \u2603 + 15)) continue;
            aqu2.a(adm2, random, new aqe(n2, \u2603, n2 + 15, \u2603 + 15));
            aqu2.b(adg2);
            \u26032 = true;
            this.a(aqu2.e(), aqu2.f(), aqu2);
        }
        return \u26032;
    }

    public boolean b(cj cj2) {
        this.a(this.c);
        return this.c(cj2) != null;
    }

    protected aqu c(cj cj2) {
        for (aqu aqu2 : this.e.values()) {
            if (!aqu2.d() || !aqu2.a().b(cj2)) continue;
            for (aqt aqt2 : aqu2.b()) {
                if (!aqt2.c().b(cj2)) continue;
                return aqu2;
            }
        }
        return null;
    }

    public boolean a(adm adm2, cj cj2) {
        this.a(adm2);
        for (aqu aqu2 : this.e.values()) {
            if (!aqu2.d() || !aqu2.a().b(cj2)) continue;
            return true;
        }
        return false;
    }

    /*
     * WARNING - void declaration
     */
    public cj b(adm adm2, cj cj2) {
        this.c = adm2;
        this.a(adm2);
        this.b.setSeed(adm2.J());
        long l2 = this.b.nextLong();
        long l3 = this.b.nextLong();
        \u2603 = (long)(cj2.n() >> 4) * l2;
        \u2603 = (long)(cj2.p() >> 4) * l3;
        this.b.setSeed(\u2603 ^ \u2603 ^ adm2.J());
        this.a(adm2, cj2.n() >> 4, cj2.p() >> 4, 0, 0, null);
        double \u26032 = Double.MAX_VALUE;
        cj \u26033 = null;
        for (aqu aqu2 : this.e.values()) {
            cj cj3;
            double d2;
            if (!aqu2.d() || !((d2 = (cj3 = ((aqt)(\u2603 = aqu2.b().get(0))).a()).i(cj2)) < \u26032)) continue;
            \u26032 = d2;
            \u26033 = cj3;
        }
        if (\u26033 != null) {
            return \u26033;
        }
        List<cj> \u26035 = this.z_();
        if (\u26035 != null) {
            void var15_12;
            Object var15_11 = null;
            for (cj cj3 : \u26035) {
                double d2 = cj3.i(cj2);
                if (!(d2 < \u26032)) continue;
                \u26032 = d2;
                cj cj4 = cj3;
            }
            return var15_12;
        }
        return null;
    }

    protected List<cj> z_() {
        return null;
    }

    private void a(adm adm2) {
        if (this.d == null) {
            this.d = (aqs)adm2.a(aqs.class, this.a());
            if (this.d == null) {
                this.d = new aqs(this.a());
                adm2.a(this.a(), this.d);
            } else {
                dn dn2 = this.d.a();
                for (String string : dn2.c()) {
                    eb eb2 = dn2.a(string);
                    if (eb2.a() != 10 || !(\u2603 = (dn)eb2).c("ChunkX") || !\u2603.c("ChunkZ")) continue;
                    int \u26032 = \u2603.f("ChunkX");
                    int \u26033 = \u2603.f("ChunkZ");
                    aqu \u26034 = aqr.a(\u2603, adm2);
                    if (\u26034 == null) continue;
                    this.e.put(adg.a(\u26032, \u26033), \u26034);
                }
            }
        }
    }

    private void a(int n2, int n3, aqu aqu2) {
        this.d.a(aqu2.a(n2, n3), n2, n3);
        this.d.c();
    }

    protected abstract boolean a(int var1, int var2);

    protected abstract aqu b(int var1, int var2);
}

