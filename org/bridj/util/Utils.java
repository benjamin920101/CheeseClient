/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Utils {
    public static int getEnclosedConstructorParametersOffset(Constructor c2) {
        Class<?> enclosingClass = c2.getDeclaringClass().getEnclosingClass();
        Class<?>[] params = c2.getParameterTypes();
        int overrideOffset = params.length > 0 && enclosingClass != null && enclosingClass == params[0] ? 1 : 0;
        return overrideOffset;
    }

    public static boolean isDirect(Buffer b2) {
        if (b2 instanceof ByteBuffer) {
            return ((ByteBuffer)b2).isDirect();
        }
        if (b2 instanceof IntBuffer) {
            return ((IntBuffer)b2).isDirect();
        }
        if (b2 instanceof LongBuffer) {
            return ((LongBuffer)b2).isDirect();
        }
        if (b2 instanceof DoubleBuffer) {
            return ((DoubleBuffer)b2).isDirect();
        }
        if (b2 instanceof FloatBuffer) {
            return ((FloatBuffer)b2).isDirect();
        }
        if (b2 instanceof ShortBuffer) {
            return ((ShortBuffer)b2).isDirect();
        }
        if (b2 instanceof CharBuffer) {
            return ((CharBuffer)b2).isDirect();
        }
        return false;
    }

    public static boolean isSignedIntegral(Type tpe) {
        return tpe == Integer.TYPE || tpe == Integer.class || tpe == Long.TYPE || tpe == Long.class || tpe == Short.TYPE || tpe == Short.class || tpe == Byte.TYPE || tpe == Byte.class;
    }

    public static String toString(Type t2) {
        if (t2 == null) {
            return "?";
        }
        if (t2 instanceof Class) {
            return ((Class)t2).getName();
        }
        return t2.toString();
    }

    public static String toString(Throwable th2) {
        StringWriter sw2 = new StringWriter();
        PrintWriter pw2 = new PrintWriter(sw2);
        th2.printStackTrace(pw2);
        return sw2.toString();
    }

    public static boolean eq(Object a2, Object b2) {
        if (a2 == null != (b2 == null)) {
            return false;
        }
        return a2 == null || a2.equals(b2);
    }

    public static <T> Class<T> getClass(Type type) {
        if (type == null) {
            return null;
        }
        if (type instanceof Class) {
            return (Class)type;
        }
        if (type instanceof ParameterizedType) {
            return Utils.getClass(((ParameterizedType)type).getRawType());
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(Utils.getClass(((GenericArrayType)type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof WildcardType) {
            return null;
        }
        if (type instanceof TypeVariable) {
            Type[] bounds = ((TypeVariable)type).getBounds();
            return Utils.getClass(bounds[0]);
        }
        throw new UnsupportedOperationException("Cannot infer class from type " + type);
    }

    public static Type getParent(Type type) {
        if (type instanceof Class) {
            return ((Class)type).getSuperclass();
        }
        return Utils.getParent(Utils.getClass(type));
    }

    public static Class[] getClasses(Type[] types) {
        int n2 = types.length;
        Class[] ret = new Class[n2];
        for (int i2 = 0; i2 < n2; ++i2) {
            ret[i2] = Utils.getClass(types[i2]);
        }
        return ret;
    }

    public static Type getUniqueParameterizedTypeParameter(Type type) {
        return type instanceof ParameterizedType ? ((ParameterizedType)type).getActualTypeArguments()[0] : null;
    }
}

