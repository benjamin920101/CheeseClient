/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public abstract class bfh {
    private double c;
    private double d;
    private double e;
    protected List<bht> a = Lists.newArrayListWithCapacity(17424);
    protected boolean b;

    public void a(double d2, double d3, double d4) {
        this.b = true;
        this.a.clear();
        this.c = d2;
        this.d = d3;
        this.e = d4;
    }

    public void a(bht bht2) {
        cj cj2 = bht2.j();
        bfl.b((float)((double)cj2.n() - this.c), (float)((double)cj2.o() - this.d), (float)((double)cj2.p() - this.e));
    }

    public void a(bht bht2, adf adf2) {
        this.a.add(bht2);
    }

    public abstract void a(adf var1);
}

