/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class mj
implements m {
    private static final mj a = new mj();
    private StringBuffer b = new StringBuffer();

    @Override
    public String e_() {
        return "Rcon";
    }

    @Override
    public eu f_() {
        return new fa(this.e_());
    }

    @Override
    public void a(eu eu2) {
        this.b.append(eu2.c());
    }

    @Override
    public boolean a(int n2, String string) {
        return true;
    }

    @Override
    public cj c() {
        return new cj(0, 0, 0);
    }

    @Override
    public aui d() {
        return new aui(0.0, 0.0, 0.0);
    }

    @Override
    public adm e() {
        return MinecraftServer.N().e();
    }

    @Override
    public pk f() {
        return null;
    }

    @Override
    public boolean u_() {
        return true;
    }

    @Override
    public void a(n.a a2, int n2) {
    }
}

