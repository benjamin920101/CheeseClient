/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public abstract class aih
extends ahh {
    public static final amk b = amk.a("seamless");
    public static final amm<a> N = amm.a("variant", a.class);

    public aih() {
        super(arm.e);
        alz alz2 = this.M.b();
        alz2 = this.l() ? alz2.a(b, false) : alz2.a(a, ahh.a.b);
        this.j(alz2.a(N, aih$a.a));
        this.a(yz.b);
    }

    @Override
    public String f() {
        return di.a(this.a() + ".red_sandstone.name");
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zw.a(afi.cP);
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zw.a(afi.cP);
    }

    @Override
    public String b(int n2) {
        return super.a() + "." + aih$a.a(n2).d();
    }

    @Override
    public amo<?> n() {
        return N;
    }

    @Override
    public Object a(zx zx2) {
        return aih$a.a(zx2.i() & 7);
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        if (zw2 == zw.a(afi.cO)) {
            return;
        }
        for (a a2 : aih$a.values()) {
            list.add(new zx(zw2, 1, a2.a()));
        }
    }

    @Override
    public alz a(int n2) {
        alz alz2 = this.Q().a(N, aih$a.a(n2 & 7));
        alz2 = this.l() ? alz2.a(b, (n2 & 8) != 0) : alz2.a(a, (n2 & 8) == 0 ? ahh.a.b : ahh.a.a);
        return alz2;
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(N).a();
        if (this.l()) {
            if (alz2.b(b).booleanValue()) {
                n2 |= 8;
            }
        } else if (alz2.b(a) == ahh.a.a) {
            n2 |= 8;
        }
        return n2;
    }

    @Override
    protected ama e() {
        if (this.l()) {
            return new ama(this, b, N);
        }
        return new ama(this, a, N);
    }

    @Override
    public arn g(alz alz2) {
        return alz2.b(N).c();
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(N).a();
    }

    public static enum a implements nw
    {
        a(0, "red_sandstone", ajh.a.b.c());

        private static final a[] b;
        private final int c;
        private final String d;
        private final arn e;

        private a(int n3, String string2, arn arn2) {
            this.c = n3;
            this.d = string2;
            this.e = arn2;
        }

        public int a() {
            return this.c;
        }

        public arn c() {
            return this.e;
        }

        public String toString() {
            return this.d;
        }

        public static a a(int n2) {
            if (n2 < 0 || n2 >= b.length) {
                n2 = 0;
            }
            return b[n2];
        }

        @Override
        public String l() {
            return this.d;
        }

        public String d() {
            return this.d;
        }

        static {
            b = new a[aih$a.values().length];
            a[] aArray = aih$a.values();
            int \u26032 = aArray.length;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                aih$a.b[\u2603.a()] = \u2603 = aArray[i2];
            }
        }
    }
}

