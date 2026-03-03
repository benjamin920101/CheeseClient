/*
 * Decompiled with CFR 0.152.
 */
public class axx
extends axu {
    private final axu a;
    private String f;

    public axx(axu axu2) {
        this.a = axu2;
    }

    @Override
    public void b() {
        int n2 = 0;
        this.f = bnq.a("options.skinCustomisation.title", new Object[0]);
        for (wo wo2 : wo.values()) {
            this.n.add(new a(wo2.b(), this.l / 2 - 155 + n2 % 2 * 160, this.m / 6 + 24 * (n2 >> 1), 150, 20, wo2));
            ++n2;
        }
        if (n2 % 2 == 1) {
            ++n2;
        }
        this.n.add(new avs(200, this.l / 2 - 100, this.m / 6 + 24 * (n2 >> 1), bnq.a("gui.done", new Object[0])));
    }

    @Override
    protected void a(avs avs2) {
        if (!avs2.l) {
            return;
        }
        if (avs2.k == 200) {
            this.j.t.b();
            this.j.a(this.a);
        } else if (avs2 instanceof a) {
            wo wo2 = ((a)avs2).p;
            this.j.t.a(wo2);
            avs2.j = this.a(wo2);
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.a(this.q, this.f, this.l / 2, 20, 0xFFFFFF);
        super.a(n2, n3, f2);
    }

    private String a(wo wo2) {
        String string = this.j.t.d().contains((Object)wo2) ? bnq.a("options.on", new Object[0]) : bnq.a("options.off", new Object[0]);
        return wo2.d().d() + ": " + string;
    }

    class a
    extends avs {
        private final wo p;

        private a(int n2, int n3, int n4, int n5, int n6, wo wo2) {
            super(n2, n3, n4, n5, n6, axx.this.a(wo2));
            this.p = wo2;
        }
    }
}

