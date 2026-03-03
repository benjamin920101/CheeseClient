/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.tuple;

import java.io.Serializable;
import java.util.Objects;
import net.dv8tion.jda.internal.utils.tuple.ImmutablePair;

public abstract class Pair<L, R>
implements Serializable {
    public static <L, R> Pair<L, R> of(L left, R right) {
        return new ImmutablePair<L, R>(left, right);
    }

    public abstract L getLeft();

    public abstract R getRight();

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Pair) {
            Pair other = (Pair)obj;
            return Objects.equals(this.getLeft(), other.getLeft()) && Objects.equals(this.getRight(), other.getRight());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.getLeft()) ^ Objects.hashCode(this.getRight());
    }

    public String toString() {
        return "(" + this.getLeft() + ',' + this.getRight() + ')';
    }
}

