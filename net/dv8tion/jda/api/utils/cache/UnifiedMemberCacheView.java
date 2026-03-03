/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils.cache;

import java.util.Collection;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.cache.CacheView;

public interface UnifiedMemberCacheView
extends CacheView<Member> {
    @Nonnull
    public List<Member> getElementsById(long var1);

    @Nonnull
    default public List<Member> getElementsById(@Nonnull String id2) {
        return this.getElementsById(MiscUtil.parseSnowflake(id2));
    }

    @Nonnull
    public List<Member> getElementsByUsername(@Nonnull String var1, boolean var2);

    @Nonnull
    default public List<Member> getElementsByUsername(@Nonnull String name) {
        return this.getElementsByUsername(name, false);
    }

    @Nonnull
    public List<Member> getElementsByNickname(@Nullable String var1, boolean var2);

    @Nonnull
    default public List<Member> getElementsByNickname(@Nullable String name) {
        return this.getElementsByNickname(name, false);
    }

    @Nonnull
    public List<Member> getElementsWithRoles(Role ... var1);

    @Nonnull
    public List<Member> getElementsWithRoles(@Nonnull Collection<Role> var1);
}

