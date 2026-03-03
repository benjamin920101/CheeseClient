/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.util;

public class Tuple {
    protected final Object[] data;

    public Tuple(Object[] data) {
        this.data = data;
    }

    public Tuple(int n2) {
        this.data = new Object[n2];
    }

    public int hashCode() {
        int h2 = 0;
        int n2 = this.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            Object o2 = this.get(i2);
            if (o2 == null) continue;
            h2 ^= o2.hashCode();
        }
        return h2;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Tuple)) {
            return false;
        }
        Tuple t2 = (Tuple)obj;
        int s2 = this.size();
        if (t2.size() != s2) {
            return false;
        }
        for (int i2 = 0; i2 < s2; ++i2) {
            Object o1 = this.get(i2);
            Object o2 = t2.get(i2);
            if (!(o1 == null ? o2 != null : !o1.equals(o2))) continue;
            return false;
        }
        return true;
    }

    public Object get(int index) {
        return this.data[index];
    }

    public void set(int index, Object value) {
        this.data[index] = value;
    }

    public int size() {
        return this.data.length;
    }

    public String toString() {
        StringBuilder b2 = new StringBuilder("{");
        int n2 = this.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (i2 != 0) {
                b2.append(',');
            }
            b2.append('\n');
            b2.append('\t').append(this.get(i2));
        }
        b2.append("\n}");
        return b2.toString();
    }
}

