/*
 * Decompiled with CFR 0.152.
 */
public enum pt {
    a(vq.class, 70, arm.a, false, false),
    b(tm.class, 10, arm.a, true, true),
    c(tj.class, 15, arm.a, true, false),
    d(tz.class, 5, arm.h, true, false);

    private final Class<? extends pi> e;
    private final int f;
    private final arm g;
    private final boolean h;
    private final boolean i;

    private pt(Class<? extends pi> clazz, int n3, arm arm2, boolean bl2, boolean bl3) {
        this.e = clazz;
        this.f = n3;
        this.g = arm2;
        this.h = bl2;
        this.i = bl3;
    }

    public Class<? extends pi> a() {
        return this.e;
    }

    public int b() {
        return this.f;
    }

    public boolean d() {
        return this.h;
    }

    public boolean e() {
        return this.i;
    }
}

