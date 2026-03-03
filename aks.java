/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class aks
extends afh {
    public static final amm<zd> a = amm.a("color", zd.class);

    protected aks() {
        super(arm.r);
        this.j(this.M.b().a(a, zd.a));
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.0625f, 1.0f);
        this.a(true);
        this.a(yz.c);
        this.b(0);
    }

    @Override
    public arn g(alz alz2) {
        return alz2.b(a).e();
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public void j() {
        this.b(0);
    }

    @Override
    public void a(adq adq2, cj cj2) {
        this.b(0);
    }

    protected void b(int n2) {
        \u2603 = 0;
        float f2 = (float)(1 * (1 + \u2603)) / 16.0f;
        this.a(0.0f, 0.0f, 0.0f, 1.0f, f2, 1.0f);
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        return super.d(adm2, cj2) && this.e(adm2, cj2);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        this.e(adm2, cj2, alz2);
    }

    private boolean e(adm adm2, cj cj2, alz alz2) {
        if (!this.e(adm2, cj2)) {
            this.b(adm2, cj2, alz2, 0);
            adm2.g(cj2);
            return false;
        }
        return true;
    }

    private boolean e(adm adm2, cj cj2) {
        return !adm2.d(cj2.b());
    }

    @Override
    public boolean a(adq adq2, cj cj2, cq cq2) {
        if (cq2 == cq.b) {
            return true;
        }
        return super.a(adq2, cj2, cq2);
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (int i2 = 0; i2 < 16; ++i2) {
            list.add(new zx(zw2, 1, i2));
        }
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, zd.b(n2));
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }
}

