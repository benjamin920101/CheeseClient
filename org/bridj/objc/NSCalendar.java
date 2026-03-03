/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.objc;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.objc.NSString;
import org.bridj.objc.ObjCObject;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Library(value="Foundation")
public class NSCalendar
extends ObjCObject {
    public static native Pointer<NSCalendar> currentCalendar();

    public native Pointer<NSString> calendarIdentifier();

    static {
        BridJ.register();
    }
}

