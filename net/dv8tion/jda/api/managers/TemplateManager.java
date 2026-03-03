/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.managers;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.managers.Manager;

public interface TemplateManager
extends Manager<TemplateManager> {
    public static final long NAME = 1L;
    public static final long DESCRIPTION = 2L;

    @Override
    @Nonnull
    public TemplateManager reset(long var1);

    @Override
    @Nonnull
    public TemplateManager reset(long ... var1);

    @Nonnull
    @CheckReturnValue
    public TemplateManager setName(@Nonnull String var1);

    @Nonnull
    @CheckReturnValue
    public TemplateManager setDescription(@Nullable String var1);
}

