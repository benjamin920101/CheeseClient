/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.tuple;

import net.dv8tion.jda.internal.utils.tuple.MutablePair;

public class MutableTriple<LEFT, MIDDLE, RIGHT>
extends MutablePair<LEFT, RIGHT> {
    public MIDDLE middle;

    private MutableTriple(LEFT left, MIDDLE middle, RIGHT right) {
        super(left, right);
        this.middle = middle;
    }

    public static <LEFT, MIDDLE, RIGHT> MutableTriple<LEFT, MIDDLE, RIGHT> of(LEFT left, MIDDLE middle, RIGHT right) {
        return new MutableTriple<LEFT, MIDDLE, RIGHT>(left, middle, right);
    }

    public MIDDLE getMiddle() {
        return this.middle;
    }

    public void setMiddle(MIDDLE middle) {
        this.middle = middle;
    }
}

