/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import org.apache.commons.lang3.Validate;

public class boe
extends bnv<bod> {
    public bod a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        float[] \u26032 = new float[256];
        float[] \u26033 = new float[256];
        float[] \u26034 = new float[256];
        float \u26035 = 1.0f;
        float \u26036 = 0.0f;
        float \u26037 = 0.0f;
        if (jsonObject.has("characters")) {
            if (!jsonObject.get("characters").isJsonObject()) {
                throw new JsonParseException("Invalid font->characters: expected object, was " + jsonObject.get("characters"));
            }
            \u2603 = jsonObject.getAsJsonObject("characters");
            if (\u2603.has("default")) {
                if (!\u2603.get("default").isJsonObject()) {
                    throw new JsonParseException("Invalid font->characters->default: expected object, was " + \u2603.get("default"));
                }
                \u2603 = \u2603.getAsJsonObject("default");
                \u26035 = ni.a(\u2603, "width", \u26035);
                Validate.inclusiveBetween(0.0, 3.4028234663852886E38, \u26035, "Invalid default width");
                \u26036 = ni.a(\u2603, "spacing", \u26036);
                Validate.inclusiveBetween(0.0, 3.4028234663852886E38, \u26036, "Invalid default spacing");
                \u26037 = ni.a(\u2603, "left", \u26036);
                Validate.inclusiveBetween(0.0, 3.4028234663852886E38, \u26037, "Invalid default left");
            }
            for (int i2 = 0; i2 < 256; ++i2) {
                JsonElement jsonElement2 = \u2603.get(Integer.toString(i2));
                float \u26038 = \u26035;
                float \u26039 = \u26036;
                float \u260310 = \u26037;
                if (jsonElement2 != null) {
                    JsonObject jsonObject2 = ni.l(jsonElement2, "characters[" + i2 + "]");
                    \u26038 = ni.a(jsonObject2, "width", \u26035);
                    Validate.inclusiveBetween(0.0, 3.4028234663852886E38, \u26038, "Invalid width");
                    \u26039 = ni.a(jsonObject2, "spacing", \u26036);
                    Validate.inclusiveBetween(0.0, 3.4028234663852886E38, \u26039, "Invalid spacing");
                    \u260310 = ni.a(jsonObject2, "left", \u26037);
                    Validate.inclusiveBetween(0.0, 3.4028234663852886E38, \u260310, "Invalid left");
                }
                \u26032[i2] = \u26038;
                \u26033[i2] = \u26039;
                \u26034[i2] = \u260310;
            }
        }
        return new bod(\u26032, \u26034, \u26033);
    }

    @Override
    public String a() {
        return "font";
    }

    @Override
    public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return this.a(jsonElement, type, jsonDeserializationContext);
    }
}

