/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.category.update;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ForRemoval;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.channel.category.GenericCategoryEvent;

@Deprecated
@ForRemoval(deadline="4.4.0")
@DeprecatedSince(value="4.2.0")
public class CategoryUpdatePermissionsEvent
extends GenericCategoryEvent {
    protected final List<IPermissionHolder> changed;

    public CategoryUpdatePermissionsEvent(@Nonnull JDA api2, long responseNumber, @Nonnull Category category, @Nonnull List<IPermissionHolder> changed) {
        super(api2, responseNumber, category);
        this.changed = changed;
    }

    @Nonnull
    public List<IPermissionHolder> getChangedPermissionHolders() {
        return this.changed;
    }

    @Nonnull
    public List<Role> getChangedRoles() {
        return this.changed.stream().filter(it2 -> it2 instanceof Role).map(Role.class::cast).collect(Collectors.toList());
    }

    @Nonnull
    public List<Member> getChangedMembers() {
        return this.changed.stream().filter(it2 -> it2 instanceof Member).map(Member.class::cast).collect(Collectors.toList());
    }
}

