/*
 * Decompiled with CFR 0.152.
 */
import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class aup
extends ate {
    private static final Logger b = LogManager.getLogger();
    private auo c;
    private dn d;

    public aup() {
        this("scoreboard");
    }

    public aup(String string) {
        super(string);
    }

    public void a(auo auo2) {
        this.c = auo2;
        if (this.d != null) {
            this.a(this.d);
        }
    }

    @Override
    public void a(dn dn2) {
        if (this.c == null) {
            this.d = dn2;
            return;
        }
        this.b(dn2.c("Objectives", 10));
        this.c(dn2.c("PlayerScores", 10));
        if (dn2.b("DisplaySlots", 10)) {
            this.c(dn2.m("DisplaySlots"));
        }
        if (dn2.b("Teams", 9)) {
            this.a(dn2.c("Teams", 10));
        }
    }

    protected void a(du du2) {
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            dn dn2 = du2.b(i2);
            String \u26032 = dn2.j("Name");
            if (\u26032.length() > 16) {
                \u26032 = \u26032.substring(0, 16);
            }
            aul \u26033 = this.c.e(\u26032);
            String \u26034 = dn2.j("DisplayName");
            if (\u26034.length() > 32) {
                \u26034 = \u26034.substring(0, 32);
            }
            \u26033.a(\u26034);
            if (dn2.b("TeamColor", 8)) {
                \u26033.a(a.b(dn2.j("TeamColor")));
            }
            \u26033.b(dn2.j("Prefix"));
            \u26033.c(dn2.j("Suffix"));
            if (dn2.b("AllowFriendlyFire", 99)) {
                \u26033.a(dn2.n("AllowFriendlyFire"));
            }
            if (dn2.b("SeeFriendlyInvisibles", 99)) {
                \u26033.b(dn2.n("SeeFriendlyInvisibles"));
            }
            if (dn2.b("NameTagVisibility", 8) && (\u2603 = auq.a.a(dn2.j("NameTagVisibility"))) != null) {
                \u26033.a(\u2603);
            }
            if (dn2.b("DeathMessageVisibility", 8) && (\u2603 = auq.a.a(dn2.j("DeathMessageVisibility"))) != null) {
                \u26033.b(\u2603);
            }
            this.a(\u26033, dn2.c("Players", 8));
        }
    }

    protected void a(aul aul2, du du2) {
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            this.c.a(du2.f(i2), aul2.b());
        }
    }

    protected void c(dn dn2) {
        for (int i2 = 0; i2 < 19; ++i2) {
            if (!dn2.b("slot_" + i2, 8)) continue;
            String string = dn2.j("slot_" + i2);
            auk \u26032 = this.c.b(string);
            this.c.a(i2, \u26032);
        }
    }

    protected void b(du du2) {
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            dn dn2 = du2.b(i2);
            auu \u26032 = auu.a.get(dn2.j("CriteriaName"));
            if (\u26032 == null) continue;
            String \u26033 = dn2.j("Name");
            if (\u26033.length() > 16) {
                \u26033 = \u26033.substring(0, 16);
            }
            auk \u26034 = this.c.a(\u26033, \u26032);
            \u26034.a(dn2.j("DisplayName"));
            \u26034.a(auu.a.a(dn2.j("RenderType")));
        }
    }

    protected void c(du du2) {
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            dn dn2 = du2.b(i2);
            auk \u26032 = this.c.b(dn2.j("Objective"));
            String \u26033 = dn2.j("Name");
            if (\u26033.length() > 40) {
                \u26033 = \u26033.substring(0, 40);
            }
            aum \u26034 = this.c.c(\u26033, \u26032);
            \u26034.c(dn2.f("Score"));
            if (!dn2.c("Locked")) continue;
            \u26034.a(dn2.n("Locked"));
        }
    }

    @Override
    public void b(dn dn2) {
        if (this.c == null) {
            b.warn("Tried to save scoreboard without having a scoreboard...");
            return;
        }
        dn2.a("Objectives", this.b());
        dn2.a("PlayerScores", this.e());
        dn2.a("Teams", this.a());
        this.d(dn2);
    }

    protected du a() {
        du du2 = new du();
        Collection<aul> \u26032 = this.c.g();
        for (aul aul2 : \u26032) {
            dn dn2 = new dn();
            dn2.a("Name", aul2.b());
            dn2.a("DisplayName", aul2.c());
            if (aul2.l().b() >= 0) {
                dn2.a("TeamColor", aul2.l().e());
            }
            dn2.a("Prefix", aul2.e());
            dn2.a("Suffix", aul2.f());
            dn2.a("AllowFriendlyFire", aul2.g());
            dn2.a("SeeFriendlyInvisibles", aul2.h());
            dn2.a("NameTagVisibility", aul2.i().e);
            dn2.a("DeathMessageVisibility", aul2.j().e);
            du \u26033 = new du();
            for (String string : aul2.d()) {
                \u26033.a(new ea(string));
            }
            dn2.a("Players", \u26033);
            du2.a(dn2);
        }
        return du2;
    }

    protected void d(dn dn2) {
        \u2603 = new dn();
        boolean \u26032 = false;
        for (int i2 = 0; i2 < 19; ++i2) {
            auk auk2 = this.c.a(i2);
            if (auk2 == null) continue;
            \u2603.a("slot_" + i2, auk2.b());
            \u26032 = true;
        }
        if (\u26032) {
            dn2.a("DisplaySlots", \u2603);
        }
    }

    protected du b() {
        du du2 = new du();
        Collection<auk> \u26032 = this.c.c();
        for (auk auk2 : \u26032) {
            if (auk2.c() == null) continue;
            dn dn2 = new dn();
            dn2.a("Name", auk2.b());
            dn2.a("CriteriaName", auk2.c().a());
            dn2.a("DisplayName", auk2.d());
            dn2.a("RenderType", auk2.e().a());
            du2.a(dn2);
        }
        return du2;
    }

    protected du e() {
        du du2 = new du();
        Collection<aum> \u26032 = this.c.e();
        for (aum aum2 : \u26032) {
            if (aum2.d() == null) continue;
            dn dn2 = new dn();
            dn2.a("Name", aum2.e());
            dn2.a("Objective", aum2.d().b());
            dn2.a("Score", aum2.c());
            dn2.a("Locked", aum2.g());
            du2.a(dn2);
        }
        return du2;
    }
}

