/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.relocated.org.objectweb.asm;

import org.bridj.relocated.org.objectweb.asm.AnnotationVisitor;
import org.bridj.relocated.org.objectweb.asm.AnnotationWriter;
import org.bridj.relocated.org.objectweb.asm.Attribute;
import org.bridj.relocated.org.objectweb.asm.ByteVector;
import org.bridj.relocated.org.objectweb.asm.ClassReader;
import org.bridj.relocated.org.objectweb.asm.ClassVisitor;
import org.bridj.relocated.org.objectweb.asm.FieldVisitor;
import org.bridj.relocated.org.objectweb.asm.FieldWriter;
import org.bridj.relocated.org.objectweb.asm.Handle;
import org.bridj.relocated.org.objectweb.asm.Item;
import org.bridj.relocated.org.objectweb.asm.MethodVisitor;
import org.bridj.relocated.org.objectweb.asm.MethodWriter;
import org.bridj.relocated.org.objectweb.asm.Type;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ClassWriter
extends ClassVisitor {
    public static final int COMPUTE_MAXS = 1;
    public static final int COMPUTE_FRAMES = 2;
    static final byte[] a;
    ClassReader M;
    int b;
    int c = 1;
    final ByteVector d = new ByteVector();
    Item[] e = new Item[256];
    int f = (int)(0.75 * (double)this.e.length);
    final Item g = new Item();
    final Item h = new Item();
    final Item i = new Item();
    final Item j = new Item();
    Item[] H;
    private short G;
    private int k;
    private int l;
    String I;
    private int m;
    private int n;
    private int o;
    private int[] p;
    private int q;
    private ByteVector r;
    private int s;
    private int t;
    private AnnotationWriter u;
    private AnnotationWriter v;
    private Attribute w;
    private int x;
    private ByteVector y;
    int z;
    ByteVector A;
    FieldWriter B;
    FieldWriter C;
    MethodWriter D;
    MethodWriter E;
    private final boolean K;
    private final boolean J;
    boolean L;

    public ClassWriter(int n2) {
        super(262144);
        this.K = (n2 & 1) != 0;
        this.J = (n2 & 2) != 0;
    }

    public ClassWriter(ClassReader classReader, int n2) {
        this(n2);
        classReader.a(this);
        this.M = classReader;
    }

    public final void visit(int n2, int n3, String string, String string2, String string3, String[] stringArray) {
        this.b = n2;
        this.k = n3;
        this.l = this.newClass(string);
        this.I = string;
        if (string2 != null) {
            this.m = this.newUTF8(string2);
        }
        int n4 = this.n = string3 == null ? 0 : this.newClass(string3);
        if (stringArray != null && stringArray.length > 0) {
            this.o = stringArray.length;
            this.p = new int[this.o];
            for (int i2 = 0; i2 < this.o; ++i2) {
                this.p[i2] = this.newClass(stringArray[i2]);
            }
        }
    }

    public final void visitSource(String string, String string2) {
        if (string != null) {
            this.q = this.newUTF8(string);
        }
        if (string2 != null) {
            this.r = new ByteVector().putUTF8(string2);
        }
    }

    public final void visitOuterClass(String string, String string2, String string3) {
        this.s = this.newClass(string);
        if (string2 != null && string3 != null) {
            this.t = this.newNameType(string2, string3);
        }
    }

    public final AnnotationVisitor visitAnnotation(String string, boolean bl2) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.newUTF8(string)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this, true, byteVector, byteVector, 2);
        if (bl2) {
            annotationWriter.g = this.u;
            this.u = annotationWriter;
        } else {
            annotationWriter.g = this.v;
            this.v = annotationWriter;
        }
        return annotationWriter;
    }

    public final void visitAttribute(Attribute attribute) {
        attribute.a = this.w;
        this.w = attribute;
    }

    public final void visitInnerClass(String string, String string2, String string3, int n2) {
        if (this.y == null) {
            this.y = new ByteVector();
        }
        ++this.x;
        this.y.putShort(string == null ? 0 : this.newClass(string));
        this.y.putShort(string2 == null ? 0 : this.newClass(string2));
        this.y.putShort(string3 == null ? 0 : this.newUTF8(string3));
        this.y.putShort(n2);
    }

    public final FieldVisitor visitField(int n2, String string, String string2, String string3, Object object) {
        return new FieldWriter(this, n2, string, string2, string3, object);
    }

    public final MethodVisitor visitMethod(int n2, String string, String string2, String string3, String[] stringArray) {
        return new MethodWriter(this, n2, string, string2, string3, stringArray, this.K, this.J);
    }

    public final void visitEnd() {
    }

    public byte[] toByteArray() {
        int n2;
        if (this.c > Short.MAX_VALUE) {
            throw new RuntimeException("Class file too large!");
        }
        int n3 = 24 + 2 * this.o;
        int n4 = 0;
        FieldWriter fieldWriter = this.B;
        while (fieldWriter != null) {
            ++n4;
            n3 += fieldWriter.a();
            fieldWriter = (FieldWriter)fieldWriter.fv;
        }
        int n5 = 0;
        MethodWriter methodWriter = this.D;
        while (methodWriter != null) {
            ++n5;
            n3 += methodWriter.a();
            methodWriter = (MethodWriter)methodWriter.mv;
        }
        int n6 = 0;
        if (this.A != null) {
            ++n6;
            n3 += 8 + this.A.b;
            this.newUTF8("BootstrapMethods");
        }
        if (this.m != 0) {
            ++n6;
            n3 += 8;
            this.newUTF8("Signature");
        }
        if (this.q != 0) {
            ++n6;
            n3 += 8;
            this.newUTF8("SourceFile");
        }
        if (this.r != null) {
            ++n6;
            n3 += this.r.b + 4;
            this.newUTF8("SourceDebugExtension");
        }
        if (this.s != 0) {
            ++n6;
            n3 += 10;
            this.newUTF8("EnclosingMethod");
        }
        if ((this.k & 0x20000) != 0) {
            ++n6;
            n3 += 6;
            this.newUTF8("Deprecated");
        }
        if ((this.k & 0x1000) != 0 && ((this.b & 0xFFFF) < 49 || (this.k & 0x40000) != 0)) {
            ++n6;
            n3 += 6;
            this.newUTF8("Synthetic");
        }
        if (this.y != null) {
            ++n6;
            n3 += 8 + this.y.b;
            this.newUTF8("InnerClasses");
        }
        if (this.u != null) {
            ++n6;
            n3 += 8 + this.u.a();
            this.newUTF8("RuntimeVisibleAnnotations");
        }
        if (this.v != null) {
            ++n6;
            n3 += 8 + this.v.a();
            this.newUTF8("RuntimeInvisibleAnnotations");
        }
        if (this.w != null) {
            n6 += this.w.a();
            n3 += this.w.a(this, null, 0, -1, -1);
        }
        ByteVector byteVector = new ByteVector(n3 += this.d.b);
        byteVector.putInt(-889275714).putInt(this.b);
        byteVector.putShort(this.c).putByteArray(this.d.a, 0, this.d.b);
        int n7 = 0x60000 | (this.k & 0x40000) / 64;
        byteVector.putShort(this.k & ~n7).putShort(this.l).putShort(this.n);
        byteVector.putShort(this.o);
        for (n2 = 0; n2 < this.o; ++n2) {
            byteVector.putShort(this.p[n2]);
        }
        byteVector.putShort(n4);
        fieldWriter = this.B;
        while (fieldWriter != null) {
            fieldWriter.a(byteVector);
            fieldWriter = (FieldWriter)fieldWriter.fv;
        }
        byteVector.putShort(n5);
        methodWriter = this.D;
        while (methodWriter != null) {
            methodWriter.a(byteVector);
            methodWriter = (MethodWriter)methodWriter.mv;
        }
        byteVector.putShort(n6);
        if (this.A != null) {
            byteVector.putShort(this.newUTF8("BootstrapMethods"));
            byteVector.putInt(this.A.b + 2).putShort(this.z);
            byteVector.putByteArray(this.A.a, 0, this.A.b);
        }
        if (this.m != 0) {
            byteVector.putShort(this.newUTF8("Signature")).putInt(2).putShort(this.m);
        }
        if (this.q != 0) {
            byteVector.putShort(this.newUTF8("SourceFile")).putInt(2).putShort(this.q);
        }
        if (this.r != null) {
            n2 = this.r.b - 2;
            byteVector.putShort(this.newUTF8("SourceDebugExtension")).putInt(n2);
            byteVector.putByteArray(this.r.a, 2, n2);
        }
        if (this.s != 0) {
            byteVector.putShort(this.newUTF8("EnclosingMethod")).putInt(4);
            byteVector.putShort(this.s).putShort(this.t);
        }
        if ((this.k & 0x20000) != 0) {
            byteVector.putShort(this.newUTF8("Deprecated")).putInt(0);
        }
        if ((this.k & 0x1000) != 0 && ((this.b & 0xFFFF) < 49 || (this.k & 0x40000) != 0)) {
            byteVector.putShort(this.newUTF8("Synthetic")).putInt(0);
        }
        if (this.y != null) {
            byteVector.putShort(this.newUTF8("InnerClasses"));
            byteVector.putInt(this.y.b + 2).putShort(this.x);
            byteVector.putByteArray(this.y.a, 0, this.y.b);
        }
        if (this.u != null) {
            byteVector.putShort(this.newUTF8("RuntimeVisibleAnnotations"));
            this.u.a(byteVector);
        }
        if (this.v != null) {
            byteVector.putShort(this.newUTF8("RuntimeInvisibleAnnotations"));
            this.v.a(byteVector);
        }
        if (this.w != null) {
            this.w.a(this, null, 0, -1, -1, byteVector);
        }
        if (this.L) {
            ClassWriter classWriter = new ClassWriter(2);
            new ClassReader(byteVector.a).accept(classWriter, 4);
            return classWriter.toByteArray();
        }
        return byteVector.a;
    }

    Item a(Object object) {
        if (object instanceof Integer) {
            int n2 = (Integer)object;
            return this.a(n2);
        }
        if (object instanceof Byte) {
            int n3 = ((Byte)object).intValue();
            return this.a(n3);
        }
        if (object instanceof Character) {
            char c2 = ((Character)object).charValue();
            return this.a(c2);
        }
        if (object instanceof Short) {
            int n4 = ((Short)object).intValue();
            return this.a(n4);
        }
        if (object instanceof Boolean) {
            int n5 = (Boolean)object != false ? 1 : 0;
            return this.a(n5);
        }
        if (object instanceof Float) {
            float f2 = ((Float)object).floatValue();
            return this.a(f2);
        }
        if (object instanceof Long) {
            long l2 = (Long)object;
            return this.a(l2);
        }
        if (object instanceof Double) {
            double d2 = (Double)object;
            return this.a(d2);
        }
        if (object instanceof String) {
            return this.b((String)object);
        }
        if (object instanceof Type) {
            Type type = (Type)object;
            int n6 = type.getSort();
            if (n6 == 9) {
                return this.a(type.getDescriptor());
            }
            if (n6 == 10) {
                return this.a(type.getInternalName());
            }
            return this.c(type.getDescriptor());
        }
        if (object instanceof Handle) {
            Handle handle = (Handle)object;
            return this.a(handle.a, handle.b, handle.c, handle.d);
        }
        throw new IllegalArgumentException("value " + object);
    }

    public int newConst(Object object) {
        return this.a((Object)object).a;
    }

    public int newUTF8(String string) {
        this.g.a(1, string, null, null);
        Item item = this.a(this.g);
        if (item == null) {
            this.d.putByte(1).putUTF8(string);
            item = new Item(this.c++, this.g);
            this.b(item);
        }
        return item.a;
    }

    Item a(String string) {
        this.h.a(7, string, null, null);
        Item item = this.a(this.h);
        if (item == null) {
            this.d.b(7, this.newUTF8(string));
            item = new Item(this.c++, this.h);
            this.b(item);
        }
        return item;
    }

    public int newClass(String string) {
        return this.a((String)string).a;
    }

    Item c(String string) {
        this.h.a(16, string, null, null);
        Item item = this.a(this.h);
        if (item == null) {
            this.d.b(16, this.newUTF8(string));
            item = new Item(this.c++, this.h);
            this.b(item);
        }
        return item;
    }

    public int newMethodType(String string) {
        return this.c((String)string).a;
    }

    Item a(int n2, String string, String string2, String string3) {
        this.j.a(20 + n2, string, string2, string3);
        Item item = this.a(this.j);
        if (item == null) {
            if (n2 <= 4) {
                this.b(15, n2, this.newField(string, string2, string3));
            } else {
                this.b(15, n2, this.newMethod(string, string2, string3, n2 == 9));
            }
            item = new Item(this.c++, this.j);
            this.b(item);
        }
        return item;
    }

    public int newHandle(int n2, String string, String string2, String string3) {
        return this.a((int)n2, (String)string, (String)string2, (String)string3).a;
    }

    Item a(String string, String string2, Handle handle, Object ... objectArray) {
        int n2;
        ByteVector byteVector = this.A;
        if (byteVector == null) {
            byteVector = this.A = new ByteVector();
        }
        int n3 = byteVector.b;
        int n4 = handle.hashCode();
        byteVector.putShort(this.newHandle(handle.a, handle.b, handle.c, handle.d));
        int n5 = objectArray.length;
        byteVector.putShort(n5);
        for (int i2 = 0; i2 < n5; ++i2) {
            Object object = objectArray[i2];
            n4 ^= object.hashCode();
            byteVector.putShort(this.newConst(object));
        }
        byte[] byArray = byteVector.a;
        int n6 = 2 + n5 << 1;
        Item item = this.e[(n4 &= Integer.MAX_VALUE) % this.e.length];
        block1: while (item != null) {
            if (item.b != 33 || item.j != n4) {
                item = item.k;
                continue;
            }
            n2 = item.c;
            for (int i3 = 0; i3 < n6; ++i3) {
                if (byArray[n3 + i3] == byArray[n2 + i3]) continue;
                item = item.k;
                continue block1;
            }
        }
        if (item != null) {
            n2 = item.a;
            byteVector.b = n3;
        } else {
            n2 = this.z++;
            item = new Item(n2);
            item.a(n3, n4);
            this.b(item);
        }
        this.i.a(string, string2, n2);
        item = this.a(this.i);
        if (item == null) {
            this.a(18, n2, this.newNameType(string, string2));
            item = new Item(this.c++, this.i);
            this.b(item);
        }
        return item;
    }

    public int newInvokeDynamic(String string, String string2, Handle handle, Object ... objectArray) {
        return this.a((String)string, (String)string2, (Handle)handle, (Object[])objectArray).a;
    }

    Item a(String string, String string2, String string3) {
        this.i.a(9, string, string2, string3);
        Item item = this.a(this.i);
        if (item == null) {
            this.a(9, this.newClass(string), this.newNameType(string2, string3));
            item = new Item(this.c++, this.i);
            this.b(item);
        }
        return item;
    }

    public int newField(String string, String string2, String string3) {
        return this.a((String)string, (String)string2, (String)string3).a;
    }

    Item a(String string, String string2, String string3, boolean bl2) {
        int n2 = bl2 ? 11 : 10;
        this.i.a(n2, string, string2, string3);
        Item item = this.a(this.i);
        if (item == null) {
            this.a(n2, this.newClass(string), this.newNameType(string2, string3));
            item = new Item(this.c++, this.i);
            this.b(item);
        }
        return item;
    }

    public int newMethod(String string, String string2, String string3, boolean bl2) {
        return this.a((String)string, (String)string2, (String)string3, (boolean)bl2).a;
    }

    Item a(int n2) {
        this.g.a(n2);
        Item item = this.a(this.g);
        if (item == null) {
            this.d.putByte(3).putInt(n2);
            item = new Item(this.c++, this.g);
            this.b(item);
        }
        return item;
    }

    Item a(float f2) {
        this.g.a(f2);
        Item item = this.a(this.g);
        if (item == null) {
            this.d.putByte(4).putInt(this.g.c);
            item = new Item(this.c++, this.g);
            this.b(item);
        }
        return item;
    }

    Item a(long l2) {
        this.g.a(l2);
        Item item = this.a(this.g);
        if (item == null) {
            this.d.putByte(5).putLong(l2);
            item = new Item(this.c, this.g);
            this.c += 2;
            this.b(item);
        }
        return item;
    }

    Item a(double d2) {
        this.g.a(d2);
        Item item = this.a(this.g);
        if (item == null) {
            this.d.putByte(6).putLong(this.g.d);
            item = new Item(this.c, this.g);
            this.c += 2;
            this.b(item);
        }
        return item;
    }

    private Item b(String string) {
        this.h.a(8, string, null, null);
        Item item = this.a(this.h);
        if (item == null) {
            this.d.b(8, this.newUTF8(string));
            item = new Item(this.c++, this.h);
            this.b(item);
        }
        return item;
    }

    public int newNameType(String string, String string2) {
        return this.a((String)string, (String)string2).a;
    }

    Item a(String string, String string2) {
        this.h.a(12, string, string2, null);
        Item item = this.a(this.h);
        if (item == null) {
            this.a(12, this.newUTF8(string), this.newUTF8(string2));
            item = new Item(this.c++, this.h);
            this.b(item);
        }
        return item;
    }

    int c(String string) {
        this.g.a(30, string, null, null);
        Item item = this.a(this.g);
        if (item == null) {
            item = this.c(this.g);
        }
        return item.a;
    }

    int a(String string, int n2) {
        this.g.b = 31;
        this.g.c = n2;
        this.g.g = string;
        this.g.j = Integer.MAX_VALUE & 31 + string.hashCode() + n2;
        Item item = this.a(this.g);
        if (item == null) {
            item = this.c(this.g);
        }
        return item.a;
    }

    private Item c(Item item) {
        this.G = (short)(this.G + 1);
        Item item2 = new Item(this.G, this.g);
        this.b(item2);
        if (this.H == null) {
            this.H = new Item[16];
        }
        if (this.G == this.H.length) {
            Item[] itemArray = new Item[2 * this.H.length];
            System.arraycopy(this.H, 0, itemArray, 0, this.H.length);
            this.H = itemArray;
        }
        this.H[this.G] = item2;
        return item2;
    }

    int a(int n2, int n3) {
        this.h.b = 32;
        this.h.d = (long)n2 | (long)n3 << 32;
        this.h.j = Integer.MAX_VALUE & 32 + n2 + n3;
        Item item = this.a(this.h);
        if (item == null) {
            String string = this.H[n2].g;
            String string2 = this.H[n3].g;
            this.h.c = this.c(this.getCommonSuperClass(string, string2));
            item = new Item(0, this.h);
            this.b(item);
        }
        return item.c;
    }

    protected String getCommonSuperClass(String string, String string2) {
        Class<?> clazz;
        Class<?> clazz2;
        ClassLoader classLoader = this.getClass().getClassLoader();
        try {
            clazz2 = Class.forName(string.replace('/', '.'), false, classLoader);
            clazz = Class.forName(string2.replace('/', '.'), false, classLoader);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception.toString());
        }
        if (clazz2.isAssignableFrom(clazz)) {
            return string;
        }
        if (clazz.isAssignableFrom(clazz2)) {
            return string2;
        }
        if (clazz2.isInterface() || clazz.isInterface()) {
            return "java/lang/Object";
        }
        while (!(clazz2 = clazz2.getSuperclass()).isAssignableFrom(clazz)) {
        }
        return clazz2.getName().replace('.', '/');
    }

    private Item a(Item item) {
        Item item2 = this.e[item.j % this.e.length];
        while (!(item2 == null || item2.b == item.b && item.a(item2))) {
            item2 = item2.k;
        }
        return item2;
    }

    private void b(Item item) {
        int n2;
        if (this.c + this.G > this.f) {
            n2 = this.e.length;
            int n3 = n2 * 2 + 1;
            Item[] itemArray = new Item[n3];
            for (int i2 = n2 - 1; i2 >= 0; --i2) {
                Item item2 = this.e[i2];
                while (item2 != null) {
                    int n4 = item2.j % itemArray.length;
                    Item item3 = item2.k;
                    item2.k = itemArray[n4];
                    itemArray[n4] = item2;
                    item2 = item3;
                }
            }
            this.e = itemArray;
            this.f = (int)((double)n3 * 0.75);
        }
        n2 = item.j % this.e.length;
        item.k = this.e[n2];
        this.e[n2] = item;
    }

    private void a(int n2, int n3, int n4) {
        this.d.b(n2, n3).putShort(n4);
    }

    private void b(int n2, int n3, int n4) {
        this.d.a(n2, n3).putShort(n4);
    }

    static {
        byte[] byArray = new byte[220];
        String string = "AAAAAAAAAAAAAAAABCLMMDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANAAAAAAAAAAAAAAAAAAAAJJJJJJJJJJJJJJJJDOPAAAAAAGGGGGGGHIFBFAAFFAARQJJKKJJJJJJJJJJJJJJJJJJ";
        for (int i2 = 0; i2 < byArray.length; ++i2) {
            byArray[i2] = (byte)(string.charAt(i2) - 65);
        }
        a = byArray;
    }
}

