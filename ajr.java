/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ajr
extends afh {
    public static final amk a = amk.a("wet");

    protected ajr() {
        super(arm.m);
        this.j(this.M.b().a(a, false));
        this.a(yz.b);
    }

    @Override
    public String f() {
        return di.a(this.a() + ".dry.name");
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(a) != false ? 1 : 0;
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        this.e(adm2, cj2, alz2);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        this.e(adm2, cj2, alz2);
        super.a(adm2, cj2, alz2, afh2);
    }

    protected void e(adm adm2, cj cj2, alz alz2) {
        if (!alz2.b(a).booleanValue() && this.e(adm2, cj2)) {
            adm2.a(cj2, alz2.a(a, true), 2);
            adm2.b(2001, cj2, afh.a(afi.j));
        }
    }

    private boolean e(adm adm22, cj cj2) {
        LinkedList<nz<cj, Integer>> linkedList = Lists.newLinkedList();
        ArrayList<cj> \u26032 = Lists.newArrayList();
        linkedList.add(new nz<cj, Integer>(cj2, 0));
        int \u26033 = 0;
        while (!linkedList.isEmpty()) {
            nz nz2 = (nz)linkedList.poll();
            cj \u26034 = (cj)nz2.a();
            int \u26035 = (Integer)nz2.b();
            for (cq cq2 : cq.values()) {
                cj cj3 = \u26034.a(cq2);
                if (adm22.p(cj3).c().t() != arm.h) continue;
                adm22.a(cj3, afi.a.Q(), 2);
                \u26032.add(cj3);
                ++\u26033;
                if (\u26035 >= 6) continue;
                linkedList.add(new nz<cj, Integer>(cj3, \u26035 + 1));
            }
            if (\u26033 <= 64) continue;
            break;
        }
        for (cj \u26034 : \u26032) {
            adm adm22;
            adm22.c(\u26034, afi.a);
        }
        return \u26033 > 0;
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        list.add(new zx(zw2, 1, 0));
        list.add(new zx(zw2, 1, 1));
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, (n2 & 1) == 1);
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a) != false ? 1 : 0;
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2, Random random) {
        if (!alz2.b(a).booleanValue()) {
            return;
        }
        cq cq2 = cq.a(random);
        if (cq2 == cq.b || adm.a(adm2, cj2.a(cq2))) {
            return;
        }
        double \u26032 = cj2.n();
        double \u26033 = cj2.o();
        double \u26034 = cj2.p();
        if (cq2 == cq.a) {
            \u26033 -= 0.05;
            \u26032 += random.nextDouble();
            \u26034 += random.nextDouble();
        } else {
            \u26033 += random.nextDouble() * 0.8;
            if (cq2.k() == cq.a.a) {
                \u26034 += random.nextDouble();
                \u26032 = cq2 == cq.f ? (\u26032 += 1.1) : (\u26032 += 0.05);
            } else {
                \u26032 += random.nextDouble();
                \u26034 = cq2 == cq.d ? (\u26034 += 1.1) : (\u26034 += 0.05);
            }
        }
        adm2.a(cy.s, \u26032, \u26033, \u26034, 0.0, 0.0, 0.0, new int[0]);
    }
}

