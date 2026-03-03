/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.handle;

import java.util.Objects;
import net.dv8tion.jda.api.events.self.SelfUpdateAvatarEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateMFAEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateNameEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateVerifiedEvent;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.SelfUserImpl;
import net.dv8tion.jda.internal.handle.SocketHandler;

public class UserUpdateHandler
extends SocketHandler {
    public UserUpdateHandler(JDAImpl api2) {
        super(api2);
    }

    @Override
    protected Long handleInternally(DataObject content) {
        SelfUserImpl self = (SelfUserImpl)this.getJDA().getSelfUser();
        String name = content.getString("username");
        String discriminator = content.getString("discriminator");
        String avatarId = content.getString("avatar", null);
        Boolean verified = content.hasKey("verified") ? Boolean.valueOf(content.getBoolean("verified")) : null;
        Boolean mfaEnabled = content.hasKey("mfa_enabled") ? Boolean.valueOf(content.getBoolean("mfa_enabled")) : null;
        String email = content.getString("email", null);
        Boolean mobile = content.hasKey("mobile") ? Boolean.valueOf(content.getBoolean("mobile")) : null;
        Boolean nitro = content.hasKey("premium") ? Boolean.valueOf(content.getBoolean("premium")) : null;
        String phoneNumber = content.getString("phone", null);
        if (!Objects.equals(name, self.getName()) || !Objects.equals(discriminator, self.getDiscriminator())) {
            String oldName = self.getName();
            self.setName(name);
            this.getJDA().handleEvent(new SelfUpdateNameEvent(this.getJDA(), this.responseNumber, oldName));
        }
        if (!Objects.equals(avatarId, self.getAvatarId())) {
            String oldAvatarId = self.getAvatarId();
            self.setAvatarId(avatarId);
            this.getJDA().handleEvent(new SelfUpdateAvatarEvent(this.getJDA(), this.responseNumber, oldAvatarId));
        }
        if (verified != null && verified.booleanValue() != self.isVerified()) {
            boolean wasVerified = self.isVerified();
            self.setVerified(verified);
            this.getJDA().handleEvent(new SelfUpdateVerifiedEvent(this.getJDA(), this.responseNumber, wasVerified));
        }
        if (mfaEnabled != null && mfaEnabled.booleanValue() != self.isMfaEnabled()) {
            boolean wasMfaEnabled = self.isMfaEnabled();
            self.setMfaEnabled(mfaEnabled);
            this.getJDA().handleEvent(new SelfUpdateMFAEvent(this.getJDA(), this.responseNumber, wasMfaEnabled));
        }
        return null;
    }
}

