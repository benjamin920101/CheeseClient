/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.util.UUID;

public class lz
extends ma<GameProfile> {
    private final int a;
    private final boolean b;

    public lz(GameProfile gameProfile, int n2, boolean bl2) {
        super(gameProfile);
        this.a = n2;
        this.b = bl2;
    }

    public lz(JsonObject jsonObject) {
        super(lz.b(jsonObject), jsonObject);
        this.a = jsonObject.has("level") ? jsonObject.get("level").getAsInt() : 0;
        this.b = jsonObject.has("bypassesPlayerLimit") && jsonObject.get("bypassesPlayerLimit").getAsBoolean();
    }

    public int a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }

    @Override
    protected void a(JsonObject jsonObject) {
        if (this.f() == null) {
            return;
        }
        jsonObject.addProperty("uuid", ((GameProfile)this.f()).getId() == null ? "" : ((GameProfile)this.f()).getId().toString());
        jsonObject.addProperty("name", ((GameProfile)this.f()).getName());
        super.a(jsonObject);
        jsonObject.addProperty("level", this.a);
        jsonObject.addProperty("bypassesPlayerLimit", this.b);
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

