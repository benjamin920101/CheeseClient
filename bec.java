/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

public class bec {
    private static final jy b = new jy("textures/particle/particles.png");
    protected adm a;
    private List<beb>[][] c = new List[4][];
    private List<bep> d = Lists.newArrayList();
    private bmj e;
    private Random f = new Random();
    private Map<Integer, bed> g = Maps.newHashMap();

    public bec(adm adm2, bmj bmj2) {
        this.a = adm2;
        this.e = bmj2;
        for (int i2 = 0; i2 < 4; ++i2) {
            this.c[i2] = new List[2];
            for (\u2603 = 0; \u2603 < 2; ++\u2603) {
                this.c[i2][\u2603] = Lists.newArrayList();
            }
        }
        this.c();
    }

    private void c() {
        this.a(cy.a.c(), new bdp.a());
        this.a(cy.e.c(), new bdl.a());
        this.a(cy.f.c(), new bek.a());
        this.a(cy.g.c(), new beq.a());
        this.a(cy.N.c(), new ber.a());
        this.a(cy.h.c(), new bel.a());
        this.a(cy.i.c(), new bem.b());
        this.a(cy.j.c(), new bdm.b());
        this.a(cy.k.c(), new bdm.a());
        this.a(cy.l.c(), new beh.a());
        this.a(cy.m.c(), new bdx.a());
        this.a(cy.n.c(), new bej.d());
        this.a(cy.o.c(), new bej.b());
        this.a(cy.p.c(), new bej.c());
        this.a(cy.q.c(), new bej.a());
        this.a(cy.r.c(), new bej.e());
        this.a(cy.s.c(), new bdn.b());
        this.a(cy.t.c(), new bdn.a());
        this.a(cy.u.c(), new bdt.a());
        this.a(cy.v.c(), new bem.a());
        this.a(cy.w.c(), new bem.b());
        this.a(cy.x.c(), new bea.a());
        this.a(cy.y.c(), new bef.a());
        this.a(cy.z.c(), new bdo.a());
        this.a(cy.A.c(), new bdr.a());
        this.a(cy.B.c(), new bdy.a());
        this.a(cy.C.c(), new bds.a());
        this.a(cy.D.c(), new bee.a());
        this.a(cy.E.c(), new beg.a());
        this.a(cy.F.c(), new bdk.c());
        this.a(cy.G.c(), new bei.a());
        this.a(cy.H.c(), new bdk.b());
        this.a(cy.I.c(), new bdt.b());
        this.a(cy.J.c(), new bdj.a());
        this.a(cy.K.c(), new bdk.a());
        this.a(cy.L.c(), new beo.a());
        this.a(cy.M.c(), new ben.a());
        this.a(cy.c.c(), new bdv.a());
        this.a(cy.b.c(), new bdu.a());
        this.a(cy.d.c(), new bdq.d());
        this.a(cy.P.c(), new bdz.a());
    }

    public void a(int n2, bed bed2) {
        this.g.put(n2, bed2);
    }

    public void a(pk pk2, cy cy2) {
        this.d.add(new bep(this.a, pk2, cy2));
    }

    public beb a(int n2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
        bed bed2 = this.g.get(n2);
        if (bed2 != null && (\u2603 = bed2.a(n2, this.a, d2, d3, d4, d5, d6, d7, nArray)) != null) {
            this.a(\u2603);
            return \u2603;
        }
        return null;
    }

    public void a(beb beb2) {
        int n2 = beb2.a();
        int n3 = \u2603 = beb2.j() != 1.0f ? 0 : 1;
        if (this.c[n2][\u2603].size() >= 4000) {
            this.c[n2][\u2603].remove(0);
        }
        this.c[n2][\u2603].add(beb2);
    }

    public void a() {
        for (int i2 = 0; i2 < 4; ++i2) {
            this.a(i2);
        }
        ArrayList<bep> arrayList = Lists.newArrayList();
        for (bep bep2 : this.d) {
            bep2.t_();
            if (!bep2.I) continue;
            arrayList.add(bep2);
        }
        this.d.removeAll(arrayList);
    }

    private void a(int n2) {
        for (\u2603 = 0; \u2603 < 2; ++\u2603) {
            this.a(this.c[n2][\u2603]);
        }
    }

    private void a(List<beb> list2) {
        List<beb> list2;
        ArrayList<beb> arrayList = Lists.newArrayList();
        for (int i2 = 0; i2 < list2.size(); ++i2) {
            beb beb2 = list2.get(i2);
            this.d(beb2);
            if (!beb2.I) continue;
            arrayList.add(beb2);
        }
        list2.removeAll(arrayList);
    }

    private void d(final beb beb2) {
        try {
            beb2.t_();
        }
        catch (Throwable throwable) {
            b b2 = b.a(throwable, "Ticking Particle");
            c \u26032 = b2.a("Particle being ticked");
            final int \u26033 = beb2.a();
            \u26032.a("Particle", new Callable<String>(){

                public String a() throws Exception {
                    return beb2.toString();
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            \u26032.a("Particle Type", new Callable<String>(){

                public String a() throws Exception {
                    if (\u26033 == 0) {
                        return "MISC_TEXTURE";
                    }
                    if (\u26033 == 1) {
                        return "TERRAIN_TEXTURE";
                    }
                    if (\u26033 == 3) {
                        return "ENTITY_PARTICLE_TEXTURE";
                    }
                    return "Unknown - " + \u26033;
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            throw new e(b2);
        }
    }

    public void a(pk pk2, float f2) {
        \u2603 = auz.b();
        \u2603 = auz.d();
        \u2603 = auz.e();
        \u2603 = auz.f();
        \u2603 = auz.c();
        beb.aw = pk2.P + (pk2.s - pk2.P) * (double)f2;
        beb.ax = pk2.Q + (pk2.t - pk2.Q) * (double)f2;
        beb.ay = pk2.R + (pk2.u - pk2.R) * (double)f2;
        bfl.l();
        bfl.b(770, 771);
        bfl.a(516, 0.003921569f);
        for (int i2 = 0; i2 < 3; ++i2) {
            for (\u2603 = 0; \u2603 < 2; ++\u2603) {
                if (this.c[i2][\u2603].isEmpty()) continue;
                switch (\u2603) {
                    case 0: {
                        bfl.a(false);
                        break;
                    }
                    case 1: {
                        bfl.a(true);
                    }
                }
                switch (i2) {
                    default: {
                        this.e.a(b);
                        break;
                    }
                    case 1: {
                        this.e.a(bmh.g);
                    }
                }
                bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
                bfx bfx2 = bfx.a();
                bfd \u26032 = bfx2.c();
                \u26032.a(7, bms.d);
                for (int i3 = 0; i3 < this.c[i2][\u2603].size(); ++i3) {
                    final beb beb2 = this.c[i2][\u2603].get(i3);
                    try {
                        beb2.a(\u26032, pk2, f2, \u2603, \u2603, \u2603, \u2603, \u2603);
                        continue;
                    }
                    catch (Throwable \u26033) {
                        b b2 = b.a(\u26033, "Rendering Particle");
                        c \u26034 = b2.a("Particle being rendered");
                        final int \u26035 = i2;
                        \u26034.a("Particle", new Callable<String>(){

                            public String a() throws Exception {
                                return beb2.toString();
                            }

                            @Override
                            public /* synthetic */ Object call() throws Exception {
                                return this.a();
                            }
                        });
                        \u26034.a("Particle Type", new Callable<String>(){

                            public String a() throws Exception {
                                if (\u26035 == 0) {
                                    return "MISC_TEXTURE";
                                }
                                if (\u26035 == 1) {
                                    return "TERRAIN_TEXTURE";
                                }
                                if (\u26035 == 3) {
                                    return "ENTITY_PARTICLE_TEXTURE";
                                }
                                return "Unknown - " + \u26035;
                            }

                            @Override
                            public /* synthetic */ Object call() throws Exception {
                                return this.a();
                            }
                        });
                        throw new e(b2);
                    }
                }
                bfx2.b();
            }
        }
        bfl.a(true);
        bfl.k();
        bfl.a(516, 0.1f);
    }

    public void b(pk pk2, float f2) {
        \u2603 = (float)Math.PI / 180;
        \u2603 = ns.b(pk2.y * ((float)Math.PI / 180));
        \u2603 = ns.a(pk2.y * ((float)Math.PI / 180));
        \u2603 = -\u2603 * ns.a(pk2.z * ((float)Math.PI / 180));
        \u2603 = \u2603 * ns.a(pk2.z * ((float)Math.PI / 180));
        \u2603 = ns.b(pk2.z * ((float)Math.PI / 180));
        for (int i2 = 0; i2 < 2; ++i2) {
            List<beb> list = this.c[3][i2];
            if (list.isEmpty()) continue;
            bfx \u26032 = bfx.a();
            bfd \u26033 = \u26032.c();
            for (int i3 = 0; i3 < list.size(); ++i3) {
                beb beb2 = list.get(i3);
                beb2.a(\u26033, pk2, f2, \u2603, \u2603, \u2603, \u2603, \u2603);
            }
        }
    }

    public void a(adm adm2) {
        this.a = adm2;
        for (int i2 = 0; i2 < 4; ++i2) {
            for (\u2603 = 0; \u2603 < 2; ++\u2603) {
                this.c[i2][\u2603].clear();
            }
        }
        this.d.clear();
    }

    public void a(cj cj2, alz alz2) {
        if (alz2.c().t() == arm.a) {
            return;
        }
        alz2 = alz2.c().a(alz2, (adq)this.a, cj2);
        int n2 = 4;
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                    double d2 = (double)cj2.n() + ((double)\u2603 + 0.5) / (double)n2;
                    \u2603 = (double)cj2.o() + ((double)\u2603 + 0.5) / (double)n2;
                    \u2603 = (double)cj2.p() + ((double)\u2603 + 0.5) / (double)n2;
                    this.a(new beo(this.a, d2, \u2603, \u2603, d2 - (double)cj2.n() - 0.5, \u2603 - (double)cj2.o() - 0.5, \u2603 - (double)cj2.p() - 0.5, alz2).a(cj2));
                }
            }
        }
    }

    public void a(cj cj2, cq cq2) {
        alz alz2 = this.a.p(cj2);
        afh \u26032 = alz2.c();
        if (\u26032.b() == -1) {
            return;
        }
        int \u26033 = cj2.n();
        int \u26034 = cj2.o();
        int \u26035 = cj2.p();
        float \u26036 = 0.1f;
        double \u26037 = (double)\u26033 + this.f.nextDouble() * (\u26032.C() - \u26032.B() - (double)(\u26036 * 2.0f)) + (double)\u26036 + \u26032.B();
        double \u26038 = (double)\u26034 + this.f.nextDouble() * (\u26032.E() - \u26032.D() - (double)(\u26036 * 2.0f)) + (double)\u26036 + \u26032.D();
        double \u26039 = (double)\u26035 + this.f.nextDouble() * (\u26032.G() - \u26032.F() - (double)(\u26036 * 2.0f)) + (double)\u26036 + \u26032.F();
        if (cq2 == cq.a) {
            \u26038 = (double)\u26034 + \u26032.D() - (double)\u26036;
        }
        if (cq2 == cq.b) {
            \u26038 = (double)\u26034 + \u26032.E() + (double)\u26036;
        }
        if (cq2 == cq.c) {
            \u26039 = (double)\u26035 + \u26032.F() - (double)\u26036;
        }
        if (cq2 == cq.d) {
            \u26039 = (double)\u26035 + \u26032.G() + (double)\u26036;
        }
        if (cq2 == cq.e) {
            \u26037 = (double)\u26033 + \u26032.B() - (double)\u26036;
        }
        if (cq2 == cq.f) {
            \u26037 = (double)\u26033 + \u26032.C() + (double)\u26036;
        }
        this.a(new beo(this.a, \u26037, \u26038, \u26039, 0.0, 0.0, 0.0, alz2).a(cj2).a(0.2f).h(0.6f));
    }

    public void b(beb beb2) {
        this.a(beb2, 1, 0);
    }

    public void c(beb beb2) {
        this.a(beb2, 0, 1);
    }

    private void a(beb beb2, int n2, int n3) {
        for (\u2603 = 0; \u2603 < 4; ++\u2603) {
            if (!this.c[\u2603][n2].contains(beb2)) continue;
            this.c[\u2603][n2].remove(beb2);
            this.c[\u2603][n3].add(beb2);
        }
    }

    public String b() {
        int n2 = 0;
        for (\u2603 = 0; \u2603 < 4; ++\u2603) {
            for (\u2603 = 0; \u2603 < 2; ++\u2603) {
                n2 += this.c[\u2603][\u2603].size();
            }
        }
        return "" + n2;
    }
}

