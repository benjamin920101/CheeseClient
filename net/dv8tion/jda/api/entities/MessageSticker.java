/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.Set;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.ISnowflake;

public class MessageSticker
implements ISnowflake {
    private final long id;
    private final String name;
    private final String description;
    private final long packId;
    private final String asset;
    private final String previewAsset;
    private final StickerFormat formatType;
    private final Set<String> tags;
    public static final String ASSET_URL = "https://cdn.discordapp.com/stickers/%s/%s.%s";

    public MessageSticker(long id2, String name, String description, long packId, String asset, String previewAsset, StickerFormat formatType, Set<String> tags) {
        this.id = id2;
        this.name = name;
        this.description = description;
        this.packId = packId;
        this.asset = asset;
        this.previewAsset = previewAsset;
        this.formatType = formatType;
        this.tags = tags;
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    @Nonnull
    public String getDescription() {
        return this.description;
    }

    @Nonnull
    public String getPackId() {
        return Long.toUnsignedString(this.getPackIdLong());
    }

    public long getPackIdLong() {
        return this.packId;
    }

    @Nonnull
    public String getAssetHash() {
        return this.asset;
    }

    @Nonnull
    public String getAssetUrl() {
        return String.format(ASSET_URL, this.id, this.asset, this.formatType.getExtension());
    }

    @Nonnull
    public StickerFormat getFormatType() {
        return this.formatType;
    }

    @Nonnull
    public Set<String> getTags() {
        return this.tags;
    }

    public static enum StickerFormat {
        PNG(1, "png"),
        APNG(2, "apng"),
        LOTTIE(3, "json"),
        UNKNOWN(-1, null);

        private final int id;
        private final String extension;

        private StickerFormat(int id2, String extension) {
            this.id = id2;
            this.extension = extension;
        }

        @Nonnull
        public String getExtension() {
            if (this == UNKNOWN) {
                throw new IllegalStateException("Can only get extension of a known format");
            }
            return this.extension;
        }

        @Nonnull
        public static StickerFormat fromId(int id2) {
            for (StickerFormat stickerFormat : StickerFormat.values()) {
                if (stickerFormat.id != id2) continue;
                return stickerFormat;
            }
            return UNKNOWN;
        }
    }
}

