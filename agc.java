/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;

public class agc
extends afe {
    public static final amm<afe.b> b = amm.a("shape", afe.b.class, new Predicate<afe.b>(){

        public boolean a(afe.b b2) {
            return b2 != afe.b.j && b2 != afe.b.i && b2 != afe.b.g && b2 != afe.b.h;
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((afe.b)object);
        }
    });
    public static final amk N = amk.a("powered");

    public agc() {
        super(true);
        this.j(this.M.b().a(N, false).a(b, afe.b.a));
        this.a(true);
    }

    @Override
    public int a(adm adm2) {
        return 20;
    }

    @Override
    public boolean i() {
        return true;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pk pk2) {
        if (adm2.D) {
            return;
        }
        if (alz2.b(N).booleanValue()) {
            return;
        }
        this.e(adm2, cj2, alz2);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, Random random) {
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (adm2.D || !alz2.b(N).booleanValue()) {
            return;
        }
        this.e(adm2, cj2, alz2);
    }

    @Override
    public int a(adq adq2, cj cj2, alz alz2, cq cq2) {
        return alz2.b(N) != false ? 15 : 0;
    }

    @Override
    public int b(adq adq2, cj cj2, alz alz2, cq cq2) {
        if (!alz2.b(N).booleanValue()) {
            return 0;
        }
        return cq2 == cq.b ? 15 : 0;
    }

    private void e(adm adm2, cj cj2, alz alz2) {
        boolean bl2 = alz2.b(N);
        \u2603 = false;
        List<va> \u26032 = this.a(adm2, cj2, va.class, new Predicate[0]);
        if (!\u26032.isEmpty()) {
            \u2603 = true;
        }
        if (\u2603 && !bl2) {
            adm2.a(cj2, alz2.a(N, true), 3);
            adm2.c(cj2, this);
            adm2.c(cj2.b(), this);
            adm2.b(cj2, cj2);
        }
        if (!\u2603 && bl2) {
            adm2.a(cj2, alz2.a(N, false), 3);
            adm2.c(cj2, this);
            adm2.c(cj2.b(), this);
            adm2.b(cj2, cj2);
        }
        if (\u2603) {
            adm2.a(cj2, (afh)this, this.a(adm2));
        }
        adm2.e(cj2, this);
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        super.c(adm2, cj2, alz2);
        this.e(adm2, cj2, alz2);
    }

    @Override
    public amo<afe.b> n() {
        return b;
    }

    @Override
    public boolean O() {
        return true;
    }

    @Override
    public int l(adm adm2, cj cj2) {
        if (adm2.p(cj2).b(N).booleanValue()) {
            List<vc> list = this.a(adm2, cj2, vc.class, new Predicate[0]);
            if (!list.isEmpty()) {
                return list.get(0).j().j();
            }
            List<va> \u26032 = this.a(adm2, cj2, va.class, po.c);
            if (!\u26032.isEmpty()) {
                return xi.b((og)((Object)\u26032.get(0)));
            }
        }
        return 0;
    }

    protected <T extends va> List<T> a(adm adm2, cj cj2, Class<T> clazz, Predicate<pk> ... predicateArray) {
        aug aug2 = this.a(cj2);
        if (predicateArray.length != 1) {
            return adm2.a(clazz, aug2);
        }
        return adm2.a(clazz, aug2, predicateArray[0]);
    }

    private aug a(cj cj2) {
        float f2 = 0.2f;
        return new aug((float)cj2.n() + 0.2f, cj2.o(), (float)cj2.p() + 0.2f, (float)(cj2.n() + 1) - 0.2f, (float)(cj2.o() + 1) - 0.2f, (float)(cj2.p() + 1) - 0.2f);
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(b, afe.b.a(n2 & 7)).a(N, (n2 & 8) > 0);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(b).a();
        if (alz2.b(N).booleanValue()) {
            n2 |= 8;
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, b, N);
    }
}

