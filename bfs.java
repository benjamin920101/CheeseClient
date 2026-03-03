/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class bfs
implements bfm {
    private int[] a;
    private int b;
    private int c;

    @Override
    public BufferedImage a(BufferedImage bufferedImage) {
        if (bufferedImage == null) {
            return null;
        }
        this.b = 64;
        this.c = 64;
        \u2603 = new BufferedImage(this.b, this.c, 2);
        Graphics graphics = \u2603.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, null);
        if (bufferedImage.getHeight() == 32) {
            graphics.drawImage(\u2603, 24, 48, 20, 52, 4, 16, 8, 20, null);
            graphics.drawImage(\u2603, 28, 48, 24, 52, 8, 16, 12, 20, null);
            graphics.drawImage(\u2603, 20, 52, 16, 64, 8, 20, 12, 32, null);
            graphics.drawImage(\u2603, 24, 52, 20, 64, 4, 20, 8, 32, null);
            graphics.drawImage(\u2603, 28, 52, 24, 64, 0, 20, 4, 32, null);
            graphics.drawImage(\u2603, 32, 52, 28, 64, 12, 20, 16, 32, null);
            graphics.drawImage(\u2603, 40, 48, 36, 52, 44, 16, 48, 20, null);
            graphics.drawImage(\u2603, 44, 48, 40, 52, 48, 16, 52, 20, null);
            graphics.drawImage(\u2603, 36, 52, 32, 64, 48, 20, 52, 32, null);
            graphics.drawImage(\u2603, 40, 52, 36, 64, 44, 20, 48, 32, null);
            graphics.drawImage(\u2603, 44, 52, 40, 64, 40, 20, 44, 32, null);
            graphics.drawImage(\u2603, 48, 52, 44, 64, 52, 20, 56, 32, null);
        }
        graphics.dispose();
        this.a = ((DataBufferInt)\u2603.getRaster().getDataBuffer()).getData();
        this.b(0, 0, 32, 16);
        this.a(32, 0, 64, 32);
        this.b(0, 16, 64, 32);
        this.a(0, 32, 16, 48);
        this.a(16, 32, 40, 48);
        this.a(40, 32, 56, 48);
        this.a(0, 48, 16, 64);
        this.b(16, 48, 48, 64);
        this.a(48, 48, 64, 64);
        return \u2603;
    }

    @Override
    public void a() {
    }

    private void a(int n2, int n3, int n4, int n5) {
        if (this.c(n2, n3, n4, n5)) {
            return;
        }
        for (\u2603 = n2; \u2603 < n4; ++\u2603) {
            for (\u2603 = n3; \u2603 < n5; ++\u2603) {
                int n6 = \u2603 + \u2603 * this.b;
                this.a[n6] = this.a[n6] & 0xFFFFFF;
            }
        }
    }

    private void b(int n2, int n3, int n4, int n5) {
        for (\u2603 = n2; \u2603 < n4; ++\u2603) {
            for (\u2603 = n3; \u2603 < n5; ++\u2603) {
                int n6 = \u2603 + \u2603 * this.b;
                this.a[n6] = this.a[n6] | 0xFF000000;
            }
        }
    }

    private boolean c(int n2, int n3, int n4, int n5) {
        for (\u2603 = n2; \u2603 < n4; ++\u2603) {
            for (\u2603 = n3; \u2603 < n5; ++\u2603) {
                \u2603 = this.a[\u2603 + \u2603 * this.b];
                if ((\u2603 >> 24 & 0xFF) >= 128) continue;
                return true;
            }
        }
        return false;
    }
}

