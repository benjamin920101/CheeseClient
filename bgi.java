/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class bgi {
    public static final cq a = null;
    public final cq b;
    public final int c;
    public final String d;
    public final bgk e;

    public bgi(cq cq2, int n2, String string, bgk bgk2) {
        this.b = cq2;
        this.c = n2;
        this.d = string;
        this.e = bgk2;
    }

    static class a
    implements JsonDeserializer<bgi> {
        a() {
        }

        public bgi a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            cq \u26032 = this.c(jsonObject);
            int \u26033 = this.a(jsonObject);
            String \u26034 = this.b(jsonObject);
            bgk \u26035 = (bgk)jsonDeserializationContext.deserialize(jsonObject, (Type)((Object)bgk.class));
            return new bgi(\u26032, \u26033, \u26034, \u26035);
        }

        protected int a(JsonObject jsonObject) {
            return ni.a(jsonObject, "tintindex", -1);
        }

        private String b(JsonObject jsonObject) {
            return ni.h(jsonObject, "texture");
        }

        private cq c(JsonObject jsonObject) {
            String string = ni.a(jsonObject, "cullface", "");
            return cq.a(string);
        }

        @Override
        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.a(jsonElement, type, jsonDeserializationContext);
        }
    }
}

