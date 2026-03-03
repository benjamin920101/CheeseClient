/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com;

import org.bridj.CRuntime;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Runtime;

@Runtime(value=CRuntime.class)
public class SAFEARRAYBOUND
extends StructObject {
    @Field(value=0)
    public int cElements() {
        return this.io.getIntField(this, 0);
    }

    @Field(value=0)
    public SAFEARRAYBOUND cElements(int cElements) {
        this.io.setIntField(this, 0, cElements);
        return this;
    }

    public final int cElements_$eq(int cElements) {
        this.cElements(cElements);
        return cElements;
    }

    @CLong
    @Field(value=1)
    public long lLbound() {
        return this.io.getCLongField(this, 1);
    }

    @CLong
    @Field(value=1)
    public SAFEARRAYBOUND lLbound(long lLbound) {
        this.io.setCLongField(this, 1, lLbound);
        return this;
    }

    public final long lLbound_$eq(long lLbound) {
        this.lLbound(lLbound);
        return lLbound;
    }
}

