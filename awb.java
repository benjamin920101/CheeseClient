/*
 * Decompiled with CFR 0.152.
 */
public class awb
extends avs {
    private boolean o;
    private String p;
    private final awg.b q;

    public awb(awg.b b2, int n2, int n3, int n4, String string, boolean bl2) {
        super(n2, n3, n4, 150, 20, "");
        this.p = string;
        this.o = bl2;
        this.j = this.c();
        this.q = b2;
    }

    private String c() {
        return bnq.a(this.p, new Object[0]) + ": " + (this.o ? bnq.a("gui.yes", new Object[0]) : bnq.a("gui.no", new Object[0]));
    }

    public void b(boolean bl2) {
        this.o = bl2;
        this.j = this.c();
        this.q.a(this.k, bl2);
    }

    @Override
    public boolean c(ave ave2, int n2, int n3) {
        if (super.c(ave2, n2, n3)) {
            this.o = !this.o;
            this.j = this.c();
            this.q.a(this.k, this.o);
            return true;
        }
        return false;
    }
}

