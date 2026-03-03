/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import org.bridj.FlagSet;
import org.bridj.NativeObject;
import org.bridj.Pointer;
import org.bridj.PointerIO;
import org.bridj.TypedPointer;
import org.bridj.dyncall.DyncallLibrary;

interface CallIO {
    public Object newInstance(long var1);

    public void checkArg(Object var1);

    public long getDCStruct();

    public long getPeer(Object var1);

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class GenericPointerHandler
    implements CallIO {
        Type targetType;
        PointerIO pointerIO;

        public GenericPointerHandler(Type targetType) {
            this.targetType = targetType;
            this.pointerIO = PointerIO.getInstance(targetType);
        }

        @Override
        public Pointer<?> newInstance(long address) {
            return Pointer.pointerToAddress(address, this.pointerIO);
        }

        @Override
        public void checkArg(Object ptr) {
        }

        @Override
        public long getDCStruct() {
            return 0L;
        }

        @Override
        public long getPeer(Object o2) {
            return Pointer.getPeer((Pointer)o2);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class NativeObjectHandler
    implements CallIO {
        final Class<? extends NativeObject> nativeClass;
        final Type nativeType;
        final Pointer<DyncallLibrary.DCstruct> pStruct;

        public NativeObjectHandler(Class<? extends NativeObject> type, Type t2, Pointer<DyncallLibrary.DCstruct> pStruct) {
            this.nativeClass = type;
            this.nativeType = t2;
            this.pStruct = pStruct;
        }

        @Override
        public NativeObject newInstance(long address) {
            return Pointer.pointerToAddress(address).getNativeObject(this.nativeClass);
        }

        @Override
        public void checkArg(Object ptr) {
            if (ptr == null) {
                throw new IllegalArgumentException("Native object of type " + this.nativeClass.getName() + " passed by value cannot be given a null value !");
            }
        }

        @Override
        public long getDCStruct() {
            return Pointer.getPeer(this.pStruct);
        }

        @Override
        public long getPeer(Object o2) {
            return Pointer.getAddress((NativeObject)o2, this.nativeClass);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class TypedPointerIO
    implements CallIO {
        Class<? extends TypedPointer> type;
        Constructor<?> constructor;

        public TypedPointerIO(Class<? extends TypedPointer> type) {
            this.type = type;
            try {
                this.constructor = type.getConstructor(Long.TYPE);
            }
            catch (Exception ex2) {
                throw new RuntimeException("Failed to create " + CallIO.class.getName() + " for type " + type.getName(), ex2);
            }
        }

        @Override
        public Pointer<?> newInstance(long address) {
            try {
                return address == 0L ? null : (Pointer)this.constructor.newInstance(address);
            }
            catch (Exception ex2) {
                throw new RuntimeException("Failed to instantiate pointer of type " + this.type.getName(), ex2);
            }
        }

        @Override
        public void checkArg(Object ptr) {
            this.type.cast(ptr);
        }

        @Override
        public long getDCStruct() {
            return 0L;
        }

        @Override
        public long getPeer(Object o2) {
            return Pointer.getPeer((Pointer)o2);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class Utils {
        public static CallIO createPointerCallIOToTargetType(Type targetType) {
            return new GenericPointerHandler(targetType);
        }

        public static <EE extends Enum<EE>> CallIO createValuedEnumCallIO(final Class<EE> enumClass) {
            return new CallIO(){

                public Object newInstance(long value) {
                    return FlagSet.fromValue(value, enumClass);
                }

                public void checkArg(Object e2) {
                    if (!(e2 instanceof FlagSet)) {
                        enumClass.cast(e2);
                    }
                }

                public long getDCStruct() {
                    return 0L;
                }

                public long getPeer(Object o2) {
                    return 0L;
                }
            };
        }

        public static CallIO createPointerCallIO(Type type) {
            return Utils.createPointerCallIO(org.bridj.util.Utils.getClass(type), type);
        }

        public static CallIO createPointerCallIO(Class<?> cl2, Type type) {
            if (cl2 == Pointer.class) {
                return Utils.createPointerCallIOToTargetType(org.bridj.util.Utils.getUniqueParameterizedTypeParameter(type));
            }
            assert (TypedPointer.class.isAssignableFrom(cl2));
            return new TypedPointerIO(cl2);
        }
    }
}

