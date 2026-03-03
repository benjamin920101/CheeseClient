/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.client.ClientBrandRetriever;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class avv
extends avp {
    private final ave a;
    private final avn f;

    public avv(ave ave2) {
        this.a = ave2;
        this.f = ave2.k;
    }

    public void a(avr avr2) {
        this.a.A.a("debug");
        bfl.E();
        this.a();
        this.b(avr2);
        bfl.F();
        if (this.a.t.aD) {
            this.e();
        }
        this.a.A.b();
    }

    private boolean d() {
        return this.a.h.cq() || this.a.t.w;
    }

    protected void a() {
        List<String> list = this.b();
        for (int i2 = 0; i2 < list.size(); ++i2) {
            String string = list.get(i2);
            if (Strings.isNullOrEmpty(string)) continue;
            int \u26032 = this.f.a;
            int \u26033 = this.f.a(string);
            int \u26034 = 2;
            int \u26035 = 2 + \u26032 * i2;
            avv.a(1, \u26035 - 1, 2 + \u26033 + 1, \u26035 + \u26032 - 1, -1873784752);
            this.f.a(string, 2, \u26035, 0xE0E0E0);
        }
    }

    protected void b(avr avr2) {
        List<String> list = this.c();
        for (int i2 = 0; i2 < list.size(); ++i2) {
            String string = list.get(i2);
            if (Strings.isNullOrEmpty(string)) continue;
            int \u26032 = this.f.a;
            int \u26033 = this.f.a(string);
            int \u26034 = avr2.a() - 2 - \u26033;
            int \u26035 = 2 + \u26032 * i2;
            avv.a(\u26034 - 1, \u26035 - 1, \u26034 + \u26033 + 1, \u26035 + \u26032 - 1, -1873784752);
            this.f.a(string, \u26034, \u26035, 0xE0E0E0);
        }
    }

    protected List<String> b() {
        cj cj2 = new cj(this.a.ac().s, this.a.ac().aR().b, this.a.ac().u);
        if (this.d()) {
            return Lists.newArrayList("Minecraft 1.8.8 (" + this.a.c() + "/" + ClientBrandRetriever.getClientModName() + ")", this.a.C, this.a.g.f(), this.a.g.g(), "P: " + this.a.j.b() + ". T: " + this.a.f.z(), this.a.f.A(), "", String.format("Chunk-relative: %d %d %d", cj2.n() & 0xF, cj2.o() & 0xF, cj2.p() & 0xF));
        }
        pk \u26032 = this.a.ac();
        cq \u26033 = \u26032.aP();
        String \u26034 = "Invalid";
        switch (\u26033) {
            case c: {
                \u26034 = "Towards negative Z";
                break;
            }
            case d: {
                \u26034 = "Towards positive Z";
                break;
            }
            case e: {
                \u26034 = "Towards negative X";
                break;
            }
            case f: {
                \u26034 = "Towards positive X";
            }
        }
        ArrayList<String> \u26035 = Lists.newArrayList("Minecraft 1.8.8 (" + this.a.c() + "/" + ClientBrandRetriever.getClientModName() + ")", this.a.C, this.a.g.f(), this.a.g.g(), "P: " + this.a.j.b() + ". T: " + this.a.f.z(), this.a.f.A(), "", String.format("XYZ: %.3f / %.5f / %.3f", this.a.ac().s, this.a.ac().aR().b, this.a.ac().u), String.format("Block: %d %d %d", cj2.n(), cj2.o(), cj2.p()), String.format("Chunk: %d %d %d in %d %d %d", cj2.n() & 0xF, cj2.o() & 0xF, cj2.p() & 0xF, cj2.n() >> 4, cj2.o() >> 4, cj2.p() >> 4), String.format("Facing: %s (%s) (%.1f / %.1f)", \u26033, \u26034, Float.valueOf(ns.g(\u26032.y)), Float.valueOf(ns.g(\u26032.z))));
        if (this.a.f != null && this.a.f.e(cj2)) {
            Object object = this.a.f.f(cj2);
            \u26035.add("Biome: " + ((amy)object).a((cj)cj2, (aec)this.a.f.v()).ah);
            \u26035.add("Light: " + ((amy)object).a(cj2, 0) + " (" + ((amy)object).a(ads.a, cj2) + " sky, " + ((amy)object).a(ads.b, cj2) + " block)");
            ok \u26036 = this.a.f.E(cj2);
            if (this.a.E() && this.a.G() != null && (\u2603 = this.a.G().ap().a(this.a.h.aK())) != null) {
                \u26036 = \u2603.o.E(new cj(\u2603));
            }
            \u26035.add(String.format("Local Difficulty: %.2f (Day %d)", Float.valueOf(\u26036.b()), this.a.f.L() / 24000L));
        }
        if (this.a.o != null && this.a.o.a()) {
            \u26035.add("Shader: " + this.a.o.f().b());
        }
        if (this.a.s != null && this.a.s.a == auh.a.b && this.a.s.a() != null) {
            object = this.a.s.a();
            \u26035.add(String.format("Looking at: %d %d %d", ((df)object).n(), ((df)object).o(), ((df)object).p()));
        }
        return \u26035;
    }

    protected List<String> c() {
        long l2 = Runtime.getRuntime().maxMemory();
        \u2603 = Runtime.getRuntime().totalMemory();
        \u2603 = Runtime.getRuntime().freeMemory();
        \u2603 = \u2603 - \u2603;
        ArrayList<String> \u26032 = Lists.newArrayList(String.format("Java: %s %dbit", System.getProperty("java.version"), this.a.U() ? 64 : 32), String.format("Mem: % 2d%% %03d/%03dMB", \u2603 * 100L / l2, avv.a(\u2603), avv.a(l2)), String.format("Allocated: % 2d%% %03dMB", \u2603 * 100L / l2, avv.a(\u2603)), "", String.format("CPU: %s", bqs.j()), "", String.format("Display: %dx%d (%s)", Display.getWidth(), Display.getHeight(), GL11.glGetString(7936)), GL11.glGetString(7937), GL11.glGetString(7938));
        if (this.d()) {
            return \u26032;
        }
        if (this.a.s != null && this.a.s.a == auh.a.b && this.a.s.a() != null) {
            cj cj2 = this.a.s.a();
            alz \u26033 = this.a.f.p(cj2);
            if (this.a.f.G() != adr.g) {
                \u26033 = \u26033.c().a(\u26033, (adq)this.a.f, cj2);
            }
            \u26032.add("");
            \u26032.add(String.valueOf(afh.c.c(\u26033.c())));
            for (Map.Entry entry : \u26033.b().entrySet()) {
                String string = ((Comparable)entry.getValue()).toString();
                if (entry.getValue() == Boolean.TRUE) {
                    string = (Object)((Object)a.k) + string;
                } else if (entry.getValue() == Boolean.FALSE) {
                    string = (Object)((Object)a.m) + string;
                }
                \u26032.add(((amo)entry.getKey()).a() + ": " + string);
            }
        }
        return \u26032;
    }

    private void e() {
        bfl.i();
        nh nh2 = this.a.aj();
        int \u26032 = nh2.a();
        int \u26033 = nh2.b();
        long[] \u26034 = nh2.c();
        avr \u26035 = new avr(this.a);
        int \u26036 = \u26032;
        int \u26037 = 0;
        avv.a(0, \u26035.b() - 60, 240, \u26035.b(), -1873784752);
        while (\u26036 != \u26033) {
            int n2 = nh2.a(\u26034[\u26036], 30);
            \u2603 = this.c(ns.a(n2, 0, 60), 0, 30, 60);
            this.b(\u26037, \u26035.b(), \u26035.b() - n2, \u2603);
            ++\u26037;
            \u26036 = nh2.b(\u26036 + 1);
        }
        avv.a(1, \u26035.b() - 30 + 1, 14, \u26035.b() - 30 + 10, -1873784752);
        this.f.a("60", 2, \u26035.b() - 30 + 2, 0xE0E0E0);
        this.a(0, 239, \u26035.b() - 30, -1);
        avv.a(1, \u26035.b() - 60 + 1, 14, \u26035.b() - 60 + 10, -1873784752);
        this.f.a("30", 2, \u26035.b() - 60 + 2, 0xE0E0E0);
        this.a(0, 239, \u26035.b() - 60, -1);
        this.a(0, 239, \u26035.b() - 1, -1);
        this.b(0, \u26035.b() - 60, \u26035.b(), -1);
        this.b(239, \u26035.b() - 60, \u26035.b(), -1);
        if (this.a.t.g <= 120) {
            this.a(0, 239, \u26035.b() - 60 + this.a.t.g / 2, -16711681);
        }
        bfl.j();
    }

    private int c(int n2, int n3, int n4, int n5) {
        if (n2 < n4) {
            return this.a(-16711936, -256, (float)n2 / (float)n4);
        }
        return this.a(-256, -65536, (float)(n2 - n4) / (float)(n5 - n4));
    }

    private int a(int n2, int n3, float f2) {
        int n4 = n2 >> 24 & 0xFF;
        \u2603 = n2 >> 16 & 0xFF;
        \u2603 = n2 >> 8 & 0xFF;
        \u2603 = n2 & 0xFF;
        \u2603 = n3 >> 24 & 0xFF;
        \u2603 = n3 >> 16 & 0xFF;
        \u2603 = n3 >> 8 & 0xFF;
        \u2603 = n3 & 0xFF;
        \u2603 = ns.a((int)((float)n4 + (float)(\u2603 - n4) * f2), 0, 255);
        \u2603 = ns.a((int)((float)\u2603 + (float)(\u2603 - \u2603) * f2), 0, 255);
        \u2603 = ns.a((int)((float)\u2603 + (float)(\u2603 - \u2603) * f2), 0, 255);
        \u2603 = ns.a((int)((float)\u2603 + (float)(\u2603 - \u2603) * f2), 0, 255);
        return \u2603 << 24 | \u2603 << 16 | \u2603 << 8 | \u2603;
    }

    private static long a(long l2) {
        return l2 / 1024L / 1024L;
    }
}

