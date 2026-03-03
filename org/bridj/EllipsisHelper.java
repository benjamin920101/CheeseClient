/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.bridj.CLong;
import org.bridj.NativeConstants;
import org.bridj.NativeObject;
import org.bridj.Pointer;
import org.bridj.SizeT;

class EllipsisHelper {
    static ThreadLocal<IntBuffer[]> holders = new ThreadLocal<IntBuffer[]>(){

        @Override
        protected IntBuffer[] initialValue() {
            return new IntBuffer[1];
        }
    };

    EllipsisHelper() {
    }

    public static IntBuffer unrollEllipsis(Object[] args) {
        IntBuffer[] holder = holders.get();
        int n2 = args.length;
        IntBuffer buf = holder[0];
        if (buf == null || buf.capacity() < n2) {
            buf = ByteBuffer.allocateDirect(n2 * 4).asIntBuffer();
        }
        for (int i2 = 0; i2 < n2; ++i2) {
            NativeConstants.ValueType type;
            Object arg2 = args[i2];
            if (arg2 == null || arg2 instanceof Pointer) {
                type = NativeConstants.ValueType.ePointerValue;
            } else if (arg2 instanceof Integer) {
                type = NativeConstants.ValueType.eIntValue;
            } else if (arg2 instanceof Long) {
                type = NativeConstants.ValueType.eLongValue;
            } else if (arg2 instanceof Short) {
                type = NativeConstants.ValueType.eShortValue;
            } else if (arg2 instanceof Double) {
                type = NativeConstants.ValueType.eDoubleValue;
            } else if (arg2 instanceof Float) {
                type = NativeConstants.ValueType.eFloatValue;
            } else if (arg2 instanceof Byte) {
                type = NativeConstants.ValueType.eByteValue;
            } else if (arg2 instanceof Boolean) {
                type = NativeConstants.ValueType.eBooleanValue;
            } else if (arg2 instanceof Character) {
                type = NativeConstants.ValueType.eWCharValue;
            } else if (arg2 instanceof SizeT) {
                type = NativeConstants.ValueType.eSizeTValue;
                args[i2] = arg2 = Long.valueOf(((SizeT)arg2).longValue());
            } else if (arg2 instanceof CLong) {
                type = NativeConstants.ValueType.eCLongValue;
                args[i2] = arg2 = Long.valueOf(((CLong)arg2).longValue());
            } else if (arg2 instanceof NativeObject) {
                type = NativeConstants.ValueType.eNativeObjectValue;
            } else {
                throw new IllegalArgumentException("Argument type not handled in variable argument calls  : " + arg2 + " (" + arg2.getClass().getName() + ")");
            }
            buf.put(i2, type.ordinal());
        }
        return buf;
    }
}

