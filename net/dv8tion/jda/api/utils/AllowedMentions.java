/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

import java.util.Collection;
import java.util.EnumSet;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.internal.utils.AllowedMentionsImpl;
import net.dv8tion.jda.internal.utils.Checks;

public interface AllowedMentions<R> {
    public static void setDefaultMentions(@Nullable Collection<Message.MentionType> allowedMentions) {
        AllowedMentionsImpl.setDefaultMentions(allowedMentions);
    }

    @Nonnull
    public static EnumSet<Message.MentionType> getDefaultMentions() {
        return AllowedMentionsImpl.getDefaultMentions();
    }

    public static void setDefaultMentionRepliedUser(boolean mention) {
        AllowedMentionsImpl.setDefaultMentionRepliedUser(mention);
    }

    public static boolean isDefaultMentionRepliedUser() {
        return AllowedMentionsImpl.isDefaultMentionRepliedUser();
    }

    @Nonnull
    @CheckReturnValue
    public R mentionRepliedUser(boolean var1);

    @Nonnull
    @CheckReturnValue
    public R allowedMentions(@Nullable Collection<Message.MentionType> var1);

    @Nonnull
    @CheckReturnValue
    public R mention(IMentionable ... var1);

    @Nonnull
    @CheckReturnValue
    default public R mention(@Nonnull Collection<? extends IMentionable> mentions) {
        Checks.noneNull(mentions, "Mention");
        return this.mention(mentions.toArray(new IMentionable[0]));
    }

    @Nonnull
    @CheckReturnValue
    public R mentionUsers(String ... var1);

    @Nonnull
    @CheckReturnValue
    default public R mentionUsers(long ... userIds) {
        Checks.notNull(userIds, "UserId array");
        String[] stringIds = new String[userIds.length];
        for (int i2 = 0; i2 < userIds.length; ++i2) {
            stringIds[i2] = Long.toUnsignedString(userIds[i2]);
        }
        return this.mentionUsers(stringIds);
    }

    @Nonnull
    @CheckReturnValue
    public R mentionRoles(String ... var1);

    @Nonnull
    @CheckReturnValue
    default public R mentionRoles(long ... roleIds) {
        Checks.notNull(roleIds, "RoleId array");
        String[] stringIds = new String[roleIds.length];
        for (int i2 = 0; i2 < roleIds.length; ++i2) {
            stringIds[i2] = Long.toUnsignedString(roleIds[i2]);
        }
        return this.mentionRoles(stringIds);
    }
}

