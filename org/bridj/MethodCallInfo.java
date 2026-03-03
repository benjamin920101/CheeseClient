/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.bridj.BridJ;
import org.bridj.CallIO;
import org.bridj.DyncallStructs;
import org.bridj.LastError;
import org.bridj.NativeConstants;
import org.bridj.NativeObject;
import org.bridj.Platform;
import org.bridj.Pointer;
import org.bridj.SizeT;
import org.bridj.StructIO;
import org.bridj.StructObject;
import org.bridj.TimeT;
import org.bridj.ValuedEnum;
import org.bridj.ann.CLong;
import org.bridj.ann.Constructor;
import org.bridj.ann.Convention;
import org.bridj.ann.DisableDirect;
import org.bridj.ann.Ptr;
import org.bridj.ann.Virtual;
import org.bridj.dyncall.DyncallLibrary;
import org.bridj.util.AnnotationUtils;
import org.bridj.util.Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MethodCallInfo {
    List<CallIO> callIOs;
    private Class<?> declaringClass;
    long nativeClass;
    int returnValueType;
    int[] paramsValueTypes;
    Method method;
    String methodName;
    String symbolName;
    private long forwardedPointer;
    String dcSignature;
    String javaSignature;
    String asmSignature;
    Object javaCallback;
    boolean isGenericCallback;
    boolean isObjCBlock;
    int virtualIndex = -1;
    int virtualTableOffset = 0;
    private int dcCallingConvention = 0;
    boolean isVarArgs;
    boolean isStatic;
    boolean isCPlusPlus;
    boolean direct;
    boolean startsWithThis;
    boolean bNeedsThisPointer;
    boolean bThrowLastError;
    boolean hasCC;

    public MethodCallInfo(Method method) {
        this(method, method);
    }

    static boolean derivesFrom(Class<?> c2, String className) {
        while (c2 != null) {
            if (c2.getName().equals(className)) {
                return true;
            }
            c2 = c2.getSuperclass();
        }
        return false;
    }

    public MethodCallInfo(Type genericReturnType, Type[] parameterTypes, boolean prependJNIPointers) {
        this(genericReturnType, new Annotation[0], parameterTypes, new Annotation[parameterTypes.length][], prependJNIPointers);
    }

    public MethodCallInfo(Type genericReturnType, Annotation[] returnAnnotations, Type[] parameterTypes, Annotation[][] paramsAnnotations, boolean prependJNIPointers) {
        this.init(null, Utils.getClass(genericReturnType), genericReturnType, returnAnnotations, Utils.getClasses(parameterTypes), parameterTypes, paramsAnnotations, prependJNIPointers, false, true);
    }

    public MethodCallInfo(Method method, Method definition) {
        List<Class<?>> exceptionTypes;
        this.setMethod(method);
        this.setDeclaringClass(method.getDeclaringClass());
        this.symbolName = this.methodName;
        int modifiers = method.getModifiers();
        this.isStatic = Modifier.isStatic(modifiers);
        this.isVarArgs = method.isVarArgs();
        boolean isNative = Modifier.isNative(modifiers);
        boolean isVirtual = AnnotationUtils.isAnnotationPresent(Virtual.class, definition, new Annotation[0]);
        boolean isDirectModeAllowed = AnnotationUtils.getInheritableAnnotation(DisableDirect.class, definition, new Annotation[0]) == null && BridJ.isDirectModeEnabled();
        this.isCPlusPlus = !this.isStatic && MethodCallInfo.derivesFrom(method.getDeclaringClass(), "org.bridj.cpp.CPPObject");
        this.isObjCBlock = !this.isStatic && MethodCallInfo.derivesFrom(method.getDeclaringClass(), "org.bridj.objc.ObjCBlock");
        this.init(method, method.getReturnType(), method.getGenericReturnType(), method.getAnnotations(), method.getParameterTypes(), method.getGenericParameterTypes(), method.getParameterAnnotations(), isNative, isVirtual, isDirectModeAllowed);
        Convention cc2 = AnnotationUtils.getInheritableAnnotation(Convention.class, definition, new Annotation[0]);
        if (cc2 != null) {
            this.setCallingConvention(cc2.value());
        }
        if (!(exceptionTypes = Arrays.asList(definition.getExceptionTypes())).isEmpty()) {
            this.direct = false;
            if (exceptionTypes.contains(LastError.class)) {
                this.bThrowLastError = true;
            }
        }
    }

    protected void init(AnnotatedElement annotatedElement, Class returnType, Type genericReturnType, Annotation[] returnAnnotations, Class[] parameterTypes, Type[] genericParameterTypes, Annotation[][] paramsAnnotations, boolean prependJNIPointers, boolean isVirtual, boolean isDirectModeAllowed) {
        assert (returnType != null);
        assert (genericReturnType != null);
        assert (parameterTypes != null);
        assert (genericParameterTypes != null);
        assert (returnAnnotations != null);
        assert (parameterTypes.length == genericParameterTypes.length);
        assert (paramsAnnotations.length == genericParameterTypes.length);
        int nParams = genericParameterTypes.length;
        this.paramsValueTypes = new int[nParams];
        this.direct = isDirectModeAllowed;
        StringBuilder javaSig = new StringBuilder(64);
        StringBuilder asmSig = new StringBuilder(64);
        StringBuilder dcSig = new StringBuilder(16);
        javaSig.append('(');
        asmSig.append('(');
        if (prependJNIPointers) {
            dcSig.append('p').append('p');
        }
        if (BridJ.debug) {
            BridJ.info("Analyzing " + (this.declaringClass == null ? "anonymous method" : this.declaringClass.getName() + "." + this.methodName));
        }
        if (this.isObjCBlock) {
            this.appendToSignature(0, NativeConstants.ValueType.ePointerValue, Pointer.class, (Type)((Object)Pointer.class), null, dcSig, null);
        }
        for (int iParam = 0; iParam < nParams; ++iParam) {
            Type genericParameterType = genericParameterTypes[iParam];
            Class parameterType = parameterTypes[iParam];
            NativeConstants.ValueType paramValueType = this.getValueType(iParam, nParams, parameterType, genericParameterType, null, paramsAnnotations[iParam]);
            if (BridJ.veryVerbose) {
                BridJ.info("\tparam " + (Object)((Object)paramValueType));
            }
            this.paramsValueTypes[iParam] = paramValueType.ordinal();
            this.appendToSignature(iParam, paramValueType, parameterType, genericParameterType, javaSig, dcSig, asmSig);
        }
        javaSig.append(')');
        asmSig.append(')');
        dcSig.append(')');
        NativeConstants.ValueType retType = this.getValueType(-1, nParams, returnType, genericReturnType, annotatedElement, returnAnnotations);
        if (BridJ.veryVerbose) {
            BridJ.info("\treturns " + (Object)((Object)retType));
        }
        this.appendToSignature(-1, retType, returnType, genericReturnType, javaSig, dcSig, asmSig);
        this.returnValueType = retType.ordinal();
        this.javaSignature = javaSig.toString();
        this.asmSignature = asmSig.toString();
        this.dcSignature = dcSig.toString();
        boolean bl2 = this.isCPlusPlus = this.isCPlusPlus || isVirtual;
        if (this.isCPlusPlus && !this.isStatic) {
            if (!this.startsWithThis) {
                this.direct = false;
            }
            this.bNeedsThisPointer = true;
            if (Platform.isWindows() && !Platform.is64Bits()) {
                this.setDcCallingConvention(5);
            }
        }
        if (nParams > Platform.getMaxDirectMappingArgCount()) {
            this.direct = false;
        }
        if (BridJ.veryVerbose) {
            BridJ.info("\t-> direct " + this.direct);
            BridJ.info("\t-> javaSignature " + this.javaSignature);
            BridJ.info("\t-> callIOs " + this.callIOs);
            BridJ.info("\t-> asmSignature " + this.asmSignature);
            BridJ.info("\t-> dcSignature " + this.dcSignature);
        }
        if (BridJ.veryVerbose) {
            BridJ.info((this.direct ? "[mappable as direct] " : "[not mappable as direct] ") + this.method);
        }
    }

    public boolean hasCallingConvention() {
        return this.hasCC;
    }

    public void setCallingConvention(Convention.Style style) {
        if (style == null) {
            return;
        }
        if (!Platform.isWindows() || Platform.is64Bits()) {
            return;
        }
        switch (style) {
            case FastCall: {
                this.direct = false;
                this.setDcCallingConvention(Platform.isWindows() ? 3 : 0);
                break;
            }
            case Pascal: 
            case StdCall: {
                this.direct = false;
                this.setDcCallingConvention(2);
                break;
            }
            case ThisCall: {
                this.direct = false;
                this.setDcCallingConvention(Platform.isWindows() ? 5 : 0);
            }
        }
        if (BridJ.veryVerbose) {
            BridJ.info("Setting CC " + (Object)((Object)style) + " (-> " + this.dcCallingConvention + ") for " + this.methodName);
        }
    }

    void addCallIO(CallIO handler) {
        if (this.callIOs == null) {
            this.callIOs = new ArrayList<CallIO>();
        }
        this.callIOs.add(handler);
    }

    public CallIO[] getCallIOs() {
        if (this.callIOs == null) {
            return new CallIO[0];
        }
        return this.callIOs.toArray(new CallIO[this.callIOs.size()]);
    }

    public void prependCallbackCC() {
        char cc2 = MethodCallInfo.getDcCallbackConvention(this.getDcCallingConvention());
        if (cc2 == '\u0000') {
            return;
        }
        this.dcSignature = String.valueOf('_') + String.valueOf(cc2) + this.dcSignature;
    }

    public String getDcSignature() {
        return this.dcSignature;
    }

    public String getJavaSignature() {
        return this.javaSignature;
    }

    public String getASMSignature() {
        return this.asmSignature;
    }

    boolean getBoolAnnotation(Class<? extends Annotation> ac2, AnnotatedElement element, Annotation ... directAnnotations) {
        Annotation ann2 = AnnotationUtils.getAnnotation(ac2, element, directAnnotations);
        return ann2 != null;
    }

    public NativeConstants.ValueType getValueType(int iParam, int nParams, Class<?> c2, Type t2, AnnotatedElement element, Annotation ... directAnnotations) {
        boolean isPtr = AnnotationUtils.isAnnotationPresent(Ptr.class, element, directAnnotations);
        boolean isCLong = AnnotationUtils.isAnnotationPresent(CLong.class, element, directAnnotations);
        Constructor cons = AnnotationUtils.getAnnotation(Constructor.class, element, directAnnotations);
        if (isPtr || cons != null || isCLong) {
            if (c2 != Long.class && c2 != Long.TYPE) {
                throw new RuntimeException("Annotation should only be used on a long parameter, not on a " + c2.getName());
            }
            if (isPtr) {
                if (!Platform.is64Bits()) {
                    this.direct = false;
                }
            } else if (isCLong) {
                if (Platform.CLONG_SIZE != 8) {
                    this.direct = false;
                }
            } else if (cons != null) {
                this.isCPlusPlus = true;
                this.startsWithThis = true;
                if (iParam != 0) {
                    throw new RuntimeException("Annotation " + Constructor.class.getName() + " cannot have more than one (long) argument");
                }
            }
            return NativeConstants.ValueType.eSizeTValue;
        }
        if (c2 == null || c2.equals(Void.TYPE)) {
            return NativeConstants.ValueType.eVoidValue;
        }
        if (c2 == Integer.class || c2 == Integer.TYPE) {
            return NativeConstants.ValueType.eIntValue;
        }
        if (c2 == Long.class || c2 == Long.TYPE) {
            return !isPtr || Platform.is64Bits() ? NativeConstants.ValueType.eLongValue : NativeConstants.ValueType.eIntValue;
        }
        if (c2 == Short.class || c2 == Short.TYPE) {
            return NativeConstants.ValueType.eShortValue;
        }
        if (c2 == Byte.class || c2 == Byte.TYPE) {
            return NativeConstants.ValueType.eByteValue;
        }
        if (c2 == Boolean.class || c2 == Boolean.TYPE) {
            return NativeConstants.ValueType.eBooleanValue;
        }
        if (c2 == Float.class || c2 == Float.TYPE) {
            this.usesFloats();
            return NativeConstants.ValueType.eFloatValue;
        }
        if (c2 == Character.TYPE || c2 == Character.TYPE) {
            if (Platform.WCHAR_T_SIZE != 2) {
                this.direct = false;
            }
            return NativeConstants.ValueType.eWCharValue;
        }
        if (c2 == Double.class || c2 == Double.TYPE) {
            this.usesFloats();
            return NativeConstants.ValueType.eDoubleValue;
        }
        if (c2 == org.bridj.CLong.class) {
            this.direct = false;
            return NativeConstants.ValueType.eCLongObjectValue;
        }
        if (c2 == SizeT.class) {
            this.direct = false;
            return NativeConstants.ValueType.eSizeTObjectValue;
        }
        if (c2 == TimeT.class) {
            this.direct = false;
            return NativeConstants.ValueType.eTimeTObjectValue;
        }
        if (Pointer.class.isAssignableFrom(c2)) {
            this.direct = false;
            CallIO cio = CallIO.Utils.createPointerCallIO(c2, t2);
            if (BridJ.veryVerbose) {
                BridJ.info("CallIO : " + cio);
            }
            this.addCallIO(cio);
            return NativeConstants.ValueType.ePointerValue;
        }
        if (c2.isArray() && iParam == nParams - 1) {
            this.direct = false;
            return NativeConstants.ValueType.eEllipsis;
        }
        if (ValuedEnum.class.isAssignableFrom(c2)) {
            this.direct = false;
            CallIO cio = CallIO.Utils.createValuedEnumCallIO(Utils.getClass(Utils.getUniqueParameterizedTypeParameter(t2)));
            if (BridJ.veryVerbose) {
                BridJ.info("CallIO : " + cio);
            }
            this.addCallIO(cio);
            return NativeConstants.ValueType.eIntFlagSet;
        }
        if (NativeObject.class.isAssignableFrom(c2)) {
            Pointer<DyncallLibrary.DCstruct> pStruct = null;
            if (StructObject.class.isAssignableFrom(c2)) {
                StructIO io2 = StructIO.getInstance(c2, t2);
                try {
                    pStruct = DyncallStructs.buildDCstruct(io2);
                }
                catch (Throwable th2) {
                    BridJ.error("Unable to create low-level struct metadata for " + Utils.toString(t2) + " : won't be able to use it as a by-value function argument.", th2);
                }
            }
            this.addCallIO(new CallIO.NativeObjectHandler(c2, t2, pStruct));
            this.direct = false;
            return NativeConstants.ValueType.eNativeObjectValue;
        }
        throw new NoSuchElementException("No " + NativeConstants.ValueType.class.getSimpleName() + " for class " + c2.getName());
    }

    void usesFloats() {
    }

    public void appendToSignature(int iParam, NativeConstants.ValueType type, Class<?> parameterType, Type genericParameterType, StringBuilder javaSig, StringBuilder dcSig, StringBuilder asmSig) {
        ParameterizedType pt2;
        Type[] ts2;
        String javaChar;
        char dcChar;
        String asmChar = null;
        switch (type) {
            case eVoidValue: {
                dcChar = 'v';
                javaChar = "V";
                break;
            }
            case eIntValue: {
                dcChar = 'i';
                javaChar = "I";
                break;
            }
            case eLongValue: {
                dcChar = 'l';
                javaChar = "J";
                break;
            }
            case eSizeTValue: {
                javaChar = "J";
                if (Platform.SIZE_T_SIZE == 8) {
                    dcChar = 'l';
                    break;
                }
                dcChar = 'i';
                this.direct = false;
                break;
            }
            case eShortValue: {
                dcChar = 's';
                javaChar = "S";
                break;
            }
            case eDoubleValue: {
                dcChar = 'd';
                javaChar = "D";
                break;
            }
            case eFloatValue: {
                dcChar = 'f';
                javaChar = "F";
                break;
            }
            case eByteValue: {
                dcChar = 'c';
                javaChar = "B";
                break;
            }
            case eBooleanValue: {
                dcChar = 'B';
                javaChar = "Z";
                break;
            }
            case eWCharValue: {
                switch (Platform.WCHAR_T_SIZE) {
                    case 1: {
                        dcChar = 'c';
                        this.direct = false;
                        break;
                    }
                    case 2: {
                        dcChar = 's';
                        break;
                    }
                    case 4: {
                        dcChar = 'i';
                        this.direct = false;
                        break;
                    }
                    default: {
                        throw new RuntimeException("Unhandled sizeof(wchar_t) in GetJavaTypeSignature: " + Platform.WCHAR_T_SIZE);
                    }
                }
                javaChar = "C";
                break;
            }
            case eIntFlagSet: {
                dcChar = 'i';
                javaChar = "L" + parameterType.getName().replace('.', '/') + ";";
                this.direct = false;
                break;
            }
            case eCLongObjectValue: {
                dcChar = 'p';
                javaChar = "Lorg/bridj/CLong;";
                this.direct = false;
                break;
            }
            case eSizeTObjectValue: {
                dcChar = 'p';
                javaChar = "Lorg/bridj/SizeT;";
                this.direct = false;
                break;
            }
            case eTimeTObjectValue: {
                dcChar = 'p';
                javaChar = "Lorg/bridj/TimeT;";
                this.direct = false;
                break;
            }
            case ePointerValue: {
                dcChar = 'p';
                javaChar = "L" + parameterType.getName().replace('.', '/') + ";";
                this.direct = false;
                break;
            }
            case eNativeObjectValue: {
                dcChar = 'T';
                javaChar = "L" + parameterType.getName().replace('.', '/') + ";";
                this.direct = false;
                break;
            }
            case eEllipsis: {
                javaChar = "[Ljava/lang/Object;";
                dcChar = '?';
                break;
            }
            default: {
                this.direct = false;
                throw new RuntimeException("Unhandled " + NativeConstants.ValueType.class.getSimpleName() + ": " + (Object)((Object)type));
            }
        }
        if (genericParameterType instanceof ParameterizedType && iParam < 0 && (ts2 = (pt2 = (ParameterizedType)genericParameterType).getActualTypeArguments()) != null && ts2.length == 1) {
            Type t2 = ts2[0];
            if (t2 instanceof ParameterizedType) {
                t2 = ((ParameterizedType)t2).getRawType();
            }
            if (t2 instanceof Class) {
                Class c2 = (Class)t2;
                if (javaChar.endsWith(";")) {
                    asmChar = javaChar.substring(0, javaChar.length() - 1) + "<*L" + c2.getName().replace('.', '/') + ";>";
                }
            }
        }
        if (javaSig != null) {
            javaSig.append(javaChar);
        }
        if (asmChar == null) {
            asmChar = javaChar;
        }
        if (asmSig != null) {
            asmSig.append(asmChar);
        }
        if (dcSig != null) {
            dcSig.append(dcChar);
        }
    }

    public void setMethod(Method method) {
        this.method = method;
        if (method != null) {
            this.methodName = method.getName();
        }
        if (this.declaringClass == null) {
            this.setDeclaringClass(method.getDeclaringClass());
        }
    }

    public void setJavaSignature(String javaSignature) {
        this.javaSignature = javaSignature;
    }

    public Method getMethod() {
        return this.method;
    }

    public void setDeclaringClass(Class<?> declaringClass) {
        this.declaringClass = declaringClass;
    }

    public Class<?> getDeclaringClass() {
        return this.declaringClass;
    }

    public void setForwardedPointer(long forwardedPointer) {
        this.forwardedPointer = forwardedPointer;
    }

    public long getForwardedPointer() {
        return this.forwardedPointer;
    }

    public void setVirtualIndex(int virtualIndex) {
        this.virtualIndex = virtualIndex;
        if (BridJ.veryVerbose) {
            BridJ.info("\t-> virtualIndex " + virtualIndex);
        }
    }

    public int getVirtualIndex() {
        return this.virtualIndex;
    }

    public String getSymbolName() {
        return this.symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    static char getDcCallbackConvention(int dcCallingConvention) {
        switch (dcCallingConvention) {
            case 2: {
                return 's';
            }
            case 3: {
                return 'F';
            }
            case 4: {
                return 'f';
            }
            case 5: {
                return '+';
            }
        }
        return '\u0000';
    }

    public void setDcCallingConvention(int dcCallingConvention) {
        this.hasCC = true;
        this.dcCallingConvention = dcCallingConvention;
    }

    public int getDcCallingConvention() {
        return this.dcCallingConvention;
    }

    public Object getJavaCallback() {
        return this.javaCallback;
    }

    public void setJavaCallback(Object javaCallback) {
        this.javaCallback = javaCallback;
    }

    public void setGenericCallback(boolean genericCallback) {
        this.isGenericCallback = genericCallback;
    }

    public boolean isGenericCallback() {
        return this.isGenericCallback;
    }

    public void setNativeClass(long nativeClass) {
        this.nativeClass = nativeClass;
    }
}

