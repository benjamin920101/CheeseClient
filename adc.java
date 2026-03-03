/*
 * Decompiled with CFR 0.152.
 */
import io.netty.buffer.ByteBuf;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;

public abstract class adc
implements m {
    private static final SimpleDateFormat a = new SimpleDateFormat("HH:mm:ss");
    private int b;
    private boolean c = true;
    private eu d = null;
    private String e = "";
    private String f = "@";
    private final n g = new n();

    public int j() {
        return this.b;
    }

    public eu k() {
        return this.d;
    }

    public void a(dn dn2) {
        dn2.a("Command", this.e);
        dn2.a("SuccessCount", this.b);
        dn2.a("CustomName", this.f);
        dn2.a("TrackOutput", this.c);
        if (this.d != null && this.c) {
            dn2.a("LastOutput", eu.a.a(this.d));
        }
        this.g.b(dn2);
    }

    public void b(dn dn2) {
        this.e = dn2.j("Command");
        this.b = dn2.f("SuccessCount");
        if (dn2.b("CustomName", 8)) {
            this.f = dn2.j("CustomName");
        }
        if (dn2.b("TrackOutput", 1)) {
            this.c = dn2.n("TrackOutput");
        }
        if (dn2.b("LastOutput", 8) && this.c) {
            this.d = eu.a.a(dn2.j("LastOutput"));
        }
        this.g.a(dn2);
    }

    @Override
    public boolean a(int n2, String string) {
        return n2 <= 2;
    }

    public void a(String string) {
        this.e = string;
        this.b = 0;
    }

    public String l() {
        return this.e;
    }

    public void a(adm adm2) {
        if (adm2.D) {
            this.b = 0;
        }
        if ((\u2603 = MinecraftServer.N()) != null && \u2603.O() && \u2603.al()) {
            l l2 = \u2603.P();
            try {
                this.d = null;
                this.b = l2.a(this, this.e);
            }
            catch (Throwable \u26032) {
                b b2 = b.a(\u26032, "Executing command block");
                c \u26033 = b2.a("Command to be executed");
                \u26033.a("Command", new Callable<String>(){

                    public String a() throws Exception {
                        return adc.this.l();
                    }

                    @Override
                    public /* synthetic */ Object call() throws Exception {
                        return this.a();
                    }
                });
                \u26033.a("Name", new Callable<String>(){

                    public String a() throws Exception {
                        return adc.this.e_();
                    }

                    @Override
                    public /* synthetic */ Object call() throws Exception {
                        return this.a();
                    }
                });
                throw new e(b2);
            }
        } else {
            this.b = 0;
        }
    }

    @Override
    public String e_() {
        return this.f;
    }

    @Override
    public eu f_() {
        return new fa(this.e_());
    }

    public void b(String string) {
        this.f = string;
    }

    @Override
    public void a(eu eu2) {
        if (this.c && this.e() != null && !this.e().D) {
            this.d = new fa("[" + a.format(new Date()) + "] ").a(eu2);
            this.h();
        }
    }

    @Override
    public boolean u_() {
        MinecraftServer minecraftServer = MinecraftServer.N();
        return minecraftServer == null || !minecraftServer.O() || minecraftServer.d[0].Q().b("commandBlockOutput");
    }

    @Override
    public void a(n.a a2, int n2) {
        this.g.a(this, a2, n2);
    }

    public abstract void h();

    public abstract int i();

    public abstract void a(ByteBuf var1);

    public void b(eu eu2) {
        this.d = eu2;
    }

    public void a(boolean bl2) {
        this.c = bl2;
    }

    public boolean m() {
        return this.c;
    }

    public boolean a(wn wn2) {
        if (!wn2.bA.d) {
            return false;
        }
        if (wn2.e().D) {
            wn2.a(this);
        }
        return true;
    }

    public n n() {
        return this.g;
    }
}

