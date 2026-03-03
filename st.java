/*
 * Decompiled with CFR 0.152.
 */
import org.apache.commons.lang3.StringUtils;

public abstract class st
extends rd {
    protected final py e;
    protected boolean f;
    private boolean a;
    private int b;
    private int c;
    private int d;

    public st(py py2, boolean bl2) {
        this(py2, bl2, false);
    }

    public st(py py2, boolean bl2, boolean bl3) {
        this.e = py2;
        this.f = bl2;
        this.a = bl3;
    }

    @Override
    public boolean b() {
        pr pr2 = this.e.u();
        if (pr2 == null) {
            return false;
        }
        if (!pr2.ai()) {
            return false;
        }
        auq \u26032 = this.e.bO();
        auq \u26033 = pr2.bO();
        if (\u26032 != null && \u26033 == \u26032) {
            return false;
        }
        double \u26034 = this.f();
        if (this.e.h(pr2) > \u26034 * \u26034) {
            return false;
        }
        if (this.f) {
            if (this.e.t().a(pr2)) {
                this.d = 0;
            } else if (++this.d > 60) {
                return false;
            }
        }
        return !(pr2 instanceof wn) || !((wn)pr2).bA.a;
    }

    protected double f() {
        qc qc2 = this.e.a(vy.b);
        return qc2 == null ? 16.0 : qc2.e();
    }

    @Override
    public void c() {
        this.b = 0;
        this.c = 0;
        this.d = 0;
    }

    @Override
    public void d() {
        this.e.d((pr)null);
    }

    public static boolean a(ps ps2, pr pr2, boolean bl2, boolean bl3) {
        if (pr2 == null) {
            return false;
        }
        if (pr2 == ps2) {
            return false;
        }
        if (!pr2.ai()) {
            return false;
        }
        if (!ps2.a(pr2.getClass())) {
            return false;
        }
        auq auq2 = ps2.bO();
        \u2603 = pr2.bO();
        if (auq2 != null && \u2603 == auq2) {
            return false;
        }
        if (ps2 instanceof px && StringUtils.isNotEmpty(((px)((Object)ps2)).b())) {
            if (pr2 instanceof px && ((px)((Object)ps2)).b().equals(((px)((Object)pr2)).b())) {
                return false;
            }
            if (pr2 == ((px)((Object)ps2)).m_()) {
                return false;
            }
        } else if (pr2 instanceof wn && !bl2 && ((wn)pr2).bA.a) {
            return false;
        }
        return !bl3 || ps2.t().a(pr2);
    }

    protected boolean a(pr pr2, boolean bl2) {
        if (!st.a(this.e, pr2, bl2, this.f)) {
            return false;
        }
        if (!this.e.e(new cj(pr2))) {
            return false;
        }
        if (this.a) {
            if (--this.c <= 0) {
                this.b = 0;
            }
            if (this.b == 0) {
                int n2 = this.b = this.a(pr2) ? 1 : 2;
            }
            if (this.b == 2) {
                return false;
            }
        }
        return true;
    }

    private boolean a(pr pr2) {
        this.c = 10 + this.e.bc().nextInt(5);
        asx asx2 = this.e.s().a(pr2);
        if (asx2 == null) {
            return false;
        }
        asv \u26032 = asx2.c();
        if (\u26032 == null) {
            return false;
        }
        int \u26033 = \u26032.a - ns.c(pr2.s);
        return (double)(\u26033 * \u26033 + (\u2603 = \u26032.c - ns.c(pr2.u)) * \u2603) <= 2.25;
    }
}

