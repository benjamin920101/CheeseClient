/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com;

import org.bridj.CRuntime;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Runtime;

@Runtime(value=CRuntime.class)
public class CY
extends StructObject {
    @Field(value=0)
    public long int64() {
        return this.io.getLongField(this, 0);
    }

    @Field(value=0)
    public CY int64(long int64) {
        this.io.setLongField(this, 0, int64);
        return this;
    }

    public final long int64_$eq(long int64) {
        this.int64(int64);
        return int64;
    }
}

