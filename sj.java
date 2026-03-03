/*
 * Decompiled with CFR 0.152.
 */
public class sj
extends rg {
    private int e;
    private wi f;

    public sj(wi wi2) {
        super(wi2, wi.class, 3.0f, 0.02f);
        this.f = wi2;
    }

    @Override
    public void c() {
        super.c();
        this.e = this.f.cs() && this.b instanceof wi && ((wi)this.b).ct() ? 10 : 0;
    }

    @Override
    public void e() {
        super.e();
        if (this.e > 0) {
            --this.e;
            if (this.e == 0) {
                oq oq2 = this.f.cq();
                for (int i2 = 0; i2 < oq2.o_(); ++i2) {
                    zx \u26032;
                    zx zx2 = oq2.a(i2);
                    \u26032 = null;
                    if (zx2 != null) {
                        zw zw2 = zx2.b();
                        if ((zw2 == zy.P || zw2 == zy.bS || zw2 == zy.bR) && zx2.b > 3) {
                            int n2 = zx2.b / 2;
                            zx2.b -= n2;
                            \u26032 = new zx(zw2, n2, zx2.i());
                        } else if (zw2 == zy.O && zx2.b > 5) {
                            n2 = zx2.b / 2 / 3 * 3;
                            \u2603 = n2 / 3;
                            zx2.b -= n2;
                            \u26032 = new zx(zy.P, \u2603, 0);
                        }
                        if (zx2.b <= 0) {
                            oq2.a(i2, null);
                        }
                    }
                    if (\u26032 == null) continue;
                    double d2 = this.f.t - (double)0.3f + (double)this.f.aS();
                    uz \u26033 = new uz(this.f.o, this.f.s, d2, this.f.u, \u26032);
                    float \u26034 = 0.3f;
                    float \u26035 = this.f.aK;
                    float \u26036 = this.f.z;
                    \u26033.v = -ns.a(\u26035 / 180.0f * (float)Math.PI) * ns.b(\u26036 / 180.0f * (float)Math.PI) * \u26034;
                    \u26033.x = ns.b(\u26035 / 180.0f * (float)Math.PI) * ns.b(\u26036 / 180.0f * (float)Math.PI) * \u26034;
                    \u26033.w = -ns.a(\u26036 / 180.0f * (float)Math.PI) * \u26034 + 0.1f;
                    \u26033.p();
                    this.f.o.d(\u26033);
                    break;
                }
            }
        }
    }
}

