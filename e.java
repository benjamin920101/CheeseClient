/*
 * Decompiled with CFR 0.152.
 */
public class e
extends RuntimeException {
    private final b a;

    public e(b b2) {
        this.a = b2;
    }

    public b a() {
        return this.a;
    }

    @Override
    public Throwable getCause() {
        return this.a.b();
    }

    @Override
    public String getMessage() {
        return this.a.a();
    }
}

