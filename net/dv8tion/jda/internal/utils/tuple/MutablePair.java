/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.tuple;

import net.dv8tion.jda.internal.utils.tuple.Pair;

public class MutablePair<L, R>
extends Pair<L, R> {
    public L left;
    public R right;

    public static <L, R> MutablePair<L, R> of(L left, R right) {
        return new MutablePair<L, R>(left, right);
    }

    public MutablePair() {
    }

    public MutablePair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public L getLeft() {
        return this.left;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    @Override
    public R getRight() {
        return this.right;
    }

    public void setRight(R right) {
        this.right = right;
    }
}

