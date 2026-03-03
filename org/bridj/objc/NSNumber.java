/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.objc;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.objc.NSObject;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Library(value="Foundation")
public class NSNumber
extends NSObject {
    public static native Pointer<NSNumber> numberWithBool(boolean var0);

    public static native Pointer<NSNumber> numberWithInt(int var0);

    public static native Pointer<NSNumber> numberWithDouble(double var0);

    public static native Pointer<NSNumber> numberWithLong(long var0);

    public static native Pointer<NSNumber> numberWithFloat(float var0);

    public native short shortValue();

    public native int intValue();

    public native long longValue();

    public native float floatValue();

    public native double doubleValue();

    public native int compare(Pointer<NSNumber> var1);

    public native boolean isEqualToNumber(Pointer<NSNumber> var1);

    @Override
    public boolean equals(Object o2) {
        if (!(o2 instanceof NSNumber)) {
            return false;
        }
        NSNumber nn = (NSNumber)o2;
        return this.isEqualToNumber(Pointer.pointerTo(nn));
    }

    static {
        BridJ.register();
    }
}

