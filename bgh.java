/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Map;
import org.lwjgl.util.vector.Vector3f;

public class bgh {
    public final Vector3f a;
    public final Vector3f b;
    public final Map<cq, bgi> c;
    public final bgj d;
    public final boolean e;

    public bgh(Vector3f vector3f, Vector3f vector3f2, Map<cq, bgi> map, bgj bgj2, boolean bl2) {
        this.a = vector3f;
        this.b = vector3f2;
        this.c = map;
        this.d = bgj2;
        this.e = bl2;
        this.a();
    }

    private void a() {
        for (Map.Entry<cq, bgi> entry : this.c.entrySet()) {
            float[] fArray = this.a(entry.getKey());
            entry.getValue().e.a(fArray);
        }
    }

    private float[] a(cq cq2) {
        float[] fArray;
        switch (cq2) {
            case a: 
            case b: {
                fArray = new float[]{this.a.x, this.a.z, this.b.x, this.b.z};
                break;
            }
            case c: 
            case d: {
                fArray = new float[]{this.a.x, 16.0f - this.b.y, this.b.x, 16.0f - this.a.y};
                break;
            }
            case e: 
            case f: {
                fArray = new float[]{this.a.z, 16.0f - this.b.y, this.b.z, 16.0f - this.a.y};
                break;
            }
            default: {
                throw new NullPointerException();
            }
        }
        return fArray;
    }

    static class a
    implements JsonDeserializer<bgh> {
        a() {
        }

        public bgh a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Vector3f \u26032 = this.e(jsonObject);
            Vector3f \u26033 = this.d(jsonObject);
            bgj \u26034 = this.a(jsonObject);
            Map<cq, bgi> \u26035 = this.a(jsonDeserializationContext, jsonObject);
            if (jsonObject.has("shade") && !ni.c(jsonObject, "shade")) {
                throw new JsonParseException("Expected shade to be a Boolean");
            }
            boolean \u26036 = ni.a(jsonObject, "shade", true);
            return new bgh(\u26032, \u26033, \u26035, \u26034, \u26036);
        }

        private bgj a(JsonObject jsonObject) {
            bgj \u26036 = null;
            if (jsonObject.has("rotation")) {
                JsonObject jsonObject2 = ni.s(jsonObject, "rotation");
                Vector3f \u26032 = this.a(jsonObject2, "origin");
                \u26032.scale(0.0625f);
                cq.a \u26033 = this.c(jsonObject2);
                float \u26034 = this.b(jsonObject2);
                boolean \u26035 = ni.a(jsonObject2, "rescale", false);
                \u26036 = new bgj(\u26032, \u26033, \u26034, \u26035);
            }
            return \u26036;
        }

        private float b(JsonObject jsonObject) {
            float f2 = ni.k(jsonObject, "angle");
            if (f2 != 0.0f && ns.e(f2) != 22.5f && ns.e(f2) != 45.0f) {
                throw new JsonParseException("Invalid rotation " + f2 + " found, only -45/-22.5/0/22.5/45 allowed");
            }
            return f2;
        }

        private cq.a c(JsonObject jsonObject) {
            String string = ni.h(jsonObject, "axis");
            cq.a \u26032 = cq.a.a(string.toLowerCase());
            if (\u26032 == null) {
                throw new JsonParseException("Invalid rotation axis: " + string);
            }
            return \u26032;
        }

        private Map<cq, bgi> a(JsonDeserializationContext jsonDeserializationContext, JsonObject jsonObject) {
            Map<cq, bgi> map = this.b(jsonDeserializationContext, jsonObject);
            if (map.isEmpty()) {
                throw new JsonParseException("Expected between 1 and 6 unique faces, got 0");
            }
            return map;
        }

        private Map<cq, bgi> b(JsonDeserializationContext jsonDeserializationContext, JsonObject jsonObject) {
            EnumMap<cq, bgi> enumMap = Maps.newEnumMap(cq.class);
            JsonObject \u26032 = ni.s(jsonObject, "faces");
            for (Map.Entry<String, JsonElement> entry : \u26032.entrySet()) {
                cq cq2 = this.a(entry.getKey());
                enumMap.put(cq2, (bgi)jsonDeserializationContext.deserialize(entry.getValue(), (Type)((Object)bgi.class)));
            }
            return enumMap;
        }

        private cq a(String string) {
            cq cq2 = cq.a(string);
            if (cq2 == null) {
                throw new JsonParseException("Unknown facing: " + string);
            }
            return cq2;
        }

        private Vector3f d(JsonObject jsonObject) {
            Vector3f vector3f = this.a(jsonObject, "to");
            if (vector3f.x < -16.0f || vector3f.y < -16.0f || vector3f.z < -16.0f || vector3f.x > 32.0f || vector3f.y > 32.0f || vector3f.z > 32.0f) {
                throw new JsonParseException("'to' specifier exceeds the allowed boundaries: " + vector3f);
            }
            return vector3f;
        }

        private Vector3f e(JsonObject jsonObject) {
            Vector3f vector3f = this.a(jsonObject, "from");
            if (vector3f.x < -16.0f || vector3f.y < -16.0f || vector3f.z < -16.0f || vector3f.x > 32.0f || vector3f.y > 32.0f || vector3f.z > 32.0f) {
                throw new JsonParseException("'from' specifier exceeds the allowed boundaries: " + vector3f);
            }
            return vector3f;
        }

        private Vector3f a(JsonObject jsonObject, String string) {
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

