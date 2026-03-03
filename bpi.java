/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import org.apache.commons.lang3.Validate;

public class bpi
implements JsonDeserializer<bph> {
    public bph a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = ni.l(jsonElement, "entry");
        bph \u26032 = new bph();
        \u26032.a(ni.a(jsonObject, "replace", false));
        bpg \u26033 = bpg.a(ni.a(jsonObject, "category", bpg.a.a()));
        \u26032.a(\u26033);
        Validate.notNull(\u26033, "Invalid category", new Object[0]);
        if (jsonObject.has("sounds")) {
            JsonArray jsonArray = ni.t(jsonObject, "sounds");
            for (int i2 = 0; i2 < jsonArray.size(); ++i2) {
                JsonElement jsonElement2 = jsonArray.get(i2);
                bph.a \u26034 = new bph.a();
                if (ni.a(jsonElement2)) {
                    \u26034.a(ni.a(jsonElement2, "sound"));
                } else {
                    JsonObject jsonObject2 = ni.l(jsonElement2, "sound");
                    \u26034.a(ni.h(jsonObject2, "name"));
                    if (jsonObject2.has("type")) {
                        bph.a.a a2 = bph.a.a.a(ni.h(jsonObject2, "type"));
                        Validate.notNull(a2, "Invalid type", new Object[0]);
                        \u26034.a(a2);
                    }
                    if (jsonObject2.has("volume")) {
                        float f2 = ni.k(jsonObject2, "volume");
                        Validate.isTrue(f2 > 0.0f, "Invalid volume", new Object[0]);
                        \u26034.a(f2);
                    }
                    if (jsonObject2.has("pitch")) {
                        float f3 = ni.k(jsonObject2, "pitch");
                        Validate.isTrue(f3 > 0.0f, "Invalid pitch", new Object[0]);
                        \u26034.b(f3);
                    }
                    if (jsonObject2.has("weight")) {
                        int n2 = ni.m(jsonObject2, "weight");
                        Validate.isTrue(n2 > 0, "Invalid weight", new Object[0]);
                        \u26034.a(n2);
                    }
                    if (jsonObject2.has("stream")) {
                        \u26034.a(ni.i(jsonObject2, "stream"));
                    }
                }
                \u26032.a().add(\u26034);
            }
        }
        return \u26032;
    }

    @Override
    public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return this.a(jsonElement, type, jsonDeserializationContext);
    }
}

