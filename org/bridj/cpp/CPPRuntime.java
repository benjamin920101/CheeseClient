/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp;

import java.io.FileNotFoundException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.bridj.BridJ;
import org.bridj.BridJRuntime;
import org.bridj.CRuntime;
import org.bridj.Callback;
import org.bridj.DynamicFunction;
import org.bridj.DynamicFunctionFactory;
import org.bridj.MethodCallInfo;
import org.bridj.NativeEntities;
import org.bridj.NativeLibrary;
import org.bridj.NativeObject;
import org.bridj.Platform;
import org.bridj.Pointer;
import org.bridj.PointerIO;
import org.bridj.SizeT;
import org.bridj.ann.Convention;
import org.bridj.ann.Template;
import org.bridj.ann.Virtual;
import org.bridj.cpp.CPPObject;
import org.bridj.cpp.CPPType;
import org.bridj.demangling.Demangler;
import org.bridj.util.Pair;
import org.bridj.util.Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CPPRuntime
extends CRuntime {
    public static final int DEFAULT_CONSTRUCTOR = -1;
    public static final int SKIP_CONSTRUCTOR = -2;
    Map<Class<?>, Integer> virtualMethodsCounts = new HashMap();
    volatile MemoryOperators memoryOperators;
    Set<Type> typesThatDontNeedASyntheticVirtualTable = new HashSet<Type>();
    Map<Type, VTable> syntheticVirtualTables = new HashMap<Type, VTable>();
    Map<Pair<Type, Integer>, DynamicFunction> constructors = new HashMap<Pair<Type, Integer>, DynamicFunction>();
    Map<Type, CPPDestructor> destructors = new HashMap<Type, CPPDestructor>();
    Map<Type, Long> vtables = new HashMap<Type, Long>();

    public static CPPRuntime getInstance() {
        return BridJ.getRuntimeByRuntimeClass(CPPRuntime.class);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Object[] getTemplateParameters(CPPObject object, Class<?> typeClass) {
        CPPObject cPPObject = object;
        synchronized (cPPObject) {
            Object[] params = null;
            if (object.templateParameters != null) {
                params = object.templateParameters.get(typeClass);
            }
            return params;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setTemplateParameters(CPPObject object, Class<?> typeClass, Object[] params) {
        CPPObject cPPObject = object;
        synchronized (cPPObject) {
            if (object.templateParameters == null) {
                object.templateParameters = new HashMap();
            }
            object.templateParameters.put(typeClass, params);
        }
    }

    protected static int getAnnotatedTemplateTypeVariableIndexInArguments(TypeVariable<?> var) {
        Object d2 = var.getGenericDeclaration();
        AnnotatedElement e2 = (AnnotatedElement)d2;
        Template t2 = e2.getAnnotation(Template.class);
        if (t2 == null) {
            throw new RuntimeException(e2 + " is not a C++ class template (misses the @" + Template.class.getName() + " annotation)");
        }
        int iTypeVar = Arrays.asList(d2.getTypeParameters()).indexOf(var);
        int nTypes = 0;
        int iParam = -1;
        Class<?>[] values = t2.value();
        int n2 = values.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            Class<?> c2 = values[i2];
            if (c2 == Class.class || c2 == Type.class) {
                ++nTypes;
            }
            if (nTypes != iTypeVar) continue;
            iParam = i2;
            break;
        }
        if (iParam < 0) {
            throw new RuntimeException("Couldn't find the type variable " + var + " (offset " + iTypeVar + ") in the @" + Template.class.getName() + " annotation : " + Arrays.asList(values));
        }
        return iParam;
    }

    protected ClassTypeVariableExtractor createClassTypeVariableExtractor(TypeVariable<Class<?>> var) {
        final Class<?> typeClass = var.getGenericDeclaration();
        final int iTypeInParams = CPPRuntime.getAnnotatedTemplateTypeVariableIndexInArguments(var);
        return new ClassTypeVariableExtractor(){

            public Type extract(CPPObject instance) {
                typeClass.cast(instance);
                Object[] params = CPPRuntime.this.getTemplateParameters(instance, typeClass);
                if (params == null) {
                    throw new RuntimeException("No type parameters found in this instance : " + instance);
                }
                return (Type)params[iTypeInParams];
            }
        };
    }

    protected MethodTypeVariableExtractor createMethodTypeVariableExtractor(TypeVariable<?> var) {
        Object d2 = var.getGenericDeclaration();
        if (d2 instanceof Class) {
            Class typeClass = (Class)d2;
            final ClassTypeVariableExtractor ce2 = this.createClassTypeVariableExtractor(var);
            return new MethodTypeVariableExtractor(){

                public Type extract(CPPObject instance, Object[] methodTemplateParameters) {
                    return ce2.extract(instance);
                }
            };
        }
        Method method = (Method)d2;
        final Class<?> typeClass = method.getDeclaringClass();
        final int iTypeInParams = CPPRuntime.getAnnotatedTemplateTypeVariableIndexInArguments(var);
        return new MethodTypeVariableExtractor(){

            public Type extract(CPPObject instance, Object[] methodTemplateParameters) {
                typeClass.cast(instance);
                return (Type)methodTemplateParameters[iTypeInParams];
            }
        };
    }

    @Override
    public <T extends NativeObject> Class<? extends T> getActualInstanceClass(Pointer<T> pInstance, Type officialType) {
        return Utils.getClass(officialType);
    }

    public int getVirtualMethodsCount(Class<?> type) {
        Integer count = this.virtualMethodsCounts.get(type);
        if (count == null) {
            ArrayList<VirtMeth> mets = new ArrayList<VirtMeth>();
            this.listVirtualMethods(type, mets);
            count = mets.size();
            this.virtualMethodsCounts.put(type, count);
        }
        return count;
    }

    protected void listVirtualMethods(Class<?> type, List<VirtMeth> out) {
        if (!CPPObject.class.isAssignableFrom(type)) {
            return;
        }
        Class<?> sup = type.getSuperclass();
        if (sup != CPPObject.class) {
            this.listVirtualMethods(sup, out);
        }
        int nParentMethods = out.size();
        TreeMap<Integer, VirtMeth> newVirtuals = new TreeMap<Integer, VirtMeth>();
        block0: for (Method method : type.getDeclaredMethods()) {
            String methodName = method.getName();
            Type[] methodParameterTypes = method.getGenericParameterTypes();
            for (int iParentMethod = 0; iParentMethod < nParentMethods; ++iParentMethod) {
                VirtMeth pvm = out.get(iParentMethod);
                Method parentMethod = pvm.definition;
                if (parentMethod.getDeclaringClass() == type || !parentMethod.getName().equals(methodName) || !CPPRuntime.isOverridenSignature(parentMethod.getGenericParameterTypes(), methodParameterTypes, 0)) continue;
                VirtMeth vm2 = new VirtMeth();
                vm2.definition = pvm.definition;
                vm2.implementation = method;
                out.set(iParentMethod, vm2);
                continue block0;
            }
            Virtual virtual = method.getAnnotation(Virtual.class);
            if (virtual == null) continue;
            VirtMeth vm3 = new VirtMeth();
            vm3.definition = vm3.implementation = method;
            newVirtuals.put(virtual.value(), vm3);
        }
        out.addAll(newVirtuals.values());
    }

    @Override
    protected void registerNativeMethod(Class<?> type, NativeLibrary typeLibrary, Method method, NativeLibrary methodLibrary, NativeEntities.Builder builder, CRuntime.MethodCallInfoBuilder methodCallInfoBuilder) throws FileNotFoundException {
        int modifiers = method.getModifiers();
        boolean isCPPClass = CPPObject.class.isAssignableFrom(method.getDeclaringClass());
        if (!isCPPClass) {
            super.registerNativeMethod(type, typeLibrary, method, methodLibrary, builder, methodCallInfoBuilder);
            return;
        }
        MethodCallInfo mci = methodCallInfoBuilder.apply(method);
        Virtual va2 = method.getAnnotation(Virtual.class);
        if (va2 == null) {
            Demangler.Symbol symbol = methodLibrary.getSymbol(method);
            mci.setForwardedPointer(symbol == null ? 0L : symbol.getAddress());
            if (mci.getForwardedPointer() == 0L) {
                assert (BridJ.error("Method " + method.toGenericString() + " is not virtual but its address could not be resolved in the library."));
                return;
            }
            if (Modifier.isStatic(modifiers)) {
                builder.addFunction(mci);
                if (BridJ.debug) {
                    BridJ.info("Registering " + method + " as function or static C++ method " + symbol.getName());
                }
            } else {
                builder.addFunction(mci);
                if (BridJ.debug) {
                    BridJ.info("Registering " + method + " as C++ method " + symbol.getName());
                }
            }
        } else {
            int absoluteVirtualIndex;
            Pointer<Pointer<?>> pVirtualTable;
            int theoreticalVirtualIndex;
            if (Modifier.isStatic(modifiers)) {
                BridJ.warning("Method " + method.toGenericString() + " is native and maps to a function, but is not static.");
            }
            int theoreticalAbsoluteVirtualIndex = (theoreticalVirtualIndex = va2.value()) < 0 ? -1 : this.getAbsoluteVirtualIndex(method, theoreticalVirtualIndex, type);
            Pointer<Pointer<?>> pointer = pVirtualTable = isCPPClass && typeLibrary != null ? Pointer.pointerToAddress(this.getVirtualTable(type, typeLibrary), Pointer.class) : null;
            if (pVirtualTable == null) {
                if (theoreticalAbsoluteVirtualIndex < 0) {
                    BridJ.error("Method " + method.toGenericString() + " is virtual but the virtual table of class " + type.getName() + " was not found and the virtual method index is not provided in its @Virtual annotation.");
                    return;
                }
                absoluteVirtualIndex = theoreticalAbsoluteVirtualIndex;
            } else {
                int guessedAbsoluteVirtualIndex = this.getPositionInVirtualTable(pVirtualTable, method, typeLibrary);
                if (guessedAbsoluteVirtualIndex < 0) {
                    if (theoreticalAbsoluteVirtualIndex < 0) {
                        BridJ.error("Method " + method.toGenericString() + " is virtual but its position could not be found in the virtual table.");
                        return;
                    }
                    absoluteVirtualIndex = theoreticalAbsoluteVirtualIndex;
                } else {
                    if (theoreticalAbsoluteVirtualIndex >= 0 && guessedAbsoluteVirtualIndex != theoreticalAbsoluteVirtualIndex) {
                        BridJ.warning("Method " + method.toGenericString() + " has @Virtual annotation indicating virtual index " + theoreticalAbsoluteVirtualIndex + ", but analysis of the actual virtual table rather indicates it has index " + guessedAbsoluteVirtualIndex + " (using the guess)");
                    }
                    absoluteVirtualIndex = guessedAbsoluteVirtualIndex;
                }
            }
            mci.setVirtualIndex(absoluteVirtualIndex);
            if (BridJ.debug) {
                BridJ.info("Registering " + method.toGenericString() + " as virtual C++ method with absolute virtual table index = " + absoluteVirtualIndex);
            }
            builder.addVirtualMethod(mci);
        }
    }

    int getAbsoluteVirtualIndex(Method method, int virtualIndex, Class<?> type) {
        Class<?> superclass = type.getSuperclass();
        int virtualOffset = this.getVirtualMethodsCount(superclass);
        boolean isNewVirtual = true;
        if (superclass != null) {
            try {
                superclass.getMethod(method.getName(), method.getParameterTypes());
                isNewVirtual = false;
            }
            catch (NoSuchMethodException ex2) {
                // empty catch block
            }
        }
        int absoluteVirtualIndex = isNewVirtual ? virtualOffset + virtualIndex : virtualIndex;
        return absoluteVirtualIndex;
    }

    public synchronized MemoryOperators getMemoryOperators() {
        if (this.memoryOperators == null) {
            try {
                NativeLibrary libStdCpp = BridJ.getNativeLibrary("stdc++");
                this.memoryOperators = new MemoryOperators(libStdCpp);
            }
            catch (Exception ex2) {
                BridJ.error(null, ex2);
            }
        }
        return this.memoryOperators;
    }

    int getPositionInVirtualTable(Method method, NativeLibrary library) {
        Class<?> type = method.getDeclaringClass();
        Pointer<Pointer<?>> pVirtualTable = Pointer.pointerToAddress(this.getVirtualTable(type, library), Pointer.class);
        return this.getPositionInVirtualTable(pVirtualTable, method, library);
    }

    String getCPPClassName(Class<?> declaringClass) {
        return declaringClass.getSimpleName();
    }

    public int getPositionInVirtualTable(Pointer<Pointer<?>> pVirtualTable, Method method, NativeLibrary library) {
        int methodsOffset = 0;
        String className = this.getCPPClassName(method.getDeclaringClass());
        int iVirtual = 0;
        while (true) {
            Pointer<?> pMethod;
            String virtualMethodName;
            String string = virtualMethodName = (pMethod = pVirtualTable.get(methodsOffset + iVirtual)) == null ? null : library.getSymbolName(pMethod.getPeer());
            if (virtualMethodName == null) {
                if (BridJ.debug) {
                    BridJ.info("\tVtable(" + className + ")[" + iVirtual + "] = null");
                }
                return -1;
            }
            try {
                Demangler.MemberRef mr2 = library.parseSymbol(virtualMethodName);
                if (BridJ.debug) {
                    BridJ.info("\tVtable(" + className + ")[" + iVirtual + "] = " + virtualMethodName + " = " + mr2);
                }
                if (mr2 != null && mr2.matchesSignature(method)) {
                    return iVirtual;
                }
                if (library.isMSVC() && !mr2.matchesEnclosingType(method)) {
                    break;
                }
            }
            catch (Demangler.DemanglingException ex2) {
                BridJ.warning("Failed to demangle '" + virtualMethodName + "' during inspection of virtual table for '" + method.toGenericString() + "' : " + ex2);
            }
            ++iVirtual;
        }
        return -1;
    }

    static int getDefaultDyncallCppConvention() {
        int convention = 0;
        if (!Platform.is64Bits() && Platform.isWindows()) {
            convention = 5;
        }
        return convention;
    }

    private String ptrToString(Pointer<?> ptr, NativeLibrary library) {
        return ptr == null ? "null" : Long.toHexString(ptr.getPeer()) + " (" + library.getSymbolName(ptr.getPeer()) + ")";
    }

    protected boolean installRegularVTablePtr(Type type, NativeLibrary library, Pointer<?> peer) {
        long vtablePtr = this.getVirtualTable(type, library);
        if (vtablePtr != 0L) {
            if (BridJ.debug) {
                BridJ.info("Installing regular vtable pointer " + Pointer.pointerToAddress(vtablePtr) + " to instance at " + peer + " (type = " + Utils.toString(type) + ")");
            }
            peer.setSizeT(vtablePtr);
            return true;
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected boolean installSyntheticVTablePtr(Type type, NativeLibrary library, Pointer<?> peer) {
        Map<Type, VTable> map = this.syntheticVirtualTables;
        synchronized (map) {
            VTable vtable = this.syntheticVirtualTables.get(type);
            if (vtable == null && !this.typesThatDontNeedASyntheticVirtualTable.contains(type)) {
                ArrayList<VirtMeth> methods = new ArrayList<VirtMeth>();
                this.listVirtualMethods(Utils.getClass(type), methods);
                boolean needsASyntheticVirtualTable = false;
                for (VirtMeth method : methods) {
                    if (Modifier.isNative(method.implementation.getModifiers())) continue;
                    needsASyntheticVirtualTable = true;
                    break;
                }
                if (needsASyntheticVirtualTable) {
                    Type parentType = Utils.getParent(type);
                    Pointer<Pointer> parentVTablePtr = null;
                    if (CPPObject.class.isAssignableFrom(Utils.getClass(parentType))) {
                        parentVTablePtr = peer.getPointer(Pointer.class);
                        if (BridJ.debug) {
                            BridJ.info("Found parent virtual table pointer = " + this.ptrToString(parentVTablePtr, library));
                        }
                    }
                    vtable = this.synthetizeVirtualTable(type, parentVTablePtr, methods, library);
                    this.syntheticVirtualTables.put(type, vtable);
                } else {
                    this.typesThatDontNeedASyntheticVirtualTable.add(type);
                }
            }
            if (vtable != null) {
                if (BridJ.debug) {
                    BridJ.info("Installing synthetic vtable pointer " + vtable.ptr + " to instance at " + peer + " (type = " + Utils.toString(type) + ", " + vtable.callbacks.size() + " callbacks)");
                }
                peer.setPointer(vtable.ptr);
                return vtable.ptr != null;
            }
            return false;
        }
    }

    protected VTable synthetizeVirtualTable(Type type, Pointer<Pointer> parentVTablePtr, List<VirtMeth> methods, NativeLibrary library) {
        int nMethods = methods.size();
        VTable vtable = new VTable();
        vtable.ptr = Pointer.allocatePointers(nMethods + 2).next(2L);
        Class c2 = Utils.getClass(type);
        for (int iMethod = 0; iMethod < nMethods; ++iMethod) {
            Pointer pMethod;
            VirtMeth vm2 = methods.get(iMethod);
            if (Modifier.isNative(vm2.implementation.getModifiers())) {
                pMethod = parentVTablePtr == null ? null : parentVTablePtr.get(iMethod);
            } else {
                try {
                    MethodCallInfo mci = new MethodCallInfo(vm2.implementation, vm2.definition);
                    mci.setDeclaringClass(vm2.implementation.getDeclaringClass());
                    pMethod = CPPRuntime.createCToJavaCallback(mci, c2);
                    vtable.callbacks.put(vm2.implementation, pMethod);
                }
                catch (Throwable th2) {
                    BridJ.error("Failed to register overridden method " + vm2.implementation + " for type " + type + " (original method = " + vm2.definition + ")", th2);
                    pMethod = null;
                }
            }
            vtable.ptr.set(iMethod, pMethod);
        }
        return vtable;
    }

    static int getTemplateParametersCount(Class<?> typeClass) {
        Template t2 = typeClass.getAnnotation(Template.class);
        int templateParametersCount = t2 == null ? 0 : t2.value().length;
        return templateParametersCount;
    }

    DynamicFunction getConstructor(Class<?> typeClass, final Type type, NativeLibrary lib, int constructorId) {
        Pair<Type, Integer> key = new Pair<Type, Integer>(type, constructorId);
        DynamicFunction constructor = this.constructors.get(key);
        if (constructor == null) {
            try {
                Demangler.Symbol symbol;
                Constructor constr;
                try {
                    constr = this.findConstructor(typeClass, constructorId, true);
                    if (BridJ.debug) {
                        BridJ.info("Found constructor for " + Utils.toString(type) + " : " + constr);
                    }
                }
                catch (NoSuchMethodException ex2) {
                    if (BridJ.debug) {
                        BridJ.info("No constructor for " + Utils.toString(type));
                    }
                    return null;
                }
                Demangler.Symbol symbol2 = symbol = lib == null ? null : lib.getFirstMatchingSymbol(new NativeLibrary.SymbolAccepter(){

                    public boolean accept(Demangler.Symbol symbol) {
                        return symbol.matchesConstructor(constr.getDeclaringClass() == Utils.getClass(type) ? type : constr.getDeclaringClass(), constr);
                    }
                });
                if (symbol == null) {
                    if (BridJ.debug) {
                        BridJ.info("No matching constructor for " + Utils.toString(type) + " (" + constr + ")");
                    }
                    return null;
                }
                if (BridJ.debug) {
                    BridJ.info("Registering constructor " + constr + " as " + symbol.getName());
                }
                int templateParametersCount = CPPRuntime.getTemplateParametersCount(typeClass);
                Class<?>[] consParamTypes = constr.getParameterTypes();
                Type[] consThisParamTypes = new Class[consParamTypes.length + 1 - templateParametersCount];
                consThisParamTypes[0] = Pointer.class;
                System.arraycopy(consParamTypes, templateParametersCount, consThisParamTypes, 1, consParamTypes.length - templateParametersCount);
                DynamicFunctionFactory constructorFactory = this.getDynamicFunctionFactory(lib, Convention.Style.ThisCall, Void.TYPE, consThisParamTypes);
                constructor = constructorFactory.newInstance(Pointer.pointerToAddress(symbol.getAddress()));
                this.constructors.put(key, constructor);
            }
            catch (Throwable th2) {
                th2.printStackTrace();
                throw new RuntimeException("Unable to create constructor " + constructorId + " for " + type + " : " + th2, th2);
            }
        }
        return constructor;
    }

    CPPDestructor getDestructor(final Class<?> typeClass, Type type, NativeLibrary lib) {
        CPPDestructor destructor = this.destructors.get(type);
        if (destructor == null) {
            Demangler.Symbol symbol = lib.getFirstMatchingSymbol(new NativeLibrary.SymbolAccepter(){

                public boolean accept(Demangler.Symbol symbol) {
                    return symbol.matchesDestructor(typeClass);
                }
            });
            if (BridJ.debug && symbol != null) {
                BridJ.info("Registering destructor of " + Utils.toString(type) + " as " + symbol.getName());
            }
            if (symbol != null) {
                destructor = Pointer.pointerToAddress(symbol.getAddress(), CPPDestructor.class).get();
                this.destructors.put(type, destructor);
            }
        }
        return destructor;
    }

    Pointer.Releaser newCPPReleaser(Type type) {
        try {
            Class typeClass = Utils.getClass(type);
            NativeLibrary lib = BridJ.getNativeLibrary(typeClass);
            return this.newCPPReleaser(type, typeClass, lib);
        }
        catch (Throwable th2) {
            throw new RuntimeException("Failed to create a C++ destructor for type " + Utils.toString(type) + " : " + th2, th2);
        }
    }

    Pointer.Releaser newCPPReleaser(final Type type, Class<?> typeClass, NativeLibrary lib) throws FileNotFoundException {
        CPPDestructor destructor;
        Pointer.Releaser releaser = null;
        if (lib != null && BridJ.enableDestructors && (destructor = this.getDestructor(typeClass, type, lib)) != null) {
            releaser = new Pointer.Releaser(){

                @Override
                public void release(Pointer<?> p2) {
                    if (BridJ.debug) {
                        BridJ.info("Destructing instance of C++ type " + Utils.toString(type) + " (address = " + p2 + ", destructor = " + Pointer.pointerTo(destructor) + ")");
                    }
                    long peer = p2.getPeer();
                    destructor.destroy(peer);
                    BridJ.setJavaObjectFromNativePeer(peer, null);
                }
            };
        }
        return releaser;
    }

    protected <T extends CPPObject> Pointer<T> newCPPInstance(T instance, Type type, int constructorId, Object ... args) {
        Pointer peer = null;
        try {
            int templateParametersCount;
            DynamicFunction constructor;
            Class typeClass = Utils.getClass(type);
            NativeLibrary lib = BridJ.getNativeLibrary(typeClass);
            if (BridJ.debug) {
                BridJ.info("Creating C++ instance of type " + type + " with args " + Arrays.asList(args));
            }
            Pointer.Releaser releaser = this.newCPPReleaser(type, typeClass, lib);
            long size = this.sizeOf(type, null);
            peer = Pointer.allocateBytes(PointerIO.getInstance(type), size, releaser).as(type);
            DynamicFunction dynamicFunction = constructor = constructorId == -2 ? null : this.getConstructor(typeClass, type, lib, constructorId);
            if (lib != null && CPPObject.class.isAssignableFrom(typeClass)) {
                this.installRegularVTablePtr(type, lib, peer);
            }
            if ((templateParametersCount = CPPRuntime.getTemplateParametersCount(typeClass)) > 0) {
                Object[] templateArgs = new Object[templateParametersCount];
                System.arraycopy(args, 0, templateArgs, 0, templateParametersCount);
                this.setTemplateParameters(instance, typeClass, templateArgs);
            }
            if (constructor != null) {
                Object[] consThisArgs = new Object[args.length - templateParametersCount + 1];
                consThisArgs[0] = peer;
                System.arraycopy(args, templateParametersCount, consThisArgs, 1, args.length - templateParametersCount);
                constructor.apply(consThisArgs);
            }
            if (CPPObject.class.isAssignableFrom(typeClass) && this.installSyntheticVTablePtr(type, lib, peer)) {
                BridJ.setJavaObjectFromNativePeer(peer.getPeer(), instance);
            }
            return peer;
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            if (peer != null) {
                peer.release();
            }
            throw new RuntimeException("Failed to allocate new instance of type " + type, ex2);
        }
    }

    long getVirtualTable(Type type, NativeLibrary library) {
        Long vtable = this.vtables.get(type);
        if (vtable == null) {
            final Class typeClass = Utils.getClass(type);
            Demangler.Symbol symbol = library.getFirstMatchingSymbol(new NativeLibrary.SymbolAccepter(){

                public boolean accept(Demangler.Symbol symbol) {
                    return symbol.matchesVirtualTable(typeClass);
                }
            });
            if (symbol != null) {
                if (BridJ.debug) {
                    BridJ.info("Registering vtable of " + Utils.toString(type) + " as " + symbol.getName());
                }
            } else if (this.getVirtualMethodsCount(typeClass) > 0) {
                BridJ.error("Failed to find a vtable for type " + Utils.toString(type));
            }
            if (symbol != null) {
                long address = symbol.getAddress();
                vtable = library.isMSVC() ? address : address + (long)(2 * Pointer.SIZE);
            } else {
                vtable = 0L;
            }
            this.vtables.put(type, vtable);
        }
        return vtable;
    }

    @Override
    public <T extends NativeObject> BridJRuntime.TypeInfo<T> getTypeInfo(Type type) {
        return new CPPTypeInfo(type);
    }

    public <T extends CPPObject> CPPTypeInfo<T> getCPPTypeInfo(Type type) {
        return (CPPTypeInfo)this.getTypeInfo(type);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public class CPPTypeInfo<T extends CPPObject>
    extends CRuntime.CTypeInfo<T> {
        Map<TypeVariable<Class<?>>, ClassTypeVariableExtractor> classTypeVariableExtractors;
        Map<TypeVariable<?>, MethodTypeVariableExtractor> methodTypeVariableExtractors;

        public CPPTypeInfo(Type type) {
            super(type);
        }

        public Type resolveClassType(CPPObject instance, TypeVariable<?> var) {
            return this.getClassTypeVariableExtractor(var).extract(instance);
        }

        public Type resolveMethodType(CPPObject instance, Object[] methodTemplateParameters, TypeVariable<?> var) {
            return this.getMethodTypeVariableExtractor(var).extract(instance, methodTemplateParameters);
        }

        protected synchronized ClassTypeVariableExtractor getClassTypeVariableExtractor(TypeVariable<Class<?>> var) {
            ClassTypeVariableExtractor e2;
            if (this.classTypeVariableExtractors == null) {
                this.classTypeVariableExtractors = new HashMap();
            }
            if ((e2 = this.classTypeVariableExtractors.get(var)) == null) {
                e2 = CPPRuntime.this.createClassTypeVariableExtractor(var);
                this.classTypeVariableExtractors.put(var, e2);
            }
            return e2;
        }

        protected synchronized MethodTypeVariableExtractor getMethodTypeVariableExtractor(TypeVariable<?> var) {
            MethodTypeVariableExtractor e2;
            if (this.methodTypeVariableExtractors == null) {
                this.methodTypeVariableExtractors = new HashMap();
            }
            if ((e2 = this.methodTypeVariableExtractors.get(var)) == null) {
                e2 = CPPRuntime.this.createMethodTypeVariableExtractor(var);
                this.methodTypeVariableExtractors.put(var, e2);
            }
            return e2;
        }

        @Override
        public long sizeOf() {
            return super.sizeOf();
        }

        @Override
        public T createReturnInstance() {
            try {
                Object[] templateParameters = this.getTemplateParameters(this.type);
                CPPObject instance = (CPPObject)this.getCastClass().newInstance();
                this.initialize((T)instance, -2, templateParameters);
                return (T)instance;
            }
            catch (Throwable th2) {
                throw new RuntimeException("Failed to create a return instance for type " + Utils.toString(this.type) + " : " + th2, th2);
            }
        }

        @Override
        public T cast(Pointer peer) {
            if (BridJ.isCastingNativeObjectReturnTypeInCurrentThread()) {
                peer = peer.withReleaser(CPPRuntime.this.newCPPReleaser(this.type));
            }
            CPPObject instance = (CPPObject)super.cast(peer);
            Object[] templateParameters = this.getTemplateParameters(this.type);
            CPPRuntime.this.setTemplateParameters(instance, this.typeClass, templateParameters);
            return (T)instance;
        }

        @Override
        public void initialize(T instance, int constructorId, Object ... args) {
            if (instance instanceof CPPObject) {
                int[] position = new int[]{0};
                Type cppType = CPPType.parseCPPType(CPPType.cons(this.typeClass, args), position);
                CPPRuntime.this.setNativeObjectPeer(instance, CPPRuntime.this.newCPPInstance(instance, cppType, constructorId, args));
                super.initialize(instance, -1, new Object[0]);
            } else {
                super.initialize(instance, constructorId, args);
            }
        }

        @Override
        public T clone(T instance) throws CloneNotSupportedException {
            if (instance instanceof CPPObject) {
                // empty if block
            }
            return (T)((CPPObject)super.clone(instance));
        }

        @Override
        public void destroy(T instance) {
        }

        private Object[] getTemplateParameters(Type type) {
            if (!(type instanceof CPPType)) {
                return null;
            }
            return ((CPPType)type).getTemplateParameters();
        }
    }

    static class VTable {
        Pointer<Pointer<?>> ptr;
        Map<Method, Pointer<?>> callbacks = new HashMap();

        VTable() {
        }
    }

    @Convention(value=Convention.Style.ThisCall)
    public static abstract class CPPDestructor
    extends Callback {
        public abstract void destroy(long var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class MemoryOperators {
        protected DynamicFunction<Pointer<?>> newFct;
        protected DynamicFunction<Pointer<?>> newArrayFct;
        protected DynamicFunction<Void> deleteFct;
        protected DynamicFunction<Void> deleteArrayFct;

        protected MemoryOperators() {
        }

        public MemoryOperators(NativeLibrary library) {
            for (Demangler.Symbol sym : library.getSymbols()) {
                try {
                    Demangler.MemberRef parsedRef = sym.getParsedRef();
                    Demangler.IdentLike n2 = parsedRef.getMemberName();
                    if (Demangler.SpecialName.New.equals(n2)) {
                        this.newFct = Pointer.pointerToAddress(sym.getAddress()).asDynamicFunction(null, (Type)((Object)Pointer.class), new Type[]{SizeT.class});
                        continue;
                    }
                    if (Demangler.SpecialName.NewArray.equals(n2)) {
                        this.newFct = Pointer.pointerToAddress(sym.getAddress()).asDynamicFunction(null, (Type)((Object)Pointer.class), new Type[]{SizeT.class});
                        continue;
                    }
                    if (Demangler.SpecialName.Delete.equals(n2)) {
                        this.newFct = Pointer.pointerToAddress(sym.getAddress()).asDynamicFunction(null, (Type)((Object)Void.class), new Type[]{Pointer.class});
                        continue;
                    }
                    if (!Demangler.SpecialName.DeleteArray.equals(n2)) continue;
                    this.newFct = Pointer.pointerToAddress(sym.getAddress()).asDynamicFunction(null, (Type)((Object)Void.class), new Type[]{Pointer.class});
                }
                catch (Exception ex2) {}
            }
        }

        public Pointer<?> cppNew(long size) {
            return this.newFct.apply(new SizeT(size));
        }

        public Pointer<?> cppNewArray(long size) {
            return this.newArrayFct.apply(new SizeT(size));
        }

        public void cppDelete(Pointer<?> ptr) {
            this.deleteFct.apply(ptr);
        }

        public void cppDeleteArray(Pointer<?> ptr) {
            this.deleteArrayFct.apply(ptr);
        }
    }

    protected static class VirtMeth {
        Method implementation;
        Method definition;

        protected VirtMeth() {
        }
    }

    protected static interface MethodTypeVariableExtractor {
        public Type extract(CPPObject var1, Object[] var2);
    }

    protected static interface ClassTypeVariableExtractor {
        public Type extract(CPPObject var1);
    }
}

