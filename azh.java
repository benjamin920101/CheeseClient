/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class azh
extends axu
implements awx {
    private static final Logger a = LogManager.getLogger();
    private final bdg f = new bdg();
    private axu g;
    private azl h;
    private bdf i;
    private avs r;
    private avs s;
    private avs t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private String y;
    private bde z;
    private bpq.c A;
    private bpq.b B;
    private boolean C;

    public azh(axu axu2) {
        this.g = axu2;
    }

    @Override
    public void b() {
        Keyboard.enableRepeatEvents(true);
        this.n.clear();
        if (!this.C) {
            this.C = true;
            this.i = new bdf(this.j);
            this.i.a();
            this.A = new bpq.c();
            try {
                this.B = new bpq.b(this.A);
                this.B.start();
            }
            catch (Exception exception) {
                a.warn("Unable to start LAN server detection: " + exception.getMessage());
            }
            this.h = new azl(this, this.j, this.l, this.m, 32, this.m - 64, 36);
            this.h.a(this.i);
        } else {
            this.h.a(this.l, this.m, 32, this.m - 64);
        }
        this.a();
    }

    @Override
    public void k() {
        super.k();
        this.h.p();
    }

    public void a() {
        this.r = new avs(7, this.l / 2 - 154, this.m - 28, 70, 20, bnq.a("selectServer.edit", new Object[0]));
        this.n.add(this.r);
        this.t = new avs(2, this.l / 2 - 74, this.m - 28, 70, 20, bnq.a("selectServer.delete", new Object[0]));
        this.n.add(this.t);
        this.s = new avs(1, this.l / 2 - 154, this.m - 52, 100, 20, bnq.a("selectServer.select", new Object[0]));
        this.n.add(this.s);
        this.n.add(new avs(4, this.l / 2 - 50, this.m - 52, 100, 20, bnq.a("selectServer.direct", new Object[0])));
        this.n.add(new avs(3, this.l / 2 + 4 + 50, this.m - 52, 100, 20, bnq.a("selectServer.add", new Object[0])));
        this.n.add(new avs(8, this.l / 2 + 4, this.m - 28, 70, 20, bnq.a("selectServer.refresh", new Object[0])));
        this.n.add(new avs(0, this.l / 2 + 4 + 76, this.m - 28, 75, 20, bnq.a("gui.cancel", new Object[0])));
        this.b(this.h.e());
    }

    @Override
    public void e() {
        super.e();
        if (this.A.a()) {
            List<bpq.a> list = this.A.c();
            this.A.b();
            this.h.a(list);
        }
        this.f.a();
    }

    @Override
    public void m() {
        Keyboard.enableRepeatEvents(false);
        if (this.B != null) {
            this.B.interrupt();
            this.B = null;
        }
        this.f.b();
    }

    @Override
    protected void a(avs avs22) {
        avs avs22;
        if (!avs22.l) {
            return;
        }
        awd.a a2 = \u2603 = this.h.e() < 0 ? null : this.h.b(this.h.e());
        if (avs22.k == 2 && \u2603 instanceof azk) {
            String string = ((azk)\u2603).a().a;
            if (string != null) {
                this.u = true;
                \u2603 = bnq.a("selectServer.deleteQuestion", new Object[0]);
                \u2603 = "'" + string + "' " + bnq.a("selectServer.deleteWarning", new Object[0]);
                \u2603 = bnq.a("selectServer.deleteButton", new Object[0]);
                \u2603 = bnq.a("gui.cancel", new Object[0]);
                awy awy2 = new awy(this, \u2603, \u2603, \u2603, \u2603, this.h.e());
                this.j.a(awy2);
            }
        } else if (avs22.k == 1) {
            this.f();
        } else if (avs22.k == 4) {
            this.x = true;
            this.z = new bde(bnq.a("selectServer.defaultName", new Object[0]), "", false);
            this.j.a(new axg(this, this.z));
        } else if (avs22.k == 3) {
            this.v = true;
            this.z = new bde(bnq.a("selectServer.defaultName", new Object[0]), "", false);
            this.j.a(new axi(this, this.z));
        } else if (avs22.k == 7 && \u2603 instanceof azk) {
            this.w = true;
            bde bde2 = ((azk)\u2603).a();
            this.z = new bde(bde2.a, bde2.b, false);
            this.z.a(bde2);
            this.j.a(new axi(this, this.z));
        } else if (avs22.k == 0) {
            this.j.a(this.g);
        } else if (avs22.k == 8) {
            this.i();
        }
    }

    private void i() {
        this.j.a(new azh(this.g));
    }

    @Override
    public void a(boolean bl2, int n2) {
        awd.a a2 = \u2603 = this.h.e() < 0 ? null : this.h.b(this.h.e());
        if (this.u) {
            this.u = false;
            if (bl2 && \u2603 instanceof azk) {
                this.i.b(this.h.e());
                this.i.b();
                this.h.c(-1);
                this.h.a(this.i);
            }
            this.j.a(this);
        } else if (this.x) {
            this.x = false;
            if (bl2) {
                this.a(this.z);
            } else {
                this.j.a(this);
            }
        } else if (this.v) {
            this.v = false;
            if (bl2) {
                this.i.a(this.z);
                this.i.b();
                this.h.c(-1);
                this.h.a(this.i);
            }
            this.j.a(this);
        } else if (this.w) {
            this.w = false;
            if (bl2 && \u2603 instanceof azk) {
                bde bde2 = ((azk)\u2603).a();
                bde2.a = this.z.a;
                bde2.b = this.z.b;
                bde2.a(this.z);
                this.i.b();
                this.h.a(this.i);
            }
            this.j.a(this);
        }
    }

    @Override
    protected void a(char c2, int n2) {
        \u2603 = this.h.e();
        awd.a a2 = \u2603 = \u2603 < 0 ? null : this.h.b(\u2603);
        if (n2 == 63) {
            this.i();
            return;
        }
        if (\u2603 >= 0) {
            if (n2 == 200) {
                if (azh.r()) {
                    if (\u2603 > 0 && \u2603 instanceof azk) {
                        this.i.a(\u2603, \u2603 - 1);
                        this.b(this.h.e() - 1);
                        this.h.h(-this.h.r());
                        this.h.a(this.i);
                    }
                } else if (\u2603 > 0) {
                    this.b(this.h.e() - 1);
                    this.h.h(-this.h.r());
                    if (this.h.b(this.h.e()) instanceof azi) {
                        if (this.h.e() > 0) {
                            this.b(this.h.b() - 1);
                            this.h.h(-this.h.r());
                        } else {
                            this.b(-1);
                        }
                    }
                } else {
                    this.b(-1);
                }
            } else if (n2 == 208) {
                if (azh.r()) {
                    if (\u2603 < this.i.c() - 1) {
                        this.i.a(\u2603, \u2603 + 1);
                        this.b(\u2603 + 1);
                        this.h.h(this.h.r());
                        this.h.a(this.i);
                    }
                } else if (\u2603 < this.h.b()) {
                    this.b(this.h.e() + 1);
                    this.h.h(this.h.r());
                    if (this.h.b(this.h.e()) instanceof azi) {
                        if (this.h.e() < this.h.b() - 1) {
                            this.b(this.h.b() + 1);
                            this.h.h(this.h.r());
                        } else {
                            this.b(-1);
                        }
                    }
                } else {
                    this.b(-1);
                }
            } else if (n2 == 28 || n2 == 156) {
                this.a((avs)this.n.get(2));
            } else {
                super.a(c2, n2);
            }
        } else {
            super.a(c2, n2);
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.y = null;
        this.c();
        this.h.a(n2, n3, f2);
        this.a(this.q, bnq.a("multiplayer.title", new Object[0]), this.l / 2, 20, 0xFFFFFF);
        super.a(n2, n3, f2);
        if (this.y != null) {
            this.a(Lists.newArrayList(Splitter.on("\n").split(this.y)), n2, n3);
        }
    }

    public void f() {
        awd.a a2;
        awd.a a3 = a2 = this.h.e() < 0 ? null : this.h.b(this.h.e());
        if (a2 instanceof azk) {
            this.a(((azk)a2).a());
        } else if (a2 instanceof azj) {
            bpq.a a4 = ((azj)a2).a();
            this.a(new bde(a4.a(), a4.b(), true));
        }
    }

    private void a(bde bde2) {
        this.j.a(new awz(this, this.j, bde2));
    }

    public void b(int n2) {
        this.h.c(n2);
        awd.a a2 = n2 < 0 ? null : this.h.b(n2);
        this.s.l = false;
        this.r.l = false;
        this.t.l = false;
        if (a2 != null && !(a2 instanceof azi)) {
            this.s.l = true;
            if (a2 instanceof azk) {
                this.r.l = true;
                this.t.l = true;
            }
        }
    }

    public bdg g() {
        return this.f;
    }

    public void a(String string) {
        this.y = string;
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        super.a(n2, n3, n4);
        this.h.b(n2, n3, n4);
    }

    @Override
    protected void b(int n2, int n3, int n4) {
        super.b(n2, n3, n4);
        this.h.c(n2, n3, n4);
    }

    public bdf h() {
        return this.i;
    }

    public boolean a(azk azk2, int n2) {
        return n2 > 0;
    }

    public boolean b(azk azk2, int n2) {
        return n2 < this.i.c() - 1;
    }

    public void a(azk azk2, int n2, boolean bl2) {
        int n3 = bl2 ? 0 : n2 - 1;
        this.i.a(n2, n3);
        if (this.h.e() == n2) {
            this.b(n3);
        }
        this.h.a(this.i);
    }

    public void b(azk azk2, int n2, boolean bl2) {
        int n3 = bl2 ? this.i.c() - 1 : n2 + 1;
        this.i.a(n2, n3);
        if (this.h.e() == n2) {
            this.b(n3);
        }
        this.h.a(this.i);
    }
}

