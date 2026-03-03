/*
 * Decompiled with CFR 0.152.
 */
import com.mojang.authlib.GameProfile;
import java.io.IOException;

public class jl
implements ff<jk> {
    private GameProfile a;

    public jl() {
    }

    public jl(GameProfile gameProfile) {
        this.a = gameProfile;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = new GameProfile(null, em2.c(16));
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a.getName());
    }

    @Override
    public void a(jk jk2) {
        jk2.a(this);
    }

    public GameProfile a() {
        return this.a;
    }
}

