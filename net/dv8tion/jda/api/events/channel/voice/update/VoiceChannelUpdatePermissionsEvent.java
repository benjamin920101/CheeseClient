/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.events.channel.voice.update;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ForRemoval;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.IPermissionHolder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.channel.voice.GenericVoiceChannelEvent;

@Deprecated
@ForRemoval(deadline="4.4.0")
@DeprecatedSince(value="4.2.0")
public class VoiceChannelUpdatePermissionsEvent
extends GenericVoiceChannelEvent {
    private final List<IPermissionHolder> changedPermHolders;

    public VoiceChannelUpdatePermissionsEvent(@Nonnull JDA api2, long responseNumber, @Nonnull VoiceChannel channel, @Nonnull List<IPermissionHolder> changed) {
        super(api2, responseNumber, channel);
        this.changedPermHolders = changed;
    }

    @Nonnull
    public List<IPermissionHolder> getChangedPermissionHolders() {
        return this.changedPermHolders;
    }

    @Nonnull
    public List<Role> getChangedRoles() {
        return this.changedPermHolders.stream().filter(p2 -> p2 instanceof Role).map(Role.class::cast).collect(Collectors.toList());
    }

    @Nonnull
    public List<Member> getChangedMembers() {
        return this.changedPermHolders.stream().filter(p2 -> p2 instanceof Member).map(Member.class::cast).collect(Collectors.toList());
    }
}

