/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Sets;
import java.util.Set;

public class aag
extends za {
    private static final Set<afh> c = Sets.newHashSet(afi.cs, afi.q, afi.e, afi.E, afi.ah, afi.ag, afi.T, afi.D, afi.R, afi.o, afi.aI, afi.S, afi.p, afi.y, afi.x, afi.aD, afi.Y, afi.aV, afi.cB, afi.av, afi.aC, afi.A, afi.cM, afi.b, afi.U);

    protected aag(zw.a a2) {
        super(2.0f, a2, c);
    }

    @Override
    public boolean b(afh afh2) {
        if (afh2 == afi.Z) {
            return this.b.d() == 3;
        }
        if (afh2 == afi.ah || afh2 == afi.ag) {
            return this.b.d() >= 2;
        }
        if (afh2 == afi.bP || afh2 == afi.bT) {
            return this.b.d() >= 2;
        }
        if (afh2 == afi.R || afh2 == afi.o) {
            return this.b.d() >= 2;
        }
        if (afh2 == afi.S || afh2 == afi.p) {
            return this.b.d() >= 1;
        }
        if (afh2 == afi.y || afh2 == afi.x) {
            return this.b.d() >= 1;
        }
        if (afh2 == afi.aC || afh2 == afi.aD) {
            return this.b.d() >= 2;
        }
        if (afh2.t() == arm.e) {
            return true;
        }
        if (afh2.t() == arm.f) {
            return true;
        }
        return afh2.t() == arm.g;
    }

    @Override
    public float a(zx zx2, afh afh2) {
        if (afh2.t() == arm.f || afh2.t() == arm.g || afh2.t() == arm.e) {
            return this.a;
        }
        return super.a(zx2, afh2);
    }
}

