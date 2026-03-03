/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;

public class atg
extends ate {
    public int b;
    public int c;
    public byte d;
    public byte e;
    public byte[] f = new byte[16384];
    public List<a> g = Lists.newArrayList();
    private Map<wn, a> i = Maps.newHashMap();
    public Map<String, atf> h = Maps.newLinkedHashMap();

    public atg(String string) {
        super(string);
    }

    public void a(double d2, double d3, int n2) {
        \u2603 = 128 * (1 << n2);
        \u2603 = ns.c((d2 + 64.0) / (double)\u2603);
        \u2603 = ns.c((d3 + 64.0) / (double)\u2603);
        this.b = \u2603 * \u2603 + \u2603 / 2 - 64;
        this.c = \u2603 * \u2603 + \u2603 / 2 - 64;
    }

    @Override
    public void a(dn dn2) {
        this.d = dn2.d("dimension");
        this.b = dn2.f("xCenter");
        this.c = dn2.f("zCenter");
        this.e = dn2.d("scale");
        this.e = (byte)ns.a(this.e, 0, 4);
        int n2 = dn2.e("width");
        \u2603 = dn2.e("height");
        if (n2 == 128 && \u2603 == 128) {
            this.f = dn2.k("colors");
        } else {
            byte[] byArray = dn2.k("colors");
            this.f = new byte[16384];
            int \u26032 = (128 - n2) / 2;
            int \u26033 = (128 - \u2603) / 2;
            for (int i2 = 0; i2 < \u2603; ++i2) {
                \u2603 = i2 + \u26033;
                if (\u2603 < 0 && \u2603 >= 128) continue;
                for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                    \u2603 = \u2603 + \u26032;
                    if (\u2603 < 0 && \u2603 >= 128) continue;
                    this.f[\u2603 + \u2603 * 128] = byArray[\u2603 + i2 * n2];
                }
            }
        }
    }

    @Override
    public void b(dn dn2) {
        dn2.a("dimension", this.d);
        dn2.a("xCenter", this.b);
        dn2.a("zCenter", this.c);
        dn2.a("scale", this.e);
        dn2.a("width", (short)128);
        dn2.a("height", (short)128);
        dn2.a("colors", this.f);
    }

    public void a(wn wn22, zx zx22) {
        zx zx22;
        Object \u26032;
        wn wn22;
        if (!this.i.containsKey(wn22)) {
            a a2 = new a(wn22);
            this.i.put(wn22, a2);
            this.g.add(a2);
        }
        if (!wn22.bi.c(zx22)) {
            this.h.remove(wn22.e_());
        }
        for (int i2 = 0; i2 < this.g.size(); ++i2) {
            \u26032 = this.g.get(i2);
            if (((a)\u26032).a.I || !((a)\u26032).a.bi.c(zx22) && !zx22.y()) {
                this.i.remove(((a)\u26032).a);
                this.g.remove(\u26032);
                continue;
            }
            if (zx22.y() || ((a)\u26032).a.am != this.d) continue;
            this.a(0, ((a)\u26032).a.o, ((a)\u26032).a.e_(), ((a)\u26032).a.s, ((a)\u26032).a.u, ((a)\u26032).a.y);
        }
        if (zx22.y()) {
            uo uo2 = zx22.z();
            \u26032 = uo2.n();
            this.a(1, wn22.o, "frame-" + uo2.F(), ((df)\u26032).n(), ((df)\u26032).p(), uo2.b.b() * 90);
        }
        if (zx22.n() && zx22.o().b("Decorations", 9)) {
            du \u26033 = zx22.o().c("Decorations", 10);
            for (int i3 = 0; i3 < \u26033.c(); ++i3) {
                dn dn2 = \u26033.b(i3);
                if (this.h.containsKey(dn2.j("id"))) continue;
                this.a(dn2.d("type"), wn22.o, dn2.j("id"), dn2.i("x"), dn2.i("z"), dn2.i("rot"));
            }
        }
    }

    private void a(int n2, adm adm2, String string, double d2, double d3, double d4) {
        byte \u26036;
        int n3 = 1 << this.e;
        float \u26032 = (float)(d2 - (double)this.b) / (float)n3;
        float \u26033 = (float)(d3 - (double)this.c) / (float)n3;
        byte \u26034 = (byte)((double)(\u26032 * 2.0f) + 0.5);
        byte \u26035 = (byte)((double)(\u26033 * 2.0f) + 0.5);
        \u2603 = 63;
        if (\u26032 >= (float)(-\u2603) && \u26033 >= (float)(-\u2603) && \u26032 <= (float)\u2603 && \u26033 <= (float)\u2603) {
            \u26036 = (byte)((d4 += d4 < 0.0 ? -8.0 : 8.0) * 16.0 / 360.0);
            if (this.d < 0) {
                int n4 = (int)(adm2.P().g() / 10L);
                \u26036 = (byte)(n4 * n4 * 34187121 + n4 * 121 >> 15 & 0xF);
            }
        } else if (Math.abs(\u26032) < 320.0f && Math.abs(\u26033) < 320.0f) {
            n2 = 6;
            \u26036 = 0;
            if (\u26032 <= (float)(-\u2603)) {
                \u26034 = (byte)((double)(\u2603 * 2) + 2.5);
            }
            if (\u26033 <= (float)(-\u2603)) {
                \u26035 = (byte)((double)(\u2603 * 2) + 2.5);
            }
            if (\u26032 >= (float)\u2603) {
                \u26034 = (byte)(\u2603 * 2 + 1);
            }
            if (\u26033 >= (float)\u2603) {
                \u26035 = (byte)(\u2603 * 2 + 1);
            }
        } else {
            this.h.remove(string);
            return;
        }
        this.h.put(string, new atf((byte)n2, \u26034, \u26035, \u26036));
    }

    public ff a(zx zx2, adm adm2, wn wn2) {
        a a2 = this.i.get(wn2);
        if (a2 == null) {
            return null;
        }
        return a2.a(zx2);
    }

    public void a(int n2, int n3) {
        super.c();
        for (a a2 : this.g) {
            a2.a(n2, n3);
        }
    }

    public a a(wn wn2) {
        a a2 = this.i.get(wn2);
        if (a2 == null) {
            a2 = new a(wn2);
            this.i.put(wn2, a2);
            this.g.add(a2);
        }
        return a2;
    }

    public class a {
        public final wn a;
        private boolean d = true;
        private int e = 0;
        private int f = 0;
        private int g = 127;
        private int h = 127;
        private int i;
        public int b;

        public a(wn wn2) {
            this.a = wn2;
        }

        public ff a(zx zx2) {
            if (this.d) {
                this.d = false;
                return new gu(zx2.i(), atg.this.e, atg.this.h.values(), atg.this.f, this.e, this.f, this.g + 1 - this.e, this.h + 1 - this.f);
            }
            if (this.i++ % 5 == 0) {
                return new gu(zx2.i(), atg.this.e, atg.this.h.values(), atg.this.f, 0, 0, 0, 0);
            }
            return null;
        }

        public void a(int n2, int n3) {
            if (this.d) {
                this.e = Math.min(this.e, n2);
                this.f = Math.min(this.f, n3);
                this.g = Math.max(this.g, n2);
                this.h = Math.max(this.h, n3);
            } else {
                this.d = true;
                this.e = n2;
                this.f = n3;
                this.g = n2;
                this.h = n3;
            }
        }
    }
}

