/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.WeakHashMap;
import org.bridj.IntValuedEnum;
import org.bridj.ValuedEnum;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FlagSet<E extends Enum<E>>
implements ValuedEnum<E> {
    private final long value;
    private final Class<E> enumClass;
    private E[] enumClassValues;
    private static Map<Class<?>, Object[]> enumsCache = new WeakHashMap();

    protected FlagSet(long value, Class<E> enumClass, E[] enumClassValues) {
        this.enumClass = enumClass;
        this.value = value;
        this.enumClassValues = enumClassValues;
    }

    private static synchronized <EE extends Enum<EE>> EE[] getValues(Class<EE> enumClass) {
        Enum[] values = (Enum[])enumsCache.get(enumClass);
        if (values == null) {
            try {
                Method valuesMethod = enumClass.getMethod("values", new Class[0]);
                Class<?> valuesType = valuesMethod.getReturnType();
                if (!valuesType.isArray() || !ValuedEnum.class.isAssignableFrom(valuesType.getComponentType())) {
                    throw new RuntimeException();
                }
                values = (Enum[])valuesMethod.invoke(null, new Object[0]);
                enumsCache.put(enumClass, values);
            }
            catch (Exception ex2) {
                throw new IllegalArgumentException("Class " + enumClass + " does not have a public static " + ValuedEnum.class.getName() + "[] values() method.", ex2);
            }
        }
        return values;
    }

    public boolean equals(Object o2) {
        if (!(o2 instanceof ValuedEnum)) {
            return false;
        }
        return this.value() == ((ValuedEnum)o2).value();
    }

    public int hashCode() {
        return Long.valueOf(this.value()).hashCode();
    }

    @Override
    public Iterator<E> iterator() {
        return this.getMatchingEnums().iterator();
    }

    public E toEnum() {
        Enum nullMatch = null;
        Enum match = null;
        for (Enum e2 : this.getMatchingEnums()) {
            if (((ValuedEnum)((Object)e2)).value() == 0L) {
                nullMatch = e2;
                continue;
            }
            if (match == null) {
                match = e2;
                continue;
            }
            throw new NoSuchElementException("More than one enum value corresponding to " + this + " : " + e2 + " and " + match + "...");
        }
        if (match != null) {
            return (E)match;
        }
        if (this.value() == 0L) {
            return (E)nullMatch;
        }
        throw new NoSuchElementException("No enum value corresponding to " + this);
    }

    public String toString() {
        StringBuilder b2 = new StringBuilder();
        b2.append(this.enumClass.getSimpleName()).append("(").append(this.value()).append(" = ");
        try {
            boolean first = true;
            for (Enum e2 : this.getMatchingEnums()) {
                if (first) {
                    first = false;
                } else {
                    b2.append(" | ");
                }
                b2.append(e2);
            }
        }
        catch (Throwable th2) {
            b2.append("?");
        }
        b2.append(")");
        return b2.toString();
    }

    public static <EE extends Enum<EE>> FlagSet<EE> fromValue(long value, Class<EE> enumClass) {
        return new FlagSet(value, enumClass, null);
    }

    public static <EE extends Enum<EE>> IntFlagSet<EE> fromValue(int value, Class<EE> enumClass) {
        return new IntFlagSet((long)value, enumClass, null);
    }

    public static <EE extends Enum<EE>> FlagSet<EE> fromValue(ValuedEnum<EE> value) {
        if (value instanceof Enum) {
            return FlagSet.fromValue((long)value.value(), (Enum[])new Enum[]{(Enum)((Object)value)});
        }
        return (FlagSet)value;
    }

    public static <EE extends Enum<EE>> FlagSet<EE> fromValue(long value, EE ... enumValue) {
        return new FlagSet(value, null, enumValue);
    }

    public static <EE extends Enum<EE>> IntFlagSet<EE> fromValue(int value, EE ... enumValue) {
        return new IntFlagSet((long)value, null, enumValue);
    }

    public static List<Long> getBits(long value) {
        ArrayList<Long> list = new ArrayList<Long>();
        for (int i2 = 0; i2 < 64; ++i2) {
            long bit2 = 1L << i2;
            if ((value & bit2) == 0L) continue;
            list.add(bit2);
        }
        return list;
    }

    @Override
    public long value() {
        return this.value;
    }

    public Class<E> getEnumClass() {
        return this.enumClass;
    }

    protected E[] getEnumClassValues() {
        return this.enumClassValues == null ? (this.enumClassValues = FlagSet.getValues(this.enumClass)) : this.enumClassValues;
    }

    public boolean is(E ... valuesToBeCombinedWithOR) {
        return this.value() == FlagSet.orValue(valuesToBeCombinedWithOR);
    }

    public boolean has(E ... valuesToBeCombinedWithOR) {
        return (this.value() & FlagSet.orValue(valuesToBeCombinedWithOR)) != 0L;
    }

    public FlagSet<E> or(E ... valuesToBeCombinedWithOR) {
        return new FlagSet(this.value() | FlagSet.orValue(valuesToBeCombinedWithOR), this.enumClass, null);
    }

    static <E extends Enum<E>> long orValue(E ... valuesToBeCombinedWithOR) {
        long value = 0L;
        for (E v2 : valuesToBeCombinedWithOR) {
            value |= ((ValuedEnum)v2).value();
        }
        return value;
    }

    public FlagSet<E> without(E ... valuesToBeCombinedWithOR) {
        return new FlagSet(this.value() & (FlagSet.orValue(valuesToBeCombinedWithOR) ^ 0xFFFFFFFFFFFFFFFFL), this.enumClass, null);
    }

    public FlagSet<E> and(E ... valuesToBeCombinedWithOR) {
        return new FlagSet(this.value() & FlagSet.orValue(valuesToBeCombinedWithOR), this.enumClass, null);
    }

    protected List<E> getMatchingEnums() {
        ArrayList<Enum> ret = new ArrayList<Enum>();
        if (this.enumClass != null) {
            for (Enum e2 : this.getEnumClassValues()) {
                long eMask = ((ValuedEnum)((Object)e2)).value();
                if ((this.value & eMask) != eMask) continue;
                ret.add(e2);
            }
        }
        return ret;
    }

    public static <E extends Enum<E>> FlagSet<E> fromValues(E ... enumValues) {
        long value = 0L;
        Class<?> cl2 = null;
        for (E enumValue : enumValues) {
            if (enumValue == null) continue;
            if (cl2 == null) {
                cl2 = enumValue.getClass();
            }
            value |= ((ValuedEnum)enumValue).value();
        }
        return new FlagSet(value, cl2, enumValues);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static class IntFlagSet<E extends Enum<E>>
    extends FlagSet<E>
    implements IntValuedEnum<E> {
        protected IntFlagSet(long value, Class<E> enumClass, E[] enumClassValues) {
            super(value, enumClass, enumClassValues);
        }
    }
}

