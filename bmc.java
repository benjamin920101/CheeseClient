/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bmc
extends bly {
    private static final Logger f = LogManager.getLogger();
    private final jy g;
    private final List<String> h;
    private final List<zd> i;

    public bmc(jy jy2, List<String> list, List<zd> list2) {
        this.g = jy2;
        this.h = list;
        this.i = list2;
    }

    @Override
    public void a(bni bni2) throws IOException {
        this.c();
        try {
            BufferedImage bufferedImage = bml.a(bni2.a(this.g).b());
            int \u26032 = bufferedImage.getType();
            if (\u26032 == 0) {
                \u26032 = 6;
            }
            bufferedImage2 = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), \u26032);
            Graphics \u26033 = bufferedImage2.getGraphics();
            \u26033.drawImage(bufferedImage, 0, 0, null);
            for (int i2 = 0; i2 < 17 && i2 < this.h.size() && i2 < this.i.size(); ++i2) {
                BufferedImage bufferedImage2;
                String string = this.h.get(i2);
                arn \u26034 = this.i.get(i2).e();
                if (string == null || (\u2603 = bml.a(\u2603 = bni2.a(new jy(string)).b())).getWidth() != bufferedImage2.getWidth() || \u2603.getHeight() != bufferedImage2.getHeight() || \u2603.getType() != 6) continue;
                for (int i3 = 0; i3 < \u2603.getHeight(); ++i3) {
                    for (\u2603 = 0; \u2603 < \u2603.getWidth(); ++\u2603) {
                        \u2603 = \u2603.getRGB(\u2603, i3);
                        if ((\u2603 & 0xFF000000) == 0) continue;
                        \u2603 = (\u2603 & 0xFF0000) << 8 & 0xFF000000;
                        \u2603 = bufferedImage.getRGB(\u2603, i3);
                        \u2603 = ns.d(\u2603, \u26034.L) & 0xFFFFFF;
                        \u2603.setRGB(\u2603, i3, \u2603 | \u2603);
                    }
                }
                bufferedImage2.getGraphics().drawImage(\u2603, 0, 0, null);
            }
        }
        catch (IOException iOException) {
            f.error("Couldn't load layered image", (Throwable)iOException);
            return;
        }
        bml.a(this.b(), bufferedImage2);
    }
}

