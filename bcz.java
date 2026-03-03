/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bcz
implements amv {
    private static final Logger a = LogManager.getLogger();
    private amy b;
    private nq<amy> c = new nq();
    private List<amy> d = Lists.newArrayList();
    private adm e;

    public bcz(adm adm2) {
        this.b = new amx(adm2, 0, 0);
        this.e = adm2;
    }

    @Override
    public boolean a(int n2, int n3) {
        return true;
    }

    public void b(int n2, int n3) {
        amy amy2 = this.d(n2, n3);
        if (!amy2.f()) {
            amy2.d();
        }
        this.c.d(adg.a(n2, n3));
        this.d.remove(amy2);
    }

    public amy c(int n2, int n3) {
        amy amy2 = new amy(this.e, n2, n3);
        this.c.a(adg.a(n2, n3), amy2);
        this.d.add(amy2);
        amy2.c(true);
        return amy2;
    }

    @Override
    public amy d(int n2, int n3) {
        amy amy2 = this.c.a(adg.a(n2, n3));
        if (amy2 == null) {
            return this.b;
        }
        return amy2;
    }

    @Override
    public boolean a(boolean bl2, nu nu2) {
        return true;
    }

    @Override
    public void c() {
    }

    @Override
    public boolean d() {
        long l2 = System.currentTimeMillis();
        for (amy amy2 : this.d) {
            amy2.b(System.currentTimeMillis() - l2 > 5L);
        }
        if (System.currentTimeMillis() - l2 > 100L) {
            a.info("Warning: Clientside chunk ticking took {} ms", System.currentTimeMillis() - l2);
        }
        return false;
    }

    @Override
    public boolean e() {
        return false;
    }

    @Override
    public void a(amv amv2, int n2, int n3) {
    }

    @Override
    public boolean a(amv amv2, amy amy2, int n2, int n3) {
        return false;
    }

    @Override
    public String f() {
        return "MultiplayerChunkCache: " + this.c.a() + ", " + this.d.size();
    }

    @Override
    public List<ady.c> a(pt pt2, cj cj2) {
        return null;
    }

    @Override
    public cj a(adm adm2, String string, cj cj2) {
        return null;
    }

    @Override
    public int g() {
        return this.d.size();
    }

    @Override
    public void a(amy amy2, int n2, int n3) {
    }

    @Override
    public amy a(cj cj2) {
        return this.d(cj2.n() >> 4, cj2.p() >> 4);
    }
}

