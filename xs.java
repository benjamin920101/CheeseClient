/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class xs
extends xi {
    public og a = new oq("Enchant", true, 2){

        @Override
        public int q_() {
            return 64;
        }

        @Override
        public void p_() {
            super.p_();
            xs.this.a(this);
        }
    };
    private adm i;
    private cj j;
    private Random k = new Random();
    public int f;
    public int[] g = new int[3];
    public int[] h = new int[]{-1, -1, -1};

    public xs(wm wm2, adm adm2) {
        this(wm2, adm2, cj.a);
    }

    public xs(wm wm2, adm adm2, cj cj2) {
        int n2;
        this.i = adm2;
        this.j = cj2;
        this.f = wm2.d.cj();
        this.a(new yg(this.a, 0, 15, 47){

            @Override
            public boolean a(zx zx2) {
                return true;
            }

            @Override
            public int a() {
                return 1;
            }
        });
        this.a(new yg(this.a, 1, 35, 47){

            @Override
            public boolean a(zx zx2) {
                return zx2.b() == zy.aW && zd.a(zx2.i()) == zd.l;
            }
        });
        for (n2 = 0; n2 < 3; ++n2) {
            for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                this.a(new yg(wm2, \u2603 + n2 * 9 + 9, 8 + \u2603 * 18, 84 + n2 * 18));
            }
        }
        for (n2 = 0; n2 < 9; ++n2) {
            this.a(new yg(wm2, n2, 8 + n2 * 18, 142));
        }
    }

    @Override
    public void a(xn xn2) {
        super.a(xn2);
        xn2.a((xi)this, 0, this.g[0]);
        xn2.a((xi)this, 1, this.g[1]);
        xn2.a((xi)this, 2, this.g[2]);
        xn2.a((xi)this, 3, this.f & 0xFFFFFFF0);
        xn2.a((xi)this, 4, this.h[0]);
        xn2.a((xi)this, 5, this.h[1]);
        xn2.a((xi)this, 6, this.h[2]);
    }

    @Override
    public void b() {
        super.b();
        for (int i2 = 0; i2 < this.e.size(); ++i2) {
            xn xn2 = (xn)this.e.get(i2);
            xn2.a((xi)this, 0, this.g[0]);
            xn2.a((xi)this, 1, this.g[1]);
            xn2.a((xi)this, 2, this.g[2]);
            xn2.a((xi)this, 3, this.f & 0xFFFFFFF0);
            xn2.a((xi)this, 4, this.h[0]);
            xn2.a((xi)this, 5, this.h[1]);
            xn2.a((xi)this, 6, this.h[2]);
        }
    }

    @Override
    public void b(int n2, int n3) {
        if (n2 >= 0 && n2 <= 2) {
            this.g[n2] = n3;
        } else if (n2 == 3) {
            this.f = n3;
        } else if (n2 >= 4 && n2 <= 6) {
            this.h[n2 - 4] = n3;
        } else {
            super.b(n2, n3);
        }
    }

    @Override
    public void a(og og2) {
        if (og2 == this.a) {
            zx zx2 = og2.a(0);
            if (zx2 == null || !zx2.v()) {
                for (int i2 = 0; i2 < 3; ++i2) {
                    this.g[i2] = 0;
                    this.h[i2] = -1;
                }
            } else if (!this.i.D) {
                int n2 = 0;
                for (\u2603 = -1; \u2603 <= 1; ++\u2603) {
                    for (\u2603 = -1; \u2603 <= 1; ++\u2603) {
                        if (\u2603 == 0 && \u2603 == 0 || !this.i.d(this.j.a(\u2603, 0, \u2603)) || !this.i.d(this.j.a(\u2603, 1, \u2603))) continue;
                        if (this.i.p(this.j.a(\u2603 * 2, 0, \u2603 * 2)).c() == afi.X) {
                            ++n2;
                        }
                        if (this.i.p(this.j.a(\u2603 * 2, 1, \u2603 * 2)).c() == afi.X) {
                            ++n2;
                        }
                        if (\u2603 == 0 || \u2603 == 0) continue;
                        if (this.i.p(this.j.a(\u2603 * 2, 0, \u2603)).c() == afi.X) {
                            ++n2;
                        }
                        if (this.i.p(this.j.a(\u2603 * 2, 1, \u2603)).c() == afi.X) {
                            ++n2;
                        }
                        if (this.i.p(this.j.a(\u2603, 0, \u2603 * 2)).c() == afi.X) {
                            ++n2;
                        }
                        if (this.i.p(this.j.a(\u2603, 1, \u2603 * 2)).c() != afi.X) continue;
                        ++n2;
                    }
                }
                this.k.setSeed(this.f);
                for (\u2603 = 0; \u2603 < 3; ++\u2603) {
                    this.g[\u2603] = ack.a(this.k, \u2603, n2, zx2);
                    this.h[\u2603] = -1;
                    if (this.g[\u2603] >= \u2603 + 1) continue;
                    this.g[\u2603] = 0;
                }
                for (\u2603 = 0; \u2603 < 3; ++\u2603) {
                    if (this.g[\u2603] <= 0 || (\u2603 = this.a(zx2, \u2603, this.g[\u2603])) == null || \u2603.isEmpty()) continue;
                    acl acl2 = \u2603.get(this.k.nextInt(\u2603.size()));
                    this.h[\u2603] = acl2.b.B | acl2.c << 8;
                }
                this.b();
            }
        }
    }

    @Override
    public boolean a(wn wn22, int n2) {
        zx zx2 = this.a.a(0);
        \u2603 = this.a.a(1);
        int \u26032 = n2 + 1;
        if (!(\u2603 != null && \u2603.b >= \u26032 || wn22.bA.d)) {
            return false;
        }
        if (this.g[n2] > 0 && zx2 != null && (wn22.bB >= \u26032 && wn22.bB >= this.g[n2] || wn22.bA.d)) {
            if (!this.i.D) {
                List<acl> list = this.a(zx2, n2, this.g[n2]);
                boolean bl2 = \u2603 = zx2.b() == zy.aL;
                if (list != null) {
                    wn wn22;
                    wn22.b(\u26032);
                    if (\u2603) {
                        zx2.a(zy.cd);
                    }
                    for (int i2 = 0; i2 < list.size(); ++i2) {
                        acl acl2 = list.get(i2);
                        if (\u2603) {
                            zy.cd.a(zx2, acl2);
                            continue;
                        }
                        zx2.a(acl2.b, acl2.c);
                    }
                    if (!wn22.bA.d) {
                        \u2603.b -= \u26032;
                        if (\u2603.b <= 0) {
                            this.a.a(1, null);
                        }
                    }
                    wn22.b(na.W);
                    this.a.p_();
                    this.f = wn22.cj();
                    this.a(this.a);
                }
            }
            return true;
        }
        return false;
    }

    private List<acl> a(zx zx2, int n2, int n3) {
        this.k.setSeed(this.f + n2);
        List<acl> list = ack.b(this.k, zx2, n3);
        if (zx2.b() == zy.aL && list != null && list.size() > 1) {
            list.remove(this.k.nextInt(list.size()));
        }
        return list;
    }

    public int e() {
        zx zx2 = this.a.a(1);
        if (zx2 == null) {
            return 0;
        }
        return zx2.b;
    }

    @Override
    public void b(wn wn2) {
        super.b(wn2);
        if (this.i.D) {
            return;
        }
        for (int i2 = 0; i2 < this.a.o_(); ++i2) {
            zx zx2 = this.a.b(i2);
            if (zx2 == null) continue;
            wn2.a(zx2, false);
        }
    }

    @Override
    public boolean a(wn wn2) {
        if (this.i.p(this.j).c() != afi.bC) {
            return false;
        }
        return !(wn2.e((double)this.j.n() + 0.5, (double)this.j.o() + 0.5, (double)this.j.p() + 0.5) > 64.0);
    }

    @Override
    public zx b(wn wn2, int n2) {
        zx zx2 = null;
        yg \u26032 = (yg)this.c.get(n2);
        if (\u26032 != null && \u26032.e()) {
            \u2603 = \u26032.d();
            zx2 = \u2603.k();
            if (n2 == 0) {
                if (!this.a(\u2603, 2, 38, true)) {
                    return null;
                }
            } else if (n2 == 1) {
                if (!this.a(\u2603, 2, 38, true)) {
                    return null;
                }
            } else if (\u2603.b() == zy.aW && zd.a(\u2603.i()) == zd.l) {
                if (!this.a(\u2603, 1, 2, true)) {
                    return null;
                }
            } else if (!((yg)this.c.get(0)).e() && ((yg)this.c.get(0)).a(\u2603)) {
                if (\u2603.n() && \u2603.b == 1) {
                    ((yg)this.c.get(0)).d(\u2603.k());
                    \u2603.b = 0;
                } else if (\u2603.b >= 1) {
                    ((yg)this.c.get(0)).d(new zx(\u2603.b(), 1, \u2603.i()));
                    --\u2603.b;
                }
            } else {
                return null;
            }
            if (\u2603.b == 0) {
                \u26032.d(null);
            } else {
                \u26032.f();
            }
            if (\u2603.b == zx2.b) {
                return null;
            }
            \u26032.a(wn2, \u2603);
        }
        return zx2;
    }
}

