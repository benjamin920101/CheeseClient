/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class avy
extends avp {
    protected int a = 200;
    protected int f = 20;
    public int g;
    public int h;
    private List<String> k;
    public int i;
    private boolean l;
    public boolean j = true;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private int q;
    private avn r;
    private int s;

    public avy(avn avn2, int n2, int n3, int n4, int n5, int n6, int n7) {
        this.r = avn2;
        this.i = n2;
        this.g = n3;
        this.h = n4;
        this.a = n5;
        this.f = n6;
        this.k = Lists.newArrayList();
        this.l = false;
        this.m = false;
        this.n = n7;
        this.o = -1;
        this.p = -1;
        this.q = -1;
        this.s = 0;
    }

    public void a(String string) {
        this.k.add(bnq.a(string, new Object[0]));
    }

    public avy a() {
        this.l = true;
        return this;
    }

    public void a(ave ave2, int n2, int n3) {
        if (!this.j) {
            return;
        }
        bfl.l();
        bfl.a(770, 771, 1, 0);
        this.b(ave2, n2, n3);
        \u2603 = this.h + this.f / 2 + this.s / 2;
        \u2603 = \u2603 - this.k.size() * 10 / 2;
        for (\u2603 = 0; \u2603 < this.k.size(); ++\u2603) {
            if (this.l) {
                this.a(this.r, this.k.get(\u2603), this.g + this.a / 2, \u2603 + \u2603 * 10, this.n);
                continue;
            }
            this.c(this.r, this.k.get(\u2603), this.g, \u2603 + \u2603 * 10, this.n);
        }
    }

    protected void b(ave ave2, int n2, int n3) {
        if (this.m) {
            \u2603 = this.a + this.s * 2;
            \u2603 = this.f + this.s * 2;
            \u2603 = this.g - this.s;
            \u2603 = this.h - this.s;
            avy.a(\u2603, \u2603, \u2603 + \u2603, \u2603 + \u2603, this.o);
            this.a(\u2603, \u2603 + \u2603, \u2603, this.p);
            this.a(\u2603, \u2603 + \u2603, \u2603 + \u2603, this.q);
            this.b(\u2603, \u2603, \u2603 + \u2603, this.p);
            this.b(\u2603 + \u2603, \u2603, \u2603 + \u2603, this.q);
        }
    }
}

