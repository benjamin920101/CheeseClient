/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class aku
extends akw {
    private int a;
    private du f;
    private boolean g;
    private List<a> h;
    private List<zd> i;
    private String j;

    public void a(zx zx22) {
        this.f = null;
        if (zx22.n() && zx22.o().b("BlockEntityTag", 10)) {
            dn dn2 = zx22.o().m("BlockEntityTag");
            if (dn2.c("Patterns")) {
                this.f = (du)dn2.c("Patterns", 10).b();
            }
            this.a = dn2.b("Base", 99) ? dn2.f("Base") : zx22.i() & 0xF;
        } else {
            zx zx22;
            this.a = zx22.i() & 0xF;
        }
        this.h = null;
        this.i = null;
        this.j = "";
        this.g = true;
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        aku.a(dn2, this.a, this.f);
    }

    public static void a(dn dn2, int n2, du du2) {
        dn2.a("Base", n2);
        if (du2 != null) {
            dn2.a("Patterns", du2);
        }
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.a = dn2.f("Base");
        this.f = dn2.c("Patterns", 10);
        this.h = null;
        this.i = null;
        this.j = null;
        this.g = true;
    }

    @Override
    public ff y_() {
        dn dn2 = new dn();
        this.b(dn2);
        return new ft(this.c, 6, dn2);
    }

    public int b() {
        return this.a;
    }

    public static int b(zx zx2) {
        dn dn2 = zx2.a("BlockEntityTag", false);
        if (dn2 != null && dn2.c("Base")) {
            return dn2.f("Base");
        }
        return zx2.i();
    }

    public static int c(zx zx2) {
        dn dn2 = zx2.a("BlockEntityTag", false);
        if (dn2 != null && dn2.c("Patterns")) {
            return dn2.c("Patterns", 10).c();
        }
        return 0;
    }

    public List<a> c() {
        this.h();
        return this.h;
    }

    public du d() {
        return this.f;
    }

    public List<zd> e() {
        this.h();
        return this.i;
    }

    public String g() {
        this.h();
        return this.j;
    }

    private void h() {
        if (this.h != null && this.i != null && this.j != null) {
            return;
        }
        if (!this.g) {
            this.j = "";
            return;
        }
        this.h = Lists.newArrayList();
        this.i = Lists.newArrayList();
        this.h.add(aku$a.a);
        this.i.add(zd.a(this.a));
        this.j = "b" + this.a;
        if (this.f != null) {
            for (int i2 = 0; i2 < this.f.c(); ++i2) {
                dn dn2 = this.f.b(i2);
                a \u26032 = aku$a.a(dn2.j("Pattern"));
                if (\u26032 == null) continue;
                this.h.add(\u26032);
                int \u26033 = dn2.f("Color");
                this.i.add(zd.a(\u26033));
                this.j = this.j + \u26032.b() + \u26033;
            }
        }
    }

    public static void e(zx zx2) {
        dn dn2 = zx2.a("BlockEntityTag", false);
        if (dn2 == null || !dn2.b("Patterns", 9)) {
            return;
        }
        du \u26032 = dn2.c("Patterns", 10);
        if (\u26032.c() <= 0) {
            return;
        }
        \u26032.a(\u26032.c() - 1);
        if (\u26032.c_()) {
            zx2.o().o("BlockEntityTag");
            if (zx2.o().c_()) {
                zx2.d((dn)null);
            }
        }
    }

    public static enum a {
        a("base", "b"),
        b("square_bottom_left", "bl", "   ", "   ", "#  "),
        c("square_bottom_right", "br", "   ", "   ", "  #"),
        d("square_top_left", "tl", "#  ", "   ", "   "),
        e("square_top_right", "tr", "  #", "   ", "   "),
        f("stripe_bottom", "bs", "   ", "   ", "###"),
        g("stripe_top", "ts", "###", "   ", "   "),
        h("stripe_left", "ls", "#  ", "#  ", "#  "),
        i("stripe_right", "rs", "  #", "  #", "  #"),
        j("stripe_center", "cs", " # ", " # ", " # "),
        k("stripe_middle", "ms", "   ", "###", "   "),
        l("stripe_downright", "drs", "#  ", " # ", "  #"),
        m("stripe_downleft", "dls", "  #", " # ", "#  "),
        n("small_stripes", "ss", "# #", "# #", "   "),
        o("cross", "cr", "# #", " # ", "# #"),
        p("straight_cross", "sc", " # ", "###", " # "),
        q("triangle_bottom", "bt", "   ", " # ", "# #"),
        r("triangle_top", "tt", "# #", " # ", "   "),
        s("triangles_bottom", "bts", "   ", "# #", " # "),
        t("triangles_top", "tts", " # ", "# #", "   "),
        u("diagonal_left", "ld", "## ", "#  ", "   "),
        v("diagonal_up_right", "rd", "   ", "  #", " ##"),
        w("diagonal_up_left", "lud", "   ", "#  ", "## "),
        x("diagonal_right", "rud", " ##", "  #", "   "),
        y("circle", "mc", "   ", " # ", "   "),
        z("rhombus", "mr", " # ", "# #", " # "),
        A("half_vertical", "vh", "## ", "## ", "## "),
        B("half_horizontal", "hh", "###", "###", "   "),
        C("half_vertical_right", "vhr", " ##", " ##", " ##"),
        D("half_horizontal_bottom", "hhb", "   ", "###", "###"),
        E("border", "bo", "###", "# #", "###"),
        F("curly_border", "cbo", new zx(afi.bn)),
        G("creeper", "cre", new zx(zy.bX, 1, 4)),
        H("gradient", "gra", "# #", " # ", " # "),
        I("gradient_up", "gru", " # ", " # ", "# #"),
        J("bricks", "bri", new zx(afi.V)),
        K("skull", "sku", new zx(zy.bX, 1, 1)),
        L("flower", "flo", new zx(afi.O, 1, agw.a.j.b())),
        M("mojang", "moj", new zx(zy.ao, 1, 1));

        private String N;
        private String O;
        private String[] P = new String[3];
        private zx Q;

        private a(String string2, String string3) {
            this.N = string2;
            this.O = string3;
        }

        private a(String string2, String string3, zx zx2) {
            this(string2, string3);
            this.Q = zx2;
        }

        private a(String string2, String string3, String string4, String string5, String string6) {
            this(string2, string3);
            this.P[0] = string4;
            this.P[1] = string5;
            this.P[2] = string6;
        }

        public String a() {
            return this.N;
        }

        public String b() {
            return this.O;
        }

        public String[] c() {
            return this.P;
        }

        public boolean d() {
            return this.Q != null || this.P[0] != null;
        }

        public boolean e() {
            return this.Q != null;
        }

        public zx f() {
            return this.Q;
        }

        public static a a(String string) {
            for (a a2 : aku$a.values()) {
                if (!a2.O.equals(string)) continue;
                return a2;
            }
            return null;
        }
    }
}

