/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import org.bridj.AbstractIntegral;
import org.bridj.Platform;

public final class SizeT
extends AbstractIntegral {
    public static final int SIZE = Platform.SIZE_T_SIZE;
    public static final SizeT ZERO = new SizeT(0L);
    public static final SizeT ONE = new SizeT(1L);
    private static final long serialVersionUID = 1547942367767922396L;

    public SizeT(long value) {
        super(value);
    }

    public static SizeT valueOf(long value) {
        if (value == 0L) {
            return ZERO;
        }
        if (value == 1L) {
            return ONE;
        }
        return new SizeT(value);
    }
}

