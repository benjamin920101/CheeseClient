/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.bridj.Platform;
import org.bridj.relocated.org.objectweb.asm.AnnotationVisitor;
import org.bridj.relocated.org.objectweb.asm.Attribute;
import org.bridj.relocated.org.objectweb.asm.ClassReader;
import org.bridj.relocated.org.objectweb.asm.ClassVisitor;
import org.bridj.relocated.org.objectweb.asm.FieldVisitor;
import org.bridj.relocated.org.objectweb.asm.MethodVisitor;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class BytecodeAnalyzer {
    private BytecodeAnalyzer() {
    }

    public static List<String[]> getNativeMethodSignatures(Class c2) throws IOException {
        return BytecodeAnalyzer.getNativeMethodSignatures(BytecodeAnalyzer.getInternalName(c2), Platform.getClassLoader(c2));
    }

    public static List<String[]> getNativeMethodSignatures(String internalName, ClassLoader classLoader) throws IOException {
        return BytecodeAnalyzer.getNativeMethodSignatures(internalName, classLoader, new ArrayList<String[]>());
    }

    private static List<String[]> getNativeMethodSignatures(final String internalName, ClassLoader classLoader, final List<String[]> ret) throws IOException {
        ClassReader r2 = new ClassReader(BytecodeAnalyzer.readByteCode(internalName, classLoader));
        String p2 = r2.getSuperName();
        if (p2 != null && !p2.equals("java/lang/Object")) {
            BytecodeAnalyzer.getNativeMethodSignatures(p2, classLoader, ret);
        }
        r2.accept(new EmptyVisitor(){

            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                if (Modifier.isNative(access)) {
                    ret.add(new String[]{internalName, name, desc});
                }
                return null;
            }
        }, 7);
        return ret;
    }

    private static List<String> getFieldNames(String internalName, String recurseToInternalName, ClassLoader classLoader, final List<String> ret) throws IOException {
        ClassReader r2 = new ClassReader(BytecodeAnalyzer.readByteCode(internalName, classLoader));
        String p2 = r2.getSuperName();
        if (p2 != null && !p2.equals("java/lang/Object") && !recurseToInternalName.equals(internalName)) {
            BytecodeAnalyzer.getFieldNames(p2, recurseToInternalName, classLoader, ret);
        }
        r2.accept(new EmptyVisitor(){

            public FieldVisitor visitField(int i2, String name, String string1, String string2, Object o2) {
                ret.add(name);
                return null;
            }
        }, 7);
        return ret;
    }

    private static List<String> getMethodNames(String internalName, String recurseToInternalName, ClassLoader classLoader, final List<String> ret) throws IOException {
        ClassReader r2 = new ClassReader(BytecodeAnalyzer.readByteCode(internalName, classLoader));
        String p2 = r2.getSuperName();
        if (p2 != null && !p2.equals("java/lang/Object") && !recurseToInternalName.equals(internalName)) {
            BytecodeAnalyzer.getMethodNames(p2, recurseToInternalName, classLoader, ret);
        }
        r2.accept(new EmptyVisitor(){

            public MethodVisitor visitMethod(int i2, String name, String string1, String string2, String[] strings) {
                ret.add(name);
                return null;
            }
        }, 7);
        return ret;
    }

    static String getInternalName(Class c2) {
        return c2.getName().replace('.', '/');
    }

    static URL getClassResource(Class c2) throws FileNotFoundException {
        return BytecodeAnalyzer.getClassResource(BytecodeAnalyzer.getInternalName(c2), Platform.getClassLoader(c2));
    }

    static URL getClassResource(String internalClassName, ClassLoader classLoader) throws FileNotFoundException {
        String p2 = internalClassName + ".class";
        URL u2 = classLoader.getResource(p2);
        if (u2 == null) {
            throw new FileNotFoundException("Resource '" + p2 + "'");
        }
        return u2;
    }

    static byte[] readByteCode(String classInternalName, ClassLoader classLoader) throws FileNotFoundException, IOException {
        return BytecodeAnalyzer.readBytes(BytecodeAnalyzer.getClassResource(classInternalName, classLoader).openStream(), true);
    }

    static byte[] readBytes(InputStream in2, boolean close) throws IOException {
        int len;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b2 = new byte[1024];
        while ((len = in2.read(b2)) > 0) {
            out.write(b2, 0, len);
        }
        if (close) {
            in2.close();
        }
        return out.toByteArray();
    }

    public static List<String> getFieldNames(Class c2, Class recurseTo) throws IOException {
        return BytecodeAnalyzer.getFieldNames(BytecodeAnalyzer.getInternalName(c2), BytecodeAnalyzer.getInternalName(recurseTo), Platform.getClassLoader(c2), new ArrayList<String>());
    }

    public static List<String> getMethodNames(Class c2, Class recurseTo) throws IOException {
        return BytecodeAnalyzer.getMethodNames(BytecodeAnalyzer.getInternalName(c2), BytecodeAnalyzer.getInternalName(recurseTo), Platform.getClassLoader(c2), new ArrayList<String>());
    }

    static class EmptyVisitor
    extends ClassVisitor {
        public EmptyVisitor() {
            super(262144);
        }

        public void visit(int i2, int i1, String string, String string1, String string2, String[] strings) {
        }

        public void visitSource(String string, String string1) {
        }

        public void visitOuterClass(String string, String string1, String string2) {
        }

        public AnnotationVisitor visitAnnotation(String string, boolean bln2) {
            return null;
        }

        public void visitAttribute(Attribute atrbt) {
        }

        public void visitInnerClass(String string, String string1, String string2, int i2) {
        }

        public FieldVisitor visitField(int i2, String string, String string1, String string2, Object o2) {
            return null;
        }

        public MethodVisitor visitMethod(int i2, String string, String string1, String string2, String[] strings) {
            return null;
        }

        public void visitEnd() {
        }
    }
}

