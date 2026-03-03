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
import org.lwjgl.util.vector.Vector3f;

public class bgq {
    public static final bgq a = new bgq(new Vector3f(), new Vector3f(), new Vector3f(1.0f, 1.0f, 1.0f));
    public final Vector3f b;
    public final Vector3f c;
    public final Vector3f d;

    public bgq(Vector3f vector3f, Vector3f vector3f2, Vector3f vector3f3) {
        this.b = new Vector3f(vector3f);
        this.c = new Vector3f(vector3f2);
        this.d = new Vector3f(vector3f3);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (this.getClass() != object.getClass()) {
            return false;
        }
        bgq bgq2 = (bgq)object;
        if (!this.b.equals(bgq2.b)) {
            return false;
        }
        if (!this.d.equals(bgq2.d)) {
            return false;
        }
        return this.c.equals(bgq2.c);
    }

    public int hashCode() {
        int n2 = this.b.hashCode();
        n2 = 31 * n2 + this.c.hashCode();
        n2 = 31 * n2 + this.d.hashCode();
        return n2;
    }

    static class a
    implements JsonDeserializer<bgq> {
        private static final Vector3f a = new Vector3f(0.0f, 0.0f, 0.0f);
        private static final Vector3f b = new Vector3f(0.0f, 0.0f, 0.0f);
        private static final Vector3f c = new Vector3f(1.0f, 1.0f, 1.0f);

        a() {
        }

        public bgq a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Vector3f \u26032 = this.a(jsonObject, "rotation", a);
            Vector3f \u26033 = this.a(jsonObject, "translation", b);
            \u26033.scale(0.0625f);
            \u26033.x = ns.a(\u26033.x, -1.5f, 1.5f);
            \u26033.y = ns.a(\u26033.y, -1.5f, 1.5f);
            \u26033.z = ns.a(\u26033.z, -1.5f, 1.5f);
            Vector3f \u26034 = this.a(jsonObject, "scale", c);
            \u26034.x = ns.a(\u26034.x, -4.0f, 4.0f);
            \u26034.y = ns.a(\u26034.y, -4.0f, 4.0f);
            \u26034.z = ns.a(\u26034.z, -4.0f, 4.0f);
            return new bgq(\u26032, \u26033, \u26034);
        }

        private Vector3f a(JsonObject jsonObject, String string, Vector3f vector3f) {
            if (!jsonObject.has(string)) {
                return vector3f;
            }
            JsonArray jsonArray = ni.t(jsonObject, string);
            if (jsonArray.size() != 3) {
                throw new JsonParseException("Expected 3 " + string + " values, found: " + jsonArray.size());
            }
            float[] \u26032 = new float[3];
            for (int i2 = 0; i2 < \u26032.length; ++i2) {
                \u26032[i2] = ni.d(jsonArray.get(i2), string + "[" + i2 + "]");
            }
            return new Vector3f(\u26032[0], \u26032[1], \u26032[2]);
        }

        @Override
        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.a(jsonElement, type, jsonDeserializationContext);
        }
    }
}

