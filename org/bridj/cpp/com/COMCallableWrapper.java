/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com;

import org.bridj.Pointer;
import org.bridj.cpp.com.IDispatch;
import org.bridj.cpp.com.ITypeInfo;
import org.bridj.cpp.com.VARIANT;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class COMCallableWrapper
extends IDispatch {
    Object instance;

    public COMCallableWrapper(Object instance) {
        this.instance = instance;
    }

    @Override
    public int GetIDsOfNames(Pointer riid, Pointer<Pointer<Character>> rgszNames, int cNames, int lcid, Pointer<Integer> rgDispId) {
        return -2147467263;
    }

    @Override
    public int Invoke(int dispIdMember, Pointer<Byte> riid, int lcid, short wFlags, Pointer<IDispatch.DISPPARAMS> pDispParams, Pointer<VARIANT> pVarResult, Pointer<IDispatch.EXCEPINFO> pExcepInfo, Pointer<Integer> puArgErr) {
        return -2147467263;
    }

    @Override
    public int GetTypeInfo(int iTInfo, int lcid, Pointer<Pointer<ITypeInfo>> ppTInfo) {
        return -2147467263;
    }

    @Override
    public int GetTypeInfoCount(Pointer<Integer> pctinfo) {
        return -2147467263;
    }
}

