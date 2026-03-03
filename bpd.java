/*
 * Decompiled with CFR 0.152.
 */
public class bpd
extends bpb {
    private final va k;
    private float l = 0.0f;

    public bpd(va va2) {
        super(new jy("minecraft:minecart.base"));
        this.k = va2;
        this.g = true;
        this.h = 0;
    }

    @Override
    public void c() {
        if (this.k.I) {
            this.j = true;
            return;
        }
        this.d = (float)this.k.s;
        this.e = (float)this.k.t;
        this.f = (float)this.k.u;
        float f2 = ns.a(this.k.v * this.k.v + this.k.x * this.k.x);
        if ((double)f2 >= 0.01) {
            this.l = ns.a(this.l + 0.0025f, 0.0f, 1.0f);
            this.b = 0.0f + ns.a(f2, 0.0f, 0.5f) * 0.7f;
        } else {
            this.l = 0.0f;
            this.b = 0.0f;
        }
    }
}

