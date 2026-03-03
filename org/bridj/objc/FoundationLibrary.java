/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.objc;

import java.nio.charset.Charset;
import org.bridj.BridJ;
import org.bridj.CRuntime;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Ptr;
import org.bridj.ann.Runtime;
import org.bridj.objc.NSString;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Library(value="Foundation")
@Runtime(value=CRuntime.class)
public class FoundationLibrary {
    public static final int kCFStringEncodingASCII = 1536;
    public static final int kCFStringEncodingUnicode = 256;
    public static final int kCFStringEncodingUTF8 = 0x8000100;

    public static native Pointer<NSString> CFStringCreateWithBytes(Pointer<?> var0, Pointer<Byte> var1, @Ptr long var2, int var4, boolean var5);

    public static Pointer<NSString> pointerToNSString(String s2) {
        Pointer<Byte> p2 = Pointer.pointerToString(s2, Pointer.StringType.C, Charset.forName("utf-8"));
        assert (p2 != null);
        Pointer<NSString> ps2 = FoundationLibrary.CFStringCreateWithBytes(null, p2, p2.getValidBytes() - 1L, 0x8000100, false);
        assert (ps2 != null);
        return ps2;
    }

    static {
        BridJ.register();
    }
}

