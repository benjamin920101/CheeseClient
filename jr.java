/*
 * Decompiled with CFR 0.152.
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.lang.reflect.Type;

public class jr
implements ff<jp> {
    private static final Gson a = new GsonBuilder().registerTypeAdapter((Type)((Object)js.c.class), new js.c.a()).registerTypeAdapter((Type)((Object)js.a.class), new js.a.a()).registerTypeAdapter((Type)((Object)js.class), new js.b()).registerTypeHierarchyAdapter(eu.class, new eu.a()).registerTypeHierarchyAdapter(ez.class, new ez.a()).registerTypeAdapterFactory(new nr()).create();
    private js b;

    public jr() {
    }

    public jr(js js2) {
        this.b = js2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.b = a.fromJson(em2.c(Short.MAX_VALUE), js.class);
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(a.toJson(this.b));
    }

    @Override
    public void a(jp jp2) {
        jp2.a(this);
    }

    public js a() {
        return this.b;
    }
}

