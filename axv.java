/*
 * Decompiled with CFR 0.152.
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class axv
extends axu
implements awx {
    private static final Logger g = LogManager.getLogger();
    private final DateFormat h = new SimpleDateFormat();
    protected axu a;
    protected String f = "Select world";
    private boolean i;
    private int r;
    private List<ats> s;
    private a t;
    private String u;
    private String v;
    private String[] w = new String[4];
    private boolean x;
    private avs y;
    private avs z;
    private avs A;
    private avs B;

    public axv(axu axu2) {
        this.a = axu2;
    }

    @Override
    public void b() {
        this.f = bnq.a("selectWorld.title", new Object[0]);
        try {
            this.f();
        }
        catch (atq atq2) {
            g.error("Couldn't load level list", (Throwable)atq2);
            this.j.a(new axj("Unable to load worlds", atq2.getMessage()));
            return;
        }
        this.u = bnq.a("selectWorld.world", new Object[0]);
        this.v = bnq.a("selectWorld.conversion", new Object[0]);
        this.w[adp.a.b.a()] = bnq.a("gameMode.survival", new Object[0]);
        this.w[adp.a.c.a()] = bnq.a("gameMode.creative", new Object[0]);
        this.w[adp.a.d.a()] = bnq.a("gameMode.adventure", new Object[0]);
        this.w[adp.a.e.a()] = bnq.a("gameMode.spectator", new Object[0]);
        this.t = new a(this.j);
        this.t.d(4, 5);
        this.a();
    }

    @Override
    public void k() {
        super.k();
        this.t.p();
    }

    private void f() throws atq {
        atr atr2 = this.j.f();
        this.s = atr2.b();
        Collections.sort(this.s);
        this.r = -1;
    }

    protected String b(int n2) {
        return this.s.get(n2).a();
    }

    protected String h(int n2) {
        String string = this.s.get(n2).b();
        if (StringUtils.isEmpty(string)) {
            string = bnq.a("selectWorld.world", new Object[0]) + " " + (n2 + 1);
        }
        return string;
    }

    public void a() {
        this.z = new avs(1, this.l / 2 - 154, this.m - 52, 150, 20, bnq.a("selectWorld.select", new Object[0]));
        this.n.add(this.z);
        this.n.add(new avs(3, this.l / 2 + 4, this.m - 52, 150, 20, bnq.a("selectWorld.create", new Object[0])));
        this.A = new avs(6, this.l / 2 - 154, this.m - 28, 72, 20, bnq.a("selectWorld.rename", new Object[0]));
        this.n.add(this.A);
        this.y = new avs(2, this.l / 2 - 76, this.m - 28, 72, 20, bnq.a("selectWorld.delete", new Object[0]));
        this.n.add(this.y);
        this.B = new avs(7, this.l / 2 + 4, this.m - 28, 72, 20, bnq.a("selectWorld.recreate", new Object[0]));
        this.n.add(this.B);
        this.n.add(new avs(0, this.l / 2 + 82, this.m - 28, 72, 20, bnq.a("gui.cancel", new Object[0])));
        this.z.l = false;
        this.y.l = false;
        this.A.l = false;
        this.B.l = false;
    }

    @Override
    protected void a(avs avs22) {
        avs avs22;
        if (!avs22.l) {
            return;
        }
        if (avs22.k == 2) {
            String string = this.h(this.r);
            if (string != null) {
                this.x = true;
                awy awy2 = axv.a(this, string, this.r);
                this.j.a(awy2);
            }
        } else if (avs22.k == 1) {
            this.i(this.r);
        } else if (avs22.k == 3) {
            this.j.a(new axb(this));
        } else if (avs22.k == 6) {
            this.j.a(new axt(this, this.b(this.r)));
        } else if (avs22.k == 0) {
            this.j.a(this.a);
        } else if (avs22.k == 7) {
            axb axb2 = new axb(this);
            atp \u26032 = this.j.f().a(this.b(this.r), false);
            ato \u26033 = \u26032.d();
            \u26032.a();
            axb2.a(\u26033);
            this.j.a(axb2);
        } else {
            this.t.a(avs22);
        }
    }

    public void i(int n2) {
        this.j.a((axu)null);
        if (this.i) {
            return;
        }
        this.i = true;
        String string = this.b(n2);
        if (string == null) {
            string = "World" + n2;
        }
        if ((\u2603 = this.h(n2)) == null) {
            \u2603 = "World" + n2;
        }
        if (this.j.f().f(string)) {
            this.j.a(string, \u2603, null);
        }
    }

    @Override
    public void a(boolean bl2, int n2) {
        if (this.x) {
            this.x = false;
            if (bl2) {
                atr atr2 = this.j.f();
                atr2.d();
                atr2.e(this.b(n2));
                try {
                    this.f();
                }
                catch (atq \u26032) {
                    g.error("Couldn't load level list", (Throwable)\u26032);
                }
            }
            this.j.a(this);
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.t.a(n2, n3, f2);
        this.a(this.q, this.f, this.l / 2, 20, 0xFFFFFF);
        super.a(n2, n3, f2);
    }

    public static awy a(awx awx2, String string, int n2) {
        String string2 = bnq.a("selectWorld.deleteQuestion", new Object[0]);
        \u2603 = "'" + string + "' " + bnq.a("selectWorld.deleteWarning", new Object[0]);
        \u2603 = bnq.a("selectWorld.deleteButton", new Object[0]);
        \u2603 = bnq.a("gui.cancel", new Object[0]);
        awy \u26032 = new awy(awx2, string2, \u2603, \u2603, \u2603, n2);
        return \u26032;
    }

    class a
    extends awi {
        public a(ave ave2) {
            super(ave2, axv.this.l, axv.this.m, 32, axv.this.m - 64, 36);
        }

        @Override
        protected int b() {
            return axv.this.s.size();
        }

        @Override
        protected void a(int n2, boolean bl2, int n3, int n4) {
            axv.this.r = n2;
            ((axv)axv.this).z.l = \u2603 = axv.this.r >= 0 && axv.this.r < this.b();
            ((axv)axv.this).y.l = \u2603;
            ((axv)axv.this).A.l = \u2603;
            ((axv)axv.this).B.l = \u2603;
            if (bl2 && \u2603) {
                axv.this.i(n2);
            }
        }

        @Override
        protected boolean a(int n2) {
            return n2 == axv.this.r;
        }

        @Override
        protected int k() {
            return axv.this.s.size() * 36;
        }

        @Override
        protected void a() {
            axv.this.c();
        }

        @Override
        protected void a(int n2, int n3, int n4, int n5, int n6, int n7) {
            ats ats2 = (ats)axv.this.s.get(n2);
            String \u26032 = ats2.b();
            if (StringUtils.isEmpty(\u26032)) {
                \u26032 = axv.this.u + " " + (n2 + 1);
            }
            String \u26033 = ats2.a();
            \u26033 = \u26033 + " (" + axv.this.h.format(new Date(ats2.e()));
            \u26033 = \u26033 + ")";
            String \u26034 = "";
            if (ats2.d()) {
                \u26034 = axv.this.v + " " + \u26034;
            } else {
                \u26034 = axv.this.w[ats2.f().a()];
                if (ats2.g()) {
                    \u26034 = (Object)((Object)a.e) + bnq.a("gameMode.hardcore", new Object[0]) + (Object)((Object)a.v);
                }
                if (ats2.h()) {
                    \u26034 = \u26034 + ", " + bnq.a("selectWorld.cheats", new Object[0]);
                }
            }
            axv.this.c(axv.this.q, \u26032, n3 + 2, n4 + 1, 0xFFFFFF);
            axv.this.c(axv.this.q, \u26033, n3 + 2, n4 + 12, 0x808080);
            axv.this.c(axv.this.q, \u26034, n3 + 2, n4 + 12 + 10, 0x808080);
        }
    }
}

