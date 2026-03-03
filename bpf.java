/*
 * Decompiled with CFR 0.152.
 */
public class bpf
extends bpa {
    public static bpf a(jy jy2, float f2) {
        return new bpf(jy2, 0.25f, f2, false, 0, bpj.a.a, 0.0f, 0.0f, 0.0f);
    }

    public static bpf a(jy jy2) {
        return new bpf(jy2, 1.0f, 1.0f, false, 0, bpj.a.a, 0.0f, 0.0f, 0.0f);
    }

    public static bpf a(jy jy2, float f2, float f3, float f4) {
        return new bpf(jy2, 4.0f, 1.0f, false, 0, bpj.a.b, f2, f3, f4);
    }

    public bpf(jy jy2, float f2, float f3, float f4, float f5, float f6) {
        this(jy2, f2, f3, false, 0, bpj.a.b, f4, f5, f6);
    }

    private bpf(jy jy2, float f2, float f3, boolean bl2, int n2, bpj.a a2, float f4, float f5, float f6) {
        super(jy2);
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.e = f5;
        this.f = f6;
        this.g = bl2;
        this.h = n2;
        this.i = a2;
    }
}

