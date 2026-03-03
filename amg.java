/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;

public class amg
implements Predicate<alz> {
    private final afh a;

    private amg(afh afh2) {
        this.a = afh2;
    }

    public static amg a(afh afh2) {
        return new amg(afh2);
    }

    public boolean a(alz alz2) {
        return alz2 != null && alz2.c() == this.a;
    }

    @Override
    public /* synthetic */ boolean apply(Object object) {
        return this.a((alz)object);
    }
}

