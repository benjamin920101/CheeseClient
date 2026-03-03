/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.server.MinecraftServer;

public class ln
implements jd {
    private final MinecraftServer a;
    private final ek b;

    public ln(MinecraftServer minecraftServer, ek ek2) {
        this.a = minecraftServer;
        this.b = ek2;
    }

    @Override
    public void a(jc jc22) {
        switch (jc22.a()) {
            case d: {
                jc jc22;
                this.b.a(el.d);
                if (jc22.b() > 47) {
                    fa fa2 = new fa("Outdated server! I'm still on 1.8.8");
                    this.b.a(new jj(fa2));
                    this.b.a(fa2);
                    break;
                }
                if (jc22.b() < 47) {
                    fa fa3 = new fa("Outdated client! Please use 1.8.8");
                    this.b.a(new jj(fa3));
                    this.b.a(fa3);
                    break;
                }
                this.b.a(new lo(this.a, this.b));
                break;
            }
            case c: {
                this.b.a(el.c);
                this.b.a(new lp(this.a, this.b));
                break;
            }
            default: {
                jc jc22;
                throw new UnsupportedOperationException("Invalid intention " + (Object)((Object)jc22.a()));
            }
        }
    }

    @Override
    public void a(eu eu2) {
    }
}

