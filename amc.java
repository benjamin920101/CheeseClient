/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;

public class amc {
    private final adm a;
    private final cj b;
    private final boolean c;
    private alz d;
    private akw e;
    private boolean f;

    public amc(adm adm2, cj cj2, boolean bl2) {
        this.a = adm2;
        this.b = cj2;
        this.c = bl2;
    }

    public alz a() {
        if (this.d == null && (this.c || this.a.e(this.b))) {
            this.d = this.a.p(this.b);
        }
        return this.d;
    }

    public akw b() {
        if (this.e == null && !this.f) {
            this.e = this.a.s(this.b);
            this.f = true;
        }
        return this.e;
    }

    public cj d() {
        return this.b;
    }

    public static Predicate<amc> a(final Predicate<alz> predicate) {
        return new Predicate<amc>(){

            public boolean a(amc amc2) {
                return amc2 != null && predicate.apply(amc2.a());
            }

            @Override
            public /* synthetic */ boolean apply(Object object) {
                return this.a((amc)object);
            }
        };
    }
}

