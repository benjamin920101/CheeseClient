/*
 * Decompiled with CFR 0.152.
 */
import java.util.concurrent.Callable;

public abstract class ase {
    private long c;
    protected ase a;
    private long d;
    protected long b;

    public static ase[] a(long l2, adr adr22, String string) {
        ase ase2;
        adr adr22;
        int n2;
        ase ase3 = new asd(1L);
        ase3 = new asa(2000L, ase3);
        ase3 = new ars(1L, ase3);
        ase3 = new asr(2001L, ase3);
        ase3 = new ars(2L, ase3);
        ase3 = new ars(50L, ase3);
        ase3 = new ars(70L, ase3);
        ase3 = new ash(2L, ase3);
        ase3 = new aru(2L, ase3);
        ase3 = new ars(3L, ase3);
        ase3 = new arr(2L, ase3, arr.a.a);
        ase3 = new arr(2L, ase3, arr.a.b);
        ase3 = new arr(3L, ase3, arr.a.c);
        ase3 = new asr(2002L, ase3);
        ase3 = new asr(2003L, ase3);
        ase3 = new ars(4L, ase3);
        ase3 = new art(5L, ase3);
        ase3 = new arq(4L, ase3);
        ase3 = asr.b(1000L, ase3, 0);
        ant \u26032 = null;
        int \u26033 = n2 = 4;
        if (adr22 == adr.f && string.length() > 0) {
            \u26032 = ant.a.a(string).b();
            n2 = \u26032.G;
            \u26033 = \u26032.H;
        }
        if (adr22 == adr.d) {
            n2 = 6;
        }
        \u2603 = ase3;
        \u2603 = asr.b(1000L, \u2603, 0);
        \u2603 = new asi(100L, \u2603);
        ase2 = ase3;
        ase2 = new arw(200L, ase2, adr22, string);
        ase2 = asr.b(1000L, ase2, 2);
        ase2 = new arv(1000L, ase2);
        \u2603 = \u2603;
        \u2603 = asr.b(1000L, \u2603, 2);
        ase2 = new asg(1000L, ase2, \u2603);
        \u2603 = asr.b(1000L, \u2603, 2);
        \u2603 = asr.b(1000L, \u2603, \u26033);
        \u2603 = new asj(1L, \u2603);
        \u2603 = new asm(1000L, \u2603);
        ase2 = new asf(1001L, ase2);
        for (int i2 = 0; i2 < n2; ++i2) {
            ase2 = new asr(1000 + i2, ase2);
            if (i2 == 0) {
                ase2 = new ars(3L, ase2);
            }
            if (i2 != 1 && n2 != 1) continue;
            ase2 = new asl(1000L, ase2);
        }
        ase2 = new asm(1000L, ase2);
        ase2 = new ask(100L, ase2, \u2603);
        \u2603 = ase2;
        asq \u26034 = new asq(10L, ase2);
        ase2.a(l2);
        \u26034.a(l2);
        return new ase[]{ase2, \u26034, \u2603};
    }

    public ase(long l2) {
        this.b = l2;
        this.b *= this.b * 6364136223846793005L + 1442695040888963407L;
        this.b += l2;
        this.b *= this.b * 6364136223846793005L + 1442695040888963407L;
        this.b += l2;
        this.b *= this.b * 6364136223846793005L + 1442695040888963407L;
        this.b += l2;
    }

    public void a(long l2) {
        this.c = l2;
        if (this.a != null) {
            this.a.a(l2);
        }
        this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
        this.c += this.b;
        this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
        this.c += this.b;
        this.c *= this.c * 6364136223846793005L + 1442695040888963407L;
        this.c += this.b;
    }

    public void a(long l2, long l3) {
        this.d = this.c;
        this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
        this.d += l2;
        this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
        this.d += l3;
        this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
        this.d += l2;
        this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
        this.d += l3;
    }

    protected int a(int n2) {
        \u2603 = (int)((this.d >> 24) % (long)n2);
        if (\u2603 < 0) {
            \u2603 += n2;
        }
        this.d *= this.d * 6364136223846793005L + 1442695040888963407L;
        this.d += this.c;
        return \u2603;
    }

    public abstract int[] a(int var1, int var2, int var3, int var4);

    protected static boolean a(int n2, int n3) {
        if (n2 == n3) {
            return true;
        }
        if (n2 == ady.ab.az || n2 == ady.ac.az) {
            return n3 == ady.ab.az || n3 == ady.ac.az;
        }
        final ady ady2 = ady.e(n2);
        \u2603 = ady.e(n3);
        try {
            if (ady2 != null && \u2603 != null) {
                return ady2.a(\u2603);
            }
        }
        catch (Throwable \u26032) {
            b b2 = b.a(\u26032, "Comparing biomes");
            c \u26033 = b2.a("Biomes being compared");
            \u26033.a("Biome A ID", n2);
            \u26033.a("Biome B ID", n3);
            \u26033.a("Biome A", new Callable<String>(){

                public String a() throws Exception {
                    return String.valueOf(ady2);
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            \u26033.a("Biome B", new Callable<String>(){

                public String a() throws Exception {
                    return String.valueOf(\u2603);
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            throw new e(b2);
        }
        return false;
    }

    protected static boolean b(int n2) {
        return n2 == ady.p.az || n2 == ady.N.az || n2 == ady.z.az;
    }

    protected int a(int ... nArray) {
        return nArray[this.a(nArray.length)];
    }

    protected int b(int n2, int n3, int n4, int n5) {
        if (n3 == n4 && n4 == n5) {
            return n3;
        }
        if (n2 == n3 && n2 == n4) {
            return n2;
        }
        if (n2 == n3 && n2 == n5) {
            return n2;
        }
        if (n2 == n4 && n2 == n5) {
            return n2;
        }
        if (n2 == n3 && n4 != n5) {
            return n2;
        }
        if (n2 == n4 && n3 != n5) {
            return n2;
        }
        if (n2 == n5 && n3 != n4) {
            return n2;
        }
        if (n3 == n4 && n2 != n5) {
            return n3;
        }
        if (n3 == n5 && n2 != n4) {
            return n3;
        }
        if (n4 == n5 && n2 != n3) {
            return n4;
        }
        return this.a(new int[]{n2, n3, n4, n5});
    }
}

