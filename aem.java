/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.Random;

public class aem
extends ady {
    protected ady aE;

    public aem(int n2, ady ady2) {
        super(n2);
        this.aE = ady2;
        this.a(ady2.ai, true);
        this.ah = ady2.ah + " M";
        this.ak = ady2.ak;
        this.al = ady2.al;
        this.am = ady2.am;
        this.an = ady2.an;
        this.ao = ady2.ao;
        this.ap = ady2.ap;
        this.aq = ady2.aq;
        this.ar = ady2.ar;
        this.ax = ady2.ax;
        this.ay = ady2.ay;
        this.au = Lists.newArrayList(ady2.au);
        this.at = Lists.newArrayList(ady2.at);
        this.aw = Lists.newArrayList(ady2.aw);
        this.av = Lists.newArrayList(ady2.av);
        this.ap = ady2.ap;
        this.aq = ady2.aq;
        this.an = ady2.an + 0.1f;
        this.ao = ady2.ao + 0.2f;
    }

    @Override
    public void a(adm adm2, Random random, cj cj2) {
        this.aE.as.a(adm2, random, this, cj2);
    }

    @Override
    public void a(adm adm2, Random random, ans ans2, int n2, int n3, double d2) {
        this.aE.a(adm2, random, ans2, n2, n3, d2);
    }

    @Override
    public float g() {
        return this.aE.g();
    }

    @Override
    public aoh a(Random random) {
        return this.aE.a(random);
    }

    @Override
    public int c(cj cj2) {
        return this.aE.c(cj2);
    }

    @Override
    public int b(cj cj2) {
        return this.aE.b(cj2);
    }

    @Override
    public Class<? extends ady> l() {
        return this.aE.l();
    }

    @Override
    public boolean a(ady ady2) {
        return this.aE.a(ady2);
    }

    @Override
    public ady.b m() {
        return this.aE.m();
    }
}

