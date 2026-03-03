/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.apache.commons.lang3.Validate;

public class bob
extends bnv<boa>
implements JsonSerializer<boa> {
    public boa a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        ArrayList<bnz> arrayList = Lists.newArrayList();
        JsonObject \u26032 = ni.l(jsonElement, "metadata section");
        int \u26033 = ni.a(\u26032, "frametime", 1);
        if (\u26033 != 1) {
            Validate.inclusiveBetween(1L, Integer.MAX_VALUE, \u26033, "Invalid default frame time");
        }
        if (\u26032.has("frames")) {
            try {
                JsonArray jsonArray = ni.t(\u26032, "frames");
                for (int i2 = 0; i2 < jsonArray.size(); ++i2) {
                    JsonElement jsonElement2 = jsonArray.get(i2);
                    bnz \u26034 = this.a(i2, jsonElement2);
                    if (\u26034 == null) continue;
                    arrayList.add(\u26034);
                }
            }
            catch (ClassCastException classCastException) {
                throw new JsonParseException("Invalid animation->frames: expected array, was " + \u26032.get("frames"), classCastException);
            }
        }
        int n2 = ni.a(\u26032, "width", -1);
        i2 = ni.a(\u26032, "height", -1);
        if (n2 != -1) {
            Validate.inclusiveBetween(1L, Integer.MAX_VALUE, n2, "Invalid width");
        }
        if (i2 != -1) {
            Validate.inclusiveBetween(1L, Integer.MAX_VALUE, i2, "Invalid height");
        }
        boolean \u26035 = ni.a(\u26032, "interpolate", false);
        return new boa(arrayList, n2, i2, \u26033, \u26035);
    }

    private bnz a(int n2, JsonElement jsonElement) {
        if (jsonElement.isJsonPrimitive()) {
            return new bnz(ni.f(jsonElement, "frames[" + n2 + "]"));
        }
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = ni.l(jsonElement, "frames[" + n2 + "]");
            int \u26032 = ni.a(jsonObject, "time", -1);
            if (jsonObject.has("time")) {
                Validate.inclusiveBetween(1L, Integer.MAX_VALUE, \u26032, "Invalid frame time");
            }
            int \u26033 = ni.m(jsonObject, "index");
            Validate.inclusiveBetween(0L, Integer.MAX_VALUE, \u26033, "Invalid frame index");
            return new bnz(\u26033, \u26032);
        }
        return null;
    }

    public JsonElement a(boa boa2, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("frametime", boa2.d());
        if (boa2.b() != -1) {
            jsonObject.addProperty("width", boa2.b());
        }
        if (boa2.a() != -1) {
            jsonObject.addProperty("height", boa2.a());
        }
        if (boa2.c() > 0) {
            JsonArray jsonArray = new JsonArray();
            for (int i2 = 0; i2 < boa2.c(); ++i2) {
                if (boa2.b(i2)) {
                    JsonObject jsonObject2 = new JsonObject();
                    jsonObject2.addProperty("index", boa2.c(i2));
                    jsonObject2.addProperty("time", boa2.a(i2));
                    jsonArray.add(jsonObject2);
                    continue;
                }
                jsonArray.add(new JsonPrimitive(boa2.c(i2)));
            }
            jsonObject.add("frames", jsonArray);
        }
        return jsonObject;
    }

    @Override
    public String a() {
        return "animation";
    }

    @Override
    public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return this.a(jsonElement, type, jsonDeserializationContext);
    }

    @Override
    public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
        return this.a((boa)object, type, jsonSerializationContext);
    }
}

