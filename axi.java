/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import java.net.IDN;
import org.lwjgl.input.Keyboard;

public class axi
extends axu {
    private final axu a;
    private final bde f;
    private avw g;
    private avw h;
    private avs i;
    private Predicate<String> r = new Predicate<String>(){

        public boolean a(String string) {
            if (string.length() == 0) {
                return true;
            }
            String[] stringArray = string.split(":");
            if (stringArray.length == 0) {
                return true;
            }
            try {
                String string2 = IDN.toASCII(stringArray[0]);
                return true;
            }
            catch (IllegalArgumentException illegalArgumentException) {
                return false;
            }
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((String)object);
        }
    };

    public axi(axu axu2, bde bde2) {
        this.a = axu2;
        this.f = bde2;
    }

    @Override
    public void e() {
        this.h.a();
        this.g.a();
    }

    @Override
    public void b() {
        Keyboard.enableRepeatEvents(true);
        this.n.clear();
        this.n.add(new avs(0, this.l / 2 - 100, this.m / 4 + 96 + 18, bnq.a("addServer.add", new Object[0])));
        this.n.add(new avs(1, this.l / 2 - 100, this.m / 4 + 120 + 18, bnq.a("gui.cancel", new Object[0])));
        this.i = new avs(2, this.l / 2 - 100, this.m / 4 + 72, bnq.a("addServer.resourcePack", new Object[0]) + ": " + this.f.b().a().d());
        this.n.add(this.i);
        this.h = new avw(0, this.q, this.l / 2 - 100, 66, 200, 20);
        this.h.b(true);
        this.h.a(this.f.a);
        this.g = new avw(1, this.q, this.l / 2 - 100, 106, 200, 20);
        this.g.f(128);
        this.g.a(this.f.b);
        this.g.a(this.r);
        ((avs)this.n.get((int)0)).l = this.g.b().length() > 0 && this.g.b().split(":").length > 0 && this.h.b().length() > 0;
    }

    @Override
    public void m() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void a(avs avs2) {
        if (!avs2.l) {
            return;
        }
        if (avs2.k == 2) {
            this.f.a(bde.a.values()[(this.f.b().ordinal() + 1) % bde.a.values().length]);
            this.i.j = bnq.a("addServer.resourcePack", new Object[0]) + ": " + this.f.b().a().d();
        } else if (avs2.k == 1) {
            this.a.a(false, 0);
        } else if (avs2.k == 0) {
            this.f.a = this.h.b();
            this.f.b = this.g.b();
            this.a.a(true, 0);
        }
    }

    @Override
    protected void a(char c2, int n2) {
        this.h.a(c2, n2);
        this.g.a(c2, n2);
        if (n2 == 15) {
            this.h.b(!this.h.m());
            this.g.b(!this.g.m());
        }
        if (n2 == 28 || n2 == 156) {
            this.a((avs)this.n.get(0));
        }
        ((avs)this.n.get((int)0)).l = this.g.b().length() > 0 && this.g.b().split(":").length > 0 && this.h.b().length() > 0;
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        super.a(n2, n3, n4);
        this.g.a(n2, n3, n4);
        this.h.a(n2, n3, n4);
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c();
        this.a(this.q, bnq.a("addServer.title", new Object[0]), this.l / 2, 17, 0xFFFFFF);
        this.c(this.q, bnq.a("addServer.enterName", new Object[0]), this.l / 2 - 100, 53, 0xA0A0A0);
        this.c(this.q, bnq.a("addServer.enterIp", new Object[0]), this.l / 2 - 100, 94, 0xA0A0A0);
        this.h.g();
        this.g.g();
        super.a(n2, n3, f2);
    }
}

