/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.relocated.org.objectweb.asm;

import org.bridj.relocated.org.objectweb.asm.AnnotationVisitor;
import org.bridj.relocated.org.objectweb.asm.ByteVector;
import org.bridj.relocated.org.objectweb.asm.ClassWriter;
import org.bridj.relocated.org.objectweb.asm.Item;
import org.bridj.relocated.org.objectweb.asm.Type;

final class AnnotationWriter
extends AnnotationVisitor {
    private final ClassWriter a;
    private int b;
    private final boolean c;
    private final ByteVector d;
    private final ByteVector e;
    private final int f;
    AnnotationWriter g;
    AnnotationWriter h;

    AnnotationWriter(ClassWriter classWriter, boolean bl2, ByteVector byteVector, ByteVector byteVector2, int n2) {
        super(262144);
        this.a = classWriter;
        this.c = bl2;
        this.d = byteVector;
        this.e = byteVector2;
        this.f = n2;
    }

    public void visit(String string, Object object) {
        ++this.b;
        if (this.c) {
            this.d.putShort(this.a.newUTF8(string));
        }
        if (object instanceof String) {
            this.d.b(115, this.a.newUTF8((String)object));
        } else if (object instanceof Byte) {
            this.d.b(66, this.a.a((int)((Byte)object).byteValue()).a);
        } else if (object instanceof Boolean) {
            int n2 = (Boolean)object != false ? 1 : 0;
            this.d.b(90, this.a.a((int)n2).a);
        } else if (object instanceof Character) {
            this.d.b(67, this.a.a((int)((Character)object).charValue()).a);
        } else if (object instanceof Short) {
            this.d.b(83, this.a.a((int)((Short)object).shortValue()).a);
        } else if (object instanceof Type) {
            this.d.b(99, this.a.newUTF8(((Type)object).getDescriptor()));
        } else if (object instanceof byte[]) {
            byte[] byArray = (byte[])object;
            this.d.b(91, byArray.length);
            for (int i2 = 0; i2 < byArray.length; ++i2) {
                this.d.b(66, this.a.a((int)byArray[i2]).a);
            }
        } else if (object instanceof boolean[]) {
            boolean[] blArray = (boolean[])object;
            this.d.b(91, blArray.length);
            for (int i3 = 0; i3 < blArray.length; ++i3) {
                this.d.b(90, this.a.a((int)(blArray[i3] ? 1 : 0)).a);
            }
        } else if (object instanceof short[]) {
            short[] sArray = (short[])object;
            this.d.b(91, sArray.length);
            for (int i4 = 0; i4 < sArray.length; ++i4) {
                this.d.b(83, this.a.a((int)sArray[i4]).a);
            }
        } else if (object instanceof char[]) {
            char[] cArray = (char[])object;
            this.d.b(91, cArray.length);
            for (int i5 = 0; i5 < cArray.length; ++i5) {
                this.d.b(67, this.a.a((int)cArray[i5]).a);
            }
        } else if (object instanceof int[]) {
            int[] nArray = (int[])object;
            this.d.b(91, nArray.length);
            for (int i6 = 0; i6 < nArray.length; ++i6) {
                this.d.b(73, this.a.a((int)nArray[i6]).a);
            }
        } else if (object instanceof long[]) {
            long[] lArray = (long[])object;
            this.d.b(91, lArray.length);
            for (int i7 = 0; i7 < lArray.length; ++i7) {
                this.d.b(74, this.a.a((long)lArray[i7]).a);
            }
        } else if (object instanceof float[]) {
            float[] fArray = (float[])object;
            this.d.b(91, fArray.length);
            for (int i8 = 0; i8 < fArray.length; ++i8) {
                this.d.b(70, this.a.a((float)fArray[i8]).a);
            }
        } else if (object instanceof double[]) {
            double[] dArray = (double[])object;
            this.d.b(91, dArray.length);
            for (int i9 = 0; i9 < dArray.length; ++i9) {
                this.d.b(68, this.a.a((double)dArray[i9]).a);
            }
        } else {
            Item item = this.a.a(object);
            this.d.b(".s.IFJDCS".charAt(item.b), item.a);
        }
    }

    public void visitEnum(String string, String string2, String string3) {
        ++this.b;
        if (this.c) {
            this.d.putShort(this.a.newUTF8(string));
        }
        this.d.b(101, this.a.newUTF8(string2)).putShort(this.a.newUTF8(string3));
    }

    public AnnotationVisitor visitAnnotation(String string, String string2) {
        ++this.b;
        if (this.c) {
            this.d.putShort(this.a.newUTF8(string));
        }
        this.d.b(64, this.a.newUTF8(string2)).putShort(0);
        return new AnnotationWriter(this.a, true, this.d, this.d, this.d.b - 2);
    }

    public AnnotationVisitor visitArray(String string) {
        ++this.b;
        if (this.c) {
            this.d.putShort(this.a.newUTF8(string));
        }
        this.d.b(91, 0);
        return new AnnotationWriter(this.a, false, this.d, this.d, this.d.b - 2);
    }

    public void visitEnd() {
        if (this.e != null) {
            byte[] byArray = this.e.a;
            byArray[this.f] = (byte)(this.b >>> 8);
            byArray[this.f + 1] = (byte)this.b;
        }
    }

    int a() {
        int n2 = 0;
        AnnotationWriter annotationWriter = this;
        while (annotationWriter != null) {
            n2 += annotationWriter.d.b;
            annotationWriter = annotationWriter.g;
        }
        return n2;
    }

    void a(ByteVector byteVector) {
        int n2 = 0;
        int n3 = 2;
        AnnotationWriter annotationWriter = this;
        AnnotationWriter annotationWriter2 = null;
        while (annotationWriter != null) {
            ++n2;
            n3 += annotationWriter.d.b;
            annotationWriter.visitEnd();
            annotationWriter.h = annotationWriter2;
            annotationWriter2 = annotationWriter;
            annotationWriter = annotationWriter.g;
        }
        byteVector.putInt(n3);
        byteVector.putShort(n2);
        annotationWriter = annotationWriter2;
        while (annotationWriter != null) {
            byteVector.putByteArray(annotationWriter.d.a, 0, annotationWriter.d.b);
            annotationWriter = annotationWriter.h;
        }
    }

    static void a(AnnotationWriter[] annotationWriterArray, int n2, ByteVector byteVector) {
        int n3;
        int n4 = 1 + 2 * (annotationWriterArray.length - n2);
        for (n3 = n2; n3 < annotationWriterArray.length; ++n3) {
            n4 += annotationWriterArray[n3] == null ? 0 : annotationWriterArray[n3].a();
        }
        byteVector.putInt(n4).putByte(annotationWriterArray.length - n2);
        for (n3 = n2; n3 < annotationWriterArray.length; ++n3) {
            AnnotationWriter annotationWriter = annotationWriterArray[n3];
            AnnotationWriter annotationWriter2 = null;
            int n5 = 0;
            while (annotationWriter != null) {
                ++n5;
                annotationWriter.visitEnd();
                annotationWriter.h = annotationWriter2;
                annotationWriter2 = annotationWriter;
                annotationWriter = annotationWriter.g;
            }
            byteVector.putShort(n5);
            annotationWriter = annotationWriter2;
            while (annotationWriter != null) {
                byteVector.putByteArray(annotationWriter.d.a, 0, annotationWriter.d.b);
                annotationWriter = annotationWriter.h;
            }
        }
    }
}

