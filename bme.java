/*
 * Decompiled with CFR 0.152.
 */
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bme
extends bly {
    private static final Logger g = LogManager.getLogger();
    protected final jy f;

    public bme(jy jy2) {
        this.f = jy2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void a(bni bni2) throws IOException {
        this.c();
        InputStream \u26032 = null;
        try {
            bnh bnh2 = bni2.a(this.f);
            \u26032 = bnh2.b();
            BufferedImage \u26033 = bml.a(\u26032);
            boolean \u26034 = false;
            boolean \u26035 = false;
            if (bnh2.c()) {
                try {
                    bon bon2 = (bon)bnh2.a("texture");
                    if (bon2 != null) {
                        \u26034 = bon2.a();
                        \u26035 = bon2.b();
                    }
                }
                catch (RuntimeException runtimeException) {
                    g.warn("Failed reading metadata of: " + this.f, (Throwable)runtimeException);
                }
            }
            bml.a(this.b(), \u26033, \u26034, \u26035);
        }
        finally {
            if (\u26032 != null) {
                \u26032.close();
            }
        }
    }
}

