/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ale
extends akw
implements km,
ol {
    public int a;
    public float f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    public float l;
    public float m;
    public float n;
    private static Random o = new Random();
    private String p;

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        if (this.l_()) {
            dn2.a("CustomName", this.p);
        }
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        if (dn2.b("CustomName", 8)) {
            this.p = dn2.j("CustomName");
        }
    }

    @Override
    public void c() {
        float f2;
        this.k = this.j;
        this.m = this.l;
        wn wn2 = this.b.a((float)this.c.n() + 0.5f, (float)this.c.o() + 0.5f, (double)((float)this.c.p() + 0.5f), 3.0);
        if (wn2 != null) {
            double d2 = wn2.s - (double)((float)this.c.n() + 0.5f);
            \u2603 = wn2.u - (double)((float)this.c.p() + 0.5f);
            this.n = (float)ns.b(\u2603, d2);
            this.j += 0.1f;
            if (this.j < 0.5f || o.nextInt(40) == 0) {
                float f3 = this.h;
                do {
                    this.h += (float)(o.nextInt(4) - o.nextInt(4));
                } while (f3 == this.h);
            }
        } else {
            this.n += 0.02f;
            this.j -= 0.1f;
        }
        while (this.l >= (float)Math.PI) {
            this.l -= (float)Math.PI * 2;
        }
        while (this.l < (float)(-Math.PI)) {
            this.l += (float)Math.PI * 2;
        }
        while (this.n >= (float)Math.PI) {
            this.n -= (float)Math.PI * 2;
        }
        while (this.n < (float)(-Math.PI)) {
            this.n += (float)Math.PI * 2;
        }
        for (f2 = this.n - this.l; f2 >= (float)Math.PI; f2 -= (float)Math.PI * 2) {
        }
        while (f2 < (float)(-Math.PI)) {
            f2 += (float)Math.PI * 2;
        }
        this.l += f2 * 0.4f;
        this.j = ns.a(this.j, 0.0f, 1.0f);
        ++this.a;
        this.g = this.f;
        \u2603 = (this.h - this.f) * 0.4f;
        \u2603 = 0.2f;
        \u2603 = ns.a(\u2603, -\u2603, \u2603);
        this.i += (\u2603 - this.i) * 0.9f;
        this.f += this.i;
    }

    @Override
    public String e_() {
        return this.l_() ? this.p : "container.enchant";
    }

    @Override
    public boolean l_() {
        return this.p != null && this.p.length() > 0;
    }

    public void a(String string) {
        this.p = string;
    }

    @Override
    public eu f_() {
        if (this.l_()) {
            return new fa(this.e_());
        }
        return new fb(this.e_(), new Object[0]);
    }

    @Override
    public xi a(wm wm2, wn wn2) {
        return new xs(wm2, this.b, this.c);
    }

    @Override
    public String k() {
        return "minecraft:enchanting_table";
    }
}

