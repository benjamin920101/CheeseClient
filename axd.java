/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.primitives.Floats;
import java.util.Random;

public class axd
extends axu
implements avx.a,
awg.b {
    private axb i;
    protected String a = "Customize World Settings";
    protected String f = "Page 1 of 3";
    protected String g = "Basic Settings";
    protected String[] h = new String[4];
    private awg r;
    private avs s;
    private avs t;
    private avs u;
    private avs v;
    private avs w;
    private avs x;
    private avs y;
    private avs z;
    private boolean A = false;
    private int B = 0;
    private boolean C = false;
    private Predicate<String> D = new Predicate<String>(){

        public boolean a(String string) {
            Float f2 = Floats.tryParse(string);
            return string.length() == 0 || f2 != null && Floats.isFinite(f2.floatValue()) && f2.floatValue() >= 0.0f;
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((String)object);
        }
    };
    private ant.a E = new ant.a();
    private ant.a F;
    private Random G = new Random();

    public axd(axu axu2, String string) {
        this.i = (axb)axu2;
        this.a(string);
    }

    @Override
    public void b() {
        int n2 = 0;
        \u2603 = 0;
        if (this.r != null) {
            n2 = this.r.e();
            \u2603 = this.r.n();
        }
        this.a = bnq.a("options.customizeTitle", new Object[0]);
        this.n.clear();
        this.v = new avs(302, 20, 5, 80, 20, bnq.a("createWorld.customize.custom.prev", new Object[0]));
        this.n.add(this.v);
        this.w = new avs(303, this.l - 100, 5, 80, 20, bnq.a("createWorld.customize.custom.next", new Object[0]));
        this.n.add(this.w);
        this.u = new avs(304, this.l / 2 - 187, this.m - 27, 90, 20, bnq.a("createWorld.customize.custom.defaults", new Object[0]));
        this.n.add(this.u);
        this.t = new avs(301, this.l / 2 - 92, this.m - 27, 90, 20, bnq.a("createWorld.customize.custom.randomize", new Object[0]));
        this.n.add(this.t);
        this.z = new avs(305, this.l / 2 + 3, this.m - 27, 90, 20, bnq.a("createWorld.customize.custom.presets", new Object[0]));
        this.n.add(this.z);
        this.s = new avs(300, this.l / 2 + 98, this.m - 27, 90, 20, bnq.a("gui.done", new Object[0]));
        this.n.add(this.s);
        this.u.l = this.A;
        this.x = new avs(306, this.l / 2 - 55, 160, 50, 20, bnq.a("gui.yes", new Object[0]));
        this.x.m = false;
        this.n.add(this.x);
        this.y = new avs(307, this.l / 2 + 5, 160, 50, 20, bnq.a("gui.no", new Object[0]));
        this.y.m = false;
        this.n.add(this.y);
        if (this.B != 0) {
            this.x.m = true;
            this.y.m = true;
        }
        this.f();
        if (n2 != 0) {
            this.r.c(n2);
            this.r.h(\u2603);
            this.i();
        }
    }

    @Override
    public void k() {
        super.k();
        this.r.p();
    }

    private void f() {
        awg.f[] fArray = new awg.f[]{new awg.g(160, bnq.a("createWorld.customize.custom.seaLevel", new Object[0]), true, this, 1.0f, 255.0f, this.F.r), new awg.a(148, bnq.a("createWorld.customize.custom.useCaves", new Object[0]), true, this.F.s), new awg.a(150, bnq.a("createWorld.customize.custom.useStrongholds", new Object[0]), true, this.F.v), new awg.a(151, bnq.a("createWorld.customize.custom.useVillages", new Object[0]), true, this.F.w), new awg.a(152, bnq.a("createWorld.customize.custom.useMineShafts", new Object[0]), true, this.F.x), new awg.a(153, bnq.a("createWorld.customize.custom.useTemples", new Object[0]), true, this.F.y), new awg.a(210, bnq.a("createWorld.customize.custom.useMonuments", new Object[0]), true, this.F.z), new awg.a(154, bnq.a("createWorld.customize.custom.useRavines", new Object[0]), true, this.F.A), new awg.a(149, bnq.a("createWorld.customize.custom.useDungeons", new Object[0]), true, this.F.t), new awg.g(157, bnq.a("createWorld.customize.custom.dungeonChance", new Object[0]), true, this, 1.0f, 100.0f, this.F.u), new awg.a(155, bnq.a("createWorld.customize.custom.useWaterLakes", new Object[0]), true, this.F.B), new awg.g(158, bnq.a("createWorld.customize.custom.waterLakeChance", new Object[0]), true, this, 1.0f, 100.0f, this.F.C), new awg.a(156, bnq.a("createWorld.customize.custom.useLavaLakes", new Object[0]), true, this.F.D), new awg.g(159, bnq.a("createWorld.customize.custom.lavaLakeChance", new Object[0]), true, this, 10.0f, 100.0f, this.F.E), new awg.a(161, bnq.a("createWorld.customize.custom.useLavaOceans", new Object[0]), true, this.F.F), new awg.g(162, bnq.a("createWorld.customize.custom.fixedBiome", new Object[0]), true, this, -1.0f, 37.0f, this.F.G), new awg.g(163, bnq.a("createWorld.customize.custom.biomeSize", new Object[0]), true, this, 1.0f, 8.0f, this.F.H), new awg.g(164, bnq.a("createWorld.customize.custom.riverSize", new Object[0]), true, this, 1.0f, 5.0f, this.F.I)};
        \u2603 = new awg.f[]{new awg.e(416, bnq.a("tile.dirt.name", new Object[0]), false), null, new awg.g(165, bnq.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0f, 50.0f, this.F.J), new awg.g(166, bnq.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0f, 40.0f, this.F.K), new awg.g(167, bnq.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.L), new awg.g(168, bnq.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.M), new awg.e(417, bnq.a("tile.gravel.name", new Object[0]), false), null, new awg.g(169, bnq.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0f, 50.0f, this.F.N), new awg.g(170, bnq.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0f, 40.0f, this.F.O), new awg.g(171, bnq.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.P), new awg.g(172, bnq.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.Q), new awg.e(418, bnq.a("tile.stone.granite.name", new Object[0]), false), null, new awg.g(173, bnq.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0f, 50.0f, this.F.R), new awg.g(174, bnq.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0f, 40.0f, this.F.S), new awg.g(175, bnq.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.T), new awg.g(176, bnq.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.U), new awg.e(419, bnq.a("tile.stone.diorite.name", new Object[0]), false), null, new awg.g(177, bnq.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0f, 50.0f, this.F.V), new awg.g(178, bnq.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0f, 40.0f, this.F.W), new awg.g(179, bnq.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.X), new awg.g(180, bnq.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.Y), new awg.e(420, bnq.a("tile.stone.andesite.name", new Object[0]), false), null, new awg.g(181, bnq.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0f, 50.0f, this.F.Z), new awg.g(182, bnq.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0f, 40.0f, this.F.aa), new awg.g(183, bnq.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.ab), new awg.g(184, bnq.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.ac), new awg.e(421, bnq.a("tile.oreCoal.name", new Object[0]), false), null, new awg.g(185, bnq.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0f, 50.0f, this.F.ad), new awg.g(186, bnq.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0f, 40.0f, this.F.ae), new awg.g(187, bnq.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.af), new awg.g(189, bnq.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.ag), new awg.e(422, bnq.a("tile.oreIron.name", new Object[0]), false), null, new awg.g(190, bnq.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0f, 50.0f, this.F.ah), new awg.g(191, bnq.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0f, 40.0f, this.F.ai), new awg.g(192, bnq.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.aj), new awg.g(193, bnq.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.ak), new awg.e(423, bnq.a("tile.oreGold.name", new Object[0]), false), null, new awg.g(194, bnq.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0f, 50.0f, this.F.al), new awg.g(195, bnq.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0f, 40.0f, this.F.am), new awg.g(196, bnq.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.an), new awg.g(197, bnq.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.ao), new awg.e(424, bnq.a("tile.oreRedstone.name", new Object[0]), false), null, new awg.g(198, bnq.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0f, 50.0f, this.F.ap), new awg.g(199, bnq.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0f, 40.0f, this.F.aq), new awg.g(200, bnq.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.ar), new awg.g(201, bnq.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.as), new awg.e(425, bnq.a("tile.oreDiamond.name", new Object[0]), false), null, new awg.g(202, bnq.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0f, 50.0f, this.F.at), new awg.g(203, bnq.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0f, 40.0f, this.F.au), new awg.g(204, bnq.a("createWorld.customize.custom.minHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.av), new awg.g(205, bnq.a("createWorld.customize.custom.maxHeight", new Object[0]), false, this, 0.0f, 255.0f, this.F.aw), new awg.e(426, bnq.a("tile.oreLapis.name", new Object[0]), false), null, new awg.g(206, bnq.a("createWorld.customize.custom.size", new Object[0]), false, this, 1.0f, 50.0f, this.F.ax), new awg.g(207, bnq.a("createWorld.customize.custom.count", new Object[0]), false, this, 0.0f, 40.0f, this.F.ay), new awg.g(208, bnq.a("createWorld.customize.custom.center", new Object[0]), false, this, 0.0f, 255.0f, this.F.az), new awg.g(209, bnq.a("createWorld.customize.custom.spread", new Object[0]), false, this, 0.0f, 255.0f, this.F.aA)};
        \u2603 = new awg.f[]{new awg.g(100, bnq.a("createWorld.customize.custom.mainNoiseScaleX", new Object[0]), false, this, 1.0f, 5000.0f, this.F.i), new awg.g(101, bnq.a("createWorld.customize.custom.mainNoiseScaleY", new Object[0]), false, this, 1.0f, 5000.0f, this.F.j), new awg.g(102, bnq.a("createWorld.customize.custom.mainNoiseScaleZ", new Object[0]), false, this, 1.0f, 5000.0f, this.F.k), new awg.g(103, bnq.a("createWorld.customize.custom.depthNoiseScaleX", new Object[0]), false, this, 1.0f, 2000.0f, this.F.f), new awg.g(104, bnq.a("createWorld.customize.custom.depthNoiseScaleZ", new Object[0]), false, this, 1.0f, 2000.0f, this.F.g), new awg.g(105, bnq.a("createWorld.customize.custom.depthNoiseScaleExponent", new Object[0]), false, this, 0.01f, 20.0f, this.F.h), new awg.g(106, bnq.a("createWorld.customize.custom.baseSize", new Object[0]), false, this, 1.0f, 25.0f, this.F.l), new awg.g(107, bnq.a("createWorld.customize.custom.coordinateScale", new Object[0]), false, this, 1.0f, 6000.0f, this.F.b), new awg.g(108, bnq.a("createWorld.customize.custom.heightScale", new Object[0]), false, this, 1.0f, 6000.0f, this.F.c), new awg.g(109, bnq.a("createWorld.customize.custom.stretchY", new Object[0]), false, this, 0.01f, 50.0f, this.F.m), new awg.g(110, bnq.a("createWorld.customize.custom.upperLimitScale", new Object[0]), false, this, 1.0f, 5000.0f, this.F.d), new awg.g(111, bnq.a("createWorld.customize.custom.lowerLimitScale", new Object[0]), false, this, 1.0f, 5000.0f, this.F.e), new awg.g(112, bnq.a("createWorld.customize.custom.biomeDepthWeight", new Object[0]), false, this, 1.0f, 20.0f, this.F.n), new awg.g(113, bnq.a("createWorld.customize.custom.biomeDepthOffset", new Object[0]), false, this, 0.0f, 20.0f, this.F.o), new awg.g(114, bnq.a("createWorld.customize.custom.biomeScaleWeight", new Object[0]), false, this, 1.0f, 20.0f, this.F.p), new awg.g(115, bnq.a("createWorld.customize.custom.biomeScaleOffset", new Object[0]), false, this, 0.0f, 20.0f, this.F.q)};
        \u2603 = new awg.f[]{new awg.e(400, bnq.a("createWorld.customize.custom.mainNoiseScaleX", new Object[0]) + ":", false), new awg.c(132, String.format("%5.3f", Float.valueOf(this.F.i)), false, this.D), new awg.e(401, bnq.a("createWorld.customize.custom.mainNoiseScaleY", new Object[0]) + ":", false), new awg.c(133, String.format("%5.3f", Float.valueOf(this.F.j)), false, this.D), new awg.e(402, bnq.a("createWorld.customize.custom.mainNoiseScaleZ", new Object[0]) + ":", false), new awg.c(134, String.format("%5.3f", Float.valueOf(this.F.k)), false, this.D), new awg.e(403, bnq.a("createWorld.customize.custom.depthNoiseScaleX", new Object[0]) + ":", false), new awg.c(135, String.format("%5.3f", Float.valueOf(this.F.f)), false, this.D), new awg.e(404, bnq.a("createWorld.customize.custom.depthNoiseScaleZ", new Object[0]) + ":", false), new awg.c(136, String.format("%5.3f", Float.valueOf(this.F.g)), false, this.D), new awg.e(405, bnq.a("createWorld.customize.custom.depthNoiseScaleExponent", new Object[0]) + ":", false), new awg.c(137, String.format("%2.3f", Float.valueOf(this.F.h)), false, this.D), new awg.e(406, bnq.a("createWorld.customize.custom.baseSize", new Object[0]) + ":", false), new awg.c(138, String.format("%2.3f", Float.valueOf(this.F.l)), false, this.D), new awg.e(407, bnq.a("createWorld.customize.custom.coordinateScale", new Object[0]) + ":", false), new awg.c(139, String.format("%5.3f", Float.valueOf(this.F.b)), false, this.D), new awg.e(408, bnq.a("createWorld.customize.custom.heightScale", new Object[0]) + ":", false), new awg.c(140, String.format("%5.3f", Float.valueOf(this.F.c)), false, this.D), new awg.e(409, bnq.a("createWorld.customize.custom.stretchY", new Object[0]) + ":", false), new awg.c(141, String.format("%2.3f", Float.valueOf(this.F.m)), false, this.D), new awg.e(410, bnq.a("createWorld.customize.custom.upperLimitScale", new Object[0]) + ":", false), new awg.c(142, String.format("%5.3f", Float.valueOf(this.F.d)), false, this.D), new awg.e(411, bnq.a("createWorld.customize.custom.lowerLimitScale", new Object[0]) + ":", false), new awg.c(143, String.format("%5.3f", Float.valueOf(this.F.e)), false, this.D), new awg.e(412, bnq.a("createWorld.customize.custom.biomeDepthWeight", new Object[0]) + ":", false), new awg.c(144, String.format("%2.3f", Float.valueOf(this.F.n)), false, this.D), new awg.e(413, bnq.a("createWorld.customize.custom.biomeDepthOffset", new Object[0]) + ":", false), new awg.c(145, String.format("%2.3f", Float.valueOf(this.F.o)), false, this.D), new awg.e(414, bnq.a("createWorld.customize.custom.biomeScaleWeight", new Object[0]) + ":", false), new awg.c(146, String.format("%2.3f", Float.valueOf(this.F.p)), false, this.D), new awg.e(415, bnq.a("createWorld.customize.custom.biomeScaleOffset", new Object[0]) + ":", false), new awg.c(147, String.format("%2.3f", Float.valueOf(this.F.q)), false, this.D)};
        this.r = new awg(this.j, this.l, this.m, 32, this.m - 32, 25, this, fArray, \u2603, \u2603, \u2603);
        for (int i2 = 0; i2 < 4; ++i2) {
            this.h[i2] = bnq.a("createWorld.customize.custom.page" + i2, new Object[0]);
        }
        this.i();
    }

    public String a() {
        return this.F.toString().replace("\n", "");
    }

    public void a(String string) {
        this.F = string != null && string.length() != 0 ? ant.a.a(string) : new ant.a();
    }

    @Override
    public void a(int n2, String string) {
        float f2 = 0.0f;
        try {
            f2 = Float.parseFloat(string);
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        \u2603 = 0.0f;
        switch (n2) {
            case 139: {
                \u2603 = this.F.b = ns.a(f2, 1.0f, 6000.0f);
                break;
            }
            case 140: {
                \u2603 = this.F.c = ns.a(f2, 1.0f, 6000.0f);
                break;
            }
            case 142: {
                \u2603 = this.F.d = ns.a(f2, 1.0f, 5000.0f);
                break;
            }
            case 143: {
                \u2603 = this.F.e = ns.a(f2, 1.0f, 5000.0f);
                break;
            }
            case 135: {
                \u2603 = this.F.f = ns.a(f2, 1.0f, 2000.0f);
                break;
            }
            case 136: {
                \u2603 = this.F.g = ns.a(f2, 1.0f, 2000.0f);
                break;
            }
            case 137: {
                \u2603 = this.F.h = ns.a(f2, 0.01f, 20.0f);
                break;
            }
            case 132: {
                \u2603 = this.F.i = ns.a(f2, 1.0f, 5000.0f);
                break;
            }
            case 133: {
                \u2603 = this.F.j = ns.a(f2, 1.0f, 5000.0f);
                break;
            }
            case 134: {
                \u2603 = this.F.k = ns.a(f2, 1.0f, 5000.0f);
                break;
            }
            case 138: {
                \u2603 = this.F.l = ns.a(f2, 1.0f, 25.0f);
                break;
            }
            case 141: {
                \u2603 = this.F.m = ns.a(f2, 0.01f, 50.0f);
                break;
            }
            case 144: {
                \u2603 = this.F.n = ns.a(f2, 1.0f, 20.0f);
                break;
            }
            case 145: {
                \u2603 = this.F.o = ns.a(f2, 0.0f, 20.0f);
                break;
            }
            case 146: {
                \u2603 = this.F.p = ns.a(f2, 1.0f, 20.0f);
                break;
            }
            case 147: {
                \u2603 = this.F.q = ns.a(f2, 0.0f, 20.0f);
            }
        }
        if (\u2603 != f2 && f2 != 0.0f) {
            ((avw)this.r.d(n2)).a(this.b(n2, \u2603));
        }
        ((avx)this.r.d(n2 - 132 + 100)).a(\u2603, false);
        if (!this.F.equals(this.E)) {
            this.a(true);
        }
    }

    private void a(boolean bl2) {
        this.A = bl2;
        this.u.l = bl2;
    }

    @Override
    public String a(int n2, String string, float f2) {
        return string + ": " + this.b(n2, f2);
    }

    private String b(int n2, float f22) {
        switch (n2) {
            case 100: 
            case 101: 
            case 102: 
            case 103: 
            case 104: 
            case 107: 
            case 108: 
            case 110: 
            case 111: 
            case 132: 
            case 133: 
            case 134: 
            case 135: 
            case 136: 
            case 139: 
            case 140: 
            case 142: 
            case 143: {
                return String.format("%5.3f", Float.valueOf(f22));
            }
            case 105: 
            case 106: 
            case 109: 
            case 112: 
            case 113: 
            case 114: 
            case 115: 
            case 137: 
            case 138: 
            case 141: 
            case 144: 
            case 145: 
            case 146: 
            case 147: {
                return String.format("%2.3f", Float.valueOf(f22));
            }
            case 162: {
                float f22;
                if (f22 < 0.0f) {
                    return bnq.a("gui.all", new Object[0]);
                }
                if ((int)f22 >= ady.x.az) {
                    ady ady2 = ady.n()[(int)f22 + 2];
                    return ady2 != null ? ady2.ah : "?";
                }
                ady \u26032 = ady.n()[(int)f22];
                return \u26032 != null ? \u26032.ah : "?";
            }
        }
        return String.format("%d", (int)f22);
    }

    @Override
    public void a(int n2, boolean bl2) {
        switch (n2) {
            case 148: {
                this.F.s = bl2;
                break;
            }
            case 149: {
                this.F.t = bl2;
                break;
            }
            case 150: {
                this.F.v = bl2;
                break;
            }
            case 151: {
                this.F.w = bl2;
                break;
            }
            case 152: {
                this.F.x = bl2;
                break;
            }
            case 153: {
                this.F.y = bl2;
                break;
            }
            case 154: {
                this.F.A = bl2;
                break;
            }
            case 210: {
                this.F.z = bl2;
                break;
            }
            case 155: {
                this.F.B = bl2;
                break;
            }
            case 156: {
                this.F.D = bl2;
                break;
            }
            case 161: {
                this.F.F = bl2;
            }
        }
        if (!this.F.equals(this.E)) {
            this.a(true);
        }
    }

    @Override
    public void a(int n2, float f2) {
        switch (n2) {
            case 107: {
                this.F.b = f2;
                break;
            }
            case 108: {
                this.F.c = f2;
                break;
            }
            case 110: {
                this.F.d = f2;
                break;
            }
            case 111: {
                this.F.e = f2;
                break;
            }
            case 103: {
                this.F.f = f2;
                break;
            }
            case 104: {
                this.F.g = f2;
                break;
            }
            case 105: {
                this.F.h = f2;
                break;
            }
            case 100: {
                this.F.i = f2;
                break;
            }
            case 101: {
                this.F.j = f2;
                break;
            }
            case 102: {
                this.F.k = f2;
                break;
            }
            case 106: {
                this.F.l = f2;
                break;
            }
            case 109: {
                this.F.m = f2;
                break;
            }
            case 112: {
                this.F.n = f2;
                break;
            }
            case 113: {
                this.F.o = f2;
                break;
            }
            case 114: {
                this.F.p = f2;
                break;
            }
            case 115: {
                this.F.q = f2;
                break;
            }
            case 157: {
                this.F.u = (int)f2;
                break;
            }
            case 158: {
                this.F.C = (int)f2;
                break;
            }
            case 159: {
                this.F.E = (int)f2;
                break;
            }
            case 160: {
                this.F.r = (int)f2;
                break;
            }
            case 162: {
                this.F.G = (int)f2;
                break;
            }
            case 163: {
                this.F.H = (int)f2;
                break;
            }
            case 164: {
                this.F.I = (int)f2;
                break;
            }
            case 166: {
                this.F.K = (int)f2;
                break;
            }
            case 165: {
                this.F.J = (int)f2;
                break;
            }
            case 167: {
                this.F.L = (int)f2;
                break;
            }
            case 168: {
                this.F.M = (int)f2;
                break;
            }
            case 170: {
                this.F.O = (int)f2;
                break;
            }
            case 169: {
                this.F.N = (int)f2;
                break;
            }
            case 171: {
                this.F.P = (int)f2;
                break;
            }
            case 172: {
                this.F.Q = (int)f2;
                break;
            }
            case 174: {
                this.F.S = (int)f2;
                break;
            }
            case 173: {
                this.F.R = (int)f2;
                break;
            }
            case 175: {
                this.F.T = (int)f2;
                break;
            }
            case 176: {
                this.F.U = (int)f2;
                break;
            }
            case 178: {
                this.F.W = (int)f2;
                break;
            }
            case 177: {
                this.F.V = (int)f2;
                break;
            }
            case 179: {
                this.F.X = (int)f2;
                break;
            }
            case 180: {
                this.F.Y = (int)f2;
                break;
            }
            case 182: {
                this.F.aa = (int)f2;
                break;
            }
            case 181: {
                this.F.Z = (int)f2;
                break;
            }
            case 183: {
                this.F.ab = (int)f2;
                break;
            }
            case 184: {
                this.F.ac = (int)f2;
                break;
            }
            case 186: {
                this.F.ae = (int)f2;
                break;
            }
            case 185: {
                this.F.ad = (int)f2;
                break;
            }
            case 187: {
                this.F.af = (int)f2;
                break;
            }
            case 189: {
                this.F.ag = (int)f2;
                break;
            }
            case 191: {
                this.F.ai = (int)f2;
                break;
            }
            case 190: {
                this.F.ah = (int)f2;
                break;
            }
            case 192: {
                this.F.aj = (int)f2;
                break;
            }
            case 193: {
                this.F.ak = (int)f2;
                break;
            }
            case 195: {
                this.F.am = (int)f2;
                break;
            }
            case 194: {
                this.F.al = (int)f2;
                break;
            }
            case 196: {
                this.F.an = (int)f2;
                break;
            }
            case 197: {
                this.F.ao = (int)f2;
                break;
            }
            case 199: {
                this.F.aq = (int)f2;
                break;
            }
            case 198: {
                this.F.ap = (int)f2;
                break;
            }
            case 200: {
                this.F.ar = (int)f2;
                break;
            }
            case 201: {
                this.F.as = (int)f2;
                break;
            }
            case 203: {
                this.F.au = (int)f2;
                break;
            }
            case 202: {
                this.F.at = (int)f2;
                break;
            }
            case 204: {
                this.F.av = (int)f2;
                break;
            }
            case 205: {
                this.F.aw = (int)f2;
                break;
            }
            case 207: {
                this.F.ay = (int)f2;
                break;
            }
            case 206: {
                this.F.ax = (int)f2;
                break;
            }
            case 208: {
                this.F.az = (int)f2;
                break;
            }
            case 209: {
                this.F.aA = (int)f2;
            }
        }
        if (n2 >= 100 && n2 < 116 && (\u2603 = this.r.d(n2 - 100 + 132)) != null) {
            ((avw)\u2603).a(this.b(n2, f2));
        }
        if (!this.F.equals(this.E)) {
            this.a(true);
        }
    }

    @Override
    protected void a(avs avs2) {
        if (!avs2.l) {
            return;
        }
        switch (avs2.k) {
            case 300: {
                this.i.a = this.F.toString();
                this.j.a(this.i);
                break;
            }
            case 305: {
                this.j.a(new axc(this));
                break;
            }
            case 301: {
                for (int i2 = 0; i2 < this.r.b(); ++i2) {
                    avp avp2;
                    avp avp3;
                    awg.d d2 = this.r.e(i2);
                    avp \u26032 = d2.a();
                    if (\u26032 instanceof avs) {
                        avp3 = (avs)\u26032;
                        if (avp3 instanceof avx) {
                            float f2 = ((avx)avp3).d() * (0.75f + this.G.nextFloat() * 0.5f) + (this.G.nextFloat() * 0.1f - 0.05f);
                            ((avx)avp3).a(ns.a(f2, 0.0f, 1.0f));
                        } else if (avp3 instanceof awb) {
                            ((awb)avp3).b(this.G.nextBoolean());
                        }
                    }
                    if (!((avp3 = d2.b()) instanceof avs)) continue;
                    avp2 = avp3;
                    if (avp2 instanceof avx) {
                        float f3 = ((avx)avp2).d() * (0.75f + this.G.nextFloat() * 0.5f) + (this.G.nextFloat() * 0.1f - 0.05f);
                        ((avx)avp2).a(ns.a(f3, 0.0f, 1.0f));
                        continue;
                    }
                    if (!(avp2 instanceof awb)) continue;
                    ((awb)avp2).b(this.G.nextBoolean());
                }
                break;
            }
            case 302: {
                this.r.h();
                this.i();
                break;
            }
            case 303: {
                this.r.i();
                this.i();
                break;
            }
            case 304: {
                if (!this.A) break;
                this.b(304);
                break;
            }
            case 307: {
                this.B = 0;
                this.h();
                break;
            }
            case 306: {
                this.h();
            }
        }
    }

    private void g() {
        this.F.a();
        this.f();
        this.a(false);
    }

    private void b(int n2) {
        this.B = n2;
        this.b(true);
    }

    private void h() {
        switch (this.B) {
            case 300: {
                this.a((awb)this.r.d(300));
                break;
            }
            case 304: {
                this.g();
            }
        }
        this.B = 0;
        this.C = true;
        this.b(false);
    }

    private void b(boolean bl2) {
        this.x.m = bl2;
        this.y.m = bl2;
        this.t.l = !bl2;
        this.s.l = !bl2;
        this.v.l = !bl2;
        this.w.l = !bl2;
        this.u.l = this.A && !bl2;
        this.z.l = !bl2;
        this.r.a(!bl2);
    }

    private void i() {
        this.v.l = this.r.e() != 0;
        this.w.l = this.r.e() != this.r.f() - 1;
        this.f = bnq.a("book.pageIndicator", this.r.e() + 1, this.r.f());
        this.g = this.h[this.r.e()];
        this.t.l = this.r.e() != this.r.f() - 1;
    }

    @Override
    protected void a(char c2, int n2) {
        super.a(c2, n2);
        if (this.B != 0) {
            return;
        }
        switch (n2) {
            case 208: {
                this.a(-1.0f);
                break;
            }
            case 200: {
                this.a(1.0f);
                break;
            }
            default: {
                this.r.a(c2, n2);
            }
        }
    }

    private void a(float f2) {
        avp avp2 = this.r.g();
        if (!(avp2 instanceof avw)) {
            return;
        }
        float \u26032 = f2;
        if (axu.r()) {
            \u26032 *= 0.1f;
            if (axu.q()) {
                \u26032 *= 0.1f;
            }
        } else if (axu.q()) {
            \u26032 *= 10.0f;
            if (axu.s()) {
                \u26032 *= 10.0f;
            }
        }
        if ((\u26033 = Floats.tryParse((\u2603 = (avw)avp2).b())) == null) {
            return;
        }
        Float \u26033 = Float.valueOf(\u26033.floatValue() + \u26032);
        int \u26034 = \u2603.d();
        String \u26035 = this.b(\u2603.d(), \u26033.floatValue());
        \u2603.a(\u26035);
        this.a(\u26034, \u26035);
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        super.a(n2, n3, n4);
        if (this.B != 0 || this.C) {
            return;
        }
        this.r.b(n2, n3, n4);
    }

    @Override
    protected void b(int n2, int n3, int n4) {
        super.b(n2, n3, n4);
        if (this.C) {
            this.C = false;
            return;
        }
        if (this.B != 0) {
            return;
        }
        this.r.c(n2, n3, n4);
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.r.a(n2, n3, f2);
        this.a(this.q, this.a, this.l / 2, 2, 0xFFFFFF);
        this.a(this.q, this.f, this.l / 2, 12, 0xFFFFFF);
        this.a(this.q, this.g, this.l / 2, 22, 0xFFFFFF);
        super.a(n2, n3, f2);
        if (this.B != 0) {
            axd.a(0, 0, this.l, this.m, Integer.MIN_VALUE);
            this.a(this.l / 2 - 91, this.l / 2 + 90, 99, -2039584);
            this.a(this.l / 2 - 91, this.l / 2 + 90, 185, -6250336);
            this.b(this.l / 2 - 91, 99, 185, -2039584);
            this.b(this.l / 2 + 90, 99, 185, -6250336);
            \u2603 = 85.0f;
            \u2603 = 180.0f;
            bfl.f();
            bfl.n();
            bfx bfx2 = bfx.a();
            bfd \u26032 = bfx2.c();
            this.j.P().a(b);
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            float \u26033 = 32.0f;
            \u26032.a(7, bms.i);
            \u26032.b((double)(this.l / 2 - 90), 185.0, 0.0).a(0.0, 2.65625).b(64, 64, 64, 64).d();
            \u26032.b((double)(this.l / 2 + 90), 185.0, 0.0).a(5.625, 2.65625).b(64, 64, 64, 64).d();
            \u26032.b((double)(this.l / 2 + 90), 100.0, 0.0).a(5.625, 0.0).b(64, 64, 64, 64).d();
            \u26032.b((double)(this.l / 2 - 90), 100.0, 0.0).a(0.0, 0.0).b(64, 64, 64, 64).d();
            bfx2.b();
            this.a(this.q, bnq.a("createWorld.customize.custom.confirmTitle", new Object[0]), this.l / 2, 105, 0xFFFFFF);
            this.a(this.q, bnq.a("createWorld.customize.custom.confirm1", new Object[0]), this.l / 2, 125, 0xFFFFFF);
            this.a(this.q, bnq.a("createWorld.customize.custom.confirm2", new Object[0]), this.l / 2, 135, 0xFFFFFF);
            this.x.a(this.j, n2, n3);
            this.y.a(this.j, n2, n3);
        }
    }
}

