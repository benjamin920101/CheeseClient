/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import org.bridj.NativeError;

class SignalError
extends NativeError {
    final int signal;
    final int code;
    final long address;

    SignalError(int signal, int code, long address) {
        super(SignalError.getFullSignalMessage(signal, code, address));
        this.signal = signal;
        this.code = code;
        this.address = address;
    }

    public int getSignal() {
        return this.signal;
    }

    public int getCode() {
        return this.code;
    }

    public long getAddress() {
        return this.address;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SignalError)) {
            return false;
        }
        SignalError e2 = (SignalError)obj;
        return this.signal == e2.signal && this.code == e2.code;
    }

    public int hashCode() {
        return Integer.valueOf(this.signal).hashCode() ^ Integer.valueOf(this.code).hashCode() ^ Long.valueOf(this.address).hashCode();
    }

    public static String getFullSignalMessage(int signal, int code, long address) {
        String simple = SignalError.getSignalMessage(signal, 0, address);
        if (code == 0) {
            return simple;
        }
        String sub = SignalError.getSignalMessage(signal, code, address);
        if (sub.equals(simple)) {
            return simple;
        }
        return simple + " (" + sub + ")";
    }

    public static void throwNew(int signal, int code, long address) {
        throw new SignalError(signal, code, address);
    }

    public static String getSignalMessage(int signal, int code, long address) {
        switch (signal) {
            case 11: {
                switch (code) {
                    case 1: {
                        return "Address not mapped to object";
                    }
                    case 2: {
                        return "Invalid permission for mapped object";
                    }
                }
                return "Segmentation fault : " + SignalError.toHex(address);
            }
            case 10: {
                switch (code) {
                    case 1: {
                        return "Invalid address alignment";
                    }
                    case 2: {
                        return "Nonexistent physical address";
                    }
                    case 3: {
                        return "Object-specific HW error";
                    }
                }
                return "Bus error : " + SignalError.toHex(address);
            }
            case 6: {
                return "Native exception (call to abort())";
            }
            case 8: {
                switch (code) {
                    case 7: {
                        return "Integer divide by zero";
                    }
                    case 8: {
                        return "Integer overflow";
                    }
                    case 1: {
                        return "Floating point divide by zero";
                    }
                    case 2: {
                        return "Floating point overflow";
                    }
                    case 3: {
                        return "Floating point underflow";
                    }
                    case 4: {
                        return "Floating point inexact result";
                    }
                    case 5: {
                        return "Invalid floating point operation";
                    }
                    case 6: {
                        return "Subscript out of range";
                    }
                }
                return "Floating point error";
            }
            case 12: {
                return "Bad argument to system call";
            }
            case 5: {
                switch (code) {
                    case 1: {
                        return "Process breakpoint";
                    }
                    case 2: {
                        return "Process trace trap";
                    }
                }
                return "Trace trap";
            }
            case 4: {
                switch (code) {
                    case 1: {
                        return "Illegal opcode";
                    }
                    case 2: {
                        return "Illegal trap";
                    }
                    case 3: {
                        return "Privileged opcode";
                    }
                    case 4: {
                        return "Illegal operand";
                    }
                    case 5: {
                        return "Illegal addressing mode";
                    }
                    case 6: {
                        return "Privileged register";
                    }
                    case 7: {
                        return "Coprocessor error";
                    }
                    case 8: {
                        return "Internal stack error";
                    }
                }
                return "Illegal instruction";
            }
        }
        return "Native error";
    }
}

