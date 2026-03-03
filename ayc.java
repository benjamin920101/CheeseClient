/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ayc
extends axu {
    private static final Logger a = LogManager.getLogger();
    private static final jy f = new jy("textures/gui/title/minecraft.png");
    private static final jy g = new jy("textures/misc/vignette.png");
    private int h;
    private List<String> i;
    private int r;
    private float s = 0.5f;

    @Override
    public void e() {
        bpv bpv2 = this.j.r();
        bpz \u26032 = this.j.W();
        if (this.h == 0) {
            bpv2.a();
            bpv2.a(bpv.a.d);
            \u26032.e();
        }
        \u26032.c();
        ++this.h;
        float \u26033 = (float)(this.r + this.m + this.m + 24) / this.s;
        if ((float)this.h > \u26033) {
            this.a();
        }
    }

    @Override
    protected void a(char c2, int n2) {
        if (n2 == 1) {
            this.a();
        }
    }

    private void a() {
        this.j.h.a.a(new ig(ig.a.a));
        this.j.a((axu)null);
    }

    @Override
    public boolean d() {
        return true;
    }

    @Override
    public void b() {
        if (this.i != null) {
            return;
        }
        this.i = Lists.newArrayList();
        try {
            int n2;
            String \u26038 = "";
            \u2603 = "" + (Object)((Object)a.p) + (Object)((Object)a.q) + (Object)((Object)a.k) + (Object)((Object)a.l);
            int \u26032 = 274;
            InputStream \u26033 = this.j.Q().a(new jy("texts/end.txt")).b();
            BufferedReader \u26034 = new BufferedReader(new InputStreamReader(\u26033, Charsets.UTF_8));
            Random \u26035 = new Random(8124371L);
            while ((\u26038 = \u26034.readLine()) != null) {
                \u26038 = \u26038.replaceAll("PLAYERNAME", this.j.L().c());
                while (\u26038.contains(\u2603)) {
                    n2 = \u26038.indexOf(\u2603);
                    String \u26036 = \u26038.substring(0, n2);
                    String \u26037 = \u26038.substring(n2 + \u2603.length());
                    \u26038 = \u26036 + (Object)((Object)a.p) + (Object)((Object)a.q) + "XXXXXXXX".substring(0, \u26035.nextInt(4) + 3) + \u26037;
                }
                this.i.addAll(this.j.k.c(\u26038, \u26032));
                this.i.add("");
            }
            \u26033.close();
            for (n2 = 0; n2 < 8; ++n2) {
                this.i.add("");
            }
            \u26033 = this.j.Q().a(new jy("texts/credits.txt")).b();
            \u26034 = new BufferedReader(new InputStreamReader(\u26033, Charsets.UTF_8));
            while ((\u26038 = \u26034.readLine()) != null) {
                \u26038 = \u26038.replaceAll("PLAYERNAME", this.j.L().c());
                \u26038 = \u26038.replaceAll("\t", "    ");
                this.i.addAll(this.j.k.c(\u26038, \u26032));
                this.i.add("");
            }
            \u26033.close();
            this.r = this.i.size() * 12;
        }
        catch (Exception exception) {
            a.error("Couldn't load credits", (Throwable)exception);
        }
    }

    private void b(int n2, int n3, float f2) {
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        this.j.P().a(avp.b);
        \u26032.a(7, bms.i);
        int \u26033 = this.l;
        float \u26034 = 0.0f - ((float)this.h + f2) * 0.5f * this.s;
        float \u26035 = (float)this.m - ((float)this.h + f2) * 0.5f * this.s;
        float \u26036 = 0.015625f;
        float \u26037 = ((float)this.h + f2 - 0.0f) * 0.02f;
        float \u26038 = (float)(this.r + this.m + this.m + 24) / this.s;
        float \u26039 = (\u26038 - 20.0f - ((float)this.h + f2)) * 0.005f;
        if (\u26039 < \u26037) {
            \u26037 = \u26039;
        }
        if (\u26037 > 1.0f) {
            \u26037 = 1.0f;
        }
        \u26037 *= \u26037;
        \u26037 = \u26037 * 96.0f / 255.0f;
        \u26032.b(0.0, (double)this.m, (double)this.e).a(0.0, \u26034 * \u26036).a(\u26037, \u26037, \u26037, 1.0f).d();
        \u26032.b((double)\u26033, (double)this.m, (double)this.e).a((float)\u26033 * \u26036, \u26034 * \u26036).a(\u26037, \u26037, \u26037, 1.0f).d();
        \u26032.b((double)\u26033, 0.0, (double)this.e).a((float)\u26033 * \u26036, \u26035 * \u26036).a(\u26037, \u26037, \u26037, 1.0f).d();
        \u26032.b(0.0, 0.0, (double)this.e).a(0.0, \u26035 * \u26036).a(\u26037, \u26037, \u26037, 1.0f).d();
        bfx2.b();
    }

    @Override
    public void a(int n2, int n3, float f2) {
        int n4;
        this.b(n2, n3, f2);
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        int \u26033 = 274;
        int \u26034 = this.l / 2 - \u26033 / 2;
        int \u26035 = this.m + 50;
        float \u26036 = -((float)this.h + f2) * this.s;
        bfl.E();
        bfl.b(0.0f, \u26036, 0.0f);
        this.j.P().a(f);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.b(\u26034, \u26035, 0, 0, 155, 44);
        this.b(\u26034 + 155, \u26035, 0, 45, 155, 44);
        int \u26037 = \u26035 + 200;
        for (n4 = 0; n4 < this.i.size(); ++n4) {
            if (n4 == this.i.size() - 1 && (\u2603 = (float)\u26037 + \u26036 - (float)(this.m / 2 - 6)) < 0.0f) {
                bfl.b(0.0f, -\u2603, 0.0f);
            }
            if ((float)\u26037 + \u26036 + 12.0f + 8.0f > 0.0f && (float)\u26037 + \u26036 < (float)this.m) {
                String string = this.i.get(n4);
                if (string.startsWith("[C]")) {
                    this.q.a(string.substring(3), (float)(\u26034 + (\u26033 - this.q.a(string.substring(3))) / 2), (float)\u26037, 0xFFFFFF);
                } else {
                    this.q.b.setSeed((long)n4 * 4238972211L + (long)(this.h / 4));
                    this.q.a(string, (float)\u26034, (float)\u26037, 0xFFFFFF);
                }
            }
            \u26037 += 12;
        }
        bfl.F();
        this.j.P().a(g);
        bfl.l();
        bfl.b(0, 769);
        n4 = this.l;
        \u2603 = this.m;
        \u26032.a(7, bms.i);
        \u26032.b(0.0, (double)\u2603, (double)this.e).a(0.0, 1.0).a(1.0f, 1.0f, 1.0f, 1.0f).d();
        \u26032.b((double)n4, (double)\u2603, (double)this.e).a(1.0, 1.0).a(1.0f, 1.0f, 1.0f, 1.0f).d();
        \u26032.b((double)n4, 0.0, (double)this.e).a(1.0, 0.0).a(1.0f, 1.0f, 1.0f, 1.0f).d();
        \u26032.b(0.0, 0.0, (double)this.e).a(0.0, 0.0).a(1.0f, 1.0f, 1.0f, 1.0f).d();
        bfx2.b();
        bfl.k();
        super.a(n2, n3, f2);
    }
}

