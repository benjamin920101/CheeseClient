/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

public abstract class NativeError
extends Error {
    protected NativeError(String message) {
        super(message);
    }

    static String toHex(long address) {
        return "0x" + Long.toHexString(address);
    }
}

