/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class blq {
    private static final Logger a = LogManager.getLogger();
    private static final blp b = new blp();
    private static blq c = null;
    private static int d = -1;
    private static boolean e = true;
    private final Map<String, Object> f = Maps.newHashMap();
    private final List<String> g = Lists.newArrayList();
    private final List<Integer> h = Lists.newArrayList();
    private final List<blv> i = Lists.newArrayList();
    private final List<Integer> j = Lists.newArrayList();
    private final Map<String, blv> k = Maps.newHashMap();
    private final int l;
    private final String m;
    private final boolean n;
    private boolean o;
    private final blo p;
    private final List<Integer> q;
    private final List<String> r;
    private final blt s;
    private final blt t;

    public blq(bni bni2, String string) throws IOException {
        JsonParser jsonParser = new JsonParser();
        jy \u26032 = new jy("shaders/program/" + string + ".json");
        this.m = string;
        InputStream \u26033 = null;
        try {
            JsonArray jsonArray;
            JsonArray jsonArray2;
            \u26033 = bni2.a(\u26032).b();
            JsonObject jsonObject = jsonParser.parse(IOUtils.toString(\u26033, Charsets.UTF_8)).getAsJsonObject();
            String \u26034 = ni.h(jsonObject, "vertex");
            String \u26035 = ni.h(jsonObject, "fragment");
            JsonArray \u26036 = ni.a(jsonObject, "samplers", null);
            if (\u26036 != null) {
                int n2 = 0;
                for (JsonElement object : \u26036) {
                    try {
                        this.a(object);
                    }
                    catch (Exception exception) {
                        kc kc2 = kc.a(exception);
                        kc2.a("samplers[" + n2 + "]");
                        throw kc2;
                    }
                    ++n2;
                }
            }
            if ((jsonArray2 = ni.a(jsonObject, "attributes", null)) != null) {
                int n3 = 0;
                this.q = Lists.newArrayListWithCapacity(jsonArray2.size());
                this.r = Lists.newArrayListWithCapacity(jsonArray2.size());
                for (JsonElement jsonElement : jsonArray2) {
                    try {
                        this.r.add(ni.a(jsonElement, "attribute"));
                    }
                    catch (Exception exception) {
                        kc kc3 = kc.a(exception);
                        kc3.a("attributes[" + n3 + "]");
                        throw kc3;
                    }
                    ++n3;
                }
            } else {
                this.q = null;
                this.r = null;
            }
            if ((jsonArray = ni.a(jsonObject, "uniforms", null)) != null) {
                int n4 = 0;
                for (JsonElement jsonElement : jsonArray) {
                    try {
                        this.b(jsonElement);
                    }
                    catch (Exception exception) {
                        kc kc4 = kc.a(exception);
                        kc4.a("uniforms[" + n4 + "]");
                        throw kc4;
                    }
                    ++n4;
                }
            }
            this.p = blo.a(ni.a(jsonObject, "blend", null));
            this.n = ni.a(jsonObject, "cull", true);
            this.s = blt.a(bni2, blt.a.a, \u26034);
            this.t = blt.a(bni2, blt.a.b, \u26035);
            this.l = blu.b().c();
            blu.b().b(this);
            this.i();
            if (this.r != null) {
                for (String string2 : this.r) {
                    int n5 = bqs.b(this.l, string2);
                    this.q.add(n5);
                }
            }
        }
        catch (Exception exception) {
            try {
                kc kc5 = kc.a(exception);
                kc5.b(\u26032.a());
                throw kc5;
            }
            catch (Throwable throwable) {
                IOUtils.closeQuietly(\u26033);
                throw throwable;
            }
        }
        IOUtils.closeQuietly(\u26033);
        this.d();
    }

    public void a() {
        blu.b().a(this);
    }

    public void b() {
        bqs.d(0);
        d = -1;
        c = null;
        e = true;
        for (int i2 = 0; i2 < this.h.size(); ++i2) {
            if (this.f.get(this.g.get(i2)) == null) continue;
            bfl.g(bqs.q + i2);
            bfl.i(0);
        }
    }

    public void c() {
        this.o = false;
        c = this;
        this.p.a();
        if (this.l != d) {
            bqs.d(this.l);
            d = this.l;
        }
        if (this.n) {
            bfl.o();
        } else {
            bfl.p();
        }
        for (int i2 = 0; i2 < this.h.size(); ++i2) {
            if (this.f.get(this.g.get(i2)) == null) continue;
            bfl.g(bqs.q + i2);
            bfl.w();
            Object object = this.f.get(this.g.get(i2));
            int \u26032 = -1;
            if (object instanceof bfw) {
                \u26032 = ((bfw)object).g;
            } else if (object instanceof bmk) {
                \u26032 = ((bmk)object).b();
            } else if (object instanceof Integer) {
                \u26032 = (Integer)object;
            }
            if (\u26032 == -1) continue;
            bfl.i(\u26032);
            bqs.f(bqs.a(this.l, this.g.get(i2)), i2);
        }
        for (blv blv2 : this.i) {
            blv2.b();
        }
    }

    public void d() {
        this.o = true;
    }

    public blv a(String string) {
        if (this.k.containsKey(string)) {
            return this.k.get(string);
        }
        return null;
    }

    public blv b(String string) {
        if (this.k.containsKey(string)) {
            return this.k.get(string);
        }
        return b;
    }

    private void i() {
        int \u26032;
        int n2 = 0;
        \u2603 = 0;
        while (n2 < this.g.size()) {
            String string = this.g.get(n2);
            \u26032 = bqs.a(this.l, string);
            if (\u26032 == -1) {
                a.warn("Shader " + this.m + "could not find sampler named " + string + " in the specified shader program.");
                this.f.remove(string);
                this.g.remove(\u2603);
                --\u2603;
            } else {
                this.h.add(\u26032);
            }
            ++n2;
            ++\u2603;
        }
        for (blv \u26033 : this.i) {
            string = \u26033.a();
            \u26032 = bqs.a(this.l, string);
            if (\u26032 == -1) {
                a.warn("Could not find uniform named " + string + " in the specified" + " shader program.");
                continue;
            }
            this.j.add(\u26032);
            \u26033.b(\u26032);
            this.k.put(string, \u26033);
        }
    }

    private void a(JsonElement jsonElement) throws kc {
        JsonObject jsonObject = ni.l(jsonElement, "sampler");
        String \u26032 = ni.h(jsonObject, "name");
        if (!ni.a(jsonObject, "file")) {
            this.f.put(\u26032, null);
            this.g.add(\u26032);
            return;
        }
        this.g.add(\u26032);
    }

    public void a(String string, Object object) {
        if (this.f.containsKey(string)) {
            this.f.remove(string);
        }
        this.f.put(string, object);
        this.d();
    }

    private void b(JsonElement jsonElement) throws kc {
        Object \u260382;
        JsonObject jsonObject = ni.l(jsonElement, "uniform");
        String \u26032 = ni.h(jsonObject, "name");
        int \u26033 = blv.a(ni.h(jsonObject, "type"));
        int \u26034 = ni.m(jsonObject, "count");
        float[] \u26035 = new float[Math.max(\u26034, 16)];
        JsonArray \u26036 = ni.t(jsonObject, "values");
        if (\u26036.size() != \u26034 && \u26036.size() > 1) {
            throw new kc("Invalid amount of values specified (expected " + \u26034 + ", found " + \u26036.size() + ")");
        }
        int \u26037 = 0;
        for (Object \u260382 : \u26036) {
            try {
                \u26035[\u26037] = ni.d((JsonElement)\u260382, "value");
            }
            catch (Exception exception) {
                kc kc2 = kc.a(exception);
                kc2.a("values[" + \u26037 + "]");
                throw kc2;
            }
            ++\u26037;
        }
        if (\u26034 > 1 && \u26036.size() == 1) {
            while (\u26037 < \u26034) {
                \u26035[\u26037] = \u26035[0];
                ++\u26037;
            }
        }
        int n2 = \u26034 > 1 && \u26034 <= 4 && \u26033 < 8 ? \u26034 - 1 : 0;
        \u260382 = new blv(\u26032, \u26033 + n2, \u26034, this);
        if (\u26033 <= 3) {
            ((blv)\u260382).a((int)\u26035[0], (int)\u26035[1], (int)\u26035[2], (int)\u26035[3]);
        } else if (\u26033 <= 7) {
            ((blv)\u260382).b(\u26035[0], \u26035[1], \u26035[2], \u26035[3]);
        } else {
            ((blv)\u260382).a(\u26035);
        }
        this.i.add((blv)\u260382);
    }

    public blt e() {
        return this.s;
    }

    public blt f() {
        return this.t;
    }

    public int h() {
        return this.l;
    }
}

