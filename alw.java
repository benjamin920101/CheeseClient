/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public class alw {
    private final adm a;
    private final cj b;
    private final cj c;
    private final cq d;
    private final List<cj> e = Lists.newArrayList();
    private final List<cj> f = Lists.newArrayList();

    public alw(adm adm2, cj cj2, cq cq2, boolean bl2) {
        this.a = adm2;
        this.b = cj2;
        if (bl2) {
            this.d = cq2;
            this.c = cj2.a(cq2);
        } else {
            this.d = cq2.d();
            this.c = cj2.a(cq2, 2);
        }
    }

    public boolean a() {
        this.e.clear();
        this.f.clear();
        afh afh2 = this.a.p(this.c).c();
        if (!als.a(afh2, this.a, this.c, this.d, false)) {
            if (afh2.k() != 1) {
                return false;
            }
            this.f.add(this.c);
            return true;
        }
        if (!this.a(this.c)) {
            return false;
        }
        for (int i2 = 0; i2 < this.e.size(); ++i2) {
            cj cj2 = this.e.get(i2);
            if (this.a.p(cj2).c() != afi.cE || this.b(cj2)) continue;
            return false;
        }
        return true;
    }

    private boolean a(cj cj2) {
        int n2;
        afh \u26034 = this.a.p(cj2).c();
        if (\u26034.t() == arm.a) {
            return true;
        }
        if (!als.a(\u26034, this.a, cj2, this.d, false)) {
            return true;
        }
        if (cj2.equals(this.b)) {
            return true;
        }
        if (this.e.contains(cj2)) {
            return true;
        }
        int \u26032 = 1;
        if (\u26032 + this.e.size() > 12) {
            return false;
        }
        while (\u26034 == afi.cE && (\u26034 = this.a.p(\u2603 = cj2.a(this.d.d(), \u26032)).c()).t() != arm.a && als.a(\u26034, this.a, \u2603, this.d, false) && !\u2603.equals(this.b)) {
            if (++\u26032 + this.e.size() <= 12) continue;
            return false;
        }
        int \u26033 = 0;
        for (n2 = \u26032 - 1; n2 >= 0; --n2) {
            this.e.add(cj2.a(this.d.d(), n2));
            ++\u26033;
        }
        n2 = 1;
        while (true) {
            cj cj3;
            if ((\u2603 = this.e.indexOf(cj3 = cj2.a(this.d, n2))) > -1) {
                this.a(\u26033, \u2603);
                for (\u2603 = 0; \u2603 <= \u2603 + \u26033; ++\u2603) {
                    cj cj4 = this.e.get(\u2603);
                    if (this.a.p(cj4).c() != afi.cE || this.b(cj4)) continue;
                    return false;
                }
                return true;
            }
            \u26034 = this.a.p(cj3).c();
            if (\u26034.t() == arm.a) {
                return true;
            }
            if (!als.a(\u26034, this.a, cj3, this.d, true) || cj3.equals(this.b)) {
                return false;
            }
            if (\u26034.k() == 1) {
                this.f.add(cj3);
                return true;
            }
            if (this.e.size() >= 12) {
                return false;
            }
            this.e.add(cj3);
            ++\u26033;
            ++n2;
        }
    }

    private void a(int n2, int n3) {
        ArrayList<cj> arrayList = Lists.newArrayList();
        \u2603 = Lists.newArrayList();
        \u2603 = Lists.newArrayList();
        arrayList.addAll(this.e.subList(0, n3));
        \u2603.addAll(this.e.subList(this.e.size() - n2, this.e.size()));
        \u2603.addAll(this.e.subList(n3, this.e.size() - n2));
        this.e.clear();
        this.e.addAll(arrayList);
        this.e.addAll(\u2603);
        this.e.addAll(\u2603);
    }

    private boolean b(cj cj2) {
        for (cq cq2 : cq.values()) {
            if (cq2.k() == this.d.k() || this.a(cj2.a(cq2))) continue;
            return false;
        }
        return true;
    }

    public List<cj> c() {
        return this.e;
    }

    public List<cj> d() {
        return this.f;
    }
}

