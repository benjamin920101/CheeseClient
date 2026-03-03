/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.hooks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
@Inherited
public @interface SubscribeEvent {
}

