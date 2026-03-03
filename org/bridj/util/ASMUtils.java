/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.util;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.bridj.Pointer;
import org.bridj.relocated.org.objectweb.asm.ClassReader;
import org.bridj.relocated.org.objectweb.asm.ClassVisitor;
import org.bridj.relocated.org.objectweb.asm.ClassWriter;
import org.bridj.relocated.org.objectweb.asm.FieldVisitor;
import org.bridj.relocated.org.objectweb.asm.MethodVisitor;
import org.bridj.util.ClassDefiner;
import org.bridj.util.JNIUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ASMUtils {
    public static String typeDesc(Type t2) {
        if (t2 instanceof Class) {
            Class c2 = (Class)t2;
            if (c2 == Pointer.class) {
                return "Pointer";
            }
            if (c2.isPrimitive()) {
                String s2 = c2.getSimpleName();
                return Character.toUpperCase(s2.charAt(0)) + s2.substring(1);
            }
            if (c2.isArray()) {
                return ASMUtils.typeDesc(c2.getComponentType()) + "Array";
            }
            return c2.getName().replace('.', '_');
        }
        ParameterizedType p2 = (ParameterizedType)t2;
        StringBuilder b2 = new StringBuilder(ASMUtils.typeDesc(p2.getRawType()));
        for (Type pp2 : p2.getActualTypeArguments()) {
            b2.append("_").append(ASMUtils.typeDesc(pp2));
        }
        return b2.toString();
    }

    public static void addSuperCall(ClassVisitor cv2, String superClassInternalName) {
        MethodVisitor mv2 = cv2.visitMethod(1, "<init>", "()V", null, null);
        mv2.visitCode();
        mv2.visitVarInsn(25, 0);
        mv2.visitMethodInsn(183, superClassInternalName, "<init>", "()V");
        mv2.visitInsn(177);
        mv2.visitMaxs(1, 1);
        mv2.visitEnd();
    }

    public static <T> Class<? extends T> createSubclassWithSynchronizedNativeMethodsAndNoStaticFields(Class<T> original, ClassDefiner classDefiner) throws IOException {
        String suffix = "$SynchronizedNative";
        final String originalInternalName = JNIUtils.getNativeName(original);
        String synchronizedName = original.getName() + suffix;
        final String synchronizedInternalName = originalInternalName + suffix;
        ClassWriter classWriter = new ClassWriter(0);
        ClassVisitor cv2 = new ClassVisitor(262144, classWriter){

            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                super.visit(version, access, synchronizedInternalName, null, originalInternalName, new String[0]);
                ASMUtils.addSuperCall(this.cv, originalInternalName);
            }

            public void visitInnerClass(String name, String outerName, String innerName, int access) {
            }

            public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
                return null;
            }

            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                if (!Modifier.isNative(access)) {
                    return null;
                }
                return super.visitMethod(access | 0x20, name, desc, signature, exceptions);
            }
        };
        ClassReader classReader = new ClassReader(original.getName());
        classReader.accept(cv2, 0);
        return classDefiner.defineClass(synchronizedName, classWriter.toByteArray());
    }
}

