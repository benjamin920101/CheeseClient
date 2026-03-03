/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class aez
extends agr {
    public static final aml a = aml.a("facing", cq.c.a);
    public static final amn b = amn.a("damage", 0, 2);

    protected aez() {
        super(arm.g);
        this.j(this.M.b().a(a, cq.c).a(b, 0));
        this.e(0);
        this.a(yz.c);
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        cq cq3 = pr2.aP().e();
        return super.a(adm2, cj2, cq2, f2, f3, f4, n2, pr2).a(a, cq3).a(b, n2 >> 2);
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (!adm2.D) {
            wn2.a(new a(adm2, cj2));
        }
        return true;
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(b);
    }

    @Override
    public void a(adq adq2, cj cj2) {
        cq cq2 = adq2.p(cj2).b(a);
        if (cq2.k() == cq.a.a) {
            this.a(0.0f, 0.0f, 0.125f, 1.0f, 1.0f, 0.875f);
        } else {
            this.a(0.125f, 0.0f, 0.0f, 0.875f, 1.0f, 1.0f);
        }
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        list.add(new zx(zw2, 1, 0));
        list.add(new zx(zw2, 1, 1));
        list.add(new zx(zw2, 1, 2));
    }

    @Override
    protected void a(uy uy2) {
        uy2.a(true);
    }

    @Override
    public void a_(adm adm2, cj cj2) {
        adm2.b(1022, cj2, 0);
    }

    @Override
    public boolean a(adq adq2, cj cj2, cq cq2) {
        return true;
    }

    @Override
    public alz b(alz alz2) {
        return this.Q().a(a, cq.d);
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, cq.b(n2 & 3)).a(b, (n2 & 0xF) >> 2);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(a).b();
        return n2 |= alz2.b(b) << 2;
    }

    @Override
    protected ama e() {
        return new ama(this, a, b);
    }

    public static class a
    implements ol {
        private final adm a;
        private final cj b;

        public a(adm adm2, cj cj2) {
            this.a = adm2;
            this.b = cj2;
        }

        @Override
        public String e_() {
            return "anvil";
        }

        @Override
        public boolean l_() {
            return false;
        }

        @Override
        public eu f_() {
            return new fb(afi.cf.a() + ".name", new Object[0]);
        }

        @Override
        public xi a(wm wm2, wn wn2) {
            return new xk(wm2, this.a, this.b, wn2);
        }

        @Override
        public String k() {
            return "minecraft:anvil";
        }
    }
}

