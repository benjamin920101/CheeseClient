/*
 * Decompiled with CFR 0.152.
 */
public class awu
extends axu {
    private static final avh.a[] a = new avh.a[]{avh.a.p, avh.a.q, avh.a.r, avh.a.s, avh.a.t, avh.a.z, avh.a.B, avh.a.C, avh.a.A, avh.a.Q};
    private final axu f;
    private final avh g;
    private String h;

    public awu(axu axu2, avh avh2) {
        this.f = axu2;
        this.g = avh2;
    }

    @Override
    public void b() {
        int n2 = 0;
        this.h = bnq.a("options.chat.title", new Object[0]);
        for (avh.a a2 : a) {
            if (a2.a()) {
                this.n.add(new awj(a2.c(), this.l / 2 - 155 + n2 % 2 * 160, this.m / 6 + 24 * (n2 >> 1), a2));
            } else {
                this.n.add(new awe(a2.c(), this.l / 2 - 155 + n2 % 2 * 160, this.m / 6 + 24 * (n2 >> 1), a2, this.g.c(a2)));
            }
            ++n2;
        }
        this.n.add(new avs(200, this.l / 2 - 100, this.m / 6 + 120, bnq.a("gui.done", new Object[0])));
    }

    @Override
    protected void a(avs avs2) {
        if (!avs2.l) {
            return;
        }
        if (avs2.k < 100 && avs2 instanceof awe) {
            this.g.a(((awe)avs2).c(), 1);
            avs2.j = this.g.c(avh.a.a(avs2.k));
        }
        if (avs2.k == 200) {
            this.j.t.b();
            this.j.a(this.f);
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.a(this.q, this.h, this.l / 2, 20, 0xFFFFFF);
        super.a(n2, n3, f2);
    }
}

