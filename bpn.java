/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;
import java.net.SocketAddress;
import net.minecraft.server.MinecraftServer;

public class bpn
extends lx {
    private dn f;

    public bpn(bpo bpo2) {
        super(bpo2);
        this.a(10);
    }

    @Override
    protected void b(lf lf2) {
        if (lf2.e_().equals(this.b().S())) {
            this.f = new dn();
            lf2.e(this.f);
        }
        super.b(lf2);
    }

    @Override
    public String a(SocketAddress socketAddress, GameProfile gameProfile) {
        if (gameProfile.getName().equalsIgnoreCase(this.b().S()) && this.a(gameProfile.getName()) != null) {
            return "That name is already taken.";
        }
        return super.a(socketAddress, gameProfile);
    }

    public bpo b() {
        return (bpo)super.c();
    }

    @Override
    public dn t() {
        return this.f;
    }

    @Override
    public /* synthetic */ MinecraftServer c() {
        return this.b();
    }
}

