/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bridj.CRuntime;
import org.bridj.CallbackInterface;
import org.bridj.DynamicFunction;
import org.bridj.DynamicFunctionFactory;
import org.bridj.MethodCallInfo;
import org.bridj.NativeEntities;
import org.bridj.NativeLibrary;
import org.bridj.Platform;
import org.bridj.PlatformSupport;
import org.bridj.ann.Convention;
import org.bridj.relocated.org.objectweb.asm.ClassWriter;
import org.bridj.relocated.org.objectweb.asm.Label;
import org.bridj.relocated.org.objectweb.asm.MethodVisitor;
import org.bridj.util.ASMUtils;
import org.bridj.util.ClassDefiner;
import org.bridj.util.JNIUtils;
import org.bridj.util.Pair;
import org.bridj.util.Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class CallbackNativeImplementer
extends ClassLoader
implements ClassDefiner {
    Map<Class<? extends CallbackInterface>, Class<?>> implClasses = new HashMap();
    String implNameSuffix = "_NativeImpl";
    final NativeEntities nativeEntities;
    final CRuntime runtime;
    volatile ClassDefiner classDefiner;
    protected Map<Pair<NativeLibrary, Pair<Convention.Style, List<Type>>>, DynamicFunctionFactory> dynamicCallbacks = new HashMap<Pair<NativeLibrary, Pair<Convention.Style, List<Type>>>, DynamicFunctionFactory>();
    private static volatile long nextDynamicCallbackId = 0L;

    public CallbackNativeImplementer(NativeEntities nativeEntities, CRuntime runtime) {
        super(Platform.getClassLoader());
        this.nativeEntities = nativeEntities;
        this.runtime = runtime;
    }

    public synchronized ClassDefiner getClassDefiner() {
        if (this.classDefiner == null) {
            this.classDefiner = PlatformSupport.getInstance().getClassDefiner(this, this);
        }
        return this.classDefiner;
    }

    public synchronized <T extends CallbackInterface> Class<? extends T> getCallbackImplType(Class<T> callbackType, NativeLibrary forcedLibrary) {
        Class<?> callbackImplType = this.implClasses.get(callbackType);
        if (callbackImplType == null) {
            try {
                String callbackTypeName = callbackType.getName().replace('.', '/');
                String callbackTypeImplName = callbackTypeName.replace('$', '_') + this.implNameSuffix;
                String sourceFile = callbackType.getSimpleName() + this.implNameSuffix + ".java";
                Method callbackMethod = this.runtime.getUniqueAbstractCallbackMethod(callbackType);
                Class<?>[] parameterTypes = callbackMethod.getParameterTypes();
                MethodCallInfo mci = new MethodCallInfo(callbackMethod);
                String methodName = callbackMethod.getName();
                String methodSignature = mci.getJavaSignature();
                byte[] byteArray = this.emitBytes(sourceFile, callbackTypeName, callbackTypeImplName, methodName, methodSignature);
                callbackImplType = this.getClassDefiner().defineClass(callbackTypeImplName.replace('/', '.'), byteArray);
                this.implClasses.put(callbackType, callbackImplType);
                this.runtime.register(callbackImplType, forcedLibrary, null);
            }
            catch (Exception ex2) {
                throw new RuntimeException("Failed to create implementation class for callback type " + callbackType.getName() + " : " + ex2, ex2);
            }
        }
        return callbackImplType;
    }

    private static synchronized long getNextDynamicCallbackId() {
        return nextDynamicCallbackId++;
    }

    public synchronized DynamicFunctionFactory getDynamicCallback(NativeLibrary library, final Convention.Style callingConvention, Type returnType, Type ... paramTypes) {
        ArrayList<Type> list = new ArrayList<Type>(paramTypes.length + 1);
        list.add(returnType);
        list.addAll(Arrays.asList(paramTypes));
        Pair pl = new Pair(callingConvention, list);
        Pair key = new Pair(library, pl);
        DynamicFunctionFactory cb2 = this.dynamicCallbacks.get(key);
        if (cb2 == null) {
            try {
                StringBuilder javaSig = new StringBuilder("(");
                StringBuilder desc = new StringBuilder();
                for (Type paramType : paramTypes) {
                    javaSig.append(JNIUtils.getNativeSignature(Utils.getClass(paramType)));
                    desc.append(ASMUtils.typeDesc(paramType));
                }
                javaSig.append(")").append(JNIUtils.getNativeSignature(Utils.getClass(returnType)));
                desc.append("To").append(ASMUtils.typeDesc(returnType)).append("_").append(CallbackNativeImplementer.getNextDynamicCallbackId());
                String callbackTypeImplName = "org/bridj/dyncallbacks/" + desc;
                String methodName = "apply";
                byte[] byteArray = this.emitBytes("<anonymous>", DynamicFunction.class.getName().replace(".", "/"), callbackTypeImplName, methodName, javaSig.toString());
                Class<?> callbackImplType = this.getClassDefiner().defineClass(callbackTypeImplName.replace('/', '.'), byteArray);
                Class[] paramClasses = new Class[paramTypes.length];
                int n2 = paramTypes.length;
                for (int i2 = 0; i2 < n2; ++i2) {
                    paramClasses[i2] = Utils.getClass(paramTypes[i2]);
                }
                CRuntime.MethodCallInfoBuilder methodCallInfoBuilder = new CRuntime.MethodCallInfoBuilder(){

                    public MethodCallInfo apply(Method method) throws FileNotFoundException {
                        MethodCallInfo mci = super.apply(method);
                        mci.setCallingConvention(callingConvention);
                        return mci;
                    }
                };
                cb2 = new DynamicFunctionFactory(callbackImplType, callbackImplType.getMethod(methodName, paramClasses), methodCallInfoBuilder);
                this.dynamicCallbacks.put(key, cb2);
                this.runtime.register(callbackImplType, null, methodCallInfoBuilder);
            }
            catch (Throwable th2) {
                th2.printStackTrace();
                throw new RuntimeException("Failed to create callback for " + list + " : " + th2, th2);
            }
        }
        return cb2;
    }

    private byte[] emitBytes(String sourceFile, String callbackTypeName, String callbackTypeImplName, String methodName, String methodSignature) {
        ClassWriter cw2 = new ClassWriter(0);
        cw2.visit(49, 33, callbackTypeImplName, null, callbackTypeName, null);
        cw2.visitSource(sourceFile, null);
        MethodVisitor mv2 = cw2.visitMethod(1, "<init>", "()V", null, null);
        mv2.visitCode();
        Label l0 = new Label();
        mv2.visitLabel(l0);
        mv2.visitLineNumber(5, l0);
        mv2.visitVarInsn(25, 0);
        mv2.visitMethodInsn(183, callbackTypeName, "<init>", "()V");
        mv2.visitInsn(177);
        Label l1 = new Label();
        mv2.visitLabel(l1);
        mv2.visitLocalVariable("this", "L" + callbackTypeImplName + ";", null, l0, l1, 0);
        mv2.visitMaxs(1, 1);
        mv2.visitEnd();
        mv2 = cw2.visitMethod(257, methodName, methodSignature, null, null);
        mv2.visitEnd();
        cw2.visitEnd();
        return cw2.toByteArray();
    }

    @Override
    public Class<?> defineClass(String className, byte[] data) throws ClassFormatError {
        return this.defineClass(className, data, 0, data.length);
    }
}

