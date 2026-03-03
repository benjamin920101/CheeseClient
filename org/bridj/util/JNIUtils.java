/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import org.bridj.BridJ;
import org.bridj.Platform;
import org.bridj.util.BytecodeAnalyzer;
import org.bridj.util.StringUtils;

public class JNIUtils {
    private static Map<String, NativeMethodsCache> nativeMethodsCache = new WeakHashMap<String, NativeMethodsCache>();
    private static final String bridjPackage = BridJ.class.getPackage().getName();
    private static final String bridjNormalPackagePrefix = bridjPackage.endsWith("v0_6_2") ? bridjPackage.substring(0, bridjPackage.length() - "v0_6_2".length()) : bridjPackage + ".";
    private static final String bridjVersionSpecificPackagePrefix = bridjPackage + ".";

    private static synchronized NativeMethodsCache getNativeMethodsCache(String internalClassName) throws IOException {
        NativeMethodsCache cache = nativeMethodsCache.get(internalClassName);
        if (cache == null) {
            cache = new NativeMethodsCache(internalClassName);
            nativeMethodsCache.put(internalClassName, cache);
        }
        return cache;
    }

    static int findLastNonEscapeUnderscore(String s2) {
        int len;
        int i2 = len = s2.length();
        do {
            if ((i2 = s2.lastIndexOf("_", i2 - 1)) < 0 || i2 != len - 1 && Character.isDigit(s2.charAt(i2 + 1))) continue;
            return i2;
        } while (i2 > 0);
        return -1;
    }

    public static String decodeVersionSpecificMethodNameClassAndSignature(String symbolName, Object[] nameAndSigArray) throws NoSuchMethodException, IOException {
        return JNIUtils.decodeMethodNameClassAndSignature(symbolName, nameAndSigArray, bridjNormalPackagePrefix, bridjVersionSpecificPackagePrefix);
    }

    static String decodeMethodNameClassAndSignature(String symbolName, Object[] nameAndSigArray, String normalClassPrefix, String replacementClassPrefix) throws NoSuchMethodException, IOException {
        if (symbolName.startsWith("_")) {
            symbolName = symbolName.substring(1);
        }
        if (symbolName.startsWith("Java_")) {
            symbolName = symbolName.substring("Java_".length());
        }
        int i2 = JNIUtils.findLastNonEscapeUnderscore(symbolName);
        String className = symbolName.substring(0, i2).replace('_', '.');
        if (normalClassPrefix != null && className.startsWith(normalClassPrefix) && !className.startsWith(replacementClassPrefix)) {
            className = replacementClassPrefix + className.substring(normalClassPrefix.length());
        }
        String methodName = symbolName.substring(i2 + 1).replaceAll("_1", "_");
        NativeMethodsCache mc2 = JNIUtils.getNativeMethodsCache(className.replace('.', '/'));
        String sig = mc2.get(methodName);
        if (sig == null) {
            throw new NoSuchMethodException("Method " + methodName + " not found in class " + className + " : known method names = " + StringUtils.implode(mc2.getNames(), (Object)", "));
        }
        nameAndSigArray[0] = methodName;
        nameAndSigArray[1] = sig;
        String internalClassName = className.replace('.', '/');
        return internalClassName;
    }

    public static String getNativeName(Class c2) {
        return c2.getName().replace('.', '/');
    }

    public static String getNativeSignature(Method m2) {
        StringBuffer b2 = new StringBuffer();
        b2.append('(');
        for (Class<?> c2 : m2.getParameterTypes()) {
            b2.append(JNIUtils.getNativeSignature(c2));
        }
        b2.append(')');
        b2.append(JNIUtils.getNativeSignature(m2.getReturnType()));
        return b2.toString();
    }

    public static String getNativeSignature(Class c2) {
        if (c2.isPrimitive()) {
            if (c2 == Integer.TYPE) {
                return "I";
            }
            if (c2 == Long.TYPE) {
                return "J";
            }
            if (c2 == Short.TYPE) {
                return "S";
            }
            if (c2 == Byte.TYPE) {
                return "B";
            }
            if (c2 == Boolean.TYPE) {
                return "Z";
            }
            if (c2 == Double.TYPE) {
                return "D";
            }
            if (c2 == Float.TYPE) {
                return "F";
            }
            if (c2 == Character.TYPE) {
                return "C";
            }
            if (c2 == Void.TYPE) {
                return "V";
            }
            throw new RuntimeException("unexpected case");
        }
        if (c2.isArray()) {
            return "[" + JNIUtils.getNativeSignature(c2.getComponentType());
        }
        return "L" + JNIUtils.getNativeName(c2) + ";";
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static class NativeMethodsCache {
        Map<String, String> signatures = new HashMap<String, String>();

        public NativeMethodsCache(String internalClassName) throws IOException {
            for (String[] sig : BytecodeAnalyzer.getNativeMethodSignatures(internalClassName, Platform.getClassLoader())) {
                this.signatures.put(sig[1], sig[2]);
            }
        }

        public String get(String name) {
            return this.signatures.get(name);
        }

        public Set<String> getNames() {
            return this.signatures.keySet();
        }
    }
}

