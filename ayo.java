/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.gson.JsonParseException;
import io.netty.buffer.Unpooled;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class ayo
extends axu {
    private static final Logger a = LogManager.getLogger();
    private static final jy f = new jy("textures/gui/book.png");
    private final wn g;
    private final zx h;
    private final boolean i;
    private boolean r;
    private boolean s;
    private int t;
    private int u = 192;
    private int v = 192;
    private int w = 1;
    private int x;
    private du y;
    private String z = "";
    private List<eu> A;
    private int B = -1;
    private a C;
    private a D;
    private avs E;
    private avs F;
    private avs G;
    private avs H;

    public ayo(wn wn2, zx zx2, boolean bl22) {
        boolean bl22;
        this.g = wn2;
        this.h = zx2;
        this.i = bl22;
        if (zx2.n()) {
            dn dn2 = zx2.o();
            this.y = dn2.c("pages", 8);
            if (this.y != null) {
                this.y = (du)this.y.b();
                this.w = this.y.c();
                if (this.w < 1) {
                    this.w = 1;
                }
            }
        }
        if (this.y == null && bl22) {
            this.y = new du();
            this.y.a(new ea(""));
            this.w = 1;
        }
    }

    @Override
    public void e() {
        super.e();
        ++this.t;
    }

    @Override
    public void b() {
        this.n.clear();
        Keyboard.enableRepeatEvents(true);
        if (this.i) {
            this.F = new avs(3, this.l / 2 - 100, 4 + this.v, 98, 20, bnq.a("book.signButton", new Object[0]));
            this.n.add(this.F);
            this.E = new avs(0, this.l / 2 + 2, 4 + this.v, 98, 20, bnq.a("gui.done", new Object[0]));
            this.n.add(this.E);
            this.G = new avs(5, this.l / 2 - 100, 4 + this.v, 98, 20, bnq.a("book.finalizeButton", new Object[0]));
            this.n.add(this.G);
            this.H = new avs(4, this.l / 2 + 2, 4 + this.v, 98, 20, bnq.a("gui.cancel", new Object[0]));
            this.n.add(this.H);
        } else {
            this.E = new avs(0, this.l / 2 - 100, 4 + this.v, 200, 20, bnq.a("gui.done", new Object[0]));
            this.n.add(this.E);
        }
        int n2 = (this.l - this.u) / 2;
        \u2603 = 2;
        this.C = new a(1, n2 + 120, \u2603 + 154, true);
        this.n.add(this.C);
        this.D = new a(2, n2 + 38, \u2603 + 154, false);
        this.n.add(this.D);
        this.f();
    }

    @Override
    public void m() {
        Keyboard.enableRepeatEvents(false);
    }

    private void f() {
        this.C.m = !this.s && (this.x < this.w - 1 || this.i);
        this.D.m = !this.s && this.x > 0;
        boolean bl2 = this.E.m = !this.i || !this.s;
        if (this.i) {
            this.F.m = !this.s;
            this.H.m = this.s;
            this.G.m = this.s;
            this.G.l = this.z.trim().length() > 0;
        }
    }

    private void a(boolean bl2) {
        if (!this.i || !this.r) {
            return;
        }
        if (this.y != null) {
            Object object;
            while (this.y.c() > 1 && ((String)(object = this.y.f(this.y.c() - 1))).length() == 0) {
                this.y.a(this.y.c() - 1);
            }
            if (this.h.n()) {
                object = this.h.o();
                ((dn)object).a("pages", this.y);
            } else {
                this.h.a("pages", this.y);
            }
            object = "MC|BEdit";
            if (bl2) {
                object = "MC|BSign";
                this.h.a("author", new ea(this.g.e_()));
                this.h.a("title", new ea(this.z.trim()));
                for (int i2 = 0; i2 < this.y.c(); ++i2) {
                    String string = this.y.f(i2);
                    fa \u26032 = new fa(string);
                    string = eu.a.a(\u26032);
                    this.y.a(i2, new ea(string));
                }
                this.h.a(zy.bN);
            }
            em em2 = new em(Unpooled.buffer());
            em2.a(this.h);
            this.j.u().a(new im((String)object, em2));
        }
    }

    @Override
    protected void a(avs avs2) {
        if (!avs2.l) {
            return;
        }
        if (avs2.k == 0) {
            this.j.a((axu)null);
            this.a(false);
        } else if (avs2.k == 3 && this.i) {
            this.s = true;
        } else if (avs2.k == 1) {
            if (this.x < this.w - 1) {
                ++this.x;
            } else if (this.i) {
                this.g();
                if (this.x < this.w - 1) {
                    ++this.x;
                }
            }
        } else if (avs2.k == 2) {
            if (this.x > 0) {
                --this.x;
            }
        } else if (avs2.k == 5 && this.s) {
            this.a(true);
            this.j.a((axu)null);
        } else if (avs2.k == 4 && this.s) {
            this.s = false;
        }
        this.f();
    }

    private void g() {
        if (this.y == null || this.y.c() >= 50) {
            return;
        }
        this.y.a(new ea(""));
        ++this.w;
        this.r = true;
    }

    @Override
    protected void a(char c2, int n2) {
        super.a(c2, n2);
        if (!this.i) {
            return;
        }
        if (this.s) {
            this.c(c2, n2);
        } else {
            this.b(c2, n2);
        }
    }

    private void b(char c22, int n2) {
        char c22;
        if (axu.e(n2)) {
            this.b(axu.o());
            return;
        }
        switch (n2) {
            case 14: {
                String string = this.h();
                if (string.length() > 0) {
                    this.a(string.substring(0, string.length() - 1));
                }
                return;
            }
            case 28: 
            case 156: {
                this.b("\n");
                return;
            }
        }
        if (f.a(c22)) {
            this.b(Character.toString(c22));
            return;
        }
    }

    private void c(char c2, int n2) {
        switch (n2) {
            case 14: {
                if (!this.z.isEmpty()) {
                    this.z = this.z.substring(0, this.z.length() - 1);
                    this.f();
                }
                return;
            }
            case 28: 
            case 156: {
                if (!this.z.isEmpty()) {
                    this.a(true);
                    this.j.a((axu)null);
                }
                return;
            }
        }
        if (this.z.length() < 16 && f.a(c2)) {
            this.z = this.z + Character.toString(c2);
            this.f();
            this.r = true;
        }
    }

    private String h() {
        if (this.y != null && this.x >= 0 && this.x < this.y.c()) {
            return this.y.f(this.x);
        }
        return "";
    }

    private void a(String string) {
        if (this.y != null && this.x >= 0 && this.x < this.y.c()) {
            this.y.a(this.x, new ea(string));
            this.r = true;
        }
    }

    private void b(String string) {
        \u2603 = this.h();
        \u2603 = \u2603 + string;
        int n2 = this.q.b(\u2603 + "" + (Object)((Object)a.a) + "_", 118);
        if (n2 <= 128 && \u2603.length() < 256) {
            this.a(\u2603);
        }
    }

    @Override
    public void a(int n22, int n3, float f2) {
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(f);
        int n4 = (this.l - this.u) / 2;
        \u2603 = 2;
        this.b(n4, \u2603, 0, 0, this.u, this.v);
        if (this.s) {
            String string = this.z;
            if (this.i) {
                string = this.t / 6 % 2 == 0 ? string + "" + (Object)((Object)a.a) + "_" : string + "" + (Object)((Object)a.h) + "_";
            }
            \u2603 = bnq.a("book.editTitle", new Object[0]);
            int \u26032 = this.q.a(\u2603);
            this.q.a(\u2603, n4 + 36 + (116 - \u26032) / 2, \u2603 + 16 + 16, 0);
            int \u26033 = this.q.a(string);
            this.q.a(string, n4 + 36 + (116 - \u26033) / 2, \u2603 + 48, 0);
            \u2603 = bnq.a("book.byAuthor", this.g.e_());
            int \u26034 = this.q.a(\u2603);
            this.q.a((Object)((Object)a.i) + \u2603, n4 + 36 + (116 - \u26034) / 2, \u2603 + 48 + 10, 0);
            \u2603 = bnq.a("book.finalizeWarning", new Object[0]);
            this.q.a(\u2603, n4 + 36, \u2603 + 80, 116, 0);
        } else {
            String string;
            string = bnq.a("book.pageIndicator", this.x + 1, this.w);
            \u2603 = "";
            if (this.y != null && this.x >= 0 && this.x < this.y.c()) {
                \u2603 = this.y.f(this.x);
            }
            if (this.i) {
                \u2603 = this.q.b() ? \u2603 + "_" : (this.t / 6 % 2 == 0 ? \u2603 + "" + (Object)((Object)a.a) + "_" : \u2603 + "" + (Object)((Object)a.h) + "_");
            } else if (this.B != this.x) {
                eu eu2;
                if (abd.b(this.h.o())) {
                    try {
                        eu2 = eu.a.a(\u2603);
                        this.A = eu2 != null ? avu.a(eu2, 116, this.q, true, true) : null;
                    }
                    catch (JsonParseException jsonParseException) {
                        this.A = null;
                    }
                } else {
                    eu2 = new fa(a.e.toString() + "* Invalid book tag *");
                    this.A = Lists.newArrayList(eu2);
                }
                this.B = this.x;
            }
            int \u26035 = this.q.a(string);
            this.q.a(string, n4 - \u26035 + this.u - 44, \u2603 + 16, 0);
            if (this.A == null) {
                this.q.a(\u2603, n4 + 36, \u2603 + 16 + 16, 116, 0);
            } else {
                int n22;
                int n5 = Math.min(128 / this.q.a, this.A.size());
                for (\u2603 = 0; \u2603 < n5; ++\u2603) {
                    eu eu3 = this.A.get(\u2603);
                    this.q.a(eu3.c(), n4 + 36, \u2603 + 16 + 16 + \u2603 * this.q.a, 0);
                }
                eu \u26036 = this.b(n22, n3);
                if (\u26036 != null) {
                    this.a(\u26036, n22, n3);
                }
            }
        }
        super.a(n22, n3, f2);
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        if (n4 == 0 && this.a(\u2603 = this.b(n2, n3))) {
            return;
        }
        super.a(n2, n3, n4);
    }

    @Override
    protected boolean a(eu eu22) {
        eu eu22;
        et et2 = \u2603 = eu22 == null ? null : eu22.b().h();
        if (\u2603 == null) {
            return false;
        }
        if (\u2603.a() == et.a.f) {
            String string = \u2603.b();
            try {
                int n2 = Integer.parseInt(string) - 1;
                if (n2 >= 0 && n2 < this.w && n2 != this.x) {
                    this.x = n2;
                    this.f();
                    return true;
                }
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            return false;
        }
        boolean \u26032 = super.a(eu22);
        if (\u26032 && \u2603.a() == et.a.c) {
            this.j.a((axu)null);
        }
        return \u26032;
    }

    public eu b(int n2, int n3) {
        if (this.A == null) {
            return null;
        }
        \u2603 = n2 - (this.l - this.u) / 2 - 36;
        \u2603 = n3 - 2 - 16 - 16;
        if (\u2603 < 0 || \u2603 < 0) {
            return null;
        }
        \u2603 = Math.min(128 / this.q.a, this.A.size());
        if (\u2603 <= 116 && \u2603 < this.j.k.a * \u2603 + \u2603) {
            \u2603 = \u2603 / this.j.k.a;
            if (\u2603 >= 0 && \u2603 < this.A.size()) {
                eu eu2 = this.A.get(\u2603);
                int \u26032 = 0;
                for (eu eu3 : eu2) {
                    if (!(eu3 instanceof fa) || (\u26032 += this.j.k.a(((fa)eu3).g())) <= \u2603) continue;
                    return eu3;
                }
            }
            return null;
        }
        return null;
    }

    static class a
    extends avs {
        private final boolean o;

        public a(int n2, int n3, int n4, boolean bl2) {
            super(n2, n3, n4, 23, 13, "");
            this.o = bl2;
        }

        @Override
        public void a(ave ave2, int n2, int n3) {
            if (!this.m) {
                return;
            }
            boolean bl2 = n2 >= this.h && n3 >= this.i && n2 < this.h + this.f && n3 < this.i + this.g;
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            ave2.P().a(f);
            int \u26032 = 0;
            int \u26033 = 192;
            if (bl2) {
                \u26032 += 23;
            }
            if (!this.o) {
                \u26033 += 13;
            }
            this.b(this.h, this.i, \u26032, \u26033, 23, 13);
        }
    }
}

