/*
 * Decompiled with CFR 0.152.
 */
import java.awt.image.BufferedImage;
import java.io.IOException;

public class blz
extends bly {
    private final int[] f;
    private final int g;
    private final int h;

    public blz(BufferedImage bufferedImage) {
        this(bufferedImage.getWidth(), bufferedImage.getHeight());
        bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), this.f, 0, bufferedImage.getWidth());
        this.d();
    }

    public blz(int n2, int n3) {
        this.g = n2;
        this.h = n3;
        this.f = new int[n2 * n3];
        bml.a(this.b(), n2, n3);
    }

    @Override
    public void a(bni bni2) throws IOException {
    }

    public void d() {
        bml.a(this.b(), this.f, this.g, this.h);
    }

    public int[] e() {
        return this.f;
    }
}

