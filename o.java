/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.server.MinecraftServer;

public class o {
    private static final Pattern a = Pattern.compile("^@([pare])(?:\\[([\\w=,!-]*)\\])?$");
    private static final Pattern b = Pattern.compile("\\G([-!]?[\\w-]*)(?:$|,)");
    private static final Pattern c = Pattern.compile("\\G(\\w+)=([-!]?[\\w-]*)(?:$|,)");
    private static final Set<String> d = Sets.newHashSet("x", "y", "z", "dx", "dy", "dz", "rm", "r");

    public static lf a(m m2, String string) {
        return o.a(m2, string, lf.class);
    }

    public static <T extends pk> T a(m m2, String string, Class<? extends T> clazz) {
        List<T> list = o.b(m2, string, clazz);
        return (T)(list.size() == 1 ? (pk)list.get(0) : null);
    }

    public static eu b(m m2, String string) {
        List<pk> list = o.b(m2, string, pk.class);
        if (list.isEmpty()) {
            return null;
        }
        ArrayList<eu> \u26032 = Lists.newArrayList();
        for (pk pk2 : list) {
            \u26032.add(pk2.f_());
        }
        return i.a(\u26032);
    }

    public static <T extends pk> List<T> b(m m2, String string, Class<? extends T> clazz) {
        Matcher matcher = a.matcher(string);
        if (matcher.matches() && m2.a(1, "@")) {
            Map<String, String> map = o.c(matcher.group(2));
            if (!o.b(m2, map)) {
                return Collections.emptyList();
            }
            String \u26032 = matcher.group(1);
            cj \u26033 = o.b(map, m2.c());
            List<adm> \u26034 = o.a(m2, map);
            ArrayList<? extends T> \u26035 = Lists.newArrayList();
            for (adm adm2 : \u26034) {
                if (adm2 == null) continue;
                ArrayList<Predicate<pk>> arrayList = Lists.newArrayList();
                arrayList.addAll(o.a(map, \u26032));
                arrayList.addAll(o.b(map));
                arrayList.addAll(o.c(map));
                arrayList.addAll(o.d(map));
                arrayList.addAll(o.e(map));
                arrayList.addAll(o.f(map));
                arrayList.addAll(o.a(map, \u26033));
                arrayList.addAll(o.g(map));
                \u26035.addAll(o.a(map, clazz, arrayList, \u26032, adm2, \u26033));
            }
            return o.a(\u26035, map, m2, clazz, \u26032, \u26033);
        }
        return Collections.emptyList();
    }

    private static List<adm> a(m m2, Map<String, String> map) {
        ArrayList<adm> arrayList = Lists.newArrayList();
        if (o.h(map)) {
            arrayList.add(m2.e());
        } else {
            Collections.addAll(arrayList, MinecraftServer.N().d);
        }
        return arrayList;
    }

    private static <T extends pk> boolean b(m m2, Map<String, String> map) {
        String string = o.b(map, "type");
        String string2 = string = string != null && string.startsWith("!") ? string.substring(1) : string;
        if (string != null && !pm.b(string)) {
            fb fb2 = new fb("commands.generic.entity.invalidType", string);
            fb2.b().a(a.m);
            m2.a(fb2);
            return false;
        }
        return true;
    }

    private static List<Predicate<pk>> a(Map<String, String> map, String string) {
        ArrayList<Predicate<pk>> arrayList = Lists.newArrayList();
        String \u26032 = o.b(map, "type");
        boolean bl2 = \u2603 = \u26032 != null && \u26032.startsWith("!");
        if (\u2603) {
            \u26032 = \u26032.substring(1);
        }
        final String \u26033 = \u26032;
        boolean \u26034 = !string.equals("e");
        boolean bl3 = \u2603 = string.equals("r") && \u26032 != null;
        if (\u26032 != null && string.equals("e") || \u2603) {
            arrayList.add(new Predicate<pk>(){

                public boolean a(pk pk2) {
                    return pm.a(pk2, \u26033) != \u2603;
                }

                @Override
                public /* synthetic */ boolean apply(Object object) {
                    return this.a((pk)object);
                }
            });
        } else if (\u26034) {
            arrayList.add(new Predicate<pk>(){

                public boolean a(pk pk2) {
                    return pk2 instanceof wn;
                }

                @Override
                public /* synthetic */ boolean apply(Object object) {
                    return this.a((pk)object);
                }
            });
        }
        return arrayList;
    }

    private static List<Predicate<pk>> b(Map<String, String> map) {
        ArrayList<Predicate<pk>> arrayList = Lists.newArrayList();
        final int \u26032 = o.a(map, "lm", -1);
        final int \u26033 = o.a(map, "l", -1);
        if (\u26032 > -1 || \u26033 > -1) {
            arrayList.add(new Predicate<pk>(){

                public boolean a(pk pk2) {
                    if (!(pk2 instanceof lf)) {
                        return false;
                    }
                    lf lf2 = (lf)pk2;
                    return !(\u26032 > -1 && lf2.bB < \u26032 || \u26033 > -1 && lf2.bB > \u26033);
                }

                @Override
                public /* synthetic */ boolean apply(Object object) {
                    return this.a((pk)object);
                }
            });
        }
        return arrayList;
    }

    private static List<Predicate<pk>> c(Map<String, String> map) {
        ArrayList<Predicate<pk>> arrayList = Lists.newArrayList();
        final int \u26032 = o.a(map, "m", adp.a.a.a());
        if (\u26032 != adp.a.a.a()) {
            arrayList.add(new Predicate<pk>(){

                public boolean a(pk pk2) {
                    if (!(pk2 instanceof lf)) {
                        return false;
                    }
                    lf lf2 = (lf)pk2;
                    return lf2.c.b().a() == \u26032;
                }

                @Override
                public /* synthetic */ boolean apply(Object object) {
                    return this.a((pk)object);
                }
            });
        }
        return arrayList;
    }

    private static List<Predicate<pk>> d(Map<String, String> map) {
        ArrayList<Predicate<pk>> arrayList = Lists.newArrayList();
        String \u26032 = o.b(map, "team");
        boolean bl2 = \u2603 = \u26032 != null && \u26032.startsWith("!");
        if (\u2603) {
            \u26032 = \u26032.substring(1);
        }
        final String \u26033 = \u26032;
        if (\u26032 != null) {
            arrayList.add(new Predicate<pk>(){

                public boolean a(pk pk2) {
                    if (!(pk2 instanceof pr)) {
                        return false;
                    }
                    pr pr2 = (pr)pk2;
                    auq \u26032 = pr2.bO();
                    String \u260332 = \u26032 == null ? "" : \u26032.b();
                    return \u260332.equals(\u26033) != \u2603;
                }

                @Override
                public /* synthetic */ boolean apply(Object object) {
                    return this.a((pk)object);
                }
            });
        }
        return arrayList;
    }

    private static List<Predicate<pk>> e(Map<String, String> map) {
        ArrayList<Predicate<pk>> arrayList = Lists.newArrayList();
        final Map<String, Integer> \u26032 = o.a(map);
        if (\u26032 != null && \u26032.size() > 0) {
            arrayList.add(new Predicate<pk>(){

                public boolean a(pk pk2) {
                    auo auo2 = MinecraftServer.N().a(0).Z();
                    for (Map.Entry entry : \u26032.entrySet()) {
                        String string = (String)entry.getKey();
                        boolean \u260322 = false;
                        if (string.endsWith("_min") && string.length() > 4) {
                            \u260322 = true;
                            string = string.substring(0, string.length() - 4);
                        }
                        if ((\u2603 = auo2.b(string)) == null) {
                            return false;
                        }
                        String string2 = \u2603 = pk2 instanceof lf ? pk2.e_() : pk2.aK().toString();
                        if (!auo2.b(\u2603, \u2603)) {
                            return false;
                        }
                        aum \u26033 = auo2.c(\u2603, \u2603);
                        int \u26034 = \u26033.c();
                        if (\u26034 < (Integer)entry.getValue() && \u260322) {
                            return false;
                        }
                        if (\u26034 <= (Integer)entry.getValue() || \u260322) continue;
                        return false;
                    }
                    return true;
                }

                @Override
                public /* synthetic */ boolean apply(Object object) {
                    return this.a((pk)object);
                }
            });
        }
        return arrayList;
    }

    private static List<Predicate<pk>> f(Map<String, String> map) {
        ArrayList<Predicate<pk>> arrayList = Lists.newArrayList();
        String \u26032 = o.b(map, "name");
        boolean bl2 = \u2603 = \u26032 != null && \u26032.startsWith("!");
        if (\u2603) {
            \u26032 = \u26032.substring(1);
        }
        final String \u26033 = \u26032;
        if (\u26032 != null) {
            arrayList.add(new Predicate<pk>(){

                public boolean a(pk pk2) {
                    return pk2.e_().equals(\u26033) != \u2603;
                }

                @Override
                public /* synthetic */ boolean apply(Object object) {
                    return this.a((pk)object);
                }
            });
        }
        return arrayList;
    }

    private static List<Predicate<pk>> a(Map<String, String> map, final cj cj2) {
        ArrayList<Predicate<pk>> arrayList = Lists.newArrayList();
        final int \u26032 = o.a(map, "rm", -1);
        final int \u26033 = o.a(map, "r", -1);
        if (cj2 != null && (\u26032 >= 0 || \u26033 >= 0)) {
            final int n2 = \u26032 * \u26032;
            \u2603 = \u26033 * \u26033;
            arrayList.add(new Predicate<pk>(){

                public boolean a(pk pk2) {
                    int n22 = (int)pk2.c(cj2);
                    return !(\u26032 >= 0 && n22 < n2 || \u26033 >= 0 && n22 > \u2603);
                }

                @Override
                public /* synthetic */ boolean apply(Object object) {
                    return this.a((pk)object);
                }
            });
        }
        return arrayList;
    }

    private static List<Predicate<pk>> g(Map<String, String> map2) {
        Map<String, String> map2;
        int n2;
        ArrayList<Predicate<pk>> arrayList = Lists.newArrayList();
        if (map2.containsKey("rym") || map2.containsKey("ry")) {
            n2 = o.a(o.a(map2, "rym", 0));
            \u2603 = o.a(o.a(map2, "ry", 359));
            arrayList.add(new Predicate<pk>(){

                public boolean a(pk pk2) {
                    int n22 = o.a((int)Math.floor(pk2.y));
                    if (n2 > \u2603) {
                        return n22 >= n2 || n22 <= \u2603;
                    }
                    return n22 >= n2 && n22 <= \u2603;
                }

                @Override
                public /* synthetic */ boolean apply(Object object) {
                    return this.a((pk)object);
                }
            });
        }
        if (map2.containsKey("rxm") || map2.containsKey("rx")) {
            n2 = o.a(o.a(map2, "rxm", 0));
            \u2603 = o.a(o.a(map2, "rx", 359));
            arrayList.add(new Predicate<pk>(){

                public boolean a(pk pk2) {
                    int n22 = o.a((int)Math.floor(pk2.z));
                    if (n2 > \u2603) {
                        return n22 >= n2 || n22 <= \u2603;
                    }
                    return n22 >= n2 && n22 <= \u2603;
                }

                @Override
                public /* synthetic */ boolean apply(Object object) {
                    return this.a((pk)object);
                }
            });
        }
        return arrayList;
    }

    private static <T extends pk> List<T> a(Map<String, String> map, Class<? extends T> clazz, List<Predicate<pk>> list, String string, adm adm2, cj cj22) {
        ArrayList<pk> arrayList = Lists.newArrayList();
        String \u26032 = o.b(map, "type");
        \u26032 = \u26032 != null && \u26032.startsWith("!") ? \u26032.substring(1) : \u26032;
        boolean \u26033 = !string.equals("e");
        boolean \u26034 = string.equals("r") && \u26032 != null;
        int \u26035 = o.a(map, "dx", 0);
        int \u26036 = o.a(map, "dy", 0);
        int \u26037 = o.a(map, "dz", 0);
        int \u26038 = o.a(map, "r", -1);
        Predicate<pk> \u26039 = Predicates.and(list);
        Predicate<pk> \u260310 = Predicates.and(po.a, \u26039);
        if (cj22 != null) {
            int n2 = adm2.j.size();
            boolean bl2 = \u2603 = n2 < (\u2603 = adm2.f.size()) / 16;
            if (map.containsKey("dx") || map.containsKey("dy") || map.containsKey("dz")) {
                final aug aug2 = o.a(cj22, \u26035, \u26036, \u26037);
                if (\u26033 && \u2603 && !\u26034) {
                    Predicate<pk> predicate = new Predicate<pk>(){

                        public boolean a(pk pk2) {
                            if (pk2.s < aug2.a || pk2.t < aug2.b || pk2.u < aug2.c) {
                                return false;
                            }
                            return !(pk2.s >= aug2.d) && !(pk2.t >= aug2.e) && !(pk2.u >= aug2.f);
                        }

                        @Override
                        public /* synthetic */ boolean apply(Object object) {
                            return this.a((pk)object);
                        }
                    };
                    arrayList.addAll(adm2.b(clazz, Predicates.and(\u260310, predicate)));
                } else {
                    arrayList.addAll((Collection<pk>)((Object)adm2.a(clazz, aug2, \u260310)));
                }
            } else if (\u26038 >= 0) {
                cj cj22;
                aug \u260311 = new aug(cj22.n() - \u26038, cj22.o() - \u26038, cj22.p() - \u26038, cj22.n() + \u26038 + 1, cj22.o() + \u26038 + 1, cj22.p() + \u26038 + 1);
                if (\u26033 && \u2603 && !\u26034) {
                    arrayList.addAll(adm2.b(clazz, \u260310));
                } else {
                    arrayList.addAll((Collection<pk>)((Object)adm2.a(clazz, \u260311, \u260310)));
                }
            } else if (string.equals("a")) {
                arrayList.addAll(adm2.b(clazz, \u26039));
            } else if (string.equals("p") || string.equals("r") && !\u26034) {
                arrayList.addAll(adm2.b(clazz, \u260310));
            } else {
                arrayList.addAll(adm2.a(clazz, \u260310));
            }
        } else if (string.equals("a")) {
            arrayList.addAll(adm2.b(clazz, \u26039));
        } else if (string.equals("p") || string.equals("r") && !\u26034) {
            arrayList.addAll(adm2.b(clazz, \u260310));
        } else {
            arrayList.addAll(adm2.a(clazz, \u260310));
        }
        return arrayList;
    }

    private static <T extends pk> List<T> a(List<T> list2, Map<String, String> map, m m2, Class<? extends T> clazz, String string, final cj cj2) {
        List<T> list2;
        int n2 = o.a(map, "c", string.equals("a") || string.equals("e") ? 0 : 1);
        if (string.equals("p") || string.equals("a") || string.equals("e")) {
            if (cj2 != null) {
                Collections.sort(list2, new Comparator<pk>(){

                    public int a(pk pk2, pk pk3) {
                        return ComparisonChain.start().compare(pk2.b(cj2), pk3.b(cj2)).result();
                    }

                    @Override
                    public /* synthetic */ int compare(Object object, Object object2) {
                        return this.a((pk)object, (pk)object2);
                    }
                });
            }
        } else if (string.equals("r")) {
            Collections.shuffle(list2);
        }
        if ((\u2603 = m2.f()) != null && clazz.isAssignableFrom(\u2603.getClass()) && n2 == 1 && list2.contains(\u2603) && !"r".equals(string)) {
            list2 = Lists.newArrayList(\u2603);
        }
        if (n2 != 0) {
            if (n2 < 0) {
                Collections.reverse(list2);
            }
            list2 = list2.subList(0, Math.min(Math.abs(n2), list2.size()));
        }
        return list2;
    }

    private static aug a(cj cj2, int n2, int n3, int n4) {
        boolean bl2 = n2 < 0;
        \u2603 = n3 < 0;
        \u2603 = n4 < 0;
        int \u26032 = cj2.n() + (bl2 ? n2 : 0);
        int \u26033 = cj2.o() + (\u2603 ? n3 : 0);
        int \u26034 = cj2.p() + (\u2603 ? n4 : 0);
        int \u26035 = cj2.n() + (bl2 ? 0 : n2) + 1;
        int \u26036 = cj2.o() + (\u2603 ? 0 : n3) + 1;
        int \u26037 = cj2.p() + (\u2603 ? 0 : n4) + 1;
        return new aug(\u26032, \u26033, \u26034, \u26035, \u26036, \u26037);
    }

    public static int a(int n2) {
        if ((n2 %= 360) >= 160) {
            n2 -= 360;
        }
        if (n2 < 0) {
            n2 += 360;
        }
        return n2;
    }

    private static cj b(Map<String, String> map, cj cj2) {
        return new cj(o.a(map, "x", cj2.n()), o.a(map, "y", cj2.o()), o.a(map, "z", cj2.p()));
    }

    private static boolean h(Map<String, String> map) {
        for (String string : d) {
            if (!map.containsKey(string)) continue;
            return true;
        }
        return false;
    }

    private static int a(Map<String, String> map, String string, int n2) {
        return map.containsKey(string) ? ns.a(map.get(string), n2) : n2;
    }

    private static String b(Map<String, String> map, String string) {
        return map.get(string);
    }

    public static Map<String, Integer> a(Map<String, String> map) {
        HashMap<String, Integer> hashMap = Maps.newHashMap();
        for (String string : map.keySet()) {
            if (!string.startsWith("score_") || string.length() <= "score_".length()) continue;
            hashMap.put(string.substring("score_".length()), ns.a(map.get(string), 1));
        }
        return hashMap;
    }

    public static boolean a(String string) {
        Matcher matcher = a.matcher(string);
        if (matcher.matches()) {
            Map<String, String> map = o.c(matcher.group(2));
            String \u26032 = matcher.group(1);
            int \u26033 = "a".equals(\u26032) || "e".equals(\u26032) ? 0 : 1;
            return o.a(map, "c", \u26033) != 1;
        }
        return false;
    }

    public static boolean b(String string) {
        return a.matcher(string).matches();
    }

    private static Map<String, String> c(String string2) {
        String string2;
        Object object;
        HashMap<String, String> hashMap = Maps.newHashMap();
        if (string2 == null) {
            return hashMap;
        }
        int \u26032 = 0;
        int \u26033 = -1;
        Matcher \u26034 = b.matcher(string2);
        while (\u26034.find()) {
            object = null;
            switch (\u26032++) {
                case 0: {
                    object = "x";
                    break;
                }
                case 1: {
                    object = "y";
                    break;
                }
                case 2: {
                    object = "z";
                    break;
                }
                case 3: {
                    object = "r";
                }
            }
            if (object != null && \u26034.group(1).length() > 0) {
                hashMap.put((String)object, \u26034.group(1));
            }
            \u26033 = \u26034.end();
        }
        if (\u26033 < string2.length()) {
            object = c.matcher(\u26033 == -1 ? string2 : string2.substring(\u26033));
            while (((Matcher)object).find()) {
                hashMap.put(((Matcher)object).group(1), ((Matcher)object).group(2));
            }
        }
        return hashMap;
    }
}

