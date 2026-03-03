/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class avh {
    private static final Logger aO = LogManager.getLogger();
    private static final Gson aP = new Gson();
    private static final ParameterizedType aQ = new ParameterizedType(){

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{String.class};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    };
    private static final String[] aR = new String[]{"options.guiScale.auto", "options.guiScale.small", "options.guiScale.normal", "options.guiScale.large"};
    private static final String[] aS = new String[]{"options.particles.all", "options.particles.decreased", "options.particles.minimal"};
    private static final String[] aT = new String[]{"options.ao.off", "options.ao.min", "options.ao.max"};
    private static final String[] aU = new String[]{"options.stream.compression.low", "options.stream.compression.medium", "options.stream.compression.high"};
    private static final String[] aV = new String[]{"options.stream.chat.enabled.streaming", "options.stream.chat.enabled.always", "options.stream.chat.enabled.never"};
    private static final String[] aW = new String[]{"options.stream.chat.userFilter.all", "options.stream.chat.userFilter.subs", "options.stream.chat.userFilter.mods"};
    private static final String[] aX = new String[]{"options.stream.mic_toggle.mute", "options.stream.mic_toggle.talk"};
    private static final String[] aY = new String[]{"options.off", "options.graphics.fast", "options.graphics.fancy"};
    public float a = 0.5f;
    public boolean b;
    public int c = -1;
    public boolean d = true;
    public boolean e;
    public boolean f = true;
    public int g = 120;
    public int h = 2;
    public boolean i = true;
    public int j = 2;
    public List<String> k = Lists.newArrayList();
    public List<String> l = Lists.newArrayList();
    public wn.b m = wn.b.a;
    public boolean n = true;
    public boolean o = true;
    public boolean p = true;
    public float q = 1.0f;
    public boolean r = true;
    public boolean s;
    public boolean t = true;
    public boolean u = false;
    public boolean v = true;
    public boolean w = false;
    public boolean x;
    public boolean y;
    public boolean z = true;
    private final Set<wo> aZ = Sets.newHashSet(wo.values());
    public boolean A;
    public int B;
    public int C;
    public boolean D = true;
    public float E = 1.0f;
    public float F = 1.0f;
    public float G = 0.44366196f;
    public float H = 1.0f;
    public boolean I = true;
    public int J = 4;
    private Map<bpg, Float> ba = Maps.newEnumMap(bpg.class);
    public float K = 0.5f;
    public float L = 1.0f;
    public float M = 1.0f;
    public float N = 0.5412844f;
    public float O = 0.31690142f;
    public int P = 1;
    public boolean Q = true;
    public String R = "";
    public int S = 0;
    public int T = 0;
    public int U = 0;
    public boolean V = true;
    public boolean W = true;
    public avb X = new avb("key.forward", 17, "key.categories.movement");
    public avb Y = new avb("key.left", 30, "key.categories.movement");
    public avb Z = new avb("key.back", 31, "key.categories.movement");
    public avb aa = new avb("key.right", 32, "key.categories.movement");
    public avb ab = new avb("key.jump", 57, "key.categories.movement");
    public avb ac = new avb("key.sneak", 42, "key.categories.movement");
    public avb ad = new avb("key.sprint", 29, "key.categories.movement");
    public avb ae = new avb("key.inventory", 18, "key.categories.inventory");
    public avb af = new avb("key.use", -99, "key.categories.gameplay");
    public avb ag = new avb("key.drop", 16, "key.categories.gameplay");
    public avb ah = new avb("key.attack", -100, "key.categories.gameplay");
    public avb ai = new avb("key.pickItem", -98, "key.categories.gameplay");
    public avb aj = new avb("key.chat", 20, "key.categories.multiplayer");
    public avb ak = new avb("key.playerlist", 15, "key.categories.multiplayer");
    public avb al = new avb("key.command", 53, "key.categories.multiplayer");
    public avb am = new avb("key.screenshot", 60, "key.categories.misc");
    public avb an = new avb("key.togglePerspective", 63, "key.categories.misc");
    public avb ao = new avb("key.smoothCamera", 0, "key.categories.misc");
    public avb ap = new avb("key.fullscreen", 87, "key.categories.misc");
    public avb aq = new avb("key.spectatorOutlines", 0, "key.categories.misc");
    public avb ar = new avb("key.streamStartStop", 64, "key.categories.stream");
    public avb as = new avb("key.streamPauseUnpause", 65, "key.categories.stream");
    public avb at = new avb("key.streamCommercial", 0, "key.categories.stream");
    public avb au = new avb("key.streamToggleMic", 0, "key.categories.stream");
    public avb[] av = new avb[]{new avb("key.hotbar.1", 2, "key.categories.inventory"), new avb("key.hotbar.2", 3, "key.categories.inventory"), new avb("key.hotbar.3", 4, "key.categories.inventory"), new avb("key.hotbar.4", 5, "key.categories.inventory"), new avb("key.hotbar.5", 6, "key.categories.inventory"), new avb("key.hotbar.6", 7, "key.categories.inventory"), new avb("key.hotbar.7", 8, "key.categories.inventory"), new avb("key.hotbar.8", 9, "key.categories.inventory"), new avb("key.hotbar.9", 10, "key.categories.inventory")};
    public avb[] aw = ArrayUtils.addAll(new avb[]{this.ah, this.af, this.X, this.Y, this.Z, this.aa, this.ab, this.ac, this.ad, this.ag, this.ae, this.aj, this.ak, this.ai, this.al, this.am, this.an, this.ao, this.ar, this.as, this.at, this.au, this.ap, this.aq}, this.av);
    protected ave ax;
    private File bb;
    public oj ay = oj.c;
    public boolean az;
    public int aA;
    public boolean aB;
    public boolean aC;
    public boolean aD;
    public String aE = "";
    public boolean aF;
    public boolean aG;
    public float aH = 70.0f;
    public float aI;
    public float aJ;
    public int aK;
    public int aL;
    public String aM = "en_US";
    public boolean aN = false;

    public avh(ave ave2, File file) {
        this.ax = ave2;
        this.bb = new File(file, "options.txt");
        if (ave2.U() && Runtime.getRuntime().maxMemory() >= 1000000000L) {
            avh$a.f.a(32.0f);
        } else {
            avh$a.f.a(16.0f);
        }
        this.c = ave2.U() ? 12 : 8;
        this.a();
    }

    public avh() {
    }

    public static String c(int n2) {
        if (n2 < 0) {
            return bnq.a("key.mouseButton", n2 + 101);
        }
        if (n2 < 256) {
            return Keyboard.getKeyName(n2);
        }
        return String.format("%c", Character.valueOf((char)(n2 - 256))).toUpperCase();
    }

    public static boolean a(avb avb2) {
        if (avb2.i() == 0) {
            return false;
        }
        if (avb2.i() < 0) {
            return Mouse.isButtonDown(avb2.i() + 100);
        }
        return Keyboard.isKeyDown(avb2.i());
    }

    public void a(avb avb2, int n2) {
        avb2.b(n2);
        this.b();
    }

    public void a(a a22, float f2) {
        a a22;
        if (a22 == avh$a.b) {
            this.a = f2;
        }
        if (a22 == avh$a.c) {
            this.aH = f2;
        }
        if (a22 == avh$a.d) {
            this.aI = f2;
        }
        if (a22 == avh$a.i) {
            this.g = (int)f2;
        }
        if (a22 == avh$a.s) {
            this.q = f2;
            this.ax.q.d().b();
        }
        if (a22 == avh$a.B) {
            this.H = f2;
            this.ax.q.d().b();
        }
        if (a22 == avh$a.C) {
            this.G = f2;
            this.ax.q.d().b();
        }
        if (a22 == avh$a.A) {
            this.F = f2;
            this.ax.q.d().b();
        }
        if (a22 == avh$a.z) {
            this.E = f2;
            this.ax.q.d().b();
        }
        if (a22 == avh$a.D) {
            int n2 = this.J;
            this.J = (int)f2;
            if ((float)n2 != f2) {
                this.ax.T().a(this.J);
                this.ax.P().a(bmh.g);
                this.ax.T().a(false, this.J > 0);
                this.ax.B();
            }
        }
        if (a22 == avh$a.P) {
            this.v = !this.v;
            this.ax.g.a();
        }
        if (a22 == avh$a.f) {
            this.c = (int)f2;
            this.ax.g.m();
        }
        if (a22 == avh$a.F) {
            this.K = f2;
        }
        if (a22 == avh$a.G) {
            this.L = f2;
            this.ax.Y().p();
        }
        if (a22 == avh$a.H) {
            this.M = f2;
            this.ax.Y().p();
        }
        if (a22 == avh$a.I) {
            this.N = f2;
        }
        if (a22 == avh$a.J) {
            this.O = f2;
        }
    }

    public void a(a a2, int n2) {
        if (a2 == avh$a.a) {
            boolean bl2 = this.b = !this.b;
        }
        if (a2 == avh$a.n) {
            this.aK = this.aK + n2 & 3;
        }
        if (a2 == avh$a.o) {
            this.aL = (this.aL + n2) % 3;
        }
        if (a2 == avh$a.g) {
            boolean bl3 = this.d = !this.d;
        }
        if (a2 == avh$a.k) {
            this.h = (this.h + n2) % 3;
        }
        if (a2 == avh$a.E) {
            this.aN = !this.aN;
            this.ax.k.a(this.ax.S().a() || this.aN);
        }
        if (a2 == avh$a.j) {
            boolean bl4 = this.f = !this.f;
        }
        if (a2 == avh$a.h) {
            this.e = !this.e;
            this.ax.e();
        }
        if (a2 == avh$a.l) {
            this.i = !this.i;
            this.ax.g.a();
        }
        if (a2 == avh$a.m) {
            this.j = (this.j + n2) % 3;
            this.ax.g.a();
        }
        if (a2 == avh$a.p) {
            this.m = wn.b.a((this.m.a() + n2) % 3);
        }
        if (a2 == avh$a.K) {
            this.P = (this.P + n2) % 3;
        }
        if (a2 == avh$a.L) {
            boolean bl5 = this.Q = !this.Q;
        }
        if (a2 == avh$a.M) {
            this.S = (this.S + n2) % 3;
        }
        if (a2 == avh$a.N) {
            this.T = (this.T + n2) % 3;
        }
        if (a2 == avh$a.O) {
            this.U = (this.U + n2) % 2;
        }
        if (a2 == avh$a.q) {
            boolean bl6 = this.n = !this.n;
        }
        if (a2 == avh$a.r) {
            boolean bl7 = this.o = !this.o;
        }
        if (a2 == avh$a.t) {
            boolean bl8 = this.p = !this.p;
        }
        if (a2 == avh$a.u) {
            boolean bl9 = this.r = !this.r;
        }
        if (a2 == avh$a.y) {
            boolean bl10 = this.A = !this.A;
        }
        if (a2 == avh$a.v) {
            boolean bl11 = this.s = !this.s;
            if (this.ax.K() != this.s) {
                this.ax.q();
            }
        }
        if (a2 == avh$a.w) {
            this.t = !this.t;
            Display.setVSyncEnabled(this.t);
        }
        if (a2 == avh$a.x) {
            this.u = !this.u;
            this.ax.g.a();
        }
        if (a2 == avh$a.P) {
            this.v = !this.v;
            this.ax.g.a();
        }
        if (a2 == avh$a.Q) {
            boolean bl12 = this.w = !this.w;
        }
        if (a2 == avh$a.R) {
            this.W = !this.W;
        }
        this.b();
    }

    public float a(a a2) {
        if (a2 == avh$a.c) {
            return this.aH;
        }
        if (a2 == avh$a.d) {
            return this.aI;
        }
        if (a2 == avh$a.e) {
            return this.aJ;
        }
        if (a2 == avh$a.b) {
            return this.a;
        }
        if (a2 == avh$a.s) {
            return this.q;
        }
        if (a2 == avh$a.B) {
            return this.H;
        }
        if (a2 == avh$a.C) {
            return this.G;
        }
        if (a2 == avh$a.z) {
            return this.E;
        }
        if (a2 == avh$a.A) {
            return this.F;
        }
        if (a2 == avh$a.i) {
            return this.g;
        }
        if (a2 == avh$a.D) {
            return this.J;
        }
        if (a2 == avh$a.f) {
            return this.c;
        }
        if (a2 == avh$a.F) {
            return this.K;
        }
        if (a2 == avh$a.G) {
            return this.L;
        }
        if (a2 == avh$a.H) {
            return this.M;
        }
        if (a2 == avh$a.I) {
            return this.N;
        }
        if (a2 == avh$a.J) {
            return this.O;
        }
        return 0.0f;
    }

    public boolean b(a a2) {
        switch (a2) {
            case a: {
                return this.b;
            }
            case g: {
                return this.d;
            }
            case h: {
                return this.e;
            }
            case j: {
                return this.f;
            }
            case q: {
                return this.n;
            }
            case r: {
                return this.o;
            }
            case t: {
                return this.p;
            }
            case u: {
                return this.r;
            }
            case v: {
                return this.s;
            }
            case w: {
                return this.t;
            }
            case x: {
                return this.u;
            }
            case y: {
                return this.A;
            }
            case L: {
                return this.Q;
            }
            case E: {
                return this.aN;
            }
            case P: {
                return this.v;
            }
            case Q: {
                return this.w;
            }
            case R: {
                return this.W;
            }
        }
        return false;
    }

    private static String a(String[] stringArray, int n2) {
        if (n2 < 0 || n2 >= stringArray.length) {
            n2 = 0;
        }
        return bnq.a(stringArray[n2], new Object[0]);
    }

    public String c(a a22) {
        a a22;
        String string = bnq.a(a22.d(), new Object[0]) + ": ";
        if (a22.a()) {
            float f2 = this.a(a22);
            \u2603 = a22.c(f2);
            if (a22 == avh$a.b) {
                if (\u2603 == 0.0f) {
                    return string + bnq.a("options.sensitivity.min", new Object[0]);
                }
                if (\u2603 == 1.0f) {
                    return string + bnq.a("options.sensitivity.max", new Object[0]);
                }
                return string + (int)(\u2603 * 200.0f) + "%";
            }
            if (a22 == avh$a.c) {
                if (f2 == 70.0f) {
                    return string + bnq.a("options.fov.min", new Object[0]);
                }
                if (f2 == 110.0f) {
                    return string + bnq.a("options.fov.max", new Object[0]);
                }
                return string + (int)f2;
            }
            if (a22 == avh$a.i) {
                if (f2 == a22.X) {
                    return string + bnq.a("options.framerateLimit.max", new Object[0]);
                }
                return string + (int)f2 + " fps";
            }
            if (a22 == avh$a.k) {
                if (f2 == a22.W) {
                    return string + bnq.a("options.cloudHeight.min", new Object[0]);
                }
                return string + ((int)f2 + 128);
            }
            if (a22 == avh$a.d) {
                if (\u2603 == 0.0f) {
                    return string + bnq.a("options.gamma.min", new Object[0]);
                }
                if (\u2603 == 1.0f) {
                    return string + bnq.a("options.gamma.max", new Object[0]);
                }
                return string + "+" + (int)(\u2603 * 100.0f) + "%";
            }
            if (a22 == avh$a.e) {
                return string + (int)(\u2603 * 400.0f) + "%";
            }
            if (a22 == avh$a.s) {
                return string + (int)(\u2603 * 90.0f + 10.0f) + "%";
            }
            if (a22 == avh$a.C) {
                return string + avt.b(\u2603) + "px";
            }
            if (a22 == avh$a.B) {
                return string + avt.b(\u2603) + "px";
            }
            if (a22 == avh$a.A) {
                return string + avt.a(\u2603) + "px";
            }
            if (a22 == avh$a.f) {
                return string + (int)f2 + " chunks";
            }
            if (a22 == avh$a.D) {
                if (f2 == 0.0f) {
                    return string + bnq.a("options.off", new Object[0]);
                }
                return string + (int)f2;
            }
            if (a22 == avh$a.J) {
                return string + bqn.a(\u2603) + " fps";
            }
            if (a22 == avh$a.I) {
                return string + bqn.b(\u2603) + " Kbps";
            }
            if (a22 == avh$a.F) {
                return string + String.format("%.3f bpp", Float.valueOf(bqn.c(\u2603)));
            }
            if (\u2603 == 0.0f) {
                return string + bnq.a("options.off", new Object[0]);
            }
            return string + (int)(\u2603 * 100.0f) + "%";
        }
        if (a22.b()) {
            boolean bl2 = this.b(a22);
            if (bl2) {
                return string + bnq.a("options.on", new Object[0]);
            }
            return string + bnq.a("options.off", new Object[0]);
        }
        if (a22 == avh$a.n) {
            return string + avh.a(aR, this.aK);
        }
        if (a22 == avh$a.p) {
            return string + bnq.a(this.m.b(), new Object[0]);
        }
        if (a22 == avh$a.o) {
            return string + avh.a(aS, this.aL);
        }
        if (a22 == avh$a.m) {
            return string + avh.a(aT, this.j);
        }
        if (a22 == avh$a.K) {
            return string + avh.a(aU, this.P);
        }
        if (a22 == avh$a.M) {
            return string + avh.a(aV, this.S);
        }
        if (a22 == avh$a.N) {
            return string + avh.a(aW, this.T);
        }
        if (a22 == avh$a.O) {
            return string + avh.a(aX, this.U);
        }
        if (a22 == avh$a.k) {
            return string + avh.a(aY, this.h);
        }
        if (a22 == avh$a.l) {
            if (this.i) {
                return string + bnq.a("options.graphics.fancy", new Object[0]);
            }
            String string2 = "options.graphics.fast";
            return string + bnq.a("options.graphics.fast", new Object[0]);
        }
        return string;
    }

    public void a() {
        try {
            if (!this.bb.exists()) {
                return;
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.bb));
            String \u26032 = "";
            this.ba.clear();
            while ((\u26032 = bufferedReader.readLine()) != null) {
                try {
                    String[] stringArray = \u26032.split(":");
                    if (stringArray[0].equals("mouseSensitivity")) {
                        this.a = this.a(stringArray[1]);
                    }
                    if (stringArray[0].equals("fov")) {
                        this.aH = this.a(stringArray[1]) * 40.0f + 70.0f;
                    }
                    if (stringArray[0].equals("gamma")) {
                        this.aI = this.a(stringArray[1]);
                    }
                    if (stringArray[0].equals("saturation")) {
                        this.aJ = this.a(stringArray[1]);
                    }
                    if (stringArray[0].equals("invertYMouse")) {
                        this.b = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("renderDistance")) {
                        this.c = Integer.parseInt(stringArray[1]);
                    }
                    if (stringArray[0].equals("guiScale")) {
                        this.aK = Integer.parseInt(stringArray[1]);
                    }
                    if (stringArray[0].equals("particles")) {
                        this.aL = Integer.parseInt(stringArray[1]);
                    }
                    if (stringArray[0].equals("bobView")) {
                        this.d = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("anaglyph3d")) {
                        this.e = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("maxFps")) {
                        this.g = Integer.parseInt(stringArray[1]);
                    }
                    if (stringArray[0].equals("fboEnable")) {
                        this.f = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("difficulty")) {
                        this.ay = oj.a(Integer.parseInt(stringArray[1]));
                    }
                    if (stringArray[0].equals("fancyGraphics")) {
                        this.i = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("ao")) {
                        this.j = stringArray[1].equals("true") ? 2 : (stringArray[1].equals("false") ? 0 : Integer.parseInt(stringArray[1]));
                    }
                    if (stringArray[0].equals("renderClouds")) {
                        if (stringArray[1].equals("true")) {
                            this.h = 2;
                        } else if (stringArray[1].equals("false")) {
                            this.h = 0;
                        } else if (stringArray[1].equals("fast")) {
                            this.h = 1;
                        }
                    }
                    if (stringArray[0].equals("resourcePacks")) {
                        this.k = (List)aP.fromJson(\u26032.substring(\u26032.indexOf(58) + 1), (Type)aQ);
                        if (this.k == null) {
                            this.k = Lists.newArrayList();
                        }
                    }
                    if (stringArray[0].equals("incompatibleResourcePacks")) {
                        this.l = (List)aP.fromJson(\u26032.substring(\u26032.indexOf(58) + 1), (Type)aQ);
                        if (this.l == null) {
                            this.l = Lists.newArrayList();
                        }
                    }
                    if (stringArray[0].equals("lastServer") && stringArray.length >= 2) {
                        this.aE = \u26032.substring(\u26032.indexOf(58) + 1);
                    }
                    if (stringArray[0].equals("lang") && stringArray.length >= 2) {
                        this.aM = stringArray[1];
                    }
                    if (stringArray[0].equals("chatVisibility")) {
                        this.m = wn.b.a(Integer.parseInt(stringArray[1]));
                    }
                    if (stringArray[0].equals("chatColors")) {
                        this.n = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("chatLinks")) {
                        this.o = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("chatLinksPrompt")) {
                        this.p = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("chatOpacity")) {
                        this.q = this.a(stringArray[1]);
                    }
                    if (stringArray[0].equals("snooperEnabled")) {
                        this.r = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("fullscreen")) {
                        this.s = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("enableVsync")) {
                        this.t = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("useVbo")) {
                        this.u = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("hideServerAddress")) {
                        this.x = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("advancedItemTooltips")) {
                        this.y = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("pauseOnLostFocus")) {
                        this.z = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("touchscreen")) {
                        this.A = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("overrideHeight")) {
                        this.C = Integer.parseInt(stringArray[1]);
                    }
                    if (stringArray[0].equals("overrideWidth")) {
                        this.B = Integer.parseInt(stringArray[1]);
                    }
                    if (stringArray[0].equals("heldItemTooltips")) {
                        this.D = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("chatHeightFocused")) {
                        this.H = this.a(stringArray[1]);
                    }
                    if (stringArray[0].equals("chatHeightUnfocused")) {
                        this.G = this.a(stringArray[1]);
                    }
                    if (stringArray[0].equals("chatScale")) {
                        this.E = this.a(stringArray[1]);
                    }
                    if (stringArray[0].equals("chatWidth")) {
                        this.F = this.a(stringArray[1]);
                    }
                    if (stringArray[0].equals("showInventoryAchievementHint")) {
                        this.I = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("mipmapLevels")) {
                        this.J = Integer.parseInt(stringArray[1]);
                    }
                    if (stringArray[0].equals("streamBytesPerPixel")) {
                        this.K = this.a(stringArray[1]);
                    }
                    if (stringArray[0].equals("streamMicVolume")) {
                        this.L = this.a(stringArray[1]);
                    }
                    if (stringArray[0].equals("streamSystemVolume")) {
                        this.M = this.a(stringArray[1]);
                    }
                    if (stringArray[0].equals("streamKbps")) {
                        this.N = this.a(stringArray[1]);
                    }
                    if (stringArray[0].equals("streamFps")) {
                        this.O = this.a(stringArray[1]);
                    }
                    if (stringArray[0].equals("streamCompression")) {
                        this.P = Integer.parseInt(stringArray[1]);
                    }
                    if (stringArray[0].equals("streamSendMetadata")) {
                        this.Q = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("streamPreferredServer") && stringArray.length >= 2) {
                        this.R = \u26032.substring(\u26032.indexOf(58) + 1);
                    }
                    if (stringArray[0].equals("streamChatEnabled")) {
                        this.S = Integer.parseInt(stringArray[1]);
                    }
                    if (stringArray[0].equals("streamChatUserFilter")) {
                        this.T = Integer.parseInt(stringArray[1]);
                    }
                    if (stringArray[0].equals("streamMicToggleBehavior")) {
                        this.U = Integer.parseInt(stringArray[1]);
                    }
                    if (stringArray[0].equals("forceUnicodeFont")) {
                        this.aN = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("allowBlockAlternatives")) {
                        this.v = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("reducedDebugInfo")) {
                        this.w = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("useNativeTransport")) {
                        this.V = stringArray[1].equals("true");
                    }
                    if (stringArray[0].equals("entityShadows")) {
                        this.W = stringArray[1].equals("true");
                    }
                    for (avb avb2 : this.aw) {
                        if (!stringArray[0].equals("key_" + avb2.g())) continue;
                        avb2.b(Integer.parseInt(stringArray[1]));
                    }
                    for (bpg bpg2 : bpg.values()) {
                        if (!stringArray[0].equals("soundCategory_" + bpg2.a())) continue;
                        this.ba.put(bpg2, Float.valueOf(this.a(stringArray[1])));
                    }
                    for (wo wo2 : wo.values()) {
                        if (!stringArray[0].equals("modelPart_" + wo2.c())) continue;
                        this.a(wo2, stringArray[1].equals("true"));
                    }
                }
                catch (Exception exception) {
                    aO.warn("Skipping bad option: " + \u26032);
                }
            }
            avb.b();
            bufferedReader.close();
        }
        catch (Exception exception) {
            aO.error("Failed to load options", (Throwable)exception);
        }
    }

    private float a(String string) {
        if (string.equals("true")) {
            return 1.0f;
        }
        if (string.equals("false")) {
            return 0.0f;
        }
        return Float.parseFloat(string);
    }

    public void b() {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(this.bb));
            printWriter.println("invertYMouse:" + this.b);
            printWriter.println("mouseSensitivity:" + this.a);
            printWriter.println("fov:" + (this.aH - 70.0f) / 40.0f);
            printWriter.println("gamma:" + this.aI);
            printWriter.println("saturation:" + this.aJ);
            printWriter.println("renderDistance:" + this.c);
            printWriter.println("guiScale:" + this.aK);
            printWriter.println("particles:" + this.aL);
            printWriter.println("bobView:" + this.d);
            printWriter.println("anaglyph3d:" + this.e);
            printWriter.println("maxFps:" + this.g);
            printWriter.println("fboEnable:" + this.f);
            printWriter.println("difficulty:" + this.ay.a());
            printWriter.println("fancyGraphics:" + this.i);
            printWriter.println("ao:" + this.j);
            switch (this.h) {
                case 2: {
                    printWriter.println("renderClouds:true");
                    break;
                }
                case 1: {
                    printWriter.println("renderClouds:fast");
                    break;
                }
                case 0: {
                    printWriter.println("renderClouds:false");
                }
            }
            printWriter.println("resourcePacks:" + aP.toJson(this.k));
            printWriter.println("incompatibleResourcePacks:" + aP.toJson(this.l));
            printWriter.println("lastServer:" + this.aE);
            printWriter.println("lang:" + this.aM);
            printWriter.println("chatVisibility:" + this.m.a());
            printWriter.println("chatColors:" + this.n);
            printWriter.println("chatLinks:" + this.o);
            printWriter.println("chatLinksPrompt:" + this.p);
            printWriter.println("chatOpacity:" + this.q);
            printWriter.println("snooperEnabled:" + this.r);
            printWriter.println("fullscreen:" + this.s);
            printWriter.println("enableVsync:" + this.t);
            printWriter.println("useVbo:" + this.u);
            printWriter.println("hideServerAddress:" + this.x);
            printWriter.println("advancedItemTooltips:" + this.y);
            printWriter.println("pauseOnLostFocus:" + this.z);
            printWriter.println("touchscreen:" + this.A);
            printWriter.println("overrideWidth:" + this.B);
            printWriter.println("overrideHeight:" + this.C);
            printWriter.println("heldItemTooltips:" + this.D);
            printWriter.println("chatHeightFocused:" + this.H);
            printWriter.println("chatHeightUnfocused:" + this.G);
            printWriter.println("chatScale:" + this.E);
            printWriter.println("chatWidth:" + this.F);
            printWriter.println("showInventoryAchievementHint:" + this.I);
            printWriter.println("mipmapLevels:" + this.J);
            printWriter.println("streamBytesPerPixel:" + this.K);
            printWriter.println("streamMicVolume:" + this.L);
            printWriter.println("streamSystemVolume:" + this.M);
            printWriter.println("streamKbps:" + this.N);
            printWriter.println("streamFps:" + this.O);
            printWriter.println("streamCompression:" + this.P);
            printWriter.println("streamSendMetadata:" + this.Q);
            printWriter.println("streamPreferredServer:" + this.R);
            printWriter.println("streamChatEnabled:" + this.S);
            printWriter.println("streamChatUserFilter:" + this.T);
            printWriter.println("streamMicToggleBehavior:" + this.U);
            printWriter.println("forceUnicodeFont:" + this.aN);
            printWriter.println("allowBlockAlternatives:" + this.v);
            printWriter.println("reducedDebugInfo:" + this.w);
            printWriter.println("useNativeTransport:" + this.V);
            printWriter.println("entityShadows:" + this.W);
            for (avb avb2 : this.aw) {
                printWriter.println("key_" + avb2.g() + ":" + avb2.i());
            }
            for (bpg bpg2 : bpg.values()) {
                printWriter.println("soundCategory_" + bpg2.a() + ":" + this.a(bpg2));
            }
            for (wo wo2 : wo.values()) {
                printWriter.println("modelPart_" + wo2.c() + ":" + this.aZ.contains((Object)wo2));
            }
            printWriter.close();
        }
        catch (Exception exception) {
            aO.error("Failed to save options", (Throwable)exception);
        }
        this.c();
    }

    public float a(bpg bpg2) {
        if (this.ba.containsKey((Object)bpg2)) {
            return this.ba.get((Object)bpg2).floatValue();
        }
        return 1.0f;
    }

    public void a(bpg bpg2, float f2) {
        this.ax.W().a(bpg2, f2);
        this.ba.put(bpg2, Float.valueOf(f2));
    }

    public void c() {
        if (this.ax.h != null) {
            int n2 = 0;
            for (wo wo2 : this.aZ) {
                n2 |= wo2.a();
            }
            this.ax.h.a.a(new ih(this.aM, this.c, this.m, this.n, n2));
        }
    }

    public Set<wo> d() {
        return ImmutableSet.copyOf(this.aZ);
    }

    public void a(wo wo2, boolean bl2) {
        if (bl2) {
            this.aZ.add(wo2);
        } else {
            this.aZ.remove((Object)wo2);
        }
        this.c();
    }

    public void a(wo wo2) {
        if (!this.d().contains((Object)wo2)) {
            this.aZ.add(wo2);
        } else {
            this.aZ.remove((Object)wo2);
        }
        this.c();
    }

    public int e() {
        if (this.c >= 4) {
            return this.h;
        }
        return 0;
    }

    public boolean f() {
        return this.V;
    }

    public static enum a {
        a("options.invertMouse", false, true),
        b("options.sensitivity", true, false),
        c("options.fov", true, false, 30.0f, 110.0f, 1.0f),
        d("options.gamma", true, false),
        e("options.saturation", true, false),
        f("options.renderDistance", true, false, 2.0f, 16.0f, 1.0f),
        g("options.viewBobbing", false, true),
        h("options.anaglyph", false, true),
        i("options.framerateLimit", true, false, 10.0f, 260.0f, 10.0f),
        j("options.fboEnable", false, true),
        k("options.renderClouds", false, false),
        l("options.graphics", false, false),
        m("options.ao", false, false),
        n("options.guiScale", false, false),
        o("options.particles", false, false),
        p("options.chat.visibility", false, false),
        q("options.chat.color", false, true),
        r("options.chat.links", false, true),
        s("options.chat.opacity", true, false),
        t("options.chat.links.prompt", false, true),
        u("options.snooper", false, true),
        v("options.fullscreen", false, true),
        w("options.vsync", false, true),
        x("options.vbo", false, true),
        y("options.touchscreen", false, true),
        z("options.chat.scale", true, false),
        A("options.chat.width", true, false),
        B("options.chat.height.focused", true, false),
        C("options.chat.height.unfocused", true, false),
        D("options.mipmapLevels", true, false, 0.0f, 4.0f, 1.0f),
        E("options.forceUnicodeFont", false, true),
        F("options.stream.bytesPerPixel", true, false),
        G("options.stream.micVolumne", true, false),
        H("options.stream.systemVolume", true, false),
        I("options.stream.kbps", true, false),
        J("options.stream.fps", true, false),
        K("options.stream.compression", false, false),
        L("options.stream.sendMetadata", false, true),
        M("options.stream.chat.enabled", false, false),
        N("options.stream.chat.userFilter", false, false),
        O("options.stream.micToggleBehavior", false, false),
        P("options.blockAlternatives", false, true),
        Q("options.reducedDebugInfo", false, true),
        R("options.entityShadows", false, true);

        private final boolean S;
        private final boolean T;
        private final String U;
        private final float V;
        private float W;
        private float X;

        public static a a(int n2) {
            for (a a2 : avh$a.values()) {
                if (a2.c() != n2) continue;
                return a2;
            }
            return null;
        }

        private a(String string2, boolean bl2, boolean bl3) {
            this(string2, bl2, bl3, 0.0f, 1.0f, 0.0f);
        }

        private a(String string2, boolean bl2, boolean bl3, float f2, float f3, float f4) {
            this.U = string2;
            this.S = bl2;
            this.T = bl3;
            this.W = f2;
            this.X = f3;
            this.V = f4;
        }

        public boolean a() {
            return this.S;
        }

        public boolean b() {
            return this.T;
        }

        public int c() {
            return this.ordinal();
        }

        public String d() {
            return this.U;
        }

        public float f() {
            return this.X;
        }

        public void a(float f2) {
            this.X = f2;
        }

        public float c(float f2) {
            return ns.a((this.e(f2) - this.W) / (this.X - this.W), 0.0f, 1.0f);
        }

        public float d(float f2) {
            return this.e(this.W + (this.X - this.W) * ns.a(f2, 0.0f, 1.0f));
        }

        public float e(float f2) {
            f2 = this.f(f2);
            return ns.a(f2, this.W, this.X);
        }

        protected float f(float f2) {
            if (this.V > 0.0f) {
                f2 = this.V * (float)Math.round(f2 / this.V);
            }
            return f2;
        }
    }
}

