/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.dyncall;

import org.bridj.BridJ;
import org.bridj.CRuntime;
import org.bridj.Pointer;
import org.bridj.ann.CLong;
import org.bridj.ann.Library;
import org.bridj.ann.Optional;
import org.bridj.ann.Ptr;
import org.bridj.ann.Runtime;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Library(value="bridj")
@Runtime(value=CRuntime.class)
public class DyncallLibrary {
    public static final char DC_SIGCHAR_FLOAT = 'f';
    public static final int DC_CALL_C_DEFAULT = 0;
    public static final int DC_CALL_C_PPC32_OSX = 9;
    public static final int DC_CALL_C_PPC32_SYSV = 13;
    public static final int DC_CALL_C_PPC32_DARWIN = 9;
    public static final int DC_ERROR_UNSUPPORTED_MODE = -1;
    public static final int DC_CALL_C_ELLIPSIS_VARARGS = 101;
    public static final int DC_CALL_C_X86_WIN32_THIS_MS = 5;
    public static final int DC_CALL_C_ARM_ARM_EABI = 10;
    public static final int DC_CALL_SYS_X86_INT80H_BSD = 202;
    public static final char DC_SIGCHAR_CC_ELLIPSIS = 'e';
    public static final int DC_CALL_C_X64_SYSV = 8;
    public static final char DC_SIGCHAR_POINTER = 'p';
    public static final int DEFAULT_ALIGNMENT = 0;
    public static final char DC_SIGCHAR_CC_FASTCALL_GNU = 'f';
    public static final char DC_SIGCHAR_UINT = 'I';
    public static final char DC_SIGCHAR_ENDARG = ')';
    public static final char DC_SIGCHAR_VOID = 'v';
    public static final char DC_SIGCHAR_UCHAR = 'C';
    public static final int DC_CALL_C_MIPS32_O32 = 16;
    public static final char DC_SIGCHAR_INT = 'i';
    public static final int DC_CALL_SYS_X86_INT80H_LINUX = 201;
    public static final char DC_SIGCHAR_DOUBLE = 'd';
    public static final int DC_CALL_C_X64_WIN64 = 7;
    public static final int DC_CALL_C_SPARC32 = 20;
    public static final int DC_CALL_C_ARM_THUMB_EABI = 11;
    public static final char DC_SIGCHAR_STRUCT = 'T';
    public static final int DC_CALL_C_X86_WIN32_THIS_GNU = 6;
    public static final int DC_CALL_SYS_DEFAULT = 200;
    public static final char DC_SIGCHAR_CC_STDCALL = 's';
    public static final int DC_CALL_C_ELLIPSIS = 100;
    public static final int DC_CALL_C_X86_PLAN9 = 19;
    public static final int DC_CALL_C_ARM_THUMB = 15;
    public static final char DC_SIGCHAR_CC_FASTCALL_MS = 'F';
    public static final char DC_SIGCHAR_STRING = 'Z';
    public static final int DC_CALL_C_MIPS32_EABI = 12;
    public static final int DC_CALL_C_X86_WIN32_FAST_GNU = 4;
    public static final char DC_SIGCHAR_LONGLONG = 'l';
    public static final char DC_SIGCHAR_SHORT = 's';
    public static final char DC_SIGCHAR_ULONGLONG = 'L';
    public static final int DC_ERROR_NONE = 0;
    public static final int DC_CALL_C_SPARC64 = 21;
    public static final int DC_CALL_C_PPC32_LINUX = 13;
    public static final char DC_SIGCHAR_ULONG = 'J';
    public static final char DC_SIGCHAR_CHAR = 'c';
    public static final char DC_SIGCHAR_CC_PREFIX = '_';
    public static final char DC_SIGCHAR_LONG = 'j';
    public static final int DC_CALL_C_MIPS64_N32 = 17;
    public static final int DC_CALL_C_X86_WIN32_STD = 2;
    public static final char DC_SIGCHAR_CC_THISCALL_MS = '+';
    public static final int DC_CALL_C_X86_CDECL = 1;
    public static final int DC_CALL_C_X86_WIN32_FAST_MS = 3;
    public static final int DC_CALL_C_ARM_ARM = 14;
    public static final char DC_SIGCHAR_USHORT = 'S';
    public static final char DC_SIGCHAR_BOOL = 'B';
    public static final int DC_CALL_C_MIPS64_N64 = 18;

    public static native Pointer<DCCallVM> dcNewCallVM(@Ptr long var0);

    public static native void dcFree(Pointer<DCCallVM> var0);

    public static native void dcReset(Pointer<DCCallVM> var0);

    public static native void dcMode(Pointer<DCCallVM> var0, int var1);

    public static native void dcArgBool(Pointer<DCCallVM> var0, int var1);

    public static native void dcArgChar(Pointer<DCCallVM> var0, byte var1);

    public static native void dcArgShort(Pointer<DCCallVM> var0, short var1);

    public static native void dcArgInt(Pointer<DCCallVM> var0, int var1);

    public static native void dcArgLong(Pointer<DCCallVM> var0, @CLong long var1);

    public static native void dcArgLongLong(Pointer<DCCallVM> var0, long var1);

    public static native void dcArgFloat(Pointer<DCCallVM> var0, float var1);

    public static native void dcArgDouble(Pointer<DCCallVM> var0, double var1);

    public static native void dcArgPointer(Pointer<DCCallVM> var0, Pointer<?> var1);

    @Optional
    public static native void dcArgStruct(Pointer<DCCallVM> var0, Pointer<DCstruct> var1, Pointer<?> var2);

    public static native void dcCallVoid(Pointer<DCCallVM> var0, Pointer<?> var1);

    public static native int dcCallBool(Pointer<DCCallVM> var0, Pointer<?> var1);

    public static native byte dcCallChar(Pointer<DCCallVM> var0, Pointer<?> var1);

    public static native short dcCallShort(Pointer<DCCallVM> var0, Pointer<?> var1);

    public static native int dcCallInt(Pointer<DCCallVM> var0, Pointer<?> var1);

    @CLong
    public static native long dcCallLong(Pointer<DCCallVM> var0, Pointer<?> var1);

    public static native long dcCallLongLong(Pointer<DCCallVM> var0, Pointer<?> var1);

    public static native float dcCallFloat(Pointer<DCCallVM> var0, Pointer<?> var1);

    public static native double dcCallDouble(Pointer<DCCallVM> var0, Pointer<?> var1);

    public static native Pointer<?> dcCallPointer(Pointer<DCCallVM> var0, Pointer<?> var1);

    @Optional
    public static native void dcCallStruct(Pointer<DCCallVM> var0, Pointer<?> var1, Pointer<DCstruct> var2, Pointer<?> var3);

    public static native int dcGetError(Pointer<DCCallVM> var0);

    @Optional
    public static native Pointer<DCstruct> dcNewStruct(@Ptr long var0, int var2);

    @Optional
    public static native void dcStructField(Pointer<DCstruct> var0, int var1, int var2, @Ptr long var3);

    @Optional
    public static native void dcSubStruct(Pointer<DCstruct> var0, @Ptr long var1, int var3, @Ptr long var4);

    @Optional
    public static native void dcCloseStruct(Pointer<DCstruct> var0);

    @Optional
    @Ptr
    public static native long dcStructSize(Pointer<DCstruct> var0);

    @Optional
    @Ptr
    public static native long dcStructAlignment(Pointer<DCstruct> var0);

    @Optional
    public static native void dcFreeStruct(Pointer<DCstruct> var0);

    @Optional
    public static native Pointer<DCstruct> dcDefineStruct(Pointer<Byte> var0);

    static {
        BridJ.register();
    }

    public static interface DCCallVM {
    }

    public static interface DCstruct {
    }
}

