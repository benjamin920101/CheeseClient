/*
 * Decompiled with CFR 0.152.
 */
public class bev
extends beu {
    private final avh e;

    public bev(avh avh2) {
        this.e = avh2;
    }

    @Override
    public void a() {
        this.a = 0.0f;
        this.b = 0.0f;
        if (this.e.X.d()) {
            this.b += 1.0f;
        }
        if (this.e.Z.d()) {
            this.b -= 1.0f;
        }
        if (this.e.Y.d()) {
            this.a += 1.0f;
        }
        if (this.e.aa.d()) {
            this.a -= 1.0f;
        }
        this.c = this.e.ab.d();
        this.d = this.e.ac.d();
        if (this.d) {
            this.a = (float)((double)this.a * 0.3);
            this.b = (float)((double)this.b * 0.3);
        }
    }
}

