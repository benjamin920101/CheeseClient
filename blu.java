/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class blu {
    private static final Logger a = LogManager.getLogger();
    private static blu b;

    public static void a() {
        b = new blu();
    }

    public static blu b() {
        return b;
    }

    private blu() {
    }

    public void a(blq blq2) {
        blq2.f().b(blq2);
        blq2.e().b(blq2);
        bqs.e(blq2.h());
    }

    public int c() throws kc {
        int n2 = bqs.d();
        if (n2 <= 0) {
            throw new kc("Could not create shader program (returned program ID " + n2 + ")");
        }
        return n2;
    }

    public void b(blq blq2) throws IOException {
        blq2.f().a(blq2);
        blq2.e().a(blq2);
        bqs.f(blq2.h());
        int n2 = bqs.a(blq2.h(), bqs.m);
        if (n2 == 0) {
            a.warn("Error encountered when linking program containing VS " + blq2.e().a() + " and FS " + blq2.f().a() + ". Log output:");
            a.warn(bqs.e(blq2.h(), 32768));
        }
    }
}

