/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Charsets;
import com.google.common.collect.Iterators;
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
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.IOUtils;

public class lt {
    public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    private final Map<String, a> c = Maps.newHashMap();
    private final Map<UUID, a> d = Maps.newHashMap();
    private final LinkedList<GameProfile> e = Lists.newLinkedList();
    private final MinecraftServer f;
    protected final Gson b;
    private final File g;
    private static final ParameterizedType h = new ParameterizedType(){

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{a.class};
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

    public lt(MinecraftServer minecraftServer, File file) {
        this.f = minecraftServer;
        this.g = file;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeHierarchyAdapter(a.class, new b());
        this.b = gsonBuilder.create();
        this.b();
    }

    private static GameProfile a(MinecraftServer minecraftServer, String string) {
        final GameProfile[] gameProfileArray = new GameProfile[1];
        ProfileLookupCallback \u26032 = new ProfileLookupCallback(){

            @Override
            public void onProfileLookupSucceeded(GameProfile gameProfile) {
                gameProfileArray[0] = gameProfile;
            }

            @Override
            public void onProfileLookupFailed(GameProfile gameProfile, Exception exception) {
                gameProfileArray[0] = null;
            }
        };
        minecraftServer.aE().findProfilesByNames(new String[]{string}, Agent.MINECRAFT, \u26032);
        if (!minecraftServer.af() && gameProfileArray[0] == null) {
            UUID uUID = wn.a(new GameProfile(null, string));
            GameProfile \u26033 = new GameProfile(uUID, string);
            \u26032.onProfileLookupSucceeded(\u26033);
        }
        return gameProfileArray[0];
    }

    public void a(GameProfile gameProfile) {
        this.a(gameProfile, null);
    }

    private void a(GameProfile gameProfile, Date \u260322) {
        Date \u260322;
        UUID uUID = gameProfile.getId();
        if (\u260322 == null) {
            Object object = Calendar.getInstance();
            ((Calendar)object).setTime(new Date());
            ((Calendar)object).add(2, 1);
            \u260322 = ((Calendar)object).getTime();
        }
        object = gameProfile.getName().toLowerCase(Locale.ROOT);
        a a2 = new a(gameProfile, \u260322);
        if (this.d.containsKey(uUID)) {
            \u2603 = this.d.get(uUID);
            this.c.remove(\u2603.a().getName().toLowerCase(Locale.ROOT));
            this.e.remove(gameProfile);
        }
        this.c.put(gameProfile.getName().toLowerCase(Locale.ROOT), a2);
        this.d.put(uUID, a2);
        this.e.addFirst(gameProfile);
        this.c();
    }

    public GameProfile a(String string) {
        string2 = string.toLowerCase(Locale.ROOT);
        a a2 = this.c.get(string2);
        if (a2 != null && new Date().getTime() >= a2.c.getTime()) {
            this.d.remove(a2.a().getId());
            this.c.remove(a2.a().getName().toLowerCase(Locale.ROOT));
            this.e.remove(a2.a());
            a2 = null;
        }
        if (a2 != null) {
            GameProfile gameProfile = a2.a();
            this.e.remove(gameProfile);
            this.e.addFirst(gameProfile);
        } else {
            String string2;
            GameProfile \u26032 = lt.a(this.f, string2);
            if (\u26032 != null) {
                this.a(\u26032);
                a2 = this.c.get(string2);
            }
        }
        this.c();
        return a2 == null ? null : a2.a();
    }

    public String[] a() {
        ArrayList<String> arrayList = Lists.newArrayList(this.c.keySet());
        return arrayList.toArray(new String[arrayList.size()]);
    }

    public GameProfile a(UUID uUID) {
        a a2 = this.d.get(uUID);
        return a2 == null ? null : a2.a();
    }

    private a b(UUID uUID) {
        a a2 = this.d.get(uUID);
        if (a2 != null) {
            GameProfile gameProfile = a2.a();
            this.e.remove(gameProfile);
            this.e.addFirst(gameProfile);
        }
        return a2;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Iterators could be improved
     * Loose catch block
     */
    public void b() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = Files.newReader(this.g, Charsets.UTF_8);
            List list = (List)this.b.fromJson((Reader)bufferedReader, (Type)h);
            this.c.clear();
            this.d.clear();
            this.e.clear();
            for (a a2 : Lists.reverse(list)) {
                if (a2 == null) continue;
                this.a(a2.a(), a2.b());
            }
        }
        catch (FileNotFoundException fileNotFoundException) {
            IOUtils.closeQuietly(bufferedReader);
        }
        catch (JsonParseException jsonParseException) {
            IOUtils.closeQuietly(bufferedReader);
            {
                catch (Throwable throwable) {
                    IOUtils.closeQuietly(bufferedReader);
                    throw throwable;
                }
            }
        }
        IOUtils.closeQuietly(bufferedReader);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    public void c() {
        String string = this.b.toJson(this.a(1000));
        BufferedWriter \u26032 = null;
        try {
            \u26032 = Files.newWriter(this.g, Charsets.UTF_8);
            \u26032.write(string);
        }
        catch (FileNotFoundException \u26033) {
            IOUtils.closeQuietly(\u26032);
            return;
        }
        catch (IOException \u26034) {
            IOUtils.closeQuietly(\u26032);
            return;
            {
                catch (Throwable throwable) {
                    IOUtils.closeQuietly(\u26032);
                    throw throwable;
                }
            }
        }
        IOUtils.closeQuietly(\u26032);
    }

    private List<a> a(int n2) {
        ArrayList<a> arrayList = Lists.newArrayList();
        ArrayList<GameProfile> \u26032 = Lists.newArrayList(Iterators.limit(this.e.iterator(), n2));
        for (GameProfile gameProfile : \u26032) {
            a a2 = this.b(gameProfile.getId());
            if (a2 == null) continue;
            arrayList.add(a2);
        }
        return arrayList;
    }

    class a {
        private final GameProfile b;
        private final Date c;

        private a(GameProfile gameProfile, Date date) {
            this.b = gameProfile;
            this.c = date;
        }

        public GameProfile a() {
            return this.b;
        }

        public Date b() {
            return this.c;
        }
    }

    class b
    implements JsonDeserializer<a>,
    JsonSerializer<a> {
        private b() {
        }

        public JsonElement a(a a2, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", a2.a().getName());
            UUID \u26032 = a2.a().getId();
            jsonObject.addProperty("uuid", \u26032 == null ? "" : \u26032.toString());
            jsonObject.addProperty("expiresOn", a.format(a2.b()));
            return jsonObject;
        }

        public a a(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonElement \u26032 = jsonObject.get("name");
                JsonElement \u26033 = jsonObject.get("uuid");
                JsonElement \u26034 = jsonObject.get("expiresOn");
                if (\u26032 == null || \u26033 == null) {
                    return null;
                }
                String \u26035 = \u26033.getAsString();
                String \u26036 = \u26032.getAsString();
                Date \u26037 = null;
                if (\u26034 != null) {
                    try {
                        \u26037 = a.parse(\u26034.getAsString());
                    }
                    catch (ParseException parseException) {
                        \u26037 = null;
                    }
                }
                if (\u26036 == null || \u26035 == null) {
                    return null;
                }
                try {
                    UUID uUID = UUID.fromString(\u26035);
                }
                catch (Throwable throwable) {
                    return null;
                }
                a a2 = new a(new GameProfile(uUID, \u26036), \u26037);
                return a2;
            }
            return null;
        }

        @Override
        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.a((a)object, type, jsonSerializationContext);
        }

        @Override
        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.a(jsonElement, type, jsonDeserializationContext);
        }
    }
}

