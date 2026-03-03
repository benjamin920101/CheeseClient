/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class bhq {
    public static final bhq a = new bhq(){

        @Override
        protected void a(adf adf2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void c(adf adf2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean a(cq cq2, cq cq3) {
            return false;
        }
    };
    private final boolean[] b = new boolean[adf.values().length];
    private final boolean[] c = new boolean[adf.values().length];
    private boolean d = true;
    private final List<akw> e = Lists.newArrayList();
    private bhx f = new bhx();
    private bfd.a g;

    public boolean a() {
        return this.d;
    }

    protected void a(adf adf2) {
        this.d = false;
        this.b[adf2.ordinal()] = true;
    }

    public boolean b(adf adf2) {
        return !this.b[adf2.ordinal()];
    }

    public void c(adf adf2) {
        this.c[adf2.ordinal()] = true;
    }

    public boolean d(adf adf2) {
        return this.c[adf2.ordinal()];
    }

    public List<akw> b() {
        return this.e;
    }

    public void a(akw akw2) {
        this.e.add(akw2);
    }

    public boolean a(cq cq2, cq cq3) {
        return this.f.a(cq2, cq3);
    }

    public void a(bhx bhx2) {
        this.f = bhx2;
    }

    public bfd.a c() {
        return this.g;
    }

    public void a(bfd.a a2) {
        this.g = a2;
    }
}

