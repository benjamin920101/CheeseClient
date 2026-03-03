/*
 * Decompiled with CFR 0.152.
 */
package jaco.mp3.player;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.Icon;

final class O
implements Icon {
    private BufferedImage a;

    private O(BufferedImage bufferedImage, byte by) {
        this.a = bufferedImage;
    }

    @Override
    public final void paintIcon(Component component, Graphics graphics, int n2, int n3) {
        graphics.drawImage(this.a, n2, n3, component);
    }

    @Override
    public final int getIconWidth() {
        return this.a.getWidth();
    }

    @Override
    public final int getIconHeight() {
        return this.a.getHeight();
    }

    /* synthetic */ O(BufferedImage bufferedImage) {
        this(bufferedImage, 0);
    }
}

