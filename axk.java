/*
 * Decompiled with CFR 0.152.
 */
public class axk
extends awv {
    @Override
    public void b() {
        super.b();
        this.n.add(new avs(1, this.l / 2 - 100, this.m - 40, bnq.a("multiplayer.stopSleeping", new Object[0])));
    }

    @Override
    protected void a(char c22, int n2) {
        if (n2 == 1) {
            this.f();
        } else if (n2 == 28 || n2 == 156) {
            String string = this.a.b().trim();
            if (!string.isEmpty()) {
                this.j.h.e(string);
            }
            this.a.a("");
            this.j.q.d().d();
        } else {
            char c22;
            super.a(c22, n2);
        }
    }

    @Override
    protected void a(avs avs2) {
        if (avs2.k == 1) {
            this.f();
        } else {
            super.a(avs2);
        }
    }

    private void f() {
        bcy bcy2 = this.j.h.a;
        bcy2.a(new is(this.j.h, is.a.c));
    }
}

