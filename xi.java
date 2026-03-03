/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class xi {
    public List<zx> b = Lists.newArrayList();
    public List<yg> c = Lists.newArrayList();
    public int d;
    private short a;
    private int f = -1;
    private int g;
    private final Set<yg> h = Sets.newHashSet();
    protected List<xn> e = Lists.newArrayList();
    private Set<wn> i = Sets.newHashSet();

    protected yg a(yg yg2) {
        yg2.e = this.c.size();
        this.c.add(yg2);
        this.b.add(null);
        return yg2;
    }

    public void a(xn xn2) {
        if (this.e.contains(xn2)) {
            throw new IllegalArgumentException("Listener already listening");
        }
        this.e.add(xn2);
        xn2.a(this, this.a());
        this.b();
    }

    public void b(xn xn2) {
        this.e.remove(xn2);
    }

    public List<zx> a() {
        ArrayList<zx> arrayList = Lists.newArrayList();
        for (int i2 = 0; i2 < this.c.size(); ++i2) {
            arrayList.add(this.c.get(i2).d());
        }
        return arrayList;
    }

    public void b() {
        for (int i2 = 0; i2 < this.c.size(); ++i2) {
            zx zx2 = this.c.get(i2).d();
            \u2603 = this.b.get(i2);
            if (zx.b(\u2603, zx2)) continue;
            \u2603 = zx2 == null ? null : zx2.k();
            this.b.set(i2, \u2603);
            for (int i3 = 0; i3 < this.e.size(); ++i3) {
                this.e.get(i3).a(this, i2, \u2603);
            }
        }
    }

    public boolean a(wn wn2, int n2) {
        return false;
    }

    public yg a(og og2, int n2) {
        for (\u2603 = 0; \u2603 < this.c.size(); ++\u2603) {
            yg yg2 = this.c.get(\u2603);
            if (!yg2.a(og2, n2)) continue;
            return yg2;
        }
        return null;
    }

    public yg a(int n2) {
        return this.c.get(n2);
    }

    public zx b(wn wn2, int n2) {
        yg yg2 = this.c.get(n2);
        if (yg2 != null) {
            return yg2.d();
        }
        return null;
    }

    public zx a(int n2, int n3, int n42, wn wn2) {
        int n42;
        zx \u26035 = null;
        wm \u26032 = wn2.bi;
        if (n42 == 5) {
            int n5 = this.g;
            this.g = xi.c(n3);
            if ((n5 != 1 || this.g != 2) && n5 != this.g) {
                this.d();
            } else if (\u26032.p() == null) {
                this.d();
            } else if (this.g == 0) {
                this.f = xi.b(n3);
                if (xi.a(this.f, wn2)) {
                    this.g = 1;
                    this.h.clear();
                } else {
                    this.d();
                }
            } else if (this.g == 1) {
                yg yg2 = this.c.get(n2);
                if (yg2 != null && xi.a(yg2, \u26032.p(), true) && yg2.a(\u26032.p()) && \u26032.p().b > this.h.size() && this.b(yg2)) {
                    this.h.add(yg2);
                }
            } else if (this.g == 2) {
                if (!this.h.isEmpty()) {
                    zx zx2 = \u26032.p().k();
                    int \u26033 = \u26032.p().b;
                    for (yg yg3 : this.h) {
                        if (yg3 == null || !xi.a(yg3, \u26032.p(), true) || !yg3.a(\u26032.p()) || \u26032.p().b < this.h.size() || !this.b(yg3)) continue;
                        zx zx3 = zx2.k();
                        int \u26034 = yg3.e() ? yg3.d().b : 0;
                        xi.a(this.h, this.f, zx3, \u26034);
                        if (zx3.b > zx3.c()) {
                            zx3.b = zx3.c();
                        }
                        if (zx3.b > yg3.b(zx3)) {
                            zx3.b = yg3.b(zx3);
                        }
                        \u26033 -= zx3.b - \u26034;
                        yg3.d(zx3);
                    }
                    zx2.b = \u26033;
                    if (zx2.b <= 0) {
                        zx2 = null;
                    }
                    \u26032.b(zx2);
                }
                this.d();
            } else {
                this.d();
            }
        } else if (this.g != 0) {
            this.d();
        } else if (!(n42 != 0 && n42 != 1 || n3 != 0 && n3 != 1)) {
            if (n2 == -999) {
                if (\u26032.p() != null) {
                    if (n3 == 0) {
                        wn2.a(\u26032.p(), true);
                        \u26032.b((zx)null);
                    }
                    if (n3 == 1) {
                        wn2.a(\u26032.p().a(1), true);
                        if (\u26032.p().b == 0) {
                            \u26032.b((zx)null);
                        }
                    }
                }
            } else if (n42 == 1) {
                if (n2 < 0) {
                    return null;
                }
                yg yg4 = this.c.get(n2);
                if (yg4 != null && yg4.a(wn2) && (\u2603 = this.b(wn2, n2)) != null) {
                    zw zw2 = \u2603.b();
                    \u26035 = \u2603.k();
                    if (yg4.d() != null && yg4.d().b() == zw2) {
                        this.a(n2, n3, true, wn2);
                    }
                }
            } else {
                if (n2 < 0) {
                    return null;
                }
                yg yg5 = this.c.get(n2);
                if (yg5 != null) {
                    zx \u26037 = yg5.d();
                    zx4 = \u26032.p();
                    if (\u26037 != null) {
                        \u26035 = \u26037.k();
                    }
                    if (\u26037 == null) {
                        if (zx4 != null && yg5.a(zx4)) {
                            zx zx4;
                            int n6 = n7 = n3 == 0 ? zx4.b : 1;
                            if (n7 > yg5.b(zx4)) {
                                int n7 = yg5.b(zx4);
                            }
                            if (zx4.b >= n7) {
                                yg5.d(zx4.a(n7));
                            }
                            if (zx4.b == 0) {
                                \u26032.b((zx)null);
                            }
                        }
                    } else if (yg5.a(wn2)) {
                        if (zx4 == null) {
                            int n8 = n3 == 0 ? \u26037.b : (\u26037.b + 1) / 2;
                            zx \u26036 = yg5.a(n8);
                            \u26032.b(\u26036);
                            if (\u26037.b == 0) {
                                yg5.d(null);
                            }
                            yg5.a(wn2, \u26032.p());
                        } else if (yg5.a(zx4)) {
                            if (\u26037.b() != zx4.b() || \u26037.i() != zx4.i() || !zx.a(\u26037, zx4)) {
                                if (zx4.b <= yg5.b(zx4)) {
                                    yg5.d(zx4);
                                    \u26032.b(\u26037);
                                }
                            } else {
                                int n9 = \u2603 = n3 == 0 ? zx4.b : 1;
                                if (\u2603 > yg5.b(zx4) - \u26037.b) {
                                    \u2603 = yg5.b(zx4) - \u26037.b;
                                }
                                if (\u2603 > zx4.c() - \u26037.b) {
                                    \u2603 = zx4.c() - \u26037.b;
                                }
                                zx4.a(\u2603);
                                if (zx4.b == 0) {
                                    \u26032.b((zx)null);
                                }
                                \u26037.b += \u2603;
                            }
                        } else if (\u26037.b() == zx4.b() && zx4.c() > 1 && (!\u26037.f() || \u26037.i() == zx4.i()) && zx.a(\u26037, zx4) && (\u2603 = \u26037.b) > 0 && \u2603 + zx4.b <= zx4.c()) {
                            zx4.b += \u2603;
                            \u26037 = yg5.a(\u2603);
                            if (\u26037.b == 0) {
                                yg5.d(null);
                            }
                            yg5.a(wn2, \u26032.p());
                        }
                    }
                    yg5.f();
                }
            }
        } else if (n42 == 2 && n3 >= 0 && n3 < 9) {
            yg yg6 = this.c.get(n2);
            if (yg6.a(wn2)) {
                zx zx5 = \u26032.a(n3);
                boolean \u26038 = zx5 == null || yg6.d == \u26032 && yg6.a(zx5);
                int \u26039 = -1;
                if (!\u26038) {
                    \u26039 = \u26032.j();
                    \u26038 |= \u26039 > -1;
                }
                if (yg6.e() && \u26038) {
                    \u2603 = yg6.d();
                    \u26032.a(n3, \u2603.k());
                    if (yg6.d == \u26032 && yg6.a(zx5) || zx5 == null) {
                        yg6.a(\u2603.b);
                        yg6.d(zx5);
                        yg6.a(wn2, \u2603);
                    } else if (\u26039 > -1) {
                        \u26032.a(zx5);
                        yg6.a(\u2603.b);
                        yg6.d(null);
                        yg6.a(wn2, \u2603);
                    }
                } else if (!yg6.e() && zx5 != null && yg6.a(zx5)) {
                    \u26032.a(n3, null);
                    yg6.d(zx5);
                }
            }
        } else if (n42 == 3 && wn2.bA.d && \u26032.p() == null && n2 >= 0) {
            yg yg7 = this.c.get(n2);
            if (yg7 != null && yg7.e()) {
                zx zx6 = yg7.d().k();
                zx6.b = zx6.c();
                \u26032.b(zx6);
            }
        } else if (n42 == 4 && \u26032.p() == null && n2 >= 0) {
            yg yg8 = this.c.get(n2);
            if (yg8 != null && yg8.e() && yg8.a(wn2)) {
                zx zx7 = yg8.a(n3 == 0 ? 1 : yg8.d().b);
                yg8.a(wn2, zx7);
                wn2.a(zx7, true);
            }
        } else if (n42 == 6 && n2 >= 0) {
            yg yg9 = this.c.get(n2);
            zx \u260310 = \u26032.p();
            if (!(\u260310 == null || yg9 != null && yg9.e() && yg9.a(wn2))) {
                int n10 = n3 == 0 ? 0 : this.c.size() - 1;
                \u2603 = n3 == 0 ? 1 : -1;
                for (\u2603 = 0; \u2603 < 2; ++\u2603) {
                    for (\u2603 = n10; \u2603 >= 0 && \u2603 < this.c.size() && \u260310.b < \u260310.c(); \u2603 += \u2603) {
                        yg yg10 = this.c.get(\u2603);
                        if (!yg10.e() || !xi.a(yg10, \u260310, true) || !yg10.a(wn2) || !this.a(\u260310, yg10) || \u2603 == 0 && yg10.d().b == yg10.d().c()) continue;
                        int \u260311 = Math.min(\u260310.c() - \u260310.b, yg10.d().b);
                        zx \u260312 = yg10.a(\u260311);
                        \u260310.b += \u260311;
                        if (\u260312.b <= 0) {
                            yg10.d(null);
                        }
                        yg10.a(wn2, \u260312);
                    }
                }
            }
            this.b();
        }
        return \u26035;
    }

    public boolean a(zx zx2, yg yg2) {
        return true;
    }

    protected void a(int n2, int n3, boolean bl2, wn wn2) {
        this.a(n2, n3, 1, wn2);
    }

    public void b(wn wn2) {
        wm wm2 = wn2.bi;
        if (wm2.p() != null) {
            wn2.a(wm2.p(), false);
            wm2.b((zx)null);
        }
    }

    public void a(og og2) {
        this.b();
    }

    public void a(int n2, zx zx2) {
        this.a(n2).d(zx2);
    }

    public void a(zx[] zxArray) {
        for (int i2 = 0; i2 < zxArray.length; ++i2) {
            this.a(i2).d(zxArray[i2]);
        }
    }

    public void b(int n2, int n3) {
    }

    public short a(wm wm2) {
        this.a = (short)(this.a + 1);
        return this.a;
    }

    public boolean c(wn wn2) {
        return !this.i.contains(wn2);
    }

    public void a(wn wn2, boolean bl2) {
        if (bl2) {
            this.i.remove(wn2);
        } else {
            this.i.add(wn2);
        }
    }

    public abstract boolean a(wn var1);

    protected boolean a(zx zx22, int n2, int n3, boolean bl22) {
        boolean bl22;
        zx zx22;
        boolean bl3;
        zx \u26032;
        yg yg2;
        bl3 = false;
        int n4 = n2;
        if (bl22) {
            n4 = n3 - 1;
        }
        if (zx22.d()) {
            while (zx22.b > 0 && (!bl22 && n4 < n3 || bl22 && n4 >= n2)) {
                yg2 = this.c.get(n4);
                \u26032 = yg2.d();
                if (\u26032 != null && \u26032.b() == zx22.b() && (!zx22.f() || zx22.i() == \u26032.i()) && zx.a(zx22, \u26032)) {
                    int n5 = \u26032.b + zx22.b;
                    if (n5 <= zx22.c()) {
                        zx22.b = 0;
                        \u26032.b = n5;
                        yg2.f();
                        bl3 = true;
                    } else if (\u26032.b < zx22.c()) {
                        zx22.b -= zx22.c() - \u26032.b;
                        \u26032.b = zx22.c();
                        yg2.f();
                        bl3 = true;
                    }
                }
                if (bl22) {
                    --n4;
                    continue;
                }
                ++n4;
            }
        }
        if (zx22.b > 0) {
            n4 = bl22 ? n3 - 1 : n2;
            while (!bl22 && n4 < n3 || bl22 && n4 >= n2) {
                yg2 = this.c.get(n4);
                \u26032 = yg2.d();
                if (\u26032 == null) {
                    yg2.d(zx22.k());
                    yg2.f();
                    zx22.b = 0;
                    bl3 = true;
                    break;
                }
                if (bl22) {
                    --n4;
                    continue;
                }
                ++n4;
            }
        }
        return bl3;
    }

    public static int b(int n2) {
        return n2 >> 2 & 3;
    }

    public static int c(int n2) {
        return n2 & 3;
    }

    public static int d(int n2, int n3) {
        return n2 & 3 | (n3 & 3) << 2;
    }

    public static boolean a(int n2, wn wn2) {
        if (n2 == 0) {
            return true;
        }
        if (n2 == 1) {
            return true;
        }
        return n2 == 2 && wn2.bA.d;
    }

    protected void d() {
        this.g = 0;
        this.h.clear();
    }

    public static boolean a(yg yg2, zx zx2, boolean bl2) {
        boolean bl3 = \u2603 = yg2 == null || !yg2.e();
        if (yg2 != null && yg2.e() && zx2 != null && zx2.a(yg2.d()) && zx.a(yg2.d(), zx2)) {
            \u2603 |= yg2.d().b + (bl2 ? 0 : zx2.b) <= zx2.c();
        }
        return \u2603;
    }

    public static void a(Set<yg> set, int n2, zx zx2, int n3) {
        switch (n2) {
            case 0: {
                zx2.b = ns.d((float)zx2.b / (float)set.size());
                break;
            }
            case 1: {
                zx2.b = 1;
                break;
            }
            case 2: {
                zx2.b = zx2.b().j();
            }
        }
        zx2.b += n3;
    }

    public boolean b(yg yg2) {
        return true;
    }

    public static int a(akw akw2) {
        if (akw2 instanceof og) {
            return xi.b((og)((Object)akw2));
        }
        return 0;
    }

    public static int b(og og22) {
        og og22;
        if (og22 == null) {
            return 0;
        }
        int n2 = 0;
        float \u26032 = 0.0f;
        for (\u2603 = 0; \u2603 < og22.o_(); ++\u2603) {
            zx zx2 = og22.a(\u2603);
            if (zx2 == null) continue;
            \u26032 += (float)zx2.b / (float)Math.min(og22.q_(), zx2.c());
            ++n2;
        }
        return ns.d((\u26032 /= (float)og22.o_()) * 14.0f) + (n2 > 0 ? 1 : 0);
    }
}

