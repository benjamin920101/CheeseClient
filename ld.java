/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ld
implements amv {
    private static final Logger b = LogManager.getLogger();
    private Set<Long> c = Collections.newSetFromMap(new ConcurrentHashMap());
    private amy d;
    private amv e;
    private and f;
    public boolean a = true;
    private nq<amy> g = new nq();
    private List<amy> h = Lists.newArrayList();
    private le i;

    public ld(le le2, and and2, amv amv2) {
        this.d = new amx(le2, 0, 0);
        this.i = le2;
        this.f = and2;
        this.e = amv2;
    }

    @Override
    public boolean a(int n2, int n3) {
        return this.g.b(adg.a(n2, n3));
    }

    public List<amy> a() {
        return this.h;
    }

    public void b(int n2, int n3) {
        if (this.i.t.e()) {
            if (!this.i.c(n2, n3)) {
                this.c.add(adg.a(n2, n3));
            }
        } else {
            this.c.add(adg.a(n2, n3));
        }
    }

    public void b() {
        for (amy amy2 : this.h) {
            this.b(amy2.a, amy2.b);
        }
    }

    public amy c(int n2, int n3) {
        long l2 = adg.a(n2, n3);
        this.c.remove(l2);
        amy \u26032 = this.g.a(l2);
        if (\u26032 == null) {
            \u26032 = this.e(n2, n3);
            if (\u26032 == null) {
                if (this.e == null) {
                    \u26032 = this.d;
                } else {
                    try {
                        \u26032 = this.e.d(n2, n3);
                    }
                    catch (Throwable throwable) {
                        b b2 = b.a(throwable, "Exception generating new chunk");
                        c \u26033 = b2.a("Chunk to be generated");
                        \u26033.a("Location", String.format("%d,%d", n2, n3));
                        \u26033.a("Position hash", l2);
                        \u26033.a("Generator", this.e.f());
                        throw new e(b2);
                    }
                }
            }
            this.g.a(l2, \u26032);
            this.h.add(\u26032);
            \u26032.c();
            \u26032.a(this, this, n2, n3);
        }
        return \u26032;
    }

    @Override
    public amy d(int n2, int n3) {
        amy amy2 = this.g.a(adg.a(n2, n3));
        if (amy2 == null) {
            if (this.i.ad() || this.a) {
                return this.c(n2, n3);
            }
            return this.d;
        }
        return amy2;
    }

    private amy e(int n2, int n3) {
        if (this.f == null) {
            return null;
        }
        try {
            amy amy2 = this.f.a(this.i, n2, n3);
            if (amy2 != null) {
                amy2.b(this.i.K());
                if (this.e != null) {
                    this.e.a(amy2, n2, n3);
                }
            }
            return amy2;
        }
        catch (Exception exception) {
            b.error("Couldn't load chunk", (Throwable)exception);
            return null;
        }
    }

    private void a(amy amy2) {
        if (this.f == null) {
            return;
        }
        try {
            this.f.b(this.i, amy2);
        }
        catch (Exception exception) {
            b.error("Couldn't save entities", (Throwable)exception);
        }
    }

    private void b(amy amy2) {
        if (this.f == null) {
            return;
        }
        try {
            amy2.b(this.i.K());
            this.f.a(this.i, amy2);
        }
        catch (IOException iOException) {
            b.error("Couldn't save chunk", (Throwable)iOException);
        }
        catch (adn adn2) {
            b.error("Couldn't save chunk; already in use by another instance of Minecraft?", (Throwable)adn2);
        }
    }

    @Override
    public void a(amv amv2, int n2, int n3) {
        amy amy2 = this.d(n2, n3);
        if (!amy2.t()) {
            amy2.n();
            if (this.e != null) {
                this.e.a(amv2, n2, n3);
                amy2.e();
            }
        }
    }

    @Override
    public boolean a(amv amv2, amy amy2, int n2, int n3) {
        if (this.e != null && this.e.a(amv2, amy2, n2, n3)) {
            amy amy3 = this.d(n2, n3);
            amy3.e();
            return true;
        }
        return false;
    }

    @Override
    public boolean a(boolean bl2, nu nu2) {
        int n2 = 0;
        ArrayList<amy> \u26032 = Lists.newArrayList(this.h);
        for (\u2603 = 0; \u2603 < \u26032.size(); ++\u2603) {
            amy amy2 = (amy)\u26032.get(\u2603);
            if (bl2) {
                this.a(amy2);
            }
            if (!amy2.a(bl2)) continue;
            this.b(amy2);
            amy2.f(false);
            if (++n2 != 24 || bl2) continue;
            return false;
        }
        return true;
    }

    @Override
    public void c() {
        if (this.f != null) {
            this.f.b();
        }
    }

    @Override
    public boolean d() {
        if (!this.i.c) {
            for (int i2 = 0; i2 < 100; ++i2) {
                if (this.c.isEmpty()) continue;
                Long l2 = this.c.iterator().next();
                amy \u26032 = this.g.a(l2);
                if (\u26032 != null) {
                    \u26032.d();
                    this.b(\u26032);
                    this.a(\u26032);
                    this.g.d(l2);
                    this.h.remove(\u26032);
                }
                this.c.remove(l2);
            }
            if (this.f != null) {
                this.f.a();
            }
        }
        return this.e.d();
    }

    @Override
    public boolean e() {
        return !this.i.c;
    }

    @Override
    public String f() {
        return "ServerChunkCache: " + this.g.a() + " Drop: " + this.c.size();
    }

    @Override
    public List<ady.c> a(pt pt2, cj cj2) {
        return this.e.a(pt2, cj2);
    }

    @Override
    public cj a(adm adm2, String string, cj cj2) {
        return this.e.a(adm2, string, cj2);
    }

    @Override
    public int g() {
        return this.g.a();
    }

    @Override
    public void a(amy amy2, int n2, int n3) {
    }

    @Override
    public amy a(cj cj2) {
        return this.d(cj2.n() >> 4, cj2.p() >> 4);
    }
}

