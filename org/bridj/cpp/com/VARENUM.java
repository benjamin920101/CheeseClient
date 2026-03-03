/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com;

import java.util.Collections;
import java.util.Iterator;
import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;
import org.bridj.ValuedEnum;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum VARENUM implements IntValuedEnum<VARENUM>
{
    VT_EMPTY(0L),
    VT_NULL(1L),
    VT_I2(2L),
    VT_I4(3L),
    VT_R4(4L),
    VT_R8(5L),
    VT_CY(6L),
    VT_DATE(7L),
    VT_BSTR(8L),
    VT_DISPATCH(9L),
    VT_ERROR(10L),
    VT_BOOL(11L),
    VT_VARIANT(12L),
    VT_UNKNOWN(13L),
    VT_DECIMAL(14L),
    VT_I1(16L),
    VT_UI1(17L),
    VT_UI2(18L),
    VT_UI4(19L),
    VT_I8(20L),
    VT_UI8(21L),
    VT_INT(22L),
    VT_UINT(23L),
    VT_VOID(24L),
    VT_HRESULT(25L),
    VT_PTR(26L),
    VT_SAFEARRAY(27L),
    VT_CARRAY(28L),
    VT_USERDEFINED(29L),
    VT_LPSTR(30L),
    VT_LPWSTR(31L),
    VT_FILETIME(64L),
    VT_BLOB(65L),
    VT_STREAM(66L),
    VT_STORAGE(67L),
    VT_STREAMED_OBJECT(68L),
    VT_STORED_OBJECT(69L),
    VT_BLOB_OBJECT(70L),
    VT_CF(71L),
    VT_CLSID(72L),
    VT_VECTOR(4096L),
    VT_ARRAY(8192L),
    VT_BYREF(16384L),
    VT_RESERVED(32768L),
    VT_ILLEGAL(65535L),
    VT_ILLEGALMASKED(4095L),
    VT_TYPEMASK(4095L);

    public final long value;

    private VARENUM(long value) {
        this.value = value;
    }

    @Override
    public long value() {
        return this.value;
    }

    @Override
    public Iterator<VARENUM> iterator() {
        return Collections.singleton(this).iterator();
    }

    public static ValuedEnum<VARENUM> fromValue(long value) {
        return FlagSet.fromValue((long)value, (Enum[])VARENUM.values());
    }
}

