/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class mv
extends nb {
    private static final Logger b = LogManager.getLogger();
    private final MinecraftServer c;
    private final File d;
    private final Set<mw> e = Sets.newHashSet();
    private int f = -300;
    private boolean g = false;

    public mv(MinecraftServer minecraftServer, File file) {
        this.c = minecraftServer;
        this.d = file;
    }

    public void a() {
        if (this.d.isFile()) {
            try {
                this.a.clear();
                this.a.putAll(this.a(FileUtils.readFileToString(this.d)));
            }
            catch (IOException iOException) {
                b.error("Couldn't read statistics file " + this.d, (Throwable)iOException);
            }
            catch (JsonParseException jsonParseException) {
                b.error("Couldn't parse statistics file " + this.d, (Throwable)jsonParseException);
            }
        }
    }

    public void b() {
        try {
            FileUtils.writeStringToFile(this.d, mv.a(this.a));
        }
        catch (IOException iOException) {
            b.error("Couldn't save stats", (Throwable)iOException);
        }
    }

    @Override
    public void a(wn wn2, mw mw2, int n2) {
        \u2603 = mw2.d() ? this.a(mw2) : 0;
        super.a(wn2, mw2, n2);
        this.e.add(mw2);
        if (mw2.d() && \u2603 == 0 && n2 > 0) {
            this.g = true;
            if (this.c.aB()) {
                this.c.ap().a(new fb("chat.type.achievement", wn2.f_(), mw2.j()));
            }
        }
        if (mw2.d() && \u2603 > 0 && n2 == 0) {
            this.g = true;
            if (this.c.aB()) {
                this.c.ap().a(new fb("chat.type.achievement.taken", wn2.f_(), mw2.j()));
            }
        }
    }

    public Set<mw> c() {
        HashSet<mw> hashSet = Sets.newHashSet(this.e);
        this.e.clear();
        this.g = false;
        return hashSet;
    }

    public Map<mw, my> a(String string) {
        JsonElement jsonElement = new JsonParser().parse(string);
        if (!jsonElement.isJsonObject()) {
            return Maps.newHashMap();
        }
        JsonObject \u26032 = jsonElement.getAsJsonObject();
        HashMap<mw, my> \u26033 = Maps.newHashMap();
        for (Map.Entry<String, JsonElement> entry2 : \u26032.entrySet()) {
            Map.Entry<String, JsonElement> entry2;
            mw mw2 = na.a(entry2.getKey());
            if (mw2 != null) {
                my my2 = new my();
                if (entry2.getValue().isJsonPrimitive() && entry2.getValue().getAsJsonPrimitive().isNumber()) {
                    my2.a(entry2.getValue().getAsInt());
                } else if (entry2.getValue().isJsonObject()) {
                    JsonObject jsonObject = entry2.getValue().getAsJsonObject();
                    if (jsonObject.has("value") && jsonObject.get("value").isJsonPrimitive() && jsonObject.get("value").getAsJsonPrimitive().isNumber()) {
                        my2.a(jsonObject.getAsJsonPrimitive("value").getAsInt());
                    }
                    if (jsonObject.has("progress") && mw2.l() != null) {
                        try {
                            Constructor<? extends mz> constructor = mw2.l().getConstructor(new Class[0]);
                            mz \u26034 = constructor.newInstance(new Object[0]);
                            \u26034.a(jsonObject.get("progress"));
                            my2.a(\u26034);
                        }
                        catch (Throwable throwable) {
                            b.warn("Invalid statistic progress in " + this.d, throwable);
                        }
                    }
                }
                \u26033.put(mw2, my2);
                continue;
            }
            b.warn("Invalid statistic in " + this.d + ": Don't know what " + entry2.getKey() + " is");
        }
        return \u26033;
    }

    public static String a(Map<mw, my> map) {
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<mw, my> entry : map.entrySet()) {
            if (entry.getValue().b() != null) {
                JsonObject jsonObject2 = new JsonObject();
                jsonObject2.addProperty("value", entry.getValue().a());
                try {
                    jsonObject2.add("progress", entry.getValue().b().a());
                }
                catch (Throwable \u26032) {
                    b.warn("Couldn't save statistic " + entry.getKey().e() + ": error serializing progress", \u26032);
                }
                jsonObject.add(entry.getKey().e, jsonObject2);
                continue;
            }
            jsonObject.addProperty(entry.getKey().e, entry.getValue().a());
        }
        return jsonObject.toString();
    }

    public void d() {
        for (mw mw2 : this.a.keySet()) {
            this.e.add(mw2);
        }
    }

    public void a(lf lf22) {
        lf lf22;
        int n2 = this.c.at();
        HashMap<mw, Integer> \u26032 = Maps.newHashMap();
        if (this.g || n2 - this.f > 300) {
            this.f = n2;
            for (mw mw2 : this.c()) {
                \u26032.put(mw2, this.a(mw2));
            }
        }
        lf22.a.a(new fr(\u26032));
    }

    public void b(lf lf22) {
        lf lf22;
        HashMap<mw, Integer> hashMap = Maps.newHashMap();
        for (mq mq2 : mr.e) {
            if (!this.a(mq2)) continue;
            hashMap.put(mq2, this.a((mw)mq2));
            this.e.remove(mq2);
        }
        lf22.a.a(new fr(hashMap));
    }

    public boolean e() {
        return this.g;
    }
}

