/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public interface eu
extends Iterable<eu> {
    public eu a(ez var1);

    public ez b();

    public eu a(String var1);

    public eu a(eu var1);

    public String e();

    public String c();

    public String d();

    public List<eu> a();

    public eu f();

    public static class a
    implements JsonDeserializer<eu>,
    JsonSerializer<eu> {
        private static final Gson a;

        /*
         * WARNING - void declaration
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public eu a(JsonElement jsonElement2, Type type, JsonDeserializationContext jsonDeserializationContext2) throws JsonParseException {
            void var5_14;
            JsonElement jsonElement2;
            if (jsonElement2.isJsonPrimitive()) {
                return new fa(jsonElement2.getAsString());
            }
            if (jsonElement2.isJsonObject()) {
                JsonDeserializationContext jsonDeserializationContext2;
                void var5_12;
                Object object;
                JsonObject jsonObject = jsonElement2.getAsJsonObject();
                if (jsonObject.has("text")) {
                    fa fa2 = new fa(jsonObject.get("text").getAsString());
                } else if (jsonObject.has("translate")) {
                    object = jsonObject.get("translate").getAsString();
                    if (jsonObject.has("with")) {
                        JsonArray jsonArray = jsonObject.getAsJsonArray("with");
                        Object[] \u26032 = new Object[jsonArray.size()];
                        for (int i2 = 0; i2 < \u26032.length; ++i2) {
                            \u26032[i2] = this.a(jsonArray.get(i2), type, jsonDeserializationContext2);
                            if (!(\u26032[i2] instanceof fa) || !(\u2603 = (fa)\u26032[i2]).b().g() || !\u2603.a().isEmpty()) continue;
                            \u26032[i2] = \u2603.g();
                        }
                        fb fb2 = new fb((String)object, \u26032);
                    } else {
                        fb fb3 = new fb((String)object, new Object[0]);
                    }
                } else if (jsonObject.has("score")) {
                    object = jsonObject.getAsJsonObject("score");
                    if (!((JsonObject)object).has("name") || !((JsonObject)object).has("objective")) throw new JsonParseException("A score component needs a least a name and an objective");
                    ex ex2 = new ex(ni.h((JsonObject)object, "name"), ni.h((JsonObject)object, "objective"));
                    if (((JsonObject)object).has("value")) {
                        ex2.b(ni.h((JsonObject)object, "value"));
                    }
                } else {
                    if (!jsonObject.has("selector")) throw new JsonParseException("Don't know how to turn " + jsonElement2.toString() + " into a Component");
                    ey ey2 = new ey(ni.h(jsonObject, "selector"));
                }
                if (jsonObject.has("extra")) {
                    object = jsonObject.getAsJsonArray("extra");
                    if (((JsonArray)object).size() <= 0) throw new JsonParseException("Unexpected empty array of components");
                    for (int i3 = 0; i3 < ((JsonArray)object).size(); ++i3) {
                        var5_12.a(this.a(((JsonArray)object).get(i3), type, jsonDeserializationContext2));
                    }
                }
                var5_12.a((ez)jsonDeserializationContext2.deserialize(jsonElement2, (Type)((Object)ez.class)));
                return var5_12;
            }
            if (!jsonElement2.isJsonArray()) throw new JsonParseException("Don't know how to turn " + jsonElement2.toString() + " into a Component");
            JsonArray \u26033 = jsonElement2.getAsJsonArray();
            Object var5_13 = null;
            for (JsonElement jsonElement3 : \u26033) {
                eu eu2 = this.a(jsonElement3, jsonElement3.getClass(), jsonDeserializationContext2);
                if (var5_14 == null) {
                    eu eu3 = eu2;
                    continue;
                }
                var5_14.a(eu2);
            }
            return var5_14;
        }

        private void a(ez ez2, JsonObject jsonObject, JsonSerializationContext jsonSerializationContext) {
            JsonElement jsonElement = jsonSerializationContext.serialize(ez2);
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject2 = (JsonObject)jsonElement;
                for (Map.Entry<String, JsonElement> entry : jsonObject2.entrySet()) {
                    jsonObject.add(entry.getKey(), entry.getValue());
                }
            }
        }

        public JsonElement a(eu eu22, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonElement \u26033;
            es es2;
            if (eu22 instanceof fa && eu22.b().g() && eu22.a().isEmpty()) {
                return new JsonPrimitive(((fa)eu22).g());
            }
            JsonObject jsonObject = new JsonObject();
            if (!eu22.b().g()) {
                this.a(eu22.b(), jsonObject, jsonSerializationContext);
            }
            if (!eu22.a().isEmpty()) {
                JsonArray iterable = new JsonArray();
                for (eu eu2 : eu22.a()) {
                    iterable.add(this.a(eu2, eu2.getClass(), jsonSerializationContext));
                }
                jsonObject.add("extra", iterable);
            }
            if (eu22 instanceof fa) {
                jsonObject.addProperty("text", ((fa)eu22).g());
            } else if (eu22 instanceof fb) {
                es2 = (fb)eu22;
                jsonObject.addProperty("translate", ((fb)es2).i());
                if (((fb)es2).j() != null && ((fb)es2).j().length > 0) {
                    \u26033 = new JsonArray();
                    for (Object object : ((fb)es2).j()) {
                        if (object instanceof eu) {
                            ((JsonArray)\u26033).add(this.a((eu)object, object.getClass(), jsonSerializationContext));
                            continue;
                        }
                        ((JsonArray)\u26033).add(new JsonPrimitive(String.valueOf(object)));
                    }
                    jsonObject.add("with", \u26033);
                }
            } else if (eu22 instanceof ex) {
                es2 = (ex)eu22;
                \u26033 = new JsonObject();
                ((JsonObject)\u26033).addProperty("name", ((ex)es2).g());
                ((JsonObject)\u26033).addProperty("objective", ((ex)es2).h());
                ((JsonObject)\u26033).addProperty("value", ((ex)es2).e());
                jsonObject.add("score", \u26033);
            } else if (eu22 instanceof ey) {
                es2 = (ey)eu22;
                jsonObject.addProperty("selector", ((ey)es2).g());
            } else {
                throw new IllegalArgumentException("Don't know how to serialize " + eu22 + " as a Component");
            }
            return jsonObject;
        }

        public static String a(eu eu2) {
            return a.toJson(eu2);
        }

        public static eu a(String string) {
            return a.fromJson(string, eu.class);
        }

        @Override
        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.a((eu)object, type, jsonSerializationContext);
        }

        @Override
        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.a(jsonElement, type, jsonDeserializationContext);
        }

        static {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeHierarchyAdapter(eu.class, new a());
            gsonBuilder.registerTypeHierarchyAdapter(ez.class, new ez.a());
            gsonBuilder.registerTypeAdapterFactory(new nr());
            a = gsonBuilder.create();
        }
    }
}

