/*
 * Decompiled with CFR 0.152.
 */
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;
import javax.imageio.ImageIO;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class bml {
    private static final Logger c = LogManager.getLogger();
    private static final IntBuffer d = avd.f(0x400000);
    public static final blz a = new blz(16, 16);
    public static final int[] b = a.e();
    private static final int[] e;

    public static int a() {
        return bfl.y();
    }

    public static void a(int n2) {
        bfl.h(n2);
    }

    public static int a(int n2, BufferedImage bufferedImage) {
        return bml.a(n2, bufferedImage, false, false);
    }

    public static void a(int n2, int[] nArray, int n3, int n4) {
        bml.b(n2);
        bml.a(0, nArray, n3, n4, 0, 0, false, false, false);
    }

    public static int[][] a(int n2, int n3, int[][] nArray) {
        int[][] nArrayArray = new int[n2 + 1][];
        nArrayArray[0] = nArray[0];
        if (n2 > 0) {
            int n4;
            boolean bl2 = false;
            for (n4 = 0; n4 < nArray.length; ++n4) {
                if (nArray[0][n4] >> 24 != 0) continue;
                bl2 = true;
                break;
            }
            for (n4 = 1; n4 <= n2; ++n4) {
                int[] nArray2;
                if (nArray[n4] != null) {
                    nArrayArray[n4] = nArray[n4];
                    continue;
                }
                int[] nArray3 = nArrayArray[n4 - 1];
                nArray2 = new int[nArray3.length >> 2];
                int \u26032 = n3 >> n4;
                int \u26033 = nArray2.length / \u26032;
                int \u26034 = \u26032 << 1;
                for (int i2 = 0; i2 < \u26032; ++i2) {
                    for (\u2603 = 0; \u2603 < \u26033; ++\u2603) {
                        \u2603 = 2 * (i2 + \u2603 * \u26034);
                        nArray2[i2 + \u2603 * \u26032] = bml.a(nArray3[\u2603 + 0], nArray3[\u2603 + 1], nArray3[\u2603 + 0 + \u26034], nArray3[\u2603 + 1 + \u26034], bl2);
                    }
                }
                nArrayArray[n4] = nArray2;
            }
        }
        return nArrayArray;
    }

    private static int a(int n22, int n3, int n4, int n5, boolean bl2) {
        int n22;
        if (!bl2) {
            int n6 = bml.a(n22, n3, n4, n5, 24);
            \u2603 = bml.a(n22, n3, n4, n5, 16);
            \u2603 = bml.a(n22, n3, n4, n5, 8);
            \u2603 = bml.a(n22, n3, n4, n5, 0);
            return n6 << 24 | \u2603 << 16 | \u2603 << 8 | \u2603;
        }
        bml.e[0] = n22;
        bml.e[1] = n3;
        bml.e[2] = n4;
        bml.e[3] = n5;
        float \u26032 = 0.0f;
        float \u26033 = 0.0f;
        float \u26034 = 0.0f;
        float \u26035 = 0.0f;
        for (\u2603 = 0; \u2603 < 4; ++\u2603) {
            if (e[\u2603] >> 24 == 0) continue;
            \u26032 += (float)Math.pow((float)(e[\u2603] >> 24 & 0xFF) / 255.0f, 2.2);
            \u26033 += (float)Math.pow((float)(e[\u2603] >> 16 & 0xFF) / 255.0f, 2.2);
            \u26034 += (float)Math.pow((float)(e[\u2603] >> 8 & 0xFF) / 255.0f, 2.2);
            \u26035 += (float)Math.pow((float)(e[\u2603] >> 0 & 0xFF) / 255.0f, 2.2);
        }
        \u2603 = (int)(Math.pow(\u26032 /= 4.0f, 0.45454545454545453) * 255.0);
        \u2603 = (int)(Math.pow(\u26033 /= 4.0f, 0.45454545454545453) * 255.0);
        \u2603 = (int)(Math.pow(\u26034 /= 4.0f, 0.45454545454545453) * 255.0);
        \u2603 = (int)(Math.pow(\u26035 /= 4.0f, 0.45454545454545453) * 255.0);
        if (\u2603 < 96) {
            \u2603 = 0;
        }
        return \u2603 << 24 | \u2603 << 16 | \u2603 << 8 | \u2603;
    }

    private static int a(int n2, int n3, int n4, int n5, int n6) {
        float f2 = (float)Math.pow((float)(n2 >> n6 & 0xFF) / 255.0f, 2.2);
        \u2603 = (float)Math.pow((float)(n3 >> n6 & 0xFF) / 255.0f, 2.2);
        \u2603 = (float)Math.pow((float)(n4 >> n6 & 0xFF) / 255.0f, 2.2);
        \u2603 = (float)Math.pow((float)(n5 >> n6 & 0xFF) / 255.0f, 2.2);
        \u2603 = (float)Math.pow((double)(f2 + \u2603 + \u2603 + \u2603) * 0.25, 0.45454545454545453);
        return (int)((double)\u2603 * 255.0);
    }

    public static void a(int[][] nArray, int n2, int n3, int n4, int n5, boolean bl2, boolean bl3) {
        for (int i2 = 0; i2 < nArray.length; ++i2) {
            int[] nArray2 = nArray[i2];
            bml.a(i2, nArray2, n2 >> i2, n3 >> i2, n4 >> i2, n5 >> i2, bl2, bl3, nArray.length > 1);
        }
    }

    private static void a(int n2, int[] nArray, int n3, int n4, int n5, int n6, boolean bl2, boolean bl3, boolean bl4) {
        int n7 = 0x400000 / n3;
        bml.a(bl2, bl4);
        bml.a(bl3);
        for (\u2603 = 0; \u2603 < n3 * n4; \u2603 += n3 * \u2603) {
            \u2603 = \u2603 / n3;
            \u2603 = Math.min(n7, n4 - \u2603);
            \u2603 = n3 * \u2603;
            bml.b(nArray, \u2603, \u2603);
            GL11.glTexSubImage2D(3553, n2, n5, n6 + \u2603, n3, \u2603, 32993, 33639, d);
        }
    }

    public static int a(int n2, BufferedImage bufferedImage, boolean bl2, boolean bl3) {
        bml.a(n2, bufferedImage.getWidth(), bufferedImage.getHeight());
        return bml.a(n2, bufferedImage, 0, 0, bl2, bl3);
    }

    public static void a(int n2, int n3, int n4) {
        bml.a(n2, 0, n3, n4);
    }

    public static void a(int n2, int n3, int n4, int n5) {
        bml.a(n2);
        bml.b(n2);
        if (n3 >= 0) {
            GL11.glTexParameteri(3553, 33085, n3);
            GL11.glTexParameterf(3553, 33082, 0.0f);
            GL11.glTexParameterf(3553, 33083, n3);
            GL11.glTexParameterf(3553, 34049, 0.0f);
        }
        for (\u2603 = 0; \u2603 <= n3; ++\u2603) {
            GL11.glTexImage2D(3553, \u2603, 6408, n4 >> \u2603, n5 >> \u2603, 0, 32993, 33639, (IntBuffer)null);
        }
    }

    public static int a(int n2, BufferedImage bufferedImage, int n3, int n4, boolean bl2, boolean bl3) {
        bml.b(n2);
        bml.a(bufferedImage, n3, n4, bl2, bl3);
        return n2;
    }

    private static void a(BufferedImage bufferedImage, int n2, int n3, boolean bl2, boolean bl3) {
        int n4 = bufferedImage.getWidth();
        \u2603 = bufferedImage.getHeight();
        \u2603 = 0x400000 / n4;
        int[] \u26032 = new int[\u2603 * n4];
        bml.b(bl2);
        bml.a(bl3);
        for (\u2603 = 0; \u2603 < n4 * \u2603; \u2603 += n4 * \u2603) {
            \u2603 = \u2603 / n4;
            \u2603 = Math.min(\u2603, \u2603 - \u2603);
            \u2603 = n4 * \u2603;
            bufferedImage.getRGB(0, \u2603, n4, \u2603, \u26032, 0, n4);
            bml.a(\u26032, \u2603);
            GL11.glTexSubImage2D(3553, 0, n2, n3 + \u2603, n4, \u2603, 32993, 33639, d);
        }
    }

    private static void a(boolean bl2) {
        if (bl2) {
            GL11.glTexParameteri(3553, 10242, 10496);
            GL11.glTexParameteri(3553, 10243, 10496);
        } else {
            GL11.glTexParameteri(3553, 10242, 10497);
            GL11.glTexParameteri(3553, 10243, 10497);
        }
    }

    private static void b(boolean bl2) {
        bml.a(bl2, false);
    }

    private static void a(boolean bl2, boolean bl3) {
        if (bl2) {
            GL11.glTexParameteri(3553, 10241, bl3 ? 9987 : 9729);
            GL11.glTexParameteri(3553, 10240, 9729);
        } else {
            GL11.glTexParameteri(3553, 10241, bl3 ? 9986 : 9728);
            GL11.glTexParameteri(3553, 10240, 9728);
        }
    }

    private static void a(int[] nArray, int n2) {
        bml.b(nArray, 0, n2);
    }

    private static void b(int[] nArray, int n2, int n3) {
        int[] nArray2 = nArray;
        if (ave.A().t.e) {
            nArray2 = bml.a(nArray);
        }
        d.clear();
        d.put(nArray2, n2, n3);
        d.position(0).limit(n3);
    }

    static void b(int n2) {
        bfl.i(n2);
    }

    public static int[] a(bni bni2, jy jy2) throws IOException {
        BufferedImage bufferedImage = bml.a(bni2.a(jy2).b());
        int \u26032 = bufferedImage.getWidth();
        int \u26033 = bufferedImage.getHeight();
        int[] \u26034 = new int[\u26032 * \u26033];
        bufferedImage.getRGB(0, 0, \u26032, \u26033, \u26034, 0, \u26032);
        return \u26034;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static BufferedImage a(InputStream inputStream) throws IOException {
        try {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            return bufferedImage;
        }
        finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    public static int[] a(int[] nArray) {
        int[] nArray2;
        nArray2 = new int[nArray.length];
        for (int i2 = 0; i2 < nArray.length; ++i2) {
            nArray2[i2] = bml.c(nArray[i2]);
        }
        return nArray2;
    }

    public static int c(int n2) {
        \u2603 = n2 >> 24 & 0xFF;
        \u2603 = n2 >> 16 & 0xFF;
        \u2603 = n2 >> 8 & 0xFF;
        \u2603 = n2 & 0xFF;
        \u2603 = (\u2603 * 30 + \u2603 * 59 + \u2603 * 11) / 100;
        \u2603 = (\u2603 * 30 + \u2603 * 70) / 100;
        \u2603 = (\u2603 * 30 + \u2603 * 70) / 100;
        return \u2603 << 24 | \u2603 << 16 | \u2603 << 8 | \u2603;
    }

    public static void a(int[] nArray, int n2, int n3) {
        int[] nArray2 = new int[n2];
        int \u26032 = n3 / 2;
        for (int i2 = 0; i2 < \u26032; ++i2) {
            System.arraycopy(nArray, i2 * n2, nArray2, 0, n2);
            System.arraycopy(nArray, (n3 - 1 - i2) * n2, nArray, i2 * n2, n2);
            System.arraycopy(nArray2, 0, nArray, (n3 - 1 - i2) * n2, n2);
        }
    }

    static {
        int n2 = -16777216;
        \u2603 = -524040;
        int[] \u26032 = new int[]{-524040, -524040, -524040, -524040, -524040, -524040, -524040, -524040};
        int[] \u26033 = new int[]{-16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216, -16777216};
        \u2603 = \u26032.length;
        for (\u2603 = 0; \u2603 < 16; ++\u2603) {
            System.arraycopy(\u2603 < \u2603 ? \u26032 : \u26033, 0, b, 16 * \u2603, \u2603);
            System.arraycopy(\u2603 < \u2603 ? \u26033 : \u26032, 0, b, 16 * \u2603 + \u2603, \u2603);
        }
        a.d();
        e = new int[4];
    }
}

