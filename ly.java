/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;

public class ly
extends mb<GameProfile, lz> {
    public ly(File file) {
        super(file);
    }

    @Override
    protected ma<GameProfile> a(JsonObject jsonObject) {
        return new lz(jsonObject);
    }

    @Override
    public String[] a() {
        String[] stringArray = new String[this.e().size()];
        int \u26032 = 0;
        for (lz lz2 : this.e().values()) {
            stringArray[\u26032++] = ((GameProfile)lz2.f()).getName();
        }
        return stringArray;
    }

    @Override
    public boolean b(GameProfile gameProfile) {
        lz lz2 = (lz)this.b(gameProfile);
        if (lz2 != null) {
            return lz2.b();
        }
        return false;
    }

    protected String c(GameProfile gameProfile) {
        return gameProfile.getId().toString();
    }

    public GameProfile a(String string) {
        for (lz lz2 : this.e().values()) {
            if (!string.equalsIgnoreCase(((GameProfile)lz2.f()).getName())) continue;
            return (GameProfile)lz2.f();
        }
        return null;
    }

    @Override
    protected /* synthetic */ String a(Object object) {
        return this.c((GameProfile)object);
    }
}

