/*
 * Decompiled with CFR 0.152.
 */
public class axr
extends axu
implements nu {
    private String a = "";
    private String f = "";
    private int g;
    private boolean h;

    @Override
    public void a(String string) {
        this.b(string);
    }

    @Override
    public void b(String string) {
        this.a = string;
        this.c("Working...");
    }

    @Override
    public void c(String string) {
        this.f = string;
        this.a(0);
    }

    @Override
    public void a(int n2) {
        this.g = n2;
    }

    @Override
    public void a() {
        this.h = true;
    }

    @Override
    public void a(int n2, int n3, float f2) {
        if (this.h) {
            if (!this.j.al()) {
                this.j.a((axu)null);
            }
            return;
        }
        this.c();
        this.a(this.q, this.a, this.l / 2, 70, 0xFFFFFF);
        this.a(this.q, this.f + " " + this.g + "%", this.l / 2, 90, 0xFFFFFF);
        super.a(n2, n3, f2);
    }
}

