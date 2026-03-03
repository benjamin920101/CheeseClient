/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Sets;
import java.util.Set;

public class yl
extends za {
    private static final Set<afh> c = Sets.newHashSet(afi.f, afi.X, afi.r, afi.s, afi.ae, afi.aU, afi.aZ, afi.bk, afi.au);

    protected yl(zw.a a2) {
        super(3.0f, a2, c);
    }

    @Override
    public float a(zx zx2, afh afh2) {
        if (afh2.t() == arm.d || afh2.t() == arm.k || afh2.t() == arm.l) {
            return this.a;
        }
        return super.a(zx2, afh2);
    }
}

