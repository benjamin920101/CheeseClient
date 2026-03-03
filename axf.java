/*
 * Decompiled with CFR 0.152.
 */
import java.net.URI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class axf
extends axu {
    private static final Logger a = LogManager.getLogger();
    private static final jy f = new jy("textures/gui/demo_background.png");

    @Override
    public void b() {
        this.n.clear();
        int n2 = -16;
        this.n.add(new avs(1, this.l / 2 - 116, this.m / 2 + 62 + n2, 114, 20, bnq.a("demo.help.buy", new Object[0])));
        this.n.add(new avs(2, this.l / 2 + 2, this.m / 2 + 62 + n2, 114, 20, bnq.a("demo.help.later", new Object[0])));
    }

    @Override
    protected void a(avs avs2) {
        switch (avs2.k) {
            case 2: {
                this.j.a((axu)null);
                this.j.n();
                break;
            }
            case 1: {
                avs2.l = false;
                try {
                    Class<?> clazz = Class.forName("java.awt.Desktop");
                    Object \u26032 = clazz.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
                    clazz.getMethod("browse", URI.class).invoke(\u26032, new URI("http://www.minecraft.net/store?source=demo"));
                    break;
                }
                catch (Throwable throwable) {
                    a.error("Couldn't open link", throwable);
                }
            }
        }
    }

    @Override
    public void e() {
        super.e();
    }

    @Override
    public void c() {
        super.c();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(f);
        int n2 = (this.l - 248) / 2;
        \u2603 = (this.m - 166) / 2;
        this.b(n2, \u2603, 0, 0, 248, 166);
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        int n4 = (this.l - 248) / 2 + 10;
        \u2603 = (this.m - 166) / 2 + 8;
        this.q.a(bnq.a("demo.help.title", new Object[0]), n4, \u2603, 0x1F1F1F);
        avh \u26032 = this.j.t;
        this.q.a(bnq.a("demo.help.movementShort", avh.c(\u26032.X.i()), avh.c(\u26032.Y.i()), avh.c(\u26032.Z.i()), avh.c(\u26032.aa.i())), n4, \u2603 += 12, 0x4F4F4F);
        this.q.a(bnq.a("demo.help.movementMouse", new Object[0]), n4, \u2603 + 12, 0x4F4F4F);
        this.q.a(bnq.a("demo.help.jump", avh.c(\u26032.ab.i())), n4, \u2603 + 24, 0x4F4F4F);
        this.q.a(bnq.a("demo.help.inventory", avh.c(\u26032.ae.i())), n4, \u2603 + 36, 0x4F4F4F);
        this.q.a(bnq.a("demo.help.fullWrapped", new Object[0]), n4, \u2603 + 68, 218, 0x1F1F1F);
        super.a(n2, n3, f2);
    }
}

