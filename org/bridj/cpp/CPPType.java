/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import org.bridj.ann.Template;

public class CPPType
implements ParameterizedType {
    private final Type[] actualTypeArguments;
    private final Type ownerType;
    private final Type rawType;
    private final Object[] templateParameters;

    public CPPType(Type ownerType, Type rawType, Object ... templateParameters) {
        this.ownerType = ownerType;
        this.actualTypeArguments = CPPType.getTypes(templateParameters);
        this.rawType = rawType;
        this.templateParameters = templateParameters;
    }

    public CPPType(Type rawType, Object ... templateParameters) {
        this(null, rawType, templateParameters);
    }

    private static Type[] getTypes(Object[] objects) {
        ArrayList<Type> ret = new ArrayList<Type>(objects.length);
        for (Object o2 : objects) {
            if (!(o2 instanceof Type)) continue;
            ret.add((Type)o2);
        }
        return ret.toArray(new Type[ret.size()]);
    }

    static Object[] cons(Class firstClass, Object ... flattenedClassesAndParams) {
        Object[] a2 = new Object[flattenedClassesAndParams.length + 1];
        a2[0] = firstClass;
        System.arraycopy(flattenedClassesAndParams, 0, a2, 1, flattenedClassesAndParams.length);
        return a2;
    }

    public static Type getCPPType(Object ... flattenedClassesAndParams) {
        int[] position = new int[]{0};
        Type t2 = CPPType.parseCPPType(flattenedClassesAndParams, position);
        if (position[0] < flattenedClassesAndParams.length) {
            CPPType.parseError("Unexpected trailing parameters", flattenedClassesAndParams, position);
        }
        return t2;
    }

    static void parseError(String message, Object[] flattenedClassesAndParams, int[] position) {
        throw new IllegalArgumentException("Error while parsing C++ type in " + Arrays.asList(flattenedClassesAndParams) + " at offset " + position[0] + " : " + message);
    }

    static void notEOF(String message, Object[] flattenedClassesAndParams, int[] position) {
        if (position[0] >= flattenedClassesAndParams.length) {
            throw new IllegalArgumentException("EOF while parsing C++ type in " + Arrays.asList(flattenedClassesAndParams) + " at offset " + position[0] + " : " + message);
        }
    }

    static Type parseCPPType(Object[] flattenedClassesAndParams, int[] position) {
        CPPType.notEOF("expecting class", flattenedClassesAndParams, position);
        Object oc = flattenedClassesAndParams[position[0]];
        if (!(oc instanceof Class)) {
            CPPType.parseError("expected class", flattenedClassesAndParams, position);
        }
        Class c2 = (Class)oc;
        position[0] = position[0] + 1;
        Template t2 = c2.getAnnotation(Template.class);
        Class<?>[] paramTypes = t2 == null ? null : t2.value();
        int nParams = paramTypes == null ? 0 : paramTypes.length;
        Object[] params = new Object[nParams];
        for (int iParam = 0; iParam < nParams; ++iParam) {
            CPPType.notEOF("expecting param " + iParam + " for template " + c2.getName(), flattenedClassesAndParams, position);
            Object param = flattenedClassesAndParams[position[0]];
            Class<?> paramType = paramTypes[iParam];
            if (paramType.equals(Class.class) && param.getClass().equals(Class.class)) {
                param = CPPType.parseCPPType(flattenedClassesAndParams, position);
            } else {
                if (!paramType.isInstance(param)) {
                    CPPType.parseError("bad type for template param " + iParam + " : expected a " + paramType + ", got " + param, flattenedClassesAndParams, position);
                }
                position[0] = position[0] + 1;
            }
            params[iParam] = param;
        }
        return nParams == 0 ? c2 : new CPPType((Type)c2, params);
    }

    public Type[] getActualTypeArguments() {
        return (Type[])this.actualTypeArguments.clone();
    }

    public Type getOwnerType() {
        return this.ownerType;
    }

    public Type getRawType() {
        return this.rawType;
    }

    public Object[] getTemplateParameters() {
        return (Object[])this.templateParameters.clone();
    }

    public int hashCode() {
        int h2 = this.getRawType().hashCode();
        if (this.getOwnerType() != null) {
            h2 ^= this.getOwnerType().hashCode();
        }
        int n2 = this.templateParameters.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            h2 ^= this.templateParameters[i2].hashCode();
        }
        return h2;
    }

    static boolean eq(Object a2, Object b2) {
        if (a2 == null != (b2 == null)) {
            return false;
        }
        return a2 == null || a2.equals(b2);
    }

    public boolean equals(Object o2) {
        if (o2 == null || !(o2 instanceof CPPType)) {
            return false;
        }
        CPPType t2 = (CPPType)o2;
        if (!CPPType.eq(this.getRawType(), t2.getRawType())) {
            return false;
        }
        if (!CPPType.eq(this.getOwnerType(), t2.getOwnerType())) {
            return false;
        }
        Object[] tp2 = t2.templateParameters;
        if (this.templateParameters.length != tp2.length) {
            return false;
        }
        int n2 = this.templateParameters.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            if (CPPType.eq(this.templateParameters[i2], tp2[i2])) continue;
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder b2 = new StringBuilder();
        if (this.getOwnerType() != null) {
            b2.append(this.getOwnerType()).append('.');
        }
        b2.append(this.getRawType());
        int n2 = this.templateParameters.length;
        if (n2 != 0) {
            b2.append('<');
            for (int i2 = 0; i2 < n2; ++i2) {
                if (i2 > 0) {
                    b2.append(", ");
                }
                b2.append(this.templateParameters[i2]);
            }
            b2.append('>');
        }
        return b2.toString();
    }
}

