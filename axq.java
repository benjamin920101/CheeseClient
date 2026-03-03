/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.List;
import org.lwjgl.input.Keyboard;

public class axq
extends axu {
    private static final List<a> a = Lists.newArrayList();
    private final axa f;
    private String g;
    private String h;
    private String i;
    private b r;
    private avs s;
    private avw t;

    public axq(axa axa2) {
        this.f = axa2;
    }

    @Override
    public void b() {
        this.n.clear();
        Keyboard.enableRepeatEvents(true);
        this.g = bnq.a("createWorld.customize.presets.title", new Object[0]);
        this.h = bnq.a("createWorld.customize.presets.share", new Object[0]);
        this.i = bnq.a("createWorld.customize.presets.list", new Object[0]);
        this.t = new avw(2, this.q, 50, 40, this.l - 100, 20);
        this.r = new b();
        this.t.f(1230);
        this.t.a(this.f.a());
        this.s = new avs(0, this.l / 2 - 155, this.m - 28, 150, 20, bnq.a("createWorld.customize.presets.select", new Object[0]));
        this.n.add(this.s);
        this.n.add(new avs(1, this.l / 2 + 5, this.m - 28, 150, 20, bnq.a("gui.cancel", new Object[0])));
        this.a();
    }

    @Override
    public void k() {
        super.k();
        this.r.p();
    }

    @Override
    public void m() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        this.t.a(n2, n3, n4);
        super.a(n2, n3, n4);
    }

    @Override
    protected void a(char c2, int n2) {
        if (!this.t.a(c2, n2)) {
            super.a(c2, n2);
        }
    }

    @Override
    protected void a(avs avs2) {
        if (avs2.k == 0 && this.g()) {
            this.f.a(this.t.b());
            this.j.a(this.f);
        } else if (avs2.k == 1) {
            this.j.a(this.f);
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.r.a(n2, n3, f2);
        this.a(this.q, this.g, this.l / 2, 8, 0xFFFFFF);
        this.c(this.q, this.h, 50, 30, 0xA0A0A0);
        this.c(this.q, this.i, 50, 70, 0xA0A0A0);
        this.t.g();
        super.a(n2, n3, f2);
    }

    @Override
    public void e() {
        this.t.a();
        super.e();
    }

    public void a() {
        boolean bl2;
        this.s.l = bl2 = this.g();
    }

    private boolean g() {
        return this.r.u > -1 && this.r.u < a.size() || this.t.b().length() > 1;
    }

    private static void a(String string, zw zw2, ady ady2, aqa ... aqaArray) {
        axq.a(string, zw2, 0, ady2, null, aqaArray);
    }

    private static void a(String string, zw zw2, ady ady2, List<String> list, aqa ... aqaArray) {
        axq.a(string, zw2, 0, ady2, list, aqaArray);
    }

    private static void a(String string, zw zw22, int n2, ady ady2, List<String> list, aqa ... aqaArray) {
        zw zw22;
        apz apz2 = new apz();
        for (int i2 = aqaArray.length - 1; i2 >= 0; --i2) {
            apz2.c().add(aqaArray[i2]);
        }
        apz2.a(ady2.az);
        apz2.d();
        if (list != null) {
            for (String string2 : list) {
                apz2.b().put(string2, Maps.newHashMap());
            }
        }
        a.add(new a(zw22, n2, string, apz2.toString()));
    }

    static {
        axq.a("Classic Flat", zw.a(afi.c), ady.q, Arrays.asList("village"), new aqa(1, afi.c), new aqa(2, afi.d), new aqa(1, afi.h));
        axq.a("Tunnelers' Dream", zw.a(afi.b), ady.s, Arrays.asList("biome_1", "dungeon", "decoration", "stronghold", "mineshaft"), new aqa(1, afi.c), new aqa(5, afi.d), new aqa(230, afi.b), new aqa(1, afi.h));
        axq.a("Water World", zy.ax, ady.N, Arrays.asList("biome_1", "oceanmonument"), new aqa(90, afi.j), new aqa(5, afi.m), new aqa(5, afi.d), new aqa(5, afi.b), new aqa(1, afi.h));
        axq.a("Overworld", zw.a(afi.H), akc.a.b.a(), ady.q, Arrays.asList("village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon", "lake", "lava_lake"), new aqa(1, afi.c), new aqa(3, afi.d), new aqa(59, afi.b), new aqa(1, afi.h));
        axq.a("Snowy Kingdom", zw.a(afi.aH), ady.B, Arrays.asList("village", "biome_1"), new aqa(1, afi.aH), new aqa(1, afi.c), new aqa(3, afi.d), new aqa(59, afi.b), new aqa(1, afi.h));
        axq.a("Bottomless Pit", zy.G, ady.q, Arrays.asList("village", "biome_1"), new aqa(1, afi.c), new aqa(3, afi.d), new aqa(2, afi.e));
        axq.a("Desert", zw.a(afi.m), ady.r, Arrays.asList("village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon"), new aqa(8, afi.m), new aqa(52, afi.A), new aqa(3, afi.b), new aqa(1, afi.h));
        axq.a("Redstone Ready", zy.aC, ady.r, new aqa(52, afi.A), new aqa(3, afi.b), new aqa(1, afi.h));
    }

    static class a {
        public zw a;
        public int b;
        public String c;
        public String d;

        public a(zw zw2, int n2, String string, String string2) {
            this.a = zw2;
            this.b = n2;
            this.c = string;
            this.d = string2;
        }
    }

    class b
    extends awi {
        public int u;

        public b() {
            super(axq.this.j, axq.this.l, axq.this.m, 80, axq.this.m - 37, 24);
            this.u = -1;
        }

        private void a(int n2, int n3, zw zw2, int n4) {
            this.e(n2 + 1, n3 + 1);
            bfl.B();
            avc.c();
            axq.this.k.a(new zx(zw2, 1, n4), n2 + 2, n3 + 2);
            avc.a();
            bfl.C();
        }

        private void e(int n2, int n3) {
            this.d(n2, n3, 0, 0);
        }

        private void d(int n2, int n3, int n4, int n5) {
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            this.a.P().a(avp.c);
            float f2 = 0.0078125f;
            \u2603 = 0.0078125f;
            int \u26032 = 18;
            int \u26033 = 18;
            bfx \u26034 = bfx.a();
            bfd \u26035 = \u26034.c();
            \u26035.a(7, bms.g);
            \u26035.b((double)(n2 + 0), (double)(n3 + 18), (double)axq.this.e).a((float)(n4 + 0) * 0.0078125f, (float)(n5 + 18) * 0.0078125f).d();
            \u26035.b((double)(n2 + 18), (double)(n3 + 18), (double)axq.this.e).a((float)(n4 + 18) * 0.0078125f, (float)(n5 + 18) * 0.0078125f).d();
            \u26035.b((double)(n2 + 18), (double)(n3 + 0), (double)axq.this.e).a((float)(n4 + 18) * 0.0078125f, (float)(n5 + 0) * 0.0078125f).d();
            \u26035.b((double)(n2 + 0), (double)(n3 + 0), (double)axq.this.e).a((float)(n4 + 0) * 0.0078125f, (float)(n5 + 0) * 0.0078125f).d();
            \u26034.b();
        }

        @Override
        protected int b() {
            return a.size();
        }

        @Override
        protected void a(int n2, boolean bl2, int n3, int n4) {
            this.u = n2;
            axq.this.a();
            axq.this.t.a(((a)a.get((int)((axq)axq.this).r.u)).d);
        }

        @Override
        protected boolean a(int n2) {
            return n2 == this.u;
        }

        @Override
        protected void a() {
        }

        @Override
        protected void a(int n2, int n3, int n4, int n5, int n6, int n7) {
            a a2 = (a)a.get(n2);
            this.a(n3, n4, a2.a, a2.b);
            axq.this.q.a(a2.c, n3 + 18 + 5, n4 + 6, 0xFFFFFF);
        }
    }
}

