/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.objc;

import org.bridj.CallbackInterface;
import org.bridj.Pointer;
import org.bridj.objc.ObjCObject;

public abstract class ObjCBlock
extends ObjCObject
implements CallbackInterface {
    Pointer<? extends CallbackInterface> pCallback;
}

