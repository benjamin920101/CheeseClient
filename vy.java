/*
 * Decompiled with CFR 0.152.
 */
import java.util.Collection;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class vy {
    private static final Logger f = LogManager.getLogger();
    public static final qb a = new qj(null, "generic.maxHealth", 20.0, 0.0, 1024.0).a("Max Health").a(true);
    public static final qb b = new qj(null, "generic.followRange", 32.0, 0.0, 2048.0).a("Follow Range");
    public static final qb c = new qj(null, "generic.knockbackResistance", 0.0, 0.0, 1.0).a("Knockback Resistance");
    public static final qb d = new qj(null, "generic.movementSpeed", 0.7f, 0.0, 1024.0).a("Movement Speed").a(true);
    public static final qb e = new qj(null, "generic.attackDamage", 2.0, 0.0, 2048.0);

    public static du a(qf qf2) {
        du du2 = new du();
        for (qc qc2 : qf2.a()) {
            du2.a(vy.a(qc2));
        }
        return du2;
    }

    private static dn a(qc qc2) {
        dn dn2 = new dn();
        qb \u26032 = qc2.a();
        dn2.a("Name", \u26032.a());
        dn2.a("Base", qc2.b());
        Collection<qd> \u26033 = qc2.c();
        if (\u26033 != null && !\u26033.isEmpty()) {
            du du2 = new du();
            for (qd qd2 : \u26033) {
                if (!qd2.e()) continue;
                du2.a(vy.a(qd2));
            }
            dn2.a("Modifiers", du2);
        }
        return dn2;
    }

    private static dn a(qd qd2) {
        dn dn2 = new dn();
        dn2.a("Name", qd2.b());
        dn2.a("Amount", qd2.d());
        dn2.a("Operation", qd2.c());
        dn2.a("UUIDMost", qd2.a().getMostSignificantBits());
        dn2.a("UUIDLeast", qd2.a().getLeastSignificantBits());
        return dn2;
    }

    public static void a(qf qf2, du du2) {
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            dn dn2 = du2.b(i2);
            qc \u26032 = qf2.a(dn2.j("Name"));
            if (\u26032 != null) {
                vy.a(\u26032, dn2);
                continue;
            }
            f.warn("Ignoring unknown attribute '" + dn2.j("Name") + "'");
        }
    }

    private static void a(qc qc2, dn dn2) {
        qc2.a(dn2.i("Base"));
        if (dn2.b("Modifiers", 9)) {
            du du2 = dn2.c("Modifiers", 10);
            for (int i2 = 0; i2 < du2.c(); ++i2) {
                qd qd2 = vy.a(du2.b(i2));
                if (qd2 == null) continue;
                \u2603 = qc2.a(qd2.a());
                if (\u2603 != null) {
                    qc2.c(\u2603);
                }
                qc2.b(qd2);
            }
        }
    }

    public static qd a(dn dn2) {
        UUID uUID = new UUID(dn2.g("UUIDMost"), dn2.g("UUIDLeast"));
        try {
            return new qd(uUID, dn2.j("Name"), dn2.i("Amount"), dn2.f("Operation"));
        }
        catch (Exception \u26032) {
            f.warn("Unable to create attribute: " + \u26032.getMessage());
            return null;
        }
    }
}

