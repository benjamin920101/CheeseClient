/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

class NativeConstants {
    NativeConstants() {
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static enum CallbackType {
        eJavaCallbackToNativeFunction,
        eNativeToJavaCallback,
        eJavaToNativeFunction,
        eJavaToVirtualMethod;

    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    static enum ValueType {
        eVoidValue,
        eWCharValue,
        eCLongValue,
        eCLongObjectValue,
        eSizeTValue,
        eSizeTObjectValue,
        eIntValue,
        eShortValue,
        eByteValue,
        eBooleanValue,
        eLongValue,
        eDoubleValue,
        eFloatValue,
        ePointerValue,
        eEllipsis,
        eIntFlagSet,
        eNativeObjectValue,
        eTimeTObjectValue;

    }
}

