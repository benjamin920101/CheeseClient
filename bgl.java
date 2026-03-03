/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bgl {
    private static final Logger f = LogManager.getLogger();
    static final Gson a = new GsonBuilder().registerTypeAdapter((Type)((Object)bgl.class), new b()).registerTypeAdapter((Type)((Object)bgh.class), new bgh.a()).registerTypeAdapter((Type)((Object)bgi.class), new bgi.a()).registerTypeAdapter((Type)((Object)bgk.class), new bgk.a()).registerTypeAdapter((Type)((Object)bgq.class), new bgq.a()).registerTypeAdapter((Type)((Object)bgr.class), new bgr.a()).create();
    private final List<bgh> g;
    private final boolean h;
    private final boolean i;
    private bgr j;
    public String b = "";
    protected final Map<String, String> c;
    protected bgl d;
    protected jy e;

    public static bgl a(Reader reader) {
        return a.fromJson(reader, bgl.class);
    }

    public static bgl a(String string) {
        return bgl.a(new StringReader(string));
    }

    protected bgl(List<bgh> list, Map<String, String> map, boolean bl2, boolean bl3, bgr bgr2) {
        this(null, list, map, bl2, bl3, bgr2);
    }

    protected bgl(jy jy2, Map<String, String> map, boolean bl2, boolean bl3, bgr bgr2) {
        this(jy2, Collections.emptyList(), map, bl2, bl3, bgr2);
    }

    private bgl(jy jy2, List<bgh> list, Map<String, String> map, boolean bl2, boolean bl3, bgr bgr2) {
        this.g = list;
        this.i = bl2;
        this.h = bl3;
        this.c = map;
        this.e = jy2;
        this.j = bgr2;
    }

    public List<bgh> a() {
        if (this.h()) {
            return this.d.a();
        }
        return this.g;
    }

    private boolean h() {
        return this.d != null;
    }

    public boolean b() {
        if (this.h()) {
            return this.d.b();
        }
        return this.i;
    }

    public boolean c() {
        return this.h;
    }

    public boolean d() {
        return this.e == null || this.d != null && this.d.d();
    }

    public void a(Map<jy, bgl> map) {
        if (this.e != null) {
            this.d = map.get(this.e);
        }
    }

    public boolean b(String string) {
        return !"missingno".equals(this.c(string));
    }

    public String c(String string) {
        if (!this.d(string)) {
            string = '#' + string;
        }
        return this.a(string, new a(this));
    }

    private String a(String string2, a a2) {
        String string2;
        if (this.d(string2)) {
            if (this == a2.b) {
                f.warn("Unable to resolve texture due to upward reference: " + string2 + " in " + this.b);
                return "missingno";
            }
            String string3 = this.c.get(string2.substring(1));
            if (string3 == null && this.h()) {
                string3 = this.d.a(string2, a2);
            }
            a2.b = this;
            if (string3 != null && this.d(string3)) {
                string3 = a2.a.a(string3, a2);
            }
            if (string3 == null || this.d(string3)) {
                return "missingno";
            }
            return string3;
        }
        return string2;
    }

    private boolean d(String string) {
        return string.charAt(0) == '#';
    }

    public jy e() {
        return this.e;
    }

    public bgl f() {
        return this.h() ? this.d.f() : this;
    }

    public bgr g() {
        bgq bgq2 = this.a(bgr.b.b);
        \u2603 = this.a(bgr.b.c);
        \u2603 = this.a(bgr.b.d);
        \u2603 = this.a(bgr.b.e);
        \u2603 = this.a(bgr.b.f);
        \u2603 = this.a(bgr.b.g);
        return new bgr(bgq2, \u2603, \u2603, \u2603, \u2603, \u2603);
    }

    private bgq a(bgr.b b2) {
        if (this.d != null && !this.j.c(b2)) {
            return this.d.a(b2);
        }
        return this.j.b(b2);
    }

    public static void b(Map<jy, bgl> map) {
        for (bgl bgl2 : map.values()) {
            try {
                \u2603 = bgl2.d;
                \u2603 = \u2603.d;
                while (\u2603 != \u2603) {
                    \u2603 = \u2603.d;
                    \u2603 = \u2603.d.d;
                }
                throw new c();
            }
            catch (NullPointerException nullPointerException) {
            }
        }
    }

    public static class c
    extends RuntimeException {
    }

    public static class b
    implements JsonDeserializer<bgl> {
        public bgl a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            List<bgh> \u26032 = this.a(jsonDeserializationContext, jsonObject);
            String \u26033 = this.c(jsonObject);
            boolean \u26034 = StringUtils.isEmpty(\u26033);
            boolean \u26035 = \u26032.isEmpty();
            if (\u26035 && \u26034) {
                throw new JsonParseException("BlockModel requires either elements or parent, found neither");
            }
            if (!\u26034 && !\u26035) {
                throw new JsonParseException("BlockModel requires either elements or parent, found both");
            }
            Map<String, String> \u26036 = this.b(jsonObject);
            boolean \u26037 = this.a(jsonObject);
            bgr \u26038 = bgr.a;
            if (jsonObject.has("display")) {
                \u2603 = ni.s(jsonObject, "display");
                \u26038 = (bgr)jsonDeserializationContext.deserialize(\u2603, (Type)((Object)bgr.class));
            }
            if (\u26035) {
                return new bgl(new jy(\u26033), \u26036, \u26037, true, \u26038);
            }
            return new bgl(\u26032, \u26036, \u26037, true, \u26038);
        }

        private Map<String, String> b(JsonObject jsonObject) {
            HashMap<String, String> hashMap = Maps.newHashMap();
            if (jsonObject.has("textures")) {
                JsonObject jsonObject2 = jsonObject.getAsJsonObject("textures");
                for (Map.Entry<String, JsonElement> entry : jsonObject2.entrySet()) {
                    hashMap.put(entry.getKey(), entry.getValue().getAsString());
                }
            }
            return hashMap;
        }

        private String c(JsonObject jsonObject) {
            return ni.a(jsonObject, "parent", "");
        }

        protected boolean a(JsonObject jsonObject) {
            return ni.a(jsonObject, "ambientocclusion", true);
        }

        protected List<bgh> a(JsonDeserializationContext jsonDeserializationContext, JsonObject jsonObject) {
            ArrayList<bgh> arrayList = Lists.newArrayList();
            if (jsonObject.has("elements")) {
                for (JsonElement jsonElement : ni.t(jsonObject, "elements")) {
                    arrayList.add((bgh)jsonDeserializationContext.deserialize(jsonElement, (Type)((Object)bgh.class)));
                }
            }
            return arrayList;
        }

        @Override
        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.a(jsonElement, type, jsonDeserializationContext);
        }
    }

    static final class a {
        public final bgl a;
        public bgl b;

        private a(bgl bgl2) {
            this.a = bgl2;
        }
    }
}

