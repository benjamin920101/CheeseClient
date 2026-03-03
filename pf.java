/*
 * Decompiled with CFR 0.152.
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class pf {
    private static final Logger a = LogManager.getLogger();
    private int b;
    private int c;
    private int d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;

    public pf(int n2, int n3) {
        this(n2, n3, 0);
    }

    public pf(int n2, int n3, int n4) {
        this(n2, n3, n4, false, true);
    }

    public pf(int n2, int n3, int n4, boolean bl2, boolean bl3) {
        this.b = n2;
        this.c = n3;
        this.d = n4;
        this.f = bl2;
        this.h = bl3;
    }

    public pf(pf pf2) {
        this.b = pf2.b;
        this.c = pf2.c;
        this.d = pf2.d;
        this.f = pf2.f;
        this.h = pf2.h;
    }

    public void a(pf pf2) {
        if (this.b != pf2.b) {
            a.warn("This method should only be called for matching effects!");
        }
        if (pf2.d > this.d) {
            this.d = pf2.d;
            this.c = pf2.c;
        } else if (pf2.d == this.d && this.c < pf2.c) {
            this.c = pf2.c;
        } else if (!pf2.f && this.f) {
            this.f = pf2.f;
        }
        this.h = pf2.h;
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public void a(boolean bl2) {
        this.e = bl2;
    }

    public boolean e() {
        return this.f;
    }

    public boolean f() {
        return this.h;
    }

    public boolean a(pr pr2) {
        if (this.c > 0) {
            if (pe.a[this.b].a(this.c, this.d)) {
                this.b(pr2);
            }
            this.i();
        }
        return this.c > 0;
    }

    private int i() {
        return --this.c;
    }

    public void b(pr pr2) {
        if (this.c > 0) {
            pe.a[this.b].a(pr2, this.d);
        }
    }

    public String g() {
        return pe.a[this.b].a();
    }

    public int hashCode() {
        return this.b;
    }

    public String toString() {
        String string = "";
        string = this.c() > 0 ? this.g() + " x " + (this.c() + 1) + ", Duration: " + this.b() : this.g() + ", Duration: " + this.b();
        if (this.e) {
            string = string + ", Splash: true";
        }
        if (!this.h) {
            string = string + ", Particles: false";
        }
        if (pe.a[this.b].j()) {
            return "(" + string + ")";
        }
        return string;
    }

    public boolean equals(Object object) {
        if (!(object instanceof pf)) {
            return false;
        }
        pf pf2 = (pf)object;
        return this.b == pf2.b && this.d == pf2.d && this.c == pf2.c && this.e == pf2.e && this.f == pf2.f;
    }

    public dn a(dn dn2) {
        dn2.a("Id", (byte)this.a());
        dn2.a("Amplifier", (byte)this.c());
        dn2.a("Duration", this.b());
        dn2.a("Ambient", this.e());
        dn2.a("ShowParticles", this.f());
        return dn2;
    }

    public static pf b(dn dn2) {
        byte by = dn2.d("Id");
        if (by < 0 || by >= pe.a.length || pe.a[by] == null) {
            return null;
        }
        \u2603 = dn2.d("Amplifier");
        int \u26032 = dn2.f("Duration");
        boolean \u26033 = dn2.n("Ambient");
        boolean \u26034 = true;
        if (dn2.b("ShowParticles", 1)) {
            \u26034 = dn2.n("ShowParticles");
        }
        return new pf(by, \u26032, \u2603, \u26033, \u26034);
    }

    public void b(boolean bl2) {
        this.g = bl2;
    }

    public boolean h() {
        return this.g;
    }
}

