/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;

public class mc
extends mb<GameProfile, md> {
    public mc(File file) {
        super(file);
    }

    @Override
    protected ma<GameProfile> a(JsonObject jsonObject) {
        return new md(jsonObject);
    }

    public boolean a(GameProfile gameProfile) {
        return this.d(gameProfile);
    }

    @Override
    public String[] a() {
        String[] stringArray = new String[this.e().size()];
        int \u26032 = 0;
        for (md md2 : this.e().values()) {
            stringArray[\u26032++] = ((GameProfile)md2.f()).getName();
        }
        return stringArray;
    }

    @Override
    protected String b(GameProfile gameProfile) {
        return gameProfile.getId().toString();
    }

    public GameProfile a(String string) {
        for (md md2 : this.e().values()) {
            if (!string.equalsIgnoreCase(((GameProfile)md2.f()).getName())) continue;
            return (GameProfile)md2.f();
        }
        return null;
    }

    @Override
    protected /* synthetic */ String a(Object object) {
        return this.b((GameProfile)object);
    }
}

