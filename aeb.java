/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aeb {
    protected adm a;
    protected Random b;
    protected cj c;
    protected ant d;
    protected aot e = new aop(4);
    protected aot f = new apo(afi.m, 7);
    protected aot g = new apo(afi.n, 6);
    protected aot h;
    protected aot i;
    protected aot j;
    protected aot k;
    protected aot l;
    protected aot m;
    protected aot n;
    protected aot o;
    protected aot p;
    protected aot q;
    protected aot r;
    protected aou s = new aou(afi.N, agw.a.a);
    protected aot t = new aom(afi.P);
    protected aot u = new aom(afi.Q);
    protected aot v = new aoz();
    protected aot w = new apm();
    protected aot x = new aon();
    protected aot y = new apx();
    protected int z;
    protected int A;
    protected int B = 2;
    protected int C = 1;
    protected int D;
    protected int E;
    protected int F;
    protected int G;
    protected int H = 1;
    protected int I = 3;
    protected int J = 1;
    protected int K;
    public boolean L = true;

    public void a(adm adm2, Random random, ady ady2, cj cj2) {
        if (this.a != null) {
            throw new RuntimeException("Already decorating");
        }
        this.a = adm2;
        String string = adm2.P().B();
        this.d = string != null ? ant.a.a(string).b() : ant.a.a("").b();
        this.b = random;
        this.c = cj2;
        this.h = new apj(afi.d.Q(), this.d.I);
        this.i = new apj(afi.n.Q(), this.d.M);
        this.j = new apj(afi.b.Q().a(ajy.a, ajy.a.b), this.d.Q);
        this.k = new apj(afi.b.Q().a(ajy.a, ajy.a.d), this.d.U);
        this.l = new apj(afi.b.Q().a(ajy.a, ajy.a.f), this.d.Y);
        this.m = new apj(afi.q.Q(), this.d.ac);
        this.n = new apj(afi.p.Q(), this.d.ag);
        this.o = new apj(afi.o.Q(), this.d.ak);
        this.p = new apj(afi.aC.Q(), this.d.ao);
        this.q = new apj(afi.ag.Q(), this.d.as);
        this.r = new apj(afi.x.Q(), this.d.aw);
        this.a(ady2);
        this.a = null;
        this.b = null;
    }

    protected void a(ady ady2) {
        Object \u26033;
        int n2;
        int n3;
        this.a();
        for (n3 = 0; n3 < this.I; ++n3) {
            n2 = this.b.nextInt(16) + 8;
            \u2603 = this.b.nextInt(16) + 8;
            this.f.b(this.a, this.b, this.a.r(this.c.a(n2, 0, \u2603)));
        }
        for (n3 = 0; n3 < this.J; ++n3) {
            n2 = this.b.nextInt(16) + 8;
            \u2603 = this.b.nextInt(16) + 8;
            this.e.b(this.a, this.b, this.a.r(this.c.a(n2, 0, \u2603)));
        }
        for (n3 = 0; n3 < this.H; ++n3) {
            n2 = this.b.nextInt(16) + 8;
            \u2603 = this.b.nextInt(16) + 8;
            this.g.b(this.a, this.b, this.a.r(this.c.a(n2, 0, \u2603)));
        }
        n3 = this.A;
        if (this.b.nextInt(10) == 0) {
            ++n3;
        }
        for (n2 = 0; n2 < n3; ++n2) {
            \u2603 = this.b.nextInt(16) + 8;
            \u2603 = this.b.nextInt(16) + 8;
            aoh aoh2 = ady2.a(this.b);
            aoh2.e();
            cj \u26032 = this.a.m(this.c.a(\u2603, 0, \u2603));
            if (!aoh2.b(this.a, this.b, \u26032)) continue;
            aoh2.a(this.a, this.b, \u26032);
        }
        for (n2 = 0; n2 < this.K; ++n2) {
            \u2603 = this.b.nextInt(16) + 8;
            \u2603 = this.b.nextInt(16) + 8;
            this.v.b(this.a, this.b, this.a.m(this.c.a(\u2603, 0, \u2603)));
        }
        for (n2 = 0; n2 < this.B; ++n2) {
            \u2603 = this.b.nextInt(16) + 8;
            \u2603 = this.a.m(this.c.a(\u2603, 0, \u2603 = this.b.nextInt(16) + 8)).o() + 32;
            if (\u2603 <= 0 || (\u2603 = ((agw.a)(\u2603 = ady2.a(this.b, (cj)(\u26033 = this.c.a(\u2603, \u2603 = this.b.nextInt(\u2603), \u2603))))).a().a()).t() == arm.a) continue;
            this.s.a(\u2603, (agw.a)\u2603);
            this.s.b(this.a, this.b, (cj)\u26033);
        }
        for (n2 = 0; n2 < this.C; ++n2) {
            \u2603 = this.b.nextInt(16) + 8;
            \u2603 = this.a.m(this.c.a(\u2603, 0, \u2603 = this.b.nextInt(16) + 8)).o() * 2;
            if (\u2603 <= 0) continue;
            \u2603 = this.b.nextInt(\u2603);
            ady2.b(this.b).b(this.a, this.b, this.c.a(\u2603, \u2603, \u2603));
        }
        for (n2 = 0; n2 < this.D; ++n2) {
            \u2603 = this.b.nextInt(16) + 8;
            \u2603 = this.a.m(this.c.a(\u2603, 0, \u2603 = this.b.nextInt(16) + 8)).o() * 2;
            if (\u2603 <= 0) continue;
            \u2603 = this.b.nextInt(\u2603);
            new aoq().b(this.a, this.b, this.c.a(\u2603, \u2603, \u2603));
        }
        for (n2 = 0; n2 < this.z; ++n2) {
            \u2603 = this.b.nextInt(16) + 8;
            \u2603 = this.a.m(this.c.a(\u2603, 0, \u2603 = this.b.nextInt(16) + 8)).o() * 2;
            if (\u2603 <= 0) continue;
            \u2603 = this.b.nextInt(\u2603);
            \u26033 = this.c.a(\u2603, \u2603, \u2603);
            while (((df)\u26033).o() > 0 && this.a.d((cj)(\u2603 = ((cj)\u26033).b()))) {
                \u26033 = \u2603;
            }
            this.y.b(this.a, this.b, (cj)\u26033);
        }
        for (n2 = 0; n2 < this.E; ++n2) {
            int n4;
            if (this.b.nextInt(4) == 0) {
                \u2603 = this.b.nextInt(16) + 8;
                \u2603 = this.b.nextInt(16) + 8;
                cj cj2 = this.a.m(this.c.a(\u2603, 0, \u2603));
                this.t.b(this.a, this.b, cj2);
            }
            if (this.b.nextInt(8) != 0 || (n4 = this.a.m(this.c.a(\u2603 = this.b.nextInt(16) + 8, 0, \u2603 = this.b.nextInt(16) + 8)).o() * 2) <= 0) continue;
            \u2603 = this.b.nextInt(n4);
            \u26033 = this.c.a(\u2603, \u2603, \u2603);
            this.u.b(this.a, this.b, (cj)\u26033);
        }
        if (this.b.nextInt(4) == 0 && (\u2603 = this.a.m(this.c.a(n2 = this.b.nextInt(16) + 8, 0, \u2603 = this.b.nextInt(16) + 8)).o() * 2) > 0) {
            \u2603 = this.b.nextInt(\u2603);
            this.t.b(this.a, this.b, this.c.a(n2, \u2603, \u2603));
        }
        if (this.b.nextInt(8) == 0 && (\u2603 = this.a.m(this.c.a(n2 = this.b.nextInt(16) + 8, 0, \u2603 = this.b.nextInt(16) + 8)).o() * 2) > 0) {
            \u2603 = this.b.nextInt(\u2603);
            this.u.b(this.a, this.b, this.c.a(n2, \u2603, \u2603));
        }
        for (n2 = 0; n2 < this.F; ++n2) {
            \u2603 = this.b.nextInt(16) + 8;
            \u2603 = this.a.m(this.c.a(\u2603, 0, \u2603 = this.b.nextInt(16) + 8)).o() * 2;
            if (\u2603 <= 0) continue;
            \u2603 = this.b.nextInt(\u2603);
            this.w.b(this.a, this.b, this.c.a(\u2603, \u2603, \u2603));
        }
        for (n2 = 0; n2 < 10; ++n2) {
            \u2603 = this.b.nextInt(16) + 8;
            \u2603 = this.a.m(this.c.a(\u2603, 0, \u2603 = this.b.nextInt(16) + 8)).o() * 2;
            if (\u2603 <= 0) continue;
            \u2603 = this.b.nextInt(\u2603);
            this.w.b(this.a, this.b, this.c.a(\u2603, \u2603, \u2603));
        }
        if (this.b.nextInt(32) == 0 && (\u2603 = this.a.m(this.c.a(n2 = this.b.nextInt(16) + 8, 0, \u2603 = this.b.nextInt(16) + 8)).o() * 2) > 0) {
            \u2603 = this.b.nextInt(\u2603);
            new apl().b(this.a, this.b, this.c.a(n2, \u2603, \u2603));
        }
        for (n2 = 0; n2 < this.G; ++n2) {
            \u2603 = this.b.nextInt(16) + 8;
            \u2603 = this.a.m(this.c.a(\u2603, 0, \u2603 = this.b.nextInt(16) + 8)).o() * 2;
            if (\u2603 <= 0) continue;
            \u2603 = this.b.nextInt(\u2603);
            this.x.b(this.a, this.b, this.c.a(\u2603, \u2603, \u2603));
        }
        if (this.L) {
            for (n2 = 0; n2 < 50; ++n2) {
                \u2603 = this.b.nextInt(16) + 8;
                \u2603 = this.b.nextInt(16) + 8;
                \u2603 = this.b.nextInt(248) + 8;
                if (\u2603 <= 0) continue;
                \u2603 = this.b.nextInt(\u2603);
                \u26033 = this.c.a(\u2603, \u2603, \u2603);
                new apr(afi.i).b(this.a, this.b, (cj)\u26033);
            }
            for (n2 = 0; n2 < 20; ++n2) {
                \u2603 = this.b.nextInt(16) + 8;
                \u2603 = this.b.nextInt(16) + 8;
                \u2603 = this.b.nextInt(this.b.nextInt(this.b.nextInt(240) + 8) + 8);
                cj cj3 = this.c.a(\u2603, \u2603, \u2603);
                new apr(afi.k).b(this.a, this.b, cj3);
            }
        }
    }

    protected void a(int n2, aot aot2, int n3, int n4) {
        if (n4 < n3) {
            \u2603 = n3;
            n3 = n4;
            n4 = \u2603;
        } else if (n4 == n3) {
            if (n3 < 255) {
                ++n4;
            } else {
                --n3;
            }
        }
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            cj cj2 = this.c.a(this.b.nextInt(16), this.b.nextInt(n4 - n3) + n3, this.b.nextInt(16));
            aot2.b(this.a, this.b, cj2);
        }
    }

    protected void b(int n2, aot aot2, int n3, int n4) {
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            cj cj2 = this.c.a(this.b.nextInt(16), this.b.nextInt(n4) + this.b.nextInt(n4) + n3 - n4, this.b.nextInt(16));
            aot2.b(this.a, this.b, cj2);
        }
    }

    protected void a() {
        this.a(this.d.J, this.h, this.d.K, this.d.L);
        this.a(this.d.N, this.i, this.d.O, this.d.P);
        this.a(this.d.V, this.k, this.d.W, this.d.X);
        this.a(this.d.R, this.j, this.d.S, this.d.T);
        this.a(this.d.Z, this.l, this.d.aa, this.d.ab);
        this.a(this.d.ad, this.m, this.d.ae, this.d.af);
        this.a(this.d.ah, this.n, this.d.ai, this.d.aj);
        this.a(this.d.al, this.o, this.d.am, this.d.an);
        this.a(this.d.ap, this.p, this.d.aq, this.d.ar);
        this.a(this.d.at, this.q, this.d.au, this.d.av);
        this.b(this.d.ax, this.r, this.d.ay, this.d.az);
    }
}

