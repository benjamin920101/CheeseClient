/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com;

import org.bridj.CRuntime;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Runtime;
import org.bridj.ann.Union;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.CY;
import org.bridj.cpp.com.DECIMAL;
import org.bridj.cpp.com.IDispatch;
import org.bridj.cpp.com.IRecordInfo;
import org.bridj.cpp.com.IUnknown;
import org.bridj.cpp.com.SAFEARRAY;

@Runtime(value=CRuntime.class)
public class VARIANT
extends StructObject {
    public VARIANT(Object value) {
        COMRuntime.setValue(this, value);
    }

    public VARIANT() {
    }

    public VARIANT clone() {
        return COMRuntime.clone(this);
    }

    @Field(value=0)
    public __VARIANT_NAME_1_union __VARIANT_NAME_1() {
        return (__VARIANT_NAME_1_union)this.io.getNativeObjectField(this, 0);
    }

    public Object getValue() {
        return COMRuntime.getValue(this);
    }

    public VARIANT setValue(Object value) {
        return COMRuntime.setValue(this, value);
    }

    public String toString() {
        return COMRuntime.toString(this);
    }

    @Union
    public static class __VARIANT_NAME_1_union
    extends StructObject {
        @Field(value=0)
        public __tagVARIANT __VARIANT_NAME_2() {
            return (__tagVARIANT)this.io.getNativeObjectField(this, 0);
        }

        @Field(value=1)
        public DECIMAL decVal() {
            return (DECIMAL)this.io.getNativeObjectField(this, 1);
        }

        public static class __tagVARIANT
        extends StructObject {
            @Field(value=0)
            public short vt() {
                return this.io.getShortField(this, 0);
            }

            @Field(value=0)
            public __tagVARIANT vt(short vt2) {
                this.io.setShortField(this, 0, vt2);
                return this;
            }

            public final short vt_$eq(short vt2) {
                this.vt(vt2);
                return vt2;
            }

            @Field(value=1)
            public short wReserved1() {
                return this.io.getShortField(this, 1);
            }

            @Field(value=1)
            public __tagVARIANT wReserved1(short wReserved1) {
                this.io.setShortField(this, 1, wReserved1);
                return this;
            }

            public final short wReserved1_$eq(short wReserved1) {
                this.wReserved1(wReserved1);
                return wReserved1;
            }

            @Field(value=2)
            public short wReserved2() {
                return this.io.getShortField(this, 2);
            }

            @Field(value=2)
            public __tagVARIANT wReserved2(short wReserved2) {
                this.io.setShortField(this, 2, wReserved2);
                return this;
            }

            public final short wReserved2_$eq(short wReserved2) {
                this.wReserved2(wReserved2);
                return wReserved2;
            }

            @Field(value=3)
            public short wReserved3() {
                return this.io.getShortField(this, 3);
            }

            @Field(value=3)
            public __tagVARIANT wReserved3(short wReserved3) {
                this.io.setShortField(this, 3, wReserved3);
                return this;
            }

            public final short wReserved3_$eq(short wReserved3) {
                this.wReserved3(wReserved3);
                return wReserved3;
            }

            @Field(value=4)
            public __VARIANT_NAME_3_union __VARIANT_NAME_3() {
                return (__VARIANT_NAME_3_union)this.io.getNativeObjectField(this, 4);
            }

            /*
             * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
             */
            @Union
            public static class __VARIANT_NAME_3_union
            extends StructObject {
                @Field(value=0)
                public long llval() {
                    return this.io.getLongField(this, 0);
                }

                @Field(value=0)
                public __VARIANT_NAME_3_union llval(long llval) {
                    this.io.setLongField(this, 0, llval);
                    return this;
                }

                public final long llval_$eq(long llval) {
                    this.llval(llval);
                    return llval;
                }

                @CLong
                @Field(value=1)
                public long lVal() {
                    return this.io.getCLongField(this, 1);
                }

                @CLong
                @Field(value=1)
                public __VARIANT_NAME_3_union lVal(long lVal) {
                    this.io.setCLongField(this, 1, lVal);
                    return this;
                }

                public final long lVal_$eq(long lVal) {
                    this.lVal(lVal);
                    return lVal;
                }

                @Field(value=2)
                public byte bVal() {
                    return this.io.getByteField(this, 2);
                }

                @Field(value=2)
                public __VARIANT_NAME_3_union bVal(byte bVal) {
                    this.io.setByteField(this, 2, bVal);
                    return this;
                }

                public final byte bVal_$eq(byte bVal) {
                    this.bVal(bVal);
                    return bVal;
                }

                @Field(value=3)
                public short iVal() {
                    return this.io.getShortField(this, 3);
                }

                @Field(value=3)
                public __VARIANT_NAME_3_union iVal(short iVal) {
                    this.io.setShortField(this, 3, iVal);
                    return this;
                }

                public final short iVal_$eq(short iVal) {
                    this.iVal(iVal);
                    return iVal;
                }

                @Field(value=4)
                public float fltVal() {
                    return this.io.getFloatField(this, 4);
                }

                @Field(value=4)
                public __VARIANT_NAME_3_union fltVal(float fltVal) {
                    this.io.setFloatField(this, 4, fltVal);
                    return this;
                }

                public final float fltVal_$eq(float fltVal) {
                    this.fltVal(fltVal);
                    return fltVal;
                }

                @Field(value=5)
                public double dblVal() {
                    return this.io.getDoubleField(this, 5);
                }

                @Field(value=5)
                public __VARIANT_NAME_3_union dblVal(double dblVal) {
                    this.io.setDoubleField(this, 5, dblVal);
                    return this;
                }

                public final double dblVal_$eq(double dblVal) {
                    this.dblVal(dblVal);
                    return dblVal;
                }

                @Field(value=6)
                public int boolVal() {
                    return this.io.getIntField(this, 6);
                }

                @Field(value=6)
                public __VARIANT_NAME_3_union boolVal(int boolVal) {
                    this.io.setIntField(this, 6, boolVal);
                    return this;
                }

                public final int boolVal_$eq(int boolVal) {
                    this.boolVal(boolVal);
                    return boolVal;
                }

                @Field(value=7)
                public int bool() {
                    return this.io.getIntField(this, 7);
                }

                @Field(value=7)
                public __VARIANT_NAME_3_union bool(int bool) {
                    this.io.setIntField(this, 7, bool);
                    return this;
                }

                public final int bool_$eq(int bool) {
                    this.bool(bool);
                    return bool;
                }

                @Field(value=8)
                public int scode() {
                    return this.io.getIntField(this, 8);
                }

                @Field(value=8)
                public __VARIANT_NAME_3_union scode(int scode) {
                    this.io.setIntField(this, 8, scode);
                    return this;
                }

                public final int scode_$eq(int scode) {
                    this.scode(scode);
                    return scode;
                }

                @Field(value=9)
                public CY cyVal() {
                    return (CY)this.io.getNativeObjectField(this, 9);
                }

                @Field(value=10)
                public double date() {
                    return this.io.getDoubleField(this, 10);
                }

                @Field(value=10)
                public __VARIANT_NAME_3_union date(double date) {
                    this.io.setDoubleField(this, 10, date);
                    return this;
                }

                public final double date_$eq(double date) {
                    this.date(date);
                    return date;
                }

                @Field(value=11)
                public Pointer<Byte> bstrVal() {
                    return this.io.getPointerField(this, 11);
                }

                @Field(value=11)
                public __VARIANT_NAME_3_union bstrVal(Pointer<Byte> bstrVal) {
                    this.io.setPointerField(this, 11, bstrVal);
                    return this;
                }

                public final Pointer<Byte> bstrVal_$eq(Pointer<Byte> bstrVal) {
                    this.bstrVal(bstrVal);
                    return bstrVal;
                }

                @Field(value=12)
                public Pointer<IUnknown> punkVal() {
                    return this.io.getPointerField(this, 12);
                }

                @Field(value=12)
                public __VARIANT_NAME_3_union punkVal(Pointer<IUnknown> punkVal) {
                    this.io.setPointerField(this, 12, punkVal);
                    return this;
                }

                public final Pointer<IUnknown> punkVal_$eq(Pointer<IUnknown> punkVal) {
                    this.punkVal(punkVal);
                    return punkVal;
                }

                @Field(value=13)
                public Pointer<IDispatch> pdispVal() {
                    return this.io.getPointerField(this, 13);
                }

                @Field(value=13)
                public __VARIANT_NAME_3_union pdispVal(Pointer<IDispatch> pdispVal) {
                    this.io.setPointerField(this, 13, pdispVal);
                    return this;
                }

                public final Pointer<IDispatch> pdispVal_$eq(Pointer<IDispatch> pdispVal) {
                    this.pdispVal(pdispVal);
                    return pdispVal;
                }

                @Field(value=14)
                public Pointer<SAFEARRAY> parray() {
                    return this.io.getPointerField(this, 14);
                }

                @Field(value=14)
                public __VARIANT_NAME_3_union parray(Pointer<SAFEARRAY> parray) {
                    this.io.setPointerField(this, 14, parray);
                    return this;
                }

                public final Pointer<SAFEARRAY> parray_$eq(Pointer<SAFEARRAY> parray) {
                    this.parray(parray);
                    return parray;
                }

                @Field(value=15)
                public Pointer<Byte> pbVal() {
                    return this.io.getPointerField(this, 15);
                }

                @Field(value=15)
                public __VARIANT_NAME_3_union pbVal(Pointer<Byte> pbVal) {
                    this.io.setPointerField(this, 15, pbVal);
                    return this;
                }

                public final Pointer<Byte> pbVal_$eq(Pointer<Byte> pbVal) {
                    this.pbVal(pbVal);
                    return pbVal;
                }

                @Field(value=16)
                public Pointer<Short> piVal() {
                    return this.io.getPointerField(this, 16);
                }

                @Field(value=16)
                public __VARIANT_NAME_3_union piVal(Pointer<Short> piVal) {
                    this.io.setPointerField(this, 16, piVal);
                    return this;
                }

                public final Pointer<Short> piVal_$eq(Pointer<Short> piVal) {
                    this.piVal(piVal);
                    return piVal;
                }

                @Field(value=17)
                public Pointer<CLong> plVal() {
                    return this.io.getPointerField(this, 17);
                }

                @Field(value=17)
                public __VARIANT_NAME_3_union plVal(Pointer<CLong> plVal) {
                    this.io.setPointerField(this, 17, plVal);
                    return this;
                }

                public final Pointer<CLong> plVal_$eq(Pointer<CLong> plVal) {
                    this.plVal(plVal);
                    return plVal;
                }

                @Field(value=18)
                public Pointer<Long> pllVal() {
                    return this.io.getPointerField(this, 18);
                }

                @Field(value=18)
                public __VARIANT_NAME_3_union pllVal(Pointer<Long> pllVal) {
                    this.io.setPointerField(this, 18, pllVal);
                    return this;
                }

                public final Pointer<Long> pllVal_$eq(Pointer<Long> pllVal) {
                    this.pllVal(pllVal);
                    return pllVal;
                }

                @Field(value=19)
                public Pointer<Float> pfltVal() {
                    return this.io.getPointerField(this, 19);
                }

                @Field(value=19)
                public __VARIANT_NAME_3_union pfltVal(Pointer<Float> pfltVal) {
                    this.io.setPointerField(this, 19, pfltVal);
                    return this;
                }

                public final Pointer<Float> pfltVal_$eq(Pointer<Float> pfltVal) {
                    this.pfltVal(pfltVal);
                    return pfltVal;
                }

                @Field(value=20)
                public Pointer<Double> pdblVal() {
                    return this.io.getPointerField(this, 20);
                }

                @Field(value=20)
                public __VARIANT_NAME_3_union pdblVal(Pointer<Double> pdblVal) {
                    this.io.setPointerField(this, 20, pdblVal);
                    return this;
                }

                public final Pointer<Double> pdblVal_$eq(Pointer<Double> pdblVal) {
                    this.pdblVal(pdblVal);
                    return pdblVal;
                }

                @Field(value=21)
                public Pointer<Integer> pboolVal() {
                    return this.io.getPointerField(this, 21);
                }

                @Field(value=21)
                public __VARIANT_NAME_3_union pboolVal(Pointer<Integer> pboolVal) {
                    this.io.setPointerField(this, 21, pboolVal);
                    return this;
                }

                public final Pointer<Integer> pboolVal_$eq(Pointer<Integer> pboolVal) {
                    this.pboolVal(pboolVal);
                    return pboolVal;
                }

                @Field(value=22)
                public Pointer<Integer> pbool() {
                    return this.io.getPointerField(this, 22);
                }

                @Field(value=22)
                public __VARIANT_NAME_3_union pbool(Pointer<Integer> pbool) {
                    this.io.setPointerField(this, 22, pbool);
                    return this;
                }

                public final Pointer<Integer> pbool_$eq(Pointer<Integer> pbool) {
                    this.pbool(pbool);
                    return pbool;
                }

                @Field(value=23)
                public Pointer<Integer> pscode() {
                    return this.io.getPointerField(this, 23);
                }

                @Field(value=23)
                public __VARIANT_NAME_3_union pscode(Pointer<Integer> pscode) {
                    this.io.setPointerField(this, 23, pscode);
                    return this;
                }

                public final Pointer<Integer> pscode_$eq(Pointer<Integer> pscode) {
                    this.pscode(pscode);
                    return pscode;
                }

                @Field(value=24)
                public Pointer<CY> pcyVal() {
                    return this.io.getPointerField(this, 24);
                }

                @Field(value=24)
                public __VARIANT_NAME_3_union pcyVal(Pointer<CY> pcyVal) {
                    this.io.setPointerField(this, 24, pcyVal);
                    return this;
                }

                public final Pointer<CY> pcyVal_$eq(Pointer<CY> pcyVal) {
                    this.pcyVal(pcyVal);
                    return pcyVal;
                }

                @Field(value=25)
                public Pointer<Double> pdate() {
                    return this.io.getPointerField(this, 25);
                }

                @Field(value=25)
                public __VARIANT_NAME_3_union pdate(Pointer<Double> pdate) {
                    this.io.setPointerField(this, 25, pdate);
                    return this;
                }

                public final Pointer<Double> pdate_$eq(Pointer<Double> pdate) {
                    this.pdate(pdate);
                    return pdate;
                }

                @Field(value=26)
                public Pointer<Pointer<Byte>> pbstrVal() {
                    return this.io.getPointerField(this, 26);
                }

                @Field(value=26)
                public __VARIANT_NAME_3_union pbstrVal(Pointer<Pointer<Byte>> pbstrVal) {
                    this.io.setPointerField(this, 26, pbstrVal);
                    return this;
                }

                public final Pointer<Pointer<Byte>> pbstrVal_$eq(Pointer<Pointer<Byte>> pbstrVal) {
                    this.pbstrVal(pbstrVal);
                    return pbstrVal;
                }

                @Field(value=27)
                public Pointer<Pointer<IUnknown>> ppunkVal() {
                    return this.io.getPointerField(this, 27);
                }

                @Field(value=27)
                public __VARIANT_NAME_3_union ppunkVal(Pointer<Pointer<IUnknown>> ppunkVal) {
                    this.io.setPointerField(this, 27, ppunkVal);
                    return this;
                }

                public final Pointer<Pointer<IUnknown>> ppunkVal_$eq(Pointer<Pointer<IUnknown>> ppunkVal) {
                    this.ppunkVal(ppunkVal);
                    return ppunkVal;
                }

                @Field(value=28)
                public Pointer<Pointer<IDispatch>> ppdispVal() {
                    return this.io.getPointerField(this, 28);
                }

                @Field(value=28)
                public __VARIANT_NAME_3_union ppdispVal(Pointer<Pointer<IDispatch>> ppdispVal) {
                    this.io.setPointerField(this, 28, ppdispVal);
                    return this;
                }

                public final Pointer<Pointer<IDispatch>> ppdispVal_$eq(Pointer<Pointer<IDispatch>> ppdispVal) {
                    this.ppdispVal(ppdispVal);
                    return ppdispVal;
                }

                @Field(value=29)
                public Pointer<Pointer<SAFEARRAY>> pparray() {
                    return this.io.getPointerField(this, 29);
                }

                @Field(value=29)
                public __VARIANT_NAME_3_union pparray(Pointer<Pointer<SAFEARRAY>> pparray) {
                    this.io.setPointerField(this, 29, pparray);
                    return this;
                }

                public final Pointer<Pointer<SAFEARRAY>> pparray_$eq(Pointer<Pointer<SAFEARRAY>> pparray) {
                    this.pparray(pparray);
                    return pparray;
                }

                @Field(value=30)
                public Pointer<VARIANT> pvarVal() {
                    return this.io.getPointerField(this, 30);
                }

                @Field(value=30)
                public __VARIANT_NAME_3_union pvarVal(Pointer<VARIANT> pvarVal) {
                    this.io.setPointerField(this, 30, pvarVal);
                    return this;
                }

                public final Pointer<VARIANT> pvarVal_$eq(Pointer<VARIANT> pvarVal) {
                    this.pvarVal(pvarVal);
                    return pvarVal;
                }

                @Field(value=31)
                public Pointer<Pointer<?>> byref() {
                    return this.io.getPointerField(this, 31);
                }

                @Field(value=31)
                public __VARIANT_NAME_3_union byref(Pointer<Pointer<?>> byref) {
                    this.io.setPointerField(this, 31, byref);
                    return this;
                }

                public final Pointer<Pointer<?>> byref_$eq(Pointer<Pointer<?>> byref) {
                    this.byref(byref);
                    return byref;
                }

                @Field(value=32)
                public byte cVal() {
                    return this.io.getByteField(this, 32);
                }

                @Field(value=32)
                public __VARIANT_NAME_3_union cVal(byte cVal) {
                    this.io.setByteField(this, 32, cVal);
                    return this;
                }

                public final byte cVal_$eq(byte cVal) {
                    this.cVal(cVal);
                    return cVal;
                }

                @Field(value=33)
                public short uiVal() {
                    return this.io.getShortField(this, 33);
                }

                @Field(value=33)
                public __VARIANT_NAME_3_union uiVal(short uiVal) {
                    this.io.setShortField(this, 33, uiVal);
                    return this;
                }

                public final short uiVal_$eq(short uiVal) {
                    this.uiVal(uiVal);
                    return uiVal;
                }

                @Field(value=34)
                public int ulVal() {
                    return this.io.getIntField(this, 34);
                }

                @Field(value=34)
                public __VARIANT_NAME_3_union ulVal(int ulVal) {
                    this.io.setIntField(this, 34, ulVal);
                    return this;
                }

                public final int ulVal_$eq(int ulVal) {
                    this.ulVal(ulVal);
                    return ulVal;
                }

                @Field(value=35)
                public long ullVal() {
                    return this.io.getLongField(this, 35);
                }

                @Field(value=35)
                public __VARIANT_NAME_3_union ullVal(long ullVal) {
                    this.io.setLongField(this, 35, ullVal);
                    return this;
                }

                public final long ullVal_$eq(long ullVal) {
                    this.ullVal(ullVal);
                    return ullVal;
                }

                @Field(value=36)
                public int intVal() {
                    return this.io.getIntField(this, 36);
                }

                @Field(value=36)
                public __VARIANT_NAME_3_union intVal(int intVal) {
                    this.io.setIntField(this, 36, intVal);
                    return this;
                }

                public final int intVal_$eq(int intVal) {
                    this.intVal(intVal);
                    return intVal;
                }

                @Field(value=37)
                public int uintVal() {
                    return this.io.getIntField(this, 37);
                }

                @Field(value=37)
                public __VARIANT_NAME_3_union uintVal(int uintVal) {
                    this.io.setIntField(this, 37, uintVal);
                    return this;
                }

                public final int uintVal_$eq(int uintVal) {
                    this.uintVal(uintVal);
                    return uintVal;
                }

                @Field(value=38)
                public Pointer<DECIMAL> pdecVal() {
                    return this.io.getPointerField(this, 38);
                }

                @Field(value=38)
                public __VARIANT_NAME_3_union pdecVal(Pointer<DECIMAL> pdecVal) {
                    this.io.setPointerField(this, 38, pdecVal);
                    return this;
                }

                public final Pointer<DECIMAL> pdecVal_$eq(Pointer<DECIMAL> pdecVal) {
                    this.pdecVal(pdecVal);
                    return pdecVal;
                }

                @Field(value=39)
                public Pointer<Byte> pcVal() {
                    return this.io.getPointerField(this, 39);
                }

                @Field(value=39)
                public __VARIANT_NAME_3_union pcVal(Pointer<Byte> pcVal) {
                    this.io.setPointerField(this, 39, pcVal);
                    return this;
                }

                public final Pointer<Byte> pcVal_$eq(Pointer<Byte> pcVal) {
                    this.pcVal(pcVal);
                    return pcVal;
                }

                @Field(value=40)
                public Pointer<Short> puiVal() {
                    return this.io.getPointerField(this, 40);
                }

                @Field(value=40)
                public __VARIANT_NAME_3_union puiVal(Pointer<Short> puiVal) {
                    this.io.setPointerField(this, 40, puiVal);
                    return this;
                }

                public final Pointer<Short> puiVal_$eq(Pointer<Short> puiVal) {
                    this.puiVal(puiVal);
                    return puiVal;
                }

                @Field(value=41)
                public Pointer<Integer> pulVal() {
                    return this.io.getPointerField(this, 41);
                }

                @Field(value=41)
                public __VARIANT_NAME_3_union pulVal(Pointer<Integer> pulVal) {
                    this.io.setPointerField(this, 41, pulVal);
                    return this;
                }

                public final Pointer<Integer> pulVal_$eq(Pointer<Integer> pulVal) {
                    this.pulVal(pulVal);
                    return pulVal;
                }

                @Field(value=42)
                public Pointer<Long> pullVal() {
                    return this.io.getPointerField(this, 42);
                }

                @Field(value=42)
                public __VARIANT_NAME_3_union pullVal(Pointer<Long> pullVal) {
                    this.io.setPointerField(this, 42, pullVal);
                    return this;
                }

                public final Pointer<Long> pullVal_$eq(Pointer<Long> pullVal) {
                    this.pullVal(pullVal);
                    return pullVal;
                }

                @Field(value=43)
                public Pointer<Integer> pintVal() {
                    return this.io.getPointerField(this, 43);
                }

                @Field(value=43)
                public __VARIANT_NAME_3_union pintVal(Pointer<Integer> pintVal) {
                    this.io.setPointerField(this, 43, pintVal);
                    return this;
                }

                public final Pointer<Integer> pintVal_$eq(Pointer<Integer> pintVal) {
                    this.pintVal(pintVal);
                    return pintVal;
                }

                @Field(value=44)
                public Pointer<Integer> puintVal() {
                    return this.io.getPointerField(this, 44);
                }

                @Field(value=44)
                public __VARIANT_NAME_3_union puintVal(Pointer<Integer> puintVal) {
                    this.io.setPointerField(this, 44, puintVal);
                    return this;
                }

                public final Pointer<Integer> puintVal_$eq(Pointer<Integer> puintVal) {
                    this.puintVal(puintVal);
                    return puintVal;
                }

                @Field(value=45)
                public __tagBRECORD __VARIANT_NAME_4() {
                    return (__tagBRECORD)this.io.getNativeObjectField(this, 45);
                }

                /*
                 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
                 */
                public static class __tagBRECORD
                extends StructObject {
                    @Field(value=0)
                    public Pointer<?> pvRecord() {
                        return this.io.getPointerField(this, 0);
                    }

                    @Field(value=0)
                    public __tagBRECORD pvRecord(Pointer<?> pvRecord) {
                        this.io.setPointerField(this, 0, pvRecord);
                        return this;
                    }

                    public final Pointer<?> pvRecord_$eq(Pointer<?> pvRecord) {
                        this.pvRecord(pvRecord);
                        return pvRecord;
                    }

                    @Field(value=1)
                    public Pointer<IRecordInfo> pRecInfo() {
                        return this.io.getPointerField(this, 1);
                    }

                    @Field(value=1)
                    public __tagBRECORD pRecInfo(Pointer<IRecordInfo> pRecInfo) {
                        this.io.setPointerField(this, 1, pRecInfo);
                        return this;
                    }

                    public final Pointer<IRecordInfo> pRecInfo_$eq(Pointer<IRecordInfo> pRecInfo) {
                        this.pRecInfo(pRecInfo);
                        return pRecInfo;
                    }
                }
            }
        }
    }
}

