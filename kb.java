/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;
import java.io.PrintStream;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class kb {
    private static final PrintStream a = System.out;
    private static boolean b = false;
    private static final Logger c = LogManager.getLogger();

    public static boolean a() {
        return b;
    }

    static void b() {
        agg.N.a(zy.g, new ka(){

            @Override
            protected wv a(adm adm2, cz cz2) {
                wq wq2 = new wq(adm2, cz2.a(), cz2.b(), cz2.c());
                wq2.a = 1;
                return wq2;
            }
        });
        agg.N.a(zy.aP, new ka(){

            @Override
            protected wv a(adm adm2, cz cz2) {
                return new wz(adm2, cz2.a(), cz2.b(), cz2.c());
            }
        });
        agg.N.a(zy.aD, new ka(){

            @Override
            protected wv a(adm adm2, cz cz2) {
                return new wx(adm2, cz2.a(), cz2.b(), cz2.c());
            }
        });
        agg.N.a(zy.bK, new ka(){

            @Override
            protected wv a(adm adm2, cz cz2) {
                return new xb(adm2, cz2.a(), cz2.b(), cz2.c());
            }

            @Override
            protected float a() {
                return super.a() * 0.5f;
            }

            @Override
            protected float b() {
                return super.b() * 1.25f;
            }
        });
        agg.N.a(zy.bz, new cr(){
            private final cn b = new cn();

            @Override
            public zx a(ck ck2, final zx zx2) {
                if (aai.f(zx2.i())) {
                    return new ka(){

                        @Override
                        protected wv a(adm adm2, cz cz2) {
                            return new xc(adm2, cz2.a(), cz2.b(), cz2.c(), zx2.k());
                        }

                        @Override
                        protected float a() {
                            return super.a() * 0.5f;
                        }

                        @Override
                        protected float b() {
                            return super.b() * 1.25f;
                        }
                    }.a(ck2, zx2);
                }
                return this.b.a(ck2, zx2);
            }
        });
        agg.N.a(zy.bJ, new cn(){

            @Override
            public zx b(ck ck2, zx zx2) {
                cq cq2 = agg.b(ck2.f());
                double \u26032 = ck2.a() + (double)cq2.g();
                double \u26033 = (float)ck2.d().o() + 0.2f;
                double \u26034 = ck2.c() + (double)cq2.i();
                pk \u26035 = aax.a(ck2.i(), zx2.i(), \u26032, \u26033, \u26034);
                if (\u26035 instanceof pr && zx2.s()) {
                    ((ps)\u26035).a(zx2.q());
                }
                zx2.a(1);
                return zx2;
            }
        });
        agg.N.a(zy.cb, new cn(){

            @Override
            public zx b(ck ck2, zx zx2) {
                cq cq2 = agg.b(ck2.f());
                double \u26032 = ck2.a() + (double)cq2.g();
                double \u26033 = (float)ck2.d().o() + 0.2f;
                double \u26034 = ck2.c() + (double)cq2.i();
                wt \u26035 = new wt(ck2.i(), \u26032, \u26033, \u26034, zx2);
                ck2.i().d(\u26035);
                zx2.a(1);
                return zx2;
            }

            @Override
            protected void a(ck ck2) {
                ck2.i().b(1002, ck2.d(), 0);
            }
        });
        agg.N.a(zy.bL, new cn(){

            @Override
            public zx b(ck ck2, zx zx2) {
                cq cq2 = agg.b(ck2.f());
                cz \u26032 = agg.a(ck2);
                double \u26033 = \u26032.a() + (double)((float)cq2.g() * 0.3f);
                double \u26034 = \u26032.b() + (double)((float)cq2.h() * 0.3f);
                double \u26035 = \u26032.c() + (double)((float)cq2.i() * 0.3f);
                adm \u26036 = ck2.i();
                Random \u26037 = \u26036.s;
                double \u26038 = \u26037.nextGaussian() * 0.05 + (double)cq2.g();
                double \u26039 = \u26037.nextGaussian() * 0.05 + (double)cq2.h();
                double \u260310 = \u26037.nextGaussian() * 0.05 + (double)cq2.i();
                \u26036.d(new ww(\u26036, \u26033, \u26034, \u26035, \u26038, \u26039, \u260310));
                zx2.a(1);
                return zx2;
            }

            @Override
            protected void a(ck ck2) {
                ck2.i().b(1009, ck2.d(), 0);
            }
        });
        agg.N.a(zy.aE, new cn(){
            private final cn b = new cn();

            @Override
            public zx b(ck ck22, zx zx2) {
                double d2;
                cq cq2 = agg.b(ck22.f());
                adm \u26032 = ck22.i();
                double \u26033 = ck22.a() + (double)((float)cq2.g() * 1.125f);
                double \u26034 = ck22.b() + (double)((float)cq2.h() * 1.125f);
                double \u26035 = ck22.c() + (double)((float)cq2.i() * 1.125f);
                cj \u26036 = ck22.d().a(cq2);
                arm \u26037 = \u26032.p(\u26036).c().t();
                if (arm.h.equals(\u26037)) {
                    d2 = 1.0;
                } else if (arm.a.equals(\u26037) && arm.h.equals(\u26032.p(\u26036.b()).c().t())) {
                    d2 = 0.0;
                } else {
                    ck ck22;
                    return this.b.a(ck22, zx2);
                }
                ux \u26038 = new ux(\u26032, \u26033, \u26034 + d2, \u26035);
                \u26032.d(\u26038);
                zx2.a(1);
                return zx2;
            }

            @Override
            protected void a(ck ck2) {
                ck2.i().b(1000, ck2.d(), 0);
            }
        });
        cn cn2 = new cn(){
            private final cn b = new cn();

            @Override
            public zx b(ck ck2, zx zx2) {
                yv yv2 = (yv)zx2.b();
                cj \u26032 = ck2.d().a(agg.b(ck2.f()));
                if (yv2.a(ck2.i(), \u26032)) {
                    zx2.a(zy.aw);
                    zx2.b = 1;
                    return zx2;
                }
                return this.b.a(ck2, zx2);
            }
        };
        agg.N.a(zy.ay, cn2);
        agg.N.a(zy.ax, cn2);
        agg.N.a(zy.aw, new cn(){
            private final cn b = new cn();

            @Override
            public zx b(ck ck22, zx zx2) {
                zw zw2;
                adm adm2 = ck22.i();
                alz \u26032 = adm2.p(\u2603 = ck22.d().a(agg.b(ck22.f())));
                afh \u26033 = \u26032.c();
                arm \u26034 = \u26033.t();
                if (arm.h.equals(\u26034) && \u26033 instanceof ahv && \u26032.b(ahv.b) == 0) {
                    zw2 = zy.ax;
                } else if (arm.i.equals(\u26034) && \u26033 instanceof ahv && \u26032.b(ahv.b) == 0) {
                    zw2 = zy.ay;
                } else {
                    ck ck22;
                    return super.b(ck22, zx2);
                }
                adm2.g(\u2603);
                if (--zx2.b == 0) {
                    zx2.a(zw2);
                    zx2.b = 1;
                } else if (((alc)ck22.h()).a(new zx(zw2)) < 0) {
                    this.b.a(ck22, new zx(zw2));
                }
                return zx2;
            }
        });
        agg.N.a(zy.d, new cn(){
            private boolean b = true;

            @Override
            protected zx b(ck ck2, zx zx2) {
                adm adm2 = ck2.i();
                if (adm2.d(\u2603 = ck2.d().a(agg.b(ck2.f())))) {
                    adm2.a(\u2603, afi.ab.Q());
                    if (zx2.a(1, adm2.s)) {
                        zx2.b = 0;
                    }
                } else if (adm2.p(\u2603).c() == afi.W) {
                    afi.W.d(adm2, \u2603, afi.W.Q().a(ake.a, true));
                    adm2.g(\u2603);
                } else {
                    this.b = false;
                }
                return zx2;
            }

            @Override
            protected void a(ck ck2) {
                if (this.b) {
                    ck2.i().b(1000, ck2.d(), 0);
                } else {
                    ck2.i().b(1001, ck2.d(), 0);
                }
            }
        });
        agg.N.a(zy.aW, new cn(){
            private boolean b = true;

            @Override
            protected zx b(ck ck22, zx zx2) {
                ck ck22;
                if (zd.a == zd.a(zx2.i())) {
                    adm adm2 = ck22.i();
                    if (ze.a(zx2, adm2, \u2603 = ck22.d().a(agg.b(ck22.f())))) {
                        if (!adm2.D) {
                            adm2.b(2005, \u2603, 0);
                        }
                    } else {
                        this.b = false;
                    }
                    return zx2;
                }
                return super.b(ck22, zx2);
            }

            @Override
            protected void a(ck ck2) {
                if (this.b) {
                    ck2.i().b(1000, ck2.d(), 0);
                } else {
                    ck2.i().b(1001, ck2.d(), 0);
                }
            }
        });
        agg.N.a(zw.a(afi.W), new cn(){

            @Override
            protected zx b(ck ck2, zx zx2) {
                adm adm2 = ck2.i();
                cj \u26032 = ck2.d().a(agg.b(ck2.f()));
                vj \u26033 = new vj(adm2, (double)\u26032.n() + 0.5, \u26032.o(), (double)\u26032.p() + 0.5, null);
                adm2.d(\u26033);
                adm2.a(\u26033, "game.tnt.primed", 1.0f, 1.0f);
                --zx2.b;
                return zx2;
            }
        });
        agg.N.a(zy.bX, new cn(){
            private boolean b = true;

            @Override
            protected zx b(ck ck2, zx zx22) {
                zx zx22;
                adm adm2 = ck2.i();
                cq \u26032 = agg.b(ck2.f());
                cj \u26033 = ck2.d().a(\u26032);
                ajm \u26034 = afi.ce;
                if (adm2.d(\u26033) && \u26034.b(adm2, \u26033, zx22)) {
                    if (!adm2.D) {
                        adm2.a(\u26033, \u26034.Q().a(ajm.a, cq.b), 3);
                        akw akw2 = adm2.s(\u26033);
                        if (akw2 instanceof alo) {
                            if (zx22.i() == 3) {
                                GameProfile gameProfile = null;
                                if (zx22.n()) {
                                    dn dn2 = zx22.o();
                                    if (dn2.b("SkullOwner", 10)) {
                                        gameProfile = dy.a(dn2.m("SkullOwner"));
                                    } else if (dn2.b("SkullOwner", 8) && !nx.b(\u2603 = dn2.j("SkullOwner"))) {
                                        gameProfile = new GameProfile(null, \u2603);
                                    }
                                }
                                ((alo)akw2).a(gameProfile);
                            } else {
                                ((alo)akw2).a(zx22.i());
                            }
                            ((alo)akw2).b(\u26032.d().b() * 4);
                            afi.ce.a(adm2, \u26033, (alo)akw2);
                        }
                        --zx22.b;
                    }
                } else {
                    this.b = false;
                }
                return zx22;
            }

            @Override
            protected void a(ck ck2) {
                if (this.b) {
                    ck2.i().b(1000, ck2.d(), 0);
                } else {
                    ck2.i().b(1001, ck2.d(), 0);
                }
            }
        });
        agg.N.a(zw.a(afi.aU), new cn(){
            private boolean b = true;

            @Override
            protected zx b(ck ck2, zx zx2) {
                adm adm2 = ck2.i();
                cj \u26032 = ck2.d().a(agg.b(ck2.f()));
                aiv \u26033 = (aiv)afi.aU;
                if (adm2.d(\u26032) && \u26033.e(adm2, \u26032)) {
                    if (!adm2.D) {
                        adm2.a(\u26032, \u26033.Q(), 3);
                    }
                    --zx2.b;
                } else {
                    this.b = false;
                }
                return zx2;
            }

            @Override
            protected void a(ck ck2) {
                if (this.b) {
                    ck2.i().b(1000, ck2.d(), 0);
                } else {
                    ck2.i().b(1001, ck2.d(), 0);
                }
            }
        });
    }

    public static void c() {
        if (b) {
            return;
        }
        b = true;
        if (c.isDebugEnabled()) {
            kb.d();
        }
        afh.S();
        agv.l();
        zw.t();
        na.a();
        kb.b();
    }

    private static void d() {
        System.setErr(new kg("STDERR", System.err));
        System.setOut(new kg("STDOUT", a));
    }

    public static void a(String string) {
        a.println(string);
    }
}

