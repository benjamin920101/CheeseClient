/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import tv.twitch.chat.ChatUserInfo;

public abstract class axu
extends avp
implements awx {
    private static final Logger a = LogManager.getLogger();
    private static final Set<String> f = Sets.newHashSet("http", "https");
    private static final Splitter g = Splitter.on('\n');
    protected ave j;
    protected bjh k;
    public int l;
    public int m;
    protected List<avs> n = Lists.newArrayList();
    protected List<avy> o = Lists.newArrayList();
    public boolean p;
    protected avn q;
    private avs h;
    private int i;
    private long r;
    private int s;
    private URI t;

    public void a(int n2, int n3, float f2) {
        int n4;
        for (n4 = 0; n4 < this.n.size(); ++n4) {
            this.n.get(n4).a(this.j, n2, n3);
        }
        for (n4 = 0; n4 < this.o.size(); ++n4) {
            this.o.get(n4).a(this.j, n2, n3);
        }
    }

    protected void a(char c2, int n2) {
        if (n2 == 1) {
            this.j.a((axu)null);
            if (this.j.m == null) {
                this.j.n();
            }
        }
    }

    public static String o() {
        try {
            Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
            if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return (String)transferable.getTransferData(DataFlavor.stringFlavor);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return "";
    }

    public static void e(String string) {
        if (StringUtils.isEmpty(string)) {
            return;
        }
        try {
            StringSelection stringSelection = new StringSelection(string);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    protected void a(zx zx2, int n2, int n3) {
        List<String> list = zx2.a((wn)this.j.h, this.j.t.y);
        for (int i2 = 0; i2 < list.size(); ++i2) {
            if (i2 == 0) {
                list.set(i2, (Object)((Object)zx2.u().e) + list.get(i2));
                continue;
            }
            list.set(i2, (Object)((Object)a.h) + list.get(i2));
        }
        this.a(list, n2, n3);
    }

    protected void a(String string, int n2, int n3) {
        this.a(Arrays.asList(string), n2, n3);
    }

    protected void a(List<String> list, int n22, int n3) {
        int n22;
        if (list.isEmpty()) {
            return;
        }
        bfl.C();
        avc.a();
        bfl.f();
        bfl.i();
        \u2603 = 0;
        for (String string : list) {
            int n4 = this.q.a(string);
            if (n4 <= \u2603) continue;
            \u2603 = n4;
        }
        \u2603 = n22 + 12;
        \u2603 = n3 - 12;
        n4 = \u2603;
        \u2603 = 8;
        if (list.size() > 1) {
            \u2603 += 2 + (list.size() - 1) * 10;
        }
        if (\u2603 + \u2603 > this.l) {
            \u2603 -= 28 + \u2603;
        }
        if (\u2603 + \u2603 + 6 > this.m) {
            \u2603 = this.m - \u2603 - 6;
        }
        this.e = 300.0f;
        this.k.a = 300.0f;
        \u2603 = -267386864;
        this.a(\u2603 - 3, \u2603 - 4, \u2603 + n4 + 3, \u2603 - 3, \u2603, \u2603);
        this.a(\u2603 - 3, \u2603 + \u2603 + 3, \u2603 + n4 + 3, \u2603 + \u2603 + 4, \u2603, \u2603);
        this.a(\u2603 - 3, \u2603 - 3, \u2603 + n4 + 3, \u2603 + \u2603 + 3, \u2603, \u2603);
        this.a(\u2603 - 4, \u2603 - 3, \u2603 - 3, \u2603 + \u2603 + 3, \u2603, \u2603);
        this.a(\u2603 + n4 + 3, \u2603 - 3, \u2603 + n4 + 4, \u2603 + \u2603 + 3, \u2603, \u2603);
        \u2603 = 0x505000FF;
        \u2603 = (\u2603 & 0xFEFEFE) >> 1 | \u2603 & 0xFF000000;
        this.a(\u2603 - 3, \u2603 - 3 + 1, \u2603 - 3 + 1, \u2603 + \u2603 + 3 - 1, \u2603, \u2603);
        this.a(\u2603 + n4 + 2, \u2603 - 3 + 1, \u2603 + n4 + 3, \u2603 + \u2603 + 3 - 1, \u2603, \u2603);
        this.a(\u2603 - 3, \u2603 - 3, \u2603 + n4 + 3, \u2603 - 3 + 1, \u2603, \u2603);
        this.a(\u2603 - 3, \u2603 + \u2603 + 2, \u2603 + n4 + 3, \u2603 + \u2603 + 3, \u2603, \u2603);
        for (\u2603 = 0; \u2603 < list.size(); ++\u2603) {
            String string = list.get(\u2603);
            this.q.a(string, (float)\u2603, (float)\u2603, -1);
            if (\u2603 == 0) {
                \u2603 += 2;
            }
            \u2603 += 10;
        }
        this.e = 0.0f;
        this.k.a = 0.0f;
        bfl.e();
        bfl.j();
        avc.b();
        bfl.B();
    }

    protected void a(eu eu2, int n22, int n3) {
        block21: {
            if (eu2 == null || eu2.b().i() == null) {
                return;
            }
            ew ew2 = eu2.b().i();
            if (ew2.a() == ew.a.c) {
                zx zx2 = null;
                try {
                    dn dn2 = ed.a(ew2.b().c());
                    if (dn2 instanceof dn) {
                        zx2 = zx.a(dn2);
                    }
                }
                catch (ec dn2) {
                    // empty catch block
                }
                if (zx2 != null) {
                    this.a(zx2, n22, n3);
                } else {
                    this.a((Object)((Object)a.m) + "Invalid Item!", n22, n3);
                }
            } else if (ew2.a() == ew.a.d) {
                if (this.j.t.y) {
                    try {
                        int n22;
                        dn dn3 = ed.a(ew2.b().c());
                        if (dn3 instanceof dn) {
                            ArrayList<String> arrayList = Lists.newArrayList();
                            dn \u26032 = dn3;
                            arrayList.add(\u26032.j("name"));
                            if (\u26032.b("type", 8)) {
                                String string = \u26032.j("type");
                                arrayList.add("Type: " + string + " (" + pm.a(string) + ")");
                            }
                            arrayList.add(\u26032.j("id"));
                            this.a(arrayList, n22, n3);
                            break block21;
                        }
                        this.a((Object)((Object)a.m) + "Invalid Entity!", n22, n3);
                    }
                    catch (ec ec2) {
                        this.a((Object)((Object)a.m) + "Invalid Entity!", n22, n3);
                    }
                }
            } else if (ew2.a() == ew.a.a) {
                this.a(g.splitToList(ew2.b().d()), n22, n3);
            } else if (ew2.a() == ew.a.b) {
                mw mw2 = na.a(ew2.b().c());
                if (mw2 != null) {
                    eu eu3 = mw2.e();
                    fb \u26033 = new fb("stats.tooltip.type." + (mw2.d() ? "achievement" : "statistic"), new Object[0]);
                    \u26033.b().b(true);
                    String \u26034 = mw2 instanceof mq ? ((mq)mw2).f() : null;
                    ArrayList<String> \u26035 = Lists.newArrayList(eu3.d(), \u26033.d());
                    if (\u26034 != null) {
                        \u26035.addAll(this.q.c(\u26034, 150));
                    }
                    this.a(\u26035, n22, n3);
                } else {
                    this.a((Object)((Object)a.m) + "Invalid statistic/achievement!", n22, n3);
                }
            }
        }
        bfl.f();
    }

    protected void a(String string, boolean bl2) {
    }

    protected boolean a(eu eu2) {
        if (eu2 == null) {
            return false;
        }
        et et2 = eu2.b().h();
        if (axu.r()) {
            if (eu2.b().j() != null) {
                this.a(eu2.b().j(), false);
            }
        } else if (et2 != null) {
            block23: {
                if (et2.a() == et.a.a) {
                    if (!this.j.t.o) {
                        return false;
                    }
                    try {
                        URI uRI = new URI(et2.b());
                        String \u26032 = uRI.getScheme();
                        if (\u26032 == null) {
                            throw new URISyntaxException(et2.b(), "Missing protocol");
                        }
                        if (!f.contains(\u26032.toLowerCase())) {
                            throw new URISyntaxException(et2.b(), "Unsupported protocol: " + \u26032.toLowerCase());
                        }
                        if (this.j.t.p) {
                            this.t = uRI;
                            this.j.a(new aww((awx)this, et2.b(), 31102009, false));
                            break block23;
                        }
                        this.a(uRI);
                    }
                    catch (URISyntaxException uRISyntaxException) {
                        a.error("Can't open url for " + et2, (Throwable)uRISyntaxException);
                    }
                } else if (et2.a() == et.a.b) {
                    URI uRI = new File(et2.b()).toURI();
                    this.a(uRI);
                } else if (et2.a() == et.a.e) {
                    this.a(et2.b(), true);
                } else if (et2.a() == et.a.c) {
                    this.b(et2.b(), false);
                } else if (et2.a() == et.a.d) {
                    ChatUserInfo chatUserInfo = this.j.Y().e(et2.b());
                    if (chatUserInfo != null) {
                        this.j.a(new bab(this.j.Y(), chatUserInfo));
                    } else {
                        a.error("Tried to handle twitch user but couldn't find them!");
                    }
                } else {
                    a.error("Don't know how to handle " + et2);
                }
            }
            return true;
        }
        return false;
    }

    public void f(String string) {
        this.b(string, true);
    }

    public void b(String string, boolean bl2) {
        if (bl2) {
            this.j.q.d().a(string);
        }
        this.j.h.e(string);
    }

    protected void a(int n2, int n3, int n4) {
        if (n4 == 0) {
            for (\u2603 = 0; \u2603 < this.n.size(); ++\u2603) {
                avs avs2 = this.n.get(\u2603);
                if (!avs2.c(this.j, n2, n3)) continue;
                this.h = avs2;
                avs2.a(this.j.W());
                this.a(avs2);
            }
        }
    }

    protected void b(int n2, int n3, int n4) {
        if (this.h != null && n4 == 0) {
            this.h.a(n2, n3);
            this.h = null;
        }
    }

    protected void a(int n2, int n3, int n4, long l2) {
    }

    protected void a(avs avs2) {
    }

    public void a(ave ave2, int n2, int n3) {
        this.j = ave2;
        this.k = ave2.ag();
        this.q = ave2.k;
        this.l = n2;
        this.m = n3;
        this.n.clear();
        this.b();
    }

    public void b() {
    }

    public void p() {
        if (Mouse.isCreated()) {
            while (Mouse.next()) {
                this.k();
            }
        }
        if (Keyboard.isCreated()) {
            while (Keyboard.next()) {
                this.l();
            }
        }
    }

    public void k() {
        int n2 = Mouse.getEventX() * this.l / this.j.d;
        \u2603 = this.m - Mouse.getEventY() * this.m / this.j.e - 1;
        \u2603 = Mouse.getEventButton();
        if (Mouse.getEventButtonState()) {
            if (this.j.t.A && this.s++ > 0) {
                return;
            }
            this.i = \u2603;
            this.r = ave.J();
            this.a(n2, \u2603, this.i);
        } else if (\u2603 != -1) {
            if (this.j.t.A && --this.s > 0) {
                return;
            }
            this.i = -1;
            this.b(n2, \u2603, \u2603);
        } else if (this.i != -1 && this.r > 0L) {
            long l2 = ave.J() - this.r;
            this.a(n2, \u2603, this.i, l2);
        }
    }

    public void l() {
        if (Keyboard.getEventKeyState()) {
            this.a(Keyboard.getEventCharacter(), Keyboard.getEventKey());
        }
        this.j.Z();
    }

    public void e() {
    }

    public void m() {
    }

    public void c() {
        this.b_(0);
    }

    public void b_(int n2) {
        if (this.j.f != null) {
            this.a(0, 0, this.l, this.m, -1072689136, -804253680);
        } else {
            this.c(n2);
        }
    }

    public void c(int n2) {
        bfl.f();
        bfl.n();
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        this.j.P().a(b);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        float \u26033 = 32.0f;
        \u26032.a(7, bms.i);
        \u26032.b(0.0, (double)this.m, 0.0).a(0.0, (float)this.m / 32.0f + (float)n2).b(64, 64, 64, 255).d();
        \u26032.b((double)this.l, (double)this.m, 0.0).a((float)this.l / 32.0f, (float)this.m / 32.0f + (float)n2).b(64, 64, 64, 255).d();
        \u26032.b((double)this.l, 0.0, 0.0).a((float)this.l / 32.0f, (double)n2).b(64, 64, 64, 255).d();
        \u26032.b(0.0, 0.0, 0.0).a(0.0, (double)n2).b(64, 64, 64, 255).d();
        bfx2.b();
    }

    public boolean d() {
        return true;
    }

    @Override
    public void a(boolean bl2, int n2) {
        if (n2 == 31102009) {
            if (bl2) {
                this.a(this.t);
            }
            this.t = null;
            this.j.a(this);
        }
    }

    private void a(URI uRI) {
        try {
            Class<?> clazz = Class.forName("java.awt.Desktop");
            Object \u26032 = clazz.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
            clazz.getMethod("browse", URI.class).invoke(\u26032, uRI);
        }
        catch (Throwable throwable) {
            a.error("Couldn't open link", throwable);
        }
    }

    public static boolean q() {
        if (ave.a) {
            return Keyboard.isKeyDown(219) || Keyboard.isKeyDown(220);
        }
        return Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
    }

    public static boolean r() {
        return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
    }

    public static boolean s() {
        return Keyboard.isKeyDown(56) || Keyboard.isKeyDown(184);
    }

    public static boolean d(int n2) {
        return n2 == 45 && axu.q() && !axu.r() && !axu.s();
    }

    public static boolean e(int n2) {
        return n2 == 47 && axu.q() && !axu.r() && !axu.s();
    }

    public static boolean f(int n2) {
        return n2 == 46 && axu.q() && !axu.r() && !axu.s();
    }

    public static boolean g(int n2) {
        return n2 == 30 && axu.q() && !axu.r() && !axu.s();
    }

    public void b(ave ave2, int n2, int n3) {
        this.a(ave2, n2, n3);
    }
}

