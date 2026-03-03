/*
 * Decompiled with CFR 0.152.
 */
public class aww
extends awy {
    private final String r;
    private final String s;
    private final String t;
    private boolean u = true;

    public aww(awx awx2, String string, int n2, boolean bl2) {
        super(awx2, bnq.a(bl2 ? "chat.link.confirmTrusted" : "chat.link.confirm", new Object[0]), string, n2);
        this.g = bnq.a(bl2 ? "chat.link.open" : "gui.yes", new Object[0]);
        this.h = bnq.a(bl2 ? "gui.cancel" : "gui.no", new Object[0]);
        this.s = bnq.a("chat.copy", new Object[0]);
        this.r = bnq.a("chat.link.warning", new Object[0]);
        this.t = string;
    }

    @Override
    public void b() {
        super.b();
        this.n.clear();
        this.n.add(new avs(0, this.l / 2 - 50 - 105, this.m / 6 + 96, 100, 20, this.g));
        this.n.add(new avs(2, this.l / 2 - 50, this.m / 6 + 96, 100, 20, this.s));
        this.n.add(new avs(1, this.l / 2 - 50 + 105, this.m / 6 + 96, 100, 20, this.h));
    }

    @Override
    protected void a(avs avs2) {
        if (avs2.k == 2) {
            this.a();
        }
        this.a.a(avs2.k == 0, this.i);
    }

    public void a() {
        aww.e(this.t);
    }

    @Override
    public void a(int n2, int n3, float f2) {
        super.a(n2, n3, f2);
        if (this.u) {
            this.a(this.q, this.r, this.l / 2, 110, 0xFFCCCC);
        }
    }

    public void f() {
        this.u = false;
    }
}

