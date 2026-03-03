/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.bridj.util.Utils;

public class DefaultParameterizedType
implements ParameterizedType {
    private final Type[] actualTypeArguments;
    private final Type ownerType;
    private final Type rawType;

    public DefaultParameterizedType(Type ownerType, Type rawType, Type[] actualTypeArguments) {
        this.ownerType = ownerType;
        this.actualTypeArguments = actualTypeArguments;
        this.rawType = rawType;
    }

    public DefaultParameterizedType(Type rawType, Type ... actualTypeArguments) {
        this((Type)null, rawType, actualTypeArguments);
    }

    public String toString() {
        StringBuilder b2 = new StringBuilder();
        if (this.ownerType != null) {
            b2.append(Utils.toString(this.ownerType)).append(".");
        }
        b2.append(this.rawType);
        if (this.actualTypeArguments.length > 0) {
            b2.append("<");
            for (int i2 = 0; i2 < this.actualTypeArguments.length; ++i2) {
                if (i2 > 0) {
                    b2.append(", ");
                }
                b2.append(Utils.toString(this.actualTypeArguments[i2]));
            }
            b2.append(">");
        }
        return b2.toString();
    }

    public static Type paramType(Type rawType, Type ... actualTypeArguments) {
        return new DefaultParameterizedType(rawType, actualTypeArguments);
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

    public int hashCode() {
        int h2 = this.getRawType().hashCode();
        if (this.getOwnerType() != null) {
            h2 ^= this.getOwnerType().hashCode();
        }
        int n2 = this.actualTypeArguments.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            h2 ^= this.actualTypeArguments[i2].hashCode();
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
        if (o2 == null || !(o2 instanceof DefaultParameterizedType)) {
            return false;
        }
        DefaultParameterizedType t2 = (DefaultParameterizedType)o2;
        if (!DefaultParameterizedType.eq(this.getRawType(), t2.getRawType())) {
            return false;
        }
        if (!DefaultParameterizedType.eq(this.getOwnerType(), t2.getOwnerType())) {
            return false;
        }
        Type[] tp2 = t2.actualTypeArguments;
        if (this.actualTypeArguments.length != tp2.length) {
            return false;
        }
        int n2 = this.actualTypeArguments.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            if (DefaultParameterizedType.eq(this.actualTypeArguments[i2], tp2[i2])) continue;
            return false;
        }
        return true;
    }
}

