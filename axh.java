/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class axh
extends axu {
    private String a;
    private eu f;
    private List<String> g;
    private final axu h;
    private int i;

    public axh(axu axu2, String string, eu eu2) {
        this.h = axu2;
        this.a = bnq.a(string, new Object[0]);
        this.f = eu2;
    }

    @Override
    protected void a(char c2, int n2) {
    }

    @Override
    public void b() {
        this.n.clear();
        this.g = this.q.c(this.f.d(), this.l - 50);
        this.i = this.g.size() * this.q.a;
        this.n.add(new avs(0, this.l / 2 - 100, this.m / 2 + this.i / 2 + this.q.a, bnq.a("gui.toMenu", new Object[0])));
    }

    @Override
    protected void a(avs avs2) {
        if (avs2.k == 0) {
            this.j.a(this.h);
        }
    }

    @Override
    public void a(int n22, int n3, float f2) {
        int n22;
        this.c();
        this.a(this.q, this.a, this.l / 2, this.m / 2 - this.i / 2 - this.q.a * 2, 0xAAAAAA);
        int n4 = this.m / 2 - this.i / 2;
        if (this.g != null) {
            for (String string : this.g) {
                this.a(this.q, string, this.l / 2, n4, 0xFFFFFF);
                n4 += this.q.a;
            }
        }
        super.a(n22, n3, f2);
    }
}

