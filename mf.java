/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.util.UUID;

public class mf
extends ma<GameProfile> {
    public mf(GameProfile gameProfile) {
        super(gameProfile);
    }

    public mf(JsonObject jsonObject) {
        super(mf.b(jsonObject), jsonObject);
    }

    @Override
    protected void a(JsonObject jsonObject) {
        if (this.f() == null) {
            return;
        }
        jsonObject.addProperty("uuid", ((GameProfile)this.f()).getId() == null ? "" : ((GameProfile)this.f()).getId().toString());
        jsonObject.addProperty("name", ((GameProfile)this.f()).getName());
        super.a(jsonObject);
    }

    private static GameProfile b(JsonObject jsonObject) {
        if (!jsonObject.has("uuid") || !jsonObject.has("name")) {
            return null;
        }
        String string = jsonObject.get("uuid").getAsString();
        try {
            UUID uUID = UUID.fromString(string);
        }
        catch (Throwable throwable) {
            return null;
        }
        return new GameProfile(uUID, jsonObject.get("name").getAsString());
    }
}

