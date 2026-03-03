/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class mb<K, V extends ma<K>> {
    protected static final Logger a = LogManager.getLogger();
    protected final Gson b;
    private final File c;
    private final Map<String, V> d = Maps.newHashMap();
    private boolean e = true;
    private static final ParameterizedType f = new ParameterizedType(){

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{ma.class};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    };

    public mb(File file) {
        this.c = file;
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        gsonBuilder.registerTypeHierarchyAdapter(ma.class, new a());
        this.b = gsonBuilder.create();
    }

    public boolean b() {
        return this.e;
    }

    public void a(boolean bl2) {
        this.e = bl2;
    }

    public void a(V v2) {
        this.d.put(this.a((K)((ma)v2).f()), v2);
        try {
            this.f();
        }
        catch (IOException iOException) {
            a.warn("Could not save the list after adding a user.", (Throwable)iOException);
        }
    }

    public V b(K k2) {
        this.h();
        return (V)((ma)this.d.get(this.a(k2)));
    }

    public void c(K k2) {
        this.d.remove(this.a(k2));
        try {
            this.f();
        }
        catch (IOException iOException) {
            a.warn("Could not save the list after removing a user.", (Throwable)iOException);
        }
    }

    public String[] a() {
        return this.d.keySet().toArray(new String[this.d.size()]);
    }

    protected String a(K k2) {
        return k2.toString();
    }

    protected boolean d(K k2) {
        return this.d.containsKey(this.a(k2));
    }

    private void h() {
        ArrayList<Object> arrayList = Lists.newArrayList();
        for (ma \u26032 : this.d.values()) {
            if (!\u26032.e()) continue;
            arrayList.add(\u26032.f());
        }
        for (Object object : arrayList) {
            this.d.remove(object);
        }
    }

    protected ma<K> a(JsonObject jsonObject) {
        return new ma<Object>(null, jsonObject);
    }

    protected Map<String, V> e() {
        return this.d;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void f() throws IOException {
        Collection<V> collection = this.d.values();
        String \u26032 = this.b.toJson(collection);
        BufferedWriter \u26033 = null;
        try {
            \u26033 = Files.newWriter(this.c, Charsets.UTF_8);
            \u26033.write(\u26032);
        }
        catch (Throwable throwable) {
            IOUtils.closeQuietly(\u26033);
            throw throwable;
        }
        IOUtils.closeQuietly(\u26033);
    }

    class a
    implements JsonDeserializer<ma<K>>,
    JsonSerializer<ma<K>> {
        private a() {
        }

        public JsonElement a(ma<K> ma2, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            ma2.a(jsonObject);
            return jsonObject;
        }

        public ma<K> a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                ma \u26032 = mb.this.a(jsonObject);
                return \u26032;
            }
            return null;
        }

        @Override
        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.a((ma)object, type, jsonSerializationContext);
        }

        @Override
        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.a(jsonElement, type, jsonDeserializationContext);
        }
    }
}

