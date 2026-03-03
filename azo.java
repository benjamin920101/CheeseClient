/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Sys;

public class azo
extends axu {
    private static final Logger a = LogManager.getLogger();
    private final axu f;
    private List<azp> g;
    private List<azp> h;
    private azt i;
    private azv r;
    private boolean s = false;

    public azo(axu axu2) {
        this.f = axu2;
    }

    @Override
    public void b() {
        this.n.add(new awe(2, this.l / 2 - 154, this.m - 48, bnq.a("resourcePack.openFolder", new Object[0])));
        this.n.add(new awe(1, this.l / 2 + 4, this.m - 48, bnq.a("gui.done", new Object[0])));
        if (!this.s) {
            this.g = Lists.newArrayList();
            this.h = Lists.newArrayList();
            bnm bnm2 = this.j.R();
            bnm2.a();
            ArrayList<bnm.a> \u26032 = Lists.newArrayList(bnm2.b());
            \u26032.removeAll(bnm2.c());
            for (bnm.a \u26033 : \u26032) {
                this.g.add(new azr(this, \u26033));
            }
            for (bnm.a \u26033 : Lists.reverse(bnm2.c())) {
                this.h.add(new azr(this, \u26033));
            }
            this.h.add(new azq(this));
        }
        this.i = new azt(this.j, 200, this.m, this.g);
        this.i.i(this.l / 2 - 4 - 200);
        this.i.d(7, 8);
        this.r = new azv(this.j, 200, this.m, this.h);
        this.r.i(this.l / 2 + 4);
        this.r.d(7, 8);
    }

    @Override
    public void k() {
        super.k();
        this.r.p();
        this.i.p();
    }

    public boolean a(azp azp2) {
        return this.h.contains(azp2);
    }

    public List<azp> b(azp azp2) {
        if (this.a(azp2)) {
            return this.h;
        }
        return this.g;
    }

    public List<azp> a() {
        return this.g;
    }

    public List<azp> f() {
        return this.h;
    }

    @Override
    protected void a(avs avs22) {
        if (!avs22.l) {
            return;
        }
        if (avs22.k == 2) {
            boolean bl2;
            File file = this.j.R().d();
            String \u26032 = file.getAbsolutePath();
            if (g.a() == g.a.d) {
                try {
                    a.info(\u26032);
                    Runtime.getRuntime().exec(new String[]{"/usr/bin/open", \u26032});
                    return;
                }
                catch (IOException iOException) {
                    a.error("Couldn't open file", (Throwable)iOException);
                }
            } else if (g.a() == g.a.c) {
                String string = String.format("cmd.exe /C start \"Open file\" \"%s\"", \u26032);
                try {
                    Runtime.getRuntime().exec(string);
                    return;
                }
                catch (IOException \u26033) {
                    a.error("Couldn't open file", (Throwable)\u26033);
                }
            }
            boolean bl3 = false;
            try {
                Class<?> clazz = Class.forName("java.awt.Desktop");
                Object \u26034 = clazz.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
                clazz.getMethod("browse", URI.class).invoke(\u26034, file.toURI());
            }
            catch (Throwable throwable) {
                a.error("Couldn't open link", throwable);
                bl2 = true;
            }
            if (bl2) {
                a.info("Opening via system class!");
                Sys.openURL("file://" + \u26032);
            }
        } else if (avs22.k == 1) {
            if (this.s) {
                ArrayList<bnm.a> arrayList = Lists.newArrayList();
                for (azp azp2 : this.h) {
                    if (!(azp2 instanceof azr)) continue;
                    arrayList.add(((azr)azp2).j());
                }
                Collections.reverse(arrayList);
                this.j.R().a(arrayList);
                this.j.t.k.clear();
                this.j.t.l.clear();
                for (bnm.a a2 : arrayList) {
                    this.j.t.k.add(a2.d());
                    if (a2.f() == 1) continue;
                    this.j.t.l.add(a2.d());
                }
                this.j.t.b();
                this.j.e();
            }
            this.j.a(this.f);
        }
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        super.a(n2, n3, n4);
        this.i.b(n2, n3, n4);
        this.r.b(n2, n3, n4);
    }

    @Override
    protected void b(int n2, int n3, int n4) {
        super.b(n2, n3, n4);
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c(0);
        this.i.a(n2, n3, f2);
        this.r.a(n2, n3, f2);
        this.a(this.q, bnq.a("resourcePack.title", new Object[0]), this.l / 2, 16, 0xFFFFFF);
        this.a(this.q, bnq.a("resourcePack.folderInfo", new Object[0]), this.l / 2 - 77, this.m - 26, 0x808080);
        super.a(n2, n3, f2);
    }

    public void g() {
        this.s = true;
    }
}

