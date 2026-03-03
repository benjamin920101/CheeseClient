/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class re {
    private static final Logger a = LogManager.getLogger();
    private List<a> b = Lists.newArrayList();
    private List<a> c = Lists.newArrayList();
    private final nt d;
    private int e;
    private int f = 3;

    public re(nt nt2) {
        this.d = nt2;
    }

    public void a(int n2, rd rd2) {
        this.b.add(new a(n2, rd2));
    }

    public void a(rd rd2) {
        Iterator<a> iterator = this.b.iterator();
        while (iterator.hasNext()) {
            a a2 = iterator.next();
            rd \u26032 = a2.a;
            if (\u26032 != rd2) continue;
            if (this.c.contains(a2)) {
                \u26032.d();
                this.c.remove(a2);
            }
            iterator.remove();
        }
    }

    public void a() {
        this.d.a("goalSetup");
        if (this.e++ % this.f == 0) {
            for (a a2 : this.b) {
                boolean bl2 = this.c.contains(a2);
                if (bl2) {
                    if (this.b(a2) && this.a(a2)) continue;
                    a2.a.d();
                    this.c.remove(a2);
                }
                if (!this.b(a2) || !a2.a.a()) continue;
                a2.a.c();
                this.c.add(a2);
            }
        } else {
            Iterator<a> iterator = this.c.iterator();
            while (iterator.hasNext()) {
                a a2;
                a2 = iterator.next();
                if (this.a(a2)) continue;
                a2.a.d();
                iterator.remove();
            }
        }
        this.d.b();
        this.d.a("goalTick");
        for (a a2 : this.c) {
            a2.a.e();
        }
        this.d.b();
    }

    private boolean a(a a2) {
        boolean bl2 = a2.a.b();
        return bl2;
    }

    private boolean b(a a2) {
        for (a a3 : this.b) {
            if (a3 == a2 || !(a2.b >= a3.b ? !this.a(a2, a3) && this.c.contains(a3) : !a3.a.i() && this.c.contains(a3))) continue;
            return false;
        }
        return true;
    }

    private boolean a(a a2, a a3) {
        return (a2.a.j() & a3.a.j()) == 0;
    }

    class a {
        public rd a;
        public int b;

        public a(int n2, rd rd2) {
            this.b = n2;
            this.a = rd2;
        }
    }
}

