/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import java.util.Map;
import org.apache.logging.log4j.LogManager;

public enum el {
    a(-1){
        {
            this.a(fg.a, jc.class);
        }
    }
    ,
    b(0){
        {
            this.a(fg.b, gn.class);
            this.a(fg.b, gt.class);
            this.a(fg.b, fy.class);
            this.a(fg.b, hu.class);
            this.a(fg.b, hn.class);
            this.a(fg.b, ht.class);
            this.a(fg.b, hp.class);
            this.a(fg.b, he.class);
            this.a(fg.b, fi.class);
            this.a(fg.b, hi.class);
            this.a(fg.b, ha.class);
            this.a(fg.b, fq.class);
            this.a(fg.b, fp.class);
            this.a(fg.b, hy.class);
            this.a(fg.b, fk.class);
            this.a(fg.b, fn.class);
            this.a(fg.b, fo.class);
            this.a(fg.b, fl.class);
            this.a(fg.b, hm.class);
            this.a(fg.b, hb.class);
            this.a(fg.b, gv.class);
            this.a(fg.b, gv.a.class);
            this.a(fg.b, gv.c.class);
            this.a(fg.b, gv.b.class);
            this.a(fg.b, hz.class);
            this.a(fg.b, hf.class);
            this.a(fg.b, gi.class);
            this.a(fg.b, hl.class);
            this.a(fg.b, hk.class);
            this.a(fg.b, ib.class);
            this.a(fg.b, hc.class);
            this.a(fg.b, ho.class);
            this.a(fg.b, ia.class);
            this.a(fg.b, go.class);
            this.a(fg.b, fz.class);
            this.a(fg.b, fv.class);
            this.a(fg.b, fu.class);
            this.a(fg.b, fs.class);
            this.a(fg.b, gp.class);
            this.a(fg.b, gk.class);
            this.a(fg.b, gq.class);
            this.a(fg.b, gs.class);
            this.a(fg.b, gr.class);
            this.a(fg.b, gm.class);
            this.a(fg.b, fm.class);
            this.a(fg.b, gc.class);
            this.a(fg.b, gb.class);
            this.a(fg.b, gf.class);
            this.a(fg.b, gd.class);
            this.a(fg.b, ge.class);
            this.a(fg.b, ga.class);
            this.a(fg.b, hw.class);
            this.a(fg.b, gu.class);
            this.a(fg.b, ft.class);
            this.a(fg.b, gw.class);
            this.a(fg.b, fr.class);
            this.a(fg.b, gz.class);
            this.a(fg.b, gx.class);
            this.a(fg.b, fx.class);
            this.a(fg.b, hq.class);
            this.a(fg.b, hs.class);
            this.a(fg.b, hj.class);
            this.a(fg.b, hr.class);
            this.a(fg.b, gg.class);
            this.a(fg.b, gh.class);
            this.a(fg.b, fw.class);
            this.a(fg.b, gy.class);
            this.a(fg.b, hh.class);
            this.a(fg.b, hg.class);
            this.a(fg.b, hv.class);
            this.a(fg.b, gl.class);
            this.a(fg.b, hx.class);
            this.a(fg.b, hd.class);
            this.a(fg.b, gj.class);
            this.a(fg.a, io.class);
            this.a(fg.a, ie.class);
            this.a(fg.a, in.class);
            this.a(fg.a, ip.class);
            this.a(fg.a, ip.a.class);
            this.a(fg.a, ip.c.class);
            this.a(fg.a, ip.b.class);
            this.a(fg.a, ir.class);
            this.a(fg.a, ja.class);
            this.a(fg.a, iv.class);
            this.a(fg.a, iy.class);
            this.a(fg.a, is.class);
            this.a(fg.a, it.class);
            this.a(fg.a, il.class);
            this.a(fg.a, ik.class);
            this.a(fg.a, ii.class);
            this.a(fg.a, iw.class);
            this.a(fg.a, ij.class);
            this.a(fg.a, ix.class);
            this.a(fg.a, iq.class);
            this.a(fg.a, id.class);
            this.a(fg.a, ih.class);
            this.a(fg.a, ig.class);
            this.a(fg.a, im.class);
            this.a(fg.a, iz.class);
            this.a(fg.a, iu.class);
        }
    }
    ,
    c(1){
        {
            this.a(fg.a, jv.class);
            this.a(fg.b, jr.class);
            this.a(fg.a, ju.class);
            this.a(fg.b, jq.class);
        }
    }
    ,
    d(2){
        {
            this.a(fg.b, jj.class);
            this.a(fg.b, jh.class);
            this.a(fg.b, jg.class);
            this.a(fg.b, ji.class);
            this.a(fg.a, jl.class);
            this.a(fg.a, jm.class);
        }
    };

    private static int e;
    private static int f;
    private static final el[] g;
    private static final Map<Class<? extends ff>, el> h;
    private final int i;
    private final Map<fg, BiMap<Integer, Class<? extends ff>>> j = Maps.newEnumMap(fg.class);

    private el(int n3) {
        this.i = n3;
    }

    protected el a(fg fg2, Class<? extends ff> clazz) {
        BiMap<Integer, Class<? extends ff>> biMap = this.j.get((Object)fg2);
        if (biMap == null) {
            biMap = HashBiMap.create();
            this.j.put(fg2, biMap);
        }
        if (biMap.containsValue(clazz)) {
            String string = (Object)((Object)fg2) + " packet " + clazz + " is already known to ID " + biMap.inverse().get(clazz);
            LogManager.getLogger().fatal(string);
            throw new IllegalArgumentException(string);
        }
        biMap.put(biMap.size(), clazz);
        return this;
    }

    public Integer a(fg fg2, ff ff2) {
        return (Integer)this.j.get((Object)fg2).inverse().get(ff2.getClass());
    }

    public ff a(fg fg2, int n2) throws IllegalAccessException, InstantiationException {
        Class clazz = (Class)this.j.get((Object)fg2).get(n2);
        if (clazz == null) {
            return null;
        }
        return (ff)clazz.newInstance();
    }

    public int a() {
        return this.i;
    }

    public static el a(int n2) {
        if (n2 < e || n2 > f) {
            return null;
        }
        return g[n2 - e];
    }

    public static el a(ff ff2) {
        return h.get(ff2.getClass());
    }

    static {
        e = -1;
        f = 2;
        g = new el[f - e + 1];
        h = Maps.newHashMap();
        for (el el2 : el.values()) {
            int n2 = el2.a();
            if (n2 < e || n2 > f) {
                throw new Error("Invalid protocol ID " + Integer.toString(n2));
            }
            el.g[n2 - el.e] = el2;
            for (fg fg2 : el2.j.keySet()) {
                for (Class<? extends ff> clazz : el2.j.get((Object)fg2).values()) {
                    if (h.containsKey(clazz) && h.get(clazz) != el2) {
                        throw new Error("Packet " + clazz + " is already assigned to protocol " + (Object)((Object)h.get(clazz)) + " - can't reassign to " + (Object)((Object)el2));
                    }
                    try {
                        clazz.newInstance();
                    }
                    catch (Throwable throwable) {
                        throw new Error("Packet " + clazz + " fails instantiation checks! " + clazz);
                    }
                    h.put(clazz, el2);
                }
            }
        }
    }
}

