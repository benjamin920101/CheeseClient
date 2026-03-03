/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class bok
extends bnv<boj>
implements JsonSerializer<boj> {
    public boj a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        eu \u26032 = (eu)jsonDeserializationContext.deserialize(jsonObject.get("description"), (Type)((Object)eu.class));
        if (\u26032 == null) {
            throw new JsonParseException("Invalid/missing description!");
        }
        int \u26033 = ni.m(jsonObject, "pack_format");
        return new boj(\u26032, \u26033);
    }

    public JsonElement a(boj boj2, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("pack_format", boj2.b());
        jsonObject.add("description", jsonSerializationContext.serialize(boj2.a()));
        return jsonObject;
    }

    @Override
    public String a() {
        return "pack";
    }

    @Override
    public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return this.a(jsonElement, type, jsonDeserializationContext);
    }

    @Override
    public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
        return this.a((boj)object, type, jsonSerializationContext);
    }
}

