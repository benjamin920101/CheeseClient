/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.strategy;

import java.io.Serializable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface HashingStrategy<T>
extends Serializable {
    public static final long serialVersionUID = 5674097166776615540L;

    public int computeHashCode(T var1);

    public boolean equals(T var1, T var2);
}

