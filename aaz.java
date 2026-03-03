/*
 * Decompiled with CFR 0.152.
 */
public class aaz
extends yo {
    private final afh b;
    private String[] c;

    public aaz(afh afh2, boolean bl2) {
        super(afh2);
        this.b = afh2;
        if (bl2) {
            this.d(0);
            this.a(true);
        }
    }

    @Override
    public int a(zx zx2, int n2) {
        return this.b.h(this.b.a(zx2.i()));
    }

    @Override
    public int a(int n2) {
        return n2;
    }

    public aaz a(String[] stringArray) {
        this.c = stringArray;
        return this;
    }

    @Override
    public String e_(zx zx2) {
        if (this.c == null) {
            return super.e_(zx2);
        }
        int n2 = zx2.i();
        if (n2 >= 0 && n2 < this.c.length) {
            return super.e_(zx2) + "." + this.c[n2];
        }
        return super.e_(zx2);
    }
}

