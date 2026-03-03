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

public class bgk {
    public float[] a;
    public final int b;

    public bgk(float[] fArray, int n2) {
        this.a = fArray;
        this.b = n2;
    }

    public float a(int n2) {
        if (this.a == null) {
            throw new NullPointerException("uvs");
        }
        \u2603 = this.d(n2);
        return \u2603 == 0 || \u2603 == 1 ? this.a[0] : this.a[2];
    }

    public float b(int n2) {
        if (this.a == null) {
            throw new NullPointerException("uvs");
        }
        \u2603 = this.d(n2);
        return \u2603 == 0 || \u2603 == 3 ? this.a[1] : this.a[3];
    }

    private int d(int n2) {
        return (n2 + this.b / 90) % 4;
    }

    public int c(int n2) {
        return (n2 + (4 - this.b / 90)) % 4;
    }

    public void a(float[] fArray) {
        if (this.a == null) {
            this.a = fArray;
        }
    }

    static class a
    implements JsonDeserializer<bgk> {
        a() {
        }

        public bgk a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            float[] \u26032 = this.b(jsonObject);
            int \u26033 = this.a(jsonObject);
            return new bgk(\u26032, \u26033);
        }

        protected int a(JsonObject jsonObject) {
            int n2 = ni.a(jsonObject, "rotation", 0);
            if (n2 < 0 || n2 % 90 != 0 || n2 / 90 > 3) {
                throw new JsonParseException("Invalid rotation " + n2 + " found, only 0/90/180/270 allowed");
            }
            return n2;
        }

        private float[] b(JsonObject jsonObject) {
            if (!jsonObject.has("uv")) {
                return null;
            }
            JsonArray jsonArray = ni.t(jsonObject, "uv");
            if (jsonArray.size() != 4) {
                throw new JsonParseException("Expected 4 uv values, found: " + jsonArray.size());
            }
            float[] \u26032 = new float[4];
            for (int i2 = 0; i2 < \u26032.length; ++i2) {
                \u26032[i2] = ni.d(jsonArray.get(i2), "uv[" + i2 + "]");
            }
            return \u26032;
        }

        @Override
        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.a(jsonElement, type, jsonDeserializationContext);
        }
    }
}

