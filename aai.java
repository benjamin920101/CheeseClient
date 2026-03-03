/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class aai
extends zw {
    private Map<Integer, List<pf>> a = Maps.newHashMap();
    private static final Map<List<pf>, Integer> b = Maps.newLinkedHashMap();

    public aai() {
        this.c(1);
        this.a(true);
        this.d(0);
        this.a(yz.k);
    }

    public List<pf> h(zx zx2) {
        if (!zx2.n() || !zx2.o().b("CustomPotionEffects", 9)) {
            List<pf> list = this.a.get(zx2.i());
            if (list == null) {
                list = abe.b(zx2.i(), false);
                this.a.put(zx2.i(), list);
            }
            return list;
        }
        ArrayList<pf> arrayList = Lists.newArrayList();
        du \u26032 = zx2.o().c("CustomPotionEffects", 10);
        for (int i2 = 0; i2 < \u26032.c(); ++i2) {
            dn dn2 = \u26032.b(i2);
            pf \u26033 = pf.b(dn2);
            if (\u26033 == null) continue;
            arrayList.add(\u26033);
        }
        return arrayList;
    }

    public List<pf> e(int n2) {
        List<pf> list = this.a.get(n2);
        if (list == null) {
            list = abe.b(n2, false);
            this.a.put(n2, list);
        }
        return list;
    }

    @Override
    public zx b(zx zx2, adm adm2, wn wn22) {
        wn wn22;
        if (!wn22.bA.d) {
            --zx2.b;
        }
        if (!adm2.D && (\u2603 = this.h(zx2)) != null) {
            for (pf pf2 : \u2603) {
                wn22.c(new pf(pf2));
            }
        }
        wn22.b(na.ad[zw.b(this)]);
        if (!wn22.bA.d) {
            if (zx2.b <= 0) {
                return new zx(zy.bA);
            }
            wn22.bi.a(new zx(zy.bA));
        }
        return zx2;
    }

    @Override
    public int d(zx zx2) {
        return 32;
    }

    @Override
    public aba e(zx zx2) {
        return aba.c;
    }

    @Override
    public zx a(zx zx2, adm adm2, wn wn2) {
        if (aai.f(zx2.i())) {
            if (!wn2.bA.d) {
                --zx2.b;
            }
            adm2.a((pk)wn2, "random.bow", 0.5f, 0.4f / (g.nextFloat() * 0.4f + 0.8f));
            if (!adm2.D) {
                adm2.d(new xc(adm2, (pr)wn2, zx2));
            }
            wn2.b(na.ad[zw.b(this)]);
            return zx2;
        }
        wn2.a(zx2, this.d(zx2));
        return zx2;
    }

    public static boolean f(int n2) {
        return (n2 & 0x4000) != 0;
    }

    public int g(int n2) {
        return abe.a(n2, false);
    }

    @Override
    public int a(zx zx2, int n2) {
        if (n2 > 0) {
            return 0xFFFFFF;
        }
        return this.g(zx2.i());
    }

    public boolean h(int n2) {
        List<pf> list = this.e(n2);
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (pf pf2 : list) {
            if (!pe.a[pf2.a()].b()) continue;
            return true;
        }
        return false;
    }

    @Override
    public String a(zx zx2) {
        if (zx2.i() == 0) {
            return di.a("item.emptyPotion.name").trim();
        }
        String string = "";
        if (aai.f(zx2.i())) {
            string = di.a("potion.prefix.grenade").trim() + " ";
        }
        if ((\u2603 = zy.bz.h(zx2)) != null && !\u2603.isEmpty()) {
            \u2603 = \u2603.get(0).g();
            \u2603 = \u2603 + ".postfix";
            return string + di.a(\u2603).trim();
        }
        \u2603 = abe.c(zx2.i());
        return di.a(\u2603).trim() + " " + super.a(zx2);
    }

    @Override
    public void a(zx zx2, wn wn2, List<String> list3, boolean bl2) {
        Object object;
        if (zx2.i() == 0) {
            return;
        }
        List<pf> list2 = zy.bz.h(zx2);
        HashMultimap<String, qd> \u26032 = HashMultimap.create();
        if (list2 != null && !list2.isEmpty()) {
            for (pf pf2 : list2) {
                object = di.a(pf2.g()).trim();
                pe pe2 = pe.a[pf2.a()];
                Map<qb, qd> \u26033 = pe2.l();
                if (\u26033 != null && \u26033.size() > 0) {
                    for (Map.Entry<qb, qd> entry : \u26033.entrySet()) {
                        qd qd2 = entry.getValue();
                        qd qd3 = new qd(qd2.b(), pe2.a(pf2.c(), qd2), qd2.c());
                        \u26032.put(entry.getKey().a(), qd3);
                    }
                }
                if (pf2.c() > 0) {
                    object = (String)object + " " + di.a("potion.potency." + pf2.c()).trim();
                }
                if (pf2.b() > 20) {
                    object = (String)object + " (" + pe.a(pf2) + ")";
                }
                if (pe2.g()) {
                    list3.add((Object)((Object)a.m) + (String)object);
                    continue;
                }
                list3.add((Object)((Object)a.h) + (String)object);
            }
        } else {
            String iterator = di.a("potion.empty").trim();
            list3.add((Object)((Object)a.h) + iterator);
        }
        if (!\u26032.isEmpty()) {
            list3.add("");
            list3.add((Object)((Object)a.f) + di.a("potion.effects.whenDrank"));
            for (Map.Entry entry : \u26032.entries()) {
                object = (qd)entry.getValue();
                double d2 = ((qd)object).d();
                double \u26034 = ((qd)object).c() == 1 || ((qd)object).c() == 2 ? ((qd)object).d() * 100.0 : ((qd)object).d();
                if (d2 > 0.0) {
                    list3.add((Object)((Object)a.j) + di.a("attribute.modifier.plus." + ((qd)object).c(), zx.a.format(\u26034), di.a("attribute.name." + (String)entry.getKey())));
                    continue;
                }
                if (!(d2 < 0.0)) continue;
                list3.add((Object)((Object)a.m) + di.a("attribute.modifier.take." + ((qd)object).c(), zx.a.format(\u26034 *= -1.0), di.a("attribute.name." + (String)entry.getKey())));
            }
        }
    }

    @Override
    public boolean f(zx zx2) {
        List<pf> list = this.h(zx2);
        return list != null && !list.isEmpty();
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        super.a(zw2, yz2, list);
        if (b.isEmpty()) {
            for (int i2 = 0; i2 <= 15; ++i2) {
                for (n2 = 0; n2 <= 1; ++n2) {
                    \u2603 = i2;
                    \u2603 = n2 == 0 ? (\u2603 |= 0x2000) : (\u2603 |= 0x4000);
                    for (\u2603 = 0; \u2603 <= 2; ++\u2603) {
                        \u2603 = \u2603;
                        if (\u2603 != 0) {
                            if (\u2603 == 1) {
                                \u2603 |= 0x20;
                            } else if (\u2603 == 2) {
                                \u2603 |= 0x40;
                            }
                        }
                        if ((\u2603 = abe.b(\u2603, false)) == null || \u2603.isEmpty()) continue;
                        b.put(\u2603, \u2603);
                    }
                }
            }
        }
        for (int n2 : b.values()) {
            list.add(new zx(zw2, 1, n2));
        }
    }
}

