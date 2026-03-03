/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import org.bridj.BridJ;
import org.bridj.CRuntime;
import org.bridj.FlagSet;
import org.bridj.Platform;
import org.bridj.Pointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Convention;
import org.bridj.ann.Library;
import org.bridj.ann.Ptr;
import org.bridj.ann.Runtime;
import org.bridj.cpp.CPPRuntime;
import org.bridj.cpp.com.CLSID;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;
import org.bridj.cpp.com.OLEAutomationLibrary;
import org.bridj.cpp.com.VARENUM;
import org.bridj.cpp.com.VARIANT;
import org.bridj.util.Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Library(value="Ole32")
@Runtime(value=CRuntime.class)
@Convention(value=Convention.Style.StdCall)
public class COMRuntime
extends CPPRuntime {
    public static final int CLSCTX_INPROC_SERVER = 1;
    public static final int CLSCTX_INPROC_HANDLER = 2;
    public static final int CLSCTX_LOCAL_SERVER = 4;
    public static final int CLSCTX_INPROC_SERVER16 = 8;
    public static final int CLSCTX_REMOTE_SERVER = 16;
    public static final int CLSCTX_INPROC_HANDLER16 = 32;
    public static final int CLSCTX_RESERVED1 = 64;
    public static final int CLSCTX_RESERVED2 = 128;
    public static final int CLSCTX_RESERVED3 = 256;
    public static final int CLSCTX_RESERVED4 = 512;
    public static final int CLSCTX_NO_CODE_DOWNLOAD = 1024;
    public static final int CLSCTX_RESERVED5 = 2048;
    public static final int CLSCTX_NO_CUSTOM_MARSHAL = 4096;
    public static final int CLSCTX_ENABLE_CODE_DOWNLOAD = 8192;
    public static final int CLSCTX_NO_FAILURE_LOG = 16384;
    public static final int CLSCTX_DISABLE_AAA = 32768;
    public static final int CLSCTX_ENABLE_AAA = 65536;
    public static final int CLSCTX_FROM_DEFAULT_CONTEXT = 131072;
    public static final int CLSCTX_ACTIVATE_32_BIT_SERVER = 262144;
    public static final int CLSCTX_ACTIVATE_64_BIT_SERVER = 524288;
    public static final int CLSCTX_ENABLE_CLOAKING = 0x100000;
    public static final int CLSCTX_PS_DLL = Integer.MIN_VALUE;
    public static final int CLSCTX_INPROC = 3;
    public static final int CLSCTX_ALL = 23;
    public static final int CLSCTX_SERVER = 21;
    public static final int S_OK = 0;
    public static final int S_FALSE = 1;
    public static final int REGDB_E_CLASSNOTREG = -2147221164;
    public static final int CLASS_E_NOAGGREGATION = -2147221232;
    public static final int CO_E_NOTINITIALIZED = -2147221008;
    public static final int E_UNEXPECTED = -2147418113;
    public static final int E_NOTIMPL = -2147467263;
    public static final int E_OUTOFMEMORY = -2147024882;
    public static final int E_INVALIDARG = -2147024809;
    public static final int E_NOINTERFACE = -2147467262;
    public static final int E_POINTER = -2147467261;
    public static final int E_HANDLE = -2147024890;
    public static final int E_ABORT = -2147467260;
    public static final int E_FAIL = -2147467259;
    public static final int E_ACCESSDENIED = -2147024891;
    public static final int DISP_E_BADVARTYPE = -2147352568;
    public static final int DISP_E_NOTACOLLECTION = -2147352559;
    public static final int DISP_E_MEMBERNOTFOUND = -2147352573;
    public static final int DISP_E_ARRAYISLOCKED = -2147352563;
    public static final int DISP_E_EXCEPTION = -2147352567;
    public static final int DISP_E_TYPEMISMATCH = -2147352571;
    public static final int DISP_E_BADINDEX = -2147352565;
    public static final int DISP_E_BADCALLEE = -2147352560;
    public static final int DISP_E_OVERFLOW = -2147352566;
    public static final int DISP_E_UNKNOWNINTERFACE = -2147352575;
    public static final int DISP_E_DIVBYZERO = -2147352558;
    public static final int DISP_E_UNKNOWNLCID = -2147352564;
    public static final int DISP_E_PARAMNOTOPTIONAL = -2147352561;
    public static final int DISP_E_PARAMNOTFOUND = -2147352572;
    public static final int DISP_E_BADPARAMCOUNT = -2147352562;
    public static final int DISP_E_BUFFERTOOSMALL = -2147352557;
    public static final int DISP_E_UNKNOWNNAME = -2147352570;
    public static final int DISP_E_NONAMEDARGS = -2147352569;
    static ThreadLocal<Object> comInitializer;
    private static final String model = "00000000-0000-0000-0000-000000000000";
    static final /* synthetic */ boolean $assertionsDisabled;

    @Deprecated
    public static native int CoCreateInstance(Pointer<Byte> var0, Pointer<IUnknown> var1, int var2, Pointer<Byte> var3, Pointer<Pointer<?>> var4);

    static native int CoInitializeEx(@Ptr long var0, int var2);

    static native int CoInitialize(@Ptr long var0);

    static native void CoUninitialize();

    static void error(int err) {
        switch (err) {
            case -2147418113: 
            case -2147024882: 
            case -2147024809: {
                throw new RuntimeException("Error " + Integer.toHexString(err));
            }
            case 0: {
                return;
            }
            case -2147221008: {
                throw new RuntimeException("CoInitialized wasn't called !!");
            }
            case -2147467262: {
                throw new RuntimeException("Interface does not inherit from class");
            }
            case -2147467261: {
                throw new RuntimeException("Allocated pointer pointer is null !!");
            }
        }
        throw new RuntimeException("Unexpected COM error code : " + err);
    }

    public static <I extends IUnknown> Pointer<Byte> getIID(Class<I> type) {
        IID id2 = type.getAnnotation(IID.class);
        if (id2 == null) {
            throw new RuntimeException("No " + IID.class.getName() + " annotation set on type " + type.getName() + " !");
        }
        return COMRuntime.parseGUID(id2.value());
    }

    public static <I extends IUnknown> Pointer<Byte> getCLSID(Class<I> type) {
        CLSID id2 = type.getAnnotation(CLSID.class);
        if (id2 == null) {
            throw new RuntimeException("No " + CLSID.class.getName() + " annotation set on type " + type.getName() + " !");
        }
        return COMRuntime.parseGUID(id2.value());
    }

    @Override
    protected boolean isSymbolOptional(Method method) {
        return true;
    }

    public static void initialize() {
        comInitializer.get();
    }

    public static <I extends IUnknown> I newInstance(Class<I> type) throws ClassNotFoundException {
        return COMRuntime.newInstance(type, type);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static <T extends IUnknown, I extends IUnknown> I newInstance(Class<T> instanceClass, Class<I> instanceInterface) throws ClassNotFoundException {
        IUnknown iUnknown;
        COMRuntime.initialize();
        Pointer<Pointer<?>> p2 = Pointer.allocatePointer();
        Pointer<Byte> clsid = COMRuntime.getCLSID(instanceClass);
        Pointer<Byte> uuid = COMRuntime.getIID(instanceInterface);
        try {
            IUnknown instance;
            int ret = COMRuntime.CoCreateInstance(clsid, null, 23, uuid, p2);
            if (ret == -2147221164) {
                throw new ClassNotFoundException("COM class is not registered : " + instanceClass.getSimpleName() + " (clsid = " + clsid.getCString() + ")");
            }
            COMRuntime.error(ret);
            Pointer<?> inst = p2.getPointer();
            if (inst == null) {
                throw new RuntimeException("Serious low-level issue : CoCreateInstance executed fine but we only retrieved a null pointer !");
            }
            iUnknown = instance = (IUnknown)inst.getNativeObject(instanceInterface);
        }
        catch (Throwable throwable) {
            Pointer.release(p2, clsid, uuid);
            throw throwable;
        }
        Pointer.release(p2, clsid, uuid);
        return (I)iUnknown;
    }

    public static Pointer<?> parseGUID(String descriptor) {
        Pointer<Byte> out = Pointer.allocateBytes(20L);
        if ((descriptor = descriptor.replaceAll("-", "")).length() != 32) {
            throw new RuntimeException("Expected something like :\n00000000-0000-0000-0000-000000000000\nBut got instead :\n" + descriptor);
        }
        out.setIntAtOffset(0L, (int)Long.parseLong(descriptor.substring(0, 8), 16));
        out.setShortAtOffset(4L, (short)Long.parseLong(descriptor.substring(8, 12), 16));
        out.setShortAtOffset(6L, (short)Long.parseLong(descriptor.substring(12, 16), 16));
        for (int i2 = 0; i2 < 8; ++i2) {
            out.setByteAtOffset(8 + i2, (byte)Long.parseLong(descriptor.substring(16 + i2 * 2, 16 + i2 * 2 + 2), 16));
        }
        return out;
    }

    static ValuedEnum<VARENUM> getType(VARIANT v2) {
        VARIANT.__VARIANT_NAME_1_union v1 = v2.__VARIANT_NAME_1();
        VARIANT.__VARIANT_NAME_1_union.__tagVARIANT v22 = v1.__VARIANT_NAME_2();
        short vt2 = v22.vt();
        return FlagSet.fromValue((int)vt2, VARENUM.class);
    }

    static VARIANT setType(VARIANT v2, ValuedEnum<VARENUM> vt2) {
        VARIANT.__VARIANT_NAME_1_union v1 = v2.__VARIANT_NAME_1();
        VARIANT.__VARIANT_NAME_1_union.__tagVARIANT v22 = v1.__VARIANT_NAME_2();
        v22.vt((short)vt2.value());
        return v2;
    }

    static VARIANT.__VARIANT_NAME_1_union.__tagVARIANT.__VARIANT_NAME_3_union getValues(VARIANT v2) {
        VARIANT.__VARIANT_NAME_1_union v1 = v2.__VARIANT_NAME_1();
        VARIANT.__VARIANT_NAME_1_union.__tagVARIANT v22 = v1.__VARIANT_NAME_2();
        VARIANT.__VARIANT_NAME_1_union.__tagVARIANT.__VARIANT_NAME_3_union v3 = v22.__VARIANT_NAME_3();
        return v3;
    }

    public static Object getValue(VARIANT v2) {
        FlagSet<VARENUM> vt2 = FlagSet.fromValue(COMRuntime.getType(v2));
        VARIANT.__VARIANT_NAME_1_union.__tagVARIANT.__VARIANT_NAME_3_union values = COMRuntime.getValues(v2);
        if (vt2.has(new VARENUM[]{VARENUM.VT_BYREF})) {
            switch ((VARENUM)vt2.without(new VARENUM[]{VARENUM.VT_BYREF}).toEnum()) {
                case VT_DISPATCH: {
                    return values.ppdispVal();
                }
                case VT_UNKNOWN: {
                    return values.ppunkVal();
                }
                case VT_VARIANT: {
                    return values.pvarVal();
                }
                case VT_I1: 
                case VT_UI1: {
                    return values.pbVal();
                }
                case VT_I2: 
                case VT_UI2: {
                    return values.piVal();
                }
                case VT_I4: 
                case VT_UI4: {
                    return values.plVal();
                }
                case VT_R4: {
                    return values.pfltVal();
                }
                case VT_R8: {
                    return values.pdblVal();
                }
                case VT_I8: 
                case VT_UI8: {
                    return values.pllVal();
                }
                case VT_BOOL: {
                    return values.pbVal().as(Boolean.class);
                }
                case VT_BSTR: {
                    return values.pbstrVal();
                }
                case VT_LPSTR: {
                    return values.byref().getCString();
                }
                case VT_LPWSTR: {
                    return values.byref().getWideCString();
                }
            }
            return values.byref();
        }
        switch (vt2.toEnum()) {
            case VT_I1: 
            case VT_UI1: {
                return values.bVal();
            }
            case VT_I2: 
            case VT_UI2: {
                return values.uiVal();
            }
            case VT_I4: 
            case VT_UI4: {
                return values.ulVal();
            }
            case VT_I8: 
            case VT_UI8: {
                return values.ullVal();
            }
            case VT_BOOL: {
                return values.bVal() != 0;
            }
            case VT_R4: {
                return Float.valueOf(values.fltVal());
            }
            case VT_R8: {
                return values.dblVal();
            }
            case VT_BSTR: {
                return values.bstrVal().getString(Pointer.StringType.BSTR);
            }
            case VT_EMPTY: {
                return null;
            }
        }
        throw new UnsupportedOperationException("Conversion not implemented yet from VARIANT type " + vt2 + " to Java !");
    }

    static void change(VARIANT v2, ValuedEnum<VARENUM> vt2) {
        Pointer<VARIANT> pv2 = Pointer.pointerTo(v2);
        int res = OLEAutomationLibrary.VariantChangeType(pv2, pv2, (short)0, (short)vt2.value());
        if (!$assertionsDisabled && res != 0) {
            throw new AssertionError();
        }
    }

    public static VARIANT setValue(VARIANT v2, Object value) {
        VARIANT.__VARIANT_NAME_1_union.__tagVARIANT.__VARIANT_NAME_3_union values = COMRuntime.getValues(v2);
        if (value == null) {
            COMRuntime.change(v2, VARENUM.VT_EMPTY);
        } else if (value instanceof Integer) {
            COMRuntime.change(v2, VARENUM.VT_I4);
            values.lVal(((Integer)value).intValue());
        } else if (value instanceof Long) {
            COMRuntime.change(v2, VARENUM.VT_I8);
            values.llval((Long)value);
        } else if (value instanceof Short) {
            COMRuntime.change(v2, VARENUM.VT_I2);
            values.iVal((Short)value);
        } else if (value instanceof Byte) {
            COMRuntime.change(v2, VARENUM.VT_I1);
            values.bVal((Byte)value);
        } else if (value instanceof Float) {
            COMRuntime.change(v2, VARENUM.VT_R4);
            values.fltVal(((Float)value).floatValue());
        } else if (value instanceof Double) {
            COMRuntime.change(v2, VARENUM.VT_I8);
            values.dblVal((Double)value);
        } else if (value instanceof Character) {
            COMRuntime.change(v2, VARENUM.VT_I2);
            values.iVal((short)((Character)value).charValue());
        } else if (value instanceof String) {
            COMRuntime.change(v2, VARENUM.VT_BSTR);
            values.bstrVal().setString((String)value, Pointer.StringType.BSTR);
        } else if (value instanceof Pointer) {
            Pointer ptr = (Pointer)value;
            Type targetType = ptr.getTargetType();
            Class targetClass = Utils.getClass(targetType);
            if (targetClass == null) {
                COMRuntime.change(v2, VARENUM.VT_PTR);
            } else {
                VARENUM ve2 = targetClass == Integer.class || targetClass == Integer.TYPE ? VARENUM.VT_I4 : (targetClass == Long.class || targetClass == Long.TYPE ? VARENUM.VT_I8 : (targetClass == Short.class || targetClass == Short.TYPE ? VARENUM.VT_I2 : (targetClass == Byte.class || targetClass == Byte.TYPE ? VARENUM.VT_I1 : (targetClass == Character.class || targetClass == Character.TYPE ? VARENUM.VT_LPWSTR : (targetClass == Boolean.class || targetClass == Boolean.TYPE ? VARENUM.VT_BOOL : (targetClass == Float.class || targetClass == Float.TYPE ? VARENUM.VT_R4 : (targetClass == Double.class || targetClass == Double.TYPE ? VARENUM.VT_R8 : (Pointer.class.isAssignableFrom(targetClass) ? VARENUM.VT_PTR : null))))))));
                COMRuntime.change(v2, FlagSet.fromValues((Enum[])new VARENUM[]{VARENUM.VT_BYREF, ve2}));
            }
        } else {
            throw new UnsupportedOperationException("Unable to convert an object of type " + value.getClass().getName() + " to a COM VARIANT object !");
        }
        return v2;
    }

    public static String toString(VARIANT v2) {
        StringBuilder b2 = new StringBuilder("Variant(value = ");
        try {
            b2.append(COMRuntime.getValue(v2));
        }
        catch (Throwable th2) {
            b2.append("?");
        }
        b2.append(", type = ").append(COMRuntime.getType(v2)).append(")");
        return b2.toString();
    }

    public static VARIANT clone(VARIANT v2) {
        VARIANT c2 = new VARIANT();
        int res = OLEAutomationLibrary.VariantCopy(Pointer.pointerTo(v2), Pointer.pointerTo(c2));
        switch (res) {
            case 0: {
                break;
            }
            case -2147352563: {
                throw new RuntimeException("The variant contains an array that is locked.");
            }
            case -2147352568: {
                throw new RuntimeException("The source and destination have an invalid variant type (usually uninitialized).");
            }
            case -2147024882: {
                throw new RuntimeException("Memory could not be allocated for the copy.");
            }
            case -2147024809: {
                throw new RuntimeException("One of the arguments is invalid.");
            }
            default: {
                throw new RuntimeException("Grave error : unexpected error code for VariantCopy : " + res);
            }
        }
        return c2;
    }

    static {
        boolean bl2 = $assertionsDisabled = !COMRuntime.class.desiredAssertionStatus();
        if (Platform.isWindows()) {
            BridJ.register();
        }
        comInitializer = new ThreadLocal<Object>(){

            @Override
            protected Object initialValue() {
                COMRuntime.error(COMRuntime.CoInitializeEx(0L, 0));
                return new Object(){

                    protected void finalize() throws Throwable {
                        COMRuntime.CoUninitialize();
                    }
                };
            }
        };
    }

    public static interface COINIT {
        public static final int COINIT_APARTMENTTHREADED = 2;
        public static final int COINIT_MULTITHREADED = 0;
        public static final int COINIT_DISABLE_OLE1DDE = 4;
        public static final int COINIT_SPEED_OVER_MEMORY = 8;
    }
}

