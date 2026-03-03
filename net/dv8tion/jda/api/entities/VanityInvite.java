/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.entities;

import java.util.Objects;
import javax.annotation.Nonnull;

public class VanityInvite {
    private final String code;
    private final int uses;

    public VanityInvite(@Nonnull String code, int uses) {
        this.code = code;
        this.uses = uses;
    }

    @Nonnull
    public String getCode() {
        return this.code;
    }

    public int getUses() {
        return this.uses;
    }

    @Nonnull
    public String getUrl() {
        return "https://discord.gg/" + this.getCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VanityInvite)) {
            return false;
        }
        VanityInvite other = (VanityInvite)obj;
        return this.uses == other.uses && this.code.equals(other.code);
    }

    public int hashCode() {
        return Objects.hash(this.code, this.uses);
    }

    public String toString() {
        return "VanityInvite(" + this.code + ")";
    }
}

