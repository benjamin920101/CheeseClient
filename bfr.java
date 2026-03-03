/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class bfr
implements ado,
bnj {
    private static final Logger b = LogManager.getLogger();
    private static final jy c = new jy("textures/environment/moon_phases.png");
    private static final jy d = new jy("textures/environment/sun.png");
    private static final jy e = new jy("textures/environment/clouds.png");
    private static final jy f = new jy("textures/environment/end_sky.png");
    private static final jy g = new jy("textures/misc/forcefield.png");
    private final ave h;
    private final bmj i;
    private final biu j;
    private bdb k;
    private Set<bht> l = Sets.newLinkedHashSet();
    private List<a> m = Lists.newArrayListWithCapacity(69696);
    private final Set<akw> n = Sets.newHashSet();
    private bga o;
    private int p = -1;
    private int q = -1;
    private int r = -1;
    private bmu s;
    private bmt t;
    private bmt u;
    private bmt v;
    private int w;
    private final Map<Integer, kw> x = Maps.newHashMap();
    private final Map<cj, bpj> y = Maps.newHashMap();
    private final bmi[] z = new bmi[10];
    private bfw A;
    private blr B;
    private double C = Double.MIN_VALUE;
    private double D = Double.MIN_VALUE;
    private double E = Double.MIN_VALUE;
    private int F = Integer.MIN_VALUE;
    private int G = Integer.MIN_VALUE;
    private int H = Integer.MIN_VALUE;
    private double I = Double.MIN_VALUE;
    private double J = Double.MIN_VALUE;
    private double K = Double.MIN_VALUE;
    private double L = Double.MIN_VALUE;
    private double M = Double.MIN_VALUE;
    private final bho N = new bho();
    private bfh O;
    private int P = -1;
    private int Q = 2;
    private int R;
    private int S;
    private int T;
    private boolean U = false;
    private bid V;
    private final Vector4f[] W = new Vector4f[8];
    private final bqr X = new bqr();
    private boolean Y = false;
    bhu a;
    private double Z;
    private double aa;
    private double ab;
    private boolean ac = true;

    public bfr(ave ave2) {
        this.h = ave2;
        this.j = ave2.af();
        this.i = ave2.P();
        this.i.a(g);
        GL11.glTexParameteri(3553, 10242, 10497);
        GL11.glTexParameteri(3553, 10243, 10497);
        bfl.i(0);
        this.n();
        this.Y = bqs.f();
        if (this.Y) {
            this.O = new bfy();
            this.a = new bhv();
        } else {
            this.O = new bft();
            this.a = new bhr();
        }
        this.s = new bmu();
        this.s.a(new bmv(0, bmv.a.a, bmv.b.a, 3));
        this.q();
        this.p();
        this.o();
    }

    @Override
    public void a(bni bni2) {
        this.n();
    }

    private void n() {
        bmh bmh2 = this.h.T();
        for (int i2 = 0; i2 < this.z.length; ++i2) {
            this.z[i2] = bmh2.a("minecraft:blocks/destroy_stage_" + i2);
        }
    }

    public void b() {
        if (bqs.O) {
            if (blu.b() == null) {
                blu.a();
            }
            jy jy2 = new jy("shaders/post/entity_outline.json");
            try {
                this.B = new blr(this.h.P(), this.h.Q(), this.h.b(), jy2);
                this.B.a(this.h.d, this.h.e);
                this.A = this.B.a("final");
            }
            catch (IOException \u26032) {
                b.warn("Failed to load shader: " + jy2, (Throwable)\u26032);
                this.B = null;
                this.A = null;
            }
            catch (JsonSyntaxException \u26033) {
                b.warn("Failed to load shader: " + jy2, (Throwable)\u26033);
                this.B = null;
                this.A = null;
            }
        } else {
            this.B = null;
            this.A = null;
        }
    }

    public void c() {
        if (this.d()) {
            bfl.l();
            bfl.a(770, 771, 0, 1);
            this.A.a(this.h.d, this.h.e, false);
            bfl.k();
        }
    }

    protected boolean d() {
        return this.A != null && this.B != null && this.h.h != null && this.h.h.v() && this.h.t.aq.d();
    }

    private void o() {
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        if (this.v != null) {
            this.v.c();
        }
        if (this.r >= 0) {
            avd.b(this.r);
            this.r = -1;
        }
        if (this.Y) {
            this.v = new bmt(this.s);
            this.a(\u26032, -16.0f, true);
            \u26032.e();
            \u26032.b();
            this.v.a(\u26032.f());
        } else {
            this.r = avd.a(1);
            GL11.glNewList(this.r, 4864);
            this.a(\u26032, -16.0f, true);
            bfx2.b();
            GL11.glEndList();
        }
    }

    private void p() {
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        if (this.u != null) {
            this.u.c();
        }
        if (this.q >= 0) {
            avd.b(this.q);
            this.q = -1;
        }
        if (this.Y) {
            this.u = new bmt(this.s);
            this.a(\u26032, 16.0f, false);
            \u26032.e();
            \u26032.b();
            this.u.a(\u26032.f());
        } else {
            this.q = avd.a(1);
            GL11.glNewList(this.q, 4864);
            this.a(\u26032, 16.0f, false);
            bfx2.b();
            GL11.glEndList();
        }
    }

    private void a(bfd bfd2, float f2, boolean bl2) {
        int n2 = 64;
        \u2603 = 6;
        bfd2.a(7, bms.e);
        for (\u2603 = -384; \u2603 <= 384; \u2603 += 64) {
            for (\u2603 = -384; \u2603 <= 384; \u2603 += 64) {
                float f3 = \u2603;
                \u2603 = \u2603 + 64;
                if (bl2) {
                    \u2603 = \u2603;
                    f3 = \u2603 + 64;
                }
                bfd2.b((double)f3, (double)f2, (double)\u2603).d();
                bfd2.b((double)\u2603, (double)f2, (double)\u2603).d();
                bfd2.b((double)\u2603, (double)f2, (double)(\u2603 + 64)).d();
                bfd2.b((double)f3, (double)f2, (double)(\u2603 + 64)).d();
            }
        }
    }

    private void q() {
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        if (this.t != null) {
            this.t.c();
        }
        if (this.p >= 0) {
            avd.b(this.p);
            this.p = -1;
        }
        if (this.Y) {
            this.t = new bmt(this.s);
            this.a(\u26032);
            \u26032.e();
            \u26032.b();
            this.t.a(\u26032.f());
        } else {
            this.p = avd.a(1);
            bfl.E();
            GL11.glNewList(this.p, 4864);
            this.a(\u26032);
            bfx2.b();
            GL11.glEndList();
            bfl.F();
        }
    }

    private void a(bfd bfd2) {
        Random random = new Random(10842L);
        bfd2.a(7, bms.e);
        for (int i2 = 0; i2 < 1500; ++i2) {
            double d2 = random.nextFloat() * 2.0f - 1.0f;
            \u2603 = random.nextFloat() * 2.0f - 1.0f;
            \u2603 = random.nextFloat() * 2.0f - 1.0f;
            \u2603 = 0.15f + random.nextFloat() * 0.1f;
            \u2603 = d2 * d2 + \u2603 * \u2603 + \u2603 * \u2603;
            if (!(\u2603 < 1.0) || !(\u2603 > 0.01)) continue;
            \u2603 = 1.0 / Math.sqrt(\u2603);
            \u2603 = (d2 *= \u2603) * 100.0;
            \u2603 = (\u2603 *= \u2603) * 100.0;
            \u2603 = (\u2603 *= \u2603) * 100.0;
            \u2603 = Math.atan2(d2, \u2603);
            \u2603 = Math.sin(\u2603);
            \u2603 = Math.cos(\u2603);
            \u2603 = Math.atan2(Math.sqrt(d2 * d2 + \u2603 * \u2603), \u2603);
            \u2603 = Math.sin(\u2603);
            \u2603 = Math.cos(\u2603);
            \u2603 = random.nextDouble() * Math.PI * 2.0;
            \u2603 = Math.sin(\u2603);
            \u2603 = Math.cos(\u2603);
            for (int i3 = 0; i3 < 4; ++i3) {
                double d3 = 0.0;
                \u2603 = (double)((i3 & 2) - 1) * \u2603;
                \u2603 = (double)((i3 + 1 & 2) - 1) * \u2603;
                \u2603 = 0.0;
                \u2603 = \u2603 * \u2603 - \u2603 * \u2603;
                \u2603 = \u2603 = \u2603 * \u2603 + \u2603 * \u2603;
                \u2603 = \u2603 * \u2603 + 0.0 * \u2603;
                \u2603 = 0.0 * \u2603 - \u2603 * \u2603;
                \u2603 = \u2603 * \u2603 - \u2603 * \u2603;
                \u2603 = \u2603;
                \u2603 = \u2603 * \u2603 + \u2603 * \u2603;
                bfd2.b(\u2603 + \u2603, \u2603 + \u2603, \u2603 + \u2603).d();
            }
        }
    }

    public void a(bdb bdb2) {
        if (this.k != null) {
            this.k.b(this);
        }
        this.C = Double.MIN_VALUE;
        this.D = Double.MIN_VALUE;
        this.E = Double.MIN_VALUE;
        this.F = Integer.MIN_VALUE;
        this.G = Integer.MIN_VALUE;
        this.H = Integer.MIN_VALUE;
        this.j.a(bdb2);
        this.k = bdb2;
        if (bdb2 != null) {
            bdb2.a(this);
            this.a();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a() {
        if (this.k == null) {
            return;
        }
        this.ac = true;
        afi.t.b(this.h.t.i);
        afi.u.b(this.h.t.i);
        this.P = this.h.t.c;
        boolean bl2 = this.Y;
        this.Y = bqs.f();
        if (bl2 && !this.Y) {
            this.O = new bft();
            this.a = new bhr();
        } else if (!bl2 && this.Y) {
            this.O = new bfy();
            this.a = new bhv();
        }
        if (bl2 != this.Y) {
            this.q();
            this.p();
            this.o();
        }
        if (this.o != null) {
            this.o.a();
        }
        this.e();
        Set<akw> set = this.n;
        synchronized (set) {
            this.n.clear();
        }
        this.o = new bga(this.k, this.h.t.c, this, this.a);
        if (this.k != null && (\u2603 = this.h.ac()) != null) {
            this.o.a(\u2603.s, \u2603.u);
        }
        this.Q = 2;
    }

    protected void e() {
        this.l.clear();
        this.N.b();
    }

    public void a(int n2, int n3) {
        if (!bqs.O) {
            return;
        }
        if (this.B != null) {
            this.B.a(n2, n3);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(pk pk2, bia bia2, float f2) {
        if (this.Q > 0) {
            --this.Q;
            return;
        }
        double d2 = pk2.p + (pk2.s - pk2.p) * (double)f2;
        double d3 = pk2.q + (pk2.t - pk2.q) * (double)f2;
        \u2603 = pk2.r + (pk2.u - pk2.r) * (double)f2;
        this.k.B.a("prepare");
        bhc.a.a(this.k, this.h.P(), this.h.k, this.h.ac(), f2);
        this.j.a(this.k, this.h.k, this.h.ac(), this.h.i, this.h.t, f2);
        this.R = 0;
        this.S = 0;
        this.T = 0;
        pk \u26032 = this.h.ac();
        \u2603 = \u26032.P + (\u26032.s - \u26032.P) * (double)f2;
        \u2603 = \u26032.Q + (\u26032.t - \u26032.Q) * (double)f2;
        \u2603 = \u26032.R + (\u26032.u - \u26032.R) * (double)f2;
        bhc.b = \u2603;
        bhc.c = \u2603;
        bhc.d = \u2603;
        this.j.a(\u2603, \u2603, \u2603);
        this.h.o.i();
        this.k.B.c("global");
        List<pk> \u26033 = this.k.E();
        this.R = \u26033.size();
        for (int n2 = 0; n2 < this.k.k.size(); ++n2) {
            pk pk3 = (pk)this.k.k.get(n2);
            ++this.S;
            if (!pk3.h(d2, d3, \u2603)) continue;
            this.j.a(pk3, f2);
        }
        if (this.d()) {
            bfl.c(519);
            bfl.n();
            this.A.f();
            this.A.a(false);
            this.k.B.c("entityOutlines");
            avc.a();
            this.j.c(true);
            for (int i2 = 0; i2 < \u26033.size(); ++i2) {
                pk pk4 = \u26033.get(i2);
                boolean \u26034 = this.h.ac() instanceof pr && ((pr)this.h.ac()).bJ();
                boolean bl2 = \u2603 = pk4.h(d2, d3, \u2603) && (pk4.ah || bia2.a(pk4.aR()) || pk4.l == this.h.h) && pk4 instanceof wn;
                if (pk4 == this.h.ac() && this.h.t.aA == 0 && !\u26034 || !\u2603) continue;
                this.j.a(pk4, f2);
            }
            this.j.c(false);
            avc.b();
            bfl.a(false);
            this.B.a(f2);
            bfl.e();
            bfl.a(true);
            this.h.b().a(false);
            bfl.m();
            bfl.l();
            bfl.g();
            bfl.c(515);
            bfl.j();
            bfl.d();
        }
        this.k.B.c("entities");
        for (a a2 : this.m) {
            amy amy2 = this.k.f(a2.a.j());
            ne<pk> \u26035 = amy2.s()[a2.a.j().o() / 16];
            if (\u26035.isEmpty()) continue;
            for (pk pk3 : \u26035) {
                boolean bl3;
                boolean bl4 = bl3 = this.j.a(pk3, bia2, d2, d3, \u2603) || pk3.l == this.h.h;
                if (bl3) {
                    boolean bl5 = \u2603 = this.h.ac() instanceof pr ? ((pr)this.h.ac()).bJ() : false;
                    if (pk3 == this.h.ac() && this.h.t.aA == 0 && !\u2603 || pk3.t >= 0.0 && pk3.t < 256.0 && !this.k.e(new cj(pk3))) continue;
                    ++this.S;
                    this.j.a(pk3, f2);
                }
                if (bl3 || !(pk3 instanceof xd)) continue;
                this.h.af().b(pk3, f2);
            }
        }
        this.k.B.c("blockentities");
        avc.b();
        for (a a3 : this.m) {
            List<akw> list = a3.a.g().b();
            if (list.isEmpty()) continue;
            for (akw akw2 : list) {
                bhc.a.a(akw2, f2, -1);
            }
        }
        Iterator<kw> iterator = this.n;
        synchronized (iterator) {
            for (akw akw2 : this.n) {
                bhc.a.a(akw2, f2, -1);
            }
        }
        this.s();
        for (kw kw2 : this.x.values()) {
            cj cj2 = kw2.b();
            akw \u26036 = this.k.s(cj2);
            if (\u26036 instanceof aky) {
                aky aky2 = (aky)\u26036;
                if (aky2.h != null) {
                    cj2 = cj2.a(cq.e);
                    \u26036 = this.k.s(cj2);
                } else if (aky2.f != null) {
                    cj2 = cj2.a(cq.c);
                    \u26036 = this.k.s(cj2);
                }
            }
            afh afh2 = this.k.p(cj2).c();
            if (\u26036 == null || !(afh2 instanceof afs) && !(afh2 instanceof agp) && !(afh2 instanceof ajl) && !(afh2 instanceof ajm)) continue;
            bhc.a.a(\u26036, f2, kw2.c());
        }
        this.t();
        this.h.o.h();
        this.h.A.b();
    }

    public String f() {
        int n2;
        int n3 = this.o.f.length;
        n2 = 0;
        for (a a2 : this.m) {
            bhq bhq2 = a2.a.b;
            if (bhq2 == bhq.a || bhq2.a()) continue;
            ++n2;
        }
        return String.format("C: %d/%d %sD: %d, %s", n2, n3, this.h.G ? "(s) " : "", this.P, this.N.a());
    }

    public String g() {
        return "E: " + this.S + "/" + this.R + ", B: " + this.T + ", I: " + (this.R - this.T - this.S);
    }

    public void a(pk pk2, double d2, bia object, int n2, boolean bl22) {
        Collection<bht> collection;
        double d3;
        Object \u26032;
        if (this.h.t.c != this.P) {
            this.a();
        }
        this.k.B.a("camera");
        double d4 = pk2.s - this.C;
        \u2603 = pk2.t - this.D;
        \u2603 = pk2.u - this.E;
        if (this.F != pk2.ae || this.G != pk2.af || this.H != pk2.ag || d4 * d4 + \u2603 * \u2603 + \u2603 * \u2603 > 16.0) {
            this.C = pk2.s;
            this.D = pk2.t;
            this.E = pk2.u;
            this.F = pk2.ae;
            this.G = pk2.af;
            this.H = pk2.ag;
            this.o.a(pk2.s, pk2.u);
        }
        this.k.B.c("renderlistcamera");
        d3 = pk2.P + (pk2.s - pk2.P) * d2;
        \u2603 = pk2.Q + (pk2.t - pk2.Q) * d2;
        \u2603 = pk2.R + (pk2.u - pk2.R) * d2;
        this.O.a(d3, \u2603, \u2603);
        this.k.B.c("cull");
        if (this.V != null) {
            \u26032 = new bic(this.V);
            ((bic)\u26032).a(this.X.a, this.X.b, this.X.c);
            object = \u26032;
        }
        this.h.A.c("culling");
        \u26032 = new cj(d3, \u2603 + (double)pk2.aS(), \u2603);
        bht \u26033 = this.o.a((cj)\u26032);
        cj \u26034 = new cj(ns.c(d3 / 16.0) * 16, ns.c(\u2603 / 16.0) * 16, ns.c(\u2603 / 16.0) * 16);
        this.ac = this.ac || !this.l.isEmpty() || pk2.s != this.I || pk2.t != this.J || pk2.u != this.K || (double)pk2.z != this.L || (double)pk2.y != this.M;
        this.I = pk2.s;
        this.J = pk2.t;
        this.K = pk2.u;
        this.L = pk2.z;
        this.M = pk2.y;
        boolean bl3 = \u2603 = this.V != null;
        if (!\u2603 && this.ac) {
            Object \u260312;
            this.ac = false;
            this.m = Lists.newArrayList();
            collection = Lists.newLinkedList();
            boolean \u26035 = this.h.G;
            if (\u26033 == null) {
                int n3 = ((df)\u26032).o() > 0 ? 248 : 8;
                for (\u2603 = -this.P; \u2603 <= this.P; ++\u2603) {
                    for (\u2603 = -this.P; \u2603 <= this.P; ++\u2603) {
                        \u260312 = this.o.a(new cj((\u2603 << 4) + 8, n3, (\u2603 << 4) + 8));
                        if (\u260312 == null || !object.a(((bht)\u260312).c)) continue;
                        ((bht)\u260312).a(n2);
                        collection.add((bht)((Object)new a((bht)\u260312, null, 0)));
                    }
                }
            } else {
                boolean bl22;
                boolean \u26039 = false;
                a \u26036 = new a(\u26033, null, 0);
                Set<cq> \u26037 = this.c((cj)\u26032);
                if (\u26037.size() == 1) {
                    \u260312 = this.a(pk2, d2);
                    cq[] \u26038 = cq.a(((Vector3f)\u260312).x, ((Vector3f)\u260312).y, ((Vector3f)\u260312).z).d();
                    \u26037.remove(\u26038);
                }
                if (\u26037.isEmpty()) {
                    \u26039 = true;
                }
                if (!\u26039 || bl22) {
                    if (bl22 && this.k.p((cj)\u26032).c().c()) {
                        \u26035 = false;
                    }
                    \u26033.a(n2);
                    collection.add((bht)((Object)\u26036));
                } else {
                    this.m.add(\u26036);
                }
            }
            while (!collection.isEmpty()) {
                a a2 = (a)collection.poll();
                bht \u260310 = a2.a;
                cq \u260311 = a2.b;
                \u260312 = \u260310.j();
                this.m.add(a2);
                for (cq cq2 : cq.values()) {
                    bht bht2 = this.a(\u26034, \u260310, cq2);
                    if (\u26035 && a2.c.contains(cq2.d()) || \u26035 && \u260311 != null && !\u260310.g().a(\u260311.d(), cq2) || bht2 == null || !bht2.a(n2) || !object.a(bht2.c)) continue;
                    a \u260313 = new a(bht2, cq2, a2.d + 1);
                    \u260313.c.addAll(a2.c);
                    \u260313.c.add(cq2);
                    collection.add((bht)((Object)\u260313));
                }
            }
        }
        if (this.U) {
            this.a(d3, \u2603, \u2603);
            this.U = false;
        }
        this.N.e();
        collection = this.l;
        this.l = Sets.newLinkedHashSet();
        for (a a3 : this.m) {
            bht bht3 = a3.a;
            if (!bht3.l() && !collection.contains(bht3)) continue;
            this.ac = true;
            if (this.a(\u26034, a3.a)) {
                this.h.A.a("build near");
                this.N.b(bht3);
                bht3.a(false);
                this.h.A.b();
                continue;
            }
            this.l.add(bht3);
        }
        this.l.addAll(collection);
        this.h.A.b();
    }

    private boolean a(cj cj2, bht bht2) {
        cj cj3 = bht2.j();
        if (ns.a(cj2.n() - cj3.n()) > 16) {
            return false;
        }
        if (ns.a(cj2.o() - cj3.o()) > 16) {
            return false;
        }
        return ns.a(cj2.p() - cj3.p()) <= 16;
    }

    private Set<cq> c(cj cj2) {
        bhw bhw2 = new bhw();
        cj \u26032 = new cj(cj2.n() >> 4 << 4, cj2.o() >> 4 << 4, cj2.p() >> 4 << 4);
        amy \u26033 = this.k.f(\u26032);
        for (cj.a a2 : cj.b(\u26032, \u26032.a(15, 15, 15))) {
            if (!\u26033.a(a2).c()) continue;
            bhw2.a(a2);
        }
        return bhw2.b(cj2);
    }

    private bht a(cj cj2, bht bht2, cq cq2) {
        cj cj3 = bht2.a(cq2);
        if (ns.a(cj2.n() - cj3.n()) > this.P * 16) {
            return null;
        }
        if (cj3.o() < 0 || cj3.o() >= 256) {
            return null;
        }
        if (ns.a(cj2.p() - cj3.p()) > this.P * 16) {
            return null;
        }
        return this.o.a(cj3);
    }

    private void a(double d2, double d3, double d4) {
        this.V = new bib();
        ((bib)this.V).b();
        bqq bqq2 = new bqq(this.V.c);
        bqq2.transpose();
        \u2603 = new bqq(this.V.b);
        \u2603.transpose();
        \u2603 = new bqq();
        bqq.mul(\u2603, bqq2, \u2603);
        \u2603.invert();
        this.X.a = d2;
        this.X.b = d3;
        this.X.c = d4;
        this.W[0] = new Vector4f(-1.0f, -1.0f, -1.0f, 1.0f);
        this.W[1] = new Vector4f(1.0f, -1.0f, -1.0f, 1.0f);
        this.W[2] = new Vector4f(1.0f, 1.0f, -1.0f, 1.0f);
        this.W[3] = new Vector4f(-1.0f, 1.0f, -1.0f, 1.0f);
        this.W[4] = new Vector4f(-1.0f, -1.0f, 1.0f, 1.0f);
        this.W[5] = new Vector4f(1.0f, -1.0f, 1.0f, 1.0f);
        this.W[6] = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.W[7] = new Vector4f(-1.0f, 1.0f, 1.0f, 1.0f);
        for (int i2 = 0; i2 < 8; ++i2) {
            bqq.transform(\u2603, this.W[i2], this.W[i2]);
            this.W[i2].x /= this.W[i2].w;
            this.W[i2].y /= this.W[i2].w;
            this.W[i2].z /= this.W[i2].w;
            this.W[i2].w = 1.0f;
        }
    }

    protected Vector3f a(pk pk2, double d2) {
        float f2 = (float)((double)pk2.B + (double)(pk2.z - pk2.B) * d2);
        \u2603 = (float)((double)pk2.A + (double)(pk2.y - pk2.A) * d2);
        if (ave.A().t.aA == 2) {
            f2 += 180.0f;
        }
        \u2603 = ns.b(-\u2603 * ((float)Math.PI / 180) - (float)Math.PI);
        \u2603 = ns.a(-\u2603 * ((float)Math.PI / 180) - (float)Math.PI);
        \u2603 = -ns.b(-f2 * ((float)Math.PI / 180));
        \u2603 = ns.a(-f2 * ((float)Math.PI / 180));
        return new Vector3f(\u2603 * \u2603, \u2603, \u2603 * \u2603);
    }

    public int a(adf adf22, double d2, int n2, pk pk2) {
        adf adf22;
        avc.a();
        if (adf22 == adf.d) {
            this.h.A.a("translucent_sort");
            double d3 = pk2.s - this.Z;
            \u2603 = pk2.t - this.aa;
            \u2603 = pk2.u - this.ab;
            if (d3 * d3 + \u2603 * \u2603 + \u2603 * \u2603 > 1.0) {
                this.Z = pk2.s;
                this.aa = pk2.t;
                this.ab = pk2.u;
                int n3 = 0;
                for (a a2 : this.m) {
                    if (!a2.a.b.d(adf22) || n3++ >= 15) continue;
                    this.N.c(a2.a);
                }
            }
            this.h.A.b();
        }
        this.h.A.a("filterempty");
        int n4 = 0;
        boolean \u26032 = adf22 == adf.d;
        \u2603 = \u26032 ? this.m.size() - 1 : 0;
        \u2603 = \u26032 ? -1 : this.m.size();
        \u2603 = \u26032 ? -1 : 1;
        for (\u2603 = \u2603; \u2603 != \u2603; \u2603 += \u2603) {
            bht bht2 = this.m.get((int)\u2603).a;
            if (bht2.g().b(adf22)) continue;
            ++n4;
            this.O.a(bht2, adf22);
        }
        this.h.A.c("render_" + (Object)((Object)adf22));
        this.a(adf22);
        this.h.A.b();
        return n4;
    }

    private void a(adf adf2) {
        this.h.o.i();
        if (bqs.f()) {
            GL11.glEnableClientState(32884);
            bqs.l(bqs.q);
            GL11.glEnableClientState(32888);
            bqs.l(bqs.r);
            GL11.glEnableClientState(32888);
            bqs.l(bqs.q);
            GL11.glEnableClientState(32886);
        }
        this.O.a(adf2);
        if (bqs.f()) {
            List<bmv> list = bms.a.h();
            for (bmv bmv2 : list) {
                bmv.b b2 = bmv2.b();
                int \u26032 = bmv2.d();
                switch (b2) {
                    case a: {
                        GL11.glDisableClientState(32884);
                        break;
                    }
                    case d: {
                        bqs.l(bqs.q + \u26032);
                        GL11.glDisableClientState(32888);
                        bqs.l(bqs.q);
                        break;
                    }
                    case c: {
                        GL11.glDisableClientState(32886);
                        bfl.G();
                    }
                }
            }
        }
        this.h.o.h();
    }

    private void a(Iterator<kw> iterator) {
        while (iterator.hasNext()) {
            kw kw2 = iterator.next();
            int \u26032 = kw2.d();
            if (this.w - \u26032 <= 400) continue;
            iterator.remove();
        }
    }

    public void j() {
        ++this.w;
        if (this.w % 20 == 0) {
            this.a(this.x.values().iterator());
        }
    }

    private void r() {
        bfl.n();
        bfl.c();
        bfl.l();
        bfl.a(770, 771, 1, 0);
        avc.a();
        bfl.a(false);
        this.i.a(f);
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        for (int i2 = 0; i2 < 6; ++i2) {
            bfl.E();
            if (i2 == 1) {
                bfl.b(90.0f, 1.0f, 0.0f, 0.0f);
            }
            if (i2 == 2) {
                bfl.b(-90.0f, 1.0f, 0.0f, 0.0f);
            }
            if (i2 == 3) {
                bfl.b(180.0f, 1.0f, 0.0f, 0.0f);
            }
            if (i2 == 4) {
                bfl.b(90.0f, 0.0f, 0.0f, 1.0f);
            }
            if (i2 == 5) {
                bfl.b(-90.0f, 0.0f, 0.0f, 1.0f);
            }
            \u26032.a(7, bms.i);
            \u26032.b(-100.0, -100.0, -100.0).a(0.0, 0.0).b(40, 40, 40, 255).d();
            \u26032.b(-100.0, -100.0, 100.0).a(0.0, 16.0).b(40, 40, 40, 255).d();
            \u26032.b(100.0, -100.0, 100.0).a(16.0, 16.0).b(40, 40, 40, 255).d();
            \u26032.b(100.0, -100.0, -100.0).a(16.0, 0.0).b(40, 40, 40, 255).d();
            bfx2.b();
            bfl.F();
        }
        bfl.a(true);
        bfl.w();
        bfl.d();
    }

    public void a(float f22, int n2) {
        float f22;
        int \u26039;
        int \u26037;
        if (this.h.f.t.q() == 1) {
            this.r();
            return;
        }
        if (!this.h.f.t.d()) {
            return;
        }
        bfl.x();
        aui aui2 = this.k.a(this.h.ac(), f22);
        float \u26032 = (float)aui2.a;
        float \u26033 = (float)aui2.b;
        float \u26034 = (float)aui2.c;
        if (n2 != 2) {
            float f3 = (\u26032 * 30.0f + \u26033 * 59.0f + \u26034 * 11.0f) / 100.0f;
            \u2603 = (\u26032 * 30.0f + \u26033 * 70.0f) / 100.0f;
            \u2603 = (\u26032 * 30.0f + \u26034 * 70.0f) / 100.0f;
            \u26032 = f3;
            \u26033 = \u2603;
            \u26034 = \u2603;
        }
        bfl.c(\u26032, \u26033, \u26034);
        bfx bfx2 = bfx.a();
        bfd \u26035 = bfx2.c();
        bfl.a(false);
        bfl.m();
        bfl.c(\u26032, \u26033, \u26034);
        if (this.Y) {
            this.u.a();
            GL11.glEnableClientState(32884);
            GL11.glVertexPointer(3, 5126, 12, 0L);
            this.u.a(7);
            this.u.b();
            GL11.glDisableClientState(32884);
        } else {
            bfl.o(this.q);
        }
        bfl.n();
        bfl.c();
        bfl.l();
        bfl.a(770, 771, 1, 0);
        avc.a();
        float[] \u26036 = this.k.t.a(this.k.c(f22), f22);
        if (\u26036 != null) {
            bfl.x();
            bfl.j(7425);
            bfl.E();
            bfl.b(90.0f, 1.0f, 0.0f, 0.0f);
            bfl.b(ns.a(this.k.d(f22)) < 0.0f ? 180.0f : 0.0f, 0.0f, 0.0f, 1.0f);
            bfl.b(90.0f, 0.0f, 0.0f, 1.0f);
            float f4 = \u26036[0];
            \u2603 = \u26036[1];
            \u2603 = \u26036[2];
            if (n2 != 2) {
                \u2603 = (f4 * 30.0f + \u2603 * 59.0f + \u2603 * 11.0f) / 100.0f;
                \u2603 = (f4 * 30.0f + \u2603 * 70.0f) / 100.0f;
                f5 = (f4 * 30.0f + \u2603 * 70.0f) / 100.0f;
                f4 = \u2603;
                \u2603 = \u2603;
                \u2603 = f5;
            }
            \u26035.a(6, bms.f);
            \u26035.b(0.0, 100.0, 0.0).a(f4, \u2603, \u2603, \u26036[3]).d();
            \u26037 = 16;
            for (\u26039 = 0; \u26039 <= 16; ++\u26039) {
                float f5 = (float)\u26039 * (float)Math.PI * 2.0f / 16.0f;
                \u2603 = ns.a(f5);
                \u2603 = ns.b(f5);
                \u26035.b((double)(\u2603 * 120.0f), (double)(\u2603 * 120.0f), (double)(-\u2603 * 40.0f * \u26036[3])).a(\u26036[0], \u26036[1], \u26036[2], 0.0f).d();
            }
            bfx2.b();
            bfl.F();
            bfl.j(7424);
        }
        bfl.w();
        bfl.a(770, 1, 1, 0);
        bfl.E();
        f4 = 1.0f - this.k.j(f22);
        bfl.c(1.0f, 1.0f, 1.0f, f4);
        bfl.b(-90.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(this.k.c(f22) * 360.0f, 1.0f, 0.0f, 0.0f);
        \u2603 = 30.0f;
        this.i.a(d);
        \u26035.a(7, bms.g);
        \u26035.b((double)(-\u2603), 100.0, (double)(-\u2603)).a(0.0, 0.0).d();
        \u26035.b((double)\u2603, 100.0, (double)(-\u2603)).a(1.0, 0.0).d();
        \u26035.b((double)\u2603, 100.0, (double)\u2603).a(1.0, 1.0).d();
        \u26035.b((double)(-\u2603), 100.0, (double)\u2603).a(0.0, 1.0).d();
        bfx2.b();
        \u2603 = 20.0f;
        this.i.a(c);
        int \u26038 = this.k.x();
        \u26037 = \u26038 % 4;
        \u26039 = \u26038 / 4 % 2;
        f5 = (float)(\u26037 + 0) / 4.0f;
        \u2603 = (float)(\u26039 + 0) / 2.0f;
        \u2603 = (float)(\u26037 + 1) / 4.0f;
        \u2603 = (float)(\u26039 + 1) / 2.0f;
        \u26035.a(7, bms.g);
        \u26035.b((double)(-\u2603), -100.0, (double)\u2603).a(\u2603, \u2603).d();
        \u26035.b((double)\u2603, -100.0, (double)\u2603).a(f5, \u2603).d();
        \u26035.b((double)\u2603, -100.0, (double)(-\u2603)).a(f5, \u2603).d();
        \u26035.b((double)(-\u2603), -100.0, (double)(-\u2603)).a(\u2603, \u2603).d();
        bfx2.b();
        bfl.x();
        \u2603 = this.k.g(f22) * f4;
        if (\u2603 > 0.0f) {
            bfl.c(\u2603, \u2603, \u2603, \u2603);
            if (this.Y) {
                this.t.a();
                GL11.glEnableClientState(32884);
                GL11.glVertexPointer(3, 5126, 12, 0L);
                this.t.a(7);
                this.t.b();
                GL11.glDisableClientState(32884);
            } else {
                bfl.o(this.p);
            }
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.k();
        bfl.d();
        bfl.m();
        bfl.F();
        bfl.x();
        bfl.c(0.0f, 0.0f, 0.0f);
        double \u260310 = this.h.h.e((float)f22).b - this.k.X();
        if (\u260310 < 0.0) {
            bfl.E();
            bfl.b(0.0f, 12.0f, 0.0f);
            if (this.Y) {
                this.v.a();
                GL11.glEnableClientState(32884);
                GL11.glVertexPointer(3, 5126, 12, 0L);
                this.v.a(7);
                this.v.b();
                GL11.glDisableClientState(32884);
            } else {
                bfl.o(this.r);
            }
            bfl.F();
            \u2603 = 1.0f;
            \u2603 = -((float)(\u260310 + 65.0));
            \u2603 = -1.0f;
            f5 = \u2603;
            \u26035.a(7, bms.f);
            \u26035.b(-1.0, (double)f5, 1.0).b(0, 0, 0, 255).d();
            \u26035.b(1.0, (double)f5, 1.0).b(0, 0, 0, 255).d();
            \u26035.b(1.0, -1.0, 1.0).b(0, 0, 0, 255).d();
            \u26035.b(-1.0, -1.0, 1.0).b(0, 0, 0, 255).d();
            \u26035.b(-1.0, -1.0, -1.0).b(0, 0, 0, 255).d();
            \u26035.b(1.0, -1.0, -1.0).b(0, 0, 0, 255).d();
            \u26035.b(1.0, (double)f5, -1.0).b(0, 0, 0, 255).d();
            \u26035.b(-1.0, (double)f5, -1.0).b(0, 0, 0, 255).d();
            \u26035.b(1.0, -1.0, -1.0).b(0, 0, 0, 255).d();
            \u26035.b(1.0, -1.0, 1.0).b(0, 0, 0, 255).d();
            \u26035.b(1.0, (double)f5, 1.0).b(0, 0, 0, 255).d();
            \u26035.b(1.0, (double)f5, -1.0).b(0, 0, 0, 255).d();
            \u26035.b(-1.0, (double)f5, -1.0).b(0, 0, 0, 255).d();
            \u26035.b(-1.0, (double)f5, 1.0).b(0, 0, 0, 255).d();
            \u26035.b(-1.0, -1.0, 1.0).b(0, 0, 0, 255).d();
            \u26035.b(-1.0, -1.0, -1.0).b(0, 0, 0, 255).d();
            \u26035.b(-1.0, -1.0, -1.0).b(0, 0, 0, 255).d();
            \u26035.b(-1.0, -1.0, 1.0).b(0, 0, 0, 255).d();
            \u26035.b(1.0, -1.0, 1.0).b(0, 0, 0, 255).d();
            \u26035.b(1.0, -1.0, -1.0).b(0, 0, 0, 255).d();
            bfx2.b();
        }
        if (this.k.t.g()) {
            bfl.c(\u26032 * 0.2f + 0.04f, \u26033 * 0.2f + 0.04f, \u26034 * 0.6f + 0.1f);
        } else {
            bfl.c(\u26032, \u26033, \u26034);
        }
        bfl.E();
        bfl.b(0.0f, -((float)(\u260310 - 16.0)), 0.0f);
        bfl.o(this.r);
        bfl.F();
        bfl.w();
        bfl.a(true);
    }

    public void b(float f2, int n2) {
        if (!this.h.f.t.d()) {
            return;
        }
        if (this.h.t.e() == 2) {
            this.c(f2, n2);
            return;
        }
        bfl.p();
        float f3 = (float)(this.h.ac().Q + (this.h.ac().t - this.h.ac().Q) * (double)f2);
        int \u26032 = 32;
        int \u26033 = 8;
        bfx \u26034 = bfx.a();
        bfd \u26035 = \u26034.c();
        this.i.a(e);
        bfl.l();
        bfl.a(770, 771, 1, 0);
        aui \u26036 = this.k.e(f2);
        \u2603 = (float)\u26036.a;
        \u2603 = (float)\u26036.b;
        \u2603 = (float)\u26036.c;
        if (n2 != 2) {
            \u2603 = (\u2603 * 30.0f + \u2603 * 59.0f + \u2603 * 11.0f) / 100.0f;
            \u2603 = (\u2603 * 30.0f + \u2603 * 70.0f) / 100.0f;
            \u2603 = (\u2603 * 30.0f + \u2603 * 70.0f) / 100.0f;
            \u2603 = \u2603;
            \u2603 = \u2603;
            \u2603 = \u2603;
        }
        \u2603 = 4.8828125E-4f;
        double \u26037 = (float)this.w + f2;
        double \u26038 = this.h.ac().p + (this.h.ac().s - this.h.ac().p) * (double)f2 + \u26037 * (double)0.03f;
        double \u26039 = this.h.ac().r + (this.h.ac().u - this.h.ac().r) * (double)f2;
        int \u260310 = ns.c(\u26038 / 2048.0);
        int \u260311 = ns.c(\u26039 / 2048.0);
        \u2603 = this.k.t.f() - f3 + 0.33f;
        \u2603 = (float)((\u26038 -= (double)(\u260310 * 2048)) * 4.8828125E-4);
        \u2603 = (float)((\u26039 -= (double)(\u260311 * 2048)) * 4.8828125E-4);
        \u26035.a(7, bms.i);
        for (int i2 = -256; i2 < 256; i2 += 32) {
            for (\u2603 = -256; \u2603 < 256; \u2603 += 32) {
                \u26035.b((double)(i2 + 0), (double)\u2603, (double)(\u2603 + 32)).a((float)(i2 + 0) * 4.8828125E-4f + \u2603, (float)(\u2603 + 32) * 4.8828125E-4f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).d();
                \u26035.b((double)(i2 + 32), (double)\u2603, (double)(\u2603 + 32)).a((float)(i2 + 32) * 4.8828125E-4f + \u2603, (float)(\u2603 + 32) * 4.8828125E-4f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).d();
                \u26035.b((double)(i2 + 32), (double)\u2603, (double)(\u2603 + 0)).a((float)(i2 + 32) * 4.8828125E-4f + \u2603, (float)(\u2603 + 0) * 4.8828125E-4f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).d();
                \u26035.b((double)(i2 + 0), (double)\u2603, (double)(\u2603 + 0)).a((float)(i2 + 0) * 4.8828125E-4f + \u2603, (float)(\u2603 + 0) * 4.8828125E-4f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).d();
            }
        }
        \u26034.b();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.k();
        bfl.o();
    }

    public boolean a(double d2, double d3, double d4, float f2) {
        return false;
    }

    private void c(float f2, int n2) {
        bfl.p();
        float f3 = (float)(this.h.ac().Q + (this.h.ac().t - this.h.ac().Q) * (double)f2);
        bfx \u26032 = bfx.a();
        bfd \u26033 = \u26032.c();
        \u2603 = 12.0f;
        \u2603 = 4.0f;
        double \u26034 = (float)this.w + f2;
        double \u26035 = (this.h.ac().p + (this.h.ac().s - this.h.ac().p) * (double)f2 + \u26034 * (double)0.03f) / 12.0;
        double \u26036 = (this.h.ac().r + (this.h.ac().u - this.h.ac().r) * (double)f2) / 12.0 + (double)0.33f;
        \u2603 = this.k.t.f() - f3 + 0.33f;
        int \u26037 = ns.c(\u26035 / 2048.0);
        int \u26038 = ns.c(\u26036 / 2048.0);
        \u26035 -= (double)(\u26037 * 2048);
        \u26036 -= (double)(\u26038 * 2048);
        this.i.a(e);
        bfl.l();
        bfl.a(770, 771, 1, 0);
        aui \u26039 = this.k.e(f2);
        \u2603 = (float)\u26039.a;
        \u2603 = (float)\u26039.b;
        \u2603 = (float)\u26039.c;
        if (n2 != 2) {
            \u2603 = (\u2603 * 30.0f + \u2603 * 59.0f + \u2603 * 11.0f) / 100.0f;
            \u2603 = (\u2603 * 30.0f + \u2603 * 70.0f) / 100.0f;
            \u2603 = (\u2603 * 30.0f + \u2603 * 70.0f) / 100.0f;
            \u2603 = \u2603;
            \u2603 = \u2603;
            \u2603 = \u2603;
        }
        \u2603 = \u2603 * 0.9f;
        \u2603 = \u2603 * 0.9f;
        \u2603 = \u2603 * 0.9f;
        \u2603 = \u2603 * 0.7f;
        \u2603 = \u2603 * 0.7f;
        \u2603 = \u2603 * 0.7f;
        \u2603 = \u2603 * 0.8f;
        \u2603 = \u2603 * 0.8f;
        \u2603 = \u2603 * 0.8f;
        \u2603 = 0.00390625f;
        \u2603 = (float)ns.c(\u26035) * 0.00390625f;
        \u2603 = (float)ns.c(\u26036) * 0.00390625f;
        \u2603 = (float)(\u26035 - (double)ns.c(\u26035));
        \u2603 = (float)(\u26036 - (double)ns.c(\u26036));
        int \u260310 = 8;
        int \u260311 = 4;
        \u2603 = 9.765625E-4f;
        bfl.a(12.0f, 1.0f, 12.0f);
        for (int i2 = 0; i2 < 2; ++i2) {
            if (i2 == 0) {
                bfl.a(false, false, false, false);
            } else {
                switch (n2) {
                    case 0: {
                        bfl.a(false, true, true, true);
                        break;
                    }
                    case 1: {
                        bfl.a(true, false, false, true);
                        break;
                    }
                    case 2: {
                        bfl.a(true, true, true, true);
                    }
                }
            }
            for (n3 = -3; n3 <= 4; ++n3) {
                for (\u2603 = -3; \u2603 <= 4; ++\u2603) {
                    int n3;
                    \u26033.a(7, bms.l);
                    float f4 = n3 * 8;
                    \u2603 = \u2603 * 8;
                    \u2603 = f4 - \u2603;
                    \u2603 = \u2603 - \u2603;
                    if (\u2603 > -5.0f) {
                        \u26033.b((double)(\u2603 + 0.0f), (double)(\u2603 + 0.0f), (double)(\u2603 + 8.0f)).a((f4 + 0.0f) * 0.00390625f + \u2603, (\u2603 + 8.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, -1.0f, 0.0f).d();
                        \u26033.b((double)(\u2603 + 8.0f), (double)(\u2603 + 0.0f), (double)(\u2603 + 8.0f)).a((f4 + 8.0f) * 0.00390625f + \u2603, (\u2603 + 8.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, -1.0f, 0.0f).d();
                        \u26033.b((double)(\u2603 + 8.0f), (double)(\u2603 + 0.0f), (double)(\u2603 + 0.0f)).a((f4 + 8.0f) * 0.00390625f + \u2603, (\u2603 + 0.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, -1.0f, 0.0f).d();
                        \u26033.b((double)(\u2603 + 0.0f), (double)(\u2603 + 0.0f), (double)(\u2603 + 0.0f)).a((f4 + 0.0f) * 0.00390625f + \u2603, (\u2603 + 0.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, -1.0f, 0.0f).d();
                    }
                    if (\u2603 <= 5.0f) {
                        \u26033.b((double)(\u2603 + 0.0f), (double)(\u2603 + 4.0f - 9.765625E-4f), (double)(\u2603 + 8.0f)).a((f4 + 0.0f) * 0.00390625f + \u2603, (\u2603 + 8.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, 1.0f, 0.0f).d();
                        \u26033.b((double)(\u2603 + 8.0f), (double)(\u2603 + 4.0f - 9.765625E-4f), (double)(\u2603 + 8.0f)).a((f4 + 8.0f) * 0.00390625f + \u2603, (\u2603 + 8.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, 1.0f, 0.0f).d();
                        \u26033.b((double)(\u2603 + 8.0f), (double)(\u2603 + 4.0f - 9.765625E-4f), (double)(\u2603 + 0.0f)).a((f4 + 8.0f) * 0.00390625f + \u2603, (\u2603 + 0.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, 1.0f, 0.0f).d();
                        \u26033.b((double)(\u2603 + 0.0f), (double)(\u2603 + 4.0f - 9.765625E-4f), (double)(\u2603 + 0.0f)).a((f4 + 0.0f) * 0.00390625f + \u2603, (\u2603 + 0.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, 1.0f, 0.0f).d();
                    }
                    if (n3 > -1) {
                        for (int i3 = 0; i3 < 8; ++i3) {
                            \u26033.b((double)(\u2603 + (float)i3 + 0.0f), (double)(\u2603 + 0.0f), (double)(\u2603 + 8.0f)).a((f4 + (float)i3 + 0.5f) * 0.00390625f + \u2603, (\u2603 + 8.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(-1.0f, 0.0f, 0.0f).d();
                            \u26033.b((double)(\u2603 + (float)i3 + 0.0f), (double)(\u2603 + 4.0f), (double)(\u2603 + 8.0f)).a((f4 + (float)i3 + 0.5f) * 0.00390625f + \u2603, (\u2603 + 8.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(-1.0f, 0.0f, 0.0f).d();
                            \u26033.b((double)(\u2603 + (float)i3 + 0.0f), (double)(\u2603 + 4.0f), (double)(\u2603 + 0.0f)).a((f4 + (float)i3 + 0.5f) * 0.00390625f + \u2603, (\u2603 + 0.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(-1.0f, 0.0f, 0.0f).d();
                            \u26033.b((double)(\u2603 + (float)i3 + 0.0f), (double)(\u2603 + 0.0f), (double)(\u2603 + 0.0f)).a((f4 + (float)i3 + 0.5f) * 0.00390625f + \u2603, (\u2603 + 0.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(-1.0f, 0.0f, 0.0f).d();
                        }
                    }
                    if (n3 <= 1) {
                        for (i3 = 0; i3 < 8; ++i3) {
                            \u26033.b((double)(\u2603 + (float)i3 + 1.0f - 9.765625E-4f), (double)(\u2603 + 0.0f), (double)(\u2603 + 8.0f)).a((f4 + (float)i3 + 0.5f) * 0.00390625f + \u2603, (\u2603 + 8.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(1.0f, 0.0f, 0.0f).d();
                            \u26033.b((double)(\u2603 + (float)i3 + 1.0f - 9.765625E-4f), (double)(\u2603 + 4.0f), (double)(\u2603 + 8.0f)).a((f4 + (float)i3 + 0.5f) * 0.00390625f + \u2603, (\u2603 + 8.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(1.0f, 0.0f, 0.0f).d();
                            \u26033.b((double)(\u2603 + (float)i3 + 1.0f - 9.765625E-4f), (double)(\u2603 + 4.0f), (double)(\u2603 + 0.0f)).a((f4 + (float)i3 + 0.5f) * 0.00390625f + \u2603, (\u2603 + 0.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(1.0f, 0.0f, 0.0f).d();
                            \u26033.b((double)(\u2603 + (float)i3 + 1.0f - 9.765625E-4f), (double)(\u2603 + 0.0f), (double)(\u2603 + 0.0f)).a((f4 + (float)i3 + 0.5f) * 0.00390625f + \u2603, (\u2603 + 0.0f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(1.0f, 0.0f, 0.0f).d();
                        }
                    }
                    if (\u2603 > -1) {
                        for (i3 = 0; i3 < 8; ++i3) {
                            \u26033.b((double)(\u2603 + 0.0f), (double)(\u2603 + 4.0f), (double)(\u2603 + (float)i3 + 0.0f)).a((f4 + 0.0f) * 0.00390625f + \u2603, (\u2603 + (float)i3 + 0.5f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, 0.0f, -1.0f).d();
                            \u26033.b((double)(\u2603 + 8.0f), (double)(\u2603 + 4.0f), (double)(\u2603 + (float)i3 + 0.0f)).a((f4 + 8.0f) * 0.00390625f + \u2603, (\u2603 + (float)i3 + 0.5f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, 0.0f, -1.0f).d();
                            \u26033.b((double)(\u2603 + 8.0f), (double)(\u2603 + 0.0f), (double)(\u2603 + (float)i3 + 0.0f)).a((f4 + 8.0f) * 0.00390625f + \u2603, (\u2603 + (float)i3 + 0.5f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, 0.0f, -1.0f).d();
                            \u26033.b((double)(\u2603 + 0.0f), (double)(\u2603 + 0.0f), (double)(\u2603 + (float)i3 + 0.0f)).a((f4 + 0.0f) * 0.00390625f + \u2603, (\u2603 + (float)i3 + 0.5f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, 0.0f, -1.0f).d();
                        }
                    }
                    if (\u2603 <= 1) {
                        for (i3 = 0; i3 < 8; ++i3) {
                            \u26033.b((double)(\u2603 + 0.0f), (double)(\u2603 + 4.0f), (double)(\u2603 + (float)i3 + 1.0f - 9.765625E-4f)).a((f4 + 0.0f) * 0.00390625f + \u2603, (\u2603 + (float)i3 + 0.5f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, 0.0f, 1.0f).d();
                            \u26033.b((double)(\u2603 + 8.0f), (double)(\u2603 + 4.0f), (double)(\u2603 + (float)i3 + 1.0f - 9.765625E-4f)).a((f4 + 8.0f) * 0.00390625f + \u2603, (\u2603 + (float)i3 + 0.5f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, 0.0f, 1.0f).d();
                            \u26033.b((double)(\u2603 + 8.0f), (double)(\u2603 + 0.0f), (double)(\u2603 + (float)i3 + 1.0f - 9.765625E-4f)).a((f4 + 8.0f) * 0.00390625f + \u2603, (\u2603 + (float)i3 + 0.5f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, 0.0f, 1.0f).d();
                            \u26033.b((double)(\u2603 + 0.0f), (double)(\u2603 + 0.0f), (double)(\u2603 + (float)i3 + 1.0f - 9.765625E-4f)).a((f4 + 0.0f) * 0.00390625f + \u2603, (\u2603 + (float)i3 + 0.5f) * 0.00390625f + \u2603).a(\u2603, \u2603, \u2603, 0.8f).c(0.0f, 0.0f, 1.0f).d();
                        }
                    }
                    \u26032.b();
                }
            }
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.k();
        bfl.o();
    }

    public void a(long l2) {
        this.ac |= this.N.a(l2);
        if (!this.l.isEmpty()) {
            Iterator<bht> iterator = this.l.iterator();
            while (iterator.hasNext() && this.N.a(\u2603 = iterator.next())) {
                \u2603.a(false);
                iterator.remove();
                long l3 = l2 - System.nanoTime();
                if (l3 >= 0L) continue;
                break;
            }
        }
    }

    public void a(pk pk2, float f2) {
        float \u260320;
        double d2;
        double \u260319;
        float f3;
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        ams \u26033 = this.k.af();
        double \u26034 = this.h.t.c * 16;
        if (pk2.s < \u26033.d() - \u26034 && pk2.s > \u26033.b() + \u26034 && pk2.u < \u26033.e() - \u26034 && pk2.u > \u26033.c() + \u26034) {
            return;
        }
        double \u26035 = 1.0 - \u26033.a(pk2) / \u26034;
        \u26035 = Math.pow(\u26035, 4.0);
        double \u26036 = pk2.P + (pk2.s - pk2.P) * (double)f2;
        double \u26037 = pk2.Q + (pk2.t - pk2.Q) * (double)f2;
        double \u26038 = pk2.R + (pk2.u - pk2.R) * (double)f2;
        bfl.l();
        bfl.a(770, 1, 1, 0);
        this.i.a(g);
        bfl.a(false);
        bfl.E();
        int \u26039 = \u26033.a().a();
        float \u260310 = (float)(\u26039 >> 16 & 0xFF) / 255.0f;
        float \u260311 = (float)(\u26039 >> 8 & 0xFF) / 255.0f;
        float \u260312 = (float)(\u26039 & 0xFF) / 255.0f;
        bfl.c(\u260310, \u260311, \u260312, (float)\u26035);
        bfl.a(-3.0f, -3.0f);
        bfl.q();
        bfl.a(516, 0.1f);
        bfl.d();
        bfl.p();
        float \u260313 = (float)(ave.J() % 3000L) / 3000.0f;
        float \u260314 = 0.0f;
        float \u260315 = 0.0f;
        float \u260316 = 128.0f;
        \u26032.a(7, bms.g);
        \u26032.c(-\u26036, -\u26037, -\u26038);
        double \u260317 = Math.max((double)ns.c(\u26038 - \u26034), \u26033.c());
        double \u260318 = Math.min((double)ns.f(\u26038 + \u26034), \u26033.e());
        if (\u26036 > \u26033.d() - \u26034) {
            f3 = 0.0f;
            \u260319 = \u260317;
            while (\u260319 < \u260318) {
                d2 = Math.min(1.0, \u260318 - \u260319);
                \u260320 = (float)d2 * 0.5f;
                \u26032.b(\u26033.d(), 256.0, \u260319).a(\u260313 + f3, \u260313 + 0.0f).d();
                \u26032.b(\u26033.d(), 256.0, \u260319 + d2).a(\u260313 + \u260320 + f3, \u260313 + 0.0f).d();
                \u26032.b(\u26033.d(), 0.0, \u260319 + d2).a(\u260313 + \u260320 + f3, \u260313 + 128.0f).d();
                \u26032.b(\u26033.d(), 0.0, \u260319).a(\u260313 + f3, \u260313 + 128.0f).d();
                \u260319 += 1.0;
                f3 += 0.5f;
            }
        }
        if (\u26036 < \u26033.b() + \u26034) {
            f3 = 0.0f;
            \u260319 = \u260317;
            while (\u260319 < \u260318) {
                d2 = Math.min(1.0, \u260318 - \u260319);
                \u260320 = (float)d2 * 0.5f;
                \u26032.b(\u26033.b(), 256.0, \u260319).a(\u260313 + f3, \u260313 + 0.0f).d();
                \u26032.b(\u26033.b(), 256.0, \u260319 + d2).a(\u260313 + \u260320 + f3, \u260313 + 0.0f).d();
                \u26032.b(\u26033.b(), 0.0, \u260319 + d2).a(\u260313 + \u260320 + f3, \u260313 + 128.0f).d();
                \u26032.b(\u26033.b(), 0.0, \u260319).a(\u260313 + f3, \u260313 + 128.0f).d();
                \u260319 += 1.0;
                f3 += 0.5f;
            }
        }
        \u260317 = Math.max((double)ns.c(\u26036 - \u26034), \u26033.b());
        \u260318 = Math.min((double)ns.f(\u26036 + \u26034), \u26033.d());
        if (\u26038 > \u26033.e() - \u26034) {
            f3 = 0.0f;
            \u260319 = \u260317;
            while (\u260319 < \u260318) {
                d2 = Math.min(1.0, \u260318 - \u260319);
                \u260320 = (float)d2 * 0.5f;
                \u26032.b(\u260319, 256.0, \u26033.e()).a(\u260313 + f3, \u260313 + 0.0f).d();
                \u26032.b(\u260319 + d2, 256.0, \u26033.e()).a(\u260313 + \u260320 + f3, \u260313 + 0.0f).d();
                \u26032.b(\u260319 + d2, 0.0, \u26033.e()).a(\u260313 + \u260320 + f3, \u260313 + 128.0f).d();
                \u26032.b(\u260319, 0.0, \u26033.e()).a(\u260313 + f3, \u260313 + 128.0f).d();
                \u260319 += 1.0;
                f3 += 0.5f;
            }
        }
        if (\u26038 < \u26033.c() + \u26034) {
            f3 = 0.0f;
            \u260319 = \u260317;
            while (\u260319 < \u260318) {
                d2 = Math.min(1.0, \u260318 - \u260319);
                \u260320 = (float)d2 * 0.5f;
                \u26032.b(\u260319, 256.0, \u26033.c()).a(\u260313 + f3, \u260313 + 0.0f).d();
                \u26032.b(\u260319 + d2, 256.0, \u26033.c()).a(\u260313 + \u260320 + f3, \u260313 + 0.0f).d();
                \u26032.b(\u260319 + d2, 0.0, \u26033.c()).a(\u260313 + \u260320 + f3, \u260313 + 128.0f).d();
                \u26032.b(\u260319, 0.0, \u26033.c()).a(\u260313 + f3, \u260313 + 128.0f).d();
                \u260319 += 1.0;
                f3 += 0.5f;
            }
        }
        bfx2.b();
        \u26032.c(0.0, 0.0, 0.0);
        bfl.o();
        bfl.c();
        bfl.a(0.0f, 0.0f);
        bfl.r();
        bfl.d();
        bfl.k();
        bfl.F();
        bfl.a(true);
    }

    private void s() {
        bfl.a(774, 768, 1, 0);
        bfl.l();
        bfl.c(1.0f, 1.0f, 1.0f, 0.5f);
        bfl.a(-3.0f, -3.0f);
        bfl.q();
        bfl.a(516, 0.1f);
        bfl.d();
        bfl.E();
    }

    private void t() {
        bfl.c();
        bfl.a(0.0f, 0.0f);
        bfl.r();
        bfl.d();
        bfl.a(true);
        bfl.F();
    }

    public void a(bfx bfx2, bfd bfd2, pk pk2, float f2) {
        double d2 = pk2.P + (pk2.s - pk2.P) * (double)f2;
        \u2603 = pk2.Q + (pk2.t - pk2.Q) * (double)f2;
        \u2603 = pk2.R + (pk2.u - pk2.R) * (double)f2;
        if (!this.x.isEmpty()) {
            this.i.a(bmh.g);
            this.s();
            bfd2.a(7, bms.a);
            bfd2.c(-d2, -\u2603, -\u2603);
            bfd2.c();
            Iterator<kw> iterator = this.x.values().iterator();
            while (iterator.hasNext()) {
                kw kw2 = iterator.next();
                cj \u26032 = kw2.b();
                double \u26033 = (double)\u26032.n() - d2;
                double \u26034 = (double)\u26032.o() - \u2603;
                double \u26035 = (double)\u26032.p() - \u2603;
                afh \u26036 = this.k.p(\u26032).c();
                if (\u26036 instanceof afs || \u26036 instanceof agp || \u26036 instanceof ajl || \u26036 instanceof ajm) continue;
                if (\u26033 * \u26033 + \u26034 * \u26034 + \u26035 * \u26035 > 1024.0) {
                    iterator.remove();
                    continue;
                }
                alz \u26037 = this.k.p(\u26032);
                if (\u26037.c().t() == arm.a) continue;
                int \u26038 = kw2.c();
                bmi \u26039 = this.z[\u26038];
                bgd \u260310 = this.h.ae();
                \u260310.a(\u26037, \u26032, \u26039, this.k);
            }
            bfx2.b();
            bfd2.c(0.0, 0.0, 0.0);
            this.t();
        }
    }

    public void a(wn wn2, auh auh2, int n2, float f2) {
        if (n2 == 0 && auh2.a == auh.a.b) {
            bfl.l();
            bfl.a(770, 771, 1, 0);
            bfl.c(0.0f, 0.0f, 0.0f, 0.4f);
            GL11.glLineWidth(2.0f);
            bfl.x();
            bfl.a(false);
            \u2603 = 0.002f;
            cj cj2 = auh2.a();
            afh \u26032 = this.k.p(cj2).c();
            if (\u26032.t() != arm.a && this.k.af().a(cj2)) {
                \u26032.a((adq)this.k, cj2);
                double d2 = wn2.P + (wn2.s - wn2.P) * (double)f2;
                \u2603 = wn2.Q + (wn2.t - wn2.Q) * (double)f2;
                \u2603 = wn2.R + (wn2.u - wn2.R) * (double)f2;
                bfr.a(\u26032.b(this.k, cj2).b(0.002f, 0.002f, 0.002f).c(-d2, -\u2603, -\u2603));
            }
            bfl.a(true);
            bfl.w();
            bfl.k();
        }
    }

    public static void a(aug aug2) {
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        \u26032.a(3, bms.e);
        \u26032.b(aug2.a, aug2.b, aug2.c).d();
        \u26032.b(aug2.d, aug2.b, aug2.c).d();
        \u26032.b(aug2.d, aug2.b, aug2.f).d();
        \u26032.b(aug2.a, aug2.b, aug2.f).d();
        \u26032.b(aug2.a, aug2.b, aug2.c).d();
        bfx2.b();
        \u26032.a(3, bms.e);
        \u26032.b(aug2.a, aug2.e, aug2.c).d();
        \u26032.b(aug2.d, aug2.e, aug2.c).d();
        \u26032.b(aug2.d, aug2.e, aug2.f).d();
        \u26032.b(aug2.a, aug2.e, aug2.f).d();
        \u26032.b(aug2.a, aug2.e, aug2.c).d();
        bfx2.b();
        \u26032.a(1, bms.e);
        \u26032.b(aug2.a, aug2.b, aug2.c).d();
        \u26032.b(aug2.a, aug2.e, aug2.c).d();
        \u26032.b(aug2.d, aug2.b, aug2.c).d();
        \u26032.b(aug2.d, aug2.e, aug2.c).d();
        \u26032.b(aug2.d, aug2.b, aug2.f).d();
        \u26032.b(aug2.d, aug2.e, aug2.f).d();
        \u26032.b(aug2.a, aug2.b, aug2.f).d();
        \u26032.b(aug2.a, aug2.e, aug2.f).d();
        bfx2.b();
    }

    public static void a(aug aug2, int n2, int n3, int n4, int n5) {
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        \u26032.a(3, bms.f);
        \u26032.b(aug2.a, aug2.b, aug2.c).b(n2, n3, n4, n5).d();
        \u26032.b(aug2.d, aug2.b, aug2.c).b(n2, n3, n4, n5).d();
        \u26032.b(aug2.d, aug2.b, aug2.f).b(n2, n3, n4, n5).d();
        \u26032.b(aug2.a, aug2.b, aug2.f).b(n2, n3, n4, n5).d();
        \u26032.b(aug2.a, aug2.b, aug2.c).b(n2, n3, n4, n5).d();
        bfx2.b();
        \u26032.a(3, bms.f);
        \u26032.b(aug2.a, aug2.e, aug2.c).b(n2, n3, n4, n5).d();
        \u26032.b(aug2.d, aug2.e, aug2.c).b(n2, n3, n4, n5).d();
        \u26032.b(aug2.d, aug2.e, aug2.f).b(n2, n3, n4, n5).d();
        \u26032.b(aug2.a, aug2.e, aug2.f).b(n2, n3, n4, n5).d();
        \u26032.b(aug2.a, aug2.e, aug2.c).b(n2, n3, n4, n5).d();
        bfx2.b();
        \u26032.a(1, bms.f);
        \u26032.b(aug2.a, aug2.b, aug2.c).b(n2, n3, n4, n5).d();
        \u26032.b(aug2.a, aug2.e, aug2.c).b(n2, n3, n4, n5).d();
        \u26032.b(aug2.d, aug2.b, aug2.c).b(n2, n3, n4, n5).d();
        \u26032.b(aug2.d, aug2.e, aug2.c).b(n2, n3, n4, n5).d();
        \u26032.b(aug2.d, aug2.b, aug2.f).b(n2, n3, n4, n5).d();
        \u26032.b(aug2.d, aug2.e, aug2.f).b(n2, n3, n4, n5).d();
        \u26032.b(aug2.a, aug2.b, aug2.f).b(n2, n3, n4, n5).d();
        \u26032.b(aug2.a, aug2.e, aug2.f).b(n2, n3, n4, n5).d();
        bfx2.b();
    }

    private void b(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.o.a(n2, n3, n4, n5, n6, n7);
    }

    @Override
    public void a(cj cj2) {
        int n2 = cj2.n();
        \u2603 = cj2.o();
        \u2603 = cj2.p();
        this.b(n2 - 1, \u2603 - 1, \u2603 - 1, n2 + 1, \u2603 + 1, \u2603 + 1);
    }

    @Override
    public void b(cj cj2) {
        int n2 = cj2.n();
        \u2603 = cj2.o();
        \u2603 = cj2.p();
        this.b(n2 - 1, \u2603 - 1, \u2603 - 1, n2 + 1, \u2603 + 1, \u2603 + 1);
    }

    @Override
    public void a(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.b(n2 - 1, n3 - 1, n4 - 1, n5 + 1, n6 + 1, n7 + 1);
    }

    @Override
    public void a(String string, cj cj2) {
        bpj \u26032 = this.y.get(cj2);
        if (\u26032 != null) {
            this.h.W().b(\u26032);
            this.y.remove(cj2);
        }
        if (string != null) {
            aak aak2 = aak.b(string);
            if (aak2 != null) {
                this.h.q.a(aak2.g());
            }
            \u26032 = bpf.a(new jy(string), cj2.n(), cj2.o(), cj2.p());
            this.y.put(cj2, \u26032);
            this.h.W().a(\u26032);
        }
    }

    @Override
    public void a(String string, double d2, double d3, double d4, float f2, float f3) {
    }

    @Override
    public void a(wn wn2, String string, double d2, double d3, double d4, float f2, float f3) {
    }

    @Override
    public void a(int n2, boolean bl2, final double d2, final double d3, final double d4, double d5, double d6, double d7, int ... nArray) {
        try {
            this.b(n2, bl2, d2, d3, d4, d5, d6, d7, nArray);
        }
        catch (Throwable throwable) {
            b b2 = b.a(throwable, "Exception while adding particle");
            c \u26032 = b2.a("Particle being added");
            \u26032.a("ID", n2);
            if (nArray != null) {
                \u26032.a("Parameters", nArray);
            }
            \u26032.a("Position", new Callable<String>(){

                public String a() throws Exception {
                    return c.a(d2, d3, d4);
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            throw new e(b2);
        }
    }

    private void a(cy cy2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
        this.a(cy2.c(), cy2.e(), d2, d3, d4, d5, d6, d7, nArray);
    }

    private beb b(int n2, boolean bl2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
        if (this.h == null || this.h.ac() == null || this.h.j == null) {
            return null;
        }
        int n3 = this.h.t.aL;
        if (n3 == 1 && this.k.s.nextInt(3) == 0) {
            n3 = 2;
        }
        double \u26032 = this.h.ac().s - d2;
        double \u26033 = this.h.ac().t - d3;
        double \u26034 = this.h.ac().u - d4;
        if (bl2) {
            return this.h.j.a(n2, d2, d3, d4, d5, d6, d7, nArray);
        }
        double \u26035 = 16.0;
        if (\u26032 * \u26032 + \u26033 * \u26033 + \u26034 * \u26034 > 256.0) {
            return null;
        }
        if (n3 > 1) {
            return null;
        }
        return this.h.j.a(n2, d2, d3, d4, d5, d6, d7, nArray);
    }

    @Override
    public void a(pk pk2) {
    }

    @Override
    public void b(pk pk2) {
    }

    public void k() {
    }

    @Override
    public void a(int n2, cj cj2, int n3) {
        switch (n2) {
            case 1013: 
            case 1018: {
                if (this.h.ac() == null) break;
                double d2 = (double)cj2.n() - this.h.ac().s;
                \u2603 = (double)cj2.o() - this.h.ac().t;
                \u2603 = (double)cj2.p() - this.h.ac().u;
                \u2603 = Math.sqrt(d2 * d2 + \u2603 * \u2603 + \u2603 * \u2603);
                \u2603 = this.h.ac().s;
                \u2603 = this.h.ac().t;
                \u2603 = this.h.ac().u;
                if (\u2603 > 0.0) {
                    \u2603 += d2 / \u2603 * 2.0;
                    \u2603 += \u2603 / \u2603 * 2.0;
                    \u2603 += \u2603 / \u2603 * 2.0;
                }
                if (n2 == 1013) {
                    this.k.a(\u2603, \u2603, \u2603, "mob.wither.spawn", 1.0f, 1.0f, false);
                    break;
                }
                this.k.a(\u2603, \u2603, \u2603, "mob.enderdragon.end", 5.0f, 1.0f, false);
            }
        }
    }

    @Override
    public void a(wn wn2, int n2, cj cj22, int n32) {
        Random random = this.k.s;
        switch (n2) {
            case 1001: {
                this.k.a(cj22, "random.click", 1.0f, 1.2f, false);
                break;
            }
            case 1000: {
                this.k.a(cj22, "random.click", 1.0f, 1.0f, false);
                break;
            }
            case 1002: {
                this.k.a(cj22, "random.bow", 1.0f, 1.2f, false);
                break;
            }
            case 2000: {
                int n4 = n32 % 3 - 1;
                \u2603 = n32 / 3 % 3 - 1;
                double \u26032 = (double)cj22.n() + (double)n4 * 0.6 + 0.5;
                double \u26033 = (double)cj22.o() + 0.5;
                double \u26034 = (double)cj22.p() + (double)\u2603 * 0.6 + 0.5;
                for (\u2603 = 0; \u2603 < 10; ++\u2603) {
                    double d2 = random.nextDouble() * 0.2 + 0.01;
                    \u2603 = \u26032 + (double)n4 * 0.01 + (random.nextDouble() - 0.5) * (double)\u2603 * 0.5;
                    \u2603 = \u26033 + (random.nextDouble() - 0.5) * 0.5;
                    \u2603 = \u26034 + (double)\u2603 * 0.01 + (random.nextDouble() - 0.5) * (double)n4 * 0.5;
                    \u2603 = (double)n4 * d2 + random.nextGaussian() * 0.01;
                    \u2603 = -0.03 + random.nextGaussian() * 0.01;
                    \u2603 = (double)\u2603 * d2 + random.nextGaussian() * 0.01;
                    this.a(cy.l, \u2603, \u2603, \u2603, \u2603, \u2603, \u2603, new int[0]);
                }
                break;
            }
            case 2003: {
                cj cj22;
                double \u26035 = (double)cj22.n() + 0.5;
                double \u26036 = cj22.o();
                double \u26037 = (double)cj22.p() + 0.5;
                for (int i2 = 0; i2 < 8; ++i2) {
                    this.a(cy.K, \u26035, \u26036, \u26037, random.nextGaussian() * 0.15, random.nextDouble() * 0.2, random.nextGaussian() * 0.15, zw.b(zy.bH));
                }
                for (double d3 = 0.0; d3 < Math.PI * 2; d3 += 0.15707963267948966) {
                    this.a(cy.y, \u26035 + Math.cos(d3) * 5.0, \u26036 - 0.4, \u26037 + Math.sin(d3) * 5.0, Math.cos(d3) * -5.0, 0.0, Math.sin(d3) * -5.0, new int[0]);
                    this.a(cy.y, \u26035 + Math.cos(d3) * 5.0, \u26036 - 0.4, \u26037 + Math.sin(d3) * 5.0, Math.cos(d3) * -7.0, 0.0, Math.sin(d3) * -7.0, new int[0]);
                }
                break;
            }
            case 2002: {
                int n32;
                cj cj22;
                double \u26038 = cj22.n();
                double \u26039 = cj22.o();
                double \u260310 = cj22.p();
                for (int i3 = 0; i3 < 8; ++i3) {
                    this.a(cy.K, \u26038, \u26039, \u260310, random.nextGaussian() * 0.15, random.nextDouble() * 0.2, random.nextGaussian() * 0.15, zw.b(zy.bz), n32);
                }
                i3 = zy.bz.g(n32);
                float \u260311 = (float)(i3 >> 16 & 0xFF) / 255.0f;
                float \u260312 = (float)(i3 >> 8 & 0xFF) / 255.0f;
                float \u260313 = (float)(i3 >> 0 & 0xFF) / 255.0f;
                cy \u260314 = cy.n;
                if (zy.bz.h(n32)) {
                    \u260314 = cy.o;
                }
                for (\u2603 = 0; \u2603 < 100; ++\u2603) {
                    double d4 = random.nextDouble() * 4.0;
                    \u2603 = random.nextDouble() * Math.PI * 2.0;
                    \u2603 = Math.cos(\u2603) * d4;
                    \u2603 = 0.01 + random.nextDouble() * 0.5;
                    \u2603 = Math.sin(\u2603) * d4;
                    beb \u260315 = this.b(\u260314.c(), \u260314.e(), \u26038 + \u2603 * 0.1, \u26039 + 0.3, \u260310 + \u2603 * 0.1, \u2603, \u2603, \u2603, new int[0]);
                    if (\u260315 == null) continue;
                    float \u260316 = 0.75f + random.nextFloat() * 0.25f;
                    \u260315.b(\u260311 * \u260316, \u260312 * \u260316, \u260313 * \u260316);
                    \u260315.a((float)d4);
                }
                this.k.a(cj22, "game.potion.smash", 1.0f, this.k.s.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 2001: {
                afh afh2 = afh.c(n32 & 0xFFF);
                if (afh2.t() != arm.a) {
                    this.h.W().a(new bpf(new jy(afh2.H.a()), (afh2.H.d() + 1.0f) / 2.0f, afh2.H.e() * 0.8f, (float)cj22.n() + 0.5f, (float)cj22.o() + 0.5f, (float)cj22.p() + 0.5f));
                }
                this.h.j.a(cj22, afh2.a(n32 >> 12 & 0xFF));
                break;
            }
            case 2004: {
                for (int i4 = 0; i4 < 20; ++i4) {
                    double d5 = (double)cj22.n() + 0.5 + ((double)this.k.s.nextFloat() - 0.5) * 2.0;
                    \u2603 = (double)cj22.o() + 0.5 + ((double)this.k.s.nextFloat() - 0.5) * 2.0;
                    \u2603 = (double)cj22.p() + 0.5 + ((double)this.k.s.nextFloat() - 0.5) * 2.0;
                    this.k.a(cy.l, d5, \u2603, \u2603, 0.0, 0.0, 0.0, new int[0]);
                    this.k.a(cy.A, d5, \u2603, \u2603, 0.0, 0.0, 0.0, new int[0]);
                }
                break;
            }
            case 2005: {
                cj cj22;
                ze.a(this.k, cj22, n32);
                break;
            }
            case 1006: {
                cj cj22;
                this.k.a(cj22, "random.door_close", 1.0f, this.k.s.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1003: {
                cj cj22;
                this.k.a(cj22, "random.door_open", 1.0f, this.k.s.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1004: {
                cj cj22;
                this.k.a(cj22, "random.fizz", 0.5f, 2.6f + (random.nextFloat() - random.nextFloat()) * 0.8f, false);
                break;
            }
            case 1020: {
                cj cj22;
                this.k.a(cj22, "random.anvil_break", 1.0f, this.k.s.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1021: {
                cj cj22;
                this.k.a(cj22, "random.anvil_use", 1.0f, this.k.s.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1022: {
                cj cj22;
                this.k.a(cj22, "random.anvil_land", 0.3f, this.k.s.nextFloat() * 0.1f + 0.9f, false);
                break;
            }
            case 1005: {
                int n32;
                if (zw.b(n32) instanceof aak) {
                    this.k.a(cj22, "records." + ((aak)zw.b((int)n32)).a);
                    break;
                }
                this.k.a(cj22, (String)null);
                break;
            }
            case 1007: {
                cj cj22;
                this.k.a(cj22, "mob.ghast.charge", 10.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1008: {
                cj cj22;
                this.k.a(cj22, "mob.ghast.fireball", 10.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1010: {
                cj cj22;
                this.k.a(cj22, "mob.zombie.wood", 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1012: {
                cj cj22;
                this.k.a(cj22, "mob.zombie.woodbreak", 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1011: {
                cj cj22;
                this.k.a(cj22, "mob.zombie.metal", 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1009: {
                cj cj22;
                this.k.a(cj22, "mob.ghast.fireball", 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1014: {
                cj cj22;
                this.k.a(cj22, "mob.wither.shoot", 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1016: {
                cj cj22;
                this.k.a(cj22, "mob.zombie.infect", 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1017: {
                cj cj22;
                this.k.a(cj22, "mob.zombie.unfect", 2.0f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
                break;
            }
            case 1015: {
                cj cj22;
                this.k.a(cj22, "mob.bat.takeoff", 0.05f, (random.nextFloat() - random.nextFloat()) * 0.2f + 1.0f, false);
            }
        }
    }

    @Override
    public void b(int n2, cj cj2, int n3) {
        if (n3 < 0 || n3 >= 10) {
            this.x.remove(n2);
        } else {
            kw kw2 = this.x.get(n2);
            if (kw2 == null || kw2.b().n() != cj2.n() || kw2.b().o() != cj2.o() || kw2.b().p() != cj2.p()) {
                kw2 = new kw(n2, cj2);
                this.x.put(n2, kw2);
            }
            kw2.a(n3);
            kw2.b(this.w);
        }
    }

    public void m() {
        this.ac = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a(Collection<akw> collection, Collection<akw> collection2) {
        Set<akw> set = this.n;
        synchronized (set) {
            this.n.removeAll(collection);
            this.n.addAll(collection2);
        }
    }

    class a {
        final bht a;
        final cq b;
        final Set<cq> c = EnumSet.noneOf(cq.class);
        final int d;

        private a(bht bht2, cq cq2, int n2) {
            this.a = bht2;
            this.b = cq2;
            this.d = n2;
        }
    }
}

