/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.ITypeInfo;
import org.bridj.cpp.com.IUnknown;
import org.bridj.cpp.com.VARIANT;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@IID(value="00020400-0000-0000-C000-000000000046")
public class IDispatch
extends IUnknown {
    @Virtual(value=0)
    public native int GetTypeInfoCount(Pointer<Integer> var1);

    @Virtual(value=1)
    public native int GetTypeInfo(int var1, int var2, Pointer<Pointer<ITypeInfo>> var3);

    @Virtual(value=2)
    public native int GetIDsOfNames(Pointer var1, Pointer<Pointer<Character>> var2, int var3, int var4, Pointer<Integer> var5);

    @Virtual(value=3)
    public native int Invoke(int var1, Pointer<Byte> var2, int var3, short var4, Pointer<DISPPARAMS> var5, Pointer<VARIANT> var6, Pointer<EXCEPINFO> var7, Pointer<Integer> var8);

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class EXCEPINFO
    extends StructObject {
        @Field(value=0)
        public native short wCode();

        @Field(value=1)
        public native short wReserved();

        @Field(value=2)
        public native Pointer<Character> bstrSource();

        @Field(value=3)
        public native Pointer<Character> bstrDescription();

        @Field(value=4)
        public native Pointer<Character> bstrHelpFile();

        @Field(value=5)
        public native int dwHelpContext();

        @Field(value=6)
        public native Pointer<?> pvReserved();

        @Field(value=7)
        public native Pointer<?> pfnDeferredFillIn();

        @Field(value=8)
        public native int scode();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class DISPPARAMS
    extends StructObject {
        @Field(value=0)
        public native Pointer<VARIANT> rgvarg();

        @Field(value=1)
        public native Pointer<Integer> rgdispidNamedArgs();

        @Field(value=2)
        public native int cArgs();

        @Field(value=3)
        public native int cNamedArgs();
    }
}

