/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.bridj.BridJ;
import org.bridj.CommonPointerIOs;
import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;
import org.bridj.NativeObject;
import org.bridj.Platform;
import org.bridj.Pointer;
import org.bridj.PointerIO;
import org.bridj.SizeT;
import org.bridj.StructObject;
import org.bridj.TypedPointer;
import org.bridj.ValuedEnum;
import org.bridj.ann.Alignment;
import org.bridj.ann.Array;
import org.bridj.ann.Bits;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Ptr;
import org.bridj.ann.Struct;
import org.bridj.ann.Union;
import org.bridj.ann.Virtual;
import org.bridj.util.AnnotationUtils;
import org.bridj.util.Pair;
import org.bridj.util.Utils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class StructIO {
    static Map<Type, StructIO> structIOs = new HashMap<Type, StructIO>();
    static Customizer dummyCustomizer = new DefaultCustomizer();
    static Map<Class, Customizer> customizers = new HashMap<Class, Customizer>();
    protected PointerIO<?> pointerIO;
    protected volatile FieldDesc[] fields;
    private long structSize = -1L;
    private long structAlignment = -1L;
    protected final Class<?> structClass;
    protected final Type structType;
    protected boolean hasFieldFields;
    Customizer customizer;
    private List<AggregatedFieldDesc> aggregatedFields;
    SolidRanges solidRanges;

    static synchronized Customizer getCustomizer(Class<?> structClass) {
        Customizer c2 = customizers.get(structClass);
        if (c2 == null) {
            Class<? extends Customizer> customizerClass;
            Struct s2 = structClass.getAnnotation(Struct.class);
            if (s2 != null && (customizerClass = s2.customizer()) != null && customizerClass != Customizer.class) {
                try {
                    c2 = customizerClass.newInstance();
                }
                catch (Throwable th2) {
                    throw new RuntimeException("Failed to create customizer of class " + customizerClass.getName() + " for struct class " + structClass.getName() + " : " + th2, th2);
                }
            }
            if (c2 == null) {
                c2 = dummyCustomizer;
            }
            customizers.put(structClass, c2);
        }
        return c2;
    }

    public static StructIO getInstance(Type structType) {
        return StructIO.getInstance(Utils.getClass(structType), structType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static StructIO getInstance(Class structClass, Type structType) {
        Map<Type, StructIO> map = structIOs;
        synchronized (map) {
            StructIO io2 = structIOs.get(structType == null ? structClass : structType);
            if (io2 == null && (io2 = new StructIO(structClass, structType)) != null) {
                StructIO.registerStructIO(structClass, structType, io2);
            }
            return io2;
        }
    }

    public static synchronized StructIO registerStructIO(Class structClass, Type structType, StructIO io2) {
        structIOs.put(structType, io2);
        return io2;
    }

    public void prependBytes(long bytes) {
        this.build();
        for (FieldDesc field : this.fields) {
            field.offset(bytes);
        }
        this.structSize += bytes;
    }

    public void appendBytes(long bytes) {
        this.build();
        this.structSize += bytes;
    }

    public void setFieldOffset(String fieldName, long fieldOffset, boolean propagateChanges) {
        this.build();
        long propagatedOffset = 0L;
        FieldDesc[] arr$ = this.fields;
        int len$ = arr$.length;
        int i$ = 0;
        if (i$ < len$) {
            FieldDesc field = arr$[i$];
            if (field.name.equals(fieldName)) {
                propagatedOffset = fieldOffset - field.byteOffset;
                field.offset(propagatedOffset);
                if (!propagateChanges) {
                    return;
                }
                this.structSize += propagatedOffset;
            } else if (propagateChanges) {
                field.offset(propagatedOffset);
            }
            return;
        }
    }

    public StructIO(Class<?> structClass, Type structType) {
        this.structClass = structClass;
        this.structType = structType;
        this.customizer = StructIO.getCustomizer(structClass);
    }

    boolean isVirtual() {
        for (Method m2 : this.structClass.getMethods()) {
            if (m2.getAnnotation(Virtual.class) == null) continue;
            return true;
        }
        return false;
    }

    public Class<?> getStructClass() {
        return this.structClass;
    }

    public Type getStructType() {
        return this.structType;
    }

    public String toString() {
        return "StructIO(" + Utils.toString(this.structType) + ")";
    }

    public synchronized PointerIO<?> getPointerIO() {
        if (this.pointerIO == null) {
            this.pointerIO = new CommonPointerIOs.StructPointerIO(this);
        }
        return this.pointerIO;
    }

    protected long alignSize(long size, long alignment) {
        long r2;
        if (alignment > 1L && (r2 = size % alignment) != 0L) {
            size += alignment - r2;
        }
        return size;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void build() {
        if (this.fields == null) {
            StructIO structIO = this;
            synchronized (structIO) {
                if (this.fields == null) {
                    this.fields = this.computeStructLayout();
                    if (this.fields.length == 0 && BridJ.verbose) {
                        BridJ.info("No fields found in " + Utils.toString(this.structType) + " (maybe they weren't declared as public ?)");
                    }
                    this.customizer.afterBuild(this);
                    if (BridJ.debug) {
                        BridJ.info(this.describe());
                    }
                }
            }
        }
    }

    public final long getStructSize() {
        this.build();
        return this.structSize;
    }

    public final long getStructAlignment() {
        this.build();
        return this.structAlignment;
    }

    protected void orderFields(List<FieldDecl> fields) {
        Collections.sort(fields, new Comparator<FieldDecl>(){

            @Override
            public int compare(FieldDecl o1, FieldDecl o2) {
                long d2 = o1.index - o2.index;
                if (d2 != 0L) {
                    return d2 < 0L ? -1 : (d2 == 0L ? 0 : 1);
                }
                if (o1.declaringClass.isAssignableFrom(o2.declaringClass)) {
                    return -1;
                }
                if (o2.declaringClass.isAssignableFrom(o1.declaringClass)) {
                    return 1;
                }
                throw new RuntimeException("Failed to order fields " + o2.desc.name + " and " + o2.desc.name);
            }
        });
    }

    protected boolean acceptFieldGetter(Member member, boolean getter) {
        if (member instanceof Method && ((Method)member).getParameterTypes().length != (getter ? 0 : 1)) {
            return false;
        }
        if (((AnnotatedElement)((Object)member)).getAnnotation(Field.class) == null) {
            return false;
        }
        int modifiers = member.getModifiers();
        return !Modifier.isStatic(modifiers);
    }

    protected FieldDecl createFieldDecl(java.lang.reflect.Field getter) {
        FieldDecl field = this.createFieldDecl((Member)getter);
        field.desc.field = getter;
        field.desc.valueType = getter.getGenericType();
        field.valueClass = getter.getType();
        return field;
    }

    protected FieldDecl createFieldDecl(Method getter) {
        FieldDecl field = this.createFieldDecl((Member)getter);
        field.desc.getter = getter;
        field.desc.valueType = getter.getGenericReturnType();
        field.valueClass = getter.getReturnType();
        return field;
    }

    protected FieldDecl createFieldDecl(Member member) {
        FieldDecl field = new FieldDecl();
        field.declaringClass = member.getDeclaringClass();
        String name = member.getName();
        if (name.matches("get[A-Z].*")) {
            name = Character.toLowerCase(name.charAt(3)) + name.substring(4);
        }
        field.desc.name = name;
        AnnotatedElement getter = (AnnotatedElement)((Object)member);
        Field fil = getter.getAnnotation(Field.class);
        Bits bits = getter.getAnnotation(Bits.class);
        Array arr2 = getter.getAnnotation(Array.class);
        if (fil != null) {
            field.index = fil.value();
            field.unionWith = fil.unionWith();
        }
        if (field.unionWith < 0L && field.declaringClass.getAnnotation(Union.class) != null) {
            field.unionWith = 0L;
        }
        if (bits != null) {
            field.desc.bitLength = bits.value();
        }
        if (arr2 != null) {
            long length = 1L;
            for (long dim : arr2.value()) {
                length *= dim;
            }
            field.desc.arrayLength = length;
            field.desc.isArray = true;
        }
        field.desc.isCLong = AnnotationUtils.isAnnotationPresent(CLong.class, getter, new Annotation[0]);
        field.desc.isSizeT = AnnotationUtils.isAnnotationPresent(Ptr.class, getter, new Annotation[0]);
        return field;
    }

    protected List<FieldDecl> listFields() {
        ArrayList<FieldDecl> list = new ArrayList<FieldDecl>();
        for (Method method : this.structClass.getMethods()) {
            if (!this.acceptFieldGetter(method, true)) continue;
            FieldDecl io2 = this.createFieldDecl(method);
            try {
                Method setter = this.structClass.getMethod(method.getName(), io2.valueClass);
                if (this.acceptFieldGetter(setter, false)) {
                    io2.setter = setter;
                }
            }
            catch (Exception ex2) {
                // empty catch block
            }
            if (io2 == null) continue;
            list.add(io2);
        }
        int nFieldFields = 0;
        for (java.lang.reflect.Field field : this.structClass.getFields()) {
            FieldDecl io3;
            if (!this.acceptFieldGetter(field, true) || (io3 = this.createFieldDecl(field)) == null) continue;
            list.add(io3);
            ++nFieldFields;
        }
        if (nFieldFields > 0) {
            BridJ.warning("Struct " + this.structClass.getName() + " has " + nFieldFields + " struct fields implemented as Java fields, which won't give the best performance and might require counter-intuitive calls to BridJ.readFromNative / .writeToNative. Please consider using JNAerator to generate your struct instead.");
        }
        return list;
    }

    protected static long primTypeAlignment(Class<?> primType, long length) {
        if (StructIO.isDouble(primType) && !BridJ.alignDoubles && Platform.isLinux() && !Platform.is64Bits()) {
            return 4L;
        }
        return length;
    }

    private static boolean isDouble(Class<?> primType) {
        return primType == Double.class || primType == Double.TYPE;
    }

    protected static int primTypeLength(Class<?> primType) {
        if (primType == Integer.class || primType == Integer.TYPE) {
            return 4;
        }
        if (primType == Long.class || primType == Long.TYPE) {
            return 8;
        }
        if (primType == Short.class || primType == Short.TYPE) {
            return 2;
        }
        if (primType == Byte.class || primType == Byte.TYPE) {
            return 1;
        }
        if (primType == Character.class || primType == Character.TYPE) {
            return 2;
        }
        if (primType == Boolean.class || primType == Boolean.TYPE) {
            return 1;
        }
        if (primType == Float.class || primType == Float.TYPE) {
            return 4;
        }
        if (StructIO.isDouble(primType)) {
            return 8;
        }
        if (Pointer.class.isAssignableFrom(primType)) {
            return Pointer.SIZE;
        }
        throw new UnsupportedOperationException("Field type " + primType.getName() + " not supported yet");
    }

    public List<AggregatedFieldDesc> getAggregatedFields() {
        this.build();
        return this.aggregatedFields;
    }

    protected FieldDesc[] computeStructLayout() {
        List<FieldDecl> fieldDecls = this.listFields();
        this.orderFields(fieldDecls);
        this.customizer.beforeAggregation(this, fieldDecls);
        LinkedHashMap fieldsMap = new LinkedHashMap();
        for (FieldDecl field : fieldDecls) {
            if (field.index < 0L) {
                throw new RuntimeException("Negative field index not allowed for field " + field.desc.name);
            }
            long index = field.unionWith >= 0L ? field.unionWith : field.index;
            Pair key = new Pair(field.declaringClass, index);
            ArrayList<FieldDecl> siblings = (ArrayList<FieldDecl>)fieldsMap.get(key);
            if (siblings == null) {
                siblings = new ArrayList<FieldDecl>();
                fieldsMap.put(key, siblings);
            }
            siblings.add(field);
        }
        Alignment alignment = this.structClass.getAnnotation(Alignment.class);
        this.structAlignment = alignment != null ? (long)alignment.value() : 1L;
        this.aggregatedFields = new ArrayList<AggregatedFieldDesc>();
        for (List fieldGroup : fieldsMap.values()) {
            AggregatedFieldDesc aggregatedField = this.aggregateFields(fieldGroup);
            if (aggregatedField == null) continue;
            this.aggregatedFields.add(aggregatedField);
        }
        this.customizer.beforeLayout(this, this.aggregatedFields);
        this.performLayout(this.aggregatedFields);
        this.customizer.afterLayout(this, this.aggregatedFields);
        ArrayList<FieldDesc> fieldDescs = new ArrayList<FieldDesc>();
        SolidRanges.Builder rangesBuilder = new SolidRanges.Builder();
        for (AggregatedFieldDesc aggregatedField : this.aggregatedFields) {
            for (FieldDecl field : aggregatedField.fields) {
                FieldDesc desc = field.desc;
                desc.byteOffset = aggregatedField.byteOffset;
                desc.byteLength = aggregatedField.byteLength;
                desc.bitOffset = aggregatedField.bitOffset;
                fieldDescs.add(desc);
                rangesBuilder.add(desc);
                this.hasFieldFields = this.hasFieldFields || desc.field != null;
            }
        }
        this.solidRanges = rangesBuilder.toSolidRanges();
        return fieldDescs.toArray(new FieldDesc[fieldDescs.size()]);
    }

    protected long alignmentOf(Type tpe) {
        Class<?> c2 = PointerIO.getClass(tpe);
        if (StructObject.class.isAssignableFrom(c2)) {
            return StructIO.getInstance(c2).getStructAlignment();
        }
        return BridJ.sizeOf(tpe);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected AggregatedFieldDesc aggregateFields(List<FieldDecl> fieldGroup) {
        AggregatedFieldDesc aggregatedField = new AggregatedFieldDesc();
        boolean isMultiFields = fieldGroup.size() > 1;
        aggregatedField.fields.addAll(fieldGroup);
        for (FieldDecl field : fieldGroup) {
            StructIO io2;
            if (field.valueClass.isArray()) {
                throw new RuntimeException("Struct fields cannot be array types : please use a combination of Pointer and @Array (for instance, an int[10] is a @Array(10) Pointer<Integer>).");
            }
            if (field.valueClass.isPrimitive()) {
                if (field.desc.isCLong) {
                    field.desc.byteLength = org.bridj.CLong.SIZE;
                } else if (field.desc.isSizeT) {
                    field.desc.byteLength = SizeT.SIZE;
                } else {
                    field.desc.byteLength = StructIO.primTypeLength(field.valueClass);
                    field.desc.alignment = StructIO.primTypeAlignment(field.valueClass, field.desc.byteLength);
                }
            } else if (field.valueClass == org.bridj.CLong.class) {
                field.desc.byteLength = org.bridj.CLong.SIZE;
            } else if (field.valueClass == SizeT.class) {
                field.desc.byteLength = SizeT.SIZE;
            } else if (StructObject.class.isAssignableFrom(field.valueClass)) {
                field.desc.nativeTypeOrPointerTargetType = field.desc.valueType;
                io2 = StructIO.getInstance(field.valueClass, field.desc.valueType);
                field.desc.byteLength = io2.getStructSize();
                field.desc.alignment = io2.getStructAlignment();
                field.desc.isNativeObject = true;
            } else if (ValuedEnum.class.isAssignableFrom(field.valueClass)) {
                field.desc.nativeTypeOrPointerTargetType = field.desc.valueType instanceof ParameterizedType ? PointerIO.getClass(((ParameterizedType)field.desc.valueType).getActualTypeArguments()[0]) : null;
                Class<?> c2 = PointerIO.getClass(field.desc.nativeTypeOrPointerTargetType);
                if (!IntValuedEnum.class.isAssignableFrom(c2)) throw new RuntimeException("Enum type unknown : " + c2);
                field.desc.byteLength = 4L;
            } else if (TypedPointer.class.isAssignableFrom(field.valueClass)) {
                field.desc.nativeTypeOrPointerTargetType = field.desc.valueType;
                if (field.desc.isArray) {
                    throw new RuntimeException("Typed pointer field cannot be an array : " + field.desc.name);
                }
                field.desc.byteLength = Pointer.SIZE;
            } else if (Pointer.class.isAssignableFrom(field.valueClass)) {
                Type tpe;
                Type type = tpe = field.desc.valueType instanceof ParameterizedType ? ((ParameterizedType)field.desc.valueType).getActualTypeArguments()[0] : null;
                if (!(tpe instanceof WildcardType) && !(tpe instanceof TypeVariable)) {
                    field.desc.nativeTypeOrPointerTargetType = tpe;
                }
                if (field.desc.isArray) {
                    field.desc.byteLength = BridJ.sizeOf(field.desc.nativeTypeOrPointerTargetType);
                    field.desc.alignment = this.alignmentOf(field.desc.nativeTypeOrPointerTargetType);
                } else {
                    field.desc.byteLength = Pointer.SIZE;
                }
            } else if (Buffer.class.isAssignableFrom(field.valueClass)) {
                if (field.valueClass == IntBuffer.class) {
                    field.desc.byteLength = 4L;
                } else if (field.valueClass == LongBuffer.class) {
                    field.desc.byteLength = 8L;
                } else if (field.valueClass == ShortBuffer.class) {
                    field.desc.byteLength = 2L;
                } else if (field.valueClass == ByteBuffer.class) {
                    field.desc.byteLength = 1L;
                } else if (field.valueClass == FloatBuffer.class) {
                    field.desc.byteLength = 4L;
                } else {
                    if (field.valueClass != DoubleBuffer.class) throw new UnsupportedOperationException("Field array type " + field.valueClass.getName() + " not supported yet");
                    field.desc.byteLength = 8L;
                }
            } else if (field.valueClass.isArray() && field.valueClass.getComponentType().isPrimitive()) {
                field.desc.byteLength = StructIO.primTypeLength(field.valueClass.getComponentType());
                field.desc.alignment = StructIO.primTypeAlignment(field.valueClass, field.desc.byteLength);
            } else {
                io2 = StructIO.getInstance(field.valueClass, field.desc.valueType);
                long s2 = io2.getStructSize();
                if (s2 <= 0L) throw new UnsupportedOperationException("Field type " + field.valueClass.getName() + " not supported yet");
                field.desc.byteLength = s2;
            }
            aggregatedField.alignment = Math.max(aggregatedField.alignment, field.desc.alignment >= 0L ? field.desc.alignment : field.desc.byteLength);
            long length = field.desc.arrayLength * field.desc.byteLength;
            if (length >= aggregatedField.byteLength) {
                aggregatedField.byteLength = length;
            }
            if (field.desc.bitLength < 0L) continue;
            if (isMultiFields) {
                throw new RuntimeException("No support for bit fields unions yet !");
            }
            aggregatedField.bitLength = field.desc.bitLength;
            aggregatedField.byteLength = (aggregatedField.bitLength >>> 3) + (long)((aggregatedField.bitLength & 7L) != 0L ? 1 : 0);
        }
        return aggregatedField;
    }

    protected void performLayout(Iterable<AggregatedFieldDesc> aggregatedFields) {
        int pack;
        this.structSize = 0L;
        this.structAlignment = -1L;
        Struct s2 = this.structClass.getAnnotation(Struct.class);
        int n2 = pack = s2 != null ? s2.pack() : -1;
        if (this.isVirtual()) {
            this.structSize += (long)Pointer.SIZE;
            if ((long)Pointer.SIZE >= this.structAlignment) {
                this.structAlignment = Pointer.SIZE;
            }
        }
        int cumulativeBitOffset = 0;
        for (AggregatedFieldDesc aggregatedField : aggregatedFields) {
            this.structAlignment = Math.max(this.structAlignment, aggregatedField.alignment);
            if (aggregatedField.bitLength < 0L) {
                if (cumulativeBitOffset != 0) {
                    cumulativeBitOffset = 0;
                    ++this.structSize;
                }
                this.structSize = this.alignSize(this.structSize, pack > 0 ? (long)pack : aggregatedField.alignment);
            }
            long fieldByteOffset = this.structSize;
            long fieldBitOffset = cumulativeBitOffset;
            if (aggregatedField.bitLength >= 0L) {
                cumulativeBitOffset = (int)((long)cumulativeBitOffset + aggregatedField.bitLength);
                this.structSize += (long)(cumulativeBitOffset >>> 3);
                cumulativeBitOffset &= 7;
            } else {
                this.structSize += aggregatedField.byteLength;
            }
            aggregatedField.byteOffset = fieldByteOffset;
            aggregatedField.bitOffset = fieldBitOffset;
        }
        if (cumulativeBitOffset > 0) {
            this.structSize = this.alignSize(this.structSize + 1L, this.structAlignment);
        } else if (this.structSize > 0L) {
            this.structSize = this.alignSize(this.structSize, pack > 0 ? (long)pack : this.structAlignment);
        }
    }

    public boolean equal(StructObject a2, StructObject b2) {
        return this.compare(a2, b2) == 0;
    }

    public int compare(StructObject a2, StructObject b2) {
        Pointer<StructObject> pA = Pointer.pointerTo(a2);
        Pointer<StructObject> pB = Pointer.pointerTo(b2);
        if (pA == null || pB == null) {
            return pA != null ? 1 : (pB != null ? -1 : 0);
        }
        long[] offsets = this.solidRanges.offsets;
        long[] lengths = this.solidRanges.lengths;
        int n2 = offsets.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            long offset = offsets[i2];
            long length = lengths[i2];
            int cmp = pA.compareBytesAtOffset(offset, pB, offset, length);
            if (cmp == 0) continue;
            return cmp;
        }
        return 0;
    }

    public final String describe(StructObject struct) {
        StringBuilder b2 = new StringBuilder();
        b2.append(StructIO.describe(this.structType)).append(" { ");
        for (FieldDesc fd : this.fields) {
            b2.append("\n\t").append(fd.name).append(" = ");
            try {
                Object value = fd.getter != null ? fd.getter.invoke((Object)struct, new Object[0]) : fd.field.get(struct);
                if (value instanceof String) {
                    b2.append('\"').append(value.toString().replaceAll("\"", "\\\"")).append('\"');
                } else if (value instanceof Character) {
                    b2.append('\'').append(value).append('\'');
                } else if (value instanceof NativeObject) {
                    String d2 = BridJ.describe((NativeObject)value);
                    b2.append(d2.replaceAll("\n", "\n\t"));
                } else {
                    b2.append(value);
                }
            }
            catch (Throwable th2) {
                if (BridJ.debug) {
                    th2.printStackTrace();
                }
                b2.append("?");
            }
            b2.append("; ");
        }
        b2.append("\n}");
        return b2.toString();
    }

    static String describe(Type t2) {
        if (t2 == null) {
            return "?";
        }
        if (t2 instanceof Class) {
            return ((Class)t2).getSimpleName();
        }
        return t2.toString().replaceAll("\\bjava\\.lang\\.", "").replaceAll("\\borg\\.bridj\\.cpp\\.com\\.", "").replaceAll("\\borg\\.bridj\\.Pointer\\b", "Pointer");
    }

    public final String describe() {
        StringBuilder b2 = new StringBuilder();
        b2.append("// ");
        b2.append("size = ").append(this.structSize).append(", ");
        b2.append("alignment = ").append(this.structAlignment);
        b2.append("\nstruct ");
        b2.append(StructIO.describe(this.structType)).append(" { ");
        int nFields = this.fields.length;
        for (int iField = 0; iField < nFields; ++iField) {
            FieldDesc fd = this.fields[iField];
            b2.append("\n\t");
            b2.append("@Field(").append(iField).append(") ");
            if (fd.isCLong) {
                b2.append("@CLong ");
            } else if (fd.isSizeT) {
                b2.append("@Ptr ");
            }
            b2.append(StructIO.describe(fd.valueType)).append(" ").append(fd.name).append("; ");
            b2.append("// ");
            b2.append("offset = ").append(fd.byteOffset).append(", ");
            b2.append("length = ").append(fd.byteLength).append(", ");
            if (fd.bitOffset != 0L) {
                b2.append("bitOffset = ").append(fd.bitOffset).append(", ");
            }
            if (fd.bitLength != -1L) {
                b2.append("bitLength = ").append(fd.bitLength).append(", ");
            }
            if (fd.arrayLength != 1L) {
                b2.append("arrayLength = ").append(fd.arrayLength).append(", ");
            }
            if (fd.alignment == 1L) continue;
            b2.append("alignment = ").append(fd.alignment);
        }
        b2.append("\n}");
        return b2.toString();
    }

    public final void writeFieldsToNative(StructObject struct) {
        if (!this.hasFieldFields) {
            return;
        }
        try {
            for (FieldDesc fd : this.fields) {
                if (fd.field == null || fd.isArray) continue;
                Object value = fd.field.get(struct);
                if (value instanceof NativeObject) {
                    if (value == null) continue;
                    BridJ.writeToNative((NativeObject)value);
                    continue;
                }
                Pointer ptr = struct.peer.offset(fd.byteOffset);
                Type tpe = fd.isNativeObject || fd.isArray ? fd.nativeTypeOrPointerTargetType : fd.field.getGenericType();
                ptr = ptr.as(tpe);
                ptr = StructIO.fixIntegralTypeIOToMatchLength(ptr, fd.byteLength, fd.arrayLength);
                if (fd.isCLong && org.bridj.CLong.SIZE == 4 || fd.isSizeT && SizeT.SIZE == 4) {
                    value = (int)((Long)value).longValue();
                }
                ptr.set(value);
            }
        }
        catch (Throwable th2) {
            throw new RuntimeException("Unexpected error while writing fields from struct " + Utils.toString(this.structType) + " (" + Pointer.pointerTo(struct) + ")", th2);
        }
    }

    static Pointer fixIntegralTypeIOToMatchLength(Pointer ptr, long byteLength, long arrayLength) {
        long targetSize = ptr.getTargetSize();
        if (targetSize * arrayLength == byteLength) {
            return ptr;
        }
        Type targetType = ptr.getTargetType();
        if (!Utils.isSignedIntegral(targetType)) {
            return ptr;
        }
        switch ((int)byteLength) {
            case 1: {
                return ptr.as(Byte.TYPE);
            }
            case 2: {
                return ptr.as(Short.TYPE);
            }
            case 4: {
                return ptr.as(Integer.TYPE);
            }
            case 8: {
                return ptr.as(Long.TYPE);
            }
        }
        return ptr;
    }

    public final void readFieldsFromNative(StructObject struct) {
        if (!this.hasFieldFields) {
            return;
        }
        try {
            for (FieldDesc fd : this.fields) {
                Object value;
                if (fd.field == null) continue;
                Pointer ptr = struct.peer.offset(fd.byteOffset);
                Type tpe = fd.isNativeObject || fd.isArray ? fd.nativeTypeOrPointerTargetType : fd.field.getGenericType();
                ptr = ptr.as(tpe);
                ptr = StructIO.fixIntegralTypeIOToMatchLength(ptr, fd.byteLength, fd.arrayLength);
                if (fd.isArray) {
                    ptr = ptr.validElements(fd.arrayLength);
                    value = ptr;
                } else {
                    value = ptr.get();
                }
                fd.field.set(struct, value);
                if (!(value instanceof NativeObject) || value == null) continue;
                BridJ.readFromNative((NativeObject)value);
            }
        }
        catch (Throwable th2) {
            throw new RuntimeException("Unexpected error while reading fields from struct " + Utils.toString(this.structType) + " (" + Pointer.pointerTo(struct) + ") : " + th2, th2);
        }
    }

    public final <T> Pointer<T> getPointerField(StructObject struct, int fieldIndex) {
        Pointer p2;
        FieldDesc fd = this.fields[fieldIndex];
        if (fd.isArray) {
            p2 = struct.peer.offset(fd.byteOffset).as(fd.nativeTypeOrPointerTargetType);
            p2 = p2.validElements(fd.arrayLength);
        } else {
            p2 = struct.peer.getPointerAtOffset(fd.byteOffset, fd.nativeTypeOrPointerTargetType);
        }
        return p2;
    }

    public final <T> void setPointerField(StructObject struct, int fieldIndex, Pointer<T> value) {
        FieldDesc fd = this.fields[fieldIndex];
        struct.peer.setPointerAtOffset(fd.byteOffset, value);
    }

    public final <T extends TypedPointer> T getTypedPointerField(StructObject struct, int fieldIndex) {
        FieldDesc fd = this.fields[fieldIndex];
        PointerIO pio = PointerIO.getInstance(fd.nativeTypeOrPointerTargetType);
        return (T)((TypedPointer)pio.castTarget(struct.peer.getSizeTAtOffset(fd.byteOffset)));
    }

    public final <O extends NativeObject> O getNativeObjectField(StructObject struct, int fieldIndex) {
        FieldDesc fd = this.fields[fieldIndex];
        return struct.peer.offset(fd.byteOffset).getNativeObject(fd.nativeTypeOrPointerTargetType);
    }

    public final <O extends NativeObject> void setNativeObjectField(StructObject struct, int fieldIndex, O value) {
        FieldDesc fd = this.fields[fieldIndex];
        struct.peer.offset(fd.byteOffset).setNativeObject(value, fd.nativeTypeOrPointerTargetType);
    }

    public final <E extends Enum<E>> IntValuedEnum<E> getEnumField(StructObject struct, int fieldIndex) {
        FieldDesc fd = this.fields[fieldIndex];
        return FlagSet.fromValue(struct.peer.getIntAtOffset(fd.byteOffset), (Class)fd.nativeTypeOrPointerTargetType);
    }

    public final void setEnumField(StructObject struct, int fieldIndex, ValuedEnum<?> value) {
        FieldDesc fd = this.fields[fieldIndex];
        struct.peer.setIntAtOffset(fd.byteOffset, (int)value.value());
    }

    public final void setIntField(StructObject struct, int fieldIndex, int value) {
        FieldDesc fd = this.fields[fieldIndex];
        if (4L != fd.byteLength) {
            struct.peer.setSignedIntegralAtOffset(fd.byteOffset, value, fd.byteLength);
        }
        struct.peer.setIntAtOffset(fd.byteOffset, value);
    }

    public final int getIntField(StructObject struct, int fieldIndex) {
        FieldDesc fd = this.fields[fieldIndex];
        if (4L != fd.byteLength) {
            return (int)struct.peer.getSignedIntegralAtOffset(fd.byteOffset, fd.byteLength);
        }
        return struct.peer.getIntAtOffset(fd.byteOffset);
    }

    public final void setLongField(StructObject struct, int fieldIndex, long value) {
        FieldDesc fd = this.fields[fieldIndex];
        if (8L != fd.byteLength) {
            struct.peer.setSignedIntegralAtOffset(fd.byteOffset, value, fd.byteLength);
        }
        struct.peer.setLongAtOffset(fd.byteOffset, value);
    }

    public final long getLongField(StructObject struct, int fieldIndex) {
        FieldDesc fd = this.fields[fieldIndex];
        if (8L != fd.byteLength) {
            return struct.peer.getSignedIntegralAtOffset(fd.byteOffset, fd.byteLength);
        }
        return struct.peer.getLongAtOffset(fd.byteOffset);
    }

    public final void setShortField(StructObject struct, int fieldIndex, short value) {
        FieldDesc fd = this.fields[fieldIndex];
        if (2L != fd.byteLength) {
            struct.peer.setSignedIntegralAtOffset(fd.byteOffset, value, fd.byteLength);
        }
        struct.peer.setShortAtOffset(fd.byteOffset, value);
    }

    public final short getShortField(StructObject struct, int fieldIndex) {
        FieldDesc fd = this.fields[fieldIndex];
        if (2L != fd.byteLength) {
            return (short)struct.peer.getSignedIntegralAtOffset(fd.byteOffset, fd.byteLength);
        }
        return struct.peer.getShortAtOffset(fd.byteOffset);
    }

    public final void setByteField(StructObject struct, int fieldIndex, byte value) {
        FieldDesc fd = this.fields[fieldIndex];
        if (1L != fd.byteLength) {
            struct.peer.setSignedIntegralAtOffset(fd.byteOffset, value, fd.byteLength);
        }
        struct.peer.setByteAtOffset(fd.byteOffset, value);
    }

    public final byte getByteField(StructObject struct, int fieldIndex) {
        FieldDesc fd = this.fields[fieldIndex];
        if (1L != fd.byteLength) {
            return (byte)struct.peer.getSignedIntegralAtOffset(fd.byteOffset, fd.byteLength);
        }
        return struct.peer.getByteAtOffset(fd.byteOffset);
    }

    public final void setCharField(StructObject struct, int fieldIndex, char value) {
        FieldDesc fd = this.fields[fieldIndex];
        struct.peer.setCharAtOffset(fd.byteOffset, value);
    }

    public final char getCharField(StructObject struct, int fieldIndex) {
        FieldDesc fd = this.fields[fieldIndex];
        return struct.peer.getCharAtOffset(fd.byteOffset);
    }

    public final void setFloatField(StructObject struct, int fieldIndex, float value) {
        FieldDesc fd = this.fields[fieldIndex];
        struct.peer.setFloatAtOffset(fd.byteOffset, value);
    }

    public final float getFloatField(StructObject struct, int fieldIndex) {
        FieldDesc fd = this.fields[fieldIndex];
        return struct.peer.getFloatAtOffset(fd.byteOffset);
    }

    public final void setDoubleField(StructObject struct, int fieldIndex, double value) {
        FieldDesc fd = this.fields[fieldIndex];
        struct.peer.setDoubleAtOffset(fd.byteOffset, value);
    }

    public final double getDoubleField(StructObject struct, int fieldIndex) {
        FieldDesc fd = this.fields[fieldIndex];
        return struct.peer.getDoubleAtOffset(fd.byteOffset);
    }

    public final void setBooleanField(StructObject struct, int fieldIndex, boolean value) {
        FieldDesc fd = this.fields[fieldIndex];
        struct.peer.setBooleanAtOffset(fd.byteOffset, value);
    }

    public final boolean getBooleanField(StructObject struct, int fieldIndex) {
        FieldDesc fd = this.fields[fieldIndex];
        return struct.peer.getBooleanAtOffset(fd.byteOffset);
    }

    public final void setSizeTField(StructObject struct, int fieldIndex, long value) {
        FieldDesc fd = this.fields[fieldIndex];
        struct.peer.setSizeTAtOffset(fd.byteOffset, value);
    }

    public final long getSizeTField(StructObject struct, int fieldIndex) {
        FieldDesc fd = this.fields[fieldIndex];
        return struct.peer.getSizeTAtOffset(fd.byteOffset);
    }

    public final void setCLongField(StructObject struct, int fieldIndex, long value) {
        FieldDesc fd = this.fields[fieldIndex];
        struct.peer.setCLongAtOffset(fd.byteOffset, value);
    }

    public final long getCLongField(StructObject struct, int fieldIndex) {
        FieldDesc fd = this.fields[fieldIndex];
        return struct.peer.getCLongAtOffset(fd.byteOffset);
    }

    static class SolidRanges {
        long[] offsets;
        long[] lengths;

        SolidRanges() {
        }

        static class Builder {
            List<Long> offsets = new ArrayList<Long>();
            List<Long> lengths = new ArrayList<Long>();
            long lastOffset = -1L;
            long nextOffset = 0L;
            int count;

            Builder() {
            }

            void add(FieldDesc f2) {
                long offset = f2.byteOffset;
                long length = f2.byteLength;
                if (offset == this.lastOffset) {
                    this.lengths.set(this.count - 1, Math.max(this.lengths.get(this.count - 1), length));
                } else if (offset == this.nextOffset && this.count != 0) {
                    this.lengths.set(this.count - 1, this.lengths.get(this.count - 1) + length);
                } else {
                    this.offsets.add(offset);
                    this.lengths.add(length);
                    ++this.count;
                }
                this.lastOffset = offset;
                this.nextOffset = offset + length;
            }

            SolidRanges toSolidRanges() {
                SolidRanges r2 = new SolidRanges();
                r2.offsets = new long[this.count];
                r2.lengths = new long[this.count];
                for (int i2 = 0; i2 < this.count; ++i2) {
                    r2.offsets[i2] = this.offsets.get(i2);
                    r2.lengths[i2] = this.lengths.get(i2);
                }
                return r2;
            }
        }
    }

    public static class AggregatedFieldDesc
    extends FieldDesc {
        public List<FieldDecl> fields = new ArrayList<FieldDecl>();
    }

    public static class FieldDecl {
        final FieldDesc desc = new FieldDesc();
        Method setter;
        long index = -1L;
        long unionWith = -1L;
        Class<?> valueClass;
        Class<?> declaringClass;
        boolean isBitField;

        public String toString() {
            return this.desc.name + " (index = " + this.index + (this.unionWith < 0L ? "" : ", unionWith = " + this.unionWith) + ", desc = " + this.desc + ")";
        }
    }

    public static class FieldDesc {
        public long alignment = -1L;
        public long byteOffset = -1L;
        public long byteLength = -1L;
        public long bitOffset;
        public long bitLength = -1L;
        public long arrayLength = 1L;
        public boolean isArray;
        public boolean isNativeObject;
        public Type nativeTypeOrPointerTargetType;
        public java.lang.reflect.Field field;
        Type valueType;
        Method getter;
        String name;
        boolean isCLong;
        boolean isSizeT;

        public void offset(long bytes) {
            this.byteOffset += bytes;
        }

        public String toString() {
            return "Field(byteOffset = " + this.byteOffset + ", byteLength = " + this.byteLength + ", bitOffset = " + this.bitOffset + ", bitLength = " + this.bitLength + (this.nativeTypeOrPointerTargetType == null ? "" : ", ttype = " + this.nativeTypeOrPointerTargetType) + ")";
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class DefaultCustomizer
    implements Customizer {
        @Override
        public void beforeAggregation(StructIO io2, List<FieldDecl> fieldDecls) {
        }

        @Override
        public void beforeLayout(StructIO io2, List<AggregatedFieldDesc> aggregatedFields) {
        }

        @Override
        public void afterLayout(StructIO io2, List<AggregatedFieldDesc> aggregatedFields) {
        }

        @Override
        public void afterBuild(StructIO io2) {
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    @Deprecated
    public static interface Customizer {
        public void beforeAggregation(StructIO var1, List<FieldDecl> var2);

        public void beforeLayout(StructIO var1, List<AggregatedFieldDesc> var2);

        public void afterLayout(StructIO var1, List<AggregatedFieldDesc> var2);

        public void afterBuild(StructIO var1);
    }
}

