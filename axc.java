/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import org.lwjgl.input.Keyboard;

public class axc
extends axu {
    private static final List<a> f = Lists.newArrayList();
    private b g;
    private avs h;
    private avw i;
    private axd r;
    protected String a = "Customize World Presets";
    private String s;
    private String t;

    public axc(axd axd2) {
        this.r = axd2;
    }

    @Override
    public void b() {
        this.n.clear();
        Keyboard.enableRepeatEvents(true);
        this.a = bnq.a("createWorld.customize.custom.presets.title", new Object[0]);
        this.s = bnq.a("createWorld.customize.presets.share", new Object[0]);
        this.t = bnq.a("createWorld.customize.presets.list", new Object[0]);
        this.i = new avw(2, this.q, 50, 40, this.l - 100, 20);
        this.g = new b();
        this.i.f(2000);
        this.i.a(this.r.a());
        this.h = new avs(0, this.l / 2 - 102, this.m - 27, 100, 20, bnq.a("createWorld.customize.presets.select", new Object[0]));
        this.n.add(this.h);
        this.n.add(new avs(1, this.l / 2 + 3, this.m - 27, 100, 20, bnq.a("gui.cancel", new Object[0])));
        this.a();
    }

    @Override
    public void k() {
        super.k();
        this.g.p();
    }

    @Override
    public void m() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        this.i.a(n2, n3, n4);
        super.a(n2, n3, n4);
    }

    @Override
    protected void a(char c2, int n2) {
        if (!this.i.a(c2, n2)) {
            super.a(c2, n2);
        }
    }

    @Override
    protected void a(avs avs2) {
        switch (avs2.k) {
            case 0: {
                this.r.a(this.i.b());
                this.j.a(this.r);
                break;
            }
            case 1: {
                this.j.a(this.r);
            }
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.g.a(n2, n3, f2);
        this.a(this.q, this.a, this.l / 2, 8, 0xFFFFFF);
        this.c(this.q, this.s, 50, 30, 0xA0A0A0);
        this.c(this.q, this.t, 50, 70, 0xA0A0A0);
        this.i.g();
        super.a(n2, n3, f2);
    }

    @Override
    public void e() {
        this.i.a();
        super.e();
    }

    public void a() {
        this.h.l = this.g();
    }

    private boolean g() {
        return this.g.u > -1 && this.g.u < f.size() || this.i.b().length() > 1;
    }

    static {
        ant.a a2 = ant.a.a("{ \"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":5000.0, \"mainNoiseScaleY\":1000.0, \"mainNoiseScaleZ\":5000.0, \"baseSize\":8.5, \"stretchY\":8.0, \"biomeDepthWeight\":2.0, \"biomeDepthOffset\":0.5, \"biomeScaleWeight\":2.0, \"biomeScaleOffset\":0.375, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":255 }");
        jy \u26032 = new jy("textures/gui/presets/water.png");
        f.add(new a(bnq.a("createWorld.customize.custom.preset.waterWorld", new Object[0]), \u26032, a2));
        a2 = ant.a.a("{\"coordinateScale\":3000.0, \"heightScale\":6000.0, \"upperLimitScale\":250.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":80.0, \"mainNoiseScaleY\":160.0, \"mainNoiseScaleZ\":80.0, \"baseSize\":8.5, \"stretchY\":10.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":63 }");
        \u26032 = new jy("textures/gui/presets/isles.png");
        f.add(new a(bnq.a("createWorld.customize.custom.preset.isleLand", new Object[0]), \u26032, a2));
        a2 = ant.a.a("{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":5000.0, \"mainNoiseScaleY\":1000.0, \"mainNoiseScaleZ\":5000.0, \"baseSize\":8.5, \"stretchY\":5.0, \"biomeDepthWeight\":2.0, \"biomeDepthOffset\":1.0, \"biomeScaleWeight\":4.0, \"biomeScaleOffset\":1.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":63 }");
        \u26032 = new jy("textures/gui/presets/delight.png");
        f.add(new a(bnq.a("createWorld.customize.custom.preset.caveDelight", new Object[0]), \u26032, a2));
        a2 = ant.a.a("{\"coordinateScale\":738.41864, \"heightScale\":157.69133, \"upperLimitScale\":801.4267, \"lowerLimitScale\":1254.1643, \"depthNoiseScaleX\":374.93652, \"depthNoiseScaleZ\":288.65228, \"depthNoiseScaleExponent\":1.2092624, \"mainNoiseScaleX\":1355.9908, \"mainNoiseScaleY\":745.5343, \"mainNoiseScaleZ\":1183.464, \"baseSize\":1.8758626, \"stretchY\":1.7137525, \"biomeDepthWeight\":1.7553768, \"biomeDepthOffset\":3.4701107, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":2.535211, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":63 }");
        \u26032 = new jy("textures/gui/presets/madness.png");
        f.add(new a(bnq.a("createWorld.customize.custom.preset.mountains", new Object[0]), \u26032, a2));
        a2 = ant.a.a("{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":1000.0, \"mainNoiseScaleY\":3000.0, \"mainNoiseScaleZ\":1000.0, \"baseSize\":8.5, \"stretchY\":10.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":20 }");
        \u26032 = new jy("textures/gui/presets/drought.png");
        f.add(new a(bnq.a("createWorld.customize.custom.preset.drought", new Object[0]), \u26032, a2));
        a2 = ant.a.a("{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":2.0, \"lowerLimitScale\":64.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":80.0, \"mainNoiseScaleY\":160.0, \"mainNoiseScaleZ\":80.0, \"baseSize\":8.5, \"stretchY\":12.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":false, \"seaLevel\":6 }");
        \u26032 = new jy("textures/gui/presets/chaos.png");
        f.add(new a(bnq.a("createWorld.customize.custom.preset.caveChaos", new Object[0]), \u26032, a2));
        a2 = ant.a.a("{\"coordinateScale\":684.412, \"heightScale\":684.412, \"upperLimitScale\":512.0, \"lowerLimitScale\":512.0, \"depthNoiseScaleX\":200.0, \"depthNoiseScaleZ\":200.0, \"depthNoiseScaleExponent\":0.5, \"mainNoiseScaleX\":80.0, \"mainNoiseScaleY\":160.0, \"mainNoiseScaleZ\":80.0, \"baseSize\":8.5, \"stretchY\":12.0, \"biomeDepthWeight\":1.0, \"biomeDepthOffset\":0.0, \"biomeScaleWeight\":1.0, \"biomeScaleOffset\":0.0, \"useCaves\":true, \"useDungeons\":true, \"dungeonChance\":8, \"useStrongholds\":true, \"useVillages\":true, \"useMineShafts\":true, \"useTemples\":true, \"useRavines\":true, \"useWaterLakes\":true, \"waterLakeChance\":4, \"useLavaLakes\":true, \"lavaLakeChance\":80, \"useLavaOceans\":true, \"seaLevel\":40 }");
        \u26032 = new jy("textures/gui/presets/luck.png");
        f.add(new a(bnq.a("createWorld.customize.custom.preset.goodLuck", new Object[0]), \u26032, a2));
    }

    static class a {
        public String a;
        public jy b;
        public ant.a c;

        public a(String string, jy jy2, ant.a a2) {
            this.a = string;
            this.b = jy2;
            this.c = a2;
        }
    }

    class b
    extends awi {
        public int u;

        public b() {
            super(axc.this.j, axc.this.l, axc.this.m, 80, axc.this.m - 32, 38);
            this.u = -1;
        }

        @Override
        protected int b() {
            return f.size();
        }

        @Override
        protected void a(int n2, boolean bl2, int n3, int n4) {
            this.u = n2;
            axc.this.a();
            axc.this.i.a(((a)f.get((int)((axc)axc.this).g.u)).c.toString());
        }

        @Override
        protected boolean a(int n2) {
            return n2 == this.u;
        }

        @Override
        protected void a() {
        }

        private void a(int n2, int n3, jy jy2) {
            int n4 = n2 + 5;
            \u2603 = n3;
            axc.this.a(n4 - 1, n4 + 32, \u2603 - 1, -2039584);
            axc.this.a(n4 - 1, n4 + 32, \u2603 + 32, -6250336);
            axc.this.b(n4 - 1, \u2603 - 1, \u2603 + 32, -2039584);
            axc.this.b(n4 + 32, \u2603 - 1, \u2603 + 32, -6250336);
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            this.a.P().a(jy2);
            \u2603 = 32;
            \u2603 = 32;
            bfx \u26032 = bfx.a();
            bfd \u26033 = \u26032.c();
            \u26033.a(7, bms.g);
            \u26033.b((double)(n4 + 0), (double)(\u2603 + 32), 0.0).a(0.0, 1.0).d();
            \u26033.b((double)(n4 + 32), (double)(\u2603 + 32), 0.0).a(1.0, 1.0).d();
            \u26033.b((double)(n4 + 32), (double)(\u2603 + 0), 0.0).a(1.0, 0.0).d();
            \u26033.b((double)(n4 + 0), (double)(\u2603 + 0), 0.0).a(0.0, 0.0).d();
            \u26032.b();
        }

        @Override
        protected void a(int n2, int n3, int n4, int n5, int n6, int n7) {
            a a2 = (a)f.get(n2);
            this.a(n3, n4, a2.b);
            axc.this.q.a(a2.a, n3 + 32 + 10, n4 + 14, 0xFFFFFF);
        }
    }
}

