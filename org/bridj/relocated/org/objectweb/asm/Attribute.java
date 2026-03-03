/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.relocated.org.objectweb.asm;

import org.bridj.relocated.org.objectweb.asm.ByteVector;
import org.bridj.relocated.org.objectweb.asm.ClassReader;
import org.bridj.relocated.org.objectweb.asm.ClassWriter;
import org.bridj.relocated.org.objectweb.asm.Label;

public class Attribute {
    public final String type;
    byte[] b;
    Attribute a;

    protected Attribute(String string) {
        this.type = string;
    }

    public boolean isUnknown() {
        return true;
    }

    public boolean isCodeAttribute() {
        return false;
    }

    protected Label[] getLabels() {
        return null;
    }

    protected Attribute read(ClassReader classReader, int n2, int n3, char[] cArray, int n4, Label[] labelArray) {
        Attribute attribute = new Attribute(this.type);
        attribute.b = new byte[n3];
        System.arraycopy(classReader.b, n2, attribute.b, 0, n3);
        return attribute;
    }

    protected ByteVector write(ClassWriter classWriter, byte[] byArray, int n2, int n3, int n4) {
        ByteVector byteVector = new ByteVector();
        byteVector.a = this.b;
        byteVector.b = this.b.length;
        return byteVector;
    }

    final int a() {
        int n2 = 0;
        Attribute attribute = this;
        while (attribute != null) {
            ++n2;
            attribute = attribute.a;
        }
        return n2;
    }

    final int a(ClassWriter classWriter, byte[] byArray, int n2, int n3, int n4) {
        Attribute attribute = this;
        int n5 = 0;
        while (attribute != null) {
            classWriter.newUTF8(attribute.type);
            n5 += attribute.write((ClassWriter)classWriter, (byte[])byArray, (int)n2, (int)n3, (int)n4).b + 6;
            attribute = attribute.a;
        }
        return n5;
    }

    final void a(ClassWriter classWriter, byte[] byArray, int n2, int n3, int n4, ByteVector byteVector) {
        Attribute attribute = this;
        while (attribute != null) {
            ByteVector byteVector2 = attribute.write(classWriter, byArray, n2, n3, n4);
            byteVector.putShort(classWriter.newUTF8(attribute.type)).putInt(byteVector2.b);
            byteVector.putByteArray(byteVector2.a, 0, byteVector2.b);
            attribute = attribute.a;
        }
    }
}

