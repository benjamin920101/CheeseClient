/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Inherited
@Target(value={ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface IID {
    public String value();
}

