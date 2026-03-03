/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com;

import org.bridj.Pointer;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.GUID;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.ITypeInfo;
import org.bridj.cpp.com.IUnknown;
import org.bridj.cpp.com.VARIANT;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@IID(value="0000002F-0000-0000-C000-000000000046")
public class IRecordInfo
extends IUnknown {
    @Virtual(value=0)
    public native int RecordInit(Pointer<?> var1);

    @Virtual(value=1)
    public native int RecordClear(Pointer<?> var1);

    @Virtual(value=2)
    public native int RecordCopy(Pointer<?> var1, Pointer<?> var2);

    @Virtual(value=3)
    public native int GetGuid(Pointer<GUID> var1);

    @Virtual(value=4)
    public native int GetName(Pointer<Pointer<Byte>> var1);

    @Virtual(value=5)
    public native int GetSize(Pointer<Integer> var1);

    @Virtual(value=6)
    public native int GetTypeInfo(Pointer<Pointer<ITypeInfo>> var1);

    @Virtual(value=7)
    public native int GetField(Pointer<?> var1, Pointer<Byte> var2, Pointer<VARIANT> var3);

    @Virtual(value=8)
    public native int GetFieldNoCopy(Pointer<?> var1, Pointer<Byte> var2, Pointer<VARIANT> var3, Pointer<Pointer<?>> var4);

    @Virtual(value=9)
    public native int PutField(int var1, Pointer<?> var2, Pointer<Byte> var3, Pointer<VARIANT> var4);

    @Virtual(value=10)
    public native int PutFieldNoCopy(int var1, Pointer<?> var2, Pointer<Byte> var3, Pointer<VARIANT> var4);

    @Virtual(value=11)
    public native int GetFieldNames(Pointer<Integer> var1, Pointer<Pointer<Byte>> var2);

    @Virtual(value=12)
    public native boolean IsMatchingType(Pointer<IRecordInfo> var1);

    @Virtual(value=13)
    public native Pointer<?> RecordCreate();

    @Virtual(value=14)
    public native int RecordCreateCopy(Pointer<?> var1, Pointer<Pointer<?>> var2);

    @Virtual(value=15)
    public native int RecordDestroy(Pointer<?> var1);
}

