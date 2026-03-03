/*
 * Decompiled with CFR 0.152.
 */
import java.util.UUID;

public class bmz {
    private static final jy a = new jy("textures/entity/steve.png");
    private static final jy b = new jy("textures/entity/alex.png");

    public static jy a() {
        return a;
    }

    public static jy a(UUID uUID) {
        if (bmz.c(uUID)) {
            return b;
        }
        return a;
    }

    public static String b(UUID uUID) {
        if (bmz.c(uUID)) {
            return "slim";
        }
        return "default";
    }

    private static boolean c(UUID uUID) {
        return (uUID.hashCode() & 1) == 1;
    }
}

