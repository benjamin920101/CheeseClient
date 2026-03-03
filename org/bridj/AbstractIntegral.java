/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

class AbstractIntegral
extends Number {
    protected final long value;
    private static final long HIGH_NEG = -4294967296L;

    public AbstractIntegral(long value) {
        this.value = value;
    }

    public static int safeIntCast(long value) {
        long high = value & 0xFFFFFFFF00000000L;
        if (high != 0L && high != -4294967296L) {
            throw new RuntimeException("Value " + value + " = 0x" + Long.toHexString(value) + " is not within the int range");
        }
        return (int)(value & 0xFFFFFFFFFFFFFFFFL);
    }

    public int intValue() {
        return AbstractIntegral.safeIntCast(this.value);
    }

    public long longValue() {
        return this.value;
    }

    public float floatValue() {
        return this.value;
    }

    public double doubleValue() {
        return this.value;
    }

    public boolean equals(Object o2) {
        if (o2 == null || !(o2 instanceof AbstractIntegral)) {
            return false;
        }
        if (!o2.getClass().equals(this.getClass())) {
            return false;
        }
        return this.value == ((AbstractIntegral)o2).value;
    }

    public int hashCode() {
        return Long.valueOf(this.value).hashCode();
    }

    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.value + ")";
    }
}

