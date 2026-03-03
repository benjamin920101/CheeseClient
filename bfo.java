/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;

public class bfo {
    private final Map<Integer, bov> a = Maps.newHashMap();
    private final Map<Integer, boq> b = Maps.newHashMap();
    private final Map<zw, bfp> c = Maps.newHashMap();
    private final bou d;

    public bfo(bou bou2) {
        this.d = bou2;
    }

    public bmi a(zw zw2) {
        return this.a(zw2, 0);
    }

    public bmi a(zw zw2, int n2) {
        return this.a(new zx(zw2, 1, n2)).e();
    }

    public boq a(zx zx2) {
        zw zw2 = zx2.b();
        boq \u26032 = this.b(zw2, this.b(zx2));
        if (\u26032 == null && (\u2603 = this.c.get(zw2)) != null) {
            \u26032 = this.d.a(\u2603.a(zx2));
        }
        if (\u26032 == null) {
            \u26032 = this.d.a();
        }
        return \u26032;
    }

    protected int b(zx zx2) {
        return zx2.e() ? 0 : zx2.i();
    }

    protected boq b(zw zw2, int n2) {
        return this.b.get(this.c(zw2, n2));
    }

    private int c(zw zw2, int n2) {
        return zw.b(zw2) << 16 | n2;
    }

    public void a(zw zw2, int n2, bov bov2) {
        this.a.put(this.c(zw2, n2), bov2);
        this.b.put(this.c(zw2, n2), this.d.a(bov2));
    }

    public void a(zw zw2, bfp bfp2) {
        this.c.put(zw2, bfp2);
    }

    public bou a() {
        return this.d;
    }

    public void b() {
        this.b.clear();
        for (Map.Entry<Integer, bov> entry : this.a.entrySet()) {
            this.b.put(entry.getKey(), this.d.a(entry.getValue()));
        }
    }
}

