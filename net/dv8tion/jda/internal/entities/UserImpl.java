/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import java.util.EnumSet;
import java.util.Formatter;
import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.PrivateChannelImpl;
import net.dv8tion.jda.internal.entities.UserById;
import net.dv8tion.jda.internal.requests.DeferredRestAction;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Helpers;

public class UserImpl
extends UserById
implements User {
    protected final JDAImpl api;
    protected short discriminator;
    protected String name;
    protected String avatarId;
    protected long privateChannel = 0L;
    protected boolean bot;
    protected boolean system;
    protected boolean fake = false;
    protected int flags;

    public UserImpl(long id2, JDAImpl api2) {
        super(id2);
        this.api = api2;
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
    }

    @Override
    @Nonnull
    public String getDiscriminator() {
        return Helpers.format("%04d", this.discriminator);
    }

    @Override
    public String getAvatarId() {
        return this.avatarId;
    }

    @Override
    @Nonnull
    public String getDefaultAvatarId() {
        return String.valueOf(this.discriminator % 5);
    }

    @Override
    @Nonnull
    public String getAsTag() {
        return this.getName() + '#' + this.getDiscriminator();
    }

    @Override
    public boolean hasPrivateChannel() {
        return this.privateChannel != 0L;
    }

    @Override
    @Nonnull
    public RestAction<PrivateChannel> openPrivateChannel() {
        return new DeferredRestAction<PrivateChannel, RestActionImpl>(this.getJDA(), PrivateChannel.class, this::getPrivateChannel, () -> {
            Route.CompiledRoute route = Route.Self.CREATE_PRIVATE_CHANNEL.compile(new String[0]);
            DataObject body = DataObject.empty().put("recipient_id", this.getId());
            return new RestActionImpl<PrivateChannel>((JDA)this.getJDA(), route, body, (response, request) -> {
                PrivateChannel priv = this.api.getEntityBuilder().createPrivateChannel(response.getObject(), this);
                this.privateChannel = priv.getIdLong();
                return priv;
            });
        });
    }

    public PrivateChannel getPrivateChannel() {
        if (!this.hasPrivateChannel()) {
            return null;
        }
        PrivateChannel channel = this.getJDA().getPrivateChannelById(this.privateChannel);
        return channel != null ? channel : new PrivateChannelImpl(this.privateChannel, this);
    }

    @Override
    @Nonnull
    public List<Guild> getMutualGuilds() {
        return this.getJDA().getMutualGuilds(this);
    }

    @Override
    public boolean isBot() {
        return this.bot;
    }

    @Override
    public boolean isSystem() {
        return this.system;
    }

    @Override
    @Nonnull
    public JDAImpl getJDA() {
        return this.api;
    }

    @Override
    @Nonnull
    public EnumSet<User.UserFlag> getFlags() {
        return User.UserFlag.getFlags(this.flags);
    }

    @Override
    public int getFlagsRaw() {
        return this.flags;
    }

    @Override
    public String toString() {
        return "U:" + this.getName() + '(' + this.getId() + ')';
    }

    public UserImpl setName(String name) {
        this.name = name;
        return this;
    }

    public UserImpl setDiscriminator(String discriminator) {
        this.discriminator = Short.parseShort(discriminator);
        return this;
    }

    public UserImpl setAvatarId(String avatarId) {
        this.avatarId = avatarId;
        return this;
    }

    public UserImpl setPrivateChannel(PrivateChannel privateChannel) {
        if (privateChannel != null) {
            this.privateChannel = privateChannel.getIdLong();
        }
        return this;
    }

    public UserImpl setBot(boolean bot2) {
        this.bot = bot2;
        return this;
    }

    public UserImpl setSystem(boolean system) {
        this.system = system;
        return this;
    }

    public UserImpl setFake(boolean fake) {
        this.fake = fake;
        return this;
    }

    public UserImpl setFlags(int flags) {
        this.flags = flags;
        return this;
    }

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        boolean leftJustified;
        boolean alt2 = (flags & 4) == 4;
        boolean upper = (flags & 2) == 2;
        boolean bl2 = leftJustified = (flags & 1) == 1;
        String out = !alt2 ? this.getAsMention() : (upper ? this.getAsTag().toUpperCase() : this.getAsTag());
        MiscUtil.appendTo(formatter, width, precision, leftJustified, out);
    }
}

