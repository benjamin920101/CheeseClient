/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;

public class boh
extends bnv<bog> {
    public bog a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        HashSet<bnr> \u26032 = Sets.newHashSet();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String string = entry.getKey();
            JsonObject \u26033 = ni.l(entry.getValue(), "language");
            \u2603 = ni.h(\u26033, "region");
            \u2603 = ni.h(\u26033, "name");
            boolean \u26034 = ni.a(\u26033, "bidirectional", false);
            if (\u2603.isEmpty()) {
                throw new JsonParseException("Invalid language->'" + string + "'->region: empty value");
            }
            if (\u2603.isEmpty()) {
                throw new JsonParseException("Invalid language->'" + string + "'->name: empty value");
            }
            if (\u26032.add(new bnr(string, \u2603, \u2603, \u26034))) continue;
            throw new JsonParseException("Duplicate language->'" + string + "' defined");
        }
        return new bog(\u26032);
    }

    @Override
    public String a() {
        return "language";
    }

    @Override
    public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return this.a(jsonElement, type, jsonDeserializationContext);
    }
}

