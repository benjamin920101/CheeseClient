/*
 * Decompiled with CFR 0.152.
 */
public class avz
extends avs {
    public avz(int n2, int n3, int n4) {
        super(n2, n3, n4, 20, 20, "");
    }

    @Override
    public void a(ave ave2, int n2, int n3) {
        if (!this.m) {
            return;
        }
        ave2.P().a(avs.a);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        boolean bl2 = n2 >= this.h && n3 >= this.i && n2 < this.h + this.f && n3 < this.i + this.g;
        int \u26032 = 106;
        if (bl2) {
            \u26032 += this.g;
        }
        this.b(this.h, this.i, 0, \u26032, this.f, this.g);
    }
}

