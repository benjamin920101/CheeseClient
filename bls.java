/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import org.lwjgl.util.vector.Matrix4f;

public class bls {
    private final blq c;
    public final bfw a;
    public final bfw b;
    private final List<Object> d = Lists.newArrayList();
    private final List<String> e = Lists.newArrayList();
    private final List<Integer> f = Lists.newArrayList();
    private final List<Integer> g = Lists.newArrayList();
    private Matrix4f h;

    public bls(bni bni2, String string, bfw bfw2, bfw bfw3) throws IOException {
        this.c = new blq(bni2, string);
        this.a = bfw2;
        this.b = bfw3;
    }

    public void b() {
        this.c.a();
    }

    public void a(String string, Object object, int n2, int n3) {
        this.e.add(this.e.size(), string);
        this.d.add(this.d.size(), object);
        this.f.add(this.f.size(), n2);
        this.g.add(this.g.size(), n3);
    }

    private void d() {
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.k();
        bfl.i();
        bfl.c();
        bfl.n();
        bfl.f();
        bfl.h();
        bfl.w();
        bfl.i(0);
    }

    public void a(Matrix4f matrix4f) {
        this.h = matrix4f;
    }

    public void a(float f2) {
        float f3;
        this.d();
        this.a.e();
        f3 = this.b.a;
        \u2603 = this.b.b;
        bfl.b(0, 0, (int)f3, (int)\u2603);
        this.c.a("DiffuseSampler", this.a);
        for (int i2 = 0; i2 < this.d.size(); ++i2) {
            this.c.a(this.e.get(i2), this.d.get(i2));
            this.c.b("AuxSize" + i2).a(this.f.get(i2).intValue(), this.g.get(i2).intValue());
        }
        this.c.b("ProjMat").a(this.h);
        this.c.b("InSize").a(this.a.a, this.a.b);
        this.c.b("OutSize").a(f3, \u2603);
        this.c.b("Time").a(f2);
        ave \u26032 = ave.A();
        this.c.b("ScreenSize").a(\u26032.d, \u26032.e);
        this.c.c();
        this.b.f();
        this.b.a(false);
        bfl.a(false);
        bfl.a(true, true, true, true);
        bfx \u26033 = bfx.a();
        bfd \u26034 = \u26033.c();
        \u26034.a(7, bms.f);
        \u26034.b(0.0, (double)\u2603, 500.0).b(255, 255, 255, 255).d();
        \u26034.b((double)f3, (double)\u2603, 500.0).b(255, 255, 255, 255).d();
        \u26034.b((double)f3, 0.0, 500.0).b(255, 255, 255, 255).d();
        \u26034.b(0.0, 0.0, 500.0).b(255, 255, 255, 255).d();
        \u26033.b();
        bfl.a(true);
        bfl.a(true, true, true, true);
        this.c.b();
        this.b.e();
        this.a.d();
        for (Object object : this.d) {
            if (!(object instanceof bfw)) continue;
            ((bfw)object).d();
        }
    }

    public blq c() {
        return this.c;
    }
}

