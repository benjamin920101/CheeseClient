/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public class axl
extends axu {
    protected axu a;
    private a f;
    private final avh g;
    private final bns h;
    private awe i;
    private awe r;

    public axl(axu axu2, avh avh2, bns bns2) {
        this.a = axu2;
        this.g = avh2;
        this.h = bns2;
    }

    @Override
    public void b() {
        this.i = new awe(100, this.l / 2 - 155, this.m - 38, avh.a.E, this.g.c(avh.a.E));
        this.n.add(this.i);
        this.r = new awe(6, this.l / 2 - 155 + 160, this.m - 38, bnq.a("gui.done", new Object[0]));
        this.n.add(this.r);
        this.f = new a(this.j);
        this.f.d(7, 8);
    }

    @Override
    public void k() {
        super.k();
        this.f.p();
    }

    @Override
    protected void a(avs avs2) {
        if (!avs2.l) {
            return;
        }
        switch (avs2.k) {
            case 100: {
                if (!(avs2 instanceof awe)) break;
                this.g.a(((awe)avs2).c(), 1);
                avs2.j = this.g.c(avh.a.E);
                avr avr2 = new avr(this.j);
                int \u26032 = avr2.a();
                int \u26033 = avr2.b();
                this.a(this.j, \u26032, \u26033);
                break;
            }
            case 5: {
                break;
            }
            case 6: {
                this.j.a(this.a);
                break;
            }
            default: {
                this.f.a(avs2);
            }
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.f.a(n2, n3, f2);
        this.a(this.q, bnq.a("options.language", new Object[0]), this.l / 2, 16, 0xFFFFFF);
        this.a(this.q, "(" + bnq.a("options.languageWarning", new Object[0]) + ")", this.l / 2, this.m - 56, 0x808080);
        super.a(n2, n3, f2);
    }

    class a
    extends awi {
        private final List<String> v;
        private final Map<String, bnr> w;

        public a(ave ave2) {
            super(ave2, axl.this.l, axl.this.m, 32, axl.this.m - 65 + 4, 18);
            this.v = Lists.newArrayList();
            this.w = Maps.newHashMap();
            for (bnr bnr2 : axl.this.h.d()) {
                this.w.put(bnr2.a(), bnr2);
                this.v.add(bnr2.a());
            }
        }

        @Override
        protected int b() {
            return this.v.size();
        }

        @Override
        protected void a(int n2, boolean bl2, int n3, int n4) {
            bnr bnr2 = this.w.get(this.v.get(n2));
            axl.this.h.a(bnr2);
            ((axl)axl.this).g.aM = bnr2.a();
            this.a.e();
            axl.this.q.a(axl.this.h.a() || ((axl)axl.this).g.aN);
            axl.this.q.b(axl.this.h.b());
            ((axl)axl.this).r.j = bnq.a("gui.done", new Object[0]);
            ((axl)axl.this).i.j = axl.this.g.c(avh.a.E);
            axl.this.g.b();
        }

        @Override
        protected boolean a(int n2) {
            return this.v.get(n2).equals(axl.this.h.c().a());
        }

        @Override
        protected int k() {
            return this.b() * 18;
        }

        @Override
        protected void a() {
            axl.this.c();
        }

        @Override
        protected void a(int n2, int n3, int n4, int n5, int n6, int n7) {
            axl.this.q.b(true);
            axl.this.a(axl.this.q, this.w.get(this.v.get(n2)).toString(), this.b / 2, n4 + 1, 0xFFFFFF);
            axl.this.q.b(axl.this.h.c().b());
        }
    }
}

