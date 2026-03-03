/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.bridj.MethodCallInfo;
import org.bridj.Platform;
import org.bridj.Pointer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class JNI {
    @Deprecated
    public static native long getEnv();

    @Deprecated
    public static native long getJVM();

    @Deprecated
    public static native Object refToObject(long var0);

    static native long loadLibrary(String var0);

    static native void freeLibrary(long var0);

    static native long loadLibrarySymbols(String var0);

    static native void freeLibrarySymbols(long var0);

    static native long findSymbolInLibrary(long var0, String var2);

    static native String[] getLibrarySymbols(long var0, long var2);

    static native String findSymbolName(long var0, long var2, long var4);

    public static native long newGlobalRef(Object var0);

    public static native void deleteGlobalRef(long var0);

    public static Pointer<?> getGlobalPointer(Object object) {
        return Pointer.pointerToAddress(JNI.newGlobalRef(object), new Pointer.Releaser(){

            @Override
            public void release(Pointer<?> p2) {
                JNI.deleteGlobalRef(p2.getPeer());
            }
        });
    }

    public static native long newWeakGlobalRef(Object var0);

    public static native void deleteWeakGlobalRef(long var0);

    public static native ByteBuffer newDirectByteBuffer(long var0, long var2);

    public static native long getDirectBufferAddress(Buffer var0);

    public static native long getDirectBufferCapacity(Buffer var0);

    @Deprecated
    static native long getIntArrayElements(int[] var0, boolean[] var1);

    @Deprecated
    static native void releaseIntArrayElements(int[] var0, long var1, int var3);

    @Deprecated
    static native int get_int(long var0);

    @Deprecated
    static native void set_int(long var0, int var2);

    @Deprecated
    static native int[] get_int_array(long var0, int var2);

    @Deprecated
    static native void set_int_array(long var0, int[] var2, int var3, int var4);

    @Deprecated
    static native int get_int_disordered(long var0);

    @Deprecated
    static native void set_int_disordered(long var0, int var2);

    @Deprecated
    static native int[] get_int_array_disordered(long var0, int var2);

    @Deprecated
    static native void set_int_array_disordered(long var0, int[] var2, int var3, int var4);

    @Deprecated
    static native long getLongArrayElements(long[] var0, boolean[] var1);

    @Deprecated
    static native void releaseLongArrayElements(long[] var0, long var1, int var3);

    @Deprecated
    static native long get_long(long var0);

    @Deprecated
    static native void set_long(long var0, long var2);

    @Deprecated
    static native long[] get_long_array(long var0, int var2);

    @Deprecated
    static native void set_long_array(long var0, long[] var2, int var3, int var4);

    @Deprecated
    static native long get_long_disordered(long var0);

    @Deprecated
    static native void set_long_disordered(long var0, long var2);

    @Deprecated
    static native long[] get_long_array_disordered(long var0, int var2);

    @Deprecated
    static native void set_long_array_disordered(long var0, long[] var2, int var3, int var4);

    @Deprecated
    static native long getShortArrayElements(short[] var0, boolean[] var1);

    @Deprecated
    static native void releaseShortArrayElements(short[] var0, long var1, int var3);

    @Deprecated
    static native short get_short(long var0);

    @Deprecated
    static native void set_short(long var0, short var2);

    @Deprecated
    static native short[] get_short_array(long var0, int var2);

    @Deprecated
    static native void set_short_array(long var0, short[] var2, int var3, int var4);

    @Deprecated
    static native short get_short_disordered(long var0);

    @Deprecated
    static native void set_short_disordered(long var0, short var2);

    @Deprecated
    static native short[] get_short_array_disordered(long var0, int var2);

    @Deprecated
    static native void set_short_array_disordered(long var0, short[] var2, int var3, int var4);

    @Deprecated
    static native long getByteArrayElements(byte[] var0, boolean[] var1);

    @Deprecated
    static native void releaseByteArrayElements(byte[] var0, long var1, int var3);

    @Deprecated
    static native byte get_byte(long var0);

    @Deprecated
    static native void set_byte(long var0, byte var2);

    @Deprecated
    static native byte[] get_byte_array(long var0, int var2);

    @Deprecated
    static native void set_byte_array(long var0, byte[] var2, int var3, int var4);

    @Deprecated
    static native long getCharArrayElements(char[] var0, boolean[] var1);

    @Deprecated
    static native void releaseCharArrayElements(char[] var0, long var1, int var3);

    @Deprecated
    static native char get_char(long var0);

    @Deprecated
    static native void set_char(long var0, char var2);

    @Deprecated
    static native char[] get_char_array(long var0, int var2);

    @Deprecated
    static native void set_char_array(long var0, char[] var2, int var3, int var4);

    @Deprecated
    static native char get_char_disordered(long var0);

    @Deprecated
    static native void set_char_disordered(long var0, char var2);

    @Deprecated
    static native char[] get_char_array_disordered(long var0, int var2);

    @Deprecated
    static native void set_char_array_disordered(long var0, char[] var2, int var3, int var4);

    @Deprecated
    static native long getFloatArrayElements(float[] var0, boolean[] var1);

    @Deprecated
    static native void releaseFloatArrayElements(float[] var0, long var1, int var3);

    @Deprecated
    static native float get_float(long var0);

    @Deprecated
    static native void set_float(long var0, float var2);

    @Deprecated
    static native float[] get_float_array(long var0, int var2);

    @Deprecated
    static native void set_float_array(long var0, float[] var2, int var3, int var4);

    @Deprecated
    static native float get_float_disordered(long var0);

    @Deprecated
    static native void set_float_disordered(long var0, float var2);

    @Deprecated
    static native float[] get_float_array_disordered(long var0, int var2);

    @Deprecated
    static native void set_float_array_disordered(long var0, float[] var2, int var3, int var4);

    @Deprecated
    static native long getDoubleArrayElements(double[] var0, boolean[] var1);

    @Deprecated
    static native void releaseDoubleArrayElements(double[] var0, long var1, int var3);

    @Deprecated
    static native double get_double(long var0);

    @Deprecated
    static native void set_double(long var0, double var2);

    @Deprecated
    static native double[] get_double_array(long var0, int var2);

    @Deprecated
    static native void set_double_array(long var0, double[] var2, int var3, int var4);

    @Deprecated
    static native double get_double_disordered(long var0);

    @Deprecated
    static native void set_double_disordered(long var0, double var2);

    @Deprecated
    static native double[] get_double_array_disordered(long var0, int var2);

    @Deprecated
    static native void set_double_array_disordered(long var0, double[] var2, int var3, int var4);

    @Deprecated
    static native long getBooleanArrayElements(boolean[] var0, boolean[] var1);

    @Deprecated
    static native void releaseBooleanArrayElements(boolean[] var0, long var1, int var3);

    @Deprecated
    static native boolean get_boolean(long var0);

    @Deprecated
    static native void set_boolean(long var0, boolean var2);

    @Deprecated
    static native boolean[] get_boolean_array(long var0, int var2);

    @Deprecated
    static native void set_boolean_array(long var0, boolean[] var2, int var3, int var4);

    public static native void callSinglePointerArgVoidFunction(long var0, long var2, int var4);

    static native long createCToJavaCallback(MethodCallInfo var0);

    static native long getActualCToJavaCallback(long var0);

    static native long bindJavaMethodsToObjCMethods(MethodCallInfo ... var0);

    static native long bindJavaToCCallbacks(MethodCallInfo ... var0);

    static native long bindJavaMethodsToCFunctions(MethodCallInfo ... var0);

    static native long bindJavaMethodsToVirtualMethods(MethodCallInfo ... var0);

    static native void freeCToJavaCallback(long var0);

    static native void freeObjCMethodBindings(long var0, int var2);

    static native void freeJavaToCCallbacks(long var0, int var2);

    static native void freeCFunctionBindings(long var0, int var2);

    static native void freeVirtualMethodBindings(long var0, int var2);

    static native long createCallTempStruct();

    static native void deleteCallTempStruct(long var0);

    static native long mallocNulled(long var0);

    static native long mallocNulledAligned(long var0, int var2);

    static native long malloc(long var0);

    static native void free(long var0);

    static native long strlen(long var0);

    static native long wcslen(long var0);

    static native void memcpy(long var0, long var2, long var4);

    static native void memmove(long var0, long var2, long var4);

    static native long memchr(long var0, byte var2, long var3);

    static native long memmem(long var0, long var2, long var4, long var6);

    static native long memmem_last(long var0, long var2, long var4, long var6);

    static native int memcmp(long var0, long var2, long var4);

    static native void memset(long var0, byte var2, long var3);

    static {
        Platform.initLibrary();
    }
}

