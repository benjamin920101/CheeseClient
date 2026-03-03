/*
 * Decompiled with CFR 0.152.
 */
public abstract class no<T> {
    private T a;
    private boolean b = false;

    public T c() {
        if (!this.b) {
            this.b = true;
            this.a = this.b();
        }
        return this.a;
    }

    protected abstract T b();
}

