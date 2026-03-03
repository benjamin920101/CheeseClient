/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.util.List;
import org.bridj.Pointer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface NativeList<T>
extends List<T> {
    public Pointer<?> getPointer();
}

