/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.bridj.StructIO;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Target(value={ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
@Inherited
public @interface Struct {
    public int pack() default -1;

    public int fieldCount() default -1;

    public int size() default -1;

    public Class<? extends StructIO.Customizer> customizer() default StructIO.Customizer.class;
}

