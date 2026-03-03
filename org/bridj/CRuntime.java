/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import org.bridj.AbstractBridJRuntime;
import org.bridj.BridJ;
import org.bridj.BridJRuntime;
import org.bridj.Callback;
import org.bridj.CallbackInterface;
import org.bridj.CallbackNativeImplementer;
import org.bridj.DynamicFunction;
import org.bridj.DynamicFunctionFactory;
import org.bridj.JNI;
import org.bridj.MethodCallInfo;
import org.bridj.NativeEntities;
import org.bridj.NativeLibrary;
import org.bridj.NativeObject;
import org.bridj.NativeObjectInterface;
import org.bridj.Platform;
import org.bridj.Pointer;
import org.bridj.PointerIO;
import org.bridj.StructIO;
import org.bridj.StructObject;
import org.bridj.ann.Convention;
import org.bridj.ann.JNIBound;
import org.bridj.ann.Optional;
import org.bridj.demangling.Demangler;
import org.bridj.util.AnnotationUtils;
import org.bridj.util.ConcurrentCache;
import org.bridj.util.Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CRuntime
extends AbstractBridJRuntime {
    static final Set<Type> registeredTypes = new HashSet<Type>();
    final AtomicReference<CallbackNativeImplementer> _callbackNativeImplementer = new AtomicReference();
    static final int defaultObjectSize = Platform.is64Bits() ? 8 : 4;
    public static final String PROPERTY_bridj_c_defaultObjectSize = "bridj.c.defaultObjectSize";
    protected Set<Class> rootCallbackClasses = new HashSet<Class>(Arrays.asList(Callback.class, DynamicFunction.class));

    @Deprecated
    public CRuntime() {
    }

    public CallbackNativeImplementer getCallbackNativeImplementer() {
        CallbackNativeImplementer impl = this._callbackNativeImplementer.get();
        if (impl == null) {
            CallbackNativeImplementer newImpl = new CallbackNativeImplementer(BridJ.getOrphanEntities(), this);
            impl = this._callbackNativeImplementer.compareAndSet(null, newImpl) ? newImpl : this._callbackNativeImplementer.get();
        }
        return impl;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    public static CRuntime getInstance() {
        return BridJ.getRuntimeByRuntimeClass(CRuntime.class);
    }

    @Override
    public <T extends NativeObject> Class<? extends T> getActualInstanceClass(Pointer<T> pInstance, Type officialType) {
        return Utils.getClass(officialType);
    }

    @Override
    public <T extends NativeObject> BridJRuntime.TypeInfo<T> getTypeInfo(Type type) {
        return new CTypeInfo(type);
    }

    @Override
    public void register(Type type) {
        this.register(type, null, null);
    }

    @Override
    public void unregister(Type type) {
        Class typeClass = Utils.getClass(type);
        registeredTypes.remove(typeClass);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    synchronized void register(Type type, NativeLibrary forcedLibrary, MethodCallInfoBuilder methodCallInfoBuilder) {
        Class typeClass = Utils.getClass(type);
        if (!BridJ.getRuntimeClass(typeClass).isInstance(this)) {
            BridJ.register(typeClass);
            return;
        }
        Set<Type> set = registeredTypes;
        synchronized (set) {
            if (!registeredTypes.add(typeClass)) {
                return;
            }
        }
        if (methodCallInfoBuilder == null) {
            methodCallInfoBuilder = new MethodCallInfoBuilder();
        }
        assert (BridJ.info("Registering type " + Utils.toString(type)));
        int typeModifiers = typeClass.getModifiers();
        NativeLibrary typeLibrary = null;
        try {
            typeLibrary = forcedLibrary == null ? this.getNativeLibrary(typeClass) : forcedLibrary;
        }
        catch (Throwable th2) {
            // empty catch block
        }
        ConcurrentCache<NativeEntities, NativeEntities.Builder> builders = new ConcurrentCache<NativeEntities, NativeEntities.Builder>(NativeEntities.Builder.class);
        try {
            HashSet<Method> handledMethods = new HashSet<Method>();
            if (CallbackInterface.class.isAssignableFrom(typeClass)) {
                if (this.rootCallbackClasses.contains(type)) {
                    return;
                }
                if (Modifier.isAbstract(typeModifiers)) {
                    this.getCallbackNativeImplementer().getCallbackImplType((Class)type, forcedLibrary);
                }
            }
            ArrayList<Method> nativeMethods = new ArrayList<Method>();
            for (Method method : typeClass.getDeclaredMethods()) {
                int modifiers = method.getModifiers();
                if (!Modifier.isNative(modifiers) || AnnotationUtils.isAnnotationPresent(JNIBound.class, method, new Annotation[0])) continue;
                nativeMethods.add(method);
            }
            if (nativeMethods.isEmpty()) return;
            try {
                for (Method method : nativeMethods) {
                    if (!handledMethods.add(method)) continue;
                    try {
                        NativeLibrary methodLibrary = forcedLibrary == null ? BridJ.getNativeLibrary(method) : forcedLibrary;
                        NativeEntities nativeEntities = methodLibrary == null ? BridJ.getOrphanEntities() : methodLibrary.getNativeEntities();
                        NativeEntities.Builder builder = builders.get(nativeEntities);
                        this.registerNativeMethod(typeClass, typeLibrary, method, methodLibrary, builder, methodCallInfoBuilder);
                    }
                    catch (Exception ex2) {
                        assert (BridJ.error("Method " + method.toGenericString() + " cannot be mapped : " + ex2, ex2));
                        continue;
                        return;
                    }
                }
            }
            catch (Exception ex3) {
                throw new RuntimeException("Failed to register class " + Utils.toString(type), ex3);
            }
        }
        finally {
            for (Map.Entry e2 : builders.entrySet()) {
                ((NativeEntities)e2.getKey()).addDefinitions(typeClass, e2.getValue());
            }
            this.registerFamily(type, forcedLibrary, methodCallInfoBuilder);
        }
    }

    protected void registerFamily(Type type, NativeLibrary forcedLibrary, MethodCallInfoBuilder methodCallInfoBuilder) {
        Class typeClass = Utils.getClass(type);
        for (Class<?> child : typeClass.getClasses()) {
            this.register(child, forcedLibrary, methodCallInfoBuilder);
        }
        if ((typeClass = typeClass.getSuperclass()) != null && typeClass != Object.class) {
            this.register(typeClass, forcedLibrary, methodCallInfoBuilder);
        }
    }

    protected NativeLibrary getNativeLibrary(Class<?> type) throws IOException {
        return BridJ.getNativeLibrary(type);
    }

    protected boolean isSymbolOptional(Method method) {
        return AnnotationUtils.getInheritableAnnotation(Optional.class, method, new Annotation[0]) != null;
    }

    protected void registerNativeMethod(Class<?> type, NativeLibrary typeLibrary, Method method, NativeLibrary methodLibrary, NativeEntities.Builder builder, MethodCallInfoBuilder methodCallInfoBuilder) throws FileNotFoundException {
        MethodCallInfo mci;
        try {
            mci = methodCallInfoBuilder.apply(method);
            if (mci == null) {
                return;
            }
        }
        catch (Throwable th2) {
            BridJ.error("Unable to register " + method + " : " + th2);
            th2.printStackTrace();
            return;
        }
        if (CallbackInterface.class.isAssignableFrom(type)) {
            if (BridJ.debug) {
                BridJ.info("Registering java -> native callback : " + method);
            }
            builder.addJavaToNativeCallback(mci);
        } else {
            Convention.Style cc2;
            Demangler.Symbol symbol;
            Demangler.Symbol symbol2 = symbol = methodLibrary == null ? null : methodLibrary.getSymbol(method);
            if (symbol == null) {
                if (!this.isSymbolOptional(method)) {
                    BridJ.error("Failed to get address of method " + method);
                }
                return;
            }
            mci.setForwardedPointer(symbol.getAddress());
            if (!mci.hasCallingConvention() && (cc2 = symbol.getInferredCallingConvention()) != null) {
                mci.setCallingConvention(cc2);
            }
            builder.addFunction(mci);
            if (BridJ.debug) {
                BridJ.info("Registering " + method + " as C function " + symbol.getName());
            }
        }
    }

    public <T extends NativeObject> Pointer<T> allocate(Class<T> type, int constructorId, Object ... args) {
        if (CallbackInterface.class.isAssignableFrom(type)) {
            if (constructorId != -1 || args.length != 0) {
                throw new RuntimeException("Callback should have a constructorId == -1 and no constructor args !");
            }
            return null;
        }
        throw new RuntimeException("Cannot allocate instance of type " + type.getName() + " (unhandled NativeObject subclass)");
    }

    public int getDefaultStructSize() {
        String s2 = System.getProperty(PROPERTY_bridj_c_defaultObjectSize);
        if (s2 != null) {
            try {
                return Integer.parseInt(s2);
            }
            catch (Throwable th2) {
                BridJ.error("Invalid value for property bridj.c.defaultObjectSize : '" + s2 + "'");
            }
        }
        return defaultObjectSize;
    }

    public long sizeOf(Type structType, StructIO io2) {
        long size;
        if (io2 == null) {
            io2 = StructIO.getInstance(Utils.getClass(structType), structType);
        }
        if (io2 == null || (size = io2.getStructSize()) <= 0L) {
            return this.getDefaultStructSize();
        }
        return size;
    }

    protected Method getUniqueAbstractCallbackMethod(Class type) {
        Class parent = null;
        while ((parent = type.getSuperclass()) != null && !this.rootCallbackClasses.contains(parent)) {
            type = parent;
        }
        Method method = null;
        for (Method dm2 : type.getDeclaredMethods()) {
            int modifiers = dm2.getModifiers();
            if (!Modifier.isAbstract(modifiers)) continue;
            if (method == null) {
                method = dm2;
                continue;
            }
            throw new RuntimeException("Callback " + type.getName() + " has more than one abstract method (" + dm2 + " and " + method + ")");
        }
        if (method == null) {
            throw new RuntimeException("Type doesn't have any abstract method : " + type.getName() + " (parent = " + parent.getName() + ")");
        }
        return method;
    }

    public <T extends NativeObject> Class<? extends T> getTypeForCast(Type type) {
        Class typeClass = Utils.getClass(type);
        if (CallbackInterface.class.isAssignableFrom(typeClass)) {
            return this.getCallbackNativeImplementer().getCallbackImplType(typeClass, null);
        }
        return typeClass;
    }

    public DynamicFunctionFactory getDynamicFunctionFactory(NativeLibrary library, Convention.Style callingConvention, Type returnType, Type ... parameterTypes) {
        return this.getCallbackNativeImplementer().getDynamicCallback(library, callingConvention, returnType, parameterTypes);
    }

    public static <T> Pointer<T> createCToJavaCallback(MethodCallInfo mci, Type t2) {
        mci.prependCallbackCC();
        final long handle = JNI.createCToJavaCallback(mci);
        long peer = JNI.getActualCToJavaCallback(handle);
        Throwable stackTrace = BridJ.debugPointers ? new RuntimeException().fillInStackTrace() : null;
        return Pointer.pointerToAddress(peer, t2, new Pointer.Releaser(){

            @Override
            public void release(Pointer<?> p2) {
                if (BridJ.debugPointers) {
                    BridJ.info("Freeing callback pointer " + p2 + "\n(Creation trace = \n\t" + Utils.toString(p2.creationTrace).replaceAll("\n", "\n\t") + "\n)", new RuntimeException().fillInStackTrace());
                }
                if (BridJ.debugNeverFree) {
                    return;
                }
                JNI.freeCToJavaCallback(handle);
            }
        });
    }

    protected <T extends CallbackInterface> Pointer<T> registerCallbackInstance(T instance) {
        try {
            Class<?> c2 = instance.getClass();
            MethodCallInfo mci = new MethodCallInfo(this.getUniqueAbstractCallbackMethod(c2));
            mci.setDeclaringClass(c2);
            mci.setJavaCallback(instance);
            return CRuntime.createCToJavaCallback(mci, c2);
        }
        catch (Exception e2) {
            throw new RuntimeException("Failed to register callback instance of type " + instance.getClass().getName(), e2);
        }
    }

    protected void setNativeObjectPeer(NativeObjectInterface instance, Pointer<? extends NativeObjectInterface> peer) {
        ((NativeObject)instance).peer = peer;
    }

    public static class MethodCallInfoBuilder {
        public MethodCallInfo apply(Method method) throws FileNotFoundException {
            return new MethodCallInfo(method);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public class CTypeInfo<T extends NativeObject>
    implements BridJRuntime.TypeInfo<T> {
        protected final Type type;
        protected final Class<T> typeClass;
        protected final StructIO structIO;
        protected final PointerIO<T> pointerIO;
        protected volatile Class<T> castClass;

        public CTypeInfo(Type type) {
            this.type = type;
            this.typeClass = Utils.getClass(type);
            this.structIO = StructIO.getInstance(this.typeClass, this.typeClass);
            if (this.structIO != null) {
                this.structIO.build();
            }
            this.pointerIO = PointerIO.getInstance(this.structIO);
            CRuntime.this.register(this.typeClass);
        }

        @Override
        public long sizeOf() {
            return this.structIO.getStructSize();
        }

        @Override
        public boolean equal(T instance, T other) {
            if (this.structIO != null) {
                if (((StructObject)instance).io != this.structIO) {
                    throw new IllegalArgumentException("This is not this instance's StructIO");
                }
                if (((StructObject)other).io != this.structIO) {
                    return false;
                }
                return this.structIO.equal((StructObject)instance, (StructObject)other);
            }
            return ((NativeObject)instance).peer.equals(((NativeObject)other).peer);
        }

        @Override
        public int compare(T instance, T other) {
            if (this.structIO != null) {
                if (((StructObject)instance).io != this.structIO) {
                    throw new IllegalArgumentException("This is not this instance's StructIO");
                }
                if (((StructObject)other).io != this.structIO) {
                    return 1;
                }
                return this.structIO.compare((StructObject)instance, (StructObject)other);
            }
            return ((NativeObject)instance).peer.compareTo(((NativeObject)other).peer);
        }

        @Override
        public BridJRuntime getRuntime() {
            return CRuntime.this;
        }

        @Override
        public Type getType() {
            return this.type;
        }

        protected Class<T> getCastClass() {
            if (this.castClass == null) {
                this.castClass = CRuntime.this.getTypeForCast(this.typeClass);
            }
            return this.castClass;
        }

        protected T newCastInstance() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            Class<T> cc2 = this.getCastClass();
            try {
                return (T)((NativeObject)cc2.newInstance());
            }
            catch (IllegalAccessException ex2) {
                Constructor<T> constructor = cc2.getConstructor(new Class[0]);
                constructor.setAccessible(true);
                return (T)((NativeObject)constructor.newInstance(new Object[0]));
            }
        }

        @Override
        public T cast(Pointer peer) {
            try {
                T instance = this.newCastInstance();
                this.initialize(instance, peer);
                return instance;
            }
            catch (Exception ex2) {
                throw new RuntimeException("Failed to cast pointer " + peer + " to instance of type " + Utils.toString(this.type), ex2);
            }
        }

        @Override
        public T createReturnInstance() {
            try {
                T instance = this.newCastInstance();
                this.initialize(instance);
                return instance;
            }
            catch (Exception ex2) {
                throw new RuntimeException("Failed to create return instance for type " + Utils.toString(this.type), ex2);
            }
        }

        @Override
        public void writeToNative(T instance) {
            if (instance instanceof StructObject) {
                this.structIO.writeFieldsToNative((StructObject)instance);
            }
        }

        @Override
        public void readFromNative(T instance) {
            if (instance instanceof StructObject) {
                this.structIO.readFieldsFromNative((StructObject)instance);
            }
        }

        @Override
        public void copyNativeObjectToAddress(T instance, Pointer<T> ptr) {
            if (instance instanceof StructObject) {
                ((StructObject)instance).peer.copyBytesTo(ptr, this.structIO.getStructSize());
            }
        }

        @Override
        public String describe(T instance) {
            if (instance instanceof StructObject) {
                return this.structIO.describe((StructObject)instance);
            }
            return instance.toString();
        }

        @Override
        public String describe() {
            if (this.structIO != null) {
                return this.structIO.describe();
            }
            return Utils.toString(this.typeClass);
        }

        @Override
        public void initialize(T instance) {
            if (!BridJ.isCastingNativeObjectInCurrentThread()) {
                if (instance instanceof CallbackInterface) {
                    if (!(instance instanceof DynamicFunction)) {
                        CRuntime.this.setNativeObjectPeer((NativeObjectInterface)instance, (Pointer<? extends NativeObjectInterface>)CRuntime.this.registerCallbackInstance((CallbackInterface)instance));
                    }
                } else {
                    this.initialize(instance, -1, new Object[0]);
                }
                if (instance instanceof StructObject) {
                    this.structIO.readFieldsFromNative((StructObject)instance);
                }
            } else if (instance instanceof StructObject) {
                ((StructObject)instance).io = this.structIO;
            }
        }

        @Override
        public void initialize(T instance, Pointer peer) {
            ((NativeObject)instance).peer = peer;
            if (instance instanceof StructObject) {
                ((StructObject)instance).io = this.structIO;
                this.structIO.readFieldsFromNative((StructObject)instance);
            }
        }

        @Override
        public void initialize(T instance, int constructorId, Object ... args) {
            StructObject s2 = (StructObject)instance;
            if (constructorId < 0) {
                s2.io = this.structIO;
                if (((NativeObject)instance).peer == null) {
                    ((NativeObject)instance).peer = Pointer.allocate(this.pointerIO);
                }
            } else {
                throw new UnsupportedOperationException("TODO implement structs constructors !");
            }
        }

        @Override
        public T clone(T instance) throws CloneNotSupportedException {
            if (instance == null) {
                return null;
            }
            try {
                NativeObject clone = (NativeObject)this.typeClass.newInstance();
                Pointer<T> p2 = Pointer.allocate(this.pointerIO);
                Pointer.pointerTo(instance).copyTo(p2);
                this.initialize(clone, p2);
                return (T)clone;
            }
            catch (Exception ex2) {
                throw new RuntimeException("Failed to clone instance of type " + this.getType());
            }
        }

        @Override
        public void destroy(T instance) {
            if (instance instanceof CallbackInterface) {
                return;
            }
        }
    }
}

