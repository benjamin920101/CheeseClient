/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class awv
extends axu {
    private static final Logger f = LogManager.getLogger();
    private String g = "";
    private int h = -1;
    private boolean i;
    private boolean r;
    private int s;
    private List<String> t = Lists.newArrayList();
    protected avw a;
    private String u = "";

    public awv() {
    }

    public awv(String string) {
        this.u = string;
    }

    @Override
    public void b() {
        Keyboard.enableRepeatEvents(true);
        this.h = this.j.q.d().c().size();
        this.a = new avw(0, this.q, 4, this.m - 12, this.l - 4, 12);
        this.a.f(100);
        this.a.a(false);
        this.a.b(true);
        this.a.a(this.u);
        this.a.d(false);
    }

    @Override
    public void m() {
        Keyboard.enableRepeatEvents(false);
        this.j.q.d().d();
    }

    @Override
    public void e() {
        this.a.a();
    }

    @Override
    protected void a(char c2, int n22) {
        int n22;
        this.r = false;
        if (n22 == 15) {
            this.a();
        } else {
            this.i = false;
        }
        if (n22 == 1) {
            this.j.a((axu)null);
        } else if (n22 == 28 || n22 == 156) {
            String string = this.a.b().trim();
            if (string.length() > 0) {
                this.f(string);
            }
            this.j.a((axu)null);
        } else if (n22 == 200) {
            this.b(-1);
        } else if (n22 == 208) {
            this.b(1);
        } else if (n22 == 201) {
            this.j.q.d().b(this.j.q.d().i() - 1);
        } else if (n22 == 209) {
            this.j.q.d().b(-this.j.q.d().i() + 1);
        } else {
            this.a.a(c2, n22);
        }
    }

    @Override
    public void k() {
        super.k();
        int n2 = Mouse.getEventDWheel();
        if (n2 != 0) {
            if (n2 > 1) {
                n2 = 1;
            }
            if (n2 < -1) {
                n2 = -1;
            }
            if (!awv.r()) {
                n2 *= 7;
            }
            this.j.q.d().b(n2);
        }
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        if (n4 == 0 && this.a(\u2603 = this.j.q.d().a(Mouse.getX(), Mouse.getY()))) {
            return;
        }
        this.a.a(n2, n3, n4);
        super.a(n2, n3, n4);
    }

    @Override
    protected void a(String string, boolean bl2) {
        if (bl2) {
            this.a.a(string);
        } else {
            this.a.b(string);
        }
    }

    public void a() {
        if (this.i) {
            this.a.b(this.a.a(-1, this.a.i(), false) - this.a.i());
            if (this.s >= this.t.size()) {
                this.s = 0;
            }
        } else {
            int n2 = this.a.a(-1, this.a.i(), false);
            this.t.clear();
            this.s = 0;
            String \u26032 = this.a.b().substring(n2).toLowerCase();
            String \u26033 = this.a.b().substring(0, this.a.i());
            this.a(\u26033, \u26032);
            if (this.t.isEmpty()) {
                return;
            }
            this.i = true;
            this.a.b(n2 - this.a.i());
        }
        if (this.t.size() > 1) {
            StringBuilder \u26034 = new StringBuilder();
            for (String \u26033 : this.t) {
                if (\u26034.length() > 0) {
                    \u26034.append(", ");
                }
                \u26034.append(\u26033);
            }
            this.j.q.d().a(new fa(\u26034.toString()), 1);
        }
        this.a.b(this.t.get(this.s++));
    }

    private void a(String string, String string2) {
        if (string.length() < 1) {
            return;
        }
        cj cj2 = null;
        if (this.j.s != null && this.j.s.a == auh.a.b) {
            cj2 = this.j.s.a();
        }
        this.j.h.a.a(new id(string, cj2));
        this.r = true;
    }

    public void b(int n2) {
        \u2603 = this.h + n2;
        \u2603 = this.j.q.d().c().size();
        if ((\u2603 = ns.a(\u2603, 0, \u2603)) == this.h) {
            return;
        }
        if (\u2603 == \u2603) {
            this.h = \u2603;
            this.a.a(this.g);
            return;
        }
        if (this.h == \u2603) {
            this.g = this.a.b();
        }
        this.a.a(this.j.q.d().c().get(\u2603));
        this.h = \u2603;
    }

    @Override
    public void a(int n2, int n3, float f2) {
        awv.a(2, this.m - 14, this.l - 2, this.m - 2, Integer.MIN_VALUE);
        this.a.g();
        eu eu2 = this.j.q.d().a(Mouse.getX(), Mouse.getY());
        if (eu2 != null && eu2.b().i() != null) {
            this.a(eu2, n2, n3);
        }
        super.a(n2, n3, f2);
    }

    public void a(String[] stringArray) {
        if (this.r) {
            this.i = false;
            this.t.clear();
            for (String string : stringArray) {
                if (string.length() <= 0) continue;
                this.t.add(string);
            }
            String string = this.a.b().substring(this.a.a(-1, this.a.i(), false));
            \u2603 = StringUtils.getCommonPrefix(stringArray);
            if (\u2603.length() > 0 && !string.equalsIgnoreCase(\u2603)) {
                this.a.b(this.a.a(-1, this.a.i(), false) - this.a.i());
                this.a.b(\u2603);
            } else if (this.t.size() > 0) {
                this.i = true;
                this.a();
            }
        }
    }

    @Override
    public boolean d() {
        return false;
    }
}

