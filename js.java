/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.util.UUID;

public class js {
    private eu a;
    private a b;
    private c c;
    private String d;

    public eu a() {
        return this.a;
    }

    public void a(eu eu2) {
        this.a = eu2;
    }

    public a b() {
        return this.b;
    }

    public void a(a a2) {
        this.b = a2;
    }

    public c c() {
        return this.c;
    }

    public void a(c c2) {
        this.c = c2;
    }

    public void a(String string) {
        this.d = string;
    }

    public String d() {
        return this.d;
    }

    public static class b
    implements JsonDeserializer<js>,
    JsonSerializer<js> {
        public js a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = ni.l(jsonElement, "status");
            js \u26032 = new js();
            if (jsonObject.has("description")) {
                \u26032.a((eu)jsonDeserializationContext.deserialize(jsonObject.get("description"), (Type)((Object)eu.class)));
            }
            if (jsonObject.has("players")) {
                \u26032.a((a)jsonDeserializationContext.deserialize(jsonObject.get("players"), (Type)((Object)a.class)));
            }
            if (jsonObject.has("version")) {
                \u26032.a((c)jsonDeserializationContext.deserialize(jsonObject.get("version"), (Type)((Object)c.class)));
            }
            if (jsonObject.has("favicon")) {
                \u26032.a(ni.h(jsonObject, "favicon"));
            }
            return \u26032;
        }

        public JsonElement a(js js2, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            if (js2.a() != null) {
                jsonObject.add("description", jsonSerializationContext.serialize(js2.a()));
            }
            if (js2.b() != null) {
                jsonObject.add("players", jsonSerializationContext.serialize(js2.b()));
            }
            if (js2.c() != null) {
                jsonObject.add("version", jsonSerializationContext.serialize(js2.c()));
            }
            if (js2.d() != null) {
                jsonObject.addProperty("favicon", js2.d());
            }
            return jsonObject;
        }

        @Override
        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.a((js)object, type, jsonSerializationContext);
        }

        @Override
        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.a(jsonElement, type, jsonDeserializationContext);
        }
    }

    public static class c {
        private final String a;
        private final int b;

        public c(String string, int n2) {
            this.a = string;
            this.b = n2;
        }

        public String a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public static class a
        implements JsonDeserializer<c>,
        JsonSerializer<c> {
            public c a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                JsonObject jsonObject = ni.l(jsonElement, "version");
                return new c(ni.h(jsonObject, "name"), ni.m(jsonObject, "protocol"));
            }

            public JsonElement a(c c2, Type type, JsonSerializationContext jsonSerializationContext) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", c2.a());
                jsonObject.addProperty("protocol", c2.b());
                return jsonObject;
            }

            @Override
            public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
                return this.a((c)object, type, jsonSerializationContext);
            }

            @Override
            public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return this.a(jsonElement, type, jsonDeserializationContext);
            }
        }
    }

    public static class js$a {
        private final int a;
        private final int b;
        private GameProfile[] c;

        public js$a(int n2, int n3) {
            this.a = n2;
            this.b = n3;
        }

        public int a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public GameProfile[] c() {
            return this.c;
        }

        public void a(GameProfile[] gameProfileArray) {
            this.c = gameProfileArray;
        }

        public static class a
        implements JsonDeserializer<js$a>,
        JsonSerializer<js$a> {
            public js$a a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                JsonObject jsonObject = ni.l(jsonElement, "players");
                js$a \u26032 = new js$a(ni.m(jsonObject, "max"), ni.m(jsonObject, "online"));
                if (ni.d(jsonObject, "sample") && (\u2603 = ni.t(jsonObject, "sample")).size() > 0) {
                    GameProfile[] gameProfileArray = new GameProfile[\u2603.size()];
                    for (int i2 = 0; i2 < gameProfileArray.length; ++i2) {
                        JsonObject jsonObject2 = ni.l(\u2603.get(i2), "player[" + i2 + "]");
                        String \u26033 = ni.h(jsonObject2, "id");
                        gameProfileArray[i2] = new GameProfile(UUID.fromString(\u26033), ni.h(jsonObject2, "name"));
                    }
                    \u26032.a(gameProfileArray);
                }
                return \u26032;
            }

            public JsonElement a(js$a a2, Type type, JsonSerializationContext jsonSerializationContext) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("max", a2.a());
                jsonObject.addProperty("online", a2.b());
                if (a2.c() != null && a2.c().length > 0) {
                    JsonArray jsonArray = new JsonArray();
                    for (int i2 = 0; i2 < a2.c().length; ++i2) {
                        JsonObject jsonObject2 = new JsonObject();
                        UUID \u26032 = a2.c()[i2].getId();
                        jsonObject2.addProperty("id", \u26032 == null ? "" : \u26032.toString());
                        jsonObject2.addProperty("name", a2.c()[i2].getName());
                        jsonArray.add(jsonObject2);
                    }
                    jsonObject.add("sample", jsonArray);
                }
                return jsonObject;
            }

            @Override
            public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
                return this.a((js$a)object, type, jsonSerializationContext);
            }

            @Override
            public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return this.a(jsonElement, type, jsonDeserializationContext);
            }
        }
    }
}

