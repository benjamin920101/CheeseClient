/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.UUID;

public final class dy {
    public static GameProfile a(dn dn2) {
        String string = null;
        \u2603 = null;
        if (dn2.b("Name", 8)) {
            string = dn2.j("Name");
        }
        if (dn2.b("Id", 8)) {
            \u2603 = dn2.j("Id");
        }
        if (!nx.b(string) || !nx.b(\u2603)) {
            UUID uUID;
            try {
                uUID = UUID.fromString(\u2603);
            }
            catch (Throwable throwable) {
                uUID = null;
            }
            GameProfile \u26032 = new GameProfile(uUID, string);
            if (dn2.b("Properties", 10)) {
                dn dn3 = dn2.m("Properties");
                for (String string2 : dn3.c()) {
                    du du2 = dn3.c(string2, 10);
                    for (int i2 = 0; i2 < du2.c(); ++i2) {
                        dn dn4 = du2.b(i2);
                        String \u26033 = dn4.j("Value");
                        if (dn4.b("Signature", 8)) {
                            \u26032.getProperties().put(string2, new Property(string2, \u26033, dn4.j("Signature")));
                            continue;
                        }
                        \u26032.getProperties().put(string2, new Property(string2, \u26033));
                    }
                }
            }
            return \u26032;
        }
        return null;
    }

    public static dn a(dn dn22, GameProfile gameProfile) {
        dn dn22;
        if (!nx.b(gameProfile.getName())) {
            dn22.a("Name", gameProfile.getName());
        }
        if (gameProfile.getId() != null) {
            dn22.a("Id", gameProfile.getId().toString());
        }
        if (!gameProfile.getProperties().isEmpty()) {
            dn dn3 = new dn();
            for (String string : gameProfile.getProperties().keySet()) {
                du du2 = new du();
                for (Property property : gameProfile.getProperties().get(string)) {
                    dn dn4 = new dn();
                    dn4.a("Value", property.getValue());
                    if (property.hasSignature()) {
                        dn4.a("Signature", property.getSignature());
                    }
                    du2.a(dn4);
                }
                dn3.a(string, du2);
            }
            dn22.a("Properties", dn3);
        }
        return dn22;
    }

    public static boolean a(eb eb22, eb eb3, boolean bl2) {
        eb eb22;
        if (eb22 == eb3) {
            return true;
        }
        if (eb22 == null) {
            return true;
        }
        if (eb3 == null) {
            return false;
        }
        if (!eb22.getClass().equals(eb3.getClass())) {
            return false;
        }
        if (eb22 instanceof dn) {
            dn dn2 = (dn)eb22;
            \u2603 = (dn)eb3;
            for (String string : dn2.c()) {
                eb eb4 = dn2.a(string);
                if (dy.a(eb4, \u2603.a(string), bl2)) continue;
                return false;
            }
            return true;
        }
        if (eb22 instanceof du && bl2) {
            du du2 = (du)eb22;
            \u2603 = (du)eb3;
            if (du2.c() == 0) {
                return \u2603.c() == 0;
            }
            for (int i2 = 0; i2 < du2.c(); ++i2) {
                eb eb5 = du2.g(i2);
                boolean \u26032 = false;
                for (int i3 = 0; i3 < \u2603.c(); ++i3) {
                    if (!dy.a(eb5, \u2603.g(i3), bl2)) continue;
                    \u26032 = true;
                    break;
                }
                if (\u26032) continue;
                return false;
            }
            return true;
        }
        return eb22.equals(eb3);
    }
}

