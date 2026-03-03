/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class avt
extends avp {
    private static final Logger a = LogManager.getLogger();
    private final ave f;
    private final List<String> g = Lists.newArrayList();
    private final List<ava> h = Lists.newArrayList();
    private final List<ava> i = Lists.newArrayList();
    private int j;
    private boolean k;

    public avt(ave ave2) {
        this.f = ave2;
    }

    public void a(int n2) {
        int \u26039;
        int \u26038;
        int n3;
        if (this.f.t.m == wn.b.c) {
            return;
        }
        \u2603 = this.i();
        boolean bl2 = false;
        int \u26032 = 0;
        int \u26033 = this.i.size();
        float \u26034 = this.f.t.q * 0.9f + 0.1f;
        if (\u26033 <= 0) {
            return;
        }
        if (this.e()) {
            bl2 = true;
        }
        float \u26035 = this.h();
        int \u26036 = ns.f((float)this.f() / \u26035);
        bfl.E();
        bfl.b(2.0f, 20.0f, 0.0f);
        bfl.a(\u26035, \u26035, 1.0f);
        for (n3 = 0; n3 + this.j < this.i.size() && n3 < \u2603; ++n3) {
            ava ava2 = this.i.get(n3 + this.j);
            if (ava2 == null || (\u2603 = n2 - ava2.b()) >= 200 && !bl2) continue;
            double \u26037 = (double)\u2603 / 200.0;
            \u26037 = 1.0 - \u26037;
            \u26037 *= 10.0;
            \u26037 = ns.a(\u26037, 0.0, 1.0);
            \u26037 *= \u26037;
            \u26038 = (int)(255.0 * \u26037);
            if (bl2) {
                \u26038 = 255;
            }
            \u26038 = (int)((float)\u26038 * \u26034);
            ++\u26032;
            if (\u26038 <= 3) continue;
            \u26039 = 0;
            int \u260310 = -n3 * 9;
            avt.a(\u26039, \u260310 - 9, \u26039 + \u26036 + 4, \u260310, \u26038 / 2 << 24);
            String \u260311 = ava2.a().d();
            bfl.l();
            this.f.k.a(\u260311, (float)\u26039, (float)(\u260310 - 8), 0xFFFFFF + (\u26038 << 24));
            bfl.c();
            bfl.k();
        }
        if (bl2) {
            n3 = this.f.k.a;
            bfl.b(-3.0f, 0.0f, 0.0f);
            \u2603 = \u26033 * n3 + \u26033;
            \u2603 = \u26032 * n3 + \u26032;
            \u2603 = this.j * \u2603 / \u26033;
            \u2603 = \u2603 * \u2603 / \u2603;
            if (\u2603 != \u2603) {
                \u26038 = \u2603 > 0 ? 170 : 96;
                \u26039 = this.k ? 0xCC3333 : 0x3333AA;
                avt.a(0, -\u2603, 2, -\u2603 - \u2603, \u26039 + (\u26038 << 24));
                avt.a(2, -\u2603, 1, -\u2603 - \u2603, 0xCCCCCC + (\u26038 << 24));
            }
        }
        bfl.F();
    }

    public void a() {
        this.i.clear();
        this.h.clear();
        this.g.clear();
    }

    public void a(eu eu2) {
        this.a(eu2, 0);
    }

    public void a(eu eu2, int n2) {
        this.a(eu2, n2, this.f.q.e(), false);
        a.info("[CHAT] " + eu2.c());
    }

    private void a(eu eu2, int n2, int n3, boolean bl22) {
        boolean bl22;
        if (n2 != 0) {
            this.c(n2);
        }
        int n4 = ns.d((float)this.f() / this.h());
        List<eu> \u26032 = avu.a(eu2, n4, this.f.k, false, false);
        boolean \u26033 = this.e();
        for (eu eu3 : \u26032) {
            if (\u26033 && this.j > 0) {
                this.k = true;
                this.b(1);
            }
            this.i.add(0, new ava(n3, eu3, n2));
        }
        while (this.i.size() > 100) {
            this.i.remove(this.i.size() - 1);
        }
        if (!bl22) {
            this.h.add(0, new ava(n3, eu2, n2));
            while (this.h.size() > 100) {
                this.h.remove(this.h.size() - 1);
            }
        }
    }

    public void b() {
        this.i.clear();
        this.d();
        for (int i2 = this.h.size() - 1; i2 >= 0; --i2) {
            ava ava2 = this.h.get(i2);
            this.a(ava2.a(), ava2.c(), ava2.b(), true);
        }
    }

    public List<String> c() {
        return this.g;
    }

    public void a(String string) {
        if (this.g.isEmpty() || !this.g.get(this.g.size() - 1).equals(string)) {
            this.g.add(string);
        }
    }

    public void d() {
        this.j = 0;
        this.k = false;
    }

    public void b(int n2) {
        this.j += n2;
        \u2603 = this.i.size();
        if (this.j > \u2603 - this.i()) {
            this.j = \u2603 - this.i();
        }
        if (this.j <= 0) {
            this.j = 0;
            this.k = false;
        }
    }

    public eu a(int n2, int n3) {
        if (!this.e()) {
            return null;
        }
        avr avr2 = new avr(this.f);
        int \u26032 = avr2.e();
        float \u26033 = this.h();
        int \u26034 = n2 / \u26032 - 3;
        int \u26035 = n3 / \u26032 - 27;
        \u26034 = ns.d((float)\u26034 / \u26033);
        \u26035 = ns.d((float)\u26035 / \u26033);
        if (\u26034 < 0 || \u26035 < 0) {
            return null;
        }
        int \u26036 = Math.min(this.i(), this.i.size());
        if (\u26034 <= ns.d((float)this.f() / this.h()) && \u26035 < this.f.k.a * \u26036 + \u26036) {
            int n4 = \u26035 / this.f.k.a + this.j;
            if (n4 >= 0 && n4 < this.i.size()) {
                ava ava2 = this.i.get(n4);
                int \u26037 = 0;
                for (eu eu2 : ava2.a()) {
                    if (!(eu2 instanceof fa) || (\u26037 += this.f.k.a(avu.a(((fa)eu2).g(), false))) <= \u26034) continue;
                    return eu2;
                }
            }
            return null;
        }
        return null;
    }

    public boolean e() {
        return this.f.m instanceof awv;
    }

    public void c(int n2) {
        ava ava2;
        Iterator<ava> iterator = this.i.iterator();
        while (iterator.hasNext()) {
            ava2 = iterator.next();
            if (ava2.c() != n2) continue;
            iterator.remove();
        }
        iterator = this.h.iterator();
        while (iterator.hasNext()) {
            ava2 = iterator.next();
            if (ava2.c() != n2) continue;
            iterator.remove();
            break;
        }
    }

    public int f() {
        return avt.a(this.f.t.F);
    }

    public int g() {
        return avt.b(this.e() ? this.f.t.H : this.f.t.G);
    }

    public float h() {
        return this.f.t.E;
    }

    public static int a(float f2) {
        int n2 = 320;
        \u2603 = 40;
        return ns.d(f2 * (float)(n2 - \u2603) + (float)\u2603);
    }

    public static int b(float f2) {
        int n2 = 180;
        \u2603 = 20;
        return ns.d(f2 * (float)(n2 - \u2603) + (float)\u2603);
    }

    public int i() {
        return this.g() / 9;
    }
}

