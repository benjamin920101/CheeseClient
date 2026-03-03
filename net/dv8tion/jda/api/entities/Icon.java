/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.annotation.Nonnull;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.IOUtil;

public class Icon {
    protected final String encoding;

    protected Icon(@Nonnull IconType type, @Nonnull String base64Encoding) {
        this.encoding = type.getHeader() + base64Encoding;
    }

    @Nonnull
    public String getEncoding() {
        return this.encoding;
    }

    @Nonnull
    public static Icon from(@Nonnull File file) throws IOException {
        Checks.notNull(file, "Provided File");
        Checks.check(file.exists(), "Provided file does not exist!");
        int index = file.getName().lastIndexOf(46);
        if (index < 0) {
            return Icon.from(file, IconType.JPEG);
        }
        String ext = file.getName().substring(index + 1);
        IconType type = IconType.fromExtension(ext);
        return Icon.from(file, type);
    }

    @Nonnull
    public static Icon from(@Nonnull InputStream stream) throws IOException {
        return Icon.from(stream, IconType.JPEG);
    }

    @Nonnull
    public static Icon from(@Nonnull byte[] data) {
        return Icon.from(data, IconType.JPEG);
    }

    @Nonnull
    public static Icon from(@Nonnull File file, @Nonnull IconType type) throws IOException {
        Checks.notNull(file, "Provided File");
        Checks.notNull((Object)type, "IconType");
        Checks.check(file.exists(), "Provided file does not exist!");
        return Icon.from(IOUtil.readFully(file), type);
    }

    @Nonnull
    public static Icon from(@Nonnull InputStream stream, @Nonnull IconType type) throws IOException {
        Checks.notNull(stream, "InputStream");
        Checks.notNull((Object)type, "IconType");
        return Icon.from(IOUtil.readFully(stream), type);
    }

    @Nonnull
    public static Icon from(@Nonnull byte[] data, @Nonnull IconType type) {
        Checks.notNull(data, "Provided byte[]");
        Checks.notNull((Object)type, "IconType");
        return new Icon(type, new String(Base64.getEncoder().encode(data), StandardCharsets.UTF_8));
    }

    public static enum IconType {
        JPEG("image/jpeg"),
        PNG("image/png"),
        WEBP("image/webp"),
        GIF("image/gif"),
        UNKNOWN("image/jpeg");

        private final String mime;
        private final String header;

        private IconType(String mime) {
            this.mime = mime;
            this.header = "data:" + mime + ";base64,";
        }

        @Nonnull
        public String getMIME() {
            return this.mime;
        }

        @Nonnull
        public String getHeader() {
            return this.header;
        }

        @Nonnull
        public static IconType fromMIME(@Nonnull String mime) {
            Checks.notNull(mime, "MIME Type");
            for (IconType type : IconType.values()) {
                if (!type.mime.equalsIgnoreCase(mime)) continue;
                return type;
            }
            return UNKNOWN;
        }

        @Nonnull
        public static IconType fromExtension(@Nonnull String extension) {
            Checks.notNull(extension, "Extension Type");
            switch (extension.toLowerCase()) {
                case "jpe": 
                case "jif": 
                case "jfif": 
                case "jfi": 
                case "jpg": 
                case "jpeg": {
                    return JPEG;
                }
                case "png": {
                    return PNG;
                }
                case "webp": {
                    return WEBP;
                }
                case "gif": {
                    return GIF;
                }
            }
            return UNKNOWN;
        }
    }
}

