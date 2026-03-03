/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class akw {
    private static final Logger a = LogManager.getLogger();
    private static Map<String, Class<? extends akw>> f = Maps.newHashMap();
    private static Map<Class<? extends akw>, String> g = Maps.newHashMap();
    protected adm b;
    protected cj c = cj.a;
    protected boolean d;
    private int h = -1;
    protected afh e;

    private static void a(Class<? extends akw> clazz, String string) {
        if (f.containsKey(string)) {
            throw new IllegalArgumentException("Duplicate id: " + string);
        }
        f.put(string, clazz);
        g.put(clazz, string);
    }

    public adm z() {
        return this.b;
    }

    public void a(adm adm2) {
        this.b = adm2;
    }

    public boolean t() {
        return this.b != null;
    }

    public void a(dn dn2) {
        this.c = new cj(dn2.f("x"), dn2.f("y"), dn2.f("z"));
    }

    public void b(dn dn2) {
        String string = g.get(this.getClass());
        if (string == null) {
            throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
        }
        dn2.a("id", string);
        dn2.a("x", this.c.n());
        dn2.a("y", this.c.o());
        dn2.a("z", this.c.p());
    }

    public static akw c(dn dn2) {
        akw akw2 = null;
        try {
            Class<? extends akw> clazz = f.get(dn2.j("id"));
            if (clazz != null) {
                akw2 = clazz.newInstance();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        if (akw2 != null) {
            akw2.a(dn2);
        } else {
            a.warn("Skipping BlockEntity with id " + dn2.j("id"));
        }
        return akw2;
    }

    public int u() {
        if (this.h == -1) {
            alz alz2 = this.b.p(this.c);
            this.h = alz2.c().c(alz2);
        }
        return this.h;
    }

    public void p_() {
        if (this.b != null) {
            alz alz2 = this.b.p(this.c);
            this.h = alz2.c().c(alz2);
            this.b.b(this.c, this);
            if (this.w() != afi.a) {
                this.b.e(this.c, this.w());
            }
        }
    }

    public double a(double d2, double d3, double d4) {
        \u2603 = (double)this.c.n() + 0.5 - d2;
        \u2603 = (double)this.c.o() + 0.5 - d3;
        \u2603 = (double)this.c.p() + 0.5 - d4;
        return \u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603;
    }

    public double s() {
        return 4096.0;
    }

    public cj v() {
        return this.c;
    }

    public afh w() {
        if (this.e == null) {
            this.e = this.b.p(this.c).c();
        }
        return this.e;
    }

    public ff y_() {
        return null;
    }

    public boolean x() {
        return this.d;
    }

    public void y() {
        this.d = true;
    }

    public void D() {
        this.d = false;
    }

    public boolean c(int n2, int n3) {
        return false;
    }

    public void E() {
        this.e = null;
        this.h = -1;
    }

    public void a(c c2) {
        c2.a("Name", new Callable<String>(){

            public String a() throws Exception {
                return (String)g.get(akw.this.getClass()) + " // " + akw.this.getClass().getCanonicalName();
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        if (this.b == null) {
            return;
        }
        c.a(c2, this.c, this.w(), this.u());
        c2.a("Actual block type", new Callable<String>(){

            public String a() throws Exception {
                int n2 = afh.a(akw.this.b.p(akw.this.c).c());
                try {
                    return String.format("ID #%d (%s // %s)", n2, afh.c(n2).a(), afh.c(n2).getClass().getCanonicalName());
                }
                catch (Throwable \u26032) {
                    return "ID #" + n2;
                }
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Actual block data value", new Callable<String>(){

            public String a() throws Exception {
                alz alz2 = akw.this.b.p(akw.this.c);
                int \u26032 = alz2.c().c(alz2);
                if (\u26032 < 0) {
                    return "Unknown? (Got " + \u26032 + ")";
                }
                String \u26033 = String.format("%4s", Integer.toBinaryString(\u26032)).replace(" ", "0");
                return String.format("%1$d / 0x%1$X / 0b%2$s", \u26032, \u26033);
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
    }

    public void a(cj cj2) {
        this.c = cj2;
    }

    public boolean F() {
        return false;
    }

    static {
        akw.a(alh.class, "Furnace");
        akw.a(aky.class, "Chest");
        akw.a(alf.class, "EnderChest");
        akw.a(ahq.a.class, "RecordPlayer");
        akw.a(alc.class, "Trap");
        akw.a(ald.class, "Dropper");
        akw.a(aln.class, "Sign");
        akw.a(all.class, "MobSpawner");
        akw.a(alm.class, "Music");
        akw.a(alu.class, "Piston");
        akw.a(akx.class, "Cauldron");
        akw.a(ale.class, "EnchantTable");
        akw.a(alp.class, "Airportal");
        akw.a(akz.class, "Control");
        akw.a(akv.class, "Beacon");
        akw.a(alo.class, "Skull");
        akw.a(alb.class, "DLDetector");
        akw.a(alj.class, "Hopper");
        akw.a(ala.class, "Comparator");
        akw.a(alg.class, "FlowerPot");
        akw.a(aku.class, "Banner");
    }
}

