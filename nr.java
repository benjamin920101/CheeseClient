/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

public class nr
implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<T> clazz = typeToken.getRawType();
        if (!clazz.isEnum()) {
            return null;
        }
        final HashMap<String, T> \u26032 = Maps.newHashMap();
        for (T t2 : clazz.getEnumConstants()) {
            \u26032.put(this.a(t2), t2);
        }
        return new TypeAdapter<T>(){

            @Override
            public void write(JsonWriter jsonWriter, T t2) throws IOException {
                if (t2 == null) {
                    jsonWriter.nullValue();
                } else {
                    jsonWriter.value(nr.this.a(t2));
                }
            }

            @Override
            public T read(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                    return null;
                }
                return \u26032.get(jsonReader.nextString());
            }
        };
    }

    private String a(Object object) {
        if (object instanceof Enum) {
            return ((Enum)object).name().toLowerCase(Locale.US);
        }
        return object.toString().toLowerCase(Locale.US);
    }
}

