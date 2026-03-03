/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;

public class bad
implements bah {
    private final GameProfile a;
    private final jy b;

    public bad(GameProfile gameProfile) {
        this.a = gameProfile;
        this.b = bet.c(gameProfile.getName());
        bet.a(this.b, gameProfile.getName());
    }

    @Override
    public void a(baf baf2) {
        ave.A().u().a(new iz(this.a.getId()));
    }

    @Override
    public eu A_() {
        return new fa(this.a.getName());
    }

    @Override
    public void a(float f2, int n2) {
        ave.A().P().a(this.b);
        bfl.c(1.0f, 1.0f, 1.0f, (float)n2 / 255.0f);
        avp.a(2, 2, 8.0f, 8.0f, 8, 8, 12, 12, 64.0f, 64.0f);
        avp.a(2, 2, 40.0f, 8.0f, 8, 8, 12, 12, 64.0f, 64.0f);
    }

    @Override
    public boolean B_() {
        return true;
    }
}

