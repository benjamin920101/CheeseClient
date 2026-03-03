/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonParseException;
import net.minecraft.server.MinecraftServer;

public class aln
extends akw {
    public final eu[] a = new eu[]{new fa(""), new fa(""), new fa(""), new fa("")};
    public int f = -1;
    private boolean g = true;
    private wn h;
    private final n i = new n();

    @Override
    public void b(dn dn22) {
        dn dn22;
        super.b(dn22);
        for (int i2 = 0; i2 < 4; ++i2) {
            String string = eu.a.a(this.a[i2]);
            dn22.a("Text" + (i2 + 1), string);
        }
        this.i.b(dn22);
    }

    @Override
    public void a(dn dn22) {
        dn dn22;
        this.g = false;
        super.a(dn22);
        m m2 = new m(){

            @Override
            public String e_() {
                return "Sign";
            }

            @Override
            public eu f_() {
                return new fa(this.e_());
            }

            @Override
            public void a(eu eu2) {
            }

            @Override
            public boolean a(int n2, String string) {
                return true;
            }

            @Override
            public cj c() {
                return aln.this.c;
            }

            @Override
            public aui d() {
                return new aui((double)aln.this.c.n() + 0.5, (double)aln.this.c.o() + 0.5, (double)aln.this.c.p() + 0.5);
            }

            @Override
            public adm e() {
                return aln.this.b;
            }

            @Override
            public pk f() {
                return null;
            }

            @Override
            public boolean u_() {
                return false;
            }

            @Override
            public void a(n.a a2, int n2) {
            }
        };
        for (int i2 = 0; i2 < 4; ++i2) {
            String string = dn22.j("Text" + (i2 + 1));
            try {
                eu eu2 = eu.a.a(string);
                try {
                    this.a[i2] = ev.a(m2, eu2, null);
                }
                catch (bz \u26032) {
                    this.a[i2] = eu2;
                }
                continue;
            }
            catch (JsonParseException jsonParseException) {
                this.a[i2] = new fa(string);
            }
        }
        this.i.a(dn22);
    }

    @Override
    public ff y_() {
        eu[] euArray = new eu[4];
        System.arraycopy(this.a, 0, euArray, 0, 4);
        return new hw(this.b, this.c, euArray);
    }

    @Override
    public boolean F() {
        return true;
    }

    public boolean b() {
        return this.g;
    }

    public void a(boolean bl2) {
        this.g = bl2;
        if (!bl2) {
            this.h = null;
        }
    }

    public void a(wn wn2) {
        this.h = wn2;
    }

    public wn c() {
        return this.h;
    }

    public boolean b(final wn wn2) {
        m m2 = new m(){

            @Override
            public String e_() {
                return wn2.e_();
            }

            @Override
            public eu f_() {
                return wn2.f_();
            }

            @Override
            public void a(eu eu2) {
            }

            @Override
            public boolean a(int n2, String string) {
                return n2 <= 2;
            }

            @Override
            public cj c() {
                return aln.this.c;
            }

            @Override
            public aui d() {
                return new aui((double)aln.this.c.n() + 0.5, (double)aln.this.c.o() + 0.5, (double)aln.this.c.p() + 0.5);
            }

            @Override
            public adm e() {
                return wn2.e();
            }

            @Override
            public pk f() {
                return wn2;
            }

            @Override
            public boolean u_() {
                return false;
            }

            @Override
            public void a(n.a a2, int n2) {
                aln.this.i.a(this, a2, n2);
            }
        };
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            ez ez2 = \u2603 = this.a[i2] == null ? null : this.a[i2].b();
            if (\u2603 == null || \u2603.h() == null || (\u2603 = \u2603.h()).a() != et.a.c) continue;
            MinecraftServer.N().P().a(m2, \u2603.b());
        }
        return true;
    }

    public n d() {
        return this.i;
    }
}

