/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import tv.twitch.ErrorCode;

public class baa
extends axu {
    private static final Logger a = LogManager.getLogger();
    private final eu f = new fb("stream.unavailable.title", new Object[0]);
    private final axu g;
    private final a h;
    private final List<fb> i;
    private final List<String> r = Lists.newArrayList();

    public baa(axu axu2, a a2) {
        this(axu2, a2, null);
    }

    public baa(axu axu2, a a2, List<fb> list) {
        this.g = axu2;
        this.h = a2;
        this.i = list;
    }

    @Override
    public void b() {
        if (this.r.isEmpty()) {
            this.r.addAll(this.q.c(this.h.a().d(), (int)((float)this.l * 0.75f)));
            if (this.i != null) {
                this.r.add("");
                for (fb fb2 : this.i) {
                    this.r.add(fb2.e());
                }
            }
        }
        if (this.h.b() != null) {
            this.n.add(new avs(0, this.l / 2 - 155, this.m - 50, 150, 20, bnq.a("gui.cancel", new Object[0])));
            this.n.add(new avs(1, this.l / 2 - 155 + 160, this.m - 50, 150, 20, bnq.a(this.h.b().d(), new Object[0])));
        } else {
            this.n.add(new avs(0, this.l / 2 - 75, this.m - 50, 150, 20, bnq.a("gui.cancel", new Object[0])));
        }
    }

    @Override
    public void m() {
    }

    @Override
    public void a(int n22, int n3, float f2) {
        int n22;
        this.c();
        int n4 = Math.max((int)((double)this.m * 0.85 / 2.0 - (double)((float)(this.r.size() * this.q.a) / 2.0f)), 50);
        this.a(this.q, this.f.d(), this.l / 2, n4 - this.q.a * 2, 0xFFFFFF);
        for (String string : this.r) {
            this.a(this.q, string, this.l / 2, n4, 0xA0A0A0);
            n4 += this.q.a;
        }
        super.a(n22, n3, f2);
    }

    @Override
    protected void a(avs avs2) {
        if (!avs2.l) {
            return;
        }
        if (avs2.k == 1) {
            switch (this.h) {
                case h: 
                case i: {
                    this.a("https://account.mojang.com/me/settings");
                    break;
                }
                case g: {
                    this.a("https://account.mojang.com/migrate");
                    break;
                }
                case e: {
                    this.a("http://www.apple.com/osx/");
                    break;
                }
                case l: 
                case c: 
                case k: {
                    this.a("http://bugs.mojang.com/browse/MC");
                }
            }
        }
        this.j.a(this.g);
    }

    private void a(String string) {
        try {
            Class<?> clazz = Class.forName("java.awt.Desktop");
            Object \u26032 = clazz.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
            clazz.getMethod("browse", URI.class).invoke(\u26032, new URI(string));
        }
        catch (Throwable throwable) {
            a.error("Couldn't open link", throwable);
        }
    }

    public static void a(axu axu2) {
        ave ave2 = ave.A();
        bqm \u26032 = ave2.Y();
        if (!bqs.l) {
            ArrayList<fb> arrayList = Lists.newArrayList();
            arrayList.add(new fb("stream.unavailable.no_fbo.version", GL11.glGetString(7938)));
            arrayList.add(new fb("stream.unavailable.no_fbo.blend", GLContext.getCapabilities().GL_EXT_blend_func_separate));
            arrayList.add(new fb("stream.unavailable.no_fbo.arb", GLContext.getCapabilities().GL_ARB_framebuffer_object));
            arrayList.add(new fb("stream.unavailable.no_fbo.ext", GLContext.getCapabilities().GL_EXT_framebuffer_object));
            ave2.a(new baa(axu2, baa$a.a, arrayList));
        } else if (\u26032 instanceof bqo) {
            if (((bqo)\u26032).a().getMessage().contains("Can't load AMD 64-bit .dll on a IA 32-bit platform")) {
                ave2.a(new baa(axu2, baa$a.b));
            } else {
                ave2.a(new baa(axu2, baa$a.c));
            }
        } else if (!\u26032.A() && \u26032.B() == ErrorCode.TTV_EC_OS_TOO_OLD) {
            switch (g.a()) {
                case c: {
                    ave2.a(new baa(axu2, baa$a.d));
                    break;
                }
                case d: {
                    ave2.a(new baa(axu2, baa$a.e));
                    break;
                }
                default: {
                    ave2.a(new baa(axu2, baa$a.f));
                    break;
                }
            }
        } else if (!ave2.M().containsKey("twitch_access_token")) {
            if (ave2.L().f() == avm.a.a) {
                ave2.a(new baa(axu2, baa$a.g));
            } else {
                ave2.a(new baa(axu2, baa$a.h));
            }
        } else if (!\u26032.C()) {
            switch (\u26032.E()) {
                case b: {
                    ave2.a(new baa(axu2, baa$a.i));
                    break;
                }
                default: {
                    ave2.a(new baa(axu2, baa$a.j));
                    break;
                }
            }
        } else if (\u26032.B() != null) {
            List<fb> list = Arrays.asList(new fb("stream.unavailable.initialization_failure.extra", ErrorCode.getString(\u26032.B())));
            ave2.a(new baa(axu2, baa$a.k, list));
        } else {
            ave2.a(new baa(axu2, baa$a.l));
        }
    }

    public static enum a {
        a(new fb("stream.unavailable.no_fbo", new Object[0])),
        b(new fb("stream.unavailable.library_arch_mismatch", new Object[0])),
        c(new fb("stream.unavailable.library_failure", new Object[0]), new fb("stream.unavailable.report_to_mojang", new Object[0])),
        d(new fb("stream.unavailable.not_supported.windows", new Object[0])),
        e(new fb("stream.unavailable.not_supported.mac", new Object[0]), new fb("stream.unavailable.not_supported.mac.okay", new Object[0])),
        f(new fb("stream.unavailable.not_supported.other", new Object[0])),
        g(new fb("stream.unavailable.account_not_migrated", new Object[0]), new fb("stream.unavailable.account_not_migrated.okay", new Object[0])),
        h(new fb("stream.unavailable.account_not_bound", new Object[0]), new fb("stream.unavailable.account_not_bound.okay", new Object[0])),
        i(new fb("stream.unavailable.failed_auth", new Object[0]), new fb("stream.unavailable.failed_auth.okay", new Object[0])),
        j(new fb("stream.unavailable.failed_auth_error", new Object[0])),
        k(new fb("stream.unavailable.initialization_failure", new Object[0]), new fb("stream.unavailable.report_to_mojang", new Object[0])),
        l(new fb("stream.unavailable.unknown", new Object[0]), new fb("stream.unavailable.report_to_mojang", new Object[0]));

        private final eu m;
        private final eu n;

        private a(eu eu2) {
            this(eu2, null);
        }

        private a(eu eu2, eu eu3) {
            this.m = eu2;
            this.n = eu3;
        }

        public eu a() {
            return this.m;
        }

        public eu b() {
            return this.n;
        }
    }
}

