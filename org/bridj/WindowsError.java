/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.lang.reflect.Field;
import org.bridj.NativeError;
import org.bridj.WinExceptionsConstants;

class WindowsError
extends NativeError {
    final int code;
    final long info;
    final long address;

    WindowsError(int code, long info, long address) {
        super(WindowsError.computeMessage(code, info, address));
        this.code = code;
        this.info = info;
        this.address = address;
    }

    public static void throwNew(int code, long info, long address) {
        throw new WindowsError(code, info, address);
    }

    static String subMessage(long info, long address) {
        switch ((int)info) {
            case 0: {
                return "Attempted to read from inaccessible address " + WindowsError.toHex(address);
            }
            case 1: {
                return "Attempted to write to inaccessible address " + WindowsError.toHex(address);
            }
            case 8: {
                return "Attempted to execute memory " + WindowsError.toHex(address) + " that's not executable  (DEP violation)";
            }
        }
        return "?";
    }

    public static String computeMessage(int code, long info, long address) {
        switch (code) {
            case -1073741819: {
                return "EXCEPTION_ACCESS_VIOLATION : " + WindowsError.subMessage(info, address);
            }
            case -1073741818: {
                return "EXCEPTION_IN_PAGE_ERROR : " + WindowsError.subMessage(info, address);
            }
        }
        try {
            for (Field field : WinExceptionsConstants.class.getFields()) {
                int value;
                if (!field.getName().startsWith("EXCEPTION_") || field.getType() != Integer.TYPE || (value = ((Integer)field.get(null)).intValue()) != code) continue;
                return field.getName();
            }
        }
        catch (Throwable th2) {
            // empty catch block
        }
        return "Windows native error (code = " + code + ", info = " + info + ", address = " + address + ") !";
    }
}

