/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.util.jh;

import com.github.sarxos.webcam.util.jh.JHFilter;
import java.awt.image.BufferedImage;

public class JHBlurFilter
extends JHFilter {
    private float hRadius;
    private float vRadius;
    private int iterations = 1;
    private boolean premultiplyAlpha = true;

    public JHBlurFilter() {
    }

    public JHBlurFilter(float hRadius, float vRadius, int iterations) {
        this.hRadius = hRadius;
        this.vRadius = vRadius;
        this.iterations = iterations;
    }

    public void setPremultiplyAlpha(boolean premultiplyAlpha) {
        this.premultiplyAlpha = premultiplyAlpha;
    }

    public boolean getPremultiplyAlpha() {
        return this.premultiplyAlpha;
    }

    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dst) {
        int width = src.getWidth();
        int height = src.getHeight();
        if (dst == null) {
            dst = this.createCompatibleDestImage(src, null);
        }
        int[] inPixels = new int[width * height];
        int[] outPixels = new int[width * height];
        this.getRGB(src, 0, 0, width, height, inPixels);
        if (this.premultiplyAlpha) {
            JHBlurFilter.premultiply(inPixels, 0, inPixels.length);
        }
        for (int i2 = 0; i2 < this.iterations; ++i2) {
            JHBlurFilter.blur(inPixels, outPixels, width, height, this.hRadius);
            JHBlurFilter.blur(outPixels, inPixels, height, width, this.vRadius);
        }
        JHBlurFilter.blurFractional(inPixels, outPixels, width, height, this.hRadius);
        JHBlurFilter.blurFractional(outPixels, inPixels, height, width, this.vRadius);
        if (this.premultiplyAlpha) {
            JHBlurFilter.unpremultiply(inPixels, 0, inPixels.length);
        }
        this.setRGB(dst, 0, 0, width, height, inPixels);
        return dst;
    }

    public static void blur(int[] in2, int[] out, int width, int height, float radius) {
        int widthMinus1 = width - 1;
        int r2 = (int)radius;
        int tableSize = 2 * r2 + 1;
        int[] divide = new int[256 * tableSize];
        for (int i2 = 0; i2 < 256 * tableSize; ++i2) {
            divide[i2] = i2 / tableSize;
        }
        int inIndex = 0;
        for (int y2 = 0; y2 < height; ++y2) {
            int outIndex = y2;
            int ta2 = 0;
            int tr2 = 0;
            int tg2 = 0;
            int tb = 0;
            for (int i3 = -r2; i3 <= r2; ++i3) {
                int rgb = in2[inIndex + JHBlurFilter.clamp(i3, 0, width - 1)];
                ta2 += rgb >> 24 & 0xFF;
                tr2 += rgb >> 16 & 0xFF;
                tg2 += rgb >> 8 & 0xFF;
                tb += rgb & 0xFF;
            }
            for (int x2 = 0; x2 < width; ++x2) {
                int i2;
                out[outIndex] = divide[ta2] << 24 | divide[tr2] << 16 | divide[tg2] << 8 | divide[tb];
                int i1 = x2 + r2 + 1;
                if (i1 > widthMinus1) {
                    i1 = widthMinus1;
                }
                if ((i2 = x2 - r2) < 0) {
                    i2 = 0;
                }
                int rgb1 = in2[inIndex + i1];
                int rgb2 = in2[inIndex + i2];
                ta2 += (rgb1 >> 24 & 0xFF) - (rgb2 >> 24 & 0xFF);
                tr2 += (rgb1 & 0xFF0000) - (rgb2 & 0xFF0000) >> 16;
                tg2 += (rgb1 & 0xFF00) - (rgb2 & 0xFF00) >> 8;
                tb += (rgb1 & 0xFF) - (rgb2 & 0xFF);
                outIndex += height;
            }
            inIndex += width;
        }
    }

    public static void blurFractional(int[] in2, int[] out, int width, int height, float radius) {
        radius -= (float)((int)radius);
        float f2 = 1.0f / (1.0f + 2.0f * radius);
        int inIndex = 0;
        for (int y2 = 0; y2 < height; ++y2) {
            int outIndex = y2;
            out[outIndex] = in2[0];
            outIndex += height;
            for (int x2 = 1; x2 < width - 1; ++x2) {
                int i2 = inIndex + x2;
                int rgb1 = in2[i2 - 1];
                int rgb2 = in2[i2];
                int rgb3 = in2[i2 + 1];
                int a1 = rgb1 >> 24 & 0xFF;
                int r1 = rgb1 >> 16 & 0xFF;
                int g1 = rgb1 >> 8 & 0xFF;
                int b1 = rgb1 & 0xFF;
                int a2 = rgb2 >> 24 & 0xFF;
                int r2 = rgb2 >> 16 & 0xFF;
                int g2 = rgb2 >> 8 & 0xFF;
                int b2 = rgb2 & 0xFF;
                int a3 = rgb3 >> 24 & 0xFF;
                int r3 = rgb3 >> 16 & 0xFF;
                int g3 = rgb3 >> 8 & 0xFF;
                int b3 = rgb3 & 0xFF;
                a1 = a2 + (int)((float)(a1 + a3) * radius);
                r1 = r2 + (int)((float)(r1 + r3) * radius);
                g1 = g2 + (int)((float)(g1 + g3) * radius);
                b1 = b2 + (int)((float)(b1 + b3) * radius);
                a1 = (int)((float)a1 * f2);
                r1 = (int)((float)r1 * f2);
                g1 = (int)((float)g1 * f2);
                b1 = (int)((float)b1 * f2);
                out[outIndex] = a1 << 24 | r1 << 16 | g1 << 8 | b1;
                outIndex += height;
            }
            out[outIndex] = in2[width - 1];
            inIndex += width;
        }
    }

    public void setHRadius(float hRadius) {
        this.hRadius = hRadius;
    }

    public float getHRadius() {
        return this.hRadius;
    }

    public void setVRadius(float vRadius) {
        this.vRadius = vRadius;
    }

    public float getVRadius() {
        return this.vRadius;
    }

    public void setRadius(float radius) {
        this.hRadius = this.vRadius = radius;
    }

    public float getRadius() {
        return this.hRadius;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public int getIterations() {
        return this.iterations;
    }

    public String toString() {
        return "Blur/Box Blur...";
    }

    public static void premultiply(int[] p2, int offset, int length) {
        length += offset;
        for (int i2 = offset; i2 < length; ++i2) {
            int rgb = p2[i2];
            int a2 = rgb >> 24 & 0xFF;
            int r2 = rgb >> 16 & 0xFF;
            int g2 = rgb >> 8 & 0xFF;
            int b2 = rgb & 0xFF;
            float f2 = (float)a2 * 0.003921569f;
            r2 = (int)((float)r2 * f2);
            g2 = (int)((float)g2 * f2);
            b2 = (int)((float)b2 * f2);
            p2[i2] = a2 << 24 | r2 << 16 | g2 << 8 | b2;
        }
    }

    public static void unpremultiply(int[] p2, int offset, int length) {
        length += offset;
        for (int i2 = offset; i2 < length; ++i2) {
            int rgb = p2[i2];
            int a2 = rgb >> 24 & 0xFF;
            int r2 = rgb >> 16 & 0xFF;
            int g2 = rgb >> 8 & 0xFF;
            int b2 = rgb & 0xFF;
            if (a2 == 0 || a2 == 255) continue;
            float f2 = 255.0f / (float)a2;
            r2 = (int)((float)r2 * f2);
            g2 = (int)((float)g2 * f2);
            b2 = (int)((float)b2 * f2);
            if (r2 > 255) {
                r2 = 255;
            }
            if (g2 > 255) {
                g2 = 255;
            }
            if (b2 > 255) {
                b2 = 255;
            }
            p2[i2] = a2 << 24 | r2 << 16 | g2 << 8 | b2;
        }
    }

    public static int clamp(int x2, int a2, int b2) {
        return x2 < a2 ? a2 : (x2 > b2 ? b2 : x2);
    }
}

