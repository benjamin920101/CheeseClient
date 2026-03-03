/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class boo
extends bnv<bon> {
    public bon a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        boolean \u26032 = ni.a(jsonObject, "blur", false);
        boolean \u26033 = ni.a(jsonObject, "clamp", false);
        ArrayList<Integer> \u26034 = Lists.newArrayList();
        if (jsonObject.has("mipmaps")) {
            try {
                JsonArray jsonArray = jsonObject.getAsJsonArray("mipmaps");
                for (int i2 = 0; i2 < jsonArray.size(); ++i2) {
                    JsonElement jsonElement2 = jsonArray.get(i2);
                    if (jsonElement2.isJsonPrimitive()) {
                        try {
                            \u26034.add(jsonElement2.getAsInt());
                            continue;
                        }
                        catch (NumberFormatException numberFormatException) {
                            throw new JsonParseException("Invalid texture->mipmap->" + i2 + ": expected number, was " + jsonElement2, numberFormatException);
                        }
                    }
                    if (!jsonElement2.isJsonObject()) continue;
                    throw new JsonParseException("Invalid texture->mipmap->" + i2 + ": expected number, was " + jsonElement2);
                }
            }
            catch (ClassCastException classCastException) {
                throw new JsonParseException("Invalid texture->mipmaps: expected array, was " + jsonObject.get("mipmaps"), classCastException);
            }
        }
        return new bon(\u26032, \u26033, \u26034);
    }

    @Override
    public String a() {
        return "texture";
    }

    @Override
    public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return this.a(jsonElement, type, jsonDeserializationContext);
    }
}

