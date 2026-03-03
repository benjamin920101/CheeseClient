/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bridj.BridJ;
import org.bridj.JNI;
import org.bridj.MethodCallInfo;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class NativeEntities {
    Map<Class<?>, CBInfo> functions = new HashMap();
    Map<Class<?>, CBInfo> virtualMethods = new HashMap();
    Map<Class<?>, CBInfo> javaToNativeCallbacks = new HashMap();
    Map<Class<?>, CBInfo> objcMethodInfos = new HashMap();

    public void release() {
        if (BridJ.debugNeverFree) {
            return;
        }
        for (CBInfo callbacks : this.functions.values()) {
            JNI.freeCFunctionBindings(callbacks.handle, callbacks.size);
        }
        for (CBInfo callbacks : this.javaToNativeCallbacks.values()) {
            JNI.freeJavaToCCallbacks(callbacks.handle, callbacks.size);
        }
        for (CBInfo callbacks : this.virtualMethods.values()) {
            JNI.freeVirtualMethodBindings(callbacks.handle, callbacks.size);
        }
        for (CBInfo callbacks : this.objcMethodInfos.values()) {
            JNI.freeObjCMethodBindings(callbacks.handle, callbacks.size);
        }
    }

    public void finalize() {
        this.release();
    }

    public void addDefinitions(Class<?> type, Builder builder) {
        block6: {
            try {
                int n2 = builder.functionInfos.size();
                if (n2 != 0) {
                    this.functions.put(type, new CBInfo(JNI.bindJavaMethodsToCFunctions(builder.functionInfos.toArray(new MethodCallInfo[n2])), n2));
                }
                if ((n2 = builder.virtualMethods.size()) != 0) {
                    this.virtualMethods.put(type, new CBInfo(JNI.bindJavaMethodsToVirtualMethods(builder.virtualMethods.toArray(new MethodCallInfo[n2])), n2));
                }
                if ((n2 = builder.javaToNativeCallbacks.size()) != 0) {
                    this.javaToNativeCallbacks.put(type, new CBInfo(JNI.bindJavaToCCallbacks(builder.javaToNativeCallbacks.toArray(new MethodCallInfo[n2])), n2));
                }
                if ((n2 = builder.objcMethodInfos.size()) != 0) {
                    this.objcMethodInfos.put(type, new CBInfo(JNI.bindJavaMethodsToObjCMethods(builder.objcMethodInfos.toArray(new MethodCallInfo[n2])), n2));
                }
            }
            catch (Throwable th2) {
                if ($assertionsDisabled || BridJ.error("Failed to add native definitions for class " + type.getName(), th2)) break block6;
                throw new AssertionError();
            }
        }
    }

    public static class Builder {
        List<MethodCallInfo> functionInfos = new ArrayList<MethodCallInfo>();
        List<MethodCallInfo> virtualMethods = new ArrayList<MethodCallInfo>();
        List<MethodCallInfo> javaToNativeCallbacks = new ArrayList<MethodCallInfo>();
        List<MethodCallInfo> cppMethodInfos = new ArrayList<MethodCallInfo>();
        List<MethodCallInfo> objcMethodInfos = new ArrayList<MethodCallInfo>();

        public void addFunction(MethodCallInfo info) {
            this.functionInfos.add(info);
        }

        public void addVirtualMethod(MethodCallInfo info) {
            this.virtualMethods.add(info);
        }

        public void addJavaToNativeCallback(MethodCallInfo info) {
            this.javaToNativeCallbacks.add(info);
        }

        public void addMethodFunction(MethodCallInfo info) {
            this.cppMethodInfos.add(info);
        }

        public void addObjCMethod(MethodCallInfo info) {
            this.objcMethodInfos.add(info);
        }
    }

    static class CBInfo {
        long handle;
        int size;

        public CBInfo(long handle, int size) {
            this.handle = handle;
            this.size = size;
        }
    }
}

