/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class box
implements boq {
    private final int a;
    private final List<b> b;
    private final boq c;

    public box(List<b> list) {
        this.b = list;
        this.a = oa.a(list);
        this.c = list.get((int)0).b;
    }

    @Override
    public List<bgg> a(cq cq2) {
        return this.c.a(cq2);
    }

    @Override
    public List<bgg> a() {
        return this.c.a();
    }

    @Override
    public boolean b() {
        return this.c.b();
    }

    @Override
    public boolean c() {
        return this.c.c();
    }

    @Override
    public boolean d() {
        return this.c.d();
    }

    @Override
    public bmi e() {
        return this.c.e();
    }

    @Override
    public bgr f() {
        return this.c.f();
    }

    public boq a(long l2) {
        return oa.a(this.b, (int)(Math.abs((int)((int)l2 >> 16)) % this.a)).b;
    }

    static class b
    extends oa.a
    implements Comparable<b> {
        protected final boq b;

        public b(boq boq2, int n2) {
            super(n2);
            this.b = boq2;
        }

        public int a(b b2) {
            return ComparisonChain.start().compare(b2.a, this.a).compare(this.a(), b2.a()).result();
        }

        protected int a() {
            int n2 = this.b.a().size();
            for (cq cq2 : cq.values()) {
                n2 += this.b.a(cq2).size();
            }
            return n2;
        }

        public String toString() {
            return "MyWeighedRandomItem{weight=" + this.a + ", model=" + this.b + '}';
        }

        @Override
        public /* synthetic */ int compareTo(Object object) {
            return this.a((b)object);
        }
    }

    public static class a {
        private List<b> a = Lists.newArrayList();

        public a a(boq boq2, int n2) {
            this.a.add(new b(boq2, n2));
            return this;
        }

        public box a() {
            Collections.sort(this.a);
            return new box(this.a);
        }

        public boq b() {
            return this.a.get((int)0).b;
        }
    }
}

