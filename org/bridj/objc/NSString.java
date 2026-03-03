/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.objc;

import java.nio.charset.Charset;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.objc.FoundationLibrary;
import org.bridj.objc.NSObject;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Library(value="Foundation")
public class NSString
extends NSObject {
    public native int length();

    public native boolean isAbsolutePath();

    public native Pointer<Byte> UTF8String();

    public NSString() {
    }

    public NSString(String s2) {
        super((Pointer<? extends NSObject>)FoundationLibrary.pointerToNSString(s2));
    }

    @Override
    public String toString() {
        return this.UTF8String().getString(Pointer.StringType.C, Charset.forName("utf-8"));
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    public static NSString valueOf(String s2) {
        return FoundationLibrary.pointerToNSString(s2).get();
    }
}

