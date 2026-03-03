/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.MinecraftServer;

public class alo
extends akw {
    private int a;
    private int f;
    private GameProfile g = null;

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("SkullType", (byte)(this.a & 0xFF));
        dn2.a("Rot", (byte)(this.f & 0xFF));
        if (this.g != null) {
            \u2603 = new dn();
            dy.a(\u2603, this.g);
            dn2.a("Owner", \u2603);
        }
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.a = dn2.d("SkullType");
        this.f = dn2.d("Rot");
        if (this.a == 3) {
            if (dn2.b("Owner", 10)) {
                this.g = dy.a(dn2.m("Owner"));
            } else if (dn2.b("ExtraType", 8) && !nx.b(\u2603 = dn2.j("ExtraType"))) {
                this.g = new GameProfile(null, \u2603);
                this.e();
            }
        }
    }

    public GameProfile b() {
        return this.g;
    }

    @Override
    public ff y_() {
        dn dn2 = new dn();
        this.b(dn2);
        return new ft(this.c, 4, dn2);
    }

    public void a(int n2) {
        this.a = n2;
        this.g = null;
    }

    public void a(GameProfile gameProfile) {
        this.a = 3;
        this.g = gameProfile;
        this.e();
    }

    private void e() {
        this.g = alo.b(this.g);
        this.p_();
    }

    public static GameProfile b(GameProfile gameProfile) {
        GameProfile gameProfile2;
        if (gameProfile == null || nx.b(gameProfile.getName())) {
            return gameProfile;
        }
        if (gameProfile.isComplete() && gameProfile.getProperties().containsKey("textures")) {
            return gameProfile;
        }
        if (MinecraftServer.N() == null) {
            return gameProfile;
        }
        gameProfile2 = MinecraftServer.N().aF().a(gameProfile.getName());
        if (gameProfile2 == null) {
            return gameProfile;
        }
        Property property = Iterables.getFirst(gameProfile2.getProperties().get("textures"), null);
        if (property == null) {
            gameProfile2 = MinecraftServer.N().aD().fillProfileProperties(gameProfile2, true);
        }
        return gameProfile2;
    }

    public int c() {
        return this.a;
    }

    public int d() {
        return this.f;
    }

    public void b(int n2) {
        this.f = n2;
    }
}

