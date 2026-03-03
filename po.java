/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;

public final class po {
    public static final Predicate<pk> a = new Predicate<pk>(){

        public boolean a(pk pk2) {
            return pk2.ai();
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((pk)object);
        }
    };
    public static final Predicate<pk> b = new Predicate<pk>(){

        public boolean a(pk pk2) {
            return pk2.ai() && pk2.l == null && pk2.m == null;
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((pk)object);
        }
    };
    public static final Predicate<pk> c = new Predicate<pk>(){

        public boolean a(pk pk2) {
            return pk2 instanceof og && pk2.ai();
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((pk)object);
        }
    };
    public static final Predicate<pk> d = new Predicate<pk>(){

        public boolean a(pk pk2) {
            return !(pk2 instanceof wn) || !((wn)pk2).v();
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((pk)object);
        }
    };

    public static class a
    implements Predicate<pk> {
        private final zx a;

        public a(zx zx2) {
            this.a = zx2;
        }

        public boolean a(pk pk2) {
            if (!pk2.ai()) {
                return false;
            }
            if (!(pk2 instanceof pr)) {
                return false;
            }
            pr pr2 = (pr)pk2;
            if (pr2.p(ps.c(this.a)) != null) {
                return false;
            }
            if (pr2 instanceof ps) {
                return ((ps)pr2).bY();
            }
            if (pr2 instanceof um) {
                return true;
            }
            return pr2 instanceof wn;
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((pk)object);
        }
    }
}

