/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import org.bridj.BridJ;
import org.bridj.BridJRuntime;
import org.bridj.NativeObjectInterface;
import org.bridj.Pointer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class NativeObject
implements NativeObjectInterface {
    protected Pointer<? extends NativeObject> peer;
    protected BridJRuntime.TypeInfo typeInfo;

    protected NativeObject(Pointer<? extends NativeObject> peer) {
        BridJ.initialize(this, peer);
    }

    protected NativeObject() {
        BridJ.initialize(this);
    }

    protected NativeObject(int constructorId, Object ... args) {
        BridJ.initialize(this, constructorId, args);
    }

    public NativeObject clone() throws CloneNotSupportedException {
        return BridJ.clone(this);
    }

    public boolean equals(Object o2) {
        if (!(o2 instanceof NativeObject)) {
            return false;
        }
        return this.typeInfo.equal(this, (NativeObject)o2);
    }
}

