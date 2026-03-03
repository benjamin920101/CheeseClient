/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com;

import org.bridj.CRuntime;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
import org.bridj.ann.Runtime;
import org.bridj.cpp.com.SAFEARRAYBOUND;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Runtime(value=CRuntime.class)
public class SAFEARRAY
extends StructObject {
    @Field(value=0)
    public short cDims() {
        return this.io.getShortField(this, 0);
    }

    @Field(value=0)
    public SAFEARRAY cDims(short cDims) {
        this.io.setShortField(this, 0, cDims);
        return this;
    }

    public final short cDims_$eq(short cDims) {
        this.cDims(cDims);
        return cDims;
    }

    @Field(value=1)
    public short fFeatures() {
        return this.io.getShortField(this, 1);
    }

    @Field(value=1)
    public SAFEARRAY fFeatures(short fFeatures) {
        this.io.setShortField(this, 1, fFeatures);
        return this;
    }

    public final short fFeatures_$eq(short fFeatures) {
        this.fFeatures(fFeatures);
        return fFeatures;
    }

    @Field(value=2)
    public int cbElements() {
        return this.io.getIntField(this, 2);
    }

    @Field(value=2)
    public SAFEARRAY cbElements(int cbElements) {
        this.io.setIntField(this, 2, cbElements);
        return this;
    }

    public final int cbElements_$eq(int cbElements) {
        this.cbElements(cbElements);
        return cbElements;
    }

    @Field(value=3)
    public int cLocks() {
        return this.io.getIntField(this, 3);
    }

    @Field(value=3)
    public SAFEARRAY cLocks(int cLocks) {
        this.io.setIntField(this, 3, cLocks);
        return this;
    }

    public final int cLocks_$eq(int cLocks) {
        this.cLocks(cLocks);
        return cLocks;
    }

    @Field(value=4)
    public Pointer<?> pvData() {
        return this.io.getPointerField(this, 4);
    }

    @Field(value=4)
    public SAFEARRAY pvData(Pointer<?> pvData) {
        this.io.setPointerField(this, 4, pvData);
        return this;
    }

    public final Pointer<?> pvData_$eq(Pointer<?> pvData) {
        this.pvData(pvData);
        return pvData;
    }

    @Array(value={1L})
    @Field(value=5)
    public Pointer<SAFEARRAYBOUND> rgsabound() {
        return this.io.getPointerField(this, 5);
    }
}

