/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ForRemoval;
import net.dv8tion.jda.annotations.ReplaceWith;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ComponentLayout;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.internal.entities.DataMessage;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.Helpers;

public class MessageBuilder
implements Appendable {
    protected final StringBuilder builder = new StringBuilder();
    protected boolean isTTS = false;
    protected String nonce;
    protected MessageEmbed embed;
    protected List<ComponentLayout> components = new ArrayList<ComponentLayout>();
    protected EnumSet<Message.MentionType> allowedMentions = null;
    protected Set<String> mentionedUsers = new HashSet<String>();
    protected Set<String> mentionedRoles = new HashSet<String>();

    public MessageBuilder() {
    }

    public MessageBuilder(@Nullable CharSequence content) {
        if (content != null) {
            this.builder.append(content);
        }
    }

    public MessageBuilder(@Nullable Message message) {
        if (message != null) {
            this.isTTS = message.isTTS();
            this.builder.append(message.getContentRaw());
            List<MessageEmbed> embeds = message.getEmbeds();
            if (embeds != null && !embeds.isEmpty() && embeds.get(0).getType() == EmbedType.RICH) {
                this.embed = embeds.get(0);
            }
            this.components.addAll(message.getActionRows());
            if (message instanceof DataMessage) {
                DataMessage data = (DataMessage)message;
                if (data.getAllowedMentions() != null) {
                    this.allowedMentions = Helpers.copyEnumSet(Message.MentionType.class, data.getAllowedMentions());
                }
                Collections.addAll(this.mentionedUsers, data.getMentionedUsersWhitelist());
                Collections.addAll(this.mentionedRoles, data.getMentionedRolesWhitelist());
            }
        }
    }

    public MessageBuilder(@Nullable MessageBuilder builder) {
        if (builder != null) {
            this.isTTS = builder.isTTS;
            this.builder.append((CharSequence)builder.builder);
            this.nonce = builder.nonce;
            this.embed = builder.embed;
            this.components.addAll(builder.components);
            if (builder.allowedMentions != null) {
                this.allowedMentions = Helpers.copyEnumSet(Message.MentionType.class, builder.allowedMentions);
            }
            this.mentionedRoles.addAll(builder.mentionedRoles);
            this.mentionedUsers.addAll(builder.mentionedUsers);
        }
    }

    public MessageBuilder(@Nullable EmbedBuilder builder) {
        if (builder != null) {
            this.embed = builder.build();
        }
    }

    public MessageBuilder(@Nullable MessageEmbed embed) {
        this.embed = embed;
    }

    @Nonnull
    public MessageBuilder setTTS(boolean tts) {
        this.isTTS = tts;
        return this;
    }

    @Nonnull
    public MessageBuilder setEmbed(@Nullable MessageEmbed embed) {
        this.embed = embed;
        return this;
    }

    @Nonnull
    public MessageBuilder setActionRows(@Nullable Collection<? extends ActionRow> rows) {
        if (rows == null) {
            this.components.clear();
            return this;
        }
        Checks.noneNull(rows, "ActionRows");
        Checks.check(rows.size() <= 5, "Can only have 5 action rows per message!");
        this.components.clear();
        this.components.addAll(rows);
        return this;
    }

    @Nonnull
    public MessageBuilder setActionRows(ActionRow ... rows) {
        if (rows == null) {
            this.components.clear();
            return this;
        }
        return this.setActionRows(Arrays.asList(rows));
    }

    @Nonnull
    public MessageBuilder setNonce(@Nullable String nonce) {
        this.nonce = nonce;
        return this;
    }

    @Nonnull
    public MessageBuilder setContent(@Nullable String content) {
        if (content == null) {
            this.builder.setLength(0);
        } else {
            int newLength = Math.max(this.builder.length(), content.length());
            this.builder.replace(0, newLength, content);
        }
        return this;
    }

    @Override
    @Nonnull
    public MessageBuilder append(@Nullable CharSequence text) {
        this.builder.append(text);
        return this;
    }

    @Override
    @Nonnull
    public MessageBuilder append(@Nullable CharSequence text, int start, int end) {
        this.builder.append(text, start, end);
        return this;
    }

    @Override
    @Nonnull
    public MessageBuilder append(char c2) {
        this.builder.append(c2);
        return this;
    }

    @Nonnull
    public MessageBuilder append(@Nullable Object object) {
        return this.append(String.valueOf(object));
    }

    @Nonnull
    public MessageBuilder append(@Nonnull IMentionable mention) {
        Checks.notNull(mention, "Mentionable");
        this.builder.append(mention.getAsMention());
        return this;
    }

    @Nonnull
    public MessageBuilder append(@Nullable CharSequence text, Formatting ... format) {
        boolean blockPresent = false;
        for (Formatting formatting : format) {
            if (formatting == Formatting.BLOCK) {
                blockPresent = true;
                continue;
            }
            this.builder.append(formatting.getTag());
        }
        if (blockPresent) {
            this.builder.append(Formatting.BLOCK.getTag());
        }
        this.builder.append(text);
        if (blockPresent) {
            this.builder.append(Formatting.BLOCK.getTag());
        }
        for (int i2 = format.length - 1; i2 >= 0; --i2) {
            if (format[i2] == Formatting.BLOCK) continue;
            this.builder.append(format[i2].getTag());
        }
        return this;
    }

    @Nonnull
    public MessageBuilder appendFormat(@Nonnull String format, Object ... args) {
        Checks.notEmpty(format, "Format String");
        this.append(String.format(format, args));
        return this;
    }

    @Nonnull
    public MessageBuilder appendCodeLine(@Nullable CharSequence text) {
        this.append(text, Formatting.BLOCK);
        return this;
    }

    @Nonnull
    public MessageBuilder appendCodeBlock(@Nullable CharSequence text, @Nullable CharSequence language) {
        this.builder.append("```").append(language).append('\n').append(text).append("\n```");
        return this;
    }

    public int length() {
        return this.builder.length();
    }

    public boolean isEmpty() {
        return this.builder.length() == 0 && this.embed == null;
    }

    @Nonnull
    public MessageBuilder replace(@Nonnull String target, @Nonnull String replacement) {
        int index = this.builder.indexOf(target);
        while (index != -1) {
            this.builder.replace(index, index + target.length(), replacement);
            index = this.builder.indexOf(target, index + replacement.length());
        }
        return this;
    }

    @Nonnull
    public MessageBuilder replaceFirst(@Nonnull String target, @Nonnull String replacement) {
        int index = this.builder.indexOf(target);
        if (index != -1) {
            this.builder.replace(index, index + target.length(), replacement);
        }
        return this;
    }

    @Nonnull
    public MessageBuilder replaceLast(@Nonnull String target, @Nonnull String replacement) {
        int index = this.builder.lastIndexOf(target);
        if (index != -1) {
            this.builder.replace(index, index + target.length(), replacement);
        }
        return this;
    }

    @Nonnull
    public MessageBuilder clearMentionedUsers() {
        this.mentionedUsers.clear();
        return this;
    }

    @Nonnull
    public MessageBuilder clearMentionedRoles() {
        this.mentionedRoles.clear();
        return this;
    }

    @Nonnull
    public MessageBuilder clearMentions() {
        return this.clearMentionedUsers().clearMentionedRoles();
    }

    @Nonnull
    public MessageBuilder setAllowedMentions(@Nullable Collection<Message.MentionType> mentionTypes) {
        this.allowedMentions = mentionTypes == null ? MessageAction.getDefaultMentions() : Helpers.copyEnumSet(Message.MentionType.class, mentionTypes);
        return this;
    }

    @Nonnull
    public MessageBuilder allowMentions(Message.MentionType ... types) {
        Checks.noneNull((Object[])types, "MentionTypes");
        if (types.length > 0) {
            if (this.allowedMentions == null) {
                this.allowedMentions = MessageAction.getDefaultMentions();
            }
            Collections.addAll(this.allowedMentions, types);
        }
        return this;
    }

    @Nonnull
    public MessageBuilder denyMentions(Message.MentionType ... types) {
        Checks.noneNull((Object[])types, "MentionTypes");
        if (types.length > 0) {
            if (this.allowedMentions == null) {
                this.allowedMentions = MessageAction.getDefaultMentions();
            }
            for (Message.MentionType type : types) {
                this.allowedMentions.remove((Object)type);
            }
        }
        return this;
    }

    @Nonnull
    public MessageBuilder mention(IMentionable ... mentions) {
        Checks.noneNull(mentions, "Mentions");
        for (IMentionable mention : mentions) {
            if (mention instanceof User || mention instanceof Member) {
                this.mentionedUsers.add(mention.getId());
                continue;
            }
            if (!(mention instanceof Role)) continue;
            this.mentionedRoles.add(mention.getId());
        }
        return this;
    }

    @Nonnull
    public MessageBuilder mention(@Nonnull Collection<? extends IMentionable> mentions) {
        Checks.noneNull(mentions, "Mentions");
        return this.mention(mentions.toArray(new IMentionable[0]));
    }

    @Nonnull
    public MessageBuilder mentionUsers(String ... users) {
        Checks.noneNull(users, "Users");
        Collections.addAll(this.mentionedUsers, users);
        return this;
    }

    @Nonnull
    public MessageBuilder mentionRoles(String ... roles) {
        Checks.noneNull(roles, "Roles");
        Collections.addAll(this.mentionedRoles, roles);
        return this;
    }

    @Nonnull
    public MessageBuilder mentionUsers(long ... users) {
        Checks.notNull(users, "Users");
        return this.mentionUsers(this.toStringArray(users));
    }

    @Nonnull
    public MessageBuilder mentionRoles(long ... roles) {
        Checks.notNull(roles, "Roles");
        return this.mentionRoles(this.toStringArray(roles));
    }

    @Nonnull
    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @ReplaceWith(value="setAllowedMentions(Collections.emptyList())")
    @DeprecatedSince(value="4.2.0")
    public MessageBuilder stripMentions(@Nonnull JDA jda) {
        return this.stripMentions(jda, (Guild)null, Message.MentionType.values());
    }

    @Nonnull
    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @ReplaceWith(value="setAllowedMentions(Collections.emptyList())")
    @DeprecatedSince(value="4.2.0")
    public MessageBuilder stripMentions(@Nonnull Guild guild) {
        return this.stripMentions(guild.getJDA(), guild, Message.MentionType.values());
    }

    @Nonnull
    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @ReplaceWith(value="denyMentions(types)")
    @DeprecatedSince(value="4.2.0")
    public MessageBuilder stripMentions(@Nonnull Guild guild, Message.MentionType ... types) {
        return this.stripMentions(guild.getJDA(), guild, types);
    }

    @Nonnull
    @Deprecated
    @ForRemoval(deadline="4.4.0")
    @ReplaceWith(value="denyMentions(types)")
    @DeprecatedSince(value="4.2.0")
    public MessageBuilder stripMentions(@Nonnull JDA jda, Message.MentionType ... types) {
        return this.stripMentions(jda, (Guild)null, types);
    }

    @Nonnull
    private MessageBuilder stripMentions(JDA jda, Guild guild, Message.MentionType ... types) {
        if (types == null) {
            return this;
        }
        String string = null;
        block7: for (Message.MentionType mention : types) {
            if (mention == null) continue;
            switch (mention) {
                case EVERYONE: {
                    this.replace("@everyone", "@\u0435veryone");
                    continue block7;
                }
                case HERE: {
                    this.replace("@here", "@h\u0435re");
                    continue block7;
                }
                case CHANNEL: {
                    if (string == null) {
                        string = this.builder.toString();
                    }
                    Matcher matcher = Message.MentionType.CHANNEL.getPattern().matcher(string);
                    while (matcher.find()) {
                        TextChannel channel = jda.getTextChannelById(matcher.group(1));
                        if (channel == null) continue;
                        this.replace(matcher.group(), "#" + channel.getName());
                    }
                    continue block7;
                }
                case ROLE: {
                    if (string == null) {
                        string = this.builder.toString();
                    }
                    Matcher matcher = Message.MentionType.ROLE.getPattern().matcher(string);
                    block9: while (matcher.find()) {
                        for (Guild g2 : jda.getGuilds()) {
                            Role role = g2.getRoleById(matcher.group(1));
                            if (role == null) continue;
                            this.replace(matcher.group(), "@" + role.getName());
                            continue block9;
                        }
                    }
                    continue block7;
                }
                case USER: {
                    if (string == null) {
                        string = this.builder.toString();
                    }
                    Matcher matcher = Message.MentionType.USER.getPattern().matcher(string);
                    while (matcher.find()) {
                        Member member;
                        User user = jda.getUserById(matcher.group(1));
                        if (user == null) continue;
                        String replacement = guild != null && (member = guild.getMember(user)) != null ? member.getEffectiveName() : user.getName();
                        this.replace(matcher.group(), "@" + replacement);
                    }
                    continue block7;
                }
            }
        }
        return this;
    }

    @Nonnull
    public StringBuilder getStringBuilder() {
        return this.builder;
    }

    @Nonnull
    public MessageBuilder clear() {
        this.builder.setLength(0);
        this.embed = null;
        this.isTTS = false;
        return this;
    }

    public int indexOf(@Nonnull CharSequence target, int fromIndex, int endIndex) {
        int targetCount;
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("index out of range: " + fromIndex);
        }
        if (endIndex < 0) {
            throw new IndexOutOfBoundsException("index out of range: " + endIndex);
        }
        if (fromIndex > this.length()) {
            throw new IndexOutOfBoundsException("fromIndex > length()");
        }
        if (fromIndex > endIndex) {
            throw new IndexOutOfBoundsException("fromIndex > endIndex");
        }
        if (endIndex >= this.builder.length()) {
            endIndex = this.builder.length() - 1;
        }
        if ((targetCount = target.length()) == 0) {
            return fromIndex;
        }
        char strFirstChar = target.charAt(0);
        int max = endIndex + targetCount - 1;
        block0: for (int i2 = fromIndex; i2 <= max; ++i2) {
            if (this.builder.charAt(i2) != strFirstChar) continue;
            for (int j2 = 1; j2 < targetCount; ++j2) {
                if (this.builder.charAt(i2 + j2) != target.charAt(j2)) continue block0;
            }
            return i2;
        }
        return -1;
    }

    public int lastIndexOf(@Nonnull CharSequence target, int fromIndex, int endIndex) {
        int targetCount;
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("index out of range: " + fromIndex);
        }
        if (endIndex < 0) {
            throw new IndexOutOfBoundsException("index out of range: " + endIndex);
        }
        if (fromIndex > this.length()) {
            throw new IndexOutOfBoundsException("fromIndex > length()");
        }
        if (fromIndex > endIndex) {
            throw new IndexOutOfBoundsException("fromIndex > endIndex");
        }
        if (endIndex >= this.builder.length()) {
            endIndex = this.builder.length() - 1;
        }
        if ((targetCount = target.length()) == 0) {
            return endIndex;
        }
        int rightIndex = endIndex - targetCount;
        if (fromIndex > rightIndex) {
            fromIndex = rightIndex;
        }
        int strLastIndex = targetCount - 1;
        char strLastChar = target.charAt(strLastIndex);
        int min = fromIndex + targetCount - 1;
        block0: for (int i2 = endIndex; i2 >= min; --i2) {
            if (this.builder.charAt(i2) != strLastChar) continue;
            int j2 = strLastIndex - 1;
            int k2 = 1;
            while (j2 >= 0) {
                if (this.builder.charAt(i2 - k2) != target.charAt(j2)) continue block0;
                --j2;
                ++k2;
            }
            return i2 - target.length() + 1;
        }
        return -1;
    }

    @Nonnull
    public Message build() {
        String message = this.builder.toString();
        if (this.isEmpty()) {
            throw new IllegalStateException("Cannot build a Message with no content. (You never added any content to the message)");
        }
        if (message.length() > 2000) {
            throw new IllegalStateException("Cannot build a Message with more than 2000 characters. Please limit your input.");
        }
        String[] ids = new String[]{};
        return new DataMessage(this.isTTS, message, this.nonce, this.embed, this.allowedMentions, this.mentionedUsers.toArray(ids), this.mentionedRoles.toArray(ids), this.components.toArray(new ComponentLayout[0]));
    }

    @Nonnull
    public Queue<Message> buildAll(SplitPolicy ... policy) {
        if (this.isEmpty()) {
            throw new UnsupportedOperationException("Cannot build a Message with no content. (You never added any content to the message)");
        }
        LinkedList<Message> messages = new LinkedList<Message>();
        if (this.builder.length() <= 2000) {
            messages.add(this.build());
            return messages;
        }
        if (policy == null || policy.length == 0) {
            policy = new SplitPolicy[]{SplitPolicy.ANYWHERE};
        }
        int currentBeginIndex = 0;
        block0: while (currentBeginIndex < this.builder.length() - 2001) {
            for (SplitPolicy splitPolicy : policy) {
                int currentEndIndex = splitPolicy.nextMessage(currentBeginIndex, this);
                if (currentEndIndex == -1) continue;
                messages.add(this.build(currentBeginIndex, currentEndIndex));
                currentBeginIndex = currentEndIndex;
                continue block0;
            }
            throw new IllegalStateException("Failed to split the messages");
        }
        if (currentBeginIndex < this.builder.length() - 1) {
            messages.add(this.build(currentBeginIndex, this.builder.length() - 1));
        }
        if (this.embed != null) {
            ((DataMessage)messages.get(messages.size() - 1)).setEmbed(this.embed);
        }
        return messages;
    }

    @Nonnull
    protected DataMessage build(int beginIndex, int endIndex) {
        String[] ids = new String[]{};
        return new DataMessage(this.isTTS, this.builder.substring(beginIndex, endIndex), null, null, this.allowedMentions, this.mentionedUsers.toArray(ids), this.mentionedRoles.toArray(ids), this.components.toArray(new ComponentLayout[0]));
    }

    private String[] toStringArray(long[] users) {
        String[] ids = new String[users.length];
        for (int i2 = 0; i2 < ids.length; ++i2) {
            ids[i2] = Long.toUnsignedString(users[i2]);
        }
        return ids;
    }

    public static enum Formatting {
        ITALICS("*"),
        BOLD("**"),
        STRIKETHROUGH("~~"),
        UNDERLINE("__"),
        BLOCK("`");

        private final String tag;

        private Formatting(String tag) {
            this.tag = tag;
        }

        @Nonnull
        private String getTag() {
            return this.tag;
        }
    }

    public static interface SplitPolicy {
        public static final SplitPolicy NEWLINE = new CharSequenceSplitPolicy("\n", true);
        public static final SplitPolicy SPACE = new CharSequenceSplitPolicy(" ", true);
        public static final SplitPolicy ANYWHERE = (i2, b2) -> Math.min(i2 + 2000, b2.length());

        @Nonnull
        public static SplitPolicy onChars(@Nonnull CharSequence chars, boolean remove) {
            return new CharSequenceSplitPolicy(chars, remove);
        }

        public int nextMessage(int var1, MessageBuilder var2);

        public static class CharSequenceSplitPolicy
        implements SplitPolicy {
            private final boolean remove;
            private final CharSequence chars;

            private CharSequenceSplitPolicy(@Nonnull CharSequence chars, boolean remove) {
                this.chars = chars;
                this.remove = remove;
            }

            @Override
            public int nextMessage(int currentBeginIndex, MessageBuilder builder) {
                int currentEndIndex = builder.lastIndexOf(this.chars, currentBeginIndex, currentBeginIndex + 2000 - (this.remove ? this.chars.length() : 0));
                if (currentEndIndex < 0) {
                    return -1;
                }
                return currentEndIndex + this.chars.length();
            }
        }
    }
}

