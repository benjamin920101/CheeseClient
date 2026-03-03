/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public abstract class azp
implements awd.a {
    private static final jy c = new jy("textures/gui/resource_packs.png");
    private static final eu d = new fb("resourcePack.incompatible", new Object[0]);
    private static final eu e = new fb("resourcePack.incompatible.old", new Object[0]);
    private static final eu f = new fb("resourcePack.incompatible.new", new Object[0]);
    protected final ave a;
    protected final azo b;

    public azp(azo azo2) {
        this.b = azo2;
        this.a = ave.A();
    }

    @Override
    public void a(int n2, int n3, int n4, int n5, int n6, int n7, int n8, boolean bl2) {
        int n9 = this.a();
        if (n9 != 1) {
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            avp.a(n3 - 1, n4 - 1, n3 + n5 - 9, n4 + n6 + 1, -8978432);
        }
        this.d();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        avp.a(n3, n4, 0.0f, 0.0f, 32, 32, 32.0f, 32.0f);
        String \u26032 = this.c();
        String \u26033 = this.b();
        if ((this.a.t.A || bl2) && this.e()) {
            this.a.P().a(c);
            avp.a(n3, n4, n3 + 32, n4 + 32, -1601138544);
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            \u2603 = n7 - n3;
            \u2603 = n8 - n4;
            if (n9 < 1) {
                \u26032 = d.d();
                \u26033 = e.d();
            } else if (n9 > 1) {
                \u26032 = d.d();
                \u26033 = f.d();
            }
            if (this.f()) {
                if (\u2603 < 32) {
                    avp.a(n3, n4, 0.0f, 32.0f, 32, 32, 256.0f, 256.0f);
                } else {
                    avp.a(n3, n4, 0.0f, 0.0f, 32, 32, 256.0f, 256.0f);
                }
            } else {
                if (this.g()) {
                    if (\u2603 < 16) {
                        avp.a(n3, n4, 32.0f, 32.0f, 32, 32, 256.0f, 256.0f);
                    } else {
                        avp.a(n3, n4, 32.0f, 0.0f, 32, 32, 256.0f, 256.0f);
                    }
                }
                if (this.h()) {
                    if (\u2603 < 32 && \u2603 > 16 && \u2603 < 16) {
                        avp.a(n3, n4, 96.0f, 32.0f, 32, 32, 256.0f, 256.0f);
                    } else {
                        avp.a(n3, n4, 96.0f, 0.0f, 32, 32, 256.0f, 256.0f);
                    }
                }
                if (this.i()) {
                    if (\u2603 < 32 && \u2603 > 16 && \u2603 > 16) {
                        avp.a(n3, n4, 64.0f, 32.0f, 32, 32, 256.0f, 256.0f);
                    } else {
                        avp.a(n3, n4, 64.0f, 0.0f, 32, 32, 256.0f, 256.0f);
                    }
                }
            }
        }
        if ((\u2603 = this.a.k.a(\u26032)) > 157) {
            \u26032 = this.a.k.a(\u26032, 157 - this.a.k.a("...")) + "...";
        }
        this.a.k.a(\u26032, (float)(n3 + 32 + 2), (float)(n4 + 1), 0xFFFFFF);
        List<String> \u26034 = this.a.k.c(\u26033, 157);
        for (\u2603 = 0; \u2603 < 2 && \u2603 < \u26034.size(); ++\u2603) {
            this.a.k.a(\u26034.get(\u2603), (float)(n3 + 32 + 2), (float)(n4 + 12 + 10 * \u2603), 0x808080);
        }
    }

    protected abstract int a();

    protected abstract String b();

    protected abstract String c();

    protected abstract void d();

    protected boolean e() {
        return true;
    }

    protected boolean f() {
        return !this.b.a(this);
    }

    protected boolean g() {
        return this.b.a(this);
    }

    protected boolean h() {
        List<azp> list = this.b.b(this);
        int \u26032 = list.indexOf(this);
        return \u26032 > 0 && list.get(\u26032 - 1).e();
    }

    protected boolean i() {
        List<azp> list = this.b.b(this);
        int \u26032 = list.indexOf(this);
        return \u26032 >= 0 && \u26032 < list.size() - 1 && list.get(\u26032 + 1).e();
    }

    @Override
    public boolean a(int n2, int n3, int n4, int n5, int n62, int n7) {
        if (this.e() && n62 <= 32) {
            int n62;
            if (this.f()) {
                this.b.g();
                \u2603 = this.a();
                if (\u2603 != 1) {
                    String string = bnq.a("resourcePack.incompatible.confirm.title", new Object[0]);
                    \u2603 = bnq.a("resourcePack.incompatible.confirm." + (\u2603 > 1 ? "new" : "old"), new Object[0]);
                    this.a.a(new awy(new awx(){

                        @Override
                        public void a(boolean bl2, int n2) {
                            List<azp> list = azp.this.b.b(azp.this);
                            azp.this.a.a(azp.this.b);
                            if (bl2) {
                                list.remove(azp.this);
                                azp.this.b.f().add(0, azp.this);
                            }
                        }
                    }, string, \u2603, 0));
                } else {
                    this.b.b(this).remove(this);
                    this.b.f().add(0, this);
                }
                return true;
            }
            if (n62 < 16 && this.g()) {
                this.b.b(this).remove(this);
                this.b.a().add(0, this);
                this.b.g();
                return true;
            }
            if (n62 > 16 && n7 < 16 && this.h()) {
                List<azp> list = this.b.b(this);
                int \u26032 = list.indexOf(this);
                list.remove(this);
                list.add(\u26032 - 1, this);
                this.b.g();
                return true;
            }
            if (n62 > 16 && n7 > 16 && this.i()) {
                \u2603 = this.b.b(this);
                int \u26033 = \u2603.indexOf(this);
                \u2603.remove(this);
                \u2603.add(\u26033 + 1, this);
                this.b.g();
                return true;
            }
        }
        return false;
    }

    @Override
    public void a(int n2, int n3, int n4) {
    }

    @Override
    public void b(int n2, int n3, int n4, int n5, int n6, int n7) {
    }
}

