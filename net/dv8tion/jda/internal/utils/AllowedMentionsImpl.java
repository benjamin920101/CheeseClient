/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.utils.AllowedMentions;
import net.dv8tion.jda.api.utils.data.DataArray;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.api.utils.data.SerializableData;
import net.dv8tion.jda.internal.entities.DataMessage;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.Helpers;

public class AllowedMentionsImpl
implements SerializableData,
AllowedMentions<AllowedMentionsImpl> {
    private static EnumSet<Message.MentionType> defaultParse = EnumSet.allOf(Message.MentionType.class);
    private static boolean defaultMentionRepliedUser = true;
    private EnumSet<Message.MentionType> parse = AllowedMentionsImpl.getDefaultMentions();
    private final Set<String> users = new HashSet<String>();
    private final Set<String> roles = new HashSet<String>();
    private boolean mentionRepliedUser = defaultMentionRepliedUser;

    public static void setDefaultMentions(@Nullable Collection<Message.MentionType> allowedMentions) {
        defaultParse = allowedMentions == null ? EnumSet.allOf(Message.MentionType.class) : Helpers.copyEnumSet(Message.MentionType.class, allowedMentions);
    }

    @Nonnull
    public static EnumSet<Message.MentionType> getDefaultMentions() {
        return defaultParse.clone();
    }

    public static void setDefaultMentionRepliedUser(boolean mention) {
        defaultMentionRepliedUser = mention;
    }

    public static boolean isDefaultMentionRepliedUser() {
        return defaultMentionRepliedUser;
    }

    @Override
    @Nonnull
    public DataObject toData() {
        DataObject allowedMentionsObj = DataObject.empty();
        DataArray parsable = DataArray.empty();
        if (this.parse != null) {
            this.parse.stream().map(Message.MentionType::getParseKey).filter(Objects::nonNull).distinct().forEach(parsable::add);
        }
        if (!this.users.isEmpty()) {
            parsable.remove(Message.MentionType.USER.getParseKey());
            allowedMentionsObj.put("users", DataArray.fromCollection(this.users));
        }
        if (!this.roles.isEmpty()) {
            parsable.remove(Message.MentionType.ROLE.getParseKey());
            allowedMentionsObj.put("roles", DataArray.fromCollection(this.roles));
        }
        allowedMentionsObj.put("replied_user", this.mentionRepliedUser);
        return allowedMentionsObj.put("parse", parsable);
    }

    public AllowedMentionsImpl applyMessage(Message message) {
        if (message instanceof DataMessage) {
            DataMessage data = (DataMessage)message;
            String[] mentionedRoles = data.getMentionedRolesWhitelist();
            String[] mentionedUsers = data.getMentionedUsersWhitelist();
            EnumSet<Message.MentionType> allowedMentions = data.getAllowedMentions();
            if (allowedMentions != null) {
                this.allowedMentions(allowedMentions);
            }
            this.mentionRoles(mentionedRoles);
            this.mentionUsers(mentionedUsers);
        } else {
            if (message.mentionsEveryone()) {
                String content = message.getContentRaw();
                EnumSet<Message.MentionType> parse = EnumSet.noneOf(Message.MentionType.class);
                if (content.contains("@everyone")) {
                    parse.add(Message.MentionType.EVERYONE);
                }
                if (content.contains("@here")) {
                    parse.add(Message.MentionType.HERE);
                }
                this.parse = parse;
            } else {
                this.parse = EnumSet.noneOf(Message.MentionType.class);
            }
            ((AllowedMentionsImpl)this.mention(message.getMentionedUsers())).mention(message.getMentionedRoles());
        }
        return this;
    }

    @Override
    @Nonnull
    public AllowedMentionsImpl mentionRepliedUser(boolean mention) {
        this.mentionRepliedUser = mention;
        return this;
    }

    @Override
    @Nonnull
    public AllowedMentionsImpl allowedMentions(@Nullable Collection<Message.MentionType> allowedMentions) {
        this.parse = allowedMentions == null ? EnumSet.allOf(Message.MentionType.class) : Helpers.copyEnumSet(Message.MentionType.class, allowedMentions);
        return this;
    }

    @Override
    @Nonnull
    public AllowedMentionsImpl mention(IMentionable ... mentions) {
        Checks.noneNull(mentions, "Mentionables");
        for (IMentionable mentionable : mentions) {
            if (mentionable instanceof User || mentionable instanceof Member) {
                this.users.add(mentionable.getId());
                continue;
            }
            if (!(mentionable instanceof Role)) continue;
            this.roles.add(mentionable.getId());
        }
        return this;
    }

    @Override
    @Nonnull
    public AllowedMentionsImpl mentionUsers(String ... userIds) {
        Checks.noneNull(userIds, "User Id");
        Collections.addAll(this.users, userIds);
        return this;
    }

    @Override
    @Nonnull
    public AllowedMentionsImpl mentionRoles(String ... roleIds) {
        Checks.noneNull(roleIds, "Role Id");
        Collections.addAll(this.roles, roleIds);
        return this;
    }
}

