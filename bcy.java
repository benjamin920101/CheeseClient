/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.realms.DisconnectedRealmsScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bcy
implements fj {
    private static final Logger b = LogManager.getLogger();
    private final ek c;
    private final GameProfile d;
    private final axu e;
    private ave f;
    private bdb g;
    private boolean h;
    private final Map<UUID, bdc> i = Maps.newHashMap();
    public int a = 20;
    private boolean j = false;
    private final Random k = new Random();

    public bcy(ave ave2, axu axu2, ek ek2, GameProfile gameProfile) {
        this.f = ave2;
        this.e = axu2;
        this.c = ek2;
        this.d = gameProfile;
    }

    public void b() {
        this.g = null;
    }

    @Override
    public void a(gt gt2) {
        fh.a(gt2, this, this.f);
        this.f.c = new bda(this.f, this);
        this.g = new bdb(this, new adp(0L, gt2.c(), false, gt2.b(), gt2.g()), gt2.d(), gt2.e(), this.f.A);
        this.f.t.ay = gt2.e();
        this.f.a(this.g);
        this.f.h.am = gt2.d();
        this.f.a(new axs(this));
        this.f.h.d(gt2.a());
        this.a = gt2.f();
        this.f.h.k(gt2.h());
        this.f.c.a(gt2.c());
        this.f.t.c();
        this.c.a(new im("MC|Brand", new em(Unpooled.buffer()).a(ClientBrandRetriever.getClientModName())));
    }

    @Override
    public void a(fk fk22) {
        fk fk22;
        pk[] pkArray;
        fh.a(fk22, this, this.f);
        double d2 = (double)fk22.b() / 32.0;
        \u2603 = (double)fk22.c() / 32.0;
        \u2603 = (double)fk22.d() / 32.0;
        pk \u26032 = null;
        if (fk22.j() == 10) {
            \u26032 = va.a(this.g, d2, \u2603, \u2603, va.a.a(fk22.k()));
        } else if (fk22.j() == 90) {
            pkArray = this.g.a(fk22.k());
            if (pkArray instanceof wn) {
                \u26032 = new ur(this.g, d2, \u2603, \u2603, (wn)pkArray);
            }
            fk22.g(0);
        } else if (fk22.j() == 60) {
            \u26032 = new wq(this.g, d2, \u2603, \u2603);
        } else if (fk22.j() == 61) {
            \u26032 = new wx(this.g, d2, \u2603, \u2603);
        } else if (fk22.j() == 71) {
            \u26032 = new uo(this.g, new cj(ns.c(d2), ns.c(\u2603), ns.c(\u2603)), cq.b(fk22.k()));
            fk22.g(0);
        } else if (fk22.j() == 77) {
            \u26032 = new up(this.g, new cj(ns.c(d2), ns.c(\u2603), ns.c(\u2603)));
            fk22.g(0);
        } else if (fk22.j() == 65) {
            \u26032 = new xa(this.g, d2, \u2603, \u2603);
        } else if (fk22.j() == 72) {
            \u26032 = new wr(this.g, d2, \u2603, \u2603);
        } else if (fk22.j() == 76) {
            \u26032 = new wt(this.g, d2, \u2603, \u2603, null);
        } else if (fk22.j() == 63) {
            \u26032 = new wu(this.g, d2, \u2603, \u2603, (double)fk22.e() / 8000.0, (double)fk22.f() / 8000.0, (double)fk22.g() / 8000.0);
            fk22.g(0);
        } else if (fk22.j() == 64) {
            \u26032 = new ww(this.g, d2, \u2603, \u2603, (double)fk22.e() / 8000.0, (double)fk22.f() / 8000.0, (double)fk22.g() / 8000.0);
            fk22.g(0);
        } else if (fk22.j() == 66) {
            \u26032 = new xd(this.g, d2, \u2603, \u2603, (double)fk22.e() / 8000.0, (double)fk22.f() / 8000.0, (double)fk22.g() / 8000.0);
            fk22.g(0);
        } else if (fk22.j() == 62) {
            \u26032 = new wz(this.g, d2, \u2603, \u2603);
        } else if (fk22.j() == 73) {
            \u26032 = new xc((adm)this.g, d2, \u2603, \u2603, fk22.k());
            fk22.g(0);
        } else if (fk22.j() == 75) {
            \u26032 = new xb(this.g, d2, \u2603, \u2603);
            fk22.g(0);
        } else if (fk22.j() == 1) {
            \u26032 = new ux(this.g, d2, \u2603, \u2603);
        } else if (fk22.j() == 50) {
            \u26032 = new vj(this.g, d2, \u2603, \u2603, null);
        } else if (fk22.j() == 78) {
            \u26032 = new um(this.g, d2, \u2603, \u2603);
        } else if (fk22.j() == 51) {
            \u26032 = new uf(this.g, d2, \u2603, \u2603);
        } else if (fk22.j() == 2) {
            \u26032 = new uz(this.g, d2, \u2603, \u2603);
        } else if (fk22.j() == 70) {
            \u26032 = new uy(this.g, d2, \u2603, \u2603, afh.d(fk22.k() & 0xFFFF));
            fk22.g(0);
        }
        if (\u26032 != null) {
            \u26032.bW = fk22.b();
            \u26032.bX = fk22.c();
            \u26032.bY = fk22.d();
            \u26032.z = (float)(fk22.h() * 360) / 256.0f;
            \u26032.y = (float)(fk22.i() * 360) / 256.0f;
            pkArray = \u26032.aB();
            if (pkArray != null) {
                int n2 = fk22.a() - \u26032.F();
                for (\u2603 = 0; \u2603 < pkArray.length; ++\u2603) {
                    pkArray[\u2603].d(pkArray[\u2603].F() + n2);
                }
            }
            \u26032.d(fk22.a());
            this.g.a(fk22.a(), \u26032);
            if (fk22.k() > 0) {
                if (fk22.j() == 60 && (\u2603 = this.g.a(fk22.k())) instanceof pr && \u26032 instanceof wq) {
                    ((wq)\u26032).c = \u2603;
                }
                \u26032.i((double)fk22.e() / 8000.0, (double)fk22.f() / 8000.0, (double)fk22.g() / 8000.0);
            }
        }
    }

    @Override
    public void a(fl fl2) {
        fh.a(fl2, this, this.f);
        pp pp2 = new pp(this.g, (double)fl2.b() / 32.0, (double)fl2.c() / 32.0, (double)fl2.d() / 32.0, fl2.e());
        pp2.bW = fl2.b();
        pp2.bX = fl2.c();
        pp2.bY = fl2.d();
        pp2.y = 0.0f;
        pp2.z = 0.0f;
        pp2.d(fl2.a());
        this.g.a(fl2.a(), (pk)pp2);
    }

    @Override
    public void a(fm fm2) {
        fh.a(fm2, this, this.f);
        double d2 = (double)fm2.b() / 32.0;
        \u2603 = (double)fm2.c() / 32.0;
        \u2603 = (double)fm2.d() / 32.0;
        uv \u26032 = null;
        if (fm2.e() == 1) {
            \u26032 = new uv(this.g, d2, \u2603, \u2603);
        }
        if (\u26032 != null) {
            \u26032.bW = fm2.b();
            \u26032.bX = fm2.c();
            \u26032.bY = fm2.d();
            \u26032.y = 0.0f;
            \u26032.z = 0.0f;
            \u26032.d(fm2.a());
            this.g.c(\u26032);
        }
    }

    @Override
    public void a(fo fo2) {
        fh.a(fo2, this, this.f);
        uq uq2 = new uq(this.g, fo2.b(), fo2.c(), fo2.d());
        this.g.a(fo2.a(), (pk)uq2);
    }

    @Override
    public void a(hm hm2) {
        fh.a(hm2, this, this.f);
        pk pk2 = this.g.a(hm2.a());
        if (pk2 == null) {
            return;
        }
        pk2.i((double)hm2.b() / 8000.0, (double)hm2.c() / 8000.0, (double)hm2.d() / 8000.0);
    }

    @Override
    public void a(hk hk2) {
        fh.a(hk2, this, this.f);
        pk pk2 = this.g.a(hk2.b());
        if (pk2 != null && hk2.a() != null) {
            pk2.H().a(hk2.a());
        }
    }

    @Override
    public void a(fp fp2) {
        fh.a(fp2, this, this.f);
        double d2 = (double)fp2.d() / 32.0;
        \u2603 = (double)fp2.e() / 32.0;
        \u2603 = (double)fp2.f() / 32.0;
        float \u26032 = (float)(fp2.g() * 360) / 256.0f;
        float \u26033 = (float)(fp2.h() * 360) / 256.0f;
        bex \u26034 = new bex(this.f.f, this.a(fp2.c()).a());
        \u26034.bW = fp2.d();
        \u26034.p = \u26034.P = (double)\u26034.bW;
        \u26034.bX = fp2.e();
        \u26034.q = \u26034.Q = (double)\u26034.bX;
        \u26034.bY = fp2.f();
        \u26034.r = \u26034.R = (double)\u26034.bY;
        int \u26035 = fp2.i();
        \u26034.bi.a[\u26034.bi.c] = \u26035 == 0 ? null : new zx(zw.b(\u26035), 1, 0);
        \u26034.a(d2, \u2603, \u2603, \u26032, \u26033);
        this.g.a(fp2.b(), (pk)\u26034);
        List<pz.a> \u26036 = fp2.a();
        if (\u26036 != null) {
            \u26034.H().a(\u26036);
        }
    }

    @Override
    public void a(hz hz2) {
        fh.a(hz2, this, this.f);
        pk pk2 = this.g.a(hz2.a());
        if (pk2 == null) {
            return;
        }
        pk2.bW = hz2.b();
        pk2.bX = hz2.c();
        pk2.bY = hz2.d();
        double \u26032 = (double)pk2.bW / 32.0;
        double \u26033 = (double)pk2.bX / 32.0;
        double \u26034 = (double)pk2.bY / 32.0;
        float \u26035 = (float)(hz2.e() * 360) / 256.0f;
        float \u26036 = (float)(hz2.f() * 360) / 256.0f;
        if (Math.abs(pk2.s - \u26032) >= 0.03125 || Math.abs(pk2.t - \u26033) >= 0.015625 || Math.abs(pk2.u - \u26034) >= 0.03125) {
            pk2.a(\u26032, \u26033, \u26034, \u26035, \u26036, 3, true);
        } else {
            pk2.a(pk2.s, pk2.t, pk2.u, \u26035, \u26036, 3, true);
        }
        pk2.C = hz2.g();
    }

    @Override
    public void a(hi hi2) {
        fh.a(hi2, this, this.f);
        if (hi2.a() >= 0 && hi2.a() < wm.i()) {
            this.f.h.bi.c = hi2.a();
        }
    }

    @Override
    public void a(gv gv2) {
        fh.a(gv2, this, this.f);
        pk pk2 = gv2.a(this.g);
        if (pk2 == null) {
            return;
        }
        pk2.bW += gv2.a();
        pk2.bX += gv2.b();
        pk2.bY += gv2.c();
        double \u26032 = (double)pk2.bW / 32.0;
        double \u26033 = (double)pk2.bX / 32.0;
        double \u26034 = (double)pk2.bY / 32.0;
        float \u26035 = gv2.f() ? (float)(gv2.d() * 360) / 256.0f : pk2.y;
        float \u26036 = gv2.f() ? (float)(gv2.e() * 360) / 256.0f : pk2.z;
        pk2.a(\u26032, \u26033, \u26034, \u26035, \u26036, 3, false);
        pk2.C = gv2.g();
    }

    @Override
    public void a(hf hf2) {
        fh.a(hf2, this, this.f);
        pk pk2 = hf2.a(this.g);
        if (pk2 == null) {
            return;
        }
        float \u26032 = (float)(hf2.a() * 360) / 256.0f;
        pk2.f(\u26032);
    }

    @Override
    public void a(hb hb2) {
        fh.a(hb2, this, this.f);
        for (int i2 = 0; i2 < hb2.a().length; ++i2) {
            this.g.e(hb2.a()[i2]);
        }
    }

    @Override
    public void a(fi fi2) {
        fh.a(fi2, this, this.f);
        bew bew2 = this.f.h;
        double \u26032 = fi2.a();
        double \u26033 = fi2.b();
        double \u26034 = fi2.c();
        float \u26035 = fi2.d();
        float \u26036 = fi2.e();
        if (fi2.f().contains((Object)fi.a.a)) {
            \u26032 += bew2.s;
        } else {
            bew2.v = 0.0;
        }
        if (fi2.f().contains((Object)fi.a.b)) {
            \u26033 += bew2.t;
        } else {
            bew2.w = 0.0;
        }
        if (fi2.f().contains((Object)fi.a.c)) {
            \u26034 += bew2.u;
        } else {
            bew2.x = 0.0;
        }
        if (fi2.f().contains((Object)fi.a.e)) {
            \u26036 += bew2.z;
        }
        if (fi2.f().contains((Object)fi.a.d)) {
            \u26035 += bew2.y;
        }
        bew2.a(\u26032, \u26033, \u26034, \u26035, \u26036);
        this.c.a(new ip.b(bew2.s, bew2.aR().b, bew2.u, bew2.y, bew2.z, false));
        if (!this.h) {
            this.f.h.p = this.f.h.s;
            this.f.h.q = this.f.h.t;
            this.f.h.r = this.f.h.u;
            this.h = true;
            this.f.a((axu)null);
        }
    }

    @Override
    public void a(fz fz2) {
        fh.a(fz2, this, this.f);
        for (fz.a a2 : fz2.a()) {
            this.g.b(a2.a(), a2.c());
        }
    }

    @Override
    public void a(go go2) {
        fh.a(go2, this, this.f);
        if (go2.e()) {
            if (go2.d() != 0) {
                this.g.b(go2.b(), go2.c(), true);
            } else {
                this.g.b(go2.b(), go2.c(), false);
                return;
            }
        }
        this.g.a(go2.b() << 4, 0, go2.c() << 4, (go2.b() << 4) + 15, 256, (go2.c() << 4) + 15);
        amy amy2 = this.g.a(go2.b(), go2.c());
        amy2.a(go2.a(), go2.d(), go2.e());
        this.g.b(go2.b() << 4, 0, go2.c() << 4, (go2.b() << 4) + 15, 256, (go2.c() << 4) + 15);
        if (!go2.e() || !(this.g.t instanceof ano)) {
            amy2.l();
        }
    }

    @Override
    public void a(fv fv2) {
        fh.a(fv2, this, this.f);
        this.g.b(fv2.b(), fv2.a());
    }

    @Override
    public void a(gh gh2) {
        this.c.a(gh2.a());
    }

    @Override
    public void a(eu eu2) {
        this.f.a((bdb)null);
        if (this.e != null) {
            if (this.e instanceof awr) {
                this.f.a(new DisconnectedRealmsScreen(((awr)this.e).a(), "disconnect.lost", eu2).getProxy());
            } else {
                this.f.a(new axh(this.e, "disconnect.lost", eu2));
            }
        } else {
            this.f.a(new axh(new azh(new aya()), "disconnect.lost", eu2));
        }
    }

    public void a(ff ff2) {
        this.c.a(ff2);
    }

    @Override
    public void a(hy hy2) {
        fh.a(hy2, this, this.f);
        pk pk2 = this.g.a(hy2.a());
        pr \u26032 = (pr)this.g.a(hy2.b());
        if (\u26032 == null) {
            \u26032 = this.f.h;
        }
        if (pk2 != null) {
            if (pk2 instanceof pp) {
                this.g.a(pk2, "random.orb", 0.2f, ((this.k.nextFloat() - this.k.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            } else {
                this.g.a(pk2, "random.pop", 0.2f, ((this.k.nextFloat() - this.k.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            }
            this.f.j.a(new bdw((adm)this.g, pk2, \u26032, 0.5f));
            this.g.e(hy2.a());
        }
    }

    @Override
    public void a(fy fy2) {
        fh.a(fy2, this, this.f);
        if (fy2.c() == 2) {
            this.f.q.a(fy2.a(), false);
        } else {
            this.f.q.d().a(fy2.a());
        }
    }

    @Override
    public void a(fq fq22) {
        fq fq22;
        fh.a(fq22, this, this.f);
        pk pk2 = this.g.a(fq22.a());
        if (pk2 == null) {
            return;
        }
        if (fq22.b() == 0) {
            pr pr2 = (pr)pk2;
            pr2.bw();
        } else if (fq22.b() == 1) {
            pk2.ar();
        } else if (fq22.b() == 2) {
            wn wn2 = (wn)pk2;
            wn2.a(false, false, false);
        } else if (fq22.b() == 4) {
            this.f.j.a(pk2, cy.j);
        } else if (fq22.b() == 5) {
            this.f.j.a(pk2, cy.k);
        }
    }

    @Override
    public void a(ha ha2) {
        fh.a(ha2, this, this.f);
        ha2.a(this.g).a(ha2.a());
    }

    @Override
    public void a(fn fn22) {
        fn fn22;
        fh.a(fn22, this, this.f);
        double d2 = (double)fn22.d() / 32.0;
        \u2603 = (double)fn22.e() / 32.0;
        \u2603 = (double)fn22.f() / 32.0;
        float \u26032 = (float)(fn22.j() * 360) / 256.0f;
        float \u26033 = (float)(fn22.k() * 360) / 256.0f;
        pr \u26034 = (pr)pm.a(fn22.c(), (adm)this.f.f);
        \u26034.bW = fn22.d();
        \u26034.bX = fn22.e();
        \u26034.bY = fn22.f();
        \u26034.aI = \u26034.aK = (float)(fn22.l() * 360) / 256.0f;
        pk[] \u26035 = \u26034.aB();
        if (\u26035 != null) {
            int n2 = fn22.b() - \u26034.F();
            for (\u2603 = 0; \u2603 < \u26035.length; ++\u2603) {
                \u26035[\u2603].d(\u26035[\u2603].F() + n2);
            }
        }
        \u26034.d(fn22.b());
        \u26034.a(d2, \u2603, \u2603, \u26032, \u26033);
        \u26034.v = (float)fn22.g() / 8000.0f;
        \u26034.w = (float)fn22.h() / 8000.0f;
        \u26034.x = (float)fn22.i() / 8000.0f;
        this.g.a(fn22.b(), (pk)\u26034);
        List<pz.a> \u26036 = fn22.a();
        if (\u26036 != null) {
            \u26034.H().a(\u26036);
        }
    }

    @Override
    public void a(hu hu2) {
        fh.a(hu2, this, this.f);
        this.f.f.a(hu2.a());
        this.f.f.b(hu2.b());
    }

    @Override
    public void a(ht ht2) {
        fh.a(ht2, this, this.f);
        this.f.h.a(ht2.a(), true);
        this.f.f.P().a(ht2.a());
    }

    @Override
    public void a(hl hl2) {
        fh.a(hl2, this, this.f);
        pk pk2 = this.g.a(hl2.b());
        \u2603 = this.g.a(hl2.c());
        if (hl2.a() == 0) {
            boolean \u26032 = false;
            if (hl2.b() == this.f.h.F()) {
                pk2 = this.f.h;
                if (\u2603 instanceof ux) {
                    ((ux)\u2603).a(false);
                }
                \u26032 = pk2.m == null && \u2603 != null;
            } else if (\u2603 instanceof ux) {
                ((ux)\u2603).a(true);
            }
            if (pk2 == null) {
                return;
            }
            pk2.a(\u2603);
            if (\u26032) {
                avh \u26033 = this.f.t;
                this.f.q.a(bnq.a("mount.onboard", avh.c(\u26033.ac.i())), false);
            }
        } else if (hl2.a() == 1 && pk2 instanceof ps) {
            if (\u2603 != null) {
                ((ps)pk2).a(\u2603, false);
            } else {
                ((ps)pk2).a(false, false);
            }
        }
    }

    @Override
    public void a(gi gi2) {
        fh.a(gi2, this, this.f);
        pk pk2 = gi2.a(this.g);
        if (pk2 != null) {
            if (gi2.a() == 21) {
                this.f.W().a(new bpc((vt)pk2));
            } else {
                pk2.a(gi2.a());
            }
        }
    }

    @Override
    public void a(hp hp2) {
        fh.a(hp2, this, this.f);
        this.f.h.n(hp2.a());
        this.f.h.cl().a(hp2.b());
        this.f.h.cl().b(hp2.c());
    }

    @Override
    public void a(ho ho2) {
        fh.a(ho2, this, this.f);
        this.f.h.a(ho2.a(), ho2.b(), ho2.c());
    }

    @Override
    public void a(he he22) {
        he he22;
        fh.a(he22, this, this.f);
        if (he22.a() != this.f.h.am) {
            this.h = false;
            auo auo2 = this.g.Z();
            this.g = new bdb(this, new adp(0L, he22.c(), false, this.f.f.P().t(), he22.d()), he22.a(), he22.b(), this.f.A);
            this.g.a(auo2);
            this.f.a(this.g);
            this.f.h.am = he22.a();
            this.f.a(new axs(this));
        }
        this.f.a(he22.a());
        this.f.c.a(he22.c());
    }

    @Override
    public void a(gk gk2) {
        fh.a(gk2, this, this.f);
        adi adi2 = new adi(this.f.f, null, gk2.d(), gk2.e(), gk2.f(), gk2.g(), gk2.h());
        adi2.a(true);
        this.f.h.v += (double)gk2.a();
        this.f.h.w += (double)gk2.b();
        this.f.h.x += (double)gk2.c();
    }

    @Override
    public void a(gc gc22) {
        gc gc22;
        fh.a(gc22, this, this.f);
        bew bew2 = this.f.h;
        if ("minecraft:container".equals(gc22.b())) {
            bew2.a(new oq(gc22.c(), gc22.d()));
            bew2.bk.d = gc22.a();
        } else if ("minecraft:villager".equals(gc22.b())) {
            bew2.a(new wg(bew2, gc22.c()));
            bew2.bk.d = gc22.a();
        } else if ("EntityHorse".equals(gc22.b())) {
            pk pk2 = this.g.a(gc22.e());
            if (pk2 instanceof tp) {
                bew2.a((tp)pk2, new xj(gc22.c(), gc22.d()));
                bew2.bk.d = gc22.a();
            }
        } else if (!gc22.f()) {
            bew2.a(new bey(gc22.b(), gc22.c()));
            bew2.bk.d = gc22.a();
        } else {
            bez bez2 = new bez(gc22.b(), gc22.c(), gc22.d());
            bew2.a(bez2);
            bew2.bk.d = gc22.a();
        }
    }

    @Override
    public void a(gf gf22) {
        fh.a(gf22, this, this.f);
        bew bew2 = this.f.h;
        if (gf22.a() == -1) {
            bew2.bi.b(gf22.c());
        } else {
            gf gf22;
            Object object;
            boolean bl2 = false;
            if (this.f.m instanceof ayu) {
                object = (ayu)this.f.m;
                boolean bl3 = bl2 = ((ayu)object).f() != yz.m.a();
            }
            if (gf22.a() == 0 && gf22.b() >= 36 && gf22.b() < 45) {
                object = bew2.bj.a(gf22.b()).d();
                if (gf22.c() != null && (object == null || ((zx)object).b < gf22.c().b)) {
                    gf22.c().c = 5;
                }
                bew2.bj.a(gf22.b(), gf22.c());
            } else if (!(gf22.a() != bew2.bk.d || gf22.a() == 0 && bl2)) {
                bew2.bk.a(gf22.b(), gf22.c());
            }
        }
    }

    @Override
    public void a(ga ga2) {
        fh.a(ga2, this, this.f);
        xi xi2 = null;
        bew \u26032 = this.f.h;
        if (ga2.a() == 0) {
            xi2 = \u26032.bj;
        } else if (ga2.a() == \u26032.bk.d) {
            xi2 = \u26032.bk;
        }
        if (xi2 != null && !ga2.c()) {
            this.a(new ii(ga2.a(), ga2.b(), true));
        }
    }

    @Override
    public void a(gd gd2) {
        fh.a(gd2, this, this.f);
        bew bew2 = this.f.h;
        if (gd2.a() == 0) {
            bew2.bj.a(gd2.b());
        } else if (gd2.a() == bew2.bk.d) {
            bew2.bk.a(gd2.b());
        }
    }

    @Override
    public void a(gw gw2) {
        fh.a(gw2, this, this.f);
        akw akw2 = this.g.s(gw2.a());
        if (!(akw2 instanceof aln)) {
            akw2 = new aln();
            akw2.a(this.g);
            akw2.a(gw2.a());
        }
        this.f.h.a((aln)akw2);
    }

    @Override
    public void a(hw hw2) {
        fh.a(hw2, this, this.f);
        boolean \u26032 = false;
        if (this.f.f.e(hw2.a()) && (\u2603 = this.f.f.s(hw2.a())) instanceof aln) {
            aln aln2 = (aln)\u2603;
            if (aln2.b()) {
                System.arraycopy(hw2.b(), 0, aln2.a, 0, 4);
                aln2.p_();
            }
            \u26032 = true;
        }
        if (!\u26032 && this.f.h != null) {
            this.f.h.a(new fa("Unable to locate sign at " + hw2.a().n() + ", " + hw2.a().o() + ", " + hw2.a().p()));
        }
    }

    @Override
    public void a(ft ft2) {
        fh.a(ft2, this, this.f);
        if (this.f.f.e(ft2.a())) {
            akw akw2 = this.f.f.s(ft2.a());
            int \u26032 = ft2.b();
            if (\u26032 == 1 && akw2 instanceof all || \u26032 == 2 && akw2 instanceof akz || \u26032 == 3 && akw2 instanceof akv || \u26032 == 4 && akw2 instanceof alo || \u26032 == 5 && akw2 instanceof alg || \u26032 == 6 && akw2 instanceof aku) {
                akw2.a(ft2.c());
            }
        }
    }

    @Override
    public void a(ge ge2) {
        fh.a(ge2, this, this.f);
        bew bew2 = this.f.h;
        if (bew2.bk != null && bew2.bk.d == ge2.a()) {
            bew2.bk.b(ge2.b(), ge2.c());
        }
    }

    @Override
    public void a(hn hn2) {
        fh.a(hn2, this, this.f);
        pk pk2 = this.g.a(hn2.b());
        if (pk2 != null) {
            pk2.c(hn2.c(), hn2.a());
        }
    }

    @Override
    public void a(gb gb2) {
        fh.a(gb2, this, this.f);
        this.f.h.q();
    }

    @Override
    public void a(fu fu2) {
        fh.a(fu2, this, this.f);
        this.f.f.c(fu2.a(), fu2.d(), fu2.b(), fu2.c());
    }

    @Override
    public void a(fs fs2) {
        fh.a(fs2, this, this.f);
        this.f.f.c(fs2.a(), fs2.b(), fs2.c());
    }

    @Override
    public void a(gp gp2) {
        fh.a(gp2, this, this.f);
        for (int i2 = 0; i2 < gp2.a(); ++i2) {
            \u2603 = gp2.a(i2);
            \u2603 = gp2.b(i2);
            this.g.b(\u2603, \u2603, true);
            this.g.a(\u2603 << 4, 0, \u2603 << 4, (\u2603 << 4) + 15, 256, (\u2603 << 4) + 15);
            amy amy2 = this.g.a(\u2603, \u2603);
            amy2.a(gp2.c(i2), gp2.d(i2), true);
            this.g.b(\u2603 << 4, 0, \u2603 << 4, (\u2603 << 4) + 15, 256, (\u2603 << 4) + 15);
            if (this.g.t instanceof ano) continue;
            amy2.l();
        }
    }

    @Override
    public void a(gm gm2) {
        fh.a(gm2, this, this.f);
        bew bew2 = this.f.h;
        int \u26032 = gm2.a();
        float \u26033 = gm2.b();
        int \u26034 = ns.d(\u26033 + 0.5f);
        if (\u26032 >= 0 && \u26032 < gm.a.length && gm.a[\u26032] != null) {
            ((wn)bew2).b(new fb(gm.a[\u26032], new Object[0]));
        }
        if (\u26032 == 1) {
            this.g.P().b(true);
            this.g.k(0.0f);
        } else if (\u26032 == 2) {
            this.g.P().b(false);
            this.g.k(1.0f);
        } else if (\u26032 == 3) {
            this.f.c.a(adp.a.a(\u26034));
        } else if (\u26032 == 4) {
            this.f.a(new ayc());
        } else if (\u26032 == 5) {
            avh avh2 = this.f.t;
            if (\u26033 == 0.0f) {
                this.f.a(new axf());
            } else if (\u26033 == 101.0f) {
                this.f.q.d().a(new fb("demo.help.movement", avh.c(avh2.X.i()), avh.c(avh2.Y.i()), avh.c(avh2.Z.i()), avh.c(avh2.aa.i())));
            } else if (\u26033 == 102.0f) {
                this.f.q.d().a(new fb("demo.help.jump", avh.c(avh2.ab.i())));
            } else if (\u26033 == 103.0f) {
                this.f.q.d().a(new fb("demo.help.inventory", avh.c(avh2.ae.i())));
            }
        } else if (\u26032 == 6) {
            this.g.a(bew2.s, bew2.t + (double)bew2.aS(), bew2.u, "random.successful_hit", 0.18f, 0.45f, false);
        } else if (\u26032 == 7) {
            this.g.k(\u26033);
        } else if (\u26032 == 8) {
            this.g.i(\u26033);
        } else if (\u26032 == 10) {
            this.g.a(cy.P, bew2.s, bew2.t, bew2.u, 0.0, 0.0, 0.0, new int[0]);
            this.g.a(bew2.s, bew2.t, bew2.u, "mob.guardian.curse", 1.0f, 1.0f, false);
        }
    }

    @Override
    public void a(gu gu2) {
        fh.a(gu2, this, this.f);
        atg atg2 = aab.a(gu2.a(), this.f.f);
        gu2.a(atg2);
        this.f.o.k().a(atg2);
    }

    @Override
    public void a(gq gq2) {
        fh.a(gq2, this, this.f);
        if (gq2.a()) {
            this.f.f.a(gq2.b(), gq2.d(), gq2.c());
        } else {
            this.f.f.b(gq2.b(), gq2.d(), gq2.c());
        }
    }

    @Override
    public void a(fr fr2) {
        fh.a(fr2, this, this.f);
        boolean bl2 = false;
        for (Map.Entry<mw, Integer> entry : fr2.a().entrySet()) {
            mw mw2 = entry.getKey();
            int \u26032 = entry.getValue();
            if (mw2.d() && \u26032 > 0) {
                if (this.j && this.f.h.x().a(mw2) == 0) {
                    mq mq2 = (mq)mw2;
                    this.f.p.a(mq2);
                    this.f.Y().a(new bqe(mq2), 0L);
                    if (mw2 == mr.f) {
                        this.f.t.I = false;
                        this.f.t.b();
                    }
                }
                bl2 = true;
            }
            this.f.h.x().a(this.f.h, mw2, \u26032);
        }
        if (!this.j && !bl2 && this.f.t.I) {
            this.f.p.b(mr.f);
        }
        this.j = true;
        if (this.f.m instanceof ayg) {
            ((ayg)((Object)this.f.m)).a();
        }
    }

    @Override
    public void a(ib ib2) {
        fh.a(ib2, this, this.f);
        pk pk2 = this.g.a(ib2.b());
        if (!(pk2 instanceof pr)) {
            return;
        }
        pf \u26032 = new pf(ib2.c(), ib2.e(), ib2.d(), false, ib2.f());
        \u26032.b(ib2.a());
        ((pr)pk2).c(\u26032);
    }

    @Override
    public void a(gy gy2) {
        fh.a(gy2, this, this.f);
        pk pk2 = this.g.a(gy2.c);
        pr pr2 = \u2603 = pk2 instanceof pr ? (pr)pk2 : null;
        if (gy2.a == gy.a.b) {
            long l2 = 1000 * gy2.d / 20;
            bqf \u26032 = new bqf(this.f.h, \u2603);
            this.f.Y().a(\u26032, 0L - l2, 0L);
        } else if (gy2.a == gy.a.c && (\u2603 = this.g.a(gy2.b)) instanceof wn) {
            bqg \u26033 = new bqg((wn)\u2603, \u2603);
            \u26033.a(gy2.e);
            this.f.Y().a(\u26033, 0L);
        }
    }

    @Override
    public void a(fw fw2) {
        fh.a(fw2, this, this.f);
        this.f.f.P().a(fw2.b());
        this.f.f.P().e(fw2.a());
    }

    @Override
    public void a(hh hh2) {
        fh.a(hh2, this, this.f);
        pk pk2 = hh2.a(this.g);
        if (pk2 != null) {
            this.f.a(pk2);
        }
    }

    @Override
    public void a(hg hg2) {
        fh.a(hg2, this, this.f);
        hg2.a(this.g.af());
    }

    @Override
    public void a(hv hv2) {
        fh.a(hv2, this, this.f);
        hv.a a2 = hv2.a();
        String \u26032 = null;
        String \u26033 = null;
        String \u26034 = hv2.b() != null ? hv2.b().d() : "";
        switch (a2) {
            case a: {
                \u26032 = \u26034;
                break;
            }
            case b: {
                \u26033 = \u26034;
                break;
            }
            case e: {
                this.f.q.a("", "", -1, -1, -1);
                this.f.q.a();
                return;
            }
        }
        this.f.q.a(\u26032, \u26033, hv2.c(), hv2.d(), hv2.e());
    }

    @Override
    public void a(gl gl2) {
        if (!this.c.c()) {
            this.c.a(gl2.a());
        }
    }

    @Override
    public void a(hx hx2) {
        this.f.q.h().b(hx2.a().d().length() == 0 ? null : hx2.a());
        this.f.q.h().a(hx2.b().d().length() == 0 ? null : hx2.b());
    }

    @Override
    public void a(hc hc2) {
        fh.a(hc2, this, this.f);
        pk pk2 = this.g.a(hc2.a());
        if (pk2 instanceof pr) {
            ((pr)pk2).l(hc2.b());
        }
    }

    @Override
    public void a(gz gz2) {
        fh.a(gz2, this, this.f);
        for (gz.b b2 : gz2.a()) {
            if (gz2.b() == gz.a.e) {
                this.i.remove(b2.a().getId());
                continue;
            }
            bdc bdc2 = this.i.get(b2.a().getId());
            if (gz2.b() == gz.a.a) {
                bdc2 = new bdc(b2);
                this.i.put(bdc2.a().getId(), bdc2);
            }
            if (bdc2 == null) continue;
            switch (gz2.b()) {
                case a: {
                    bdc2.a(b2.c());
                    bdc2.a(b2.b());
                    break;
                }
                case b: {
                    bdc2.a(b2.c());
                    break;
                }
                case c: {
                    bdc2.a(b2.b());
                    break;
                }
                case d: {
                    bdc2.a(b2.d());
                }
            }
        }
    }

    @Override
    public void a(gn gn2) {
        this.a(new io(gn2.a()));
    }

    @Override
    public void a(gx gx2) {
        fh.a(gx2, this, this.f);
        bew bew2 = this.f.h;
        bew2.bA.b = gx2.b();
        bew2.bA.d = gx2.d();
        bew2.bA.a = gx2.a();
        bew2.bA.c = gx2.c();
        bew2.bA.a(gx2.e());
        bew2.bA.b(gx2.f());
    }

    @Override
    public void a(fx fx2) {
        fh.a(fx2, this, this.f);
        String[] stringArray = fx2.a();
        if (this.f.m instanceof awv) {
            awv awv2 = (awv)this.f.m;
            awv2.a(stringArray);
        }
    }

    @Override
    public void a(gs gs2) {
        fh.a(gs2, this, this.f);
        this.f.f.a(gs2.b(), gs2.c(), gs2.d(), gs2.a(), gs2.e(), gs2.f(), false);
    }

    @Override
    public void a(hd hd2) {
        final String string = hd2.a();
        \u2603 = hd2.b();
        if (string.startsWith("level://")) {
            File file = new File(this.f.v, "saves");
            String \u26032 = string.substring("level://".length());
            \u2603 = new File(file, \u26032);
            if (\u2603.isFile()) {
                this.c.a(new iu(\u2603, iu.a.d));
                Futures.addCallback(this.f.R().a(\u2603), new FutureCallback<Object>(){

                    @Override
                    public void onSuccess(Object object) {
                        bcy.this.c.a(new iu(\u2603, iu.a.a));
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        bcy.this.c.a(new iu(\u2603, iu.a.c));
                    }
                });
            } else {
                this.c.a(new iu(\u2603, iu.a.c));
            }
            return;
        }
        if (this.f.D() != null && this.f.D().b() == bde.a.a) {
            this.c.a(new iu(\u2603, iu.a.d));
            Futures.addCallback(this.f.R().a(string, \u2603), new FutureCallback<Object>(){

                @Override
                public void onSuccess(Object object) {
                    bcy.this.c.a(new iu(\u2603, iu.a.a));
                }

                @Override
                public void onFailure(Throwable throwable) {
                    bcy.this.c.a(new iu(\u2603, iu.a.c));
                }
            });
        } else if (this.f.D() == null || this.f.D().b() == bde.a.c) {
            this.f.a(new Runnable(){

                @Override
                public void run() {
                    bcy.this.f.a(new awy(new awx(){

                        @Override
                        public void a(boolean bl2, int n2) {
                            bcy.this.f = ave.A();
                            if (bl2) {
                                if (bcy.this.f.D() != null) {
                                    bcy.this.f.D().a(bde.a.a);
                                }
                                bcy.this.c.a(new iu(\u2603, iu.a.d));
                                Futures.addCallback(bcy.this.f.R().a(string, \u2603), new FutureCallback<Object>(){

                                    @Override
                                    public void onSuccess(Object object) {
                                        bcy.this.c.a(new iu(\u2603, iu.a.a));
                                    }

                                    @Override
                                    public void onFailure(Throwable throwable) {
                                        bcy.this.c.a(new iu(\u2603, iu.a.c));
                                    }
                                });
                            } else {
                                if (bcy.this.f.D() != null) {
                                    bcy.this.f.D().a(bde.a.b);
                                }
                                bcy.this.c.a(new iu(\u2603, iu.a.b));
                            }
                            bdf.b(bcy.this.f.D());
                            bcy.this.f.a((axu)null);
                        }
                    }, bnq.a("multiplayer.texturePrompt.line1", new Object[0]), bnq.a("multiplayer.texturePrompt.line2", new Object[0]), 0));
                }
            });
        } else {
            this.c.a(new iu(\u2603, iu.a.b));
        }
    }

    @Override
    public void a(gj gj2) {
        fh.a(gj2, this, this.f);
        pk pk2 = gj2.a(this.g);
        if (pk2 != null) {
            pk2.g(gj2.a());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void a(gg gg22) {
        gg gg22;
        fh.a(gg22, this, this.f);
        if ("MC|TrList".equals(gg22.a())) {
            em em2 = gg22.b();
            try {
                int n2 = em2.readInt();
                axu \u26032 = this.f.m;
                if (\u26032 == null || !(\u26032 instanceof azd) || n2 != this.f.h.bk.d) return;
                acy \u26033 = ((azd)\u26032).a();
                ada \u26034 = ada.b(em2);
                \u26033.a(\u26034);
                return;
            }
            catch (IOException iOException) {
                b.error("Couldn't load trade info", (Throwable)iOException);
                return;
            }
            finally {
                em2.release();
            }
        } else if ("MC|Brand".equals(gg22.a())) {
            this.f.h.f(gg22.b().c(Short.MAX_VALUE));
            return;
        } else {
            if (!"MC|BOpen".equals(gg22.a()) || (\u2603 = this.f.h.bZ()) == null || \u2603.b() != zy.bN) return;
            this.f.a(new ayo(this.f.h, \u2603, false));
        }
    }

    @Override
    public void a(hq hq2) {
        fh.a(hq2, this, this.f);
        auo auo2 = this.g.Z();
        if (hq2.c() == 0) {
            auk auk2 = auo2.a(hq2.a(), auu.b);
            auk2.a(hq2.b());
            auk2.a(hq2.d());
        } else {
            auk \u26032 = auo2.b(hq2.a());
            if (hq2.c() == 1) {
                auo2.k(\u26032);
            } else if (hq2.c() == 2) {
                \u26032.a(hq2.b());
                \u26032.a(hq2.d());
            }
        }
    }

    @Override
    public void a(hs hs22) {
        hs hs22;
        fh.a(hs22, this, this.f);
        auo auo2 = this.g.Z();
        auk \u26032 = auo2.b(hs22.b());
        if (hs22.d() == hs.a.a) {
            aum aum2 = auo2.c(hs22.a(), \u26032);
            aum2.c(hs22.c());
        } else if (hs22.d() == hs.a.b) {
            if (nx.b(hs22.b())) {
                auo2.d(hs22.a(), null);
            } else if (\u26032 != null) {
                auo2.d(hs22.a(), \u26032);
            }
        }
    }

    @Override
    public void a(hj hj2) {
        fh.a(hj2, this, this.f);
        auo auo2 = this.g.Z();
        if (hj2.b().length() == 0) {
            auo2.a(hj2.a(), null);
        } else {
            auk auk2 = auo2.b(hj2.b());
            auo2.a(hj2.a(), auk2);
        }
    }

    @Override
    public void a(hr hr22) {
        hr hr22;
        fh.a(hr22, this, this.f);
        auo auo2 = this.g.Z();
        aul \u26032 = hr22.f() == 0 ? auo2.e(hr22.a()) : auo2.d(hr22.a());
        if (hr22.f() == 0 || hr22.f() == 2) {
            \u26032.a(hr22.b());
            \u26032.b(hr22.c());
            \u26032.c(hr22.d());
            \u26032.a(a.a(hr22.h()));
            \u26032.a(hr22.g());
            Object object = auq.a.a(hr22.i());
            if (object != null) {
                \u26032.a((auq.a)((Object)object));
            }
        }
        if (hr22.f() == 0 || hr22.f() == 3) {
            for (String string : hr22.e()) {
                auo2.a(string, hr22.a());
            }
        }
        if (hr22.f() == 4) {
            for (String string : hr22.e()) {
                auo2.a(string, \u26032);
            }
        }
        if (hr22.f() == 1) {
            auo2.d(\u26032);
        }
    }

    @Override
    public void a(gr gr2) {
        fh.a(gr2, this, this.f);
        if (gr2.j() == 0) {
            double d2 = gr2.i() * gr2.f();
            \u2603 = gr2.i() * gr2.g();
            \u2603 = gr2.i() * gr2.h();
            try {
                this.g.a(gr2.a(), gr2.b(), gr2.c(), gr2.d(), gr2.e(), d2, \u2603, \u2603, gr2.k());
            }
            catch (Throwable \u26032) {
                b.warn("Could not spawn particle effect " + (Object)((Object)gr2.a()));
            }
        } else {
            for (int i2 = 0; i2 < gr2.j(); ++i2) {
                double d3 = this.k.nextGaussian() * (double)gr2.f();
                \u2603 = this.k.nextGaussian() * (double)gr2.g();
                \u2603 = this.k.nextGaussian() * (double)gr2.h();
                \u2603 = this.k.nextGaussian() * (double)gr2.i();
                \u2603 = this.k.nextGaussian() * (double)gr2.i();
                \u2603 = this.k.nextGaussian() * (double)gr2.i();
                try {
                    this.g.a(gr2.a(), gr2.b(), gr2.c() + d3, gr2.d() + \u2603, gr2.e() + \u2603, \u2603, \u2603, \u2603, gr2.k());
                    continue;
                }
                catch (Throwable \u26033) {
                    b.warn("Could not spawn particle effect " + (Object)((Object)gr2.a()));
                    return;
                }
            }
        }
    }

    @Override
    public void a(ia ia2) {
        fh.a(ia2, this, this.f);
        pk pk2 = this.g.a(ia2.a());
        if (pk2 == null) {
            return;
        }
        if (!(pk2 instanceof pr)) {
            throw new IllegalStateException("Server tried to update attributes of a non-living entity (actually: " + pk2 + ")");
        }
        qf \u26032 = ((pr)pk2).by();
        for (ia.a a2 : ia2.b()) {
            qc qc2 = \u26032.a(a2.a());
            if (qc2 == null) {
                qc2 = \u26032.b(new qj(null, a2.a(), 0.0, Double.MIN_NORMAL, Double.MAX_VALUE));
            }
            qc2.a(a2.b());
            qc2.d();
            for (qd qd2 : a2.c()) {
                qc2.b(qd2);
            }
        }
    }

    public ek a() {
        return this.c;
    }

    public Collection<bdc> d() {
        return this.i.values();
    }

    public bdc a(UUID uUID) {
        return this.i.get(uUID);
    }

    public bdc a(String string) {
        for (bdc bdc2 : this.i.values()) {
            if (!bdc2.a().getName().equals(string)) continue;
            return bdc2;
        }
        return null;
    }

    public GameProfile e() {
        return this.d;
    }
}

