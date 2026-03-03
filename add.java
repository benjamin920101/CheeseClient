/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public abstract class add {
    private int a = 20;
    private String b = "Pig";
    private final List<a> c = Lists.newArrayList();
    private a d;
    private double e;
    private double f;
    private int g = 200;
    private int h = 800;
    private int i = 4;
    private pk j;
    private int k = 6;
    private int l = 16;
    private int m = 4;

    private String f() {
        if (this.i() == null) {
            if (this.b != null && this.b.equals("Minecart")) {
                this.b = "MinecartRideable";
            }
            return this.b;
        }
        return this.i().d;
    }

    public void a(String string) {
        this.b = string;
    }

    private boolean g() {
        cj cj2 = this.b();
        return this.a().b((double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, (double)this.l);
    }

    public void c() {
        if (!this.g()) {
            return;
        }
        cj cj2 = this.b();
        if (this.a().D) {
            double d2 = (float)cj2.n() + this.a().s.nextFloat();
            \u2603 = (float)cj2.o() + this.a().s.nextFloat();
            \u2603 = (float)cj2.p() + this.a().s.nextFloat();
            this.a().a(cy.l, d2, \u2603, \u2603, 0.0, 0.0, 0.0, new int[0]);
            this.a().a(cy.A, d2, \u2603, \u2603, 0.0, 0.0, 0.0, new int[0]);
            if (this.a > 0) {
                --this.a;
            }
            this.f = this.e;
            this.e = (this.e + (double)(1000.0f / ((float)this.a + 200.0f))) % 360.0;
        } else {
            if (this.a == -1) {
                this.h();
            }
            if (this.a > 0) {
                --this.a;
                return;
            }
            boolean \u26037 = false;
            for (int i2 = 0; i2 < this.i; ++i2) {
                pk pk2 = pm.a(this.f(), this.a());
                if (pk2 == null) {
                    return;
                }
                int \u26032 = this.a().a(pk2.getClass(), new aug(cj2.n(), cj2.o(), cj2.p(), cj2.n() + 1, cj2.o() + 1, cj2.p() + 1).b(this.m, this.m, this.m)).size();
                if (\u26032 >= this.k) {
                    this.h();
                    return;
                }
                double \u26033 = (double)cj2.n() + (this.a().s.nextDouble() - this.a().s.nextDouble()) * (double)this.m + 0.5;
                double \u26034 = cj2.o() + this.a().s.nextInt(3) - 1;
                double \u26035 = (double)cj2.p() + (this.a().s.nextDouble() - this.a().s.nextDouble()) * (double)this.m + 0.5;
                ps \u26036 = pk2 instanceof ps ? (ps)pk2 : null;
                pk2.b(\u26033, \u26034, \u26035, this.a().s.nextFloat() * 360.0f, 0.0f);
                if (\u26036 != null && (!\u26036.bR() || !\u26036.bS())) continue;
                this.a(pk2, true);
                this.a().b(2004, cj2, 0);
                if (\u26036 != null) {
                    \u26036.y();
                }
                \u26037 = true;
            }
            if (\u26037) {
                this.h();
            }
        }
    }

    private pk a(pk pk22, boolean bl2) {
        if (this.i() != null) {
            dn object = new dn();
            pk22.d(object);
            for (String string : this.i().c.c()) {
                eb object3 = this.i().c.a(string);
                object.a(string, object3.b());
            }
            pk22.f(object);
            if (pk22.o != null && bl2) {
                pk22.o.d(pk22);
            }
            Object \u26032 = pk22;
            while (object.b("Riding", 10)) {
                dn dn2 = object.m("Riding");
                pk pk2 = pm.a(dn2.j("id"), pk22.o);
                if (pk2 != null) {
                    dn dn22 = new dn();
                    pk2.d(dn22);
                    for (String string : dn2.c()) {
                        eb eb2 = dn2.a(string);
                        dn22.a(string, eb2.b());
                    }
                    pk2.f(dn22);
                    pk2.b(((pk)\u26032).s, ((pk)\u26032).t, ((pk)\u26032).u, ((pk)\u26032).y, ((pk)\u26032).z);
                    if (pk22.o != null && bl2) {
                        pk22.o.d(pk2);
                    }
                    ((pk)\u26032).a(pk2);
                }
                \u26032 = pk2;
                object = dn2;
            }
        } else if (pk22 instanceof pr && pk22.o != null && bl2) {
            if (pk22 instanceof ps) {
                ((ps)pk22).a(pk22.o.E(new cj(pk22)), null);
            }
            pk22.o.d(pk22);
        }
        return pk22;
    }

    private void h() {
        this.a = this.h <= this.g ? this.g : this.g + this.a().s.nextInt(this.h - this.g);
        if (this.c.size() > 0) {
            this.a(oa.a(this.a().s, this.c));
        }
        this.a(1);
    }

    public void a(dn dn22) {
        dn dn22;
        this.b = dn22.j("EntityId");
        this.a = dn22.e("Delay");
        this.c.clear();
        if (dn22.b("SpawnPotentials", 9)) {
            du du2 = dn22.c("SpawnPotentials", 10);
            for (int i2 = 0; i2 < du2.c(); ++i2) {
                this.c.add(new a(du2.b(i2)));
            }
        }
        if (dn22.b("SpawnData", 10)) {
            this.a(new a(dn22.m("SpawnData"), this.b));
        } else {
            this.a((a)null);
        }
        if (dn22.b("MinSpawnDelay", 99)) {
            this.g = dn22.e("MinSpawnDelay");
            this.h = dn22.e("MaxSpawnDelay");
            this.i = dn22.e("SpawnCount");
        }
        if (dn22.b("MaxNearbyEntities", 99)) {
            this.k = dn22.e("MaxNearbyEntities");
            this.l = dn22.e("RequiredPlayerRange");
        }
        if (dn22.b("SpawnRange", 99)) {
            this.m = dn22.e("SpawnRange");
        }
        if (this.a() != null) {
            this.j = null;
        }
    }

    public void b(dn dn22) {
        String string = this.f();
        if (nx.b(string)) {
            return;
        }
        dn22.a("EntityId", string);
        dn22.a("Delay", (short)this.a);
        dn22.a("MinSpawnDelay", (short)this.g);
        dn22.a("MaxSpawnDelay", (short)this.h);
        dn22.a("SpawnCount", (short)this.i);
        dn22.a("MaxNearbyEntities", (short)this.k);
        dn22.a("RequiredPlayerRange", (short)this.l);
        dn22.a("SpawnRange", (short)this.m);
        if (this.i() != null) {
            dn22.a("SpawnData", this.i().c.b());
        }
        if (this.i() != null || this.c.size() > 0) {
            dn dn22;
            du du2 = new du();
            if (this.c.size() > 0) {
                for (a a2 : this.c) {
                    du2.a(a2.a());
                }
            } else {
                du2.a(this.i().a());
            }
            dn22.a("SpawnPotentials", du2);
        }
    }

    public pk a(adm adm2) {
        if (this.j == null && (\u2603 = pm.a(this.f(), adm2)) != null) {
            this.j = \u2603 = this.a(\u2603, false);
        }
        return this.j;
    }

    public boolean b(int n2) {
        if (n2 == 1 && this.a().D) {
            this.a = this.g;
            return true;
        }
        return false;
    }

    private a i() {
        return this.d;
    }

    public void a(a a2) {
        this.d = a2;
    }

    public abstract void a(int var1);

    public abstract adm a();

    public abstract cj b();

    public double d() {
        return this.e;
    }

    public double e() {
        return this.f;
    }

    public class a
    extends oa.a {
        private final dn c;
        private final String d;

        public a(dn dn2) {
            this(dn2.m("Properties"), dn2.j("Type"), dn2.f("Weight"));
        }

        public a(dn dn2, String string) {
            this(dn2, string, 1);
        }

        private a(dn dn22, String string2, int n2) {
            dn dn22;
            super(n2);
            if (string2.equals("Minecart")) {
                String string2 = dn22 != null ? va.a.a(dn22.f("Type")).b() : "MinecartRideable";
            }
            this.c = dn22;
            this.d = string2;
        }

        public dn a() {
            dn dn2 = new dn();
            dn2.a("Properties", this.c);
            dn2.a("Type", this.d);
            dn2.a("Weight", this.a);
            return dn2;
        }
    }
}

