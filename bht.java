/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.nio.FloatBuffer;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class bht {
    private adm d;
    private final bfr e;
    public static int a;
    private cj f;
    public bhq b = bhq.a;
    private final ReentrantLock g = new ReentrantLock();
    private final ReentrantLock h = new ReentrantLock();
    private bhn i = null;
    private final Set<akw> j = Sets.newHashSet();
    private final int k;
    private final FloatBuffer l = avd.h(16);
    private final bmt[] m = new bmt[adf.values().length];
    public aug c;
    private int n = -1;
    private boolean o = true;
    private EnumMap<cq, cj> p = Maps.newEnumMap(cq.class);

    public bht(adm adm2, bfr bfr2, cj cj2, int n2) {
        this.d = adm2;
        this.e = bfr2;
        this.k = n2;
        if (!cj2.equals(this.j())) {
            this.a(cj2);
        }
        if (bqs.f()) {
            for (\u2603 = 0; \u2603 < adf.values().length; ++\u2603) {
                this.m[\u2603] = new bmt(bms.a);
            }
        }
    }

    public boolean a(int n2) {
        if (this.n == n2) {
            return false;
        }
        this.n = n2;
        return true;
    }

    public bmt b(int n2) {
        return this.m[n2];
    }

    public void a(cj cj2) {
        this.h();
        this.f = cj2;
        this.c = new aug(cj2, cj2.a(16, 16, 16));
        for (cq cq2 : cq.values()) {
            this.p.put(cq2, cj2.a(cq2, 16));
        }
        this.m();
    }

    public void a(float f2, float f3, float f4, bhn bhn2) {
        bhq bhq2 = bhn2.c();
        if (bhq2.c() == null || bhq2.b(adf.d)) {
            return;
        }
        this.a(bhn2.d().a(adf.d), this.f);
        bhn2.d().a(adf.d).a(bhq2.c());
        this.a(adf.d, f2, f3, f4, bhn2.d().a(adf.d), bhq2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void b(float f2, float f3, float f4, bhn bhn22) {
        Object \u26039;
        bff bff2;
        bhq bhq2 = new bhq();
        boolean \u26032 = true;
        cj \u26033 = this.f;
        cj \u26034 = \u26033.a(15, 15, 15);
        bhn22.f().lock();
        try {
            if (bhn22.a() != bhn.a.b) {
                return;
            }
            bff2 = new bff(this.d, \u26033.a(-1, -1, -1), \u26034.a(1, 1, 1), 1);
            bhn22.a(bhq2);
        }
        finally {
            bhn22.f().unlock();
        }
        bhw bhw2 = new bhw();
        HashSet<akw> \u26035 = Sets.newHashSet();
        if (!bff2.W()) {
            ++a;
            boolean[] object = new boolean[adf.values().length];
            \u26039 = ave.A().ae();
            for (cj.a a2 : cj.b(\u26033, \u26034)) {
                alz alz2 = bff2.p(a2);
                afh afh2 = alz2.c();
                if (afh2.c()) {
                    bhw2.a(a2);
                }
                if (afh2.z()) {
                    akw akw2 = bff2.s(new cj(a2));
                    bhd \u26037 = bhc.a.b(akw2);
                    if (akw2 != null && \u26037 != null) {
                        bhq2.a(akw2);
                        if (\u26037.a()) {
                            \u26035.add(akw2);
                        }
                    }
                }
                adf object2 = afh2.m();
                int n2 = object2.ordinal();
                if (afh2.b() == -1) continue;
                bfd \u26038 = bhn22.d().a(n2);
                if (!bhq2.d(object2)) {
                    bhq2.c(object2);
                    this.a(\u26038, \u26033);
                }
                int n3 = n2;
                object[n3] = object[n3] | ((bgd)\u26039).a(alz2, (cj)a2, bff2, \u26038);
            }
            for (adf adf2 : adf.values()) {
                if (object[adf2.ordinal()]) {
                    bhq2.a(adf2);
                }
                if (!bhq2.d(adf2)) continue;
                this.a(adf2, f2, f3, f4, bhn22.d().a(adf2), bhq2);
            }
        }
        bhq2.a(bhw2.a());
        this.g.lock();
        try {
            HashSet<akw> hashSet = Sets.newHashSet(\u26035);
            \u26039 = Sets.newHashSet(this.j);
            hashSet.removeAll(this.j);
            \u26039.removeAll(\u26035);
            this.j.clear();
            this.j.addAll(\u26035);
            this.e.a((Collection<akw>)\u26039, hashSet);
        }
        finally {
            this.g.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void b() {
        this.g.lock();
        try {
            if (this.i != null && this.i.a() != bhn.a.d) {
                this.i.e();
                this.i = null;
            }
        }
        finally {
            this.g.unlock();
        }
    }

    public ReentrantLock c() {
        return this.g;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public bhn d() {
        this.g.lock();
        try {
            this.b();
            bhn bhn2 = this.i = new bhn(this, bhn.b.a);
            return bhn2;
        }
        finally {
            this.g.unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public bhn e() {
        this.g.lock();
        try {
            if (this.i != null && this.i.a() == bhn.a.a) {
                bhn bhn2 = null;
                return bhn2;
            }
            if (this.i != null && this.i.a() != bhn.a.d) {
                this.i.e();
                this.i = null;
            }
            this.i = new bhn(this, bhn.b.b);
            this.i.a(this.b);
            bhn bhn3 = this.i;
            return bhn3;
        }
        finally {
            this.g.unlock();
        }
    }

    private void a(bfd bfd2, cj cj2) {
        bfd2.a(7, bms.a);
        bfd2.c((double)(-cj2.n()), (double)(-cj2.o()), (double)(-cj2.p()));
    }

    private void a(adf adf2, float f2, float f3, float f4, bfd bfd2, bhq bhq2) {
        if (adf2 == adf.d && !bhq2.b(adf2)) {
            bfd2.a(f2, f3, f4);
            bhq2.a(bfd2.a());
        }
        bfd2.e();
    }

    private void m() {
        bfl.E();
        bfl.D();
        float f2 = 1.000001f;
        bfl.b(-8.0f, -8.0f, -8.0f);
        bfl.a(f2, f2, f2);
        bfl.b(8.0f, 8.0f, 8.0f);
        bfl.a(2982, this.l);
        bfl.F();
    }

    public void f() {
        bfl.a(this.l);
    }

    public bhq g() {
        return this.b;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(bhq bhq2) {
        this.h.lock();
        try {
            this.b = bhq2;
        }
        finally {
            this.h.unlock();
        }
    }

    public void h() {
        this.b();
        this.b = bhq.a;
    }

    public void a() {
        this.h();
        this.d = null;
        for (int i2 = 0; i2 < adf.values().length; ++i2) {
            if (this.m[i2] == null) continue;
            this.m[i2].c();
        }
    }

    public cj j() {
        return this.f;
    }

    public void a(boolean bl2) {
        this.o = bl2;
    }

    public boolean l() {
        return this.o;
    }

    public cj a(cq cq2) {
        return this.p.get(cq2);
    }
}

