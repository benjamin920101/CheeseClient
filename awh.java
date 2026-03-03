/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.mojang.authlib.GameProfile;
import java.util.Comparator;
import java.util.List;

public class awh
extends avp {
    private static final Ordering<bdc> a = Ordering.from(new a());
    private final ave f;
    private final avo g;
    private eu h;
    private eu i;
    private long j;
    private boolean k;

    public awh(ave ave2, avo avo2) {
        this.f = ave2;
        this.g = avo2;
    }

    public String a(bdc bdc2) {
        if (bdc2.k() != null) {
            return bdc2.k().d();
        }
        return aul.a(bdc2.i(), bdc2.a().getName());
    }

    public void a(boolean bl2) {
        if (bl2 && !this.k) {
            this.j = ave.J();
        }
        this.k = bl2;
    }

    public void a(int n22, auo auo2, auk auk2) {
        int n3;
        int n22;
        int n4;
        int n5;
        bcy bcy2 = this.f.h.a;
        List<bdc> \u26032 = a.sortedCopy(bcy2.d());
        int \u26033 = 0;
        int \u26034 = 0;
        for (bdc bdc2 : \u26032) {
            int n6 = this.f.k.a(this.a(bdc2));
            \u26033 = Math.max(\u26033, n6);
            if (auk2 == null || auk2.e() == auu.a.b) continue;
            n6 = this.f.k.a(" " + auo2.c(bdc2.a().getName(), auk2).c());
            \u26034 = Math.max(\u26034, n6);
        }
        \u26032 = \u26032.subList(0, Math.min(\u26032.size(), 80));
        \u2603 = n5 = \u26032.size();
        n6 = 1;
        while (\u2603 > 20) {
            \u2603 = (n5 + ++n6 - 1) / n6;
        }
        boolean bl2 = \u2603 = this.f.E() || this.f.u().a().f();
        \u2603 = auk2 != null ? (auk2.e() == auu.a.b ? 90 : \u26034) : 0;
        \u2603 = Math.min(n6 * ((\u2603 ? 9 : 0) + \u26033 + \u2603 + 13), n22 - 50) / n6;
        \u2603 = n22 / 2 - (\u2603 * n6 + (n6 - 1) * 5) / 2;
        n7 = 10;
        n4 = \u2603 * n6 + (n6 - 1) * 5;
        List<String> \u26035 = null;
        List<String> \u26036 = null;
        if (this.i != null) {
            \u26035 = this.f.k.c(this.i.d(), n22 - 50);
            for (String string : \u26035) {
                n4 = Math.max(n4, this.f.k.a(string));
            }
        }
        if (this.h != null) {
            \u26036 = this.f.k.c(this.h.d(), n22 - 50);
            for (String string : \u26036) {
                n4 = Math.max(n4, this.f.k.a(string));
            }
        }
        if (\u26035 != null) {
            int n7;
            awh.a(n22 / 2 - n4 / 2 - 1, n7 - 1, n22 / 2 + n4 / 2 + 1, n7 + \u26035.size() * this.f.k.a, Integer.MIN_VALUE);
            for (String string : \u26035) {
                n3 = this.f.k.a(string);
                this.f.k.a(string, (float)(n22 / 2 - n3 / 2), (float)n7, -1);
                n7 += this.f.k.a;
            }
            ++n7;
        }
        awh.a(n22 / 2 - n4 / 2 - 1, n7 - 1, n22 / 2 + n4 / 2 + 1, n7 + \u2603 * 9, Integer.MIN_VALUE);
        for (\u2603 = 0; \u2603 < n5; ++\u2603) {
            int \u26039;
            \u2603 = \u2603 / \u2603;
            n3 = \u2603 % \u2603;
            \u2603 = \u2603 + \u2603 * \u2603 + \u2603 * 5;
            \u2603 = n7 + n3 * 9;
            awh.a(\u2603, \u2603, \u2603 + \u2603, \u2603 + 8, 0x20FFFFFF);
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            bfl.d();
            bfl.l();
            bfl.a(770, 771, 1, 0);
            if (\u2603 >= \u26032.size()) continue;
            bdc bdc3 = \u26032.get(\u2603);
            String \u26037 = this.a(bdc3);
            GameProfile \u26038 = bdc3.a();
            if (\u2603) {
                wn wn2 = this.f.f.b(\u26038.getId());
                \u26039 = wn2 != null && wn2.a(wo.a) && (\u26038.getName().equals("Dinnerbone") || \u26038.getName().equals("Grumm")) ? 1 : 0;
                this.f.P().a(bdc3.g());
                int \u260310 = 8 + (\u26039 != 0 ? 8 : 0);
                int \u260311 = 8 * (\u26039 != 0 ? -1 : 1);
                avp.a(\u2603, \u2603, 8.0f, \u260310, 8, \u260311, 8, 8, 64.0f, 64.0f);
                if (wn2 != null && wn2.a(wo.g)) {
                    int n8 = 8 + (\u26039 != 0 ? 8 : 0);
                    \u2603 = 8 * (\u26039 != 0 ? -1 : 1);
                    avp.a(\u2603, \u2603, 40.0f, n8, 8, \u2603, 8, 8, 64.0f, 64.0f);
                }
                \u2603 += 9;
            }
            if (bdc3.b() == adp.a.e) {
                \u26037 = (Object)((Object)a.u) + \u26037;
                this.f.k.a(\u26037, (float)\u2603, (float)\u2603, -1862270977);
            } else {
                this.f.k.a(\u26037, (float)\u2603, (float)\u2603, -1);
            }
            if (auk2 != null && bdc3.b() != adp.a.e && (\u26039 = (\u2603 = \u2603 + \u26033 + 1) + \u2603) - \u2603 > 5) {
                this.a(auk2, \u2603, \u26038.getName(), \u2603, \u26039, bdc3);
            }
            this.a(\u2603, \u2603 - (\u2603 ? 9 : 0), \u2603, bdc3);
        }
        if (\u26036 != null) {
            awh.a(n22 / 2 - n4 / 2 - 1, (n7 += \u2603 * 9 + 1) - 1, n22 / 2 + n4 / 2 + 1, n7 + \u26036.size() * this.f.k.a, Integer.MIN_VALUE);
            for (String string : \u26036) {
                n3 = this.f.k.a(string);
                this.f.k.a(string, (float)(n22 / 2 - n3 / 2), (float)n7, -1);
                n7 += this.f.k.a;
            }
        }
    }

    protected void a(int n2, int n3, int n4, bdc bdc2) {
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.f.P().a(d);
        int n5 = 0;
        \u2603 = 0;
        \u2603 = bdc2.c() < 0 ? 5 : (bdc2.c() < 150 ? 0 : (bdc2.c() < 300 ? 1 : (bdc2.c() < 600 ? 2 : (bdc2.c() < 1000 ? 3 : 4))));
        this.e += 100.0f;
        this.b(n3 + n2 - 11, n4, 0 + n5 * 10, 176 + \u2603 * 8, 10, 8);
        this.e -= 100.0f;
    }

    private void a(auk auk2, int n2, String string, int n3, int n4, bdc bdc2) {
        int n5 = auk2.a().c(string, auk2).c();
        if (auk2.e() == auu.a.b) {
            this.f.P().a(d);
            if (this.j == bdc2.p()) {
                if (n5 < bdc2.l()) {
                    bdc2.a(ave.J());
                    bdc2.b((long)(this.g.e() + 20));
                } else if (n5 > bdc2.l()) {
                    bdc2.a(ave.J());
                    bdc2.b((long)(this.g.e() + 10));
                }
            }
            if (ave.J() - bdc2.n() > 1000L || this.j != bdc2.p()) {
                bdc2.b(n5);
                bdc2.c(n5);
                bdc2.a(ave.J());
            }
            bdc2.c(this.j);
            bdc2.b(n5);
            \u2603 = ns.f((float)Math.max(n5, bdc2.m()) / 2.0f);
            \u2603 = Math.max(ns.f(n5 / 2), Math.max(ns.f(bdc2.m() / 2), 10));
            boolean bl2 = \u2603 = bdc2.o() > (long)this.g.e() && (bdc2.o() - (long)this.g.e()) / 3L % 2L == 1L;
            if (\u2603 > 0) {
                float f2 = Math.min((float)(n4 - n3 - 4) / (float)\u2603, 9.0f);
                if (f2 > 3.0f) {
                    int n6;
                    for (n6 = \u2603; n6 < \u2603; ++n6) {
                        this.a((float)n3 + (float)n6 * f2, (float)n2, \u2603 ? 25 : 16, 0, 9, 9);
                    }
                    for (n6 = 0; n6 < \u2603; ++n6) {
                        this.a((float)n3 + (float)n6 * f2, (float)n2, \u2603 ? 25 : 16, 0, 9, 9);
                        if (\u2603) {
                            if (n6 * 2 + 1 < bdc2.m()) {
                                this.a((float)n3 + (float)n6 * f2, (float)n2, 70, 0, 9, 9);
                            }
                            if (n6 * 2 + 1 == bdc2.m()) {
                                this.a((float)n3 + (float)n6 * f2, (float)n2, 79, 0, 9, 9);
                            }
                        }
                        if (n6 * 2 + 1 < n5) {
                            this.a((float)n3 + (float)n6 * f2, (float)n2, n6 >= 10 ? 160 : 52, 0, 9, 9);
                        }
                        if (n6 * 2 + 1 != n5) continue;
                        this.a((float)n3 + (float)n6 * f2, (float)n2, n6 >= 10 ? 169 : 61, 0, 9, 9);
                    }
                } else {
                    float \u26032 = ns.a((float)n5 / 20.0f, 0.0f, 1.0f);
                    \u2603 = (int)((1.0f - \u26032) * 255.0f) << 16 | (int)(\u26032 * 255.0f) << 8;
                    String \u26033 = "" + (float)n5 / 2.0f;
                    if (n4 - this.f.k.a(\u26033 + "hp") >= n3) {
                        \u26033 = \u26033 + "hp";
                    }
                    this.f.k.a(\u26033, (float)((n4 + n3) / 2 - this.f.k.a(\u26033) / 2), (float)n2, \u2603);
                }
            }
        } else {
            String string2 = (Object)((Object)a.o) + "" + n5;
            this.f.k.a(string2, (float)(n4 - this.f.k.a(string2)), (float)n2, 0xFFFFFF);
        }
    }

    public void a(eu eu2) {
        this.h = eu2;
    }

    public void b(eu eu2) {
        this.i = eu2;
    }

    public void a() {
        this.i = null;
        this.h = null;
    }

    static class a
    implements Comparator<bdc> {
        private a() {
        }

        public int a(bdc bdc2, bdc bdc3) {
            aul aul2 = bdc2.i();
            \u2603 = bdc3.i();
            return ComparisonChain.start().compareTrueFirst(bdc2.b() != adp.a.e, bdc3.b() != adp.a.e).compare((Comparable<?>)((Object)(aul2 != null ? aul2.b() : "")), (Comparable<?>)((Object)(\u2603 != null ? \u2603.b() : ""))).compare((Comparable<?>)((Object)bdc2.a().getName()), (Comparable<?>)((Object)bdc3.a().getName())).result();
        }

        @Override
        public /* synthetic */ int compare(Object object, Object object2) {
            return this.a((bdc)object, (bdc)object2);
        }
    }
}

