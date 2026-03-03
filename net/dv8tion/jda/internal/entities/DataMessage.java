/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageActivity;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageType;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ComponentLayout;
import net.dv8tion.jda.internal.entities.AbstractMessage;

public class DataMessage
extends AbstractMessage {
    private final EnumSet<Message.MentionType> allowedMentions;
    private final String[] mentionedRoles;
    private final String[] mentionedUsers;
    private final ComponentLayout[] components;
    private MessageEmbed embed;

    public DataMessage(boolean tts, String content, String nonce, MessageEmbed embed, EnumSet<Message.MentionType> allowedMentions, String[] mentionedUsers, String[] mentionedRoles, ComponentLayout[] components) {
        super(content, nonce, tts);
        this.embed = embed;
        this.allowedMentions = allowedMentions;
        this.mentionedUsers = mentionedUsers;
        this.mentionedRoles = mentionedRoles;
        this.components = components;
    }

    public DataMessage(boolean tts, String content, String nonce, MessageEmbed embed) {
        this(tts, content, nonce, embed, null, new String[0], new String[0], new ComponentLayout[0]);
    }

    public EnumSet<Message.MentionType> getAllowedMentions() {
        return this.allowedMentions;
    }

    public String[] getMentionedRolesWhitelist() {
        return this.mentionedRoles;
    }

    public String[] getMentionedUsersWhitelist() {
        return this.mentionedUsers;
    }

    @Override
    @Nonnull
    public MessageType getType() {
        return MessageType.DEFAULT;
    }

    public boolean equals(Object o2) {
        if (this == o2) {
            return true;
        }
        if (!(o2 instanceof DataMessage)) {
            return false;
        }
        DataMessage other = (DataMessage)o2;
        return this.isTTS == other.isTTS && other.content.equals(this.content) && Objects.equals(other.nonce, this.nonce) && Objects.equals(other.embed, this.embed);
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }

    public String toString() {
        return String.format("DataMessage(%.30s)", this.getContentRaw());
    }

    public DataMessage setEmbed(MessageEmbed embed) {
        this.embed = embed;
        return this;
    }

    @Override
    @Nonnull
    public List<MessageEmbed> getEmbeds() {
        return this.embed == null ? Collections.emptyList() : Collections.singletonList(this.embed);
    }

    @Override
    @Nonnull
    public List<ActionRow> getActionRows() {
        return this.components == null ? Collections.emptyList() : Arrays.stream(this.components).filter(ActionRow.class::isInstance).map(ActionRow.class::cast).collect(Collectors.toList());
    }

    @Override
    protected void unsupported() {
        throw new UnsupportedOperationException("This operation is not supported for Messages that were created by a MessageBuilder!");
    }

    @Override
    @Nullable
    public MessageActivity getActivity() {
        this.unsupported();
        return null;
    }

    @Override
    public long getIdLong() {
        this.unsupported();
        return 0L;
    }
}

