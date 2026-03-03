/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.realms.RealmsButton;

public class awp
extends avs {
    private RealmsButton o;

    public awp(RealmsButton realmsButton, int n2, int n3, int n4, String string) {
        super(n2, n3, n4, string);
        this.o = realmsButton;
    }

    public awp(RealmsButton realmsButton, int n2, int n3, int n4, String string, int n5, int n6) {
        super(n2, n3, n4, n5, n6, string);
        this.o = realmsButton;
    }

    public int c() {
        return this.k;
    }

    public boolean d() {
        return this.l;
    }

    public void b(boolean bl2) {
        this.l = bl2;
    }

    public void a(String string) {
        this.j = string;
    }

    @Override
    public int b() {
        return super.b();
    }

    public int e() {
        return this.i;
    }

    @Override
    public boolean c(ave ave2, int n2, int n3) {
        if (super.c(ave2, n2, n3)) {
            this.o.clicked(n2, n3);
        }
        return super.c(ave2, n2, n3);
    }

    @Override
    public void a(int n2, int n3) {
        this.o.released(n2, n3);
    }

    @Override
    public void b(ave ave2, int n2, int n3) {
        this.o.renderBg(n2, n3);
    }

    public RealmsButton f() {
        return this.o;
    }

    @Override
    public int a(boolean bl2) {
        return this.o.getYImage(bl2);
    }

    public int c(boolean bl2) {
        return super.a(bl2);
    }

    public int g() {
        return this.g;
    }
}

