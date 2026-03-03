/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class abd
extends zw {
    public abd() {
        this.c(1);
    }

    public static boolean b(dn dn2) {
        if (!abc.b(dn2)) {
            return false;
        }
        if (!dn2.b("title", 8)) {
            return false;
        }
        String string = dn2.j("title");
        if (string == null || string.length() > 32) {
            return false;
        }
        return dn2.b("author", 8);
    }

    public static int h(zx zx2) {
        return zx2.o().f("generation");
    }

    @Override
    public String a(zx zx2) {
        if (zx2.n() && !nx.b(\u2603 = (\u2603 = zx2.o()).j("title"))) {
            return \u2603;
        }
        return super.a(zx2);
    }

    @Override
    public void a(zx zx2, wn wn2, List<String> list, boolean bl2) {
        if (zx2.n()) {
            dn dn2 = zx2.o();
            String \u26032 = dn2.j("author");
            if (!nx.b(\u26032)) {
                list.add((Object)((Object)a.h) + di.a("book.byAuthor", \u26032));
            }
            list.add((Object)((Object)a.h) + di.a("book.generation." + dn2.f("generation")));
        }
    }

    @Override
    public zx a(zx zx2, adm adm2, wn wn2) {
        if (!adm2.D) {
            this.a(zx2, wn2);
        }
        wn2.a(zx2);
        wn2.b(na.ad[zw.b(this)]);
        return zx2;
    }

    private void a(zx zx2, wn wn2) {
        if (zx2 == null || zx2.o() == null) {
            return;
        }
        dn dn2 = zx2.o();
        if (dn2.n("resolved")) {
            return;
        }
        dn2.a("resolved", true);
        if (!abd.b(dn2)) {
            return;
        }
        du \u26032 = dn2.c("pages", 8);
        for (int i2 = 0; i2 < \u26032.c(); ++i2) {
            eu eu2;
            String string = \u26032.f(i2);
            try {
                eu2 = eu.a.a(string);
                eu2 = ev.a(wn2, eu2, wn2);
            }
            catch (Exception exception) {
                eu2 = new fa(string);
            }
            \u26032.a(i2, new ea(eu.a.a(eu2)));
        }
        dn2.a("pages", \u26032);
        if (wn2 instanceof lf && wn2.bZ() == zx2) {
            yg yg2 = wn2.bk.a(wn2.bi, wn2.bi.c);
            ((lf)wn2).a.a(new gf(0, yg2.e, zx2));
        }
    }

    @Override
    public boolean f(zx zx2) {
        return true;
    }
}

