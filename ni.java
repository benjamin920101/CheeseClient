/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang3.StringUtils;

public class ni {
    public static boolean a(JsonObject jsonObject, String string) {
        if (!ni.f(jsonObject, string)) {
            return false;
        }
        return jsonObject.getAsJsonPrimitive(string).isString();
    }

    public static boolean a(JsonElement jsonElement) {
        if (!jsonElement.isJsonPrimitive()) {
            return false;
        }
        return jsonElement.getAsJsonPrimitive().isString();
    }

    public static boolean c(JsonObject jsonObject, String string) {
        if (!ni.f(jsonObject, string)) {
            return false;
        }
        return jsonObject.getAsJsonPrimitive(string).isBoolean();
    }

    public static boolean d(JsonObject jsonObject, String string) {
        if (!ni.g(jsonObject, string)) {
            return false;
        }
        return jsonObject.get(string).isJsonArray();
    }

    public static boolean f(JsonObject jsonObject, String string) {
        if (!ni.g(jsonObject, string)) {
            return false;
        }
        return jsonObject.get(string).isJsonPrimitive();
    }

    public static boolean g(JsonObject jsonObject, String string) {
        if (jsonObject == null) {
            return false;
        }
        return jsonObject.get(string) != null;
    }

    public static String a(JsonElement jsonElement, String string) {
        if (jsonElement.isJsonPrimitive()) {
            return jsonElement.getAsString();
        }
        throw new JsonSyntaxException("Expected " + string + " to be a string, was " + ni.d(jsonElement));
    }

    public static String h(JsonObject jsonObject, String string) {
        if (jsonObject.has(string)) {
            return ni.a(jsonObject.get(string), string);
        }
        throw new JsonSyntaxException("Missing " + string + ", expected to find a string");
    }

    public static String a(JsonObject jsonObject, String string, String string2) {
        if (jsonObject.has(string)) {
            return ni.a(jsonObject.get(string), string);
        }
        return string2;
    }

    public static boolean b(JsonElement jsonElement, String string) {
        if (jsonElement.isJsonPrimitive()) {
            return jsonElement.getAsBoolean();
        }
        throw new JsonSyntaxException("Expected " + string + " to be a Boolean, was " + ni.d(jsonElement));
    }

    public static boolean i(JsonObject jsonObject, String string) {
        if (jsonObject.has(string)) {
            return ni.b(jsonObject.get(string), string);
        }
        throw new JsonSyntaxException("Missing " + string + ", expected to find a Boolean");
    }

    public static boolean a(JsonObject jsonObject, String string, boolean bl2) {
        if (jsonObject.has(string)) {
            return ni.b(jsonObject.get(string), string);
        }
        return bl2;
    }

    public static float d(JsonElement jsonElement, String string) {
        if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isNumber()) {
            return jsonElement.getAsFloat();
        }
        throw new JsonSyntaxException("Expected " + string + " to be a Float, was " + ni.d(jsonElement));
    }

    public static float k(JsonObject jsonObject, String string) {
        if (jsonObject.has(string)) {
            return ni.d(jsonObject.get(string), string);
        }
        throw new JsonSyntaxException("Missing " + string + ", expected to find a Float");
    }

    public static float a(JsonObject jsonObject, String string, float f2) {
        if (jsonObject.has(string)) {
            return ni.d(jsonObject.get(string), string);
        }
        return f2;
    }

    public static int f(JsonElement jsonElement, String string) {
        if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isNumber()) {
            return jsonElement.getAsInt();
        }
        throw new JsonSyntaxException("Expected " + string + " to be a Int, was " + ni.d(jsonElement));
    }

    public static int m(JsonObject jsonObject, String string) {
        if (jsonObject.has(string)) {
            return ni.f(jsonObject.get(string), string);
        }
        throw new JsonSyntaxException("Missing " + string + ", expected to find a Int");
    }

    public static int a(JsonObject jsonObject, String string, int n2) {
        if (jsonObject.has(string)) {
            return ni.f(jsonObject.get(string), string);
        }
        return n2;
    }

    public static JsonObject l(JsonElement jsonElement, String string) {
        if (jsonElement.isJsonObject()) {
            return jsonElement.getAsJsonObject();
        }
        throw new JsonSyntaxException("Expected " + string + " to be a JsonObject, was " + ni.d(jsonElement));
    }

    public static JsonObject s(JsonObject jsonObject, String string) {
        if (jsonObject.has(string)) {
            return ni.l(jsonObject.get(string), string);
        }
        throw new JsonSyntaxException("Missing " + string + ", expected to find a JsonObject");
    }

    public static JsonObject a(JsonObject jsonObject, String string, JsonObject jsonObject2) {
        if (jsonObject.has(string)) {
            return ni.l(jsonObject.get(string), string);
        }
        return jsonObject2;
    }

    public static JsonArray m(JsonElement jsonElement, String string) {
        if (jsonElement.isJsonArray()) {
            return jsonElement.getAsJsonArray();
        }
        throw new JsonSyntaxException("Expected " + string + " to be a JsonArray, was " + ni.d(jsonElement));
    }

    public static JsonArray t(JsonObject jsonObject, String string) {
        if (jsonObject.has(string)) {
            return ni.m(jsonObject.get(string), string);
        }
        throw new JsonSyntaxException("Missing " + string + ", expected to find a JsonArray");
    }

    public static JsonArray a(JsonObject jsonObject, String string, JsonArray jsonArray) {
        if (jsonObject.has(string)) {
            return ni.m(jsonObject.get(string), string);
        }
        return jsonArray;
    }

    public static String d(JsonElement jsonElement) {
        String string = StringUtils.abbreviateMiddle(String.valueOf(jsonElement), "...", 10);
        if (jsonElement == null) {
            return "null (missing)";
        }
        if (jsonElement.isJsonNull()) {
            return "null (json)";
        }
        if (jsonElement.isJsonArray()) {
            return "an array (" + string + ")";
        }
        if (jsonElement.isJsonObject()) {
            return "an object (" + string + ")";
        }
        if (jsonElement.isJsonPrimitive()) {
            JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
            if (jsonPrimitive.isNumber()) {
                return "a number (" + string + ")";
            }
            if (jsonPrimitive.isBoolean()) {
                return "a boolean (" + string + ")";
            }
        }
        return string;
    }
}

