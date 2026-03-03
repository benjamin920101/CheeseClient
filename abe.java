/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class abe {
    public static final String a;
    public static final String b;
    public static final String c;
    public static final String d;
    public static final String e;
    public static final String f;
    public static final String g;
    public static final String h;
    public static final String i;
    public static final String j;
    public static final String k;
    public static final String l;
    public static final String m;
    public static final String n;
    private static final Map<Integer, String> o;
    private static final Map<Integer, String> p;
    private static final Map<Integer, Integer> q;
    private static final String[] r;

    public static boolean a(int n2, int n3) {
        return (n2 & 1 << n3) != 0;
    }

    private static int c(int n2, int n3) {
        return abe.a(n2, n3) ? 1 : 0;
    }

    private static int d(int n2, int n3) {
        return abe.a(n2, n3) ? 0 : 1;
    }

    public static int a(int n2) {
        return abe.a(n2, 5, 4, 3, 2, 1);
    }

    public static int a(Collection<pf> collection) {
        int n2 = 3694022;
        if (collection == null || collection.isEmpty()) {
            return n2;
        }
        float \u26032 = 0.0f;
        float \u26033 = 0.0f;
        float \u26034 = 0.0f;
        float \u26035 = 0.0f;
        for (pf pf2 : collection) {
            if (!pf2.f()) continue;
            int n3 = pe.a[pf2.a()].k();
            for (\u2603 = 0; \u2603 <= pf2.c(); ++\u2603) {
                \u26032 += (float)(n3 >> 16 & 0xFF) / 255.0f;
                \u26033 += (float)(n3 >> 8 & 0xFF) / 255.0f;
                \u26034 += (float)(n3 >> 0 & 0xFF) / 255.0f;
                \u26035 += 1.0f;
            }
        }
        if (\u26035 == 0.0f) {
            return 0;
        }
        \u26032 = \u26032 / \u26035 * 255.0f;
        \u26033 = \u26033 / \u26035 * 255.0f;
        \u26034 = \u26034 / \u26035 * 255.0f;
        return (int)\u26032 << 16 | (int)\u26033 << 8 | (int)\u26034;
    }

    public static boolean b(Collection<pf> collection) {
        for (pf pf2 : collection) {
            if (pf2.e()) continue;
            return false;
        }
        return true;
    }

    public static int a(int n2, boolean bl2) {
        Integer n3 = nl.a(n2);
        if (!bl2) {
            if (q.containsKey(n3)) {
                return q.get(n3);
            }
            int n4 = abe.a(abe.b(n3, false));
            q.put(n3, n4);
            return n4;
        }
        return abe.a(abe.b(n3, true));
    }

    public static String c(int n2) {
        \u2603 = abe.a(n2);
        return r[\u2603];
    }

    private static int a(boolean bl2, boolean bl3, boolean bl4, int n2, int n3, int n4, int n5) {
        \u2603 = 0;
        if (bl2) {
            \u2603 = abe.d(n5, n3);
        } else if (n2 != -1) {
            if (n2 == 0 && abe.h(n5) == n3) {
                \u2603 = 1;
            } else if (n2 == 1 && abe.h(n5) > n3) {
                \u2603 = 1;
            } else if (n2 == 2 && abe.h(n5) < n3) {
                \u2603 = 1;
            }
        } else {
            \u2603 = abe.c(n5, n3);
        }
        if (bl3) {
            \u2603 *= n4;
        }
        if (bl4) {
            \u2603 *= -1;
        }
        return \u2603;
    }

    private static int h(int n2) {
        \u2603 = 0;
        while (n2 > 0) {
            n2 &= n2 - 1;
            ++\u2603;
        }
        return \u2603;
    }

    private static int a(String string, int n2, int n3, int n4) {
        boolean \u26039;
        boolean \u26038;
        boolean \u26036;
        boolean \u260310;
        if (n2 >= string.length() || n3 < 0 || n2 >= n3) {
            return 0;
        }
        \u2603 = string.indexOf(124, n2);
        if (\u2603 >= 0 && \u2603 < n3) {
            \u2603 = abe.a(string, n2, \u2603 - 1, n4);
            if (\u2603 > 0) {
                return \u2603;
            }
            \u2603 = abe.a(string, \u2603 + 1, n3, n4);
            if (\u2603 > 0) {
                return \u2603;
            }
            return 0;
        }
        \u2603 = string.indexOf(38, n2);
        if (\u2603 >= 0 && \u2603 < n3) {
            \u2603 = abe.a(string, n2, \u2603 - 1, n4);
            if (\u2603 <= 0) {
                return 0;
            }
            \u2603 = abe.a(string, \u2603 + 1, n3, n4);
            if (\u2603 <= 0) {
                return 0;
            }
            if (\u2603 > \u2603) {
                return \u2603;
            }
            return \u2603;
        }
        boolean \u26037 = false;
        \u260310 = false;
        \u26036 = false;
        \u26038 = false;
        \u26039 = false;
        int \u26032 = -1;
        int \u26033 = 0;
        int \u26034 = 0;
        int \u26035 = 0;
        for (int i2 = n2; i2 < n3; ++i2) {
            char c2 = string.charAt(i2);
            if (c2 >= '0' && c2 <= '9') {
                if (\u26037) {
                    \u26034 = c2 - 48;
                    \u260310 = true;
                    continue;
                }
                \u26033 *= 10;
                \u26033 += c2 - 48;
                \u26036 = true;
                continue;
            }
            if (c2 == '*') {
                \u26037 = true;
                continue;
            }
            if (c2 == '!') {
                if (\u26036) {
                    \u26035 += abe.a(\u26038, \u260310, \u26039, \u26032, \u26033, \u26034, n4);
                    \u26038 = false;
                    \u26039 = false;
                    \u26037 = false;
                    \u260310 = false;
                    \u26036 = false;
                    \u26034 = 0;
                    \u26033 = 0;
                    \u26032 = -1;
                }
                \u26038 = true;
                continue;
            }
            if (c2 == '-') {
                if (\u26036) {
                    \u26035 += abe.a(\u26038, \u260310, \u26039, \u26032, \u26033, \u26034, n4);
                    \u26038 = false;
                    \u26039 = false;
                    \u26037 = false;
                    \u260310 = false;
                    \u26036 = false;
                    \u26034 = 0;
                    \u26033 = 0;
                    \u26032 = -1;
                }
                \u26039 = true;
                continue;
            }
            if (c2 == '=' || c2 == '<' || c2 == '>') {
                if (\u26036) {
                    \u26035 += abe.a(\u26038, \u260310, \u26039, \u26032, \u26033, \u26034, n4);
                    \u26038 = false;
                    \u26039 = false;
                    \u26037 = false;
                    \u260310 = false;
                    \u26036 = false;
                    \u26034 = 0;
                    \u26033 = 0;
                    \u26032 = -1;
                }
                if (c2 == '=') {
                    \u26032 = 0;
                    continue;
                }
                if (c2 == '<') {
                    \u26032 = 2;
                    continue;
                }
                if (c2 != '>') continue;
                \u26032 = 1;
                continue;
            }
            if (c2 != '+' || !\u26036) continue;
            \u26035 += abe.a(\u26038, \u260310, \u26039, \u26032, \u26033, \u26034, n4);
            \u26038 = false;
            \u26039 = false;
            \u26037 = false;
            \u260310 = false;
            \u26036 = false;
            \u26034 = 0;
            \u26033 = 0;
            \u26032 = -1;
        }
        if (\u26036) {
            \u26035 += abe.a(\u26038, \u260310, \u26039, \u26032, \u26033, \u26034, n4);
        }
        return \u26035;
    }

    public static List<pf> b(int n2, boolean bl2) {
        ArrayList<pf> arrayList = null;
        for (pe pe22 : pe.a) {
            pe pe22;
            if (pe22 == null || pe22.j() && !bl2 || (\u2603 = o.get(pe22.d())) == null || (\u2603 = abe.a(\u2603, 0, \u2603.length(), n2)) <= 0) continue;
            int n3 = 0;
            String \u26032 = p.get(pe22.d());
            if (\u26032 != null && (n3 = abe.a(\u26032, 0, \u26032.length(), n2)) < 0) {
                n3 = 0;
            }
            if (pe22.b()) {
                \u2603 = 1;
            } else {
                \u2603 = 1200 * (\u2603 * 3 + (\u2603 - 1) * 2);
                \u2603 >>= n3;
                \u2603 = (int)Math.round((double)\u2603 * pe22.h());
                if ((n2 & 0x4000) != 0) {
                    \u2603 = (int)Math.round((double)\u2603 * 0.75 + 0.5);
                }
            }
            if (arrayList == null) {
                arrayList = Lists.newArrayList();
            }
            pf \u26033 = new pf(pe22.d(), \u2603, n3);
            if ((n2 & 0x4000) != 0) {
                \u26033.a(true);
            }
            arrayList.add(\u26033);
        }
        return arrayList;
    }

    private static int a(int n22, int n3, boolean bl2, boolean bl3, boolean bl4) {
        int n22;
        if (bl4) {
            if (!abe.a(n22, n3)) {
                return 0;
            }
        } else {
            n22 = bl2 ? (n22 &= ~(1 << n3)) : (bl3 ? ((n22 & 1 << n3) == 0 ? (n22 |= 1 << n3) : (n22 &= ~(1 << n3))) : (n22 |= 1 << n3));
        }
        return n22;
    }

    public static int a(int n22, String string) {
        int n22;
        int n3 = 0;
        \u2603 = string.length();
        boolean \u26032 = false;
        boolean \u26033 = false;
        boolean \u26034 = false;
        boolean \u26035 = false;
        \u2603 = 0;
        for (\u2603 = n3; \u2603 < \u2603; ++\u2603) {
            char c2 = string.charAt(\u2603);
            if (c2 >= '0' && c2 <= '9') {
                \u2603 *= 10;
                \u2603 += c2 - 48;
                \u26032 = true;
                continue;
            }
            if (c2 == '!') {
                if (\u26032) {
                    n22 = abe.a(n22, \u2603, \u26034, \u26033, \u26035);
                    \u26035 = false;
                    \u26033 = false;
                    \u26034 = false;
                    \u26032 = false;
                    \u2603 = 0;
                }
                \u26033 = true;
                continue;
            }
            if (c2 == '-') {
                if (\u26032) {
                    n22 = abe.a(n22, \u2603, \u26034, \u26033, \u26035);
                    \u26035 = false;
                    \u26033 = false;
                    \u26034 = false;
                    \u26032 = false;
                    \u2603 = 0;
                }
                \u26034 = true;
                continue;
            }
            if (c2 == '+') {
                if (!\u26032) continue;
                n22 = abe.a(n22, \u2603, \u26034, \u26033, \u26035);
                \u26035 = false;
                \u26033 = false;
                \u26034 = false;
                \u26032 = false;
                \u2603 = 0;
                continue;
            }
            if (c2 != '&') continue;
            if (\u26032) {
                n22 = abe.a(n22, \u2603, \u26034, \u26033, \u26035);
                \u26035 = false;
                \u26033 = false;
                \u26034 = false;
                \u26032 = false;
                \u2603 = 0;
            }
            \u26035 = true;
        }
        if (\u26032) {
            n22 = abe.a(n22, \u2603, \u26034, \u26033, \u26035);
        }
        return n22 & Short.MAX_VALUE;
    }

    public static int a(int n2, int n3, int n4, int n5, int n6, int n7) {
        return (abe.a(n2, n3) ? 16 : 0) | (abe.a(n2, n4) ? 8 : 0) | (abe.a(n2, n5) ? 4 : 0) | (abe.a(n2, n6) ? 2 : 0) | (abe.a(n2, n7) ? 1 : 0);
    }

    static {
        o = Maps.newHashMap();
        p = Maps.newHashMap();
        a = null;
        c = "+0-1-2-3&4-4+13";
        o.put(pe.l.d(), "0 & !1 & !2 & !3 & 0+6");
        b = "-0+1-2-3&4-4+13";
        o.put(pe.c.d(), "!0 & 1 & !2 & !3 & 1+6");
        h = "+0+1-2-3&4-4+13";
        o.put(pe.n.d(), "0 & 1 & !2 & !3 & 0+6");
        f = "+0-1+2-3&4-4+13";
        o.put(pe.h.d(), "0 & !1 & 2 & !3");
        d = "-0-1+2-3&4-4+13";
        o.put(pe.u.d(), "!0 & !1 & 2 & !3 & 2+6");
        e = "-0+3-4+13";
        o.put(pe.t.d(), "!0 & !1 & !2 & 3 & 3+6");
        o.put(pe.i.d(), "!0 & !1 & 2 & 3");
        o.put(pe.d.d(), "!0 & 1 & !2 & 3 & 3+6");
        g = "+0-1-2+3&4-4+13";
        o.put(pe.g.d(), "0 & !1 & !2 & 3 & 3+6");
        l = "-0+1+2-3+13&4-4";
        o.put(pe.r.d(), "!0 & 1 & 2 & !3 & 2+6");
        o.put(pe.p.d(), "!0 & 1 & 2 & 3 & 2+6");
        m = "+0-1+2+3+13&4-4";
        o.put(pe.o.d(), "0 & !1 & 2 & 3 & 2+6");
        n = "+0+1-2+3&4-4+13";
        o.put(pe.j.d(), "0 & 1 & !2 & 3 & 3+6");
        j = "+5-6-7";
        p.put(pe.c.d(), "5");
        p.put(pe.e.d(), "5");
        p.put(pe.g.d(), "5");
        p.put(pe.l.d(), "5");
        p.put(pe.i.d(), "5");
        p.put(pe.h.d(), "5");
        p.put(pe.m.d(), "5");
        p.put(pe.u.d(), "5");
        p.put(pe.j.d(), "5");
        i = "-5+6-7";
        k = "+14&13-13";
        q = Maps.newHashMap();
        r = new String[]{"potion.prefix.mundane", "potion.prefix.uninteresting", "potion.prefix.bland", "potion.prefix.clear", "potion.prefix.milky", "potion.prefix.diffuse", "potion.prefix.artless", "potion.prefix.thin", "potion.prefix.awkward", "potion.prefix.flat", "potion.prefix.bulky", "potion.prefix.bungling", "potion.prefix.buttered", "potion.prefix.smooth", "potion.prefix.suave", "potion.prefix.debonair", "potion.prefix.thick", "potion.prefix.elegant", "potion.prefix.fancy", "potion.prefix.charming", "potion.prefix.dashing", "potion.prefix.refined", "potion.prefix.cordial", "potion.prefix.sparkling", "potion.prefix.potent", "potion.prefix.foul", "potion.prefix.odorless", "potion.prefix.rank", "potion.prefix.harsh", "potion.prefix.acrid", "potion.prefix.gross", "potion.prefix.stinky"};
    }
}

