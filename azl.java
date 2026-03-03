/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class azl
extends awd {
    private final azh u;
    private final List<azk> v = Lists.newArrayList();
    private final List<azj> w = Lists.newArrayList();
    private final awd.a x = new azi();
    private int y = -1;

    public azl(azh azh2, ave ave2, int n2, int n3, int n4, int n5, int n6) {
        super(ave2, n2, n3, n4, n5, n6);
        this.u = azh2;
    }

    @Override
    public awd.a b(int n2) {
        if (n2 < this.v.size()) {
            return this.v.get(n2);
        }
        if ((n2 -= this.v.size()) == 0) {
            return this.x;
        }
        return this.w.get(--n2);
    }

    @Override
    protected int b() {
        return this.v.size() + 1 + this.w.size();
    }

    public void c(int n2) {
        this.y = n2;
    }

    @Override
    protected boolean a(int n2) {
        return n2 == this.y;
    }

    public int e() {
        return this.y;
    }

    public void a(bdf bdf2) {
        this.v.clear();
        for (int i2 = 0; i2 < bdf2.c(); ++i2) {
            this.v.add(new azk(this.u, bdf2.a(i2)));
        }
    }

    public void a(List<bpq.a> list) {
        this.w.clear();
        for (bpq.a a2 : list) {
            this.w.add(new azj(this.u, a2));
        }
    }

    @Override
    protected int d() {
        return super.d() + 30;
    }

    @Override
    public int c() {
        return super.c() + 85;
    }
}

