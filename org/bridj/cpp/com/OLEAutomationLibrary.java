/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com;

import java.util.Collections;
import java.util.Iterator;
import org.bridj.BridJ;
import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ValuedEnum;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.CPPObject;
import org.bridj.cpp.CPPRuntime;
import org.bridj.cpp.com.CY;
import org.bridj.cpp.com.DECIMAL;
import org.bridj.cpp.com.GUID;
import org.bridj.cpp.com.IDispatch;
import org.bridj.cpp.com.IRecordInfo;
import org.bridj.cpp.com.ITypeInfo;
import org.bridj.cpp.com.IUnknown;
import org.bridj.cpp.com.SAFEARRAY;
import org.bridj.cpp.com.SAFEARRAYBOUND;
import org.bridj.cpp.com.VARIANT;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Library(value="oleaut32")
@Runtime(value=CPPRuntime.class)
public class OLEAutomationLibrary {
    public static final int NUMPRS_TRAILING_WHITE = 2;
    public static final int VAR_TIMEVALUEONLY = 1;
    public static final int VARIANT_USE_NLS = 128;
    public static final int VTDATEGRE_MIN = -657434;
    public static final int ACTIVEOBJECT_WEAK = 1;
    public static final int NUMPRS_EXPONENT = 2048;
    public static final int NUMPRS_USE_ALL = 4096;
    public static final int NUMPRS_THOUSANDS = 512;
    public static final int STDOLE_MINORVERNUM = 0;
    public static final int VARIANT_LOCALBOOL = 16;
    public static final int VARIANT_NOVALUEPROP = 1;
    public static final int NUMPRS_PARENS = 128;
    public static final int VARCMP_GT = 2;
    public static final int VARCMP_LT = 0;
    public static final int NUMPRS_STD = 8191;
    public static final int VAR_CALENDAR_GREGORIAN = 256;
    public static final int NUMPRS_LEADING_PLUS = 4;
    public static final int LOAD_TLB_AS_64BIT = 64;
    public static final int LOCALE_USE_NLS = 0x10000000;
    public static final int VTDATEGRE_MAX = 2958465;
    public static final int NUMPRS_DECIMAL = 256;
    public static final int STDOLE_MAJORVERNUM = 1;
    public static final int NUMPRS_INEXACT = 131072;
    public static final int VARIANT_CALENDAR_THAI = 32;
    public static final int VARCMP_EQ = 1;
    public static final int ACTIVEOBJECT_STRONG = 0;
    public static final int NUMPRS_TRAILING_PLUS = 8;
    public static final int STDOLE2_MINORVERNUM = 0;
    public static final int VARIANT_NOUSEROVERRIDE = 4;
    public static final int NUMPRS_CURRENCY = 1024;
    public static final int VAR_CALENDAR_THAI = 128;
    public static final int STDOLE2_LCID = 0;
    public static final int VAR_FOURDIGITYEARS = 64;
    public static final int DISPATCH_PROPERTYPUT = 4;
    public static final int VARIANT_CALENDAR_GREGORIAN = 64;
    public static final int NUMPRS_HEX_OCT = 64;
    public static final int NUMPRS_LEADING_WHITE = 1;
    public static final int DISPATCH_PROPERTYPUTREF = 8;
    public static final int ID_DEFAULTINST = -2;
    public static final int VAR_LOCALBOOL = 16;
    public static final int STDOLE_LCID = 0;
    public static final int NUMPRS_TRAILING_MINUS = 32;
    public static final int NUMPRS_LEADING_MINUS = 16;
    public static final int VARIANT_ALPHABOOL = 2;
    public static final int VAR_VALIDDATE = 4;
    public static final int VARIANT_CALENDAR_HIJRI = 8;
    public static final int VAR_DATEVALUEONLY = 2;
    public static final int STDOLE2_MAJORVERNUM = 2;
    public static final int LOAD_TLB_AS_32BIT = 32;
    public static final int NUMPRS_NEG = 65536;
    public static final int VAR_CALENDAR_HIJRI = 8;
    public static final int DISPATCH_METHOD = 1;
    public static final int VARCMP_NULL = 3;
    public static final int DISPATCH_PROPERTYGET = 2;
    public static final int VAR_FORMAT_NOSUBSTITUTE = 32;
    public static final int MASK_TO_RESET_TLB_BITS = -97;

    public static native int OaBuildVersion();

    public static native Pointer<Byte> SysAllocString(Pointer<Character> var0);

    public static native Pointer<Byte> SysAllocStringByteLen(int var0);

    public static native Pointer<Byte> SysAllocStringLen(Pointer<Character> var0, int var1);

    public static native void SysFreeString(Pointer<Byte> var0);

    public static native int SysReAllocString(Pointer<Pointer<Byte>> var0, Pointer<Character> var1);

    public static native int SysReAllocStringLen(Pointer<Pointer<Byte>> var0, Pointer<Character> var1, int var2);

    public static native int SysStringByteLen(Pointer<Byte> var0);

    public static native int SysStringLen(Pointer<Byte> var0);

    public static native int SetErrorInfo(int var0, Pointer<IErrorInfo> var1);

    public static native int GetErrorInfo(int var0, Pointer<Pointer<IErrorInfo>> var1);

    public static native int CreateErrorInfo(Pointer<Pointer<ICreateErrorInfo>> var0);

    public static native Pointer<SAFEARRAY> SafeArrayCreate(short var0, int var1, Pointer<SAFEARRAYBOUND> var2);

    public static native Pointer<SAFEARRAY> SafeArrayCreateEx(short var0, int var1, Pointer<SAFEARRAYBOUND> var2);

    public static native Pointer<SAFEARRAY> SafeArrayCreateVector(short var0, @CLong long var1, int var3);

    public static native Pointer<SAFEARRAY> SafeArrayCreateVectorEx(short var0, @CLong long var1, int var3);

    public static native int SafeArrayAllocDescriptor(int var0, Pointer<Pointer<SAFEARRAY>> var1);

    public static native int SafeArrayAllocDescriptorEx(short var0, int var1, Pointer<Pointer<SAFEARRAY>> var2);

    public static native int SafeArrayAllocData(Pointer<SAFEARRAY> var0);

    public static native int SafeArrayDestroyDescriptor(Pointer<SAFEARRAY> var0);

    public static native int SafeArrayPutElement(Pointer<SAFEARRAY> var0, Pointer<CLong> var1, Pointer<?> var2);

    public static native int SafeArrayGetElement(Pointer<SAFEARRAY> var0, Pointer<CLong> var1, Pointer<?> var2);

    public static native int SafeArrayLock(Pointer<SAFEARRAY> var0);

    public static native int SafeArrayUnlock(Pointer<SAFEARRAY> var0);

    public static native int SafeArrayGetUBound(Pointer<SAFEARRAY> var0, int var1, Pointer<CLong> var2);

    public static native int SafeArrayGetLBound(Pointer<SAFEARRAY> var0, int var1, Pointer<CLong> var2);

    public static native int SafeArrayGetDim(Pointer<SAFEARRAY> var0);

    public static native int SafeArrayGetElemsize(Pointer<SAFEARRAY> var0);

    public static native int SafeArrayGetVartype(Pointer<SAFEARRAY> var0, Pointer<Short> var1);

    public static native int SafeArrayAccessData(Pointer<SAFEARRAY> var0, Pointer<Pointer<?>> var1);

    public static native int SafeArrayUnaccessData(Pointer<SAFEARRAY> var0);

    public static native int SafeArrayPtrOfIndex(Pointer<SAFEARRAY> var0, Pointer<CLong> var1, Pointer<Pointer<?>> var2);

    public static native int SafeArrayCopyData(Pointer<SAFEARRAY> var0, Pointer<SAFEARRAY> var1);

    public static native int SafeArrayDestroyData(Pointer<SAFEARRAY> var0);

    public static native int SafeArrayDestroy(Pointer<SAFEARRAY> var0);

    public static native int SafeArrayCopy(Pointer<SAFEARRAY> var0, Pointer<Pointer<SAFEARRAY>> var1);

    public static native int SafeArrayRedim(Pointer<SAFEARRAY> var0, Pointer<SAFEARRAYBOUND> var1);

    public static native int SafeArraySetRecordInfo(Pointer<SAFEARRAY> var0, Pointer<IRecordInfo> var1);

    public static native int SafeArrayGetRecordInfo(Pointer<SAFEARRAY> var0, Pointer<Pointer<IRecordInfo>> var1);

    public static native int SafeArraySetIID(Pointer<SAFEARRAY> var0, Pointer<GUID> var1);

    public static native int SafeArrayGetIID(Pointer<SAFEARRAY> var0, Pointer<GUID> var1);

    public static native int VectorFromBstr(Pointer<Byte> var0, Pointer<Pointer<SAFEARRAY>> var1);

    public static native int BstrFromVector(Pointer<SAFEARRAY> var0, Pointer<Pointer<Byte>> var1);

    public static native int RegisterActiveObject(Pointer<IUnknown> var0, int var1);

    public static native int RevokeActiveObject(int var0);

    public static native int GetActiveObject(Pointer<Pointer<IUnknown>> var0);

    public static native int GetRecordInfoFromTypeInfo(Pointer<ITypeInfo> var0, Pointer<Pointer<IRecordInfo>> var1);

    public static native int GetRecordInfoFromGuids(Pointer<GUID> var0, int var1, int var2, int var3, Pointer<GUID> var4, Pointer<Pointer<IRecordInfo>> var5);

    public static native void VariantInit(Pointer<VARIANT> var0);

    public static native int VariantClear(Pointer<VARIANT> var0);

    public static native int VariantCopy(Pointer<VARIANT> var0, Pointer<VARIANT> var1);

    public static native int VariantCopyInd(Pointer<VARIANT> var0, Pointer<VARIANT> var1);

    public static native int VariantChangeType(Pointer<VARIANT> var0, Pointer<VARIANT> var1, short var2, short var3);

    public static native int VariantChangeTypeEx(Pointer<VARIANT> var0, Pointer<VARIANT> var1, int var2, short var3, short var4);

    public static native int VarUI1FromI2(short var0, Pointer<Byte> var1);

    public static native int VarUI1FromI4(@CLong long var0, Pointer<Byte> var2);

    public static native int VarUI1FromI8(long var0, Pointer<Byte> var2);

    public static native int VarUI1FromR4(float var0, Pointer<Byte> var1);

    public static native int VarUI1FromR8(double var0, Pointer<Byte> var2);

    public static native int VarUI1FromDate(DATE var0, Pointer<Byte> var1);

    public static native int VarUI1FromBool(short var0, Pointer<Byte> var1);

    public static native int VarUI1FromI1(byte var0, Pointer<Byte> var1);

    public static native int VarUI1FromUI2(short var0, Pointer<Byte> var1);

    public static native int VarUI1FromUI4(int var0, Pointer<Byte> var1);

    public static native int VarUI1FromUI8(long var0, Pointer<Byte> var2);

    public static native int VarUI1FromStr(Pointer<Character> var0, int var1, int var2, Pointer<Byte> var3);

    public static native int VarUI1FromCy(CY var0, Pointer<Byte> var1);

    public static native int VarUI1FromDec(Pointer<DECIMAL> var0, Pointer<Byte> var1);

    public static native int VarUI1FromDisp(Pointer<IDispatch> var0, int var1, Pointer<Byte> var2);

    public static native int VarI2FromUI1(byte var0, Pointer<Short> var1);

    public static native int VarI2FromI4(@CLong long var0, Pointer<Short> var2);

    public static native int VarI2FromI8(long var0, Pointer<Short> var2);

    public static native int VarI2FromR4(float var0, Pointer<Short> var1);

    public static native int VarI2FromR8(double var0, Pointer<Short> var2);

    public static native int VarI2FromDate(DATE var0, Pointer<Short> var1);

    public static native int VarI2FromBool(short var0, Pointer<Short> var1);

    public static native int VarI2FromI1(byte var0, Pointer<Short> var1);

    public static native int VarI2FromUI2(short var0, Pointer<Short> var1);

    public static native int VarI2FromUI4(int var0, Pointer<Short> var1);

    public static native int VarI2FromUI8(long var0, Pointer<Short> var2);

    public static native int VarI2FromStr(Pointer<Character> var0, int var1, int var2, Pointer<Short> var3);

    public static native int VarI2FromCy(CY var0, Pointer<Short> var1);

    public static native int VarI2FromDec(Pointer<DECIMAL> var0, Pointer<Short> var1);

    public static native int VarI2FromDisp(Pointer<IDispatch> var0, int var1, Pointer<Short> var2);

    public static native int VarI4FromUI1(byte var0, Pointer<CLong> var1);

    public static native int VarI4FromI2(short var0, Pointer<CLong> var1);

    public static native int VarI4FromI8(long var0, Pointer<CLong> var2);

    public static native int VarI4FromR4(float var0, Pointer<CLong> var1);

    public static native int VarI4FromR8(double var0, Pointer<CLong> var2);

    public static native int VarI4FromDate(DATE var0, Pointer<CLong> var1);

    public static native int VarI4FromBool(short var0, Pointer<CLong> var1);

    public static native int VarI4FromI1(byte var0, Pointer<CLong> var1);

    public static native int VarI4FromUI2(short var0, Pointer<CLong> var1);

    public static native int VarI4FromUI4(int var0, Pointer<CLong> var1);

    public static native int VarI4FromUI8(long var0, Pointer<CLong> var2);

    public static native int VarI4FromStr(Pointer<Character> var0, int var1, int var2, Pointer<CLong> var3);

    public static native int VarI4FromCy(CY var0, Pointer<CLong> var1);

    public static native int VarI4FromDec(Pointer<DECIMAL> var0, Pointer<CLong> var1);

    public static native int VarI4FromDisp(Pointer<IDispatch> var0, int var1, Pointer<CLong> var2);

    public static native int VarI8FromUI1(byte var0, Pointer<Long> var1);

    public static native int VarI8FromI2(short var0, Pointer<Long> var1);

    public static native int VarI8FromI4(@CLong long var0, Pointer<Long> var2);

    public static native int VarI8FromR4(float var0, Pointer<Long> var1);

    public static native int VarI8FromR8(double var0, Pointer<Long> var2);

    public static native int VarI8FromDate(DATE var0, Pointer<Long> var1);

    public static native int VarI8FromStr(Pointer<Character> var0, int var1, int var2, Pointer<Long> var3);

    public static native int VarI8FromBool(short var0, Pointer<Long> var1);

    public static native int VarI8FromI1(byte var0, Pointer<Long> var1);

    public static native int VarI8FromUI2(short var0, Pointer<Long> var1);

    public static native int VarI8FromUI4(int var0, Pointer<Long> var1);

    public static native int VarI8FromUI8(long var0, Pointer<Long> var2);

    public static native int VarI8FromDec(Pointer<DECIMAL> var0, Pointer<Long> var1);

    public static native int VarI8FromInt(int var0, Pointer<Long> var1);

    public static native int VarI8FromCy(CY var0, Pointer<Long> var1);

    public static native int VarI8FromDisp(Pointer<IDispatch> var0, int var1, Pointer<Long> var2);

    public static native int VarR4FromUI1(byte var0, Pointer<Float> var1);

    public static native int VarR4FromI2(short var0, Pointer<Float> var1);

    public static native int VarR4FromI4(@CLong long var0, Pointer<Float> var2);

    public static native int VarR4FromI8(long var0, Pointer<Float> var2);

    public static native int VarR4FromR8(double var0, Pointer<Float> var2);

    public static native int VarR4FromDate(DATE var0, Pointer<Float> var1);

    public static native int VarR4FromBool(short var0, Pointer<Float> var1);

    public static native int VarR4FromI1(byte var0, Pointer<Float> var1);

    public static native int VarR4FromUI2(short var0, Pointer<Float> var1);

    public static native int VarR4FromUI4(int var0, Pointer<Float> var1);

    public static native int VarR4FromUI8(long var0, Pointer<Float> var2);

    public static native int VarR4FromStr(Pointer<Character> var0, int var1, int var2, Pointer<Float> var3);

    public static native int VarR4FromCy(CY var0, Pointer<Float> var1);

    public static native int VarR4FromDec(Pointer<DECIMAL> var0, Pointer<Float> var1);

    public static native int VarR4FromDisp(Pointer<IDispatch> var0, int var1, Pointer<Float> var2);

    public static native int VarR8FromUI1(byte var0, Pointer<Double> var1);

    public static native int VarR8FromI2(short var0, Pointer<Double> var1);

    public static native int VarR8FromI4(@CLong long var0, Pointer<Double> var2);

    public static native int VarR8FromI8(long var0, Pointer<Double> var2);

    public static native int VarR8FromR4(float var0, Pointer<Double> var1);

    public static native int VarR8FromDate(DATE var0, Pointer<Double> var1);

    public static native int VarR8FromBool(short var0, Pointer<Double> var1);

    public static native int VarR8FromI1(byte var0, Pointer<Double> var1);

    public static native int VarR8FromUI2(short var0, Pointer<Double> var1);

    public static native int VarR8FromUI4(int var0, Pointer<Double> var1);

    public static native int VarR8FromUI8(long var0, Pointer<Double> var2);

    public static native int VarR8FromStr(Pointer<Character> var0, int var1, int var2, Pointer<Double> var3);

    public static native int VarR8FromCy(CY var0, Pointer<Double> var1);

    public static native int VarR8FromDec(Pointer<DECIMAL> var0, Pointer<Double> var1);

    public static native int VarR8FromDisp(Pointer<IDispatch> var0, int var1, Pointer<Double> var2);

    public static native int VarDateFromUI1(byte var0, Pointer<DATE> var1);

    public static native int VarDateFromI2(short var0, Pointer<DATE> var1);

    public static native int VarDateFromI4(@CLong long var0, Pointer<DATE> var2);

    public static native int VarDateFromI8(long var0, Pointer<DATE> var2);

    public static native int VarDateFromR4(float var0, Pointer<DATE> var1);

    public static native int VarDateFromR8(double var0, Pointer<DATE> var2);

    public static native int VarDateFromStr(Pointer<Character> var0, int var1, int var2, Pointer<DATE> var3);

    public static native int VarDateFromI1(byte var0, Pointer<DATE> var1);

    public static native int VarDateFromUI2(short var0, Pointer<DATE> var1);

    public static native int VarDateFromUI4(int var0, Pointer<DATE> var1);

    public static native int VarDateFromUI8(long var0, Pointer<DATE> var2);

    public static native int VarDateFromBool(short var0, Pointer<DATE> var1);

    public static native int VarDateFromCy(CY var0, Pointer<DATE> var1);

    public static native int VarDateFromDec(Pointer<DECIMAL> var0, Pointer<DATE> var1);

    public static native int VarDateFromDisp(Pointer<IDispatch> var0, int var1, Pointer<DATE> var2);

    public static native int VarCyFromUI1(byte var0, Pointer<CY> var1);

    public static native int VarCyFromI2(short var0, Pointer<CY> var1);

    public static native int VarCyFromI4(@CLong long var0, Pointer<CY> var2);

    public static native int VarCyFromI8(long var0, Pointer<CY> var2);

    public static native int VarCyFromR4(float var0, Pointer<CY> var1);

    public static native int VarCyFromR8(double var0, Pointer<CY> var2);

    public static native int VarCyFromDate(DATE var0, Pointer<CY> var1);

    public static native int VarCyFromStr(Pointer<Character> var0, int var1, int var2, Pointer<CY> var3);

    public static native int VarCyFromBool(short var0, Pointer<CY> var1);

    public static native int VarCyFromI1(byte var0, Pointer<CY> var1);

    public static native int VarCyFromUI2(short var0, Pointer<CY> var1);

    public static native int VarCyFromUI4(int var0, Pointer<CY> var1);

    public static native int VarCyFromUI8(long var0, Pointer<CY> var2);

    public static native int VarCyFromDec(Pointer<DECIMAL> var0, Pointer<CY> var1);

    public static native int VarCyFromDisp(Pointer<IDispatch> var0, int var1, Pointer<CY> var2);

    public static native int VarBstrFromUI1(byte var0, int var1, int var2, Pointer<Pointer<Byte>> var3);

    public static native int VarBstrFromI2(short var0, int var1, int var2, Pointer<Pointer<Byte>> var3);

    public static native int VarBstrFromI4(@CLong long var0, int var2, int var3, Pointer<Pointer<Byte>> var4);

    public static native int VarBstrFromI8(long var0, int var2, int var3, Pointer<Pointer<Byte>> var4);

    public static native int VarBstrFromR4(float var0, int var1, int var2, Pointer<Pointer<Byte>> var3);

    public static native int VarBstrFromR8(double var0, int var2, int var3, Pointer<Pointer<Byte>> var4);

    public static native int VarBstrFromDate(DATE var0, int var1, int var2, Pointer<Pointer<Byte>> var3);

    public static native int VarBstrFromBool(short var0, int var1, int var2, Pointer<Pointer<Byte>> var3);

    public static native int VarBstrFromI1(byte var0, int var1, int var2, Pointer<Pointer<Byte>> var3);

    public static native int VarBstrFromUI2(short var0, int var1, int var2, Pointer<Pointer<Byte>> var3);

    public static native int VarBstrFromUI8(long var0, int var2, int var3, Pointer<Pointer<Byte>> var4);

    public static native int VarBstrFromUI4(int var0, int var1, int var2, Pointer<Pointer<Byte>> var3);

    public static native int VarBstrFromCy(CY var0, int var1, int var2, Pointer<Pointer<Byte>> var3);

    public static native int VarBstrFromDec(Pointer<DECIMAL> var0, int var1, int var2, Pointer<Pointer<Byte>> var3);

    public static native int VarBstrFromDisp(Pointer<IDispatch> var0, int var1, int var2, Pointer<Pointer<Byte>> var3);

    public static native int VarBoolFromUI1(byte var0, Pointer<Short> var1);

    public static native int VarBoolFromI2(short var0, Pointer<Short> var1);

    public static native int VarBoolFromI4(@CLong long var0, Pointer<Short> var2);

    public static native int VarBoolFromI8(long var0, Pointer<Short> var2);

    public static native int VarBoolFromR4(float var0, Pointer<Short> var1);

    public static native int VarBoolFromR8(double var0, Pointer<Short> var2);

    public static native int VarBoolFromDate(DATE var0, Pointer<Short> var1);

    public static native int VarBoolFromStr(Pointer<Character> var0, int var1, int var2, Pointer<Short> var3);

    public static native int VarBoolFromI1(byte var0, Pointer<Short> var1);

    public static native int VarBoolFromUI2(short var0, Pointer<Short> var1);

    public static native int VarBoolFromUI4(int var0, Pointer<Short> var1);

    public static native int VarBoolFromUI8(long var0, Pointer<Short> var2);

    public static native int VarBoolFromCy(CY var0, Pointer<Short> var1);

    public static native int VarBoolFromDec(Pointer<DECIMAL> var0, Pointer<Short> var1);

    public static native int VarBoolFromDisp(Pointer<IDispatch> var0, int var1, Pointer<Short> var2);

    public static native int VarI1FromUI1(byte var0, Pointer<Byte> var1);

    public static native int VarI1FromI2(short var0, Pointer<Byte> var1);

    public static native int VarI1FromI4(@CLong long var0, Pointer<Byte> var2);

    public static native int VarI1FromI8(long var0, Pointer<Byte> var2);

    public static native int VarI1FromR4(float var0, Pointer<Byte> var1);

    public static native int VarI1FromR8(double var0, Pointer<Byte> var2);

    public static native int VarI1FromDate(DATE var0, Pointer<Byte> var1);

    public static native int VarI1FromStr(Pointer<Character> var0, int var1, int var2, Pointer<Byte> var3);

    public static native int VarI1FromBool(short var0, Pointer<Byte> var1);

    public static native int VarI1FromUI2(short var0, Pointer<Byte> var1);

    public static native int VarI1FromUI4(int var0, Pointer<Byte> var1);

    public static native int VarI1FromUI8(long var0, Pointer<Byte> var2);

    public static native int VarI1FromCy(CY var0, Pointer<Byte> var1);

    public static native int VarI1FromDec(Pointer<DECIMAL> var0, Pointer<Byte> var1);

    public static native int VarI1FromDisp(Pointer<IDispatch> var0, int var1, Pointer<Byte> var2);

    public static native int VarUI2FromUI1(byte var0, Pointer<Short> var1);

    public static native int VarUI2FromI2(short var0, Pointer<Short> var1);

    public static native int VarUI2FromI4(@CLong long var0, Pointer<Short> var2);

    public static native int VarUI2FromI8(long var0, Pointer<Short> var2);

    public static native int VarUI2FromR4(float var0, Pointer<Short> var1);

    public static native int VarUI2FromR8(double var0, Pointer<Short> var2);

    public static native int VarUI2FromDate(DATE var0, Pointer<Short> var1);

    public static native int VarUI2FromStr(Pointer<Character> var0, int var1, int var2, Pointer<Short> var3);

    public static native int VarUI2FromBool(short var0, Pointer<Short> var1);

    public static native int VarUI2FromI1(byte var0, Pointer<Short> var1);

    public static native int VarUI2FromUI4(int var0, Pointer<Short> var1);

    public static native int VarUI2FromUI8(long var0, Pointer<Short> var2);

    public static native int VarUI2FromCy(CY var0, Pointer<Short> var1);

    public static native int VarUI2FromDec(Pointer<DECIMAL> var0, Pointer<Short> var1);

    public static native int VarUI2FromDisp(Pointer<IDispatch> var0, int var1, Pointer<Short> var2);

    public static native int VarUI4FromStr(Pointer<Character> var0, int var1, int var2, Pointer<Integer> var3);

    public static native int VarUI4FromUI1(byte var0, Pointer<Integer> var1);

    public static native int VarUI4FromI2(short var0, Pointer<Integer> var1);

    public static native int VarUI4FromI4(@CLong long var0, Pointer<Integer> var2);

    public static native int VarUI4FromI8(long var0, Pointer<Integer> var2);

    public static native int VarUI4FromR4(float var0, Pointer<Integer> var1);

    public static native int VarUI4FromR8(double var0, Pointer<Integer> var2);

    public static native int VarUI4FromDate(DATE var0, Pointer<Integer> var1);

    public static native int VarUI4FromBool(short var0, Pointer<Integer> var1);

    public static native int VarUI4FromI1(byte var0, Pointer<Integer> var1);

    public static native int VarUI4FromUI2(short var0, Pointer<Integer> var1);

    public static native int VarUI4FromUI8(long var0, Pointer<Integer> var2);

    public static native int VarUI4FromCy(CY var0, Pointer<Integer> var1);

    public static native int VarUI4FromDec(Pointer<DECIMAL> var0, Pointer<Integer> var1);

    public static native int VarUI4FromDisp(Pointer<IDispatch> var0, int var1, Pointer<Integer> var2);

    public static native int VarUI8FromUI1(byte var0, Pointer<Long> var1);

    public static native int VarUI8FromI2(short var0, Pointer<Long> var1);

    public static native int VarUI8FromI4(@CLong long var0, Pointer<Long> var2);

    public static native int VarUI8FromI8(long var0, Pointer<Long> var2);

    public static native int VarUI8FromR4(float var0, Pointer<Long> var1);

    public static native int VarUI8FromR8(double var0, Pointer<Long> var2);

    public static native int VarUI8FromDate(DATE var0, Pointer<Long> var1);

    public static native int VarUI8FromStr(Pointer<Character> var0, int var1, int var2, Pointer<Long> var3);

    public static native int VarUI8FromBool(short var0, Pointer<Long> var1);

    public static native int VarUI8FromI1(byte var0, Pointer<Long> var1);

    public static native int VarUI8FromUI2(short var0, Pointer<Long> var1);

    public static native int VarUI8FromUI4(int var0, Pointer<Long> var1);

    public static native int VarUI8FromDec(Pointer<DECIMAL> var0, Pointer<Long> var1);

    public static native int VarUI8FromInt(int var0, Pointer<Long> var1);

    public static native int VarUI8FromCy(CY var0, Pointer<Long> var1);

    public static native int VarUI8FromDisp(Pointer<IDispatch> var0, int var1, Pointer<Long> var2);

    public static native int VarDecFromUI1(byte var0, Pointer<DECIMAL> var1);

    public static native int VarDecFromI2(short var0, Pointer<DECIMAL> var1);

    public static native int VarDecFromI4(@CLong long var0, Pointer<DECIMAL> var2);

    public static native int VarDecFromI8(long var0, Pointer<DECIMAL> var2);

    public static native int VarDecFromR4(float var0, Pointer<DECIMAL> var1);

    public static native int VarDecFromR8(double var0, Pointer<DECIMAL> var2);

    public static native int VarDecFromDate(DATE var0, Pointer<DECIMAL> var1);

    public static native int VarDecFromStr(Pointer<Character> var0, int var1, int var2, Pointer<DECIMAL> var3);

    public static native int VarDecFromBool(short var0, Pointer<DECIMAL> var1);

    public static native int VarDecFromI1(byte var0, Pointer<DECIMAL> var1);

    public static native int VarDecFromUI2(short var0, Pointer<DECIMAL> var1);

    public static native int VarDecFromUI4(int var0, Pointer<DECIMAL> var1);

    public static native int VarDecFromUI8(long var0, Pointer<DECIMAL> var2);

    public static native int VarDecFromCy(CY var0, Pointer<DECIMAL> var1);

    public static native int VarDecFromDisp(Pointer<IDispatch> var0, int var1, Pointer<DECIMAL> var2);

    public static native int VarR4CmpR8(float var0, double var1);

    public static native int VarR8Pow(double var0, double var2, Pointer<Double> var4);

    public static native int VarR8Round(double var0, int var2, Pointer<Double> var3);

    public static native int VarDecAbs(Pointer<DECIMAL> var0, Pointer<DECIMAL> var1);

    public static native int VarDecAdd(Pointer<DECIMAL> var0, Pointer<DECIMAL> var1, Pointer<DECIMAL> var2);

    public static native int VarDecCmp(Pointer<DECIMAL> var0, Pointer<DECIMAL> var1);

    public static native int VarDecCmpR8(Pointer<DECIMAL> var0, double var1);

    public static native int VarDecDiv(Pointer<DECIMAL> var0, Pointer<DECIMAL> var1, Pointer<DECIMAL> var2);

    public static native int VarDecFix(Pointer<DECIMAL> var0, Pointer<DECIMAL> var1);

    public static native int VarDecInt(Pointer<DECIMAL> var0, Pointer<DECIMAL> var1);

    public static native int VarDecMul(Pointer<DECIMAL> var0, Pointer<DECIMAL> var1, Pointer<DECIMAL> var2);

    public static native int VarDecNeg(Pointer<DECIMAL> var0, Pointer<DECIMAL> var1);

    public static native int VarDecRound(Pointer<DECIMAL> var0, int var1, Pointer<DECIMAL> var2);

    public static native int VarDecSub(Pointer<DECIMAL> var0, Pointer<DECIMAL> var1, Pointer<DECIMAL> var2);

    public static native int VarCyAbs(CY var0, Pointer<CY> var1);

    public static native int VarCyAdd(CY var0, CY var1, Pointer<CY> var2);

    public static native int VarCyCmp(CY var0, CY var1);

    public static native int VarCyCmpR8(CY var0, double var1);

    public static native int VarCyFix(CY var0, Pointer<CY> var1);

    public static native int VarCyInt(CY var0, Pointer<CY> var1);

    public static native int VarCyMul(CY var0, CY var1, Pointer<CY> var2);

    public static native int VarCyMulI4(CY var0, @CLong long var1, Pointer<CY> var3);

    public static native int VarCyMulI8(CY var0, long var1, Pointer<CY> var3);

    public static native int VarCyNeg(CY var0, Pointer<CY> var1);

    public static native int VarCyRound(CY var0, int var1, Pointer<CY> var2);

    public static native int VarCySub(CY var0, CY var1, Pointer<CY> var2);

    public static native int VarAdd(Pointer<VARIANT> var0, Pointer<VARIANT> var1, Pointer<VARIANT> var2);

    public static native int VarAnd(Pointer<VARIANT> var0, Pointer<VARIANT> var1, Pointer<VARIANT> var2);

    public static native int VarCat(Pointer<VARIANT> var0, Pointer<VARIANT> var1, Pointer<VARIANT> var2);

    public static native int VarDiv(Pointer<VARIANT> var0, Pointer<VARIANT> var1, Pointer<VARIANT> var2);

    public static native int VarEqv(Pointer<VARIANT> var0, Pointer<VARIANT> var1, Pointer<VARIANT> var2);

    public static native int VarIdiv(Pointer<VARIANT> var0, Pointer<VARIANT> var1, Pointer<VARIANT> var2);

    public static native int VarImp(Pointer<VARIANT> var0, Pointer<VARIANT> var1, Pointer<VARIANT> var2);

    public static native int VarMod(Pointer<VARIANT> var0, Pointer<VARIANT> var1, Pointer<VARIANT> var2);

    public static native int VarMul(Pointer<VARIANT> var0, Pointer<VARIANT> var1, Pointer<VARIANT> var2);

    public static native int VarOr(Pointer<VARIANT> var0, Pointer<VARIANT> var1, Pointer<VARIANT> var2);

    public static native int VarPow(Pointer<VARIANT> var0, Pointer<VARIANT> var1, Pointer<VARIANT> var2);

    public static native int VarSub(Pointer<VARIANT> var0, Pointer<VARIANT> var1, Pointer<VARIANT> var2);

    public static native int VarXor(Pointer<VARIANT> var0, Pointer<VARIANT> var1, Pointer<VARIANT> var2);

    public static native int VarAbs(Pointer<VARIANT> var0, Pointer<VARIANT> var1);

    public static native int VarFix(Pointer<VARIANT> var0, Pointer<VARIANT> var1);

    public static native int VarInt(Pointer<VARIANT> var0, Pointer<VARIANT> var1);

    public static native int VarNeg(Pointer<VARIANT> var0, Pointer<VARIANT> var1);

    public static native int VarNot(Pointer<VARIANT> var0, Pointer<VARIANT> var1);

    public static native int VarRound(Pointer<VARIANT> var0, int var1, Pointer<VARIANT> var2);

    public static native int VarCmp(Pointer<VARIANT> var0, Pointer<VARIANT> var1, int var2, int var3);

    public static native int VarBstrCmp(Pointer<Byte> var0, Pointer<Byte> var1, int var2, int var3);

    public static native int VarBstrCat(Pointer<Byte> var0, Pointer<Byte> var1, Pointer<Pointer<Byte>> var2);

    public static native int VarParseNumFromStr(Pointer<Character> var0, int var1, int var2, Pointer<NUMPARSE> var3, Pointer<Byte> var4);

    public static native int VarNumFromParseNum(Pointer<NUMPARSE> var0, Pointer<Byte> var1, int var2, Pointer<VARIANT> var3);

    public static native int DosDateTimeToVariantTime(short var0, short var1, Pointer<Double> var2);

    public static native int VariantTimeToDosDateTime(double var0, Pointer<Short> var2, Pointer<Short> var3);

    public static native int VariantTimeToSystemTime(double var0, Pointer<SYSTEMTIME> var2);

    public static native int SystemTimeToVariantTime(Pointer<SYSTEMTIME> var0, Pointer<Double> var1);

    public static native int VarDateFromUdate(Pointer<UDATE> var0, int var1, Pointer<DATE> var2);

    public static native int VarDateFromUdateEx(Pointer<UDATE> var0, int var1, int var2, Pointer<DATE> var3);

    public static native int VarUdateFromDate(DATE var0, int var1, Pointer<UDATE> var2);

    public static native int VarWeekdayName(int var0, int var1, int var2, int var3, Pointer<Pointer<Byte>> var4);

    public static native int VarMonthName(int var0, int var1, int var2, Pointer<Pointer<Byte>> var3);

    public static native int GetAltMonthNames(int var0, Pointer<Pointer<Pointer<Character>>> var1);

    public static native int VarFormat(Pointer<VARIANT> var0, Pointer<Character> var1, int var2, int var3, int var4, Pointer<Pointer<Byte>> var5);

    public static native int VarFormatCurrency(Pointer<VARIANT> var0, int var1, int var2, int var3, int var4, int var5, Pointer<Pointer<Byte>> var6);

    public static native int VarFormatDateTime(Pointer<VARIANT> var0, int var1, int var2, Pointer<Pointer<Byte>> var3);

    public static native int VarFormatNumber(Pointer<VARIANT> var0, int var1, int var2, int var3, int var4, int var5, Pointer<Pointer<Byte>> var6);

    public static native int VarFormatPercent(Pointer<VARIANT> var0, int var1, int var2, int var3, int var4, int var5, Pointer<Pointer<Byte>> var6);

    public static native int VarFormatFromTokens(Pointer<VARIANT> var0, Pointer<Character> var1, int var2, Pointer<Pointer<Byte>> var3, int var4);

    public static native int VarTokenizeFormatString(Pointer<Character> var0, int var1, int var2, int var3, int var4, Pointer<Integer> var5);

    public static native int DispGetParam(Pointer<DISPPARAMS> var0, int var1, short var2, Pointer<VARIANT> var3, Pointer<Integer> var4);

    public static native int DispGetIDsOfNames(Pointer<ITypeInfo> var0, Pointer<Pointer<Character>> var1, int var2, Pointer<CLong> var3);

    public static native int DispInvoke(Pointer<?> var0, Pointer<ITypeInfo> var1, @CLong long var2, short var4, Pointer<DISPPARAMS> var5, Pointer<VARIANT> var6, Pointer<EXCEPINFO> var7, Pointer<Integer> var8);

    public static native int CreateDispTypeInfo(Pointer<INTERFACEDATA> var0, int var1, Pointer<Pointer<ITypeInfo>> var2);

    public static native int CreateStdDispatch(Pointer<IUnknown> var0, Pointer<?> var1, Pointer<ITypeInfo> var2, Pointer<Pointer<IUnknown>> var3);

    public static native int DispCallFunc(Pointer<?> var0, ValuedEnum<CALLCONV> var1, short var2, int var3, Pointer<Short> var4, Pointer<Pointer<VARIANT>> var5, Pointer<VARIANT> var6);

    public static native int LHashValOfNameSysA(ValuedEnum<SYSKIND> var0, int var1);

    public static native int LHashValOfNameSys(ValuedEnum<SYSKIND> var0, int var1);

    public static native int CreateTypeLib(ValuedEnum<SYSKIND> var0, Pointer<Character> var1, Pointer<Pointer<ICreateTypeLib>> var2);

    public static native int CreateTypeLib2(ValuedEnum<SYSKIND> var0, Pointer<Pointer<ICreateTypeLib2>> var1);

    public static native int LoadRegTypeLib(Pointer<GUID> var0, short var1, short var2, int var3, Pointer<Pointer<ITypeLib>> var4);

    public static native int LoadTypeLib(Pointer<Character> var0, Pointer<Pointer<ITypeLib>> var1);

    public static native int LoadTypeLibEx(ValuedEnum<REGKIND> var0, Pointer<Pointer<ITypeLib>> var1);

    public static native int QueryPathOfRegTypeLib(Pointer<GUID> var0, short var1, short var2, int var3, Pointer<Pointer<Byte>> var4);

    public static native int RegisterTypeLib(Pointer<ITypeLib> var0, Pointer<Character> var1, Pointer<Character> var2);

    public static native int UnRegisterTypeLib(Pointer<GUID> var0, short var1, short var2, int var3, ValuedEnum<SYSKIND> var4);

    public static native int RegisterTypeLibForUser(Pointer<ITypeLib> var0, Pointer<Character> var1, Pointer<Character> var2);

    public static native int UnRegisterTypeLibForUser(Pointer<GUID> var0, short var1, short var2, int var3, ValuedEnum<SYSKIND> var4);

    public static native void ClearCustData(Pointer<CUSTDATA> var0);

    static {
        BridJ.register();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class INTERFACEDATA
    extends StructObject {
        @Field(value=0)
        public Pointer<METHODDATA> pmethdata() {
            return this.io.getPointerField(this, 0);
        }

        @Field(value=0)
        public INTERFACEDATA pmethdata(Pointer<METHODDATA> pmethdata) {
            this.io.setPointerField(this, 0, pmethdata);
            return this;
        }

        public final Pointer<METHODDATA> pmethdata_$eq(Pointer<METHODDATA> pmethdata) {
            this.pmethdata(pmethdata);
            return pmethdata;
        }

        @Field(value=1)
        public int cMembers() {
            return this.io.getIntField(this, 1);
        }

        @Field(value=1)
        public INTERFACEDATA cMembers(int cMembers) {
            this.io.setIntField(this, 1, cMembers);
            return this;
        }

        public final int cMembers_$eq(int cMembers) {
            this.cMembers(cMembers);
            return cMembers;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class METHODDATA
    extends StructObject {
        @Field(value=0)
        public Pointer<Character> szName() {
            return this.io.getPointerField(this, 0);
        }

        @Field(value=0)
        public METHODDATA szName(Pointer<Character> szName) {
            this.io.setPointerField(this, 0, szName);
            return this;
        }

        public final Pointer<Character> szName_$eq(Pointer<Character> szName) {
            this.szName(szName);
            return szName;
        }

        @Field(value=1)
        public Pointer<PARAMDATA> ppdata() {
            return this.io.getPointerField(this, 1);
        }

        @Field(value=1)
        public METHODDATA ppdata(Pointer<PARAMDATA> ppdata) {
            this.io.setPointerField(this, 1, ppdata);
            return this;
        }

        public final Pointer<PARAMDATA> ppdata_$eq(Pointer<PARAMDATA> ppdata) {
            this.ppdata(ppdata);
            return ppdata;
        }

        @CLong
        @Field(value=2)
        public long dispid() {
            return this.io.getCLongField(this, 2);
        }

        @CLong
        @Field(value=2)
        public METHODDATA dispid(long dispid) {
            this.io.setCLongField(this, 2, dispid);
            return this;
        }

        public final long dispid_$eq(long dispid) {
            this.dispid(dispid);
            return dispid;
        }

        @Field(value=3)
        public int iMeth() {
            return this.io.getIntField(this, 3);
        }

        @Field(value=3)
        public METHODDATA iMeth(int iMeth) {
            this.io.setIntField(this, 3, iMeth);
            return this;
        }

        public final int iMeth_$eq(int iMeth) {
            this.iMeth(iMeth);
            return iMeth;
        }

        @Field(value=4)
        public ValuedEnum<CALLCONV> cc() {
            return this.io.getEnumField(this, 4);
        }

        @Field(value=4)
        public METHODDATA cc(ValuedEnum<CALLCONV> cc2) {
            this.io.setEnumField(this, 4, cc2);
            return this;
        }

        public final ValuedEnum<CALLCONV> cc_$eq(ValuedEnum<CALLCONV> cc2) {
            this.cc(cc2);
            return cc2;
        }

        @Field(value=5)
        public int cArgs() {
            return this.io.getIntField(this, 5);
        }

        @Field(value=5)
        public METHODDATA cArgs(int cArgs) {
            this.io.setIntField(this, 5, cArgs);
            return this;
        }

        public final int cArgs_$eq(int cArgs) {
            this.cArgs(cArgs);
            return cArgs;
        }

        @Field(value=6)
        public short wFlags() {
            return this.io.getShortField(this, 6);
        }

        @Field(value=6)
        public METHODDATA wFlags(short wFlags) {
            this.io.setShortField(this, 6, wFlags);
            return this;
        }

        public final short wFlags_$eq(short wFlags) {
            this.wFlags(wFlags);
            return wFlags;
        }

        @Field(value=7)
        public short vtReturn() {
            return this.io.getShortField(this, 7);
        }

        @Field(value=7)
        public METHODDATA vtReturn(short vtReturn) {
            this.io.setShortField(this, 7, vtReturn);
            return this;
        }

        public final short vtReturn_$eq(short vtReturn) {
            this.vtReturn(vtReturn);
            return vtReturn;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class PARAMDATA
    extends StructObject {
        @Field(value=0)
        public Pointer<Character> szName() {
            return this.io.getPointerField(this, 0);
        }

        @Field(value=0)
        public PARAMDATA szName(Pointer<Character> szName) {
            this.io.setPointerField(this, 0, szName);
            return this;
        }

        public final Pointer<Character> szName_$eq(Pointer<Character> szName) {
            this.szName(szName);
            return szName;
        }

        @Field(value=1)
        public short vt() {
            return this.io.getShortField(this, 1);
        }

        @Field(value=1)
        public PARAMDATA vt(short vt2) {
            this.io.setShortField(this, 1, vt2);
            return this;
        }

        public final short vt_$eq(short vt2) {
            this.vt(vt2);
            return vt2;
        }
    }

    public static class NUMPARSE
    extends StructObject {
        @Field(value=0)
        public int cDig() {
            return this.io.getIntField(this, 0);
        }

        @Field(value=0)
        public NUMPARSE cDig(int cDig) {
            this.io.setIntField(this, 0, cDig);
            return this;
        }

        public final int cDig_$eq(int cDig) {
            this.cDig(cDig);
            return cDig;
        }

        @Field(value=1)
        public int dwInFlags() {
            return this.io.getIntField(this, 1);
        }

        @Field(value=1)
        public NUMPARSE dwInFlags(int dwInFlags) {
            this.io.setIntField(this, 1, dwInFlags);
            return this;
        }

        public final int dwInFlags_$eq(int dwInFlags) {
            this.dwInFlags(dwInFlags);
            return dwInFlags;
        }

        @Field(value=2)
        public int dwOutFlags() {
            return this.io.getIntField(this, 2);
        }

        @Field(value=2)
        public NUMPARSE dwOutFlags(int dwOutFlags) {
            this.io.setIntField(this, 2, dwOutFlags);
            return this;
        }

        public final int dwOutFlags_$eq(int dwOutFlags) {
            this.dwOutFlags(dwOutFlags);
            return dwOutFlags;
        }

        @Field(value=3)
        public int cchUsed() {
            return this.io.getIntField(this, 3);
        }

        @Field(value=3)
        public NUMPARSE cchUsed(int cchUsed) {
            this.io.setIntField(this, 3, cchUsed);
            return this;
        }

        public final int cchUsed_$eq(int cchUsed) {
            this.cchUsed(cchUsed);
            return cchUsed;
        }

        @Field(value=4)
        public int nBaseShift() {
            return this.io.getIntField(this, 4);
        }

        @Field(value=4)
        public NUMPARSE nBaseShift(int nBaseShift) {
            this.io.setIntField(this, 4, nBaseShift);
            return this;
        }

        public final int nBaseShift_$eq(int nBaseShift) {
            this.nBaseShift(nBaseShift);
            return nBaseShift;
        }

        @Field(value=5)
        public int nPwr10() {
            return this.io.getIntField(this, 5);
        }

        @Field(value=5)
        public NUMPARSE nPwr10(int nPwr10) {
            this.io.setIntField(this, 5, nPwr10);
            return this;
        }

        public final int nPwr10_$eq(int nPwr10) {
            this.nPwr10(nPwr10);
            return nPwr10;
        }
    }

    public static class UDATE
    extends StructObject {
        @Field(value=0)
        public SYSTEMTIME st() {
            return (SYSTEMTIME)this.io.getNativeObjectField(this, 0);
        }

        @Field(value=1)
        public short wDayOfYear() {
            return this.io.getShortField(this, 1);
        }

        @Field(value=1)
        public UDATE wDayOfYear(short wDayOfYear) {
            this.io.setShortField(this, 1, wDayOfYear);
            return this;
        }

        public final short wDayOfYear_$eq(short wDayOfYear) {
            this.wDayOfYear(wDayOfYear);
            return wDayOfYear;
        }
    }

    public static class SYSTEMTIME
    extends StructObject {
        @Field(value=0)
        public short wYear() {
            return this.io.getShortField(this, 0);
        }

        @Field(value=0)
        public SYSTEMTIME wYear(short wYear) {
            this.io.setShortField(this, 0, wYear);
            return this;
        }

        public final short wYear_$eq(short wYear) {
            this.wYear(wYear);
            return wYear;
        }

        @Field(value=1)
        public short wMonth() {
            return this.io.getShortField(this, 1);
        }

        @Field(value=1)
        public SYSTEMTIME wMonth(short wMonth) {
            this.io.setShortField(this, 1, wMonth);
            return this;
        }

        public final short wMonth_$eq(short wMonth) {
            this.wMonth(wMonth);
            return wMonth;
        }

        @Field(value=2)
        public short wDayOfWeek() {
            return this.io.getShortField(this, 2);
        }

        @Field(value=2)
        public SYSTEMTIME wDayOfWeek(short wDayOfWeek) {
            this.io.setShortField(this, 2, wDayOfWeek);
            return this;
        }

        public final short wDayOfWeek_$eq(short wDayOfWeek) {
            this.wDayOfWeek(wDayOfWeek);
            return wDayOfWeek;
        }

        @Field(value=3)
        public short wDay() {
            return this.io.getShortField(this, 3);
        }

        @Field(value=3)
        public SYSTEMTIME wDay(short wDay) {
            this.io.setShortField(this, 3, wDay);
            return this;
        }

        public final short wDay_$eq(short wDay) {
            this.wDay(wDay);
            return wDay;
        }

        @Field(value=4)
        public short wHour() {
            return this.io.getShortField(this, 4);
        }

        @Field(value=4)
        public SYSTEMTIME wHour(short wHour) {
            this.io.setShortField(this, 4, wHour);
            return this;
        }

        public final short wHour_$eq(short wHour) {
            this.wHour(wHour);
            return wHour;
        }

        @Field(value=5)
        public short wMinute() {
            return this.io.getShortField(this, 5);
        }

        @Field(value=5)
        public SYSTEMTIME wMinute(short wMinute) {
            this.io.setShortField(this, 5, wMinute);
            return this;
        }

        public final short wMinute_$eq(short wMinute) {
            this.wMinute(wMinute);
            return wMinute;
        }

        @Field(value=6)
        public short wSecond() {
            return this.io.getShortField(this, 6);
        }

        @Field(value=6)
        public SYSTEMTIME wSecond(short wSecond) {
            this.io.setShortField(this, 6, wSecond);
            return this;
        }

        public final short wSecond_$eq(short wSecond) {
            this.wSecond(wSecond);
            return wSecond;
        }

        @Field(value=7)
        public short wMilliseconds() {
            return this.io.getShortField(this, 7);
        }

        @Field(value=7)
        public SYSTEMTIME wMilliseconds(short wMilliseconds) {
            this.io.setShortField(this, 7, wMilliseconds);
            return this;
        }

        public final short wMilliseconds_$eq(short wMilliseconds) {
            this.wMilliseconds(wMilliseconds);
            return wMilliseconds;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class CUSTDATA
    extends StructObject {
        @Field(value=0)
        public int cCustData() {
            return this.io.getIntField(this, 0);
        }

        @Field(value=0)
        public CUSTDATA cCustData(int cCustData) {
            this.io.setIntField(this, 0, cCustData);
            return this;
        }

        public final int cCustData_$eq(int cCustData) {
            this.cCustData(cCustData);
            return cCustData;
        }

        @Field(value=1)
        public Pointer<CUSTDATAITEM> prgCustData() {
            return this.io.getPointerField(this, 1);
        }

        @Field(value=1)
        public CUSTDATA prgCustData(Pointer<CUSTDATAITEM> prgCustData) {
            this.io.setPointerField(this, 1, prgCustData);
            return this;
        }

        public final Pointer<CUSTDATAITEM> prgCustData_$eq(Pointer<CUSTDATAITEM> prgCustData) {
            this.prgCustData(prgCustData);
            return prgCustData;
        }
    }

    public static class CUSTDATAITEM
    extends StructObject {
        @Field(value=0)
        public GUID guid() {
            return (GUID)this.io.getNativeObjectField(this, 0);
        }

        @Field(value=1)
        public VARIANT varValue() {
            return (VARIANT)this.io.getNativeObjectField(this, 1);
        }
    }

    public static class EXCEPINFO
    extends StructObject {
    }

    public static class DISPPARAMS
    extends StructObject {
    }

    public static class DATE
    extends StructObject {
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum REGKIND implements IntValuedEnum<REGKIND>
    {
        REGKIND_DEFAULT(0L),
        REGKIND_REGISTER(1L),
        REGKIND_NONE(2L);

        public final long value;

        private REGKIND(long value) {
            this.value = value;
        }

        @Override
        public long value() {
            return this.value;
        }

        @Override
        public Iterator<REGKIND> iterator() {
            return Collections.singleton(this).iterator();
        }

        public static ValuedEnum<REGKIND> fromValue(long value) {
            return FlagSet.fromValue((long)value, (Enum[])REGKIND.values());
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum CALLCONV implements IntValuedEnum<CALLCONV>
    {
        CC_FASTCALL(0L),
        CC_CDECL(1L),
        CC_MSCPASCAL(2L),
        CC_PASCAL(CC_MSCPASCAL.value()),
        CC_MACPASCAL(CC_MSCPASCAL.value() + 1L),
        CC_STDCALL(CC_MSCPASCAL.value() + 2L),
        CC_FPFASTCALL(CC_MSCPASCAL.value() + 3L),
        CC_SYSCALL(CC_MSCPASCAL.value() + 4L),
        CC_MPWCDECL(CC_MSCPASCAL.value() + 5L),
        CC_MPWPASCAL(CC_MSCPASCAL.value() + 6L),
        CC_MAX(CC_MSCPASCAL.value() + 7L);

        public final long value;

        private CALLCONV(long value) {
            this.value = value;
        }

        @Override
        public long value() {
            return this.value;
        }

        @Override
        public Iterator<CALLCONV> iterator() {
            return Collections.singleton(this).iterator();
        }

        public static ValuedEnum<CALLCONV> fromValue(long value) {
            return FlagSet.fromValue((long)value, (Enum[])CALLCONV.values());
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum SYSKIND implements IntValuedEnum<SYSKIND>
    {
        SYS_WIN16(0L),
        SYS_WIN32(1L),
        SYS_MAC(2L);

        public final long value;

        private SYSKIND(long value) {
            this.value = value;
        }

        @Override
        public long value() {
            return this.value;
        }

        @Override
        public Iterator<SYSKIND> iterator() {
            return Collections.singleton(this).iterator();
        }

        public static ValuedEnum<SYSKIND> fromValue(long value) {
            return FlagSet.fromValue((long)value, (Enum[])SYSKIND.values());
        }
    }

    public static class IErrorInfo
    extends CPPObject {
    }

    public static class ICreateErrorInfo
    extends CPPObject {
    }

    public static class ICreateTypeLib2
    extends CPPObject {
    }

    public static class ICreateTypeLib
    extends CPPObject {
    }

    public static class ITypeLib
    extends CPPObject {
    }
}

