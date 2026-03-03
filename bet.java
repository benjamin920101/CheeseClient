/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;

public abstract class bet
extends wn {
    private bdc a;

    public bet(adm adm2, GameProfile gameProfile) {
        super(adm2, gameProfile);
    }

    @Override
    public boolean v() {
        bdc bdc2 = ave.A().u().a(this.cd().getId());
        return bdc2 != null && bdc2.b() == adp.a.e;
    }

    public boolean a() {
        return this.b() != null;
    }

    protected bdc b() {
        if (this.a == null) {
            this.a = ave.A().u().a(this.aK());
        }
        return this.a;
    }

    public boolean g() {
        bdc bdc2 = this.b();
        return bdc2 != null && bdc2.e();
    }

    public jy i() {
        bdc bdc2 = this.b();
        return bdc2 == null ? bmz.a(this.aK()) : bdc2.g();
    }

    public jy k() {
        bdc bdc2 = this.b();
        return bdc2 == null ? null : bdc2.h();
    }

    public static bma a(jy jy2, String string) {
        bmj bmj2 = ave.A().P();
        bmk \u26032 = bmj2.b(jy2);
        if (\u26032 == null) {
            \u26032 = new bma(null, String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", nx.a(string)), bmz.a(bet.b(string)), new bfs());
            bmj2.a(jy2, \u26032);
        }
        return (bma)\u26032;
    }

    public static jy c(String string) {
        return new jy("skins/" + nx.a(string));
    }

    public String l() {
        bdc bdc2 = this.b();
        return bdc2 == null ? bmz.b(this.aK()) : bdc2.f();
    }

    public float o() {
        float f2 = 1.0f;
        if (this.bA.b) {
            f2 *= 1.1f;
        }
        qc \u26032 = this.a(vy.d);
        f2 = (float)((double)f2 * ((\u26032.e() / (double)this.bA.b() + 1.0) / 2.0));
        if (this.bA.b() == 0.0f || Float.isNaN(f2) || Float.isInfinite(f2)) {
            f2 = 1.0f;
        }
        if (this.bS() && this.bQ().b() == zy.f) {
            int n2 = this.bT();
            float \u26033 = (float)n2 / 20.0f;
            \u26033 = \u26033 > 1.0f ? 1.0f : (\u26033 *= \u26033);
            f2 *= 1.0f - \u26033 * 0.15f;
        }
        return f2;
    }
}

