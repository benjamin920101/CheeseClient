/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import org.bridj.BridJ;
import org.bridj.NativeError;

public class LastError
extends NativeError {
    final int code;
    final String description;

    LastError(int code, String description) {
        super((description == null ? "?" : description.trim()) + " (error code = " + code + ")");
        this.code = code;
        this.description = description;
        if (BridJ.verbose) {
            BridJ.info("Last error detected : " + this.getMessage());
        }
    }

    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    static void throwNewInstance(int code, String description) {
        if (code == 0) {
            return;
        }
        throw new LastError(code, description);
    }
}

