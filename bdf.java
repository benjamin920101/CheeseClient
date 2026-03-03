/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.io.File;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bdf {
    private static final Logger a = LogManager.getLogger();
    private final ave b;
    private final List<bde> c = Lists.newArrayList();

    public bdf(ave ave2) {
        this.b = ave2;
        this.a();
    }

    public void a() {
        try {
            this.c.clear();
            dn dn2 = dx.a(new File(this.b.v, "servers.dat"));
            if (dn2 == null) {
                return;
            }
            du \u26032 = dn2.c("servers", 10);
            for (int i2 = 0; i2 < \u26032.c(); ++i2) {
                this.c.add(bde.a(\u26032.b(i2)));
            }
        }
        catch (Exception exception) {
            a.error("Couldn't load server list", (Throwable)exception);
        }
    }

    public void b() {
        try {
            du du2 = new du();
            for (bde bde2 : this.c) {
                du2.a(bde2.a());
            }
            dn dn2 = new dn();
            dn2.a("servers", du2);
            dx.a(dn2, new File(this.b.v, "servers.dat"));
        }
        catch (Exception exception) {
            a.error("Couldn't save server list", (Throwable)exception);
        }
    }

    public bde a(int n2) {
        return this.c.get(n2);
    }

    public void b(int n2) {
        this.c.remove(n2);
    }

    public void a(bde bde2) {
        this.c.add(bde2);
    }

    public int c() {
        return this.c.size();
    }

    public void a(int n2, int n3) {
        bde bde2 = this.a(n2);
        this.c.set(n2, this.a(n3));
        this.c.set(n3, bde2);
        this.b();
    }

    public void a(int n2, bde bde2) {
        this.c.set(n2, bde2);
    }

    public static void b(bde bde2) {
        bdf bdf2 = new bdf(ave.A());
        bdf2.a();
        for (int i2 = 0; i2 < bdf2.c(); ++i2) {
            bde bde3 = bdf2.a(i2);
            if (!bde3.a.equals(bde2.a) || !bde3.b.equals(bde2.b)) continue;
            bdf2.a(i2, bde2);
            break;
        }
        bdf2.b();
    }
}

