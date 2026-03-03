/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.api.utils.data.SerializableData;

public class PermOverrideData
implements SerializableData {
    public static final int ROLE_TYPE = 0;
    public static final int MEMBER_TYPE = 1;
    public final int type;
    public final long id;
    public final long allow;
    public final long deny;

    public PermOverrideData(int type, long id2, long allow, long deny) {
        this.type = type;
        this.id = id2;
        this.allow = allow;
        this.deny = deny & (allow ^ 0xFFFFFFFFFFFFFFFFL);
    }

    public PermOverrideData(PermissionOverride override) {
        this.id = override.getIdLong();
        this.type = override.isMemberOverride() ? 1 : 0;
        this.allow = override.getAllowedRaw();
        this.deny = override.getDeniedRaw();
    }

    @Override
    @Nonnull
    public DataObject toData() {
        DataObject o2 = DataObject.empty();
        o2.put("type", this.type);
        o2.put("id", this.id);
        o2.put("allow", this.allow);
        o2.put("deny", this.deny);
        return o2;
    }

    public int hashCode() {
        return Long.hashCode(this.id);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PermOverrideData)) {
            return false;
        }
        PermOverrideData other = (PermOverrideData)obj;
        return other.id == this.id;
    }
}

