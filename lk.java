/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class lk
implements jd {
    private final MinecraftServer a;
    private final ek b;

    public lk(MinecraftServer minecraftServer, ek ek2) {
        this.a = minecraftServer;
        this.b = ek2;
    }

    @Override
    public void a(jc jc2) {
        this.b.a(jc2.a());
        this.b.a(new lo(this.a, this.b));
    }

    @Override
    public void a(eu eu2) {
    }
}

