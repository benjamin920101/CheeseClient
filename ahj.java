/*
 * Decompiled with CFR 0.152.
 */
public class ahj
extends afh {
    private boolean a;

    protected ahj(arm arm2, boolean bl2) {
        this(arm2, bl2, arm2.r());
    }

    protected ahj(arm arm2, boolean bl2, arn arn2) {
        super(arm2, arn2);
        this.a = bl2;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean a(adq adq2, cj cj2, cq cq2) {
        alz alz2 = adq2.p(cj2);
        afh \u26032 = alz2.c();
        if (this == afi.w || this == afi.cG) {
            if (adq2.p(cj2.a(cq2.d())) != alz2) {
                return true;
            }
            if (\u26032 == this) {
                return false;
            }
        }
        if (!this.a && \u26032 == this) {
            return false;
        }
        return super.a(adq2, cj2, cq2);
    }
}

