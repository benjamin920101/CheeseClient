/*
 * Decompiled with CFR 0.152.
 */
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.nio.IntBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class avj {
    private static final Logger a = LogManager.getLogger();
    private static final DateFormat b = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
    private static IntBuffer c;
    private static int[] d;

    public static eu a(File file, int n2, int n3, bfw bfw2) {
        return avj.a(file, null, n2, n3, bfw2);
    }

    public static eu a(File file, String string, int n22, int n3, bfw bfw2) {
        try {
            int n22;
            File file2 = new File(file, "screenshots");
            file2.mkdir();
            if (bqs.i()) {
                n22 = bfw2.a;
                n3 = bfw2.b;
            }
            \u2603 = n22 * n3;
            if (c == null || c.capacity() < \u2603) {
                c = BufferUtils.createIntBuffer(\u2603);
                d = new int[\u2603];
            }
            GL11.glPixelStorei(3333, 1);
            GL11.glPixelStorei(3317, 1);
            c.clear();
            if (bqs.i()) {
                bfl.i(bfw2.g);
                GL11.glGetTexImage(3553, 0, 32993, 33639, c);
            } else {
                GL11.glReadPixels(0, 0, n22, n3, 32993, 33639, c);
            }
            c.get(d);
            bml.a(d, n22, n3);
            BufferedImage \u26032 = null;
            if (bqs.i()) {
                \u26032 = new BufferedImage(bfw2.c, bfw2.d, 1);
                for (\u2603 = \u2603 = bfw2.b - bfw2.d; \u2603 < bfw2.b; ++\u2603) {
                    for (\u2603 = 0; \u2603 < bfw2.c; ++\u2603) {
                        \u26032.setRGB(\u2603, \u2603 - \u2603, d[\u2603 * bfw2.a + \u2603]);
                    }
                }
            } else {
                \u26032 = new BufferedImage(n22, n3, 1);
                \u26032.setRGB(0, 0, n22, n3, d, 0, n22);
            }
            File \u26033 = string == null ? avj.a(file2) : new File(file2, string);
            ImageIO.write((RenderedImage)\u26032, "png", \u26033);
            fa \u26034 = new fa(\u26033.getName());
            \u26034.b().a(new et(et.a.b, \u26033.getAbsolutePath()));
            \u26034.b().d(true);
            return new fb("screenshot.success", \u26034);
        }
        catch (Exception exception) {
            a.warn("Couldn't save screenshot", (Throwable)exception);
            return new fb("screenshot.failure", exception.getMessage());
        }
    }

    private static File a(File file) {
        String string = b.format(new Date()).toString();
        int \u26032 = 1;
        while ((\u2603 = new File(file, string + (\u26032 == 1 ? "" : "_" + \u26032) + ".png")).exists()) {
            ++\u26032;
        }
        return \u2603;
    }
}

