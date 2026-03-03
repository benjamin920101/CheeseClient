/*
 * Decompiled with CFR 0.152.
 */
public class bdq {

    public static class d
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            b b2 = new b(adm2, d2, d3, d4, d5, d6, d7, ave.A().j);
            b2.i(0.99f);
            return b2;
        }
    }

    public static class a
    extends beb {
        protected a(adm adm2, double d2, double d3, double d4) {
            super(adm2, d2, d3, d4);
            this.g = 4;
        }

        @Override
        public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
            \u2603 = 0.25f;
            \u2603 = 0.5f;
            \u2603 = 0.125f;
            \u2603 = 0.375f;
            \u2603 = 7.1f * ns.a(((float)this.f + f2 - 1.0f) * 0.25f * (float)Math.PI);
            this.au = 0.6f - ((float)this.f + f2 - 1.0f) * 0.25f * 0.5f;
            \u2603 = (float)(this.p + (this.s - this.p) * (double)f2 - aw);
            \u2603 = (float)(this.q + (this.t - this.q) * (double)f2 - ax);
            \u2603 = (float)(this.r + (this.u - this.r) * (double)f2 - ay);
            int n2 = this.b(f2);
            \u2603 = n2 >> 16 & 0xFFFF;
            \u2603 = n2 & 0xFFFF;
            bfd2.b((double)(\u2603 - f3 * \u2603 - f6 * \u2603), (double)(\u2603 - f4 * \u2603), (double)(\u2603 - f5 * \u2603 - f7 * \u2603)).a(0.5, 0.375).a(this.ar, this.as, this.at, this.au).a(\u2603, \u2603).d();
            bfd2.b((double)(\u2603 - f3 * \u2603 + f6 * \u2603), (double)(\u2603 + f4 * \u2603), (double)(\u2603 - f5 * \u2603 + f7 * \u2603)).a(0.5, 0.125).a(this.ar, this.as, this.at, this.au).a(\u2603, \u2603).d();
            bfd2.b((double)(\u2603 + f3 * \u2603 + f6 * \u2603), (double)(\u2603 + f4 * \u2603), (double)(\u2603 + f5 * \u2603 + f7 * \u2603)).a(0.25, 0.125).a(this.ar, this.as, this.at, this.au).a(\u2603, \u2603).d();
            bfd2.b((double)(\u2603 + f3 * \u2603 - f6 * \u2603), (double)(\u2603 - f4 * \u2603), (double)(\u2603 + f5 * \u2603 - f7 * \u2603)).a(0.25, 0.375).a(this.ar, this.as, this.at, this.au).a(\u2603, \u2603).d();
        }
    }

    public static class b
    extends beb {
        private int a = 160;
        private boolean az;
        private boolean aA;
        private final bec aB;
        private float aC;
        private float aD;
        private float aE;
        private boolean aF;

        public b(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, bec bec2) {
            super(adm2, d2, d3, d4);
            this.v = d5;
            this.w = d6;
            this.x = d7;
            this.aB = bec2;
            this.h *= 0.75f;
            this.g = 48 + this.V.nextInt(12);
            this.T = false;
        }

        public void a(boolean bl2) {
            this.az = bl2;
        }

        public void i(boolean bl2) {
            this.aA = bl2;
        }

        public void a(int n2) {
            float f2 = (float)((n2 & 0xFF0000) >> 16) / 255.0f;
            \u2603 = (float)((n2 & 0xFF00) >> 8) / 255.0f;
            \u2603 = (float)((n2 & 0xFF) >> 0) / 255.0f;
            \u2603 = 1.0f;
            this.b(f2 * \u2603, \u2603 * \u2603, \u2603 * \u2603);
        }

        public void b(int n2) {
            this.aC = (float)((n2 & 0xFF0000) >> 16) / 255.0f;
            this.aD = (float)((n2 & 0xFF00) >> 8) / 255.0f;
            this.aE = (float)((n2 & 0xFF) >> 0) / 255.0f;
            this.aF = true;
        }

        @Override
        public aug S() {
            return null;
        }

        @Override
        public boolean ae() {
            return false;
        }

        @Override
        public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
            if (!this.aA || this.f < this.g / 3 || (this.f + this.g) / 3 % 2 == 0) {
                super.a(bfd2, pk2, f2, f3, f4, f5, f6, f7);
            }
        }

        @Override
        public void t_() {
            this.p = this.s;
            this.q = this.t;
            this.r = this.u;
            if (this.f++ >= this.g) {
                this.J();
            }
            if (this.f > this.g / 2) {
                this.i(1.0f - ((float)this.f - (float)(this.g / 2)) / (float)this.g);
                if (this.aF) {
                    this.ar += (this.aC - this.ar) * 0.2f;
                    this.as += (this.aD - this.as) * 0.2f;
                    this.at += (this.aE - this.at) * 0.2f;
                }
            }
            this.k(this.a + (7 - this.f * 8 / this.g));
            this.w -= 0.004;
            this.d(this.v, this.w, this.x);
            this.v *= (double)0.91f;
            this.w *= (double)0.91f;
            this.x *= (double)0.91f;
            if (this.C) {
                this.v *= (double)0.7f;
                this.x *= (double)0.7f;
            }
            if (this.az && this.f < this.g / 2 && (this.f + this.g) % 2 == 0) {
                b b2 = new b(this.o, this.s, this.t, this.u, 0.0, 0.0, 0.0, this.aB);
                b2.i(0.99f);
                b2.b(this.ar, this.as, this.at);
                b2.f = b2.g / 2;
                if (this.aF) {
                    b2.aF = true;
                    b2.aC = this.aC;
                    b2.aD = this.aD;
                    b2.aE = this.aE;
                }
                b2.aA = this.aA;
                this.aB.a(b2);
            }
        }

        @Override
        public int b(float f2) {
            return 0xF000F0;
        }

        @Override
        public float c(float f2) {
            return 1.0f;
        }
    }

    public static class c
    extends beb {
        private int az;
        private final bec aA;
        private du aB;
        boolean a;

        public c(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, bec bec2, dn dn2) {
            super(adm2, d2, d3, d4, 0.0, 0.0, 0.0);
            this.v = d5;
            this.w = d6;
            this.x = d7;
            this.aA = bec2;
            this.g = 8;
            if (dn2 != null) {
                this.aB = dn2.c("Explosions", 10);
                if (this.aB.c() == 0) {
                    this.aB = null;
                } else {
                    this.g = this.aB.c() * 2 - 1;
                    for (int i2 = 0; i2 < this.aB.c(); ++i2) {
                        dn dn3 = this.aB.b(i2);
                        if (!dn3.n("Flicker")) continue;
                        this.a = true;
                        this.g += 15;
                        break;
                    }
                }
            }
        }

        @Override
        public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        }

        @Override
        public void t_() {
            if (this.az == 0 && this.aB != null) {
                int n2 = this.l();
                boolean \u26032 = false;
                if (this.aB.c() >= 3) {
                    \u26032 = true;
                } else {
                    for (\u2603 = 0; \u2603 < this.aB.c(); ++\u2603) {
                        dn dn2 = this.aB.b(\u2603);
                        if (dn2.d("Type") != 1) continue;
                        \u26032 = true;
                        break;
                    }
                }
                String \u26033 = "fireworks." + (\u26032 ? "largeBlast" : "blast") + (n2 != 0 ? "_far" : "");
                this.o.a(this.s, this.t, this.u, \u26033, 20.0f, 0.95f + this.V.nextFloat() * 0.1f, true);
            }
            if (this.az % 2 == 0 && this.aB != null && this.az / 2 < this.aB.c()) {
                n2 = this.az / 2;
                dn \u26034 = this.aB.b(n2);
                byte \u26035 = \u26034.d("Type");
                boolean \u26036 = \u26034.n("Trail");
                boolean \u26037 = \u26034.n("Flicker");
                int[] \u26038 = \u26034.l("Colors");
                int[] \u26039 = \u26034.l("FadeColors");
                if (\u26038.length == 0) {
                    \u26038 = new int[]{ze.a[0]};
                }
                if (\u26035 == 1) {
                    this.a(0.5, 4, \u26038, \u26039, \u26036, \u26037);
                } else if (\u26035 == 2) {
                    this.a(0.5, new double[][]{{0.0, 1.0}, {0.3455, 0.309}, {0.9511, 0.309}, {0.3795918367346939, -0.12653061224489795}, {0.6122448979591837, -0.8040816326530612}, {0.0, -0.35918367346938773}}, \u26038, \u26039, \u26036, \u26037, false);
                } else if (\u26035 == 3) {
                    this.a(0.5, new double[][]{{0.0, 0.2}, {0.2, 0.2}, {0.2, 0.6}, {0.6, 0.6}, {0.6, 0.2}, {0.2, 0.2}, {0.2, 0.0}, {0.4, 0.0}, {0.4, -0.6}, {0.2, -0.6}, {0.2, -0.4}, {0.0, -0.4}}, \u26038, \u26039, \u26036, \u26037, true);
                } else if (\u26035 == 4) {
                    this.a(\u26038, \u26039, \u26036, \u26037);
                } else {
                    this.a(0.25, 2, \u26038, \u26039, \u26036, \u26037);
                }
                \u2603 = \u26038[0];
                float \u260310 = (float)((\u2603 & 0xFF0000) >> 16) / 255.0f;
                float \u260311 = (float)((\u2603 & 0xFF00) >> 8) / 255.0f;
                float \u260312 = (float)((\u2603 & 0xFF) >> 0) / 255.0f;
                a \u260313 = new a(this.o, this.s, this.t, this.u);
                \u260313.b(\u260310, \u260311, \u260312);
                this.aA.a(\u260313);
            }
            ++this.az;
            if (this.az > this.g) {
                if (this.a) {
                    n2 = this.l() ? 1 : 0;
                    String string = "fireworks." + (n2 != 0 ? "twinkle_far" : "twinkle");
                    this.o.a(this.s, this.t, this.u, string, 20.0f, 0.9f + this.V.nextFloat() * 0.15f, true);
                }
                this.J();
            }
        }

        private boolean l() {
            ave ave2 = ave.A();
            return ave2 == null || ave2.ac() == null || !(ave2.ac().e(this.s, this.t, this.u) < 256.0);
        }

        private void a(double d2, double d3, double d4, double d5, double d6, double d7, int[] nArray, int[] nArray2, boolean bl2, boolean bl3) {
            b b2 = new b(this.o, d2, d3, d4, d5, d6, d7, this.aA);
            b2.i(0.99f);
            b2.a(bl2);
            b2.i(bl3);
            int \u26032 = this.V.nextInt(nArray.length);
            b2.a(nArray[\u26032]);
            if (nArray2 != null && nArray2.length > 0) {
                b2.b(nArray2[this.V.nextInt(nArray2.length)]);
            }
            this.aA.a(b2);
        }

        private void a(double d2, int n2, int[] nArray, int[] nArray2, boolean bl2, boolean bl3) {
            double d3 = this.s;
            \u2603 = this.t;
            \u2603 = this.u;
            for (int i2 = -n2; i2 <= n2; ++i2) {
                for (\u2603 = -n2; \u2603 <= n2; ++\u2603) {
                    for (\u2603 = -n2; \u2603 <= n2; ++\u2603) {
                        double d4 = (double)\u2603 + (this.V.nextDouble() - this.V.nextDouble()) * 0.5;
                        \u2603 = (double)i2 + (this.V.nextDouble() - this.V.nextDouble()) * 0.5;
                        \u2603 = (double)\u2603 + (this.V.nextDouble() - this.V.nextDouble()) * 0.5;
                        \u2603 = (double)ns.a(d4 * d4 + \u2603 * \u2603 + \u2603 * \u2603) / d2 + this.V.nextGaussian() * 0.05;
                        this.a(d3, \u2603, \u2603, d4 / \u2603, \u2603 / \u2603, \u2603 / \u2603, nArray, nArray2, bl2, bl3);
                        if (i2 == -n2 || i2 == n2 || \u2603 == -n2 || \u2603 == n2) continue;
                        \u2603 += n2 * 2 - 1;
                    }
                }
            }
        }

        private void a(double d2, double[][] dArray, int[] nArray, int[] nArray2, boolean bl2, boolean bl3, boolean bl4) {
            double d3 = dArray[0][0];
            \u2603 = dArray[0][1];
            this.a(this.s, this.t, this.u, d3 * d2, \u2603 * d2, 0.0, nArray, nArray2, bl2, bl3);
            float \u26032 = this.V.nextFloat() * (float)Math.PI;
            \u2603 = bl4 ? 0.034 : 0.34;
            for (int i2 = 0; i2 < 3; ++i2) {
                double d4 = (double)\u26032 + (double)((float)i2 * (float)Math.PI) * \u2603;
                \u2603 = d3;
                \u2603 = \u2603;
                for (int i3 = 1; i3 < dArray.length; ++i3) {
                    double d5 = dArray[i3][0];
                    \u2603 = dArray[i3][1];
                    for (\u2603 = 0.25; \u2603 <= 1.0; \u2603 += 0.25) {
                        \u2603 = (\u2603 + (d5 - \u2603) * \u2603) * d2;
                        \u2603 = (\u2603 + (\u2603 - \u2603) * \u2603) * d2;
                        \u2603 = \u2603 * Math.sin(d4);
                        \u2603 *= Math.cos(d4);
                        for (\u2603 = -1.0; \u2603 <= 1.0; \u2603 += 2.0) {
                            this.a(this.s, this.t, this.u, \u2603 * \u2603, \u2603, \u2603 * \u2603, nArray, nArray2, bl2, bl3);
                        }
                    }
                    \u2603 = d5;
                    \u2603 = \u2603;
                }
            }
        }

        private void a(int[] nArray, int[] nArray2, boolean bl2, boolean bl3) {
            double d2 = this.V.nextGaussian() * 0.05;
            \u2603 = this.V.nextGaussian() * 0.05;
            for (int i2 = 0; i2 < 70; ++i2) {
                double d3 = this.v * 0.5 + this.V.nextGaussian() * 0.15 + d2;
                \u2603 = this.x * 0.5 + this.V.nextGaussian() * 0.15 + \u2603;
                \u2603 = this.w * 0.5 + this.V.nextDouble() * 0.5;
                this.a(this.s, this.t, this.u, d3, \u2603, \u2603, nArray, nArray2, bl2, bl3);
            }
        }

        @Override
        public int a() {
            return 0;
        }
    }
}

