/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class sg
extends rd {
    private wi a;
    private ty b;
    private int c;
    private boolean d;

    public sg(wi wi2) {
        this.a = wi2;
        this.a(3);
    }

    @Override
    public boolean a() {
        if (this.a.l() >= 0) {
            return false;
        }
        if (!this.a.o.w()) {
            return false;
        }
        List<ty> list = this.a.o.a(ty.class, this.a.aR().b(6.0, 2.0, 6.0));
        if (list.isEmpty()) {
            return false;
        }
        for (ty ty2 : list) {
            if (ty2.cm() <= 0) continue;
            this.b = ty2;
            break;
        }
        return this.b != null;
    }

    @Override
    public boolean b() {
        return this.b.cm() > 0;
    }

    @Override
    public void c() {
        this.c = this.a.bc().nextInt(320);
        this.d = false;
        this.b.s().n();
    }

    @Override
    public void d() {
        this.b = null;
        this.a.s().n();
    }

    @Override
    public void e() {
        this.a.p().a(this.b, 30.0f, 30.0f);
        if (this.b.cm() == this.c) {
            this.a.s().a(this.b, 0.5);
            this.d = true;
        }
        if (this.d && this.a.h(this.b) < 4.0) {
            this.b.a(false);
            this.a.s().n();
        }
    }
}

