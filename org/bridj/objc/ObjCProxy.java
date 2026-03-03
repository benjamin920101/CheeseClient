/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.objc;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import org.bridj.BridJ;
import org.bridj.NativeObject;
import org.bridj.Pointer;
import org.bridj.PointerIO;
import org.bridj.objc.NSInvocation;
import org.bridj.objc.NSMethodSignature;
import org.bridj.objc.ObjCJNI;
import org.bridj.objc.ObjCObject;
import org.bridj.objc.ObjectiveCRuntime;
import org.bridj.objc.SEL;
import org.bridj.util.Pair;
import org.bridj.util.Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ObjCProxy
extends ObjCObject {
    final Map<SEL, Pair<NSMethodSignature, Method>> signatures = new HashMap<SEL, Pair<NSMethodSignature, Method>>();
    final Object invocationTarget;
    static final String PROXY_OBJC_CLASS_NAME = "ObjCProxy";

    protected ObjCProxy() {
        super(null);
        this.peer = ObjCJNI.createObjCProxyPeer(this);
        assert (this.getClass() != ObjCProxy.class);
        this.invocationTarget = this;
    }

    public ObjCProxy(Object invocationTarget) {
        super(null);
        this.peer = ObjCJNI.createObjCProxyPeer(this);
        assert (invocationTarget != null);
        this.invocationTarget = invocationTarget;
    }

    public void addProtocol(String name) throws ClassNotFoundException {
        Pointer<? extends ObjCObject> protocol = ObjectiveCRuntime.objc_getProtocol(Pointer.pointerToCString(name));
        if (protocol == null) {
            throw new ClassNotFoundException("Protocol " + name + " not found !");
        }
        Pointer<? extends ObjCObject> cls = ObjectiveCRuntime.getObjCClass(PROXY_OBJC_CLASS_NAME);
        if (!ObjectiveCRuntime.class_addProtocol(cls, protocol)) {
            throw new RuntimeException("Failed to add protocol " + name + " to class " + PROXY_OBJC_CLASS_NAME);
        }
    }

    public Object getInvocationTarget() {
        return this.invocationTarget;
    }

    public Pointer<NSMethodSignature> methodSignatureForSelector(SEL sel) {
        Pair<NSMethodSignature, Method> sig = this.getMethodAndSignature(sel);
        return sig == null ? null : Pointer.pointerTo((NativeObject)sig.getFirst());
    }

    public synchronized Pair<NSMethodSignature, Method> getMethodAndSignature(SEL sel) {
        Pair<NSMethodSignature, Method> sig = this.signatures.get(sel);
        if (sig == null) {
            try {
                sig = this.computeMethodAndSignature(sel);
                if (sig != null) {
                    this.signatures.put(sel, sig);
                }
            }
            catch (Throwable th2) {
                BridJ.error("Failed to compute Objective-C signature for selector " + sel + ": " + th2, th2);
            }
        }
        return sig;
    }

    Pair<NSMethodSignature, Method> computeMethodAndSignature(SEL sel) {
        String name = sel.getName();
        ObjectiveCRuntime rt2 = ObjectiveCRuntime.getInstance();
        for (Method method : this.invocationTarget.getClass().getMethods()) {
            NSMethodSignature ms2;
            long nArgs;
            String msel = rt2.getSelector(method);
            if (!msel.equals(name)) continue;
            String sig = rt2.getMethodSignature(method);
            if (BridJ.debug) {
                BridJ.info("Objective-C signature for method " + method + " = '" + sig + "'");
            }
            if ((nArgs = (ms2 = NSMethodSignature.signatureWithObjCTypes(Pointer.pointerToCString(sig)).get()).numberOfArguments() - 2L) != (long)method.getParameterTypes().length) {
                throw new RuntimeException("Bad method signature (mismatching arg types) : '" + sig + "' for " + method);
            }
            return new Pair<NSMethodSignature, Method>(ms2, method);
        }
        BridJ.error("Missing method for " + sel + " in class " + ObjCProxy.classHierarchyToString(this.getInvocationTarget().getClass()));
        return null;
    }

    static String classHierarchyToString(Class c2) {
        String s2 = Utils.toString(c2);
        Type p2 = c2.getGenericSuperclass();
        while (p2 != null && p2 != Object.class && p2 != ObjCProxy.class) {
            s2 = s2 + " extends " + Utils.toString(p2);
            p2 = Utils.getClass(p2).getGenericSuperclass();
        }
        return s2;
    }

    public synchronized void forwardInvocation(Pointer<NSInvocation> pInvocation) {
        NSInvocation invocation = pInvocation.get();
        SEL sel = invocation.selector();
        Pair<NSMethodSignature, Method> sigMet = this.getMethodAndSignature(sel);
        NSMethodSignature sig = sigMet.getFirst();
        Method method = sigMet.getSecond();
        Type[] paramTypes = method.getGenericParameterTypes();
        int nArgs = paramTypes.length;
        Object[] args = new Object[nArgs];
        for (int i2 = 0; i2 < nArgs; ++i2) {
            Type paramType = paramTypes[i2];
            PointerIO paramIO = PointerIO.getInstance(paramType);
            Pointer pArg = Pointer.allocate(paramIO);
            invocation.getArgument_atIndex(pArg, i2 + 2);
            Object arg2 = pArg.get();
            args[i2] = arg2;
        }
        try {
            method.setAccessible(true);
            Object ret = method.invoke(this.getInvocationTarget(), args);
            Type returnType = method.getGenericReturnType();
            if (returnType == Void.TYPE) {
                assert (ret == null);
            } else {
                PointerIO returnIO = PointerIO.getInstance(returnType);
                Pointer pRet = Pointer.allocate(returnIO);
                pRet.set(ret);
                invocation.setReturnValue(pRet);
            }
        }
        catch (Throwable ex2) {
            throw new RuntimeException("Failed to forward invocation from Objective-C to Java invocation target " + this.getInvocationTarget() + " for method " + method + " : " + ex2, ex2);
        }
    }
}

