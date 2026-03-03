/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.util;

import com.github.sarxos.webcam.WebcamException;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtils {
    public static final String FORMAT_GIF = "GIF";
    public static final String FORMAT_PNG = "PNG";
    public static final String FORMAT_JPG = "JPG";
    public static final String FORMAT_BMP = "BMP";
    public static final String FORMAT_WBMP = "WBMP";

    public static byte[] toByteArray(BufferedImage image, String format) {
        byte[] bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write((RenderedImage)image, format, baos);
            bytes = baos.toByteArray();
        }
        catch (IOException e2) {
            throw new WebcamException(e2);
        }
        finally {
            try {
                baos.close();
            }
            catch (IOException e3) {
                throw new WebcamException(e3);
            }
        }
        return bytes;
    }
}

