/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.entities;

import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.managers.AccountManager;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.UserImpl;
import net.dv8tion.jda.internal.managers.AccountManagerImpl;

public class SelfUserImpl
extends UserImpl
implements SelfUser {
    protected AccountManager manager;
    private boolean verified;
    private boolean mfaEnabled;
    private long applicationId;
    private String email;
    private String phoneNumber;
    private boolean mobile;
    private boolean nitro;

    public SelfUserImpl(long id2, JDAImpl api2) {
        super(id2, api2);
        this.applicationId = id2;
    }

    @Override
    public boolean hasPrivateChannel() {
        return false;
    }

    @Override
    public PrivateChannel getPrivateChannel() {
        throw new UnsupportedOperationException("You cannot get a PrivateChannel with yourself (SelfUser)");
    }

    @Override
    @Nonnull
    public RestAction<PrivateChannel> openPrivateChannel() {
        throw new UnsupportedOperationException("You cannot open a PrivateChannel with yourself (SelfUser)");
    }

    @Override
    public long getApplicationIdLong() {
        return this.applicationId;
    }

    @Override
    public boolean isVerified() {
        return this.verified;
    }

    @Override
    public boolean isMfaEnabled() {
        return this.mfaEnabled;
    }

    @Override
    public long getAllowedFileSize() {
        if (this.nitro) {
            return 0x3200000L;
        }
        return 0x800000L;
    }

    @Override
    @Nonnull
    public AccountManager getManager() {
        if (this.manager == null) {
            this.manager = new AccountManagerImpl(this);
            return this.manager;
        }
        return this.manager;
    }

    public SelfUserImpl setVerified(boolean verified) {
        this.verified = verified;
        return this;
    }

    public SelfUserImpl setMfaEnabled(boolean enabled) {
        this.mfaEnabled = enabled;
        return this;
    }

    public SelfUserImpl setEmail(String email) {
        this.email = email;
        return this;
    }

    public SelfUserImpl setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public SelfUserImpl setMobile(boolean mobile) {
        this.mobile = mobile;
        return this;
    }

    public SelfUserImpl setNitro(boolean nitro) {
        this.nitro = nitro;
        return this;
    }

    public SelfUserImpl setApplicationId(long id2) {
        this.applicationId = id2;
        return this;
    }

    public static SelfUserImpl copyOf(SelfUserImpl other, JDAImpl jda) {
        SelfUserImpl selfUser = new SelfUserImpl(other.id, jda);
        selfUser.setName(other.name).setAvatarId(other.avatarId).setDiscriminator(other.getDiscriminator()).setBot(other.bot);
        return selfUser.setVerified(other.verified).setMfaEnabled(other.mfaEnabled).setEmail(other.email).setPhoneNumber(other.phoneNumber).setMobile(other.mobile).setNitro(other.nitro).setApplicationId(other.applicationId);
    }
}

