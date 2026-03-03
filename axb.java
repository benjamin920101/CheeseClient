/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

public class axb
extends axu {
    private axu f;
    private avw g;
    private avw h;
    private String i;
    private String r = "survival";
    private String s;
    private boolean t = true;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;
    private avs A;
    private avs B;
    private avs C;
    private avs D;
    private avs E;
    private avs F;
    private avs G;
    private String H;
    private String I;
    private String J;
    private String K;
    private int L;
    public String a = "";
    private static final String[] M = new String[]{"CON", "COM", "PRN", "AUX", "CLOCK$", "NUL", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9"};

    public axb(axu axu2) {
        this.f = axu2;
        this.J = "";
        this.K = bnq.a("selectWorld.newWorld", new Object[0]);
    }

    @Override
    public void e() {
        this.g.a();
        this.h.a();
    }

    @Override
    public void b() {
        Keyboard.enableRepeatEvents(true);
        this.n.clear();
        this.n.add(new avs(0, this.l / 2 - 155, this.m - 28, 150, 20, bnq.a("selectWorld.create", new Object[0])));
        this.n.add(new avs(1, this.l / 2 + 5, this.m - 28, 150, 20, bnq.a("gui.cancel", new Object[0])));
        this.A = new avs(2, this.l / 2 - 75, 115, 150, 20, bnq.a("selectWorld.gameMode", new Object[0]));
        this.n.add(this.A);
        this.B = new avs(3, this.l / 2 - 75, 187, 150, 20, bnq.a("selectWorld.moreWorldOptions", new Object[0]));
        this.n.add(this.B);
        this.C = new avs(4, this.l / 2 - 155, 100, 150, 20, bnq.a("selectWorld.mapFeatures", new Object[0]));
        this.n.add(this.C);
        this.C.m = false;
        this.D = new avs(7, this.l / 2 + 5, 151, 150, 20, bnq.a("selectWorld.bonusItems", new Object[0]));
        this.n.add(this.D);
        this.D.m = false;
        this.E = new avs(5, this.l / 2 + 5, 100, 150, 20, bnq.a("selectWorld.mapType", new Object[0]));
        this.n.add(this.E);
        this.E.m = false;
        this.F = new avs(6, this.l / 2 - 155, 151, 150, 20, bnq.a("selectWorld.allowCommands", new Object[0]));
        this.n.add(this.F);
        this.F.m = false;
        this.G = new avs(8, this.l / 2 + 5, 120, 150, 20, bnq.a("selectWorld.customizeType", new Object[0]));
        this.n.add(this.G);
        this.G.m = false;
        this.g = new avw(9, this.q, this.l / 2 - 100, 60, 200, 20);
        this.g.b(true);
        this.g.a(this.K);
        this.h = new avw(10, this.q, this.l / 2 - 100, 60, 200, 20);
        this.h.a(this.J);
        this.a(this.z);
        this.a();
        this.f();
    }

    private void a() {
        this.i = this.g.b().trim();
        for (char c2 : f.a) {
            this.i = this.i.replace(c2, '_');
        }
        if (StringUtils.isEmpty(this.i)) {
            this.i = "World";
        }
        this.i = axb.a(this.j.f(), this.i);
    }

    private void f() {
        this.A.j = bnq.a("selectWorld.gameMode", new Object[0]) + ": " + bnq.a("selectWorld.gameMode." + this.r, new Object[0]);
        this.H = bnq.a("selectWorld.gameMode." + this.r + ".line1", new Object[0]);
        this.I = bnq.a("selectWorld.gameMode." + this.r + ".line2", new Object[0]);
        this.C.j = bnq.a("selectWorld.mapFeatures", new Object[0]) + " ";
        this.C.j = this.t ? this.C.j + bnq.a("options.on", new Object[0]) : this.C.j + bnq.a("options.off", new Object[0]);
        this.D.j = bnq.a("selectWorld.bonusItems", new Object[0]) + " ";
        this.D.j = this.w && !this.x ? this.D.j + bnq.a("options.on", new Object[0]) : this.D.j + bnq.a("options.off", new Object[0]);
        this.E.j = bnq.a("selectWorld.mapType", new Object[0]) + " " + bnq.a(adr.a[this.L].b(), new Object[0]);
        this.F.j = bnq.a("selectWorld.allowCommands", new Object[0]) + " ";
        this.F.j = this.u && !this.x ? this.F.j + bnq.a("options.on", new Object[0]) : this.F.j + bnq.a("options.off", new Object[0]);
    }

    public static String a(atr atr2, String string) {
        string = string.replaceAll("[\\./\"]", "_");
        for (String string2 : M) {
            if (!string.equalsIgnoreCase(string2)) continue;
            string = "_" + string + "_";
        }
        while (atr2.c(string) != null) {
            string = string + "-";
        }
        return string;
    }

    @Override
    public void m() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void a(avs avs2) {
        if (!avs2.l) {
            return;
        }
        if (avs2.k == 1) {
            this.j.a(this.f);
        } else if (avs2.k == 0) {
            this.j.a((axu)null);
            if (this.y) {
                return;
            }
            this.y = true;
            long l2 = new Random().nextLong();
            String \u26032 = this.h.b();
            if (!StringUtils.isEmpty(\u26032)) {
                try {
                    \u2603 = Long.parseLong(\u26032);
                    if (\u2603 != 0L) {
                        l2 = \u2603;
                    }
                }
                catch (NumberFormatException numberFormatException) {
                    l2 = \u26032.hashCode();
                }
            }
            adp.a a2 = adp.a.a(this.r);
            adp \u26033 = new adp(l2, a2, this.t, this.x, adr.a[this.L]);
            \u26033.a(this.a);
            if (this.w && !this.x) {
                \u26033.a();
            }
            if (this.u && !this.x) {
                \u26033.b();
            }
            this.j.a(this.i, this.g.b().trim(), \u26033);
        } else if (avs2.k == 3) {
            this.h();
        } else if (avs2.k == 2) {
            if (this.r.equals("survival")) {
                if (!this.v) {
                    this.u = false;
                }
                this.x = false;
                this.r = "hardcore";
                this.x = true;
                this.F.l = false;
                this.D.l = false;
                this.f();
            } else if (this.r.equals("hardcore")) {
                if (!this.v) {
                    this.u = true;
                }
                this.x = false;
                this.r = "creative";
                this.f();
                this.x = false;
                this.F.l = true;
                this.D.l = true;
            } else {
                if (!this.v) {
                    this.u = false;
                }
                this.r = "survival";
                this.f();
                this.F.l = true;
                this.D.l = true;
                this.x = false;
            }
            this.f();
        } else if (avs2.k == 4) {
            this.t = !this.t;
            this.f();
        } else if (avs2.k == 7) {
            this.w = !this.w;
            this.f();
        } else if (avs2.k == 5) {
            ++this.L;
            if (this.L >= adr.a.length) {
                this.L = 0;
            }
            while (!this.g()) {
                ++this.L;
                if (this.L < adr.a.length) continue;
                this.L = 0;
            }
            this.a = "";
            this.f();
            this.a(this.z);
        } else if (avs2.k == 6) {
            this.v = true;
            this.u = !this.u;
            this.f();
        } else if (avs2.k == 8) {
            if (adr.a[this.L] == adr.c) {
                this.j.a(new axa(this, this.a));
            } else {
                this.j.a(new axd(this, this.a));
            }
        }
    }

    private boolean g() {
        adr adr2 = adr.a[this.L];
        if (adr2 == null || !adr2.e()) {
            return false;
        }
        if (adr2 == adr.g) {
            return axb.r();
        }
        return true;
    }

    private void h() {
        this.a(!this.z);
    }

    private void a(boolean bl2) {
        this.z = bl2;
        if (adr.a[this.L] == adr.g) {
            this.A.m = !this.z;
            this.A.l = false;
            if (this.s == null) {
                this.s = this.r;
            }
            this.r = "spectator";
            this.C.m = false;
            this.D.m = false;
            this.E.m = this.z;
            this.F.m = false;
            this.G.m = false;
        } else {
            this.A.m = !this.z;
            this.A.l = true;
            if (this.s != null) {
                this.r = this.s;
                this.s = null;
            }
            this.C.m = this.z && adr.a[this.L] != adr.f;
            this.D.m = this.z;
            this.E.m = this.z;
            this.F.m = this.z;
            this.G.m = this.z && (adr.a[this.L] == adr.c || adr.a[this.L] == adr.f);
        }
        this.f();
        this.B.j = this.z ? bnq.a("gui.done", new Object[0]) : bnq.a("selectWorld.moreWorldOptions", new Object[0]);
    }

    @Override
    protected void a(char c2, int n2) {
        if (this.g.m() && !this.z) {
            this.g.a(c2, n2);
            this.K = this.g.b();
        } else if (this.h.m() && this.z) {
            this.h.a(c2, n2);
            this.J = this.h.b();
        }
        if (n2 == 28 || n2 == 156) {
            this.a((avs)this.n.get(0));
        }
        ((avs)this.n.get((int)0)).l = this.g.b().length() > 0;
        this.a();
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        super.a(n2, n3, n4);
        if (this.z) {
            this.h.a(n2, n3, n4);
        } else {
            this.g.a(n2, n3, n4);
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.a(this.q, bnq.a("selectWorld.create", new Object[0]), this.l / 2, 20, -1);
        if (this.z) {
            this.c(this.q, bnq.a("selectWorld.enterSeed", new Object[0]), this.l / 2 - 100, 47, -6250336);
            this.c(this.q, bnq.a("selectWorld.seedInfo", new Object[0]), this.l / 2 - 100, 85, -6250336);
            if (this.C.m) {
                this.c(this.q, bnq.a("selectWorld.mapFeatures.info", new Object[0]), this.l / 2 - 150, 122, -6250336);
            }
            if (this.F.m) {
                this.c(this.q, bnq.a("selectWorld.allowCommands.info", new Object[0]), this.l / 2 - 150, 172, -6250336);
            }
            this.h.g();
            if (adr.a[this.L].h()) {
                this.q.a(bnq.a(adr.a[this.L].c(), new Object[0]), this.E.h + 2, this.E.i + 22, this.E.b(), 0xA0A0A0);
            }
        } else {
            this.c(this.q, bnq.a("selectWorld.enterName", new Object[0]), this.l / 2 - 100, 47, -6250336);
            this.c(this.q, bnq.a("selectWorld.resultFolder", new Object[0]) + " " + this.i, this.l / 2 - 100, 85, -6250336);
            this.g.g();
            this.c(this.q, this.H, this.l / 2 - 100, 137, -6250336);
            this.c(this.q, this.I, this.l / 2 - 100, 149, -6250336);
        }
        super.a(n2, n3, f2);
    }

    public void a(ato ato2) {
        this.K = bnq.a("selectWorld.newWorld.copyOf", ato2.k());
        this.J = ato2.b() + "";
        this.L = ato2.u().g();
        this.a = ato2.B();
        this.t = ato2.s();
        this.u = ato2.v();
        if (ato2.t()) {
            this.r = "hardcore";
        } else if (ato2.r().e()) {
            this.r = "survival";
        } else if (ato2.r().d()) {
            this.r = "creative";
        }
    }
}

