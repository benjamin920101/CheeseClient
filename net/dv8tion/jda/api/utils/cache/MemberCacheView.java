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
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;

public interface MemberCacheView
extends SnowflakeCacheView<Member> {
    @Override
    @Nullable
    public Member getElementById(long var1);

    @Override
    @Nullable
    default public Member getElementById(@Nonnull String id2) {
        return this.getElementById(MiscUtil.parseSnowflake(id2));
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

