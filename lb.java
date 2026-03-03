/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class lb
implements ado {
    private MinecraftServer a;
    private le b;

    public lb(MinecraftServer minecraftServer, le le2) {
        this.a = minecraftServer;
        this.b = le2;
    }

    @Override
    public void a(int n2, boolean bl2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
    }

    @Override
    public void a(pk pk2) {
        this.b.s().a(pk2);
    }

    @Override
    public void b(pk pk2) {
        this.b.s().b(pk2);
        this.b.Z().a(pk2);
    }

    @Override
    public void a(String string, double d2, double d3, double d4, float f2, float f3) {
        this.a.ap().a(d2, d3, d4, f2 > 1.0f ? (double)(16.0f * f2) : 16.0, this.b.t.q(), new gs(string, d2, d3, d4, f2, f3));
    }

    @Override
    public void a(wn wn2, String string, double d2, double d3, double d4, float f2, float f3) {
        this.a.ap().a(wn2, d2, d3, d4, f2 > 1.0f ? (double)(16.0f * f2) : 16.0, this.b.t.q(), new gs(string, d2, d3, d4, f2, f3));
    }

    @Override
    public void a(int n2, int n3, int n4, int n5, int n6, int n7) {
    }

    @Override
    public void a(cj cj2) {
        this.b.t().a(cj2);
    }

    @Override
    public void b(cj cj2) {
    }

    @Override
    public void a(String string, cj cj2) {
    }

    @Override
    public void a(wn wn2, int n2, cj cj2, int n3) {
        this.a.ap().a(wn2, cj2.n(), cj2.o(), cj2.p(), 64.0, this.b.t.q(), new gq(n2, cj2, n3, false));
    }

    @Override
    public void a(int n2, cj cj2, int n3) {
        this.a.ap().a(new gq(n2, cj2, n3, true));
    }

    @Override
    public void b(int n2, cj cj2, int n3) {
        for (lf lf2 : this.a.ap().v()) {
            if (lf2 == null || lf2.o != this.b || lf2.F() == n2 || !((\u2603 = (double)cj2.n() - lf2.s) * \u2603 + (\u2603 = (double)cj2.o() - lf2.t) * \u2603 + (\u2603 = (double)cj2.p() - lf2.u) * \u2603 < 1024.0)) continue;
            lf2.a.a(new fs(n2, cj2, n3));
        }
    }
}

