/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.util;

import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Pair<U, V>
implements Comparable<Pair<U, V>>,
Map.Entry<U, V> {
    private U first;
    private V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public Pair() {
    }

    public U getFirst() {
        return this.first;
    }

    public V getSecond() {
        return this.second;
    }

    public void setFirst(U first) {
        this.first = first;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    @Override
    public int compareTo(Pair<U, V> o2) {
        Comparable cv2;
        Comparable cu2 = (Comparable)this.getFirst();
        if (cu2 == null) {
            if (this.first != null) {
                return 1;
            }
        } else {
            int d2 = cu2.compareTo(o2.getFirst());
            if (d2 != 0) {
                return d2;
            }
        }
        if ((cv2 = (Comparable)this.getSecond()) == null) {
            return this.second != null ? 1 : -1;
        }
        return cv2.compareTo(o2.getSecond());
    }

    public String toString() {
        return "Pair(" + this.first + ", " + this.second + ")";
    }

    @Override
    public U getKey() {
        return this.first;
    }

    @Override
    public V getValue() {
        return this.second;
    }

    @Override
    public V setValue(V value) {
        V oldValue = this.second;
        this.second = value;
        return oldValue;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.first == null ? 0 : this.first.hashCode());
        result = 31 * result + (this.second == null ? 0 : this.second.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Pair other = (Pair)obj;
        if (this.first == null ? other.first != null : !this.first.equals(other.first)) {
            return false;
        }
        return !(this.second == null ? other.second != null : !this.second.equals(other.second));
    }

    public boolean isFull() {
        return this.getFirst() != null && this.getSecond() != null;
    }

    public boolean isEmpty() {
        return this.getFirst() == null && this.getSecond() == null;
    }
}

