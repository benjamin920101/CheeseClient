/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.util.concurrent.Futures;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class lm
implements ic,
km {
    private static final Logger c = LogManager.getLogger();
    public final ek a;
    private final MinecraftServer d;
    public lf b;
    private int e;
    private int f;
    private int g;
    private boolean h;
    private int i;
    private long j;
    private long k;
    private int l;
    private int m;
    private nm<Short> n = new nm();
    private double o;
    private double p;
    private double q;
    private boolean r = true;

    public lm(MinecraftServer minecraftServer, ek ek2, lf lf2) {
        this.d = minecraftServer;
        this.a = ek2;
        ek2.a(this);
        this.b = lf2;
        lf2.a = this;
    }

    @Override
    public void c() {
        this.h = false;
        ++this.e;
        this.d.c.a("keepAlive");
        if ((long)this.e - this.k > 40L) {
            this.k = this.e;
            this.j = this.d();
            this.i = (int)this.j;
            this.a(new gn(this.i));
        }
        this.d.c.b();
        if (this.l > 0) {
            --this.l;
        }
        if (this.m > 0) {
            --this.m;
        }
        if (this.b.D() > 0L && this.d.aA() > 0 && MinecraftServer.az() - this.b.D() > (long)(this.d.aA() * 1000 * 60)) {
            this.c("You have been idle for too long!");
        }
    }

    public ek a() {
        return this.a;
    }

    public void c(String string) {
        final fa fa2 = new fa(string);
        this.a.a(new gh(fa2), (GenericFutureListener<? extends Future<? super Void>>)new GenericFutureListener<Future<? super Void>>(){

            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                lm.this.a.a(fa2);
            }
        }, new GenericFutureListener[0]);
        this.a.k();
        Futures.getUnchecked(this.d.a(new Runnable(){

            @Override
            public void run() {
                lm.this.a.l();
            }
        }));
    }

    @Override
    public void a(it it2) {
        fh.a(it2, this, this.b.u());
        this.b.a(it2.a(), it2.b(), it2.c(), it2.d());
    }

    private boolean b(ip ip2) {
        return !Doubles.isFinite(ip2.a()) || !Doubles.isFinite(ip2.b()) || !Doubles.isFinite(ip2.c()) || !Floats.isFinite(ip2.e()) || !Floats.isFinite(ip2.d());
    }

    @Override
    public void a(ip ip2) {
        fh.a(ip2, this, this.b.u());
        if (this.b(ip2)) {
            this.c("Invalid move packet received");
            return;
        }
        le le2 = this.d.a(this.b.am);
        this.h = true;
        if (this.b.i) {
            return;
        }
        double \u26032 = this.b.s;
        double \u26033 = this.b.t;
        double \u26034 = this.b.u;
        double \u26035 = 0.0;
        double \u26036 = ip2.a() - this.o;
        double \u26037 = ip2.b() - this.p;
        double \u26038 = ip2.c() - this.q;
        if (ip2.g()) {
            \u26035 = \u26036 * \u26036 + \u26037 * \u26037 + \u26038 * \u26038;
            if (!this.r && \u26035 < 0.25) {
                this.r = true;
            }
        }
        if (this.r) {
            this.f = this.e;
            if (this.b.m != null) {
                float f2 = this.b.y;
                \u2603 = this.b.z;
                this.b.m.al();
                double \u26039 = this.b.s;
                double \u260310 = this.b.t;
                double \u260311 = this.b.u;
                if (ip2.h()) {
                    f2 = ip2.d();
                    \u2603 = ip2.e();
                }
                this.b.C = ip2.f();
                this.b.l();
                this.b.a(\u26039, \u260310, \u260311, f2, \u2603);
                if (this.b.m != null) {
                    this.b.m.al();
                }
                this.d.ap().d(this.b);
                if (this.b.m != null) {
                    if (\u26035 > 4.0) {
                        pk pk2 = this.b.m;
                        this.b.a.a(new hz(pk2));
                        this.a(this.b.s, this.b.t, this.b.u, this.b.y, this.b.z);
                    }
                    this.b.m.ai = true;
                }
                if (this.r) {
                    this.o = this.b.s;
                    this.p = this.b.t;
                    this.q = this.b.u;
                }
                le2.g(this.b);
                return;
            }
            if (this.b.bJ()) {
                this.b.l();
                this.b.a(this.o, this.p, this.q, this.b.y, this.b.z);
                le2.g(this.b);
                return;
            }
            double d2 = this.b.t;
            this.o = this.b.s;
            this.p = this.b.t;
            this.q = this.b.u;
            \u2603 = this.b.s;
            \u2603 = this.b.t;
            \u2603 = this.b.u;
            float \u260312 = this.b.y;
            float \u260313 = this.b.z;
            if (ip2.g() && ip2.b() == -999.0) {
                ip2.a(false);
            }
            if (ip2.g()) {
                \u2603 = ip2.a();
                \u2603 = ip2.b();
                \u2603 = ip2.c();
                if (Math.abs(ip2.a()) > 3.0E7 || Math.abs(ip2.c()) > 3.0E7) {
                    this.c("Illegal position");
                    return;
                }
            }
            if (ip2.h()) {
                \u260312 = ip2.d();
                \u260313 = ip2.e();
            }
            this.b.l();
            this.b.a(this.o, this.p, this.q, \u260312, \u260313);
            if (!this.r) {
                return;
            }
            \u2603 = \u2603 - this.b.s;
            \u2603 = \u2603 - this.b.t;
            \u2603 = \u2603 - this.b.u;
            \u2603 = \u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603;
            \u2603 = this.b.v * this.b.v + this.b.w * this.b.w + this.b.x * this.b.x;
            if (!(!(\u2603 - \u2603 > 100.0) || this.d.T() && this.d.S().equals(this.b.e_()))) {
                c.warn(this.b.e_() + " moved too quickly! " + \u2603 + "," + \u2603 + "," + \u2603 + " (" + \u2603 + ", " + \u2603 + ", " + \u2603 + ")");
                this.a(this.o, this.p, this.q, this.b.y, this.b.z);
                return;
            }
            float \u260314 = 0.0625f;
            boolean \u260315 = le2.a((pk)this.b, this.b.aR().d(\u260314, \u260314, \u260314)).isEmpty();
            if (this.b.C && !ip2.f() && \u2603 > 0.0) {
                this.b.bF();
            }
            this.b.d(\u2603, \u2603, \u2603);
            this.b.C = ip2.f();
            \u2603 = \u2603;
            \u2603 = \u2603 - this.b.s;
            \u2603 = \u2603 - this.b.t;
            if (\u2603 > -0.5 || \u2603 < 0.5) {
                \u2603 = 0.0;
            }
            \u2603 = \u2603 - this.b.u;
            \u2603 = \u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603;
            boolean \u260316 = false;
            if (\u2603 > 0.0625 && !this.b.bJ() && !this.b.c.d()) {
                \u260316 = true;
                c.warn(this.b.e_() + " moved wrongly!");
            }
            this.b.a(\u2603, \u2603, \u2603, \u260312, \u260313);
            this.b.k(this.b.s - \u26032, this.b.t - \u26033, this.b.u - \u26034);
            if (!this.b.T) {
                boolean bl2 = le2.a((pk)this.b, this.b.aR().d(\u260314, \u260314, \u260314)).isEmpty();
                if (\u260315 && (\u260316 || !bl2) && !this.b.bJ()) {
                    this.a(this.o, this.p, this.q, \u260312, \u260313);
                    return;
                }
            }
            aug aug2 = this.b.aR().b(\u260314, \u260314, \u260314).a(0.0, -0.55, 0.0);
            if (!(this.d.ak() || this.b.bA.c || le2.c(aug2))) {
                if (\u2603 >= -0.03125) {
                    ++this.g;
                    if (this.g > 80) {
                        c.warn(this.b.e_() + " was kicked for floating too long!");
                        this.c("Flying is not enabled on this server");
                        return;
                    }
                }
            } else {
                this.g = 0;
            }
            this.b.C = ip2.f();
            this.d.ap().d(this.b);
            this.b.a(this.b.t - d2, ip2.f());
        } else if (this.e - this.f > 20) {
            this.a(this.o, this.p, this.q, this.b.y, this.b.z);
        }
    }

    public void a(double d2, double d3, double d4, float f2, float f3) {
        this.a(d2, d3, d4, f2, f3, Collections.<fi.a>emptySet());
    }

    public void a(double d2, double d3, double d4, float f2, float f3, Set<fi.a> set) {
        this.r = false;
        this.o = d2;
        this.p = d3;
        this.q = d4;
        if (set.contains((Object)fi.a.a)) {
            this.o += this.b.s;
        }
        if (set.contains((Object)fi.a.b)) {
            this.p += this.b.t;
        }
        if (set.contains((Object)fi.a.c)) {
            this.q += this.b.u;
        }
        float f4 = f2;
        \u2603 = f3;
        if (set.contains((Object)fi.a.d)) {
            f4 += this.b.y;
        }
        if (set.contains((Object)fi.a.e)) {
            \u2603 += this.b.z;
        }
        this.b.a(this.o, this.p, this.q, f4, \u2603);
        this.b.a.a(new fi(d2, d3, d4, f2, f3, set));
    }

    @Override
    public void a(ir ir22) {
        ir ir22;
        fh.a(ir22, this, this.b.u());
        le le2 = this.d.a(this.b.am);
        cj \u26032 = ir22.a();
        this.b.z();
        switch (ir22.c()) {
            case e: {
                if (!this.b.v()) {
                    this.b.a(false);
                }
                return;
            }
            case d: {
                if (!this.b.v()) {
                    this.b.a(true);
                }
                return;
            }
            case f: {
                this.b.bU();
                return;
            }
            case a: 
            case b: 
            case c: {
                double d2 = this.b.s - ((double)\u26032.n() + 0.5);
                \u2603 = this.b.t - ((double)\u26032.o() + 0.5) + 1.5;
                \u2603 = this.b.u - ((double)\u26032.p() + 0.5);
                \u2603 = d2 * d2 + \u2603 * \u2603 + \u2603 * \u2603;
                if (\u2603 > 36.0) {
                    return;
                }
                if (\u26032.o() < this.d.an()) break;
                return;
            }
            default: {
                throw new IllegalArgumentException("Invalid player action");
            }
        }
        if (ir22.c() == ir.a.a) {
            if (!this.d.a(le2, \u26032, this.b) && le2.af().a(\u26032)) {
                this.b.c.a(\u26032, ir22.b());
            } else {
                this.b.a.a(new fv(le2, \u26032));
            }
        } else {
            if (ir22.c() == ir.a.c) {
                this.b.c.a(\u26032);
            } else if (ir22.c() == ir.a.b) {
                this.b.c.e();
            }
            if (le2.p(\u26032).c().t() != arm.a) {
                this.b.a.a(new fv(le2, \u26032));
            }
        }
    }

    @Override
    public void a(ja ja2) {
        Object object;
        fh.a(ja2, this, this.b.u());
        le le2 = this.d.a(this.b.am);
        zx \u26032 = this.b.bi.h();
        boolean \u26033 = false;
        cj \u26034 = ja2.a();
        cq \u26035 = cq.a(ja2.b());
        this.b.z();
        if (ja2.b() == 255) {
            if (\u26032 == null) {
                return;
            }
            this.b.c.a(this.b, le2, \u26032);
        } else if (\u26034.o() < this.d.an() - 1 || \u26035 != cq.b && \u26034.o() < this.d.an()) {
            if (this.r && this.b.e((double)\u26034.n() + 0.5, (double)\u26034.o() + 0.5, (double)\u26034.p() + 0.5) < 64.0 && !this.d.a(le2, \u26034, this.b) && le2.af().a(\u26034)) {
                this.b.c.a(this.b, le2, \u26032, \u26034, \u26035, ja2.d(), ja2.e(), ja2.f());
            }
            \u26033 = true;
        } else {
            object = new fb("build.tooHigh", this.d.an());
            ((es)object).b().a(a.m);
            this.b.a.a(new fy((eu)object));
            \u26033 = true;
        }
        if (\u26033) {
            this.b.a.a(new fv(le2, \u26034));
            this.b.a.a(new fv(le2, \u26034.a(\u26035)));
        }
        if ((\u26032 = this.b.bi.h()) != null && \u26032.b == 0) {
            this.b.bi.a[this.b.bi.c] = null;
            \u26032 = null;
        }
        if (\u26032 == null || \u26032.l() == 0) {
            this.b.g = true;
            this.b.bi.a[this.b.bi.c] = zx.b(this.b.bi.a[this.b.bi.c]);
            object = this.b.bk.a(this.b.bi, this.b.bi.c);
            this.b.bk.b();
            this.b.g = false;
            if (!zx.b(this.b.bi.h(), ja2.c())) {
                this.a(new gf(this.b.bk.d, ((yg)object).e, this.b.bi.h()));
            }
        }
    }

    @Override
    public void a(iz iz2) {
        fh.a(iz2, this, this.b.u());
        if (this.b.v()) {
            pk pk2 = null;
            for (le le2 : this.d.d) {
                if (le2 != null && (pk2 = iz2.a(le2)) != null) break;
            }
            if (pk2 != null) {
                this.b.e((pk)this.b);
                this.b.a((pk)null);
                if (pk2.o != this.b.o) {
                    le le3 = this.b.u();
                    \u2603 = (le)pk2.o;
                    this.b.am = pk2.am;
                    this.a(new he(this.b.am, le3.aa(), le3.P().u(), this.b.c.b()));
                    le3.f(this.b);
                    this.b.I = false;
                    this.b.b(pk2.s, pk2.t, pk2.u, pk2.y, pk2.z);
                    if (this.b.ai()) {
                        le3.a((pk)this.b, false);
                        \u2603.d(this.b);
                        \u2603.a((pk)this.b, false);
                    }
                    this.b.a(\u2603);
                    this.d.ap().a(this.b, le3);
                    this.b.a(pk2.s, pk2.t, pk2.u);
                    this.b.c.a(\u2603);
                    this.d.ap().b(this.b, \u2603);
                    this.d.ap().f(this.b);
                } else {
                    this.b.a(pk2.s, pk2.t, pk2.u);
                }
            }
        }
    }

    @Override
    public void a(iu iu2) {
    }

    @Override
    public void a(eu eu2) {
        c.info(this.b.e_() + " lost connection: " + eu2);
        this.d.aH();
        fb fb2 = new fb("multiplayer.player.left", this.b.f_());
        fb2.b().a(a.o);
        this.d.ap().a(fb2);
        this.b.q();
        this.d.ap().e(this.b);
        if (this.d.T() && this.b.e_().equals(this.d.S())) {
            c.info("Stopping singleplayer server as player logged out");
            this.d.w();
        }
    }

    public void a(final ff ff2) {
        Object \u26032;
        if (ff2 instanceof fy) {
            fy fy2 = (fy)ff2;
            \u26032 = this.b.y();
            if (\u26032 == wn.b.c) {
                return;
            }
            if (\u26032 == wn.b.b && !fy2.b()) {
                return;
            }
        }
        try {
            this.a.a(ff2);
        }
        catch (Throwable throwable) {
            \u26032 = b.a(throwable, "Sending packet");
            c c2 = ((b)\u26032).a("Packet being sent");
            c2.a("Packet class", new Callable<String>(){

                public String a() throws Exception {
                    return ff2.getClass().getCanonicalName();
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            throw new e((b)\u26032);
        }
    }

    @Override
    public void a(iv iv2) {
        fh.a(iv2, this, this.b.u());
        if (iv2.a() < 0 || iv2.a() >= wm.i()) {
            c.warn(this.b.e_() + " tried to set an invalid carried item");
            return;
        }
        this.b.bi.c = iv2.a();
        this.b.z();
    }

    @Override
    public void a(ie ie22) {
        ie ie22;
        fh.a(ie22, this, this.b.u());
        if (this.b.y() == wn.b.c) {
            fb fb2 = new fb("chat.cannotSend", new Object[0]);
            fb2.b().a(a.m);
            this.a(new fy(fb2));
            return;
        }
        this.b.z();
        String \u26032 = ie22.a();
        \u26032 = StringUtils.normalizeSpace(\u26032);
        for (int i2 = 0; i2 < \u26032.length(); ++i2) {
            if (f.a(\u26032.charAt(i2))) continue;
            this.c("Illegal characters in chat");
            return;
        }
        if (\u26032.startsWith("/")) {
            this.d(\u26032);
        } else {
            fb fb3 = new fb("chat.type.text", this.b.f_(), \u26032);
            this.d.ap().a(fb3, false);
        }
        this.l += 20;
        if (this.l > 200 && !this.d.ap().h(this.b.cd())) {
            this.c("disconnect.spam");
        }
    }

    private void d(String string) {
        this.d.P().a(this.b, string);
    }

    @Override
    public void a(iy iy2) {
        fh.a(iy2, this, this.b.u());
        this.b.z();
        this.b.bw();
    }

    @Override
    public void a(is is2) {
        fh.a(is2, this, this.b.u());
        this.b.z();
        switch (is2.b()) {
            case a: {
                this.b.c(true);
                break;
            }
            case b: {
                this.b.c(false);
                break;
            }
            case d: {
                this.b.d(true);
                break;
            }
            case e: {
                this.b.d(false);
                break;
            }
            case c: {
                this.b.a(false, true, true);
                this.r = false;
                break;
            }
            case f: {
                if (!(this.b.m instanceof tp)) break;
                ((tp)this.b.m).v(is2.c());
                break;
            }
            case g: {
                if (!(this.b.m instanceof tp)) break;
                ((tp)this.b.m).g(this.b);
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid client command!");
            }
        }
    }

    @Override
    public void a(in in2) {
        fh.a(in2, this, this.b.u());
        le le2 = this.d.a(this.b.am);
        pk \u26032 = in2.a(le2);
        this.b.z();
        if (\u26032 != null) {
            boolean bl2 = this.b.t(\u26032);
            double \u26033 = 36.0;
            if (!bl2) {
                \u26033 = 9.0;
            }
            if (this.b.h(\u26032) < \u26033) {
                if (in2.a() == in.a.a) {
                    this.b.u(\u26032);
                } else if (in2.a() == in.a.c) {
                    \u26032.a(this.b, in2.b());
                } else if (in2.a() == in.a.b) {
                    if (\u26032 instanceof uz || \u26032 instanceof pp || \u26032 instanceof wq || \u26032 == this.b) {
                        this.c("Attempting to attack an invalid entity");
                        this.d.f("Player " + this.b.e_() + " tried to attack an invalid entity");
                        return;
                    }
                    this.b.f(\u26032);
                }
            }
        }
    }

    @Override
    public void a(ig ig2) {
        fh.a(ig2, this, this.b.u());
        this.b.z();
        ig.a a2 = ig2.a();
        switch (a2) {
            case a: {
                if (this.b.i) {
                    this.b = this.d.ap().a(this.b, 0, true);
                    break;
                }
                if (this.b.u().P().t()) {
                    if (this.d.T() && this.b.e_().equals(this.d.S())) {
                        this.b.a.c("You have died. Game over, man, it's game over!");
                        this.d.aa();
                        break;
                    }
                    md md2 = new md(this.b.cd(), null, "(You just lost the game)", null, "Death in Hardcore");
                    this.d.ap().h().a(md2);
                    this.b.a.c("You have died. Game over, man, it's game over!");
                    break;
                }
                if (this.b.bn() > 0.0f) {
                    return;
                }
                this.b = this.d.ap().a(this.b, 0, false);
                break;
            }
            case b: {
                this.b.A().a(this.b);
                break;
            }
            case c: {
                this.b.b(mr.f);
            }
        }
    }

    @Override
    public void a(il il2) {
        fh.a(il2, this, this.b.u());
        this.b.p();
    }

    @Override
    public void a(ik ik22) {
        fh.a(ik22, this, this.b.u());
        this.b.z();
        if (this.b.bk.d == ik22.a() && this.b.bk.c(this.b)) {
            if (this.b.v()) {
                ArrayList<zx> arrayList = Lists.newArrayList();
                for (int i2 = 0; i2 < this.b.bk.c.size(); ++i2) {
                    arrayList.add(this.b.bk.c.get(i2).d());
                }
                this.b.a(this.b.bk, arrayList);
            } else {
                ik ik22;
                zx \u26032 = this.b.bk.a(ik22.b(), ik22.c(), ik22.f(), (wn)this.b);
                if (zx.b(ik22.e(), \u26032)) {
                    this.b.a.a(new ga(ik22.a(), ik22.d(), true));
                    this.b.g = true;
                    this.b.bk.b();
                    this.b.o();
                    this.b.g = false;
                } else {
                    this.n.a(this.b.bk.d, ik22.d());
                    this.b.a.a(new ga(ik22.a(), ik22.d(), false));
                    this.b.bk.a((wn)this.b, false);
                    ArrayList<zx> arrayList = Lists.newArrayList();
                    for (int i3 = 0; i3 < this.b.bk.c.size(); ++i3) {
                        arrayList.add(this.b.bk.c.get(i3).d());
                    }
                    this.b.a(this.b.bk, arrayList);
                }
            }
        }
    }

    @Override
    public void a(ij ij2) {
        fh.a(ij2, this, this.b.u());
        this.b.z();
        if (this.b.bk.d == ij2.a() && this.b.bk.c(this.b) && !this.b.v()) {
            this.b.bk.a((wn)this.b, ij2.b());
            this.b.bk.b();
        }
    }

    @Override
    public void a(iw iw22) {
        fh.a(iw22, this, this.b.u());
        if (this.b.c.d()) {
            iw iw22;
            Object object;
            boolean bl2 = iw22.a() < 0;
            zx \u26032 = iw22.b();
            if (\u26032 != null && \u26032.n() && \u26032.o().b("BlockEntityTag", 10) && (\u2603 = \u26032.o().m("BlockEntityTag")).c("x") && \u2603.c("y") && \u2603.c("z") && (\u2603 = this.b.o.s(\u2603 = new cj(\u2603.f("x"), \u2603.f("y"), \u2603.f("z")))) != null) {
                object = new dn();
                \u2603.b((dn)object);
                ((dn)object).o("x");
                ((dn)object).o("y");
                ((dn)object).o("z");
                \u26032.a("BlockEntityTag", (eb)object);
            }
            boolean \u26033 = iw22.a() >= 1 && iw22.a() < 36 + wm.i();
            boolean \u26034 = \u26032 == null || \u26032.b() != null;
            boolean bl3 = \u2603 = \u26032 == null || \u26032.i() >= 0 && \u26032.b <= 64 && \u26032.b > 0;
            if (\u26033 && \u26034 && \u2603) {
                if (\u26032 == null) {
                    this.b.bj.a(iw22.a(), null);
                } else {
                    this.b.bj.a(iw22.a(), \u26032);
                }
                this.b.bj.a((wn)this.b, true);
            } else if (bl2 && \u26034 && \u2603 && this.m < 200) {
                this.m += 20;
                object = this.b.a(\u26032, true);
                if (object != null) {
                    ((uz)object).j();
                }
            }
        }
    }

    @Override
    public void a(ii ii2) {
        fh.a(ii2, this, this.b.u());
        Short s2 = this.n.a(this.b.bk.d);
        if (s2 != null && ii2.b() == s2.shortValue() && this.b.bk.d == ii2.a() && !this.b.bk.c(this.b) && !this.b.v()) {
            this.b.bk.a((wn)this.b, true);
        }
    }

    @Override
    public void a(ix ix2) {
        fh.a(ix2, this, this.b.u());
        this.b.z();
        le le2 = this.d.a(this.b.am);
        cj \u26032 = ix2.a();
        if (le2.e(\u26032)) {
            akw akw2 = le2.s(\u26032);
            if (!(akw2 instanceof aln)) {
                return;
            }
            aln \u26033 = (aln)akw2;
            if (!\u26033.b() || \u26033.c() != this.b) {
                this.d.f("Player " + this.b.e_() + " just tried to change non-editable sign");
                return;
            }
            eu[] \u26034 = ix2.b();
            for (int i2 = 0; i2 < \u26034.length; ++i2) {
                \u26033.a[i2] = new fa(a.a(\u26034[i2].c()));
            }
            \u26033.p_();
            le2.h(\u26032);
        }
    }

    @Override
    public void a(io io2) {
        if (io2.a() == this.i) {
            int n2 = (int)(this.d() - this.j);
            this.b.h = (this.b.h * 3 + n2) / 4;
        }
    }

    private long d() {
        return System.nanoTime() / 1000000L;
    }

    @Override
    public void a(iq iq2) {
        fh.a(iq2, this, this.b.u());
        this.b.bA.b = iq2.b() && this.b.bA.c;
    }

    @Override
    public void a(id id2) {
        fh.a(id2, this, this.b.u());
        ArrayList<String> arrayList = Lists.newArrayList();
        for (String string : this.d.a(this.b, id2.a(), id2.b())) {
            arrayList.add(string);
        }
        this.b.a.a(new fx(arrayList.toArray(new String[arrayList.size()])));
    }

    @Override
    public void a(ih ih2) {
        fh.a(ih2, this, this.b.u());
        this.b.a(ih2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void a(im im22) {
        im im22;
        fh.a(im22, this, this.b.u());
        if ("MC|BEdit".equals(im22.a())) {
            em em2 = new em(Unpooled.wrappedBuffer((ByteBuf)im22.b()));
            try {
                zx zx2 = em2.i();
                if (zx2 == null) {
                    return;
                }
                if (!abc.b(zx2.o())) {
                    throw new IOException("Invalid book tag!");
                }
                \u2603 = this.b.bi.h();
                if (\u2603 == null) {
                    return;
                }
                if (zx2.b() != zy.bM || zx2.b() != \u2603.b()) return;
                \u2603.a("pages", zx2.o().c("pages", 8));
                return;
            }
            catch (Exception exception) {
                c.error("Couldn't handle book info", (Throwable)exception);
                return;
            }
            finally {
                em2.release();
            }
        } else if ("MC|BSign".equals(im22.a())) {
            em em3 = new em(Unpooled.wrappedBuffer((ByteBuf)im22.b()));
            try {
                zx zx3 = em3.i();
                if (zx3 == null) {
                    return;
                }
                if (!abd.b(zx3.o())) {
                    throw new IOException("Invalid book tag!");
                }
                \u2603 = this.b.bi.h();
                if (\u2603 == null) {
                    return;
                }
                if (zx3.b() != zy.bN || \u2603.b() != zy.bM) return;
                \u2603.a("author", new ea(this.b.e_()));
                \u2603.a("title", new ea(zx3.o().j("title")));
                \u2603.a("pages", zx3.o().c("pages", 8));
                \u2603.a(zy.bN);
                return;
            }
            catch (Exception exception) {
                c.error("Couldn't sign book", (Throwable)exception);
                return;
            }
            finally {
                em3.release();
            }
        } else if ("MC|TrSel".equals(im22.a())) {
            try {
                int n2 = im22.b().readInt();
                xi \u26032 = this.b.bk;
                if (!(\u26032 instanceof yb)) return;
                ((yb)\u26032).d(n2);
                return;
            }
            catch (Exception exception) {
                c.error("Couldn't select trade", (Throwable)exception);
            }
            return;
        } else if ("MC|AdvCdm".equals(im22.a())) {
            if (!this.d.al()) {
                this.b.a(new fb("advMode.notEnabled", new Object[0]));
                return;
            } else if (this.b.a(2, "") && this.b.bA.d) {
                em em4 = im22.b();
                try {
                    Object \u26034;
                    byte by = em4.readByte();
                    adc \u26033 = null;
                    if (by == 0) {
                        \u26034 = this.b.o.s(new cj(em4.readInt(), em4.readInt(), em4.readInt()));
                        if (\u26034 instanceof akz) {
                            \u26033 = ((akz)\u26034).b();
                        }
                    } else if (by == 1 && (\u26034 = this.b.o.a(em4.readInt())) instanceof vc) {
                        \u26033 = ((vc)\u26034).j();
                    }
                    \u26034 = em4.c(em4.readableBytes());
                    boolean \u26035 = em4.readBoolean();
                    if (\u26033 == null) return;
                    \u26033.a((String)\u26034);
                    \u26033.a(\u26035);
                    if (!\u26035) {
                        \u26033.b((eu)null);
                    }
                    \u26033.h();
                    this.b.a(new fb("advMode.setCommand.success", \u26034));
                    return;
                }
                catch (Exception exception) {
                    c.error("Couldn't set command block", (Throwable)exception);
                    return;
                }
                finally {
                    em4.release();
                }
            } else {
                this.b.a(new fb("advMode.notAllowed", new Object[0]));
            }
            return;
        } else if ("MC|Beacon".equals(im22.a())) {
            if (!(this.b.bk instanceof xl)) return;
            try {
                em em5 = im22.b();
                int \u26036 = em5.readInt();
                int \u26037 = em5.readInt();
                xl \u26038 = (xl)this.b.bk;
                yg \u26039 = \u26038.a(0);
                if (!\u26039.e()) return;
                \u26039.a(1);
                og \u260310 = \u26038.e();
                \u260310.b(1, \u26036);
                \u260310.b(2, \u26037);
                \u260310.p_();
                return;
            }
            catch (Exception exception) {
                c.error("Couldn't set beacon", (Throwable)exception);
            }
            return;
        } else {
            if (!"MC|ItemName".equals(im22.a()) || !(this.b.bk instanceof xk)) return;
            xk \u260311 = (xk)this.b.bk;
            if (im22.b() == null || im22.b().readableBytes() < 1) {
                \u260311.a("");
                return;
            } else {
                String string = f.a(im22.b().c(Short.MAX_VALUE));
                if (string.length() > 30) return;
                \u260311.a(string);
            }
        }
    }
}

