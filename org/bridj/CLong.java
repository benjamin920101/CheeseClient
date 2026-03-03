/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import org.bridj.AbstractIntegral;
import org.bridj.Platform;

public final class CLong
extends AbstractIntegral {
    public static final int SIZE = Platform.CLONG_SIZE;
    private static final long serialVersionUID = 1542942327767932396L;

    public CLong(long value) {
        super(value);
    }

    public static CLong valueOf(long value) {
        return new CLong(value);
    }
}

