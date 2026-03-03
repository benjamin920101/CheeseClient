/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.relocated.org.objectweb.asm;

import org.bridj.relocated.org.objectweb.asm.AnnotationVisitor;
import org.bridj.relocated.org.objectweb.asm.Attribute;
import org.bridj.relocated.org.objectweb.asm.Handle;
import org.bridj.relocated.org.objectweb.asm.Label;

public abstract class MethodVisitor {
    protected final int api;
    protected MethodVisitor mv;

    public MethodVisitor(int n2) {
        this(n2, null);
    }

    public MethodVisitor(int n2, MethodVisitor methodVisitor) {
        this.api = n2;
        this.mv = methodVisitor;
    }

    public AnnotationVisitor visitAnnotationDefault() {
        if (this.mv != null) {
            return this.mv.visitAnnotationDefault();
        }
        return null;
    }

    public AnnotationVisitor visitAnnotation(String string, boolean bl2) {
        if (this.mv != null) {
            return this.mv.visitAnnotation(string, bl2);
        }
        return null;
    }

    public AnnotationVisitor visitParameterAnnotation(int n2, String string, boolean bl2) {
        if (this.mv != null) {
            return this.mv.visitParameterAnnotation(n2, string, bl2);
        }
        return null;
    }

    public void visitAttribute(Attribute attribute) {
        if (this.mv != null) {
            this.mv.visitAttribute(attribute);
        }
    }

    public void visitCode() {
        if (this.mv != null) {
            this.mv.visitCode();
        }
    }

    public void visitFrame(int n2, int n3, Object[] objectArray, int n4, Object[] objectArray2) {
        if (this.mv != null) {
            this.mv.visitFrame(n2, n3, objectArray, n4, objectArray2);
        }
    }

    public void visitInsn(int n2) {
        if (this.mv != null) {
            this.mv.visitInsn(n2);
        }
    }

    public void visitIntInsn(int n2, int n3) {
        if (this.mv != null) {
            this.mv.visitIntInsn(n2, n3);
        }
    }

    public void visitVarInsn(int n2, int n3) {
        if (this.mv != null) {
            this.mv.visitVarInsn(n2, n3);
        }
    }

    public void visitTypeInsn(int n2, String string) {
        if (this.mv != null) {
            this.mv.visitTypeInsn(n2, string);
        }
    }

    public void visitFieldInsn(int n2, String string, String string2, String string3) {
        if (this.mv != null) {
            this.mv.visitFieldInsn(n2, string, string2, string3);
        }
    }

    public void visitMethodInsn(int n2, String string, String string2, String string3) {
        if (this.mv != null) {
            this.mv.visitMethodInsn(n2, string, string2, string3);
        }
    }

    public void visitInvokeDynamicInsn(String string, String string2, Handle handle, Object ... objectArray) {
        if (this.mv != null) {
            this.mv.visitInvokeDynamicInsn(string, string2, handle, objectArray);
        }
    }

    public void visitJumpInsn(int n2, Label label) {
        if (this.mv != null) {
            this.mv.visitJumpInsn(n2, label);
        }
    }

    public void visitLabel(Label label) {
        if (this.mv != null) {
            this.mv.visitLabel(label);
        }
    }

    public void visitLdcInsn(Object object) {
        if (this.mv != null) {
            this.mv.visitLdcInsn(object);
        }
    }

    public void visitIincInsn(int n2, int n3) {
        if (this.mv != null) {
            this.mv.visitIincInsn(n2, n3);
        }
    }

    public void visitTableSwitchInsn(int n2, int n3, Label label, Label ... labelArray) {
        if (this.mv != null) {
            this.mv.visitTableSwitchInsn(n2, n3, label, labelArray);
        }
    }

    public void visitLookupSwitchInsn(Label label, int[] nArray, Label[] labelArray) {
        if (this.mv != null) {
            this.mv.visitLookupSwitchInsn(label, nArray, labelArray);
        }
    }

    public void visitMultiANewArrayInsn(String string, int n2) {
        if (this.mv != null) {
            this.mv.visitMultiANewArrayInsn(string, n2);
        }
    }

    public void visitTryCatchBlock(Label label, Label label2, Label label3, String string) {
        if (this.mv != null) {
            this.mv.visitTryCatchBlock(label, label2, label3, string);
        }
    }

    public void visitLocalVariable(String string, String string2, String string3, Label label, Label label2, int n2) {
        if (this.mv != null) {
            this.mv.visitLocalVariable(string, string2, string3, label, label2, n2);
        }
    }

    public void visitLineNumber(int n2, Label label) {
        if (this.mv != null) {
            this.mv.visitLineNumber(n2, label);
        }
    }

    public void visitMaxs(int n2, int n3) {
        if (this.mv != null) {
            this.mv.visitMaxs(n2, n3);
        }
    }

    public void visitEnd() {
        if (this.mv != null) {
            this.mv.visitEnd();
        }
    }
}

