/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp;

import java.util.Map;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Runtime;
import org.bridj.cpp.CPPRuntime;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Runtime(value=CPPRuntime.class)
public abstract class CPPObject
extends StructObject {
    Map<Class<?>, Object[]> templateParameters;

    protected CPPObject() {
    }

    protected CPPObject(Pointer<? extends CPPObject> peer) {
        super((Pointer<? extends StructObject>)peer);
    }

    protected CPPObject(Void voidArg, int constructorId, Object ... args) {
        super(voidArg, constructorId, args);
    }
}

