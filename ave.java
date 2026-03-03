/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import javax.imageio.ImageIO;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.OpenGLException;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.glu.GLU;

public class ave
implements od,
os {
    private static final Logger K = LogManager.getLogger();
    private static final jy L = new jy("textures/gui/title/mojang.png");
    public static final boolean a = g.a() == g.a.d;
    public static byte[] b = new byte[0xA00000];
    private static final List<DisplayMode> M = Lists.newArrayList(new DisplayMode(2560, 1600), new DisplayMode(2880, 1800));
    private final File N;
    private final PropertyMap O;
    private final PropertyMap P;
    private bde Q;
    private bmj R;
    private static ave S;
    public bda c;
    private boolean T;
    private boolean U = true;
    private boolean V;
    private b W;
    public int d;
    public int e;
    private boolean X = false;
    private avl Y = new avl(20.0f);
    private or Z = new or("client", this, MinecraftServer.az());
    public bdb f;
    public bfr g;
    private biu aa;
    private bjh ab;
    private bfn ac;
    public bew h;
    private pk ad;
    public pk i;
    public bec j;
    private final avm ae;
    private boolean af;
    public avn k;
    public avn l;
    public axu m;
    public avi n;
    public bfk o;
    private int ag;
    private int ah;
    private int ai;
    private bpo aj;
    public ayd p;
    public avo q;
    public boolean r;
    public auh s;
    public avh t;
    public avf u;
    public final File v;
    private final File ak;
    private final String al;
    private final Proxy am;
    private atr an;
    private static int ao;
    private int ap;
    private String aq;
    private int ar;
    public boolean w;
    long x = ave.J();
    private int as;
    public final nh y = new nh();
    long z = System.nanoTime();
    private final boolean at;
    private final boolean au;
    private ek av;
    private boolean aw;
    public final nt A = new nt();
    private long ax = -1L;
    private bng ay;
    private final bny az = new bny();
    private final List<bnk> aA = Lists.newArrayList();
    private final bna aB;
    private bnm aC;
    private bns aD;
    private bqm aE;
    private bfw aF;
    private bmh aG;
    private bpz aH;
    private bpv aI;
    private jy aJ;
    private final MinecraftSessionService aK;
    private bnp aL;
    private final Queue<FutureTask<?>> aM = Queues.newArrayDeque();
    private long aN = 0L;
    private final Thread aO = Thread.currentThread();
    private bou aP;
    private bgd aQ;
    volatile boolean B = true;
    public String C = "";
    public boolean D = false;
    public boolean E = false;
    public boolean F = false;
    public boolean G = true;
    long H = ave.J();
    int I;
    long J = -1L;
    private String aR = "root";

    public ave(bao bao2) {
        S = this;
        this.v = bao2.c.a;
        this.ak = bao2.c.c;
        this.N = bao2.c.b;
        this.al = bao2.d.b;
        this.O = bao2.a.b;
        this.P = bao2.a.c;
        this.aB = new bna(new bmy(bao2.c.c, bao2.c.d).a());
        this.am = bao2.a.d == null ? Proxy.NO_PROXY : bao2.a.d;
        this.aK = new YggdrasilAuthenticationService(bao2.a.d, UUID.randomUUID().toString()).createMinecraftSessionService();
        this.ae = bao2.a.a;
        K.info("Setting user: " + this.ae.c());
        K.info("(Session ID is " + this.ae.a() + ")");
        this.au = bao2.d.a;
        this.d = bao2.b.a > 0 ? bao2.b.a : 1;
        this.e = bao2.b.b > 0 ? bao2.b.b : 1;
        this.ah = bao2.b.a;
        this.ai = bao2.b.b;
        this.T = bao2.b.c;
        this.at = ave.as();
        this.aj = new bpo(this);
        if (bao2.e.a != null) {
            this.aq = bao2.e.a;
            this.ar = bao2.e.b;
        }
        ImageIO.setUseCache(false);
        kb.c();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void a() {
        this.B = true;
        try {
            this.am();
        }
        catch (Throwable throwable) {
            b b2 = b.a(throwable, "Initializing game");
            b2.a("Initialization");
            this.c(this.b(b2));
            return;
        }
        try {
            while (this.B) {
                if (this.V && this.W != null) {
                    this.c(this.W);
                    return;
                }
                try {
                    this.av();
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    this.l();
                    this.a(new axo());
                    System.gc();
                }
            }
        }
        catch (avk outOfMemoryError) {
        }
        catch (e e2) {
            this.b(e2.a());
            this.l();
            K.fatal("Reported exception thrown!", (Throwable)e2);
            this.c(e2.a());
        }
        catch (Throwable throwable) {
            b b3 = this.b(new b("Unexpected error", throwable));
            this.l();
            K.fatal("Unreported exception thrown!", throwable);
            this.c(b3);
        }
        finally {
            this.g();
        }
    }

    private void am() throws LWJGLException, IOException {
        this.t = new avh(this, this.v);
        this.aA.add(this.aB);
        this.at();
        if (this.t.C > 0 && this.t.B > 0) {
            this.d = this.t.B;
            this.e = this.t.C;
        }
        K.info("LWJGL Version: " + Sys.getVersion());
        this.ar();
        this.aq();
        this.ap();
        bqs.a();
        this.aF = new bfw(this.d, this.e, true);
        this.aF.a(0.0f, 0.0f, 0.0f, 0.0f);
        this.an();
        this.aC = new bnm(this.N, new File(this.v, "server-resource-packs"), this.aB, this.az, this.t);
        this.ay = new bnn(this.az);
        this.aD = new bns(this.az, this.t.aM);
        this.ay.a(this.aD);
        this.e();
        this.R = new bmj(this.ay);
        this.ay.a(this.R);
        this.a(this.R);
        this.ao();
        this.aL = new bnp(this.R, new File(this.ak, "skins"), this.aK);
        this.an = new atk(new File(this.v, "saves"));
        this.aH = new bpz(this.ay, this.t);
        this.ay.a(this.aH);
        this.aI = new bpv(this);
        this.k = new avn(this.t, new jy("textures/font/ascii.png"), this.R, false);
        if (this.t.aM != null) {
            this.k.a(this.d());
            this.k.b(this.aD.b());
        }
        this.l = new avn(this.t, new jy("textures/font/ascii_sga.png"), this.R, false);
        this.ay.a(this.k);
        this.ay.a(this.l);
        this.ay.a(new bnf());
        this.ay.a(new bne());
        mr.f.a(new ms(){

            @Override
            public String a(String string) {
                try {
                    return String.format(string, avh.c(ave.this.t.ae.i()));
                }
                catch (Exception exception) {
                    return "Error: " + exception.getLocalizedMessage();
                }
            }
        });
        this.u = new avf();
        this.b("Pre startup");
        bfl.w();
        bfl.j(7425);
        bfl.a(1.0);
        bfl.j();
        bfl.c(515);
        bfl.d();
        bfl.a(516, 0.1f);
        bfl.e(1029);
        bfl.n(5889);
        bfl.D();
        bfl.n(5888);
        this.b("Startup");
        this.aG = new bmh("textures");
        this.aG.a(this.t.J);
        this.R.a(bmh.g, this.aG);
        this.R.a(bmh.g);
        this.aG.a(false, this.t.J > 0);
        this.aP = new bou(this.aG);
        this.ay.a(this.aP);
        this.ab = new bjh(this.R, this.aP);
        this.aa = new biu(this.R, this.ab);
        this.ac = new bfn(this);
        this.ay.a(this.ab);
        this.o = new bfk(this, this.ay);
        this.ay.a(this.o);
        this.aQ = new bgd(this.aP.c(), this.t);
        this.ay.a(this.aQ);
        this.g = new bfr(this);
        this.ay.a(this.g);
        this.p = new ayd(this);
        bfl.b(0, 0, this.d, this.e);
        this.j = new bec(this.f, this.R);
        this.b("Post startup");
        this.q = new avo(this);
        if (this.aq != null) {
            this.a(new awz(new aya(), this, this.aq, this.ar));
        } else {
            this.a(new aya());
        }
        this.R.c(this.aJ);
        this.aJ = null;
        this.n = new avi(this);
        if (this.t.s && !this.T) {
            this.q();
        }
        try {
            Display.setVSyncEnabled(this.t.t);
        }
        catch (OpenGLException openGLException) {
            this.t.t = false;
            this.t.b();
        }
        this.g.b();
    }

    private void an() {
        this.az.a(new boo(), bon.class);
        this.az.a(new boe(), bod.class);
        this.az.a(new bob(), boa.class);
        this.az.a(new bok(), boj.class);
        this.az.a(new boh(), bog.class);
    }

    private void ao() {
        try {
            this.aE = new bqn(this, Iterables.getFirst(this.O.get("twitch_access_token"), null));
        }
        catch (Throwable throwable) {
            this.aE = new bqo(throwable);
            K.error("Couldn't initialize twitch stream");
        }
    }

    private void ap() throws LWJGLException {
        Display.setResizable(true);
        Display.setTitle("Minecraft 1.8.8");
        try {
            Display.create(new PixelFormat().withDepthBits(24));
        }
        catch (LWJGLException lWJGLException) {
            K.error("Couldn't set pixel format", (Throwable)lWJGLException);
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
            if (this.T) {
                this.au();
            }
            Display.create();
        }
    }

    private void aq() throws LWJGLException {
        if (this.T) {
            Display.setFullscreen(true);
            DisplayMode displayMode = Display.getDisplayMode();
            this.d = Math.max(1, displayMode.getWidth());
            this.e = Math.max(1, displayMode.getHeight());
        } else {
            Display.setDisplayMode(new DisplayMode(this.d, this.e));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void ar() {
        g.a a2 = g.a();
        if (a2 != g.a.d) {
            block5: {
                InputStream inputStream = null;
                \u2603 = null;
                try {
                    inputStream = this.aB.c(new jy("icons/icon_16x16.png"));
                    \u2603 = this.aB.c(new jy("icons/icon_32x32.png"));
                    if (inputStream == null || \u2603 == null) break block5;
                    Display.setIcon(new ByteBuffer[]{this.a(inputStream), this.a(\u2603)});
                }
                catch (IOException \u26032) {
                    try {
                        K.error("Couldn't set icon", (Throwable)\u26032);
                    }
                    catch (Throwable throwable) {
                        IOUtils.closeQuietly(inputStream);
                        IOUtils.closeQuietly(\u2603);
                        throw throwable;
                    }
                    IOUtils.closeQuietly(inputStream);
                    IOUtils.closeQuietly(\u2603);
                }
            }
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(\u2603);
        }
    }

    private static boolean as() {
        String[] stringArray;
        for (String \u26032 : stringArray = new String[]{"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"}) {
            String string = System.getProperty(\u26032);
            if (string == null || !string.contains("64")) continue;
            return true;
        }
        return false;
    }

    public bfw b() {
        return this.aF;
    }

    public String c() {
        return this.al;
    }

    private void at() {
        Thread thread = new Thread("Timer hack thread"){

            @Override
            public void run() {
                while (ave.this.B) {
                    try {
                        Thread.sleep(Integer.MAX_VALUE);
                    }
                    catch (InterruptedException interruptedException) {}
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    public void a(b b2) {
        this.V = true;
        this.W = b2;
    }

    public void c(b b2) {
        File file = new File(ave.A().v, "crash-reports");
        \u2603 = new File(file, "crash-" + new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date()) + "-client.txt");
        kb.a(b2.e());
        if (b2.f() != null) {
            kb.a("#@!@# Game crashed! Crash report saved to: #@!@# " + b2.f());
            System.exit(-1);
        } else if (b2.a(\u2603)) {
            kb.a("#@!@# Game crashed! Crash report saved to: #@!@# " + \u2603.getAbsolutePath());
            System.exit(-1);
        } else {
            kb.a("#@?@# Game crashed! Crash report could not be saved. #@?@#");
            System.exit(-2);
        }
    }

    public boolean d() {
        return this.aD.a() || this.t.aN;
    }

    public void e() {
        ArrayList<bnk> arrayList = Lists.newArrayList(this.aA);
        for (bnm.a a2 : this.aC.c()) {
            arrayList.add(a2.c());
        }
        if (this.aC.e() != null) {
            arrayList.add(this.aC.e());
        }
        try {
            this.ay.a(arrayList);
        }
        catch (RuntimeException runtimeException) {
            K.info("Caught error stitching, removing all assigned resourcepacks", (Throwable)runtimeException);
            arrayList.clear();
            arrayList.addAll(this.aA);
            this.aC.a(Collections.<bnm.a>emptyList());
            this.ay.a(arrayList);
            this.t.k.clear();
            this.t.l.clear();
            this.t.b();
        }
        this.aD.a(arrayList);
        if (this.g != null) {
            this.g.a();
        }
    }

    private ByteBuffer a(InputStream inputStream) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        int[] \u26032 = bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null, 0, bufferedImage.getWidth());
        ByteBuffer \u26033 = ByteBuffer.allocate(4 * \u26032.length);
        for (int n2 : \u26032) {
            \u26033.putInt(n2 << 8 | n2 >> 24 & 0xFF);
        }
        \u26033.flip();
        return \u26033;
    }

    private void au() throws LWJGLException {
        HashSet<DisplayMode> hashSet = Sets.newHashSet();
        Collections.addAll(hashSet, Display.getAvailableDisplayModes());
        DisplayMode \u26032 = Display.getDesktopDisplayMode();
        if (!hashSet.contains(\u26032) && g.a() == g.a.d) {
            block0: for (DisplayMode displayMode : M) {
                boolean bl2 = true;
                for (DisplayMode displayMode2 : hashSet) {
                    if (displayMode2.getBitsPerPixel() != 32 || displayMode2.getWidth() != displayMode.getWidth() || displayMode2.getHeight() != displayMode.getHeight()) continue;
                    bl2 = false;
                    break;
                }
                if (bl2) continue;
                for (DisplayMode displayMode2 : hashSet) {
                    if (displayMode2.getBitsPerPixel() != 32 || displayMode2.getWidth() != displayMode.getWidth() / 2 || displayMode2.getHeight() != displayMode.getHeight() / 2) continue;
                    \u26032 = displayMode2;
                    continue block0;
                }
            }
        }
        Display.setDisplayMode(\u26032);
        this.d = \u26032.getWidth();
        this.e = \u26032.getHeight();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void a(bmj bmj2) throws LWJGLException {
        avr avr2 = new avr(this);
        int \u26032 = avr2.e();
        bfw \u26033 = new bfw(avr2.a() * \u26032, avr2.b() * \u26032, true);
        \u26033.a(false);
        bfl.n(5889);
        bfl.D();
        bfl.a(0.0, avr2.a(), avr2.b(), 0.0, 1000.0, 3000.0);
        bfl.n(5888);
        bfl.D();
        bfl.b(0.0f, 0.0f, -2000.0f);
        bfl.f();
        bfl.n();
        bfl.i();
        bfl.w();
        InputStream \u26034 = null;
        try {
            \u26034 = this.aB.a(L);
            this.aJ = bmj2.a("logo", new blz(ImageIO.read(\u26034)));
            bmj2.a(this.aJ);
        }
        catch (IOException \u26035) {
            try {
                K.error("Unable to load logo: " + L, (Throwable)\u26035);
            }
            catch (Throwable throwable) {
                IOUtils.closeQuietly(\u26034);
                throw throwable;
            }
            IOUtils.closeQuietly(\u26034);
        }
        IOUtils.closeQuietly(\u26034);
        bfx \u26036 = bfx.a();
        bfd \u26037 = \u26036.c();
        \u26037.a(7, bms.i);
        \u26037.b(0.0, (double)this.e, 0.0).a(0.0, 0.0).b(255, 255, 255, 255).d();
        \u26037.b((double)this.d, (double)this.e, 0.0).a(0.0, 0.0).b(255, 255, 255, 255).d();
        \u26037.b((double)this.d, 0.0, 0.0).a(0.0, 0.0).b(255, 255, 255, 255).d();
        \u26037.b(0.0, 0.0, 0.0).a(0.0, 0.0).b(255, 255, 255, 255).d();
        \u26036.b();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        int \u26038 = 256;
        int \u26039 = 256;
        this.a((avr2.a() - \u26038) / 2, (avr2.b() - \u26039) / 2, 0, 0, \u26038, \u26039, 255, 255, 255, 255);
        bfl.f();
        bfl.n();
        \u26033.e();
        \u26033.c(avr2.a() * \u26032, avr2.b() * \u26032);
        bfl.d();
        bfl.a(516, 0.1f);
        this.h();
    }

    public void a(int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10, int n11) {
        float f2 = 0.00390625f;
        \u2603 = 0.00390625f;
        bfd \u26032 = bfx.a().c();
        \u26032.a(7, bms.i);
        \u26032.b((double)n2, (double)(n3 + n7), 0.0).a((float)n4 * f2, (float)(n5 + n7) * \u2603).b(n8, n9, n10, n11).d();
        \u26032.b((double)(n2 + n6), (double)(n3 + n7), 0.0).a((float)(n4 + n6) * f2, (float)(n5 + n7) * \u2603).b(n8, n9, n10, n11).d();
        \u26032.b((double)(n2 + n6), (double)n3, 0.0).a((float)(n4 + n6) * f2, (float)n5 * \u2603).b(n8, n9, n10, n11).d();
        \u26032.b((double)n2, (double)n3, 0.0).a((float)n4 * f2, (float)n5 * \u2603).b(n8, n9, n10, n11).d();
        bfx.a().b();
    }

    public atr f() {
        return this.an;
    }

    public void a(axu axu2) {
        if (this.m != null) {
            this.m.m();
        }
        if (axu2 == null && this.f == null) {
            axu2 = new aya();
        } else if (axu2 == null && this.h.bn() <= 0.0f) {
            axu2 = new axe();
        }
        if (axu2 instanceof aya) {
            this.t.aB = false;
            this.q.d().a();
        }
        this.m = axu2;
        if (axu2 != null) {
            this.o();
            avr avr2 = new avr(this);
            int \u26032 = avr2.a();
            int \u26033 = avr2.b();
            axu2.a(this, \u26032, \u26033);
            this.r = false;
        } else {
            this.aH.e();
            this.n();
        }
    }

    private void b(String string) {
        if (!this.U) {
            return;
        }
        int n2 = GL11.glGetError();
        if (n2 != 0) {
            String string2 = GLU.gluErrorString(n2);
            K.error("########## GL ERROR ##########");
            K.error("@ " + string);
            K.error(n2 + ": " + string2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void g() {
        try {
            this.aE.f();
            K.info("Stopping!");
            try {
                this.a((bdb)null);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            this.aH.d();
        }
        finally {
            Display.destroy();
            if (!this.V) {
                System.exit(0);
            }
        }
        System.gc();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void av() {
        long l2 = System.nanoTime();
        this.A.a("root");
        if (Display.isCreated() && Display.isCloseRequested()) {
            this.m();
        }
        if (this.af && this.f != null) {
            float f2 = this.Y.c;
            this.Y.a();
            this.Y.c = f2;
        } else {
            this.Y.a();
        }
        this.A.a("scheduledExecutables");
        Queue<FutureTask<?>> f2 = this.aM;
        synchronized (f2) {
            while (!this.aM.isEmpty()) {
                g.a(this.aM.poll(), K);
            }
        }
        this.A.b();
        long \u26032 = System.nanoTime();
        this.A.a("tick");
        for (int i2 = 0; i2 < this.Y.b; ++i2) {
            this.s();
        }
        this.A.c("preRenderErrors");
        long l3 = System.nanoTime() - \u26032;
        this.b("Pre render");
        this.A.c("sound");
        this.aH.a(this.h, this.Y.c);
        this.A.b();
        this.A.a("render");
        bfl.E();
        bfl.m(16640);
        this.aF.a(true);
        this.A.a("display");
        bfl.w();
        if (this.h != null && this.h.aj()) {
            this.t.aA = 0;
        }
        this.A.b();
        if (!this.r) {
            this.A.c("gameRenderer");
            this.o.a(this.Y.c, l2);
            this.A.b();
        }
        this.A.b();
        if (this.t.aB && this.t.aC && !this.t.az) {
            if (!this.A.a) {
                this.A.a();
            }
            this.A.a = true;
            this.a(l3);
        } else {
            this.A.a = false;
            this.J = System.nanoTime();
        }
        this.p.a();
        this.aF.e();
        bfl.F();
        bfl.E();
        this.aF.c(this.d, this.e);
        bfl.F();
        bfl.E();
        this.o.b(this.Y.c);
        bfl.F();
        this.A.a("root");
        this.h();
        Thread.yield();
        this.A.a("stream");
        this.A.a("update");
        this.aE.g();
        this.A.c("submit");
        this.aE.h();
        this.A.b();
        this.A.b();
        this.b("Post render");
        ++this.I;
        this.af = this.F() && this.m != null && this.m.d() && !this.aj.b();
        \u2603 = System.nanoTime();
        this.y.a(\u2603 - this.z);
        this.z = \u2603;
        while (ave.J() >= this.H + 1000L) {
            ao = this.I;
            Object[] objectArray = new Object[8];
            objectArray[0] = ao;
            objectArray[1] = bht.a;
            objectArray[2] = bht.a != 1 ? "s" : "";
            objectArray[3] = (float)this.t.g == avh.a.i.f() ? "inf" : Integer.valueOf(this.t.g);
            objectArray[4] = this.t.t ? " vsync" : "";
            Object object = objectArray[5] = this.t.i ? "" : " fast";
            objectArray[6] = this.t.h == 0 ? "" : (this.t.h == 1 ? " fast-clouds" : " fancy-clouds");
            objectArray[7] = bqs.f() ? " vbo" : "";
            this.C = String.format("%d fps (%d chunk update%s) T: %s%s%s%s%s", objectArray);
            bht.a = 0;
            this.H += 1000L;
            this.I = 0;
            this.Z.b();
            if (this.Z.d()) continue;
            this.Z.a();
        }
        if (this.k()) {
            this.A.a("fpslimit_wait");
            Display.sync(this.j());
            this.A.b();
        }
        this.A.b();
    }

    public void h() {
        this.A.a("display_update");
        Display.update();
        this.A.b();
        this.i();
    }

    protected void i() {
        if (!this.T && Display.wasResized()) {
            int n2 = this.d;
            \u2603 = this.e;
            this.d = Display.getWidth();
            this.e = Display.getHeight();
            if (this.d != n2 || this.e != \u2603) {
                if (this.d <= 0) {
                    this.d = 1;
                }
                if (this.e <= 0) {
                    this.e = 1;
                }
                this.a(this.d, this.e);
            }
        }
    }

    public int j() {
        if (this.f == null && this.m != null) {
            return 30;
        }
        return this.t.g;
    }

    public boolean k() {
        return (float)this.j() < avh.a.i.f();
    }

    public void l() {
        try {
            b = new byte[0];
            this.g.k();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        try {
            System.gc();
            this.a((bdb)null);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        System.gc();
    }

    private void b(int n2) {
        List<nt.a> list = this.A.b(this.aR);
        if (list == null || list.isEmpty()) {
            return;
        }
        nt.a \u26032 = list.remove(0);
        if (n2 == 0) {
            if (\u26032.c.length() > 0 && (\u2603 = this.aR.lastIndexOf(".")) >= 0) {
                this.aR = this.aR.substring(0, \u2603);
            }
        } else if (--n2 < list.size() && !list.get((int)n2).c.equals("unspecified")) {
            if (this.aR.length() > 0) {
                this.aR = this.aR + ".";
            }
            this.aR = this.aR + list.get((int)n2).c;
        }
    }

    private void a(long l2) {
        int \u26039;
        Object \u260314;
        if (!this.A.a) {
            return;
        }
        List<nt.a> list = this.A.b(this.aR);
        nt.a \u26032 = list.remove(0);
        bfl.m(256);
        bfl.n(5889);
        bfl.g();
        bfl.D();
        bfl.a(0.0, this.d, this.e, 0.0, 1000.0, 3000.0);
        bfl.n(5888);
        bfl.D();
        bfl.b(0.0f, 0.0f, -2000.0f);
        GL11.glLineWidth(1.0f);
        bfl.x();
        bfx \u26033 = bfx.a();
        bfd \u26034 = \u26033.c();
        int \u26035 = 160;
        int \u26036 = this.d - \u26035 - 10;
        int \u26037 = this.e - \u26035 * 2;
        bfl.l();
        \u26034.a(7, bms.f);
        \u26034.b((double)((float)\u26036 - (float)\u26035 * 1.1f), (double)((float)\u26037 - (float)\u26035 * 0.6f - 16.0f), 0.0).b(200, 0, 0, 0).d();
        \u26034.b((double)((float)\u26036 - (float)\u26035 * 1.1f), (double)(\u26037 + \u26035 * 2), 0.0).b(200, 0, 0, 0).d();
        \u26034.b((double)((float)\u26036 + (float)\u26035 * 1.1f), (double)(\u26037 + \u26035 * 2), 0.0).b(200, 0, 0, 0).d();
        \u26034.b((double)((float)\u26036 + (float)\u26035 * 1.1f), (double)((float)\u26037 - (float)\u26035 * 0.6f - 16.0f), 0.0).b(200, 0, 0, 0).d();
        \u26033.b();
        bfl.k();
        double \u26038 = 0.0;
        for (int i2 = 0; i2 < list.size(); ++i2) {
            float f2;
            int n2;
            \u260314 = list.get(i2);
            \u26039 = ns.c(((nt.a)\u260314).a / 4.0) + 1;
            \u26034.a(6, bms.f);
            int \u260310 = ((nt.a)\u260314).a();
            int \u260311 = \u260310 >> 16 & 0xFF;
            int \u260312 = \u260310 >> 8 & 0xFF;
            int \u260313 = \u260310 & 0xFF;
            \u26034.b((double)\u26036, (double)\u26037, 0.0).b(\u260311, \u260312, \u260313, 255).d();
            for (n2 = \u26039; n2 >= 0; --n2) {
                f2 = (float)((\u26038 + ((nt.a)\u260314).a * (double)n2 / (double)\u26039) * 3.1415927410125732 * 2.0 / 100.0);
                \u2603 = ns.a(f2) * (float)\u26035;
                \u2603 = ns.b(f2) * (float)\u26035 * 0.5f;
                \u26034.b((double)((float)\u26036 + \u2603), (double)((float)\u26037 - \u2603), 0.0).b(\u260311, \u260312, \u260313, 255).d();
            }
            \u26033.b();
            \u26034.a(5, bms.f);
            for (n2 = \u26039; n2 >= 0; --n2) {
                f2 = (float)((\u26038 + ((nt.a)\u260314).a * (double)n2 / (double)\u26039) * 3.1415927410125732 * 2.0 / 100.0);
                \u2603 = ns.a(f2) * (float)\u26035;
                \u2603 = ns.b(f2) * (float)\u26035 * 0.5f;
                \u26034.b((double)((float)\u26036 + \u2603), (double)((float)\u26037 - \u2603), 0.0).b(\u260311 >> 1, \u260312 >> 1, \u260313 >> 1, 255).d();
                \u26034.b((double)((float)\u26036 + \u2603), (double)((float)\u26037 - \u2603 + 10.0f), 0.0).b(\u260311 >> 1, \u260312 >> 1, \u260313 >> 1, 255).d();
            }
            \u26033.b();
            \u26038 += ((nt.a)\u260314).a;
        }
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        bfl.w();
        \u260314 = "";
        if (!\u26032.c.equals("unspecified")) {
            \u260314 = (String)\u260314 + "[0] ";
        }
        \u260314 = \u26032.c.length() == 0 ? (String)\u260314 + "ROOT " : (String)\u260314 + \u26032.c + " ";
        \u26039 = 0xFFFFFF;
        this.k.a((String)\u260314, (float)(\u26036 - \u26035), (float)(\u26037 - \u26035 / 2 - 16), \u26039);
        \u260314 = decimalFormat.format(\u26032.b) + "%";
        this.k.a((String)\u260314, (float)(\u26036 + \u26035 - this.k.a((String)\u260314)), (float)(\u26037 - \u26035 / 2 - 16), \u26039);
        for (int i3 = 0; i3 < list.size(); ++i3) {
            nt.a a2 = list.get(i3);
            String \u260315 = "";
            \u260315 = a2.c.equals("unspecified") ? \u260315 + "[?] " : \u260315 + "[" + (i3 + 1) + "] ";
            \u260315 = \u260315 + a2.c;
            this.k.a(\u260315, (float)(\u26036 - \u26035), (float)(\u26037 + \u26035 / 2 + i3 * 8 + 20), a2.a());
            \u260315 = decimalFormat.format(a2.a) + "%";
            this.k.a(\u260315, (float)(\u26036 + \u26035 - 50 - this.k.a(\u260315)), (float)(\u26037 + \u26035 / 2 + i3 * 8 + 20), a2.a());
            \u260315 = decimalFormat.format(a2.b) + "%";
            this.k.a(\u260315, (float)(\u26036 + \u26035 - this.k.a(\u260315)), (float)(\u26037 + \u26035 / 2 + i3 * 8 + 20), a2.a());
        }
    }

    public void m() {
        this.B = false;
    }

    public void n() {
        if (!Display.isActive()) {
            return;
        }
        if (this.w) {
            return;
        }
        this.w = true;
        this.u.a();
        this.a((axu)null);
        this.ag = 10000;
    }

    public void o() {
        if (!this.w) {
            return;
        }
        avb.a();
        this.w = false;
        this.u.b();
    }

    public void p() {
        if (this.m != null) {
            return;
        }
        this.a(new axp());
        if (this.F() && !this.aj.b()) {
            this.aH.a();
        }
    }

    private void b(boolean bl2) {
        if (!bl2) {
            this.ag = 0;
        }
        if (this.ag > 0 || this.h.bS()) {
            return;
        }
        if (bl2 && this.s != null && this.s.a == auh.a.b) {
            cj cj2 = this.s.a();
            if (this.f.p(cj2).c().t() != arm.a && this.c.c(cj2, this.s.b)) {
                this.j.a(cj2, this.s.b);
                this.h.bw();
            }
            return;
        }
        this.c.c();
    }

    private void aw() {
        if (this.ag > 0) {
            return;
        }
        this.h.bw();
        if (this.s == null) {
            K.error("Null returned as 'hitResult', this shouldn't happen!");
            if (this.c.g()) {
                this.ag = 10;
            }
            return;
        }
        switch (this.s.a) {
            case c: {
                this.c.a(this.h, this.s.d);
                break;
            }
            case b: {
                cj cj2 = this.s.a();
                if (this.f.p(cj2).c().t() != arm.a) {
                    this.c.b(cj2, this.s.b);
                    break;
                }
            }
            default: {
                if (!this.c.g()) break;
                this.ag = 10;
            }
        }
    }

    private void ax() {
        if (this.c.m()) {
            return;
        }
        this.ap = 4;
        boolean bl2 = true;
        zx \u26032 = this.h.bi.h();
        if (this.s == null) {
            K.warn("Null returned as 'hitResult', this shouldn't happen!");
        } else {
            switch (this.s.a) {
                case c: {
                    if (this.c.a((wn)this.h, this.s.d, this.s)) {
                        bl2 = false;
                        break;
                    }
                    if (!this.c.b(this.h, this.s.d)) break;
                    bl2 = false;
                    break;
                }
                case b: {
                    int n2;
                    Object object = this.s.a();
                    if (this.f.p((cj)object).c().t() == arm.a) break;
                    int n3 = n2 = \u26032 != null ? \u26032.b : 0;
                    if (this.c.a(this.h, this.f, \u26032, (cj)object, this.s.b, this.s.c)) {
                        bl2 = false;
                        this.h.bw();
                    }
                    if (\u26032 == null) {
                        return;
                    }
                    if (\u26032.b == 0) {
                        this.h.bi.a[this.h.bi.c] = null;
                        break;
                    }
                    if (\u26032.b == n2 && !this.c.h()) break;
                    this.o.c.b();
                }
            }
        }
        if (bl2 && (object = this.h.bi.h()) != null && this.c.a((wn)this.h, this.f, (zx)object)) {
            this.o.c.c();
        }
    }

    public void q() {
        try {
            this.t.s = this.T = !this.T;
            if (this.T) {
                this.au();
                this.d = Display.getDisplayMode().getWidth();
                this.e = Display.getDisplayMode().getHeight();
                if (this.d <= 0) {
                    this.d = 1;
                }
                if (this.e <= 0) {
                    this.e = 1;
                }
            } else {
                Display.setDisplayMode(new DisplayMode(this.ah, this.ai));
                this.d = this.ah;
                this.e = this.ai;
                if (this.d <= 0) {
                    this.d = 1;
                }
                if (this.e <= 0) {
                    this.e = 1;
                }
            }
            if (this.m != null) {
                this.a(this.d, this.e);
            } else {
                this.ay();
            }
            Display.setFullscreen(this.T);
            Display.setVSyncEnabled(this.t.t);
            this.h();
        }
        catch (Exception exception) {
            K.error("Couldn't toggle fullscreen", (Throwable)exception);
        }
    }

    private void a(int n2, int n3) {
        this.d = Math.max(1, n2);
        this.e = Math.max(1, n3);
        if (this.m != null) {
            avr avr2 = new avr(this);
            this.m.b(this, avr2.a(), avr2.b());
        }
        this.n = new avi(this);
        this.ay();
    }

    private void ay() {
        this.aF.a(this.d, this.e);
        if (this.o != null) {
            this.o.a(this.d, this.e);
        }
    }

    public bpv r() {
        return this.aI;
    }

    public void s() {
        if (this.ap > 0) {
            --this.ap;
        }
        this.A.a("gui");
        if (!this.af) {
            this.q.c();
        }
        this.A.b();
        this.o.a(1.0f);
        this.A.a("gameMode");
        if (!this.af && this.f != null) {
            this.c.e();
        }
        this.A.c("textures");
        if (!this.af) {
            this.R.e();
        }
        if (this.m == null && this.h != null) {
            if (this.h.bn() <= 0.0f) {
                this.a((axu)null);
            } else if (this.h.bJ() && this.f != null) {
                this.a(new axk());
            }
        } else if (this.m != null && this.m instanceof axk && !this.h.bJ()) {
            this.a((axu)null);
        }
        if (this.m != null) {
            this.ag = 10000;
        }
        if (this.m != null) {
            try {
                this.m.p();
            }
            catch (Throwable throwable) {
                b b2 = b.a(throwable, "Updating screen events");
                c \u26032 = b2.a("Affected screen");
                \u26032.a("Screen name", new Callable<String>(){

                    public String a() throws Exception {
                        return ave.this.m.getClass().getCanonicalName();
                    }

                    @Override
                    public /* synthetic */ Object call() throws Exception {
                        return this.a();
                    }
                });
                throw new e(b2);
            }
            if (this.m != null) {
                try {
                    this.m.e();
                }
                catch (Throwable \u26033) {
                    \u2603 = b.a(\u26033, "Ticking screen");
                    c c2 = \u2603.a("Affected screen");
                    c2.a("Screen name", new Callable<String>(){

                        public String a() throws Exception {
                            return ave.this.m.getClass().getCanonicalName();
                        }

                        @Override
                        public /* synthetic */ Object call() throws Exception {
                            return this.a();
                        }
                    });
                    throw new e(\u2603);
                }
            }
        }
        if (this.m == null || this.m.p) {
            int n2;
            this.A.c("mouse");
            while (Mouse.next()) {
                n2 = Mouse.getEventButton();
                avb.a(n2 - 100, Mouse.getEventButtonState());
                if (Mouse.getEventButtonState()) {
                    if (this.h.v() && n2 == 2) {
                        this.q.g().b();
                    } else {
                        avb.a(n2 - 100);
                    }
                }
                if ((\u2603 = ave.J() - this.x) > 200L) continue;
                n4 = Mouse.getEventDWheel();
                if (n4 != 0) {
                    if (this.h.v()) {
                        int n3 = n4 = n4 < 0 ? -1 : 1;
                        if (this.q.g().a()) {
                            this.q.g().b(-n4);
                        } else {
                            float f2 = ns.a(this.h.bA.a() + (float)n4 * 0.005f, 0.0f, 0.2f);
                            this.h.bA.a(f2);
                        }
                    } else {
                        int n4;
                        this.h.bi.d(n4);
                    }
                }
                if (this.m == null) {
                    if (this.w || !Mouse.getEventButtonState()) continue;
                    this.n();
                    continue;
                }
                if (this.m == null) continue;
                this.m.k();
            }
            if (this.ag > 0) {
                --this.ag;
            }
            this.A.c("keyboard");
            while (Keyboard.next()) {
                n2 = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
                avb.a(n2, Keyboard.getEventKeyState());
                if (Keyboard.getEventKeyState()) {
                    avb.a(n2);
                }
                if (this.ax > 0L) {
                    if (ave.J() - this.ax >= 6000L) {
                        throw new e(new b("Manually triggered debug crash", new Throwable()));
                    }
                    if (!Keyboard.isKeyDown(46) || !Keyboard.isKeyDown(61)) {
                        this.ax = -1L;
                    }
                } else if (Keyboard.isKeyDown(46) && Keyboard.isKeyDown(61)) {
                    this.ax = ave.J();
                }
                this.Z();
                if (!Keyboard.getEventKeyState()) continue;
                if (n2 == 62 && this.o != null) {
                    this.o.c();
                }
                if (this.m != null) {
                    this.m.l();
                } else {
                    if (n2 == 1) {
                        this.p();
                    }
                    if (n2 == 32 && Keyboard.isKeyDown(61) && this.q != null) {
                        this.q.d().a();
                    }
                    if (n2 == 31 && Keyboard.isKeyDown(61)) {
                        this.e();
                    }
                    if (n2 != 17 || Keyboard.isKeyDown(61)) {
                        // empty if block
                    }
                    if (n2 != 18 || Keyboard.isKeyDown(61)) {
                        // empty if block
                    }
                    if (n2 != 47 || Keyboard.isKeyDown(61)) {
                        // empty if block
                    }
                    if (n2 != 38 || Keyboard.isKeyDown(61)) {
                        // empty if block
                    }
                    if (n2 != 22 || Keyboard.isKeyDown(61)) {
                        // empty if block
                    }
                    if (n2 == 20 && Keyboard.isKeyDown(61)) {
                        this.e();
                    }
                    if (n2 == 33 && Keyboard.isKeyDown(61)) {
                        this.t.a(avh.a.f, axu.r() ? -1 : 1);
                    }
                    if (n2 == 30 && Keyboard.isKeyDown(61)) {
                        this.g.a();
                    }
                    if (n2 == 35 && Keyboard.isKeyDown(61)) {
                        this.t.y = !this.t.y;
                        this.t.b();
                    }
                    if (n2 == 48 && Keyboard.isKeyDown(61)) {
                        this.aa.b(!this.aa.b());
                    }
                    if (n2 == 25 && Keyboard.isKeyDown(61)) {
                        this.t.z = !this.t.z;
                        this.t.b();
                    }
                    if (n2 == 59) {
                        boolean bl2 = this.t.az = !this.t.az;
                    }
                    if (n2 == 61) {
                        this.t.aB = !this.t.aB;
                        this.t.aC = axu.r();
                        this.t.aD = axu.s();
                    }
                    if (this.t.an.f()) {
                        ++this.t.aA;
                        if (this.t.aA > 2) {
                            this.t.aA = 0;
                        }
                        if (this.t.aA == 0) {
                            this.o.a(this.ac());
                        } else if (this.t.aA == 1) {
                            this.o.a((pk)null);
                        }
                        this.g.m();
                    }
                    if (this.t.ao.f()) {
                        boolean bl3 = this.t.aF = !this.t.aF;
                    }
                }
                if (!this.t.aB || !this.t.aC) continue;
                if (n2 == 11) {
                    this.b(0);
                }
                for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                    if (n2 != 2 + \u2603) continue;
                    this.b(\u2603 + 1);
                }
            }
            for (n2 = 0; n2 < 9; ++n2) {
                if (!this.t.av[n2].f()) continue;
                if (this.h.v()) {
                    this.q.g().a(n2);
                    continue;
                }
                this.h.bi.c = n2;
            }
            int n5 = n2 = this.t.m != wn.b.c ? 1 : 0;
            while (this.t.ae.f()) {
                if (this.c.j()) {
                    this.h.u();
                    continue;
                }
                this.u().a(new ig(ig.a.c));
                this.a(new azc(this.h));
            }
            while (this.t.ag.f()) {
                if (this.h.v()) continue;
                this.h.a(axu.q());
            }
            while (this.t.aj.f() && n2 != 0) {
                this.a(new awv());
            }
            if (this.m == null && this.t.al.f() && n2 != 0) {
                this.a(new awv("/"));
            }
            if (this.h.bS()) {
                if (!this.t.af.d()) {
                    this.c.c(this.h);
                }
                while (this.t.ah.f()) {
                }
                while (this.t.af.f()) {
                }
                while (this.t.ai.f()) {
                }
            } else {
                while (this.t.ah.f()) {
                    this.aw();
                }
                while (this.t.af.f()) {
                    this.ax();
                }
                while (this.t.ai.f()) {
                    this.az();
                }
            }
            if (this.t.af.d() && this.ap == 0 && !this.h.bS()) {
                this.ax();
            }
            this.b(this.m == null && this.t.ah.d() && this.w);
        }
        if (this.f != null) {
            if (this.h != null) {
                ++this.as;
                if (this.as == 30) {
                    this.as = 0;
                    this.f.h(this.h);
                }
            }
            this.A.c("gameRenderer");
            if (!this.af) {
                this.o.e();
            }
            this.A.c("levelRenderer");
            if (!this.af) {
                this.g.j();
            }
            this.A.c("level");
            if (!this.af) {
                if (this.f.ac() > 0) {
                    this.f.d(this.f.ac() - 1);
                }
                this.f.i();
            }
        } else if (this.o.a()) {
            this.o.b();
        }
        if (!this.af) {
            this.aI.c();
            this.aH.c();
        }
        if (this.f != null) {
            if (!this.af) {
                this.f.a(this.f.aa() != oj.a, true);
                try {
                    this.f.c();
                }
                catch (Throwable throwable) {
                    b b3 = b.a(throwable, "Exception in world tick");
                    if (this.f == null) {
                        c c3 = b3.a("Affected level");
                        c3.a("Problem", "Level is null!");
                    } else {
                        this.f.a(b3);
                    }
                    throw new e(b3);
                }
            }
            this.A.c("animateTick");
            if (!this.af && this.f != null) {
                this.f.b(ns.c(this.h.s), ns.c(this.h.t), ns.c(this.h.u));
            }
            this.A.c("particles");
            if (!this.af) {
                this.j.a();
            }
        } else if (this.av != null) {
            this.A.c("pendingConnection");
            this.av.a();
        }
        this.A.b();
        this.x = ave.J();
    }

    public void a(String string3, String string2, adp adp22) {
        Object \u26034;
        this.a((bdb)null);
        System.gc();
        atp atp2 = this.an.a(string3, false);
        ato \u26032 = atp2.d();
        if (\u26032 == null && adp22 != null) {
            \u26032 = new ato(adp22, string3);
            atp2.a(\u26032);
        }
        if (adp22 == null) {
            adp adp22 = new adp(\u26032);
        }
        try {
            String string3;
            this.aj = new bpo(this, string3, string2, adp22);
            this.aj.D();
            this.aw = true;
        }
        catch (Throwable throwable) {
            b \u26035 = b.a(throwable, "Starting integrated server");
            c \u26033 = \u26035.a("Starting integrated server");
            \u26033.a("Level ID", string3);
            \u26033.a("Level Name", string2);
            throw new e(\u26035);
        }
        this.n.a(bnq.a("menu.loadingLevel", new Object[0]));
        while (!this.aj.ar()) {
            \u26034 = this.aj.j();
            if (\u26034 != null) {
                this.n.c(bnq.a((String)\u26034, new Object[0]));
            } else {
                this.n.c("");
            }
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException \u26035) {}
        }
        this.a((axu)null);
        \u26034 = this.aj.aq().a();
        ek ek2 = ek.a((SocketAddress)\u26034);
        ek2.a(new bcx(ek2, this, null));
        ek2.a(new jc(47, \u26034.toString(), 0, el.d));
        ek2.a(new jl(this.L().e()));
        this.av = ek2;
    }

    public void a(bdb bdb2) {
        this.a(bdb2, "");
    }

    public void a(bdb bdb22, String string2) {
        bdb bdb22;
        if (bdb22 == null) {
            bcy bcy2 = this.u();
            if (bcy2 != null) {
                bcy2.b();
            }
            if (this.aj != null && this.aj.O()) {
                this.aj.w();
                this.aj.a();
            }
            this.aj = null;
            this.p.b();
            this.o.k().a();
        }
        this.ad = null;
        this.av = null;
        if (this.n != null) {
            String string2;
            this.n.b(string2);
            this.n.c("");
        }
        if (bdb22 == null && this.f != null) {
            this.aC.f();
            this.q.i();
            this.a((bde)null);
            this.aw = false;
        }
        this.aH.b();
        this.f = bdb22;
        if (bdb22 != null) {
            if (this.g != null) {
                this.g.a(bdb22);
            }
            if (this.j != null) {
                this.j.a(bdb22);
            }
            if (this.h == null) {
                this.h = this.c.a(bdb22, new nb());
                this.c.b(this.h);
            }
            this.h.I();
            bdb22.d(this.h);
            this.h.b = new bev(this.t);
            this.c.a(this.h);
            this.ad = this.h;
        } else {
            this.an.d();
            this.h = null;
        }
        System.gc();
        this.x = 0L;
    }

    public void a(int n2) {
        this.f.g();
        this.f.a();
        n3 = 0;
        String \u26032 = null;
        if (this.h != null) {
            int n3 = this.h.F();
            this.f.e(this.h);
            \u26032 = this.h.w();
        }
        this.ad = null;
        bew bew2 = this.h;
        this.h = this.c.a(this.f, this.h == null ? new nb() : this.h.x());
        this.h.H().a(bew2.H().c());
        this.h.am = n2;
        this.ad = this.h;
        this.h.I();
        this.h.f(\u26032);
        this.f.d(this.h);
        this.c.b(this.h);
        this.h.b = new bev(this.t);
        this.h.d(n3);
        this.c.a(this.h);
        this.h.k(bew2.cq());
        if (this.m instanceof axe) {
            this.a((axu)null);
        }
    }

    public final boolean t() {
        return this.au;
    }

    public bcy u() {
        if (this.h != null) {
            return this.h.a;
        }
        return null;
    }

    public static boolean v() {
        return S == null || !ave.S.t.az;
    }

    public static boolean w() {
        return S != null && ave.S.t.i;
    }

    public static boolean x() {
        return S != null && ave.S.t.j != 0;
    }

    private void az() {
        boolean \u26036;
        zw \u26034;
        if (this.s == null) {
            return;
        }
        boolean bl2 = this.h.bA.d;
        int \u26032 = 0;
        \u26036 = false;
        akw \u26033 = null;
        if (this.s.a == auh.a.b) {
            Object object = this.s.a();
            \u2603 = this.f.p((cj)object).c();
            if (((afh)\u2603).t() == arm.a) {
                return;
            }
            \u26034 = ((afh)\u2603).c(this.f, (cj)object);
            if (\u26034 == null) {
                return;
            }
            if (bl2 && axu.q()) {
                \u26033 = this.f.s((cj)object);
            }
            afh \u26035 = \u26034 instanceof yo && !((afh)\u2603).M() ? afh.a(\u26034) : \u2603;
            \u26032 = \u26035.j(this.f, (cj)object);
            \u26036 = \u26034.k();
        } else if (this.s.a == auh.a.c && this.s.d != null && bl2) {
            if (this.s.d instanceof uq) {
                \u26034 = zy.an;
            } else if (this.s.d instanceof up) {
                \u26034 = zy.cn;
            } else if (this.s.d instanceof uo) {
                object = (uo)this.s.d;
                \u2603 = ((uo)object).o();
                if (\u2603 == null) {
                    \u26034 = zy.bP;
                } else {
                    \u26034 = ((zx)\u2603).b();
                    \u26032 = ((zx)\u2603).i();
                    \u26036 = true;
                }
            } else if (this.s.d instanceof va) {
                object = (va)this.s.d;
                switch (((va)object).s()) {
                    case c: {
                        \u26034 = zy.aO;
                        break;
                    }
                    case b: {
                        \u26034 = zy.aN;
                        break;
                    }
                    case d: {
                        \u26034 = zy.ch;
                        break;
                    }
                    case f: {
                        \u26034 = zy.ci;
                        break;
                    }
                    case g: {
                        \u26034 = zy.cp;
                        break;
                    }
                    default: {
                        \u26034 = zy.az;
                        break;
                    }
                }
            } else if (this.s.d instanceof ux) {
                \u26034 = zy.aE;
            } else if (this.s.d instanceof um) {
                \u26034 = zy.cj;
            } else {
                \u26034 = zy.bJ;
                \u26032 = pm.a(this.s.d);
                \u26036 = true;
                if (!pm.a.containsKey(\u26032)) {
                    return;
                }
            }
        } else {
            return;
        }
        object = this.h.bi;
        if (\u26033 == null) {
            ((wm)object).a(\u26034, \u26032, \u26036, bl2);
        } else {
            \u2603 = this.a(\u26034, \u26032, \u26033);
            ((wm)object).a(((wm)object).c, (zx)\u2603);
        }
        if (bl2) {
            int \u26037 = this.h.bj.c.size() - 9 + ((wm)object).c;
            this.c.a(((wm)object).a(((wm)object).c), \u26037);
        }
    }

    private zx a(zw zw2, int n2, akw akw2) {
        zx zx2 = new zx(zw2, 1, n2);
        dn \u26032 = new dn();
        akw2.b(\u26032);
        if (zw2 == zy.bX && \u26032.c("Owner")) {
            dn dn2 = \u26032.m("Owner");
            \u2603 = new dn();
            \u2603.a("SkullOwner", dn2);
            zx2.d(\u2603);
            return zx2;
        }
        zx2.a("BlockEntityTag", \u26032);
        dn \u26033 = new dn();
        du \u26034 = new du();
        \u26034.a(new ea("(+NBT)"));
        \u26033.a("Lore", \u26034);
        zx2.a("display", \u26033);
        return zx2;
    }

    public b b(b b2) {
        b2.g().a("Launched Version", new Callable<String>(){

            public String a() {
                return ave.this.al;
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        b2.g().a("LWJGL", new Callable<String>(){

            public String a() {
                return Sys.getVersion();
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        b2.g().a("OpenGL", new Callable<String>(){

            public String a() {
                return GL11.glGetString(7937) + " GL version " + GL11.glGetString(7938) + ", " + GL11.glGetString(7936);
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        b2.g().a("GL Caps", new Callable<String>(){

            public String a() {
                return bqs.c();
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        b2.g().a("Using VBOs", new Callable<String>(){

            public String a() {
                return ave.this.t.u ? "Yes" : "No";
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        b2.g().a("Is Modded", new Callable<String>(){

            public String a() throws Exception {
                String string = ClientBrandRetriever.getClientModName();
                if (!string.equals("vanilla")) {
                    return "Definitely; Client brand changed to '" + string + "'";
                }
                if (ave.class.getSigners() == null) {
                    return "Very likely; Jar signature invalidated";
                }
                return "Probably not. Jar signature remains and client brand is untouched.";
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        b2.g().a("Type", new Callable<String>(){

            public String a() throws Exception {
                return "Client (map_client.txt)";
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        b2.g().a("Resource Packs", new Callable<String>(){

            public String a() throws Exception {
                StringBuilder stringBuilder = new StringBuilder();
                for (String string : ave.this.t.k) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(string);
                    if (!ave.this.t.l.contains(string)) continue;
                    stringBuilder.append(" (incompatible)");
                }
                return stringBuilder.toString();
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        b2.g().a("Current Language", new Callable<String>(){

            public String a() throws Exception {
                return ave.this.aD.c().toString();
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        b2.g().a("Profiler Position", new Callable<String>(){

            public String a() throws Exception {
                return ave.this.A.a ? ave.this.A.c() : "N/A (disabled)";
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        b2.g().a("CPU", new Callable<String>(){

            public String a() {
                return bqs.j();
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        if (this.f != null) {
            this.f.a(b2);
        }
        return b2;
    }

    public static ave A() {
        return S;
    }

    public ListenableFuture<Object> B() {
        return this.a(new Runnable(){

            @Override
            public void run() {
                ave.this.e();
            }
        });
    }

    @Override
    public void a(or or22) {
        or22.a("fps", ao);
        or22.a("vsync_enabled", this.t.t);
        or22.a("display_frequency", Display.getDisplayMode().getFrequency());
        or22.a("display_type", this.T ? "fullscreen" : "windowed");
        or22.a("run_time", (MinecraftServer.az() - or22.g()) / 60L * 1000L);
        or22.a("current_action", this.aA());
        String string = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN ? "little" : "big";
        or22.a("endianness", string);
        or22.a("resource_packs", this.aC.c().size());
        int \u26032 = 0;
        for (bnm.a a2 : this.aC.c()) {
            or22.a("resource_pack[" + \u26032++ + "]", a2.d());
        }
        if (this.aj != null && this.aj.av() != null) {
            or or22;
            or22.a("snooper_partner", this.aj.av().f());
        }
    }

    private String aA() {
        if (this.aj != null) {
            if (this.aj.b()) {
                return "hosting_lan";
            }
            return "singleplayer";
        }
        if (this.Q != null) {
            if (this.Q.d()) {
                return "playing_lan";
            }
            return "multiplayer";
        }
        return "out_of_game";
    }

    @Override
    public void b(or or2) {
        or2.b("opengl_version", GL11.glGetString(7938));
        or2.b("opengl_vendor", GL11.glGetString(7936));
        or2.b("client_brand", ClientBrandRetriever.getClientModName());
        or2.b("launched_version", this.al);
        ContextCapabilities contextCapabilities = GLContext.getCapabilities();
        or2.b("gl_caps[ARB_arrays_of_arrays]", contextCapabilities.GL_ARB_arrays_of_arrays);
        or2.b("gl_caps[ARB_base_instance]", contextCapabilities.GL_ARB_base_instance);
        or2.b("gl_caps[ARB_blend_func_extended]", contextCapabilities.GL_ARB_blend_func_extended);
        or2.b("gl_caps[ARB_clear_buffer_object]", contextCapabilities.GL_ARB_clear_buffer_object);
        or2.b("gl_caps[ARB_color_buffer_float]", contextCapabilities.GL_ARB_color_buffer_float);
        or2.b("gl_caps[ARB_compatibility]", contextCapabilities.GL_ARB_compatibility);
        or2.b("gl_caps[ARB_compressed_texture_pixel_storage]", contextCapabilities.GL_ARB_compressed_texture_pixel_storage);
        or2.b("gl_caps[ARB_compute_shader]", contextCapabilities.GL_ARB_compute_shader);
        or2.b("gl_caps[ARB_copy_buffer]", contextCapabilities.GL_ARB_copy_buffer);
        or2.b("gl_caps[ARB_copy_image]", contextCapabilities.GL_ARB_copy_image);
        or2.b("gl_caps[ARB_depth_buffer_float]", contextCapabilities.GL_ARB_depth_buffer_float);
        or2.b("gl_caps[ARB_compute_shader]", contextCapabilities.GL_ARB_compute_shader);
        or2.b("gl_caps[ARB_copy_buffer]", contextCapabilities.GL_ARB_copy_buffer);
        or2.b("gl_caps[ARB_copy_image]", contextCapabilities.GL_ARB_copy_image);
        or2.b("gl_caps[ARB_depth_buffer_float]", contextCapabilities.GL_ARB_depth_buffer_float);
        or2.b("gl_caps[ARB_depth_clamp]", contextCapabilities.GL_ARB_depth_clamp);
        or2.b("gl_caps[ARB_depth_texture]", contextCapabilities.GL_ARB_depth_texture);
        or2.b("gl_caps[ARB_draw_buffers]", contextCapabilities.GL_ARB_draw_buffers);
        or2.b("gl_caps[ARB_draw_buffers_blend]", contextCapabilities.GL_ARB_draw_buffers_blend);
        or2.b("gl_caps[ARB_draw_elements_base_vertex]", contextCapabilities.GL_ARB_draw_elements_base_vertex);
        or2.b("gl_caps[ARB_draw_indirect]", contextCapabilities.GL_ARB_draw_indirect);
        or2.b("gl_caps[ARB_draw_instanced]", contextCapabilities.GL_ARB_draw_instanced);
        or2.b("gl_caps[ARB_explicit_attrib_location]", contextCapabilities.GL_ARB_explicit_attrib_location);
        or2.b("gl_caps[ARB_explicit_uniform_location]", contextCapabilities.GL_ARB_explicit_uniform_location);
        or2.b("gl_caps[ARB_fragment_layer_viewport]", contextCapabilities.GL_ARB_fragment_layer_viewport);
        or2.b("gl_caps[ARB_fragment_program]", contextCapabilities.GL_ARB_fragment_program);
        or2.b("gl_caps[ARB_fragment_shader]", contextCapabilities.GL_ARB_fragment_shader);
        or2.b("gl_caps[ARB_fragment_program_shadow]", contextCapabilities.GL_ARB_fragment_program_shadow);
        or2.b("gl_caps[ARB_framebuffer_object]", contextCapabilities.GL_ARB_framebuffer_object);
        or2.b("gl_caps[ARB_framebuffer_sRGB]", contextCapabilities.GL_ARB_framebuffer_sRGB);
        or2.b("gl_caps[ARB_geometry_shader4]", contextCapabilities.GL_ARB_geometry_shader4);
        or2.b("gl_caps[ARB_gpu_shader5]", contextCapabilities.GL_ARB_gpu_shader5);
        or2.b("gl_caps[ARB_half_float_pixel]", contextCapabilities.GL_ARB_half_float_pixel);
        or2.b("gl_caps[ARB_half_float_vertex]", contextCapabilities.GL_ARB_half_float_vertex);
        or2.b("gl_caps[ARB_instanced_arrays]", contextCapabilities.GL_ARB_instanced_arrays);
        or2.b("gl_caps[ARB_map_buffer_alignment]", contextCapabilities.GL_ARB_map_buffer_alignment);
        or2.b("gl_caps[ARB_map_buffer_range]", contextCapabilities.GL_ARB_map_buffer_range);
        or2.b("gl_caps[ARB_multisample]", contextCapabilities.GL_ARB_multisample);
        or2.b("gl_caps[ARB_multitexture]", contextCapabilities.GL_ARB_multitexture);
        or2.b("gl_caps[ARB_occlusion_query2]", contextCapabilities.GL_ARB_occlusion_query2);
        or2.b("gl_caps[ARB_pixel_buffer_object]", contextCapabilities.GL_ARB_pixel_buffer_object);
        or2.b("gl_caps[ARB_seamless_cube_map]", contextCapabilities.GL_ARB_seamless_cube_map);
        or2.b("gl_caps[ARB_shader_objects]", contextCapabilities.GL_ARB_shader_objects);
        or2.b("gl_caps[ARB_shader_stencil_export]", contextCapabilities.GL_ARB_shader_stencil_export);
        or2.b("gl_caps[ARB_shader_texture_lod]", contextCapabilities.GL_ARB_shader_texture_lod);
        or2.b("gl_caps[ARB_shadow]", contextCapabilities.GL_ARB_shadow);
        or2.b("gl_caps[ARB_shadow_ambient]", contextCapabilities.GL_ARB_shadow_ambient);
        or2.b("gl_caps[ARB_stencil_texturing]", contextCapabilities.GL_ARB_stencil_texturing);
        or2.b("gl_caps[ARB_sync]", contextCapabilities.GL_ARB_sync);
        or2.b("gl_caps[ARB_tessellation_shader]", contextCapabilities.GL_ARB_tessellation_shader);
        or2.b("gl_caps[ARB_texture_border_clamp]", contextCapabilities.GL_ARB_texture_border_clamp);
        or2.b("gl_caps[ARB_texture_buffer_object]", contextCapabilities.GL_ARB_texture_buffer_object);
        or2.b("gl_caps[ARB_texture_cube_map]", contextCapabilities.GL_ARB_texture_cube_map);
        or2.b("gl_caps[ARB_texture_cube_map_array]", contextCapabilities.GL_ARB_texture_cube_map_array);
        or2.b("gl_caps[ARB_texture_non_power_of_two]", contextCapabilities.GL_ARB_texture_non_power_of_two);
        or2.b("gl_caps[ARB_uniform_buffer_object]", contextCapabilities.GL_ARB_uniform_buffer_object);
        or2.b("gl_caps[ARB_vertex_blend]", contextCapabilities.GL_ARB_vertex_blend);
        or2.b("gl_caps[ARB_vertex_buffer_object]", contextCapabilities.GL_ARB_vertex_buffer_object);
        or2.b("gl_caps[ARB_vertex_program]", contextCapabilities.GL_ARB_vertex_program);
        or2.b("gl_caps[ARB_vertex_shader]", contextCapabilities.GL_ARB_vertex_shader);
        or2.b("gl_caps[EXT_bindable_uniform]", contextCapabilities.GL_EXT_bindable_uniform);
        or2.b("gl_caps[EXT_blend_equation_separate]", contextCapabilities.GL_EXT_blend_equation_separate);
        or2.b("gl_caps[EXT_blend_func_separate]", contextCapabilities.GL_EXT_blend_func_separate);
        or2.b("gl_caps[EXT_blend_minmax]", contextCapabilities.GL_EXT_blend_minmax);
        or2.b("gl_caps[EXT_blend_subtract]", contextCapabilities.GL_EXT_blend_subtract);
        or2.b("gl_caps[EXT_draw_instanced]", contextCapabilities.GL_EXT_draw_instanced);
        or2.b("gl_caps[EXT_framebuffer_multisample]", contextCapabilities.GL_EXT_framebuffer_multisample);
        or2.b("gl_caps[EXT_framebuffer_object]", contextCapabilities.GL_EXT_framebuffer_object);
        or2.b("gl_caps[EXT_framebuffer_sRGB]", contextCapabilities.GL_EXT_framebuffer_sRGB);
        or2.b("gl_caps[EXT_geometry_shader4]", contextCapabilities.GL_EXT_geometry_shader4);
        or2.b("gl_caps[EXT_gpu_program_parameters]", contextCapabilities.GL_EXT_gpu_program_parameters);
        or2.b("gl_caps[EXT_gpu_shader4]", contextCapabilities.GL_EXT_gpu_shader4);
        or2.b("gl_caps[EXT_multi_draw_arrays]", contextCapabilities.GL_EXT_multi_draw_arrays);
        or2.b("gl_caps[EXT_packed_depth_stencil]", contextCapabilities.GL_EXT_packed_depth_stencil);
        or2.b("gl_caps[EXT_paletted_texture]", contextCapabilities.GL_EXT_paletted_texture);
        or2.b("gl_caps[EXT_rescale_normal]", contextCapabilities.GL_EXT_rescale_normal);
        or2.b("gl_caps[EXT_separate_shader_objects]", contextCapabilities.GL_EXT_separate_shader_objects);
        or2.b("gl_caps[EXT_shader_image_load_store]", contextCapabilities.GL_EXT_shader_image_load_store);
        or2.b("gl_caps[EXT_shadow_funcs]", contextCapabilities.GL_EXT_shadow_funcs);
        or2.b("gl_caps[EXT_shared_texture_palette]", contextCapabilities.GL_EXT_shared_texture_palette);
        or2.b("gl_caps[EXT_stencil_clear_tag]", contextCapabilities.GL_EXT_stencil_clear_tag);
        or2.b("gl_caps[EXT_stencil_two_side]", contextCapabilities.GL_EXT_stencil_two_side);
        or2.b("gl_caps[EXT_stencil_wrap]", contextCapabilities.GL_EXT_stencil_wrap);
        or2.b("gl_caps[EXT_texture_3d]", contextCapabilities.GL_EXT_texture_3d);
        or2.b("gl_caps[EXT_texture_array]", contextCapabilities.GL_EXT_texture_array);
        or2.b("gl_caps[EXT_texture_buffer_object]", contextCapabilities.GL_EXT_texture_buffer_object);
        or2.b("gl_caps[EXT_texture_integer]", contextCapabilities.GL_EXT_texture_integer);
        or2.b("gl_caps[EXT_texture_lod_bias]", contextCapabilities.GL_EXT_texture_lod_bias);
        or2.b("gl_caps[EXT_texture_sRGB]", contextCapabilities.GL_EXT_texture_sRGB);
        or2.b("gl_caps[EXT_vertex_shader]", contextCapabilities.GL_EXT_vertex_shader);
        or2.b("gl_caps[EXT_vertex_weighting]", contextCapabilities.GL_EXT_vertex_weighting);
        or2.b("gl_caps[gl_max_vertex_uniforms]", GL11.glGetInteger(35658));
        GL11.glGetError();
        or2.b("gl_caps[gl_max_fragment_uniforms]", GL11.glGetInteger(35657));
        GL11.glGetError();
        or2.b("gl_caps[gl_max_vertex_attribs]", GL11.glGetInteger(34921));
        GL11.glGetError();
        or2.b("gl_caps[gl_max_vertex_texture_image_units]", GL11.glGetInteger(35660));
        GL11.glGetError();
        or2.b("gl_caps[gl_max_texture_image_units]", GL11.glGetInteger(34930));
        GL11.glGetError();
        or2.b("gl_caps[gl_max_texture_image_units]", GL11.glGetInteger(35071));
        GL11.glGetError();
        or2.b("gl_max_texture_size", ave.C());
    }

    public static int C() {
        for (int i2 = 16384; i2 > 0; i2 >>= 1) {
            GL11.glTexImage2D(32868, 0, 6408, i2, i2, 0, 6408, 5121, (ByteBuffer)null);
            \u2603 = GL11.glGetTexLevelParameteri(32868, 0, 4096);
            if (\u2603 == 0) continue;
            return i2;
        }
        return -1;
    }

    @Override
    public boolean ad() {
        return this.t.r;
    }

    public void a(bde bde2) {
        this.Q = bde2;
    }

    public bde D() {
        return this.Q;
    }

    public boolean E() {
        return this.aw;
    }

    public boolean F() {
        return this.aw && this.aj != null;
    }

    public bpo G() {
        return this.aj;
    }

    public static void H() {
        if (S == null) {
            return;
        }
        bpo bpo2 = S.G();
        if (bpo2 != null) {
            bpo2.t();
        }
    }

    public or I() {
        return this.Z;
    }

    public static long J() {
        return Sys.getTime() * 1000L / Sys.getTimerResolution();
    }

    public boolean K() {
        return this.T;
    }

    public avm L() {
        return this.ae;
    }

    public PropertyMap M() {
        return this.O;
    }

    public PropertyMap N() {
        if (this.P.isEmpty()) {
            GameProfile gameProfile = this.aa().fillProfileProperties(this.ae.e(), false);
            this.P.putAll(gameProfile.getProperties());
        }
        return this.P;
    }

    public Proxy O() {
        return this.am;
    }

    public bmj P() {
        return this.R;
    }

    public bni Q() {
        return this.ay;
    }

    public bnm R() {
        return this.aC;
    }

    public bns S() {
        return this.aD;
    }

    public bmh T() {
        return this.aG;
    }

    public boolean U() {
        return this.at;
    }

    public boolean V() {
        return this.af;
    }

    public bpz W() {
        return this.aH;
    }

    public bpv.a X() {
        if (this.h != null) {
            if (this.h.o.t instanceof ann) {
                return bpv.a.e;
            }
            if (this.h.o.t instanceof anp) {
                if (bfc.c != null && bfc.b > 0) {
                    return bpv.a.f;
                }
                return bpv.a.g;
            }
            if (this.h.bA.d && this.h.bA.c) {
                return bpv.a.c;
            }
            return bpv.a.b;
        }
        return bpv.a.a;
    }

    public bqm Y() {
        return this.aE;
    }

    public void Z() {
        int n2;
        int n3 = n2 = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() : Keyboard.getEventKey();
        if (n2 == 0 || Keyboard.isRepeatEvent()) {
            return;
        }
        if (this.m instanceof ayj && ((ayj)this.m).g > ave.J() - 20L) {
            return;
        }
        if (Keyboard.getEventKeyState()) {
            if (n2 == this.t.ar.i()) {
                if (this.Y().k()) {
                    this.Y().r();
                } else if (this.Y().j()) {
                    this.a(new awy(new awx(){

                        @Override
                        public void a(boolean bl2, int n2) {
                            if (bl2) {
                                ave.this.Y().q();
                            }
                            ave.this.a((axu)null);
                        }
                    }, bnq.a("stream.confirm_start", new Object[0]), "", 0));
                } else if (!this.Y().A() || !this.Y().i()) {
                    baa.a(this.m);
                } else if (this.f != null) {
                    this.q.d().a(new fa("Not ready to start streaming yet!"));
                }
            } else if (n2 == this.t.as.i()) {
                if (this.Y().k()) {
                    if (this.Y().l()) {
                        this.Y().o();
                    } else {
                        this.Y().n();
                    }
                }
            } else if (n2 == this.t.at.i()) {
                if (this.Y().k()) {
                    this.Y().m();
                }
            } else if (n2 == this.t.au.i()) {
                this.aE.a(true);
            } else if (n2 == this.t.ap.i()) {
                this.q();
            } else if (n2 == this.t.am.i()) {
                this.q.d().a(avj.a(this.v, this.d, this.e, this.aF));
            }
        } else if (n2 == this.t.au.i()) {
            this.aE.a(false);
        }
    }

    public MinecraftSessionService aa() {
        return this.aK;
    }

    public bnp ab() {
        return this.aL;
    }

    public pk ac() {
        return this.ad;
    }

    public void a(pk pk2) {
        this.ad = pk2;
        this.o.a(pk2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public <V> ListenableFuture<V> a(Callable<V> callable2) {
        Validate.notNull(callable2);
        if (!this.aJ()) {
            ListenableFutureTask<V> listenableFutureTask = ListenableFutureTask.create(callable2);
            Queue<FutureTask<?>> queue = this.aM;
            synchronized (queue) {
                this.aM.add(listenableFutureTask);
            }
            return listenableFutureTask;
        }
        try {
            Callable<V> callable2;
            return Futures.immediateFuture(callable2.call());
        }
        catch (Exception exception) {
            return Futures.immediateFailedCheckedFuture(exception);
        }
    }

    @Override
    public ListenableFuture<Object> a(Runnable runnable) {
        Validate.notNull(runnable);
        return this.a(Executors.callable(runnable));
    }

    @Override
    public boolean aJ() {
        return Thread.currentThread() == this.aO;
    }

    public bgd ae() {
        return this.aQ;
    }

    public biu af() {
        return this.aa;
    }

    public bjh ag() {
        return this.ab;
    }

    public bfn ah() {
        return this.ac;
    }

    public static int ai() {
        return ao;
    }

    public nh aj() {
        return this.y;
    }

    public static Map<String, String> ak() {
        HashMap<String, String> hashMap = Maps.newHashMap();
        hashMap.put("X-Minecraft-Username", ave.A().L().c());
        hashMap.put("X-Minecraft-UUID", ave.A().L().b());
        hashMap.put("X-Minecraft-Version", "1.8.8");
        return hashMap;
    }

    public boolean al() {
        return this.X;
    }

    public void a(boolean bl2) {
        this.X = bl2;
    }
}

