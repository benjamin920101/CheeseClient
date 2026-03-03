/*
 * Decompiled with CFR 0.152.
 */
public class abj {
    void a(abt abt22) {
        abt abt22;
        for (zd zd2 : zd.values()) {
            abt22.a(new zx(zy.cE, 1, zd2.b()), "###", "###", " | ", Character.valueOf('#'), new zx(afi.L, 1, zd2.a()), Character.valueOf('|'), zy.y);
        }
        abt22.a(new b());
        abt22.a(new a());
    }

    static class a
    implements abs {
        private a() {
        }

        @Override
        public boolean a(xp xp2, adm adm2) {
            boolean \u26032 = false;
            for (int i2 = 0; i2 < xp2.o_(); ++i2) {
                zx zx2 = xp2.a(i2);
                if (zx2 == null || zx2.b() != zy.cE) continue;
                if (\u26032) {
                    return false;
                }
                if (aku.c(zx2) >= 6) {
                    return false;
                }
                \u26032 = true;
            }
            if (!\u26032) {
                return false;
            }
            return this.c(xp2) != null;
        }

        @Override
        public zx a(xp xp2) {
            aku.a a2;
            zx zx2 = null;
            for (int i2 = 0; i2 < xp2.o_(); ++i2) {
                zx zx3 = xp2.a(i2);
                if (zx3 == null || zx3.b() != zy.cE) continue;
                zx2 = zx3.k();
                zx2.b = 1;
                break;
            }
            if ((a2 = this.c(xp2)) != null) {
                Object \u26033;
                int \u26032 = 0;
                for (\u2603 = 0; \u2603 < xp2.o_(); ++\u2603) {
                    \u26033 = xp2.a(\u2603);
                    if (\u26033 == null || ((zx)\u26033).b() != zy.aW) continue;
                    \u26032 = ((zx)\u26033).i();
                    break;
                }
                dn dn2 = zx2.a("BlockEntityTag", true);
                \u26033 = null;
                if (dn2.b("Patterns", 9)) {
                    \u26033 = dn2.c("Patterns", 10);
                } else {
                    \u26033 = new du();
                    dn2.a("Patterns", (eb)\u26033);
                }
                \u2603 = new dn();
                \u2603.a("Pattern", a2.b());
                \u2603.a("Color", \u26032);
                ((du)\u26033).a(\u2603);
            }
            return zx2;
        }

        @Override
        public int a() {
            return 10;
        }

        @Override
        public zx b() {
            return null;
        }

        @Override
        public zx[] b(xp xp2) {
            zx[] zxArray = new zx[xp2.o_()];
            for (int i2 = 0; i2 < zxArray.length; ++i2) {
                zx zx2 = xp2.a(i2);
                if (zx2 == null || !zx2.b().r()) continue;
                zxArray[i2] = new zx(zx2.b().q());
            }
            return zxArray;
        }

        private aku.a c(xp xp2) {
            for (aku.a a2 : aku.a.values()) {
                if (!a2.d()) continue;
                boolean \u26032 = true;
                if (a2.e()) {
                    int n2 = 0;
                    n3 = 0;
                    for (\u2603 = 0; \u2603 < xp2.o_() && \u26032; ++\u2603) {
                        zx zx2 = xp2.a(\u2603);
                        if (zx2 == null || zx2.b() == zy.cE) continue;
                        if (zx2.b() == zy.aW) {
                            if (n3 != 0) {
                                \u26032 = false;
                                break;
                            }
                            int n3 = 1;
                            continue;
                        }
                        if (n2 == 0 && zx2.a(a2.f())) {
                            n2 = 1;
                            continue;
                        }
                        \u26032 = false;
                        break;
                    }
                    if (n2 == 0) {
                        \u26032 = false;
                    }
                } else if (xp2.o_() == a2.c().length * a2.c()[0].length()) {
                    n2 = -1;
                    for (n3 = 0; n3 < xp2.o_() && \u26032; ++n3) {
                        \u2603 = n3 / 3;
                        \u2603 = n3 % 3;
                        zx \u26033 = xp2.a(n3);
                        if (\u26033 == null || \u26033.b() == zy.cE) {
                            if (a2.c()[\u2603].charAt(\u2603) == ' ') continue;
                            \u26032 = false;
                            break;
                        }
                        if (\u26033.b() != zy.aW) {
                            \u26032 = false;
                            break;
                        }
                        if (n2 != -1 && n2 != \u26033.i()) {
                            \u26032 = false;
                            break;
                        }
                        if (a2.c()[\u2603].charAt(\u2603) == ' ') {
                            \u26032 = false;
                            break;
                        }
                        n2 = \u26033.i();
                    }
                } else {
                    \u26032 = false;
                }
                if (!\u26032) continue;
                return a2;
            }
            return null;
        }
    }

    static class b
    implements abs {
        private b() {
        }

        @Override
        public boolean a(xp xp2, adm adm2) {
            zx zx2 = null;
            \u2603 = null;
            for (int i2 = 0; i2 < xp2.o_(); ++i2) {
                zx zx3 = xp2.a(i2);
                if (zx3 == null) continue;
                if (zx3.b() != zy.cE) {
                    return false;
                }
                if (zx2 != null && \u2603 != null) {
                    return false;
                }
                int \u26032 = aku.b(zx3);
                boolean bl2 = \u2603 = aku.c(zx3) > 0;
                if (zx2 != null) {
                    if (\u2603) {
                        return false;
                    }
                    if (\u26032 != aku.b(zx2)) {
                        return false;
                    }
                    \u2603 = zx3;
                    continue;
                }
                if (\u2603 != null) {
                    if (!\u2603) {
                        return false;
                    }
                    if (\u26032 != aku.b(\u2603)) {
                        return false;
                    }
                    zx2 = zx3;
                    continue;
                }
                if (\u2603) {
                    zx2 = zx3;
                    continue;
                }
                \u2603 = zx3;
            }
            return zx2 != null && \u2603 != null;
        }

        @Override
        public zx a(xp xp2) {
            for (int i2 = 0; i2 < xp2.o_(); ++i2) {
                zx zx2 = xp2.a(i2);
                if (zx2 == null || aku.c(zx2) <= 0) continue;
                \u2603 = zx2.k();
                \u2603.b = 1;
                return \u2603;
            }
            return null;
        }

        @Override
        public int a() {
            return 2;
        }

        @Override
        public zx b() {
            return null;
        }

        @Override
        public zx[] b(xp xp2) {
            zx[] zxArray = new zx[xp2.o_()];
            for (int i2 = 0; i2 < zxArray.length; ++i2) {
                zx zx2 = xp2.a(i2);
                if (zx2 == null) continue;
                if (zx2.b().r()) {
                    zxArray[i2] = new zx(zx2.b().q());
                    continue;
                }
                if (!zx2.n() || aku.c(zx2) <= 0) continue;
                zxArray[i2] = zx2.k();
                zxArray[i2].b = 1;
            }
            return zxArray;
        }
    }
}

