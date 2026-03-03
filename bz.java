/*
 * Decompiled with CFR 0.152.
 */
public class bz
extends Exception {
    private final Object[] a;

    public bz(String string, Object ... objectArray) {
        super(string);
        this.a = objectArray;
    }

    public Object[] a() {
        return this.a;
    }
}

