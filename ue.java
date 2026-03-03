/*
 * Decompiled with CFR 0.152.
 */
public class ue
extends pk {
    public final ud a;
    public final String b;

    public ue(ud ud2, String string, float f2, float f3) {
        super(ud2.a());
        this.a(f2, f3);
        this.a = ud2;
        this.b = string;
    }

    @Override
    protected void h() {
    }

    @Override
    protected void a(dn dn2) {
    }

    @Override
    protected void b(dn dn2) {
    }

    @Override
    public boolean ad() {
        return true;
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        return this.a.a(this, ow2, f2);
    }

    @Override
    public boolean k(pk pk2) {
        return this == pk2 || this.a == pk2;
    }
}

