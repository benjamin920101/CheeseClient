/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import java.util.Collections;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class so
extends rd {
    private static final Logger a = LogManager.getLogger();
    private ps b;
    private final Predicate<pk> c;
    private final sp.a d;
    private pr e;

    public so(ps ps2) {
        this.b = ps2;
        if (ps2 instanceof py) {
            a.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
        }
        this.c = new Predicate<pk>(){

            public boolean a(pk pk22) {
                pk pk22;
                if (!(pk22 instanceof wn)) {
                    return false;
                }
                if (((wn)pk22).bA.a) {
                    return false;
                }
                double d2 = so.this.f();
                if (pk22.av()) {
                    d2 *= (double)0.8f;
                }
                if (pk22.ax()) {
                    float f2 = ((wn)pk22).bY();
                    if (f2 < 0.1f) {
                        f2 = 0.1f;
                    }
                    d2 *= (double)(0.7f * f2);
                }
                if ((double)pk22.g(so.this.b) > d2) {
                    return false;
                }
                return st.a(so.this.b, (pr)pk22, false, true);
            }

            @Override
            public /* synthetic */ boolean apply(Object object) {
                return this.a((pk)object);
            }
        };
        this.d = new sp.a(ps2);
    }

    @Override
    public boolean a() {
        double d2 = this.f();
        Predicate<pk> \u26032 = this.b.o.a(wn.class, this.b.aR().b(d2, 4.0, d2), this.c);
        Collections.sort(\u26032, this.d);
        if (\u26032.isEmpty()) {
            return false;
        }
        this.e = (pr)\u26032.get(0);
        return true;
    }

    @Override
    public boolean b() {
        pr pr2 = this.b.u();
        if (pr2 == null) {
            return false;
        }
        if (!pr2.ai()) {
            return false;
        }
        if (pr2 instanceof wn && ((wn)pr2).bA.a) {
            return false;
        }
        auq \u26032 = this.b.bO();
        auq \u26033 = pr2.bO();
        if (\u26032 != null && \u26033 == \u26032) {
            return false;
        }
        double \u26034 = this.f();
        if (this.b.h(pr2) > \u26034 * \u26034) {
            return false;
        }
        return !(pr2 instanceof lf) || !((lf)pr2).c.d();
    }

    @Override
    public void c() {
        this.b.d(this.e);
        super.c();
    }

    @Override
    public void d() {
        this.b.d((pr)null);
        super.c();
    }

    protected double f() {
        qc qc2 = this.b.a(vy.b);
        return qc2 == null ? 16.0 : qc2.e();
    }
}

