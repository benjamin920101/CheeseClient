/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.util.jh;

import com.github.sarxos.webcam.util.jh.JHFilter;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class JHGrayFilter
extends JHFilter {
    protected boolean canFilterIndexColorModel = true;

    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dst) {
        int width = src.getWidth();
        int height = src.getHeight();
        int type = src.getType();
        WritableRaster srcRaster = src.getRaster();
        if (dst == null) {
            dst = this.createCompatibleDestImage(src, null);
        }
        WritableRaster dstRaster = dst.getRaster();
        int[] inPixels = new int[width];
        for (int y2 = 0; y2 < height; ++y2) {
            int x2;
            if (type == 2) {
                srcRaster.getDataElements(0, y2, width, 1, inPixels);
                for (x2 = 0; x2 < width; ++x2) {
                    inPixels[x2] = this.filterRGB(inPixels[x2]);
                }
                dstRaster.setDataElements(0, y2, width, 1, inPixels);
                continue;
            }
            src.getRGB(0, y2, width, 1, inPixels, 0, width);
            for (x2 = 0; x2 < width; ++x2) {
                inPixels[x2] = this.filterRGB(inPixels[x2]);
            }
            dst.setRGB(0, y2, width, 1, inPixels, 0, width);
        }
        return dst;
    }

    private int filterRGB(int rgb) {
        int a2 = rgb & 0xFF000000;
        int r2 = rgb >> 16 & 0xFF;
        int g2 = rgb >> 8 & 0xFF;
        int b2 = rgb & 0xFF;
        rgb = r2 * 77 + g2 * 151 + b2 * 28 >> 8;
        return a2 | rgb << 16 | rgb << 8 | rgb;
    }
}

