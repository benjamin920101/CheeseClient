/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;

public class abo {
    private static final abo a = new abo();
    private Map<zx, zx> b = Maps.newHashMap();
    private Map<zx, Float> c = Maps.newHashMap();

    public static abo a() {
        return a;
    }

    private abo() {
        this.a(afi.p, new zx(zy.j), 0.7f);
        this.a(afi.o, new zx(zy.k), 1.0f);
        this.a(afi.ag, new zx(zy.i), 1.0f);
        this.a(afi.m, new zx(afi.w), 0.1f);
        this.a(zy.al, new zx(zy.am), 0.35f);
        this.a(zy.bi, new zx(zy.bj), 0.35f);
        this.a(zy.bk, new zx(zy.bl), 0.35f);
        this.a(zy.bo, new zx(zy.bp), 0.35f);
        this.a(zy.bm, new zx(zy.bn), 0.35f);
        this.a(afi.e, new zx(afi.b), 0.1f);
        this.a(new zx(afi.bf, 1, ajz.b), new zx(afi.bf, 1, ajz.O), 0.1f);
        this.a(zy.aI, new zx(zy.aH), 0.3f);
        this.a(afi.aL, new zx(afi.cz), 0.35f);
        this.a(afi.aK, new zx(zy.aW, 1, zd.n.b()), 0.2f);
        this.a(afi.r, new zx(zy.h, 1, 1), 0.15f);
        this.a(afi.s, new zx(zy.h, 1, 1), 0.15f);
        this.a(afi.bP, new zx(zy.bO), 1.0f);
        this.a(zy.bS, new zx(zy.bT), 0.35f);
        this.a(afi.aV, new zx(zy.cf), 0.1f);
        this.a(new zx(afi.v, 1, 1), new zx(afi.v, 1, 0), 0.15f);
        for (zp.a a2 : zp.a.values()) {
            if (!a2.g()) continue;
            this.a(new zx(zy.aU, 1, a2.a()), new zx(zy.aV, 1, a2.a()), 0.35f);
        }
        this.a(afi.q, new zx(zy.h), 0.1f);
        this.a(afi.aC, new zx(zy.aC), 0.7f);
        this.a(afi.x, new zx(zy.aW, 1, zd.l.b()), 0.2f);
        this.a(afi.co, new zx(zy.cg), 0.2f);
    }

    public void a(afh afh2, zx zx2, float f2) {
        this.a(zw.a(afh2), zx2, f2);
    }

    public void a(zw zw2, zx zx2, float f2) {
        this.a(new zx(zw2, 1, Short.MAX_VALUE), zx2, f2);
    }

    public void a(zx zx2, zx zx3, float f2) {
        this.b.put(zx2, zx3);
        this.c.put(zx3, Float.valueOf(f2));
    }

    public zx a(zx zx2) {
        for (Map.Entry<zx, zx> entry : this.b.entrySet()) {
            if (!this.a(zx2, entry.getKey())) continue;
            return entry.getValue();
        }
        return null;
    }

    private boolean a(zx zx2, zx zx3) {
        return zx3.b() == zx2.b() && (zx3.i() == Short.MAX_VALUE || zx3.i() == zx2.i());
    }

    public Map<zx, zx> b() {
        return this.b;
    }

    public float b(zx zx2) {
        for (Map.Entry<zx, Float> entry : this.c.entrySet()) {
            if (!this.a(zx2, entry.getKey())) continue;
            return entry.getValue().floatValue();
        }
        return 0.0f;
    }
}

