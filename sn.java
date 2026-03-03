/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import java.util.Collections;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class sn
extends rd {
    private static final Logger a = LogManager.getLogger();
    private ps b;
    private final Predicate<pr> c;
    private final sp.a d;
    private pr e;
    private Class<? extends pr> f;

    public sn(ps ps2, Class<? extends pr> clazz) {
        this.b = ps2;
        this.f = clazz;
        if (ps2 instanceof py) {
            a.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
        }
        this.c = new Predicate<pr>(){

            public boolean a(pr pr2) {
                double d2 = sn.this.f();
                if (pr2.av()) {
                    d2 *= (double)0.8f;
                }
                if (pr2.ax()) {
                    return false;
                }
                if ((double)pr2.g(sn.this.b) > d2) {
                    return false;
                }
                return st.a(sn.this.b, pr2, false, true);
            }

            @Override
            public /* synthetic */ boolean apply(Object object) {
                return this.a((pr)object);
            }
        };
        this.d = new sp.a(ps2);
    }

    @Override
    public boolean a() {
        double d2 = this.f();
        Predicate<pr> \u26032 = this.b.o.a(this.f, this.b.aR().b(d2, 4.0, d2), this.c);
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
        double \u26032 = this.f();
        if (this.b.h(pr2) > \u26032 * \u26032) {
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

