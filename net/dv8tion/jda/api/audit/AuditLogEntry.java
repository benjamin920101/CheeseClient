/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.audit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.audit.ActionType;
import net.dv8tion.jda.api.audit.AuditLogChange;
import net.dv8tion.jda.api.audit.AuditLogKey;
import net.dv8tion.jda.api.audit.AuditLogOption;
import net.dv8tion.jda.api.audit.TargetType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.internal.entities.GuildImpl;
import net.dv8tion.jda.internal.entities.UserImpl;
import net.dv8tion.jda.internal.entities.WebhookImpl;
import net.dv8tion.jda.internal.utils.Checks;

public class AuditLogEntry
implements ISnowflake {
    protected final long id;
    protected final long targetId;
    protected final GuildImpl guild;
    protected final UserImpl user;
    protected final WebhookImpl webhook;
    protected final String reason;
    protected final Map<String, AuditLogChange> changes;
    protected final Map<String, Object> options;
    protected final ActionType type;
    protected final int rawType;

    public AuditLogEntry(ActionType type, int rawType, long id2, long targetId, GuildImpl guild, UserImpl user, WebhookImpl webhook, String reason, Map<String, AuditLogChange> changes, Map<String, Object> options) {
        this.rawType = rawType;
        this.type = type;
        this.id = id2;
        this.targetId = targetId;
        this.guild = guild;
        this.user = user;
        this.webhook = webhook;
        this.reason = reason;
        this.changes = changes != null && !changes.isEmpty() ? Collections.unmodifiableMap(changes) : Collections.emptyMap();
        this.options = options != null && !options.isEmpty() ? Collections.unmodifiableMap(options) : Collections.emptyMap();
    }

    @Override
    public long getIdLong() {
        return this.id;
    }

    public long getTargetIdLong() {
        return this.targetId;
    }

    @Nonnull
    public String getTargetId() {
        return Long.toUnsignedString(this.targetId);
    }

    @Nullable
    public Webhook getWebhook() {
        return this.webhook;
    }

    @Nonnull
    public Guild getGuild() {
        return this.guild;
    }

    @Nullable
    public User getUser() {
        return this.user;
    }

    @Nullable
    public String getReason() {
        return this.reason;
    }

    @Nonnull
    public JDA getJDA() {
        return this.guild.getJDA();
    }

    @Nonnull
    public Map<String, AuditLogChange> getChanges() {
        return this.changes;
    }

    @Nullable
    public AuditLogChange getChangeByKey(@Nullable AuditLogKey key) {
        return key == null ? null : this.getChangeByKey(key.getKey());
    }

    @Nullable
    public AuditLogChange getChangeByKey(@Nullable String key) {
        return this.changes.get(key);
    }

    @Nonnull
    public List<AuditLogChange> getChangesForKeys(AuditLogKey ... keys) {
        Checks.notNull(keys, "Keys");
        ArrayList<AuditLogChange> changes = new ArrayList<AuditLogChange>(keys.length);
        for (AuditLogKey key : keys) {
            AuditLogChange change = this.getChangeByKey(key);
            if (change == null) continue;
            changes.add(change);
        }
        return Collections.unmodifiableList(changes);
    }

    @Nonnull
    public Map<String, Object> getOptions() {
        return this.options;
    }

    @Nullable
    public <T> T getOptionByName(@Nullable String name) {
        return (T)this.options.get(name);
    }

    @Nullable
    public <T> T getOption(@Nonnull AuditLogOption option) {
        Checks.notNull((Object)option, "Option");
        return this.getOptionByName(option.getKey());
    }

    @Nonnull
    public List<Object> getOptions(AuditLogOption ... options) {
        Checks.notNull(options, "Options");
        ArrayList items = new ArrayList(options.length);
        for (AuditLogOption option : options) {
            Object obj = this.getOption(option);
            if (obj == null) continue;
            items.add(obj);
        }
        return Collections.unmodifiableList(items);
    }

    @Nonnull
    public ActionType getType() {
        return this.type;
    }

    public int getTypeRaw() {
        return this.rawType;
    }

    @Nonnull
    public TargetType getTargetType() {
        return this.type.getTargetType();
    }

    public int hashCode() {
        return Long.hashCode(this.id);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AuditLogEntry)) {
            return false;
        }
        AuditLogEntry other = (AuditLogEntry)obj;
        return other.id == this.id && other.targetId == this.targetId;
    }

    public String toString() {
        return "ALE:" + (Object)((Object)this.type) + "(ID:" + this.id + " / TID:" + this.targetId + " / " + this.guild + ')';
    }
}

