/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class lw {
    private static final Logger e = LogManager.getLogger();
    public static final File a = new File("banned-ips.txt");
    public static final File b = new File("banned-players.txt");
    public static final File c = new File("ops.txt");
    public static final File d = new File("white-list.txt");

    private static void a(MinecraftServer minecraftServer, Collection<String> collection, ProfileLookupCallback profileLookupCallback) {
        String[] stringArray = Iterators.toArray(Iterators.filter(collection.iterator(), new Predicate<String>(){

            public boolean a(String string) {
                return !nx.b(string);
            }

            @Override
            public /* synthetic */ boolean apply(Object object) {
                return this.a((String)object);
            }
        }), String.class);
        if (minecraftServer.af()) {
            minecraftServer.aE().findProfilesByNames(stringArray, Agent.MINECRAFT, profileLookupCallback);
        } else {
            for (String string : stringArray) {
                UUID uUID = wn.a(new GameProfile(null, string));
                GameProfile \u26032 = new GameProfile(uUID, string);
                profileLookupCallback.onProfileLookupSucceeded(\u26032);
            }
        }
    }

    public static String a(String string) {
        if (nx.b(string) || string.length() > 16) {
            return string;
        }
        final MinecraftServer minecraftServer = MinecraftServer.N();
        GameProfile \u26032 = minecraftServer.aF().a(string);
        if (\u26032 != null && \u26032.getId() != null) {
            return \u26032.getId().toString();
        }
        if (minecraftServer.T() || !minecraftServer.af()) {
            return wn.a(new GameProfile(null, string)).toString();
        }
        final ArrayList \u26033 = Lists.newArrayList();
        ProfileLookupCallback \u26034 = new ProfileLookupCallback(){

            @Override
            public void onProfileLookupSucceeded(GameProfile gameProfile) {
                minecraftServer.aF().a(gameProfile);
                \u26033.add(gameProfile);
            }

            @Override
            public void onProfileLookupFailed(GameProfile gameProfile, Exception exception) {
                e.warn("Could not lookup user whitelist entry for " + gameProfile.getName(), (Throwable)exception);
            }
        };
        lw.a(minecraftServer, Lists.newArrayList(string), \u26034);
        if (\u26033.size() > 0 && ((GameProfile)\u26033.get(0)).getId() != null) {
            return ((GameProfile)\u26033.get(0)).getId().toString();
        }
        return "";
    }
}

