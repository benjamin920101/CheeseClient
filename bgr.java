/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class bgr {
    public static final bgr a = new bgr();
    public static float b = 0.0f;
    public static float c = 0.0f;
    public static float d = 0.0f;
    public static float e = 0.0f;
    public static float f = 0.0f;
    public static float g = 0.0f;
    public static float h = 0.0f;
    public static float i = 0.0f;
    public static float j = 0.0f;
    public final bgq k;
    public final bgq l;
    public final bgq m;
    public final bgq n;
    public final bgq o;
    public final bgq p;

    private bgr() {
        this(bgq.a, bgq.a, bgq.a, bgq.a, bgq.a, bgq.a);
    }

    public bgr(bgr bgr2) {
        this.k = bgr2.k;
        this.l = bgr2.l;
        this.m = bgr2.m;
        this.n = bgr2.n;
        this.o = bgr2.o;
        this.p = bgr2.p;
    }

    public bgr(bgq bgq2, bgq bgq3, bgq bgq4, bgq bgq5, bgq bgq6, bgq bgq7) {
        this.k = bgq2;
        this.l = bgq3;
        this.m = bgq4;
        this.n = bgq5;
        this.o = bgq6;
        this.p = bgq7;
    }

    public void a(b b2) {
        bgq bgq2 = this.b(b2);
        if (bgq2 != bgq.a) {
            bfl.b(bgq2.c.x + b, bgq2.c.y + c, bgq2.c.z + d);
            bfl.b(bgq2.b.y + f, 0.0f, 1.0f, 0.0f);
            bfl.b(bgq2.b.x + e, 1.0f, 0.0f, 0.0f);
            bfl.b(bgq2.b.z + g, 0.0f, 0.0f, 1.0f);
            bfl.a(bgq2.d.x + h, bgq2.d.y + i, bgq2.d.z + j);
        }
    }

    public bgq b(b b2) {
        switch (b2) {
            case b: {
                return this.k;
            }
            case c: {
                return this.l;
            }
            case d: {
                return this.m;
            }
            case e: {
                return this.n;
            }
            case f: {
                return this.o;
            }
            case g: {
                return this.p;
            }
        }
        return bgq.a;
    }

    public boolean c(b b2) {
        return !this.b(b2).equals(bgq.a);
    }

    static class a
    implements JsonDeserializer<bgr> {
        a() {
        }

        public bgr a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            bgq \u26032 = this.a(jsonDeserializationContext, jsonObject, "thirdperson");
            bgq \u26033 = this.a(jsonDeserializationContext, jsonObject, "firstperson");
            bgq \u26034 = this.a(jsonDeserializationContext, jsonObject, "head");
            bgq \u26035 = this.a(jsonDeserializationContext, jsonObject, "gui");
            bgq \u26036 = this.a(jsonDeserializationContext, jsonObject, "ground");
            bgq \u26037 = this.a(jsonDeserializationContext, jsonObject, "fixed");
            return new bgr(\u26032, \u26033, \u26034, \u26035, \u26036, \u26037);
        }

        private bgq a(JsonDeserializationContext jsonDeserializationContext, JsonObject jsonObject, String string) {
            if (jsonObject.has(string)) {
                return (bgq)jsonDeserializationContext.deserialize(jsonObject.get(string), (Type)((Object)bgq.class));
            }
            return bgq.a;
        }

        @Override
        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.a(jsonElement, type, jsonDeserializationContext);
        }
    }

    public static enum b {
        a,
        b,
        c,
        d,
        e,
        f,
        g;

    }
}

