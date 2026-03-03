/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;

public class tv
extends tm {
    private final xp bm = new xp(new xi(){

        @Override
        public boolean a(wn wn2) {
            return false;
        }
    }, 2, 1);
    private static final Map<zd, float[]> bo = Maps.newEnumMap(zd.class);
    private int bp;
    private qy bq = new qy(this);

    public static float[] a(zd zd2) {
        return bo.get(zd2);
    }

    public tv(adm adm2) {
        super(adm2);
        this.a(0.9f, 1.3f);
        ((sv)this.s()).a(true);
        this.i.a(0, new ra(this));
        this.i.a(1, new rv(this, 1.25));
        this.i.a(2, new qv(this, 1.0));
        this.i.a(3, new sh(this, 1.1, zy.O, false));
        this.i.a(4, new rc(this, 1.1));
        this.i.a(5, this.bq);
        this.i.a(6, new rz(this, 1.0));
        this.i.a(7, new ri(this, wn.class, 6.0f));
        this.i.a(8, new ry(this));
        this.bm.a(0, new zx(zy.aW, 1, 0));
        this.bm.a(1, new zx(zy.aW, 1, 0));
    }

    @Override
    protected void E() {
        this.bp = this.bq.f();
        super.E();
    }

    @Override
    public void m() {
        if (this.o.D) {
            this.bp = Math.max(0, this.bp - 1);
        }
        super.m();
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(8.0);
        this.a(vy.d).a(0.23f);
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, new Byte(0));
    }

    @Override
    protected void b(boolean bl2, int n2) {
        if (!this.cm()) {
            this.a(new zx(zw.a(afi.L), 1, this.cl().a()), 0.0f);
        }
        \u2603 = this.V.nextInt(2) + 1 + this.V.nextInt(1 + n2);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            if (this.at()) {
                this.a(zy.bn, 1);
                continue;
            }
            this.a(zy.bm, 1);
        }
    }

    @Override
    protected zw A() {
        return zw.a(afi.L);
    }

    @Override
    public void a(byte by) {
        if (by == 10) {
            this.bp = 40;
        } else {
            super.a(by);
        }
    }

    public float p(float f2) {
        if (this.bp <= 0) {
            return 0.0f;
        }
        if (this.bp >= 4 && this.bp <= 36) {
            return 1.0f;
        }
        if (this.bp < 4) {
            return ((float)this.bp - f2) / 4.0f;
        }
        return -((float)(this.bp - 40) - f2) / 4.0f;
    }

    public float q(float f2) {
        if (this.bp > 4 && this.bp <= 36) {
            \u2603 = ((float)(this.bp - 4) - f2) / 32.0f;
            return 0.62831855f + 0.21991149f * ns.a(\u2603 * 28.7f);
        }
        if (this.bp > 0) {
            return 0.62831855f;
        }
        return this.z / 57.295776f;
    }

    @Override
    public boolean a(wn wn22) {
        wn wn22;
        zx zx2 = wn22.bi.h();
        if (zx2 != null && zx2.b() == zy.be && !this.cm() && !this.j_()) {
            if (!this.o.D) {
                this.l(true);
                int n2 = 1 + this.V.nextInt(3);
                for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                    uz uz2 = this.a(new zx(zw.a(afi.L), 1, this.cl().a()), 1.0f);
                    uz2.w += (double)(this.V.nextFloat() * 0.05f);
                    uz2.v += (double)((this.V.nextFloat() - this.V.nextFloat()) * 0.1f);
                    uz2.x += (double)((this.V.nextFloat() - this.V.nextFloat()) * 0.1f);
                }
            }
            zx2.a(1, (pr)wn22);
            this.a("mob.sheep.shear", 1.0f, 1.0f);
        }
        return super.a(wn22);
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("Sheared", this.cm());
        dn2.a("Color", (byte)this.cl().a());
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.l(dn2.n("Sheared"));
        this.b(zd.b(dn2.d("Color")));
    }

    @Override
    protected String z() {
        return "mob.sheep.say";
    }

    @Override
    protected String bo() {
        return "mob.sheep.say";
    }

    @Override
    protected String bp() {
        return "mob.sheep.say";
    }

    @Override
    protected void a(cj cj2, afh afh2) {
        this.a("mob.sheep.step", 0.15f, 1.0f);
    }

    public zd cl() {
        return zd.b(this.ac.a(16) & 0xF);
    }

    public void b(zd zd2) {
        byte by = this.ac.a(16);
        this.ac.b(16, (byte)(by & 0xF0 | zd2.a() & 0xF));
    }

    public boolean cm() {
        return (this.ac.a(16) & 0x10) != 0;
    }

    public void l(boolean bl2) {
        byte by = this.ac.a(16);
        if (bl2) {
            this.ac.b(16, (byte)(by | 0x10));
        } else {
            this.ac.b(16, (byte)(by & 0xFFFFFFEF));
        }
    }

    public static zd a(Random random) {
        int n2 = random.nextInt(100);
        if (n2 < 5) {
            return zd.p;
        }
        if (n2 < 10) {
            return zd.h;
        }
        if (n2 < 15) {
            return zd.i;
        }
        if (n2 < 18) {
            return zd.m;
        }
        if (random.nextInt(500) == 0) {
            return zd.g;
        }
        return zd.a;
    }

    public tv b(ph ph2) {
        tv tv2 = (tv)ph2;
        \u2603 = new tv(this.o);
        \u2603.b(this.a(this, tv2));
        return \u2603;
    }

    @Override
    public void v() {
        this.l(false);
        if (this.j_()) {
            this.a(60);
        }
    }

    @Override
    public pu a(ok ok2, pu pu2) {
        pu2 = super.a(ok2, pu2);
        this.b(tv.a(this.o.s));
        return pu2;
    }

    private zd a(tm tm2, tm tm3) {
        int n2 = ((tv)tm2).cl().b();
        \u2603 = ((tv)tm3).cl().b();
        this.bm.a(0).b(n2);
        this.bm.a(1).b(\u2603);
        zx \u26032 = abt.a().a(this.bm, ((tv)tm2).o);
        \u2603 = \u26032 != null && \u26032.b() == zy.aW ? \u26032.i() : (this.o.s.nextBoolean() ? n2 : \u2603);
        return zd.a(\u2603);
    }

    @Override
    public float aS() {
        return 0.95f * this.K;
    }

    @Override
    public /* synthetic */ ph a(ph ph2) {
        return this.b(ph2);
    }

    static {
        bo.put(zd.a, new float[]{1.0f, 1.0f, 1.0f});
        bo.put(zd.b, new float[]{0.85f, 0.5f, 0.2f});
        bo.put(zd.c, new float[]{0.7f, 0.3f, 0.85f});
        bo.put(zd.d, new float[]{0.4f, 0.6f, 0.85f});
        bo.put(zd.e, new float[]{0.9f, 0.9f, 0.2f});
        bo.put(zd.f, new float[]{0.5f, 0.8f, 0.1f});
        bo.put(zd.g, new float[]{0.95f, 0.5f, 0.65f});
        bo.put(zd.h, new float[]{0.3f, 0.3f, 0.3f});
        bo.put(zd.i, new float[]{0.6f, 0.6f, 0.6f});
        bo.put(zd.j, new float[]{0.3f, 0.5f, 0.6f});
        bo.put(zd.k, new float[]{0.5f, 0.25f, 0.7f});
        bo.put(zd.l, new float[]{0.2f, 0.3f, 0.7f});
        bo.put(zd.m, new float[]{0.4f, 0.3f, 0.2f});
        bo.put(zd.n, new float[]{0.4f, 0.5f, 0.2f});
        bo.put(zd.o, new float[]{0.6f, 0.2f, 0.2f});
        bo.put(zd.p, new float[]{0.1f, 0.1f, 0.1f});
    }
}

