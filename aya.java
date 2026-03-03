/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.realms.RealmsBridge;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

public class aya
extends axu
implements awx {
    private static final AtomicInteger f = new AtomicInteger(0);
    private static final Logger g = LogManager.getLogger();
    private static final Random h = new Random();
    private float i;
    private String r = "missingno";
    private avs s;
    private int t;
    private blz u;
    private boolean v = true;
    private final Object w = new Object();
    private String x;
    private String y = a;
    private String z;
    private static final jy A = new jy("texts/splashes.txt");
    private static final jy B = new jy("textures/gui/title/minecraft.png");
    private static final jy[] C = new jy[]{new jy("textures/gui/title/background/panorama_0.png"), new jy("textures/gui/title/background/panorama_1.png"), new jy("textures/gui/title/background/panorama_2.png"), new jy("textures/gui/title/background/panorama_3.png"), new jy("textures/gui/title/background/panorama_4.png"), new jy("textures/gui/title/background/panorama_5.png")};
    public static final String a = "Please click " + (Object)((Object)a.t) + "here" + (Object)((Object)a.v) + " for more information.";
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private jy J;
    private avs K;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public aya() {
        BufferedReader \u26032 = null;
        try {
            String string;
            ArrayList<String> arrayList = Lists.newArrayList();
            \u26032 = new BufferedReader(new InputStreamReader(ave.A().Q().a(A).b(), Charsets.UTF_8));
            while ((string = \u26032.readLine()) != null) {
                if ((string = string.trim()).isEmpty()) continue;
                arrayList.add(string);
            }
            if (!arrayList.isEmpty()) {
                do {
                    this.r = (String)arrayList.get(h.nextInt(arrayList.size()));
                } while (this.r.hashCode() == 125780783);
            }
        }
        catch (IOException iOException) {
        }
        finally {
            if (\u26032 != null) {
                try {
                    \u26032.close();
                }
                catch (IOException iOException) {}
            }
        }
        this.i = h.nextFloat();
        this.x = "";
        if (!GLContext.getCapabilities().OpenGL20 && !bqs.b()) {
            this.x = bnq.a("title.oldgl1", new Object[0]);
            this.y = bnq.a("title.oldgl2", new Object[0]);
            this.z = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
        }
    }

    @Override
    public void e() {
        ++this.t;
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    protected void a(char c2, int n2) {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void b() {
        this.u = new blz(256, 256);
        this.J = this.j.P().a("background", this.u);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        if (calendar.get(2) + 1 == 12 && calendar.get(5) == 24) {
            this.r = "Merry X-mas!";
        } else if (calendar.get(2) + 1 == 1 && calendar.get(5) == 1) {
            this.r = "Happy new year!";
        } else if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31) {
            this.r = "OOoooOOOoooo! Spooky!";
        }
        int \u26032 = 24;
        int \u26033 = this.m / 4 + 48;
        if (this.j.t()) {
            this.c(\u26033, 24);
        } else {
            this.b(\u26033, 24);
        }
        this.n.add(new avs(0, this.l / 2 - 100, \u26033 + 72 + 12, 98, 20, bnq.a("menu.options", new Object[0])));
        this.n.add(new avs(4, this.l / 2 + 2, \u26033 + 72 + 12, 98, 20, bnq.a("menu.quit", new Object[0])));
        this.n.add(new avz(5, this.l / 2 - 124, \u26033 + 72 + 12));
        Object object = this.w;
        synchronized (object) {
            this.E = this.q.a(this.x);
            this.D = this.q.a(this.y);
            int n2 = Math.max(this.E, this.D);
            this.F = (this.l - n2) / 2;
            this.G = ((avs)this.n.get((int)0)).i - 24;
            this.H = this.F + n2;
            this.I = this.G + 24;
        }
        this.j.a(false);
    }

    private void b(int n2, int n3) {
        this.n.add(new avs(1, this.l / 2 - 100, n2, bnq.a("menu.singleplayer", new Object[0])));
        this.n.add(new avs(2, this.l / 2 - 100, n2 + n3 * 1, bnq.a("menu.multiplayer", new Object[0])));
        this.K = new avs(14, this.l / 2 - 100, n2 + n3 * 2, bnq.a("menu.online", new Object[0]));
        this.n.add(this.K);
    }

    private void c(int n2, int n3) {
        this.n.add(new avs(11, this.l / 2 - 100, n2, bnq.a("menu.playdemo", new Object[0])));
        this.s = new avs(12, this.l / 2 - 100, n2 + n3 * 1, bnq.a("menu.resetdemo", new Object[0]));
        this.n.add(this.s);
        atr atr2 = this.j.f();
        ato \u26032 = atr2.c("Demo_World");
        if (\u26032 == null) {
            this.s.l = false;
        }
    }

    @Override
    protected void a(avs avs2) {
        if (avs2.k == 0) {
            this.j.a(new axn(this, this.j.t));
        }
        if (avs2.k == 5) {
            this.j.a(new axl(this, this.j.t, this.j.S()));
        }
        if (avs2.k == 1) {
            this.j.a(new axv(this));
        }
        if (avs2.k == 2) {
            this.j.a(new azh(this));
        }
        if (avs2.k == 14 && this.K.m) {
            this.a();
        }
        if (avs2.k == 4) {
            this.j.m();
        }
        if (avs2.k == 11) {
            this.j.a("Demo_World", "Demo_World", kx.a);
        }
        if (avs2.k == 12 && (\u2603 = (\u2603 = this.j.f()).c("Demo_World")) != null) {
            awy awy2 = axv.a(this, \u2603.k(), 12);
            this.j.a(awy2);
        }
    }

    private void a() {
        RealmsBridge realmsBridge = new RealmsBridge();
        realmsBridge.switchToRealms(this);
    }

    @Override
    public void a(boolean bl2, int n22) {
        int n22;
        if (bl2 && n22 == 12) {
            atr atr2 = this.j.f();
            atr2.d();
            atr2.e("Demo_World");
            this.j.a(this);
        } else if (n22 == 13) {
            if (bl2) {
                try {
                    Class<?> clazz = Class.forName("java.awt.Desktop");
                    Object \u26032 = clazz.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
                    clazz.getMethod("browse", URI.class).invoke(\u26032, new URI(this.z));
                }
                catch (Throwable throwable) {
                    g.error("Couldn't open link", throwable);
                }
            }
            this.j.a(this);
        }
    }

    private void b(int n2, int n3, float f2) {
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        bfl.n(5889);
        bfl.E();
        bfl.D();
        Project.gluPerspective(120.0f, 1.0f, 0.05f, 10.0f);
        bfl.n(5888);
        bfl.E();
        bfl.D();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.b(180.0f, 1.0f, 0.0f, 0.0f);
        bfl.b(90.0f, 0.0f, 0.0f, 1.0f);
        bfl.l();
        bfl.c();
        bfl.p();
        bfl.a(false);
        bfl.a(770, 771, 1, 0);
        int \u26033 = 8;
        for (int i2 = 0; i2 < \u26033 * \u26033; ++i2) {
            bfl.E();
            float f3 = ((float)(i2 % \u26033) / (float)\u26033 - 0.5f) / 64.0f;
            \u2603 = ((float)(i2 / \u26033) / (float)\u26033 - 0.5f) / 64.0f;
            \u2603 = 0.0f;
            bfl.b(f3, \u2603, \u2603);
            bfl.b(ns.a(((float)this.t + f2) / 400.0f) * 25.0f + 20.0f, 1.0f, 0.0f, 0.0f);
            bfl.b(-((float)this.t + f2) * 0.1f, 0.0f, 1.0f, 0.0f);
            for (int i3 = 0; i3 < 6; ++i3) {
                bfl.E();
                if (i3 == 1) {
                    bfl.b(90.0f, 0.0f, 1.0f, 0.0f);
                }
                if (i3 == 2) {
                    bfl.b(180.0f, 0.0f, 1.0f, 0.0f);
                }
                if (i3 == 3) {
                    bfl.b(-90.0f, 0.0f, 1.0f, 0.0f);
                }
                if (i3 == 4) {
                    bfl.b(90.0f, 1.0f, 0.0f, 0.0f);
                }
                if (i3 == 5) {
                    bfl.b(-90.0f, 1.0f, 0.0f, 0.0f);
                }
                this.j.P().a(C[i3]);
                \u26032.a(7, bms.i);
                \u2603 = 255 / (i2 + 1);
                float f4 = 0.0f;
                \u26032.b(-1.0, -1.0, 1.0).a(0.0, 0.0).b(255, 255, 255, \u2603).d();
                \u26032.b(1.0, -1.0, 1.0).a(1.0, 0.0).b(255, 255, 255, \u2603).d();
                \u26032.b(1.0, 1.0, 1.0).a(1.0, 1.0).b(255, 255, 255, \u2603).d();
                \u26032.b(-1.0, 1.0, 1.0).a(0.0, 1.0).b(255, 255, 255, \u2603).d();
                bfx2.b();
                bfl.F();
            }
            bfl.F();
            bfl.a(true, true, true, false);
        }
        \u26032.c(0.0, 0.0, 0.0);
        bfl.a(true, true, true, true);
        bfl.n(5889);
        bfl.F();
        bfl.n(5888);
        bfl.F();
        bfl.a(true);
        bfl.o();
        bfl.j();
    }

    private void a(float f2) {
        this.j.P().a(this.J);
        GL11.glTexParameteri(3553, 10241, 9729);
        GL11.glTexParameteri(3553, 10240, 9729);
        GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
        bfl.l();
        bfl.a(770, 771, 1, 0);
        bfl.a(true, true, true, false);
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        \u26032.a(7, bms.i);
        bfl.c();
        int \u26033 = 3;
        for (int i2 = 0; i2 < \u26033; ++i2) {
            float f3 = 1.0f / (float)(i2 + 1);
            int \u26034 = this.l;
            int \u26035 = this.m;
            \u2603 = (float)(i2 - \u26033 / 2) / 256.0f;
            \u26032.b((double)\u26034, (double)\u26035, (double)this.e).a(0.0f + \u2603, 1.0).a(1.0f, 1.0f, 1.0f, f3).d();
            \u26032.b((double)\u26034, 0.0, (double)this.e).a(1.0f + \u2603, 1.0).a(1.0f, 1.0f, 1.0f, f3).d();
            \u26032.b(0.0, 0.0, (double)this.e).a(1.0f + \u2603, 0.0).a(1.0f, 1.0f, 1.0f, f3).d();
            \u26032.b(0.0, (double)\u26035, (double)this.e).a(0.0f + \u2603, 0.0).a(1.0f, 1.0f, 1.0f, f3).d();
        }
        bfx2.b();
        bfl.d();
        bfl.a(true, true, true, true);
    }

    private void c(int n2, int n3, float f2) {
        this.j.b().e();
        bfl.b(0, 0, 256, 256);
        this.b(n2, n3, f2);
        this.a(f2);
        this.a(f2);
        this.a(f2);
        this.a(f2);
        this.a(f2);
        this.a(f2);
        this.a(f2);
        this.j.b().a(true);
        bfl.b(0, 0, this.j.d, this.j.e);
        \u2603 = this.l > this.m ? 120.0f / (float)this.l : 120.0f / (float)this.m;
        \u2603 = (float)this.m * \u2603 / 256.0f;
        \u2603 = (float)this.l * \u2603 / 256.0f;
        int n4 = this.l;
        \u2603 = this.m;
        bfx \u26032 = bfx.a();
        bfd \u26033 = \u26032.c();
        \u26033.a(7, bms.i);
        \u26033.b(0.0, (double)\u2603, (double)this.e).a(0.5f - \u2603, 0.5f + \u2603).a(1.0f, 1.0f, 1.0f, 1.0f).d();
        \u26033.b((double)n4, (double)\u2603, (double)this.e).a(0.5f - \u2603, 0.5f - \u2603).a(1.0f, 1.0f, 1.0f, 1.0f).d();
        \u26033.b((double)n4, 0.0, (double)this.e).a(0.5f + \u2603, 0.5f - \u2603).a(1.0f, 1.0f, 1.0f, 1.0f).d();
        \u26033.b(0.0, 0.0, (double)this.e).a(0.5f + \u2603, 0.5f + \u2603).a(1.0f, 1.0f, 1.0f, 1.0f).d();
        \u26032.b();
    }

    @Override
    public void a(int n2, int n3, float f2) {
        bfl.c();
        this.c(n2, n3, f2);
        bfl.d();
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        int \u26033 = 274;
        int \u26034 = this.l / 2 - \u26033 / 2;
        int \u26035 = 30;
        this.a(0, 0, this.l, this.m, -2130706433, 0xFFFFFF);
        this.a(0, 0, this.l, this.m, 0, Integer.MIN_VALUE);
        this.j.P().a(B);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        if ((double)this.i < 1.0E-4) {
            this.b(\u26034 + 0, \u26035 + 0, 0, 0, 99, 44);
            this.b(\u26034 + 99, \u26035 + 0, 129, 0, 27, 44);
            this.b(\u26034 + 99 + 26, \u26035 + 0, 126, 0, 3, 44);
            this.b(\u26034 + 99 + 26 + 3, \u26035 + 0, 99, 0, 26, 44);
            this.b(\u26034 + 155, \u26035 + 0, 0, 45, 155, 44);
        } else {
            this.b(\u26034 + 0, \u26035 + 0, 0, 0, 155, 44);
            this.b(\u26034 + 155, \u26035 + 0, 0, 45, 155, 44);
        }
        bfl.E();
        bfl.b(this.l / 2 + 90, 70.0f, 0.0f);
        bfl.b(-20.0f, 0.0f, 0.0f, 1.0f);
        float \u26036 = 1.8f - ns.e(ns.a((float)(ave.J() % 1000L) / 1000.0f * (float)Math.PI * 2.0f) * 0.1f);
        \u26036 = \u26036 * 100.0f / (float)(this.q.a(this.r) + 32);
        bfl.a(\u26036, \u26036, \u26036);
        this.a(this.q, this.r, 0, -8, -256);
        bfl.F();
        String \u26037 = "Minecraft 1.8.8";
        if (this.j.t()) {
            \u26037 = \u26037 + " Demo";
        }
        this.c(this.q, \u26037, 2, this.m - 10, -1);
        String \u26038 = "Copyright Mojang AB. Do not distribute!";
        this.c(this.q, \u26038, this.l - this.q.a(\u26038) - 2, this.m - 10, -1);
        if (this.x != null && this.x.length() > 0) {
            aya.a(this.F - 2, this.G - 2, this.H + 2, this.I - 1, 0x55200000);
            this.c(this.q, this.x, this.F, this.G, -1);
            this.c(this.q, this.y, (this.l - this.D) / 2, ((avs)this.n.get((int)0)).i - 12, -1);
        }
        super.a(n2, n3, f2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected void a(int n2, int n3, int n4) {
        super.a(n2, n3, n4);
        Object object = this.w;
        synchronized (object) {
            if (this.x.length() > 0 && n2 >= this.F && n2 <= this.H && n3 >= this.G && n3 <= this.I) {
                aww aww2 = new aww((awx)this, this.z, 13, true);
                aww2.f();
                this.j.a(aww2);
            }
        }
    }
}

