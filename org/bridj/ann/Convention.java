/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD, ElementType.TYPE, ElementType.FIELD, ElementType.PACKAGE, ElementType.PARAMETER, ElementType.CONSTRUCTOR})
@Inherited
public @interface Convention {
    public Style value();

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum Style {
        StdCall,
        FastCall,
        CDecl,
        Pascal,
        CLRCall,
        ThisCall;

    }
}

