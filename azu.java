/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public abstract class azu
extends awd {
    protected final ave u;
    protected final List<azp> v;

    public azu(ave ave2, int n2, int n3, List<azp> list) {
        super(ave2, n2, n3, 32, n3 - 55 + 4, 36);
        this.u = ave2;
        this.v = list;
        this.k = false;
        this.a(true, (int)((float)ave2.k.a * 1.5f));
    }

    @Override
    protected void a(int n2, int n3, bfx bfx2) {
        String string = (Object)((Object)a.t) + "" + (Object)((Object)a.r) + this.e();
        this.u.k.a(string, n2 + this.b / 2 - this.u.k.a(string) / 2, Math.min(this.d + 3, n3), 0xFFFFFF);
    }

    protected abstract String e();

    public List<azp> f() {
        return this.v;
    }

    @Override
    protected int b() {
        return this.f().size();
    }

    public azp c(int n2) {
        return this.f().get(n2);
    }

    @Override
    public int c() {
        return this.b;
    }

    @Override
    protected int d() {
        return this.f - 6;
    }

    @Override
    public /* synthetic */ awd.a b(int n2) {
        return this.c(n2);
    }
}

