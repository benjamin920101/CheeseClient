/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class th
extends ate {
    private adm b;
    private final List<cj> c = Lists.newArrayList();
    private final List<te> d = Lists.newArrayList();
    private final List<tf> e = Lists.newArrayList();
    private int f;

    public th(String string) {
        super(string);
    }

    public th(adm adm2) {
        super(th.a(adm2.t));
        this.b = adm2;
        this.c();
    }

    public void a(adm adm2) {
        this.b = adm2;
        for (tf tf2 : this.e) {
            tf2.a(adm2);
        }
    }

    public void a(cj cj2) {
        if (this.c.size() > 64) {
            return;
        }
        if (!this.e(cj2)) {
            this.c.add(cj2);
        }
    }

    public void a() {
        ++this.f;
        for (tf tf2 : this.e) {
            tf2.a(this.f);
        }
        this.e();
        this.f();
        this.g();
        if (this.f % 400 == 0) {
            this.c();
        }
    }

    private void e() {
        Iterator<tf> iterator = this.e.iterator();
        while (iterator.hasNext()) {
            tf tf2 = iterator.next();
            if (!tf2.g()) continue;
            iterator.remove();
            this.c();
        }
    }

    public List<tf> b() {
        return this.e;
    }

    public tf a(cj cj2, int n2) {
        tf \u26033 = null;
        double \u26032 = 3.4028234663852886E38;
        for (tf tf2 : this.e) {
            double d2 = tf2.a().i(cj2);
            if (d2 >= \u26032 || d2 > (double)((\u2603 = (float)(n2 + tf2.b())) * \u2603)) continue;
            \u26033 = tf2;
            \u26032 = d2;
        }
        return \u26033;
    }

    private void f() {
        if (this.c.isEmpty()) {
            return;
        }
        this.b(this.c.remove(0));
    }

    private void g() {
        for (int i2 = 0; i2 < this.d.size(); ++i2) {
            te te2 = this.d.get(i2);
            tf \u26032 = this.a(te2.d(), 32);
            if (\u26032 == null) {
                \u26032 = new tf(this.b);
                this.e.add(\u26032);
                this.c();
            }
            \u26032.a(te2);
        }
        this.d.clear();
    }

    private void b(cj cj2) {
        int n2 = 16;
        \u2603 = 4;
        \u2603 = 16;
        for (\u2603 = -n2; \u2603 < n2; ++\u2603) {
            for (\u2603 = -\u2603; \u2603 < \u2603; ++\u2603) {
                for (\u2603 = -\u2603; \u2603 < \u2603; ++\u2603) {
                    cj cj3 = cj2.a(\u2603, \u2603, \u2603);
                    if (!this.f(cj3)) continue;
                    te \u26032 = this.c(cj3);
                    if (\u26032 == null) {
                        this.d(cj3);
                        continue;
                    }
                    \u26032.a(this.f);
                }
            }
        }
    }

    private te c(cj cj2) {
        for (te te2 : this.d) {
            if (te2.d().n() != cj2.n() || te2.d().p() != cj2.p() || Math.abs(te2.d().o() - cj2.o()) > 1) continue;
            return te2;
        }
        for (tf tf2 : this.e) {
            te te2 = tf2.e(cj2);
            if (te2 == null) continue;
            return te2;
        }
        return null;
    }

    private void d(cj cj2) {
        cq cq2 = agh.h(this.b, cj2);
        \u2603 = cq2.d();
        int \u26032 = this.a(cj2, cq2, 5);
        if (\u26032 != (\u2603 = this.a(cj2, \u2603, \u26032 + 1))) {
            this.d.add(new te(cj2, \u26032 < \u2603 ? cq2 : \u2603, this.f));
        }
    }

    private int a(cj cj2, cq cq2, int n2) {
        \u2603 = 0;
        for (\u2603 = 1; \u2603 <= 5; ++\u2603) {
            if (!this.b.i(cj2.a(cq2, \u2603)) || ++\u2603 < n2) continue;
            return \u2603;
        }
        return \u2603;
    }

    private boolean e(cj cj2) {
        for (cj cj3 : this.c) {
            if (!cj3.equals(cj2)) continue;
            return true;
        }
        return false;
    }

    private boolean f(cj cj2) {
        afh afh2 = this.b.p(cj2).c();
        if (afh2 instanceof agh) {
            return afh2.t() == arm.d;
        }
        return false;
    }

    @Override
    public void a(dn dn2) {
        this.f = dn2.f("Tick");
        du du2 = dn2.c("Villages", 10);
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            dn dn3 = du2.b(i2);
            tf \u26032 = new tf();
            \u26032.a(dn3);
            this.e.add(\u26032);
        }
    }

    @Override
    public void b(dn dn22) {
        dn dn22;
        dn22.a("Tick", this.f);
        du du2 = new du();
        for (tf tf2 : this.e) {
            dn dn3 = new dn();
            tf2.b(dn3);
            du2.a(dn3);
        }
        dn22.a("Villages", du2);
    }

    public static String a(anm anm2) {
        return "villages" + anm2.l();
    }
}

