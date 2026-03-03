/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class ez {
    private ez a;
    private a b;
    private Boolean c;
    private Boolean d;
    private Boolean e;
    private Boolean f;
    private Boolean g;
    private et h;
    private ew i;
    private String j;
    private static final ez k = new ez(){

        @Override
        public a a() {
            return null;
        }

        @Override
        public boolean b() {
            return false;
        }

        @Override
        public boolean c() {
            return false;
        }

        @Override
        public boolean d() {
            return false;
        }

        @Override
        public boolean e() {
            return false;
        }

        @Override
        public boolean f() {
            return false;
        }

        @Override
        public et h() {
            return null;
        }

        @Override
        public ew i() {
            return null;
        }

        @Override
        public String j() {
            return null;
        }

        @Override
        public ez a(a a2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ez a(Boolean bl2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ez b(Boolean bl2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ez c(Boolean bl2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ez d(Boolean bl2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ez e(Boolean bl2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ez a(et et2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ez a(ew ew2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public ez a(ez ez2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public String toString() {
            return "Style.ROOT";
        }

        @Override
        public ez m() {
            return this;
        }

        @Override
        public ez n() {
            return this;
        }

        @Override
        public String k() {
            return "";
        }
    };

    public a a() {
        return this.b == null ? this.o().a() : this.b;
    }

    public boolean b() {
        return this.c == null ? this.o().b() : this.c.booleanValue();
    }

    public boolean c() {
        return this.d == null ? this.o().c() : this.d.booleanValue();
    }

    public boolean d() {
        return this.f == null ? this.o().d() : this.f.booleanValue();
    }

    public boolean e() {
        return this.e == null ? this.o().e() : this.e.booleanValue();
    }

    public boolean f() {
        return this.g == null ? this.o().f() : this.g.booleanValue();
    }

    public boolean g() {
        return this.c == null && this.d == null && this.f == null && this.e == null && this.g == null && this.b == null && this.h == null && this.i == null;
    }

    public et h() {
        return this.h == null ? this.o().h() : this.h;
    }

    public ew i() {
        return this.i == null ? this.o().i() : this.i;
    }

    public String j() {
        return this.j == null ? this.o().j() : this.j;
    }

    public ez a(a a2) {
        this.b = a2;
        return this;
    }

    public ez a(Boolean bl2) {
        this.c = bl2;
        return this;
    }

    public ez b(Boolean bl2) {
        this.d = bl2;
        return this;
    }

    public ez c(Boolean bl2) {
        this.f = bl2;
        return this;
    }

    public ez d(Boolean bl2) {
        this.e = bl2;
        return this;
    }

    public ez e(Boolean bl2) {
        this.g = bl2;
        return this;
    }

    public ez a(et et2) {
        this.h = et2;
        return this;
    }

    public ez a(ew ew2) {
        this.i = ew2;
        return this;
    }

    public ez a(String string) {
        this.j = string;
        return this;
    }

    public ez a(ez ez2) {
        this.a = ez2;
        return this;
    }

    public String k() {
        if (this.g()) {
            if (this.a != null) {
                return this.a.k();
            }
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (this.a() != null) {
            stringBuilder.append((Object)this.a());
        }
        if (this.b()) {
            stringBuilder.append((Object)a.r);
        }
        if (this.c()) {
            stringBuilder.append((Object)a.u);
        }
        if (this.e()) {
            stringBuilder.append((Object)a.t);
        }
        if (this.f()) {
            stringBuilder.append((Object)a.q);
        }
        if (this.d()) {
            stringBuilder.append((Object)a.s);
        }
        return stringBuilder.toString();
    }

    private ez o() {
        return this.a == null ? k : this.a;
    }

    public String toString() {
        return "Style{hasParent=" + (this.a != null) + ", color=" + (Object)((Object)this.b) + ", bold=" + this.c + ", italic=" + this.d + ", underlined=" + this.e + ", obfuscated=" + this.g + ", clickEvent=" + this.h() + ", hoverEvent=" + this.i() + ", insertion=" + this.j() + '}';
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof ez) {
            ez ez2 = (ez)object;
            return this.b() == ez2.b() && this.a() == ez2.a() && this.c() == ez2.c() && this.f() == ez2.f() && this.d() == ez2.d() && this.e() == ez2.e() && (this.h() != null ? this.h().equals(ez2.h()) : ez2.h() == null) && (this.i() != null ? this.i().equals(ez2.i()) : ez2.i() == null) && (this.j() != null ? this.j().equals(ez2.j()) : ez2.j() == null);
        }
        return false;
    }

    public int hashCode() {
        int n2 = this.b.hashCode();
        n2 = 31 * n2 + this.c.hashCode();
        n2 = 31 * n2 + this.d.hashCode();
        n2 = 31 * n2 + this.e.hashCode();
        n2 = 31 * n2 + this.f.hashCode();
        n2 = 31 * n2 + this.g.hashCode();
        n2 = 31 * n2 + this.h.hashCode();
        n2 = 31 * n2 + this.i.hashCode();
        n2 = 31 * n2 + this.j.hashCode();
        return n2;
    }

    public ez m() {
        ez ez2 = new ez();
        ez2.c = this.c;
        ez2.d = this.d;
        ez2.f = this.f;
        ez2.e = this.e;
        ez2.g = this.g;
        ez2.b = this.b;
        ez2.h = this.h;
        ez2.i = this.i;
        ez2.a = this.a;
        ez2.j = this.j;
        return ez2;
    }

    public ez n() {
        ez ez2 = new ez();
        ez2.a(this.b());
        ez2.b(this.c());
        ez2.c(this.d());
        ez2.d(this.e());
        ez2.e(this.f());
        ez2.a(this.a());
        ez2.a(this.h());
        ez2.a(this.i());
        ez2.a(this.j());
        return ez2;
    }

    public static class a
    implements JsonDeserializer<ez>,
    JsonSerializer<ez> {
        public ez a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (jsonElement.isJsonObject()) {
                Object \u26034;
                et.a \u26033;
                ez ez2 = new ez();
                JsonObject \u26032 = jsonElement.getAsJsonObject();
                if (\u26032 == null) {
                    return null;
                }
                if (\u26032.has("bold")) {
                    ez2.c = \u26032.get("bold").getAsBoolean();
                }
                if (\u26032.has("italic")) {
                    ez2.d = \u26032.get("italic").getAsBoolean();
                }
                if (\u26032.has("underlined")) {
                    ez2.e = \u26032.get("underlined").getAsBoolean();
                }
                if (\u26032.has("strikethrough")) {
                    ez2.f = \u26032.get("strikethrough").getAsBoolean();
                }
                if (\u26032.has("obfuscated")) {
                    ez2.g = \u26032.get("obfuscated").getAsBoolean();
                }
                if (\u26032.has("color")) {
                    ez2.b = (a)((Object)jsonDeserializationContext.deserialize(\u26032.get("color"), (Type)((Object)a.class)));
                }
                if (\u26032.has("insertion")) {
                    ez2.j = \u26032.get("insertion").getAsString();
                }
                if (\u26032.has("clickEvent") && (\u2603 = \u26032.getAsJsonObject("clickEvent")) != null) {
                    JsonPrimitive jsonPrimitive = \u2603.getAsJsonPrimitive("action");
                    \u26033 = jsonPrimitive == null ? null : et.a.a(jsonPrimitive.getAsString());
                    \u26034 = \u2603.getAsJsonPrimitive("value");
                    String string = \u2603 = \u26034 == null ? null : ((JsonPrimitive)\u26034).getAsString();
                    if (\u26033 != null && \u2603 != null && \u26033.a()) {
                        ez2.h = new et(\u26033, \u2603);
                    }
                }
                if (\u26032.has("hoverEvent") && (\u2603 = \u26032.getAsJsonObject("hoverEvent")) != null) {
                    jsonPrimitive = \u2603.getAsJsonPrimitive("action");
                    \u26033 = jsonPrimitive == null ? null : ew.a.a(jsonPrimitive.getAsString());
                    \u26034 = (eu)jsonDeserializationContext.deserialize(\u2603.get("value"), (Type)((Object)eu.class));
                    if (\u26033 != null && \u26034 != null && ((ew.a)((Object)\u26033)).a()) {
                        ez2.i = new ew((ew.a)((Object)\u26033), (eu)\u26034);
                    }
                }
                return ez2;
            }
            return null;
        }

        public JsonElement a(ez ez2, Type type, JsonSerializationContext jsonSerializationContext) {
            if (ez2.g()) {
                return null;
            }
            JsonObject jsonObject = new JsonObject();
            if (ez2.c != null) {
                jsonObject.addProperty("bold", ez2.c);
            }
            if (ez2.d != null) {
                jsonObject.addProperty("italic", ez2.d);
            }
            if (ez2.e != null) {
                jsonObject.addProperty("underlined", ez2.e);
            }
            if (ez2.f != null) {
                jsonObject.addProperty("strikethrough", ez2.f);
            }
            if (ez2.g != null) {
                jsonObject.addProperty("obfuscated", ez2.g);
            }
            if (ez2.b != null) {
                jsonObject.add("color", jsonSerializationContext.serialize((Object)ez2.b));
            }
            if (ez2.j != null) {
                jsonObject.add("insertion", jsonSerializationContext.serialize(ez2.j));
            }
            if (ez2.h != null) {
                \u2603 = new JsonObject();
                \u2603.addProperty("action", ez2.h.a().b());
                \u2603.addProperty("value", ez2.h.b());
                jsonObject.add("clickEvent", \u2603);
            }
            if (ez2.i != null) {
                \u2603 = new JsonObject();
                \u2603.addProperty("action", ez2.i.a().b());
                \u2603.add("value", jsonSerializationContext.serialize(ez2.i.b()));
                jsonObject.add("hoverEvent", \u2603);
            }
            return jsonObject;
        }

        @Override
        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.a((ez)object, type, jsonSerializationContext);
        }

        @Override
        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.a(jsonElement, type, jsonDeserializationContext);
        }
    }
}

