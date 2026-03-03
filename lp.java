/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class lp
implements jt {
    private static final eu a = new fa("Status request has been handled.");
    private final MinecraftServer b;
    private final ek c;
    private boolean d;

    public lp(MinecraftServer minecraftServer, ek ek2) {
        this.b = minecraftServer;
        this.c = ek2;
    }

    @Override
    public void a(eu eu2) {
    }

    @Override
    public void a(jv jv2) {
        if (this.d) {
            this.c.a(a);
            return;
        }
        this.d = true;
        this.c.a(new jr(this.b.aG()));
    }

    @Override
    public void a(ju ju2) {
        this.c.a(new jq(ju2.a()));
        this.c.a(a);
    }
}

