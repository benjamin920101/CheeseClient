/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class aqr {
    private static final Logger a = LogManager.getLogger();
    private static Map<String, Class<? extends aqu>> b = Maps.newHashMap();
    private static Map<Class<? extends aqu>, String> c = Maps.newHashMap();
    private static Map<String, Class<? extends aqt>> d = Maps.newHashMap();
    private static Map<Class<? extends aqt>, String> e = Maps.newHashMap();

    private static void b(Class<? extends aqu> clazz, String string) {
        b.put(string, clazz);
        c.put(clazz, string);
    }

    static void a(Class<? extends aqt> clazz, String string) {
        d.put(string, clazz);
        e.put(clazz, string);
    }

    public static String a(aqu aqu2) {
        return c.get(aqu2.getClass());
    }

    public static String a(aqt aqt2) {
        return e.get(aqt2.getClass());
    }

    public static aqu a(dn dn2, adm adm2) {
        aqu aqu2 = null;
        try {
            Class<? extends aqu> clazz = b.get(dn2.j("id"));
            if (clazz != null) {
                aqu2 = clazz.newInstance();
            }
        }
        catch (Exception exception) {
            a.warn("Failed Start with id " + dn2.j("id"));
            exception.printStackTrace();
        }
        if (aqu2 != null) {
            aqu2.a(adm2, dn2);
        } else {
            a.warn("Skipping Structure with id " + dn2.j("id"));
        }
        return aqu2;
    }

    public static aqt b(dn dn2, adm adm2) {
        aqt aqt2 = null;
        try {
            Class<? extends aqt> clazz = d.get(dn2.j("id"));
            if (clazz != null) {
                aqt2 = clazz.newInstance();
            }
        }
        catch (Exception exception) {
            a.warn("Failed Piece with id " + dn2.j("id"));
            exception.printStackTrace();
        }
        if (aqt2 != null) {
            aqt2.a(adm2, dn2);
        } else {
            a.warn("Skipping Piece with id " + dn2.j("id"));
        }
        return aqt2;
    }

    static {
        aqr.b(aqh.class, "Mineshaft");
        aqr.b(aqv.a.class, "Village");
        aqr.b(aqi.a.class, "Fortress");
        aqr.b(aqo.a.class, "Stronghold");
        aqr.b(aqm.a.class, "Temple");
        aqr.b(aqk.a.class, "Monument");
        aqg.a();
        aqw.a();
        aqj.a();
        aqp.a();
        aqn.a();
        aql.a();
    }
}

