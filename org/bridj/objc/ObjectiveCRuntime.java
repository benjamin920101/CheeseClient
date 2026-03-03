/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.objc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bridj.BridJ;
import org.bridj.BridJRuntime;
import org.bridj.CLong;
import org.bridj.CRuntime;
import org.bridj.CallbackInterface;
import org.bridj.MethodCallInfo;
import org.bridj.NativeEntities;
import org.bridj.NativeLibrary;
import org.bridj.NativeObject;
import org.bridj.Platform;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Ptr;
import org.bridj.objc.ObjCBlock;
import org.bridj.objc.ObjCClass;
import org.bridj.objc.ObjCJNI;
import org.bridj.objc.ObjCObject;
import org.bridj.objc.SEL;
import org.bridj.objc.Selector;
import org.bridj.util.Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Library(value="/usr/lib/libobjc.A.dylib")
public class ObjectiveCRuntime
extends CRuntime {
    Map<String, Pointer<? extends ObjCObject>> nativeClassesByObjCName = new HashMap<String, Pointer<? extends ObjCObject>>();
    Map<String, Pointer<? extends ObjCObject>> nativeMetaClassesByObjCName = new HashMap<String, Pointer<? extends ObjCObject>>();
    static final Map<Type, Character> signatureByType = new HashMap<Type, Character>();
    static final Map<Character, List<Type>> typesBySignature = new HashMap<Character, List<Type>>();
    Map<String, Class<? extends ObjCObject>> bridjClassesByObjCName = new HashMap<String, Class<? extends ObjCObject>>();
    static final Pointer.Releaser ObjCBlockReleaser;

    @Override
    public boolean isAvailable() {
        return Platform.isMacOSX();
    }

    public ObjectiveCRuntime() {
        BridJ.register();
        this.rootCallbackClasses.add(ObjCBlock.class);
    }

    <T extends ObjCObject> T realCast(Pointer<? extends ObjCObject> id2) {
        if (id2 == null) {
            return null;
        }
        Pointer<Byte> cn2 = ObjectiveCRuntime.object_getClassName(id2);
        if (cn2 == null) {
            throw new RuntimeException("Null class name for this ObjectiveC object pointer !");
        }
        String n2 = cn2.getCString();
        Class<? extends ObjCObject> c2 = this.bridjClassesByObjCName.get(n2);
        if (c2 == null) {
            throw new RuntimeException("Class " + n2 + " was not registered yet in the BridJ runtime ! (TODO : auto create by scanning path, then reflection !)");
        }
        return (T)id2.getNativeObject(c2);
    }

    protected static native Pointer<? extends ObjCObject> object_getClass(Pointer<? extends ObjCObject> var0);

    protected static native Pointer<? extends ObjCObject> objc_getClass(Pointer<Byte> var0);

    protected static native Pointer<? extends ObjCObject> objc_getMetaClass(Pointer<Byte> var0);

    protected static native Pointer<Byte> object_getClassName(Pointer<? extends ObjCObject> var0);

    protected static native Pointer<? extends ObjCObject> class_createInstance(Pointer<? extends ObjCObject> var0, @Ptr long var1);

    public static native Pointer<? extends ObjCObject> objc_getProtocol(Pointer<Byte> var0);

    public static native boolean class_addProtocol(Pointer<? extends ObjCObject> var0, Pointer<? extends ObjCObject> var1);

    protected static native boolean class_respondsToSelector(Pointer<? extends ObjCObject> var0, SEL var1);

    protected static native SEL sel_registerName(Pointer<Byte> var0);

    protected static native Pointer<Byte> sel_getName(SEL var0);

    public String getMethodSignature(Method method) {
        return this.getMethodSignature(method.getGenericReturnType(), method.getGenericParameterTypes());
    }

    public String getMethodSignature(Type returnType, Type ... paramTypes) {
        StringBuilder b2 = new StringBuilder();
        b2.append(this.getTypeSignature(returnType));
        b2.append(this.getTypeSignature((Type)((Object)Pointer.class)));
        b2.append(this.getTypeSignature((Type)((Object)SEL.class)));
        for (Type paramType : paramTypes) {
            b2.append(this.getTypeSignature(paramType));
        }
        return b2.toString();
    }

    char getTypeSignature(Type type) {
        Character c2 = signatureByType.get(type);
        if (c2 == null) {
            c2 = signatureByType.get(Utils.getClass(type));
        }
        if (c2 == null) {
            throw new RuntimeException("Unknown type for Objective-C signatures : " + Utils.toString(type));
        }
        return c2.charValue();
    }

    static void addSignature(char sig, Type ... types) {
        List<Type> typesList = typesBySignature.get(Character.valueOf(sig));
        if (typesList == null) {
            typesList = new ArrayList<Type>();
            typesBySignature.put(Character.valueOf(sig), typesList);
        }
        for (Type type : types) {
            signatureByType.put(type, Character.valueOf(sig));
            if (type == null || typesList.contains(type)) continue;
            typesList.add(type);
        }
    }

    static void initSignatures() {
        boolean is32 = CLong.SIZE == 4;
        ObjectiveCRuntime.addSignature('q', new Type[]{Long.TYPE, !is32 ? CLong.class : null});
        ObjectiveCRuntime.addSignature('i', new Type[]{Integer.TYPE, is32 ? CLong.class : null});
        ObjectiveCRuntime.addSignature('I', new Type[]{Integer.TYPE, is32 ? CLong.class : null});
        ObjectiveCRuntime.addSignature('s', Short.TYPE, Character.TYPE);
        ObjectiveCRuntime.addSignature('c', Byte.TYPE, Boolean.TYPE);
        ObjectiveCRuntime.addSignature('f', Float.TYPE);
        ObjectiveCRuntime.addSignature('d', Double.TYPE);
        ObjectiveCRuntime.addSignature('v', Void.TYPE);
        ObjectiveCRuntime.addSignature('@', new Type[]{Pointer.class});
        ObjectiveCRuntime.addSignature(':', new Type[]{SEL.class});
    }

    synchronized Pointer<? extends ObjCObject> getObjCClass(String name, boolean meta) throws ClassNotFoundException {
        if (name.equals("")) {
            return null;
        }
        Map<String, Pointer<? extends ObjCObject>> map = meta ? this.nativeMetaClassesByObjCName : this.nativeClassesByObjCName;
        Pointer<? extends ObjCObject> c2 = map.get(name);
        if (c2 == null) {
            Pointer<Byte> pName = Pointer.pointerToCString(name);
            Pointer<? extends ObjCObject> pointer = c2 = meta ? ObjectiveCRuntime.objc_getMetaClass(pName) : ObjectiveCRuntime.objc_getClass(pName);
            if (c2 != null) {
                assert (ObjectiveCRuntime.object_getClassName(c2).getCString().equals(name));
                map.put(name, c2);
            }
        }
        if (c2 == null) {
            throw new ClassNotFoundException("Objective C class not found : " + name);
        }
        return c2;
    }

    @Override
    protected NativeLibrary getNativeLibrary(Class<?> type) throws IOException {
        Library libAnn = type.getAnnotation(Library.class);
        if (libAnn != null) {
            try {
                String name = libAnn.value();
                return BridJ.getNativeLibrary(name, new File("/System/Library/Frameworks/" + name + ".framework/" + name));
            }
            catch (FileNotFoundException fileNotFoundException) {
                // empty catch block
            }
        }
        return super.getNativeLibrary(type);
    }

    @Override
    public void register(Type type) {
        Class typeClass = Utils.getClass(type);
        typeClass.getAnnotation(Library.class);
        Library libAnn = typeClass.getAnnotation(Library.class);
        if (libAnn != null) {
            String name = libAnn.value();
            File libraryFile = BridJ.getNativeLibraryFile(name);
            if (libraryFile != null) {
                System.load(libraryFile.toString());
            }
            if (ObjCObject.class.isAssignableFrom(typeClass)) {
                this.bridjClassesByObjCName.put(typeClass.getSimpleName(), typeClass);
            }
        }
        super.register(type);
    }

    public String getSelector(Method method) {
        Selector selAnn = method.getAnnotation(Selector.class);
        if (selAnn != null) {
            return selAnn.value();
        }
        String n2 = method.getName();
        if (n2.endsWith("_")) {
            n2 = n2.substring(0, n2.length() - 1);
        }
        if (method.getParameterTypes().length > 0) {
            n2 = n2 + ":";
        }
        n2 = n2.replace('_', ':');
        return n2;
    }

    @Override
    protected void registerNativeMethod(Class<?> type, NativeLibrary typeLibrary, Method method, NativeLibrary methodLibrary, NativeEntities.Builder builder, CRuntime.MethodCallInfoBuilder methodCallInfoBuilder) throws FileNotFoundException {
        if (method == null) {
            return;
        }
        if (!ObjCObject.class.isAssignableFrom(type) || ObjCBlock.class.isAssignableFrom(type)) {
            super.registerNativeMethod(type, typeLibrary, method, methodLibrary, builder, methodCallInfoBuilder);
            return;
        }
        try {
            MethodCallInfo mci = methodCallInfoBuilder.apply(method);
            boolean isStatic = Modifier.isStatic(method.getModifiers());
            if (isStatic) {
                Pointer<ObjCClass> pObjcClass = this.getObjCClass(type).as(ObjCClass.class);
                ObjCClass objcClass = pObjcClass.get();
                mci.setNativeClass(pObjcClass.getPeer());
            }
            mci.setSymbolName(this.getSelector(method));
            builder.addObjCMethod(mci);
        }
        catch (ClassNotFoundException ex2) {
            throw new RuntimeException("Failed to register method " + method + " : " + ex2, ex2);
        }
    }

    public static ObjectiveCRuntime getInstance() {
        return BridJ.getRuntimeByRuntimeClass(ObjectiveCRuntime.class);
    }

    public static Type getBlockCallbackType(Class blockClass) {
        if (!ObjCBlock.class.isAssignableFrom(blockClass) || ObjCBlock.class == blockClass) {
            throw new RuntimeException("Class " + blockClass.getName() + " should be a subclass of " + ObjCBlock.class.getName());
        }
        Type p2 = blockClass.getGenericSuperclass();
        if (Utils.getClass(p2) == ObjCBlock.class) {
            Type callbackType = Utils.getUniqueParameterizedTypeParameter(p2);
            if (callbackType == null || !(callbackType instanceof Class) && !(callbackType instanceof ParameterizedType)) {
                throw new RuntimeException("Class " + blockClass.getName() + " should inherit from " + ObjCBlock.class.getName() + " with a valid single type parameter (found " + Utils.toString(p2) + ")");
            }
            return callbackType;
        }
        throw new RuntimeException("Unexpected failure in getBlockCallbackType");
    }

    @Override
    public <T extends NativeObject> BridJRuntime.TypeInfo<T> getTypeInfo(Type type) {
        return new CRuntime.CTypeInfo<T>(type){

            @Override
            public void initialize(T instance) {
                if (!BridJ.isCastingNativeObjectInCurrentThread()) {
                    if (instance instanceof ObjCBlock) {
                        Pointer pcb;
                        ((ObjCBlock)instance).pCallback = pcb = ObjectiveCRuntime.this.registerCallbackInstance((CallbackInterface)instance);
                        Pointer pBlock = Pointer.pointerToAddress(ObjCJNI.createObjCBlockWithFunctionPointer(pcb.getPeer()), this.type);
                        pBlock = pBlock.withReleaser(new Pointer.Releaser(){

                            @Override
                            public void release(Pointer<?> p2) {
                                pcb.release();
                                ObjCJNI.releaseObjCBlock(p2.getPeer());
                            }
                        });
                        ObjectiveCRuntime.this.setNativeObjectPeer(instance, pBlock);
                    } else {
                        super.initialize(instance);
                    }
                } else {
                    super.initialize(instance);
                }
            }

            @Override
            public void initialize(T instance, Pointer peer) {
                if (instance instanceof ObjCClass) {
                    ObjectiveCRuntime.this.setNativeObjectPeer(instance, peer);
                } else {
                    super.initialize(instance, peer);
                }
            }

            @Override
            public void initialize(T instance, int constructorId, Object ... args) {
                try {
                    Pointer c2 = ObjectiveCRuntime.this.getObjCClass(this.typeClass);
                    if (c2 == null) {
                        throw new RuntimeException("Failed to get Objective-C class for type " + this.typeClass.getName());
                    }
                    Pointer<ObjCClass> pc2 = c2.as(ObjCClass.class);
                    Pointer p2 = pc2.get().new$();
                    if (constructorId != -1) {
                        throw new UnsupportedOperationException("TODO handle constructors !");
                    }
                    p2 = ((ObjCObject)p2.get()).init();
                    ObjectiveCRuntime.this.setNativeObjectPeer(instance, p2);
                }
                catch (ClassNotFoundException ex2) {
                    throw new RuntimeException("Failed to initialize instance of type " + Utils.toString(this.type) + " : " + ex2, ex2);
                }
            }
        };
    }

    public static Pointer<? extends ObjCObject> getObjCClass(String name) throws ClassNotFoundException {
        return ObjectiveCRuntime.getInstance().getObjCClass(name, false);
    }

    private Pointer<? extends ObjCObject> getObjCClass(Class<? extends NativeObject> cls) throws ClassNotFoundException {
        if (cls == ObjCClass.class) {
            return this.getObjCClass("NSObject", true);
        }
        if (cls == ObjCObject.class) {
            return this.getObjCClass("NSObject", false);
        }
        return this.getObjCClass(cls.getSimpleName(), false);
    }

    static {
        ObjectiveCRuntime.initSignatures();
        ObjCBlockReleaser = new Pointer.Releaser(){

            public void release(Pointer p2) {
                ObjCJNI.releaseObjCBlock(p2.getPeer());
            }
        };
    }
}

