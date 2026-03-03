/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;
import java.io.IOException;
import java.util.UUID;

public class jg
implements ff<jf> {
    private GameProfile a;

    public jg() {
    }

    public jg(GameProfile gameProfile) {
        this.a = gameProfile;
    }

    @Override
    public void a(em em2) throws IOException {
        String string = em2.c(36);
        \u2603 = em2.c(16);
        UUID \u26032 = UUID.fromString(string);
        this.a = new GameProfile(\u26032, \u2603);
    }

    @Override
    public void b(em em2) throws IOException {
        UUID uUID = this.a.getId();
        em2.a(uUID == null ? "" : uUID.toString());
        em2.a(this.a.getName());
    }

    @Override
    public void a(jf jf2) {
        jf2.a(this);
    }

    public GameProfile a() {
        return this.a;
    }
}

