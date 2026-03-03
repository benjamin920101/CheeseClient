/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.util.ImageUtils;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;

public class WebcamUtils {
    public static final void capture(Webcam webcam, File file) {
        if (!webcam.isOpen()) {
            webcam.open();
        }
        try {
            ImageIO.write((RenderedImage)webcam.getImage(), "JPG", file);
        }
        catch (IOException e2) {
            throw new WebcamException(e2);
        }
    }

    public static final void capture(Webcam webcam, File file, String format) {
        if (!webcam.isOpen()) {
            webcam.open();
        }
        try {
            ImageIO.write((RenderedImage)webcam.getImage(), format, file);
        }
        catch (IOException e2) {
            throw new WebcamException(e2);
        }
    }

    public static final void capture(Webcam webcam, String filename) {
        if (filename.endsWith(".jpg")) {
            filename = filename + ".jpg";
        }
        WebcamUtils.capture(webcam, new File(filename));
    }

    public static final void capture(Webcam webcam, String filename, String format) {
        String ext = "." + format.toLowerCase();
        if (!filename.startsWith(ext)) {
            filename = filename + ext;
        }
        WebcamUtils.capture(webcam, new File(filename), format);
    }

    public static final byte[] getImageBytes(Webcam webcam, String format) {
        return ImageUtils.toByteArray(webcam.getImage(), format);
    }

    public static final ByteBuffer getImageByteBuffer(Webcam webcam, String format) {
        return ByteBuffer.wrap(WebcamUtils.getImageBytes(webcam, format));
    }

    public static final ResourceBundle loadRB(Class<?> clazz, Locale locale) {
        String pkg = WebcamUtils.class.getPackage().getName().replaceAll("\\.", "/");
        return PropertyResourceBundle.getBundle(String.format("%s/i18n/%s", pkg, clazz.getSimpleName()));
    }
}

