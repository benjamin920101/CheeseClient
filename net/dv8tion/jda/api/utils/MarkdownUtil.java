/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.utils.MarkdownSanitizer;

public final class MarkdownUtil {
    private MarkdownUtil() {
    }

    @Nonnull
    public static String bold(@Nonnull String input) {
        String sanitized = MarkdownSanitizer.escape(input, -2);
        return "**" + sanitized + "**";
    }

    @Nonnull
    public static String italics(@Nonnull String input) {
        String sanitized = MarkdownSanitizer.escape(input, -3);
        return "_" + sanitized + "_";
    }

    @Nonnull
    public static String underline(@Nonnull String input) {
        String sanitized = MarkdownSanitizer.escape(input, -129);
        return "__" + sanitized + "__";
    }

    @Nonnull
    public static String monospace(@Nonnull String input) {
        String sanitized = MarkdownSanitizer.escape(input, -9);
        return "`" + sanitized + "`";
    }

    @Nonnull
    public static String codeblock(@Nonnull String input) {
        return MarkdownUtil.codeblock(null, input);
    }

    @Nonnull
    public static String codeblock(@Nullable String language, @Nonnull String input) {
        String sanitized = MarkdownSanitizer.escape(input, -33);
        if (language != null) {
            return "```" + language.trim() + "\n" + sanitized + "```";
        }
        return "```" + sanitized + "```";
    }

    @Nonnull
    public static String spoiler(@Nonnull String input) {
        String sanitized = MarkdownSanitizer.escape(input, -65);
        return "||" + sanitized + "||";
    }

    @Nonnull
    public static String strike(@Nonnull String input) {
        String sanitized = MarkdownSanitizer.escape(input, -257);
        return "~~" + sanitized + "~~";
    }

    @Nonnull
    public static String quote(@Nonnull String input) {
        String sanitized = MarkdownSanitizer.escape(input, -513);
        return "> " + sanitized.replace("\n", "\n> ");
    }

    @Nonnull
    public static String quoteBlock(@Nonnull String input) {
        return ">>> " + input;
    }

    @Nonnull
    public static String maskedLink(@Nonnull String text, @Nonnull String url) {
        return "[" + text.replace("]", "\\]") + "](" + url.replace(")", "%29") + ")";
    }
}

